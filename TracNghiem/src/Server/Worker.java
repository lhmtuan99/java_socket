/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author Admin
 */
public class Worker implements Runnable{
    private Socket socket;
    public Worker (Socket s) {
        this.socket = s;
    }
    public void run(){
        System.out.println("Client " + socket.toString() +" accepted");
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String s ="";
            String[] input=null;
            while(true)
            {
                s = in.readLine();
                System.out.println("Server received: " + s + " from " + socket.toString());
                out.write(s);
                out.flush();  
                
                if(s.equals("bye"))
                    break;
            }
            System.out.println("Closed socket for client " + socket.toString());
            in.close();
            out.close();
            socket.close();
        } catch (IOException e){
            System.out.println(e);
        }
    }
}
