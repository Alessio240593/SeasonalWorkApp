package Model;

public class Record {
    private String name;
    private String surname;
    private String email;
    private String cellnum;

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
}
