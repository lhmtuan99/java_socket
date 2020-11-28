/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;

/**
 *
 * @author tiennguyen
 */
public class MainServer {
    public static void main(String[] argv) throws IOException{
        
        ServerExcute R2 = new ServerExcute("Thread-ServerExcute");
        R2.start();
        
        
    }
}
