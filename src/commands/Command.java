package commands;

import main.CentralException;
import model.Central;

public interface Command {

    public static final String HELP_MESSAGE = "Commands:\n"
            + "\tlistcustomers                                  print all customers\n"
            + "\tliststocks                                     print all stocks\n"
            + "\taddcustomer                                    add a new customer\n"
            + "\taddstock                                       add a new stock\n"
            + "\tshowcustomer [customer id]                     show customer details\n"
            + "\tshowstock [stock id]                           show stock details\n"
            + "\tbuy [customer id] [stock id]                   borrow a customer\n"
            + "\treturn [customer id] [stock id]                return a customer\n"
            + "\tadd [supplier id] [customer id] [stock id]     renew a customer\n"
            // + "\tloadgui loads the GUI version of the app\n"
            + "\thelp                                           prints this help message\n"
            + "\texit                                           exits the program";

    public void execute(Central central) throws CentralException;
}
