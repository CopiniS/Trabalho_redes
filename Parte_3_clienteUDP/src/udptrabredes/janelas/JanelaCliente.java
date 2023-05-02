package udptrabredes.janelas;
//Correto
import java.net.InetAddress;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;
import java.nio.charset.StandardCharsets;

public class JanelaCliente extends javax.swing.JFrame {
    private DefaultListModel modelLuz = new DefaultListModel();
    private DefaultListModel modelAr = new DefaultListModel();
    static InetAddress serverIP;
    static int serverPort;
    private String comando;
    
    public JanelaCliente(InetAddress srvIP, int srvPort) {
        initComponents();
        iniciaLugaresLuzAr();
        serverIP = srvIP; 
        serverPort = srvPort;
        ta_mensagem.setEditable(false);
        
        //Informa que o programa esta em execução
        ta_mensagem.setText("Conexão com filial - IP: "+srvIP+" Porta: "+srvPort);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        list_lugaresLuzAr = new javax.swing.JList<>();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        ta_mensagem = new javax.swing.JTextArea();
        bt_set = new javax.swing.JButton();
        bt_get = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        bt_sair = new javax.swing.JButton();
        lb_visualizador = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        bt_luz = new javax.swing.JButton();
        bt_ar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(list_lugaresLuzAr);

        ta_mensagem.setColumns(20);
        ta_mensagem.setFont(new java.awt.Font("Sitka Subheading", 1, 10)); // NOI18N
        ta_mensagem.setRows(5);
        jScrollPane2.setViewportView(ta_mensagem);

        bt_set.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bt_set.setText("SET");
        bt_set.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_setMouseClicked(evt);
            }
        });

        bt_get.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bt_get.setText("GET");
        bt_get.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_getMouseClicked(evt);
            }
        });

        bt_sair.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bt_sair.setText("SAIR");
        bt_sair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_sairMouseClicked(evt);
            }
        });

        lb_visualizador.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb_visualizador.setText("Visualizador");

        bt_luz.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bt_luz.setText("LUZ");
        bt_luz.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_luzMouseClicked(evt);
            }
        });

        bt_ar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bt_ar.setText("AR");
        bt_ar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_arMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(lb_visualizador))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bt_luz, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_ar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bt_set, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bt_get, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2)
                            .addComponent(jSeparator3))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(bt_sair, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lb_visualizador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_luz)
                    .addComponent(bt_ar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt_set, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_get, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_sair)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enviarJSON(String nomeSelec){
        try {
            JSONObject json = new JSONObject();
            DatagramSocket clientSock = new DatagramSocket();
            DatagramPacket rxPkt;
            String txMsg;
            
            byte[] txData = new byte[65507];//Canal de transmissão - transmissão de dados
            
            json.put("Lugar", nomeSelec);
            json.put("Comando", this.comando);
            
            txMsg = json.toString();
            txData = txMsg.getBytes(StandardCharsets.UTF_8);//JSON convertido para array de bytes

            DatagramPacket txPkt = new DatagramPacket(txData, txMsg.length(), JanelaCliente.serverIP, JanelaCliente.serverPort);
            
            clientSock.send(txPkt);
            ta_mensagem.setText(ta_mensagem.getText()+"\n\nMensagem Enviada!");
            
            if(!this.comando.equals("set")){
                byte[] rxData = new byte[65507];
                int i = 0;

                while(i != 1){
                    String msg;
                    
                    //cria um pacote vazio
                    rxPkt = new DatagramPacket(rxData, rxData.length);
                    clientSock.receive(rxPkt);

                    rxData = rxPkt.getData();//recebe os dados
                    msg = new String(rxData, StandardCharsets.UTF_8);//converte os dados binario em utf8
                    msg = msg.substring(0, rxData.length);

                    ta_mensagem.setText(ta_mensagem.getText() +"\n"+ msg);
                    i++;
                }
            }
            
        } catch (SocketException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", 0);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", 0);
        }
    }

    private void iniciaLugaresLuzAr(){
        this.modelLuz.addElement("luz_guarita");
        this.modelLuz.addElement("luz_estacionamento");
        this.modelLuz.addElement("luz_galpao_externo");
        this.modelLuz.addElement("luz_galpao_interno");
        this.modelLuz.addElement("luz_escritorios");
        this.modelLuz.addElement("luz_sala_reunioes");
        //===========================================//
        this.modelAr.addElement("ar_guarita");
        this.modelAr.addElement("ar_escritorios");
        this.modelAr.addElement("ar_sala_reunioes");
    }
    
    private void bt_luzMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_luzMouseClicked
        list_lugaresLuzAr.setModel(modelLuz);
    }//GEN-LAST:event_bt_luzMouseClicked

    private void bt_arMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_arMouseClicked
        list_lugaresLuzAr.setModel(modelAr);
    }//GEN-LAST:event_bt_arMouseClicked

    private void bt_sairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_sairMouseClicked
        System.exit(0);
    }//GEN-LAST:event_bt_sairMouseClicked

    private void bt_setMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_setMouseClicked
        String nomeSelec = list_lugaresLuzAr.getSelectedValue();
        this.comando = "set";
        enviarJSON(nomeSelec);
    }//GEN-LAST:event_bt_setMouseClicked

    private void bt_getMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_getMouseClicked
        String nomeSelec = list_lugaresLuzAr.getSelectedValue();
        this.comando = "get";
        enviarJSON(nomeSelec); 
    }//GEN-LAST:event_bt_getMouseClicked
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_ar;
    private javax.swing.JButton bt_get;
    private javax.swing.JButton bt_luz;
    private javax.swing.JButton bt_sair;
    private javax.swing.JButton bt_set;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lb_visualizador;
    private javax.swing.JList<String> list_lugaresLuzAr;
    private javax.swing.JTextArea ta_mensagem;
    // End of variables declaration//GEN-END:variables
}
