package commands;

import main.CentralException;
import model.Central;
import model.Customer;

public class AddCustomer implements Command {

    private String customerName;
    private String phone;

    public AddCustomer(String customerName, String phone) {
        this.customerName = customerName;
        this.phone = phone;
    }

    @Override
    public void execute(Central central) throws CentralException {

        try {
            if (central.getCustomerByName(customerName) == null) {
                int maxID = 0;
                if (central.getCustomers().size() > 0) {
                    int lastIndex = central.getCustomers().size() - 1;
                    maxID = central.getCustomers().get(lastIndex).getID();
                }

                Customer customer = new Customer(++maxID, customerName, phone);
                central.addCustomer(customer);
            }
        } catch (Exception e) {
            throw new CentralException("Customer already exists\t Customer Name: " + customerName);
        }

    }

}
