// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enemy.java

package gameobjects;

import collision.Collision;
import general.*;
import java.util.ArrayList;
import java.util.Iterator;
import movement.Movement;

// Referenced classes of package gameobjects:
//            Sprite, Boss1, GameObject, Hero

public class Enemy extends Sprite
{

    public Enemy(String tempImage, int x, int y, int w, int h, int d, Movement m, 
            Collision c, AmmoHandlerEnemy a, ExplosionHandler ex, int s, double velX, double velY, float angle, int score, int e, String expl, int nrImages, double animInterval)
    {
        super(tempImage, x, y, w, h, s, 0.0D, e, nrImages, animInterval);
        destroyedPosYOffset = 0;
        this.velX = velX;
        this.velY = velY;
        angleMove = angle;
        this.score = score;
        explosionHandler = ex;
        if(expl.equals("destroy2"))
            explosionSound = "destroy2.wav";
        else
            explosionSound = "destroy3.wav";
        movement = m;
        collision = c;
        ammoHandler = a;
        dir[d] = true;
        runningDir = d;
        lastHit = false;
        animHit = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_hit").toString(), 1, 2D, 1, "png");
        animDestroyed = new Animation((new StringBuilder("enemy_")).append(expl).toString(), 5, 1.2D, 0, "png");
        if(expl.equals("boss_destroy"))
            animDestroyed = new Animation("boss_destroy", 6, 1.6000000000000001D, 0, "png");
        if(explosionHandler != null)
            animDiverse = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_diverse").toString(), 2, 1.0D, -1, "png");
        if(movement != null)
            movement.init(this);
        setDestroyedPos = true;
    }

    public void isMoving()
    {
        if(states[3] == 0 && states[4] == 0 && movement != null)
        {
            xPrev = x;
            yPrev = y;
            movement.move(this);
        }
    }

    public void isDestroyed()
    {
        if(setDestroyedPos)
        {
            setDestroyedPos();
            setDestroyedPos = false;
        }
        if(animDestroyed.isFinished())
        {
            clearStates();
            states[4] = 1;
        }
    }

    public void isColliding(Model model)
    {
        if(collision != null)
        {
            collision.checkCollision(this, model);
            collisions.clear();
            states[5] = 0;
        }
    }

    public void isDiverse()
    {
        if(explosionHandler != null)
        {
            getExplosionHandler().checkExplosionTime(this);
            if(explosionHandler.getExplCounter() == explosionHandler.getExplosions().size())
            {
                explosionHandler.setFinished(true);
                states[7] = 0;
                states[3] = 1;
                img = animDestroyed.getImage();
                Model.addSoundToList("/sounds/destroy2.wav");
            }
        }
    }

    public void checkShooting(ArrayList ammos, Sprite hero)
    {
        if(states[3] == 0 && states[4] == 0 && ammoHandler != null && (!GameControl.bossMode || Boss1.energy > 0))
            ammoHandler.checkAmmoTime(ammos, this, hero);
    }

    public void checkAmmoCollision(ArrayList gameObjects)
    {
        if(states[3] == 0 && states[4] == 0)
        {
            for(Iterator iterator = gameObjects.iterator(); iterator.hasNext();)
            {
                GameObject g = (GameObject)iterator.next();
                if(Model.inGameFieldArea(g, Hero.scrollX, Hero.scrollY) && (g.getOrder() == 2 || g.getOrder() == 5) && x < g.getX() + g.getWidth() && x + width > g.getX() && y < g.getY() + g.getHeight() && y + height > g.getY())
                {
                    collisions.add(g);
                    states[5] = 1;
                }
            }

        }
    }

    public void setDestroyedPos()
    {
        x = (x + width / 2) - destroyedSize / 2;
        y = ((y + height / 2) - destroyedSize / 2) + destroyedPosYOffset;
        width = destroyedSize;
        height = destroyedSize;
    }

    public void setEnergy(int e)
    {
        energy = e;
    }

    public int getEnergy()
    {
        return energy;
    }

    public void setDestroyedSize(int size)
    {
        destroyedSize = size;
    }

    public int getAnimMovingNr()
    {
        return animMoving[runningDir].getNr();
    }

    public void clearAnimHit()
    {
        animHit.clear();
    }

    public boolean isLastHit()
    {
        return lastHit;
    }

    public void setLastHit(boolean lastHit)
    {
        this.lastHit = lastHit;
    }

    public float getAngleMove()
    {
        return angleMove;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public void setMovingImages(String tempImage, int nrImages, double animInterval)
    {
        animMoving[0] = new Animation((new StringBuilder(String.valueOf(tempImage))).append("left").toString(), nrImages, animInterval, -1, "png");
        animMoving[3] = new Animation((new StringBuilder(String.valueOf(tempImage))).append("down").toString(), nrImages, animInterval, -1, "png");
        animMoving[1] = new Animation((new StringBuilder(String.valueOf(tempImage))).append("right").toString(), nrImages, animInterval, -1, "png");
        animMoving[2] = new Animation((new StringBuilder(String.valueOf(tempImage))).append("up").toString(), nrImages, animInterval, -1, "png");
    }

    public String getExplosionSound()
    {
        return explosionSound;
    }

    public ExplosionHandler getExplosionHandler()
    {
        return explosionHandler;
    }

    public static final int IMM1 = -1;
    public static final int IMM2 = -10;
    public static final int IMM3 = -100;
    protected final String IMAGENAME = "enemy";
    private AmmoHandlerEnemy ammoHandler;
    protected ExplosionHandler explosionHandler;
    protected int destroyedSize;
    protected int destroyedPosYOffset;
    private int score;
    private boolean lastHit;
    private float angleMove;
    private boolean setDestroyedPos;
    private String explosionSound;
}
