package Model;

public class Employer extends Person{
    private int id;
    private BirthData birthInfo;
    private Account account;

    public Employer(int id, BirthData birthInfo, Account account, Record record) {
        super(record);
        this.id = id;
        this.birthInfo = birthInfo;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public BirthData getBirthInfo() {
        return birthInfo;
    }

    public Account getAccount() {
        return account;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBirthInfo(BirthData birthInfo) {
        this.birthInfo = birthInfo;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
