import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DyePlayer2 extends Rectangle implements KeyListener {

    private boolean isPressedRight=false;
    private boolean isPressedLeft=false;
    private boolean isPressedUp=false;
    private boolean isPressedDown=false;
    BufferedImage bg;
    private final double speed = 3.5;

    public DyePlayer2(int x, int y, int width, int height){

        setBounds(x,y,width,height);
        try{

            bg = ImageIO.read(new FileImageInputStream(new File("src/Photos/char2.png")));

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(this.isPressedUp && y>0) {
            this.y -= speed;
        }
        if(this.isPressedDown && y<616) {
            this.y += speed;
        }
        if(this.isPressedLeft && x>0) {
            this.x -= speed;
        }
        if(this.isPressedRight && x<616) {
            this.x += speed;
        }

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

}
