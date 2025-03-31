package org.java.vendingmachine;

public class Dispense implements State{

    @Override
    public void collectCash(VendingMachine vendingMachine, int amount) {

    }

    @Override
    public void selectItem(VendingMachine vendingMachine, String productCode) {

    }

    @Override
    public void dispenseItem(VendingMachine vendingMachine) {
        System.out.println("Dispensing item");
        vendingMachine.updateInventory(vendingMachine.getSelectedItem());

        int itemPrice = vendingMachine.getProductCodeToPriceMap().get(vendingMachine.getSelectedItem());
        int change = vendingMachine.getCollectedCash() - itemPrice;
        if(change > 0 ) System.out.println("Returning change : " + change);

        vendingMachine.reset();
    }

    @Override
    public void cancelItem(VendingMachine vendingMachine) {

    }
}
