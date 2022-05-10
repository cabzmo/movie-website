package commands;

import java.time.LocalDate;

import main.CentralException;
import model.Central;
import model.Customer;
import model.Order;
import model.Stock;

public class CancelOrder implements Command {

    private int orderID;

    public CancelOrder(int orderID) {
        this.orderID = orderID;
    }

    @Override
    public void execute(Central central, LocalDate currentDate) throws CentralException {
        Order order = central.getOrderByID(orderID);

        if (order.getDelivered() == false) {
            Customer customer = order.getCustomer();
            Stock stock = order.getStock();
            int amount = order.getAmount();

            stock.addToInventory(amount);
            stock.removeOrder(order);
            customer.removeOrder(order);
            central.removeOrder(order);
        } else {
            throw new CentralException("Order has been delivered and cannot be cancelled. Try returning order");
        }

    }

}
