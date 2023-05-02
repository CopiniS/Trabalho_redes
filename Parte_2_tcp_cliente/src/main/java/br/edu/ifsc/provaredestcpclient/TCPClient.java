package br.edu.ifsc.provaredestcpclient;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.List;
import java.util.Scanner;
import org.json.JSONObject;

public class TCPClient {
    
    
    public static void main(String[] args) {
        //Scanner input = new Scanner(System.in);
        
        InetAddress srvAddr = null;
        int port = 0;
        String outMsg, inMsg;
        
                
        if(args.length == 2){
            try {
                srvAddr = InetAddress.getByName(args[0]);
            } catch (UnknownHostException e) {
                System.err.println("Server address: " + e.getMessage());
                System.exit(1);
            }
            port = Integer.parseInt(args[1]);
            if((port < 1) || (port > 65535)){
                System.err.println("\tInvalid port value!\n\tRange: 1 - 65535");
                System.exit(1);
            }
            
        }else{
            System.err.println("Error\n\tUses: TCPClient <ip-server> <port-server>");
            System.exit(1);
        }
        
        try {
            System.out.println("Connecting to " + srvAddr.toString() + " at port " + port + "...");
            Socket sock = new Socket(srvAddr, port);
            
            DataInputStream in = new DataInputStream(sock.getInputStream());
            DataOutputStream out = new DataOutputStream(sock.getOutputStream());
            
            while(true){
                
                outMsg = "C:\\Users\\antho\\OneDrive\\Área de Trabalho\\redes.txt";  
                
                File arquivo_file = new File(outMsg);
                                
                int arquivo_size = (int)arquivo_file.length(); 
                
                byte[] arquivo_bytes = new byte[arquivo_size]; 
                
                arquivo_bytes = Files.readAllBytes(arquivo_file.toPath());
                
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] hash_value = md.digest(arquivo_bytes);
                

                File file = new File("src\\arquivo.md5");
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));

                writer.write(hash_value.toString() +"  arquivo.txt");

                writer.flush();
                writer.close();
                
                if("<close>".equals(outMsg)){
                    sock.close();
                    System.exit(0);
                }
                System.out.println("\tHash: "+ hash_value.toString());

                JSONObject jsonObj = new JSONObject();
                
                jsonObj.put("file_name", arquivo_file);
                jsonObj.put("hash_value", hash_value.toString());
                              
                //Envia a mensagem
                out.writeUTF(jsonObj.toString());
                
                // Recebe resposta do servidor
                inMsg = in.readUTF();
                System.out.println("[Responde]: " + inMsg);
                
            }
            
            
        } catch (Exception e) {
            System.err.println("Connection: " + e.getMessage());
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
