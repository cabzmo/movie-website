package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import commands.*;

public class CommandParser {

    public static Command parse(String line, BufferedReader br) throws IOException, CentralException {
        try {
            String[] parts = line.split(" ", 4);
            String cmd = parts[0];

            if (cmd.equals("addstock")) {
                // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Supplier id: ");
                int supplierID = Integer.parseInt(br.readLine());
                System.out.print("Stock Name: ");
                String stockName = br.readLine();
                System.out.print("Inventory: ");
                int inventory = Integer.parseInt(br.readLine());
                // br.close();

                return new AddStock(stockName, inventory, supplierID);
            } else if (cmd.equals("addcustomer")) {
                // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Name: ");
                String customerName = br.readLine();
                System.out.print("Phone number: ");
                String phone = br.readLine();
                // br.close();

                return new AddCustomer(customerName, phone);
            } else if (cmd.equals("addsupplier")) {
                // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Name: ");
                String supplierName = br.readLine();
                // br.close();

                return new AddSupplier(supplierName);
            } else if (parts.length == 1) {
                if (line.equals("liststocks")) {
                    return new ListStocks();
                } else if (line.equals("listcustomers")) {
                    return new ListCustomers();
                } else if (line.equals("listsuppliers")) {
                    return new ListSuppliers();
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
                } else if (cmd.equals("showsupplier")) {
                    return new ShowSupplier(id);
                } else if (cmd.equals("removestock")) {
                    return new RemoveStock(id);
                } else if (cmd.equals("removecustomer")) {
                    return new RemoveCustomer(id);
                } else if (cmd.equals("removeorder")) {
                    return new RemoveOrder(id);
                } else if (cmd.equals("removesupplier")) {
                    return new RemoveSupplier(id);
                }
            } else if (parts.length == 3) {

                if (cmd.equals("buy")) {
                    // return new BuyStock(customerID, stockID);
                    int customerID = Integer.parseInt(parts[1]);
                    int stockID = Integer.parseInt(parts[2]);
                    System.out.println("Customer with ID " + customerID + " buying stock with ID " + stockID);
                } else if (cmd.equals("returnorder")) {
                    // return new ReturnStock(customerID, stockID);
                    int customerID = Integer.parseInt(parts[1]);
                    int orderID = Integer.parseInt(parts[2]);
                    // System.out.println("Customer with ID " + customerID + " returning stock with
                    // ID " + orderID);
                    return new ReturnOrder(customerID, orderID);
                }
            } else if (parts.length == 4) {

                if (cmd.equals("add")) {
                    int supplierID = Integer.parseInt(parts[1]);
                    String stockName = parts[2];
                    int inventory = Integer.parseInt(parts[3]);
                    System.out.println("Supplier with ID " + supplierID + " adding stock " + stockName
                            + " of the amount " + inventory);
                } else if (cmd.equals("changestocksupplier")) {
                    // System.out.println("Stock with ID " + supplierID + " adding stock " +
                    // stockName
                    // + " of the amount " + inventory);
                    int stockID = Integer.parseInt(parts[1]);
                    int oldSupplierID = Integer.parseInt(parts[2]);
                    int newSupplierID = Integer.parseInt(parts[3]);
                    return new ChangeStockSupplier(stockID, oldSupplierID, newSupplierID);
                }
            }
        } catch (NumberFormatException ex) {

        }

        throw new CentralException("Invalid command.");
    }
}
