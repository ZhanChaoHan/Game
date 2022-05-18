// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MovePath1.java

package movement;

import gameobjects.Enemy;
import gameobjects.Sprite;
import general.Model;
import java.util.ArrayList;

// Referenced classes of package movement:
//            Movement

public class MovePath1
    implements Movement
{

    public MovePath1(ArrayList order)
    {
        moveIndex = 0;
        timeCounter = 0.0D;
        moving = false;
        finished = true;
        this.order = order;
    }

    public void init(Sprite sprite)
    {
    }

    public void move(Sprite sprite)
    {
        Enemy e = (Enemy)sprite;
        if(finished)
        {
            time = ((Integer)order.get(moveIndex)).intValue();
            velX = ((Integer)order.get(moveIndex + 1)).intValue();
            velY = ((Integer)order.get(moveIndex + 2)).intValue();
            dist = ((Integer)order.get(moveIndex + 3)).intValue();
            moveIndex += 4;
            if(moveIndex == order.size())
                moveIndex = 0;
            e.setVelX(velX);
            e.setVelY(velY);
            finished = false;
        }
        checkStartTime();
        e.initMoveXY();
        e.setX(e.getX() + e.getMoveAddX());
        if(moving)
        {
            e.setY(e.getY() + e.getMoveAddY());
            checkDistance(e);
        }
    }

    private void checkDistance(Enemy e)
    {
        distCounter += Math.abs(e.getMoveAddY());
        if(distCounter >= (double)dist)
        {
            if(velY > 0)
                e.setY(e.getY() - (int)(distCounter - (double)dist));
            else
            if(velY < 0)
                e.setY(e.getY() + (int)(distCounter - (double)dist));
            distCounter = 0.0D;
            moving = false;
            finished = true;
        }
    }

    private void checkStartTime()
    {
        if(!moving)
            if(timeCounter < time)
            {
                timeCounter += Model.elapsedTime * 9.9999999999999995E-007D;
            } else
            {
                moving = true;
                timeCounter = 0.0D;
            }
    }

    private final int STEP = 4;
    private int dist;
    private int velX;
    private int velY;
    private int moveIndex;
    private double time;
    private double timeCounter;
    private double distCounter;
    private boolean moving;
    private boolean finished;
    private ArrayList order;
}
