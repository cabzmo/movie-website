package gui;

import data.CentralData;
import main.CentralException;
import model.Stock;
import model.Supplier;
import model.Central;
import model.Customer;
import model.Order;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
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
    private JMenuItem stocksAdd;
    private JMenuItem stocksDel;
    private JMenuItem stocksIssue;
    private JMenuItem stocksRenew;
    private JMenuItem stocksReturn;

    private JMenuItem cusView;
    private JMenuItem cusAdd;
    private JMenuItem cusDel;

    private JMenuItem supView;
    private JMenuItem supAdd;
    private JMenuItem supDel;

    private JMenuItem orderView;
    private JMenuItem orderAdd;
    private JMenuItem orderDel;
    private JMenuItem orderNew;
    private JMenuItem orderCancel;
    private JMenuItem orderReturn;

    private JTable stocksTable;
    private JTable customersTable;
    private JTable suppliersTable;
    private JTable ordersTable;

    private Central central;

    /**
     * 
     * @param central central
     */
    public MainWindow(Central central) {

        initialize();
        this.central = central;
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
        stocksAdd = new JMenuItem("Add");
        stocksDel = new JMenuItem("Delete");
        stocksIssue = new JMenuItem("Issue");
        stocksRenew = new JMenuItem("Renew");
        stocksReturn = new JMenuItem("Return");
        stocksMenu.add(stocksView);
        stocksMenu.add(stocksAdd);
        stocksMenu.add(stocksDel);
        stocksMenu.add(stocksIssue);
        stocksMenu.add(stocksRenew);
        stocksMenu.add(stocksReturn);
        for (int i = 0; i < stocksMenu.getItemCount(); i++) {
            stocksMenu.getItem(i).addActionListener(this);
        }

        // Customers

        customersMenu = new JMenu("Customers");
        menuBar.add(customersMenu);

        cusView = new JMenuItem("View");
        cusAdd = new JMenuItem("Add");
        cusDel = new JMenuItem("Delete");

        customersMenu.add(cusView);
        customersMenu.add(cusAdd);
        customersMenu.add(cusDel);

        cusView.addActionListener(this);
        cusAdd.addActionListener(this);
        cusDel.addActionListener(this);

        // Suppliers

        suppliersMenu = new JMenu("Suppliers");
        menuBar.add(suppliersMenu);

        supView = new JMenuItem("View");
        supAdd = new JMenuItem("Add");
        supDel = new JMenuItem("Delete");

        suppliersMenu.add(supView);
        suppliersMenu.add(supAdd);
        suppliersMenu.add(supDel);

        supView.addActionListener(this);
        supAdd.addActionListener(this);
        supDel.addActionListener(this);

        // Orders

        ordersMenu = new JMenu("Orders");
        menuBar.add(ordersMenu);

        orderView = new JMenuItem("View");
        orderAdd = new JMenuItem("Add");
        orderDel = new JMenuItem("Add");
        orderNew = new JMenuItem("Add");
        orderCancel = new JMenuItem("Add");
        orderReturn = new JMenuItem("Add");

        ordersMenu.add(orderView);
        ordersMenu.add(orderAdd);
        ordersMenu.add(orderDel);
        ordersMenu.add(orderNew);
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

        } else if (ae.getSource() == stocksIssue) {
            // new BorrowStockWindow(this);

        } else if (ae.getSource() == stocksRenew) {
            // new RenewStockWindow(this);

        } else if (ae.getSource() == stocksReturn) {
            // new ReturnStockWindow(this);

        } else if (ae.getSource() == cusView) {
            displayCustomers();

        } else if (ae.getSource() == cusAdd) {
            // new AddCustomerWindow(this);

        } else if (ae.getSource() == cusDel) {
            // new DeleteCustomerWindow(this);

        } else if (ae.getSource() == supAdd) {

        } else if (ae.getSource() == supDel) {

        } else if (ae.getSource() == supView) {
            displaySuppliers();

        } else if (ae.getSource() == orderAdd) {

        } else if (ae.getSource() == orderCancel) {

        } else if (ae.getSource() == orderDel) {

        } else if (ae.getSource() == orderNew) {

        } else if (ae.getSource() == orderReturn) {

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
    public void displayStockDetailsWindow(int id) throws CentralException {
        // new StockDetailsWindow(this, central.getCustomerByID(id));
    }

    /**
     * Displays stocks in the main window
     */
    public void displayStocks() {
        List<Stock> stocksList = central.getStocks();
        String[] columns = new String[] { "ID", "Name", "Amount in stock", "Supplier", "Orders" };

        Object[][] data = new Object[stocksList.size()][5];
        for (int i = 0; i < stocksList.size(); i++) {
            Stock stock = stocksList.get(i);
            // data[i][0] = stock.getId();
            // data[i][1] = stock.getTitle();
            // data[i][2] = stock.getAuthor();
            // data[i][3] = stock.getPublisher();
            // data[i][4] = stock.getPublicationYear();
            // data[i][5] = stock.getStatus();

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

        stocksTable = new JTable(data, columns);
        stocksTable.setModel(new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(stocksTable));
        this.revalidate();
        stocksTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    JTable clickedTable = (JTable) event.getSource();
                    int row = clickedTable.getSelectedRow();
                    int column = clickedTable.getSelectedColumn();
                    Object cell = clickedTable.getModel().getValueAt(row, column);
                    String title = clickedTable.getModel().getValueAt(row, 0).toString();
                    if (cell.toString() == "Loaned Out") {
                        try {
                            displayCustomerDetailsWindow(title);
                        } catch (CentralException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    /**
     * Displays customers in the main window.
     */
    public void displayCustomers() {
        List<Customer> customerslist = central.getCustomers();
        String[] columns = new String[] { "ID", "Name", "Phone", "Orders" };

        Object[][] data = new Object[customerslist.size()][6];
        for (int i = 0; i < customerslist.size(); i++) {
            Customer customer = customerslist.get(i);
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
                            displayStockDetailsWindow(id);
                        } catch (CentralException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public void displaySuppliers() {
        List<Supplier> suppliersList = central.getSuppliers();
        String[] columns = new String[] { "ID", "Name", "Stocks" };

        Object[][] data = new Object[suppliersList.size()][4];
        for (int i = 0; i < suppliersList.size(); i++) {
            Supplier supplier = suppliersList.get(i);
            data[i][0] = supplier.getID();
            data[i][1] = supplier.getName();
            if (supplier.getSuppliedStocks().size() == 0) {
                data[i][2] = supplier.getSuppliedStocks();
            } else {
                data[i][2] = supplier.getSuppliedStocks().size();
            }

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
                    String title = clickedTable.getModel().getValueAt(row, 0).toString();
                    if (cell.toString() == "Stocks") {
                        try {
                            displayCustomerDetailsWindow(title);
                        } catch (CentralException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public void displayOrders() {
        List<Order> ordersList = central.getOrders();
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
            // data[i][4] = order.getDelivered();
            // data[i][5] = order.getReturned();
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
        ordersTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    JTable clickedTable = (JTable) event.getSource();
                    int row = clickedTable.getSelectedRow();
                    int column = clickedTable.getSelectedColumn();
                    Object cell = clickedTable.getModel().getValueAt(row, column);
                    String title = clickedTable.getModel().getValueAt(row, 0).toString();
                    if (cell.toString() == "Loaned Out") {
                        try {
                            displayCustomerDetailsWindow(title);
                        } catch (CentralException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

}