/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VO.jogoVO;
import VO.loginVO;
import VO.postVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import persistenci.ConexaoBanco;

/**
 *
 * @author 182220058
 */
public class postDAO {
    public ArrayList<postVO> mostrarPost() throws SQLException {
        Connection con = new ConexaoBanco().getConexao();

        try {
            String sql = "Select * from post";
            PreparedStatement pstm = con.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();
            ArrayList<postVO> pro = new ArrayList<>();

            while (rs.next()) {
                postVO PVO = new postVO();

                PVO.setIdPost(rs.getInt("idPost"));
                PVO.setUsuario(rs.getString("usuario"));
                PVO.setJogo(rs.getString("jogo"));
                PVO.setHost(rs.getInt("host"));
                PVO.setComentario(rs.getString("comentario"));

                pro.add(PVO);
            }//fim do while
            pstm.close();

            return pro;

        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar posts! " + se.getMessage());
        } finally {
            con.close();
        }
    }
    public ArrayList<postVO> filtarPost(String query) throws SQLException {
        Connection con = new ConexaoBanco().getConexao();

        try {
            String sql = "Select * from post " + query;
            //query
            PreparedStatement pstm = con.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();
            ArrayList<postVO> pro = new ArrayList<>();

            while (rs.next()) {
                postVO PVO = new postVO();

                
                PVO.setUsuario(rs.getString("usuario"));
                PVO.setHost(rs.getInt("host"));
                PVO.setComentario(rs.getString("comentario"));
                PVO.setJogo(rs.getString("jogo"));
                

                pro.add(PVO);
            }//fim do while
            pstm.close();

            return pro;

        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar posts! " + se.getMessage());
        } finally {
            con.close();
        }
    }
    public int postar(postVO PVO) throws SQLException {
        Connection con = new ConexaoBanco().getConexao();
           

        try {
        loginVO LVO = new loginVO();
         String sql1 = "select * from login where email like '"+LVO.getEm() +"'";
            PreparedStatement pst = con.prepareStatement(sql1);
            ResultSet rs = pst.executeQuery();
            String usu = null;
            while(rs.next()){
            usu = rs.getString("usuario");
            System.out.println(usu);
            }
            
            String sql = "INSERT INTO post VALUES (null, ?, ?, ?, ?,?)";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, usu);
            pstm.setInt(2, 2220);
            pstm.setString(3, PVO.getComentario());
            pstm.setString(4, PVO.getJogo());
            pstm.setString(5, LVO.getEm());

            pstm.execute();
            pstm.close();
            
           
         String sql9 = "select * from post where email like '"+LVO.getEm() +"'";
            PreparedStatement psts = con.prepareStatement(sql9);
            ResultSet rss = psts.executeQuery();
            int idPost = 0;
            while(rss.next()){
            idPost =Integer.parseInt(rss.getString("idPost"));
            System.out.println(idPost);
            }
            return idPost;

        } catch (SQLException se) {
            throw new SQLException("Erro em postar o post!" + se.getMessage());
        } finally {
            con.close();
        }//fim do try catch finally

    }//
    public void deletarPost(int idPost) throws SQLException {
        Connection con = new ConexaoBanco().getConexao();

        try {
            String sql = "delete from post where idPost = ?";
            PreparedStatement pstm = con.prepareStatement(sql);

            pstm.setInt(1, idPost);
            pstm.execute();
            pstm.close();
        } catch (SQLException se) {
            throw new SQLException("Erro ao deletar post! PostDAO " + se.getMessage());
        } finally {
            con.close();
        }//fim da finally
    }
    public String permitirDeletarPost(int idPost) throws SQLException{
    Connection con = new ConexaoBanco().getConexao();

        try {
            String sql = "select * from post where idPost = ?";
            PreparedStatement pstm = con.prepareStatement(sql);

            pstm.setLong(1, idPost);
            pstm.execute();
             ResultSet rs = pstm.executeQuery();
             String rsEm = null;
              while (rs.next()) {
               rsEm = rs.getString("email");
                      }
            return rsEm;
         
        } catch (SQLException se) {
            throw new SQLException("Erro ao deletar post! PostDAO " + se.getMessage());
        }
    }
}
