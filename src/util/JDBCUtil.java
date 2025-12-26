package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    public static Connection getConnection() throws SQLException {
        // Thông tin kết nối
        String url = "jdbc:sqlserver://SHERRY:1433;databaseName=du_lieu_cu_tru;encryp=false;trustServerCertificate=true;";
        String user = "sa";        // tên user SQL Server
        String password = "123456"; // mật khẩu SQL Server
        
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
}
