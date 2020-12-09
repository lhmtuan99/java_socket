/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NguoiDungDTO;
import java.security.MessageDigest;
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
    ArrayList<NguoiDungDTO> dsnguoidung = new ArrayList<>();
    public NguoiDungDAO()
    {
        MyConnection connectiondatabase = new MyConnection();
        conn=connectiondatabase.getConnecDB();
        
    }
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
                System.out.println("da tao id thanh cong");
            }
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "thatbai");
        }
        return id+1;
        
    }
    public void them(NguoiDungDTO nguoidung)
    {
        try{
            System.out.println("DAO: "+nguoidung.getName());
            System.out.println("DAO: "+nguoidung.getUsername());
            System.out.println("DAO: "+nguoidung.getPassword());
            String qry = "INSERT INTO NguoiDung (nd_id,nd_name,nd_username,nd_password,nd_blockaccount,nd_blocktaode,nd_blockthi) VALUES ('"+createid()+"',N'"+nguoidung.getName()+"','"+nguoidung.getUsername()+"','"+getMD5(nguoidung.getPassword().toString())+"','1','1','1')";
            System.out.println(qry);
            st=conn.createStatement();
            System.out.println("qua 1 ");
            st.executeUpdate(qry);
            System.out.println("qua 2");
            }    
        catch(SQLException ex){
            System.out.println("DAO: lỗi thêm account");
        }
    }
    public int CheckLogin (String username, String password)
    {
        try {
            
            String qry = "SELECT * from NguoiDung where nd_username='"+username+"' and nd_password='"+getMD5(password)+"'";
            st=conn.createStatement();
            rs=st.executeQuery(qry);
    
        if(rs.next())
            {
                return 1;
            }
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "DAO: check login thất bại");
        }
        return 0;
    }
    public String getMD5(String md5)
    {
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            
            StringBuffer sb = new StringBuffer();
            for (int i =0;i< array.length;++i)
            {
                sb.append(Integer.toHexString(array[i] & 0xFF | 0x100 ).substring(1,3));
            }
            return sb.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public NguoiDungDTO getNguoiDung(String email)
    {
        NguoiDungDTO nd = new NguoiDungDTO();
        try{
            String query = "select * from NguoiDung where nd_username='"+email+"'";
            st = conn.createStatement();
            rs=st.executeQuery(query);
            System.out.println(rs);
            
            while (rs.next())
            {   
                //System.out.println(rs);
                
                nd.setNd_id(rs.getInt("nd_id"));
                nd.setName(rs.getString("nd_name"));
                nd.setUsername(rs.getString("nd_username"));
                nd.setBlockaccount(rs.getInt("nd_blockaccount"));
                nd.setBlocktaode(rs.getInt("nd_blocktaode"));
                nd.setBlockthi(rs.getInt("nd_blockthi"));
                return nd;
            }
            
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "lỗi đọc thông tin đề thi");
        }
        return nd;
    }
}
