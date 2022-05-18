// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AmmoHandlerBoss.java

package general;

import gameobjects.*;
import java.util.ArrayList;
import java.util.Iterator;
import movement.MoveAmmo;
import movement.Movement;

// Referenced classes of package general:
//            AmmoHandler, GameControl, Model

public class AmmoHandlerBoss
    implements AmmoHandler
{

    public AmmoHandlerBoss(ArrayList order, boolean looping)
    {
        this.order = order;
        this.looping = looping;
        orderIndex = 0;
        ammoTime = 0.0D;
        ammoList = new ArrayList();
    }

    public void checkAmmoTime(ArrayList ammos, Sprite sprite1, Sprite sprite2)
    {
        Enemy e = (Enemy)sprite1;
        if(order != null && GameControl.bossMode)
        {
            if(Boss1.movementFinished)
            {
                ammoList = (ArrayList)order.get(Boss1.moveStage - 1);
                orderIndex = 0;
                ammoTime = 0.0D;
            }
            finished = false;
            ammoTime += Model.elapsedTime * 9.9999999999999995E-007D;
            if(orderIndex <= ammoList.size() - 1 && ammoTime > (double)((Integer)ammoList.get(orderIndex)).intValue())
            {
                ammoTime -= ((Integer)ammoList.get(orderIndex)).intValue();
                orderIndex++;
                finished = true;
            }
            if(finished && !e.isActive(3))
            {
                Iterator iterator = ammos.iterator();
                while(iterator.hasNext()) 
                {
                    Ammo ammo = (Ammo)iterator.next();
                    if(!ammo.isActive(4))
                        continue;
                    a = (Ammo)ammoList.get(orderIndex);
                    x = ((e.getX() + e.getWidth() / 2) - a.getWidth() / 2) + a.getX();
                    y = ((e.getY() + e.getHeight() / 2) - a.getHeight() / 2) + a.getY();
                    ammo.init(a.getOrigImage(), x, y, a.getWidth(), a.getHeight(), a.getMovement(), a.getCollision(), 2, 1, dir, a.getVelX(), a.getVelY(), a.getMaxDist(), a.getAngleMove(), a.getHitpoints(), a.getNrImages(), a.getAnimInterval());
                    a.getMovement().init(ammo);
                    e.setState(6, 1);
                    orderIndex++;
                    if(a.getMoveAmmo().getType() == 2)
                        initInDirOfHeroVel(ammo);
                    else
                    if(a.getMoveAmmo().getType() == 1)
                    {
                        if(e.isInDir(0))
                            ammo.setVelX(ammo.getVelX() - ammo.getVelX() * 2D);
                    } else
                    if(a.getMoveAmmo().getType() == 3 && e.getX() > Hero.heroX)
                        ammo.setVelX(ammo.getVelX() - ammo.getVelX() * 2D);
                    if(a.getSound() != null)
                        Model.addSoundToList((new StringBuilder("/sounds/")).append(a.getSound()).append(".wav").toString());
                    if(looping && orderIndex > ammoList.size() - 1)
                        orderIndex = 0;
                    if(orderIndex > ammoList.size() - 1 || (ammoList.get(orderIndex) instanceof Integer))
                        break;
                }
            }
        }
        Boss1.movementFinished = false;
    }

    private Ammo initInDirOfHeroVel(Ammo ammo)
    {
        double velX = ammo.getVelY();
        double velY = ammo.getVelY();
        int distX = ammo.getX() - ammo.getWidth() / 2 - Hero.heroX;
        int distY = (ammo.getY() + ammo.getHeight() / 2) - Hero.heroY;
        if(distX == 0 && distY == 0)
            distX = 1;
        double percentX = (double)Math.abs(distX) / ((double)(Math.abs(distX) + Math.abs(distY)) * 1.0D);
        double percentY = (double)Math.abs(distY) / ((double)(Math.abs(distX) + Math.abs(distY)) * 1.0D);
        if(distY < 0)
            velY = -velY;
        if(distX > 0)
            velX = -velX;
        ammo.setVelX(velX * percentX);
        ammo.setVelY(velY * percentY);
        return ammo;
    }

    public static final int INDIROFHERO = 1000;
    private double ammoTime;
    private boolean finished;
    private boolean looping;
    private int x;
    private int y;
    private int dir;
    private int orderIndex;
    private ArrayList order;
    private ArrayList ammoList;
    private Ammo a;
}
