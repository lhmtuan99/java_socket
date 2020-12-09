/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import GUI.MainJFrame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyCode;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Admin
 */
public class Client {
        public static String input= "";
        
	public static void main(String args[]) throws UnknownHostException, IOException 
	{
            String key;
            Socket socket = null;
            int check;
            boolean isExistThreadWaitServer = false;
            try {
                socket = new Socket("127.0.0.1",1234);
                System.out.println("Client connected");
                System.out.println(socket.getLocalPort());
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                //
                MainJFrame.Run();
                //MainJFrame.AlertMessageFromServer("123");
                //
                Scanner sc = new Scanner(System.in);
                key= in.readLine();
                while(true){
                    
                    if(!isExistThreadWaitServer){
                        ThreadClientWaitServerSendData t1 = new ThreadClientWaitServerSendData(socket);
                        t1.key=key;
                        t1.start();
                        isExistThreadWaitServer=true;
                    }
                   // System.out.print("input : ");
                    //input = sc.nextLine();
                    Thread.sleep(1000);
                    if(input.length()>0){
                        if(input.equals("bye"))
                            break;
                        System.out.println("send "+input+" to server");
                        input =  encrypt(input,key+socket.getLocalPort());
                        System.out.println(input);
                        out.write(input + '\n');
                        out.flush();
                        input="";
                    }
                    
                    
//                    String res = in.readLine();
//                   
//                    System.out.println("Client received: "+ res);
                }
                
            } catch (IOException e){
                System.out.println(e);
            } catch (InterruptedException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                if(socket!=null){
                    socket.close();
                    System.out.println("Client oscket closed");
                }
            }
        }
        public static void SendToServer(String txt){
            input=txt;
        }
        public static String encrypt(String strToEncrypt, String myKey) {
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

}
