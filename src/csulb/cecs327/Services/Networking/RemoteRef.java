package csulb.cecs327.Services.Networking;
import com.google.gson.JsonObject;
import csulb.cecs327.Models.*;
import java.io.BufferedReader;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

//Todo: implement this class
public class RemoteRef implements  RemoteRefInterface {
    @Override
    public Gson getRemoteReference(String remoteMethod, String[] param){
        
        JsonObject jsonRequest = new JsonObject();
        JsonObject jsonParam = new JsonObject();
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();


        jsonRequest.addProperty("remoteMethod", remoteMethod);
        jsonRequest.addProperty("objectName","SongServices");

        if (remoteMethod.equals("getSongChunk"))
        {

            jsonParam.addProperty("song", param[0]);
            jsonParam.addProperty("fragment", param[1]);

        }


        if(remoteMethod.equals("login"))
        {
            jsonParam.addProperty("name", param[0]);
            jsonParam.addProperty("password", param[1]);
        }
        
        if(remoteMethod.equals("register")){
            jsonParam.addProperty("user", param[0]);
            jsonParam.addProperty("email", param[1]);
            jsonParam.addProperty("password", param[2]);
        }


        jsonRequest.add("param", jsonParam);





        return null;
        //gson.fromJson(jsonRequest,) 
    }

}
