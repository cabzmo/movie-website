package gui;

import commands.AddToInventory;
import commands.Command;
import main.CentralException;
import model.Stock;

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
public class AddToInventoryWindow extends JFrame implements ActionListener {
    private MainWindow mw;
    private JComboBox<String> stocksComboBox;
    private JTextField amountText = new JTextField();
    private JButton addBtn = new JButton("Add");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * add book window
     * 
     * @param mw Main GUI window
     */
    public AddToInventoryWindow(MainWindow mw) {
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

        setTitle("Add to Inventory");

        setSize(600, 200);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 2));
        topPanel.add(new JLabel("Stock : "));
        Stock[] stocksList = mw.getCentral().getStocks().toArray(new Stock[0]);
        String[] stocksListString = new String[stocksList.length];
        for (int x = 0; x < stocksList.length; x++) {
            stocksListString[x] = stocksList[x].getDetailsShort();
        }
        stocksComboBox = new JComboBox<String>(stocksListString);
        topPanel.add(stocksComboBox);
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel("Amount : "));
        topPanel.add(amountText);

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
            String stockIDString = stocksComboBox.getSelectedItem().toString().split(" ")[1].replace("#", "");
            int amount = Integer.parseInt(amountText.getText());
            Command addToInventory = new AddToInventory(Integer.valueOf(stockIDString), amount);
            addToInventory.execute(mw.getCentral(), LocalDate.now());
            mw.displayStocks();
            this.setVisible(false);
        } catch (CentralException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            setVisible(false);
        }
    }
}
