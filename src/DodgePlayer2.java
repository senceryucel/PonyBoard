import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DodgePlayer2 extends Rectangle implements KeyListener {
    private boolean isPressedRight=false;
    private boolean isPressedLeft=false;
    private boolean isPressedUp = false, isPressedDown = false;
    private boolean colCheckR = true;
    private boolean colCheckL = true;
    private boolean colCheckU = true, colCheckD = true;
    private final int speed = 5;
    BufferedImage bg;
    public DodgePlayer2(int x, int y, int width, int height){
        setBounds(x,y,width,height);
        try {
            bg = ImageIO.read(new FileImageInputStream(new File("src/Photos/char2.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(DodgePlayer1 p){
        if(this.isPressedLeft && colCheckL &&  x>0) {
            this.x -= speed;
        }
        if(this.isPressedRight  && colCheckR && x<616) {
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
            case KeyEvent.VK_D -> isPressedRight = true;
            case KeyEvent.VK_A -> isPressedLeft = true;
            case KeyEvent.VK_W -> isPressedUp = true;
            case KeyEvent.VK_S -> isPressedDown = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_D -> isPressedRight = false;
            case KeyEvent.VK_A -> isPressedLeft = false;
            case KeyEvent.VK_W -> isPressedUp = false;
            case KeyEvent.VK_S -> isPressedDown = false;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    public void checkBorder()
    {
        if(x<=320)
        {
            colCheckL = false;
        }
        else{
            colCheckL = true;
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
