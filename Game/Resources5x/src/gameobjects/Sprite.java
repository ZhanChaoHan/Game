// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Sprite.java

package gameobjects;

import collision.Collision;
import general.Animation;
import general.Model;
import java.util.ArrayList;
import java.util.Iterator;
import movement.Movement;

// Referenced classes of package gameobjects:
//            GameObject, Hero

public class Sprite extends GameObject
{

    public Sprite(String tempImage, int x, int y, int w, int h, int s, double vel, int energy, int nrImages, double animInterval)
    {
        super(x, y, w, h);
        this.vel = vel;
        this.nrImages = nrImages;
        this.energy = energy;
        origImage = tempImage;
        movement = null;
        collision = null;
        collisions = new ArrayList();
        states = new int[8];
        clearStates();
        states[s] = 1;
        dir = new boolean[4];
        borderCollHorz = -1;
        borderCollVert = -1;
        for(int i = 0; i < 4; i++)
            dir[i] = false;

        runningDir = 1;
        if(tempImage != null)
        {
            name = tempImage;
            this.animInterval = animInterval;
            animStopped = new Animation[4];
            animMoving = new Animation[4];
            animShooting = new Animation[4];
            for(int i = 0; i < 4; i++)
            {
                animStopped[i] = new Animation(tempImage, 1, animInterval, -1, "png");
                animMoving[i] = new Animation(tempImage, nrImages, animInterval, -1, "png");
                animShooting[i] = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_shooting").toString(), 1, animInterval, 2, "png");
            }

            animHit = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_hit").toString(), 1, animInterval, 1, "png");
            animDestroyed = new Animation((new StringBuilder(String.valueOf(tempImage.substring(0, tempImage.length() - 1)))).append("_destroy").toString(), 5, 0.69999999999999996D, 0, "png");
            animDiverse = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_diverse").toString(), 1, animInterval, 0, "png");
            img = animStopped[runningDir].getImage();
        }
        restMoveAdd = restMoveX = restMoveY = moveAdd = velX = velY = 0.0D;
    }

    public void initMove()
    {
        calculateXY();
    }

    protected void calculateXY()
    {
        double tempMoveAdd = Model.elapsedTime * (vel / 1000000D);
        restMoveAdd += tempMoveAdd;
        if(restMoveAdd > 1.0D)
        {
            moveAdd = (int)(restMoveAdd / 1.0D) * 1;
            restMoveAdd -= moveAdd;
        } else
        {
            moveAdd = 0.0D;
        }
        if(moveAdd >= 10D)
            moveAdd = 10D;
        else
        if(moveAdd <= -10D)
            moveAdd = -10D;
    }

    public void initMoveXY()
    {
        calculateX();
        calculateY();
    }

    public void initMoveX()
    {
        calculateX();
    }

    public void initMoveY()
    {
        calculateY();
    }

    protected void calculateX()
    {
        double tempMoveX = Model.elapsedTime * (velX / 1000000D);
        restMoveX += tempMoveX;
        if(restMoveX > 1.0D)
        {
            moveAddX = (int)(restMoveX / 1.0D) * 1;
            restMoveX -= moveAddX;
        } else
        if(restMoveX < -1D)
        {
            moveAddX = (int)(restMoveX / 1.0D) * 1;
            restMoveX -= moveAddX;
        } else
        {
            moveAddX = 0.0D;
        }
        if(moveAddX >= 10D)
            moveAddX = 10D;
        else
        if(moveAddX <= -10D)
            moveAddX = -10D;
    }

    protected void calculateY()
    {
        double tempMoveY = Model.elapsedTime * (velY / 1000000D);
        restMoveY += tempMoveY;
        if(restMoveY > 1.0D)
        {
            moveAddY = (int)(restMoveY / 1.0D) * 1;
            restMoveY -= moveAddY;
        } else
        if(restMoveY < -1D)
        {
            moveAddY = (int)(restMoveY / 1.0D) * 1;
            restMoveY -= moveAddY;
        } else
        {
            moveAddY = 0.0D;
        }
        if(moveAddY >= 10D)
            moveAddY = 10D;
        else
        if(moveAddY <= -10D)
            moveAddY = -10D;
    }

    public void clearDirs()
    {
        for(int i = 0; i < 4; i++)
            dir[i] = false;

    }

    public void setDir(int d, boolean v)
    {
        dir[d] = v;
        states[0] = 0;
        states[1] = 1;
    }

    public boolean isInDir(int d)
    {
        return dir[d];
    }

    public int getRunningDir()
    {
        return runningDir;
    }

    public void setRunningDir(int d)
    {
        runningDir = d;
    }

    public void animate()
    {
        if(img != null)
        {
            if(states[6] == 1)
                img = animShooting[runningDir].getImage();
            if(states[0] == 1)
                img = animStopped[runningDir].getImage();
            else
            if(states[1] == 1 || states[5] == 1)
                img = animMoving[runningDir].getImage();
            if(states[7] == 1)
                img = animDiverse.getImage();
            if(states[2] == 1)
                img = animHit.getImage();
            if(states[3] == 1)
                img = animDestroyed.getImage();
        }
        states[5] = 0;
    }

    public void checkTileCollision(ArrayList gameObjects)
    {
        if(states[3] == 0 && states[4] == 0)
        {
            checkAreaCollision(gameObjects);
            if(!checkIfColliding(collisions))
                collisions.clear();
        }
    }

    public void checkAreaCollision(ArrayList gameObjects)
    {
        for(Iterator iterator = gameObjects.iterator(); iterator.hasNext();)
        {
            GameObject g = (GameObject)iterator.next();
            if(isCollisionOrder(g))
                if(xPrev <= x && yPrev <= y)
                {
                    if(xPrev < g.getX() + g.getWidth() && x + width >= g.getX() && yPrev < g.getY() + g.getHeight() && y + height >= g.getY())
                        collisions.add(g);
                } else
                if(xPrev >= x && yPrev <= y)
                {
                    if(x < g.getX() + g.getWidth() && xPrev + width >= g.getX() && yPrev < g.getY() + g.getHeight() && y + height >= g.getY())
                        collisions.add(g);
                } else
                if(xPrev >= x && yPrev >= y)
                {
                    if(x < g.getX() + g.getWidth() && xPrev + width >= g.getX() && y < g.getY() + g.getHeight() && yPrev + height >= g.getY())
                        collisions.add(g);
                } else
                if(xPrev <= x && yPrev >= y && xPrev < g.getX() + g.getWidth() && x + width >= g.getX() && y < g.getY() + g.getHeight() && yPrev + height >= g.getY())
                    collisions.add(g);
        }

    }

    public boolean checkIfColliding(ArrayList gameObjects)
    {
        for(Iterator iterator = gameObjects.iterator(); iterator.hasNext();)
        {
            GameObject g = (GameObject)iterator.next();
            if(isCollisionOrder(g) && x <= g.getX() + g.getWidth() && x + width > g.getX() && y <= g.getY() + g.getHeight() && y + height > g.getY())
            {
                states[5] = 1;
                return true;
            }
        }

        return false;
    }

    public void checkCollision(ArrayList gameObjects)
    {
        if(states[3] == 0 && states[4] == 0)
        {
            for(Iterator iterator = gameObjects.iterator(); iterator.hasNext();)
            {
                GameObject g = (GameObject)iterator.next();
                if(isCollisionOrder(g) && x < g.getX() + g.getWidth() && x + width > g.getX() && y < g.getY() + g.getHeight() && y + height > g.getY())
                {
                    collisions.add(g);
                    states[5] = 1;
                }
            }

        }
    }

    public boolean isCollisionOrder(GameObject g)
    {
        return g.getOrder() == 2 || g.getOrder() == 3 || g.getOrder() == 5;
    }

    public void checkBorderCollision()
    {
        if(states[4] == 0)
        {
            borderCollHorz = checkBorderCollisionHorz();
            borderCollVert = checkBorderCollisionVert();
            if(borderCollHorz > -1 || borderCollVert > -1)
                states[5] = 1;
        }
    }

    public void checkInGameArea()
    {
        if(states[4] == 0)
            if(Model.inGameArea(this, Hero.scrollX, Hero.scrollY))
            {
                borderCollHorz = borderCollVert = 1;
            } else
            {
                borderCollHorz = borderCollVert = 0;
                states[5] = 1;
            }
    }

    public int checkBorderCollisionHorz()
    {
        if(x - width < 0 + Hero.scrollX)
            return 0;
        return x <= 120 + Hero.scrollX ? -1 : 1;
    }

    public int checkBorderCollisionVert()
    {
        if(y < 0)
            return 2;
        return y - height <= 0 + Model.gameHeight ? -1 : 3;
    }

    public void clearStates()
    {
        for(int i = 0; i < 8; i++)
            states[i] = 0;

    }

    public void isStopped()
    {
    }

    public void isColliding()
    {
        if(states[3] == 0 && states[4] == 0 && collision != null)
        {
            collision.checkCollision(this, null);
            collisions.clear();
            states[5] = 0;
        }
    }

    public void isShooting()
    {
        if(animShooting[runningDir].isFinished())
            states[6] = 0;
    }

    public void isMoving()
    {
        if(states[3] == 0 && states[4] == 0 && movement != null)
        {
            xPrev = x;
            yPrev = y;
            initMove();
            movement.move(this);
        }
    }

    public void isHit()
    {
        if(animHit.isFinished())
            states[2] = 0;
    }

    public void isDestroyed()
    {
        if(animDestroyed.isFinished())
        {
            clearStates();
            states[4] = 1;
        }
    }

    public void isErased()
    {
        x = -120;
        y = -90;
    }

    public void isDiverse()
    {
    }

    public ArrayList getCollisions()
    {
        return collisions;
    }

    public int getBorderCollHorz()
    {
        return borderCollHorz;
    }

    public int getBorderCollVert()
    {
        return borderCollVert;
    }

    public int getXPrev()
    {
        return xPrev;
    }

    public void setXPrev(int prev)
    {
        xPrev = prev;
    }

    public int getYPrev()
    {
        return yPrev;
    }

    public void setYPrev(int prev)
    {
        yPrev = prev;
    }

    public int getMoveAdd()
    {
        return (int)moveAdd;
    }

    public double getVel()
    {
        return vel;
    }

    public void setVel(double vel)
    {
        this.vel = vel;
    }

    public double getVelX()
    {
        return velX;
    }

    public void setVelX(double velX)
    {
        this.velX = velX;
    }

    public double getVelY()
    {
        return velY;
    }

    public void setVelY(double velY)
    {
        this.velY = velY;
    }

    public int getMoveAddX()
    {
        return (int)moveAddX;
    }

    public int getMoveAddY()
    {
        return (int)moveAddY;
    }

    public void setEnergy(int e)
    {
        energy = e;
    }

    public int getEnergy()
    {
        return energy;
    }

    public void setState(int s, int i)
    {
        states[s] = i;
    }

    public boolean isActive(int s)
    {
        return states[s] == 1;
    }

    public String getImage()
    {
        return img;
    }

    public int getNrImages()
    {
        return nrImages;
    }

    public void setNrImages(int nrImages)
    {
        this.nrImages = nrImages;
    }

    public Collision getCollision()
    {
        return collision;
    }

    public Movement getMovement()
    {
        return movement;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getAnimInterval()
    {
        return animInterval;
    }

    public void setAnimInterval(double animInterval)
    {
        this.animInterval = animInterval;
    }

    public String getOrigImage()
    {
        return origImage;
    }

    public static final int ISSTOPPED = 0;
    public static final int ISMOVING = 1;
    public static final int ISHIT = 2;
    public static final int ISDESTROYED = 3;
    public static final int ISERASED = 4;
    public static final int ISCOLLIDING = 5;
    public static final int ISSHOOTING = 6;
    public static final int ISDIVERSE = 7;
    private static final int PIXELSIZE = 1;
    protected final int NBROFEXPLOSIONSPRITES = 7;
    protected final int NRSTATES = 8;
    public static final int ACTIVE = 1;
    public static final int INACTIVE = 0;
    protected final int DIRS = 4;
    public static final int NODIR = -1;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    public static final int INGAMEAREA = 1;
    public static final int OUTOFGAMEAREA = 0;
    public static final long TIMEDIVIDER = 0xf4240L;
    protected Movement movement;
    protected Collision collision;
    protected int xPrev;
    protected int yPrev;
    protected int energy;
    protected boolean dir[];
    protected int runningDir;
    protected double vel;
    protected double moveAdd;
    protected double restMoveAdd;
    protected double velX;
    protected double restMoveX;
    protected double velY;
    protected double restMoveY;
    protected double moveAddX;
    protected double moveAddY;
    protected double timeAdd;
    protected double restTimeAdd;
    protected double animInterval;
    protected int nrImages;
    private String name;
    protected String origImage;
    protected Animation animMoving[];
    protected Animation animStopped[];
    protected Animation animShooting[];
    protected Animation animHit;
    protected Animation animDestroyed;
    protected Animation animDiverse;
    protected int states[];
    protected int type;
    protected int borderCollVert;
    protected int borderCollHorz;
    protected ArrayList collisions;
}
