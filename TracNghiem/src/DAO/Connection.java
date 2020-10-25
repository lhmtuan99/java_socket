/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;

/**
 *
 * @author Admin
 */
public class Connection {
    Connection cnn=null;
    public Connection getConnectionDB()
    {
        try {
            String uRL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Todolist";
            String user = "sa";
            String pass = "sa";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cnn =  (Connection) DriverManager.getConnection(uRL, user, pass);
            System.out.println("Thanh Cong");
        }
        catch (Exception e){
            System.out.println("Khong thanh cong");
        }
        return cnn;
    }
}
