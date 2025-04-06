package org.java.loadbalancer;

import org.java.loadbalancer.data.LoadBalancer;
import org.java.loadbalancer.data.Request;
import org.java.loadbalancer.data.Server;
import org.java.loadbalancer.service.SessionManager;

import static org.java.loadbalancer.service.StrategyFactory.getStrategy;

public class Launcher {

    public static void main(String[] args) {
        SessionManager sessionManager = new SessionManager();

        LoadBalancer loadBalancer = new LoadBalancer("LB1", getStrategy("RR"),false, sessionManager);

        Server server1 = new Server("server1", "127.1.1.0", 9098, 1);
        Server server2 = new Server("server2", "127.1.1.0", 9098, 2);
        Server server3 = new Server("server3", "127.1.1.0", 9098, 3);
        Server server4 = new Server("server4", "127.1.1.0", 9098, 4);

        loadBalancer.addServer(server1);
        loadBalancer.addServer(server2);
        loadBalancer.addServer(server3);
        loadBalancer.addServer(server4);

        System.out.println();
        System.out.println("Using Round robin");
        System.out.println();

        for(int i=0;i<10;i++){
            loadBalancer.routeRequest(new Request("request "+ i, "/getResource", "sessionId"+i));
        }

        System.out.println();
        System.out.println("Using weighted Round robin");
        System.out.println();

        loadBalancer.setLoadBalancingStrategy(getStrategy("WRR"));

        for(int i=0;i<10;i++){
            loadBalancer.routeRequest(new Request("request "+ i, "/getResource", "sessionId"+i));
        }

        System.out.println();
        System.out.println("Using Least Connection strategy");
        System.out.println();

        loadBalancer.setLoadBalancingStrategy(getStrategy("LCS"));

        for(int i=0;i<10;i++){
            loadBalancer.routeRequest(new Request("request "+ i, "/getResource", "sessionId"+i));
        }

        System.out.println();
        System.out.println("Using Sticky session");
        System.out.println();

        LoadBalancer loadBalancer2 = new LoadBalancer("LB2", getStrategy("RR"),true, sessionManager);

        loadBalancer2.addServer(server1);
        loadBalancer2.addServer(server2);
        loadBalancer2.addServer(server3);
        loadBalancer2.addServer(server4);

        for(int i=0;i<10;i++){
            loadBalancer2.routeRequest(new Request("request "+ i, "/getResource", "sessionId1"));
        }

    }
}
