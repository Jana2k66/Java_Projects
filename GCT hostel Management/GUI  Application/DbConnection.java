package GCT_Hostel_Management;

import java.sql.*;
public class DbConnection {
    private static final String url = "jdbc:mysql://localhost:3306/hostel";
    private static final String userName = "root";
    private static final String passWord = "";

    public static Connection getConnection() throws SQLException{
        return  DriverManager.getConnection(url,userName,passWord);
    }

}