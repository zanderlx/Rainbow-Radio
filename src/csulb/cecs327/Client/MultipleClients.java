package csulb.cecs327.Client;

public class MultipleClients {
    
    public static void main(String[] args) {
        Client client1 = new Client();
        Client client2 = new Client();
        Client client3 = new Client();
        client1.run();
        client2.run();
        client3.run();
        
    }
}
    

