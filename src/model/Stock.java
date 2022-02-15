package model;

public class Stock {

    private int id;
    private String name;
    private int inventory;

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

    @Override
    public String toString() {
        return "Stock ID " + this.id + ": " + this.name + "\nInventory: " + this.inventory;
    }
}