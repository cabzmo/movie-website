package commands;

import java.time.LocalDate;

import main.CentralException;
import model.Central;
import model.Stock;

public class AddStock implements Command {

    private int supplierID;
    private String stockName;
    private int inventory;

    public AddStock(String stockName, int inventory) {
        this.stockName = stockName;
        this.inventory = inventory;
    }

    public AddStock(String stockName, int inventory, int supplierID) {
        this.stockName = stockName;
        this.inventory = inventory;
        this.supplierID = supplierID;
    }

    @Override
    public void execute(Central central, LocalDate currentDate) throws CentralException {

        if (central.getSupplierByID(supplierID) != null) {
            if (central.getStockByName(stockName) == null) {
                int maxID = 0;
                if (central.getStocks().size() > 0) {
                    int lastIndex = central.getStocks().size() - 1;
                    maxID = central.getStocks().get(lastIndex).getID();
                }

                // Stock stock = new Stock(++maxID, this.stockName, this.inventory);
                Stock stock = new Stock(++maxID, this.stockName, this.inventory, this.supplierID);
                central.addStock(stock);
            } else {
                throw new CentralException("Stock already exists\tName: " + stockName);
            }
        } else {
            throw new CentralException("Supplier does not exist");
        }

    }

}
