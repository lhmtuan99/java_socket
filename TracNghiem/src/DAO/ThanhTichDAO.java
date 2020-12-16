/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DeThiDTO;
import DTO.DiemDTO;
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
public class ThanhTichDAO {
    Connection conn = null;
    Statement st= null;
    ResultSet rs = null;
    public ThanhTichDAO()
    {
        MyConnection connectiondatabase = new MyConnection();
        conn=connectiondatabase.getConnecDB();
    }
    public ArrayList docDSDT()
    {
        ArrayList dsdt = new ArrayList<DeThiDTO>();
        try{
            String query = "select * from DeThi where dt_public=1";
            st = conn.createStatement();
            rs=st.executeQuery(query);
            while (rs.next())
            {   
                DeThiDTO dt = new DeThiDTO();
                dt.setDt_id(rs.getInt("dt_id"));
                dt.setMonthi(rs.getString("dt_monthi"));
                dt.setTongsonguoithi(rs.getInt("dt_songuoithi"));
                dt.setTieude(rs.getString("dt_tieude"));
                dt.setTongsonguoithi(rs.getInt("dt_songuoithi"));
                dt.setThoiluong(rs.getString("dt_thoiluong"));
                dt.setSocau(rs.getString("dt_socau"));
                dt.setDt_public(rs.getInt("dt_public"));
                dt.setNguoitao(rs.getInt("dt_nguoitao"));
                dsdt.add(dt);
            }
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "Thanhtichdao: lỗi đọc thông tin đề thi");
        }
        return dsdt;
    }
    public ArrayList docDSDiem(int dt_id)
    {
        ArrayList dsdiem = new ArrayList<DiemDTO>();
        try{
            String query = "select * from Diem where dt_id="+dt_id+"";
            st = conn.createStatement();
            rs=st.executeQuery(query);
            //System.out.println(rs);
            while (rs.next())
            {   
                //System.out.println(rs);
                DiemDTO diem = new DiemDTO();
                diem.setDiem(rs.getString("Diem"));
                diem.setDt_id(rs.getInt("dt_id"));
                diem.setNd_id(rs.getInt("nd_id"));
                dsdiem.add(diem);
                
            }
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "thanhtichdao: lỗi đọc thông tin diem");
        }
        return dsdiem;
    }
}
