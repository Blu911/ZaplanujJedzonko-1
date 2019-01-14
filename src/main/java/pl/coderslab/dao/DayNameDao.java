package pl.coderslab.dao;

import pl.coderslab.model.DayName;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DayNameDao {
    private static final String FIND_ALL_DAYNAMES_QUERY = "SELECT * FROM day_name";

    /**
     * Return all DayNames
     *
     * @return
     */
    public List<DayName> findAll() {
        List<DayName> DayNameList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_DAYNAMES_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                DayName DayNameToAdd = new DayName();
                DayNameToAdd.setName(resultSet.getString("name"));
                DayNameToAdd.setOrder(resultSet.getInt("order"));
                DayNameList.add(DayNameToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DayNameList;

    }
}
