/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VO.jogoVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import persistenci.ConexaoBanco;

/**
 *
 * @author 182220058
 */
public class jogoDAO {
     public ArrayList<jogoVO> mostrarJogos() throws SQLException {
        Connection con = new ConexaoBanco().getConexao();

        try {
            String sql = "Select * from jogo";
            PreparedStatement pstm = con.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();
            ArrayList<jogoVO> pro = new ArrayList<>();

            while (rs.next()) {
                jogoVO JVO = new jogoVO();

                JVO.setIdJogo(rs.getInt("idJogo"));
                JVO.setNome(rs.getString("nome"));
                JVO.setTipo(rs.getString("tipo"));
                JVO.setAcessos(rs.getInt("acessos"));

                pro.add(JVO);
            }//fim do while
            pstm.close();

            return pro;

        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar Produto! " + se.getMessage());
        } finally {
            con.close();
        }
    }//fim do método buscarProduto
     
     public ArrayList<jogoVO> filtarJogos(String query,String quer) throws SQLException {
        Connection con = new ConexaoBanco().getConexao();

        try {
            String sql = "Select * from jogo " + query + " " + quer;
            //query
            PreparedStatement pstm = con.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();
            ArrayList<jogoVO> pro = new ArrayList<>();

            while (rs.next()) {
                jogoVO JVO = new jogoVO();

                JVO.setIdJogo(rs.getInt("idJogo"));
                JVO.setNome(rs.getString("nome"));
                JVO.setTipo(rs.getString("tipo"));
                JVO.setAcessos(rs.getInt("acessos"));

                pro.add(JVO);
            }//fim do while
            pstm.close();

            return pro;

        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar jogo! " + se.getMessage());
        } finally {
            con.close();
        }
    }//fim do método buscarProduto
     
     public ResultSet jogoComboBox() throws SQLException{
         
         Connection con = new ConexaoBanco().getConexao();

         try {
             String sql = "Select * from jogo order by nome; ";
             PreparedStatement pstm = con.prepareStatement(sql);
             
             return pstm.executeQuery();
            
        } catch (SQLException se) {
             JOptionPane.showMessageDialog(
                    null,
                    "Erro jogoComboBox " + se.getMessage() );
             return null;
        }//fim do try catch
     }
    
     
     

    
}
