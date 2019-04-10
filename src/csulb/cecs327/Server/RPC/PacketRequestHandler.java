package csulb.cecs327.Server.RPC;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * This class is made to handler all the request packet coming from client to server
 * it will send the request packet to dispatcher
 */
public class PacketRequestHandler extends Thread {
    private DatagramSocket socket;
    private DatagramPacket packet;
    public Dispatcher myDispatcher;

    public PacketRequestHandler(DatagramSocket s, DatagramPacket d, Dispatcher dispatcher) {
        // Set socket
        this.socket = s;
        // Set packet as received request packet
        this.packet = d;
        this.myDispatcher = dispatcher;
        System.out.println("New client packet handler created");
    }

    @Override
    public void run() {
        // Getting request packet's payload
        String request = new String(packet.getData());
        System.out.println("Server request string: " + request);
        // Send request to dispatcher
        String response = myDispatcher.dispatch(request.trim());
        System.out.println("Server preparing response packet");
        byte[] payload = response.getBytes();
        // Initialize payload with response bytes
        DatagramPacket responsePacket = new DatagramPacket(payload, payload.length, packet.getAddress(), packet.getPort());     // Prepare response packet
        try {
            // Send response packet back to client
            socket.send(responsePacket);
            System.out.println("Server has sent response packet, thread terminating");
            // Killing the thread after execution
            this.interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
