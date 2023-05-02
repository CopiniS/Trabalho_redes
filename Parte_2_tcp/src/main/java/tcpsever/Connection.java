package tcpsever;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.DatagramPacket;
import java.net.Socket;
import java.net.URI;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.Arrays;
import org.json.JSONObject;

public class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    
    public Connection(Socket aClientSoclet){
        try {
            clientSocket = aClientSoclet;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
        } catch (Exception e) {
            System.err.println("Connection: " + e.getMessage());
        }
    }
    
    public void run(){
        try {
            //Recebe a mensagem do cliente
            String data = in.readUTF();
                       
            JSONObject jsonObj = new JSONObject(data);
            
            File arquivo_file = new File(jsonObj.getString("file_name"));
            
            String hash = jsonObj.getString("hash_value");
            
            int arquivo_size = (int)arquivo_file.length(); 
            
            byte[] arquivo_bytes = new byte[arquivo_size]; 
            
            arquivo_bytes = Files.readAllBytes(arquivo_file.toPath());
            
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            byte[] hash_value = md.digest(arquivo_bytes);
            
            System.out.println("\tHash: "+ (hash_value));
            
            System.out.println("\t[Received = " + clientSocket.getInetAddress().toString()
                + ":" + clientSocket.getPort() + "]: " + data);
                
            String hashCalculado = hash_value.toString();
            if(hash.equals(hashCalculado)){
                System.out.println("ok");
                String rdata = "Received by server";
            out.writeUTF(rdata);
            }
            
           
            
        } catch (Exception e) {
            System.err.println("Connection error: " + e.getMessage());
        }
    }
    private static String toHexFormat(byte[] hash) {
        StringBuilder sb = new StringBuilder();
        for(byte b : hash){
            String toAppend = String.format("%2X", b).replace("", "0"); 
            sb.append(toAppend);
        }
        return sb.toString();
    }
}
