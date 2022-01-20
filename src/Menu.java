import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel implements ActionListener {

    public BufferedImage bg;
    public static JButton play, quit, credits, howToPlay, dice;
    volatile boolean playButton, creditsBool, howToPlayBool, diceBool;
    public Menu() {
        creditsBool = false;
        howToPlayBool = false;
        setPreferredSize(new Dimension(640, 640));
        playButton = false;
        try {
            bg = ImageIO.read(new FileImageInputStream(new File("src/Photos/Menu.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //PLAY BUTTON
        play = new JButton();
        play.setVisible(true);
        play.setOpaque(false);
        play.setContentAreaFilled(false);
        play.setText("");
        play.setBounds(256, 346, 130, 40);
        play.addActionListener(e -> playButtonPressed());
        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

            }
        } );

        //HOW TO PLAY BUTTON
        howToPlay = new JButton();
        howToPlay.setVisible(true);
        howToPlay.setOpaque(false);
        howToPlay.setContentAreaFilled(false);
        howToPlay.setText("");
        howToPlay.setBounds(256, 417, 130, 40);
        howToPlay.addActionListener(e -> howToPlayPressed());
        howToPlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

            }
        } );

        //CREDITS BUTTON
        credits = new JButton();
        credits.setVisible(true);
        credits.setOpaque(false);
        credits.setContentAreaFilled(false);
        credits.setText("");
        credits.setBounds(256, 487, 130, 40);
        credits.addActionListener(e -> creditsButtonPressed());
        credits.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

            }
        } );

        //QUIT BUTTON
        quit = new JButton();
        quit.setVisible(true);
        quit.setOpaque(false);
        quit.setContentAreaFilled(false);
        quit.setText("");
        quit.setBounds(256, 558, 130, 40);
        quit.addActionListener(e -> quitButtonPressed());
        quit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

            }
        } );

        //Dice button
        dice = new JButton();
        dice.setVisible(true);
        dice.setOpaque(false);
        dice.setContentAreaFilled(false);
        dice.setText("");
        dice.setBounds(256, 558, 130, 200);
        dice.addActionListener(e -> diceButtonPressed());
        dice.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

            }
        } );
    }

    public void diceButtonPressed() {
        diceBool = true;
    }
    public void playButtonPressed(){
        playButton =true;
    }
    public void creditsButtonPressed(){
        creditsBool = true;
    }
    public void howToPlayPressed(){
        howToPlayBool = true;
    }
    public void quitButtonPressed(){
        System.exit(0);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(bg, 0, 0, 640, 640, null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
