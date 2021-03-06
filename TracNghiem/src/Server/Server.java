/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.List;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Server implements Runnable{
    private Thread t;
    private String threadName;
    Server(String name) {
        threadName = name;
        System.out.println("Creating " + threadName);
    }
    
    private volatile boolean shutdown;
    
    public static int port = 1234;
    public static int numThread = 4;
    public static ServerSocket server = null;
    
    public static List<TheadClient> Clients = new ArrayList<>();
    
    public void Start() throws IOException{
        //ExecutorService executor = Executors.newFixedThreadPool(numThread);
        try{
            server = new ServerSocket(port);
            System.out.println("Server binding at port "+ port);
            System.out.println("Waiting for client...");
            while (!shutdown)
            {
                if(shutdown) server.close();
                Socket socket = server.accept();
                Clients.add(new TheadClient(socket));
                Clients.get(Clients.size()-1).start();
                
                //executor.execute(new Worker(socket));
            }
        } catch (IOException e){
            System.out.println(e);
        } finally {
            if(server!=null)
                server.close();
        }
    }    
 
    @Override
    public void run() {
        try {
            Start();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
    public void shutdown() {
        shutdown = true;
    }
}
