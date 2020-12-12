/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.CauHoi;
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
                dt.setDt_public(rs.getInt("dt_public"));
                dsdt.add(dt);
            }
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "lỗi đọc thông tin đề thi");
        }
        return dsdt;
    }
    public ArrayList GetAllDeThiPublic()
    {
        ArrayList dsdt = new ArrayList<DeThiDTO>();
        try{
            String query = "select * from DeThi where dt_public=1";
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
                dt.setDt_public(rs.getInt("dt_public"));
                dsdt.add(dt);
            }
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "lỗi đề thi public");
        }
        return dsdt;
    }
    public DTO.DeThiDTO GetDeThi(String id)
    {
        DTO.DeThiDTO dithinew = new DeThiDTO();
        try{
            String query = "select * from DeThi where dt_id="+id;
            st = conn.createStatement();
            rs=st.executeQuery(query);
            System.out.println(rs);
            while (rs.next())
            {   
                dithinew.setDt_id(rs.getInt("dt_id"));
                dithinew.setSocau(rs.getString("dt_socau"));
                dithinew.setThoiluong(rs.getString("dt_thoiluong"));
                return dithinew;
            }
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "lỗi load dethi combobox thi thu");
        }
        return dithinew;
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
    public int createidCauhoi()
    {
        int id = 0;
        try {
            
            String qry = "SELECT max(ch_id) as 'createid' FROM CauHoii";
            st=conn.createStatement();
            rs=st.executeQuery(qry);
    
        while(rs.next())
            {
            id=rs.getInt("createid");   
            }
        }
        catch (SQLException ex){
            //JOptionPane.showMessageDialog(null, "thatbai");
        }
        return id+1;
    }
    public void UpdateCauhoi(CauHoi ch)
    {
        try{
            String qry = "UPDATE CauHoii SET CauHoi=N'"+ch.getCauhoi()+"',DapAnA=N'"+ch.getDapanA()+"',DanAnB=N'"+ch.getDapanB()+"',DanAnC=N'"+ch.getDapanC()+"',DanAnD=N'"+ch.getDapanD()+"',TraLoi=N'"+ch.getTraloi()+"' WHERE ch_id="+ch.getCh_id();
            
            st = conn.createStatement();
            st.executeUpdate(qry);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "lỗi sửa câu hỏi đề thi");
        }
    }
    public void PublicDeThi(String iddethi)
    {
        try{
            String qry = "UPDATE DeThi SET dt_public=1 WHERE dt_id="+iddethi;
            
            st = conn.createStatement();
            st.executeUpdate(qry);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "lỗi public đề thi");
        }
    }
    public void them (DeThiDTO dt)
    {
        try{
            //Int b = rs + 1;
            int idDeThi = createid();
            String qry = "Insert into DeThi values (";
            qry = qry+"'"+idDeThi+"'";
            qry = qry+","+"N'"+dt.getTieude()+"'";
            qry = qry+","+"N'"+dt.getThoiluong()+"'";
            qry = qry+","+"N'"+dt.getMonthi()+"'";
            qry= qry+","+"N'"+dt.getSocau()+"'";
            
            qry= qry+","+"'"+0+"'";
            qry= qry +","+"'"+dt.getNguoitao()+"'";
            qry= qry +","+"'"+0+"'";
            qry = qry+")";
            System.out.println(qry);
            st=conn.createStatement();
            st.executeUpdate(qry);
            for(int i=1;i<= Integer.parseInt(dt.getSocau());i++){
                try{
                    qry="INSERT INTO CauHoii (ch_id,dt_id,CauHoi,DapAnA,DanAnB,DanAnC,DanAnD,TraLoi) VALUES ('"+createidCauhoi()+"','"+idDeThi+"','','','','','','')";
                    System.out.println(qry);
                    st.executeUpdate(qry);
                }catch(Exception e){
                    System.out.println("Error in tao cau hoi null");
                }
                
            }
            
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "lỗi thêm đề thi");
        }
    }
    public void sua(DeThiDTO dt)
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
    public ArrayList<DTO.CauHoi> GetAllCauHoiFromDethi(String dethiid)
    {
        ArrayList<DTO.CauHoi> Listch = new ArrayList<>();
        int id = 0;
        try {
            System.out.println(dethiid);
            String qry = "SELECT * FROM CauHoii where dt_id="+dethiid;
            System.out.println("sql ->>>"+qry);
            st=conn.createStatement();
            rs=st.executeQuery(qry);
            
            while(rs.next())
            {
                DTO.CauHoi ch = new CauHoi();
                ch.setCh_id(rs.getInt("ch_id"));
                ch.setDt_id(rs.getInt("dt_id"));
                ch.setCauhoi(rs.getString("CauHoi"));
                ch.setDapanA(rs.getString("DapAnA"));
                ch.setDapanB(rs.getString("DanAnB"));
                ch.setDapanC(rs.getString("DanAnC"));
                ch.setDapanD(rs.getString("DanAnD"));
                ch.setTraloi(rs.getString("TraLoi"));
                Listch.add(ch);
            }
        }
        catch (SQLException ex){
            //JOptionPane.showMessageDialog(null, "thatbai");
        }
        System.out.println(Listch.size());
        return Listch;
    }
}
