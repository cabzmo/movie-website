package commands;

import java.time.LocalDate;

import main.CentralException;
import model.Central;
import model.Supplier;

public class EditSupplier implements Command {

    private int supplierID;
    private String name;

    public EditSupplier(int supplierID, String name) {
        this.supplierID = supplierID;
        this.name = name;
    }

    @Override
    public void execute(Central central, LocalDate currentDate) throws CentralException {
        Supplier supplier = central.getSupplierByID(supplierID);

        supplier.setName(name);
    }

}
