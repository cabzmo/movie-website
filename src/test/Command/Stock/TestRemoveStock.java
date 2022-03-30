package test.Command.Stock;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import commands.AddCustomer;
import commands.AddStock;
import commands.AddSupplier;
import commands.RemoveStock;
import main.CentralException;
import model.Central;
import model.Supplier;

public class TestRemoveStock {
    Central central;
    private String stockName = "Mars";
    private int inventory = 12;

    @Before
    public void setUp() throws CentralException, IOException {
        central = new Central();
        new AddSupplier("supplier1").execute(central);
        new AddStock(stockName, inventory, 1).execute(central);
    }

    @Test
    public void testGetCustomersSize() throws CentralException {
        assertEquals(1, central.getStocks().size());
        new RemoveStock(stockName).execute(central);
        assertEquals(0, central.getStocks().size());
    }
}