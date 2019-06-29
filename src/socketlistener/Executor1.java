/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketlistener;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abraham SA
 */
public class Executor1 {
    
    public static void main(String[] args) throws IOException{
        final int PUERTO = 9094;
        byte[] buffer = new byte[1024];
        
        try{
            System.out.println("Iniciando el servidor UDP...");
            DatagramSocket socket = new DatagramSocket(PUERTO);
            
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
            socket.receive(peticion);
            
            StringBuilder sb = new StringBuilder();
            for (byte b : buffer) {
                sb.append(String.format("%02X", b));
            }
            
            String mensaje = sb.toString();
            System.out.println(mensaje);
            
            int puertoCliente = peticion.getPort();
            InetAddress direccion = peticion.getAddress();
            mensaje = "OK";
            buffer = mensaje.getBytes();
            
            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);
            socket.send(respuesta);
        }
        catch(Exception e){
            
        }
    }

}
