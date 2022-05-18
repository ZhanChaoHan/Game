// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MoveBoss2.java

package movement;

import gameobjects.*;
import general.GameControl;
import general.Model;

// Referenced classes of package movement:
//            Movement

public class MoveBoss2
    implements Movement
{

    public MoveBoss2()
    {
        Boss1.moveStage = 0;
        stage = 0;
        timeCounter = 0.0D;
        moveAttackDist = 0.0D;
    }

    public void init(Sprite sprite)
    {
    }

    public void move(Sprite sprite)
    {
        Enemy e = (Enemy)sprite;
        if(stage == 0)
        {
            x = e.getX();
            y = e.getY();
            timeCounter += Model.elapsedTime * 9.9999999999999995E-007D;
            if(timeCounter >= 40D)
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
            e.setVelY(3.7000000000000002D);
            e.initMoveXY();
            moveAttackDist += Math.abs(e.getMoveAddY());
            y -= e.getMoveAddY();
            if(moveAttackDist >= 13D)
            {
                y += (int)(moveAttackDist - 13D);
                moveAttackDist = 0.0D;
                Boss1.movementFinished = true;
                Boss1.moveStage = stage = 2;
            }
        } else
        if(stage == 2)
        {
            timeCounter += Model.elapsedTime * 9.9999999999999995E-007D;
            if(timeCounter >= 20D)
            {
                timeCounter = 0.0D;
                Boss1.moveStage = stage = 3;
                Boss1.movementFinished = true;
            }
        } else
        if(stage == 3)
        {
            e.setVelY(5D);
            e.initMoveXY();
            moveAttackDist += Math.abs(e.getMoveAddY());
            y += e.getMoveAddY();
            if(moveAttackDist >= 13D)
            {
                y -= (int)(moveAttackDist - 13D);
                moveAttackDist = 0.0D;
                Boss1.movementFinished = true;
                Boss1.moveStage = stage = 4;
            }
        } else
        if(stage == 4)
        {
            timeCounter += Model.elapsedTime * 9.9999999999999995E-007D;
            if(timeCounter >= 20D)
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
    private final int DIST_4TO3 = 13;
    private final int DIST_3TO2 = 24;
    private final int DIST_2TO1 = 13;
    private final int DIST_1TO2 = 13;
    private final int DIST_2TO3 = 24;
    private final int DIST_3TO4 = 13;
    private final int STARTTIME = 40;
    private final int PAUSTIME1 = 20;
    private final int PAUSTIME2 = 20;
    private final double VEL1 = 3.7000000000000002D;
    private final double VEL2 = 5D;
    private int x;
    private int y;
    private double timeCounter;
    private double moveAttackDist;
    private int stage;
}
