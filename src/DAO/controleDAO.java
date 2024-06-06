/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VO.controleVO;
import VO.loginVO;
import VO.postVO;
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
public class controleDAO {
    public ResultSet login(loginVO LVO) throws SQLException {
        //Buscar a conexão com o banco de dados
     Connection con = new ConexaoBanco().getConexao();
        
        //Criar um objeto que seja capas de Executar instruções SQL
        //no Banco de Dados
        try {
            //String que receberá a instrução SQL
            String sql;
            //Montando a instrução SELECT para buscar um objeto lVO no Banco mysql
            sql = "select * from login where email=? and senhaPais=?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, LVO.getEm());
            pstm.setString(2, LVO.getSenhaPais());
            
            //Executando o SQL
            ResultSet rs = pstm.executeQuery();
            
            return rs;
            
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "LoginDAO! " + se);
            return null;
        }

    }
       public void aplicarConfig( String [] info)throws SQLException{
        Connection con = new ConexaoBanco().getConexao();
         
         System.out.println(info[0]+" : "+info[2] + " : "+ info[1]+" : "+loginVO.em);
        try {
           
           
            String sql = "Update login set "
                   
                    + "maxTempo = " + info[0]+ ", "
                    + "chat = " + info[2]+ ", "
                    + "multiplayer = " + info[1]+ " "
                    + " where  email= '" + loginVO.em+ "'";
            
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.executeUpdate();
            pstm.close();
            
            
        } catch (SQLException se) {
            throw  new SQLException("Erro ao alterar as configurações controleDAO " + se.getMessage());
        }finally{
            con.close();
        }
    
    }
        public ArrayList<controleVO> mostrarInfo() throws SQLException {
        //Buscar a conexão com o banco de dados
     Connection con = new ConexaoBanco().getConexao();
        
        //Criar um objeto que seja capas de Executar instruções SQL
        //no Banco de Dados
        try {
            //String que receberá a instrução SQL
            String sql;
            //Montando a instrução SELECT para buscar um objeto lVO no Banco mysql
            sql = "select * from login where email=?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, loginVO.getEm());
            
            
            //Executando o SQL
            ResultSet rs = pstm.executeQuery();
            ArrayList<controleVO> pro = new ArrayList<>();

            while (rs.next()) {
                controleVO CVO = new controleVO();
                CVO.tempoMax = rs.getInt("maxTempo");
                CVO.chat = rs.getBoolean("chat");
                CVO.multiplayer = rs.getBoolean("multiplayer");
                CVO.tempoAtual = rs.getInt("atualTempo");
                CVO.dia = rs.getInt("dia");

                pro.add(CVO);
            }//fim do while
            return pro;
            
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "LoginDAO! " + se);
            return null;
        }

    }
        public boolean atualizarTempo(int tempo)throws SQLException{
        Connection con = new ConexaoBanco().getConexao();
         
         
        try {
           
           
            String sql = "Update login set "
                    + "atualTempo = " + tempo+ " "
                    + " where  email= '" + loginVO.em+ "'";
            
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.executeUpdate();
            pstm.close();
            
            
        } catch (SQLException se) {
            throw  new SQLException("Erro ao atualizar o tempo atual controleDAO " + se.getMessage());
        }finally{
            con.close();
        }
        return false;
    
    }
         
        
       
}
