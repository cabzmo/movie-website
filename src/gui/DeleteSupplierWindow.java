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

        setSize(600, 200);
        JPanel topPanel = new JPanel();
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
        bottomPanel.add(delBtn);
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(cancelBtn);
        bottomPanel.add(new JLabel("     "));

        delBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        suppliersComboBox.addActionListener(this);

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
            String supplierIDString = suppliersComboBox.getSelectedItem().toString().split(" ")[1].replace("#", "");
            int supplierID = Integer.parseInt(supplierIDString);
            if (mw.getCentral().getSupplierByID(supplierID).getSuppliedStocks().size() > 0) {
                new ConfirmDeleteSupplierAndStock(mw, supplierID);
                setVisible(false);
            } else {
                try {
                    deleteSupplier();
                    setVisible(false);
                } catch (NumberFormatException | CentralException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    setVisible(false);
                }
            }
        } else if (ae.getSource() == cancelBtn) {
            setVisible(false);
        }

    }

    private void deleteSupplier() throws NumberFormatException, CentralException {
        String supplierID = suppliersComboBox.getSelectedItem().toString().split(" ")[1].replace("#", "");

        Command deleteSupplier = new RemoveSupplier(Integer.valueOf(supplierID));
        deleteSupplier.execute(mw.getCentral(), LocalDate.now());
        mw.displaySuppliers();
        this.setVisible(false);

    }

}
