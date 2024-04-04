/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import DAO.jogoDAO;
import VO.hostVO;
import VO.jogoVO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jogoSimples.JogoDaVelha;
import jogoSimples.guessNumber;
import servicos.JogoServicos;
import utilidades.selecionarJogo;

/**
 *
 * @author 182220058
 */
public class GUIPesquisar extends javax.swing.JFrame {
    
    DefaultTableModel dtm = new DefaultTableModel(
                new Object[][]{},
            new Object[]{"Id", "Nome", "Tipo", "Acessos"}
    );
    /**
     * Creates new form GUIPesquisar
     */
    public GUIPesquisar() {
        initComponents();
        prencherTabela();
    }
    
    private void prencherTabela(){
        limparTabela();
         try {
            
             JogoServicos js = new servicos.ServicosFactory().getJogoServicos();

            ArrayList<jogoVO> crod = new ArrayList<>();
            
            
            crod = js.mostrarJogos();
            
            for ( int i = 0; i < crod.size(); i++) {
                dtm.addRow(new String[] { 
                    String.valueOf(crod.get(i).getIdJogo()),
                    String.valueOf(crod.get(i).getNome() ),
                    String.valueOf(crod.get(i).getTipo()),
                    String.valueOf(crod.get(i).getAcessos()),
                });
            }
            jtTabela.setModel(dtm);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERRO nO GUICOLABORADOR" + e.getMessage()  );
        }
    }
    private void filtarJogos() throws SQLException{
        if(jtfPesquisar.getText().isEmpty()){
            prencherTabela();
        }else{
            String pesquisa = (String) jcClasses.getSelectedItem();
            String query = null;
            
            if(pesquisa.equals("Id")){
                query = "where idJogo =  " + jtfPesquisar.getText();
                }else if( pesquisa.equals("Nome")){
                    query = "where nome like '%" + jtfPesquisar.getText() + "%' ";
                }else if(pesquisa.equals("Tipo")){
                    query = "where tipo like '%" + jtfPesquisar.getText() + "%' ";
                }
             ArrayList<jogoVO> prod = new ArrayList<>();
                
                //Recebendo o ArrayList cheio no produto
                JogoServicos js = new servicos.ServicosFactory().getJogoServicos();
                prod = js.filtarJogos(query);
                
                for( int i = 0; i < prod.size(); i++){
                    dtm.addRow(new String[] {
                        String.valueOf(prod.get(i).getIdJogo()),
                        String.valueOf(prod.get(i).getNome() ),
                        String.valueOf(prod.get(i).getTipo()),
                        String.valueOf(prod.get(i).getAcessos()),
                    });
                }//fecha o laço for
                
                //Adicionando o modelo de tablea com os dados na tabela jtProduto
                jtTabela.setModel(dtm);
        }
    
    }
    private void acessosJogo() throws SQLException{
        String query = null;
        boolean pular = false;
        
        JogoServicos js = new servicos.ServicosFactory().getJogoServicos();
        if(jcMaisPopular.isSelected()){
            query = "DESC";
        }else if(jcMenosPopular.isSelected()){
            query = "ASC";
        }else{
            pular = true;
        }
        if(!pular){
            limparTabela();
            ArrayList<jogoVO> prod = new ArrayList<>();
            prod = js.acessoJogos(query);
            
            for( int i = 0; i < prod.size(); i++){
                    dtm.addRow(new String[] {
                        String.valueOf(prod.get(i).getIdJogo()),
                        String.valueOf(prod.get(i).getNome() ),
                        String.valueOf(prod.get(i).getTipo()),
                        String.valueOf(prod.get(i).getAcessos()),
                    });
                }//fecha o laço for
                
                //Adicionando o modelo de tablea com os dados na tabela jtProduto
                jtTabela.setModel(dtm);
        }else{
            limparTabela();
            prencherTabela();
        }
    }
    
    private void limparTabela(){
        dtm.setNumRows(0);
    }
    
    private void irJogo() throws SQLException{
         selecionarJogo slcJogo = new selecionarJogo();
         slcJogo.botarJogo(jtfIrJogo.getText());
         slcJogo.acessoJogos(jtfIrJogo.getText());
      
        
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtfPesquisar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jcClasses = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jtfIrJogo = new javax.swing.JTextField();
        jbtIR = new javax.swing.JButton();
        jcMaisPopular = new javax.swing.JCheckBox();
        jcMenosPopular = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTabela = new javax.swing.JTable();
        jcbOrdemA = new javax.swing.JCheckBox();
        jcbOrdemB = new javax.swing.JCheckBox();
        jcbTipo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jtfPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfPesquisarActionPerformed(evt);
            }
        });
        jtfPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfPesquisarKeyReleased(evt);
            }
        });

        jLabel2.setText("Pesquisar");

        jcClasses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Id", "Nome", "Tipo" }));
        jcClasses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcClassesActionPerformed(evt);
            }
        });

        jLabel1.setText("Insira o Id: ");

        jbtIR.setText("Ir");
        jbtIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtIRActionPerformed(evt);
            }
        });

        jcMaisPopular.setText("Mais Popular");
        jcMaisPopular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcMaisPopularActionPerformed(evt);
            }
        });

        jcMenosPopular.setText("Menos Popular");
        jcMenosPopular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcMenosPopularActionPerformed(evt);
            }
        });

        jtTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Tipo", "Acessos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtTabela.setToolTipText("");
        jScrollPane1.setViewportView(jtTabela);

        jcbOrdemA.setText("A-Z");
        jcbOrdemA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbOrdemAActionPerformed(evt);
            }
        });

        jcbOrdemB.setText("Z-A");
        jcbOrdemB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbOrdemBActionPerformed(evt);
            }
        });

        jcbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tipo", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jcMaisPopular)
                        .addGap(18, 18, 18)
                        .addComponent(jcMenosPopular)
                        .addGap(18, 18, 18)
                        .addComponent(jcbOrdemA)
                        .addGap(18, 18, 18)
                        .addComponent(jcbOrdemB)
                        .addGap(18, 18, 18)
                        .addComponent(jcbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(27, 27, 27)
                            .addComponent(jcClasses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(38, 38, 38)
                            .addComponent(jtfPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jtfIrJogo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtIR)
                .addGap(187, 187, 187))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jcClasses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcMaisPopular)
                    .addComponent(jcMenosPopular)
                    .addComponent(jcbOrdemA)
                    .addComponent(jcbOrdemB)
                    .addComponent(jcbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtIR)
                    .addComponent(jtfIrJogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(70, 70, 70))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfPesquisarActionPerformed

    private void jcClassesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcClassesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcClassesActionPerformed

    private void jcMaisPopularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcMaisPopularActionPerformed
      
            jcMenosPopular.setSelected(false);
            jcbOrdemA.setSelected(false);
            jcbOrdemB.setSelected(false);
        try {
            
            acessosJogo();
        } catch (SQLException ex) {
            Logger.getLogger(GUIPesquisar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jcMaisPopularActionPerformed

    private void jcMenosPopularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcMenosPopularActionPerformed
      
            jcMaisPopular.setSelected(false);
            jcbOrdemA.setSelected(false);
            jcbOrdemB.setSelected(false);
        
      try {
      
            acessosJogo();
        } catch (SQLException ex) {
            Logger.getLogger(GUIPesquisar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jcMenosPopularActionPerformed

    private void jbtIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtIRActionPerformed
        try {
            irJogo();
        } catch (SQLException ex) {
            Logger.getLogger(GUIPesquisar.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }//GEN-LAST:event_jbtIRActionPerformed

    private void jtfPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfPesquisarKeyReleased
        try {
            limparTabela();
            filtarJogos();
        } catch (SQLException ex) {
            Logger.getLogger(GUIPesquisar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jtfPesquisarKeyReleased

    private void jcbOrdemAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbOrdemAActionPerformed
        jcMenosPopular.setSelected(false);
        jcMaisPopular.setSelected(false);
        jcbOrdemB.setSelected(false);
        
        try {
      
            acessosJogo();
        } catch (SQLException ex) {
            Logger.getLogger(GUIPesquisar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jcbOrdemAActionPerformed

    private void jcbOrdemBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbOrdemBActionPerformed
        jcMenosPopular.setSelected(false);
        jcMaisPopular.setSelected(false);
        jcbOrdemA.setSelected(false);
        
        try {
      
            acessosJogo();
        } catch (SQLException ex) {
            Logger.getLogger(GUIPesquisar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jcbOrdemBActionPerformed

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
            java.util.logging.Logger.getLogger(GUIPesquisar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIPesquisar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIPesquisar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIPesquisar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIPesquisar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtIR;
    private javax.swing.JComboBox<String> jcClasses;
    private javax.swing.JCheckBox jcMaisPopular;
    private javax.swing.JCheckBox jcMenosPopular;
    private javax.swing.JCheckBox jcbOrdemA;
    private javax.swing.JCheckBox jcbOrdemB;
    private javax.swing.JComboBox<String> jcbTipo;
    private javax.swing.JTable jtTabela;
    private javax.swing.JTextField jtfIrJogo;
    private javax.swing.JTextField jtfPesquisar;
    // End of variables declaration//GEN-END:variables
}
