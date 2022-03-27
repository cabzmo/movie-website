package commands;

import main.CentralException;
import model.Central;
import model.Stock;
import model.Supplier;

public class ChangeStockSupplier implements Command {

    private int stockID;
    private int oldSupplierID;
    private int newSupplierID;

    public ChangeStockSupplier(int stockID, int oldSupplierID, int newSupplierID) throws CentralException {
        this.stockID = stockID;
        this.oldSupplierID = oldSupplierID;
        this.newSupplierID = newSupplierID;
    }

    public void check(Stock stock, Supplier oldSupplier, Supplier newSupplier) {
        System.out.println(stock);
        System.out.println(oldSupplier);
        System.out.println(newSupplier);
    }

    @Override
    public void execute(Central central) throws CentralException {

        Stock stock = central.getStockByID(stockID);
        Supplier oldSupplier = central.getSupplierByID(oldSupplierID);
        Supplier newSupplier = central.getSupplierByID(newSupplierID);

        check(stock, oldSupplier, newSupplier);

        // oldSupplier = this.stock.getSupplier();
        // oldSupplier.removeSuppliedStock(this.stock);

        // this.stock.setSupplier(this.newSupplier);
        // this.newSupplier.addSuppliedStock(this.stock);

        check(stock, oldSupplier, newSupplier);
    }
}
