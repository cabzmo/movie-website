package commands;

import main.CentralException;
import model.Central;

public class ShowStock implements Command {

    private int id;

    public ShowStock(int id) {
        this.id = id;
    }

    @Override
    public void execute(Central central) throws CentralException {
        System.out.println(central.getStockByID(this.id));
    }

}
