package commands;

import main.CentralException;
import model.Central;

public interface Command {

    public void execute(Central central) throws CentralException;
}
