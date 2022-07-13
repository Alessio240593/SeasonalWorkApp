package Model;

import java.util.Objects;

public class Employer extends Person{
    private final int id;
    private final BirthData birthInfo;
    private Account account;

    public Employer(BirthData birthInfo, Account account, Record record) {
        super(record);
        this.birthInfo = birthInfo;
        this.account = account;
        // OCIO se non va lo mettiamo a mano
        this.id = this.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employer employer = (Employer) o;
        return id == employer.id && Objects.equals(birthInfo, employer.birthInfo) && Objects.equals(account, employer.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, birthInfo, account);
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

    public void setAccount(Account account) {
        this.account = account;
    }
}
