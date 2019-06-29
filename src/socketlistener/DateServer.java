/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketlistener;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 *
 * @author Abraham SA
 */
public class DateServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try(ServerSocket listener = new ServerSocket(59090)){
            System.out.println("The date server is running");
            while(true){
                try(Socket socket = listener.accept()){
                    PrintWriter out = new PrintWriter(socket.getOutputStream()
                            , true);
                    out.println(new Date().toString());
                }
            }
        }
        catch(Exception e){
            
        }
    }
    
}
