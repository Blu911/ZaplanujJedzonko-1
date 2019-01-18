package pl.coderslab.model;

import java.sql.Timestamp;

public class RecipePlan {

    private int id;
    private int recipe_id;
    private String meal_name;
    private int order;
    private int day_name_id;
    private int plan_id;
    public RecipePlan() {}

    public RecipePlan(int id, int recipe_id, String meal_name, int order, int day_name_id, int plan_id) {
        this.id = id;
        this.recipe_id = recipe_id;
        this.meal_name = meal_name;
        this.order = order;
        this.day_name_id = day_name_id;
        this.plan_id = plan_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getMeal_name() {
        return meal_name;
    }

    public void setMeal_name(String meal_name) {
        this.meal_name = meal_name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getDay_name_id() {
        return day_name_id;
    }

    public void setDay_name_id(int day_name_id) {
        this.day_name_id = day_name_id;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    @Override
    public String toString() {
        return "RecipePlan [id=" + id + ", recipe_id=" + recipe_id + ", meal_name=" + meal_name + ", order=" + order + ", day_name_id=" + day_name_id +
                ", plan_id=" + plan_id + "]";
    }
}
