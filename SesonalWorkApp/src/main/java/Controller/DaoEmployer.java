package Controller;

import Model.Job;
import Model.SeasonalWorker;

import java.util.List;

public interface DaoEmployer {
    List<Job> search(String... filter);
    void updateRecord(SeasonalWorker worker);
    void addRecord(SeasonalWorker worker);
    boolean login(String username, String password);

}
