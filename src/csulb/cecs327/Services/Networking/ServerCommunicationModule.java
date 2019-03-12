package csulb.cecs327.Services.Networking;
import java.io.*;
import java.net.*;

/**
 * This class works as a server communication Module which takes in the request and send back the response
 * 2 functions:
 * 1. connecting to the socket
 * 2. Listening to the defined port
 */
public class ServerCommunicationModule extends Thread{
    // Initializing packet size
    static final int FRAGMENT_SIZE = 8192;
    byte[] packetSize = new byte[FRAGMENT_SIZE];
    // Initializing socket, port number and the dispatcher
    DatagramSocket socket = null;
    int portNumber;
    Dispatcher dispatcher;

    /**
     * Server constructor will takes in the port number and the dispatcher
     * @param portNum
     * @param dispatcher
     */
    ServerCommunicationModule(int portNum, Dispatcher dispatcher){
        this.portNumber = portNum;
        this.dispatcher = dispatcher;
    }

    /**
     * Opens connection so long as UDP port is greater than 1023
     */
    public void connect(){                       
        try{
            socket = new DatagramSocket(this.portNumber);
            System.out.println("ServerSocket opened on port: "+ this.portNumber);
        }catch (IOException e){
            System.out.println(e);
        }
    }

    /**
     * Opening client sokcets and listen to the requests
     */    
    public void listen(){                                      
        System.out.println("Server listening.");
        try{
            while(true) {
                // Initialize request packet
                DatagramPacket requestPacket = new DatagramPacket(packetSize, packetSize.length);
                // Receive request packet
                socket.receive(requestPacket);
                System.out.println("Client packet received: " + requestPacket);
                // Create new thread to handle this request packet and return a response packet
                System.out.println("Creating new thread for handling this client packet.");
                // This is where the handler comes in and handle the request packet
                Thread t = new PacketRequestHandler(socket, requestPacket, dispatcher);
                t.start();
            }
        }catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void run(){
        connect();
        listen();
    }
}
