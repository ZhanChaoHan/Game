// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MoveBoss1.java

package movement;

import gameobjects.*;
import general.GameControl;
import general.Model;

// Referenced classes of package movement:
//            Movement

public class MoveBoss1
    implements Movement
{

    public MoveBoss1()
    {
        Boss1.moveStage = 0;
        stage = 0;
        minStage = 0;
        timeCounter = 0.0D;
        ySin = 0.0D;
        moveAttackDist = 0.0D;
        left = right = false;
    }

    public void init(Sprite sprite)
    {
    }

    public void move(Sprite sprite)
    {
        Enemy e = (Enemy)sprite;
        if(stage == 0)
        {
            xStart = x = e.getX();
            yStart = y = e.getY();
            timeCounter += Model.elapsedTime * 9.9999999999999995E-007D;
            if(timeCounter >= 100D)
            {
                e.setState(0, 0);
                GameControl.bossMode = true;
                Boss1.moveStage = stage = 1;
                Boss1.movementFinished = true;
                timeCounter = 0.0D;
            }
        } else
        if(stage == 1)
        {
            e.initMoveXY();
            yAdd = e.getMoveAddY();
            ySin += yAdd / 100D;
            y = yStart + (int)(20D * Math.sin(ySin));
            if(minStage == 0 && (int)(20D * Math.sin(ySin)) < 0)
                minStage = 1;
            if(minStage == 1 && (int)(20D * Math.sin(ySin)) > 0)
            {
                y = yStart;
                minStage = 0;
                Boss1.movementFinished = true;
                Boss1.moveStage = stage = 2;
            }
        } else
        if(stage == 2)
        {
            timeCounter += Model.elapsedTime * 9.9999999999999995E-007D;
            if(timeCounter >= 10D)
            {
                timeCounter = 0.0D;
                left = true;
                Boss1.moveStage = stage = 3;
                Boss1.movementFinished = true;
            }
        } else
        if(stage == 3)
        {
            if(left)
            {
                e.setVelX(25D);
                e.initMoveXY();
                moveAttackDist -= e.getMoveAddX();
            } else
            if(right)
            {
                e.setVelX(10D);
                e.initMoveXY();
                moveAttackDist += e.getMoveAddX();
            }
            if(left && moveAttackDist <= -150D)
            {
                left = false;
                right = true;
                moveAttackDist = -150D;
            } else
            if(right && moveAttackDist >= 0.0D)
            {
                right = false;
                moveAttackDist = 0.0D;
                Boss1.moveStage = stage = 4;
                Boss1.movementFinished = true;
            }
            x = xStart + (int)moveAttackDist;
        } else
        if(stage == 4)
        {
            timeCounter += Model.elapsedTime * 9.9999999999999995E-007D;
            if(timeCounter >= 10D)
            {
                timeCounter = 0.0D;
                Boss1.moveStage = stage = 1;
                Boss1.movementFinished = true;
            }
        }
        e.setX(x);
        e.setY(y);
    }

    private final int PAUS1 = 0;
    private final int MOVING1 = 1;
    private final int PAUS2 = 2;
    private final int MOVING2 = 3;
    private final int PAUS3 = 4;
    private final int DISTANCE = 20;
    private final int STARTTIME = 100;
    private final int MOVEATTACKTIME = 10;
    private final int AFTERATTACKTIME = 10;
    private double ySin;
    private double yAdd;
    private int x;
    private int y;
    private int xStart;
    private int yStart;
    private int minStage;
    private double timeCounter;
    private double moveAttackDist;
    private boolean left;
    private boolean right;
    private int stage;
}
