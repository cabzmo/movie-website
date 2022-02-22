package model;

import java.util.ArrayList;

import main.CentralException;

public class Stock {

    private int id;
    private String name;
    private int inventory;
    private int amountInBatch;
    private ArrayList<Order> orders = new ArrayList<Order>();

    public Stock(int id, String name, int inventory) {
        this.id = id;
        this.name = name;
        this.inventory = inventory;
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

    @Override
    public String toString() {
        String msg = "";
        msg += "Stock ID " + this.id + ": " + this.name + "\nInventory: " + this.inventory + "\nOld Inventory: "
                + this.getInventoryBeforeOrders() + "\nOrders: ";

        for (Order order : this.orders) {
            msg += order.getID() + ", ";
        }
        msg = msg.substring(0, msg.length() - 2);
        return msg;
    }
}