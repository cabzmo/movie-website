package gui;

import commands.AddStock;
import commands.Command;
import main.CentralException;
import model.Supplier;

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
    private JComboBox<String> suppliersComboBox;
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

        setSize(600, 300);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(6, 2));
        topPanel.add(new JLabel("Name : "));
        topPanel.add(nameText);
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel("Inventory : "));
        topPanel.add(inventoryText);
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel("Supplier : "));
        Supplier[] suppliersList = mw.getCentral().getSuppliers().toArray(new Supplier[0]);
        String[] suppliersListString = new String[suppliersList.length];
        for (int x = 0; x < suppliersList.length; x++) {
            suppliersListString[x] = suppliersList[x].getDetailsShort();
        }
        suppliersComboBox = new JComboBox<String>(suppliersListString);
        topPanel.add(suppliersComboBox);

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
            String supplierIDString = suppliersComboBox.getSelectedItem().toString().split(" ")[1].replace("#", "");
            Command addStock = new AddStock(name, inventory, Integer.valueOf(supplierIDString));
            addStock.execute(mw.getCentral(), LocalDate.now());
            mw.displayStocks();
            this.setVisible(false);
        } catch (CentralException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            setVisible(false);
        }
    }
}
