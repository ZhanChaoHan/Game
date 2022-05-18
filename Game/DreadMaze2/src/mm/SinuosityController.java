// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SinuosityController.java

package mm;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Referenced classes of package mm:
//            ImageManager, SoundManager, Globals, InfoPanel, 
//            StatPanel, Maze, SinuosityPanel, PlayerEntity, 
//            Room, CombatEntity

public class SinuosityController
{
    private static class SinuosityMouseListener extends MouseAdapter
    {

        public void mousePressed(MouseEvent e)
        {
            SinuosityController.mouseDown = true;
            SinuosityController.mouseX = e.getX();
            SinuosityController.mouseY = e.getY();
        }

        public void mouseReleased(MouseEvent e)
        {
            SinuosityController.clickCount++;
            SinuosityController.mouseDown = false;
            SinuosityController.mouseX = e.getX();
            SinuosityController.mouseY = e.getY();
        }

        public void mouseDragged(MouseEvent e)
        {
            SinuosityController.mouseX = e.getX();
            SinuosityController.mouseY = e.getY();
        }

        public void mouseMoved(MouseEvent e)
        {
            SinuosityController.mouseX = e.getX();
            SinuosityController.mouseY = e.getY();
        }

        public void mouseExited(MouseEvent e)
        {
            SinuosityController.mouseDown = false;
        }

        private SinuosityMouseListener()
        {
        }

        SinuosityMouseListener(SinuosityMouseListener sinuositymouselistener)
        {
            this();
        }
    }


    public SinuosityController()
    {
    }

    public static void initialize(SinuosityPanel p, InfoPanel info, StatPanel stats)
    {
        ImageManager.initialize();
        SoundManager.initialize();
        Globals.initialize();
        infoPanel = info;
        statPanel = stats;
        if(infoPanel != null)
            infoPanel.setPlayer(0);
        if(statPanel != null)
            statPanel.setPlayer(0);
        panel = p;
        difficulty = 0;
        ImageManager.preloadImage("Monster/notarget.png");
        SoundManager.preloadSound("Other/bossbattle.wav");
        SoundManager.preloadSound("Other/gameover.wav");
        Maze.createMaze(difficulty);
        targetX = targetY = 0;
        panel.resize();
        if(Globals.tutorialModeIsOn())
        {
            SinuosityPanel.createOutput("You are in the Dread Maze.", "", "", 0, 50, 100, 10, 30);
            SinuosityPanel.createOutput("You are alone - saving the world is up to you.", "", "", 0, 100, 100, 10, 20);
            SinuosityPanel.createOutput("To move, click on any visible square.", "", "", 0, 120, 100, 10, 20);
            SinuosityPanel.createOutput("To go deeper, exit in the bottom right.", "", "", 0, 140, 100, 10, 20);
        }
    }

    public static void reset()
    {
        Globals.initialize();
        Globals.setTutorialMode(false);
        difficulty = 0;
        Maze.createMaze(difficulty);
        SinuosityPanel.clearOutput();
        targetX = targetY = 0;
        gameOver = false;
        panel.resize();
        panel.zeroPosition();
    }

    public static void start()
    {
        running = true;
        gameOver = false;
        float target = 16.66667F;
        float frameAverage = target;
        long lastFrame = System.currentTimeMillis();
        float yield = 10000F;
        float damping = 0.1F;
        int lastTurn = -1;
        while(running) 
        {
            PlayerEntity players[] = Globals.getPlayers();
            long timeNow = System.currentTimeMillis();
            frameAverage = (frameAverage * 10F + (float)(timeNow - lastFrame)) / 11F;
            lastFrame = timeNow;
            yield += yield * (target / frameAverage - 1.0F) * damping + 0.05F;
            for(int i = 0; (float)i < yield; i++)
                Thread.yield();

            if(!gameOver)
            {
                Globals.tick();
                boolean newTurn = lastTurn != Globals.getCurrentTurn();
                lastTurn = Globals.getCurrentTurn();
                if(!players[0].isDead())
                {
                    if(mouseDown)
                    {
                        int f = Maze.getGridSizeWithWalls();
                        int nX = (int)(((float)mouseX - Maze.getScrollX()) / (float)f);
                        int nY = (int)(((float)mouseY - Maze.getScrollY()) / (float)f);
                        if(nX >= 0 && nX < Maze.getWidth() && nY >= 0 && nY < Maze.getHeight() && Maze.getRoom(nX, nY).wasVisited())
                        {
                            targetX = nX;
                            targetY = nY;
                        }
                    }
                    CombatEntity entity = players[0].moveTo(targetX, targetY);
                    players[0].attack(entity);
                    if(entity == null)
                        Maze.updateVisitedSpaces();
                    Maze.setPlayerLocation(players[0].getDrawX(), players[0].getDrawY());
                }
                Maze.moveEnemies(newTurn);
                if(Maze.playersWon(targetX, targetY))
                {
                    goToNextLevel();
                } else
                {
                    gameOver = isGameOver();
                    if(gameOver)
                    {
                        SinuosityPanel.clearOutput();
                        SinuosityPanel.createOutput("Inevitably, you have fainted.", "", "Other/gameover.wav", 100, 100, 200, 10, 40);
                        SinuosityPanel.createOutput("There is no one to stop the skeletons from devouring you.", "", null, 100, 135, 200, 10, 20);
                        SinuosityPanel.createOutput("Click 3 times to restart.", "", null, 100, 155, 200, 10, 20);
                        clickCount = 0;
                    }
                }
            } else
            if(clickCount >= 3)
            {
                clickCount = 0;
                reset();
            }
            panel.repaint();
            if(statPanel != null)
                statPanel.redraw();
            if(infoPanel != null)
                infoPanel.redraw();
        }
    }

    public static SinuosityMouseListener getMouseListener()
    {
        if(sinuosityMouseListener == null)
            sinuosityMouseListener = new SinuosityMouseListener(null);
        return sinuosityMouseListener;
    }

    public static boolean isGameOver()
    {
        if(gameOver)
            return true;
        for(int i = 0; i < Globals.getPlayers().length; i++)
            if(!Globals.getPlayers()[i].isDead())
                return false;

        return true;
    }

    private static void goToNextLevel()
    {
        SinuosityPanel.clearOutput();
        difficulty++;
        targetX = 0;
        targetY = 0;
        Maze.createMaze(difficulty);
        panel.resize();
        panel.zeroPosition();
        Globals.tutorialModeIsOn();
    }

    private static SinuosityPanel panel;
    private static InfoPanel infoPanel;
    private static StatPanel statPanel;
    private static SinuosityMouseListener sinuosityMouseListener;
    private static boolean running;
    private static boolean gameOver;
    private static int targetX;
    private static int targetY;
    private static int difficulty;
    private static int mouseX;
    private static int mouseY;
    private static boolean mouseDown;
    private static int clickCount;





}
