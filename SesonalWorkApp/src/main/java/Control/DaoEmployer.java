package Control;

import Model.Job;
import Model.Record;
import Model.SeasonalWorker;
import Model.Worker;

import java.util.List;

public interface DaoEmployer {
    List<Job> search(String... filter);
    void updateRecord(Record record);
    void addRecord(SeasonalWorker worker);
    boolean login(String username, String password);

}
