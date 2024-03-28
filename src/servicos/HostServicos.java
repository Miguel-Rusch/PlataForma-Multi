/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import DAO.DAOFactory;
import DAO.hostDAO;
import java.sql.SQLException;

/**
 *
 * @author 182220058
 */
public class HostServicos {
     public void criarHost() throws SQLException{
        hostDAO hdao = new DAOFactory().getHostDAO();
        hdao.criarHost();
    }
    
    public int mostrarHost() throws SQLException{
        hostDAO hdao = new DAOFactory().getHostDAO();
        return hdao.mostrarHost();
    }
    
    public boolean conectarHost(int host) throws SQLException{
        hostDAO hdao = new DAOFactory().getHostDAO();
        return hdao.conectarHost(host);
    }
    
    public void desconectarHost() throws SQLException{
        hostDAO hdao = new DAOFactory().getHostDAO();
        hdao.desconectarHost();
    }
    
    public void criarHostParaconectarcomHost(int host) throws SQLException{
        hostDAO hdao = new DAOFactory().getHostDAO();
        hdao.criarHostParaconectarcomHost(host);
    }
    
    public void registrarJogada(String mensagem) throws SQLException{
        hostDAO hdao = new DAOFactory().getHostDAO();
        hdao.registrarJogada(mensagem);
    }
    
    public void botarJogo(String idJogo) throws SQLException{
        hostDAO hdao = new DAOFactory().getHostDAO();
        hdao.botarJogo(idJogo);
    }
    
    public boolean verificarConexao() throws SQLException{
        hostDAO hdao = new DAOFactory().getHostDAO();
        return hdao.verificarConexao();
    }
    
    public String verJogo() throws SQLException{
        hostDAO hdao = new DAOFactory().getHostDAO();
        return hdao.verJogo();
    }
    
    public String verMensagem() throws SQLException{
        hostDAO hdao = new DAOFactory().getHostDAO();
        return hdao.verMensagem();
    }
}
