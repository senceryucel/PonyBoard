import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player2 extends Rectangle
{
    int health;
    BufferedImage bg;
    Player2(int health, int x, int y, int width, int height)
    {
        this.health = health;
        this.setBounds(x,y,width,height);
        try {

            bg = ImageIO.read(new FileImageInputStream(new File("src/Photos/char2.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void drawPlayer(Graphics g) {
        g.drawImage(bg, this.x, this.y, null);
    }
}