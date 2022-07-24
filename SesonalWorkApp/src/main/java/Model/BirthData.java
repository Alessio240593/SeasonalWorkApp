package Model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class BirthData {
    private LocalDate birthDate;
    private String nationality;
    private String birthplace;

    public BirthData(LocalDate birthDate, String nationality, String birthplace) {
        this.birthDate = birthDate;
        this.nationality = nationality;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
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
}
