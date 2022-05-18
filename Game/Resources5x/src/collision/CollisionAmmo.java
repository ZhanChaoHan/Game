// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CollisionAmmo.java

package collision;

import gameobjects.*;
import general.Model;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package collision:
//            Collision, CollisionHandler

public class CollisionAmmo
    implements Collision
{

    public CollisionAmmo()
    {
    }

    public void checkCollision(Sprite sprite, Model model)
    {
        Ammo s = (Ammo)sprite;
        if(s.getBorderCollHorz() == 0 || s.getBorderCollVert() == 0)
        {
            s.clearStates();
            s.setState(4, 1);
        } else
        {
            ArrayList collHeroes = new ArrayList();
            ArrayList collTiles = new ArrayList();
            ArrayList collEnemies = new ArrayList();
            ArrayList collItems = new ArrayList();
            for(Iterator iterator = s.getCollisions().iterator(); iterator.hasNext();)
            {
                GameObject gameObject = (GameObject)iterator.next();
                if((gameObject instanceof Tile) && (gameObject.getOrder() == 2 || gameObject.getOrder() == 3))
                {
                    collTiles.add((Tile)gameObject);
                    s.getType();
                } else
                if(gameObject instanceof Enemy)
                {
                    Enemy enemy = (Enemy)gameObject;
                    if(s.getType() != 2 && enemy.getEnergy() != -10 && !enemy.isActive(3))
                    {
                        if(!enemy.isActive(7) || enemy.getExplosionHandler() == null)
                            collEnemies.add((Enemy)gameObject);
                        if(enemy.isLastHit())
                        {
                            if(enemy instanceof Boss1)
                            {
                                enemy.setState(7, 1);
                                enemy.setState(1, 0);
                            } else
                            if(enemy.getExplosionHandler() != null)
                            {
                                enemy.setState(7, 1);
                                enemy.setState(1, 0);
                            } else
                            {
                                enemy.setState(3, 1);
                            }
                            enemy.setLastHit(false);
                        }
                    }
                } else
                if(gameObject instanceof ItemDoor)
                    collItems.add((ItemDoor)gameObject);
                else
                if((gameObject instanceof Hero) && s.getType() == 2)
                    collHeroes.add((Hero)gameObject);
            }

            if(collHeroes.size() > 0 && !s.isActive(3))
            {
                CollisionHandler.calculateCollision(s, collHeroes);
                initDestroyed(s);
            } else
            if(collTiles.size() > 0 && !s.isActive(3))
            {
                CollisionHandler.calculateCollision(s, collTiles);
                initDestroyed(s);
            } else
            if(collEnemies.size() > 0 && !s.isActive(3))
            {
                CollisionHandler.calculateCollision(s, collEnemies);
                initDestroyed(s);
            } else
            if(collItems.size() > 0 && !s.isActive(3))
            {
                CollisionHandler.calculateCollision(s, collItems);
                initDestroyed(s);
            }
        }
    }

    private void initDestroyed(Ammo a)
    {
        a.setAngle(0.0F);
        a.setState(3, 1);
    }
}
