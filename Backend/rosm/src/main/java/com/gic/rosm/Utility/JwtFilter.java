package com.gic.rosm.Utility;

import com.gic.rosm.Service.AuthService;
import com.gic.rosm.Service.BlackListedService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private BlackListedService blackListedService;

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain)
            throws ServletException, IOException {

        String header = req.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (blackListedService.isTokenBlacklisted(token)) {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return; // stop further processing
            }
            try {
                Claims claims = jwtUtils.extractClaims(token);

                String role = claims.get("role", String.class);
                System.out.println(role);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                claims.get("id", Long.class),
                                null,
                                List.of(new SimpleGrantedAuthority("ROLE_" + role))
                        );

                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception ignored) {}
        }

        chain.doFilter(req, res);
    }
}

