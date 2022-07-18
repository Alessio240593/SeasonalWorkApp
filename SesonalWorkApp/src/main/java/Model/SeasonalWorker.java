package Model;

import java.util.List;
import java.util.Objects;

public class SeasonalWorker extends Worker{

    private final int id;
    //da togliere
    public SeasonalWorker(int id){
        this.id = id;
    }

    public SeasonalWorker(String address, List<Job> pastExperience, BirthData brithInfo, List<Language> languages, List<License> liscense, Boolean withVehicle, List<City> activityArea, List<Season> period, Person emergencyContact, Record record) {
        super(address, pastExperience, brithInfo, languages, liscense, withVehicle, activityArea, period, emergencyContact, record);
        this.id = Math.abs(address.hashCode() ^ pastExperience.hashCode() ^ brithInfo.hashCode() ^ languages.hashCode() ^ liscense.hashCode() ^ activityArea.hashCode() ^ period.hashCode() ^ emergencyContact.hashCode() ^ record.hashCode());
    }

    public SeasonalWorker(int id, String address, List<Job> pastExperience, BirthData brithInfo, List<Language> languages, List<License> liscense, Boolean withVehicle, List<City> activityArea, List<Season> period, Person emergencyContact, Record record) {
        super(address, pastExperience, brithInfo, languages, liscense, withVehicle, activityArea, period, emergencyContact, record);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SeasonalWorker that = (SeasonalWorker) o;
        return id == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
