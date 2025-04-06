package org.java.loadbalancer.data;

public class Server {

    private String serverId;
    private String ipAddress;
    private Integer port;
    private Integer weight; // can use Atomic Integer for multithreading scenarios
    private Integer activeConnections;

    public Server(String id, String ipAddress, Integer port, Integer weight){
        this.serverId = id;
        this.ipAddress = ipAddress;
        this.port = port;
        this.weight = weight;
        this.activeConnections = 0;
    }

    public void incrementActiveConnections(){
        activeConnections++;
    }

    public String getServerId() {
        return serverId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Integer getPort() {
        return port;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getActiveConnections() {
        return activeConnections;
    }
}
