package org.java.loadbalancer.data;

import org.java.loadbalancer.service.LoadBalancingStrategy;
import org.java.loadbalancer.service.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadBalancer {

    private String name;
    private LoadBalancingStrategy loadBalancingStrategy;
    private boolean stickySessionEnabled;
    private Map<String, Server> serverIdToServerMap;
    private SessionManager sessionManager;

    public void setLoadBalancingStrategy(LoadBalancingStrategy loadBalancingStrategy) {
        this.loadBalancingStrategy = loadBalancingStrategy;
    }

    public LoadBalancer(String name, LoadBalancingStrategy loadBalancingStrategy, boolean stickySessionEnabled, SessionManager sessionManager){
        this.name = name;
        this.loadBalancingStrategy = loadBalancingStrategy;
        this.stickySessionEnabled = stickySessionEnabled;
        serverIdToServerMap = new HashMap<>();
        this.sessionManager = sessionManager;
    }
    public void routeRequest(Request request){
        if(stickySessionEnabled && sessionManager.getSessionById(request.getSessionId()) != null){
            Session session = sessionManager.getSessionById(request.getSessionId());
            Server server = serverIdToServerMap.get(session.getServerId());
            if(server != null){
                //server can process this request
                System.out.println("With Load Balancer " + name + " Server:  "+server.getServerId() + " is executing the request :  " + request.getRequestId());
                server.incrementActiveConnections();
                return;
            } else {
                sessionManager.removeSession(session.getSessionId());
            }
        }

        Server server = loadBalancingStrategy.selectServer(new ArrayList<>(serverIdToServerMap.values()), request);
        System.out.println("With Load Balancer " + name + " Server:  "+server.getServerId() + " is executing the request :  " + request.getRequestId());
        server.incrementActiveConnections();
        if(stickySessionEnabled) sessionManager.createSession(request.getSessionId(), server.getServerId());
    }

    public void addServer(Server server){
        serverIdToServerMap.put(server.getServerId(), server);
        // on server additon
    }

    public void removeServer(String serverId){
        if(serverIdToServerMap.containsKey(serverId)) serverIdToServerMap.remove(serverId);
        //on server removal
    }

}
