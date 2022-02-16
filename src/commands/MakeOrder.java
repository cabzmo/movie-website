package commands;

import main.CentralException;
import model.Central;
import model.Customer;
import model.Order;
import model.Stock;

public class MakeOrder implements Command {

    private Customer customer;
    private Stock stock;
    private int amount;

    public MakeOrder(Customer customer, Stock stock, int amount) {
        this.customer = customer;
        this.stock = stock;
        this.amount = amount;
    }

    @Override
    public void execute(Central central) throws CentralException {
        int maxOrderID = 0;
        if (central.getOrders().size() > 0) {
            int lastIndex = central.getOrders().size() - 1;
            maxOrderID = central.getOrders().get(lastIndex).getID();
        }

        Order order = new Order(++maxOrderID, customer, stock, amount);
        central.addOrder(order);

    }

}
