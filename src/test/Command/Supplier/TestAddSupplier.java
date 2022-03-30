package test.Command.Supplier;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import commands.AddSupplier;
import main.CentralException;
import model.Central;

public class TestAddSupplier {
    Central central;
    private String supplierName;

    @Before
    public void setUp() {
        central = new Central();
        supplierName = "supplier1";
    }

    @Test
    public void testGetSuppliersSize() throws CentralException {
        assertEquals(0, central.getSuppliers().size());
        new AddSupplier(supplierName).execute(central);
        assertEquals(1, central.getSuppliers().size());
    }
}
