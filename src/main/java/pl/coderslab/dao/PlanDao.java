package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Plan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class PlanDao {

    // ZAPYTANIA SQL
    private static final String CREATE_PLAN_QUERY = "INSERT INTO plan(name, description, created, admin_id) VALUES (?, ?, ?, ?)";
    private static final String DELETE_PLAN_QUERY = "DELETE FROM plan where id = ?";
    private static final String FIND_ALL_PLANS_QUERY = "SELECT * FROM plan";
    private static final String READ_PLAN_QUERY = "SELECT * from plan where id = ?";
    private static final String UPDATE_PLAN_QUERY = "UPDATE	plan SET name = ? , description = ?, created = ?, admin_id = ? WHERE id = ?";
    private static final String COUNT_USER_PLANS = "SELECT COUNT(*) FROM plan WHERE plan.admin_id = ?";
    private static final String READ_LAST_QUERY = "";
    private static final String GET_LAST_PLAN_ID_QUERY = "SELECT MAX(id) from plan WHERE admin_id = ?";

    /**
     * Get plan by id
     *
     * @param planId
     * @return
     */
    public static Plan read(Integer planId) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_PLAN_QUERY)) {

            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getTimestamp("created"));
                    plan.setAdmin_id(resultSet.getInt("admin_id"));
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
    public static List<Plan> findAll() {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLANS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setCreated(resultSet.getTimestamp("created"));
                planToAdd.setAdmin_id(resultSet.getInt("admin_id"));
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
    public static Plan create(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, plan.getName());
            insertStm.setString(2, plan.getDescription());
            insertStm.setTimestamp(3, plan.getCreated());
            insertStm.setInt(4, plan.getAdmin_id());
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
             PreparedStatement statement = connection.prepareStatement(DELETE_PLAN_QUERY);) {
            statement.setInt(1, planId);

            if (statement.executeUpdate() == 0) {
                throw new NotFoundException("Plan not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Update plan
     *
     * @param plan
     */
    public static void update(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PLAN_QUERY);) {
            statement.setInt(5, plan.getId());
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.setTimestamp(3, plan.getCreated());
            statement.setInt(4, plan.getAdmin_id());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int countUserPlans(int userId) {
        int count = 0;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_USER_PLANS);) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    count = resultSet.getInt("COUNT(*)");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public static Plan readLast(int userId) {
        int lastId = 0;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_LAST_PLAN_ID_QUERY);) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    lastId = resultSet.getInt("MAX(id)");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return read(lastId);
    }

}
