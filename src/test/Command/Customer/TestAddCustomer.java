package test.Command.Customer;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import commands.AddCustomer;
import main.CentralException;
import model.Central;

public class TestAddCustomer {
    Central central;
    private String customerName = "customer";
    private String phoneNumber = "0000";

    @Before
    public void setUp() throws CentralException, IOException {
        central = new Central();
    }

    @Test
    public void testGetCustomersSize() throws CentralException {
        assertEquals(0, central.getCustomers().size());
        new AddCustomer(customerName, phoneNumber).execute(central);
        assertEquals(1, central.getCustomers().size());
    }

    @Test
    public void testGetCustomerID() throws CentralException {
        new AddCustomer(customerName, phoneNumber).execute(central);
        assertEquals(1, central.getCustomerByID(1).getID());
    }

    @Test
    public void testGetCustomerName() throws CentralException {
        new AddCustomer(customerName, phoneNumber).execute(central);
        assertEquals(customerName, central.getCustomerByID(1).getName());
    }

    @Test
    public void testGetCustomerPhone() throws CentralException {
        new AddCustomer(customerName, phoneNumber).execute(central);
        assertEquals(phoneNumber, central.getCustomerByID(1).getPhone());
    }
}