package main;

import java.io.IOException;
import java.util.ArrayList;

import data.CentralData;
import model.Central;
import model.Stock;
import model.Supplier;

public class Main {
    public static void main(String[] args) throws CentralException, IOException {

        Central central = CentralData.load();

        // for (Stock stock : central.getStocks()) {
        // System.out.println(stock + "\n");
        // }
        // System.out.println("Total products: " + central.getStocks().size());

        Stock stock1 = new Stock(1, "Mars", 5);
        Stock stock2 = new Stock(2, "Snickers", 10);
        Stock stock3 = new Stock(3, "Bounty", 15);
        Stock stock4 = new Stock(4, "Lion", 20);
        Stock stock5 = new Stock(5, "Galaxy", 25);
        Supplier supplier3 = new Supplier(3, "Supplier3");

        supplier3.addSuppliedStock(stock1);
        // supplier3.addSuppliedStock(stock2);
        supplier3.addSuppliedStock(stock3);
        // supplier.removeSuppliedStock(stock4);
        // System.out.println(supplier);

        // for (Supplier supplier : central.getSuppliers()) {
        // System.out.println(supplier + "\n");
        // }
        // System.out.println("Total suppliers: " + central.getSuppliers().size());

        central.removeStock(central.getStockByID(1));

        CentralData.store(central);
    }
}