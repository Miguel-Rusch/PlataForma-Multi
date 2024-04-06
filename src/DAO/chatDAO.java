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
}
