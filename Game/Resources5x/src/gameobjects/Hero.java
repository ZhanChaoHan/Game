// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Hero.java

package gameobjects;

import collision.Collision;
import collision.CollisionHero;
import general.*;
import java.util.ArrayList;
import java.util.Iterator;
import movement.MoveHeroPlatform;
import movement.Movement;

// Referenced classes of package gameobjects:
//            Sprite, GameObject, Item

public class Hero extends Sprite
{

    public Hero(String tempImage, int x, int y, int w, int h, int s, double vel, int nrImages, double animInterval)
    {
        super(tempImage, x, y, w, h, s, vel, 2, nrImages, animInterval);
        movement = new MoveHeroPlatform();
        collision = new CollisionHero();
        ammoHandler = new AmmoHandlerHero();
        energy = 3;
        lives = 5;
        initType = "";
        items = new ArrayList();
        modelItems = new ArrayList();
        modelTiles = new ArrayList();
        shooting = new boolean[2];
        for(int i = 0; i < 2; i++)
            shooting[i] = false;

        powerIndex = 0;
        animStopped[0] = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_left").toString(), 1, animInterval, -1, "png");
        animStopped[1] = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_right").toString(), 1, animInterval, -1, "png");
        animStopped[2] = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_right").toString(), 1, animInterval, -1, "png");
        animStopped[3] = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_right").toString(), 1, animInterval, -1, "png");
        animMoving[0] = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_left").toString(), nrImages, animInterval, -1, "png");
        animMoving[1] = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_right").toString(), nrImages, animInterval, -1, "png");
        animMoving[2] = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_right").toString(), nrImages, animInterval, -1, "png");
        animMoving[3] = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_right").toString(), nrImages, animInterval, -1, "png");
        animShooting[0] = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_right").toString(), 1, animInterval, 1, "png");
        animShooting[1] = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_right").toString(), 1, animInterval, 1, "png");
        animShooting[2] = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_right").toString(), 1, animInterval, 1, "png");
        animShooting[3] = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_right").toString(), 1, animInterval, 1, "png");
        animHit = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_hit").toString(), 5, 0.69999999999999996D, 4, "png");
        animDestroyed = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_destroy").toString(), 11, 1.0D, 0, "png");
        animDiverse = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_diverse").toString(), 6, animInterval + 1.5D, 0, "png");
        animSuperAmmo = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_megaammo").toString(), 2, animInterval, 0, "png");
        animClimbing = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_climb").toString(), 4, animInterval, -1, "png");
        animLoosing = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_hit").toString(), 5, 1.0D, -1, "png");
        animJumping = new Animation[2];
        animJumping[0] = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_left_jump").toString(), 1, animInterval, -1, "png");
        animJumping[1] = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_right_jump").toString(), 1, animInterval, -1, "png");
        img = animStopped[runningDir].getImage();
        scrollX = scrollY = scrollPrevX = scrollPrevY = 0;
        score = totalScore = 0L;
        isRestart = false;
    }

    public void init(int x, int y)
    {
        this.x = x;
        this.y = y;
        xPrev = x;
        yPrev = y;
        scrollOffsetX = 60;
        scrollOffsetY = 45;
        scrollStartX = scrollOffsetX;
        scrollStartY = scrollOffsetY;
        scrollEndX = Model.gameWidth - 120;
        scrollEndY = Model.gameHeight - 90;
        exitX = exitY = 0;
        borderCollHorz = -1;
        borderCollVert = -1;
        width = 9;
        height = 12;
        if(isRestart)
        {
            energy = 3;
            ammoPower = 0;
        }
        for(int i = 0; i < 2; i++)
            shooting[i] = false;

        clearStates();
        states[1] = 1;
        movement = new MoveHeroPlatform();
        for(int i = 0; i < 4; i++)
            dir[i] = false;

        dir[3] = true;
        runningDir = 1;
        animDiverse.init();
        animDestroyed.init();
        img = animStopped[runningDir].getImage();
        powerIndex = 0;
        initPower = true;
        megaAmmo = false;
        scrolling = true;
        isClimbing = isLoosing = canJump = isOverlappingLadder = isOverlappingExit = isExiting = hasKey = false;
        isRestart = false;
        isFalling = true;
        setScrolling();
    }

    public void setScrolling()
    {
        scrollX = x - scrollOffsetX;
        if(scrollX <= 0)
            scrollX = 0;
        else
        if(scrollX >= scrollEndX)
            scrollX = scrollEndX;
        scrollY = y - scrollOffsetY;
        if(scrollY <= 0)
            scrollY = 0;
        else
        if(scrollY >= scrollEndY)
            scrollY = scrollEndY;
    }

    public void isMoving()
    {
        if(states[3] == 0 && states[4] == 0 && !isExiting)
        {
            movement.move(this);
            tileCollision = false;
        }
        heroX = x;
        heroY = y;
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

    public void isDestroyed()
    {
        setDestroyedPos();
        if(animDestroyed.isFinished())
        {
            clearStates();
            states[4] = 1;
        }
    }

    public void isErased()
    {
    }

    public void isDiverse()
    {
        if(animDiverse.isFinishedReal())
        {
            clearStates();
            Pauser.setPausTime(10D);
        }
    }

    public void animate()
    {
        if(img != null)
        {
            if(states[6] == 1)
                img = animShooting[runningDir].getImage();
            if(states[0] == 1)
                img = animStopped[runningDir].getImage();
            if(states[1] == 1)
            {
                img = animMoving[runningDir].getImage();
                if(!isInDir(0) && !isInDir(1))
                    img = animStopped[runningDir].getImage();
                if(isInDir(2))
                    img = animJumping[runningDir].getImage();
            } else
            if(states[5] == 1)
                img = animMoving[runningDir].getImage();
            if(states[7] == 1)
                img = animDiverse.getImage();
            if(states[2] == 1)
                img = animHit.getImage();
            else
            if(isClimbing)
            {
                img = animClimbing.getImage();
                if(!isInDir(2) && !isInDir(3))
                    img = animClimbing.getImage(0);
            }
            if(isLoosing)
                img = animLoosing.getImage();
            if(states[3] == 1)
                img = animDestroyed.getImage();
        }
        if(states[4] == 1)
            img = animDestroyed.getImage();
        states[5] = 0;
    }

    public void setDir(int d, boolean v)
    {
        dir[d] = v;
        states[0] = 0;
        states[1] = 1;
    }

    public void setDestroyedPos()
    {
        int offsetX = (getWidth() - 20) / 2;
        int offsetY = (getHeight() - 20) / 2;
        setX(getX() + offsetX);
        setY(getY() + offsetY);
        setWidth(20);
        setHeight(20);
    }

    public void checkShooting(ArrayList ammos)
    {
        if(states[3] == 0 && states[4] == 0 && !isLoosing)
            ammoHandler.checkAmmoTime(ammos, this);
        for(int i = 0; i < 2; i++)
            shooting[i] = false;

    }

    public void checkHeroCollision(ArrayList gameObjects)
    {
        for(Iterator iterator = gameObjects.iterator(); iterator.hasNext();)
        {
            GameObject g = (GameObject)iterator.next();
            if((g.getOrder() == 2 || g.getOrder() == 3 || g.getOrder() == 6) && x < (g.getX() + g.getWidth()) - 0 && x + width > g.getX() + 0 && y < (g.getY() + g.getHeight()) - 0 && y + height > g.getY() + 0)
            {
                collisions.add(g);
                states[5] = 1;
            }
        }

    }

    public void checkEndOfLevelCollision()
    {
        if(x > Model.gameWidth - width && !GameControl.bossLevel)
            isExiting = true;
    }

    public int checkBorderCollisionHorz()
    {
        if(x < 0)
            return 0;
        return x <= (0 + Model.gameWidth) - width ? -1 : 1;
    }

    public int checkBorderCollisionVert()
    {
        if(y < 0)
            return 2;
        return y <= 0 + Model.gameHeight ? -1 : 3;
    }

    public void checkJumpStatus()
    {
        if(states[3] == 0 && states[4] == 0 && modelTiles != null && !isClimbing)
        {
            canJump = false;
            Iterator iterator = modelTiles.iterator();
            while(iterator.hasNext()) 
            {
                GameObject g = (GameObject)iterator.next();
                if(g.getOrder() != 2 && g.getOrder() != 3)
                    continue;
                if(x < g.getX() + g.getWidth() && x + width > g.getX() && y + 1 <= g.getY() + g.getHeight() && y + height + 1 > g.getY() && !isInDir(2))
                {
                    getMovement().initJump();
                    break;
                }
                if(x >= g.getX() + g.getWidth() || x + width <= g.getX() || y > g.getY() + g.getHeight() || y + height <= g.getY())
                    continue;
                setDir(2, false);
                setDir(3, true);
                isFalling = true;
                break;
            }
        }
    }

    public boolean getTileCollision()
    {
        return tileCollision;
    }

    public void setTileCollision(boolean t)
    {
        tileCollision = t;
    }

    public void setShooting(boolean s, int type)
    {
        shooting[type] = s;
    }

    public boolean getShooting(int type)
    {
        return shooting[type];
    }

    public void addItem(Item i)
    {
        items.add(i);
    }

    public boolean isAnimHitFinished()
    {
        return animHit.isFinished();
    }

    public void clearAnimHit()
    {
        animHit.clear();
    }

    public void setInitType(String type)
    {
        initType = type;
    }

    public int getY()
    {
        return y;
    }

    public int getPowerX()
    {
        return powerX;
    }

    public void setPowerX(int powerX)
    {
        this.powerX = powerX;
    }

    public int getPowerY()
    {
        return powerY;
    }

    public void setPowerY(int powerY)
    {
        this.powerY = powerY;
    }

    public int getAmmoPower()
    {
        return ammoPower;
    }

    public void setAmmoPower(int ammoPower)
    {
        this.ammoPower = ammoPower;
    }

    public void setScore(long s)
    {
        score = s;
    }

    public long getScore()
    {
        return score;
    }

    public long getTotalScore()
    {
        return totalScore;
    }

    public void setTotalScore(long totalScore)
    {
        this.totalScore = totalScore;
    }

    public int getLives()
    {
        return lives;
    }

    public void setLives(int lives)
    {
        this.lives = lives;
    }

    public int getPowerIndex()
    {
        return powerIndex;
    }

    public void setPowerIndex(int powerIndex)
    {
        this.powerIndex = powerIndex;
    }

    public boolean isMegaAmmo()
    {
        return megaAmmo;
    }

    public void setMegaAmmo(boolean megaAmmo)
    {
        this.megaAmmo = megaAmmo;
    }

    public int getMegaAmmoX()
    {
        return megaAmmoX;
    }

    public void setMegaAmmoX(int megaAmmoX)
    {
        this.megaAmmoX = megaAmmoX;
    }

    public int getMegaAmmoY()
    {
        return megaAmmoY;
    }

    public void setMegaAmmoY(int megaAmmoY)
    {
        this.megaAmmoY = megaAmmoY;
    }

    public float getMegaAmmoAngle()
    {
        return megaAmmoAngle;
    }

    public void setMegaAmmoAngle(float megaAmmoAngle)
    {
        this.megaAmmoAngle = megaAmmoAngle;
    }

    public MoveHeroPlatform getMovement()
    {
        return (MoveHeroPlatform)movement;
    }

    public ArrayList getItems()
    {
        return items;
    }

    public void setModelTiles(ArrayList tiles)
    {
        modelTiles = tiles;
    }

    public ArrayList getModelTiles()
    {
        return modelTiles;
    }

    public void setModelItems(ArrayList modelItems)
    {
        this.modelItems = modelItems;
    }

    public ArrayList getModelItems()
    {
        return modelItems;
    }

    public static final double HEROVEL = 8.6999999999999993D;
    public static final double HEROVELHORZ = 2.2999999999999998D;
    public static final int HEROVELINC = 10000;
    public static final int WIDTH = 9;
    public static final int HEIGHT = 12;
    public static final int ENERGY = 3;
    public static final int MAXENERGY = 9;
    private final int LIVES = 5;
    private final int XSAFE = 0;
    private final int YSAFE = 0;
    public static final int MAXAMMOPOWER = 3;
    public static final int POWERWIDTH = 100;
    public static final int POWERHEIGHT = 100;
    public static final int MEGAAMMOWIDTH = 130;
    public static final int MEGAAMMOHEIGHT = 130;
    private final int DESTROYEDSIZE = 20;
    private boolean shooting[];
    private AmmoHandlerHero ammoHandler;
    private ArrayList items;
    private ArrayList modelItems;
    private ArrayList modelTiles;
    private boolean tileCollision;
    public static boolean scrolling;
    public static boolean initPower;
    private int powerX;
    private int powerY;
    private int powerIndex;
    private int ammoPower;
    private int lives;
    private long score;
    private long totalScore;
    public static boolean canJump;
    public static boolean isFalling;
    public static boolean isOverlappingLadder;
    public static boolean isClimbing;
    public static boolean isOverlappingExit;
    public static boolean isExiting;
    public static boolean hasKey;
    public static boolean isLoosing;
    public static boolean isRestart;
    public static int ladderX = 0;
    private boolean megaAmmo;
    private int megaAmmoX;
    private int megaAmmoY;
    private float megaAmmoAngle;
    private Animation animClimbing;
    private Animation animLoosing;
    private Animation animJumping[];
    private Animation animSuperAmmo;
    private String initType;
    public static int heroX;
    public static int heroY;
    public static int scrollX;
    public static int scrollY;
    public static int scrollPrevX;
    public static int scrollPrevY;
    public static int scrollAdd;
    public static int exitX;
    public static int exitY;
    private int scrollStartX;
    private int scrollEndX;
    private int scrollStartY;
    private int scrollEndY;
    private int scrollOffsetX;
    private int scrollOffsetY;

}
