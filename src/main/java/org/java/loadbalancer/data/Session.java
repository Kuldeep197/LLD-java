package org.java.loadbalancer.data;

public class Session {

    private String sessionId;
    private String serverId;


    public Session(String sessionId, String serverId){
        this.sessionId = sessionId;
        this.serverId = serverId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getServerId() {
        return serverId;
    }
}
