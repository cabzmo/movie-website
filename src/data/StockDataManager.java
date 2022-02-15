package data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import main.CentralException;
import model.Central;
import model.Stock;

public class StockDataManager implements DataManager {

    private final String RESOURCE = "./resources/data/stocks.txt";

    @Override
    public void loadData(Central central) throws IOException, CentralException {
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                try {
                    int id = Integer.parseInt(properties[0]);
                    String name = properties[1];
                    int inventory = Integer.parseInt(properties[2]);
                    Stock stock = new Stock(id, name, inventory);
                    central.addStock(stock);
                } catch (NumberFormatException ex) {
                    throw new CentralException("Unable to parse stock id " + properties[0] + " on line " + line_idx
                            + "\nError: " + ex);
                }
                line_idx++;
            }
        }
    }

    @Override
    public void storeData(Central central) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Stock stock : central.getStocks()) {
                out.print(stock.getID() + SEPARATOR);
                out.print(stock.getName() + SEPARATOR);
                out.print(stock.getInventory() + SEPARATOR);
                out.println();
            }
        }
    }

}
