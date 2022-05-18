// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Opponent.java

package Typing;

import java.awt.Color;
import java.awt.Graphics;

// Referenced classes of package Typing:
//            Character

public class Opponent extends Character
{

    public Opponent(int x, int power, String fileName)
    {
        super(x, fileName);
        this.power = power;
        powerMax = power;
    }

    public void damage()
    {
        power--;
    }

    public void drawPower(Graphics g)
    {
        g.setColor(Color.YELLOW);
        g.fillRect(401, 11, (int)((210F / (float)powerMax) * (float)power) - 1, 22);
        g.drawImage(wakuImage, 400, 10, null);
    }
}
