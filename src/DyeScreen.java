import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DyeScreen extends JPanel implements ActionListener {
    Timer timer = new Timer(10,this);
    DyePlayer1 player1 = new DyePlayer1(0,0,24,24);
    DyePlayer2 player2 = new DyePlayer2(616,616,24,24);
    DyeBackground block = new DyeBackground();
    BufferedImage bg;
    int countdown = 1199;
    boolean isRunning = true;
    int p1score;
    int p2score;
    public ArrayList<Rectangle> p1blocks;
    public ArrayList<Rectangle> p2blocks;


    public DyeScreen(){

        timer.start();
        addKeyListener(player1);
        addKeyListener(player2);
        setFocusable(true);
        p1blocks = new ArrayList<>();
        p2blocks = new ArrayList<>();
        try{
            bg = ImageIO.read(new FileImageInputStream(new File("src/Photos/dyeBackground.png")));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(bg,0,0,640,640,null);
        block.graphicCaller(g);

        for (Rectangle r: p1blocks) {
            //g.setColor(Color.green);
            g.setColor(new Color(19,62,89,255));
            g.fillRect((int) r.getX()-6,(int) r.getY()-6,32,32);
        }

        for (Rectangle r: p2blocks) {
            //g.setColor(Color.black);
            g.setColor(new Color(110,213,126,255));
            g.fillRect((int) r.getX()-6,(int) r.getY()-6 ,32,32);
        }
        player2.drawPlayer(g);
        player1.drawPlayer(g);


        g.setFont(new Font("Times New Roman",Font.BOLD,40));
        //g.setColor(new Color(153,0,153));
        g.setColor(new Color(236,187,145));
        g.drawString("" + p1score,60,600);
        //g.setColor(new Color(0,102,205));
        g.setColor(new Color(235,53,94,255));
        g.drawString("" + p2score, 560,600);

        if(countdown >= 500)
        {
            if((countdown / 100) % 5 == 0)
            {
                g.setFont(new Font("Times New Roman",Font.BOLD,100));
                g.setColor(Color.blue);
                g.drawString("" + countdown / 100, 272,320);
            }
        }
        else if(countdown <= 0)
        {
            countdown = 0;
            isRunning = false;
            try {
                gameOver(g);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else
        {
            g.setFont(new Font("Times New Roman",Font.BOLD,100));
            g.drawString("" + countdown / 100, 300,300);
        }
    }

    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(isRunning)
        {
            player1.update();
            player2.update();
            repaint();
            countdown--;
            pointCounter();
        }
    }

    //In order to find the size of the ArrayList which have been created in the very beginning.
    public void pointCounter()
    {
        for (Rectangle r: block.blocks) {
            boolean check = true;
            if(player1.intersects(r) ){
                p2blocks.removeIf(rec -> (rec.equals(r)));

                if(p1blocks.size()==0)
                    p1blocks.add(r);

                else{
                    for (Rectangle s: p1blocks) {

                        if(s.equals(r)){
                            check = false;
                            break;
                        }
                    }
                    if(check)
                        p1blocks.add(r);
                }
            }
            boolean check2 = true;
            if(player2.intersects(r) ){
                p1blocks.removeIf(rec -> (rec.equals(r)));

                if(p2blocks.size()==0)
                    p2blocks.add(r);

                else{
                    for (Rectangle s: p2blocks) {

                        if(s.equals(r)){
                            check2 = false;
                            break;
                        }
                    }
                    if(check2)
                        p2blocks.add(r);
                }
            }

            p1score = p1blocks.size();
            p2score = p2blocks.size();
        }
    }
    private void gameOver(Graphics g) throws InterruptedException {

        if(p1score > p2score)
        {
            g.drawString("Winner Is  Player 1!",123,123);
            Board.p1.health += 20;
            Board.p2.health -= 20;
            Thread.sleep(1000);
            Launcher.dyeScreens.get(Launcher.dyeCounter-1).setVisible(false);
            Launcher.dyeScreens.get(Launcher.dyeCounter-1).removeAll();
            Launcher.dyeScreens.get(Launcher.dyeCounter-1).revalidate();
            Launcher.dyeScreens.get(Launcher.dyeCounter-1).repaint();

            Launcher.addButton();
        }
        else if(p2score > p1score)
        {
            g.drawString("Winner Is  Player 2!",123,123);
            Board.p1.health -= 20;
            Board.p2.health += 20;
            Thread.sleep(1000);
            Launcher.dyeScreens.get(Launcher.dyeCounter-1).setVisible(false);
            Launcher.dyeScreens.get(Launcher.dyeCounter-1).removeAll();
            Launcher.dyeScreens.get(Launcher.dyeCounter-1).revalidate();
            Launcher.dyeScreens.get(Launcher.dyeCounter-1).repaint();
            Launcher.addButton();
        }
        else
        {
            g.drawString("No one won, draw.", 123, 123);
            Thread.sleep(1000);;
            Launcher.dyeScreens.get(Launcher.dyeCounter-1).setVisible(false);
            Launcher.dyeScreens.get(Launcher.dyeCounter-1).removeAll();
            Launcher.dyeScreens.get(Launcher.dyeCounter-1).revalidate();
            Launcher.dyeScreens.get(Launcher.dyeCounter-1).repaint();
            Launcher.addButton();
        }
    }
}