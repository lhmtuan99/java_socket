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
import java.net.UnknownHostException;
import java.util.Scanner;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Admin
 */
public class Client {
	public static void main(String args[]) throws UnknownHostException, IOException 
	{ 
            Socket socket = null;
            int check;
            boolean isExistThreadWaitServer = false;
            try {
                socket = new Socket("127.0.0.1",1234);
                System.out.println("Client connected");
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                Scanner sc = new Scanner(System.in);
                String input;
                while(true){
                    if(!isExistThreadWaitServer){
                        ThreadClientWaitServerSendData t1 = new ThreadClientWaitServerSendData(socket);
                        t1.start();
                        isExistThreadWaitServer=true;
                    }
                    System.out.print("input : ");
                    input = sc.nextLine();
                    System.out.println("send "+input+" to server");
                    out.write(input + '\n');
                    out.flush();
                    if(input.equals("bye"))
                        break;
                    
//                    String res = in.readLine();
//                   
//                    System.out.println("Client received: "+ res);
                }
                
            } catch (IOException e){
                System.out.println(e);
            } finally{
                if(socket!=null){
                    socket.close();
                    System.out.println("Client oscket closed");
                }
            }
        }     
}
