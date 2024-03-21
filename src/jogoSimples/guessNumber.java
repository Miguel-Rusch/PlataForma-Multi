/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogoSimples;

import DAO.hostDAO;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author 182220058
 */
public class guessNumber {
   
    public int funci(String f, int player){
    
        // Loop para duas rodadas, uma para cada jogador
        System.out.println("Chute é : " + f);
       int palpite = Integer.parseInt(f);
            int numeroAAdvinhar;

            
               
            

            boolean acertou = false;
            int tentativas = 0;

            // Loop de adivinhação
            while (!acertou) {
               numeroAAdvinhar = getPalpiteDoJogador();

                tentativas++;

                if (palpite == numeroAAdvinhar) {
                    acertou = true;
                    
                       
                        JOptionPane.showMessageDialog(null, "Jogador" + player + "acertou o número! Jogador 1 fez " + tentativas + " palpites.");
                    
                } else if (palpite > numeroAAdvinhar) {
                    JOptionPane.showMessageDialog(null, "Tente um número maior.");
                } else {
                    JOptionPane.showMessageDialog(null, "Tente um número menor.");
                }
            }
        

        return tentativas;
    }
    
     public void game() throws SQLException, InterruptedException{
         
         hostDAO hdao = new hostDAO();
         hdao.registrarJogada(getInputDoJogador());
         boolean verInput = false;
         String chute;
         do{
             System.out.println("ahhhhh");
         chute = hdao.verMensagem();
         if(!chute.equals("")){
         verInput = true;
          Thread.sleep(5000);
         }
         }while(!verInput);
         System.out.println(chute);
         
         
        int p1 = funci(chute,1);
        
        hdao.registrarJogada(p1+"");
        
        verInput = false;
        chute = "";
        do{
         chute = hdao.verMensagem();
         if(!chute.equals("")){
         verInput = true;
         Thread.sleep(5000);
         }
         }while(!verInput);
        
        exibirVencedor(p1, Integer.parseInt(chute));
    }
    
    
    public int exibirVencedor(int palpitesJogador1, int palpitesJogador2){
        int vencedor;
        if (palpitesJogador1 < palpitesJogador2) {
            vencedor = 1;
            JOptionPane.showMessageDialog(null, "Jogador 1 venceu com um total de " + palpitesJogador1 + " chutes!");
        } else if (palpitesJogador1 > palpitesJogador2) {
            vencedor = 2;
            JOptionPane.showMessageDialog(null, "Jogador 2 venceu com um total de " + palpitesJogador2 + " chutes!");
        } else {
            vencedor = 0;
            JOptionPane.showMessageDialog(null, "É um empate! Ambos os jogadores fizeram o mesmo número de chutes: " + palpitesJogador1);
        }
        return vencedor;
    }
    

    // Método para obter a entrada do jogador para o número a ser adivinhado
    public static String getInputDoJogador() {
        String input = JOptionPane.showInputDialog("Jogador 1, por favor, insira um número entre 1 e 100:");
        return input;
    }

    // Método para obter o palpite do jogador
    public static int getPalpiteDoJogador() {
        String input = JOptionPane.showInputDialog("Digite seu chute:");
        return Integer.parseInt(input);
    }

}
