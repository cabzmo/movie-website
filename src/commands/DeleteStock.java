package commands;

import java.time.LocalDate;

import main.CentralException;
import model.Central;

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

        if (stockID != 0) {
            if (central.getStockByID(stockID) != null) {
                central.removeStock(central.getStockByID(stockID));
            } else {
                throw new CentralException("No stock found\tID: " + stockID);
            }

        } else {
            if (central.getStockByName(stockName) != null) {
                central.removeStock(central.getStockByName(stockName));
            } else {
                throw new CentralException("No stock found\tName: " + stockName);
            }
        }

    }

}
