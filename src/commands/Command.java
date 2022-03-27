package commands;

import main.CentralException;
import model.Central;

public interface Command {

    public static final String HELP_MESSAGE = "Commands:\n"
            // + "\tlistcustomers print all customers\n"
            // + "\tliststocks print all stocks\n"
            // + "\taddcustomer add a new customer\n"
            // + "\taddstock add a new stock\n"
            // + "\tshowcustomer [customer id] show customer details\n"
            // + "\tshowstock [stock id] show stock details\n"
            // + "\tbuy [customer id] [stock id] borrow a customer\n"
            // + "\treturn [customer id] [stock id] return a customer\n"
            // + "\tadd [supplier id] [customer id] [stock id] renew a customer\n"
            // // + "\tloadgui loads the GUI version of the app\n"
            // + "\thelp prints this help message\n"
            // + "\texit exits the program"

            + "\taddstock                                       add a new stock [DONE]\n"
            + "\tremovestock                                    remove a stock [DONE]\n"
            + "\tliststocks                                     print all stocks\n"
            + "\tshowstock [stock id]                           show stock details [DONE]\n"
            + "\taddsupplier                                    add a new supplier [DONE]\n"
            + "\tremovesupplier                                 remove a new supplier\n"
            + "\tlistsuppliers                                  print all suppliers\n"
            + "\tshowsupplier [supplier id]                     show supplier details\n"
            + "\taddcustomer                                    add a new customer [DONE]\n"
            + "\tremovecustomer                                 remove a customer\n"
            + "\tlistcustomers                                  print all customers\n"
            + "\tshowcustomer [customer id]                     show customer details [DONE]\n"
            + "\tmakeorder [order id]                           make an order request [DONE]\n"
            + "\tcompleteorder [order id]                       complete an order [DONE]\n"
            + "\tremoveorder [order id]                         remove an order [DONE]\n"
            + "\tlistorders                                     print all orders\n"
            + "\tshoworder [order id]                           show order details [DONE]\n"
            + "\treturnorder [customer id] [order id]           return an order [DONE]\n"
            + "\tchangestocksupplier [stock id] [supplier id]   change the supplier of a stock [DONE]\n"
            + "\thelp                                           prints this help message\n"
            + "\texit                                           exits the program";

    public void execute(Central central) throws CentralException;
}
