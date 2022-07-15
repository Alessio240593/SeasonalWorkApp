package Model;

import java.util.Objects;

public class Record {
    private String name;
    private String surname;
    private String email;
    private String cellnum;

    public Record(String name, String surname, String email, String cellnum) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cellnum = cellnum;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getCellnum() {
        return cellnum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCellnum(String cellnum) {
        this.cellnum = cellnum;
    }

    @Override
    public String toString() {
        return "Record{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", cellnum='" + cellnum + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(name, record.name) && Objects.equals(surname, record.surname) && Objects.equals(email, record.email) && Objects.equals(cellnum, record.cellnum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, email, cellnum);
    }
}
