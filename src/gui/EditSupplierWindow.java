package gui;

import commands.EditSupplier;
import commands.Command;
import main.CentralException;
import model.Supplier;

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
 * @see EditSupplier
 * @see Command
 * @see CentralException
 */
public class EditSupplierWindow extends JFrame implements ActionListener {
    private MainWindow mw;
    private int supplierID;

    private JTextField nameText = new JTextField();

    private JButton editBtn = new JButton("Edit");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * add patron window
     * 
     * @param mw Main GUI window
     * @throws CentralException
     */
    public EditSupplierWindow(MainWindow mw, int supplierID) throws CentralException {
        this.mw = mw;
        this.supplierID = supplierID;
        Supplier supplier = mw.getCentral().getSupplierByID(supplierID);
        nameText.setText(supplier.getName());
        initialize();
    }

    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Edit a Supplier");

        setSize(400, 300);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(5, 2));
        topPanel.add(new JLabel("Name : "));
        topPanel.add(nameText);

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
            editSupplier();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    private void editSupplier() {
        try {
            String name = nameText.getText();

            Command editSupplier = new EditSupplier(supplierID, name);
            editSupplier.execute(mw.getCentral(), LocalDate.now());

            mw.displaySuppliers();

            this.setVisible(false);
        } catch (CentralException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            setVisible(false);
        }
    }

}
