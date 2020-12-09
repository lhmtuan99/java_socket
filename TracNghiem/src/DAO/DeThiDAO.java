/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DeThiDTO;
import DTO.otpDTO;
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
public class DeThiDAO {
    Connection conn = null;
    Statement st= null;
    ResultSet rs = null;
    ArrayList<DeThiDTO> dsdt = new ArrayList<>();
    public DeThiDAO()
    {
        MyConnection connectiondatabase = new MyConnection();
        conn=connectiondatabase.getConnecDB();
        
    }
    public ArrayList docDSDT(int id)
    {
        ArrayList dsdt = new ArrayList<DeThiDTO>();
        try{
            String query = "select * from DeThi where dt_nguoitao="+id;
            st = conn.createStatement();
            rs=st.executeQuery(query);
            System.out.println(rs);
            while (rs.next())
            {   
                System.out.println(rs);
                DeThiDTO dt = new DeThiDTO();
                dt.setDt_id(rs.getInt("dt_id"));
                dt.setMonthi(rs.getString("dt_monthi"));
                dt.setSocau(rs.getString("dt_socau"));
                dt.setThoiluong(rs.getString("dt_thoiluong"));
                dt.setTieude(rs.getString("dt_tieude"));
                dt.setTongsonguoithi(rs.getInt("dt_songuoithi"));
                dt.setNguoitao(rs.getInt("dt_nguoitao"));
                dsdt.add(dt);
            }
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "lỗi đọc thông tin đề thi");
        }
        return dsdt;
    }
    public int createid()
    {
        int id = 0;
        try {
            
            String qry = "SELECT max(dt_id) as 'createid' FROM DeThi";
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
    public void them (DeThiDTO dt)
    {
        try{
            //Int b = rs + 1;
            String qry = "Insert into DeThi values (";
            qry = qry+"'"+createid()+"'";
            qry = qry+","+"N'"+dt.getTieude()+"'";
            qry = qry+","+"N'"+dt.getThoiluong()+"'";
            qry = qry+","+"N'"+dt.getMonthi()+"'";
            qry= qry+","+"N'"+dt.getSocau()+"'";
            qry = qry+")";
            System.out.println(qry);
            st=conn.createStatement();
            st.executeUpdate(qry);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "lỗi thêm đề thi");
        }
    }
    public void sua(String id,DeThiDTO dt)
    {
        try{
            String qry = "update DeThi set ";
            qry = qry+"dt_tieude=N"+"'"+dt.getTieude()+"'";
            qry = qry+","+"dt_thoiluong=N"+"'"+dt.getThoiluong()+"'";
            qry = qry+","+"dt_monthi=N"+"'"+dt.getMonthi()+"'";
            qry = qry+","+"dt_socau=N"+"'"+dt.getSocau()+"'";
            qry = qry+" "+ "where dt_id='"+dt.getDt_id()+"'"; 
            
            st = conn.createStatement();
            st.executeUpdate(qry);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "lỗi sửa thông tin đề thi");
        }
    }
    public void xoa (String id)
    {
        try {
            String qry = "delete from DeThi where dt_id='"+id+"'";
            st = conn.createStatement();
            st.executeUpdate(qry);
            System.out.println("xoa thanh cong");
            System.out.println(id);
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "lỗi xóa sách");
        }
    }
}
