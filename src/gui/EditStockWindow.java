package gui;

import commands.EditStock;
import commands.Command;
import main.CentralException;
import model.Stock;
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
 * Window to add a patron to the library.
 * 
 * @author Qassim Hassan &amp; Kamil Elmi
 * 
 * @see EditStock
 * @see Command
 * @see CentralException
 */
public class EditStockWindow extends JFrame implements ActionListener {
    private MainWindow mw;
    private int stockID;

    private JTextField nameText = new JTextField();
    // private JTextField supplierText = new JTextField();
    private JComboBox<String> suppliersComboBox;

    private JButton editBtn = new JButton("Edit");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * add patron window
     * 
     * @param mw Main GUI window
     * @throws CentralException
     */
    public EditStockWindow(MainWindow mw, int stockID) throws CentralException {
        this.mw = mw;
        this.stockID = stockID;
        Stock stock = mw.getCentral().getStockByID(stockID);
        nameText.setText(stock.getName());

        initialize();
    }

    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Edit a Stock");

        setSize(400, 300);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(5, 2));
        topPanel.add(new JLabel("Name : "));
        topPanel.add(nameText);
        topPanel.add(new JLabel("Supplier : "));
        Supplier[] suppliersList = mw.getCentral().getSuppliers().toArray(new Supplier[0]);
        String[] suppliersListString = new String[suppliersList.length];
        for (int x = 0; x < suppliersList.length; x++) {
            suppliersListString[x] = suppliersList[x].getDetailsShort();
        }
        suppliersComboBox = new JComboBox<String>(suppliersListString);

        try {
            suppliersComboBox.setSelectedIndex(mw.getCentral().getStockByID(stockID).getSupplier().getID() - 1);
        } catch (CentralException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            setVisible(false);
        }

        topPanel.add(suppliersComboBox);

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
            editStock();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    private void editStock() {
        try {
            String name = nameText.getText();
            // String phone = supplierText.getText();
            String supplierID = suppliersComboBox.getSelectedItem().toString().split(" ")[1].replace("#", "");

            Command editStock = new EditStock(stockID, name, Integer.valueOf(supplierID));
            editStock.execute(mw.getCentral(), LocalDate.now());

            mw.displayStocks();

            this.setVisible(false);
        } catch (CentralException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            setVisible(false);
        }
    }

}
