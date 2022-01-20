import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class DodgeFood extends Rectangle {
    BufferedImage bg, bg2;
    ArrayList<Rectangle> pizza = new ArrayList<>();
    ArrayList<Rectangle> broccoli = new ArrayList<>();
    String foodName;
    Random random = new Random();
    int num = random.nextInt(2);
    final int speed = 2;
    final int height = 16, width = 16;
    int y = 0;
    public DodgeFood(int x, int y)
    {
        setBounds(x,y,width,height);
        try {
            bg = ImageIO.read(new FileImageInputStream(new File("src/Photos/pizzaPng.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
        try {
            bg2 = ImageIO.read(new FileImageInputStream(new File("src/Photos/broccoliPng.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
        if(num ==0)
        {
            foodName = "Pizza";
            pizza.add(new Rectangle(x,y,width,height));
        }
        else{
            foodName ="Broccoli";
            broccoli.add(new Rectangle(x,y,width,height));
        }
    }


    public void fall()
    {
        this.y += speed;
    }

    public void draw(Graphics g)
    {

        if(num ==0)
        {
            g.drawImage(bg,x,y,null);
        }
        else{
            g.drawImage(bg2,x,y,null);
        }
    }



}
