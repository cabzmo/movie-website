package commands;

import java.time.LocalDate;
import java.util.List;

import main.CentralException;
import model.Central;
import model.Customer;

public class ListCustomers implements Command {

    @Override
    public void execute(Central central, LocalDate currentDate) throws CentralException {
        List<Customer> customers = central.getCustomers();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
        System.out.println(customers.size() + " customer(s)");
    }

}
