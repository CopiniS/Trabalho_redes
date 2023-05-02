package udpservertrabredes;
//Correto
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.LinkedList;
import org.json.simple.JSONObject;

public class UDPServerTrabRedes {
    static List<Equipamentos> listaEquip = new LinkedList();
    static String msg;
    
    public static void tratandoEnvio(InetAddress srcIPaddress, int srcPort, String msg, DatagramSocket srvSock){
        try {
            JSONObject json = new JSONObject();
            DatagramPacket txPkt;
            byte[] txData = new byte[65507];//Canal de transmissão - transmissão de dados
            String txMsg;

            //Criar mensagem de resposta do servidor
            for(Equipamentos equip: listaEquip){
                if(msg.contains(equip.getLugar())){
                    json.put("Local", equip.getLugar());
                    json.put("Status", equip.getStatus());
                    break;
                }
            }
            
            txMsg = json.toString();
            txData = txMsg.getBytes(StandardCharsets.UTF_8);/*está transformando txMsg em bytes, 
                                                            com a codificação de carcteres do utf8*/

            txPkt = new DatagramPacket(txData/*a mensagem*/, txData.length/*o tamanho da mensagem*/,
                    srcIPaddress/*o IP de quem mandou a mensagem*/, srcPort/*a porta*/);
            
            //Enviar mensagem de resposta ao cliente
            srvSock.send(txPkt);
            System.out.println("");
            System.out.println("Mensagem enviada para o cliente");
            
        } catch (SocketException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }
    
    public static void tratandoAlteracao(String msg, InetAddress srcIPaddress){
        String nomeLugar = null;
        
        for(Equipamentos equip: listaEquip){
            if(msg.contains(equip.getLugar())){
                nomeLugar = equip.getLugar();
                if(equip.getStatus() == true){
                    equip.setStatus(false);
                }
                else{
                    equip.setStatus(true);
                }
                break;
            }
        }
        System.out.println("");
        System.out.println("Alteração Feita com Êxito em "+nomeLugar);
        System.out.println("IP origem: "+srcIPaddress);
    }
    
    public static void lerNomeList(){
        String[] nomesLocais = new String[9];
        
        nomesLocais[0] = "luz_guarita";
        nomesLocais[1] = "luz_estacionamento";
        nomesLocais[2] = "luz_galpao_externo";
        nomesLocais[3] = "luz_galpao_interno";
        nomesLocais[4] = "luz_escritorios";
        nomesLocais[5] = "luz_sala_reunioes";
        nomesLocais[6] = "ar_guarita";
        nomesLocais[7] = "ar_escritorios";
        nomesLocais[8] = "ar_sala_reunioes";
        
        for(int i = 0; i < 9; i++){
            Equipamentos equip = new Equipamentos("Vazio", false);
            equip.setLugar(nomesLocais[i]);
            listaEquip.add(equip);
        }
    }
    
    public static void main(String[] args) {
        lerNomeList();
        int port = 0;
        
        if(args.length != 1){
            System.out.println("Invalid arguments...\nUsers: UDPServer");
            System.exit(0);
        }
        else{
            port = Integer.parseInt(args[0]);
        }
        
        System.out.print("Conectado ao servidor na Porta: "+port+"...");
        System.out.println("");
        
        try {
            DatagramSocket srvSock = new DatagramSocket(port);

            
            //65535 - 20 bytes de cabeçalho do IP - 8 bytes do UDP = 65507
            
            byte[] rxData = new byte[65507];//Canal de recepção - recepção de dados
            
            while(true){
                DatagramPacket rxPkt;
                InetAddress srcIPaddress;
                String msg, txMsg;
                int srcPort;
                
                //Criar pacote udp vazio
                rxPkt = new DatagramPacket(rxData, rxData.length);
                
                //Aguarda o recebimento de uma mennsagem 
                
                srvSock.receive(rxPkt);// a porta de comunicação do servidor irá receber o pacote de dados 
                
                //tratando o payload da msg
                rxData = rxPkt.getData();
                msg = new String(rxData, StandardCharsets.UTF_8);
                msg = msg.substring(0, rxPkt.getLength());
                 
                //Obter o IP do cliente
                srcIPaddress = rxPkt.getAddress();
                srcPort = rxPkt.getPort();
                
                if(msg.contains("set")){
                    tratandoAlteracao(msg, srcIPaddress);
                }
                else{
                    tratandoEnvio(srcIPaddress, srcPort, msg, srvSock);
                }              
            }
            
        } catch (SocketException ex) {
            System.err.println("\nError: "+ex.getMessage());
            System.exit(1);
        } catch (IOException ex) {
            System.err.println("\nError: "+ex.getMessage());
            System.exit(1);
        }
    }
    
}
