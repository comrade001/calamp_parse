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
            
            int count = 0;
            while(true){
                byte[] buffer = new byte[1024];    
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socket.receive(peticion);
                StringBuilder sb = new StringBuilder();
                for (byte b : buffer) {
                    sb.append(String.format("%02X", b));
//                    sb.append(b);

                }

                String mensaje = sb.toString();
                System.out.println("Input " + count + " : " + mensaje);
                int carrete = 0;
                OptionsHeader.getHeader(mensaje, carrete);
                Integer OptionsByte = Integer.parseInt(
                        mensaje.substring(0, carrete += BYTE), 16);

                int numOfBits = 8;
                boolean[] OptionsByteBoolean = new boolean[numOfBits];
                for(int i = 0; i< numOfBits; i++){
                    OptionsByteBoolean[i] = (OptionsByte & 1 << i) != 0;
                }

                Integer MobileIdLength = null;
                String MobileId = null;
                if(OptionsByteBoolean[0]){
                    MobileIdLength = Integer.parseInt(
                            mensaje.substring(carrete, carrete += BYTE), 16);
                    MobileId = mensaje.substring(carrete
                            , carrete += MobileIdLength * BYTE);
                }
                Integer MobileIdTypeLength = null;
                String MobileIdType = null;
                if(OptionsByteBoolean[1]){
                    MobileIdTypeLength = Integer.parseInt(
                            mensaje.substring(carrete, carrete += BYTE), 16);
    //                Integer MobileIdType = Integer.parseInt(
    //                        mensaje.substring(
    //                                carrete, carrete += MobileIdTypeLength * BYTE));
                    MobileIdType = mensaje.substring(
                                    carrete, carrete += MobileIdTypeLength * BYTE);
                }
                String ServiceType = mensaje.substring(carrete, carrete += BYTE);
                Integer MessageType = Integer.parseInt( 
                        mensaje.substring(carrete, carrete += BYTE), 16);
                Integer SequenceNumber = Integer.parseInt(
                        mensaje.substring(carrete, carrete += 2 * BYTE), 16); 
                
                if(MessageType == 2){
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

                    boolean[] CommStateBoolean = new boolean[numOfBits];
                    for(int i = 0; i< numOfBits; i++){
                        CommStateBoolean[i] = (CommState & 1 << i) != 0;
                    }

                    double HDOP = (int) Long.parseLong(mensaje.substring(
                            carrete, carrete += BYTE), 16) / 1e1;

                    Integer Inputs = Integer.parseInt(
                            mensaje.substring(carrete, carrete += BYTE), 16);
                    boolean[] InputsBoolean = new boolean[numOfBits];
                    for(int i = 0; i< numOfBits; i++){
                        InputsBoolean[i] = (Inputs & 1 << i) != 0;
                    }

                    Integer UnitStatus = Integer.parseInt(
                            mensaje.substring(carrete, carrete += BYTE), 16);
                    boolean[] UnitStatusBoolean = new boolean[numOfBits];
                    for(int i = 0; i< numOfBits; i++){
                        UnitStatusBoolean[i] = (UnitStatus & 1 << i) != 0;
                    }

                    Integer EventIndex = Integer.parseInt(
                            mensaje.substring(carrete, carrete += BYTE), 16);

                    Integer EventCode = Integer.parseInt(
                            mensaje.substring(carrete, carrete += BYTE), 16);
                    
                    Integer Accums = Integer.parseInt(
                            mensaje.substring(carrete, carrete += BYTE), 16);
                    boolean[] AcumsBoolean = new boolean[numOfBits];
                    for(int i = 0; i< numOfBits; i++){
                        AcumsBoolean[i] = (Accums & 1 << i) != 0;
                    }
                    
//                    Integer AcumsFormatType = 
//                            Integer.parseInt(AcumsBoolean.substring(0, 2), 2);
//                    
//                    Integer AcumsCount = Integer.parseInt(
//                            AcumsBoolean.substring(2), 2);
                    
                    String Spare = mensaje.substring(carrete, carrete += BYTE);
                    
                    
                }

                InetAddress address = peticion.getAddress();
                
                String response = String.format("%02X", OptionsByte) 
                        + String.format("%02X", MobileIdLength)
                        + MobileId 
                        + String.format("%02X", MobileIdTypeLength)
                        + MobileIdType 
                        + "02" //ServiceType
                        + "01" //MessageType
                        + String.format("%04X", SequenceNumber)
                        + String.format("%04X", MessageType) 
                        + "00" + "00" + "000000";
                System.out.println("Output " + count + " : " + mensaje);
                int port = peticion.getPort();
                byte[] b = hexStringToByteArray(response);
                peticion = new DatagramPacket(b
                        , b.length, address, port);
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
    
    public static byte[] hexStringToByteArray(String s) {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) {
          int index = i * 2;
          int v = Integer.parseInt(s.substring(index, index + 2), 16);
          b[i] = (byte) v;
        }
        return b;
    }
}
