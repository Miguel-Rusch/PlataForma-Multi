/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.jogoDAO;
import DAO.postDAO;
import VO.jogoVO;
import VO.loginVO;
import VO.postVO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 182220058
 */
public class GUIPostar extends javax.swing.JFrame {
    
    DefaultTableModel dtm = new DefaultTableModel(
                new Object[][]{},
            new Object[]{"id","Usuário", "Host", "Comentario", "Jogo"}
    );
    /**
     * Creates new form GUIPostar
     */
    public GUIPostar() {
        initComponents();
        prencher();
    }
    private void prencher(){
     limpar();
     try {
            postDAO PDAO = new postDAO();

            ArrayList<postVO> crod = new ArrayList<>();
            
            
            crod = PDAO.mostrarPost();
            
            for ( int i = 0; i < crod.size(); i++) {
                dtm.addRow(new String[] {
                    String.valueOf(crod.get(i).getIdPost()),
                    String.valueOf(crod.get(i).getUsuario()),
                    String.valueOf(crod.get(i).getHost()),
                    String.valueOf(crod.get(i).getComentario()),
                    String.valueOf(crod.get(i).getJogo()),
                    
                });
            }
            jtTabela.setModel(dtm);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERRO nO GUICOLABORADOR" + e.getMessage()  );
        }
        
    }
    private void filtarPost() throws SQLException{
        if(jtfPesquisa.getText().isEmpty()){
            prencher();
        }else{
            String pesquisa = (String) jcClsases.getSelectedItem();
            String query = null;
            
           if( pesquisa.equals("Usuário")){
                    query = "where usuario like '%" + jtfPesquisa.getText() + "%' ";
                }else if(pesquisa.equals("Jogo")){
                    query = "where jogo like '%" + jtfPesquisa.getText() + "%' ";
                }
             ArrayList<postVO> prod = new ArrayList<>();
                
                //Recebendo o ArrayList cheio no produto
                postDAO PDAO = new postDAO();
                prod = PDAO.filtarPost(query);
                
                for( int i = 0; i < prod.size(); i++){
                    dtm.addRow(new String[] {
                        String.valueOf(prod.get(i).getUsuario()),
                        String.valueOf(prod.get(i).getHost()),
                        String.valueOf(prod.get(i).getComentario()),
                        String.valueOf(prod.get(i).getJogo())
                    });
                }//fecha o laço for
                
                //Adicionando o modelo de tablea com os dados na tabela jtProduto
                jtTabela.setModel(dtm);
        }
    }
    private void postar() throws SQLException{
        loginVO lvo = new loginVO();
        if(jtfJogo.getText().equals("")){
        JOptionPane.showMessageDialog(null,"Escreva um jogo");
        }else if(!lvo.isOnline()){
            JOptionPane.showMessageDialog(null,"Não pode criar um post pois está em modo offline");
        }else{
         postVO pvo = new postVO();
         pvo.setComentario(jtfComentario.getText());
         pvo.setJogo(jtfJogo.getText());
            if(pvo.getNumPost() < pvo.getNumPostPerm()){
            
         postDAO pdao = new postDAO();
         int idPost = pdao.postar(pvo);
         int [] postCriados =pvo.getIdPostCreated();
         
       int p = pvo.getNumPost();
         pvo.setNumPost(p+ 1);
        postCriados[p] = idPost;
         pvo.setIdPostCreated(postCriados);
         
            }else{
             JOptionPane.showMessageDialog(null,"Limite de posts atingidos delete um caso queira criar um novo");
            }
        }
    }
    private void deletar(){
        try {
            int linha = jtTabela.getSelectedRow();
            
            if( linha == -1 ){
                JOptionPane.showMessageDialog(
                        null,
                        "Por favor selecione uma linha!");
            }else{
                
                String idPost =   (String) jtTabela.getValueAt(linha, 0);
                loginVO lvo = new loginVO();
                postDAO PDAO = new postDAO();
                String rsEm = PDAO.permitirDel(Integer.parseInt(idPost));
            
                if(rsEm.equals(lvo.getEm())){
                PDAO.deletarPost(Integer.parseInt(idPost));
                
                postVO pvo = new postVO();
                pvo.setNumPost(pvo.getNumPost()- 1);
                }
                else{
                            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao deleatr post ele não foi criado por você!");
                }
                
                //Mensagem para o usuário
                              
               
            }//fim do else
        } catch (Exception e) {
          
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao deleatr post!" + e.getMessage() );
        }
    }
    public void deletarTodosPosts() throws SQLException{
     postVO pvo = new postVO();
     int[] posts = pvo.getIdPostCreated();
     postDAO pdao = new postDAO();
     for(int i = 0; i <= pvo.getNumPost();i++){
     pdao.deletarPost(posts[i]);
     posts[i] = 0;
     pvo.setNumPost(0);
     }
    }
    private void limpar(){
        dtm.setNumRows(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtTabela = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jtfPesquisa = new javax.swing.JTextField();
        jcClsases = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jtfJogo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtfComentario = new javax.swing.JTextField();
        jbCriar = new javax.swing.JButton();
        jtbDeletar = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jtTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Usuário", "Host", "Comentário", "Jogo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtTabela);

        jLabel1.setText("Pesquisar");

        jtfPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfPesquisaKeyReleased(evt);
            }
        });

        jcClsases.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Usuário", "Jogo" }));

        jLabel2.setText("       Criar Post");

        jLabel3.setText("Jogo");

        jLabel4.setText("Comentário");

        jbCriar.setText("Criar");
        jbCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCriarActionPerformed(evt);
            }
        });

        jtbDeletar.setText("Deletar");
        jtbDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbDeletarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jcClsases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jtfJogo, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4))
                            .addComponent(jbCriar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtbDeletar)
                            .addComponent(jtfComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcClsases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfJogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbCriar)
                            .addComponent(jtbDeletar)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jtfComentario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCriarActionPerformed
        try {
            postar();
            prencher();
        } catch (SQLException ex) {
            Logger.getLogger(GUIPostar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbCriarActionPerformed

    private void jtfPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfPesquisaKeyReleased
     
        try {
            limpar();
            filtarPost();
        } catch (SQLException ex) {
            Logger.getLogger(GUIPostar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jtfPesquisaKeyReleased

    private void jtbDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbDeletarActionPerformed
      deletar();
      limpar();
      prencher();
    }//GEN-LAST:event_jtbDeletarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // Caso queira fazer que quando sai feche todos seus post
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            deletarTodosPosts();
        } catch (SQLException ex) {
            Logger.getLogger(GUIPostar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(GUIPostar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIPostar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIPostar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIPostar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIPostar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbCriar;
    private javax.swing.JComboBox<String> jcClsases;
    private javax.swing.JTable jtTabela;
    private javax.swing.JButton jtbDeletar;
    private javax.swing.JTextField jtfComentario;
    private javax.swing.JTextField jtfJogo;
    private javax.swing.JTextField jtfPesquisa;
    // End of variables declaration//GEN-END:variables
}
