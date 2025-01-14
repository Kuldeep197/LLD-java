package org.java.notify_stock;

import org.java.notify_stock.data.IphoneProduct;
import org.java.notify_stock.data.StockObservableImpl;

public class Launcher {

    public static void main(String[] args) {
        StockObservableImpl stockObservable = new StockObservableImpl();
        IphoneProduct iphoneProduct = new IphoneProduct(stockObservable);
        stockObservable.setStock(23);
        stockObservable.remove(iphoneProduct); // removing observor
        stockObservable.setStock(24); // now there are not observors to notify
    }
}
