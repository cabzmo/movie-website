package commands;

import java.time.LocalDate;

import main.CentralException;
import model.Central;
import model.Order;
import model.Stock;

public class EditOrder implements Command {

    private int orderID;
    private int amount;

    public EditOrder(int orderID, int amount) {
        this.orderID = orderID;
        this.amount = amount;
    }

    @Override
    public void execute(Central central, LocalDate currentDate) throws CentralException {
        Order order = central.getOrderByID(orderID);
        Stock stock = order.getStock();

        int currentInventory = stock.getInventory() + order.getAmount();

        if (this.amount > currentInventory) {
            throw new CentralException("Not enough stock to complete order");
        } else {

            int difference = order.getAmount() - this.amount;

            if (difference > 0) {
                stock.addToInventory(difference);
            } else if (difference < 0) {
                stock.removeFromInventory(difference * -1);
            }
        }

        order.setAmount(amount);
    }

}
