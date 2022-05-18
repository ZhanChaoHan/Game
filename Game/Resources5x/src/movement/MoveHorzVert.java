// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MoveHorzVert.java

package movement;

import gameobjects.Enemy;
import gameobjects.Sprite;

// Referenced classes of package movement:
//            Movement

public class MoveHorzVert
    implements Movement
{

    public MoveHorzVert()
    {
        dist = 0;
        maxDist = 0;
        moveType = "NODIR";
    }

    public MoveHorzVert(String moveType)
    {
        dist = 0;
        maxDist = 0;
        this.moveType = moveType;
    }

    public MoveHorzVert(String moveType, int maxDist)
    {
        dist = 0;
        this.maxDist = 0;
        this.moveType = moveType;
        this.maxDist = maxDist;
    }

    public void init(Sprite s)
    {
        if(moveType.equals("INDIROFENEMY"))
        {
            Enemy e = (Enemy)s;
            e.setMovingImages(e.getOrigImage().substring(0, e.getOrigImage().length() - 4), s.getNrImages(), s.getAnimInterval());
        }
    }

    public void move(Sprite sprite)
    {
        sprite.initMoveXY();
        if(sprite.isInDir(0))
        {
            sprite.setX(sprite.getX() - sprite.getMoveAddX());
            if(sprite.getX() < -sprite.getWidth())
                sprite.setX(-sprite.getWidth());
        } else
        if(sprite.isInDir(1))
            sprite.setX(sprite.getX() + sprite.getMoveAddX());
        if(sprite.isInDir(2))
            sprite.setY(sprite.getY() - sprite.getMoveAddY());
        else
        if(sprite.isInDir(3))
            sprite.setY(sprite.getY() + sprite.getMoveAddY());
        if(moveType.equals("PREDEFMOVE"))
        {
            dist = dist + Math.abs(sprite.getMoveAddX()) + Math.abs(sprite.getMoveAddY());
            if(dist >= maxDist)
            {
                int extraDist = dist - maxDist;
                if(sprite.isInDir(0))
                {
                    sprite.setX(sprite.getX() + extraDist);
                    sprite.setDir(0, false);
                    sprite.setDir(1, true);
                } else
                if(sprite.isInDir(1))
                {
                    sprite.setX(sprite.getX() - extraDist);
                    sprite.setDir(0, true);
                    sprite.setDir(1, false);
                }
                dist = 0;
            }
        }
    }

    public static final String NODIR = "NODIR";
    public static final String INFINITE = "INFINITE";
    public static final String INDIROFHERO = "INDIROFHERO";
    public static final String INDIROFENEMY = "INDIROFENEMY";
    public static final String PREDEFMOVE = "PREDEFMOVE";
    private int dist;
    private int maxDist;
    private String moveType;
}
