package Model;

import java.util.List;

public class FactoryModel {

    public static final Worker getWorker(String type, String address, List<Job> pastExperience, BirthData brithInfo, List<Language> languages, List<License> liscense, Boolean withVehicle, List<City> activityArea, List<Season> period, Person emergencyContact, Record record) {
        if(type == null)
            return null;
        if(type.equalsIgnoreCase("SEASONAL")) {
            return new SeasonalWorker(address, pastExperience, brithInfo, languages, liscense,withVehicle, activityArea, period, emergencyContact, record);
        }
        return null;
    }

    public static final Worker getWithoutIdWorker(String type, int id, String address, List<Job> pastExperience, BirthData brithInfo, List<Language> languages, List<License> liscense, Boolean withVehicle, List<City> activityArea, List<Season> period, Person emergencyContact, Record record) {
        if(type == null)
            return null;
        if(type.equalsIgnoreCase("SEASONAL")) {
            return new SeasonalWorker(id, address, pastExperience, brithInfo, languages, liscense,withVehicle , activityArea, period, emergencyContact, record);
        }
        return null;
    }
}
