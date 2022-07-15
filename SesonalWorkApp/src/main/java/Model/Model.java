package Model;

import java.util.List;

public class Model {
    private static Model instance = new Model();

    private Model() {}

    public static Model getModel() {
        return instance;
    }

    public Employer createEmployer(int id, BirthData birthInfo, Account account, Record record) {
        return new Employer(birthInfo, account, record);
    }

    public Job createJob(Season period, String companyName, String task, City area, double grossDailySalary, Jobs job, int year) {
        return new Job(period, companyName, task, area, grossDailySalary, job, year);
    }

    public Worker createWorker(String type, String address, List<Job> pastExperience, BirthData brithInfo, List<Language> languages, List<License> liscense, boolean withVehicle, List<City> activityArea, List<Season> period, Person emergencyContact, Record record) {
        return FactoryModel.getWorker(type, address, pastExperience, brithInfo, languages, liscense, withVehicle, activityArea, period, emergencyContact, record);
    }
}
