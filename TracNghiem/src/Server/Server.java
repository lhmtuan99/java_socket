/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Admin
 */
public class Server {
    public static int port = 1234;
    public static int numThread = 4;
    public static ServerSocket server = null;
//    public static ArrayList <Worker> Workers = new ArrayList <>(); 
    public static void main( String [] args ) throws IOException{
        ExecutorService executor = Executors.newFixedThreadPool(numThread);
        try{
            server = new ServerSocket(port);
            System.out.println("Server binding at port "+ port);
            System.out.println("Waiting for client...");
            while (true)
            {
                Socket socket = server.accept();
                executor.execute(new Worker(socket));
            }
        } catch (IOException e){
            System.out.println(e);
        } finally {
            if(server!=null)
                server.close();
        }
    }    
}
