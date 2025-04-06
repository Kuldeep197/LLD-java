package org.java.loadbalancer.service;

import org.java.loadbalancer.data.Request;
import org.java.loadbalancer.data.Server;

import java.util.List;

public class RoundRobinStrategy implements LoadBalancingStrategy{
    private Integer counter = 0;

    @Override
    public Server selectServer(List<Server> serverList, Request request) {
        if(serverList.isEmpty()) return null;
        int index = counter % serverList.size();
        counter++;
        return serverList.get(index);
    }

    @Override
    public void onAddServer(List<Server> serverList) {
        //No action needed
    }

    @Override
    public void onServerRemoval(List<Server> serverList) {
        //No action needed
    }
}
