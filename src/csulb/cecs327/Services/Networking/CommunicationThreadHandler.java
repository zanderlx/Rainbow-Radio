package csulb.cecs327.Services.Networking;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

/**
 * This class is to handle the thread happening during the communication between server and client
 */
class CommunicationThreadHandler extends Thread{
    /**
     * Creating the client socket, input and output of data stream and dispatcher
     */
    Socket clientSocket = null;
    DataInputStream myInput = null;
    DataOutputStream myOutput = null;
    Dispatcher myDispatcher = null;

    /**
     * Taking in the client socket, input and output to create a new thread
     * @param socket
     * @param in
     * @param out
     */
    CommunicationThreadHandler(Socket socket, DataInputStream in, DataOutputStream out){
        this.clientSocket = socket;
        this.myInput = in;
        this.myOutput = out;
        this.myDispatcher = new Dispatcher();
        myDispatcher.registerObject(new SongDispatcher(), "SongServices");
        System.out.println("New client thread constructed");
    }

    @Override
    public void run(){
        String jsonRequest = null;
        String jsonReturn = null;
        while(true){
            try{
                // Get string input request
                jsonRequest = myInput.readUTF();
                System.out.println("Request received from client: "+ jsonRequest);
                // Save response
                jsonReturn = myDispatcher.dispatch(jsonRequest);
                System.out.println("Writing response to outputstream from server: "+jsonReturn);
                // Send to output stream
                myOutput.writeUTF(jsonReturn);
            }catch(IOException e){
                System.out.println(e);
            }
        }
    }
}
