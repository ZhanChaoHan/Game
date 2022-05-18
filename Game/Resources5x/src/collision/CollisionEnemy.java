// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CollisionEnemy.java

package collision;

import gameobjects.*;
import general.GameControl;
import general.Model;
import java.util.ArrayList;
import java.util.Iterator;
import movement.*;

// Referenced classes of package collision:
//            Collision

public class CollisionEnemy
    implements Collision
{

    public CollisionEnemy(int bounceType, int destroyedSize)
    {
        this.bounceType = bounceType;
        this.destroyedSize = destroyedSize;
        tileCollision = ammoCollision = false;
    }

    public void checkCollision(Sprite sprite, Model model)
    {
        Enemy e = (Enemy)sprite;
        ArrayList texts = model.getTexts();
        ArrayList items = model.getItems();
        Hero hero = model.getHero();
        for(Iterator iterator = e.getCollisions().iterator(); iterator.hasNext();)
        {
            GameObject gameObject = (GameObject)iterator.next();
            if((gameObject instanceof Tile) && !tileCollision && bounceType != 0)
            {
                if(e.isInDir(0) && gameObject.getX() < e.getX() + e.getWidth())
                {
                    e.setDir(0, false);
                    if(bounceType == 1)
                    {
                        e.setDir(1, true);
                        e.setRunningDir(1);
                        e.setX(e.getX() + ((gameObject.getX() + gameObject.getWidth()) - e.getX()) * 2);
                    } else
                    if(bounceType == 2)
                    {
                        e.setDir(2, true);
                        e.setRunningDir(2);
                        e.setY(e.getY() - ((gameObject.getX() + gameObject.getWidth()) - e.getX()));
                        e.setX(gameObject.getX() + gameObject.getWidth());
                    }
                } else
                if(e.isInDir(1) && gameObject.getX() + gameObject.getWidth() > e.getX())
                {
                    e.setDir(1, false);
                    if(bounceType == 1)
                    {
                        e.setDir(0, true);
                        e.setRunningDir(0);
                        e.setX(e.getX() - ((e.getX() + e.getWidth()) - gameObject.getX()) * 2);
                    } else
                    if(bounceType == 2)
                    {
                        e.setDir(3, true);
                        e.setRunningDir(3);
                        e.setY(e.getY() + ((e.getX() + e.getWidth()) - gameObject.getX()));
                        e.setX(gameObject.getX() - e.getWidth() - 1);
                    }
                } else
                if(e.isInDir(3) && gameObject.getY() < e.getY() + e.getHeight())
                {
                    e.setDir(3, false);
                    if(bounceType == 1)
                    {
                        e.setDir(2, true);
                        e.setRunningDir(2);
                        e.setY(gameObject.getY() - e.getHeight());
                    } else
                    if(bounceType == 2)
                    {
                        e.setDir(0, true);
                        e.setRunningDir(0);
                        e.setX(e.getX() - ((e.getY() + e.getHeight()) - gameObject.getY()));
                        e.setY(gameObject.getY() - e.getHeight());
                    }
                } else
                if(e.isInDir(2) && gameObject.getY() + gameObject.getHeight() > e.getY())
                {
                    e.setDir(2, false);
                    if(bounceType == 1)
                    {
                        e.setDir(3, true);
                        e.setRunningDir(3);
                        e.setY(gameObject.getY() + gameObject.getHeight());
                    } else
                    if(bounceType == 2)
                    {
                        e.setDir(1, true);
                        e.setRunningDir(1);
                        e.setX(e.getX() + ((gameObject.getY() + gameObject.getHeight()) - e.getY()));
                        e.setY(gameObject.getY() + gameObject.getHeight());
                    }
                }
                tileCollision = true;
            }
            if((gameObject instanceof Ammo) && !e.isActive(3) && !e.isActive(4) && e.getEnergy() != -10 && e.getEnergy() != -100 && (!(e instanceof Boss1) || GameControl.bossMode))
            {
                Ammo ammo = (Ammo)gameObject;
                if(!ammo.isActive(3) && ammo.getType() != 2 && !ammoCollision)
                {
                    int energy = e.getEnergy();
                    if(energy > 0)
                    {
                        energy -= ammo.getHitpoints();
                        if(e.getMovement() instanceof MoveLeft)
                        {
                            MoveLeft tempMove = (MoveLeft)e.getMovement();
                            tempMove.setXCounter(30);
                        }
                        Model.addSoundToList("/sounds/hit2.wav");
                    }
                    e.setState(2, 1);
                    if(energy <= 0)
                    {
                        e.setDestroyedSize(destroyedSize);
                        e.setLastHit(true);
                        e.setState(2, 0);
                        e.setState(5, 0);
                        int score = e.getScore() * hero.getPowerIndex();
                        hero.getMovement().setPowerTime(0.0D);
                        hero.setPowerIndex(hero.getPowerIndex() + 1);
                        texts.add(new Text((new StringBuilder("+")).append(Integer.valueOf(score).toString()).toString(), e.getX(), e.getY() - e.getHeight(), 1, 7, 1.0F, 1.0F, 0.8F, 0.7F, new MoveText(90)));
                        if(!e.getOrigImage().equals("enemy12"))
                        {
                            if((int)(Math.random() * 25D) == 0)
                                items.add(new ItemBonus("item16", (e.getX() + e.getWidth() / 2) - 5, (e.getY() + e.getHeight()) - 10, 10, 10, 10, 3, 2D));
                            else
                            if((int)(Math.random() * 35D) == 0)
                                items.add(new ItemEnergy("item2", (e.getX() + e.getWidth() / 2) - 5, (e.getY() + e.getHeight()) - 10, 10, 10, 3, 2D));
                        } else
                        {
                            double bonusRandom = Math.random();
                            double energyRandom = Math.random();
                            if(bonusRandom > 0.25D)
                                items.add(new ItemBonus("item16", (e.getX() + e.getWidth() / 2) - 5, (e.getY() + e.getHeight()) - 10, 10, 10, 0, 3, 2D));
                            else
                            if(energyRandom > 0.90000000000000002D)
                                items.add(new ItemEnergy("item2", (e.getX() + e.getWidth() / 2) - 5, (e.getY() + e.getHeight()) - 10, 10, 10, 3, 2D));
                        }
                        if(e.getExplosionHandler() == null)
                            Model.addSoundToList((new StringBuilder("/sounds/")).append(e.getExplosionSound()).toString());
                    }
                    e.setEnergy(energy);
                    e.clearAnimHit();
                    ammoCollision = true;
                }
            }
        }

        tileCollision = ammoCollision = false;
    }

    private int bounceType;
    private int destroyedSize;
    private boolean tileCollision;
    private boolean ammoCollision;
}
