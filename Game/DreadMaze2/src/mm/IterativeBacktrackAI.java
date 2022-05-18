// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IterativeBacktrackAI.java

package mm;

import java.util.ArrayList;
import java.util.Stack;

// Referenced classes of package mm:
//            Main, Room

public class IterativeBacktrackAI
{
    private static class MazeCoordinate
    {

        public int direction;
        public int x;
        public int y;

        public MazeCoordinate(int dir, int nx, int ny)
        {
            direction = dir;
            x = nx;
            y = ny;
        }
    }


    public IterativeBacktrackAI()
    {
    }

    public static boolean[][] solveMaze(Room maze[][], int startX, int startY, int goalX, int goalY, Main frame)
    {
        Stack choices = new Stack();
        makeChoice(maze, new boolean[maze.length][maze[0].length], choices, startX, startY, goalX, goalY, frame);
        boolean path[][] = new boolean[maze.length][maze[0].length];
        while(!choices.isEmpty()) 
        {
            MazeCoordinate mc = (MazeCoordinate)choices.pop();
            path[mc.x][mc.y] = true;
        }
        path[goalX][goalY] = true;
        return path;
    }

    private static void makeChoice(Room maze[][], boolean visitedSpaces[][], Stack choices, int x, int y, int goalX, int goalY, Main frame)
    {
        while(x != goalX || y != goalY) 
        {
            if(frame != null)
            {
                frame.setSpacesVisited(visitedSpaces);
                frame.setCurrentPos(x, y);
                frame.repaint();
                try
                {
                    Thread.sleep(frame.getDrawDelay());
                }
                catch(Exception exception) { }
            }
            visitedSpaces[x][y] = true;
            ArrayList currentChoices = new ArrayList();
            while(currentChoices.size() < 4) 
            {
                int direction = (int)(Math.random() * 4D);
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
                if(currentChoices.contains(Integer.valueOf(direction)))
                    continue;
                currentChoices.add(Integer.valueOf(direction));
                if(!maze[x][y].isOpen(direction) || visitedSpaces[x + dx][y + dy])
                    continue;
                choices.push(new MazeCoordinate(direction, x, y));
                x += dx;
                y += dy;
                break;
            }
            if(currentChoices.size() >= 4)
            {
                int direction = ((MazeCoordinate)choices.pop()).direction;
                if(direction == 0)
                    y++;
                else
                if(direction == 3)
                    y--;
                else
                if(direction == 1)
                    x++;
                else
                if(direction == 2)
                    x--;
            }
        }
    }
}
