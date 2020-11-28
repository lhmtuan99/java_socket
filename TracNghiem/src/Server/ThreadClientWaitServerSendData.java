/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiennguyen
 */
public class ThreadClientWaitServerSendData extends Thread{
    protected Socket socket;
    public ThreadClientWaitServerSendData(Socket clientSocket) {
        this.socket = clientSocket;
    }
    InputStream inp = null;
    BufferedReader brinp = null;
    DataOutputStream out = null;
    public void run() {
       BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(ThreadClientWaitServerSendData.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {
           try {
               String res = in.readLine();
               System.out.println("Client received: "+ res);
               Thread.sleep(1000);
           } catch (IOException ex) {
               Logger.getLogger(ThreadClientWaitServerSendData.class.getName()).log(Level.SEVERE, null, ex);
           } catch (InterruptedException ex) {
               Logger.getLogger(ThreadClientWaitServerSendData.class.getName()).log(Level.SEVERE, null, ex);
           }
            
        }
        
    }
}

