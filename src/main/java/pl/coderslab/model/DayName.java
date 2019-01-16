package pl.coderslab.model;

public class DayName {
    private String name;
    private int order;

    public DayName() {

    }

    public DayName(String name, int order) {
        this.name = name;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return name + " " + order;
    }
}
