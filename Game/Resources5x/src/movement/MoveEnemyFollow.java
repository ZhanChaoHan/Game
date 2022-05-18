// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MoveEnemyFollow.java

package movement;

import gameobjects.*;

// Referenced classes of package movement:
//            Movement

public class MoveEnemyFollow
    implements Movement
{

    public MoveEnemyFollow()
    {
    }

    public void init(Sprite sprite)
    {
    }

    public void move(Sprite sprite)
    {
        Enemy e = (Enemy)sprite;
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
        checkPosition(e);
    }

    public void checkPosition(Enemy e)
    {
        if(Hero.heroX > e.getX())
        {
            e.setDir(1, true);
            e.setDir(0, false);
            e.setX(e.getXPrev());
        } else
        {
            e.setDir(0, true);
            e.setDir(1, false);
            e.setX(e.getXPrev());
        }
    }
}
