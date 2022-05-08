package commands;

import java.time.LocalDate;

import main.CentralException;
import model.Central;

public class ShowSupplier implements Command {

    private int id;

    public ShowSupplier(int id) {
        this.id = id;
    }

    @Override
    public void execute(Central central, LocalDate currenDate) throws CentralException {
        System.out.println(central.getSupplierByID(this.id));
    }

}
