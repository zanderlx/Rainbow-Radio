package csulb.cecs327.DFS;

import java.io.*;
import java.util.*;

import com.google.gson.Gson;

public class DFSCommand {
    DFS dfs;

    /**
     * To run commands
     * @param p local port
     * @param portToJoin the port we want to join
     * @throws Exception
     */
    public DFSCommand(int p, int portToJoin) throws Exception {
        dfs = new DFS(p);

        if (portToJoin > 0) {
            System.out.println("Joining " + portToJoin);
            dfs.join("127.0.0.1", portToJoin);
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String line = buffer.readLine();
        while (!line.equals("quit")) {
            String[] result = line.split("\\s");
            if (result[0].equals("join") && result.length > 1) {
                dfs.join("127.0.0.1", Integer.parseInt(result[1]));
            }
            if (result[0].equals("print")) {
                dfs.print();
            }
            if (result[0].equals("delete")) {
                dfs.delete();
            }
            if (result[0].equals("leave")) {
                dfs.leave();
            }
            if (result[0].equals("ls")) {
                dfs.lists();
            }
            if (result[0].equals("touch")) {
                dfs.create(result[1]);
                System.out.print("New File Created\n");
            }
            if (result[0].equals("mv")) {
                dfs.move(result[1], result[2]);
            }
            if (result[0].equals("append")) {
                RemoteInputFileStream input = new RemoteInputFileStream(result[2]);
                dfs.append(result[1], input);
                System.out.println("Page added\n");
            }
            if (result[0].equals("read")) {
                int pageNumber = Integer.parseInt(result[2]);
                int i;
                RemoteInputFileStream r = dfs.read(result[1], pageNumber);
                r.connect();
                while ((i = r.read()) != -1) {
                    System.out.print((char) i);
                }
                System.out.println();
                System.out.println("page read");
            }
            if (result[0].equals("head")) {
                dfs.head(result[1]);
            }
            line = buffer.readLine();
        }
    }

    /**
     * Main function to handle commands and pass in arguments
     * @param args
     * @throws Exception
     */
    static public void main(String args[]) throws Exception {
//        System.out.println("Enter port number you wish to connect to: ");
//        Scanner input = new Scanner(System.in);
//        String userPort = input.nextLine();
//        String[] args = new String[1];
//        args[0] = userPort;
//
//        System.out.println("UI Commands Below");
//        System.out.println("print");
//        System.out.println("delete");
//        System.out.println("leave");
//        System.out.println("ls");
//        System.out.println("touch");
//        System.out.println("mv");
//        System.out.println("append");
//        System.out.println("read");
//        System.out.println("head\n\n");


        if (args.length < 1) {
            throw new IllegalArgumentException("Parameter: <port> <portToJoin>");
        }
        if (args.length > 1) {
            DFSCommand dfsCommand = new DFSCommand(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        } else {
            DFSCommand dfsCommand = new DFSCommand(Integer.parseInt(args[0]), 0);
        }
    }
}