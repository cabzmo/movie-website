package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import commands.*;

public class CommandParser {

    public static Command parse(String line) throws IOException, CentralException {
        try {
            String[] parts = line.split(" ", 4);
            String cmd = parts[0];

            if (cmd.equals("addstock")) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Stock Name: ");
                String stockName = br.readLine();
                System.out.print("Inventory: ");
                int inventory = Integer.parseInt(br.readLine());
                br.close();

                return new AddStock(stockName, inventory);
            } else if (cmd.equals("addcustomer")) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Name: ");
                String customerName = br.readLine();
                System.out.print("Phone number: ");
                String phone = br.readLine();
                br.close();

                return new AddCustomer(customerName, phone);
            } else if (parts.length == 1) {
                if (line.equals("liststocks")) {
                    return new ListStocks();
                } else if (line.equals("listcustomers")) {
                    return new ListCustomers();
                } else if (line.equals("listorders")) {
                    return new ListOrders();
                } else if (line.equals("help")) {
                    return new Help();
                }
            } else if (parts.length == 2) {
                int id = Integer.parseInt(parts[1]);

                if (cmd.equals("showstock")) {
                    return new ShowStock(id);
                } else if (cmd.equals("showcustomer")) {
                    return new ShowCustomer(id);
                } else if (cmd.equals("showorder")) {
                    return new ShowOrder(id);
                }
            } else if (parts.length == 3) {
                int customerID = Integer.parseInt(parts[1]);
                int stockID = Integer.parseInt(parts[2]);

                if (cmd.equals("buy")) {
                    // return new BuyStock(customerID, stockID);
                    System.out.println("Customer with ID " + customerID + " buying stock with ID " + stockID);
                } else if (cmd.equals("return")) {
                    // return new ReturnStock(customerID, stockID);
                    System.out.println("Customer with ID " + customerID + " returning stock with ID " + stockID);
                }
            } else if (parts.length == 4) {
                int supplierID = Integer.parseInt(parts[1]);
                String stockName = parts[2];
                int inventory = Integer.parseInt(parts[3]);

                if (cmd.equals("add")) {
                    System.out.println("Supplier with ID " + supplierID + " adding stock " + stockName
                            + " of the amount " + inventory);
                }
            }
        } catch (NumberFormatException ex) {

        }

        throw new CentralException("Invalid command.");
    }
}
