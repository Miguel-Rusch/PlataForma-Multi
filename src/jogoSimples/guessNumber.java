/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogoSimples;

import DAO.hostDAO;
import VO.hostVO;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import servicos.HostServicos;
import view.GUIHost;

/**
 *
 * @author 182220058
 */
public class guessNumber {
  public static String number;
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
         
         
         HostServicos hs = new servicos.ServicosFactory().getHostServicos();
         
        String jogada = getInputDoJogador();
         hs.registrarJogada(jogada);
         
         
         
         
         
         boolean verInput = false;
         String chute = verificarJogada();;
//         do{
//             System.out.println("ahhhhh");
//         chute = hs.verMensagem();
//         if(!chute.equals("")){
//         verInput = true;
//          Thread.sleep(5000);
//         }
//         }while(!verInput);
//         System.out.println(chute);
//         
//         int p1 = funci(chute,1);
//         hs.registrarJogada(p1+"");
       
        
       
        
//        verInput = false;
//        chute = "";
//        do{
//         chute = hs.verMensagem();
//         if(!chute.equals("")){
//         verInput = true;
//         Thread.sleep(5000);
//         }
//         }while(!verInput);
        
//        exibirVencedor(p1, Integer.parseInt(chute));
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
        String input;
        
        input = JOptionPane.showInputDialog("Jogador 1, por favor, insira um número entre 1 e 100:");
       
        return input;
    }

    // Método para obter o palpite do jogador
    public static int getPalpiteDoJogador() {
        String input = JOptionPane.showInputDialog("Digite seu chute:");
        return Integer.parseInt(input);
    }
    
     public String verificarJogada(){
         
  new Thread() {

    @Override
    public void run() {
       
       HostServicos hs = new servicos.ServicosFactory().getHostServicos();
    boolean running = false;
int FPS = 60;
int UPS =20;
long initialTime = System.nanoTime();
final double timeU = 1000000000 / UPS;
final double timeF = 1000000000 / FPS;
double deltaU = 0, deltaF = 0;
int frames = 0, ticks = 0;
long timer = System.currentTimeMillis();

    while (!running) {
        
        long currentTime = System.nanoTime();
        deltaU += (currentTime - initialTime) / timeU;
        deltaF += (currentTime - initialTime) / timeF;
        initialTime = currentTime;

        if (deltaU >= 1) {
           
            try {
                String chute[] =  hs.verMensagem();
                boolean resposta;
                if(chute[1].equals("true")){
                number = chute[0];
                    System.out.println(chute[0]);
                resposta = true;
                }else{
                    
                resposta = false;
                }
                running = resposta;
                
            } catch (SQLException ex) {
                Logger.getLogger(GUIHost.class.getName()).log(Level.SEVERE, null, ex);
            }
            ticks++;
            deltaU--;
        }

        if (deltaF >= 1) {
            
            frames++;
            deltaF--;
        }

        if (System.currentTimeMillis() - timer > 1000) {
        }

    }
  
    
    
    }
  }.start();
  return number;
}

}
