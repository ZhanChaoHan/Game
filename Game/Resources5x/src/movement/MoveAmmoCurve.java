// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MoveAmmoCurve.java

package movement;

import gameobjects.Ammo;
import gameobjects.Sprite;
import general.Model;

// Referenced classes of package movement:
//            Movement

public class MoveAmmoCurve
    implements Movement
{

    public MoveAmmoCurve(double height)
    {
        this.height = height;
        yCounter = 0.0D;
    }

    public void init(Sprite s)
    {
        yCounter = 0.0D;
        s.setVel(height);
        startY = s.getY();
    }

    public void move(Sprite sprite)
    {
        Ammo a = (Ammo)sprite;
        a.initMoveXY();
        a.setX(a.getX() + a.getMoveAddX());
        yCounter += Model.elapsedTime * 2.4999999999999999E-007D;
        a.setY(startY - (int)(height * Math.sin(yCounter)));
        if((double)a.getY() >= ((double)startY + height) - 10D)
        {
            a.setState(1, 0);
            a.setState(3, 1);
        }
    }

    private double height;
    private double yCounter;
    private int startY;
}
