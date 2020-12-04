/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NguoiDungDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class NguoiDungDAO {
    Connection conn = null;
    Statement st= null;
    ResultSet rs = null;
    ArrayList<NguoiDungDTO> dsnd = new ArrayList<>();
    
    public int createid()
    {
        int id = 0;
        try {
            
            String qry = "SELECT max (nd_id) as 'createid' FROM NguoiDung";
            st=conn.createStatement();
            rs=st.executeQuery(qry);
    
        while(rs.next())
            {
            id=rs.getInt("createid");   
            }
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "thatbai");
        }
        return id+1;
    }
    public void them(NguoiDungDTO nd)
    {
        try{
            String qry = "INSERT INTO NguoiDung (nd_id,nd_name,nd_username,nd_password,nd_trangthai) VALUES ('"+createid()+"','"+nd.getName()+"','"+nd.getUsername()+"','"+nd.getPassword()+"','active')";

            System.out.println(qry);
            st=conn.createStatement();
            st.executeUpdate(qry);
            }    
        catch(SQLException ex){
            System.out.println("DAO: lỗi thêm account");
        }
    }
}
