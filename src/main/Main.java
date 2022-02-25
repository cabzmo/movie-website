package main;

import java.io.IOException;
import java.util.ArrayList;

import commands.CompleteOrder;
import commands.MakeOrder;
import data.CentralData;
import model.Central;
import model.Customer;
import model.Order;
import model.Stock;
import model.Supplier;

public class Main {
        public static void main(String[] args) throws CentralException, IOException {

                Central central = CentralData.load();

                // for (Stock stock : central.getStocks()) {
                // System.out.println(stock + "\n");
                // }
                // System.out.println("Total products: " + central.getStocks().size());

                // Stock stock1 = new Stock(1, "Mars", 5);
                // Stock stock2 = new Stock(2, "Snickers", 10);
                // Stock stock3 = new Stock(3, "Bounty", 15);
                // Stock stock4 = new Stock(4, "Lion", 20);
                // Stock stock5 = new Stock(5, "Galaxy", 25);
                // central.addStock(stock1);
                // central.addStock(stock2);
                // central.addStock(stock3);
                // central.addStock(stock4);
                // central.addStock(stock5);
                // Supplier supplier3 = new Supplier(3, "Supplier3");

                // supplier3.addSuppliedStock(stock1);
                // supplier3.addSuppliedStock(stock2);
                // supplier3.addSuppliedStock(stock3);
                // supplier.removeSuppliedStock(stock4);
                // System.out.println(supplier);

                // for (Supplier supplier : central.getSuppliers()) {
                // System.out.println(supplier + "\n");
                // }
                // System.out.println("Total suppliers: " + central.getSuppliers().size());

                // central.removeStock(central.getStockByID(1));

                // Order order1 = new Order(1, central.getCustomerByID(1),
                // central.getStockByID(1), 2, false);
                // Order order2 = new Order(2, central.getCustomerByID(2),
                // central.getStockByID(2), 4, true);

                // central.addOrder(order1);
                // central.addOrder(order2);

                // central.removeOrder(central.getOrderByID(1));
                // central.removeOrder(central.getOrderByID(2));

                // Customer customer1 = new Customer(1, "customer1", "0000");
                // Customer customer2 = new Customer(2, "customer2", "0001");

                // central.addCustomer(customer1);
                // central.addCustomer(customer2);

                // new MakeOrder(central.getCustomerByID(1), central.getStockByID(1),
                // 2).execute(central);
                // new MakeOrder(central.getCustomerByID(2), central.getStockByID(2),
                // 4).execute(central);
                // new MakeOrder(central.getCustomerByID(1), central.getStockByID(3),
                // 2).execute(central);

                System.out.println(central.getOrderByID(1));
                System.out.println();
                System.out.println(central.getOrderByID(2));
                System.out.println();
                // new CompleteOrder(central.getOrderByID(3)).execute(central);
                System.out.println(central.getOrderByID(3));
                System.out.println();
                System.out.println(central.getCustomerByID(1));
                System.out.println();
                System.out.println(central.getCustomerByID(2));
                System.out.println();
                System.out.println(central.getStockByID(1));
                System.out.println();
                System.out.println(central.getStockByID(2));
                System.out.println();
                System.out.println(central.getStockByID(3));

                CentralData.store(central);

        }
}