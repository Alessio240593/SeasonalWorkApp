package Controller;

import Model.Job;
import Model.SeasonalWorker;

import java.util.List;

public interface DaoEmployer {
    void updateRecord(SeasonalWorker worker);
    boolean addRecord(SeasonalWorker worker);
    boolean login(String username, String password);

}
