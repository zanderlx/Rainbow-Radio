package csulb.cecs327.Server;

import csulb.cecs327.Server.RPC.*;
import csulb.cecs327.Server.RPC.components.MusicServices;
import csulb.cecs327.Server.RPC.components.SongDispatcher;
import csulb.cecs327.Server.RPC.components.UserServices;

/**
 * The main function for server backend
 */
public class Server {

    public static void main(String[] args) {
        // Instance of the Dispatcher
        Dispatcher dispatcher = new Dispatcher();
        // Instance of the services that te dispatcher can handle
        SongDispatcher songDispatcher = new SongDispatcher();
        UserServices userServices = new UserServices();
        RemoteRefServices remoteRefServices = new RemoteRefServices();
        MusicServices musicServices = new MusicServices();

        dispatcher.registerObject(songDispatcher, "SongServices");
        dispatcher.registerObject(userServices, "UserServices");
        dispatcher.registerObject(remoteRefServices, "RemoteRefServices");
        dispatcher.registerObject(musicServices, "MusicServices");

        ServerCommunicationModule serverCommunicationModule = new ServerCommunicationModule(1024, dispatcher);
        serverCommunicationModule.connect();
        serverCommunicationModule.listen();
    }
}

