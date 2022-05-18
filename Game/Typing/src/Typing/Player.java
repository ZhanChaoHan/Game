// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Player.java

package Typing;

import java.awt.Color;
import java.awt.Graphics;

// Referenced classes of package Typing:
//            Character

public class Player extends Character
{

    public Player(int x, int selectLevel, String filename)
    {
        super(x, filename);
        switch(selectLevel)
        {
        case 0: // '\0'
            power = 10;
            break;

        case 1: // '\001'
            power = 7;
            break;

        case 2: // '\002'
            power = 5;
            break;

        case 3: // '\003'
            power = 5;
            break;
        }
        powerMax = power;
    }

    public void damage()
    {
        power--;
    }

    public void draw(Graphics g)
    {
        super.draw(g);
    }

    public void drawPower(Graphics g)
    {
        g.setColor(Color.YELLOW);
        g.fillRect(51, 11, (int)((210F / (float)powerMax) * (float)power) - 1, 22);
        g.drawImage(wakuImage, 50, 10, null);
    }
}
