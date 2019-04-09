package csulb.cecs327.Server.RPC;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This service handles uploading catalog.json
 */
public class RemoteRefServices {
    /**
     * This method is called to get the catalog of services
     * @return String: catalog.json
     */
    public String getCatalog(){
        try {
            return  new String(Files.readAllBytes(Paths.get("src/csulb/cecs327/Server/RPC/catalog.json")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
