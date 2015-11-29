package com.ifi.authentication.repository;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

@Component
public class SessionRepository {

    private HashSet<String> repository = new HashSet<String>();

    public String getNewSession(){
        String sessionId = String.valueOf(UUID.randomUUID());
        repository.add(sessionId);
        return sessionId;
    }

    public void endSession(String sessionId){
        repository.remove(sessionId);
    }

    public boolean isSessionActive(String sessionId){
        return repository.contains(sessionId);
    }
}
