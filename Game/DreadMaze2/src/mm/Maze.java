// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Maze.java

package mm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Stack;

// Referenced classes of package mm:
//            Globals, MazeGenerator, SinuosityPanel, BossEntity, 
//            Room, PlayerEntity, EnemyEntity, EnemySpawner, 
//            ItemEntity, Item, CombatEntity, AnimationSet, 
//            ImageManager, Entity, AStarAI

public class Maze
{

    public Maze()
    {
    }

    public static void createMaze(int difficulty)
    {
        boolean bossLevel = false;
        if(difficulty > 0 && difficulty % 5 == 0)
            bossLevel = true;
        difficulty *= Globals.getPlayers().length;
        int difficultyCount = difficulty <= 25 ? difficulty : 25;
        maze = MazeGenerator.generateMaze(difficultyCount + 8, difficultyCount + 5);
        int numMonsters = (int)((float)difficultyCount * 2.5F);
        int numSpawners = difficultyCount / 2;
        int numItems = difficultyCount + 2;
        difficulty /= Globals.getPlayers().length;
        placePlayers(Globals.getPlayers(), 0, 0, 0);
        if(bossLevel)
        {
            SinuosityPanel.createOutput("BOSS BATTLE!!!!!!1!!1!one!!!", "", "Other/bossbattle.wav", 100, 100, 20, 10, 40);
            int x = maze.length / 2 - 1;
            int y = maze[0].length / 2 - 1;
            BossEntity boss = new BossEntity(x + 1, y + 1, difficulty);
            maze[x][y].setCombatEntity(boss);
            maze[x + 1][y].setCombatEntity(boss);
            maze[x][y + 1].setCombatEntity(boss);
            maze[x + 1][y + 1].setCombatEntity(boss);
            createEmptyArea(x - 3, y - 3, 8, 8);
            goalX = -1;
            goalY = -1;
        } else
        {
            for(int numPlaced = 0; numPlaced < numMonsters;)
            {
                int x = (int)(Math.random() * (double)maze.length);
                int y = (int)(Math.random() * (double)maze[0].length);
                if(maze[x][y].getCombatEntity() == null)
                {
                    maze[x][y].setCombatEntity(createEnemy(x, y, difficulty));
                    numPlaced++;
                }
            }

            for(int numPlaced = 0; numPlaced < numSpawners;)
            {
                int x = (int)(Math.random() * (double)maze.length);
                int y = (int)(Math.random() * (double)maze[0].length);
                if(maze[x][y].getCombatEntity() == null)
                {
                    maze[x][y].setCombatEntity(createSpawner(x, y, difficulty));
                    numPlaced++;
                }
            }

            for(int numPlaced = 0; numPlaced < numItems;)
            {
                int x = (int)(Math.random() * (double)maze.length);
                int y = (int)(Math.random() * (double)maze[0].length);
                if(maze[x][y].getItemEntity() == null)
                {
                    maze[x][y].setItemEntity(createItem(x, y, difficulty));
                    numPlaced++;
                }
            }

            goalX = maze.length - 1;
            goalY = maze[0].length - 1;
        }
        preloadAll();
        backingImage = new BufferedImage(maze.length * getGridSizeWithWalls(), maze[0].length * getGridSizeWithWalls(), 2);
    }

    private static void createEmptyArea(int x, int y, int width, int height)
    {
        int xStart = x >= 0 ? x : 0;
        int yStart = y >= 0 ? y : 0;
        int xEnd = xStart + width < maze.length ? xStart + width : maze.length - 1;
        int yEnd = yStart + height < maze[0].length ? yStart + height : maze[0].length - 1;
        for(int i = xStart; i < xEnd; i++)
        {
            for(int j = yStart; j < yEnd; j++)
            {
                if(i > 0)
                {
                    maze[i][j].setOpen(1, true);
                    maze[i - 1][j].setOpen(2, true);
                }
                if(i < maze.length - 1)
                {
                    maze[i][j].setOpen(2, true);
                    maze[i + 1][j].setOpen(1, true);
                }
                if(j > 0)
                {
                    maze[i][j].setOpen(0, true);
                    maze[i][j - 1].setOpen(3, true);
                }
                if(j < maze[0].length - 1)
                {
                    maze[i][j].setOpen(3, true);
                    maze[i][j + 1].setOpen(0, true);
                }
            }

        }

    }

    private static void placePlayers(PlayerEntity players[], int index, int x, int y)
    {
        maze[x][y].setCombatEntity(players[index]);
        players[index].move(x, y);
        players[index].revive();
        if(index + 1 < players.length)
            if(maze[x][y].isOpen(3) && maze[x][y + 1].getCombatEntity() == null)
                placePlayers(players, index + 1, x, y + 1);
            else
            if(maze[x][y].isOpen(2) && maze[x + 1][y].getCombatEntity() == null)
                placePlayers(players, index + 1, x + 1, y);
            else
            if(maze[x][y].isOpen(0) && maze[x][y - 1].getCombatEntity() == null)
                placePlayers(players, index + 1, x, y - 1);
            else
            if(maze[x][y].isOpen(1) && maze[x - 1][y].getCombatEntity() == null)
                placePlayers(players, index + 1, x - 1, y);
            else
                System.err.println((new StringBuilder("Unable to place player #")).append(index).append(" from (").append(x).append(",").append(y).append(")!").toString());
    }

    private static EnemyEntity createEnemy(int x, int y, int difficulty)
    {
        double spread = (double)difficulty / 5D;
        return new EnemyEntity(x, y, Math.max(1, difficulty + (int)(Math.random() * spread - spread / 2D)));
    }

    private static EnemySpawner createSpawner(int x, int y, int difficulty)
    {
        EnemyEntity enemy = createEnemy(0, 0, difficulty);
        return new EnemySpawner(x, y, difficulty, enemy);
    }

    private static ItemEntity createItem(int x, int y, int difficulty)
    {
        double spread = (double)difficulty / 5D;
        difficulty = Math.max(1, difficulty + (int)(Math.random() * spread - spread / 2D));
        double random = Math.random();
        if(random <= 0.14999999999999999D)
            return new ItemEntity(x, y, new Item(2, difficulty));
        if(random <= 0.29999999999999999D)
            return new ItemEntity(x, y, new Item(1, difficulty));
        else
            return new ItemEntity(x, y, new Item(0, difficulty));
    }

    private static void preloadAll()
    {
        for(int i = 0; i < maze.length; i++)
        {
            for(int j = 0; j < maze[0].length; j++)
            {
                if(maze[i][j].getCombatEntity() != null)
                    maze[i][j].getCombatEntity().getAnimationSet().preloadAll();
                if(maze[i][j].getItemEntity() != null)
                    maze[i][j].getItemEntity().getAnimationSet().preloadAll();
                maze[i][j].preloadAll();
            }

        }

        ImageManager.preloadImage("Floor/goal.png");
    }

    public static Room getRoom(int x, int y)
    {
        return maze[x][y];
    }

    public static void draw(Graphics g, float delta)
    {
        g2 = backingImage.createGraphics();
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, backingImage.getWidth(), backingImage.getHeight());
        ArrayList entities = new ArrayList();
        for(int i = 0; i < maze.length; i++)
        {
            for(int j = 0; j < maze[0].length; j++)
                maze[i][j].draw(g2, delta, entities);

        }

        g2.setColor(Color.CYAN);
        for(int i = 0; i < entities.size(); i++)
            ((Entity)entities.get(i)).draw(g2, delta);

        for(int i = 0; i < Globals.getPlayers().length; i++)
            if(Globals.getPlayers()[i].isDead())
                Globals.getPlayers()[i].draw(g2, delta);

        int f = getGridSizeWithWalls();
        int w = getWallSize();
        int s = getGridSize();
        g2.drawImage(ImageManager.getImage("Floor/goal.png"), goalX * f + w, goalY * f + w, s, s, null);
        g2.dispose();
        g.drawImage(backingImage, (int)scrollX, (int)scrollY, null);
    }

    public static CombatEntity moveEntity(CombatEntity entity)
    {
        if(entity.pathIsEmpty())
            return null;
        MazeGenerator.Coordinate move = entity.peekPath();
        if(maze[move.x][move.y].getCombatEntity() == null)
        {
            maze[entity.getX()][entity.getY()].setCombatEntity(null);
            entity.move(move.x, move.y);
            maze[move.x][move.y].setCombatEntity(entity);
            giveItem(entity);
            return null;
        } else
        {
            return maze[move.x][move.y].getCombatEntity();
        }
    }

    private static void giveItem(CombatEntity entity)
    {
        if(maze[entity.getX()][entity.getY()].getItemEntity() != null)
        {
            Item i = maze[entity.getX()][entity.getY()].pickUpItem(null);
            entity.pickUp(i);
        }
    }

    public static void updateVisitedSpaces()
    {
        PlayerEntity players[] = Globals.getPlayers();
        for(int i = 0; i < maze.length; i++)
        {
            for(int j = 0; j < maze[0].length; j++)
                if(maze[i][j].wasVisited())
                    maze[i][j].setVisited(1);

        }

        boolean fadedRooms[][] = new boolean[maze.length][maze[0].length];
        for(int i = 0; i < players.length; i++)
            setVisitedNow(players[i].getX(), players[i].getY(), fadedRooms);

        int ai[];
        for(int i = 0; i < fadedRooms.length; i++)
        {
            for(int j = 0; j < fadedRooms[0].length; j++)
            {
                float trans = fadedRooms[i][j] ? 0.25F : 1.0F;
                ai = new int[2];
                ai[0] = 1;
                maze[i][j].setWallTransparency(trans, ai);
                if(i > 0)
                    maze[i - 1][j].setWallTransparency(trans, new int[] {
                        2
                    });
                if(j > 0)
                    maze[i][j - 1].setWallTransparency(trans, new int[] {
                        3
                    });
            }

        }

    }

    private static void setVisitedNow(int i, int j, boolean fadedRooms[][])
    {
        for(int angle = 0; angle < 32; angle++)
        {
            float dx = Globals.cosPiBy16(angle);
            float dy = Globals.sinPiBy16(angle);
            for(float r = 1.0F; r < 8F; r++)
            {
                int px = (int)(dx * (r - 1.0F)) + i;
                int py = (int)(dy * (r - 1.0F)) + j;
                if(px < 0 || py < 0 || px >= maze.length || py >= maze[0].length)
                    break;
                int x = (int)(dx * r) + i;
                int y = (int)(dy * r) + j;
                int cx = x - px;
                int cy = y - py;
                if(cx == 0 || cy == 0 ? (cx != 0 || cy != 0) && (!maze[px][py].canSee(MazeGenerator.getDirection(cx, cy)) || !maze[x][y].canSee(MazeGenerator.getDirection(-cx, -cy))) : !maze[px][py].canSee(MazeGenerator.getDirection(cx, 0)) || !maze[px][py].canSee(MazeGenerator.getDirection(0, cy)) || !maze[x][y].canSee(MazeGenerator.getDirection(-cx, 0)) || !maze[x][y].canSee(MazeGenerator.getDirection(0, -cy)))
                    break;
                maze[x][y].setVisited(3);
                fadedRooms[x][y] = true;
            }

        }

    }

    public static int getWidth()
    {
        return maze.length;
    }

    public static int getHeight()
    {
        return maze[0].length;
    }

    public static void setPlayerLocation(int x, int y)
    {
        float dx = Globals.getScreenWidth() / 2 - x;
        float dy = Globals.getScreenHeight() / 2 - y;
        scrollX = (dx - scrollX) * 0.05F + 0.95F * scrollX;
        scrollY = (dy - scrollY) * 0.05F + 0.95F * scrollY;
    }

    public static float getScrollX()
    {
        return scrollX;
    }

    public static float getScrollY()
    {
        return scrollY;
    }

    public static boolean playersWon(int targetX, int targetY)
    {
        boolean on = false;
        PlayerEntity players[] = Globals.getPlayers();
        for(int i = 0; i < players.length; i++)
            if(players[i].getX() == goalX && players[i].getY() == goalY && targetX == goalX && targetY == goalY)
            {
                timeOn++;
                on = true;
                if(timeOn >= 30)
                    return true;
            }

        if(!on)
            timeOn = 0;
        return false;
    }

    public static void moveEnemies(boolean newTurn)
    {
        for(int i = 0; i < maze.length; i++)
        {
            for(int j = 0; j < maze[0].length; j++)
            {
                CombatEntity ce = maze[i][j].getCombatEntity();
                if(ce != null && (ce instanceof CombatEntity))
                    ce.act(newTurn);
            }

        }

    }

    public static int getGridSize()
    {
        return gridSize;
    }

    public static int getGridSizeWithWalls()
    {
        return gridSize + 2 * getWallSize();
    }

    public static int getWallSize()
    {
        return gridSize / 5;
    }

    public static void setGridSize(int newSize)
    {
        gridSize = newSize;
    }

    public static Stack getPath(int startX, int startY, int endX, int endY)
    {
        return AStarAI.solveMaze(maze, startX, startY, endX, endY, null);
    }

    public static void setGoalPosition(int x, int y)
    {
        goalX = x;
        goalY = y;
    }

    public static Graphics getScrollGraphics()
    {
        return g2;
    }

    private static int gridSize = 50;
    private static Room maze[][];
    private static int goalX;
    private static int goalY;
    private static float scrollX;
    private static float scrollY;
    private static BufferedImage backingImage;
    private static Graphics g2;
    private static int timeOn = 0;

}
