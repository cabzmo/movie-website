package gui;

import commands.Command;
import commands.MakeOrder;
import main.CentralException;
import model.Customer;
import model.Stock;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

/**
 * Window for a customer to borrow a book.
 * 
 * @author Qassim Hassan &amp; Kamil Elmi
 * 
 * @see Borrow
 * @see Command
 * @see dCentralException
 */
public class MakeOrderWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    // private JTextField customerIdField = new JTextField();
    // private JTextField stockIdField = new JTextField();
    private JComboBox<String> customersComboBox;
    private JComboBox<String> stocksComboBox;

    private JTextField amountField = new JTextField();

    private JButton orderBtn = new JButton("Order");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * 
     * @param mw Main GUI window
     */
    public MakeOrderWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Make an Order");

        setSize(600, 300);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(6, 2));

        topPanel.add(new JLabel("Customer : "));
        Customer[] customersList = mw.getCentral().getCustomers().toArray(new Customer[0]);
        String[] customersListString = new String[customersList.length];
        for (int x = 0; x < customersList.length; x++) {
            customersListString[x] = customersList[x].getDetailsShort();
        }
        customersComboBox = new JComboBox<String>(customersListString);
        topPanel.add(customersComboBox);

        topPanel.add(new JLabel("    "));
        topPanel.add(new JLabel("    "));

        topPanel.add(new JLabel("Stock : "));
        Stock[] stocksList = mw.getCentral().getStocks().toArray(new Stock[0]);
        String[] stocksListString = new String[stocksList.length];
        for (int x = 0; x < stocksList.length; x++) {
            stocksListString[x] = stocksList[x].getDetailsShort();
        }
        stocksComboBox = new JComboBox<String>(stocksListString);
        topPanel.add(stocksComboBox);

        topPanel.add(new JLabel("    "));
        topPanel.add(new JLabel("    "));

        topPanel.add(new JLabel("Amount : "));
        topPanel.add(amountField);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 5));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(orderBtn);
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(cancelBtn);
        bottomPanel.add(new JLabel("     "));

        orderBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        topPanel.setBorder(new EmptyBorder(20, 20, 0, 20));
        bottomPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == orderBtn) {
            issueBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    private void issueBook() {
        try {
            String customerIDString = customersComboBox.getSelectedItem().toString().split(" ")[1].replace("#", "");
            int customerID = Integer.parseInt(customerIDString);

            String stockIDString = stocksComboBox.getSelectedItem().toString().split(" ")[1].replace("#", "");
            int stockID = Integer.parseInt(stockIDString);

            // int customerId = Integer.parseInt(customerIdField.getText());
            // int stockId = Integer.parseInt(stockIdField.getText());
            int amount = Integer.parseInt(amountField.getText());
            Command makeOrder = new MakeOrder(mw.getCentral().getCustomerByID(customerID),
                    mw.getCentral().getStockByID(stockID), amount);
            makeOrder.execute(mw.getCentral(), LocalDate.now());
            mw.displayOrders();
            this.setVisible(false);
        } catch (CentralException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            setVisible(false);
        }
    }

}