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
import javax.swing.JOptionPane;
import persistenci.ConexaoBanco;
import view.GUIHost;

/**
 *
 * @author 182220058
 */
public class hostDAO {
    public void criarHost() throws SQLException {
        
        String senhaHost = JOptionPane.showInputDialog("Digite uma senha para o seu host!");
        Connection con = new ConexaoBanco().getConexao();
        loginVO lvo = new loginVO();
        try {
            String sql = "INSERT INTO host VALUES (null, false, null, null,null,?,?)";

            PreparedStatement pstm = con.prepareStatement(sql);
             pstm.setString(1, lvo.getEm());
             pstm.setString(2, senhaHost);

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
    public boolean conectarHost(int host)throws SQLException{
         Connection con = new ConexaoBanco().getConexao();
        loginVO lvo = new loginVO();
        
         String senhaHost = JOptionPane.showInputDialog("Digite uma senha:");
        try {
            //Pega a senha
            String sqll = "select * from host where idHost = ?";

            PreparedStatement pst = con.prepareStatement(sqll);
            pst.setString(1, host+"");
             

           ResultSet rs = pst.executeQuery();
           String sen = null;
           while(rs.next()){
            sen = rs.getString("senha");
           }
            if(!sen.equals(senhaHost)){
            JOptionPane.showMessageDialog(null, "Host ou senha estão incorretos");
            }else{
                //Faz o host se conectar
            String sql = "update host set conect = true,idHostConect = ? where idHost = ? and senha = ?";
            
            if(mostrarHost() == 0){
                criarHostParaconectarcomHost(host);
            }else{
                desconectarHost();
                criarHostParaconectarcomHost(host);
            }
            
            PreparedStatement pstm = con.prepareStatement(sql);
             pstm.setString(1, mostrarHost()+"");
             pstm.setString(2, host+"");
             pstm.setString(3, senhaHost);

            pstm.execute();
            pstm.close();

            
            
            JOptionPane.showMessageDialog(null,"Conectado com sucesso!");
            return true;
            }
            return false;
        } catch (SQLException se) {
            throw new SQLException("Erro em se conectar um host!");
        } finally {
            con.close();
        }//fim do try catch finally
    }
    
        public void desconectarHost()throws SQLException{
         Connection con = new ConexaoBanco().getConexao();
        loginVO lvo = new loginVO();
        try {
            String sql = "delete from host where idHost = ?";

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
        public void criarHostParaconectarcomHost(int host) throws SQLException {
        
        
        Connection con = new ConexaoBanco().getConexao();
        loginVO lvo = new loginVO();
        try {
            String sql = "INSERT INTO host VALUES (null, true, null, null,?,?,null)";

            PreparedStatement pstm = con.prepareStatement(sql);
             pstm.setString(1, host+"");
             pstm.setString(2, lvo.getEm());
            

            pstm.execute();
            pstm.close();
            
        } catch (SQLException se) {
            throw new SQLException("Erro em criar um host!");
        } finally {
            con.close();
        }//fim do try catch finally

    }//
        
       

    
}
