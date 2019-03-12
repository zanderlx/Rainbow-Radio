package csulb.cecs327.Services.Networking;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ClientRemoteRef implements RemoteRefInterface {
    private ClientCommunicationModule client;
    private JsonObject catalog;
    public ClientRemoteRef(ClientCommunicationModule client) {
        this.client = client;
        catalog = getCatalog();
    }
    
    @Override
    public JsonObject getRemoteReference(String remoteMethod) {

        JsonObject jsonRequest = new JsonObject();
        JsonObject jsonParam = new JsonObject();

        jsonRequest.addProperty("remoteMethod", remoteMethod);
//        String object = catalog.get("")
//        jsonRequest.addProperty("object", object);

//        // Register
//        if (remoteMethod.equals("register")) {
//            jsonParam.addProperty("user", param[0]);
//            jsonParam.addProperty("email", param[1]);
//            jsonParam.addProperty("password", param[2]);
//        }
//
//        // Login
//        if (remoteMethod.equals("login")) {
//            jsonParam.addProperty("name", param[0]);
//            jsonParam.addProperty("password", param[1]);
//        }
//
//        // getSongChunk
//        if (remoteMethod.equals("getSongChunk")) {
//
//            jsonParam.addProperty("song", param[0]);
//            jsonParam.addProperty("fragment", param[1]);
//
//        }
//
//        // addToPlaylist
//        if (remoteMethod.equals("addToPlaylist")) {
//            jsonParam.addProperty("song", param[0]);
//        }

        jsonRequest.add("param", jsonParam);

        return jsonRequest;

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
