package bcu.cmp5332.librarysystem.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Patron;

/**
 * Window to view book details.
 * 
 * @author Qassim Hassan &amp; Kamil Elmi
 * 
 * @see Book
 * @see Patron
 */
public class BookDetailsWindow extends JFrame implements ActionListener {
    private MainWindow mw;
    private Patron patron;

    /**
     * 
     * @param mw     Main GUI window
     * @param patron Patron to get book information from
     * @throws LibraryException
     */
    public BookDetailsWindow(MainWindow mw, Patron patron) throws LibraryException {
        this.mw = mw;
        this.patron = patron;
        initialize();
    }

    private void initialize() throws LibraryException {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Book Details");

        setSize(300, 200);
        JPanel topPanel = new JPanel();
        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        setLocationRelativeTo(mw);
        setVisible(true);

        List<Book> booksList = new ArrayList<>();

        for (int bookID : patron.getBookIDs()) {
            booksList.add(mw.getLibrary().getBookByID(bookID));
        }

        // List<Book> booksList = patron.getBooks();
        if (booksList.size() > 0) {
            String[] columns = new String[] { "ID", "Title", "Author", "Publisher", "Pub Date", "Status" };

            Object[][] data = new Object[booksList.size()][6];
            for (int i = 0; i < booksList.size(); i++) {
                Book book = booksList.get(i);
                data[i][0] = book.getId();
                data[i][1] = book.getTitle();
                data[i][2] = book.getAuthor();
                data[i][3] = book.getPublisher();
                data[i][4] = book.getPublicationYear();
                data[i][5] = book.getStatus();
            }

            JTable table = new JTable(data, columns);
            this.getContentPane().removeAll();
            this.getContentPane().add(new JScrollPane(table));
            this.revalidate();
        } else {
            topPanel.add(new JLabel("No books rented out."));
        }
    }

    /** action performed  
     * 
     * @param e action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        return;
    }
}
