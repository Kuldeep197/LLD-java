package org.java.loadbalancer.data;

public class Request {

    private String requestId;
    private String path;
    private String sessionId;

    public Request(String requestId, String path, String sessionId){
        this.requestId = requestId;
        this.path = path;
        this.sessionId = sessionId;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getPath() {
        return path;
    }

    public String getSessionId() {
        return sessionId;
    }
}
