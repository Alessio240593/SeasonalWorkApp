package Control;

import Model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class DaoEmployerImplement implements DaoEmployer{
    private static DaoEmployerImplement dao = new DaoEmployerImplement();

    private DaoEmployerImplement() {}

    public static DaoEmployerImplement getDao(){
        return dao;
    }

    @Override
    public List<Job> search(String... filter) {
        return null;
    }

    @Override
    public void updateRecord(Record record) {

    }

    @Override
    public void addRecord(SeasonalWorker worker) {
        //write on json
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        String path = System.getenv("PWD") + "/src/resources/database/workers.json";
        //controllare se crea file
        List<SeasonalWorker> workers = Utility.gsonWorkerReader(path);
        workers.add(worker);

        Utility.gsonWriter(path, gson, workers);

        //Utility.gsonWriter();
    }

    @Override
    public boolean login(String username, String password) {

        List<Account> accounts = Utility.gsonReader(System.getenv("PWD") + "/src/resources/database/employer.json");

        for(Account account : accounts) {
            if(account.getUsername().equals(username) && account.getPassword().equals(password))
                return true;
        }

        return false;
    }
}
