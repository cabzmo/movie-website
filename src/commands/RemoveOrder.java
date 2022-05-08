package commands;

import java.time.LocalDate;

import main.CentralException;
import model.Central;

public class RemoveOrder implements Command {

    private int orderID;

    public RemoveOrder(int orderID) {
        this.orderID = orderID;
    }

    @Override
    public void execute(Central central, LocalDate currentDate) throws CentralException {

        if (central.getOrderByID(orderID) != null) {
            central.removeOrder(central.getOrderByID(orderID));
        } else {
            throw new CentralException("No order found\tID: " + orderID);
        }

    }

}
