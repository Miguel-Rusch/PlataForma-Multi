/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import DAO.postDAO;
import VO.postVO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author 182220058
 */
public class PostServicos {
    
    public ArrayList<postVO> mostrarPost() throws SQLException{
        postDAO pdao = new DAO.DAOFactory().getPostDAO();
        return pdao.mostrarPost();
    }
    
    public ArrayList<postVO> filtarPost(String query) throws SQLException{
        postDAO pdao = new DAO.DAOFactory().getPostDAO();
        return pdao.filtarPost(query);
    }
    
    public int postar(postVO PVO) throws SQLException{
        postDAO pdao = new DAO.DAOFactory().getPostDAO();
        return pdao.postar(PVO);
    }
    
    public void deletarPost(int idPost) throws SQLException{
        postDAO pdao = new DAO.DAOFactory().getPostDAO();
        pdao.deletarPost(idPost);
    }
    
    public String permitirDeletarPost(int idPost) throws SQLException{
        postDAO pdao = new DAO.DAOFactory().getPostDAO();
        return pdao.permitirDeletarPost(idPost);
    }
}
