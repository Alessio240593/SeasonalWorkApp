package Controller;

import Model.SeasonalWorker;

public interface DaoEmployer {
    void updateRecord(SeasonalWorker worker);
    boolean addRecord(SeasonalWorker worker);
    boolean login(String username, String password);

}
