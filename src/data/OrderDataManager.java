package data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import main.CentralException;
import model.Central;
import model.Customer;
import model.Order;
import model.Stock;

public class OrderDataManager implements DataManager {

    private final String RESOURCE = "./resources/data/orders.txt";

    @Override
    public void loadData(Central central) throws IOException, CentralException {
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                try {
                    int id = Integer.parseInt(properties[0]);
                    int customerID = Integer.parseInt(properties[1]);
                    int stockID = Integer.parseInt(properties[2]);
                    int amount = Integer.parseInt(properties[3]);
                    String stringDelivered = properties[4];
                    boolean delivered;
                    if (stringDelivered.equals("false")) {
                        delivered = false;
                    } else {
                        delivered = true;
                    }
                    String deliveredWhen = properties[5];

                    Customer customer = central.getCustomerByID(customerID);
                    Stock stock = central.getStockByID(stockID);
                    Order order = new Order(id, customer, stock, amount, delivered, deliveredWhen);
                    customer.addOrder(order);
                    stock.addOrder(order);
                    central.addOrder(order);
                } catch (NumberFormatException ex) {
                    throw new CentralException("Unable to parse order id " + properties[0] + " on line " + line_idx
                            + "\nError: " + ex);
                }
                line_idx++;
            }
        }
    }

    @Override
    public void storeData(Central central) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Order order : central.getOrders()) {
                out.print(order.getID() + SEPARATOR);
                out.print(order.getCustomer().getID() + SEPARATOR);
                out.print(order.getStock().getID() + SEPARATOR);
                out.print(order.getAmount() + SEPARATOR);
                out.print(order.getDelivered() + SEPARATOR);
                if (order.getDeliveredWhenString() != null) {
                    out.print(order.getDeliveredWhenString() + SEPARATOR);
                } else {
                    out.print("null" + SEPARATOR);
                }
                out.println();
            }
        }
    }

}
