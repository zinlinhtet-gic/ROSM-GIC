package com.gic.rosm.Utility;

//import com.sun.security.auth.UserPrincipal;
import com.gic.rosm.Entity.Staff;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

//import java.nio.file.attribute.UserPrincipal;
import java.util.Date;

@Component
public class JwtUtils {

    private final String SECRET;
    private final long EXPIRATION; // 1 hour

    public JwtUtils() {
        String profile = System.getProperty("spring.profiles.active", "local");
        if ("docker".equals(profile)) {
            Dotenv dotenv = Dotenv.configure().directory("./").load();
            this.SECRET = dotenv.get("JWT_SECRET");
            this.EXPIRATION = Long.parseLong(dotenv.get("JWT_EXPIRE_MS"));
        } else if ("test".equals(profile)) {
            this.SECRET = "testsecretkey";
            this.EXPIRATION = 3600000;
        }
        else{
            Dotenv dotenv = Dotenv.configure().directory("./").load();
            this.SECRET = dotenv.get("JWT_SECRET");
            this.EXPIRATION = Long.parseLong(dotenv.get("JWT_EXPIRE_MS"));
        }
    }

    public String generateToken(Staff user) {
        return Jwts.builder()
                .claim("id", user.getId())
                .claim("username", user.getName())
                .claim("role", user.getRole().toString())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }
}

