package commands;

import main.CentralException;
import model.Central;

public class ShowSupplier implements Command {

    private int id;

    public ShowSupplier(int id) {
        this.id = id;
    }

    @Override
    public void execute(Central central) throws CentralException {
        System.out.println(central.getSupplierByID(this.id));
    }

}
