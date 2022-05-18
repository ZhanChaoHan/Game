// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BacktrackAI.java

package mm;

import java.util.ArrayList;
import java.util.Stack;

// Referenced classes of package mm:
//            Main, Room

public class BacktrackAI
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


    public BacktrackAI()
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
        if(x == goalX && y == goalY)
            return;
        visitedSpaces[x][y] = true;
        int direction;
        for(ArrayList currentChoices = new ArrayList(); currentChoices.size() < 4;)
        {
            direction = (int)(Math.random() * 4D);
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
            if(!currentChoices.contains(Integer.valueOf(direction)))
            {
                currentChoices.add(Integer.valueOf(direction));
                if(maze[x][y].isOpen(direction) && !visitedSpaces[x + dx][y + dy])
                {
                    choices.push(new MazeCoordinate(direction, x, y));
                    makeChoice(maze, visitedSpaces, choices, x + dx, y + dy, goalX, goalY, frame);
                    return;
                }
            }
        }

        direction = ((MazeCoordinate)choices.pop()).direction;
        if(direction == 0)
            makeChoice(maze, visitedSpaces, choices, x, y + 1, goalX, goalY, frame);
        else
        if(direction == 3)
            makeChoice(maze, visitedSpaces, choices, x, y - 1, goalX, goalY, frame);
        else
        if(direction == 1)
            makeChoice(maze, visitedSpaces, choices, x - 1, y, goalX, goalY, frame);
        else
        if(direction == 2)
            makeChoice(maze, visitedSpaces, choices, x + 1, y, goalX, goalY, frame);
    }
}
