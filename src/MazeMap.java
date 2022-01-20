import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MazeMap {
    Scanner scan;
    BufferedImage bg;
    String[] map = new String[20];
    public MazeMap()
    {
        openFile();
        readFile();
        closeFile();
        try {
            bg = ImageIO.read(new FileImageInputStream(new File("src/Photos/mazebg.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void openFile()
    {

        try {
            scan = new Scanner(new File("src/Map.txt"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String getMap(int x,int y)
    {
        String index = map[y].substring(x,x+1);
        return index;
    }
    public void readFile()
    {
        while (scan.hasNext())
        {
            for(int i =0;i<map.length;i++)
            {
                map[i] = scan.next();
            }
        }
    }
    public void closeFile()
    {
        scan.close();
    }
    public void paintMap(Graphics g) {
        g.drawImage(bg,0,0,null);

    }

}
