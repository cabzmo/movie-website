package test.Command.Stock;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import commands.AddStock;
import commands.AddSupplier;
import main.CentralException;
import model.Central;

public class TestAddStock {
    Central central;
    private String stockName = "Mars";
    private int inventory = 12;

    @Before
    public void setUp() throws CentralException, IOException {
        central = new Central();
        new AddSupplier("supplier1").execute(central);
    }

    @Test
    public void testGetStocksSize() throws CentralException {
        assertEquals(0, central.getStocks().size());
        new AddStock(stockName, inventory, 1).execute(central);
        assertEquals(1, central.getStocks().size());
    }
}