package commands;

import java.time.LocalDate;

import main.CentralException;
import model.Central;
import model.Customer;
import model.Order;

public class ReturnOrder implements Command {

    private int customerID = 0;
    private int orderID = 0;

    public ReturnOrder(int customerID, int orderID) {
        this.customerID = customerID;
        this.orderID = orderID;
    }

    public ReturnOrder(int orderID) {
        this.orderID = orderID;
    }

    @Override
    public void execute(Central central, LocalDate currentDate) throws CentralException {
        Order order = central.getOrderByID(orderID);

        if (order.getDelivered() == true) {
            order.setReturned();
        } else {
            throw new CentralException("Order has not been delivered yet. Try cancelling order");
        }
    }
}
