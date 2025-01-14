package org.java.notify_stock.data;

public interface StockObservable {

    void add(Observor observor);

    void remove(Observor observor);

    void notifyObservors();

    void setStock(int stock);

    int getStock();
}
