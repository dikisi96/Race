package resume;

public class Result {
    private String name;
    private double time;

    public Result(String name, double time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public double getTime() {
        return time;
    }
}
