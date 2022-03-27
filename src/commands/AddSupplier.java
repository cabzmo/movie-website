package commands;

import main.CentralException;
import model.*;

public class AddSupplier implements Command {

    private String supplierName;

    public AddSupplier(String supplierName) {
        this.supplierName = supplierName;
    }

    @Override
    public void execute(Central central) throws CentralException {

        try {
            if (central.getSupplierByName(supplierName) == null) {
                int maxID = 0;
                if (central.getSuppliers().size() > 0) {
                    int lastIndex = central.getSuppliers().size() - 1;
                    maxID = central.getSuppliers().get(lastIndex).getID();
                }

                Supplier supplier = new Supplier(++maxID, supplierName);
                central.addSupplier(supplier);
            }
        } catch (Exception e) {
            throw new CentralException("Supplier already exists\t Supplier Name: " + supplierName);
        }
    }
}
