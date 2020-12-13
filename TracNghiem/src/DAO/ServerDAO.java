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
public class ServerDAO {
    Connection conn = null;
    Statement st= null;
    ResultSet rs = null;
    ArrayList<NguoiDungDTO> dsnguoidung = new ArrayList<>();
    public ServerDAO()
    {
        MyConnection connectiondatabase = new MyConnection();
        conn=connectiondatabase.getConnecDB();
    }
        public ArrayList docDSND()
    {
        ArrayList dsnd = new ArrayList<NguoiDungDTO>();
        try{
            String query = "select * from NguoiDung";
            st = conn.createStatement();
            rs=st.executeQuery(query);
            while (rs.next())
            {   
                NguoiDungDTO nd = new NguoiDungDTO();
                nd.setNd_id(rs.getInt("nd_id"));
                nd.setName(rs.getString("nd_name"));
                nd.setBlockaccount(rs.getInt("nd_blockaccount"));
                nd.setBlocktaode(rs.getInt("nd_blocktaode"));
                nd.setBlockthi(rs.getInt("nd_blockthi"));
                dsnd.add(nd);
            }
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "DAO: đọc người dùng bị lỗi ");
        }
        return dsnd;
    }
    public int kiemtraNguoiDung (int id)
    {
        //String a="";
        try {
            
            String qry = "SELECT * from NguoiDung where nd_id='"+id+"'";
            st=conn.createStatement();
            rs=st.executeQuery(qry);
    
        if(rs.next())
            {
                System.out.println("Người dùng có tồn tại");
                return 1;
            }
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "DAO: Kiem tra nguoi dung that bai");
        }
        return 0;
    }
    public void Blockaccount(int id)
    {
        try{
            String qry = "update NguoiDung set nd_blockaccount=0 where nd_id='"+id+"'";
            
            st = conn.createStatement();
            st.executeUpdate(qry);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "lỗi Blockaccount");
        }
    }
    public void Blocktaode(int id)
    {
        try{
            String qry = "update NguoiDung set nd_blocktaode=0 where nd_id='"+id+"'";
            
            st = conn.createStatement();
            st.executeUpdate(qry);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "lỗi block tạo đề");
        }
    }
    public void Blockthi(int id)
    {
        try{
            String qry = "update NguoiDung set nd_blockthi=0 where nd_id='"+id+"'";
            
            st = conn.createStatement();
            st.executeUpdate(qry);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "lỗi Block Thi");
        }
    }
    public void UnBlockaccount(int id)
    {
        try{
            String qry = "update NguoiDung set nd_blockaccount=1 where nd_id='"+id+"'";
            
            st = conn.createStatement();
            st.executeUpdate(qry);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "lỗi UnBlockaccount");
        }
    }
    public void UnBlocktaode(int id)
    {
        try{
            String qry = "update NguoiDung set nd_blocktaode=1 where nd_id='"+id+"'";
            
            st = conn.createStatement();
            st.executeUpdate(qry);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "lỗi unblock tạo đề");
        }
    }
    public void UnBlockthi(int id)
    {
        try{
            String qry = "update NguoiDung set nd_blockthi=1 where nd_id='"+id+"'";
            
            st = conn.createStatement();
            st.executeUpdate(qry);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "lỗi UnBlock Thi");
        }
    }
}
