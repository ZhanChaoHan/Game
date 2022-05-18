// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Effect.java

import java.awt.Color;
import java.awt.Graphics;

public class Effect extends GameObject
{

    public Effect()
    {
    }

    public void move()
    {
        super.move();
        if(life < 0)
            erase();
        life--;
        size += type;
    }

    public void draw(Graphics g)
    {
        if(!exist)
            return;
        if(color == 'r')
            g.setColor(Color.red);
        else
        if(color == 'y')
            g.setColor(Color.yellow);
        else
        if(color == 'g')
            g.setColor(Color.green);
        else
        if(color == 'c')
            g.setColor(Color.cyan);
        else
        if(color == 'b')
            g.setColor(Color.blue);
        else
        if(color == 'm')
            g.setColor(Color.magenta);
        else
            g.setColor(Color.white);
        g.drawOval((int)px - size / 2, (int)py - size / 2, size, size);
    }
}
