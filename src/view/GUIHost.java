/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.hostDAO;
import VO.hostVO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import servicos.HostServicos;
import utilidades.selecionarJogo;

/**
 *
 * @author 182220058
 */
public class GUIHost extends javax.swing.JFrame {

    /**
     * Creates new form GUIRecorde
     */
    public GUIHost() {
        initComponents();
    }
    public void criarHost() throws SQLException{
       
        HostServicos hs = new servicos.ServicosFactory().getHostServicos();
        int host= hs.mostrarHost();
        if(host == 0){
          hs.criarHost();  
          host = hs.mostrarHost();
        }
        
        jtfNumHost.setText(""+host);
        verificarConexao();
    }
    public void conectarHost() throws SQLException{
        
        HostServicos hs = new servicos.ServicosFactory().getHostServicos();
      boolean conectado = hs.conectarHost(Integer.parseInt(jtfConectar.getText()));
       if(conectado){
       hostVO hvo = new hostVO();
            hvo.loop = true;    
       criarLoop();
       }
    }
    public void desconectarhost() throws SQLException{
        
        HostServicos hs = new servicos.ServicosFactory().getHostServicos();
        hs.desconectarHost();
        hostVO hvo = new hostVO();
            hvo.loop = false;
        
       
    }
      public void criarLoop(){
  new Thread() {

    @Override
    public void run() {
        hostVO hvo = new hostVO();
      
        HostServicos hs = new servicos.ServicosFactory().getHostServicos();
    boolean running = hvo.loop;
int FPS = 60;
int UPS =20;
long initialTime = System.nanoTime();
final double timeU = 1000000000 / UPS;
final double timeF = 1000000000 / FPS;
double deltaU = 0, deltaF = 0;
int frames = 0, ticks = 0;
long timer = System.currentTimeMillis();
String jogo[] = null;
String[] mensagem = new String[8];
    while (running) {
        running = hvo.loop;
        long currentTime = System.nanoTime();
        deltaU += (currentTime - initialTime) / timeU;
        deltaF += (currentTime - initialTime) / timeF;
        initialTime = currentTime;

        if (deltaU >= 1) {
            
            try {
                //Aqui que eu vou updatar
               
             jogo = hs.verJogo();
        
            } catch (SQLException ex) {
                Logger.getLogger(GUIHost.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(jogo[0].equals("true") ){
                jogo[0] = "false";
                
                selecionarJogo slcJogo = new selecionarJogo();
                  
                try {
                    slcJogo.acessoJogos(jogo[1]);
                  
                } catch (SQLException ex) {
                    Logger.getLogger(GUIHost.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            ticks++;
            deltaU--;
        }

        if (deltaF >= 1) {
            
            frames++;
            deltaF--;
        }

        if (System.currentTimeMillis() - timer > 1000) {
            if (true) {
                
            }
            frames = 0;
            ticks = 0;
            timer += 1000;
        }

    }
    }
  }.start();
}
          public void verificarConexao(){
  new Thread() {

    @Override
    public void run() {
         hostVO hvo = new hostVO();
       HostServicos hs = new servicos.ServicosFactory().getHostServicos();
    boolean running = false;
int FPS = 60;
int UPS =20;
long initialTime = System.nanoTime();
final double timeU = 1000000000 / UPS;
final double timeF = 1000000000 / FPS;
double deltaU = 0, deltaF = 0;
int frames = 0, ticks = 0;
long timer = System.currentTimeMillis();

    while (!running) {
        hvo.procurandoHost = true;
        long currentTime = System.nanoTime();
        deltaU += (currentTime - initialTime) / timeU;
        deltaF += (currentTime - initialTime) / timeF;
        initialTime = currentTime;

        if (deltaU >= 1) {
           
            try {
                running = hs.verificarConexao();
                
            } catch (SQLException ex) {
                Logger.getLogger(GUIHost.class.getName()).log(Level.SEVERE, null, ex);
            }
            ticks++;
            deltaU--;
        }

        if (deltaF >= 1) {
            
            frames++;
            deltaF--;
        }

        if (System.currentTimeMillis() - timer > 1000) {
        }

    }
    JOptionPane.showMessageDialog(null, "Conectou-se");
  
    hvo.loop = true;
    hvo.procurandoHost = false;
    criarLoop();
    
    }
  }.start();
}
      
        
        
        
        
        
        
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbCriarHost = new javax.swing.JButton();
        jtfNumHost = new javax.swing.JTextField();
        jbtConectar = new javax.swing.JButton();
        jtfConectar = new javax.swing.JTextField();
        jtbDesconectar = new javax.swing.JButton();

        setTitle("Host");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jbCriarHost.setText("Criar Host");
        jbCriarHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCriarHostActionPerformed(evt);
            }
        });

        jtfNumHost.setEditable(false);

        jbtConectar.setText("Conectar Host");
        jbtConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtConectarActionPerformed(evt);
            }
        });

        jtbDesconectar.setText("Desconectar");
        jtbDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbDesconectarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jbtConectar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbCriarHost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jtbDesconectar))
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfNumHost)
                    .addComponent(jtfConectar, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbCriarHost)
                    .addComponent(jtfNumHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtConectar)
                    .addComponent(jtfConectar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jtbDesconectar)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbCriarHostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCriarHostActionPerformed
        try {
            criarHost();
        } catch (SQLException ex) {
            Logger.getLogger(GUIHost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbCriarHostActionPerformed

    private void jbtConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtConectarActionPerformed
        try {
            conectarHost();
        } catch (SQLException ex) {
            Logger.getLogger(GUIHost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtConectarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       try {
            desconectarhost();
         
        } catch (SQLException ex) {
            Logger.getLogger(GUIHost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jtbDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbDesconectarActionPerformed
        try {
            
            desconectarhost();
         
        } catch (SQLException ex) {
            Logger.getLogger(GUIHost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jtbDesconectarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIHost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIHost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIHost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIHost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIHost().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbCriarHost;
    private javax.swing.JButton jbtConectar;
    private javax.swing.JButton jtbDesconectar;
    private javax.swing.JTextField jtfConectar;
    private javax.swing.JTextField jtfNumHost;
    // End of variables declaration//GEN-END:variables
}
