package Model;

import java.util.Date;
import java.util.Objects;

public class BirthData {
    
    private Date birthDate;
    private String nationality;
    private String birthplace;

    public BirthData(Date birthDate, String nationality, String birthplace) {
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.birthplace = birthplace;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BirthData birthData = (BirthData) o;
        return Objects.equals(birthDate, birthData.birthDate) && Objects.equals(nationality, birthData.nationality) && Objects.equals(birthplace, birthData.birthplace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthDate, nationality, birthplace);
    }

    @Override
    public String toString() {
        return "BirthData{" +
                "birthDate=" + birthDate +
                ", nationality='" + nationality + '\'' +
                ", birthplace='" + birthplace + '\'' +
                '}';
    }
}
