// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GameObject.java

import java.awt.Graphics;

public class GameObject
{

    public GameObject()
    {
        px = py = vx = vy = 0.0D;
        frame = size = type = life = 0;
        color = 'r';
        th = 0.0D;
        exist = false;
        anime = 0;
    }

    public static void gameObjectInit(MainPanel mainpanel)
    {
        p = mainpanel;
    }

    public void setData(double d, double d1, double d2, double d3, int i, int j, int k, int l, char c)
    {
        px = d;
        py = d1;
        vx = d2;
        vy = d3;
        size = i;
        frame = j;
        type = k;
        life = l;
        color = c;
        exist = true;
        th = Math.toRadians(-90D);
    }

    public double setTh(double d, double d1)
    {
        if(d1 == 0.0D)
            th = Math.toRadians(90D);
        else
            th = Math.atan(d / d1);
        return th;
    }

    public double setTh(double d)
    {
        return th = d;
    }

    public void move()
    {
        px += vx;
        py += vy;
        frame++;
        if(px < 0.0D || py < 0.0D || px > 480D || py > 480D)
            exist = false;
    }

    public void draw(Graphics g)
    {
        if(!exist)
            return;
        char c = '\0';
        boolean flag = false;
        if(color == '\u900F')
            return;
        if(color == 'r')
            c = '\0';
        else
        if(color == 'y')
            c = ' ';
        else
        if(color == 'g')
            c = '@';
        else
        if(color == 'c')
            c = '`';
        else
        if(color == 'b')
            c = '\200';
        else
        if(color == 'm')
            c = '\240';
        else
        if(color == '\u8056')
            c = '\300';
        else
        if(color == '\u6731')
            c = '\340';
        else
        if(color == '\u9752')
            c = '\u0100';
        else
        if(color == '\u8D64')
            c = '\u0120';
        else
        if(color == '\u7DD1')
            c = '\u0140';
        else
        if(color == '\u7D2B')
            c = '\u0160';
        if(size <= 32)
        {
            int i = ((size / 4 - 1) + anime) * 32;
            g.drawImage(p.image, (int)px - 16, (int)py - 16, (int)px + 16, (int)py + 16, c, i, c + 32, i + 32, null);
        } else
        {
            int j = ((size / 8 - 1) + anime) * 64;
            g.drawImage(p.image, (int)px - 32, (int)py - 32, (int)px + 32, (int)py + 32, c, j, c + 64, j + 64, null);
        }
    }

    public void erase()
    {
        exist = false;
    }

    public void eraseWithEffect()
    {
        if(!exist)
            return;
        if((tmp2 = p.effects.getEmpty()) != null)
            tmp2.setData(px, py, 0.0D, 0.0D, size / 2, 0, size / 4, 5, color);
        erase();
    }

    public char randomColor()
    {
        double d = Math.random();
        if(d < 0.16666666666666666D)
            return 'r';
        if(d < 0.33333333333333331D)
            return 'y';
        if(d < 0.5D)
            return 'g';
        if(d < 0.66666666666666663D)
            return 'c';
        return d >= 0.83333333333333337D ? 'm' : 'b';
    }

    public double getPx()
    {
        return px;
    }

    public double getPy()
    {
        return py;
    }

    public double getVx()
    {
        return vx;
    }

    public double getVy()
    {
        return vy;
    }

    public double getTh()
    {
        return th;
    }

    public int getSize()
    {
        return size;
    }

    public int getFrame()
    {
        return frame;
    }

    public int getType()
    {
        return type;
    }

    public int getLife()
    {
        return life;
    }

    public char getColor()
    {
        return color;
    }

    public boolean getExist()
    {
        return exist;
    }

    protected static MainPanel p;
    protected double px;
    protected double py;
    protected double vx;
    protected double vy;
    protected double th;
    protected int size;
    protected int frame;
    protected int type;
    protected int life;
    protected int anime;
    protected char color;
    protected boolean exist;
    protected GameObject tmp;
    protected GameObject tmp2;
}
