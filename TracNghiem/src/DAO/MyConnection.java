/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.Connection;
/**
 *
 * @author Admin
 */
public class MyConnection {
    static Connection conn=null;
    public Connection getConnecDB()
    {
        try {
            String uRL = "jdbc:sqlserver://127.0.0.1;databaseName=TracNghiem;useUnicode=true;characterEncoding=UTF-8";
            String user = "sa";
            String pass = "sa";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(uRL, user, pass);
            System.out.println("Thanh Cong");
        }
        catch (Exception e){
            System.out.println("Khong thanh cong");
        }
        return conn;
    }
//    public static int createid()
//    {
//        MyConnection mc = new MyConnection();
//        Connection cn = mc.getConnecDB();
//        Statement stm = null;
//        ResultSet rs= null;
//        int id = 0;
//        try {
//            
//            String qry = "SELECT max(dt_id) as 'createid' FROM DeThi";
//            stm=conn.createStatement();
//            rs=stm.executeQuery(qry);
//    
//        while(rs.next())
//            {
//            id=rs.getInt("createid");   
//            }
//        }
//        catch (SQLException ex){
//            JOptionPane.showMessageDialog(null, "thatbai");
//        }
//        return id+1;
//    }
//    // test connection
//    public static void main(String []args)
//    {
//        
//        System.out.println(createid());
//    }
}
