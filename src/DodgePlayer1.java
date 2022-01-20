import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DodgePlayer1 extends Rectangle implements KeyListener {
    private boolean isPressedRight=false;
    private boolean isPressedLeft=false;
    private boolean colCheckR = true;
    private boolean colCheckL = true;
    private boolean isPressedUp = false, isPressedDown = false;
    private boolean colCheckU = true, colCheckD = true;
    private final int speed = 5;
    BufferedImage bg;

    public DodgePlayer1(int x, int y, int width, int height){
        setBounds(x,y,width,height);
        try {
            bg = ImageIO.read(new FileImageInputStream(new File("src/Photos/char1.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(DodgePlayer2 p){
        if(this.isPressedLeft  && colCheckL &&  x>0) {
            this.x -= speed;
        }
        if(this.isPressedRight && colCheckR && x<616) {
            this.x += speed;
        }
        if(this.isPressedUp  && colCheckU && y<640) {
            this.y -= speed;
        }
        if(this.isPressedDown  && colCheckD && y>=516) {
            this.y += speed;
        }
        checkBorder();
    }
    public void drawPlayer(Graphics g){
        g.drawImage(bg,this.x,this.y,null);
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT -> isPressedRight = true;
            case KeyEvent.VK_LEFT -> isPressedLeft = true;
            case KeyEvent.VK_UP -> isPressedUp = true;
            case KeyEvent.VK_DOWN -> isPressedDown = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT -> isPressedRight = false;
            case KeyEvent.VK_LEFT -> isPressedLeft = false;
            case KeyEvent.VK_UP -> isPressedUp = false;
            case KeyEvent.VK_DOWN -> isPressedDown = false;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    public void checkBorder()
    {
        if(x>=292)
        {
            colCheckR = false;
        }
        else{
            colCheckR = true;
        }
        if(y<=520)
        {
            colCheckU = false;
        }
        else
        {
            colCheckU = true;
        }

        if(y >= 616)
        {
            colCheckD = false;
        }
        else
        {
            colCheckD = true;
        }
    }
}