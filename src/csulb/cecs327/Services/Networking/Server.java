package csulb.cecs327.Services.Networking;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Server {

    public static void main(String[] args) {
        // Instance of the Dispatcher
        Dispatcher dispatcher = new Dispatcher();
        // Instance of the services that te dispatcher can handle
        SongDispatcher songDispatcher = new SongDispatcher();
        UserServices userServices = new UserServices();

        dispatcher.registerObject(songDispatcher, "SongServices");
        dispatcher.registerObject(userServices, "UserServices");

        // Testing  the dispatcher function
        // First we read the request. In the final implementation the jsonRequest
        // is obtained from the communication module
//        try {
//            String jsonRequest = new String(Files.readAllBytes(Paths.get("/Users/pramodchamala/IdeaProjects/CECS-327-Music-Player/src/csulb/cecs327/Services/Networking/getSongChunk.json")));
//            String ret = dispatcher.dispatch(jsonRequest);
//            System.out.println(ret);
//
//            //System.out.println(jsonRequest);
//        } catch (Exception e)
//        {
//            System.out.println(e);
//        }
        ServerCommunicationModule serverCommunicationModule = new ServerCommunicationModule(1024, dispatcher);
        serverCommunicationModule.connect();
        serverCommunicationModule.listen();
    }
}

