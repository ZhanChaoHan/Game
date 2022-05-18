// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AmmoHandlerEnemy.java

package general;

import gameobjects.*;
import java.util.ArrayList;
import java.util.Iterator;
import movement.MoveAmmo;
import movement.Movement;

// Referenced classes of package general:
//            AmmoHandler, GameControl, Model

public class AmmoHandlerEnemy
    implements AmmoHandler
{

    public AmmoHandlerEnemy(ArrayList order, int areaW, int areaH)
    {
        this.areaW = areaW;
        this.areaH = areaH;
        inArea = false;
        orderIndex = 0;
        this.order = order;
        ammoTime = 0.0D;
    }

    public AmmoHandlerEnemy(ArrayList order)
    {
        areaW = -1;
        areaH = -1;
        inArea = true;
        orderIndex = 0;
        this.order = order;
        ammoTime = 0.0D;
    }

    public void checkAmmoTime(ArrayList ammos, Sprite sprite1, Sprite sprite2)
    {
        Enemy e = (Enemy)sprite1;
        Hero hero = (Hero)sprite2;
        if(hero.getX() >= e.getX() - areaW / 2 && hero.getX() <= e.getX() + areaW / 2 && hero.getY() >= e.getY() - areaH / 2 && hero.getY() <= e.getY() + areaH / 2 || areaW == -1 && areaH == -1)
        {
            inArea = true;
        } else
        {
            orderIndex = 0;
            ammoTime = ((Integer)order.get(0)).intValue();
            inArea = false;
        }
        if(order != null && inArea && (!(e instanceof Boss1) && !(e instanceof Boss2) || GameControl.bossMode) && (!e.isActive(7) || e.getExplosionHandler() == null))
        {
            finished = false;
            ammoTime += Model.elapsedTime * 9.9999999999999995E-007D;
            if(ammoTime > (double)((Integer)order.get(orderIndex)).intValue())
            {
                ammoTime -= ((Integer)order.get(orderIndex)).intValue();
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
                    a = (Ammo)order.get(orderIndex);
                    if(a != null)
                    {
                        x = ((e.getX() + e.getWidth() / 2) - a.getWidth() / 2) + a.getX();
                        y = ((e.getY() + e.getHeight() / 2) - a.getHeight() / 2) + a.getY();
                        ammo.init(a.getOrigImage(), x, y, a.getWidth(), a.getHeight(), a.getMovement(), a.getCollision(), 2, 1, dir, a.getVelX(), a.getVelY(), a.getMaxDist(), a.getAngleMove(), a.getHitpoints(), a.getNrImages(), a.getAnimInterval());
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
                        a.getMovement().init(ammo);
                        e.setState(6, 1);
                    }
                    orderIndex++;
                    if(orderIndex > order.size() - 1)
                        orderIndex = 0;
                    if(order.get(orderIndex) instanceof Integer)
                        break;
                }
                if(order.get(orderIndex) instanceof Ammo)
                    orderIndex = 0;
            }
        }
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

    public static final int INAREA = -1;
    private double ammoTime;
    private boolean finished;
    private boolean inArea;
    private int x;
    private int y;
    private int areaW;
    private int areaH;
    private int dir;
    private int orderIndex;
    private ArrayList order;
    private Ammo a;
}
