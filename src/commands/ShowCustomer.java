package commands;

import java.time.LocalDate;

import main.CentralException;
import model.Central;

public class ShowCustomer implements Command {

    private int id;

    public ShowCustomer(int id) {
        this.id = id;
    }

    @Override
    public void execute(Central central, LocalDate currentDate) throws CentralException {
        System.out.println(central.getCustomerByID(this.id));
    }

}
