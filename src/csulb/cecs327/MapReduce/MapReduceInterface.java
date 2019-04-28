package csulb.cecs327.MapReduce;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.List;

public interface MapReduceInterface {
    public void map(String key, JsonObject value, DFS context, String file) throws IOException;
    public void reduce(String key, JsonObject value, DFS context, String file) throws IOException;
}
