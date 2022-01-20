import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Board extends JPanel implements ActionListener {
    public BufferedImage bg, z1, z2, z3, z4, z5, z6, diceIcon, diceHover, diceClick, gameOverScreen, char1Winner, char2Winner;
    static Player1 p1 = new Player1(100, 260, 560, 24, 24);
    static Player2 p2 = new Player2(100, 390, 560, 24, 24);
    static int turn = 1;
    static int diceNumber;
    boolean rollTheDice = false;
    JButton diceButton;
    Timer timer = new Timer(10, this);
    public Board() {
        timer.start();
        setPreferredSize(new Dimension(640, 640));
        try {
            bg = ImageIO.read(new FileImageInputStream(new File("src/Photos/board.png")));
            z1 = ImageIO.read(new FileImageInputStream(new File("src/Photos/1zar.png")));
            z2 = ImageIO.read(new FileImageInputStream(new File("src/Photos/2zar.png")));
            z3 = ImageIO.read(new FileImageInputStream(new File("src/Photos/3zar.png")));
            z4 = ImageIO.read(new FileImageInputStream(new File("src/Photos/4zar.png")));
            z5 = ImageIO.read(new FileImageInputStream(new File("src/Photos/5zar.png")));
            z6 = ImageIO.read(new FileImageInputStream(new File("src/Photos/6zar.png")));
            diceIcon = ImageIO.read(new FileImageInputStream(new File("src/Photos/diceNormal.png")));
            diceHover = ImageIO.read(new FileImageInputStream(new File("src/Photos/diceHover.png")));
            diceClick = ImageIO.read(new FileImageInputStream(new File("src/Photos/diceClick.png")));
            gameOverScreen = ImageIO.read(new FileImageInputStream(new File("src/Photos/gameOverScreen.jpg")));
            char1Winner = ImageIO.read(new FileImageInputStream(new File("src/Photos/char1Winner.png")));
            char2Winner = ImageIO.read(new FileImageInputStream(new File("src/Photos/char2Winner.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //DICE BUTTON ADJUSTMENTS
        diceButton = new JButton();
        diceButton.setBounds(270, 310, 125, 70);
        diceButton.setIcon(new ImageIcon(diceIcon));
        diceButton.addActionListener(e -> rollButtonPressed());
        diceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                diceButton.setIcon(new ImageIcon(diceHover));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                diceButton.setIcon(new ImageIcon(diceIcon));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                diceButton.setIcon(new ImageIcon(diceClick));

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                diceButton.setIcon(new ImageIcon(diceIcon));
            }
        });

        //MAKING BUTTON BACKGROUND TRANSPARENT
        diceButton.setOpaque(false);
        diceButton.setContentAreaFilled(false);
        diceButton.setBorderPainted(false);
    }
    public void rollButtonPressed(){
     rollTheDice = true;
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //gameOverController(g);
        g.drawImage(bg, 0, 0, 640, 640, null);
        g.setFont(new Font("Times New Roman", Font.BOLD, 15));
        g.setColor(Color.BLACK);
        g.drawString("P1",262,600);
        g.drawString("P2",393,600);

        p1.drawPlayer(g);
        p2.drawPlayer(g);
        g.setFont(new Font("Times New Roman",Font.BOLD, 25));
        g.setColor(Color.BLACK);
        g.drawString("P1 Health: " + p1.health,23,193);
        g.drawString("P2 Health: " + p2.health, 462,193);

        if(diceNumber == 1)
        {
            g.drawImage(z1, 320,320,32,32,null);
        }
        else if(diceNumber == 2)
         {
             g.drawImage(z2, 320,320,32,32,null);
         }
        else if(diceNumber == 3)
        {
            g.drawImage(z3, 320,320,32,32,null);
        }
        else if(diceNumber == 4)
        {
            g.drawImage(z4, 320,320,32,32,null);
        }
        else if(diceNumber == 5)
        {
            g.drawImage(z5, 320,320,32,32,null);
        }
        else if(diceNumber == 6)
        {
            g.drawImage(z6, 320,320,32,32,null);
            //g.setFont(new Font("Times New Roman",Font.BOLD, 40));
            g.drawString("KARMA!",290,380);
        }
        g.setFont(new Font("Times New Roman", Font.BOLD, 15));
       if(turn%2 == 1)
       {
           g.drawString("Turn:", 315,575);
           g.drawString("Player " + (turn%2), 308,595);
       }
       else
           {
               g.drawString("Turn:", 315,575);
               g.drawString("Player " + ((turn%2) + 2), 308,595);
           }
        gameOverController(g);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void dice() throws InterruptedException {
        Random random = new Random();
        diceNumber = random.nextInt(6) + 1;
        //diceNumber = 1;
        if(turn == 1)
        {
            System.out.println(turn + "st dice is " + diceNumber);
        }
        else if(turn == 2)
        {
            System.out.println(turn + "nd dice is " + diceNumber);
        }
        else if(turn == 3)
        {
            System.out.println(turn + "rd dice is " + diceNumber);
        }
        else
            {
                System.out.println(turn + "th dice is " + diceNumber);
            }
        diceController();
    }


    public static void diceController() throws InterruptedException {
        Random random = new Random();

            if(diceNumber == 1)
            {
                Launcher.dyeMain();
            }
            else if(diceNumber == 2)
            {
                Launcher.dodgeMain();
            }
            else if(diceNumber ==3)
            {
                Launcher.mazeMain();
            }
            else if(diceNumber == 4)
            {
                Launcher.holyPlaceMain();
            }
            else if(diceNumber == 5)
            {
                Launcher.hellMain();
            }
            else if(diceNumber ==6)
            {
                Thread.sleep(3000);
                diceNumber = random.nextInt(5)+1;
                System.out.println(diceNumber);
                Launcher.mainFrame.repaint();
                Thread.sleep(1000);
                if(diceNumber == 1)
                {
                    Launcher.dyeMain();
                }
                else if(diceNumber ==2)
                {
                    Launcher.dodgeMain();
                }
                else if(diceNumber == 3)
                {
                    Launcher.mazeMain();
                }
                else if(diceNumber == 4){
                    p1.health += 20;
                }
                else if(diceNumber == 5){
                    p1.health -= 20;
                }
            }
            turn++;
    }
    public void setFrameName()
    {
        if(diceNumber == 1)
        {
            Launcher.mainFrame.setTitle("Dye or Die");
        }
        else if(diceNumber ==2)
        {
            Launcher.mainFrame.setTitle("Dodge It If You Can");
        }
        else if(diceNumber == 3)
        {
            Launcher.mainFrame.setTitle("The Memory Runner");
        }
    }

    public void gameOverController(Graphics g)
    {
        if(p1.health <= 0 || p2.health <= 0)
        {
            gameOver(g);
        }
    }
    public void gameOver(Graphics g)
    {
        g.setFont(new Font("Times New Roman",Font.BOLD, 25));
        Launcher.mainFrame.remove(diceButton);
        g.drawImage(gameOverScreen, 0,0,null);
        g.setColor(Color.WHITE);

        if(p1.health > p2.health)
        {
            g.drawString("Winner is Player1!", 220,160);
            g.drawImage(char1Winner, 195,240,null);
        }
        else
        {
            g.drawString("Winner is Player2!", 220,160);
            g.drawImage(char2Winner, 195,240,null);
        }

    }
}
