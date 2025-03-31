package org.java.vendingmachine;

public class Idle implements State{

    @Override
    public void collectCash(VendingMachine vendingMachine, int amount) {
        vendingMachine.setCollectedCash(amount);
        System.out.println("Machine has collected cash");
        vendingMachine.setState(new HasMoney());
    }

    @Override
    public void selectItem(VendingMachine vendingMachine, String productCode) {

    }

    @Override
    public void dispenseItem(VendingMachine vendingMachine) {

    }

    @Override
    public void cancelItem(VendingMachine vendingMachine) {

    }

}
