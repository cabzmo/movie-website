package gui;

import commands.EditCustomer;
import commands.Command;
import main.CentralException;
import model.Customer;

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
import javax.swing.border.EmptyBorder;

/**
 * Window to add a patron to the library.
 * 
 * @author Qassim Hassan &amp; Kamil Elmi
 * 
 * @see EditCustomer
 * @see Command
 * @see CentralException
 */
public class EditCustomerWindow extends JFrame implements ActionListener {
    private MainWindow mw;
    private int customerID;

    private JTextField nameText = new JTextField();
    private JTextField phoneText = new JTextField();

    private JButton editBtn = new JButton("Edit");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * add patron window
     * 
     * @param mw Main GUI window
     * @throws CentralException
     */
    public EditCustomerWindow(MainWindow mw, int customerID) throws CentralException {
        this.mw = mw;
        this.customerID = customerID;
        Customer customer = mw.getCentral().getCustomerByID(customerID);
        nameText.setText(customer.getName());
        phoneText.setText(customer.getPhone());
        initialize();
    }

    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Edit a Customer");

        setSize(400, 300);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(5, 2));
        topPanel.add(new JLabel("Name : "));
        topPanel.add(nameText);
        topPanel.add(new JLabel("Phone Number : "));
        topPanel.add(phoneText);

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
            editCustomer();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    private void editCustomer() {
        try {
            String name = nameText.getText();
            String phone = phoneText.getText();

            Command editCustomer = new EditCustomer(customerID, name, phone);
            editCustomer.execute(mw.getCentral(), LocalDate.now());

            mw.displayCustomers();

            this.setVisible(false);
        } catch (CentralException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            setVisible(false);
        }
    }

}
