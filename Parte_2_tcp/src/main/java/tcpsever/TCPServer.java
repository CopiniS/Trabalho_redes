package tcpsever;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void runServer(int port){
        System.out.println("Running TCPServer at port " + port + "...");
        try {
            ServerSocket listenSocket = new ServerSocket(port, 5);
            
            while(true){
                
                System.out.println("\tWaiting connection...");
                Socket clientSocket = listenSocket.accept();
                
                System.out.println("\t\tConnected to "
                    + clientSocket.getInetAddress().toString()
                    + " at port " + clientSocket.getPort());
                // 
                
                Connection c = new Connection(clientSocket);
                c.start();
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        int port = 0;
        
        //Obtém os parâmetros
        if(args.length == 1){
            port = Integer.parseInt(args[0]);
            if((port >= 1) && (port <= 65535)){
                runServer(port);
            }else{
                System.err.println("\nInvalid port value\n\tRange: 1-65535");
            System.exit(1);
            }
        }
            
        else{
            System.err.println("\nParameter error!\n\tSet server port (1-65535)");
            System.exit(1);
        }
            
    }
    
}
