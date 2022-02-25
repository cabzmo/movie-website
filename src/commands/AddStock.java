package commands;

import main.CentralException;
import model.Central;
import model.Stock;

public class AddStock implements Command {

    private String stockName;
    private int inventory;

    public AddStock(String stockName, int inventory) {
        this.stockName = stockName;
        this.inventory = inventory;
    }

    @Override
    public void execute(Central central) throws CentralException {

        if (central.getStockByName(stockName) == null) {
            int maxID = 0;
            if (central.getStocks().size() > 0) {
                int lastIndex = central.getStocks().size() - 1;
                maxID = central.getStocks().get(lastIndex).getID();
            }

            Stock stock = new Stock(++maxID, this.stockName, this.inventory);
            central.addStock(stock);
        } else {
            throw new CentralException("Stock already exists\tName: " + stockName);
        }
    }

}
