package model;

public class Order {

    private int id;
    private Customer customer;
    private Stock stock;
    private int amount;
    private boolean delivered;

    public Order(int id, Customer customer, Stock stock, int amount) {
        this.id = id;
        this.customer = customer;
        this.stock = stock;
        this.amount = amount;
        this.delivered = false;
    }

    public Order(int id, Customer customer, Stock stock, int amount, boolean delivered) {
        this.id = id;
        this.customer = customer;
        this.stock = stock;
        this.amount = amount;
        this.delivered = delivered;
    }

    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Stock getStock() {
        return this.stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean getDelivered() {
        return this.delivered;
    }

    public void setDelivered() {
        this.delivered = true;
    }

}
