package GUI;

import javax.xml.transform.Result;
import java.sql.*;

public class DBConnect {

    private static Connection con;
    private Statement st;
    private ResultSet rs;

    /*public DBConnect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/csdb", "root", "");
            st = con.createStatement();


        } catch(Exception ex) {
            System.out.println("Error " + ex);
        }
    }

    public static Connection getConnection() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/csdb", "root", "");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return con;
    }*/

    public DBConnect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql:sql7.freemysqlhosting.net/sql7292726", "sql7292726", "WNGLAkyC9V");
            st = con.createStatement();


        } catch(Exception ex) {
            System.out.println("Error " + ex);
        }
    }

    public static Connection getConnection() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net/sql7292726", "sql7292726", "WNGLAkyC9V");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return con;
    }
}