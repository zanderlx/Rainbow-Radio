package csulb.cecs327.Server;

import csulb.cecs327.DFS.DFS;
import csulb.cecs327.Server.RPC.*;
import csulb.cecs327.Server.RPC.components.MusicServices;
import csulb.cecs327.Server.RPC.components.RemoteRefServices;
import csulb.cecs327.Server.RPC.components.SongDispatcher;
import csulb.cecs327.Server.RPC.components.UserServices;

/**
 * The main function for server backend
 */
public class Server {

    public static void main(String[] args) {
        DFS dfs = null;
        try {
            dfs = new DFS(1029);
            dfs.join("127.0.0.1", 1030);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Instance of the Dispatcher
        Dispatcher dispatcher = new Dispatcher();
        // Instance of the services that te dispatcher can handle
        SongDispatcher songDispatcher = new SongDispatcher(dfs);
        UserServices userServices = new UserServices(dfs);
        RemoteRefServices remoteRefServices = new RemoteRefServices(dfs);
        MusicServices musicServices = new MusicServices(dfs);

        dispatcher.registerObject(songDispatcher, "SongServices");
        dispatcher.registerObject(userServices, "UserServices");
        dispatcher.registerObject(remoteRefServices, "RemoteRefServices");
        dispatcher.registerObject(musicServices, "MusicServices");

        ServerCommunicationModule serverCommunicationModule = new ServerCommunicationModule(1024, dispatcher);
        serverCommunicationModule.connect();
        serverCommunicationModule.listen();
    }
}

