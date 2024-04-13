/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VO.chatVO;
import VO.hostVO;
import VO.loginVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import persistenci.ConexaoBanco;
import servicos.HostServicos;

/**
 *
 * @author 182220058
 */
public class chatDAO {
    public void criarConexao()throws SQLException{
        Connection con = new ConexaoBanco().getConexao();
        loginVO lvo = new loginVO();
        hostVO hvo = new hostVO();
        try {
            String sql = "INSERT INTO chat VALUES (null, ?, ?,null,0)";

            PreparedStatement pstm = con.prepareStatement(sql);
            HostServicos hs = new servicos.ServicosFactory().getHostServicos();
             pstm.setInt(1, hs.mostrarHost());
             pstm.setInt(2,hvo.hostConect);
             

            pstm.execute();
            pstm.close();
            
        } catch (SQLException se) {
            throw new SQLException("Erro em criar um host!" + se.getMessage());
        } finally {
            con.close();
        }//fim do try catch finally
    }
   
    
    
    public void mandarMensagem(String mensagem)throws SQLException{
        Connection con = new ConexaoBanco().getConexao();
        loginVO lvo = new loginVO();
        hostVO hvo = new hostVO();
        chatVO cvo = new chatVO();
        try {
            String sql = "update chat set mensagem = ?,idNUmMensagem = 1 where idChat = ?";

            PreparedStatement pstm = con.prepareStatement(sql);
            HostServicos hs = new servicos.ServicosFactory().getHostServicos();
             pstm.setInt(2, acharId(1));
             pstm.setString(1,mensagem);
             
             

            pstm.execute();
            pstm.close();
            
        } catch (SQLException se) {
            throw new SQLException("Erro em criar um host!" + se.getMessage());
        } finally {
            con.close();
        }//fim do try catch finally
    }
    
    public int acharId(int i)throws SQLException{
        Connection con = new ConexaoBanco().getConexao();
        hostVO hvo = new hostVO();
        try {
            HostServicos hs = new servicos.ServicosFactory().getHostServicos();
            String sql = "select * from chat where idHost = ? and idHostConect = ?";

            PreparedStatement pstm = con.prepareStatement(sql);
            if(i == 1){
             pstm.setInt(1, hs.mostrarHost());
             pstm.setInt(2, hvo.hostConect);
            }else{
             pstm.setInt(2, hs.mostrarHost());
             pstm.setInt(1, hvo.hostConect);
            }

           ResultSet rs = pstm.executeQuery();
           int host = 0;
           while(rs.next()){
            host = rs.getInt("idChat");
           }
    
           pstm.close();
           return host;
          
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao ver o host!"+se.getMessage());
        } finally {
            con.close();
        }//fim do try catch finally
    }
     public String[] verMensagem() throws SQLException{
       
        Connection con = new ConexaoBanco().getConexao();
        hostVO hvo = new hostVO();
        try {
            
            String sql = "select * from chat where idchat = "+acharId(2);

            PreparedStatement pstm = con.prepareStatement(sql);
             

           ResultSet rs = pstm.executeQuery();
           
           String[] resultado = new String[8];
           
            int vistoMensagem = 0;
           
           String mensagem = "";
           
           while(rs.next()){
            vistoMensagem = rs.getInt("idNumMensagem");
             mensagem = rs.getString("mensagem");
             
            
           }
           if(vistoMensagem == 1){
            String sq = "update chat set idNumMensagem = 0 where idChat = ?";   
            PreparedStatement pst = con.prepareStatement(sq);
               System.out.println("é falso");
            pst.setInt(1, acharId(2));
            
            
            pst.execute();
            pst.close();
            resultado[0] = mensagem;
               System.out.println(mensagem);
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
//     public void fecharChat()throws SQLException{
//         Connection con = new ConexaoBanco().getConexao();
//        loginVO lvo = new loginVO();
//        
//        try {
//            String sql = "delete from chat where idChat = ?";
//            System.out.println("ssssssssssssssssssssssssssssssssssssssssss");
//            PreparedStatement pstm = con.prepareStatement(sql);
//             pstm.setString(1,acharId(0)+"");
//             System.out.println(acharId(0));
//
//            pstm.execute();
//            pstm.close();
//            
//        } catch (SQLException se) {
//            throw new SQLException("Erro em desconectar um host!");
//        } finally {
//            con.close();
//        }//fim do try catch finally
//    }
}
