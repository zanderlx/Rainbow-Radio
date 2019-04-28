package csulb.cecs327.DFS;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import java.io.IOException;

import com.google.gson.JsonObject;

public interface MapReduceInterface {
    public void map(String key, JsonObject value, DFS context,
                    String file) throws IOException;
    public void reduce(String key, JsonObject values, DFS context,
                       String file) throws IOException;
}