/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author Admin
 */
public class SendMailutil {
    public static String generateOTP()  
    {
        int randomPin   =(int) (Math.random()*900000)+100000; 
        String otp  = String.valueOf(randomPin); 
        return otp; 
    } 
//    public static void main(String args[])
//    { 
//        String otpSting  =generateOTP();
//        System.out.println("OTP : "+otpSting); 
//    } 
    
    public static void sendMail(String recepient) throws Exception
    {
        System.out.println("Preparing to send mail");
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        
        String myAccountEmail= "phamtiep270299@gmail.com";
        String password="nguyentiep";
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
            
        });
        
        Message message = prepareMessage(session, myAccountEmail, recepient);
        
        Transport.send(message);
        System.out.println("Successfully");
        
    }
    
    private static Message prepareMessage (Session session, String myAccountEmail, String recepient)
    {
        try {
            String otpSting  =generateOTP();
            Message message = new MimeMessage(session) {};
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("OTP");
            message.setText("Mã OTP của bạn là: "+otpSting);
            return message;
        } 
        catch(Exception ex)
        {
            System.out.println("fail");
        }
        return null;
    }
    public static void main(String []args)
    {
        try {
            sendMail("koconpro822@gmail.com");
        } catch (Exception ex) {
            Logger.getLogger(SendMailutil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
