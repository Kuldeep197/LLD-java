package org.java.vendingmachine;

import org.java.vendingmachine.exception.InsufficientCashException;
import org.java.vendingmachine.exception.InsufficientQuantityException;

public class HasMoney implements State{

    @Override
    public void collectCash(VendingMachine vendingMachine, int amount) {

    }

    @Override
    public void selectItem(VendingMachine vendingMachine, String productCode) {
        System.out.println("Selecting item");
        try {
            if (vendingMachine.getProductCodeToPriceMap().get(productCode) > vendingMachine.getCollectedCash())
                throw new InsufficientCashException("Insufficient balance");
            if (!vendingMachine.getProductCodeToQuantityMap().containsKey(productCode) || vendingMachine.getProductCodeToPriceMap().get(productCode) < 1)
                throw new InsufficientQuantityException("Insufficient Quantity");
            vendingMachine.setSelectedItem(productCode);
            vendingMachine.setState(new Dispense());
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            cancelItem(vendingMachine);
        }
    }

    @Override
    public void dispenseItem(VendingMachine vendingMachine) {

    }

    @Override
    public void cancelItem(VendingMachine vendingMachine) {
        System.out.println("Cancelling the transaction");
        vendingMachine.setState(new Idle());
        vendingMachine.setSelectedItem(null);
        vendingMachine.setCollectedCash(0);
    }
}
