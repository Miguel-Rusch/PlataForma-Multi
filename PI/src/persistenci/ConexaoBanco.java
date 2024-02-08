/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenci;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author 182220058
 */
public class ConexaoBanco {
    

    
    //Método que efetue a conexão com o Banco de Dados
    public Connection getConexao(){
        Connection c = null;
        try {
            String url = "jdbc:mysql://localhost:3306/pi?user=root&password=";
            c = DriverManager.getConnection(url);
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao Conectar! " + se.getMessage());
        }//fecho o Catch
        return c;
    }//fecha o método getConexao
    
}//fecha a classe ConexaoBanco

