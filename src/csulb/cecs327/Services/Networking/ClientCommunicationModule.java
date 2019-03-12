package csulb.cecs327.Services.Networking;
import java.io.*;
import java.net.*;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientCommunicationModule {
    static final int FRAGMENT_SIZE = 15000;                      // Packet size
    byte[] packetSize = new byte[FRAGMENT_SIZE];

    private DatagramSocket socket = null;
    private InetAddress IPAddress = null;
    private int portNumber;


    public void connect(int portNum){
        try {
            // Get localhost IP address
            this.IPAddress = InetAddress.getByName("localhost");
            // Initialize Socket
            this.socket = new DatagramSocket();
            // Initialize port number
            this.portNumber = portNum;

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public String sendRequest(String request){
        String response = "";
        try {
            // Initialize payload
            byte[] requestPayload = new byte[FRAGMENT_SIZE];
            // Fill payload
            requestPayload = request.getBytes();
            // Initialize request packet
            DatagramPacket requestPacket = new DatagramPacket(requestPayload, requestPayload.length, this.IPAddress, this.portNumber);
            System.out.println("Client sending request packet.");
            // Send request packet
            socket.send(requestPacket);
            System.out.print("Client request packet sent.");
            // Prepare response packet
            byte[] responseData = new byte[FRAGMENT_SIZE];
            // Initialize reponse packet
            DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length);
            System.out.println("Client attempting to receive response packet.");
            socket.receive(responsePacket);
            System.out.println("Client has received response packet from server.");
            response = new String(responsePacket.getData());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
