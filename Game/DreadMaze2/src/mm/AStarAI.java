// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AStarAI.java

package mm;

import java.util.ArrayList;
import java.util.Stack;

// Referenced classes of package mm:
//            Room, Main, MazeGenerator

public class AStarAI
{
    private static class RoomInfo
    {

        public int cost;
        public Room parent;

        public RoomInfo(int c, Room p)
        {
            cost = c;
            parent = p;
        }
    }


    public AStarAI()
    {
    }

    public static Stack solveMaze(Room maze[][], int startX, int startY, int goalX, int goalY, Main frame)
    {
        ArrayList open = new ArrayList();
        ArrayList closed = new ArrayList();
        boolean visitedSpaces[][] = new boolean[maze.length][maze[0].length];
        RoomInfo info[][] = new RoomInfo[maze.length][maze[0].length];
        info[startX][startY] = new RoomInfo(0, null);
        open.add(maze[startX][startY]);
        while(open.size() != 0) 
        {
            Room r = (Room)open.get(0);
            if(frame != null)
            {
                visitedSpaces[r.getX()][r.getY()] = true;
                frame.setSpacesVisited(visitedSpaces);
                frame.setCurrentPos(r.getX(), r.getY());
                frame.repaint();
                try
                {
                    Thread.sleep(frame.getDrawDelay());
                }
                catch(Exception exception) { }
            }
            if(r == maze[goalX][goalY])
                break;
            open.remove(r);
            closed.add(r);
            for(int k = 0; k < 4; k++)
                if(r.isOpen(k))
                {
                    int dx = 0;
                    int dy = 0;
                    if(k == 0)
                        dy = -1;
                    else
                    if(k == 3)
                        dy = 1;
                    else
                    if(k == 1)
                        dx = -1;
                    else
                    if(k == 2)
                        dx = 1;
                    int nextCost = info[r.getX()][r.getY()].cost + 1;
                    Room r2 = maze[r.getX() + dx][r.getY() + dy];
                    if(info[r.getX() + dx][r.getY() + dy] == null)
                        info[r.getX() + dx][r.getY() + dy] = new RoomInfo(nextCost, r);
                    if(nextCost < info[r.getX() + dx][r.getY() + dy].cost)
                    {
                        if(open.contains(r2))
                            open.remove(r2);
                        if(closed.contains(r2))
                            closed.remove(r2);
                    }
                    if(!open.contains(r2) && !closed.contains(r2))
                        addInOrder(open, info, r2, goalX, goalY);
                }

        }
        Stack path = new Stack();
        int x = goalX;
        Room p;
        for(int y = goalY; x != startX || y != startY; y = p.getY())
        {
            path.push(new MazeGenerator.Coordinate(x, y));
            p = info[x][y].parent;
            x = p.getX();
        }

        return path;
    }

    private static void addInOrder(ArrayList open, RoomInfo info[][], Room r, int goalX, int goalY)
    {
        for(int i = 0; i < open.size(); i++)
        {
            Room r2 = (Room)open.get(i);
            if(Math.abs(goalX - r.getX()) + Math.abs(goalY - r.getY()) < Math.abs(goalX - r2.getX()) + Math.abs(goalY - r2.getY()))
            {
                open.add(i, r);
                return;
            }
        }

        open.add(r);
    }
}
