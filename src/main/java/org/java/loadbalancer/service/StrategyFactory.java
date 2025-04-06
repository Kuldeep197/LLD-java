package org.java.loadbalancer.service;

public class StrategyFactory {

    public static LoadBalancingStrategy getStrategy(String identifier){
        if(identifier.equalsIgnoreCase("RR"))
            return new RoundRobinStrategy();
        if(identifier.equalsIgnoreCase("WRR"))
            return new WeightedRoundRobingStrategy();
        if(identifier.equalsIgnoreCase("LCS"))
            return new LeastConnectionStrategy();
        return null;
    }
}
