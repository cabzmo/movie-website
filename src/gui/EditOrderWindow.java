package gui;

import commands.EditOrder;
import commands.Command;
import main.CentralException;
import model.Order;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

/**
 * Window to add a patron to the library.
 * 
 * @author Qassim Hassan &amp; Kamil Elmi
 * 
 * @see EditOrder
 * @see Command
 * @see CentralException
 */
public class EditOrderWindow extends JFrame implements ActionListener {
    private MainWindow mw;
    private int orderID;

    private JTextField orderIDText = new JTextField();
    private JTextField customerText = new JTextField();
    private JTextField stockText = new JTextField();
    private JTextField amountText = new JTextField();
    private JTextField deliveredText = new JTextField();
    private JTextField returnedText = new JTextField();

    private JButton editBtn = new JButton("Edit");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * add patron window
     * 
     * @param mw Main GUI window
     * @throws CentralException
     */
    public EditOrderWindow(MainWindow mw, int orderID) throws CentralException {
        this.mw = mw;
        this.orderID = orderID;
        Order order = mw.getCentral().getOrderByID(orderID);
        orderIDText.setText(String.valueOf(order.getID()));
        customerText.setText(order.getCustomer().getName());
        stockText.setText(order.getStock().getName());
        amountText.setText(String.valueOf(order.getAmount()));

        if (order.getDelivered() == false) {
            deliveredText.setText("No");
        } else {
            // deliveredText.setText("Yes");
            deliveredText.setText(order.getDeliveredWhenString());
            amountText.setEditable(false);
            amountText.setBorder(BorderFactory.createEmptyBorder());
            editBtn.setEnabled(false);
        }

        if (order.getReturned() == false) {
            returnedText.setText("No");
        } else {
            // returnedText.setText("Yes");
            returnedText.setText(order.getReturnedWhenString());
        }
        initialize();
    }

    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Edit a Order");

        setSize(400, 300);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(6, 2));
        topPanel.add(new JLabel("ID : "));
        orderIDText.setEditable(false);
        orderIDText.setBorder(BorderFactory.createEmptyBorder());
        topPanel.add(orderIDText);

        topPanel.add(new JLabel("Customer : "));
        customerText.setEditable(false);
        customerText.setBorder(BorderFactory.createEmptyBorder());
        topPanel.add(customerText);

        topPanel.add(new JLabel("Stock : "));
        stockText.setEditable(false);
        stockText.setBorder(BorderFactory.createEmptyBorder());
        topPanel.add(stockText);

        topPanel.add(new JLabel("Amount : "));
        topPanel.add(amountText);

        topPanel.add(new JLabel("Delivered : "));
        deliveredText.setEditable(false);
        deliveredText.setBorder(BorderFactory.createEmptyBorder());
        topPanel.add(deliveredText);

        topPanel.add(new JLabel("Returned : "));
        returnedText.setEditable(false);
        returnedText.setBorder(BorderFactory.createEmptyBorder());
        topPanel.add(returnedText);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 5));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(editBtn);
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(cancelBtn);
        bottomPanel.add(new JLabel("     "));

        editBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        topPanel.setBorder(new EmptyBorder(20, 20, 0, 20));
        bottomPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }

    /**
     * @param ae action event
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == editBtn) {
            editOrder();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    private void editOrder() {
        try {
            String amount = amountText.getText();

            Command editOrder = new EditOrder(orderID, Integer.valueOf(amount));
            editOrder.execute(mw.getCentral(), LocalDate.now());

            mw.displayOrders();

            this.setVisible(false);
        } catch (CentralException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            setVisible(false);
        }
    }

}
