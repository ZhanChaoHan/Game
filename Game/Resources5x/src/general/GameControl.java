// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GameControl.java

package general;

import gameobjects.*;
import java.util.*;
import levels.Levels;
import movement.MoveParticle;

// Referenced classes of package general:
//            ExplosionHandler, Model, Pauser

public class GameControl
{

    public GameControl()
    {
        levels = new Levels();
        levelCompleted = preGameOver = gameOver = gameCompleted = levelRunning = false;
        drawHeroScore = false;
        bossMode = false;
    }

    public void updateSprite(Sprite sprite)
    {
        sprite.animate();
        if(sprite.isActive(0))
            sprite.isStopped();
        if(sprite.isActive(1))
            sprite.isMoving();
        if(sprite.isActive(6))
            sprite.isShooting();
        if(sprite.isActive(7))
            sprite.isDiverse();
        if(sprite.isActive(2))
            sprite.isHit();
        if(sprite.isActive(3))
            sprite.isDestroyed();
        if(sprite.isActive(4))
            sprite.isErased();
    }

    public void updateSprites(Collection sprites)
    {
        Sprite sprite;
        for(Iterator iterator = sprites.iterator(); iterator.hasNext(); updateSprite(sprite))
            sprite = (Sprite)iterator.next();

    }

    public void updateEnemies(ArrayList enemies)
    {
        for(Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            Enemy enemy = (Enemy)iterator.next();
            updateSprite(enemy);
            if(enemy.getExplosionHandler() != null)
            {
                Explosion ex;
                for(Iterator iterator1 = enemy.getExplosionHandler().getExplosions().iterator(); iterator1.hasNext(); updateSprite(ex))
                    ex = (Explosion)iterator1.next();

            }
        }

    }

    public void checkCollision(Model model)
    {
        for(Iterator iterator = model.getEnemies().iterator(); iterator.hasNext();)
        {
            Enemy enemy = (Enemy)iterator.next();
            if(enemy.getX() > -120)
            {
                enemy.checkCollision(model.getTiles());
                enemy.checkAmmoCollision(model.getAmmos());
                if(enemy.isActive(5))
                    enemy.isColliding(model);
                if((enemy instanceof Boss1) && bossMode && Boss1.energy <= 0)
                    enemy.getExplosionHandler().checkExplosionTime((Boss1)enemy);
            }
        }

        for(Iterator iterator1 = model.getAmmos().iterator(); iterator1.hasNext();)
        {
            Ammo ammo = (Ammo)iterator1.next();
            if(ammo.getX() > -120)
            {
                ammo.checkInGameArea();
                ammo.checkTileCollision(model.getTiles());
                ammo.checkEnemyCollision(model.getEnemies());
                ammo.checkItemCollision(model.getItems());
                if(ammo.isActive(5))
                    ammo.isColliding();
            }
        }

        Hero hero = model.getHero();
        hero.checkBorderCollision();
        hero.checkEndOfLevelCollision();
        if(bossMode && Boss1.energy > 0 || !bossMode)
        {
            hero.checkHeroCollision(model.getEnemies());
            hero.checkHeroCollision(model.getAmmos());
        }
        hero.checkCollision(model.getItems());
        if(ItemExit.hit)
        {
            model.setLevel(3);
            model.init();
        }
        if(hero.isActive(5))
            hero.isColliding(model);
    }

    public void removeTexts(ArrayList texts)
    {
        for(int i = 0; i < texts.size(); i++)
        {
            if(!((Text)texts.get(i)).isActive(4))
                continue;
            texts.remove(i);
            break;
        }

    }

    public void checkShooting(ArrayList enemies, ArrayList ammos, Hero hero)
    {
        Enemy enemy;
        for(Iterator iterator = enemies.iterator(); iterator.hasNext(); enemy.checkShooting(ammos, hero))
            enemy = (Enemy)iterator.next();

        hero.checkShooting(ammos);
    }

    public void checkGameOver(Model model)
    {
        if(model.getHero() != null)
        {
            if(!levelCompleted && !levelRestart && !gameOver && levelRunning && model.getHero().isActive(4))
            {
                if(model.getHero().getLives() == 0)
                {
                    preGameOver = true;
                } else
                {
                    model.getHero().setInitType("restart");
                    levelRestart = true;
                }
                Pauser.setPausTime(13D);
                levelRunning = false;
            }
            if(!Pauser.paused && levelRestart && !blackScreen)
            {
                blackScreen = true;
                Pauser.setPausTime(13D);
            }
            if(!Pauser.paused && levelRestart && blackScreen)
            {
                model.setLevelRestart(false);
                model.setLevelRunning(true);
                model.initRestart();
                Model.setStopAllSounds(true);
            }
            if(!Pauser.paused && preGameOver)
            {
                preGameOver = false;
                gameOver = true;
                Pauser.setPausTime(27D);
                Model.setStopAllSounds(true);
            }
            if(!Pauser.paused && gameOver)
            {
                gameOver = false;
                model.setLevel(0);
                Hero.scrollPrevX = 0;
                Model.screens.put(Model.screenType.TITLE, Boolean.valueOf(true));
                model.setMarkedIndex(1);
                blackScreen = false;
                Model.setStopAllSounds(true);
                Model.addSoundToList("/sounds/intro2.wav");
            }
        }
    }

    public void checkLevelCompleted(Model model)
    {
        if(!Pauser.paused && !blackScreen && (Hero.isExiting || Boss1.isDefeated))
        {
            if(model.getLevel() < 4)
                levelCompleted = true;
            else
                gameCompleted = true;
            Model.setStopAllSounds(true);
            Hero.isExiting = Boss1.isDefeated = false;
            blackScreen = true;
            Pauser.setPausTime(15D);
            levelRunning = false;
        }
        if(!Pauser.paused && levelCompleted && blackScreen)
        {
            model.getHero().setInitType("levelcompleted");
            model.setLevelCompleted(false);
            model.setLevel(model.getLevel() + 1);
            model.init();
        }
        if(!Pauser.paused && gameCompleted && blackScreen)
        {
            blackScreen = false;
            Model.screens.put(Model.screenType.THEEND, Boolean.valueOf(true));
        }
    }

    public void initLevel(int level, ArrayList distances, ArrayList tiles, ArrayList enemies, ArrayList items, ArrayList ammos, Hero hero)
    {
        distances.clear();
        tiles.clear();
        enemies.clear();
        items.clear();
        levels.loadLevel(level, distances, tiles, enemies, items, hero);
        ammos.clear();
        for(int i = 0; i < 140; i++)
            ammos.add(new Ammo("ammo1", 0, 0, 0, 0, null, null, 4, 0.0D, 0.0D, 0, 0.0F, 0, null, 2, 0.0D));

        levelCompleted = gameOver = gameCompleted = false;
        levelRunning = true;
    }

    public void initParticles(ArrayList p)
    {
        for(int i = 0; i < 50; i++)
            p.add(new Particle((new StringBuilder("particle")).append((int)(Math.random() * 0.0D + 1.0D)).toString(), (int)(Math.random() * 120D), (int)(Math.random() * 90D), 10, 15, new MoveParticle(), 45D, 0.0F, 1, 1.0D));

    }

    public void setLevelRunning(boolean levelRunning)
    {
        this.levelRunning = levelRunning;
    }

    public boolean getLevelRunning()
    {
        return levelRunning;
    }

    public void setLevelCompleted(boolean levelCompleted)
    {
        this.levelCompleted = levelCompleted;
    }

    public boolean getLevelCompleted()
    {
        return levelCompleted;
    }

    public void setGameCompleted(boolean gameCompleted)
    {
        this.gameCompleted = gameCompleted;
    }

    public boolean getGameCompleted()
    {
        return gameCompleted;
    }

    public void setGameOver(boolean gameOver)
    {
        this.gameOver = gameOver;
    }

    public boolean getGameOver()
    {
        return gameOver;
    }

    public void setLevelRestart(boolean levelRestart)
    {
        this.levelRestart = levelRestart;
    }

    public boolean getLevelRestart()
    {
        return levelRestart;
    }

    public boolean isDrawHeroScore()
    {
        return drawHeroScore;
    }

    public void setDrawHeroScore(boolean drawHeroScore)
    {
        this.drawHeroScore = drawHeroScore;
    }

    public static boolean isBlackScreen()
    {
        return blackScreen;
    }

    public static void setBlackScreen(boolean blackScreen)
    {
        blackScreen = blackScreen;
    }

    public boolean isPreGameOver()
    {
        return preGameOver;
    }

    public void setPreGameOver(boolean preGameOver)
    {
        this.preGameOver = preGameOver;
    }

    private final int MAXAMMOS = 140;
    private final int MAXPARTICLES = 50;
    public static boolean online = true;
    private Levels levels;
    private boolean levelRunning;
    private boolean levelCompleted;
    private boolean gameCompleted;
    private boolean preGameOver;
    private boolean gameOver;
    private boolean levelRestart;
    private boolean drawHeroScore;
    public static boolean bossMode;
    public static boolean bossLevel;
    public static boolean blackScreen = false;

}
