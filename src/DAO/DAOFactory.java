/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author 182220058
 */
public class DAOFactory {
    //Instaciando os objetos para as respectivas classes 
    private static postDAO postDAO = new postDAO();
    
    private static jogoDAO jogoDAO = new jogoDAO();
    
    private static hostDAO hostDAO = new hostDAO();
    
    
    //Fazendo uma cópia dos métodos das classes e 
    //disponibilizando para a classe que solicitar
    public static postDAO getPostDAO() {
        return postDAO;
    }

    public static jogoDAO getJogoDAO() {
        return jogoDAO;
    }

    public static hostDAO getHostDAO() {
        return hostDAO;
    }
    
    
}
