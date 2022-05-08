package gui;

import commands.AddStock;
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

/**
 * Window to add a book to the library.
 * 
 * @author Qassim Hassan &amp; Kamil Elmi
 * 
 * @see AddStock
 * @see Command
 * @see CentralException
 */
public class AddStockWindow extends JFrame implements ActionListener {
    private MainWindow mw;
    private JTextField nameText = new JTextField();
    private JTextField inventoryText = new JTextField();
    private JTextField supplierIDText = new JTextField();
    private JButton addBtn = new JButton("Add");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * add book window
     * 
     * @param mw Main GUI window
     */
    public AddStockWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    /**
     * Initialize the whole window
     * 
     */
    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Add a New Stock");

        setSize(300, 200);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(5, 2));
        topPanel.add(new JLabel("Name : "));
        topPanel.add(nameText);
        topPanel.add(new JLabel("Inventory : "));
        topPanel.add(inventoryText);
        topPanel.add(new JLabel("Supplier ID : "));
        topPanel.add(supplierIDText);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(addBtn);
        bottomPanel.add(cancelBtn);

        addBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

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
            addStock();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    /**
     * add a book
     * 
     */
    private void addStock() {
        try {
            String name = nameText.getText();
            int inventory = Integer.parseInt(inventoryText.getText());
            int supplierID = Integer.parseInt(supplierIDText.getText());
            Command addStock = new AddStock(name, inventory, supplierID);
            addStock.execute(mw.getCentral(), LocalDate.now());
            mw.displayStocks();
            this.setVisible(false);
        } catch (CentralException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
