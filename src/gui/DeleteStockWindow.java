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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import commands.Command;
import commands.DeleteStock;
import main.CentralException;
import model.Stock;

/**
 * Window for a patron to borrow a stock.
 * 
 * @author Qassim Hassan &amp; Kamil Elmi
 * 
 * @see Command
 * @see DeleteStock
 * @see CentralException
 * @see Stock
 */
public class DeleteStockWindow extends JFrame implements ActionListener {
    private MainWindow mw;

    private JComboBox<String> stocksComboBox;
    private JButton delBtn = new JButton("Delete");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * 
     * @param mw Main GUI window
     */
    public DeleteStockWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Delete Stock");

        setSize(600, 200);
        JPanel topPanel = new JPanel();
        Stock[] stocksList = mw.getCentral().getStocks().toArray(new Stock[0]);
        String[] stocksListString = new String[stocksList.length];
        for (int x = 0; x < stocksList.length; x++) {
            stocksListString[x] = stocksList[x].getDetailsShort();
        }
        stocksComboBox = new JComboBox<String>(stocksListString);
        topPanel.add(stocksComboBox);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 5));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(delBtn);
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(cancelBtn);
        bottomPanel.add(new JLabel("     "));

        delBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        stocksComboBox.addActionListener(this);

        topPanel.setBorder(new EmptyBorder(20, 20, 0, 20));
        bottomPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delBtn) {
            try {
                deleteStock();
            } catch (NumberFormatException | CentralException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                setVisible(false);
            }
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    private void deleteStock() throws NumberFormatException, CentralException {
        String stockID = stocksComboBox.getSelectedItem().toString().split(" ")[1].replace("#", "");
        if (mw.getCentral().getStockByID(Integer.valueOf(stockID)).getOrders().size() > 0) {
            throw new CentralException("Order(s) are connected to stock. Cannot delete stock");
        } else {
            Command deleteStock = new DeleteStock(Integer.valueOf(stockID));
            deleteStock.execute(mw.getCentral(), LocalDate.now());
        }

        mw.displayStocks();
        this.setVisible(false);
    }

}
