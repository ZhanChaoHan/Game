// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MoveJump.java

package movement;

import gameobjects.Enemy;
import gameobjects.Sprite;
import general.Model;

// Referenced classes of package movement:
//            Movement

public class MoveJump
    implements Movement
{

    public MoveJump(double vel, int pausOrder[], int moveOrder[], boolean animateOnJump)
    {
        this.vel = vel;
        this.moveOrder = moveOrder;
        this.pausOrder = pausOrder;
        yCounter = 0.0D;
        moveOrderCounter = pausOrderCounter = 0;
        pausTime = pausOrder[pausOrderCounter];
        moving = false;
        start = true;
        this.animateOnJump = animateOnJump;
    }

    public void init(Sprite sprite)
    {
    }

    public void move(Sprite sprite)
    {
        Enemy e = (Enemy)sprite;
        if(start)
        {
            startY = e.getY();
            start = false;
        }
        checkMoving(e);
        checkPaus(e);
    }

    private void checkPaus(Enemy e)
    {
        timeCounter += Model.elapsedTime * 9.9999999999999995E-007D;
        if(timeCounter >= pausTime)
        {
            pausOrderCounter++;
            if(pausOrderCounter > pausOrder.length - 1)
                pausOrderCounter = 0;
            timeCounter -= pausTime;
            pausTime = pausOrder[pausOrderCounter];
            yCounter = 0.0D;
            jumpDist = moveOrder[moveOrderCounter];
            moveOrderCounter++;
            if(moveOrderCounter > moveOrder.length - 1)
                moveOrderCounter = 0;
            if(animateOnJump)
                e.setState(7, 1);
            e.setY(startY);
            moving = true;
        }
    }

    private void checkMoving(Enemy e)
    {
        if(moving)
        {
            e.initMoveXY();
            e.setX(e.getX() + e.getMoveAddX());
            yCounter += Model.elapsedTime * (1.0D / (1000000D * vel));
            e.setY(startY - (int)(jumpDist * Math.sin(yCounter)));
            if(e.getY() > startY)
            {
                if(animateOnJump)
                    e.setState(7, 0);
                e.setY(startY);
                moving = false;
            }
        }
    }

    private double yCounter;
    private double y;
    private double pausTime;
    private double vel;
    private double timeCounter;
    private double jumpDist;
    private int startY;
    private int moveOrderCounter;
    private int pausOrderCounter;
    private int moveOrder[];
    private int pausOrder[];
    private boolean start;
    private boolean moving;
    private boolean animateOnJump;
}
