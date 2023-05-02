package udptrabredes;

import java.net.InetAddress;
import java.net.UnknownHostException;
import udptrabredes.janelas.JanelaCliente;
import javax.swing.JOptionPane;

public class UDPTrabRedes {
//Certo
    public static void main(String[] args) {
        int srvPort = 0;
        InetAddress srvIP = null;
        
        if(args.length != 2){
            JOptionPane.showMessageDialog(null, "Invalid arguments...\nUsers: UDPSCliente <IP> <Port>", "ERRO", 0);
            System.exit(0);
        }
        else{
            try {
                srvIP = (InetAddress) InetAddress.getByName(args[0]);
                srvPort = Integer.parseInt(args[1]);
                
            } catch (UnknownHostException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", 0);
            }
        }

        JanelaCliente j = new JanelaCliente(srvIP, srvPort);
        j.setVisible(true);
        j.setLocationRelativeTo(null);
    }
    
}
