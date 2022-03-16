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

    public Stock getStockByID(int stockID) throws CentralException {
        for (Stock stock : stocks) {
            if (stock.getID() == stockID) {
                return stock;
            }
        }
        throw new CentralException("No stock with that ID.");
    }

    public Stock getStockByName(String stockName) throws CentralException {
        for (Stock stock : stocks) {
            if (stock.getName().equals(stockName)) {
                return stock;
            }
        }
        throw new CentralException("No stock with that Name.");
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

    public Supplier getSupplierByID(int supplierID) throws CentralException {
        for (Supplier supplier : suppliers) {
            if (supplier.getID() == supplierID) {
                return supplier;
            }
        }
        throw new CentralException("No supplier with that ID.");
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

    public Customer getCustomerByID(int customerID) throws CentralException {
        for (Customer customer : customers) {
            if (customer.getID() == customerID) {
                return customer;
            }
        }
        throw new CentralException("No customer with that ID.");
    }

    public Customer getCustomerByName(String customerName) throws CentralException {
        for (Customer customer : customers) {
            if (customer.getName() == customerName) {
                return customer;
            }
        }
        throw new CentralException("No customer with that name.");
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

    public Order getOrderByID(int orderID) throws CentralException {
        for (Order order : orders) {
            if (order.getID() == orderID) {
                return order;
            }
        }
        throw new CentralException("No order with that ID.");
    }

    public ArrayList<Order> getCustomersOrders(int customerID) throws CentralException {
        // ArrayList<Order> customersOrders = new ArrayList<Order>();
        // for (Order order : orders) {
        // // System.out.println(order.getCustomer().getID() + customerID);
        // if (order.getCustomer().getID() == customerID) {
        // customersOrders.add(order);
        // }
        // }
        // System.out.println("Customer ID: " + customerID + "\nOrders: " + orders);
        return getCustomerByID(customerID).getOrders();
        // return orders;
    }
}
