package jogoSimples;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class snake extends JPanel implements KeyListener, ActionListener {

    private final int screenWidth = 600;
    private final int screenHeight = 600;
    private final int unitSize = 25;
    private final int gameUnits = (screenWidth * screenHeight) / unitSize;
    private final int delay = 100;
    private int[] snakeXLength = new int[gameUnits];
    private int[] snakeYLength = new int[gameUnits];
    private int bodyParts = 6;
    private int applesEaten;
    private int appleX;
    private int appleY;
    private char direction = 'R';
    private boolean running = false;
    private Timer timer;
    private Random random;
    private JButton restartButton;
    private JFrame frame;

    public snake() {
        random = new Random();
        frame = new JFrame("Snake Game");
        frame.setSize(screenWidth, screenHeight);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.add(this);
        frame.setVisible(true);
        restartButton = new JButton("Restart");
        restartButton.addActionListener(this);
        restartButton.setVisible(false);
        this.add(restartButton);
        this.setFocusable(true);
        this.addKeyListener(this);
        startGame();
    }

    public static void main() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new snake();
            }
        });
    }

    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(delay, this);
        timer.start();
    }

    public void restartGame() {
        snakeXLength = new int[gameUnits];
        snakeYLength = new int[gameUnits];
        bodyParts = 6;
        applesEaten = 0;
        direction = 'R';
        running = true;
        
        newApple();
        timer.start();
        restartButton.setVisible(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, unitSize, unitSize);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(snakeXLength[i], snakeYLength[i], unitSize, unitSize);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(snakeXLength[i], snakeYLength[i], unitSize, unitSize);
                }
            }
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (screenWidth - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
        } else {
            gameOver(g);
        }
    }

    public void newApple() {
        appleX = random.nextInt((int) (screenWidth / unitSize)) * unitSize;
        appleY = random.nextInt((int) (screenHeight / unitSize)) * unitSize;
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            snakeXLength[i] = snakeXLength[i - 1];
            snakeYLength[i] = snakeYLength[i - 1];
        }

        switch (direction) {
            case 'U':
                snakeYLength[0] = snakeYLength[0] - unitSize;
                break;
            case 'D':
                snakeYLength[0] = snakeYLength[0] + unitSize;
                break;
            case 'L':
                snakeXLength[0] = snakeXLength[0] - unitSize;
                break;
            case 'R':
                snakeXLength[0] = snakeXLength[0] + unitSize;
                break;
        }
    }

    public void checkApple() {
        for (int i = bodyParts; i > 0; i--) {
        if ((snakeXLength[i] == appleX) && (snakeYLength[i] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
        }
    }

    public void checkCollisions() {
        // Check if head collides with body
        for (int i = bodyParts; i > 0; i--) {
            if ((snakeXLength[0] == snakeXLength[i]) && (snakeYLength[0] == snakeYLength[i])) {
                running = false;
            }
        }
        // Check if head touches left border
        if (snakeXLength[0] < 0) {
            running = false;
        }
        // Check if head touches right border
        if (snakeXLength[0] >= screenWidth) {
            running = false;
        }
        // Check if head touches top border
        if (snakeYLength[0] < 0) {
            running = false;
        }
        // Check if head touches bottom border
        if (snakeYLength[0] >= screenHeight) {
            running = false;
        }

        if (!running) {
            timer.stop();
            restartButton.setVisible(true);
        }
    }

    public void gameOver(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 75));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (screenWidth - metrics1.stringWidth("Game Over")) / 2, screenHeight / 2);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (screenWidth - metrics2.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == restartButton) {
            restartGame();
        } else if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'a':
                if(direction!='R'){
                    direction = 'L';
                }
                
                break;
            case 'd':
                if(direction!='L'){
                    direction = 'R';
                }
                break;
            case 'w':
                if(direction!='D'){
                    direction = 'U';
                }
                break;
            case 's':
                if(direction!='U'){
                    direction = 'D';
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
