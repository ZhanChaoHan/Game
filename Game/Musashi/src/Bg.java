// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bg.java

import java.awt.Graphics;

class Bg extends Chara
{

    Bg()
    {
        super(app.imgMusashi);
        kind = 4;
        dir = 0;
        stat = 10;
        motionChange = 0;
        motionCount = 0;
        attackCount = 0;
        waitCount = 0;
    }

    void motion()
    {
    }

    void move()
    {
    }

    void attack(Chara chara)
    {
    }

    void draw(Graphics g)
    {
        if(stat == 10)
            g.drawImage(img, x, y, x + 48, y + 48, motion[dir][motionChange], lineY, motion[dir][motionChange] + 48, lineY + 48, app);
        else
        if(stat == 11)
            g.drawImage(img, x, y, x + 48, y + 48, atkMotion[dir], lineY, atkMotion[dir] + 48, lineY + 48, app);
    }
}
