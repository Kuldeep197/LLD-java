package org.java.loadbalancer.service;

import org.java.loadbalancer.data.Request;
import org.java.loadbalancer.data.Server;

import java.util.ArrayList;
import java.util.List;

public class WeightedRoundRobingStrategy implements LoadBalancingStrategy{

    private Integer counter = 0;
    private List<Server> servers = new ArrayList<>();

    @Override
    public Server selectServer(List<Server> serverList, Request request) {
        if(servers.isEmpty()) spreadServersInList(serverList);
        int index = counter % servers.size();
        counter++;
        return servers.get(index);
    }

    @Override
    public void onAddServer(List<Server> serverList) {
        spreadServersInList(serverList);
    }

    @Override
    public void onServerRemoval(List<Server> serverList) {
        spreadServersInList(serverList);
    }

    public void spreadServersInList(List<Server> servers){
        List<Server> serverList = new ArrayList<>();
        for(Server server : servers){
            for(int i=0;i<server.getWeight();i++)
                serverList.add(server);
        }
        this.servers = serverList;
    }
}
