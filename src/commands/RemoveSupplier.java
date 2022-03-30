package commands;

import main.CentralException;
import model.Central;

public class RemoveSupplier implements Command {

    private int supplierID = 0;
    private String supplierName;

    public RemoveSupplier(int supplierID) {
        this.supplierID = supplierID;
    }

    public RemoveSupplier(String supplierName) {
        this.supplierName = supplierName;
    }

    @Override
    public void execute(Central central) throws CentralException {

        if (supplierID != 0) {
            if (central.getSupplierByID(supplierID) != null) {
                central.removeSupplier(central.getSupplierByID(supplierID));
            } else {
                throw new CentralException("No supplier found\tID: " + supplierID);
            }

        } else {
            if (central.getSupplierByName(supplierName) != null) {
                central.removeSupplier(central.getSupplierByName(supplierName));
            } else {
                throw new CentralException("No supplier found\tName: " + supplierName);
            }
        }
    }

}
