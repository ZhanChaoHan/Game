// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Entity.java

package mm;

import java.awt.Graphics;

// Referenced classes of package mm:
//            Globals, AnimationSet, Maze

public abstract class Entity
{

    public Entity(int startX, int startY)
    {
        x = startX;
        y = startY;
        lastX = startX;
        lastY = startY;
        interpolation = 1.0F;
        createAnimationSet();
    }

    protected abstract void createAnimationSet();

    public abstract void act(boolean flag);

    public void draw(Graphics g, float delta)
    {
        float interp = delta / ((float)Globals.getTurnLength() / 1000F);
        interpolation = interpolation + interp <= 1.0F ? interpolation + interp : 1.0F;
        g.drawImage(animationSet.getCurrentImage(), getDrawX(), getDrawY(), Maze.getGridSize(), Maze.getGridSize(), null);
        animationSet.tick();
    }

    public int getDrawX()
    {
        return (int)(((float)(x - lastX) * interpolation + (float)lastX) * (float)Maze.getGridSizeWithWalls() + (float)Maze.getWallSize());
    }

    public int getDrawY()
    {
        return (int)(((float)(y - lastY) * interpolation + (float)lastY) * (float)Maze.getGridSizeWithWalls() + (float)Maze.getWallSize());
    }

    public void move(int newX, int newY)
    {
        if(x != newX || y != newY)
        {
            lastX = x;
            lastY = y;
            x = newX;
            y = newY;
            interpolation = 0.0F;
        }
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public AnimationSet getAnimationSet()
    {
        return animationSet;
    }

    protected int x;
    protected int y;
    protected int lastX;
    protected int lastY;
    protected float interpolation;
    public static final float INTERPOLATION_AMOUNT = 0.1F;
    protected AnimationSet animationSet;
}
