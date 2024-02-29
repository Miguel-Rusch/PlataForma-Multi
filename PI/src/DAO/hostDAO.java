/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VO.hostVO;
import VO.loginVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import persistenci.ConexaoBanco;

/**
 *
 * @author 182220058
 */
public class hostDAO {
    public void criarHost() throws SQLException {
        Connection con = new ConexaoBanco().getConexao();
        loginVO lvo = new loginVO();
        try {
            String sql = "INSERT INTO host VALUES (null, false, null, null,null,?)";

            PreparedStatement pstm = con.prepareStatement(sql);
             pstm.setString(1, lvo.getEm());

            pstm.execute();
            pstm.close();
            
        } catch (SQLException se) {
            throw new SQLException("Erro em criar um host!");
        } finally {
            con.close();
        }//fim do try catch finally

    }//
    public int mostrarHost()throws SQLException{
        Connection con = new ConexaoBanco().getConexao();
        loginVO lvo = new loginVO();
        try {
        
            String sql = "select * from host where idUsuario like '"+lvo.getEm()+"'";

            PreparedStatement pstm = con.prepareStatement(sql);
             

           ResultSet rs = pstm.executeQuery();
           int host = 0;
           while(rs.next()){
            host = rs.getInt("idHost");
           }
    
           pstm.close();
           return host;
          
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao ver o host!"+se.getMessage());
        } finally {
            con.close();
        }//fim do try catch finally
        
    
    }
    public void conectarHost(int host)throws SQLException{
         Connection con = new ConexaoBanco().getConexao();
        loginVO lvo = new loginVO();
        try {
            String sql = "update host set conect = true,idHostConect = ? where idHost = ?";

            PreparedStatement pstm = con.prepareStatement(sql);
             pstm.setString(1, mostrarHost()+"");
             pstm.setString(2, host+"");

            pstm.execute();
            pstm.close();
            
        } catch (SQLException se) {
            throw new SQLException("Erro em criar um host!");
        } finally {
            con.close();
        }//fim do try catch finally
    }
        public void desconectarHost()throws SQLException{
         Connection con = new ConexaoBanco().getConexao();
        loginVO lvo = new loginVO();
        try {
            String sql = "update host set conect = false,idHostConect = 0 where idHost = ?";

            PreparedStatement pstm = con.prepareStatement(sql);
             pstm.setString(1, mostrarHost()+"");
             

            pstm.execute();
            pstm.close();
            
        } catch (SQLException se) {
            throw new SQLException("Erro em desconectar um host!");
        } finally {
            con.close();
        }//fim do try catch finally
    }

    
}
