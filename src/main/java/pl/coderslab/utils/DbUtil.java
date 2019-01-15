package pl.coderslab.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//public class DbUtil {
//    private static DataSource dataSource;
//
//    public static Connection getConnection() throws SQLException {
//        return getInstance().getConnection();
//    }
//
//    private static DataSource getInstance() {
//        if (dataSource == null) {
//            try {
//                Context context = new InitialContext();
//                dataSource = (DataSource) context.lookup("java:comp/env/jdbc/scrumlab");
//            } catch (NamingException e) {
//                e.printStackTrace();
//            }
//        }
//        return dataSource;
//    }
//}

/** Na dole jest alternatywne połączenie zamiast tego powyżej gdyż nie chiało działać, jeżeli u Was to powyżej działa i możecie pracować z bazą to proszę o wytłumaczenie, Paweł */
public class DbUtil {
    private static String DB_URL = "jdbc:mysql://localhost:3306/scrumlab?useSSL=false&useUnicode=yes&characterEncoding=UTF-8";
    private static String DB_USER = "root";
    private static String DB_PASS = "coderslab";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {

        }
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}
