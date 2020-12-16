/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 *
 * @author tiennguyen
 */
public class TheadClient extends Thread{
    public int chit =0;
    
    public String key ;
    public String initVector ;
    public int PORT;
    protected Socket socket;
    public String line = "";
    public int id = -1;
    private boolean check = false;

    public TheadClient(Socket clientSocket) {
        this.socket = clientSocket;
        this.id = clientSocket.getPort();
        PORT = clientSocket.getPort();
    }
    public void run() {
        InputStream inp = null;
        BufferedReader brinp = null;
        DataOutputStream out = null;
        try {
            inp = socket.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }
        
        
        try {
            out.writeBytes(key + "\n");
            out.flush();
            out.writeBytes(initVector + "\n");
            out.flush();
           
        } catch (IOException ex) {
            Logger.getLogger(TheadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
        //System.out.print("");
        while (true) {
            // dont destroy this line
           // System.out.print("");    
            if(!check){
//                System.out.println(privateKey);
//                System.out.println(publicKey);
                WorkingThread workingThread = new WorkingThread(socket);
                workingThread.key = key;
                workingThread.start();
                check =true;
            }
            try {
                
                if(line.length()>0){
                    line= "ALERT:"+line+":";
                    line = encrypt(line,key+socket.getPort());
                    System.out.println(line);
                    System.out.println(socket.getPort());
                    out.writeBytes(line + "\n");
                    out.flush();
                    line="";
                }
                Thread.sleep(2000);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } catch (InterruptedException ex) {
                if(chit==0){
                System.out.println("");
                Server.Clients.remove(0);
                chit=1;
            }
                //Logger.getLogger(TheadClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
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
    
    
    
//    public String encrypt(String value) {
//	try {
//		IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
//		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
//
//		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
//
//		byte[] encrypted = cipher.doFinal(value.getBytes());
//		return Base64.encode(encrypted);
//	} catch (Exception ex) {
//		ex.printStackTrace();
//	}
//	return null;
//    }
//    public String decrypt(String encrypted) {
//	try {
//		IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
//		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
//
//		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
//		byte[] original = cipher.doFinal(Base64.decode(encrypted));
//
//		return new String(original);
//	} catch (Exception ex) {
//		ex.printStackTrace();
//	}
//
//	return null;
//    }
    
    
    
    
    
    
    
    
    
    
    
    
//    public static String MahoaData(String str,PublicKey z) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
//        
//        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//	cipher.init(Cipher.ENCRYPT_MODE, z);
//        byte encryptOut[] = cipher.doFinal(str.getBytes());
//        String strEncrypt = new String(encryptOut);
//        return strEncrypt;
//    }
//    public static byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
//	Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//	cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
//	return cipher.doFinal(data.getBytes());
//}
//    
//    public static PublicKey getPublicKey(String prublicString) {
//
//    PublicKey publicKey = null;
//        try{
//            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(java.util.Base64.getDecoder().decode(prublicString.getBytes()));
//            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//            publicKey = keyFactory.generatePublic(keySpec);
//            return publicKey;
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (InvalidKeySpecException e) {
//            e.printStackTrace();
//        }
//        return publicKey;
//}
}
