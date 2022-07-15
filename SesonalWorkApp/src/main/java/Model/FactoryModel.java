package Model;

import java.util.Date;
import java.util.List;

public class FactoryModel {

    public static final Worker getWorker(String type, String address, List<Job> pastExperience, Date brithInfo, List<Language> languages, List<License> liscense, boolean withVehicle, List<City> activityArea, List<Season> period, Person emergencyContact, Record record, int seasonalWorkerId) {
        if(type == null)
            return null;
        if(type.equalsIgnoreCase("SEASONAL")) {
            return new SeasonalWorker(address, pastExperience, brithInfo, languages, liscense,withVehicle, activityArea, period, emergencyContact, record, seasonalWorkerId);
        }
        return null;
    }
}
