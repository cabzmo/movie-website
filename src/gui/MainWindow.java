package gui;

import data.CentralData;
import main.CentralException;
import model.Stock;
import model.Supplier;
import model.Central;
import model.Customer;
import model.Order;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Main GUI window
 * 
 * @author Qassim Hassan &amp; Kamil Elmi
 * 
 * @see CentralData
 * @see CentralException
 * @see Stock
 * @see Central
 * @see Customer
 */
public class MainWindow extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu adminMenu;
    private JMenu stocksMenu;
    private JMenu customersMenu;
    private JMenu suppliersMenu;
    private JMenu ordersMenu;

    private JMenuItem adminExit;

    private JMenuItem stocksView;
    private JMenuItem stocksInvAdd;
    private JMenuItem stocksAdd;
    private JMenuItem stocksEdit;
    private JMenuItem stocksDel;

    private JMenuItem cusView;
    private JMenuItem cusAdd;
    private JMenuItem cusEdit;
    private JMenuItem cusDel;

    private JMenuItem supView;
    private JMenuItem supAdd;
    private JMenuItem supEdit;
    private JMenuItem supDel;

    private JMenuItem orderView;
    private JMenuItem orderAdd;
    private JMenuItem orderEdit;
    private JMenuItem orderDel;
    private JMenuItem orderCancel;
    private JMenuItem orderReturn;

    private JTable stocksTable;
    private JTable customersTable;
    private JTable suppliersTable;
    private JTable ordersTable;

    private JLabel tableTitle;

    private Central central;

    /**
     * 
     * @param central central
     */
    public MainWindow(Central central) {

        initialize();
        this.central = central;
        displayOrders();
    }

    /**
     * Returns the central.
     * 
     * @return central
     */
    public Central getCentral() {
        return central;
    }

    /**
     * Initialises the window.
     */
    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Central Management System");

        // Admin

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        adminMenu = new JMenu("Admin");
        menuBar.add(adminMenu);

        adminExit = new JMenuItem("Exit");
        adminMenu.add(adminExit);
        adminExit.addActionListener(this);

        // Stocks

        stocksMenu = new JMenu("Stocks");
        menuBar.add(stocksMenu);

        stocksView = new JMenuItem("View");
        stocksInvAdd = new JMenuItem("Add to Inventory");
        stocksAdd = new JMenuItem("Add new Stock");
        stocksEdit = new JMenuItem("Edit");
        stocksDel = new JMenuItem("Delete");
        stocksMenu.add(stocksView);
        stocksMenu.add(stocksInvAdd);
        stocksMenu.add(stocksAdd);
        stocksMenu.add(stocksEdit);
        stocksMenu.add(stocksDel);
        for (int i = 0; i < stocksMenu.getItemCount(); i++) {
            stocksMenu.getItem(i).addActionListener(this);
        }

        // Customers

        customersMenu = new JMenu("Customers");
        menuBar.add(customersMenu);

        cusView = new JMenuItem("View");
        cusAdd = new JMenuItem("Add");
        cusEdit = new JMenuItem("Edit");
        cusDel = new JMenuItem("Delete");

        customersMenu.add(cusView);
        customersMenu.add(cusAdd);
        customersMenu.add(cusEdit);
        customersMenu.add(cusDel);

        cusView.addActionListener(this);
        cusAdd.addActionListener(this);
        cusDel.addActionListener(this);

        // Suppliers

        suppliersMenu = new JMenu("Suppliers");
        menuBar.add(suppliersMenu);

        supView = new JMenuItem("View");
        supAdd = new JMenuItem("Add");
        supEdit = new JMenuItem("Edit");
        supDel = new JMenuItem("Delete");

        suppliersMenu.add(supView);
        suppliersMenu.add(supAdd);
        suppliersMenu.add(supEdit);
        suppliersMenu.add(supDel);

        supView.addActionListener(this);
        supAdd.addActionListener(this);
        supEdit.addActionListener(this);
        supDel.addActionListener(this);

        // Orders

        ordersMenu = new JMenu("Orders");
        menuBar.add(ordersMenu);

        orderView = new JMenuItem("View");
        orderAdd = new JMenuItem("Add");
        orderEdit = new JMenuItem("Edit");
        orderDel = new JMenuItem("Delete");
        orderCancel = new JMenuItem("Cancel");
        orderReturn = new JMenuItem("Return");

        ordersMenu.add(orderView);
        ordersMenu.add(orderAdd);
        ordersMenu.add(orderEdit);
        ordersMenu.add(orderDel);
        ordersMenu.add(orderCancel);
        ordersMenu.add(orderReturn);

        for (int i = 0; i < ordersMenu.getItemCount(); i++) {
            ordersMenu.getItem(i).addActionListener(this);
        }

        setSize(800, 500);

        setVisible(true);
        setAutoRequestFocus(true);
        toFront();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Main (GUI)
     * 
     * @param args default args
     * @throws IOException      Java IOException
     * @throws CentralException an error with the central
     */
    public static void main(String[] args) throws IOException, CentralException {
        Central central = CentralData.load();
        new MainWindow(central);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == adminExit) {
            try {
                CentralData.store(central);
            } catch (IOException | CentralException e) {
                e.printStackTrace();
            }
            System.exit(0);

        } else if (ae.getSource() == stocksView) {
            displayStocks();

        } else if (ae.getSource() == stocksAdd) {
            new AddStockWindow(this);

        } else if (ae.getSource() == stocksDel) {
            new DeleteStockWindow(this);

        } else if (ae.getSource() == stocksEdit) {

        } else if (ae.getSource() == stocksInvAdd) {

        } else if (ae.getSource() == cusView) {
            displayCustomers();

        } else if (ae.getSource() == cusAdd) {
            new AddCustomerWindow(this);

        } else if (ae.getSource() == cusEdit) {

        } else if (ae.getSource() == cusDel) {
            new DeleteCustomerWindow(this);

        } else if (ae.getSource() == supAdd) {
            new AddSupplierWindow(this);

        } else if (ae.getSource() == supEdit) {

        } else if (ae.getSource() == supDel) {
            new DeleteSupplierWindow(this);

        } else if (ae.getSource() == supView) {
            displaySuppliers();

        } else if (ae.getSource() == orderAdd) {
            new MakeOrderWindow(this);

        } else if (ae.getSource() == orderEdit) {

        } else if (ae.getSource() == orderCancel) {
            new CancelOrderWindow(this);

        } else if (ae.getSource() == orderDel) {

        } else if (ae.getSource() == orderReturn) {
            new ReturnOrderWindow(this);

        } else if (ae.getSource() == orderView) {
            displayOrders();

        }
    }

    /**
     * Loads the CustomerDetailsWindow
     * 
     * @param title title of the customer
     * @throws CentralException an error with the central
     */
    public void displayCustomerDetailsWindow(String title) throws CentralException {
        // new CustomerDetailsWindow(this, central.getStockByTitle(title));
    }

    /**
     * Loads the StockDetailsWindow
     * 
     * @param id id of the stock
     * @throws CentralException an error with the central
     */
    public void displayOrderDetailsWindowByCustomerID(int id) throws CentralException {
        new OrderDetailsWindow(this, central.getCustomerByID(id));
    }

    public void displayOrderDetailsWindowByStockID(int id) throws CentralException {
        new OrderDetailsWindow(this, central.getStockByID(id));
    }

    public void displayOrderDetailsWindowBySupplierID(int id) throws CentralException {
        new OrderDetailsWindow(this, central.getSupplierByID(id));
    }

    public void displayOrderActionsWindowBySupplierID(int id) throws CentralException {
        // new EditOrderWindow(this, central.getSupplierByID(id));
    }

    public void editCustomerWindow(int id) throws CentralException {
        new EditCustomerWindow(this, id);
    }

    /**
     * Displays stocks in the main window
     */
    public void displayStocks() {

        List<Stock> stocksList = central.getStocks();

        if (stocksList.size() == 0) {
            JOptionPane.showMessageDialog(this, "No stocks to show");
        } else {
            String[] columns = new String[] { "ID", "Name", "Amount in stock", "Supplier", "Orders" };

            Object[][] data = new Object[stocksList.size()][5];
            for (int i = 0; i < stocksList.size(); i++) {
                Stock stock = stocksList.get(i);
                data[i][0] = stock.getID();
                data[i][1] = stock.getName();
                data[i][2] = stock.getInventory();
                if (stock.getSupplier() == null) {
                    data[i][3] = stock.getSupplier();
                } else {
                    data[i][3] = stock.getSupplier().getName();
                }
                data[i][4] = "View (" + stock.getOrders().size() + ")";

            }

            // JPanel topPanel = new JPanel();
            // JPanel bottomPanel = new JPanel();

            // topPanel.setBorder(BorderFactory.createTitledBorder(
            // BorderFactory.createEtchedBorder(), "My Demo Table", TitledBorder.LEFT,
            // TitledBorder.TOP));

            stocksTable = new JTable(data, columns);

            stocksTable.setModel(new DefaultTableModel(data, columns) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
            this.getContentPane().removeAll();
            // this.getContentPane().add(scrollPane);
            this.getContentPane().add(new JScrollPane(stocksTable));
            this.revalidate();
            stocksTable.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent event) {
                    if (event.getClickCount() == 2) {
                        JTable clickedTable = (JTable) event.getSource();
                        int row = clickedTable.getSelectedRow();
                        int column = clickedTable.getSelectedColumn();
                        Object cell = clickedTable.getModel().getValueAt(row, column);
                        int id = Integer.valueOf(clickedTable.getModel().getValueAt(row, 0).toString());
                        if (cell.toString().contains("View")) {
                            try {
                                displayOrderDetailsWindowByStockID(id);
                            } catch (CentralException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
    }

    /**
     * Displays customers in the main window.
     */
    public void displayCustomers() {
        List<Customer> customersList = central.getCustomers();

        if (customersList.size() == 0) {
            JOptionPane.showMessageDialog(this, "No customers to show");
        } else {

            String[] columns = new String[] { "ID", "Name", "Phone", "Orders" };

            Object[][] data = new Object[customersList.size()][6];
            for (int i = 0; i < customersList.size(); i++) {
                Customer customer = customersList.get(i);
                data[i][0] = customer.getID();
                data[i][1] = customer.getName();
                data[i][2] = customer.getPhone();
                data[i][3] = "View (" + customer.getOrders().size() + ")";
            }

            customersTable = new JTable(data, columns);
            customersTable.setModel(new DefaultTableModel(data, columns) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
            this.getContentPane().removeAll();
            this.getContentPane().add(new JScrollPane(customersTable));
            this.revalidate();
            customersTable.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent event) {
                    if (event.getClickCount() == 2) {
                        JTable clickedTable = (JTable) event.getSource();
                        int row = clickedTable.getSelectedRow();
                        int column = clickedTable.getSelectedColumn();
                        Object cell = clickedTable.getModel().getValueAt(row, column);
                        int id = Integer.valueOf(clickedTable.getModel().getValueAt(row, 0).toString());
                        if (cell.toString().contains("View")) {
                            try {
                                displayOrderDetailsWindowByCustomerID(id);
                            } catch (CentralException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                editCustomerWindow(id);
                            } catch (CentralException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
    }

    public void displaySuppliers() {
        List<Supplier> suppliersList = central.getSuppliers();

        if (suppliersList.size() == 0) {
            JOptionPane.showMessageDialog(this, "No suppliers to show");
        } else {

            String[] columns = new String[] { "ID", "Name", "Stocks", "Orders" };

            Object[][] data = new Object[suppliersList.size()][4];
            for (int i = 0; i < suppliersList.size(); i++) {
                Supplier supplier = suppliersList.get(i);
                data[i][0] = supplier.getID();
                data[i][1] = supplier.getName();
                if (supplier.getSuppliedStocks().size() == 0) {
                    // data[i][2] = "View (" + supplier.getSuppliedStocks() + ")";
                    data[i][2] = supplier.getSuppliedStocks();
                } else {
                    // data[i][2] = "View (" + supplier.getSuppliedStocks().size() + ")";
                    data[i][2] = supplier.getSuppliedStocks().size();
                }
                int numOfOrders = 0;
                for (Stock stock : supplier.getSuppliedStocks()) {
                    numOfOrders += stock.getOrders().size();
                }
                data[i][3] = "View (" + numOfOrders + ")";
            }

            suppliersTable = new JTable(data, columns);
            suppliersTable.setModel(new DefaultTableModel(data, columns) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
            this.getContentPane().removeAll();
            this.getContentPane().add(new JScrollPane(suppliersTable));
            this.revalidate();
            suppliersTable.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent event) {
                    if (event.getClickCount() == 2) {
                        JTable clickedTable = (JTable) event.getSource();
                        int row = clickedTable.getSelectedRow();
                        int column = clickedTable.getSelectedColumn();
                        Object cell = clickedTable.getModel().getValueAt(row, column);
                        int id = Integer.valueOf(clickedTable.getModel().getValueAt(row, 0).toString());
                        if (cell.toString().contains("View")) {
                            try {
                                displayOrderDetailsWindowBySupplierID(id);
                            } catch (CentralException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
    }

    public void displayOrders() {
        List<Order> ordersList = central.getOrders();

        if (ordersList.size() == 0) {
            JOptionPane.showMessageDialog(this, "No orders to show");
        } else {
            String[] columns = new String[] { "ID", "Customer", "Stock", "Amount", "Delivered", "Returned" };

            Object[][] data = new Object[ordersList.size()][6];
            for (int i = 0; i < ordersList.size(); i++) {
                Order order = ordersList.get(i);
                data[i][0] = order.getID();
                data[i][1] = order.getCustomer().getName();
                data[i][2] = order.getStock().getName();
                data[i][3] = order.getAmount();
                if (order.getDelivered() == false) {
                    data[i][4] = "No";
                } else {
                    data[i][4] = "Yes";
                }
                if (order.getReturned() == false) {
                    data[i][5] = "No";
                } else {
                    data[i][5] = "Yes";
                }
            }

            ordersTable = new JTable(data, columns);
            ordersTable.setModel(new DefaultTableModel(data, columns) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
            this.getContentPane().removeAll();
            this.getContentPane().add(new JScrollPane(ordersTable));
            this.revalidate();
        }
    }

}