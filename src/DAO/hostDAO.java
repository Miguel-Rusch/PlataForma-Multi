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
            String sql = "INSERT INTO host VALUES (null, false, null, null,null,?,?,false,false)";

            PreparedStatement pstm = con.prepareStatement(sql);
             pstm.setString(1, lvo.getEm());
             pstm.setString(2, senhaHost);

            pstm.execute();
            pstm.close();
            
        } catch (SQLException se) {
            throw new SQLException("Erro em criar um host!" + se.getMessage());
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
         hostVO hvo = new hostVO();
        
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
               // desconectarHost();
                criarHostParaconectarcomHost(host);
            }
            hvo.hostConect = host;
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
            String sql = "INSERT INTO host VALUES (null, true, null, null,?,?,null,false,false)";

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
        
    public void registrarJogada(String mensagem) throws SQLException {
        
        
        Connection con = new ConexaoBanco().getConexao();
        loginVO lvo = new loginVO();
        try {
            String sql = "update host set vistoMensagem = true,jogada = ? where idHost = ?";
            //update host set conect = true,idHostConect = ? 
            PreparedStatement pstm = con.prepareStatement(sql);
             pstm.setString(1, mensagem);
             pstm.setInt(2, mostrarHost());

            pstm.execute();
            pstm.close();
            
        } catch (SQLException se) {
            throw new SQLException("Erro em registar a jogada!" + se.getMessage());
        } finally {
            con.close();
        }//fim do try catch finally

    }//   
    public void botarJogo(String idJogo) throws SQLException{
        
        Connection con = new ConexaoBanco().getConexao();
    
        try {
            
            String sql = "update host set vistoJogo = true,jogo = ? where idHost = ?";
            //update host set conect = true,idHostConect = ? 
            PreparedStatement pstm = con.prepareStatement(sql);
             pstm.setString(1, idJogo);
             pstm.setInt(2, mostrarHost());

            pstm.execute();
            pstm.close();
            
        } catch (SQLException se) {
            throw new SQLException("Erro em botar o jogo para o outro!" + se.getMessage());
        } finally {
            con.close();
        }//fim do try catch finally
    }
    
    
      public boolean verificarConexao()throws SQLException{
        Connection con = new ConexaoBanco().getConexao();
        hostVO hvo = new hostVO();
        try {
        
            String sql = "select * from host where idHost like '"+mostrarHost()+"'";

            PreparedStatement pstm = con.prepareStatement(sql);
             

           ResultSet rs = pstm.executeQuery();
           boolean conexao = false;
           int hostConect = 0;
           while(rs.next()){
            conexao = rs.getBoolean("conect");
            hostConect = rs.getInt("idHostConect");
           }
           hvo.hostConect = hostConect;
           pstm.close();
           return conexao;
          
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao ver o host!"+se.getMessage());
        } finally {
            con.close();
        }//fim do try catch finally
      }
      
    public String[] verJogo() throws SQLException{
       
        Connection con = new ConexaoBanco().getConexao();
        hostVO hvo = new hostVO();
        try {
        
            String sql = "select * from host where idHost like '"+hvo.hostConect+"'";

            PreparedStatement pstm = con.prepareStatement(sql);
             

           ResultSet rs = pstm.executeQuery();
           
           String[] resultado = new String[8];
           
           
           boolean vistoJogo = false;
          
           String jogo = "";
           while(rs.next()){
           
             vistoJogo = rs.getBoolean("vistoJogo");
            jogo =  rs.getString("jogo");
           }
          String sq = "update host set vistoJogo = false where idHost like '" + hvo.hostConect+"'";   
            PreparedStatement pst = con.prepareStatement(sq);
            
            
            
            
            pst.execute();
            pst.close();
           
           if(vistoJogo){
            
            resultado[0] = "true";
            resultado[1] = jogo;
           }else{
           resultado[0] = "false";    
           resultado[1] = "";
           }
    
           return resultado;
        
          
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao ver a mensagem!"+se.getMessage());
        } finally {
            con.close();
        }//fim do try catch finally
    }  
    
    public String[] verMensagem() throws SQLException{
       
        Connection con = new ConexaoBanco().getConexao();
        hostVO hvo = new hostVO();
        try {
            System.out.println(hvo.hostConect + " 301 hostDAO");
            String sql = "select * from host where idHost like '"+hvo.hostConect+"'";

            PreparedStatement pstm = con.prepareStatement(sql);
             

           ResultSet rs = pstm.executeQuery();
           
           String[] resultado = new String[8];
           
           boolean vistoMensagem = false;
           
           String mensagem = "";
           
           while(rs.next()){
            vistoMensagem = rs.getBoolean("vistoMensagem");
             mensagem = rs.getString("jogada");
             
            
           }
           if(vistoMensagem){
            String sq = "update host set vistoMensagem = false where idHost = ?";   
            PreparedStatement pst = con.prepareStatement(sq);
               System.out.println("é falso");
            pst.setInt(1, hvo.hostConect);
            
            
            pst.execute();
            pst.close();
            resultado[0] = mensagem;
            resultado[1] = "true";
           }else{
           resultado[0] = "";
           resultado[1] = "false";
           }
           
          
    
           return resultado;
        
          
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao ver a mensagem!"+se.getMessage());
        } finally {
            con.close();
        }//fim do try catch finally
    }  
}
