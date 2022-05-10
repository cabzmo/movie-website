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

import commands.Command;
import commands.ReturnOrder;
import main.CentralException;
import model.Order;

/**
 * Window for a patron to borrow a order.
 * 
 * @author Qassim Hassan &amp; Kamil Elmi
 * 
 * @see Command
 * @see ReturnOrder
 * @see CentralException
 * @see Order
 */
public class ReturnOrderWindow extends JFrame implements ActionListener {
    private MainWindow mw;

    private JComboBox<String> ordersComboBox;
    private JButton returnBtn = new JButton("Return");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * 
     * @param mw Main GUI window
     * @throws CentralException
     */
    public ReturnOrderWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Return Order");

        setSize(300, 200);
        JPanel topPanel = new JPanel();
        Order[] ordersList = mw.getCentral().getReturnableOrders().toArray(new Order[0]);

        if (ordersList.length == 0) {
            // throw new CentralException("There are no delivered orders to return");
            JOptionPane.showMessageDialog(mw, "There are no delivered orders to return");

        } else {
            String[] ordersListString = new String[ordersList.length];
            for (int x = 0; x < ordersList.length; x++) {
                ordersListString[x] = ordersList[x].getDetailsShort();
            }
            ordersComboBox = new JComboBox<String>(ordersListString);
            topPanel.add(ordersComboBox);

            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new GridLayout(1, 3));
            bottomPanel.add(new JLabel("     "));
            bottomPanel.add(returnBtn);
            bottomPanel.add(cancelBtn);

            returnBtn.addActionListener(this);
            cancelBtn.addActionListener(this);
            ordersComboBox.addActionListener(this);

            this.getContentPane().add(topPanel, BorderLayout.CENTER);
            this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
            setLocationRelativeTo(mw);

            setVisible(true);
        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == returnBtn) {
            try {
                returnOrder();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (CentralException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    private void returnOrder() throws NumberFormatException, CentralException {
        String orderID = ordersComboBox.getSelectedItem().toString().split(" ")[1].replace("#", "");
        Command returnOrder = new ReturnOrder(Integer.valueOf(orderID));
        returnOrder.execute(mw.getCentral(), LocalDate.now());
        mw.displayOrders();
        this.setVisible(false);
    }

}
