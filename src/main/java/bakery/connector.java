package bakery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Statement;

public class connector {
    private static String dbUsername = "sqluser";
    private static String dbPassword = "password";
    private static String URL = "127.0.0.1";
    private static String port = "3306";
    private static String dbName = "db";
    // private static Connection con;

    public static DBConn con = new DBConn(URL, port, dbName, dbUsername, dbPassword);
}

class DBConn {

    private Connection con;
    private String dbURL;
    private String dbUsername;
    private String dbPassword;
    private String URL;
    private String port;
    private String dbName;

    DBConn(String URL, String port, String dbName, String dbUsername, String dbPassword) {
        this.URL = URL;
        this.port = port;
        this.dbName = dbName;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    public Connection connectDB() throws ClassNotFoundException, SQLException {

        dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
        System.out.println(dbURL);

        Properties p = new Properties();
        p.setProperty("user", dbUsername);
        p.setProperty("password", dbPassword);
        p.setProperty("useSSL", "false");
        p.setProperty("autoReconnect", "true");

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(dbURL, p);
        // System.out.println("debug");
        return con;
    }

    public void ExecuteStatement(String SQL) throws SQLException {

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            stmt.close();

        } catch (SQLException s) {
            s.printStackTrace();
        }
    }
}