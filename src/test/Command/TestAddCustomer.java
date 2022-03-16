package test.Command;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import commands.AddCustomer;
import main.CentralException;
import model.Central;

public class TestAddCustomer {
    Central central;

    @Before
    public void setUp() throws CentralException, IOException {
        central = new Central();
        new AddCustomer("customer", "0000").execute(central);
    }

    @Test
    public void testGetCustomerID() throws CentralException {
        assertEquals(1, central.getCustomerByID(1).getID());
    }

    @Test
    public void testGetCustomerName() throws CentralException {
        assertEquals("customer", central.getCustomerByID(1).getName());
    }

    @Test
    public void testGetCustomerPhone() throws CentralException {
        assertEquals("0000", central.getCustomerByID(1).getPhone());
    }
}