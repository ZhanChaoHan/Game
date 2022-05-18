// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MoveAmmo.java

package movement;

import gameobjects.Ammo;
import gameobjects.Sprite;

// Referenced classes of package movement:
//            Movement

public class MoveAmmo
    implements Movement
{

    public MoveAmmo()
    {
        type = 0;
    }

    public MoveAmmo(boolean rotating)
    {
        this.rotating = rotating;
    }

    public MoveAmmo(int type)
    {
        this.type = type;
    }

    public void init(Sprite sprite)
    {
    }

    public void move(Sprite sprite)
    {
        Ammo a = (Ammo)sprite;
        a.initMoveXY();
        a.setX(a.getX() + a.getMoveAddX());
        a.setY(a.getY() - a.getMoveAddY());
        checkDistance(a);
        increaseAngle(a);
    }

    private void checkDistance(Ammo a)
    {
        a.setDist(a.getDist() + Math.abs(a.getMoveAddX() + a.getMoveAddY()));
        if(a.getDist() >= a.getMaxDist())
            a.setState(3, 1);
    }

    private void increaseAngle(Ammo a)
    {
        if(rotating)
        {
            a.setAngle(a.getAngle() + a.getAngleMove() * (float)(Math.abs(a.getMoveAddX()) + Math.abs(a.getMoveAddY())));
            if(a.getAngle() > 360F)
                a.setAngle(360F - a.getAngle());
        } else
        {
            a.setAngle(a.getAngleMove());
        }
    }

    public int getType()
    {
        return type;
    }

    public static final int NOTYPE = 0;
    public static final int INDIROFENEMY = 1;
    public static final int INDIROFHERO = 2;
    public static final int INDIROFHEROLEFTRIGHT = 3;
    private boolean rotating;
    private int type;
}
