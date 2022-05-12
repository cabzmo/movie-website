package commands;

import java.time.LocalDate;

import main.CentralException;
import model.Central;
import model.Stock;

public class DeleteStock implements Command {

    private int stockID = 0;
    private String stockName;

    public DeleteStock(int stockID) {
        this.stockID = stockID;
    }

    public DeleteStock(String stockName) {
        this.stockName = stockName;
    }

    @Override
    public void execute(Central central, LocalDate currentDate) throws CentralException {
        Stock stock = central.getStockByID(stockID);

        if (stockID != 0) {
            if (central.getStockByID(stockID) != null) {
                stock.getSupplier().removeSuppliedStock(stock);
                central.removeStock(stock);
            } else {
                throw new CentralException("No stock found\tID: " + stockID);
            }

        } else {
            if (central.getStockByName(stockName) != null) {
                stock.getSupplier().removeSuppliedStock(stock);
                central.removeStock(central.getStockByName(stockName));
            } else {
                throw new CentralException("No stock found\tName: " + stockName);
            }
        }

    }

}
