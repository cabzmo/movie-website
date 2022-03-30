package test.Command.Customer;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import commands.AddCustomer;
import main.CentralException;
import model.Central;

public class TestRemoveCustomer {
    Central central;

    @Before
    public void setUp() throws CentralException, IOException {
        central = new Central();
        new AddCustomer("customer", "0000").execute(central);
    }

    @Test
    public void testGetCustomersSize() throws CentralException {
        central.removeCustomer(central.getCustomerByID(1));
        assertEquals(0, central.getCustomers().size());
    }
}