package csulb.cecs327.Server.RPC;

import java.io.*;
import java.net.*;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * This class is for the client communication module, which has 2 functions
 * 1. Connecting to server
 * 2. Sending requests to server
 */
public class ClientCommunicationModule {
    // Initializing packets size
    static final int FRAGMENT_SIZE = 15000;
    byte[] packetSize;

    // Initializing the socket and IP Address and port
    private DatagramSocket socket = null;
    private InetAddress IPAddress = null;
    private int portNumber;

    /**
     * This class is to connect the client to server using a port number
     *
     * @param port = the port number that as used for communication between server and client
     */
    public void connect(int port) {
        try {
            // Get localhost IP address
            this.IPAddress = InetAddress.getByName("localhost");
            // Initialize Socket
            this.socket = new DatagramSocket();
            // Setting our defined portNumber as the port number client uses to conenct to server
            this.portNumber = port;

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This class is for client to send request to server
     *
     * @param request = the data that client is requesting from server
     * @return - the response from server in which client requested for
     */
    public String sendRequest(String request) {
        String response = "";
        try {
            packetSize = new byte[FRAGMENT_SIZE];
            // Initialize payload
            byte[] requestPayload = new byte[FRAGMENT_SIZE];
            // Fill in payload
            requestPayload = request.getBytes();
            // Initialize request packet
            DatagramPacket requestPacket = new DatagramPacket(requestPayload, requestPayload.length, this.IPAddress, this.portNumber);
            System.out.println("Client sending request packet to server.");
            // Sending the request-packet
            socket.send(requestPacket);
            System.out.println("Client request packet has been sent to server.");

            // Making the response packet
            byte[] responseData = new byte[FRAGMENT_SIZE];
            // Initialize the response packet
            DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length);
            System.out.println("Client is waiting for response packet from server.");
            socket.receive(responsePacket);
            System.out.println("Client has received response packet from server.");
            response = new String(responsePacket.getData());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
