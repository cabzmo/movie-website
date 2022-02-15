package model;

import java.util.ArrayList;

import main.CentralException;

public class Central {

    private final ArrayList<Stock> stocks = new ArrayList<Stock>();
    private final ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
    private final ArrayList<Customer> customers = new ArrayList<Customer>();
    private final ArrayList<Order> orders = new ArrayList<Order>();

    public void addStock(Stock stock) {
        this.stocks.add(stock);
    }

    public void removeStock(Stock stock) throws CentralException {
        if (this.stocks.contains(stock)) {
            this.stocks.remove(stock);
        } else {
            throw new CentralException("Stock ID: " + stock.getID() + " does not exist in this system");
        }
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

    public void removeSupplier(Supplier supplier) throws CentralException {
        if (this.suppliers.contains(supplier)) {
            this.suppliers.remove(supplier);
        } else {
            throw new CentralException("Supplier ID: " + supplier.getID() + " does not exist in this system");
        }
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

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public void removeCustomer(Customer customer) throws CentralException {
        if (this.customers.contains(customer)) {
            this.customers.remove(customer);
        } else {
            throw new CentralException("Customer ID: " + customer.getID() + " does not exist in this system");
        }
    }

    public ArrayList<Customer> getCustomers() {
        return this.customers;
    }

    public Customer getCustomerByID(int customerID) {
        for (Customer customer : customers) {
            if (customer.getID() == customerID) {
                return customer;
            }
        }
        return null;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void removeOrder(Order order) throws CentralException {
        if (this.orders.contains(order)) {
            this.orders.remove(order);
        } else {
            throw new CentralException("Order ID: " + order.getID() + " does not exist in this system");
        }
    }

    public ArrayList<Order> getOrders() {
        return this.orders;
    }

    public Order getOrderByID(int orderID) {
        for (Order order : orders) {
            if (order.getID() == orderID) {
                return order;
            }
        }
        return null;
    }
}
