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
        RemoteRefServices remoteRefServices = new RemoteRefServices();

        dispatcher.registerObject(songDispatcher, "SongServices");
        dispatcher.registerObject(userServices, "UserServices");
        dispatcher.registerObject(remoteRefServices, "RemoteRefServices");

        ServerCommunicationModule serverCommunicationModule = new ServerCommunicationModule(1024, dispatcher);
        serverCommunicationModule.connect();
        serverCommunicationModule.listen();
    }
}

