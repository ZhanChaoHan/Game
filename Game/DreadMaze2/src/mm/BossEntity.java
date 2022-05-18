// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BossEntity.java

package mm;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

// Referenced classes of package mm:
//            EnemyEntity, AnimationSet, Maze, ItemEntity, 
//            Item, Room, SinuosityPanel, PlayerEntity, 
//            Globals, MazeGenerator, CombatEntity

public class BossEntity extends EnemyEntity
{

    public BossEntity(int x, int y, int dd)
    {
        this(x, y, dd + 5, (dd + 1) * 12, (int)((float)(dd + 1) * 1.2F), (int)((float)(dd + 1) * 1.75F), (float)(dd + 1) / 50F, dd);
    }

    public BossEntity(int x, int y, int lev, int maxHP, int def, int pow, float doge, 
            int dd)
    {
        super(x, y, lev, maxHP, def, pow, doge, 0.0F, 0);
        difficulty = dd;
        timeSincePotion = 0;
    }

    protected void createAnimationSet()
    {
        animationSet = new AnimationSet("Stand", new AnimationSet.Animation("Monster/Minotaur/Stand/", "", 1, 0, 0, 0));
        animationSet.addAnimation("Move", new AnimationSet.Animation("Monster/Minotaur/Walk/", "", 1, 5, 0, 1));
        animationSet.addAnimation("TakeDamage", new AnimationSet.Animation("Monster/Minotaur/TakeDamage/", "", 1, 0, 2, 10));
        animationSet.addAnimation("Attack", new AnimationSet.Animation("Monster/Minotaur/Attack/", "Monster/Minotaur/Attack/", 1, 0, 2, 15));
        animationSet.addAnimation("Die", new AnimationSet.Animation("Monster/Minotaur/Stand/", "Monster/Minotaur/Die/", 1, 0, 0, 0));
    }

    public void act(boolean newTurn)
    {
        lifebarAlpha = 1.0F;
        if(!newTurn)
            return;
        timeSincePotion++;
        if(Math.random() <= 0.002D && timeSincePotion >= 250 || timeSincePotion >= 500)
        {
            timeSincePotion = 0;
            int randX = (int)(Math.random() * (double)Maze.getWidth());
            int randY = (int)(Math.random() * (double)Maze.getHeight());
            Maze.getRoom(randX, randY).setItemEntity(new ItemEntity(randX, randY, new Item(0, difficulty, 0, difficulty * 2, 0, 0, 0.0F)));
            SinuosityPanel.createOutput(0, 0, 255, "A potion spawned!", "", "", 100, 100, 10, 50, 20);
        }
        ArrayList possibilities = new ArrayList();
        if(Maze.getRoom(x, y).isOpen(2) && (Maze.getRoom(x + 1, y).getCombatEntity() instanceof PlayerEntity))
            possibilities.add(new MazeGenerator.Coordinate(x + 1, y));
        if(Maze.getRoom(x, y).isOpen(3) && (Maze.getRoom(x, y + 1).getCombatEntity() instanceof PlayerEntity))
            possibilities.add(new MazeGenerator.Coordinate(x, y + 1));
        if(Maze.getRoom(x - 1, y).isOpen(3) && (Maze.getRoom(x - 1, y + 1).getCombatEntity() instanceof PlayerEntity))
            possibilities.add(new MazeGenerator.Coordinate(x - 1, y + 1));
        if(Maze.getRoom(x - 1, y).isOpen(1) && (Maze.getRoom(x - 2, y).getCombatEntity() instanceof PlayerEntity))
            possibilities.add(new MazeGenerator.Coordinate(x - 2, y));
        if(Maze.getRoom(x - 1, y - 1).isOpen(1) && (Maze.getRoom(x - 2, y - 1).getCombatEntity() instanceof PlayerEntity))
            possibilities.add(new MazeGenerator.Coordinate(x - 2, y - 1));
        if(Maze.getRoom(x - 1, y - 1).isOpen(0) && (Maze.getRoom(x - 1, y - 2).getCombatEntity() instanceof PlayerEntity))
            possibilities.add(new MazeGenerator.Coordinate(x - 1, y - 2));
        if(Maze.getRoom(x, y - 1).isOpen(0) && (Maze.getRoom(x, y - 2).getCombatEntity() instanceof PlayerEntity))
            possibilities.add(new MazeGenerator.Coordinate(x, y - 2));
        if(Maze.getRoom(x, y - 1).isOpen(2) && (Maze.getRoom(x + 1, y - 1).getCombatEntity() instanceof PlayerEntity))
            possibilities.add(new MazeGenerator.Coordinate(x + 1, y - 1));
        if(possibilities.isEmpty())
        {
            if(shouldMove())
            {
                int dx = -1 + (int)(Math.random() * 3D);
                int dy = -1 + (int)(Math.random() * 3D);
                if(isValidLocation(x + dx, y + dy))
                {
                    moveTo(x + dx, y + dy);
                    lastActionTurn = Globals.getCurrentTurn();
                    animationSet.setAnimation("Move");
                }
            }
            return;
        } else
        {
            MazeGenerator.Coordinate c = (MazeGenerator.Coordinate)possibilities.get((int)(Math.random() * (double)possibilities.size()));
            attack(Maze.getRoom(c.x, c.y).getCombatEntity());
            return;
        }
    }

    public void die()
    {
        super.die();
        Maze.setGoalPosition(Maze.getWidth() - 1, Maze.getHeight() - 1);
        Maze.getRoom(x - 1, y).setCombatEntity(null);
        Maze.getRoom(x, y - 1).setCombatEntity(null);
        Maze.getRoom(x - 1, y - 1).setCombatEntity(null);
        Maze.getRoom(x - 1, y - 1).setItemEntity(new ItemEntity(x, y, new Item(1, difficulty + 2)));
        Maze.getRoom(x - 1, y).setItemEntity(new ItemEntity(x - 1, y, new Item(2, difficulty + 2)));
        Maze.getRoom(x, y - 1).setItemEntity(new ItemEntity(x, y - 1, new Item(0, difficulty + 4)));
    }

    public void draw(Graphics g, float delta)
    {
        int f = Maze.getGridSizeWithWalls();
        int w = Maze.getWallSize();
        int s = Maze.getGridSize();
        float interp = delta / ((float)Globals.getTurnLength() / 1000F);
        interpolation = interpolation + interp <= 1.0F ? interpolation + interp : 1.0F;
        g.drawImage(animationSet.getCurrentImage(), getDrawX() - f, getDrawY() - f, s * 2 + 2 * w, s * 2 + 2 * w, null);
        animationSet.tick();
        if(lifebarAlpha > 0.0F)
        {
            float diff = (float)hitPoints / (float)maxHitPoints - lifebarValue;
            if(Math.abs(diff) < 1.0F * delta)
                lifebarValue = (float)hitPoints / (float)maxHitPoints;
            else
                lifebarValue += (Math.abs(diff) / diff) * 1.0F * delta;
            g.setColor(new Color(1.0F, 0.0F, 0.0F, 0.5F * lifebarAlpha));
            g.drawRect(getDrawX() - f, getDrawY() - f - s / 5, s * 2 + 2 * w, s / 5);
            g.fillRect(getDrawX() - f, getDrawY() - f - s / 5, (int)((float)(s * 2 + 2 * w) * lifebarValue), s / 5);
        }
    }

    public String getName()
    {
        return "Minotaur";
    }

    public CombatEntity moveTo(int newX, int newY)
    {
        if(x != newX || y != newY)
        {
            lastX = x;
            lastY = y;
            interpolation = 0.0F;
            Maze.getRoom(x, y).setCombatEntity(null);
            Maze.getRoom(x - 1, y).setCombatEntity(null);
            Maze.getRoom(x, y - 1).setCombatEntity(null);
            Maze.getRoom(x - 1, y - 1).setCombatEntity(null);
            x = newX;
            y = newY;
            Maze.getRoom(x, y).setCombatEntity(this);
            Maze.getRoom(x - 1, y).setCombatEntity(this);
            Maze.getRoom(x, y - 1).setCombatEntity(this);
            Maze.getRoom(x - 1, y - 1).setCombatEntity(this);
        }
        return null;
    }

    protected boolean shouldMove()
    {
        return Globals.getCurrentTurn() - lastActionTurn >= 10;
    }

    public boolean isValidLocation(int newX, int newY)
    {
        Room rooms[] = {
            Maze.getRoom(newX, newY), Maze.getRoom(newX - 1, newY), Maze.getRoom(newX, newY - 1), Maze.getRoom(newX - 1, newY - 1)
        };
        for(int i = 0; i < rooms.length; i++)
            if(rooms[i].hasWalls() || rooms[i].getCombatEntity() != null && rooms[i].getCombatEntity() != this)
                return false;

        return true;
    }

    private int difficulty;
    private int timeSincePotion;
    public static final int MAX_TIME_SINCE_POTION = 500;
    public static final int MIN_TIME_SINCE_POTION = 250;
}
