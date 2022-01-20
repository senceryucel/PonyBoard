import javax.swing.*;
import java.util.LinkedList;


public class Launcher {
    static Menu menu = new Menu();
    static Board boardScreen = new Board();
    static int currentScreen = 0;
    static JFrame mainFrame = new JFrame();
    static Credits credits = new Credits();
    static HowToPlay howToPlay = new HowToPlay();
    static int menuScreen=1, creditsScreen=1,howToPlayScreen=1;
    static boolean isMenu = true;
    static int dyeCounter = 0, dodgeCounter = 0, mazeCounter = 0;
    static LinkedList<DyeScreen> dyeScreens = new LinkedList<DyeScreen>();
    static LinkedList<DodgeScreen> dodgeScreens = new LinkedList<DodgeScreen>();
    static LinkedList<MazeScreen> mazeScreens = new LinkedList<MazeScreen>();
    public static void main(String[] args) throws InterruptedException {
        mainFrame.setTitle("Pony Table");
        mainFrame.setSize(640, 640);
        mainFrame.setResizable(false);
        mainFrame.setFocusable(false);
        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        addMenu();

        //MENU
        while (isMenu) {

            if (currentScreen == 0) {
                // Menu Screen Loop
                menuScreen = 1;
                while (menuScreen == 1) {
                    //Clicking play button
                    if(menu.playButton)
                    {
                        mainFrame.setTitle("Pony Table");
                        //currentScreen = 3;
                        removeController();
                        mainFrame.setVisible(true);
                        mainFrame.add(boardScreen.diceButton);
                        mainFrame.add(boardScreen);
                        boardScreen.diceButton.setVisible(true);
                        mainFrame.setVisible(true);
                        while(true)
                        {
                            Thread.sleep(1);
                            if(boardScreen.rollTheDice)
                            {
                                boardScreen.diceButton.setVisible(false);
                                mainFrame.setVisible(true);
                                mainFrame.repaint();
                                Board.dice();
                                boardScreen.setFrameName();
                                Thread.sleep(1000);
                                mainFrame.setVisible(false);
                                mainFrame.setVisible(true);
                                boardScreen.rollTheDice = false;
                                break;
                            }
                            else { }
                        }
                        mainFrame.setVisible(true);
                        menuScreen++;
                    }
                    //Clicking credits button
                    if (menu.creditsBool) {
                        currentScreen = 1;
                        removeController();
                        mainFrame.setResizable(false);
                        mainFrame.setVisible(false);
                        menuScreen++;
                        creditsScreen = 1;
                    }
                    //Clicking How To Play button
                    if (menu.howToPlayBool) {
                        currentScreen = 2;
                        removeController();
                        mainFrame.setVisible(false);
                        menuScreen++;
                        howToPlayScreen = 1;
                    }
                    //System.out.println("Menu is ON!");
                }
            }
            //CREDITS
            if (currentScreen == 1) {
                mainFrame.add(credits.backButton);
                mainFrame.add(credits);
                mainFrame.pack();
                mainFrame.setVisible(true);
                while (creditsScreen == 1) {
                    //Waiting for closing command
                    if (credits.closeCredits) {
                        currentScreen = 0;
                        mainFrame.remove(credits.backButton);
                        mainFrame.remove(credits);
                        mainFrame.setVisible(false);
                        creditsScreen++;
                        menuScreen = 1;
                        menu.creditsBool = false;
                        credits.closeCredits = false;
                        addMenu();
                        mainFrame.pack();
                        mainFrame.setVisible(true);
                    }
                    //System.out.println("Credits is ON!");
                    Thread.sleep(1);
                }
            }
            //HOWTOPLAY

            if (currentScreen == 2) {
                mainFrame.add(howToPlay.backButton);
                mainFrame.add(howToPlay);
                mainFrame.pack();
                mainFrame.setVisible(true);

                while (howToPlayScreen == 1) {
                    //Waiting for closing command
                    if (howToPlay.closeHowToPlay) {
                        currentScreen = 0;
                        mainFrame.remove(howToPlay.backButton);
                        mainFrame.remove(howToPlay);
                        mainFrame.setVisible(false);
                        howToPlayScreen++;
                        menuScreen = 1;
                        menu.howToPlayBool = false;
                        howToPlay.closeHowToPlay = false;
                        addMenu();
                        mainFrame.setVisible(true);
                    }
                    //System.out.println("How To Play is ON!");
                    Thread.sleep(1);
                }
            }
        }
    }

    public static void dodgeMain()
    {
        mainFrame.pack();
        dodgeScreens.add(new DodgeScreen());
        mainFrame.add(dodgeScreens.get(dodgeCounter));
        dodgeCounter++;
    }

    public static void dyeMain()
    {
        mainFrame.pack();
        dyeScreens.add(new DyeScreen());
        mainFrame.add(dyeScreens.get(dyeCounter));
        dyeCounter++;
    }
    public static void mazeMain()
    {
        mainFrame.pack();
        mazeScreens.add(new MazeScreen());
        mainFrame.add(mazeScreens.get(mazeCounter));
        mazeCounter++;

    }
    public static void holyPlaceMain() throws InterruptedException {
        mainFrame.pack();
        Thread.sleep(2000);
        if(boardScreen.turn%2 == 1)
        {
            boardScreen.p1.health += 20;
        }
        else
            {
                boardScreen.p2.health += 20;
            }
        addButton();
    }
    public static void hellMain() throws InterruptedException {
        mainFrame.pack();
        Thread.sleep(2000);
        if(boardScreen.turn%2 == 1)
        {
            boardScreen.p1.health -= 20;
        }
        else
        {
            boardScreen.p2.health -= 20;
        }
        addButton();
    }

    public static void addMenu()
    {
        mainFrame.add(menu.play);
        mainFrame.add(menu.howToPlay);
        mainFrame.add(menu.credits);
        mainFrame.add(menu.quit);
        mainFrame.add(menu);
        mainFrame.pack();
    }

    public static void removeController()
    {
        mainFrame.remove(menu.play);
        mainFrame.remove(menu.quit);
        mainFrame.remove(menu.credits);
        mainFrame.remove(menu.howToPlay);
        mainFrame.remove(menu);
    }

    public static void addButton()
    {
        mainFrame.pack();
        currentScreen = 0;
        menuScreen = 1;
        menu.playButton = true;
        boardScreen.diceButton.setVisible(true);
    }
}
