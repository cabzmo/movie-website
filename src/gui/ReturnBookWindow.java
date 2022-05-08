package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.commands.ReturnBook;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * Window for a patron to return a book.
 * 
 * @author Qassim Hassan &amp; Kamil Elmi
 * 
 * @see Command
 * @see ReturnBook
 * @see LibraryException
 */
public class ReturnBookWindow extends JFrame implements ActionListener {
    private MainWindow mw;
    private JTextField bookIdField = new JTextField();
    private JTextField patronIdField = new JTextField();

    private JButton returnBtn = new JButton("Return");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * 
     * @param mw Main GUI window
     */
    public ReturnBookWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Return Book");

        setSize(300, 200);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(5, 2));
        topPanel.add(new JLabel("Book ID : "));
        topPanel.add(bookIdField);
        topPanel.add(new JLabel("Patron ID : "));
        topPanel.add(patronIdField);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(returnBtn);
        bottomPanel.add(cancelBtn);

        returnBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

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
        if (ae.getSource() == returnBtn) {
        	returnBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    private void returnBook() {
        try {
            int patronId = Integer.parseInt(patronIdField.getText());
            int bookId = Integer.parseInt(bookIdField.getText());
            Command returnBook = new ReturnBook(patronId, bookId);
            returnBook.execute(mw.getLibrary(), LocalDate.now());
            mw.displayPatrons();
            this.setVisible(false);
        } catch (LibraryException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}