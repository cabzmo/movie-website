package commands;

import main.CentralException;
import model.Central;

public class RemoveCustomer implements Command {

    private int customerID = 0;
    private String customerName;

    public RemoveCustomer(int customerID) {
        this.customerID = customerID;
    }

    public RemoveCustomer(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public void execute(Central central) throws CentralException {

        if (customerID != 0) {
            if (central.getCustomerByID(customerID) != null) {
                central.removeCustomer(central.getCustomerByID(customerID));
            } else {
                throw new CentralException("No customer found\tID: " + customerID);
            }

        } else {
            if (central.getCustomerByName(customerName) != null) {
                central.removeCustomer(central.getCustomerByName(customerName));
            } else {
                throw new CentralException("No customer found\tName: " + customerName);
            }
        }
    }

}
