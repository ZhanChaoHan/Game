// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bullet.java

import java.applet.AudioClip;
import java.awt.Graphics;

public class Bullet extends GameObject
{

    public Bullet()
    {
    }

    public void move()
    {
        if(type == 1)
        {
            boolean flag = false;
            MainPanel _tmp = p;
            if(px < 32D)
            {
                vx *= -1D;
                flag = true;
            }
            MainPanel _tmp1 = p;
            if(px > 448D)
            {
                vx *= -1D;
                flag = true;
            }
            MainPanel _tmp2 = p;
            if(py < 32D)
            {
                vy *= -1D;
                flag = true;
            }
            if(flag)
            {
                type = 0;
                p.reflect[p.wavTarg].play();
            }
        }
        if(frame == -1)
        {
            double d = vx;
            vx = -vy;
            vy = d;
        } else
        if(frame == -2)
        {
            double d1 = vy;
            vy = -vx;
            vx = d1;
        }
        if(frame < 0)
            frame++;
        super.move();
    }

    public void draw(Graphics g)
    {
        super.draw(g);
    }
}
