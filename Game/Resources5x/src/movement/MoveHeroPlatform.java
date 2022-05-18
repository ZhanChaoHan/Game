// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MoveHeroPlatform.java

package movement;

import gameobjects.Hero;
import gameobjects.Sprite;
import general.Model;

// Referenced classes of package movement:
//            Movement

public class MoveHeroPlatform
    implements Movement
{

    public MoveHeroPlatform()
    {
        loosingJumpCounter = jumpCounter = jumpY = 0;
        startLoosingJumpY = maxJump = 0;
        initJump = true;
        upKeyReleased = maxJumpSet = loosingMoveKeepFalling = false;
        startLoosingMove = true;
    }

    public void move(Sprite sprite)
    {
        Hero hero = (Hero)sprite;
        if(!Hero.isLoosing)
        {
            double vel = 3.8499999999999996D;
            Hero.isOverlappingLadder = Hero.isOverlappingExit = false;
            if(hero.isInDir(0))
            {
                hero.setVelX(-vel);
                hero.setRunningDir(0);
            } else
            if(hero.isInDir(1))
            {
                hero.setVelX(vel);
                hero.setRunningDir(1);
            }
            if(hero.isInDir(2))
                if(!Hero.isClimbing)
                    calculateJumpCounter(hero);
                else
                    hero.setVelY(-2.2000000000000002D);
            if(hero.isInDir(3))
            {
                upKeyReleased = false;
                if(!Hero.isClimbing)
                    hero.setVelY(8.25D);
                else
                    hero.setVelY(2.2000000000000002D);
            }
            hero.initMoveXY();
            if(hero.isInDir(0) || hero.isInDir(1))
            {
                hero.setX(hero.getX() + hero.getMoveAddX());
                hero.checkHeroCollision(hero.getModelTiles());
                hero.checkHeroCollision(hero.getModelItems());
                if(hero.isActive(5))
                    hero.isColliding(null);
                if(Hero.isClimbing && !hero.isInDir(2) && !hero.isInDir(3))
                {
                    Hero.isClimbing = false;
                    Hero.isOverlappingLadder = false;
                    hero.setDir(2, false);
                    hero.setDir(3, true);
                }
            }
            hero.setXPrev(hero.getX());
            if(!Hero.isClimbing)
            {
                if(hero.isInDir(2))
                {
                    setJumpY(hero);
                    hero.setY(jumpY);
                    hero.checkHeroCollision(hero.getModelTiles());
                    hero.checkHeroCollision(hero.getModelItems());
                    if(hero.isActive(5))
                        hero.isColliding(null);
                } else
                if(hero.isInDir(3))
                {
                    hero.setDir(3, true);
                    hero.setY(hero.getY() + hero.getMoveAddY());
                    hero.checkHeroCollision(hero.getModelTiles());
                    hero.checkHeroCollision(hero.getModelItems());
                    if(hero.isActive(5))
                        hero.isColliding(null);
                }
            } else
            if(Hero.isClimbing)
            {
                hero.setX(Hero.ladderX);
                if(hero.isInDir(2))
                {
                    hero.setY(hero.getY() + hero.getMoveAddY());
                    hero.checkHeroCollision(hero.getModelTiles());
                    hero.checkHeroCollision(hero.getModelItems());
                    if(hero.isActive(5))
                        hero.isColliding(null);
                } else
                if(hero.isInDir(3))
                {
                    hero.setY(hero.getY() + hero.getMoveAddY());
                    hero.checkHeroCollision(hero.getModelTiles());
                    hero.checkHeroCollision(hero.getModelItems());
                    if(hero.isActive(5))
                        hero.isColliding(null);
                } else
                {
                    hero.checkHeroCollision(hero.getModelTiles());
                    hero.checkHeroCollision(hero.getModelItems());
                    if(hero.isActive(5))
                        hero.isColliding(null);
                }
            }
            hero.setYPrev(hero.getY());
            hero.checkJumpStatus();
        } else
        {
            if(startLoosingMove)
            {
                timeCounter = 0.0D;
                startLoosingJumpY = hero.getY();
                startLoosingMove = false;
            }
            timeCounter += Model.elapsedTime * 9.9999999999999995E-007D;
            if(timeCounter >= 4D)
                calculateLoosingMove(hero);
        }
    }

    private void calculateJumpCounter(Hero hero)
    {
        if(hero.isInDir(2))
        {
            if(initJump)
            {
                startJumpY = hero.getY();
                maxJump = 0;
                jumpCounter = 0.0D;
                maxJump = 21;
                initJump = maxJumpSet = false;
                Model.addSoundToList("/sounds/jump1.wav");
            }
            if(upKeyReleased && !maxJumpSet)
            {
                if(jumpCounter <= 12D)
                    maxJump = 12;
                else
                    maxJump = 21;
                maxJumpSet = true;
            }
            if(jumpCounter <= 10D)
                hero.setVelY(9.9000000000000004D);
            else
            if(jumpCounter <= 14D)
                hero.setVelY(7.6999999999999993D);
            else
                hero.setVelY(2.75D);
        }
    }

    private void setJumpY(Hero hero)
    {
        jumpCounter += Math.abs(hero.getMoveAddY());
        if(jumpCounter > (double)maxJump)
        {
            jumpCounter = maxJump;
            hero.setDir(2, false);
            hero.setDir(3, true);
            Hero.isFalling = true;
        }
        jumpY = startJumpY - (int)jumpCounter;
    }

    public void init(Sprite sprite)
    {
        Hero hero = (Hero)sprite;
        hero.setPowerX(-100);
        hero.setPowerY(-100);
    }

    public void setPowerTime(double t)
    {
        powerTime = t;
    }

    public void initJump()
    {
        initJump = true;
        Hero.canJump = true;
        Hero.isFalling = false;
        upKeyReleased = false;
        maxJumpSet = false;
    }

    public void setUpKeyReleased()
    {
        upKeyReleased = true;
    }

    private void calculateLoosingMove(Hero hero)
    {
        hero.setVelX(0.0D);
        hero.setVelY(17.600000000000001D);
        hero.initMoveXY();
        hero.setX(hero.getX() + hero.getMoveAddX());
        if(!loosingMoveKeepFalling)
        {
            loosingJumpCounter += Model.elapsedTime * 3.3333333333333335E-007D;
            hero.setY(startLoosingJumpY - (int)(50D * Math.sin(loosingJumpCounter)));
        } else
        {
            hero.setY(hero.getY() + hero.getMoveAddY());
        }
        if((int)(50D * Math.sin(loosingJumpCounter)) <= 0)
            loosingMoveKeepFalling = true;
        if(hero.getY() > Model.gameHeight)
        {
            hero.setY(startLoosingJumpY);
            Hero.isLoosing = false;
            hero.clearStates();
            hero.setState(4, 1);
        }
    }

    private final int MAXJUMP = 21;
    private final double VELFACTOR = 5.5D;
    private double jumpCounter;
    private double loosingJumpCounter;
    private int jumpY;
    private int startJumpY;
    private int startLoosingJumpY;
    private int maxJump;
    private double powerTime;
    private double timeCounter;
    private boolean initJump;
    private boolean upKeyReleased;
    private boolean maxJumpSet;
    private boolean startLoosingMove;
    private boolean loosingMoveKeepFalling;
}
