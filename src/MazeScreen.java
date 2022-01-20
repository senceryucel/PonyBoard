import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MazeScreen extends JPanel implements ActionListener {

    Timer timer = new Timer(15,this);
    private MazePlayer1 p1 = new MazePlayer1(0,245,12,12);
    private MazePlayer2 p2 = new MazePlayer2(0,265,12,12);
    static MazeMap map;
    private int counter = 0;
    boolean isRunning = true;
    int winner = 0;

    public MazeScreen()
    {
        map = new MazeMap();
        timer.start();
        this.setSize(640,640);
        addKeyListener(p1);
        addKeyListener(p2);
        this.setFocusable(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(isRunning)
        {
            p1.update();
            p2.update();
            repaint();
            counter++;
            finishCheck();
        }
    }
    public void paint(Graphics g)
    {
        super.paint(g);
        if(counter > 0 && counter < 100)
        {
            map.paintMap(g);
        }
        else if(counter > 100 && counter < 400)
        {
            g.fillRect(0,0,640,640);
            p1.speed = 5;
            p2.speed = 5;
        }
        else if(counter > 400 && counter < 500)
        {
            map.paintMap(g);
            p1.speed = 5;
            p2.speed = 5;
        }
        else if(counter > 500 && counter < 800)
        {
            g.fillRect(0,0,640,640);
            p1.speed = 5;
            p2.speed = 5;
        }
        else if(counter > 800 && counter < 900)
        {
            map.paintMap(g);
            p1.speed = 5;
            p2.speed = 5;
        }
        else if(counter > 900 && counter < 1200)
        {
            g.fillRect(0,0,640,640);
            p1.speed = 5;
            p2.speed = 5;
        }
        else if(counter > 1200 && counter < 1300)
        {
            map.paintMap(g);
            p1.speed = 5;
            p2.speed = 5;
        }
        else if(counter > 1300 && counter < 1600)
        {
            g.fillRect(0,0,640,640);
            p1.speed = 5;
            p2.speed = 5;
        }
        else if(counter > 1600 && counter < 1700)
        {
            map.paintMap(g);
            p1.speed = 5;
            p2.speed = 5;
        }
        else if(counter > 1700 && counter < 2000)
        {
            g.fillRect(0,0,640,640);
            p1.speed = 5;
            p2.speed = 5;
        }
        else
        {
            map.paintMap(g);
            p1.speed = 5;
            p2.speed = 5;
        }

        p2.drawPlayer(g);
        p1.drawPlayer(g);

        if(winner == 1 || winner == 2)
        {
            try {
                gameOver(g);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isRunning = false;
        }
    }
    public void finishCheck()
    {
        if(p1.x >= 544 && p1.x <= 608 && p1.y >= 576 && p1.y <= 640)
        {
            winner = 1;
            isRunning = false;
        }
        if(p2.x >= 544 && p2.x <= 608 && p2.y >= 576 && p2.y <= 640)
        {
            winner = 2;
            isRunning = false;
        }
    }
    public void gameOver(Graphics g) throws InterruptedException {
        if(winner == 1)
        {
            g.drawString("The Winner is Player 1",320,320);
            Board.p1.health += 20;
            Board.p2.health -= 20;
            Thread.sleep(1000);
            Launcher.mazeScreens.get(Launcher.mazeCounter-1).setVisible(false);
            Launcher.mazeScreens.get(Launcher.mazeCounter-1).removeAll();
            Launcher.mazeScreens.get(Launcher.mazeCounter-1).revalidate();
            Launcher.mazeScreens.get(Launcher.mazeCounter-1).repaint();
            Launcher.addButton();
        }
        else if(winner == 2)
        {
            g.drawString("The Winner is Player 2",320,320);
            Board.p1.health -= 20;
            Board.p2.health += 20;
            Thread.sleep(1000);
            Launcher.mazeScreens.get(Launcher.mazeCounter-1).setVisible(false);
            Launcher.mazeScreens.get(Launcher.mazeCounter-1).removeAll();
            Launcher.mazeScreens.get(Launcher.mazeCounter-1).revalidate();
            Launcher.mazeScreens.get(Launcher.mazeCounter-1).repaint();
            Launcher.addButton();
        }

    }



}
