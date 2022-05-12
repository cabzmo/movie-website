package gui;

import commands.AddCustomer;
import commands.Command;
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
import javax.swing.border.EmptyBorder;

/**
 * Window to add a patron to the library.
 * 
 * @author Qassim Hassan &amp; Kamil Elmi
 * 
 * @see AddCustomer
 * @see Command
 * @see CentralException
 */
public class AddCustomerWindow extends JFrame implements ActionListener {
    private MainWindow mw;
    private JTextField nameText = new JTextField();
    private JTextField phoneText = new JTextField();

    private JButton addBtn = new JButton("Add");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * add patron window
     * 
     * @param mw Main GUI window
     */
    public AddCustomerWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Add a New Customer");

        setSize(600, 200);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 2));
        topPanel.add(new JLabel("Name : "));
        topPanel.add(nameText);
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel("Phone Number : "));
        topPanel.add(phoneText);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 5));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(addBtn);
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(cancelBtn);
        bottomPanel.add(new JLabel("     "));

        addBtn.addActionListener(this);
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
        if (ae.getSource() == addBtn) {
            addCustomer();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    private void addCustomer() {
        try {
            String name = nameText.getText();
            String phone = phoneText.getText();

            Command addCustomer = new AddCustomer(name, phone);
            addCustomer.execute(mw.getCentral(), LocalDate.now());

            mw.displayCustomers();

            this.setVisible(false);
        } catch (CentralException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            setVisible(false);
        }
    }

}
