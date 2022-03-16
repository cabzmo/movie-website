package model;

import java.util.ArrayList;

import main.CentralException;

public class Customer {

    private int id;
    private String name;
    private String phone;
    private ArrayList<Order> orders = new ArrayList<Order>();

    public Customer(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
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

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void removeOrder(Order order) throws CentralException {
        if (!this.orders.contains(order)) {
            throw new CentralException("This customer does not have order  " + order.getID());
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

    @Override
    public String toString() {
        String msg = "";
        msg += "Customer ID: " + this.id + "\t\tName: " + this.name + "\t\tPhone: " + this.phone + "\t\tOrders: [";
        for (Order order : this.orders) {
            msg += order.getID() + ", ";
        }
        msg = msg.substring(0, msg.length() - 2);
        msg += "]";
        return msg;
    }
}
