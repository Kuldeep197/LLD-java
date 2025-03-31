package org.java.vendingmachine;

public interface State {

    void collectCash(VendingMachine vendingMachine, int amount);
    void selectItem(VendingMachine vendingMachine, String productCode);
    void dispenseItem(VendingMachine vendingMachine);
    void cancelItem(VendingMachine vendingMachine);
}
