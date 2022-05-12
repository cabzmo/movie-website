package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import main.CentralException;
import model.Customer;

/**
 * Window to add a patron to the library.
 * 
 * @author Qassim Hassan &amp; Kamil Elmi
 * 
 * @see EditCustomer
 * @see Command
 * @see CentralException
 */
public class ShowCustomerDetailsWindow extends JFrame implements ActionListener {
    private MainWindow mw;
    private int customerID;

    private JTextField nameText = new JTextField();
    private JTextField phoneText = new JTextField();

    private JButton okBtn = new JButton("OK");

    /**
     * add patron window
     * 
     * @param mw Main GUI window
     * @throws CentralException
     */
    public ShowCustomerDetailsWindow(MainWindow mw, int customerID) throws CentralException {
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

        setTitle("Show a Customer");

        setSize(600, 200);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 2));

        topPanel.add(new JLabel("Name : "));
        nameText.setEditable(false);
        nameText.setBorder(BorderFactory.createEmptyBorder());
        topPanel.add(nameText);

        topPanel.add(new JLabel("Phone : "));
        phoneText.setEditable(false);
        phoneText.setBorder(BorderFactory.createEmptyBorder());
        topPanel.add(phoneText);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(okBtn);
        bottomPanel.add(new JLabel("     "));

        okBtn.addActionListener(this);

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
        if (ae.getSource() == okBtn) {
            mw.displayOrders();
            this.setVisible(false);
        }
    }

}
