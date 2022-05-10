package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import commands.Command;
import commands.RemoveCustomer;
import main.CentralException;
import model.Customer;

/**
 * Window for a patron to borrow a customer.
 * 
 * @author Qassim Hassan &amp; Kamil Elmi
 * 
 * @see Command
 * @see RemoveCustomer
 * @see CentralException
 * @see Customer
 */
public class DeleteCustomerWindow extends JFrame implements ActionListener {
    private MainWindow mw;

    private JComboBox<String> customersComboBox;
    private JButton delBtn = new JButton("Delete");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * 
     * @param mw Main GUI window
     */
    public DeleteCustomerWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Delete Customer");

        setSize(300, 200);
        JPanel topPanel = new JPanel();
        Customer[] customersList = mw.getCentral().getCustomers().toArray(new Customer[0]);
        String[] customersListString = new String[customersList.length];
        for (int x = 0; x < customersList.length; x++) {
            customersListString[x] = customersList[x].getDetailsShort();
        }
        customersComboBox = new JComboBox<String>(customersListString);
        topPanel.add(customersComboBox);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(delBtn);
        bottomPanel.add(cancelBtn);

        delBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        customersComboBox.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delBtn) {
            try {
                deleteCustomer();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (CentralException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    private void deleteCustomer() throws NumberFormatException, CentralException {
        String customerID = customersComboBox.getSelectedItem().toString().split(" ")[1].replace("#", "");
        Command deleteCustomer = new RemoveCustomer(Integer.valueOf(customerID));
        deleteCustomer.execute(mw.getCentral(), LocalDate.now());
        mw.displayCustomers();
        this.setVisible(false);
    }

}
