// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Character.java

package Typing;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

abstract class Character
{

    public Character(int x, String fileName)
    {
        dead = false;
        attackSound = Applet.newAudioClip(getClass().getResource("music/HitA@11.wav"));
        image = new Image[9];
        for(int i = 0; i <= 8; i++)
            image[i] = loadImage(fileName + Integer.toString(i) + ".gif");

        wakuImage = loadImage("waku.gif");
        imageNumber = 3;
        WIDTH = image[0].getWidth(null);
        HEIGHT = image[0].getHeight(null);
        this.x = x;
        y = 250 - HEIGHT;
    }

    public void draw(Graphics g)
    {
        g.drawImage(image[imageNumber], x, y, null);
        drawPower(g);
    }

    abstract void drawPower(Graphics g);

    private Image loadImage(String filename)
    {
        ImageIcon icon = new ImageIcon(getClass().getResource("image/" + filename));
        return icon.getImage();
    }

    public void setImageNumber(int imageNumber)
    {
        this.imageNumber = imageNumber;
    }

    public void attackSound()
    {
        attackSound.play();
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getWidth()
    {
        return WIDTH;
    }

    public int getHeight()
    {
        return HEIGHT;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getPower()
    {
        return power;
    }

    public void setPower(int power)
    {
        this.power = power;
    }

    public void kill()
    {
        dead = true;
    }

    public boolean isDead()
    {
        return dead;
    }

    protected int x;
    protected int y;
    protected int WIDTH;
    protected int HEIGHT;
    protected int power;
    protected int powerMax;
    protected Image image[];
    protected Image wakuImage;
    public AudioClip attackSound;
    protected boolean dead;
    protected int imageNumber;
}
