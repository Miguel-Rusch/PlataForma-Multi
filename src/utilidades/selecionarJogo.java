/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jogoSimples.guessNumber;
import servicos.HostServicos;

/**
 *
 * @author 182220058
 */
public class selecionarJogo {
    public void acessoJogos(String idJogo) throws SQLException{
     
        if(idJogo.equals("3")){
            
            
            
            HostServicos hs = new servicos.ServicosFactory().getHostServicos();
            hs.botarJogo("3");
        
            guessNumber gn = new guessNumber();
         try {
              System.out.println("11111111111111");
             gn.game();
            
         } catch (InterruptedException ex) {
             Logger.getLogger(selecionarJogo.class.getName()).log(Level.SEVERE, null, ex);
         }
        }else{
            JOptionPane.showMessageDialog(null, "Id não existe");
        }
    }
}
