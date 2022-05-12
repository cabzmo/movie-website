package commands;

import java.time.LocalDate;

import main.CentralException;
import model.Central;
import model.Stock;

public class AddToInventory implements Command {

    private int stockID;
    private int amount;

    public AddToInventory(int stockID, int amount) {
        this.stockID = stockID;
        this.amount = amount;
    }

    @Override
    public void execute(Central central, LocalDate currentDate) throws CentralException {
        Stock stock = central.getStockByID(stockID);

        stock.addToInventory(amount);
    }

}
