// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Model.java

package general;

import gameobjects.*;
import java.util.*;
import movement.MoveHeroPlatform;

// Referenced classes of package general:
//            GameControl, ImageLoader, SoundLoader, Pauser

public class Model
{
    public enum screenType 
    {

        TITLE,INFO,INTRO,LOADINGLEVEL,THEEND
    }


    public Model()
    {
        init = false;
        initRestart = false;
        gameControl = new GameControl();
        distances = new ArrayList();
        tiles = new ArrayList();
        enemies = new ArrayList();
        items = new ArrayList();
        ammos = new ArrayList();
        texts = new ArrayList();
        screens = new HashMap();
        screens.put(screenType.TITLE, Boolean.valueOf(true));
        screens.put(screenType.INFO, Boolean.valueOf(false));
        screens.put(screenType.INTRO, Boolean.valueOf(false));
        screens.put(screenType.LOADINGLEVEL, Boolean.valueOf(false));
        screens.put(screenType.THEEND, Boolean.valueOf(false));
        imageLoader = new ImageLoader();
        allImages = new ArrayList();
        imageLoader.loadImages(allImages);
        soundLoader = new SoundLoader();
        allSounds = new ArrayList();
        soundLoader.loadSounds(allSounds);
        soundList = new ArrayList();
        exit = false;
        paused = false;
        markedIndex = 1;
        level = 0;
        setMarkedXY();
        playerName = null;
    }

    public void init()
    {
        init = true;
    }

    public void initRestart()
    {
        initRestart = true;
    }

    public void updateGame()
    {
        if(init && !((Boolean)screens.get(screenType.LOADINGLEVEL)).booleanValue())
        {
            if(level == 0)
            {
                try
                {
                    Thread.sleep(2000L);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                totalTime = 0.0D;
                hero = new Hero("hero", 0, 0, 9, 12, 1, 0.33000000000000002D, 4, 1.1000000000000001D);
                level = 1;
            }
            GameControl.bossLevel = false;
            addSoundToList(levelMusic[level - 1]);
            paused = false;
            gameControl.initLevel(level, distances, tiles, enemies, items, ammos, hero);
            gameControl.setLevelCompleted(false);
            gameControl.setGameOver(false);
            gameControl.setGameCompleted(false);
            GameControl.bossMode = ItemExit.hit = false;
            markedIndex = 1;
            texts.clear();
            paus = new Select("paus", 0, 0, 39, 11, 2, 1.3999999999999999D);
            extraLife = new Select("extralife_text", 0, 0, 25, 10, 2, 1.3999999999999999D);
            setDrawHeroScore(true);
            init = false;
        } else
        if(initRestart)
        {
            GameControl.setBlackScreen(false);
            Hero.isRestart = true;
            gameControl.initLevel(level, distances, tiles, enemies, items, ammos, hero);
            GameControl.bossMode = ItemExit.hit = false;
            addSoundToList(levelMusic[level - 1]);
            initRestart = false;
        }
        if(gameControl.getLevelRunning() && !paused && !gameControl.getLevelCompleted())
        {
            GameControl.setBlackScreen(false);
            gameControl.checkShooting(enemies, ammos, hero);
            hero.setModelTiles(tiles);
            hero.setModelItems(items);
            gameControl.updateSprite(hero);
            gameControl.updateSprites(distances);
            gameControl.updateEnemies(enemies);
            gameControl.updateSprites(items);
            gameControl.updateSprites(ammos);
            gameControl.updateSprites(texts);
            if(extraLife.isActive(7))
                gameControl.updateSprite(extraLife);
            gameControl.checkCollision(this);
            gameControl.removeTexts(texts);
            hero.setScrolling();
            totalTime += elapsedTime;
        }
        gameControl.checkLevelCompleted(this);
        gameControl.checkGameOver(this);
        if(paused)
            gameControl.updateSprite(paus);
        if(!paused)
            Pauser.calculatePaus();
    }

    public void setElapsedTime(long t)
    {
        elapsedTime = t;
    }

    public double getElapsedTime()
    {
        return elapsedTime;
    }

    public int getTotalTime()
    {
        return (int)(totalTime / 1000000D / 60D);
    }

    public void setLevelRunning(boolean levelRunning)
    {
        gameControl.setLevelRunning(levelRunning);
    }

    public boolean getLevelRunning()
    {
        return gameControl.getLevelRunning();
    }

    public void setLevelCompleted(boolean levelCompleted)
    {
        gameControl.setLevelCompleted(levelCompleted);
    }

    public boolean getLevelCompleted()
    {
        return gameControl.getLevelCompleted();
    }

    public void setGameCompleted(boolean gameCompleted)
    {
        gameControl.setGameCompleted(gameCompleted);
    }

    public boolean getGameCompleted()
    {
        return gameControl.getGameCompleted();
    }

    public boolean isPreGameOver()
    {
        return gameControl.isPreGameOver();
    }

    public void setGameOver(boolean gameOver)
    {
        gameControl.setGameOver(gameOver);
    }

    public boolean getGameOver()
    {
        return gameControl.getGameOver();
    }

    public void setLevelRestart(boolean levelRestart)
    {
        gameControl.setLevelRestart(levelRestart);
    }

    public boolean getLevelRestart()
    {
        return gameControl.getLevelRestart();
    }

    public boolean isExit()
    {
        return exit;
    }

    public void setExit(boolean exit)
    {
        this.exit = exit;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getLevel()
    {
        return level;
    }

    public ArrayList getAllImages()
    {
        return allImages;
    }

    public Hero getHero()
    {
        return hero;
    }

    public void setHeroDir(int dir, boolean v)
    {
        hero.setDir(dir, v);
    }

    public boolean isInHeroDir(int dir)
    {
        return hero.isInDir(dir);
    }

    public void setHeroShooting(boolean s, int type)
    {
        hero.setShooting(s, type);
    }

    public boolean isDrawHeroScore()
    {
        return gameControl.isDrawHeroScore();
    }

    public void setDrawHeroScore(boolean drawHeroScore)
    {
        gameControl.setDrawHeroScore(drawHeroScore);
    }

    public void deleteHero()
    {
        hero = null;
    }

    public void setHeroClimbing(String dir)
    {
        if(Hero.isOverlappingLadder)
        {
            Hero.isClimbing = true;
            hero.getMovement().initJump();
            if(dir.equals("up"))
            {
                hero.setDir(2, true);
                hero.setDir(3, false);
            } else
            if(dir.equals("down"))
            {
                hero.setDir(3, true);
                hero.setDir(2, false);
            }
        }
    }

    public static boolean inGameArea(GameObject g, int gameX, int gameY)
    {
        return g.getX() - gameX >= -g.getWidth() && g.getX() - gameX <= 120 && g.getY() - gameY >= -g.getHeight() && g.getY() - gameY <= 90;
    }

    public static boolean inGameFieldArea(GameObject g, int gameX, int gameY)
    {
        return g.getX() - gameX >= 0 && (g.getX() + g.getWidth()) - gameX <= 120 && g.getY() - gameY >= 0 && (g.getY() + g.getHeight()) - gameY <= 90;
    }

    public ArrayList getDistances()
    {
        return distances;
    }

    public ArrayList getTiles()
    {
        return tiles;
    }

    public ArrayList getEnemies()
    {
        return enemies;
    }

    public ArrayList getItems()
    {
        return items;
    }

    public ArrayList getHeroItems()
    {
        return hero.getItems();
    }

    public ArrayList getAmmos()
    {
        return ammos;
    }

    public ArrayList getParticles()
    {
        return particles;
    }

    public ArrayList getTexts()
    {
        return texts;
    }

    public Select getPaus()
    {
        return paus;
    }

    public Select getSelect()
    {
        return select;
    }

    public void setMarkedXY()
    {
        markedX = 180;
        markedY = 260 - 40 * markedIndex - 10;
    }

    public void setMarkedArea(boolean up, boolean down)
    {
        markedNoMenuItem = false;
        if(up)
        {
            markedIndex++;
            if(markedIndex > 1 && ((Boolean)screens.get(screenType.TITLE)).booleanValue())
                markedIndex = 1;
            if(markedIndex > 1)
                markedIndex = 0;
        } else
        if(down)
        {
            markedIndex--;
            if(markedIndex < 0 || markedIndex == 0 && ((Boolean)screens.get(screenType.TITLE)).booleanValue())
                markedIndex = 1;
        }
        setMarkedXY();
    }

    public void setMarkedAreaMouse(int mX, int mY)
    {
        markedNoMenuItem = true;
        if(mX >= 180 && mX <= 380)
        {
            if(((Boolean)screens.get(screenType.TITLE)).booleanValue())
            {
                if(mY >= 220 && mY <= 249)
                {
                    markedIndex = 1;
                    markedNoMenuItem = false;
                }
            } else
            if(((Boolean)screens.get(screenType.INFO)).booleanValue() && mY >= 260 && mY <= 289)
            {
                markedIndex = 0;
                markedNoMenuItem = false;
            }
            setMarkedXY();
        }
    }

    public void setMarkedIndex(int markedIndex)
    {
        this.markedIndex = markedIndex;
        setMarkedArea(false, false);
    }

    public int getMarkedIndex()
    {
        return markedIndex;
    }

    public int getMarkedX()
    {
        return markedX;
    }

    public int getMarkedY()
    {
        return markedY;
    }

    public static void addSoundToList(String sound)
    {
        synchronized(soundList)
        {
            boolean soundExists = false;
            for(Iterator iterator = soundList.iterator(); iterator.hasNext();)
            {
                String s = (String)iterator.next();
                if(s.equals(sound))
                    soundExists = true;
            }

            if(!soundExists)
                soundList.add(sound);
        }
    }


    public static ArrayList<String> getSoundList()
    {
      synchronized (soundList) {
        return (ArrayList)soundList;
      }
    }


    public static void clearSoundList()
    {
        synchronized(soundList)
        {
            soundList.clear();
        }
    }

    public static synchronized void stopAllSounds()
    {
        stopAllSounds = true;
    }

    public static synchronized void setStopAllSounds(boolean stopAllSounds)
    {
        stopAllSounds = stopAllSounds;
    }

    public static synchronized boolean isStopAllSounds()
    {
        return stopAllSounds;
    }

    public static final int MAXMARKEDINDEX = 1;
    public static final int TOTALTIMEDIVIDER = 0xf4240;
    private final int MARKEDX = 180;
    private final int MARKEDY = 260;
    private final int MARKEDSTEP = 40;
    private final int MARKEDWIDTH = 200;
    private final int MARKEDHEIGHT = 29;
    private final int MARKEDOFFSETY = 10;
    public static boolean paused = false;
    public static final int PLAY = 1;
    public static final int EXIT = 0;
    private boolean exit;
    public static HashMap screens;
    private int level;
    public static double elapsedTime;
    public static double totalTime;
    private int markedX;
    private int markedY;
    private int markedIndex;
    public static int gameWidth;
    public static int gameHeight;
    public static String playerName;
    private Hero hero;
    private Select paus;
    private Select select;
    public static Select extraLife;
    public static Sprite scrollerX;
    private ArrayList distances;
    private ArrayList tiles;
    private ArrayList enemies;
    private ArrayList items;
    private ArrayList ammos;
    private ArrayList texts;
    private ArrayList particles;
    private GameControl gameControl;
    private ImageLoader imageLoader;
    private SoundLoader soundLoader;
    private ArrayList allImages;
    public static ArrayList allSounds;
    private static List soundList = Collections.synchronizedList(new ArrayList());
    public static boolean isMute = false;
    public static boolean stopAllSounds = false;
    private String levelMusic[] = {
        "/sounds/title1.wav", "/sounds/title1.wav", "/sounds/title1.wav", "/sounds/boss_music1.wav", "/sounds/title1.wav", "/sounds/title1.wav", "/sounds/title1.wav", "/sounds/boss_music1.wav", "/sounds/title3.wav", "/sounds/title3.wav", 
        "/sounds/boss_music1.wav", "/sounds/title1.wav", "/sounds/boss1.wav"
    };
    public static boolean markedNoMenuItem = true;
    private boolean init;
    private boolean initRestart;

}
