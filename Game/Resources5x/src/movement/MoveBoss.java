// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MoveBoss.java

package movement;

import gameobjects.*;
import general.GameControl;
import general.Model;
import java.util.ArrayList;

// Referenced classes of package movement:
//            Movement

public class MoveBoss
    implements Movement
{

    public MoveBoss(ArrayList moveOrder)
    {
        this.moveOrder = moveOrder;
        Boss1.moveStage = 0;
        timeCounter = 0.0D;
        moveAttackDist = 0.0D;
    }

    public void init(Sprite sprite)
    {
    }

    public void move(Sprite sprite)
    {
        Enemy e = (Enemy)sprite;
        if(Boss1.moveStage == 0)
        {
            x = e.getX();
            y = e.getY();
            timeCounter += Model.elapsedTime * 9.9999999999999995E-007D;
            if(timeCounter >= 20D)
            {
                e.setState(0, 0);
                GameControl.bossMode = true;
                Boss1.moveStage = 1;
                tempMoveList = (ArrayList)moveOrder.get(Boss1.moveStage - 1);
                Boss1.movementFinished = true;
                timeCounter = 0.0D;
            }
        } else
        if(Boss1.moveStage % 2 != 0)
        {
            velX = ((Double)tempMoveList.get(0)).doubleValue();
            velY = ((Double)tempMoveList.get(1)).doubleValue();
            distance = ((Double)tempMoveList.get(2)).doubleValue();
            e.setVelX(velX);
            e.setVelY(velY);
            e.initMoveXY();
            if(velX == 0.0D)
                moveAttackDist += Math.abs(e.getMoveAddY());
            else
            if(velY == 0.0D)
                moveAttackDist += Math.abs(e.getMoveAddX());
            else
                moveAttackDist = moveAttackDist + (double)Math.abs(e.getMoveAddX()) + (double)Math.abs(e.getMoveAddY());
            x += e.getMoveAddX();
            y += e.getMoveAddY();
            if(moveAttackDist >= distance)
            {
                if(velY > 0.0D)
                    y -= (int)(moveAttackDist - distance);
                else
                if(velY < 0.0D)
                    y += (int)(moveAttackDist - distance);
                if(velX > 0.0D)
                    x -= (int)(moveAttackDist - distance);
                else
                if(velX < 0.0D)
                    x += (int)(moveAttackDist - distance);
                moveAttackDist = 0.0D;
                Boss1.moveStage++;
                if(Boss1.moveStage > moveOrder.size())
                    Boss1.moveStage = 1;
                Boss1.movementFinished = true;
                tempMoveList = (ArrayList)moveOrder.get(Boss1.moveStage - 1);
            }
        } else
        {
            time = ((Double)tempMoveList.get(0)).doubleValue();
            timeCounter += Model.elapsedTime * 9.9999999999999995E-007D;
            if(timeCounter >= time)
            {
                timeCounter = 0.0D;
                Boss1.moveStage++;
                if(Boss1.moveStage > moveOrder.size())
                    Boss1.moveStage = 1;
                Boss1.movementFinished = true;
                tempMoveList = (ArrayList)moveOrder.get(Boss1.moveStage - 1);
            }
        }
        e.setX(x);
        e.setY(y);
    }

    private final int STARTTIME = 20;
    private int x;
    private int y;
    private double timeCounter;
    private double moveAttackDist;
    private ArrayList moveOrder;
    private ArrayList tempMoveList;
    private double velX;
    private double velY;
    private double distance;
    private double time;
}
