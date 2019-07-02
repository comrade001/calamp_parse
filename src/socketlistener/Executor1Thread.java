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
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Abraham SA
 */
public class Executor1Thread {
    public static void main(String[] args) throws IOException{
        try{
            System.out.println("The capitalization server is running...");
            int port = 9094;
            ExecutorService pool = Executors.newFixedThreadPool(10);
            pool.execute(new UdpUnicastServer(port));
        }
        catch(Exception e){

        }   
    }
}

class UdpUnicastServer implements Runnable {
    /**
    * The port where the client is listening.
    */
    private final int clientPort;
    private final Integer BYTE = 2;
    public UdpUnicastServer(int clientPort) {
        this.clientPort = clientPort;
    }
    @Override
    public void run() {
        try (DatagramSocket socket = new DatagramSocket(clientPort)) {
        byte[] buffer = new byte[1024];    
        DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
        int count = 0;
        while(true){
            socket.receive(peticion);
            StringBuilder sb = new StringBuilder();
            for (byte b : buffer) {
                sb.append(String.format("%02X", b));
            }

            String mensaje = sb.toString();
            int carrete = 0;
            Integer OptionsByte = Integer.parseInt(
                    mensaje.substring(0, carrete += BYTE), 16);
            
            int numOfBits = 8;
            boolean[] OptionsByteBoolean = new boolean[numOfBits];
            for(int i = 0; i< numOfBits; i++){
                OptionsByteBoolean[i] = (OptionsByte & 1 << i) != 0;
            }
            
            if(OptionsByteBoolean[0]){
                Integer MobileIdLength = Integer.parseInt(
                        mensaje.substring(carrete, carrete += BYTE), 16);
                String MobileId = mensaje.substring(carrete
                        , carrete += MobileIdLength * BYTE);
            }
            if(OptionsByteBoolean[1]){
                Integer MobileIdTypeLength = Integer.parseInt(
                        mensaje.substring(carrete, carrete += BYTE), 16);
                Integer MobileIdType = Integer.parseInt(
                        mensaje.substring(
                                carrete, carrete += MobileIdTypeLength * BYTE));
            }
            
            String ServiceType = mensaje.substring(carrete, carrete += BYTE);
            String MessageType = mensaje.substring(carrete, carrete += BYTE);
            Integer SequenceNumber = Integer.parseInt(
                    mensaje.substring(carrete, carrete += 2 * BYTE), 16); 
            
            java.util.Date UpdateTime = new java.util.Date(
                    Long.parseLong(mensaje.substring(
                                    carrete, carrete += 4 * BYTE), 16) * 1000);
            
            java.util.Date TimeOfFix = new java.util.Date(
                    Long.parseLong(mensaje.substring(
                                    carrete, carrete += 4 * BYTE), 16) * 1000);
            
            double Latitude = (int) Long.parseLong(mensaje.substring(
                                    carrete, carrete += 4 * BYTE), 16) / 1e7;
            
            double Longitude = (int) Long.parseLong(mensaje.substring(
                                    carrete, carrete += 4 * BYTE), 16) / 1e7;
            
            double Altitude = (int) Long.parseLong(mensaje.substring(
                                    carrete, carrete += 4 * BYTE), 16);
            
            double Speed = (int) Long.parseLong(mensaje.substring(
                    carrete, carrete += 4 * BYTE), 16) * 0.036;
            
            Integer Heading = (int) Integer.parseInt(mensaje.substring(
                    carrete, carrete += 2 * BYTE), 16);
            
            Integer Satellites = Integer.parseInt(
                    mensaje.substring(carrete, carrete += BYTE), 16);
            
            Integer FixStatus = Integer.parseInt(
                    mensaje.substring(carrete, carrete += BYTE), 16);
            
            boolean[] FixStatusBoolean = new boolean[numOfBits];
            for(int i = 0; i< numOfBits; i++){
                FixStatusBoolean[i] = (FixStatus & 1 << i) != 0;
            }
            
            Integer Carrier = Integer.parseInt(
                    mensaje.substring(carrete, carrete += 2 * BYTE), 16);
            
            short RSSI = (short) Integer.parseInt(mensaje.substring(
                                    carrete, carrete += 2 * BYTE), 16);
            
            Integer CommState = Integer.parseInt(
                    mensaje.substring(carrete, carrete += BYTE), 16);
            
            System.out.println("Count " + count + " : " + mensaje);
            
            InetAddress address = peticion.getAddress();
            String response = "83084562247869010102010006";
            int port = peticion.getPort();
            peticion = new DatagramPacket(response.getBytes()
                    , response.getBytes().length, address, port);
            socket.send(peticion);
        }
    } catch (SocketException e) {
        e.printStackTrace();
    } catch (UnknownHostException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
  }
}
