package org.java.vendingmachine;

import java.util.Map;

public class VendingMachine {

    private Integer collectedCash;
    private State state;
    private Map<String, Integer> productCodeToQuantityMap;
    private Map<String, Integer> productCodeToPriceMap;

    private String selectedItem;

    public VendingMachine(){
        this.state = new Idle();
    }

    public Integer getCollectedCash() {
        return collectedCash;
    }

    public void setCollectedCash(Integer collectedCash) {
        this.collectedCash = collectedCash;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Map<String, Integer> getProductCodeToQuantityMap() {
        return productCodeToQuantityMap;
    }

    public void setProductCodeToQuantityMap(Map<String, Integer> productCodeToQuantityMap) {
        this.productCodeToQuantityMap = productCodeToQuantityMap;
    }

    public Map<String, Integer> getProductCodeToPriceMap() {
        return productCodeToPriceMap;
    }

    public void setProductCodeToPriceMap(Map<String, Integer> productCodeToPriceMap) {
        this.productCodeToPriceMap = productCodeToPriceMap;
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void updateInventory(String productCode){
        this.productCodeToQuantityMap.put(productCode, productCodeToQuantityMap.get(productCode) - 1);

        if(productCodeToQuantityMap.get(productCode) <= 0) productCodeToQuantityMap.remove(productCode);
    }

    public void reset(){
        collectedCash = 0;
        state = new Idle();
        selectedItem = null;
    }


    public void collectCash(Integer cash){
        this.state.collectCash(this, cash);
    }

    public void selectItem(String productCode){
        this.state.selectItem(this, productCode);
    }

    public void dispense(){
        this.state.dispenseItem(this);
    }
}
