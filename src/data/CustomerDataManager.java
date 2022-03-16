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

public class CustomerDataManager implements DataManager {

    private final String RESOURCE = "./resources/data/customers.txt";

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
                    String phone = properties[2];
                    Customer customer = new Customer(id, name, phone);
                    central.addCustomer(customer);
                } catch (NumberFormatException ex) {
                    throw new CentralException("Unable to parse customer id " + properties[0] + " on line " + line_idx
                            + "\nError: " + ex);
                }
                line_idx++;
            }
        }
    }

    @Override
    public void storeData(Central central) throws IOException, CentralException {
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Customer customer : central.getCustomers()) {
                out.print(customer.getID() + SEPARATOR);
                out.print(customer.getName() + SEPARATOR);
                out.print(customer.getPhone() + SEPARATOR);
                String msg = "";
                msg += "[";
                if (central.getCustomersOrders(customer.getID()).size() > 0) {
                    for (Order order : central.getCustomersOrders(customer.getID())) {
                        msg += order.getID() + ",";
                    }
                    msg = msg.substring(0, msg.length() - 1);
                }
                msg += "]" + SEPARATOR;
                out.print(msg);
                out.println();
            }
        }
    }

}
