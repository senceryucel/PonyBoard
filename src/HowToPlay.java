import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HowToPlay extends JPanel implements ActionListener {

    JButton backButton;
    private BufferedImage howToPlay;
    boolean closeHowToPlay;
    private Image backIcon, backHover, backClick;

    public HowToPlay(){

        //SCREEN SIZE
        setPreferredSize(new Dimension(640,640));

        //CLOSE MENU
        closeHowToPlay = false;

        //MENU & BUTTON IMAGES
        try {
            howToPlay = ImageIO.read(new FileImageInputStream(new File("src/Photos/howtoplay3.png")));
            backIcon = ImageIO.read(new FileImageInputStream(new File("src/Photos/backNormal.png")));
            backHover = ImageIO.read(new FileImageInputStream(new File("src/Photos/backHover.png")));
            backClick = ImageIO.read(new FileImageInputStream(new File("src/Photos/backClick.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //BACK BUTTON ADJUSTMENTS
        backButton = new JButton();
        backButton.setBounds(446,520,68,70);
        backButton.setIcon(new ImageIcon(backIcon));
        backButton.addActionListener(e -> backButtonPressed());
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                backButton.setIcon(new ImageIcon(backHover));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                backButton.setIcon(new ImageIcon(backIcon));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                backButton.setIcon(new ImageIcon(backClick));

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                backButton.setIcon(new ImageIcon(backIcon));
            }
        } );

        //MAKING BUTTON BACKGROUND TRANSPARENT
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
    }

    public void backButtonPressed(){
        closeHowToPlay = true;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(howToPlay, 0, 0, 640, 640, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
