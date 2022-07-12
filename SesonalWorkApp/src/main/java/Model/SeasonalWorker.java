package Model;

import java.util.Date;
import java.util.List;

public class SeasonalWorker extends Worker{

    private int seasonalWorkerId;


    public SeasonalWorker(String address, List<Job> pastExperience, Date brithInfo, List<Language> languages, List<License> liscense, boolean withVehicle, List<City> activityArea, Season period, Person emergencyContact, Record record, int seasonalWorkerId) {
        super(address, pastExperience, brithInfo, languages, liscense, withVehicle, activityArea, period, emergencyContact, record);
        this.seasonalWorkerId = seasonalWorkerId;
    }
}
