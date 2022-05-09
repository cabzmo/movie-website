package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import main.CentralException;
import model.Order;
import model.Stock;
import model.Supplier;
import model.Customer;

/**
 * Window to view order details.
 * 
 * @author Qassim Hassan &amp; Kamil Elmi
 * 
 * @see Order
 * @see Customer
 */
public class OrderDetailsWindow extends JFrame implements ActionListener {
    private MainWindow mw;
    private Customer customer = null;
    private Stock stock = null;
    private Supplier supplier = null;

    /**
     * 
     * @param mw       Main GUI window
     * @param customer Customer to get order information from
     * @throws CentralException
     */
    public OrderDetailsWindow(MainWindow mw, Customer customer) throws CentralException {
        this.mw = mw;
        this.customer = customer;
        initialize();
    }

    public OrderDetailsWindow(MainWindow mw, Stock stock) throws CentralException {
        this.mw = mw;
        this.stock = stock;
        initialize();
    }

    public OrderDetailsWindow(MainWindow mw, Supplier supplier) throws CentralException {
        this.mw = mw;
        this.supplier = supplier;
        initialize();
    }

    private void initialize() throws CentralException {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Order Details");

        setSize(300, 200);
        JPanel topPanel = new JPanel();
        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        setLocationRelativeTo(mw);
        setVisible(true);

        List<Order> ordersList = new ArrayList<>();

        if (this.stock != null) {
            for (int orderID : stock.getOrderIDs()) {
                ordersList.add(mw.getCentral().getOrderByID(orderID));
            }
            System.out.println("Order Details Window: Stock");
        } else if (this.customer != null) {
            for (int orderID : customer.getOrderIDs()) {
                ordersList.add(mw.getCentral().getOrderByID(orderID));
            }
            System.out.println("Order Details Window: Customer");
        } else {
            for (Order order : mw.getCentral().getOrders()) {
                System.out.println("OrderID: " + order.getID());
                System.out.println("OrderSupplierID: " + order.getStock().getSupplier().getID());
                System.out.println("SupplierID: " + supplier.getID());
                System.out.println(
                        "SupplierID == OrderSupplierID: "
                                + (supplier.getID() == order.getStock().getSupplier().getID()));
                if (order.getStock().getSupplier().getID() == this.supplier.getID()) {
                    ordersList.add(mw.getCentral().getOrderByID(order.getID()));
                }
            }
            System.out.println("Order Details Window: Supplier");
        }

        // List<Order> ordersList = customer.getOrders();
        if (ordersList.size() > 0) {
            String[] columns = new String[] { "ID", "Customer", "Stock", "Amount" };

            Object[][] data = new Object[ordersList.size()][4];
            for (int i = 0; i < ordersList.size(); i++) {
                Order order = ordersList.get(i);
                data[i][0] = order.getID();
                data[i][1] = order.getCustomer().getName();
                data[i][2] = order.getStock().getName();
                data[i][3] = order.getAmount();
            }

            JTable table = new JTable(data, columns);
            this.getContentPane().removeAll();
            this.getContentPane().add(new JScrollPane(table));
            this.revalidate();
        } else {
            if (this.stock != null) {
                topPanel.add(new JLabel("No orders for this stock."));
            } else if (this.customer != null) {
                topPanel.add(new JLabel("No orders made by customer."));
            } else {
                topPanel.add(new JLabel("No orders for this supplier's stock."));
            }
        }
    }

    /**
     * action performed
     * 
     * @param e action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        return;
    }
}
