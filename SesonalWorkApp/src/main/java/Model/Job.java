package Model;

public class Job {
    private Season period;
    private String employerName;
    private String task;
    private City area;
    private float grossDailySalary;
    private Jobs job;

    public Season getPeriod() {
        return period;
    }

    public void setPeriod(Season period) {
        this.period = period;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
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

    public float getGrossDailySalary() {
        return grossDailySalary;
    }

    public void setGrossDailySalary(float grossDailySalary) {
        this.grossDailySalary = grossDailySalary;
    }

    public Jobs getJob() {
        return job;
    }

    public void setJob(Jobs job) {
        this.job = job;
    }
}
