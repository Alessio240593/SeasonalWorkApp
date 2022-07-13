package Control;

import Model.Employer;
import Model.Job;
import Model.Record;

import java.util.List;

public interface DaoEmployer {
    public List<Job> search(String... filter);
    public void updateRecord(Record record);
    public void addRecord(Record record);
    public boolean login(String username, String password);

}
