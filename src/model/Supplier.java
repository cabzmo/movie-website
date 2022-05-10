package model;

import java.util.ArrayList;

import main.CentralException;

public class Supplier {

    private int id;
    private String name;
    private ArrayList<Stock> suppliedStocks = new ArrayList<Stock>();

    public Supplier(int id, String name, ArrayList<Stock> suppliedStocks) {
        this.id = id;
        this.name = name;
        this.suppliedStocks = suppliedStocks;
    }

    public Supplier(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Stock> getSuppliedStocks() {
        return this.suppliedStocks;
    }

    public void setSuppliedStocks(ArrayList<Stock> suppliedStocks) {
        this.suppliedStocks = suppliedStocks;
    }

    public void addSuppliedStock(Stock stock) throws CentralException {
        if (!this.suppliedStocks.contains(stock)) {
            this.suppliedStocks.add(stock);
        } else {
            throw new CentralException("Stock ID " + stock.getID() + " already supplied by supplier");
        }
    }

    public void removeSuppliedStock(Stock stock) throws CentralException {
        if (this.suppliedStocks.contains(stock)) {
            this.suppliedStocks.remove(stock);
        } else {
            throw new CentralException("Stock ID " + stock.getID() + " not supplied by supplier");
        }
    }

    public String getDetailsShort() {
        return "Supplier #" + this.id + " - " + this.name;
    }

    @Override
    public String toString() {
        String msg = "Supplier ID " + this.id + ": " + this.name + "\nStock IDs: ";
        for (Stock stock : this.suppliedStocks) {
            msg += stock.getID() + ", ";
        }
        msg = msg.substring(0, msg.length() - 2);
        return msg;
    }

}
