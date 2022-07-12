package Model;

import java.util.Date;
import java.util.List;

public class Model {
    private static Model instance = new Model();

    private Model() {

    }

    public static Model getModel() {
        return instance;
    }

    public Employer createEmployer(int id, BirthData birthInfo, Account account, Record record) {
        return new Employer(id, birthInfo, account, record);
    }

    public Worker createWorker(String type, String address, List<Job> pastExperience, Date brithInfo, List<Language> languages, List<License> liscense, boolean withVehicle, List<City> activityArea, Season period, Person emergencyContact, Record record, int seasonalWorkerId) {
        return FactoryModel.getWorker(type, address, pastExperience, brithInfo, languages, liscense, withVehicle, activityArea, period, emergencyContact, record, seasonalWorkerId);
    }
}
