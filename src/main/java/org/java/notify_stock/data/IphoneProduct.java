package org.java.notify_stock.data;

public class IphoneProduct implements Product, Observor{

    private final StockObservableImpl stockObservable;

    public IphoneProduct(StockObservableImpl stockObservable){
        this.stockObservable = stockObservable;
        stockObservable.add(this);
    }

    @Override
    public void update() {
        int stock = stockObservable.getStock();
        System.out.println("Hey we got new iphones in our stock, last " + stock + " remaining, hurry up!" );
    }
}
