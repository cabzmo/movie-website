package commands;

import java.time.LocalDate;

import gui.MainWindow;
import main.CentralException;
import model.Central;

public class LoadGUI implements Command {

    @Override
    public void execute(Central central, LocalDate currentDate) throws CentralException {
        new MainWindow(central);
    }

}
