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
import commands.CompleteOrder;
import main.CentralException;
import model.Order;

/**
 * Window for a patron to borrow a order.
 * 
 * @author Qassim Hassan &amp; Kamil Elmi
 * 
 * @see Command
 * @see DeleteOrder
 * @see CentralException
 * @see Order
 */
public class CompleteOrderWindow extends JFrame implements ActionListener {
    private MainWindow mw;

    private JComboBox<String> ordersComboBox;
    private JButton deliverBtn = new JButton("Delivered");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * 
     * @param mw Main GUI window
     */
    public CompleteOrderWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Deliver Order");

        setSize(600, 200);
        JPanel topPanel = new JPanel();
        Order[] ordersList = mw.getCentral().getOrders().toArray(new Order[0]);
        String[] ordersListString = new String[ordersList.length];
        for (int x = 0; x < ordersList.length; x++) {
            ordersListString[x] = ordersList[x].getDetailsShort();
        }
        ordersComboBox = new JComboBox<String>(ordersListString);
        topPanel.add(ordersComboBox);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 5));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(deliverBtn);
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(cancelBtn);
        bottomPanel.add(new JLabel("     "));

        deliverBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        ordersComboBox.addActionListener(this);

        topPanel.setBorder(new EmptyBorder(20, 20, 0, 20));
        bottomPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deliverBtn) {
            try {
                deleteOrder();
            } catch (NumberFormatException | CentralException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                setVisible(false);
            }
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    private void deleteOrder() throws NumberFormatException, CentralException {
        String orderID = ordersComboBox.getSelectedItem().toString().split(" ")[1].replace("#", "");
        Command deleteOrder = new CompleteOrder(mw.getCentral().getOrderByID(Integer.valueOf(orderID)));
        deleteOrder.execute(mw.getCentral(), LocalDate.now());
        mw.displayOrders();
        this.setVisible(false);
    }

}
