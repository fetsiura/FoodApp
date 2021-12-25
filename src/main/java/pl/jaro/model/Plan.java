package pl.jaro.model;

import java.time.LocalDateTime;

public class Plan {

    private int id;
    private String name;
    private String description;
    private String created;
    private int adminId;

    public Plan(int id, String name, String description, int adminId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = LocalDateTime.now().toString();
        this.adminId = adminId;
    }

    public Plan() {

    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", created='" + created + '\'' +
                ", adminId=" + adminId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = LocalDateTime.now().toString();
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}
