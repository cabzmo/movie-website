package commands;

import java.time.LocalDate;

import main.CentralException;
import model.Central;
import model.Stock;
import model.Supplier;

public class EditStock implements Command {

    private int id;
    private String newName;
    private int newSupplierID;

    public EditStock(int id, String newName, int newSupplierID) {
        this.id = id;
        this.newName = newName;
        this.newSupplierID = newSupplierID;
    }

    @Override
    public void execute(Central central, LocalDate currentDate) throws CentralException {
        Stock stock = central.getStockByID(this.id);
        Supplier newSupplier = central.getSupplierByID(newSupplierID);
        stock.setName(newName);

        if (stock.getSupplier().getID() != newSupplier.getID()) {
            stock.getSupplier().removeSuppliedStock(stock);
            stock.setSupplier(newSupplier);
            newSupplier.addSuppliedStock(stock);
        }

    }

}
