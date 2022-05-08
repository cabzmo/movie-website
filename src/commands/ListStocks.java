package commands;

import java.time.LocalDate;
import java.util.List;

import main.CentralException;
import model.Central;
import model.Stock;

public class ListStocks implements Command {

    @Override
    public void execute(Central central, LocalDate currentDate) throws CentralException {
        List<Stock> stocks = central.getStocks();
        for (Stock stock : stocks) {
            System.out.println(stock);
        }
        System.out.println(stocks.size() + " stock(s)");
    }

}
