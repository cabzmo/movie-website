package model;

import java.util.ArrayList;

public class Central {

    private final ArrayList<Stock> stocks = new ArrayList<Stock>();
    private final ArrayList<Supplier> suppliers = new ArrayList<Supplier>();

    public void addStock(Stock stock) {
        this.stocks.add(stock);
    }

    public ArrayList<Stock> getStocks() {
        return this.stocks;
    }

    public Stock getStockByID(int stockID) {
        for (Stock stock : stocks) {
            if (stock.getID() == stockID) {
                return stock;
            }
        }
        return null;
    }

    public void addSupplier(Supplier supplier) {
        this.suppliers.add(supplier);
    }

    public ArrayList<Supplier> getSuppliers() {
        return this.suppliers;
    }

    public Supplier getSupplierByID(int supplierID) {
        for (Supplier supplier : suppliers) {
            if (supplier.getID() == supplierID) {
                return supplier;
            }
        }
        return null;
    }
}
