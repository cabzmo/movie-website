package test.Command.Order;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import commands.AddCustomer;
import commands.AddStock;
import commands.AddSupplier;
import commands.MakeOrder;
import main.CentralException;
import model.Central;

public class TestMakeOrder {
    Central central;
    private String customerName = "customer";
    private String phoneNumber = "0000";
    private String supplierName = "supplier";
    private String stockName = "stock";
    private int inventory = 12;

    @Before
    public void setUp() throws CentralException, IOException {
        central = new Central();
        new AddCustomer(customerName, phoneNumber).execute(central);
        new AddSupplier(supplierName).execute(central);
        new AddStock(stockName, inventory, 1).execute(central);
    }

    @Test
    public void testGetOrdersSize() throws CentralException {
        assertEquals(0, central.getOrders().size());
        new MakeOrder(central.getCustomerByName(customerName), central.getStockByName(stockName), 4).execute(central);
        assertEquals(1, central.getOrderByID(1).getID());
    }

    @Test
    public void testGetCustomerName() throws CentralException {
        new MakeOrder(central.getCustomerByName(customerName), central.getStockByName(stockName), 4).execute(central);
        assertEquals(customerName, central.getOrderByID(1).getCustomer().getName());
    }

    @Test
    public void testGetCustomerPhone() throws CentralException {
        new MakeOrder(central.getCustomerByName(customerName), central.getStockByName(stockName), 4).execute(central);
        assertEquals(phoneNumber, central.getOrderByID(1).getCustomer().getPhone());
    }

    @Test
    public void testGetStockName() throws CentralException {
        new MakeOrder(central.getCustomerByName(customerName), central.getStockByName(stockName), 4).execute(central);
        assertEquals(stockName, central.getOrderByID(1).getStock().getName());
    }

    @Test
    public void testGetStockInventoryBeforeOrders() throws CentralException {
        new MakeOrder(central.getCustomerByName(customerName), central.getStockByName(stockName), 4).execute(central);
        assertEquals(inventory, central.getOrderByID(1).getStock().getInventoryBeforeOrders());
    }

    @Test
    public void testGetStockSupplierName() throws CentralException {
        new MakeOrder(central.getCustomerByName(customerName), central.getStockByName(stockName), 4).execute(central);
        assertEquals(supplierName, central.getSupplierByID(central.getStockByID(1).getSupplierID()).getName());
    }
}