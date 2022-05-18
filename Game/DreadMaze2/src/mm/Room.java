// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Room.java

package mm;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

// Referenced classes of package mm:
//            ItemEntity, Maze, Item, BossEntity, 
//            ImageManager, CombatEntity

public class Room
{

    public Room(int i, int j, int type)
    {
        open = new int[4];
        x = i;
        y = j;
        entity = null;
        item = null;
        visited = 0;
        wallType = type;
        if(wallType < 0 || wallType >= 6)
            wallType = 0;
    }

    public boolean isOpen(int direction)
    {
        return open[direction] == 6;
    }

    public boolean canSee(int direction)
    {
        return (open[direction] & 2) != 0;
    }

    public boolean canMove(int direction)
    {
        return (open[direction] & 4) != 0;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public CombatEntity getCombatEntity()
    {
        return entity;
    }

    public ItemEntity getItemEntity()
    {
        return item;
    }

    public void setOpen(int direction, boolean b)
    {
        open[direction] = b ? 6 : 1;
    }

    public void setOpenType(int direction, int type)
    {
        open[direction] = type;
    }

    public void setWallTransparency(float trans, int directions[])
    {
        if(directions == null)
            directions = (new int[] {
                0, 1, 2, 3
            });
        for(int i = 0; i < directions.length; i++)
            wallTransparencies[directions[i]] = trans;

    }

    public Item pickUpItem(Item droppedItem)
    {
        Item i = null;
        if(item != null)
            i = item.getItem();
        if(droppedItem != null)
            item = new ItemEntity(x, y, droppedItem);
        else
            Maze.getRoom(x, y).setItemEntity(null);
        return i;
    }

    public void setCombatEntity(CombatEntity ce)
    {
        entity = ce;
    }

    public void setItemEntity(ItemEntity ie)
    {
        item = ie;
    }

    public void setVisited(int visit)
    {
        if(visit == 1)
            visit = visited != 1 && visited != 2 ? 1 : 2;
        visited = visit;
    }

    public boolean wasVisited()
    {
        return visited != 0;
    }

    public void draw(Graphics g, float delta, ArrayList entities)
    {
        int f = Maze.getGridSizeWithWalls();
        if(wasVisited() || (entity instanceof BossEntity))
        {
            g.drawImage(ImageManager.getImage((new StringBuilder("Floor/")).append(wallType).append(".png").toString()), x * f, y * f, f, f, null);
            drawWalls(g, f);
            if(visited == 3 || visited == 1 || (entity instanceof BossEntity))
            {
                if(item != null)
                    item.draw(g, delta);
                if(entity != null && entity.getX() == x && entity.getY() == y)
                    entities.add(entity);
            } else
            if(visited == 2)
            {
                g.setColor(new Color(0, 0, 0, 150));
                g.fillRect(x * f, y * f, f, f);
            }
        } else
        if(visited == 0)
        {
            g.setColor(Color.BLACK);
            g.fillRect(x * f, y * f, f, f);
        }
    }

    private void drawWalls(Graphics g, int f)
    {
        int w = Maze.getWallSize();
        int s = Maze.getGridSize();
        if(hasWalls())
        {
            g.drawImage(ImageManager.getImage((new StringBuilder("Wall/")).append(wallType).append(".png").toString()), x * f, y * f, w, w, null);
            g.drawImage(ImageManager.getImage((new StringBuilder("Wall/")).append(wallType).append(".png").toString()), x * f + s + w, y * f, w, w, null);
            g.drawImage(ImageManager.getImage((new StringBuilder("Wall/")).append(wallType).append(".png").toString()), x * f, y * f + s + w, w, w, null);
            g.drawImage(ImageManager.getImage((new StringBuilder("Wall/")).append(wallType).append(".png").toString()), x * f + s + w, y * f + s + w, w, w, null);
            if(!isOpen(0))
                g.drawImage(ImageManager.getImage((new StringBuilder("Wall/")).append(wallType).append(".png").toString()), x * f, y * f, f, w, null);
            if(!isOpen(3))
                g.drawImage(ImageManager.getImage((new StringBuilder("Wall/")).append(wallType).append(".png").toString()), x * f, y * f + s + w, f, w, null);
            if(!isOpen(1))
                g.drawImage(ImageManager.getImage((new StringBuilder("Wall/")).append(wallType).append(".png").toString()), x * f, y * f, w, f, null);
            if(!isOpen(2))
                g.drawImage(ImageManager.getImage((new StringBuilder("Wall/")).append(wallType).append(".png").toString()), x * f + s + w, y * f, w, f, null);
        }
    }

    public void preloadAll()
    {
        ImageManager.preloadImage((new StringBuilder("Wall/")).append(wallType).append(".png").toString());
        ImageManager.preloadImage((new StringBuilder("Floor/")).append(wallType).append(".png").toString());
    }

    public boolean hasWalls()
    {
        return !isOpen(0) || !isOpen(3) || !isOpen(1) || !isOpen(2);
    }

    private int open[];
    private int x;
    private int y;
    private CombatEntity entity;
    private ItemEntity item;
    private int visited;
    private int wallType;
    private float wallTransparencies[] = {
        1.0F, 1.0F, 1.0F, 1.0F
    };
    public static final int VISITED_NEVER = 0;
    public static final int VISITED_BEFORE = 1;
    public static final int VISITED_LONG_BEFORE = 2;
    public static final int VISITED_NOW = 3;
    public static final int OPEN_NONE = 1;
    public static final int OPEN_VISION = 2;
    public static final int OPEN_MOVE = 4;
    public static final int OPEN_ALL = 6;
}
