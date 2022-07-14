package Model;

import java.util.Date;
import java.util.List;

public abstract class Worker extends Person{
    private String address;
    private List<Job> pastExperience;
    private Date brithInfo;
    private List<Language> languages;
    private List<License> license;
    private boolean withVehicle;
    private List<City> activityArea;
    private Season period;
    private Person emergencyContact;

    public Worker(String address, List<Job> pastExperience, Date brithInfo, List<Language> languages,
                  List<License> license, boolean withVehicle, List<City> activityArea, Season period,
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

    public Date getBrithInfo() {
        return brithInfo;
    }

    public void setBrithInfo(Date brithInfo) {
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

    public boolean isWithVehicle() {
        return withVehicle;
    }

    public void setWithVehicle(boolean withVehicle) {
        this.withVehicle = withVehicle;
    }

    public List<City> getActivityArea() {
        return activityArea;
    }

    public void setActivityArea(List<City> activityArea) {
        this.activityArea = activityArea;
    }

    public Season getPeriod() {
        return period;
    }

    public void setPeriod(Season period) {
        this.period = period;
    }

    public Person getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(Person emergencyContact) {
        this.emergencyContact = emergencyContact;
    }
}
