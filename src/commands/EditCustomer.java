package commands;

import java.time.LocalDate;

import main.CentralException;
import model.Central;
import model.Customer;

public class EditCustomer implements Command {

    private int customerID;
    private String newName;
    private String newPhone;

    public EditCustomer(int customerID, String newName, String newPhone) {
        this.customerID = customerID;
        this.newName = newName;
        this.newPhone = newPhone;
    }

    @Override
    public void execute(Central central, LocalDate currentDate) throws CentralException {
        Customer customer = central.getCustomerByID(customerID);

        customer.setName(newName);
        customer.setPhone(newPhone);
    }

}
