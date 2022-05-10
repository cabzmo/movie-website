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
public class DeleteSupplierWindow extends JFrame implements ActionListener {

    private MainWindow mw;

    private JComboBox<String> suppliersComboBox;
    private JButton delBtn = new JButton("Remove");
    private JButton cancelBtn = new JButton("Cancel");
    private JButton confirmBtn = new JButton("Confirm");
    private JButton cancelBtn2 = new JButton("Cancel");

    /**
     * 
     * @param mw Main GUI window
     */
    public DeleteSupplierWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Remove Book");

        setSize(300, 200);
        JPanel topPanel = new JPanel();
        Supplier[] suppliersList = mw.getCentral().getSuppliers().toArray(new Supplier[0]);
        String[] suppliersListString = new String[suppliersList.length];
        for (int x = 0; x < suppliersList.length; x++) {
            suppliersListString[x] = suppliersList[x].getDetailsShort();
        }
        suppliersComboBox = new JComboBox<String>(suppliersListString);
        topPanel.add(suppliersComboBox);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(delBtn);
        bottomPanel.add(cancelBtn);

        delBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        suppliersComboBox.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delBtn) {
            String supplierIDString = suppliersComboBox.getSelectedItem().toString().split(" ")[1].replace("#", "");
            int supplierID = Integer.parseInt(supplierIDString);
            if (mw.getCentral().getSupplierByID(supplierID).getSuppliedStocks().size() > 0) {
                confirm();
            } else {
                try {
                    deleteSupplier();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (CentralException e) {
                    e.printStackTrace();
                }
            }
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
            mw.displaySuppliers();
        } else if (ae.getSource() == cancelBtn2) {
            this.setVisible(false);
            mw.displaySuppliers();
        } else if (ae.getSource() == confirmBtn) {
            try {
                deleteSupplier();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (CentralException e) {
                e.printStackTrace();
            }
        }

    }

    private void confirm() {
        String supplierID = suppliersComboBox.getSelectedItem().toString().split(" ")[1].replace("#", "");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Are you sure?");

        setSize(300, 200);
        JPanel topPanel = new JPanel();
        Supplier[] suppliersList = mw.getCentral().getSuppliers().toArray(new Supplier[0]);
        String[] suppliersListString = new String[suppliersList.length];
        for (int x = 0; x < suppliersList.length; x++) {
            suppliersListString[x] = suppliersList[x].getDetailsShort();
        }
        suppliersComboBox = new JComboBox<String>(suppliersListString);
        JLabel titleField = new JLabel("Are you sure you want to delete stocks too?");
        String stockListString = "";
        System.out.println(mw.getCentral().getSupplierByID(Integer.parseInt(supplierID)).getSuppliedStocks());
        for (Stock stock : mw.getCentral().getSupplierByID(Integer.parseInt(supplierID)).getSuppliedStocks()) {
            stockListString += stock.getDetailsShort() + "\n";
        }
        JLabel stockListField = new JLabel(stockListString);
        topPanel.add(titleField);
        topPanel.add(stockListField);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(confirmBtn);
        bottomPanel.add(cancelBtn2);

        delBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        suppliersComboBox.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);
    }

    private void deleteSupplier() throws NumberFormatException, CentralException {
        String supplierID = suppliersComboBox.getSelectedItem().toString().split(" ")[1].replace("#", "");

        if (mw.getCentral().getSupplierByID(Integer.valueOf(supplierID)).getSuppliedStocks().size() > 0) {
            for (Stock stock : mw.getCentral().getSupplierByID(Integer.valueOf(supplierID)).getSuppliedStocks()) {
                new DeleteStock(stock.getName()).execute(mw.getCentral(), LocalDate.now());
            }
        }

        Command deleteSupplier = new RemoveSupplier(Integer.valueOf(supplierID));
        deleteSupplier.execute(mw.getCentral(), LocalDate.now());
        mw.displaySuppliers();
        this.setVisible(false);
    }

}
