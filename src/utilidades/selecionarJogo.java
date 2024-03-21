/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import DAO.hostDAO;
import VO.hostVO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jogoSimples.guessNumber;

/**
 *
 * @author 182220058
 */
public class selecionarJogo {
    public void acessoJogos(String idJogo) throws SQLException{
     hostVO hvo = new hostVO();
        if(idJogo.equals("3")){
            
          
            hostDAO hdao = new hostDAO();
            hdao.botarJogo("3");
        
            guessNumber gn = new guessNumber();
         try {
             gn.game();
         } catch (InterruptedException ex) {
             Logger.getLogger(selecionarJogo.class.getName()).log(Level.SEVERE, null, ex);
         }
        }else{
            JOptionPane.showMessageDialog(null, "Id não existe");
        }
    }
}
