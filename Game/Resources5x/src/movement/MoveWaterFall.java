// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MoveWaterFall.java

package movement;

import gameobjects.Enemy;
import gameobjects.Sprite;
import general.Model;

// Referenced classes of package movement:
//            Movement

public class MoveWaterFall
    implements Movement
{

    public MoveWaterFall(double pausTime, double activeTime)
    {
        this.pausTime = pausTime;
        this.activeTime = activeTime;
        timeCounter = 0.0D;
        mode = 0;
    }

    public void init(Sprite s)
    {
        Enemy e = (Enemy)s;
        endY = e.getY();
        e.setY(-e.getHeight());
    }

    public void move(Sprite sprite)
    {
        Enemy e = (Enemy)sprite;
        timeCounter += Model.elapsedTime * 9.9999999999999995E-007D;
        if(timeCounter >= pausTime && mode == 0)
        {
            mode = 1;
            timeCounter -= pausTime;
            e.setY(endY);
        } else
        if(timeCounter >= activeTime && mode == 1)
        {
            mode = 0;
            timeCounter -= activeTime;
            e.setY(-e.getHeight());
        }
    }

    private final int PAUS = 0;
    private final int ACTIVE = 1;
    private double pausTime;
    private double activeTime;
    private double timeCounter;
    private int endY;
    private int mode;
}
