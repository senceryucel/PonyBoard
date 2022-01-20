import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MazePlayer2 extends Rectangle implements KeyListener {
    private boolean isPressedRight=false;
    private boolean isPressedLeft=false;
    private boolean colCheckR = true;
    private boolean colCheckL = true;
    private boolean isPressedUp = false, isPressedDown = false;
    private boolean colCheckU = true, colCheckD = true;
    int speed = 3;
    BufferedImage bg;
    int checkPointx =0;
    int checkPointy =256;
    public MazePlayer2(int x, int y, int width, int height){
        setBounds(x,y,width,height);
        try {
            bg = ImageIO.read(new FileImageInputStream(new File("src/Photos/char2.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(this.isPressedLeft  &&  x>0) {
            this.x -= speed;
        }
        if(this.isPressedRight && x<616) {
            this.x += speed;
        }
        if(this.isPressedUp  && y<640) {
            this.y -= speed;
        }
        if(this.isPressedDown  &&  y>=0) {
            this.y += speed;
        }
        checkPoint();
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
        if(!MazeScreen.map.getMap((x/31),(y/31)).equals("0"))
        {
            x= checkPointx;
            y =checkPointy;
        }
    }
    public void checkPoint()
    {
        if(this.y>=0 && this.y<=64 && this.x>=224 && this.x<=288)
        {
            checkPointx = 256;
            checkPointy = 32;
        }
        else if(this.y>=320 && this.y<=384 && this.x>=32 && this.x<=96)
        {
            checkPointx =64;
            checkPointy = 384;
        }
        else if(this.y>=480 && this.y<= 512 && this.x>192 && this.x<= 224)
        {
            checkPointx = 224;
            checkPointy = 492;
        }
    }
}
