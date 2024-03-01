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
public class postVO {
    private int idPost,host;//numPost é a variável que  quantos post o usuário  postou e o numPostPerm é o que controla o maximo de posts
    private String usuario,comentario,jogo;
    private static  int numPost,numPostPerm = 1;
    private static int[] idPostCreated = new int[getNumPostPerm()] ;

    public static int[] getIdPostCreated() {
        return idPostCreated;
    }

    public static void setIdPostCreated(int[] idPostCreated) {
        postVO.idPostCreated = idPostCreated;
    }
    
    

    public static int getNumPost() {
        return numPost;
    }

    public static void setNumPost(int numPost) {
        postVO.numPost = numPost;
    }

    public static int getNumPostPerm() {
        return numPostPerm;
    }

    public static void setNumPostPerm(int numPostPerm) {
        postVO.numPostPerm = numPostPerm;
    }
    
   

    public String getJogo() {
        return jogo;
    }

    public void setJogo(String jogo) {
        this.jogo = jogo;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public int getHost() {
        return host;
    }

    public void setHost(int host) {
        this.host = host;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    
}
