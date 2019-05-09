package csulb.cecs327.DFS;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.*;


import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Mapper implements MapReduceInterface, Serializable {
    //TODO These 2 methods


    /**
     * Distribute the words throughout the file system, putting the word according to its guid between 2 processes
     *
     * @param key This will determine if the song will be searched by artist, albumName, id, etc.
     * @param value This is the song as a JsonElement
     * @param context Connection to the DFS context, this will be emitted to once mapped
     * @throws IOException
     */
    public void map(String key, JsonElement value, DFS context, String file) throws IOException {
        //TODO

        //Finding property of key that was given for the song element
        String newKey = value.getAsJsonObject().get(key).getAsString();

        //Obtain priority information on song
        JsonObject tempValue = value.getAsJsonObject();
        JsonObject newValue = new JsonObject();

        String artist="";
        String albumName="";
        String songTitle="";
        String id="";
        Double popularity=0.0;

        if(!tempValue.get("artist").getAsJsonObject().get("name").isJsonNull())
            artist=tempValue.get("artist").getAsJsonObject().get("name").getAsString().replaceAll("\"", "");

        if(!tempValue.get("release").getAsJsonObject().get("name").isJsonNull())
            albumName= tempValue.get("release").getAsJsonObject().get("name").getAsString();

        if(!tempValue.get("song").getAsJsonObject().get("title").isJsonNull())
            songTitle =tempValue.get("song").getAsJsonObject().get("title").getAsString();

        if(!tempValue.get("artist").getAsJsonObject().get("id").isJsonNull())
            id= tempValue.get("artist").getAsJsonObject().get("id").getAsString();

        if(!tempValue.get("artist").getAsJsonObject().get("hotttnesss").isJsonNull())
            popularity = tempValue.get("artist").getAsJsonObject().get("hotttnesss").getAsDouble();

        //Create newValue as a JsonObject to be passed to emit
        newValue.addProperty("artist", artist);
        newValue.addProperty("album name", albumName);
        newValue.addProperty("song title", songTitle);
        newValue.addProperty("id", id);
        newValue.addProperty("hotttness", popularity);



        //if key == artist
        //newKey == Killers, newValue == JSONobject with values for killers
       context.emit(newKey, newValue, file);
    }


    public void reduce(String key, JsonObject values, DFS context, String file) throws IOException {

        context.emit(key, values, file);
    }

    /**
     * Converting a string into GUID - Phuc: DId it just in case, not 1 of the methods
     *
     * @param objectName
     * @return
     */
    private long md5(String objectName) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(objectName.getBytes());
            BigInteger bigInt = new BigInteger(1, m.digest());
            return Math.abs(bigInt.longValue());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }
        return 0;
    }
}
