package pl.jaro.model;

public class DayName {
    private int id;
    private String name;
    private String displayOrder;

    public DayName() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDisplayOrder(String displayOrder) {
        this.displayOrder = displayOrder;
    }

    @Override
    public String toString() {
        return "DayName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", displayOrder='" + displayOrder + '\'' +
                '}';
    }

    public DayName(int id, String name, String displayOrder) {
        this.id = id;
        this.name = name;
        this.displayOrder = displayOrder;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDisplayOrder() {
        return displayOrder;
    }
}
