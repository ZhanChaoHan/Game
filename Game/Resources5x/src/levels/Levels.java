// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Levels.java

package levels;

import collision.CollisionBoss;
import collision.CollisionEnemy;
import gameobjects.*;
import general.*;
import java.util.ArrayList;
import movement.*;

// Referenced classes of package levels:
//            Level, Level1, Level2, Level3, 
//            Level4, Level5, Level6, Level7, 
//            Level8, Level9, Level10, Level11, 
//            Level12, Level13

public class Levels
{

    public Levels()
    {
        levels = new Level[13];
        for(int i = 0; i < levels.length; i++)
            if(i == 0)
                levels[i] = new Level1();
            else
            if(i == 1)
                levels[i] = new Level2();
            else
            if(i == 2)
                levels[i] = new Level3();
            else
            if(i == 3)
                levels[i] = new Level4();
            else
            if(i == 4)
                levels[i] = new Level5();
            else
            if(i == 5)
                levels[i] = new Level6();
            else
            if(i == 6)
                levels[i] = new Level7();
            else
            if(i == 7)
                levels[i] = new Level8();
            else
            if(i == 8)
                levels[i] = new Level9();
            else
            if(i == 9)
                levels[i] = new Level10();
            else
            if(i == 10)
                levels[i] = new Level11();
            else
            if(i == 11)
                levels[i] = new Level12();
            else
            if(i == 12)
                levels[i] = new Level13();

    }

    public void loadLevel(int level, ArrayList distances, ArrayList tiles, ArrayList enemies, ArrayList items, Hero hero)
    {
        Model.gameWidth = levels[level - 1].getGameWidth();
        Model.gameHeight = levels[level - 1].getGameHeight();
        Object t[] = levels[level - 1].readTiles();
        loadTiles(tiles, t);
        Object e[] = levels[level - 1].readEnemies();
        loadEnemies(enemies, e);
        Object o[] = levels[level - 1].readItems();
        loadItems(items, o, hero);
    }

    private void loadTiles(ArrayList tiles, Object t[])
    {
        for(int i = 0; i < t.length; i += 6)
        {
            String name = (String)t[i];
            tiles.add(new Tile(name, ((Integer)t[i + 1]).intValue(), ((Integer)t[i + 2]).intValue(), ((Integer)t[i + 3]).intValue(), ((Integer)t[i + 4]).intValue(), ((Integer)t[i + 5]).intValue()));
        }

    }

    private void loadEnemies(ArrayList enemies, Object e[])
    {
        for(int i = 0; i < e.length; i += 6)
        {
            String name = (String)e[i];
            int type = ((Integer)e[i + 5]).intValue();
            if(type == 1)
            {
                AmmoHandlerEnemy a = new AmmoHandlerEnemy(AmmoOrder.getAmmoOrderEnemy(type));
                ExplosionHandler ex = new ExplosionHandler(ExplosionOrder.getOrder(type));
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, new MoveHorzVert("INDIROFENEMY"), new CollisionEnemy(1, 26), a, ex, 1, 0.80000000000000004D, 0.0D, 0.0F, 125, 10, "boss_destroy", 3, 2.5D));
            } else
            if(type == 6)
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, new MoveHorzVert(), new CollisionEnemy(1, 7), null, null, 1, 0.40000000000000002D, 0.0D, 0.0F, 125, 1, "destroy2", 4, 2.5D));
            else
            if(type == 11)
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, new MoveHorzVert(), new CollisionEnemy(1, 15), null, null, 1, 0.55000000000000004D, 0.0D, 0.0F, 125, 2, "destroy2", 4, 2D));
            else
            if(type == 12)
            {
                AmmoHandlerEnemy a = new AmmoHandlerEnemy(AmmoOrder.getAmmoOrderEnemy(type));
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, new MovePath2(MovementOrder.moveOrder(type)), new CollisionEnemy(1, 15), a, null, 1, 0.5D, 0.0D, 0.0F, 125, 2, "destroy2", 4, 2D));
            } else
            if(type == 13)
            {
                AmmoHandlerEnemy a = new AmmoHandlerEnemy(AmmoOrder.getAmmoOrderEnemy(type));
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, new MoveHorzVert(), new CollisionEnemy(1, 15), a, null, 1, 0.5D, 0.0D, 0.0F, 125, 2, "destroy2", 4, 2D));
            } else
            if(type == 16)
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, new MoveHorzVert("INDIROFENEMY"), new CollisionEnemy(2, 50), null, null, 1, 4.9000000000000004D, 4.9000000000000004D, 0.7F, 7000, 26, "destroy2", 2, 2.5D));
            else
            if(type == 17)
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, new MoveHorzVert("INDIROFENEMY"), new CollisionEnemy(2, 12), null, null, 1, 1.8999999999999999D, 1.8999999999999999D, 0.7F, 7000, 26, "destroy2", 2, 2.5D));
            else
            if(type == 21)
            {
                AmmoHandlerEnemy a = new AmmoHandlerEnemy(AmmoOrder.getAmmoOrderEnemy(type));
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, null, null, a, null, 1, 6D, 0.0D, 0.0F, 0, -100, "destroy2", 1, 4D));
            } else
            if(type == 26)
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 2, new MoveHorzVert(), new CollisionEnemy(1, 15), null, null, 1, 0.0D, 2.5D, 0.0F, 700, -100, "destroy2", 2, 0.5D));
            else
            if(type == 27)
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 2, new MoveHorzVert(), new CollisionEnemy(1, 15), null, null, 1, 0.0D, 1.1000000000000001D, 0.0F, 700, -100, "destroy2", 2, 1.0D));
            else
            if(type == 28)
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 2, new MoveHorzVert(), new CollisionEnemy(1, 15), null, null, 1, 0.0D, 1.7D, 0.0F, 700, -100, "destroy2", 2, 0.69999999999999996D));
            else
            if(type == 31)
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, new MoveWaterFall(7D, 8D), null, null, null, 1, 6D, 0.0D, 0.0F, 125, -10, "destroy2", 3, 1.3999999999999999D));
            else
            if(type == 32)
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, new MoveWaterFall(17D, 13D), null, null, null, 1, 6D, 0.0D, 0.0F, 125, -10, "destroy2", 3, 1.3999999999999999D));
            else
            if(type == 36)
            {
                AmmoHandlerEnemy a = new AmmoHandlerEnemy(AmmoOrder.getAmmoOrderEnemy(type));
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, new MoveHorzVert(), new CollisionEnemy(1, 15), a, null, 1, 0.55000000000000004D, 0.0D, 0.0F, 125, 2, "destroy2", 4, 2D));
            } else
            if(type == 41)
            {
                AmmoHandlerEnemy a = new AmmoHandlerEnemy(AmmoOrder.getAmmoOrderEnemy(type));
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, new MoveHorzVert("INDIROFENEMY"), new CollisionEnemy(1, 12), a, null, 1, 0.34999999999999998D, 0.0D, 0.0F, 125, 7, "destroy2", 3, 1.5D));
            } else
            if(type == 46)
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, null, null, null, null, 0, 0.34999999999999998D, 0.0D, 0.0F, 125, -10, "", 1, 0.0D));
            else
            if(type == 51)
            {
                AmmoHandlerBoss a = new AmmoHandlerBoss(AmmoOrder.getAmmoOrderBoss(type), false);
                enemies.add(new Boss2(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, new MoveBoss(MovementOrder.moveOrderBoss(type)), new CollisionBoss(20), a, 1, 0.0D, 0.0D, 0.0F, -100, 2, 1.5D));
            } else
            if(type == 81)
            {
                ExplosionHandler ex = new ExplosionHandler(ExplosionOrder.getOrder(type));
                enemies.add(new Boss1(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, null, new CollisionBoss(20), null, ex, 1, 0.0D, 0.0D, 10F, 0x1d4c0, 80, 1, 1.0D));
            } else
            if(type == 56)
            {
                int paus[] = {
                    30
                };
                int move[] = {
                    50
                };
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, new MoveJump(7D, paus, move, false), new CollisionEnemy(0, 15), null, null, 1, 0.0D, 0.0D, 0.0F, 0, -100, "", 2, 1.0D));
            } else
            if(type == 61)
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, null, new CollisionEnemy(0, 15), null, null, 1, 0.0D, 0.0D, 0.0F, 0, 1, "destroy2", 1, 2D));
            else
            if(type == 66)
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 3, new MoveHorzVert(), new CollisionEnemy(1, 0), null, null, 1, 0.0D, 0.5D, 0.0F, 0, -100, "destroy2", 3, 3D));
            else
            if(type == 67)
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 3, new MoveHorzVert(), new CollisionEnemy(1, 0), null, null, 1, 0.0D, 0.25D, 0.0F, 0, -100, "destroy2", 3, 4D));
            else
            if(type == 71)
            {
                AmmoHandlerBoss a = new AmmoHandlerBoss(AmmoOrder.getAmmoOrderBoss(type), false);
                ExplosionHandler ex = new ExplosionHandler(ExplosionOrder.getOrder(type));
                enemies.add(new Boss1(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 2, new MoveBoss(MovementOrder.moveOrderBoss(type)), new CollisionBoss(26), a, ex, 1, 10D, 10D, 0.0F, 0x1d4c0, 30, 2, 1.0D));
            } else
            if(type == 76)
            {
                AmmoHandlerEnemy a = new AmmoHandlerEnemy(AmmoOrder.getAmmoOrderEnemy(type));
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, null, new CollisionEnemy(1, 15), a, null, 1, 6D, 0.0D, 0.0F, 0, 2, "destroy2", 2, 1.3D));
            } else
            if(type == 82)
            {
                AmmoHandlerEnemy a = new AmmoHandlerEnemy(AmmoOrder.getAmmoOrderEnemy(type), 1600, 100);
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, null, new CollisionEnemy(1, 96), a, null, 1, 6D, 0.0D, 0.0F, 0, -100, "destroy2", 1, 1.0D));
            } else
            if(type == 86)
            {
                AmmoHandlerEnemy a = new AmmoHandlerEnemy(AmmoOrder.getAmmoOrderEnemy(type), 120, 180);
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, new MoveHorzVert(), new CollisionEnemy(1, 12), a, null, 1, 0.0D, 0.0D, 0.0F, 0, -100, "destroy2", 2, 3D));
            } else
            if(type == 93)
            {
                AmmoHandlerEnemy a = new AmmoHandlerEnemy(AmmoOrder.getAmmoOrderEnemy(type), 600, 90);
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, new MoveHorzVert(), new CollisionEnemy(1, 12), a, null, 1, 0.0D, 0.0D, 0.0F, 0, -100, "destroy2", 2, 3D));
            } else
            if(type == 96)
            {
                int paus[] = {
                    15, 20
                };
                int move[] = {
                    7, 12
                };
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, new MoveJump(3D, paus, move, true), new CollisionEnemy(0, 15), null, null, 1, 0.0D, 0.0D, 0.0F, 200, 1, "destroy2", 2, 2D));
            } else
            if(type == 97)
            {
                int paus[] = {
                    20, 15
                };
                int move[] = {
                    7, 12
                };
                AmmoHandlerEnemy a = new AmmoHandlerEnemy(AmmoOrder.getAmmoOrderEnemy(type));
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, new MoveJump(3D, paus, move, true), new CollisionEnemy(0, 15), a, null, 1, 0.0D, 0.0D, 0.0F, 200, 1, "destroy2", 2, 2D));
            } else
            if(type == 46)
            {
                int paus[] = {
                    20, 15
                };
                int move[] = {
                    7, 12
                };
                AmmoHandlerEnemy a = new AmmoHandlerEnemy(AmmoOrder.getAmmoOrderEnemy(type));
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, new MoveJump(3D, paus, move, true), new CollisionEnemy(0, 15), a, null, 1, 0.0D, 0.0D, 0.0F, 200, 1, "destroy2", 2, 2D));
            } else
            if(type == 101)
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, null, null, null, null, 1, 0.0D, 55D, 0.0F, 200, -10, "destroy2", 3, 2.5D));
            else
            if(type == 106)
            {
                AmmoHandlerBoss a = new AmmoHandlerBoss(AmmoOrder.getAmmoOrderBoss(type), false);
                ExplosionHandler ex = new ExplosionHandler(ExplosionOrder.getOrder(type));
                enemies.add(new Boss1(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, new MoveBoss(MovementOrder.moveOrderBoss(type)), new CollisionBoss(20), a, ex, 1, 0.0D, 0.0D, 0.0F, 0x1d4c0, 50, 3, 2D));
            } else
            if(type == 111)
            {
                AmmoHandlerEnemy a = new AmmoHandlerEnemy(AmmoOrder.getAmmoOrderEnemy(type));
                int paus[] = {
                    15, 20
                };
                int move[] = {
                    7, 30
                };
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, new MoveJump(3D, paus, move, true), new CollisionEnemy(0, 15), a, null, 1, 0.0D, 0.0D, 0.0F, 200, 6, "destroy2", 2, 2D));
            } else
            if(type == 112)
            {
                AmmoHandlerEnemy a = new AmmoHandlerEnemy(AmmoOrder.getAmmoOrderEnemy(type));
                int paus[] = {
                    15, 20
                };
                int move[] = {
                    7, 20
                };
                enemies.add(new Enemy(name, ((Integer)e[i + 1]).intValue(), ((Integer)e[i + 2]).intValue(), ((Integer)e[i + 3]).intValue(), ((Integer)e[i + 4]).intValue(), 0, new MoveJump(3D, paus, move, true), new CollisionEnemy(0, 15), a, null, 1, 0.0D, 0.0D, 0.0F, 200, 6, "destroy2", 2, 2D));
            }
        }

    }

    private void loadItems(ArrayList items, Object o[], Hero hero)
    {
        for(int i = 0; i < o.length; i += 6)
        {
            String name = (String)o[i];
            if(((Integer)o[i + 5]).intValue() == 1)
                hero.init(((Integer)o[i + 1]).intValue(), ((Integer)o[i + 2]).intValue());
            else
            if(((Integer)o[i + 5]).intValue() == 2)
                items.add(new ItemAmmo(name, ((Integer)o[i + 1]).intValue(), ((Integer)o[i + 2]).intValue(), ((Integer)o[i + 3]).intValue(), ((Integer)o[i + 4]).intValue(), 3, 2D));
            else
            if(((Integer)o[i + 5]).intValue() == 3)
                items.add(new ItemEnergy(name, ((Integer)o[i + 1]).intValue(), ((Integer)o[i + 2]).intValue(), ((Integer)o[i + 3]).intValue(), ((Integer)o[i + 4]).intValue(), 3, 2D));
            else
            if(((Integer)o[i + 5]).intValue() == 4)
                items.add(new ItemCharacter(name, ((Integer)o[i + 1]).intValue(), ((Integer)o[i + 2]).intValue(), ((Integer)o[i + 3]).intValue(), ((Integer)o[i + 4]).intValue(), new MoveHorzVert("NODIR"), 0.0D, 0.0D, 2, 1.5D));
            else
            if(((Integer)o[i + 5]).intValue() == 5)
                items.add(new ItemCharacter(name, ((Integer)o[i + 1]).intValue(), ((Integer)o[i + 2]).intValue(), ((Integer)o[i + 3]).intValue(), ((Integer)o[i + 4]).intValue(), new MoveHorzVert("INFINITE"), 0.20000000000000001D, 0.0D, 3, 6D));
            else
            if(((Integer)o[i + 5]).intValue() == 6)
                items.add(new ItemDoor(name, ((Integer)o[i + 1]).intValue(), ((Integer)o[i + 2]).intValue(), ((Integer)o[i + 3]).intValue(), ((Integer)o[i + 4]).intValue(), 2, 3.5D));
            else
            if(((Integer)o[i + 5]).intValue() == 7)
                items.add(new ItemCharacter(name, ((Integer)o[i + 1]).intValue(), ((Integer)o[i + 2]).intValue(), ((Integer)o[i + 3]).intValue(), ((Integer)o[i + 4]).intValue(), new MoveHorzVert("PREDEFMOVE", 9), 1.0D, 0.0D, 4, 3.7999999999999998D));
            else
            if(((Integer)o[i + 5]).intValue() == 8)
                items.add(new ItemCharacter(name, ((Integer)o[i + 1]).intValue(), ((Integer)o[i + 2]).intValue(), ((Integer)o[i + 3]).intValue(), ((Integer)o[i + 4]).intValue(), new MoveHorzVert("NODIR"), 0.0D, 0.0D, 2, 1.5D));
            else
            if(((Integer)o[i + 5]).intValue() == 9)
                items.add(new ItemCharacter(name, ((Integer)o[i + 1]).intValue(), ((Integer)o[i + 2]).intValue(), ((Integer)o[i + 3]).intValue(), ((Integer)o[i + 4]).intValue(), new MoveHorzVert("NODIR"), 0.0D, 0.0D, 3, 5.2000000000000002D));
            else
            if(((Integer)o[i + 5]).intValue() == 10)
                items.add(new ItemCharacter(name, ((Integer)o[i + 1]).intValue(), ((Integer)o[i + 2]).intValue(), ((Integer)o[i + 3]).intValue(), ((Integer)o[i + 4]).intValue(), new MoveHorzVert("NODIR"), 0.0D, 0.0D, 2, 1.8D));
            else
            if(((Integer)o[i + 5]).intValue() == 11)
                items.add(new ItemCharacter(name, ((Integer)o[i + 1]).intValue(), ((Integer)o[i + 2]).intValue(), ((Integer)o[i + 3]).intValue(), ((Integer)o[i + 4]).intValue(), new MoveHorzVert("NODIR"), 0.0D, 0.0D, 2, 1.8D));
            else
            if(((Integer)o[i + 5]).intValue() == 12)
                items.add(new ItemCharacter(name, ((Integer)o[i + 1]).intValue(), ((Integer)o[i + 2]).intValue(), ((Integer)o[i + 3]).intValue(), ((Integer)o[i + 4]).intValue(), new MoveHorzVert("NODIR"), 0.0D, 0.0D, 2, 1.8D));
            else
            if(((Integer)o[i + 5]).intValue() == 13)
                items.add(new ItemCharacter(name, ((Integer)o[i + 1]).intValue(), ((Integer)o[i + 2]).intValue(), ((Integer)o[i + 3]).intValue(), ((Integer)o[i + 4]).intValue(), new MoveHorzVert("NODIR"), 0.0D, 0.0D, 2, 1.8D));
            else
            if(((Integer)o[i + 5]).intValue() == 14)
                items.add(new ItemCharacter(name, ((Integer)o[i + 1]).intValue(), ((Integer)o[i + 2]).intValue(), ((Integer)o[i + 3]).intValue(), ((Integer)o[i + 4]).intValue(), new MoveHorzVert("INFINITE"), 1.0D, 0.0D, 2, 1.8D));
            else
            if(((Integer)o[i + 5]).intValue() == 15)
                items.add(new ItemCharacter(name, ((Integer)o[i + 1]).intValue(), ((Integer)o[i + 2]).intValue(), ((Integer)o[i + 3]).intValue(), ((Integer)o[i + 4]).intValue(), null, 1.0D, 0.0D, 4, 9D));
            else
            if(((Integer)o[i + 5]).intValue() == 16)
                items.add(new ItemCharacter(name, ((Integer)o[i + 1]).intValue(), ((Integer)o[i + 2]).intValue(), ((Integer)o[i + 3]).intValue(), ((Integer)o[i + 4]).intValue(), null, 1.0D, 0.0D, 2, 1.5D));
            else
            if(((Integer)o[i + 5]).intValue() == 17)
                items.add(new ItemBonus(name, ((Integer)o[i + 1]).intValue(), ((Integer)o[i + 2]).intValue(), ((Integer)o[i + 3]).intValue(), ((Integer)o[i + 4]).intValue(), 0, 3, 2D));
            else
            if(((Integer)o[i + 5]).intValue() == 18)
                items.add(new ItemCharacter(name, ((Integer)o[i + 1]).intValue(), ((Integer)o[i + 2]).intValue(), ((Integer)o[i + 3]).intValue(), ((Integer)o[i + 4]).intValue(), new MoveHorzVert("NODIR"), 0.0D, 0.0D, 3, 2D));
        }

    }

    private Level levels[];
}
