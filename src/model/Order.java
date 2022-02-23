package model;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Order {

    private int id;
    private Customer customer;
    private Stock stock;
    private int amount;
    private boolean delivered;
    private LocalDateTime deliveredWhen;

    public Order(int id, Customer customer, Stock stock, int amount) {
        this.id = id;
        this.customer = customer;
        this.stock = stock;
        this.amount = amount;
        this.delivered = false;
    }

    public Order(int id, Customer customer, Stock stock, int amount, boolean delivered, String deliveredWhen) {
        this.id = id;
        this.customer = customer;
        this.stock = stock;
        this.amount = amount;
        this.delivered = delivered;
        if (deliveredWhen.equals("null")) {
            this.deliveredWhen = null;
        } else {
            System.out.println("deliveredWhen: " + deliveredWhen);
            this.deliveredWhen = parseDeliveredWhenString(deliveredWhen);
        }
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
        this.deliveredWhen = LocalDateTime.now();
    }

    public void setDelivered(String deliveredWhen) {
        this.delivered = true;
        this.deliveredWhen = parseDeliveredWhenString(deliveredWhen);
    }

    public LocalDateTime parseDeliveredWhenString(String deliveredWhen) {
        String[] spliteDateTime = deliveredWhen.split(" ", -1);
        String date = spliteDateTime[0];
        String time = spliteDateTime[1];

        String[] splitDate = date.split("/", -1);
        int dayOfMonth = Integer.parseInt(splitDate[0]);
        int month = Integer.parseInt(splitDate[1]);
        int year = Integer.parseInt(splitDate[2]);

        String[] splitTime = time.split(":", -1);
        int hour = Integer.parseInt(splitTime[0]);
        int minute = Integer.parseInt(splitTime[1]);
        int second = Integer.parseInt(splitTime[2]);

        LocalDateTime dateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
        return dateTime;
    }

    public LocalDateTime getDeliveredWhen() {
        return this.deliveredWhen;
    }

    public String getDeliveredWhenString() {
        if (this.deliveredWhen == null) {
            return null;
        } else {
            return this.deliveredWhen.getDayOfMonth() + "/"
                    + this.deliveredWhen.getMonthValue() + "/"
                    + this.deliveredWhen.getYear() + " "
                    + this.deliveredWhen.getHour() + ":"
                    + this.deliveredWhen.getMinute() + ":"
                    + this.deliveredWhen.getSecond();
        }
    }

    @Override
    public String toString() {
        String msg = "Order ID: " + this.id + " Customer: " + this.customer.getID() + " Stock: " + this.stock.getID()
                + " Amount: "
                + this.amount
                + " Delivered: "
                + this.delivered;
        if (deliveredWhen != null) {
            msg += " When: " + getDeliveredWhenString();
        }
        return msg;
    }

}
