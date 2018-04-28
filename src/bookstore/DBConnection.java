package bookstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Anuj
 */
public class DBConnection {
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "password";
    private static final String CONN = "jdbc:postgresql://localhost:5432/postgres";  
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONN, USERNAME, PASSWORD);
    }
}
