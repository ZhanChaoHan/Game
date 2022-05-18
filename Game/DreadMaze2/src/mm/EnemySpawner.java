// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EnemySpawner.java

package mm;

import java.util.ArrayList;

// Referenced classes of package mm:
//            EnemyEntity, AnimationSet, Globals, Maze, 
//            Room, MazeGenerator

public class EnemySpawner extends EnemyEntity
{

    public EnemySpawner(int x, int y, int difficulty, EnemyEntity spawned)
    {
        this(x, y, difficulty / 2 + 1, difficulty * 10, difficulty / 2, 0, 0.0F, (float)difficulty / 500F, 1000 / difficulty, spawned);
    }

    public EnemySpawner(int x, int y, int lev, int maxHP, int arm, int pow, float doge, 
            float rand, int delay, EnemyEntity spawned)
    {
        super(x, y, lev, maxHP, arm, pow, doge, rand, 0);
        spawnDelay = delay;
        enemy = spawned;
        createSpawnerAnimationSet();
    }

    protected void createAnimationSet()
    {
    }

    protected void createSpawnerAnimationSet()
    {
        image = enemy.getImage();
        animationSet = new AnimationSet("Stand", new AnimationSet.Animation((new StringBuilder("Monster/")).append(IMAGES[image]).append("/Spawner/Stand/").toString(), "", 1, 0, 0, 0));
        animationSet.addAnimation("Spawn", new AnimationSet.Animation((new StringBuilder("Monster/")).append(IMAGES[image]).append("/Spawner/Spawn/").toString(), (new StringBuilder("Monster/")).append(IMAGES[image]).append("/Spawner/Spawn/").toString(), 2, 1, 0, 15));
        animationSet.addAnimation("Die", new AnimationSet.Animation((new StringBuilder("Monster/")).append(IMAGES[image]).append("/Spawner/Stand/").toString(), (new StringBuilder("Monster/")).append(IMAGES[image]).append("/Die/").toString(), 1, 0, 0, 99));
        animationSet.addAnimation("TakeDamage", new AnimationSet.Animation((new StringBuilder("Monster/")).append(IMAGES[image]).append("/Spawner/TakeDamage/").toString(), (new StringBuilder("Monster/")).append(IMAGES[image]).append("/TakeDamage/").toString(), 1, 0, 2, 10));
    }

    public void act(boolean newTurn)
    {
        lifebarTime--;
        if(lifebarTime <= 0)
        {
            lifebarTime = 0;
            lifebarAlpha = lifebarAlpha - 0.05F <= 0.0F ? 0.0F : lifebarAlpha - 0.05F;
        } else
        {
            lifebarAlpha = lifebarAlpha + 0.05F >= 1.0F ? 1.0F : lifebarAlpha + 0.05F;
        }
        if(!newTurn)
            return;
        if(Math.random() < (double)randomProbability && Globals.getCurrentTurn() >= lastTimeSpawned + spawnDelay)
        {
            Room room = Maze.getRoom(x, y);
            ArrayList possibilities = new ArrayList();
            if(room.isOpen(0) && Maze.getRoom(x, y - 1).getCombatEntity() == null)
                possibilities.add(new MazeGenerator.Coordinate(x, y - 1));
            else
            if(room.isOpen(3) && Maze.getRoom(x, y + 1).getCombatEntity() == null)
                possibilities.add(new MazeGenerator.Coordinate(x, y + 1));
            else
            if(room.isOpen(2) && Maze.getRoom(x + 1, y).getCombatEntity() == null)
                possibilities.add(new MazeGenerator.Coordinate(x + 1, y));
            else
            if(room.isOpen(1) && Maze.getRoom(x - 1, y).getCombatEntity() == null)
                possibilities.add(new MazeGenerator.Coordinate(x - 1, y));
            if(!possibilities.isEmpty())
            {
                MazeGenerator.Coordinate c = (MazeGenerator.Coordinate)possibilities.get((int)(Math.random() * (double)possibilities.size()));
                Maze.getRoom(c.x, c.y).setCombatEntity(generateEnemyEntity(c.x, c.y));
                animationSet.setAnimation("Spawn");
                lastTimeSpawned = Globals.getCurrentTurn();
            }
        }
    }

    private EnemyEntity generateEnemyEntity(int newX, int newY)
    {
        return new EnemyEntity(newX, newY, enemy.getLevel(), enemy.getMaxHitPoints(), enemy.getDefense(), enemy.getPower(), enemy.getDodge(), enemy.getRandomProbability(), enemy.getSightRadius(), enemy.getImage());
    }

    private EnemyEntity enemy;
    private int lastTimeSpawned;
    private int spawnDelay;
}
