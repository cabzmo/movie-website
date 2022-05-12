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
import model.Stock;

/**
 * Window to add a patron to the library.
 * 
 * @author Qassim Hassan &amp; Kamil Elmi
 * 
 * @see EditStock
 * @see Command
 * @see CentralException
 */
public class ShowStockDetailsWindow extends JFrame implements ActionListener {
    private MainWindow mw;
    private int stockID;

    private JTextField stockIDText = new JTextField();
    private JTextField nameText = new JTextField();
    private JTextField supplierText = new JTextField();
    private JTextField inventoryText = new JTextField();

    private JButton okBtn = new JButton("OK");

    /**
     * add patron window
     * 
     * @param mw Main GUI window
     * @throws CentralException
     */
    public ShowStockDetailsWindow(MainWindow mw, int stockID) throws CentralException {
        this.mw = mw;
        this.stockID = stockID;
        Stock stock = mw.getCentral().getStockByID(stockID);
        stockIDText.setText(String.valueOf(stock.getID()));
        nameText.setText(stock.getName());
        supplierText.setText(stock.getSupplier().getName());
        inventoryText.setText(String.valueOf(stock.getInventory()));
        initialize();
    }

    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Show a Stock");

        setSize(600, 200);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(4, 2));
        topPanel.add(new JLabel("ID : "));
        stockIDText.setEditable(false);
        stockIDText.setBorder(BorderFactory.createEmptyBorder());
        topPanel.add(stockIDText);

        topPanel.add(new JLabel("Name : "));
        nameText.setEditable(false);
        nameText.setBorder(BorderFactory.createEmptyBorder());
        topPanel.add(nameText);

        topPanel.add(new JLabel("Supplier : "));
        supplierText.setEditable(false);
        supplierText.setBorder(BorderFactory.createEmptyBorder());
        topPanel.add(supplierText);

        topPanel.add(new JLabel("Inventory : "));
        inventoryText.setEditable(false);
        inventoryText.setBorder(BorderFactory.createEmptyBorder());
        topPanel.add(inventoryText);

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
