/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import static Server.Server.Clients;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;

/**
 *
 * @author tiennguyen
 */
public class ServerExcute implements Runnable{
    private Scanner sc = new Scanner(System.in);
    private Thread t;
    private String threadName;
 
    ServerExcute(String name) {
        threadName = name;
        System.out.println("Creating " + threadName);
        
    }
    public void Menu(){
        System.out.println("------------Menu--------------");
        System.out.println("0: How many people online:");
        System.out.println("1: How many people online:");
        System.out.println("2: How many examps on system:");
        System.out.println("3: How many people system have:");
        System.out.println("4: Block someone:");
    }
    public void ServerExcute() throws IOException{
        boolean flag = false;
        Server R1 = new Server("Thread-Server");
        R1.start();
    
        while(!flag){
            Menu();
            System.out.print("Your choice is:");
            int n;
            n= sc.nextInt();
            switch(n){
                case 0: {
                    R1.shutdown();
                    flag = true;
                    break;
                }
                case 1: {
                    System.out.println("you choice 1");
                    System.out.println("Nhap data want to send to client:");
                    String str= sc.nextLine();
                     str= sc.nextLine();
                    Server.Clients.get(0).line=str;
                    for(int i=0;i<=Clients.size()-1;i++){
                        Server.Clients.get(i).line=str;
                    }
                    
                    break;
                }
                case 2:{
                    System.out.println("you choice 2");
                    break;
                }
                case 3:{
                    System.out.println("you choice 3");
                    break;
                }
                case 4:{
                    System.out.println("you choice 4");
                    break;
                }
                default:{
                    System.out.println("Dont have your option !!!");
                    break;
                }
            }
        }
        System.out.println("quit");
    }

    @Override
    public void run() {
        try {
            ServerExcute();
        } catch (IOException ex) {
            Logger.getLogger(ServerExcute.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}
