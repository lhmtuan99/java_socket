/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.otpDTO;
import GUI.SendMailutil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class otpDAO {
    Connection conn = null;
    Statement st= null;
    ResultSet rs = null;
    ArrayList<otpDTO> dsotp = new ArrayList<>();
    public otpDAO()
    {
        MyConnection connectiondatabase = new MyConnection();
        conn=connectiondatabase.getConnecDB();
    }
    public int createid()
    {
        int id = 0;
        try {
            
            String qry = "SELECT max (id) as 'createid' FROM OTP";
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
    public static String generateOTP()  
    {
        int randomPin   =(int) (Math.random()*900000)+100000; 
        String otp  = String.valueOf(randomPin); 
        System.out.println("DAO: OTP duoc create: "+otp);
        return otp; 
    } 
    public int kiemtraOTP (String otp, String gmail)
    {
        //String a="";
        try {
            
            String qry = "SELECT * from OTP where otp='"+otp+"' and gmail='"+gmail+"'";
            st=conn.createStatement();
            rs=st.executeQuery(qry);
    
        if(rs.next())
            {
                return 1;
            }
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "DAO: that bai kiem tra checkOTP");
        }
        return 0;
    }
    
    public int kiemtra(otpDTO otp)
    {
        try {
            
            String qry = "SELECT * FROM OTP where gmail='"+otp.getGmail()+"'";
            st=conn.createStatement(); 
            rs=st.executeQuery(qry);
    
            if(rs.next())
                {
                    System.out.println("DAO: Gmail đã tồn tại"); 
                    return 0;
                }
            }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "DAO: that bai kiemtra Gmail");
        }
        return 1;
    }
    public void them(otpDTO otp)
    {
        try{
            //Int b = rs + 1;
            if(kiemtra(otp)==1){
                String a = generateOTP();
            String qry = "INSERT INTO OTP (id,gmail,otp) VALUES ('"+createid()+"','"+otp.getGmail()+"','"+a+"')";
            System.out.println(qry);
            st=conn.createStatement();
            st.executeUpdate(qry);
                try {
                    System.out.println("ok");
                    sendMail(otp.getGmail(),a);
                    System.out.println(a);
                } catch (Exception ex) {
                    System.out.println("DAO: loi gui mail");
                }
            }    
        }
        catch(SQLException ex){
            System.out.println("DAO: lỗi thêm otp");
        }
    }
    public void xoa (String gmail)
    {
        try {
            String qry = "delete from OTP where gmail='"+gmail+"'";
            st = conn.createStatement();
            st.executeUpdate(qry);
            System.out.println("DAO: đã xoá OTP");
            System.out.println(gmail);
            System.out.println(gmail.length());
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "lỗi xoá OTP");
        }
    }
    public void sendMail(String recepient,String OTP) throws Exception
    {
        System.out.println("Preparing to send mail");
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        
        String myAccountEmail= "vanvananhanh1122@gmail.com";
        String password="1234Five";
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
            
        });
        
        Message message = prepareMessage(session, myAccountEmail, recepient,OTP);
        
        Transport.send(message);
        System.out.println("Successfully");
        
    }
    
    private Message prepareMessage (Session session, String myAccountEmail, String recepient,String OTP)
    {
        try {
//            String otpSting  =generateOTP();
            Message message = new MimeMessage(session) {};
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("OTP");
            message.setText("Mã OTP của bạn là: "+OTP);
            return message;
        } 
        catch(Exception ex)
        {
            System.out.println("fail");
        }
        return null;
    }
//    public 
}
