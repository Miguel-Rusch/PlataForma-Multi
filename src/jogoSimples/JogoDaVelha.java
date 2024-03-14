/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogoSimples;

/**
 *
 * @author 182220058
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JogoDaVelha extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';

    public JogoDaVelha() {
        setTitle("Jogo da Velha");
      
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btnClicked = (JButton)e.getSource();
        if (btnClicked.getText().equals("")) {
            btnClicked.setText(Character.toString(currentPlayer));
            if (checkForWin()) {
                JOptionPane.showMessageDialog(this, "Jogador " + currentPlayer + " venceu!");
                resetBoard();
            } else if (checkForDraw()) {
                JOptionPane.showMessageDialog(this, "Empate!");
                resetBoard();
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private boolean checkForWin() {
        // Verificar linhas
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) && 
                buttons[i][0].getText().equals(buttons[i][2].getText()) &&
                !buttons[i][0].getText().equals("")) {
                return true;
            }
        }
        // Verificar colunas
        for (int j = 0; j < 3; j++) {
            if (buttons[0][j].getText().equals(buttons[1][j].getText()) && 
                buttons[0][j].getText().equals(buttons[2][j].getText()) &&
                !buttons[0][j].getText().equals("")) {
                return true;
            }
        }
        // Verificar diagonais
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
            buttons[0][0].getText().equals(buttons[2][2].getText()) &&
            !buttons[0][0].getText().equals("")) {
            return true;
        }
        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
            buttons[0][2].getText().equals(buttons[2][0].getText()) &&
            !buttons[0][2].getText().equals("")) {
            return true;
        }
        return false;
    }

    private boolean checkForDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        currentPlayer = 'X';
    }

    public static void main() {
        SwingUtilities.invokeLater(() -> new JogoDaVelha());
    }
}
