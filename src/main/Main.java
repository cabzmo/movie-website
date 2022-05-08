package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

import commands.Command;
import data.CentralData;
import model.Central;

public class Main {
    public static void main(String[] args) throws CentralException, IOException {

        Central central = CentralData.load();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Library system");
        System.out.println("Enter 'help' to see a list of available commands.");
        while (true) {
            System.out.print("> ");
            String line = br.readLine();
            if (line.equals("exit")) {
                break;
            }

            try {
                // Command command = CommandParser.parse(line);
                Command command = CommandParser.parse(line, br);
                command.execute(central, LocalDate.now());
            } catch (CentralException ex) {
                System.out.println(ex.getMessage());
            }
        }

        // System.out.println(central.getSupplierByID(1).getSuppliedStocks());
        // System.out.println(central.getStockByID(1).getSupplier().getName());
        // central.getSupplierByID(1).addSuppliedStock(central.getStockByID(1));
        // System.out.println(central.getSupplierByID(1).getSuppliedStocks());

        CentralData.store(central);
        System.exit(0);

    }
}