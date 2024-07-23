package com.futureboost.FutureBoostify.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Data
public class TokenBlacklistService {
    private Set<String> blacklist = Collections.synchronizedSet(new HashSet<>());

    public void addToBlacklist(String token) {
        synchronized (blacklist) {
            blacklist.add(token);
        }
    }

    public boolean isBlacklisted(String token) {
        synchronized (blacklist) {
            return blacklist.contains(token);
        }
    }
}
