package data;

import java.io.IOException;

import main.CentralException;
import model.Central;

public interface DataManager {

    public static final String SEPARATOR = "::";

    public void loadData(Central central) throws IOException, CentralException;

    public void storeData(Central central) throws IOException;
}
