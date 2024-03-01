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
public class hostVO {
    private int idHost,idHostConect;
    private String jogada,jogadaAlter;
    private boolean conect;
    

    public int getIdHost() {
        return idHost;
    }

    public void setIdHost(int idHost) {
        this.idHost = idHost;
    }

    public int getIdHostConect() {
        return idHostConect;
    }

    public void setIdHostConect(int idHostConect) {
        this.idHostConect = idHostConect;
    }

    public String getJogada() {
        return jogada;
    }

    public void setJogada(String jogada) {
        this.jogada = jogada;
    }

    public String getJogadaAlter() {
        return jogadaAlter;
    }

    public void setJogadaAlter(String jogadaAlter) {
        this.jogadaAlter = jogadaAlter;
    }

    public boolean isConect() {
        return conect;
    }

    public void setConect(boolean conect) {
        this.conect = conect;
    }
    
    
}
