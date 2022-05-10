package model;

import java.util.ArrayList;

import main.CentralException;

public class Stock {

    private int id;
    private String name;
    private int inventory;
    private int supplierID;
    private int amountInBatch;
    private ArrayList<Order> orders = new ArrayList<Order>();
    private Supplier supplier;

    public Stock(int id, String name, int inventory) {
        this.id = id;
        this.name = name;
        this.inventory = inventory;
    }

    public Stock(int id, String name, int inventory, int supplierID) {
        this.id = id;
        this.name = name;
        this.inventory = inventory;
        this.supplierID = supplierID;
    }

    public Stock(int id, String name, int inventory, Supplier supplier) {
        this.id = id;
        this.name = name;
        this.inventory = inventory;
        this.supplierID = supplier.getID();
        this.supplier = supplier;
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

    public int getInventory() {
        return this.inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public void addToInventory(int amount) {
        this.inventory = inventory + amount;
    }

    public void removeFromInventory(int amount) {
        this.inventory = inventory - amount;
    }

    public int getInventoryBeforeOrders() {
        int inventory = this.inventory;
        for (Order order : orders) {
            inventory += order.getAmount();
        }
        return inventory;
    }

    public int getSupplierID() {
        return this.supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public int getAmountInBatch() {
        return this.amountInBatch;
    }

    public void setAmountInBatch(int amountInBatch) {
        this.amountInBatch = amountInBatch;
    }

    public void addSupplierBatch() {
        this.inventory = this.inventory + this.amountInBatch;
    }

    public void addSupplierBatch(int multiplier) {
        this.inventory = this.inventory + (this.amountInBatch * multiplier);
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void removeOrder(Order order) throws CentralException {
        if (!this.orders.contains(order)) {
            throw new CentralException("Order " + order.getID() + " does not require this stock");
        }
        this.orders.remove(order);
    }

    public boolean checkOrder(Order order) {
        if (this.orders.contains(order)) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Order> getOrders() {
        return this.orders;
    }

    public ArrayList<Integer> getOrderIDs() {
        ArrayList<Integer> orderIDs = new ArrayList<Integer>();
        for (Order order : orders) {
            orderIDs.add(order.getID());
        }
        return orderIDs;
    }

    public ArrayList<Order> getAwaitingOrders() {
        ArrayList<Order> awaitingOrders = new ArrayList<Order>();
        for (Order order : orders) {
            if (!order.getDelivered()) {
                awaitingOrders.add(order);
            }
        }
        return awaitingOrders;
    }

    public ArrayList<Order> getCompleteOrders() {
        ArrayList<Order> completedOrders = new ArrayList<Order>();
        for (Order order : orders) {
            if (order.getDelivered()) {
                completedOrders.add(order);
            }
        }
        return completedOrders;
    }

    public Supplier getSupplier() {
        return this.supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getDetailsShort() {
        return "Stock #" + this.id + " - " + this.name;
    }

    @Override
    public String toString() {
        String msg = "";
        msg += "Stock ID " + this.id + ": " + this.name + "\t\tInventory: " + this.inventory + "\t\tOld Inventory: "
                + this.getInventoryBeforeOrders() + "\t\tOrders: [";
        if (orders.size() != 0) {
            for (Order order : this.orders) {
                msg += order.getID() + ", ";
            }
            msg = msg.substring(0, msg.length() - 2);
        }
        msg += "]";
        return msg;
    }
}