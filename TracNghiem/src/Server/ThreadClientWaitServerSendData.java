/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;


import DTO.CauHoi;
import DTO.DeThiDTO;
import GUI.DangKyAccount;
import static GUI.DangKyAccount.CloseDangkiFrame;
import GUI.DeThiJPanel;
import static GUI.DeThiJPanel.jTable1;
import GUI.Login;
import static GUI.Login.CLOSE;
import static GUI.Login.CloseLoginFrame;
import GUI.MainJFrame;
import static GUI.MainJFrame.AlertMessageFromServer;
import static GUI.MainJFrame.getIntanceMainJFrame;
import GUI.TaoCauHoiJPanel;
import static GUI.TaoCauHoiJPanel.jComboBox1;
import static GUI.ThongTinNguoiDungJPanel.block1;
import static GUI.ThongTinNguoiDungJPanel.block2;
import static GUI.ThongTinNguoiDungJPanel.block3;
import static GUI.ThongTinNguoiDungJPanel.jLabel5;
import static GUI.ThongTinNguoiDungJPanel.jTextField1;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tiennguyen
 */
public class ThreadClientWaitServerSendData extends Thread{
    protected Socket socket;
    public String [] arrDethi = new String[1000];
    public static ArrayList<DTO.CauHoi> listCauHoi = null;
    public static DefaultComboBoxModel modelCombobox = null;
    public ThreadClientWaitServerSendData(Socket clientSocket) {
        this.socket = clientSocket;
    }
    InputStream inp = null;
    BufferedReader brinp = null;
    DataOutputStream out = null;
    
    public String key ;
        
    public void run() {
       BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(ThreadClientWaitServerSendData.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        System.out.println(key);
        
        while (true) {
           try {
               String res = in.readLine();
               //System.out.println("Client received: "+ TheadClient.GiaimaData(res, zz));
               System.out.println("Client received: "+ res);
               System.out.println("Client received: "+ decrypt(res,key+socket.getLocalPort()));
               String result = decrypt(res,key+socket.getLocalPort());
               XULY(result);
               Thread.sleep(1500);
           } catch (IOException ex) {
               Logger.getLogger(ThreadClientWaitServerSendData.class.getName()).log(Level.SEVERE, null, ex);
           } catch (InterruptedException ex) {
               Logger.getLogger(ThreadClientWaitServerSendData.class.getName()).log(Level.SEVERE, null, ex);
           }
            
        }
        
    }
    public void XULY(String str1){
        Login lg = new Login();
        String []str = str1.split(":");
        if(str[0].equals("1")){
            MainJFrame.AlertMessageFromServer("Vui lòng kiểm tra email để xác thực OTP...");
            DangKyAccount.OffEmail();
            DangKyAccount.CountDown();
        }else if(str[0].equals("0")){
            MainJFrame.AlertMessageFromServer("Email đã tồn tại !!!");
        }else if(str[0].equals("DKTC")){
            MainJFrame.AlertMessageFromServer("Đăng kí thành công...");
            CloseDangkiFrame();
            Login.getIntanceLogin().setVisible(true);
            
        }else if(str[0].equals("SAIOTP")){
            MainJFrame.AlertMessageFromServer("Sai OTP !!!");
        }else if(str[0].equals("DNOK")){
            CloseLoginFrame();
            getIntanceMainJFrame().setVisible(true);
            //Client.SendToServer("LOAD:DETHI:");
            jTextField1.setText(str[1]);
            jLabel5.setText(str[2]);
            block1.setText( str[3].equals("1") ? "False":"True");
            block2.setText(str[4].equals("1") ? "False":"True");
            block3.setText(str[5].equals("1") ? "False":"True");
        }else if(str[0].equals("DNSAI")){
            MainJFrame.AlertMessageFromServer("Sai tài khoản hoặc mật khẩu !!!");
        }else if(str[0].equals("LOADDETHI")){
            Vector header = new Vector ();
            header.add("ID");
            header.add("TIÊU ĐỀ");
            header.add("MÔN THI");
            header.add("SỐ CÂU");
            header.add("THỜI LƯỢNG");
            header.add("TỔNG SỐ NGƯỜI THI");
            DefaultTableModel model = new DefaultTableModel(header,0);
            int run =0;
            for(int i=1;i<str.length;i=i+6)
            {
                if(str[i+5].equals("0"))
                arrDethi[run++] = str[i]+"-"+str[i+1];
                Vector row  = new Vector();
                row.add(str[i]);
                row.add(str[i+1]);
                row.add(str[i+2]);
                row.add(str[i+3]);
                row.add(str[i+4]);
                row.add(str[i+5]);
                model.addRow(row);
                //dsdt.add(dt);
            }
            System.out.println(run);
                        
            jTable1.setModel(model); 
        }else if(str[0].equals("TAOTHANHCONG")){
            MainJFrame.AlertMessageFromServer("Tạo đề thành công...");
            Client.SendToServer("LOAD:DETHI:");
        }else if(str[0].equals("SUATHANHCONG")){
            MainJFrame.AlertMessageFromServer("Sửa đề thành công...");
            Client.SendToServer("LOAD:DETHI:");
        }else if(str[0].equals("XOATHANHCONG")){
            MainJFrame.AlertMessageFromServer("Xóa đề thành công...");
            Client.SendToServer("LOAD:DETHI:");
        }else if(str[0].equals("INF")){
            jTextField1.setText(str[1]);
            jLabel5.setText(str[2]);
            block1.setText( str[3].equals("1") ? "False":"True");
            block2.setText(str[4].equals("1") ? "False":"True");
            block3.setText(str[5].equals("1") ? "False":"True");
        }else if(str[0].equals("LOADDE")){
            listCauHoi = new ArrayList<>();
            
            for(int i=1;i<str.length;i=i+7){
            DTO.CauHoi ch = new CauHoi();
                ch.setCh_id(Integer.parseInt(str[i]));
                ch.setCauhoi(str[i+1]);
                ch.setDapanA(str[i+2]);
                ch.setDapanB(str[i+3]);
                ch.setDapanC(str[i+4]);
                ch.setDapanD(str[i+5]);
                ch.setTraloi(str[i+6]);
                listCauHoi.add(ch);
            }
            if(listCauHoi.get(0).getDapanA().length()>0){
                TaoCauHoiJPanel.jTextField1.setText(listCauHoi.get(0).getDapanA());
            }else TaoCauHoiJPanel.jTextField1.setText(" ") ;
            if(listCauHoi.get(0).getDapanB().length()>0){
                TaoCauHoiJPanel.jTextField2.setText(listCauHoi.get(0).getDapanB());
            }else TaoCauHoiJPanel.jTextField2.setText(" ") ;
            if(listCauHoi.get(0).getDapanC().length()>0){
                TaoCauHoiJPanel.jTextField3.setText(listCauHoi.get(0).getDapanC());
            }else TaoCauHoiJPanel.jTextField3.setText(" ") ;
            if(listCauHoi.get(0).getDapanD().length()>0){
                TaoCauHoiJPanel.jTextField4.setText(listCauHoi.get(0).getDapanD());
            }else TaoCauHoiJPanel.jTextField4.setText(" ") ;
            if(listCauHoi.get(0).getCauhoi().length()>0){
                TaoCauHoiJPanel.jTextField5.setText(listCauHoi.get(0).getCauhoi());
            }else TaoCauHoiJPanel.jTextField5.setText(" ") ;
            
            
            if(listCauHoi.get(0).getTraloi().length()>0){
                if(listCauHoi.get(0).getDapanA().equals(listCauHoi.get(0).getTraloi()))
                    TaoCauHoiJPanel.jRadioButton1.setSelected(true);
                if(listCauHoi.get(0).getDapanB().equals(listCauHoi.get(0).getTraloi()))
                    TaoCauHoiJPanel.jRadioButton2.setSelected(true);
                if(listCauHoi.get(0).getDapanC().equals(listCauHoi.get(0).getTraloi()))
                    TaoCauHoiJPanel.jRadioButton3.setSelected(true);
                if(listCauHoi.get(0).getDapanD().equals(listCauHoi.get(0).getTraloi()))
                    TaoCauHoiJPanel.jRadioButton4.setSelected(true);
            }
            
            TaoCauHoiJPanel.cau.setText("1");
            TaoCauHoiJPanel.tongcau.setText(listCauHoi.size()+"");
            TaoCauHoiJPanel.idcauhoi.setText(listCauHoi.get(0).getCh_id()+"");
        }else if(str[0].equals("LOADDECOMBOBOX")){
            modelCombobox = new DefaultComboBoxModel();
            int run =0;
            for(int i=1;i<str.length;i=i+6)
            {
                if(str[i+5].equals("0"))
                arrDethi[run++] = str[i]+"-"+str[i+1];
            }
            for(int i=0;i<run;i++){
                modelCombobox.addElement(arrDethi[i]);
            }
            jComboBox1.setModel(modelCombobox);
            MainJFrame.AlertMessageFromServer("Đã tải xong...");
        }else if(str[0].equals("UPDATECAUHOITHANHCONG")){
            MainJFrame.AlertMessageFromServer("Cập nhật xong...");
        }
        //MainJFrame.AlertMessageFromServer(decrypt(str,key+socket.getLocalPort()));
    }
    
     public String encrypt(String strToEncrypt, String myKey) {
      try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = myKey.getBytes("UTF-8");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return java.util.Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
      } catch (Exception e) {
            System.out.println(e.toString());
      }
      return null;
    }
     
    public String decrypt(String strToDecrypt, String myKey) {
      try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = myKey.getBytes("UTF-8");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(java.util.Base64.getDecoder().decode(strToDecrypt)));
      } catch (Exception e) {
            System.out.println(e.toString());
      }
      return null;
}
//     public String decrypt(String encrypted) {
//	try {
//		IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
//		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
//
//		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
//		byte[] original = cipher.doFinal(com.sun.org.apache.xerces.internal.impl.dv.util.Base64.decode(encrypted));
//
//		return new String(original);
//	} catch (Exception ex) {
//		ex.printStackTrace();
//	}
//
//	return null;
//    }
//     public String encrypt(String value) {
//	try {
//		IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
//		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
//
//		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
//
//		byte[] encrypted = cipher.doFinal(value.getBytes());
//		return com.sun.org.apache.xerces.internal.impl.dv.util.Base64.encode(encrypted);
//	} catch (Exception ex) {
//		ex.printStackTrace();
//	}
//	return null;
//    }
//    public static PrivateKey getPrivateKey(String privateString) {
//
//       PrivateKey privateKey = null;
//        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateString.getBytes()));
//        KeyFactory keyFactory = null;
//        try {
//            keyFactory = KeyFactory.getInstance("RSA");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        try {
//            privateKey = keyFactory.generatePrivate(keySpec);
//        } catch (InvalidKeySpecException e) {
//            e.printStackTrace();
//        }
//        return privateKey;
//    }
//    public static String GiaimaData(String data,PrivateKey prKeyy) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
//        Cipher c = Cipher.getInstance("RSA");
//        c.init(Cipher.DECRYPT_MODE, prKeyy);
//        byte decryptOut[] = c.doFinal(com.sun.org.apache.xerces.internal.impl.dv.util.Base64.decode(data));
//        return new String(decryptOut);
//    }
//    public static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
//        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//        cipher.init(Cipher.DECRYPT_MODE, privateKey);
//        return new String(cipher.doFinal(data));
//    }
}

