package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import commands.Command;
import commands.DeleteStock;
import commands.RemoveSupplier;
import main.CentralException;
import model.Stock;
import model.Supplier;

/**
 * Window for a supplier to be deleted.
 * 
 * @author Qassim Hassan &amp; Kamil Elmi
 * 
 * @see Command
 * @see RemoveSupplier
 * @see CentralException
 * @see Supplier
 */
public class ConfirmDeleteSupplierAndStock extends JFrame implements ActionListener {

    private MainWindow mw;

    private int supplierID;
    private JButton confirmBtn = new JButton("Confirm");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * 
     * @param mw Main GUI window
     */
    public ConfirmDeleteSupplierAndStock(MainWindow mw, int supplierID) {
        this.mw = mw;
        this.supplierID = supplierID;
        initialize();
    }

    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Are you sure?");

        setSize(600, 200);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 3));
        JLabel titleField = new JLabel("Are you sure? The following stocks will be deleted:");
        String stockListString = "<html>";
        System.out.println(mw.getCentral().getSupplierByID(supplierID).getSuppliedStocks());
        for (Stock stock : mw.getCentral().getSupplierByID(supplierID).getSuppliedStocks()) {
            stockListString += stock.getDetailsShort() + "<br>";
        }
        stockListString += "</html>";
        JLabel stockListField = new JLabel(stockListString);
        topPanel.add(new JLabel());
        topPanel.add(titleField);
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(stockListField);
        topPanel.add(new JLabel());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 5));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(confirmBtn);
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(cancelBtn);
        bottomPanel.add(new JLabel("     "));

        confirmBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        topPanel.setBorder(new EmptyBorder(20, 20, 0, 20));
        bottomPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == confirmBtn) {
            try {
                deleteSupplier();
                setVisible(false);
            } catch (NumberFormatException | CentralException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                setVisible(false);
            }
        } else if (ae.getSource() == cancelBtn) {
            setVisible(false);
        }
    }

    public void deleteSupplier() throws NumberFormatException, CentralException {

        Supplier supplier = mw.getCentral().getSupplierByID(supplierID);
        ArrayList<Stock> suppliedStock = supplier.getSuppliedStocks();

        ArrayList<Integer> stockIDs = new ArrayList<Integer>();
        for (Stock stock : suppliedStock) {
            stockIDs.add(stock.getID());
            if (stock.getOrders().size() > 0) {
                throw new CentralException("Order(s) are connected to supplier's stock. Cannot delete supplier");
            }
        }

        for (int stockID : stockIDs) {
            Stock stock = mw.getCentral().getStockByID(stockID);
            // if (stock.getOrders().size() > 0) {
            // throw new CentralException("Order(s) are connected to stock. Cannot delete
            // stock");
            // } else {
            stock.getSupplier().removeSuppliedStock(stock);
            mw.getCentral().removeStock(stock);
            // }
        }

        // for (Stock stock : suppliedStock) {
        // if (stock.getOrders().size() > 0) {
        // throw new CentralException("Order(s) are connected to stock. Cannot delete
        // stock");
        // } else {
        // mw.getCentral().getSupplierByID(supplierID).removeSuppliedStock(stock);
        // mw.getCentral().removeStock(stock);
        // }
        // }
        Command deleteSupplier = new RemoveSupplier(supplierID);
        deleteSupplier.execute(mw.getCentral(), LocalDate.now());
        mw.displaySuppliers();
        this.setVisible(false);

    }

}
