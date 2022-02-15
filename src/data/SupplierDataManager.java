package data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import main.CentralException;
import model.Central;
import model.Stock;
import model.Supplier;

public class SupplierDataManager implements DataManager {

    private final String RESOURCE = "./resources/data/suppliers.txt";

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

                    String numWithCommas = properties[2].substring(1, properties[2].length() - 1);
                    String[] stringSuppliedStocks = numWithCommas.split(",", -1);

                    ArrayList<Stock> stockIDs = new ArrayList<Stock>();
                    for (String stringStockID : stringSuppliedStocks) {
                        int stockID = Integer.parseInt(stringStockID);
                        stockIDs.add(central.getStockByID(stockID));
                    }

                    Supplier supplier = new Supplier(id, name, stockIDs);
                    central.addSupplier(supplier);
                } catch (NumberFormatException ex) {
                    throw new CentralException("Unable to parse supplier id " + properties[0] + " on line " + line_idx
                            + "\nError: " + ex);
                }
                line_idx++;
            }
        }
    }

    @Override
    public void storeData(Central central) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Supplier supplier : central.getSuppliers()) {
                out.print(supplier.getID() + SEPARATOR);
                out.print(supplier.getName() + SEPARATOR);
                String arrayString = "[";
                for (Stock stock : supplier.getSuppliedStocks()) {
                    arrayString += stock.getID() + ",";
                }
                arrayString = arrayString.substring(0, arrayString.length() - 1);
                arrayString += "]" + SEPARATOR;
                out.print(arrayString);
                out.println();
            }
        }
    }

}
