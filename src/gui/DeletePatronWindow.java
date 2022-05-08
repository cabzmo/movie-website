package bcu.cmp5332.librarysystem.gui;

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

import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.commands.DeletePatron;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Patron;

/**
 * Window for a patron to be deleted.
 * 
 * @author Qassim Hassan &amp; Kamil Elmi
 * 
 * @see Command
 * @see DeletePatron
 * @see LibraryException
 * @see Patron
 */
public class DeletePatronWindow extends JFrame implements ActionListener {

    private MainWindow mw;

    private JComboBox<String> patronsComboBox;
    private JButton delBtn = new JButton("Delete");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * 
     * @param mw Main GUI window
     */
    public DeletePatronWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Delete Book");

        setSize(300, 200);
        JPanel topPanel = new JPanel();
        Patron[] patronsList = mw.getLibrary().getPatrons().toArray(new Patron[0]);
        String[] booksListString = new String[patronsList.length];
        for (int x = 0; x < patronsList.length; x++) {
            booksListString[x] = patronsList[x].getDetailsShort();
        }
        patronsComboBox = new JComboBox<String>(booksListString);
        topPanel.add(patronsComboBox);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(delBtn);
        bottomPanel.add(cancelBtn);

        delBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        patronsComboBox.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delBtn) {
            try {
                deletePatron();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (LibraryException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    private void deletePatron() throws NumberFormatException, LibraryException {
        String patronID = patronsComboBox.getSelectedItem().toString().split(" ")[1].replace("#", "");
        Command deletePatron = new DeletePatron(Integer.valueOf(patronID));
        deletePatron.execute(mw.getLibrary(), LocalDate.now());
        mw.displayBooks();
        this.setVisible(false);
    }

}
