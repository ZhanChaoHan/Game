// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Ammo.java

package gameobjects;

import collision.Collision;
import general.Animation;
import java.util.ArrayList;
import java.util.Iterator;
import movement.MoveAmmo;
import movement.Movement;

// Referenced classes of package gameobjects:
//            Sprite, GameObject, ItemDoor

public class Ammo extends Sprite
{

    public Ammo(String tempImage, int x, int y, int w, int h, Movement m, Collision c, 
            int s, double velX, double velY, int maxDist, float a, 
            int hp, String sound, int nrImages, double animInterval)
    {
        super(tempImage, x, y, w, h, s, 0.0D, 1, nrImages, animInterval);
        this.velX = velX;
        this.velY = velY;
        this.maxDist = maxDist;
        angleMove = a;
        hitpoints = hp;
        movement = m;
        collision = c;
        this.animInterval = animInterval;
        type = 2;
        origImage = tempImage;
        setDestroyedPos = true;
        this.sound = sound;
        animDestroyed = new Animation((new StringBuilder(String.valueOf(tempImage.substring(0, tempImage.length() - 1)))).append("_destroy").toString(), 4, 0.69999999999999996D, 0, "png");
    }

    public void init(String tempImage, int x, int y, int w, int h, Movement m, Collision c, 
            int type, int s, int d, double velX, double velY, 
            int maxDist, float a, int hp, int nrImages, double animInterval)
    {
        this.x = x;
        this.y = y;
        xPrev = x;
        yPrev = y;
        width = w;
        height = h;
        collision = c;
        movement = m;
        movement.init(this);
        dist = 0;
        this.maxDist = maxDist;
        clearStates();
        clearDirs();
        setDir(1, true);
        states[s] = 1;
        this.velX = velX;
        this.velY = velY;
        angleMove = a;
        angle = 0.0F;
        this.type = type;
        hitpoints = hp;
        this.animInterval = animInterval;
        animMoving[1].init(tempImage, animInterval, -1, "png");
        animDestroyed.init();
        borderCollHorz = borderCollVert = 1;
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

    public void setDestroyedPos()
    {
        x = (x + width / 2) - 4;
        y = (y + height / 2) - 4;
        width = 8;
        height = 8;
    }

    public void checkEnemyCollision(ArrayList gameObjects)
    {
        if(states[3] == 0 && states[4] == 0)
        {
            for(Iterator iterator = gameObjects.iterator(); iterator.hasNext();)
            {
                GameObject g = (GameObject)iterator.next();
                if((g.getOrder() == 2 || g.getOrder() == 5) && x < g.getX() + g.getWidth() && x + width > g.getX() && y < g.getY() + g.getHeight() && y + height > g.getY())
                {
                    collisions.add(g);
                    states[5] = 1;
                }
            }

        }
    }

    public void checkItemCollision(ArrayList gameObjects)
    {
        if(states[3] == 0 && states[4] == 0)
        {
            for(Iterator iterator = gameObjects.iterator(); iterator.hasNext();)
            {
                GameObject g = (GameObject)iterator.next();
                if((g instanceof ItemDoor) && x < g.getX() + g.getWidth() && x + width > g.getX() && y < g.getY() + g.getHeight() && y + height > g.getY())
                {
                    collisions.add(g);
                    states[5] = 1;
                }
            }

        }
    }

    public void setDist(int d)
    {
        dist = d;
    }

    public int getDist()
    {
        return dist;
    }

    public void setMaxDist(int d)
    {
        maxDist = d;
    }

    public int getMaxDist()
    {
        return maxDist;
    }

    public void setType(int t)
    {
        type = t;
    }

    public int getType()
    {
        return type;
    }

    public double getAnimInterval()
    {
        return animInterval;
    }

    public void setAnimInterval(double animInterval)
    {
        this.animInterval = animInterval;
    }

    public float getAngleMove()
    {
        return angleMove;
    }

    public int getHitpoints()
    {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints)
    {
        this.hitpoints = hitpoints;
    }

    public String getOrigImage()
    {
        return origImage;
    }

    public void setOrigImage(String origImage)
    {
        this.origImage = origImage;
    }

    public String getSound()
    {
        return sound;
    }

    public void setSound(String sound)
    {
        this.sound = sound;
    }

    public MoveAmmo getMoveAmmo()
    {
        if(movement instanceof MoveAmmo)
            return (MoveAmmo)movement;
        else
            return null;
    }

    public static final int HERO1 = 0;
    public static final int HERO2 = 1;
    public static final int ENEMY = 2;
    public static final int DIST = 240;
    private final int AMMODESTROYEDSIZE = 8;
    private int dist;
    private int maxDist;
    private int hitpoints;
    private double animInterval;
    private float angleMove;
    private String origImage;
    private boolean setDestroyedPos;
    private String sound;
}
