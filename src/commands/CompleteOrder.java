package commands;

import java.time.LocalDate;

import main.CentralException;
import model.Central;
import model.Order;

public class CompleteOrder implements Command {

    private Order order;

    public CompleteOrder(Order order) {
        this.order = order;
    }

    @Override
    public void execute(Central central, LocalDate currentDate) throws CentralException {
        order.setDelivered();
    }

}
