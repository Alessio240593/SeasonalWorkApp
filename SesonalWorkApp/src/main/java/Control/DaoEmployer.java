package Control;

import Model.Job;
import Model.Record;
import Model.Worker;

import java.util.List;

public interface DaoEmployer {
    List<Job> search(String... filter);
    void updateRecord(Record record);
    void addRecord(Worker worker);
    boolean login(String username, String password);

}
