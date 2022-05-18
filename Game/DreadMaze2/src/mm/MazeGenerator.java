// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MazeGenerator.java

package mm;

import java.util.ArrayList;

// Referenced classes of package mm:
//            Main, Room

public class MazeGenerator
{
    public static class Coordinate
    {

        public boolean equals(Object o)
        {
            if(!(o instanceof Coordinate))
                return false;
            Coordinate c = (Coordinate)o;
            return c.x == x && c.y == y;
        }

        public int x;
        public int y;

        public Coordinate(int nx, int ny)
        {
            x = nx;
            y = ny;
        }
    }


    public MazeGenerator()
    {
    }

    public static Room[][] generateMaze(int width, int height)
    {
        return generateMaze(width, height, null);
    }

    public static Room[][] generateMaze(int width, int height, Main frame)
    {
        Room maze[][] = new Room[width][height];
        int groups[][] = new int[width][height];
        int currentGroup = 0;
        int type = (int)(Math.random() * 6D);
        ArrayList emptyCoords = new ArrayList();
        for(int x = 0; x < width; x++)
        {
            for(int y = 0; y < height; y++)
                emptyCoords.add(new Coordinate(x, y));

        }

        while(!emptyCoords.isEmpty()) 
        {
            Coordinate c = (Coordinate)emptyCoords.get((int)(Math.random() * (double)emptyCoords.size()));
            constructRooms(maze, c.x, c.y, new ArrayList(), emptyCoords, groups, currentGroup, frame, type);
            currentGroup++;
        }
        return maze;
    }

    public static Coordinate getDeltas(int dir)
    {
        switch(dir)
        {
        case 0: // '\0'
            return new Coordinate(0, -1);

        case 3: // '\003'
            return new Coordinate(0, 1);

        case 1: // '\001'
            return new Coordinate(-1, 0);

        case 2: // '\002'
            return new Coordinate(1, 0);
        }
        return new Coordinate(0, 0);
    }

    public static int getDirection(int dx, int dy)
    {
        switch(dx)
        {
        case 1: // '\001'
            return 2;

        case -1: 
            return 1;

        case 0: // '\0'
            switch(dy)
            {
            case 1: // '\001'
                return 3;

            case -1: 
                return 0;
            }
            break;
        }
        return -1;
    }

    private static void constructRooms(Room maze[][], int x, int y, ArrayList checkedDirs, ArrayList emptyCoords, int groups[][], int currentGroup, Main frame, 
            int type)
    {
        if(checkedDirs.size() >= 4)
            return;
        if(maze[x][y] == null)
        {
            groups[x][y] = currentGroup;
            addNewRoom(maze, x, y, groups, frame, type);
            removeCoordinateFromList(emptyCoords, new Coordinate(x, y));
            currentGroup = groups[x][y];
        }
        int direction = (int)(Math.random() * 4D);
        if(!checkedDirs.contains(Integer.valueOf(direction)))
        {
            checkedDirs.add(Integer.valueOf(direction));
            int dx = 0;
            int dy = 0;
            if(direction == 0)
                dy = -1;
            else
            if(direction == 3)
                dy = 1;
            else
            if(direction == 1)
                dx = -1;
            else
            if(direction == 2)
                dx = 1;
            if(x + dx < 0 || x + dx >= maze.length || y + dy < 0 || y + dy >= maze[0].length)
                constructRooms(maze, x, y, checkedDirs, emptyCoords, groups, currentGroup, frame, type);
            else
            if(maze[x + dx][y + dy] == null)
            {
                maze[x][y].setOpen(direction, true);
                constructRooms(maze, x + dx, y + dy, new ArrayList(), emptyCoords, groups, currentGroup, frame, type);
            } else
            {
                constructRooms(maze, x, y, checkedDirs, emptyCoords, groups, currentGroup, frame, type);
            }
        } else
        {
            constructRooms(maze, x, y, checkedDirs, emptyCoords, groups, currentGroup, frame, type);
        }
    }

    private static void addNewRoom(Room maze[][], int x, int y, int groups[][], Main frame, int type)
    {
        maze[x][y] = new Room(x, y, type);
        if(y > 0 && maze[x][y - 1] != null && maze[x][y - 1].isOpen(3))
            maze[x][y].setOpen(0, true);
        if(y < maze[0].length - 1 && maze[x][y + 1] != null && maze[x][y + 1].isOpen(0))
            maze[x][y].setOpen(3, true);
        if(x > 0 && maze[x - 1][y] != null && maze[x - 1][y].isOpen(2))
            maze[x][y].setOpen(1, true);
        if(x < maze.length - 1 && maze[x + 1][y] != null && maze[x + 1][y].isOpen(1))
            maze[x][y].setOpen(2, true);
        randomlyCombineGroup(maze, x, y, groups);
        if(frame != null)
        {
            frame.setMaze(maze);
            frame.repaint();
            try
            {
                Thread.sleep(frame.getDrawDelay());
            }
            catch(Exception exception) { }
        }
    }

    private static void linkRooms(Room maze[][], int x1, int y1, int x2, int y2)
    {
        if(Math.abs(x1 - x2) + Math.abs(y1 - y2) != 1)
            return;
        if(x1 - x2 == 1)
        {
            maze[x1][y1].setOpen(1, true);
            maze[x2][y2].setOpen(2, true);
        } else
        if(x2 - x1 == 1)
        {
            maze[x1][y1].setOpen(2, true);
            maze[x2][y2].setOpen(1, true);
        } else
        if(y1 - y2 == 1)
        {
            maze[x1][y1].setOpen(0, true);
            maze[x2][y2].setOpen(3, true);
        } else
        if(y2 - y1 == 1)
        {
            maze[x1][y1].setOpen(3, true);
            maze[x2][y2].setOpen(0, true);
        }
    }

    private static void randomlyCombineGroup(Room maze[][], int x, int y, int groups[][])
    {
        ArrayList possibilities = new ArrayList();
        if(x > 0 && maze[x - 1][y] != null && groups[x][y] != groups[x - 1][y])
            possibilities.add(Integer.valueOf(1));
        if(x < maze.length - 1 && maze[x + 1][y] != null && groups[x][y] != groups[x + 1][y])
            possibilities.add(Integer.valueOf(2));
        if(y > 0 && maze[x][y - 1] != null && groups[x][y] != groups[x][y - 1])
            possibilities.add(Integer.valueOf(0));
        if(y < maze[0].length - 1 && maze[x][y + 1] != null && groups[x][y] != groups[x][y + 1])
            possibilities.add(Integer.valueOf(3));
        if(possibilities.isEmpty())
            return;
        int random = (int)(Math.random() * (double)possibilities.size());
        if(((Integer)possibilities.get(random)).intValue() == 0)
        {
            combineGroups(maze, x, y, x, y - 1, groups);
            linkRooms(maze, x, y, x, y - 1);
        } else
        if(((Integer)possibilities.get(random)).intValue() == 3)
        {
            combineGroups(maze, x, y, x, y + 1, groups);
            linkRooms(maze, x, y, x, y + 1);
        } else
        if(((Integer)possibilities.get(random)).intValue() == 2)
        {
            combineGroups(maze, x, y, x + 1, y, groups);
            linkRooms(maze, x, y, x + 1, y);
        } else
        if(((Integer)possibilities.get(random)).intValue() == 1)
        {
            combineGroups(maze, x, y, x - 1, y, groups);
            linkRooms(maze, x, y, x - 1, y);
        }
    }

    private static void combineGroups(Room maze[][], int x1, int y1, int x2, int y2, int groups[][])
    {
        if(groups[x1][y1] == groups[x2][y2])
            return;
        int newGroup = 0;
        ArrayList open = new ArrayList();
        ArrayList closed = new ArrayList();
        if(groups[x1][y1] < groups[x2][y2])
        {
            newGroup = groups[x1][y1];
            open.add(new Coordinate(x2, y2));
            closed.add(new Coordinate(x1, y1));
        } else
        if(groups[x2][y2] < groups[x1][y1])
        {
            newGroup = groups[x2][y2];
            open.add(new Coordinate(x1, y1));
            closed.add(new Coordinate(x2, y2));
        }
        while(!open.isEmpty()) 
        {
            Coordinate c = (Coordinate)open.get(0);
            groups[c.x][c.y] = newGroup;
            closed.add(c);
            open.remove(c);
            if(maze[c.x][c.y].isOpen(0) && c.y > 0 && maze[c.x][c.y - 1] != null && !listContainsCoordinate(closed, new Coordinate(c.x, c.y - 1)))
                if(groups[c.x][c.y - 1] == newGroup)
                    closed.add(new Coordinate(c.x, c.y - 1));
                else
                    open.add(new Coordinate(c.x, c.y - 1));
            if(maze[c.x][c.y].isOpen(3) && c.y < maze[0].length - 1 && maze[c.x][c.y + 1] != null && !listContainsCoordinate(closed, new Coordinate(c.x, c.y + 1)))
                if(groups[c.x][c.y + 1] == newGroup)
                    closed.add(new Coordinate(c.x, c.y + 1));
                else
                    open.add(new Coordinate(c.x, c.y + 1));
            if(maze[c.x][c.y].isOpen(1) && c.x > 0 && maze[c.x - 1][c.y] != null && !listContainsCoordinate(closed, new Coordinate(c.x - 1, c.y)))
                if(groups[c.x - 1][c.y] == newGroup)
                    closed.add(new Coordinate(c.x - 1, c.y));
                else
                    open.add(new Coordinate(c.x - 1, c.y));
            if(maze[c.x][c.y].isOpen(2) && c.x < maze.length - 1 && maze[c.x + 1][c.y] != null && !listContainsCoordinate(closed, new Coordinate(c.x + 1, c.y)))
                if(groups[c.x + 1][c.y] == newGroup)
                    closed.add(new Coordinate(c.x + 1, c.y));
                else
                    open.add(new Coordinate(c.x + 1, c.y));
        }
    }

    private static boolean listContainsCoordinate(ArrayList list, Coordinate c)
    {
        for(int i = 0; i < list.size(); i++)
            if(((Coordinate)list.get(i)).equals(c))
                return true;

        return false;
    }

    private static void removeCoordinateFromList(ArrayList list, Coordinate c)
    {
        for(int i = 0; i < list.size(); i++)
            if(((Coordinate)list.get(i)).equals(c))
            {
                list.remove(i);
                return;
            }

    }

    public static final int NUM_DIRECTIONS = 4;
    public static final int NUM_MAZE_TYPES = 6;
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;
}
