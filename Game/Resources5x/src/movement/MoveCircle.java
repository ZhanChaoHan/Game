// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MoveCircle.java

package movement;

import gameobjects.Enemy;
import gameobjects.Sprite;

// Referenced classes of package movement:
//            Movement

public class MoveCircle
    implements Movement
{

    public MoveCircle(int d, boolean isMovingX, boolean isMovingY)
    {
        this.d = d;
        start = true;
        xSin = ySin = 0.0D;
        this.isMovingX = isMovingX;
        this.isMovingY = isMovingY;
    }

    public void init(Sprite sprite)
    {
    }

    public void move(Sprite sprite)
    {
        Enemy e = (Enemy)sprite;
        e.initMoveXY();
        if(start)
        {
            start = false;
            xStart = e.getX();
            yStart = e.getY();
        }
        if(isMovingX)
        {
            xSin += e.getMoveAddX();
            x = xStart + (int)((double)d * Math.sin(xSin / 100D));
            e.setX(x);
        }
        if(isMovingY)
        {
            ySin += e.getMoveAddY();
            y = yStart + (int)((double)d * Math.cos(ySin / 100D));
            e.setY(y);
        }
    }

    private double xSin;
    private double ySin;
    private boolean start;
    private boolean isMovingX;
    private boolean isMovingY;
    private int d;
    private int x;
    private int y;
    private int xStart;
    private int yStart;
}
