package Control;

import Model.Job;
import Model.Record;

import java.util.List;

public interface DaoEmployer {
    List<Job> search(String... filter);
    void updateRecord(Record record);
    void addRecord(Record record);
    boolean login(String username, String password);

}
