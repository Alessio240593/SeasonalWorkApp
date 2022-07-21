package Model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public abstract class Worker extends Person{
    private String address;
    private List<Job> pastExperience;
    private BirthData brithInfo;
    private List<Language> languages;
    private List<License> license;
    private Boolean withVehicle;
    private List<City> activityArea;
    private List<Season> period;
    private Person emergencyContact;

    public Worker(){

    }

    public Worker(String address, List<Job> pastExperience, BirthData brithInfo, List<Language> languages,
                  List<License> license, Boolean withVehicle, List<City> activityArea, List<Season> period,
                  Person emergencyContact, Record record) {

        super(record);
        this.address = address;
        this.pastExperience = pastExperience;
        this.brithInfo = brithInfo;
        this.languages = languages;
        this.license = license;
        this.withVehicle = withVehicle;
        this.activityArea = activityArea;
        this.period = period;
        this.emergencyContact = emergencyContact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Job> getPastExperience() {
        return pastExperience;
    }

    public void setPastExperience(List<Job> pastExperience) {
        this.pastExperience = pastExperience;
    }

    public BirthData getBrithInfo() {
        return brithInfo;
    }

    public void setBrithInfo(BirthData brithInfo) {
        this.brithInfo = brithInfo;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<License> getLicense() {
        return license;
    }

    public void setLicense(List<License> license) {
        this.license = license;
    }

    public Boolean isWithVehicle() {
        return withVehicle;
    }

    public void setWithVehicle(Boolean withVehicle) {
        this.withVehicle = withVehicle;
    }

    public List<City> getActivityArea() {
        return activityArea;
    }

    public void setActivityArea(List<City> activityArea) {
        this.activityArea = activityArea;
    }

    public List<Season> getPeriod() {
        return period;
    }

    public void setPeriod(List<Season> period) {
        this.period = period;
    }

    public Person getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(Person emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "address='" + address + '\'' +
                ", pastExperience=" + pastExperience +
                ", brithInfo=" + brithInfo +
                ", languages=" + languages +
                ", license=" + license +
                ", withVehicle=" + withVehicle +
                ", activityArea=" + activityArea +
                ", period=" + period +
                ", emergencyContact=" + emergencyContact +
                ", Record=" + getRecord() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Worker worker = (Worker) o;
        return withVehicle == worker.withVehicle && Objects.equals(address, worker.address) && Objects.equals(pastExperience, worker.pastExperience) && Objects.equals(brithInfo, worker.brithInfo) && Objects.equals(languages, worker.languages) && Objects.equals(license, worker.license) && Objects.equals(activityArea, worker.activityArea) && Objects.equals(period, worker.period) && Objects.equals(emergencyContact, worker.emergencyContact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address, pastExperience, brithInfo, languages, license, withVehicle, activityArea, period, emergencyContact);
    }
}
