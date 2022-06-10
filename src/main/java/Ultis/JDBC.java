package Ultis;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Phong Lu Minh
 */
public class JDBC {
    public static Connection Connection() throws SQLException{
        String username ="root";
        String password = "030502";
        String url = "jdbc:mysql://localhost:3306/LibraryManagement";
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}
