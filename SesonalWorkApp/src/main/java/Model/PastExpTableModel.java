package Model;

import java.util.Objects;

public class PastExpTableModel {
    private String period;
    private String year;
    private String companyName;
    private String task;
    private String area;
    private String grossDailySalary;
    private String job;

    public PastExpTableModel(String period, String year, String companyName, String task, String area, String grossDailySalary, String job) {
        this.period = period;
        this.year = year;
        this.companyName = companyName;
        this.task = task;
        this.area = area;
        this.grossDailySalary = grossDailySalary;
        this.job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PastExpTableModel that = (PastExpTableModel) o;
        return Objects.equals(period, that.period) && Objects.equals(year, that.year) && Objects.equals(companyName, that.companyName) && Objects.equals(task, that.task) && Objects.equals(area, that.area) && Objects.equals(grossDailySalary, that.grossDailySalary) && Objects.equals(job, that.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(period, year, companyName, task, area, grossDailySalary, job);
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGrossDailySalary() {
        return grossDailySalary;
    }

    public void setGrossDailySalary(String grossDailySalary) {
        this.grossDailySalary = grossDailySalary;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
