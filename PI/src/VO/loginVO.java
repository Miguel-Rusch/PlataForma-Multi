/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

/**
 *
 * @author 182220058
 */
public class loginVO {
    private int idLogin;
    private String email,senha,usuario;
   private static String em;
   private static boolean online;

    public static boolean isOnline() {
        return online;
    }

    public static void setOnline(boolean online) {
        loginVO.online = online;
    }
   

    public static String getEm() {
        return em;
    }

    public static void setEm(String em) {
        loginVO.em = em;
    }

    public int getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
}
