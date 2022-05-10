package gui;

import commands.Command;
import commands.MakeOrder;
import main.CentralException;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

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
    private JTextField customerIdField = new JTextField();
    private JTextField stockIdField = new JTextField();
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

        setTitle("Issue Book");

        setSize(300, 200);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(5, 2));
        topPanel.add(new JLabel("Customer ID : "));
        topPanel.add(customerIdField);
        topPanel.add(new JLabel("Stock ID : "));
        topPanel.add(stockIdField);
        topPanel.add(new JLabel("Amount : "));
        topPanel.add(amountField);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(orderBtn);
        bottomPanel.add(cancelBtn);

        orderBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

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
            int customerId = Integer.parseInt(customerIdField.getText());
            int stockId = Integer.parseInt(stockIdField.getText());
            int amount = Integer.parseInt(amountField.getText());
            Command makeOrder = new MakeOrder(mw.getCentral().getCustomerByID(customerId),
                    mw.getCentral().getStockByID(stockId), amount);
            makeOrder.execute(mw.getCentral(), LocalDate.now());
            mw.displayOrders();
            this.setVisible(false);
        } catch (CentralException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}