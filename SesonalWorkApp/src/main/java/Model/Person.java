package Model;

import java.util.Objects;

public class Person {
    private Record record;

    public Person(){

    }

    public Person(Record record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return record.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(record, person.record);
    }

    @Override
    public int hashCode() {
        return Objects.hash(record);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
