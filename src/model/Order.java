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
    private boolean returned;
    private LocalDateTime returnedWhen;

    public Order(int id, Customer customer, Stock stock, int amount) {
        this.id = id;
        this.customer = customer;
        this.stock = stock;
        this.amount = amount;
        this.delivered = false;
        this.returned = false;
    }

    public Order(int id, Customer customer, Stock stock, int amount, boolean delivered, String deliveredWhen,
            boolean returned, String returnedWhen) {
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
        this.returned = returned;
        if (returnedWhen.equals("null")) {
            this.returnedWhen = null;
        } else {
            System.out.println("returnedWhen: " + returnedWhen);
            this.returnedWhen = parseDeliveredWhenString(returnedWhen);
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

    public boolean getReturned() {
        return this.returned;
    }

    public void setReturned() {
        this.returned = true;
        this.returnedWhen = LocalDateTime.now();
    }

    public void setReturned(String returnedWhen) {
        this.returned = true;
        this.returnedWhen = parseDeliveredWhenString(returnedWhen);
    }

    public LocalDateTime getReturnedWhen() {
        return this.returnedWhen;
    }

    public String getReturnedWhenString() {
        if (this.returnedWhen == null) {
            return null;
        } else {
            return this.returnedWhen.getDayOfMonth() + "/"
                    + this.returnedWhen.getMonthValue() + "/"
                    + this.returnedWhen.getYear() + " "
                    + this.returnedWhen.getHour() + ":"
                    + this.returnedWhen.getMinute() + ":"
                    + this.returnedWhen.getSecond();
        }
    }

    public String getDetailsShort() {
        return "Order #" + this.id + " - " + this.customer.getName() + " - " + this.stock.getName() + " - "
                + this.amount;
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
        msg += " Returned: "
                + this.returned;
        if (returnedWhen != null) {
            msg += " When: " + getReturnedWhenString();
        }
        return msg;
    }

}
