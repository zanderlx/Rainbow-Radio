package csulb.cecs327.Services.Networking;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class ClientThreadHandler extends Thread{
    Socket clientSocket = null;
    DataInputStream myInput = null;
    DataOutputStream myOutput = null;
    Dispatcher myDispatcher = null;

    // Constructor
    ClientThreadHandler(Socket s, DataInputStream is, DataOutputStream os){
        this.clientSocket = s;
        this.myInput = is;
        this.myOutput = os;

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
                jsonRequest = myInput.readUTF();                   // Get string input request
                System.out.println("Request received from client: "+ jsonRequest);
                jsonReturn = myDispatcher.dispatch(jsonRequest);    // Save response
                System.out.println("Writing response to outputstream from server: "+jsonReturn);
                myOutput.writeUTF(jsonReturn);                    // Send to output stream
            }catch(IOException e){
                System.out.println(e);
            }
        }
    }
}
