package pl.coderslab.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Book;
import pl.coderslab.utils.DbUtil;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdminDao {

    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins(first_name,last_name,email,password,superadmin) VALUES (?,?,?,?,?)";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins where id = ?";
    private static final String FIND_ALL_ADMINS_QUERY = "SELECT * FROM admins";
    private static final String READ_ADMINS_QUERY = "SELECT * from admins where id = ?";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins SET first_name = ? , last_name = ?, email = ?, password = ?, superadmin = ? WHERE	id = ?";


    /**
     * Get admin by id
     *
     * @param adminId
     * @return
     */
    public static Admin read(Integer adminId) {
        Admin admin = new Admin();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ADMINS_QUERY);
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    admin.setId(resultSet.getInt("id"));
                    admin.setFirstName(resultSet.getString("first_name"));
                    admin.setLastName(resultSet.getString("last_name"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPassword(resultSet.getString("password"));
                    admin.setSuperadmin(resultSet.getInt("superadmin"));
                    admin.setEnable(resultSet.getInt("enable"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;

    }

    /**
     * Return all admins
     *
     * @return
     */
    public static List<Admin> findAll() {
        List<Admin> adminList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ADMINS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Admin adminToAdd = new Admin();
                adminToAdd.setId(resultSet.getInt("id"));
                adminToAdd.setFirstName(resultSet.getString("first_name"));
                adminToAdd.setLastName(resultSet.getString("last_name"));
                adminToAdd.setEmail(resultSet.getString("email"));
                adminToAdd.setPassword(resultSet.getString("password"));
                adminToAdd.setSuperadmin(resultSet.getInt("superadmin"));
                adminToAdd.setEnable(resultSet.getInt("enable"));
                adminList.add(adminToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;

    }


    /**
     * Create admin
     *
     * @param admin
     * @return
     */
    public static Admin create(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_ADMIN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, admin.getFirstName());
            insertStm.setString(2, admin.getLastName());
            insertStm.setString(3, admin.getEmail());
            insertStm.setString(4, admin.getPassword());
            insertStm.setInt(5, admin.getSuperadmin());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    admin.setId(generatedKeys.getInt(1));
                    return admin;
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
     * Remove admin by id
     *
     * @param adminId
     */
    public static void delete(Integer adminId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ADMIN_QUERY)) {
            statement.setInt(1, adminId);

            if (statement.executeUpdate() == 0) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Update admin
     *
     * @param admin
     */
    public static void update(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_QUERY);) {
            statement.setInt(6, admin.getId());
            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getEmail());
            statement.setString(4, admin.getPassword());
            statement.setInt(5, admin.getSuperadmin());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * VerifyEmailAndPass of admin
     *
     * @param email
     * @param password
     * @return
     */

    public static Admin verifyEmailAndPass(String email, String password) {
        List<Admin> adminList = AdminDao.findAll();

        for (Admin admin : adminList) {
            if (Objects.equals(admin.getEmail(), email) && BCrypt.checkpw(password, admin.getPassword())) {
                return admin;
            }
        }
        return null;
    }


}
