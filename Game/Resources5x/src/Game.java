// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Game.java

import gameobjects.Ammo;
import gameobjects.Boss1;
import gameobjects.Enemy;
import gameobjects.Explosion;
import gameobjects.GameObject;
import gameobjects.Hero;
import gameobjects.Select;
import general.C;
import general.ControlKeyboard;
import general.ControlMouse;
import general.ExplosionHandler;
import general.GameControl;
import general.Model;
import general.Pauser;
import general.Sound;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class Game extends Applet
{

    public Game()
    {
        soundThread = new SoundThread();
        start = true;
    }

    public void init()
    {
        model = new Model();
        ControlKeyboard controlKeyboard = new ControlKeyboard(model);
        addKeyListener(controlKeyboard);
        ControlMouse controlMouse = new ControlMouse(model);
        addMouseListener(controlMouse);
        addMouseMotionListener(controlMouse);
        bufferImage = createImage(120 * C.SIZE, 90 * C.SIZE);
        xg = (Graphics2D)bufferImage.getGraphics();
        t1 = t2 = 0L;
        loadImages();
        loadSounds();
        soundThread.start();
    }

    public int formatLevel(int level, String type)
    {
        int returnLevel = 0;
        int divider = 4;
        if(type.equals("world"))
        {
            returnLevel = level / divider + 1;
        } else
        {
            returnLevel = level % divider;
            if(returnLevel == 0)
                returnLevel = divider;
        }
        return returnLevel;
    }

    public void drawHUD()
    {
        xg.setColor(Color.black);
        xg.fillRect(0, 90 * C.SIZE, 120 * C.SIZE, 10 * C.SIZE);
        Hero hero = model.getHero();
        for(int x = C.SIZE * 2; x < hero.getLives() * 6 * C.SIZE + C.SIZE; x += 6 * C.SIZE)
            xg.drawImage((Image)images.get("/images/life.png"), x, C.RESIZEDWINDOWHEIGHT - 5 * C.SIZE, 4 * C.SIZE, 4 * C.SIZE, this);

        if(Model.extraLife.isActive(7))
            xg.drawImage((Image)images.get(Model.extraLife.getImage()), 47 * C.SIZE, 35 * C.SIZE, Model.extraLife.getWidth() * C.SIZE, Model.extraLife.getHeight() * C.SIZE, this);
        for(int x = 120 * C.SIZE - hero.getEnergy() * C.SIZE * 5; x < 120 * C.SIZE; x += 5 * C.SIZE)
            xg.drawImage((Image)images.get("/images/energy.png"), x, 90 * C.SIZE - 5 * C.SIZE, 3 * C.SIZE, 4 * C.SIZE, this);

        if(model.getLevel() % 4 == 0)
        {
            xg.drawImage((Image)images.get("/images/boss_text.png"), C.SIZE, 1 * C.SIZE, 15 * C.SIZE, 5 * C.SIZE, this);
        } else
        {
            xg.drawImage((Image)images.get((new StringBuilder("/images/digit_")).append(formatLevel(model.getLevel(), "world")).append(".png").toString()), 2 * C.SIZE, 1 * C.SIZE, 3 * C.SIZE, 5 * C.SIZE, this);
            xg.setColor(Color.white);
            xg.fillRect(5 * C.SIZE, 3 * C.SIZE, 2 * C.SIZE, 1 * C.SIZE);
            xg.drawImage((Image)images.get((new StringBuilder("/images/digit_")).append(formatLevel(model.getLevel(), "stage")).append(".png").toString()), 7 * C.SIZE, 1 * C.SIZE, 3 * C.SIZE, 5 * C.SIZE, this);
        }
        xg.drawImage((Image)images.get("/images/bonus_icon.png"), 120 * C.SIZE - 11 * C.SIZE, 1 * C.SIZE, 5 * C.SIZE, 5 * C.SIZE, this);
        xg.drawImage((Image)images.get((new StringBuilder("/images/digit_")).append(hero.getScore()).append(".png").toString()), 120 * C.SIZE - 5 * C.SIZE, 1 * C.SIZE, 3 * C.SIZE, 5 * C.SIZE, this);
        if(GameControl.bossMode)
        {
            xg.drawImage((Image)images.get("/images/energybar.png"), 30 * C.SIZE, 1 * C.SIZE, 60 * C.SIZE, 5 * C.SIZE, this);
            double divider = (double)Boss1.maxEnergy / 15D;
            double energy = (double)Boss1.energy / divider;
            for(int i = 0; (double)i < energy; i++)
                xg.drawImage((Image)images.get("/images/energy.png"), 120 * C.SIZE - 34 * C.SIZE - i * 4 * C.SIZE, 1 * C.SIZE, 3 * C.SIZE, 4 * C.SIZE, this);

        }
        if(!isFocusOwner())
            Model.paused = true;
        if(Model.paused)
            xg.drawImage((Image)images.get(model.getPaus().getImage()), C.RESIZEDWINDOWWIDTH / 2 - (model.getPaus().getWidth() / 2) * C.SIZE, C.RESIZEDWINDOWHEIGHT / 2 - (model.getPaus().getHeight() / 2) * C.SIZE, model.getPaus().getWidth() * C.SIZE, model.getPaus().getHeight() * C.SIZE, this);
        if(!Pauser.paused)
            if(model.getLevelCompleted())
                xg.drawImage((Image)images.get("/images/levelcompleted.png"), 0, 0, 120 * C.SIZE, 90 * C.SIZE, this);
            else
            if(model.getGameCompleted())
                xg.drawImage((Image)images.get("/images/levelcompleted.png"), 0, 0, 120 * C.SIZE, 90 * C.SIZE, this);
        if(model.getGameOver())
            xg.drawImage((Image)images.get("/images/gameover.png"), 0, 0, 120 * C.SIZE, 90 * C.SIZE, this);
        if(((Boolean)Model.screens.get(general.Model.screenType.THEEND)).booleanValue())
            xg.drawImage((Image)images.get("/images/end.png"), 0, 0, 120 * C.SIZE, 90 * C.SIZE, this);
        if(GameControl.blackScreen)
            xg.drawImage((Image)images.get("/images/blackscreen.png"), 0, 0, 120 * C.SIZE, 90 * C.SIZE, this);
        boolean _tmp = Model.isMute;
    }

    private void drawLevelObject(GameObject o, int x, int y)
    {
        if(Model.inGameArea(o, x, y))
        {
            tempImage = (Image)images.get(o.getImage());
            xg.drawImage(tempImage, o.getX() * C.SIZE - x * C.SIZE, o.getY() * C.SIZE - y * C.SIZE, o.getWidth() * C.SIZE, o.getHeight() * C.SIZE, this);
        }
    }

    public void paint(Graphics g)
    {
        t1 = System.nanoTime();
        g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        xg.setColor(Color.black);
        xg.fillRect(0, 0, 120 * C.SIZE, 90 * C.SIZE);
        if(model.getLevelRunning() || model.getLevelCompleted() || model.getGameCompleted() || model.getGameOver() || model.getLevelRestart() || model.isPreGameOver())
        {
            int x = Hero.scrollX;
            int y = Model.gameHeight - 90;
            xg.drawImage((Image)images.get("/images/t1.png"), 0, 0, 120 * C.SIZE, 90 * C.SIZE, this);
            for(Iterator iterator = model.getTiles().iterator(); iterator.hasNext();)
            {
                GameObject o = (GameObject)iterator.next();
                if(o.getOrder() == 4)
                    drawLevelObject(o, x, y);
            }

            GameObject i;
            for(Iterator iterator1 = model.getItems().iterator(); iterator1.hasNext(); drawLevelObject(i, x, y))
                i = (GameObject)iterator1.next();

            for(Iterator iterator2 = model.getTiles().iterator(); iterator2.hasNext();)
            {
                GameObject o = (GameObject)iterator2.next();
                if((o.getOrder() == 3 || o.getOrder() == 6) && Model.inGameArea(o, x, y))
                    drawLevelObject(o, x, y);
            }

            Ammo a;
            for(Iterator iterator3 = model.getAmmos().iterator(); iterator3.hasNext(); drawLevelObject(a, x, y))
                a = (Ammo)iterator3.next();

            for(Iterator iterator4 = model.getEnemies().iterator(); iterator4.hasNext();)
            {
                Enemy e = (Enemy)iterator4.next();
                drawLevelObject(e, x, y);
                if(e.getExplosionHandler() != null && e.isActive(7))
                {
                    for(Iterator iterator6 = e.getExplosionHandler().getExplosions().iterator(); iterator6.hasNext();)
                    {
                        Explosion ex = (Explosion)iterator6.next();
                        if(ex.isActive(0))
                            drawLevelObject(ex, x, y);
                    }

                }
            }

            Hero hero = model.getHero();
            if(hero != null && !hero.isActive(4))
            {
                tempImage = (Image)images.get(hero.getImage());
                xg.drawImage(tempImage, hero.getX() * C.SIZE - x * C.SIZE, hero.getY() * C.SIZE - y * C.SIZE, hero.getWidth() * C.SIZE, hero.getHeight() * C.SIZE, this);
            }
            for(Iterator iterator5 = model.getTiles().iterator(); iterator5.hasNext();)
            {
                GameObject o = (GameObject)iterator5.next();
                if(o.getOrder() == 1)
                    drawLevelObject(o, x, y);
            }

            drawHUD();
        } else
        {
            if(((Boolean)Model.screens.get(general.Model.screenType.TITLE)).booleanValue())
                xg.drawImage((Image)images.get("/images/title.png"), 0, 0, 120 * C.SIZE, 90 * C.SIZE, this);
            else
            if(((Boolean)Model.screens.get(general.Model.screenType.INFO)).booleanValue())
                xg.drawImage((Image)images.get("/images/info.jpg"), 0, 0, 120 * C.SIZE, 90 * C.SIZE, this);
            if(((Boolean)Model.screens.get(general.Model.screenType.LOADINGLEVEL)).booleanValue())
            {
                xg.drawImage((Image)images.get("/images/loading.png"), 0, 0, 120 * C.SIZE, 90 * C.SIZE, this);
                Model.screens.put(general.Model.screenType.LOADINGLEVEL, Boolean.valueOf(false));
            }
        }
        g2.drawImage(bufferImage, 0, 0, this);
        if(start)
        {
            Model.addSoundToList("/sounds/intro2.wav");
            start = false;
        }
        try
        {
            Thread.sleep(25L);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        model.updateGame();
        t2 = System.nanoTime();
        elapsedTime = (t2 - t1) / 100L;
        model.setElapsedTime(elapsedTime);
        repaint();
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void stop()
    {
        xg.dispose();
    }

    public void destroy()
    {
        stopAllSounds();
    }

    private void stopAllSounds()
    {
        synchronized(allSounds)
        {
            AudioClip a;
            for(Iterator iterator = allSounds.values().iterator(); iterator.hasNext(); a.stop())
                a = (AudioClip)iterator.next();

        }
    }

    private void loadImages()
    {
        MediaTracker mediaTracker = new MediaTracker(this);
        images = new HashMap();
        for(Iterator iterator = model.getAllImages().iterator(); iterator.hasNext(); mediaTracker.addImage(tempImage, 0))
        {
            String filename = (String)iterator.next();
            URL imageURL = getClass().getResource(filename);
            tempImage = getImage(getCodeBase(), imageURL.toString());
            images.put(filename, tempImage);
        }

        try
        {
            mediaTracker.waitForID(0);
        }
        catch(InterruptedException interruptedexception) { }
    }

    public void loadSounds()
    {
        sounds = new String[3];
        allSounds = new HashMap();
        Sound sound;
        for(Iterator iterator = Model.allSounds.iterator(); iterator.hasNext(); allSounds.put(sound.getFilename(), tempSound))
        {
            sound = (Sound)iterator.next();
            URL soundURL = getClass().getResource(sound.getFilename());
            tempSound = getAudioClip(getDocumentBase(), soundURL.toString());
        }

    }

    private final int ENERGIDIVIDER = 15;
    private final int STEPTEXT2 = 15;
    private final int LEVELDIVIDER = 4;
    private Model model;
    private long t1;
    private long t2;
    private long elapsedTime;
    private Image tempImage;
    private AudioClip tempSound;
    private HashMap images;
    private Image bufferImage;
    private Graphics2D xg;
    private Graphics2D g2;
    public static final int MAXSIMSOUNDS = 3;
    public static String sounds[];
    public static HashMap allSounds;
    public static java.util.List tempSoundList;
    public Thread soundThread;
    private boolean start;
}
