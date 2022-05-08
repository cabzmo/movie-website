package test.Command.Stock;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import commands.AddCustomer;
import commands.AddStock;
import commands.AddSupplier;
import commands.DeleteStock;
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
        new AddSupplier("supplier1").execute(central, LocalDate.now());
        new AddStock(stockName, inventory, 1).execute(central, LocalDate.now());
    }

    @Test
    public void testGetCustomersSize() throws CentralException {
        assertEquals(1, central.getStocks().size());
        new DeleteStock(stockName).execute(central, LocalDate.now());
        assertEquals(0, central.getStocks().size());
    }
}