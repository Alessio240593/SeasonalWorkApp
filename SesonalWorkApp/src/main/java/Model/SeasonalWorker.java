package Model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class SeasonalWorker extends Worker{

    private final int seasonalWorkerId;

    public SeasonalWorker(String address, List<Job> pastExperience, Date brithInfo, List<Language> languages, List<License> liscense, boolean withVehicle, List<City> activityArea, List<Season> period, Person emergencyContact, Record record, int seasonalWorkerId) {
        super(address, pastExperience, brithInfo, languages, liscense, withVehicle, activityArea, period, emergencyContact, record);
        this.seasonalWorkerId = seasonalWorkerId;
    }

    public int getSeasonalWorkerId() {
        return seasonalWorkerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SeasonalWorker that = (SeasonalWorker) o;
        return seasonalWorkerId == that.seasonalWorkerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), seasonalWorkerId);
    }
}
