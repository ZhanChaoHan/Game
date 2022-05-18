// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MovePath2.java

package movement;

import gameobjects.Enemy;
import gameobjects.Sprite;
import general.Model;
import java.util.ArrayList;

// Referenced classes of package movement:
//            Movement

public class MovePath2
    implements Movement
{

    public MovePath2(ArrayList order)
    {
        moveIndex = 0;
        timeCounter = 0.0D;
        moving = sleeping = extraSleeping = false;
        this.order = order;
    }

    public MovePath2(ArrayList order, boolean rotating)
    {
        moveIndex = 0;
        timeCounter = 0.0D;
        moving = sleeping = extraSleeping = false;
        this.order = order;
        this.rotating = rotating;
    }

    public void init(Sprite sprite)
    {
    }

    public void move(Sprite sprite)
    {
        Enemy e = (Enemy)sprite;
        if(!sleeping && !extraSleeping && !moving)
        {
            if(order.get(moveIndex) instanceof Double)
            {
                time = ((Double)order.get(moveIndex)).doubleValue();
                moveIndex++;
                sleeping = true;
            } else
            if(order.get(moveIndex) instanceof Integer)
            {
                dist = ((Integer)order.get(moveIndex)).intValue();
                moveIndex++;
                moving = true;
            }
            if(moveIndex == order.size())
                moveIndex = 0;
        }
        checkTime();
        if(moving)
        {
            e.initMoveXY();
            if(e.isInDir(0))
                e.setX(e.getX() - e.getMoveAddX());
            else
            if(e.isInDir(1))
                e.setX(e.getX() + e.getMoveAddX());
            if(e.isInDir(2))
                e.setY(e.getY() + e.getMoveAddY());
            else
            if(e.isInDir(3))
                e.setY(e.getY() - e.getMoveAddY());
            checkDistance(e);
        }
        increaseAngle(e);
    }

    private void checkDistance(Enemy e)
    {
        distCounter += Math.abs(e.getMoveAddX() + e.getMoveAddY());
        if(distCounter >= (double)dist)
        {
            if(e.isInDir(1))
                e.setX(e.getX() - (int)(distCounter - (double)dist));
            else
            if(e.isInDir(0))
                e.setX(e.getX() + (int)(distCounter - (double)dist));
            distCounter = 0.0D;
            moving = false;
        }
    }

    private void checkTime()
    {
        timeCounter += Model.elapsedTime * 9.9999999999999995E-007D;
        if(sleeping && timeCounter >= time)
        {
            timeCounter -= time;
            sleeping = false;
            extraSleeping = true;
        }
        if(extraSleeping && timeCounter >= 4D)
            extraSleeping = false;
    }

    private void increaseAngle(Enemy e)
    {
        if(rotating)
        {
            e.setAngle(e.getAngle() + e.getAngleMove() * (float)(e.getMoveAddX() + e.getMoveAddY()));
            if(e.getAngle() > 360F)
                e.setAngle(e.getAngle() - 360F);
        } else
        {
            e.setAngle(e.getAngleMove());
        }
    }

    private final int EXTRASLEEP = 4;
    private int dist;
    private int moveIndex;
    private double time;
    private double timeCounter;
    private double distCounter;
    private boolean moving;
    private boolean sleeping;
    private boolean extraSleeping;
    private boolean rotating;
    private ArrayList order;
}
