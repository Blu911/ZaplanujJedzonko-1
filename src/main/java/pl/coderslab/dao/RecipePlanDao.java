package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.RecipePlan;
import pl.coderslab.model.RecipePlan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RecipePlanDao {


    // ZAPYTANIA SQL
    private static final String CREATE_RECIPE_PLAN_QUERY = "INSERT INTO plan(recipe_id, meal_name, order, day_name_id, plan_id) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_RECIPE_PLAN_QUERY = "DELETE FROM plan where id = ?";
    private static final String FIND_ALL_RECIPE_PLANS_QUERY = "SELECT * FROM recipe_plan";
    private static final String READ_RECIPE_PLAN_QUERY = "SELECT * from recipe_plan where id = ?";
    private static final String READ_RECIPE_PLAN_BY_ADMIN_ID_QUERY = "SELECT * from recipe_plan where admin_id = ?";




    /**
     * Get recipe by admin_id
     *
     * @param adminId
     * @return
     */
    public static List<RecipePlan> findAllByAdminId(Integer adminId) {
        List<RecipePlan> recipeplanList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_PLAN_BY_ADMIN_ID_QUERY)) {

            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    RecipePlan planToAdd = new RecipePlan();
                    planToAdd.setId(resultSet.getInt("id"));
                    planToAdd.setRecipe_id(resultSet.getInt("recipe_id"));
                    planToAdd.setMeal_name(resultSet.getString("meal_name"));
                    planToAdd.setOrder(resultSet.getInt("order"));
                    planToAdd.setDay_name_id(resultSet.getInt("day_name_id"));
                    planToAdd.setPlan_id(resultSet.getInt("plan_id"));
                    recipeplanList.add(planToAdd);
                }
            }

        } catch(Exception e){
            e.printStackTrace();
        }
        return recipeplanList;
    }
    /**
     * Get plan by id
     *
     * @param planId
     * @return
     */
    public static RecipePlan read(Integer planId) {
        RecipePlan plan = new RecipePlan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_PLAN_QUERY)) {

            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setRecipe_id(resultSet.getInt("recipe_id"));
                    plan.setMeal_name(resultSet.getString("meal_name"));
                    plan.setOrder(resultSet.getInt("order"));
                    plan.setDay_name_id(resultSet.getInt("day_name_id"));
                    plan.setPlan_id(resultSet.getInt("plan_id"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;
    }

    /**
     * Return all plans
     *
     * @return
     */
    public static List<RecipePlan> findAll() {
        List<RecipePlan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPE_PLANS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                RecipePlan planToAdd = new RecipePlan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setRecipe_id(resultSet.getInt("recipe_id"));
                planToAdd.setMeal_name(resultSet.getString("meal_name"));
                planToAdd.setOrder(resultSet.getInt("order"));
                planToAdd.setDay_name_id(resultSet.getInt("day_name_id"));
                planToAdd.setPlan_id(resultSet.getInt("plan_id"));
                planList.add(planToAdd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return planList;

    }

    /**
     * Create plan
     *
     * @param plan
     * @return
     */
    public static RecipePlan create(RecipePlan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_RECIPE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setInt(1,plan.getRecipe_id());
            insertStm.setString(2,plan.getMeal_name());
            insertStm.setInt(3,plan.getOrder());
            insertStm.setInt(4,plan.getDay_name_id());
            insertStm.setInt(5,plan.getPlan_id());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    plan.setId(generatedKeys.getInt(1));
                    return plan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Remove plan by id
     *
     * @param planId
     */
    public static void delete(Integer planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_PLAN_QUERY);) {
            statement.setInt(1, planId);

            if (statement.executeUpdate() == 0) {
                throw new NotFoundException("Plan not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
