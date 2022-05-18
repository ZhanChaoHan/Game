// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CombatEntity.java

package mm;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Stack;

// Referenced classes of package mm:
//            Entity, AnimationSet, Maze, SinuosityPanel, 
//            Item, Globals, MazeGenerator

public abstract class CombatEntity extends Entity
{

    public CombatEntity(int x, int y, int lev, int maxHP, int arm, int pow, float doge)
    {
        super(x, y);
        lifebarTime = 0;
        lifebarAlpha = 0.0F;
        lifebarValue = 1.0F;
        level = lev;
        maxHitPoints = maxHP;
        hitPoints = maxHitPoints;
        defense = arm;
        power = pow;
        dodge = doge;
        targetX = x;
        targetY = y;
        dodge = Math.min(0.9F, dodge);
    }

    public int getLevel()
    {
        return level;
    }

    public int getMaxHitPoints()
    {
        return maxHitPoints;
    }

    public int getHitPoints()
    {
        return hitPoints;
    }

    public int getDefense()
    {
        return defense;
    }

    public int getPower()
    {
        return power;
    }

    public float getDodge()
    {
        return dodge;
    }

    public int getTargetX()
    {
        return targetX;
    }

    public int getTargetY()
    {
        return targetY;
    }

    public boolean pathIsEmpty()
    {
        return path.isEmpty();
    }

    public MazeGenerator.Coordinate peekPath()
    {
        return (MazeGenerator.Coordinate)path.peek();
    }

    public Item getWeapon()
    {
        return weapon;
    }

    public Item getArmor()
    {
        return armor;
    }

    public void modifyStats(int maxHP, int HP, int arm, int pow, float doge)
    {
        maxHitPoints += maxHP;
        modifyHitPoints(HP);
        defense += arm;
        power += pow;
        dodge += doge;
    }

    public void takeDamage(int change)
    {
        if(Math.random() < (double)dodge)
            return;
        int amount = change - defense;
        if(amount <= 0)
            amount = 1;
        modifyHitPoints(-amount);
        animationSet.setAnimation("TakeDamage");
        int f = Maze.getGridSizeWithWalls();
        int xPos = (x * f + f / 2) - (int)(Math.random() * 10D);
        int yPos = (y * f + f / 2) - (int)(Math.random() * 10D);
        SinuosityPanel.createOutput((new StringBuilder("-")).append(amount).toString(), "", "", xPos, yPos, 5, 100, 16);
    }

    public void gainLife(int change)
    {
        modifyHitPoints(change);
    }

    private void modifyHitPoints(int change)
    {
        lifebarTime = 100;
        hitPoints += change;
        if(hitPoints <= 0)
            die();
        else
        if(hitPoints > maxHitPoints)
            hitPoints = maxHitPoints;
    }

    public void levelUp()
    {
        level++;
    }

    public abstract void die();

    public void pickUp(Item item)
    {
        if(item == null)
            return;
        item.pickUp(this);
        if(item.getType() == 0)
            animationSet.setAnimation("GetItem");
        else
        if(item.getType() == 1)
        {
            if(weapon == null)
            {
                weapon = item;
                animationSet.setAnimation("GetItem");
            } else
            if(weapon != null && item.getLevel() > weapon.getLevel())
            {
                weapon.drop(this);
                weapon = item;
                animationSet.setAnimation("GetItem");
            } else
            {
                item.drop(this);
            }
        } else
        if(item.getType() == 2)
            if(armor == null)
            {
                armor = item;
                animationSet.setAnimation("GetItem");
            } else
            if(armor != null && item.getLevel() > armor.getLevel())
            {
                armor.drop(this);
                armor = item;
                animationSet.setAnimation("GetItem");
            } else
            {
                item.drop(this);
            }
    }

    public CombatEntity moveTo(int newX, int newY)
    {
        if(newX == x && newY == y)
            return null;
        if(newX != targetX || newY != targetY || path == null)
        {
            targetX = newX;
            targetY = newY;
            path = Maze.getPath(x, y, targetX, targetY);
        }
        if(shouldMove())
        {
            CombatEntity entity = Maze.moveEntity(this);
            if(entity == null)
            {
                lastActionTurn = Globals.getCurrentTurn();
                animationSet.setAnimation("Move");
                path.pop();
            }
            return entity;
        } else
        {
            return null;
        }
    }

    protected boolean shouldMove()
    {
        return Globals.getCurrentTurn() - lastActionTurn >= 1;
    }

    public void attack(CombatEntity defender)
    {
        if(defender == null)
            return;
        if(shouldAttack())
        {
            defender.takeDamage(getPower());
            lastActionTurn = Globals.getCurrentTurn();
            animationSet.setAnimation("Attack");
        }
    }

    protected boolean shouldAttack()
    {
        return Globals.getCurrentTurn() - lastActionTurn >= 5;
    }

    public void act(boolean newTurn)
    {
        lifebarTime = 1;
        if(lifebarTime <= 0)
        {
            lifebarTime = 0;
            lifebarAlpha = lifebarAlpha - 0.05F <= 0.0F ? 0.0F : lifebarAlpha - 0.05F;
        } else
        {
            lifebarAlpha = lifebarAlpha + 0.05F >= 1.0F ? 1.0F : lifebarAlpha + 0.05F;
        }
    }

    public void draw(Graphics g, float delta)
    {
        super.draw(g, delta);
        if(lifebarAlpha > 0.0F)
        {
            float diff = (float)hitPoints / (float)maxHitPoints - lifebarValue;
            if(Math.abs(diff) < 1.0F * delta)
                lifebarValue = (float)hitPoints / (float)maxHitPoints;
            else
                lifebarValue += (Math.abs(diff) / diff) * 1.0F * delta;
            int drawX = getDrawX();
            int drawY = getDrawY();
            int s = Maze.getGridSize();
            g.setColor(new Color(1.0F, 0.0F, 0.0F, 0.5F * lifebarAlpha));
            g.drawRect(drawX, drawY - s / 10, s, s / 10);
            g.fillRect(drawX, drawY - s / 10, (int)((float)s * lifebarValue), s / 8);
        }
    }

    protected int level;
    protected int maxHitPoints;
    protected int hitPoints;
    protected int defense;
    protected int power;
    protected float dodge;
    protected Item weapon;
    protected Item armor;
    protected int lastActionTurn;
    protected int targetX;
    protected int targetY;
    protected Stack path;
    protected int lifebarTime;
    protected float lifebarAlpha;
    protected float lifebarValue;
    public static final int MAX_LIFEBAR_TIME = 100;
    public static final float ALPHA_FADE = 0.05F;
    public static final float LIFEBAR_INTERPOLATION = 1F;
}
