package org.java.notify_stock.data;

import java.util.ArrayList;
import java.util.List;

public class StockObservableImpl implements StockObservable{
    List<Observor> observors = new ArrayList<>();
    Integer stock;

    @Override
    public void add(Observor observor) {
        observors.add(observor);
    }

    @Override
    public void remove(Observor observor) {
        observors.remove(observor);
    }

    @Override
    public void notifyObservors() {
        for(Observor observor : observors){
            observor.update();
        }
    }

    @Override
    public void setStock(int stock) {
        this.stock = stock;
        notifyObservors();
    }

    @Override
    public int getStock() {
        return stock;
    }
}
