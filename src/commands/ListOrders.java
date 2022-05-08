package commands;

import java.time.LocalDate;
import java.util.List;

import main.CentralException;
import model.Central;
import model.Order;

public class ListOrders implements Command {

    @Override
    public void execute(Central central, LocalDate currentDate) throws CentralException {
        List<Order> orders = central.getOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
        System.out.println(orders.size() + " order(s)");
    }

}
