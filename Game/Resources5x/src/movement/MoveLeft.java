// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MoveLeft.java

package movement;

import gameobjects.Enemy;
import gameobjects.Sprite;

// Referenced classes of package movement:
//            Movement

public class MoveLeft
    implements Movement
{

    public MoveLeft()
    {
        rotating = true;
        xCounter = 0.0D;
    }

    public MoveLeft(boolean rotating)
    {
        xCounter = 0.0D;
        this.rotating = rotating;
    }

    public void init(Sprite s)
    {
        s.setVel(7D);
    }

    public void move(Sprite sprite)
    {
        Enemy e = (Enemy)sprite;
        e.initMove();
        e.initMoveXY();
        e.setX((e.getX() - e.getMoveAddX()) + (int)xCounter);
        if(xCounter > 0.0D)
        {
            xCounter -= e.getMoveAdd();
            if(xCounter < 0.0D)
                xCounter = 0.0D;
        }
        increaseAngle(e);
    }

    private void increaseAngle(Enemy e)
    {
        if(rotating)
        {
            e.setAngle(e.getAngle() + e.getAngleMove() * (float)(Math.abs(e.getMoveAddX()) + Math.abs(e.getMoveAddY())));
            if(e.getAngle() > 360F)
                e.setAngle(e.getAngle() - 360F);
        } else
        {
            e.setAngle(e.getAngleMove());
        }
    }

    public void setXCounter(int xCounter)
    {
        this.xCounter = xCounter;
    }

    private double height;
    private double xCounter;
    private double x;
    private boolean rotating;
}
