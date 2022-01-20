import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class DodgeScreen extends JPanel implements ActionListener {
    BufferedImage bg;
    boolean isInGame = true;
    Random random = new Random();
    Timer timer = new Timer(10,this);
    DodgePlayer1 player1 = new DodgePlayer1(160,616,24,24);
    DodgePlayer2 player2 = new DodgePlayer2(480,616,24,24);
    DodgeFood[] foods = new DodgeFood[100];
    int counter = 0;
    int P1score = 0;
    int P2score = 0;
    int countdown = 4199;
    int timerCounter = 0;

    public DodgeScreen(){

        try {
            bg = ImageIO.read(new FileImageInputStream(new File("src/Photos/dodgelastbg.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
        timer.start();
        addKeyListener(player1);
        addKeyListener(player2);
        setBackground(Color.black);
        spawnFood();
        this.setFocusable(true);
    }
    @Override
    protected void paintComponent(Graphics g) {
        if(isInGame)
        {
            super.paintComponent(g);
            g.drawImage(bg, 0,0,null);
            player1.drawPlayer(g);
            player2.drawPlayer(g);
            g.setColor(Color.cyan);
            g.setFont(new Font("Times New Roman",Font.BOLD, 25));
            g.drawString("Score: " + P1score,5,630);
            g.drawString("Score: " + P2score, 542,630);
            g.setColor(Color.cyan);
            g.setFont(new Font("Times New Roman",Font.BOLD,30));
            g.drawString("" + countdown/100, 306,630);
            if(countdown == 0)
            {
                isInGame = false;
            }

            for(int i = 0; i < counter; i++)
            {
                foods[i].draw(g);
                foods[i].fall();
            }
        }
        else
        {
            try {
                gameOver(g);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void repaint() {
        super.repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        player1.update(player2);
        player2.update(player1);
        repaint();
        checkFood();
        countdown--;
        levels();
    }
    public void spawnFood()
    {
        foods[counter] = new DodgeFood(random.nextInt(614), 0);
        while(foods[counter].x > 302 && foods[counter].x < 338)
        {
            foods[counter].x = random.nextInt(614);
        }
        counter++;
        if(counter%100 == 1)
        {
            foods = Arrays.copyOf(foods, foods.length + 100);
        }
    }
    public void checkFood()
    {
        for(int i = 0; i < counter; i++)
        {
            if((player1.x >= (foods[i].x - 20) && player1.x <= (foods[i].x + 20)) && (player1.y >= (foods[i].y - 12) && player1.y <= (foods[i].y + 12)))
            {
                foods[i].y = 650;
                if(foods[i].foodName == "Pizza")
                {
                    P1score++;
                }
                else if(foods[i].foodName == "Broccoli")
                {
                    if(P1score > 0)
                    {
                        P1score--;
                    }
                }
                System.out.println("p1 score: " + P1score);
            }
            if((player2.x >= (foods[i].x - 20) && player2.x <= (foods[i].x + 20)) && (player2.y >= (foods[i].y - 12) && player2.y <= (foods[i].y + 12)))
            {
                foods[i].y = 650;
                if(foods[i].foodName == "Pizza")
                {
                    P2score++;
                }
                else if(foods[i].foodName == "Broccoli")
                {
                    if(P2score > 0)
                    {
                        P2score--;
                    }
                }
                System.out.println("p2 score: " + P2score);
            }
        }
    }
    public void levels()
    {
        if(countdown <= 1000)
        {
            if(timerCounter == 2)
            {
                spawnFood();
                timerCounter++;
            }
            else
            {
                if(foods[counter-1].y == 4)
                {
                    spawnFood();
                }
            }
        }
        else if(countdown <= 3000)
        {
            if(timerCounter == 1)
            {
                spawnFood();
                timerCounter++;
            }
            else
            {
                if(foods[counter-1].y == 10)
                {
                    spawnFood();
                }
            }
        }
        else if(countdown <= 5000)
        {
            if(timerCounter == 0)
            {
                spawnFood();
                timerCounter++;
            }else
            {
                if(foods[counter-1].y == 20)
                {
                    spawnFood();
                }
            }
        }
        else
        {
            if(foods[counter-1].y == 30)
            {
                spawnFood();
            }
        }
    }
    public void gameOver (Graphics g) throws InterruptedException {

        if(P1score > P2score)
        {
            g.setColor(Color.white);
            g.setFont( new Font("Times New Roman",Font.ITALIC, 30));
            g.drawString("The Winner Is Player1", 160,320 );
            Board.p1.health += 20;
            Board.p2.health -= 20;
            Thread.sleep(1000);
            Launcher.dodgeScreens.get(Launcher.dodgeCounter-1).setVisible(false);
            Launcher.dodgeScreens.get(Launcher.dodgeCounter-1).removeAll();
            Launcher.dodgeScreens.get(Launcher.dodgeCounter-1).revalidate();
            Launcher.dodgeScreens.get(Launcher.dodgeCounter-1).repaint();
            Launcher.addButton();
        }
        else if(P2score > P1score)
        {
            g.setColor(Color.white);
            g.setFont( new Font("Times New Roman",Font.ITALIC, 30));
            g.drawString("The Winner Is Player2", 160,320 );
            Board.p1.health -= 20;
            Board.p2.health += 20;
            Thread.sleep(1000);
            Launcher.dodgeScreens.get(Launcher.dodgeCounter-1).setVisible(false);
            Launcher.dodgeScreens.get(Launcher.dodgeCounter-1).removeAll();
            Launcher.dodgeScreens.get(Launcher.dodgeCounter-1).revalidate();
            Launcher.dodgeScreens.get(Launcher.dodgeCounter-1).repaint();
            Launcher.addButton();
        }
        else
        {
            g.setColor(Color.white);
            g.setFont( new Font("Times New Roman",Font.ITALIC, 30));
            g.drawString("Draw, no one won", 180,320 );

        }
    }
}