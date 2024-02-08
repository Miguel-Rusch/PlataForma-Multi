/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VO.loginVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import persistenci.ConexaoBanco;

/**
 *
 * @author 182220058
 */
public class loginDAO {
    public void cadastrar(loginVO LVO) throws SQLException {
        Connection con = new ConexaoBanco().getConexao();

        try {
            String sql = "INSERT INTO login VALUES (null, ?, ?, ? )";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, LVO.getEmail());
            pstm.setString(2, LVO.getSenha());
            pstm.setString(3, LVO.getUsuario());

            pstm.execute();
            pstm.close();

        } catch (SQLException se) {
            throw new SQLException("Erro no Cadastro!");
        } finally {
            con.close();
        }//fim do try catch finally

    }//
    public ResultSet login(loginVO LVO) throws SQLException {
        //Buscar a conexão com o banco de dados
     Connection con = new ConexaoBanco().getConexao();
        
        //Criar um objeto que seja capas de Executar instruções SQL
        //no Banco de Dados
        try {
            //String que receberá a instrução SQL
            String sql;
            //Montando a instrução SELECT para buscar um objeto lVO no Banco mysql
            sql = "select * from login where email=? and senha=?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, LVO.getEmail());
            pstm.setString(2, LVO.getSenha());
            
            //Executando o SQL
            ResultSet rs = pstm.executeQuery();
            return rs;
            
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "LoginDAO! " + se);
            return null;
        }

    }
    
}
