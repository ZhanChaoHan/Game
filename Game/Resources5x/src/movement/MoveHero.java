// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MoveHero.java

package movement;

import gameobjects.Hero;
import gameobjects.Sprite;
import general.Model;

// Referenced classes of package movement:
//            Movement

public class MoveHero
    implements Movement
{

    public MoveHero()
    {
        restartTime = 0.0D;
    }

    public void move(Sprite sprite)
    {
        Hero hero = (Hero)sprite;
        double vel = 8.6999999999999993D;
        if(hero.isInDir(2) && (hero.isInDir(0) || hero.isInDir(1)))
            vel = 2.2999999999999998D;
        if(hero.isInDir(3) && (hero.isInDir(0) || hero.isInDir(1)))
            vel = 2.2999999999999998D;
        if(hero.isInDir(1) && (hero.isInDir(2) || hero.isInDir(3)))
            vel = 2.2999999999999998D;
        if(hero.isInDir(0) && (hero.isInDir(2) || hero.isInDir(3)))
            vel = 2.2999999999999998D;
        if(hero.isInDir(0))
        {
            hero.setVelX(-vel);
            hero.setRunningDir(0);
        } else
        if(hero.isInDir(1))
        {
            hero.setVelX(vel);
            hero.setRunningDir(1);
        }
        if(hero.isInDir(3))
        {
            hero.setVelY(-vel);
            hero.setRunningDir(3);
        } else
        if(hero.isInDir(2))
        {
            hero.setVelY(vel);
            hero.setRunningDir(2);
        }
        hero.initMoveXY();
        if(hero.isInDir(0) || hero.isInDir(1))
            hero.setX(hero.getX() + hero.getMoveAddX());
        if(hero.isInDir(2) || hero.isInDir(3))
            hero.setY(hero.getY() - hero.getMoveAddY());
        setPowerBallPosition(hero);
        setMegaAmmoBallPosition(hero);
        if(hero.isActive(7))
        {
            restartTime += Model.elapsedTime * 9.9999999999999995E-007D;
            if(restartTime > 25D)
            {
                hero.setState(7, 0);
                restartTime = 0.0D;
            }
        }
    }

    public void init(Sprite sprite)
    {
        Hero hero = (Hero)sprite;
        hero.setPowerX(-100);
        hero.setPowerY(-100);
    }

    private void setPowerBallPosition(Hero hero)
    {
        if(hero.getPowerIndex() > 1)
        {
            hero.setPowerX(((hero.getX() + hero.getWidth() / 2) - 12 - 2) + (int)(38D * Math.sin(powerTime)));
            hero.setPowerY((hero.getY() + hero.getHeight() / 2) - 12 - (int)(38D * Math.cos(powerTime)));
            powerTime += Model.elapsedTime * 2.6315789473684208E-007D;
            if(powerTime > 6.4031853071795863D)
            {
                powerTime = 0.0D;
                hero.setPowerIndex(1);
                hero.setPowerX(-100);
                hero.setPowerY(-100);
            }
        }
    }

    private void setMegaAmmoBallPosition(Hero hero)
    {
        if(hero.isMegaAmmo())
        {
            hero.setMegaAmmoX(hero.getX() - 40);
            hero.setMegaAmmoY(hero.getY() - 44);
            hero.setMegaAmmoAngle(hero.getMegaAmmoAngle() - (float)(int)(1.3D * (double)hero.getMoveAdd()));
            if(hero.getMegaAmmoAngle() <= -360F)
            {
                hero.setMegaAmmoAngle(0.0F);
                hero.setMegaAmmo(false);
                hero.setMegaAmmoX(-200);
                hero.setMegaAmmoY(-200);
            }
        }
    }

    public void setPowerTime(double t)
    {
        powerTime = t;
    }

    private final double MEGAAMMOVEL = 1.3D;
    private final int RESTARTTIME = 25;
    private final double POWERTIME = 3.7999999999999998D;
    private final int POWERX = 38;
    private final int POWERY = 38;
    private final int POWERBALLSIZE = 25;
    private final double ONELOOP = 6.4031853071795863D;
    private double restartTime;
    private double powerTime;
}
