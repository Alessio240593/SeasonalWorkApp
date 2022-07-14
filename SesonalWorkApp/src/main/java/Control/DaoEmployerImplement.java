package Control;

import Model.Account;
import Model.Employer;
import Model.Job;
import Model.Record;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
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
    public void updateRecord(Record record) {

    }

    @Override
    public void addRecord(Record record) {
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
