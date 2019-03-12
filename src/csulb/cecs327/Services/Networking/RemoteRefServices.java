package csulb.cecs327.Services.Networking;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RemoteRefServices {
    public String getCatalog(){
        try {
            return  new String(Files.readAllBytes(Paths.get("csulb/cecs327/Services/Networking/catalog.json")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
