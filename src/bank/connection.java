package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection
{
    // JDBC driver name and database URL
    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

     final String DB_URL = "jdbc:mysql://localhost:3306/bankprog";

    //  Database credentials
     final String USER = "root" ;
     final String PASS = "root";

    public  Connection getConn()
    {
        Connection con = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to a selected database...");
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            return con;
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}