package org.java.loadbalancer.service;

import org.java.loadbalancer.data.Session;

import java.util.HashMap;
import java.util.Map;

public class SessionManager {

    private Map<String, Session> sessionIdToSessionMap;

    public SessionManager(){
        this.sessionIdToSessionMap = new HashMap<>();
    }

    public void createSession(String sessionId, String serverId){
        sessionIdToSessionMap.putIfAbsent(sessionId, new Session(sessionId, serverId));
    }

    public Session getSessionById(String sessionId){
        return sessionIdToSessionMap.getOrDefault(sessionId, null);
    }

    public void removeSession(String sessionId){
        sessionIdToSessionMap.remove(sessionId);
    }
}
