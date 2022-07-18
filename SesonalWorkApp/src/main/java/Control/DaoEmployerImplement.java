package Control;

import Model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    public void updateRecord(SeasonalWorker worker) {
        //write on json
        Boolean find = false;
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        String path = System.getenv("PWD") + "/src/resources/database/workers.json";
        List<SeasonalWorker> workers = Utility.gsonWorkerReader(path);
        SeasonalWorker toUpdate = new SeasonalWorker(worker.getId());

        for (SeasonalWorker w : workers) {
            if(worker.getId() == w.getId()) {
                toUpdate = w;
            }
        }

        String address = worker.getAddress();
        if(address != null) {
            toUpdate.setAddress(address);
        }


    }

    @Override
    public void addRecord(SeasonalWorker worker) {
        //write on json
        Boolean find = false;
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        String path = System.getenv("PWD") + "/src/resources/database/workers.json";
        //controllare se crea file
        List<SeasonalWorker> workers = Utility.gsonWorkerReader(path);
        if(workers == null) {
            workers = new ArrayList<>();
        }

        for (Worker w: workers) {
            if(w.equals(worker)) {
                find = true;
            }
        }

        if(!find)
            workers.add(worker);

        Utility.gsonWriter(path, gson, workers);
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
