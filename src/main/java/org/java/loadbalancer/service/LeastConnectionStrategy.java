package org.java.loadbalancer.service;

import org.java.loadbalancer.data.Request;
import org.java.loadbalancer.data.Server;

import java.util.List;

public class LeastConnectionStrategy implements LoadBalancingStrategy{


    @Override
    public Server selectServer(List<Server> serverList, Request request) {
        int leastConnections = Integer.MAX_VALUE;
        Server selectedServer = null;
        for(Server server : serverList){
            if(leastConnections > server.getActiveConnections()){
                selectedServer = server;
                leastConnections = server.getActiveConnections();
            }
        }
        return selectedServer;
    }

    @Override
    public void onAddServer(List<Server> serverList) {
        //no impact
    }

    @Override
    public void onServerRemoval(List<Server> serverList) {
        // no impact
    }
}
