package commands;

import model.Central;

import java.time.LocalDate;

import main.CentralException;

public class Help implements Command {

    @Override
    public void execute(Central central) throws CentralException {
        System.out.println(Command.HELP_MESSAGE);
    }
}
