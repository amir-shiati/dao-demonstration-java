package core.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Connection Variable
    private static Connection con;

    private static String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static String DB_URL = "jdbc:postgresql://localhost:5432/dao_demonstration";
    private static String DB_USER = "postgres";
    private static String DB_PW = "";

    /**
     * Create a new Connection-Object to the DB
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static void createConnection()
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        System.out.println("-" + DB_DRIVER + "-");
        String unicode = "?useUnicode=yes&characterEncoding=UTF-8";
        Class.forName(DB_DRIVER);
        con = DriverManager.getConnection(DB_URL + unicode, DB_USER, DB_PW);

        System.out.println("--- database connection created ---" + DB_URL + " - AUTOCOMMIT " + con.getAutoCommit());

    }

    /**
     * Get DB Connection. If the connection is not open, a new connection is created
     *
     * @return
     */
    public static Connection getDBConnection() {
        try {
            if (con == null || con.isClosed() || !con.isValid(10000)) {
                DBConnection.createConnection();
            }
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;
    }

    /**
     *
     */
    public static void closeDBConnection() {
        try {
            con.close();
            System.out.println(" --- database connection closed --- ");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}