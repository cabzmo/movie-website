package commands;

import main.CentralException;
import model.Central;

public class ShowCustomer implements Command {

    private int id;

    public ShowCustomer(int id) {
        this.id = id;
    }

    @Override
    public void execute(Central central) throws CentralException {
        System.out.println(central.getCustomerByID(this.id));
    }

}
