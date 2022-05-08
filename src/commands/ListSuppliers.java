package commands;

import java.time.LocalDate;
import java.util.List;

import main.CentralException;
import model.Central;
import model.Supplier;

public class ListSuppliers implements Command {

    @Override
    public void execute(Central central, LocalDate currentDate) throws CentralException {
        List<Supplier> suppliers = central.getSuppliers();
        for (Supplier supplier : suppliers) {
            System.out.println(supplier);
        }
        System.out.println(suppliers.size() + " supplier(s)");
    }

}
