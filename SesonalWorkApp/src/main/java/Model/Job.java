package Model;

import java.util.Objects;

public class Job {
    private Season period;
    private int year;
    private String companyName;
    private String task;
    private City area;
    private double grossDailySalary;
    private Jobs job;

    public Job(Season period, String companyName, String task,
               City area, double grossDailySalary, Jobs job, int year) {
        this.period = period;
        this.companyName = companyName;
        this.task = task;
        this.year = year;
        this.area = area;
        this.grossDailySalary = grossDailySalary;
        this.job = job;
    }

    public Season getPeriod() {
        return period;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
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

    public City getArea() {
        return area;
    }

    public void setArea(City area) {
        this.area = area;
    }

    public double getGrossDailySalary() {
        return grossDailySalary;
    }

    public void setGrossDailySalary(double grossDailySalary) {
        this.grossDailySalary = grossDailySalary;
    }

    public Jobs getJob() {
        return job;
    }

    public void setJob(Jobs job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Job{" +
                "period=" + period +
                ", year=" + year +
                ", companyName='" + companyName + '\'' +
                ", task='" + task + '\'' +
                ", area=" + area +
                ", grossDailySalary=" + grossDailySalary +
                ", job=" + job +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job1 = (Job) o;
        return year == job1.year && Double.compare(job1.grossDailySalary, grossDailySalary) == 0 && period == job1.period && Objects.equals(companyName, job1.companyName) && Objects.equals(task, job1.task) && area == job1.area && job == job1.job;
    }

    @Override
    public int hashCode() {
        return Objects.hash(period, year, companyName, task, area, grossDailySalary, job);
    }
}
