package csulb.cecs327.Services.Networking;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.FileReader;

public class ClientRemoteRef implements RemoteRefInterface {
    private ClientCommunicationModule client;
    private JsonObject catalog; // representing catalog.json from server
    public ClientRemoteRef(ClientCommunicationModule client) {
        this.client = client;
        catalog = getCatalog();
    }
    
    @Override
    /**
     * This class is for client to receive a JSON with parameters
     * for the desired Remote Method
     * @param remoteMethod = name of method client wishes to find
     * @return - JSON Object of desired method
     */
    public JsonObject getRemoteReference(String remoteMethod) {
        //Setting temp JsonObject
        Gson gson = new Gson();
        JsonObject request = new JsonObject();
        JsonObject[] jsonRequest;

        //Creating an array of Json Objects from catalog.json
        try {
            String path = "catalog.json";
            BufferedReader br = new BufferedReader(new FileReader(path));
            jsonRequest = gson.fromJson(br, JsonObject[].class);
            
            //Looks for method name, and returns JSON object with matching name 
            for (JsonObject object : jsonRequest){
                if( object.get("name").equals(remoteMethod))
                    request = object;
                else{
                    System.out.println("Remote Method not found.");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return request;
    }



    private JsonObject getCatalog() {
        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("remoteMethod", "getCatalog");
        jsonRequest.addProperty("objectName", "RemoteRefServices");
        String strRet = client.sendRequest(jsonRequest.toString());
        JsonParser jsonParser = new JsonParser();
        return jsonParser.parse(strRet.trim()).getAsJsonObject();
    }

}
