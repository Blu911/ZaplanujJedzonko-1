package pl.coderslab.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Recipe {

    private int id;
    private String name;
    private String ingredients;
    private String description;



    private Timestamp created;
    private Timestamp updated;
    private int preparation_time;
    private int admin_id;
    public Recipe() {}

    public Recipe(String name, String ingredients, String description, Timestamp created, Timestamp updated, int preparation_time, int admin_id) {
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.created = created;
        this.updated = updated;
        this.preparation_time = preparation_time;
        this.admin_id = admin_id;
    }

    @Override
    public String toString() {
        return "Recipe [id=" + id + ", name=" + name + ", ingredients=" + ingredients + ", description=" + description + ", created=" + created + ", updated=" + updated +
                ", preparation_time" + preparation_time + ", admin_id=" + admin_id + "]";
    }

    public String getIngredients() { return ingredients; }

    public void setIngredients(String ingredients) { this.ingredients = ingredients; }



    public int getPreparation_time() { return preparation_time; }

    public void setPreparation_time(int preparation_time) { this.preparation_time = preparation_time; }

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

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }
}
