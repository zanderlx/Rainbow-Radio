package csulb.cecs327.DFS;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Mapper implements MapReduceInterface, Serializable {
    //TODO These 2 methods
    /**
     * Distribute the words throughout the file system, putting the word according to its guid between 2 processes
     * @param key
     * @param value
     * @param context
     * @throws IOException
     */
    public void map(String key, JsonObject value, DFS context, String file) throws IOException {

    }

    public void reduce(Integer key, JsonObject values, DFS context, String file) throws IOException{

    }

    /**
     * Converting a string into GUID - Phuc: DId it just in case, not 1 of the methods
     * @param objectName
     * @return
     */
    private long md5(String objectName)
    {
        try
        {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(objectName.getBytes());
            BigInteger bigInt = new BigInteger(1,m.digest());
            return Math.abs(bigInt.longValue());
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();

        }
        return 0;
    }
}
