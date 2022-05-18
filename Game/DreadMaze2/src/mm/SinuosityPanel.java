// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SinuosityPanel.java

package mm;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;

// Referenced classes of package mm:
//            SinuosityController, Maze, Sinuosity, Globals, 
//            SoundManager, ImageManager

public class SinuosityPanel extends JPanel
{
    private static class OutputMessage
    {

        public boolean isDone()
        {
            return lifetimeIsOver() && transparency <= 0;
        }

        private boolean lifetimeIsOver()
        {
            return Globals.getCurrentTurn() - timeStarted >= lifetime;
        }

        public void draw(Graphics g, float delta)
        {
            if(lifetimeIsOver())
            {
                transparency -= fadeSpeed;
                if(transparency < 0)
                    transparency = 0;
            } else
            {
                transparency += fadeSpeed;
                if(transparency >= 255)
                    transparency = 255;
            }
            if(image.length() > 0)
                g.drawImage(ImageManager.getImage(image), x, y, null);
            if(message.length() > 0)
            {
                g.setFont(font);
                g.setColor(new Color(50, 50, 50, transparency));
                g.drawString(message, x + (int)Maze.getScrollX() + 1, y + (int)Maze.getScrollY() + 1);
                g.setColor(new Color(205, 205, 205, transparency));
                g.drawString(message, (x + (int)Maze.getScrollX()) - 1, (y + (int)Maze.getScrollY()) - 1);
                g.setColor(new Color(red, green, blue, transparency));
                g.drawString(message, x + (int)Maze.getScrollX(), y + (int)Maze.getScrollY());
            }
        }

        private Font font;
        private String message;
        private String image;
        private String sound;
        private int x;
        private int y;
        private int lifetime;
        private int fadeSpeed;
        private int timeStarted;
        private int transparency;
        private int red;
        private int blue;
        private int green;

        public OutputMessage(int r, int g, int b, String text, String im, String noise, int newX, 
                int newY, int life, int speed, int fontsize)
        {
            red = r;
            blue = b;
            green = g;
            font = new Font("Monaco", 1, fontsize);
            message = text;
            image = im;
            sound = noise;
            x = newX;
            y = newY;
            lifetime = life;
            fadeSpeed = speed;
            timeStarted = Globals.getCurrentTurn();
            transparency = 0;
            if(sound != null && sound.length() > 0)
                SoundManager.playSound(sound);
        }
    }


    public SinuosityPanel(Sinuosity m)
    {
        main = m;
        output = new ArrayList();
        addMouseListener(SinuosityController.getMouseListener());
        addMouseMotionListener(SinuosityController.getMouseListener());
        setBackground(Color.BLACK);
        lastDrawTime = System.currentTimeMillis();
    }

    public void paintComponent(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        long now = System.currentTimeMillis();
        float delta = (float)(now - lastDrawTime) / 1000F;
        lastDrawTime = now;
        Maze.draw(g, delta);
        ArrayList removeList = new ArrayList();
        for(int i = 0; i < output.size(); i++)
            if(((OutputMessage)output.get(i)).isDone())
                removeList.add((OutputMessage)output.get(i));
            else
                ((OutputMessage)output.get(i)).draw(g, delta);

        output.removeAll(removeList);
    }

    public void resize()
    {
    }

    public static void createOutput(int red, int green, int blue, String message, String image, String sound, int x, int y, 
            int life, int fadeSpeed, int size)
    {
        output.add(new OutputMessage(red, green, blue, message, image, sound, x, y, life, fadeSpeed, size));
    }

    public static void createOutput(String message, String image, String sound, int x, int y, int life, int fadeSpeed, int size)
    {
        output.add(new OutputMessage(255, 0, 0, message, image, sound, x, y, life, fadeSpeed, size));
    }

    public static void clearOutput()
    {
        output.clear();
    }

    public void zeroPosition()
    {
        main.zeroPanelPosition();
    }

    private static final long serialVersionUID = 1L;
    private Sinuosity main;
    private long lastDrawTime;
    private static ArrayList output;
}
