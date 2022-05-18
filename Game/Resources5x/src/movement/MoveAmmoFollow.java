// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MoveAmmoFollow.java

package movement;

import gameobjects.*;

// Referenced classes of package movement:
//            Movement

public class MoveAmmoFollow
    implements Movement
{

    public MoveAmmoFollow()
    {
    }

    public void init(Sprite s)
    {
        Ammo a = (Ammo)s;
        vel = (int)a.getVelX();
    }

    public void move(Sprite sprite)
    {
        Ammo a = (Ammo)sprite;
        distX = a.getX() - Hero.heroX;
        distY = a.getY() - Hero.heroY;
        if(distX == 0 && distY == 0)
            distX = 1;
        percentX = (double)Math.abs(distX) / ((double)(Math.abs(distX) + Math.abs(distY)) * 1.0D);
        percentY = (double)Math.abs(distY) / ((double)(Math.abs(distX) + Math.abs(distY)) * 1.0D);
        a.setVelX(vel * percentX);
        a.setVelY(vel * percentY);
        a.initMoveXY();
        if(distY >= 0)
            a.setY(a.getY() - a.getMoveAddY());
        else
        if(distY < 0)
            a.setY(a.getY() + a.getMoveAddY());
        if(distX >= 0)
            a.setX(a.getX() - a.getMoveAddX());
        else
        if(distX < 0)
            a.setX(a.getX() + a.getMoveAddX());
        checkDistance(a);
        increaseAngle(a);
    }

    private void checkDistance(Ammo a)
    {
        a.setDist(a.getDist() + Math.abs(a.getMoveAddX()) + Math.abs(a.getMoveAddY()));
        if(a.getDist() >= a.getMaxDist())
            a.setState(3, 1);
    }

    private void increaseAngle(Ammo a)
    {
        a.setAngle(a.getAngle() + a.getAngleMove() * (float)(Math.abs(a.getMoveAddX()) + Math.abs(a.getMoveAddY())));
        if(a.getAngle() > 360F)
            a.setAngle(360F - a.getAngle());
    }

    private int distX;
    private int distY;
    private double percentX;
    private double percentY;
    private double vel;
}
