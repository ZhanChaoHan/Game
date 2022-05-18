// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MoveWave.java

package movement;

import gameobjects.Sprite;

// Referenced classes of package movement:
//            Movement

public class MoveWave
    implements Movement
{

    public MoveWave(int d)
    {
        this.d = d;
        ySin = 0.0D;
    }

    public void init(Sprite s)
    {
        yStart = s.getY();
        ySin = 0.0D;
    }

    public void move(Sprite sprite)
    {
        Sprite e = sprite;
        e.initMoveXY();
        ySin += e.getMoveAddY();
        y = yStart + (int)((double)d * Math.cos(ySin / 20D));
        e.setY(y);
        e.setX(e.getX() + e.getMoveAddX());
    }

    private double ySin;
    private int d;
    private int y;
    private int yStart;
}
