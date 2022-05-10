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
import commands.CancelOrder;
import main.CentralException;
import model.Order;

/**
 * Window for a patron to borrow a order.
 * 
 * @author Qassim Hassan &amp; Kamil Elmi
 * 
 * @see Command
 * @see CancelOrder
 * @see CentralException
 * @see Order
 */
public class CancelOrderWindow extends JFrame implements ActionListener {
    private MainWindow mw;

    private JComboBox<String> ordersComboBox;
    private JButton cancelOrderBtn = new JButton("Cancel");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * 
     * @param mw Main GUI window
     * @throws CentralException
     */
    public CancelOrderWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Cancel Order");

        setSize(300, 200);
        JPanel topPanel = new JPanel();
        Order[] ordersList = mw.getCentral().getCancellableOrders().toArray(new Order[0]);

        if (ordersList.length == 0) {
            JOptionPane.showMessageDialog(mw, "There are no undelivered orders to return");

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
            bottomPanel.add(cancelOrderBtn);
            bottomPanel.add(cancelBtn);

            cancelOrderBtn.addActionListener(this);
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
        if (ae.getSource() == cancelOrderBtn) {
            try {
                cancelOrder();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (CentralException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    private void cancelOrder() throws NumberFormatException, CentralException {
        String orderID = ordersComboBox.getSelectedItem().toString().split(" ")[1].replace("#", "");
        Command cancelOrder = new CancelOrder(Integer.valueOf(orderID));
        cancelOrder.execute(mw.getCentral(), LocalDate.now());
        mw.displayOrders();
        this.setVisible(false);
    }

}
