package bcu.cmp5332.librarysystem.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Patron;

/**
 * Window to view patron details.
 * 
 * @author Qassim Hassan &amp; Kamil Elmi
 * 
 * @see Book
 * @see Patron
 */
public class PatronDetailsWindow extends JFrame implements ActionListener {
    private MainWindow mw;
    private Book book;
    private Patron patron;
    private JTextField idText = new JTextField();
    private JTextField nameText = new JTextField();
    private JTextField phoneText = new JTextField();

    /**
     * 
     * @param mw   Main GUI window
     * @param book Book to get patron information from
     * @throws LibraryException
     */
    public PatronDetailsWindow(MainWindow mw, Book book) throws LibraryException {
        this.mw = mw;
        this.book = book;
        this.patron = mw.getLibrary().getPatronByID(book.getLoan().getPatronID());
        initialize();
    }

    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Patron Details");

        setSize(300, 200);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 2));
        topPanel.add(new JLabel("ID : "));
        idText.setText(String.valueOf(book.getId()));
        topPanel.add(idText);

        topPanel.add(new JLabel("Name : "));
        nameText.setText(patron.getName());
        topPanel.add(nameText);

        topPanel.add(new JLabel("Phone : "));
        phoneText.setText(patron.getPhone());
        topPanel.add(phoneText);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        setLocationRelativeTo(mw);

        setVisible(true);

    }

    /** 
     * @param ae action event 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        return;
    }
}
