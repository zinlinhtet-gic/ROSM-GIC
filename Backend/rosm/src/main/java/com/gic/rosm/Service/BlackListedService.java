package com.gic.rosm.Service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BlackListedService {

    private final Set<String> loggedOutTokens = new HashSet<>();

    public void addTokenToBlacklist(String token) {
        loggedOutTokens.add(token);
    }

    public boolean isTokenBlacklisted(String token) {
        return loggedOutTokens.contains(token);
    }
}
