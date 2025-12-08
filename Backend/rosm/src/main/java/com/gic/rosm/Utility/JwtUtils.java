package com.gic.rosm.Utility;

//import com.sun.security.auth.UserPrincipal;
import com.gic.rosm.Entity.Staff;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

//import java.nio.file.attribute.UserPrincipal;
import java.util.Date;

@Component
public class JwtUtils {

    private final String SECRET = "MY_SECRET_KEY";
    private final long EXPIRATION = 1000 * 60 * 60; // 1 hour

    public String generateToken(Staff user) {
        return Jwts.builder()
                .claim("id", user.getId())
                .claim("username", user.getName())
                .claim("role", user.getRole())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }
}

