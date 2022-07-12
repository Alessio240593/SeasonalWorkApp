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

    Employer employes;

    public List<Account> gsonReader(String path) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        List<Account> result = null;

        try (BufferedReader br = new BufferedReader(
                new FileReader(path))) {
            //convert the json string back to object

            Type listType = new TypeToken<ArrayList<Account>>(){}.getType();
            result = new Gson().fromJson(br, listType);

        }
        catch (IOException ex) {
            System.out.println(ex.getCause());
        }

        return result;
    }

    @Override
    public Employer getEmployer() {
        return null;
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

        List<Account> accounts = gsonReader("./database/employer.json");

        for(Account account : accounts) {
            if(account.getUsername().equals(username) && account.getPassword().equals(password))
                return true;
        }

        return false;
    }
}
