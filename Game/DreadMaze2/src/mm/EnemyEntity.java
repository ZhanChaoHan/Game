// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EnemyEntity.java

package mm;

import java.util.ArrayList;

// Referenced classes of package mm:
//            CombatEntity, AnimationSet, Globals, PlayerEntity, 
//            Maze, Room, Item, MazeGenerator

public class EnemyEntity extends CombatEntity
{

    public EnemyEntity(int x, int y, int difficulty)
    {
        this(x, y, difficulty / 2 + 1, difficulty * 3, difficulty / 3, difficulty, (float)difficulty / 100F, 1.0F - (float)difficulty / 100F, difficulty);
    }

    public EnemyEntity(int x, int y, int lev, int maxHP, int arm, int pow, float doge, 
            float rand, int sight)
    {
        this(x, y, lev, maxHP, arm, pow, doge, rand, sight, (int)(Math.random() * (double)IMAGES.length));
    }

    public EnemyEntity(int x, int y, int lev, int maxHP, int arm, int pow, float doge, 
            float rand, int sight, int im)
    {
        super(x, y, lev, maxHP, arm, pow, doge);
        randomProbability = rand;
        sightRadius = sight;
        image = im;
        createEnemyAnimationSet(im);
    }

    protected void createAnimationSet()
    {
    }

    protected void createEnemyAnimationSet(int im)
    {
        if(animationSet == null)
        {
            image = im;
            animationSet = new AnimationSet("Stand", new AnimationSet.Animation((new StringBuilder("Monster/")).append(IMAGES[image]).append("/Stand/").toString(), "", 1, 0, 0, 0));
            animationSet.addAnimation("Move", new AnimationSet.Animation((new StringBuilder("Monster/")).append(IMAGES[image]).append("/Walk/").toString(), "", 2, 3, 0, 1));
            animationSet.addAnimation("GetItem", new AnimationSet.Animation((new StringBuilder("Monster/")).append(IMAGES[image]).append("/Walk/").toString(), "", 1, 0, 0, 0));
            animationSet.addAnimation("TakeDamage", new AnimationSet.Animation((new StringBuilder("Monster/")).append(IMAGES[image]).append("/TakeDamage/").toString(), (new StringBuilder("Monster/")).append(IMAGES[image]).append("/TakeDamage/").toString(), 1, 0, 2, 10));
            animationSet.addAnimation("Attack", new AnimationSet.Animation((new StringBuilder("Monster/")).append(IMAGES[image]).append("/Attack/").toString(), (new StringBuilder("Monster/")).append(IMAGES[image]).append("/Attack/").toString(), 1, 0, 2, 15));
            animationSet.addAnimation("Die", new AnimationSet.Animation((new StringBuilder("Monster/")).append(IMAGES[image]).append("/Stand/").toString(), (new StringBuilder("Monster/")).append(IMAGES[image]).append("/Die/").toString(), 1, 0, 0, 0));
        }
    }

    public void act(boolean newTurn)
    {
        super.act(newTurn);
        if(!newTurn)
            return;
        boolean moveRandomly = false;
        PlayerEntity players[] = Globals.getPlayers();
        if(Math.random() <= (double)randomProbability)
            moveRandomly = true;
        for(int k = 0; k < players.length; k++)
        {
            if(players[k].isDead())
                continue;
            double distance = Math.abs(players[k].getX() - x) + Math.abs(players[k].getY() - y);
            if(distance <= 1.0D || (double)sightRadius >= distance && !moveRandomly)
            {
                targetX = players[k].getX();
                targetY = players[k].getY();
                if(distance <= 1.0D)
                    randomProbability = 0.0F;
                path = Maze.getPath(x, y, targetX, targetY);
                break;
            }
            moveRandomly = true;
        }

        if(moveRandomly && targetX == x && targetY == y)
        {
            targetX += (int)(Math.random() * 5D) - 2;
            targetY += (int)(Math.random() * 5D) - 2;
            if(targetX < 0)
                targetX = 0;
            else
            if(targetX >= Maze.getWidth())
                targetX = Maze.getWidth() - 1;
            if(targetY < 0)
                targetY = 0;
            else
            if(targetY >= Maze.getHeight())
                targetY = Maze.getHeight() - 1;
            path = Maze.getPath(x, y, targetX, targetY);
        }
        attack(moveTo(targetX, targetY));
    }

    public void die()
    {
        Room r = Maze.getRoom(x, y);
        r.setCombatEntity(null);
        animationSet.setAnimation("Die");
        if(r.getItemEntity() == null)
        {
            ArrayList items = new ArrayList();
            if(weapon != null)
                items.add(weapon);
            if(armor != null)
                items.add(armor);
            if(items.isEmpty())
                return;
            r.pickUpItem((Item)items.get((int)(Math.random() * (double)items.size())));
        }
    }

    public int getSightRadius()
    {
        return sightRadius;
    }

    public float getRandomProbability()
    {
        return randomProbability;
    }

    public void setRandomProbability(float n)
    {
        randomProbability = n;
    }

    public void attack(CombatEntity defender)
    {
        if(!(defender instanceof PlayerEntity))
        {
            return;
        } else
        {
            super.attack(defender);
            return;
        }
    }

    protected boolean shouldMove()
    {
        return Globals.getCurrentTurn() - lastActionTurn >= 5;
    }

    protected boolean shouldAttack()
    {
        return Globals.getCurrentTurn() - lastActionTurn >= 10;
    }

    public String getName()
    {
        return IMAGES[image];
    }

    protected int getImage()
    {
        return image;
    }

    protected float randomProbability;
    private int sightRadius;
    protected int image;
    public static final String IMAGES[] = {
        "Skeleton", "Slime", "Ninja"
    };

}
