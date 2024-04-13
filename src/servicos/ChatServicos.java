/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import DAO.DAOFactory;
import DAO.chatDAO;
import java.sql.SQLException;

/**
 *
 * @author 182220058
 */
public class ChatServicos {
    public void criarConexao() throws SQLException{
        chatDAO cdao = new DAOFactory().getChatDAO();
        cdao.criarConexao();
       
    }
    
    public void mandarMensagem(String mensagem) throws SQLException{
        chatDAO cdao = new DAOFactory().getChatDAO();
        cdao.mandarMensagem(mensagem);
    }
    
    public String[] verMensagem()throws SQLException{
        chatDAO cdao = new DAOFactory().getChatDAO();
        return cdao.verMensagem();
    }
}
