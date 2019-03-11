/**
* The Proxy implements ProxyInterface class. The class is incomplete 
* 
* @author  Oscar Morales-Ponce
* @version 0.15
* @since   2019-01-24 
*/
package csulb.cecs327.Services.Networking;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class Proxy implements ProxyInterface {
    //Todo: replace with communication module
    //Dispatcher dispacher;   // This is only for test. it should use the Communication  Module
    ClientCommunicationModule client = null;
    public Proxy(int portNumber)
    {
        this.client = new ClientCommunicationModule();
        client.connect(portNumber);
    }
    
    /*
    * Executes the  remote method "remoteMethod". The method blocks until
    * it receives the reply of the message. 
    */
    public JsonObject synchExecution(String remoteMethod, String[] param)
    {
        RemoteRef remoteRef = new RemoteRef();
        JsonObject jsonRequest = remoteRef.getRemoteReference("remoteMethod");
        
        JsonParser parser = new JsonParser();
        //String strRet =  this.dispacher.dispatch(jsonRequest.toString());
        System.out.println("Sending request: "+ jsonRequest.toString());
        String strRet =  client.sendRequest(jsonRequest.toString());
        System.out.println("Returning response from server to input stream: "+strRet);
        String myReturn = strRet.trim();
        return parser.parse(myReturn).getAsJsonObject();
    }

    /*
    * Executes the  remote method remoteMethod and returns without waiting
    * for the reply. It does similar to synchExecution but does not 
    * return any value
    * 
    */
    //Todo: implement this method
    public void asynchExecution(String remoteMethod, String[] param)
    {
        return;
    }
}


