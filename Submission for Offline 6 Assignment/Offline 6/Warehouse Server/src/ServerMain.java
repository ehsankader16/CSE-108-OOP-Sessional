
import java.io.IOException;
import java.net.ServerSocket;


public class ServerMain {


    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(3600);
            while(true) {
                new ServerController(serverSocket.accept()).start();
            }
        } catch(IOException e) {
            System.out.println("Server exception " + e.getMessage());
        }

    }

}
