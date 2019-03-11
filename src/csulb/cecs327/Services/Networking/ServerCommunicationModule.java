package csulb.cecs327.Services.Networking;
import java.io.*;
import java.net.*;

public class ServerCommunicationModule extends Thread{
    static final int FRAGMENT_SIZE = 8192;                      // Packet size
    byte[] packetSize = new byte[FRAGMENT_SIZE];

    DatagramSocket socket = null;
    int portNumber;
    Dispatcher dispatcher;

    ServerCommunicationModule(int portNum, Dispatcher dispatcher){
        this.portNumber = portNum;
        this.dispatcher = dispatcher;
    }

    public void connect(){                        // portNumber must be > 1023
        try{
            socket = new DatagramSocket(this.portNumber);                             // Initialize socket
            System.out.println("ServerSocket opened on port: "+ this.portNumber);
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public void listen(){                                       // Opens client socket and listens for requests
        System.out.println("Server listening.");
        try{
            while(true) {
                DatagramPacket requestPacket = new DatagramPacket(packetSize, packetSize.length);               // Initialize request packet
                socket.receive(requestPacket);                                                                // Receive request packet
                System.out.println("Client packet received: " + requestPacket);

                System.out.println("Creating new thread for handling this client packet.");             // Create new thread to handle this request packet and return a response packet
                Thread t = new ClientPacketRequestHandler(socket, requestPacket, dispatcher);
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
