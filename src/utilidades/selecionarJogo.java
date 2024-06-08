/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;


import DAO.hostDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jogoSimples.Wordles;
import jogoSimples.guessNumber;
import jogoSimples.snake;
import servicos.HostServicos;

/**
 *
 * @author 182220058
 */
public class selecionarJogo {
    public void acessoJogos(String idJogo) throws SQLException{
     hostDAO hdao = new hostDAO();
     
        if(idJogo.equals("1") && hdao.verificarConexao()){
            
            
            
            
        
            guessNumber gn = new guessNumber();
         try {
              
             gn.game();
            
         } catch (InterruptedException ex) {
             Logger.getLogger(selecionarJogo.class.getName()).log(Level.SEVERE, null, ex);
         }
        }else if(idJogo.equals("2")){
      Wordles wo = new Wordles();
      wo.iniciar();
        }else if(idJogo.equals("3")){
        snake sn = new snake();
        sn.main();
        }
        else{
            JOptionPane.showMessageDialog(null, "Id não existe ou o jogo que está acessando é multiplayer");
        }
    }
    public void botarJogo(String ifJogo)throws SQLException{
        HostServicos hs = new servicos.ServicosFactory().getHostServicos();
        hs.botarJogo(ifJogo);
    }
}
