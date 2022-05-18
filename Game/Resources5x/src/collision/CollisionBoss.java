// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CollisionBoss.java

package collision;

import gameobjects.*;
import general.GameControl;
import general.Model;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package collision:
//            Collision

public class CollisionBoss
    implements Collision
{

    public CollisionBoss(int destroyedSize)
    {
        this.destroyedSize = destroyedSize;
    }

    public void checkCollision(Sprite sprite, Model model)
    {
        Enemy e = (Enemy)sprite;
        ArrayList enemies = model.getEnemies();
        ArrayList texts = model.getTexts();
        Hero hero = model.getHero();
        for(Iterator iterator = e.getCollisions().iterator(); iterator.hasNext();)
        {
            GameObject gameObject = (GameObject)iterator.next();
            if(gameObject instanceof Ammo)
            {
                Ammo ammo = (Ammo)gameObject;
                if(!ammo.isActive(3) && ammo.getType() == 0 && !e.isActive(3) && !e.isActive(4) && !e.isActive(7) && GameControl.bossMode)
                {
                    int energy = Boss1.energy;
                    if(e instanceof Boss1)
                    {
                        if(energy > 0)
                        {
                            energy -= ammo.getHitpoints();
                            e.setState(2, 1);
                            Model.addSoundToList("/sounds/hit1.wav");
                        }
                        if(energy <= 0)
                        {
                            e.setDestroyedSize(destroyedSize);
                            e.setLastHit(true);
                            e.setState(2, 0);
                            e.setState(5, 0);
                            e.setState(6, 0);
                            hero.setAngle(0.0F);
                            hero.setPowerIndex(hero.getPowerIndex() + 1);
                            Model.setStopAllSounds(true);
                        }
                        for(Iterator iterator1 = enemies.iterator(); iterator1.hasNext();)
                        {
                            Enemy enemy = (Enemy)iterator1.next();
                            if(enemy instanceof Boss2)
                            {
                                if(energy <= 0)
                                {
                                    enemy.clearStates();
                                    enemy.setState(7, 1);
                                } else
                                {
                                    enemy.setState(2, 1);
                                }
                                break;
                            }
                        }

                        Boss1.energy = energy;
                        e.clearAnimHit();
                    }
                }
            }
        }

    }

    private int destroyedSize;
}
