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
public class TheadClient extends Thread{
    protected Socket socket;
    public String line = "";
    public int id = -1;
    private boolean check = false;
    public TheadClient(Socket clientSocket) {
        this.socket = clientSocket;
        this.id = clientSocket.getPort();
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
        //System.out.print("");
        while (true) {
            // dont destroy this line
           // System.out.print("");    
            if(!check){
                WorkingThread workingThread = new WorkingThread(socket);
                workingThread.start();
                check =true;
            }
            try {
                
                if(line.length()>0){
                    out.writeBytes(line + "\n");
                    out.flush();
                    line="";
                }
                Thread.sleep(2000);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } catch (InterruptedException ex) {
                Logger.getLogger(TheadClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
}
