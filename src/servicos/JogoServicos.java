/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import DAO.DAOFactory;
import DAO.jogoDAO;
import VO.jogoVO;
import java.sql.SQLException;
import java.util.ArrayList;



/**
 *
 * @author 182220058
 */
public class JogoServicos {
    
   public ArrayList<jogoVO> mostrarJogos() throws SQLException{
       jogoDAO jdao = new DAOFactory().getJogoDAO();
       return jdao.mostrarJogos();
   }
   
   public ArrayList<jogoVO> filtarJogos(String query) throws SQLException{
       jogoDAO jdao = new DAOFactory().getJogoDAO();
       return jdao.filtarJogos(query);
   }
   
   public ArrayList<jogoVO> acessoJogos(String query) throws SQLException{
       jogoDAO jdao = new DAOFactory().getJogoDAO();
       return jdao.acessoJogos(query);
   }
}
