package commands;

import main.CentralException;
import model.Central;

public class ReturnOrder implements Command {

    private int customerID;
    private int orderID;

    public ReturnOrder(int customerID, int orderID) {
        this.customerID = customerID;
        this.orderID = orderID;
    }

    @Override
    public void execute(Central central) throws CentralException {
        System.out.println(central.getOrderByID(orderID).getCustomer().getName());
        System.out.println(customerID);
        System.out.println(central.getOrderByID(orderID).getCustomer().getID() == customerID);
        System.out.println();
        if (central.getOrderByID(orderID).getCustomer().getID() == customerID) {
            System.out.println("Order " + orderID + " belongs to customer " + customerID);
        } else {
            throw new CentralException("Order " + orderID + " DOESN'T belong to customer " + customerID);
        }
    }
}
