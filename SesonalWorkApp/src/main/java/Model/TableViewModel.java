package Model;

public class TableViewModel {
    private String name;
    private String surname;
    private String email;
    private String cell;
    private String birthDate;
    private String birthPlace;

    public TableViewModel(String name, String surname, String email, String cell, String birthDate, String birthPlace) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cell = cell;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }
}
