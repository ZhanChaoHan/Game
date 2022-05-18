// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CollisionHero.java

package collision;

import gameobjects.*;
import general.GameControl;
import general.Model;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package collision:
//            Collision, CollisionHandler

public class CollisionHero
    implements Collision
{

    public CollisionHero()
    {
    }

    private void checkBorderCollision(Hero hero)
    {
        if(!Hero.isLoosing)
        {
            if(hero.getBorderCollHorz() == 0)
                hero.setX(0);
            else
            if(hero.getBorderCollHorz() == 1 && GameControl.bossLevel)
                hero.setX((0 + Model.gameWidth) - hero.getWidth());
            if(hero.getBorderCollVert() == 2)
            {
                hero.setY(0);
                hero.setDir(2, false);
                hero.setDir(3, true);
            } else
            if(hero.getBorderCollVert() == 3)
                initDestroyedHero(hero);
        }
    }

    public void checkCollision(Sprite sprite, Model model)
    {
        Hero hero = (Hero)sprite;
        checkBorderCollision(hero);
        ArrayList texts = new ArrayList();
        if(model != null)
            texts = model.getTexts();
        ArrayList collObjects = new ArrayList();
        if(!hero.isActive(3) && !hero.isActive(4) && !hero.isActive(7) && !Hero.isLoosing)
        {
            for(Iterator iterator = hero.getCollisions().iterator(); iterator.hasNext();)
            {
                GameObject gameObject = (GameObject)iterator.next();
                if((gameObject instanceof Tile) && !hero.isActive(3) && !hero.isActive(4) && !hero.getTileCollision() && (gameObject.getOrder() == 2 || gameObject.getOrder() == 3 || gameObject.getOrder() == 6))
                {
                    if(gameObject.getOrder() == 2 || gameObject.getOrder() == 3)
                        collObjects.add(gameObject);
                    else
                    if(gameObject.getOrder() == 6)
                    {
                        Hero.isOverlappingLadder = true;
                        Hero.ladderX = gameObject.getX();
                    }
                } else
                if(((gameObject instanceof Enemy) || (gameObject instanceof Ammo)) && !hero.isActive(2) && !hero.isActive(3) && !hero.isActive(4))
                {
                    if(gameObject instanceof Enemy)
                    {
                        Enemy enemy = (Enemy)gameObject;
                        if(!enemy.isActive(3) && !enemy.getOrigImage().equals("enemy12") && (!enemy.isActive(7) || enemy.getExplosionHandler() == null))
                            if(hero.getEnergy() > 1)
                            {
                                hero.setEnergy(hero.getEnergy() - 1);
                                hero.setState(2, 1);
                                Model.addSoundToList("/sounds/hit2.wav");
                            } else
                            {
                                initDestroyedHero(hero);
                            }
                    } else
                    if(gameObject instanceof Ammo)
                    {
                        Ammo ammo = (Ammo)gameObject;
                        if(!ammo.isActive(3) && ammo.getType() == 2)
                        {
                            ammo.setState(3, 1);
                            if(hero.getEnergy() > 1)
                            {
                                hero.setEnergy(hero.getEnergy() - 1);
                                hero.setPowerIndex(1);
                                hero.setPowerX(-100);
                                hero.setPowerY(-100);
                                hero.setState(2, 1);
                                Model.addSoundToList("/sounds/hit2.wav");
                            } else
                            {
                                initDestroyedHero(hero);
                            }
                        }
                    }
                    hero.clearAnimHit();
                }
                if(gameObject instanceof Item)
                    if(gameObject instanceof ItemBonus)
                    {
                        ItemBonus item = (ItemBonus)gameObject;
                        if(!item.isActive(2) && !item.isActive(7))
                        {
                            hero.setScore(hero.getScore() + 1L);
                            if(hero.getLives() < 10 && hero.getScore() > 9L)
                            {
                                hero.setLives(hero.getLives() + 1);
                                hero.setScore(0L);
                                Model.extraLife.setState(7, 1);
                                Model.addSoundToList("/sounds/energy2.wav");
                            } else
                            {
                                Model.addSoundToList("/sounds/energy1.wav");
                            }
                            item.setState(2, 1);
                        }
                    } else
                    if(gameObject instanceof ItemAmmo)
                    {
                        ItemAmmo item = (ItemAmmo)gameObject;
                        if(!item.isActive(2) && !item.isActive(7))
                        {
                            hero.getAmmoPower();
                            hero.setAmmoPower(hero.getAmmoPower() + 1);
                            if(hero.getAmmoPower() > 3)
                                hero.setAmmoPower(3);
                            item.setState(2, 1);
                            Model.addSoundToList("/sounds/bonus1.wav");
                        }
                    } else
                    if(gameObject instanceof ItemEnergy)
                    {
                        ItemEnergy item = (ItemEnergy)gameObject;
                        if(!item.isActive(2) && !item.isActive(7))
                        {
                            hero.setEnergy(hero.getEnergy() + 1);
                            if(hero.getEnergy() >= 9)
                                hero.setEnergy(9);
                            Model.addSoundToList("/sounds/energy2.wav");
                            item.setState(2, 1);
                        }
                    } else
                    if(gameObject instanceof ItemKey)
                    {
                        ItemKey item = (ItemKey)gameObject;
                        if(!item.isActive(2) && !item.isActive(7))
                        {
                            item.setState(2, 1);
                            Hero.hasKey = true;
                            Model.addSoundToList("/sounds/bonus1.wav");
                        }
                    } else
                    if((gameObject instanceof ItemExit) && !hero.isActive(2))
                    {
                        Hero.isOverlappingExit = true;
                        Hero.exitX = gameObject.getX() + 2;
                        Hero.exitY = gameObject.getY() + 4;
                    }
            }

        }
        if(collObjects.size() > 0 && !hero.isActive(3))
            CollisionHandler.calculateCollision(hero, collObjects);
    }

    private void initDestroyedHero(Hero hero)
    {
        hero.setEnergy(0);
        hero.setLives(hero.getLives() - 1);
        Hero.isLoosing = true;
        Model.setStopAllSounds(true);
        Model.addSoundToList("/sounds/destroy1.wav");
    }
}
