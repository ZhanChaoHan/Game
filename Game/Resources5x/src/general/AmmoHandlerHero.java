// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AmmoHandlerHero.java

package general;

import collision.CollisionAmmo;
import gameobjects.*;
import java.util.ArrayList;
import java.util.Iterator;
import movement.MoveAmmo;

// Referenced classes of package general:
//            Model, Fader

public class AmmoHandlerHero
{

    public AmmoHandlerHero()
    {
        finished = true;
        ammoCounter = 0;
    }

    public void checkAmmoTime(ArrayList ammos, Sprite sprite1)
    {
        Hero hero = (Hero)sprite1;
        if(hero.getAmmoPower() == 0)
            ammoInterval = 1.8D;
        else
        if(hero.getAmmoPower() == 1)
            ammoInterval = 1.3999999999999999D;
        else
        if(hero.getAmmoPower() == 2)
            ammoInterval = 1.2D;
        else
        if(hero.getAmmoPower() == 3)
            ammoInterval = 1.2D;
        else
        if(hero.getAmmoPower() == 4)
            ammoInterval = 1.2D;
        if(!finished)
        {
            ammoTime += Model.elapsedTime * 9.9999999999999995E-007D;
            if(ammoTime > ammoInterval)
            {
                ammoTime -= ammoInterval;
                if(ammoTime <= ammoInterval)
                    ammoTime = 0.0D;
                finished = true;
            }
        }
        if(finished && hero.getShooting(0))
        {
            ammoCounter = 0;
            Iterator iterator = ammos.iterator();
            while(iterator.hasNext()) 
            {
                Ammo ammo = (Ammo)iterator.next();
                if(!ammo.isActive(4))
                    continue;
                hero.setState(6, 1);
                finished = false;
                String tempAmmo = "";
                if(hero.isMegaAmmo())
                {
                    ammo.init("ammo13", (hero.getX() + hero.getWidth()) - 30, hero.getY() + 10, 50, 45, new MoveAmmo(), new CollisionAmmo(), 0, 1, 1, 25D, 0.0D, 240, 0.0F, 2, 2, 2D);
                    Fader.initFade("megaAmmo");
                    break;
                }
                int dir;
                if(hero.getRunningDir() == 1)
                    dir = 1;
                else
                    dir = -1;
                if(hero.getAmmoPower() == 0)
                {
                    ammo.init("ammo1", hero.getX() + 1, hero.getY() + 3, 4, 4, new MoveAmmo(), new CollisionAmmo(), 0, 1, 1, 16D * (double)dir, 0.0D, 240, 0.0F, 1, 2, 2D);
                    Model.addSoundToList("/sounds/ammo1.wav");
                    break;
                }
                if(hero.getAmmoPower() == 1)
                {
                    ammo.init("ammo2", hero.getX() + 1, hero.getY() + 1, 10, 4, new MoveAmmo(), new CollisionAmmo(), 0, 1, 1, 22D * (double)dir, 0.0D, 240, 0.0F, 1, 2, 0.59999999999999998D);
                    Model.addSoundToList("/sounds/ammo2.wav");
                    break;
                }
                if(hero.getAmmoPower() == 2)
                {
                    tempAmmo = "ammo5";
                    if(dir == 1)
                        tempAmmo = "ammo6";
                    ammo.init(tempAmmo, hero.getX() + 1, hero.getY() + 1, 10, 4, new MoveAmmo(), new CollisionAmmo(), 0, 1, 1, 25D * (double)dir, 0.0D, 240, 0.0F, 2, 2, 2D);
                    Model.addSoundToList("/sounds/ammo2.wav");
                    break;
                }
                if(hero.getAmmoPower() != 3)
                    continue;
                if(dir == 1)
                {
                    ammo.init("ammo10", hero.getX() + 1, hero.getY() + 1, 8, 3, new MoveAmmo(), new CollisionAmmo(), 0, 1, 1, 30D * (double)dir, 0.0D, 240, 0.0F, 3, 2, 2D);
                    Model.addSoundToList("/sounds/ammo5.wav");
                } else
                {
                    ammo.init("ammo10", hero.getX(), hero.getY() + 1, 8, 3, new MoveAmmo(), new CollisionAmmo(), 0, 1, 1, 30D * (double)dir, 0.0D, 240, 0.0F, 3, 2, 2D);
                    Model.addSoundToList("/sounds/ammo5.wav");
                }
                break;
            }
        }
        if(hero.getShooting(1))
        {
            for(int i = 0; i < hero.getItems().size(); i++)
            {
                if(!(hero.getItems().get(i) instanceof ItemSuperAmmo))
                    continue;
                createSuperAmmo(hero, ammos);
                hero.getItems().remove(i);
                break;
            }

        }
    }

    private void createSuperAmmo(Hero hero, ArrayList ammos)
    {
        Iterator iterator = ammos.iterator();
        while(iterator.hasNext()) 
        {
            Ammo ammo = (Ammo)iterator.next();
            if(!ammo.isActive(4))
                continue;
            hero.setState(6, 1);
            if(ammoCounter == 0)
            {
                ammo.init("ammo13", (hero.getX() + hero.getWidth()) - 30, hero.getY() + 5, 10, 4, new MoveAmmo(true), new CollisionAmmo(), 1, 1, 1, 25D, 0.0D, 240, 4F, 2, 2, 1.0D);
                Fader.initFade("superAmmo");
                Model.addSoundToList("/sounds/herosuperammo1.wav");
            } else
            if(ammoCounter == 1)
                ammo.init("ammo13", (hero.getX() + hero.getWidth()) - 30, hero.getY() + 5, 10, 4, new MoveAmmo(true), new CollisionAmmo(), 1, 1, 1, 17.5D, -17.5D, 240, 4F, 2, 2, 1.0D);
            else
            if(ammoCounter == 2)
                ammo.init("ammo13", (hero.getX() + hero.getWidth()) - 30, hero.getY() + 5, 10, 4, new MoveAmmo(true), new CollisionAmmo(), 1, 1, 1, 0.0D, -25D, 240, 4F, 2, 2, 1.0D);
            else
            if(ammoCounter == 3)
                ammo.init("ammo13", (hero.getX() + hero.getWidth()) - 30, hero.getY() + 5, 10, 4, new MoveAmmo(true), new CollisionAmmo(), 1, 1, 1, -17.5D, -17.5D, 240, 4F, 2, 2, 1.0D);
            else
            if(ammoCounter == 4)
                ammo.init("ammo13", (hero.getX() + hero.getWidth()) - 30, hero.getY() + 5, 10, 4, new MoveAmmo(true), new CollisionAmmo(), 1, 1, 1, -25D, 0.0D, 240, 4F, 2, 2, 1.0D);
            else
            if(ammoCounter == 5)
                ammo.init("ammo13", (hero.getX() + hero.getWidth()) - 30, hero.getY() + 5, 10, 4, new MoveAmmo(true), new CollisionAmmo(), 1, 1, 1, -17.5D, 17.5D, 240, 4F, 2, 2, 1.0D);
            else
            if(ammoCounter == 6)
                ammo.init("ammo13", (hero.getX() + hero.getWidth()) - 30, hero.getY() + 5, 10, 4, new MoveAmmo(true), new CollisionAmmo(), 1, 1, 1, 0.0D, 25D, 240, 4F, 2, 2, 1.0D);
            else
            if(ammoCounter == 7)
                ammo.init("ammo13", (hero.getX() + hero.getWidth()) - 30, hero.getY() + 5, 10, 4, new MoveAmmo(true), new CollisionAmmo(), 1, 1, 1, 17.5D, 17.5D, 240, 4F, 2, 2, 1.0D);
            else
            if(ammoCounter == 8)
                ammo.init("ammo13", (hero.getX() + hero.getWidth()) - 30, hero.getY() + 5, 35, 26, new MoveAmmo(true), new CollisionAmmo(), 1, 1, 1, 10D, 22.5D, 240, 4F, 2, 2, 1.0D);
            else
            if(ammoCounter == 9)
                ammo.init("ammo13", (hero.getX() + hero.getWidth()) - 30, hero.getY() + 5, 35, 26, new MoveAmmo(true), new CollisionAmmo(), 1, 1, 1, 22.5D, 10D, 240, 4F, 2, 2, 1.0D);
            else
            if(ammoCounter == 10)
                ammo.init("ammo13", (hero.getX() + hero.getWidth()) - 30, hero.getY() + 5, 35, 26, new MoveAmmo(true), new CollisionAmmo(), 1, 1, 1, 10D, -22.5D, 240, 4F, 2, 2, 1.0D);
            else
            if(ammoCounter == 11)
                ammo.init("ammo13", (hero.getX() + hero.getWidth()) - 30, hero.getY() + 5, 35, 26, new MoveAmmo(true), new CollisionAmmo(), 1, 1, 1, 22.5D, -10D, 240, 4F, 2, 2, 1.0D);
            else
            if(ammoCounter == 12)
                ammo.init("ammo13", (hero.getX() + hero.getWidth()) - 30, hero.getY() + 5, 35, 26, new MoveAmmo(true), new CollisionAmmo(), 1, 1, 1, -10D, -22.5D, 240, 4F, 2, 2, 1.0D);
            else
            if(ammoCounter == 13)
                ammo.init("ammo13", (hero.getX() + hero.getWidth()) - 30, hero.getY() + 5, 35, 26, new MoveAmmo(true), new CollisionAmmo(), 1, 1, 1, -22.5D, -10D, 240, 4F, 2, 2, 1.0D);
            else
            if(ammoCounter == 14)
                ammo.init("ammo13", (hero.getX() + hero.getWidth()) - 30, hero.getY() + 5, 35, 26, new MoveAmmo(true), new CollisionAmmo(), 1, 1, 1, -10D, 22.5D, 240, 4F, 2, 2, 1.0D);
            else
            if(ammoCounter == 15)
            {
                ammo.init("ammo13", (hero.getX() + hero.getWidth()) - 30, hero.getY() + 5, 35, 26, new MoveAmmo(true), new CollisionAmmo(), 1, 1, 1, -22.5D, 10D, 240, 4F, 2, 2, 1.0D);
                break;
            }
            ammoCounter++;
        }
        ammoCounter = 0;
    }

    private final int WIDTH1 = 4;
    private final int HEIGHT1 = 4;
    private final int WIDTH2 = 8;
    private final int HEIGHT2 = 3;
    private final int WIDTH3 = 10;
    private final int HEIGHT3 = 4;
    private final int WIDTH4 = 10;
    private final int HEIGHT4 = 4;
    private final int WIDTH5 = 35;
    private final int HEIGHT5 = 26;
    private final int WIDTH6 = 50;
    private final int HEIGHT6 = 45;
    private final double AMMOINTERVAL1 = 1.8D;
    private final double AMMOINTERVAL2 = 1.3999999999999999D;
    private final double AMMOINTERVAL3 = 1.2D;
    private final double AMMOVEL1 = 16D;
    private final double AMMOVEL2 = 22D;
    private final double AMMOVEL3 = 25D;
    private final double AMMOVEL4 = 25D;
    private final double AMMOVEL5 = 30D;
    private final int HP1 = 1;
    private final int HP2 = 1;
    private final int HP3 = 2;
    private final int HP4 = 2;
    private final int HP5 = 3;
    private final int ANGLE1 = 4;
    private double ammoTime;
    private double ammoInterval;
    private boolean finished;
    private int ammoCounter;
}
