package org.java.loadbalancer.service;

import org.java.loadbalancer.data.Request;
import org.java.loadbalancer.data.Server;

import java.util.List;

public interface LoadBalancingStrategy {

    Server selectServer(List<Server> serverList, Request request);

    void onAddServer(List<Server> serverList);

    void onServerRemoval(List<Server> serverList);
}
