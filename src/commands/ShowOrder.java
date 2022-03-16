package commands;

import main.CentralException;
import model.Central;

public class ShowOrder implements Command {

    private int id;

    public ShowOrder(int id) {
        this.id = id;
    }

    @Override
    public void execute(Central central) throws CentralException {
        System.out.println(central.getOrderByID(this.id));
    }

}
