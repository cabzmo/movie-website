package commands;

import main.CentralException;
import model.Central;
import model.Order;

public class CompleteOrder implements Command {

    private Order order;

    public CompleteOrder(Order order) {
        this.order = order;
    }

    @Override
    public void execute(Central central) throws CentralException {
        order.setDelivered();
    }

}
