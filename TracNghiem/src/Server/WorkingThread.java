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
public class WorkingThread extends Thread {
    protected Socket socket;
    public WorkingThread(Socket clientSocket) {
        this.socket = clientSocket;
    }
    InputStream inp = null;
    BufferedReader brinp = null;
    DataOutputStream out = null;
    public void run() {
        String line;
        try {
            inp = socket.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
        } catch (IOException e) {
            return;
        }
        while (true) {
            try {
                line = brinp.readLine();
                if ((line == null) || line.equalsIgnoreCase("QUIT")) {
                    socket.close();
                    return;
                } else {
                    // excute option of client here
                    System.out.println(line);
                    System.out.println(socket);
                    
                }
            } catch (IOException ex) {
                Logger.getLogger(WorkingThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}

