// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MoveBoss3.java

package movement;

import gameobjects.*;
import general.GameControl;
import general.Model;

// Referenced classes of package movement:
//            Movement

public class MoveBoss3
    implements Movement
{

    public MoveBoss3()
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
            if(timeCounter >= 5D)
            {
                timeCounter = 0.0D;
                Boss1.moveStage = stage = 3;
                Boss1.movementFinished = true;
            }
        } else
        if(stage == 3)
        {
            e.setVelX(3.7000000000000002D);
            e.initMoveXY();
            moveAttackDist += Math.abs(e.getMoveAddX());
            x -= e.getMoveAddX();
            if(moveAttackDist >= 24D)
            {
                x += (int)(moveAttackDist - 24D);
                moveAttackDist = 0.0D;
                Boss1.movementFinished = true;
                Boss1.moveStage = stage = 4;
            }
        } else
        if(stage == 4)
        {
            timeCounter += Model.elapsedTime * 9.9999999999999995E-007D;
            if(timeCounter >= 5D)
            {
                timeCounter = 0.0D;
                Boss1.moveStage = stage = 5;
                Boss1.movementFinished = true;
            }
        } else
        if(stage == 5)
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
                Boss1.moveStage = stage = 6;
            }
        } else
        if(stage == 6)
        {
            timeCounter += Model.elapsedTime * 9.9999999999999995E-007D;
            if(timeCounter >= 45D)
            {
                timeCounter = 0.0D;
                Boss1.moveStage = stage = 7;
                Boss1.movementFinished = true;
            }
        } else
        if(stage == 7)
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
                Boss1.moveStage = stage = 8;
            }
        } else
        if(stage == 8)
        {
            timeCounter += Model.elapsedTime * 9.9999999999999995E-007D;
            if(timeCounter >= 5D)
            {
                timeCounter = 0.0D;
                Boss1.moveStage = stage = 9;
                Boss1.movementFinished = true;
            }
        } else
        if(stage == 9)
        {
            e.setVelX(3.7000000000000002D);
            e.initMoveXY();
            moveAttackDist += Math.abs(e.getMoveAddX());
            x += e.getMoveAddX();
            if(moveAttackDist >= 24D)
            {
                x -= (int)(moveAttackDist - 24D);
                moveAttackDist = 0.0D;
                Boss1.movementFinished = true;
                Boss1.moveStage = stage = 10;
            }
        } else
        if(stage == 10)
        {
            timeCounter += Model.elapsedTime * 9.9999999999999995E-007D;
            if(timeCounter >= 5D)
            {
                timeCounter = 0.0D;
                Boss1.moveStage = stage = 11;
                Boss1.movementFinished = true;
            }
        } else
        if(stage == 11)
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
                Boss1.moveStage = stage = 12;
            }
        } else
        if(stage == 12)
        {
            timeCounter += Model.elapsedTime * 9.9999999999999995E-007D;
            if(timeCounter >= 45D)
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
    private final int MOVING3 = 5;
    private final int PAUS4 = 6;
    private final int MOVING4 = 7;
    private final int PAUS5 = 8;
    private final int MOVING5 = 9;
    private final int PAUS6 = 10;
    private final int MOVING6 = 11;
    private final int PAUS7 = 12;
    private final int DIST_4TO3 = 13;
    private final int DIST_3TO2 = 24;
    private final int DIST_2TO1 = 13;
    private final int DIST_1TO2 = 13;
    private final int DIST_2TO3 = 24;
    private final int DIST_3TO4 = 13;
    private final int STARTTIME = 40;
    private final int PAUSTIME1 = 45;
    private final int PAUSTIME2 = 0;
    private final int PAUSTIME3 = 45;
    private final int PAUSTIME4 = 5;
    private final double VEL1 = 3.7000000000000002D;
    private final double VEL2 = 5D;
    private int x;
    private int y;
    private double timeCounter;
    private double moveAttackDist;
    private int stage;
}
