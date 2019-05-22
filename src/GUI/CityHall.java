package GUI;

public class CityHall {

    int level;
    String name;
    int workers;

    public CityHall() {
    }

    public CityHall(int level, String name, int workers) {
        super();
        this.setLevel(level);
        this.setName(name);
        this.setWorkers(workers);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorkers() {
        return workers;
    }

    public void setWorkers(int workers) {
        this.workers = workers;
    }
}
