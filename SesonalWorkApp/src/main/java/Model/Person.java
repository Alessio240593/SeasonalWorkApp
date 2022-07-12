package Model;

public class Person {
    private Record record;

    public Person(Record record) {
        this.record = record;
    }

    Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

}
