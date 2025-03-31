package org.java.vendingmachine;

import java.util.HashMap;
import java.util.Map;

public class Launcher {

    public static void main(String[] args) {

        //Initialising Vending machine
        VendingMachine vendingMachine = new VendingMachine();

        Map<String, Integer> map = new HashMap<>();
        map.put("101", 1);

        Map<String, Integer> priceMap = new HashMap<>();
        priceMap.put("101", 10);
        vendingMachine.setProductCodeToPriceMap(priceMap);
        vendingMachine.setProductCodeToQuantityMap(map);

        //Test-1 happy flow
        vendingMachine.collectCash(11);
        vendingMachine.selectItem("101");
        vendingMachine.dispense();

        //Test-2, when item is out of stock
        vendingMachine.collectCash(11);
        vendingMachine.selectItem("101");
        vendingMachine.dispense();
    }
}
