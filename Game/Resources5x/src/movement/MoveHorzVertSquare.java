// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MoveHorzVertSquare.java

package movement;

import gameobjects.Enemy;
import gameobjects.Sprite;

// Referenced classes of package movement:
//            Movement

public class MoveHorzVertSquare
    implements Movement
{

    public MoveHorzVertSquare()
    {
    }

    public void init(Sprite s)
    {
        Enemy e = (Enemy)s;
        e.setMovingImages(e.getOrigImage(), s.getNrImages(), s.getAnimInterval());
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
            e.setY(e.getY() - e.getMoveAddY());
        else
        if(e.isInDir(3))
            e.setY(e.getY() + e.getMoveAddY());
    }
}
