// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CollisionHandler.java

package collision;

import gameobjects.GameObject;
import gameobjects.Sprite;
import java.util.ArrayList;
import java.util.Iterator;

public class CollisionHandler
{

    public CollisionHandler()
    {
    }

    public static void calculateCollision(Sprite s, ArrayList gameObjects)
    {
        if(Math.abs(s.getXPrev() - s.getX()) == 0 && Math.abs(s.getYPrev() - s.getY()) != 0 || Math.abs(s.getYPrev() - s.getY()) == 0 && Math.abs(s.getXPrev() - s.getX()) != 0)
            checkCollisionHorzVert(s, gameObjects);
        else
        if(Math.abs(s.getXPrev() - s.getX()) != 0 && Math.abs(s.getYPrev() - s.getY()) != 0)
            checkCollisionAngle(s, gameObjects);
    }

    private static void checkCollisionAngle(Sprite sprite, ArrayList gameObjects)
    {
        int dir = 0;
        double stepFactor = 0.0D;
        double rest = 0.0D;
        if(sprite.getX() > sprite.getXPrev() && sprite.getY() > sprite.getYPrev())
            dir = 0;
        else
        if(sprite.getX() > sprite.getXPrev() && sprite.getY() < sprite.getYPrev())
            dir = 1;
        else
        if(sprite.getX() < sprite.getXPrev() && sprite.getY() < sprite.getYPrev())
            dir = 2;
        else
        if(sprite.getX() < sprite.getXPrev() && sprite.getY() > sprite.getYPrev())
            dir = 3;
        if(Math.abs(sprite.getYPrev() - sprite.getY()) >= Math.abs(sprite.getXPrev() - sprite.getX()))
        {
            stepFactor = Math.abs(sprite.getYPrev() - sprite.getY()) / Math.abs(sprite.getXPrev() - sprite.getX());
            boolean coll = true;
            while(coll) 
            {
                if(rest > 1.0D)
                    rest--;
                rest += stepFactor - (double)(int)stepFactor;
                if(dir == 0 || dir == 1)
                    sprite.setX(sprite.getX() - 1);
                else
                if(dir == 2 || dir == 3)
                    sprite.setX(sprite.getX() + 1);
                if(dir == 0 || dir == 3)
                {
                    for(int a = 0; a < (int)stepFactor + (int)rest; a++)
                    {
                        sprite.setY(sprite.getY() - 1);
                        coll = checkCollision(sprite, gameObjects);
                        if(!coll)
                            break;
                    }

                } else
                if(dir == 1 || dir == 2)
                {
                    for(int a = 0; a < (int)stepFactor + (int)rest; a++)
                    {
                        sprite.setY(sprite.getY() + 1);
                        coll = checkCollision(sprite, gameObjects);
                        if(!coll)
                            break;
                    }

                }
                if(sprite.getX() == sprite.getXPrev() && sprite.getY() == sprite.getYPrev())
                    coll = false;
            }
        } else
        if(Math.abs(sprite.getXPrev() - sprite.getX()) >= Math.abs(sprite.getYPrev() - sprite.getY()))
        {
            stepFactor = Math.abs(sprite.getXPrev() - sprite.getX()) / Math.abs(sprite.getYPrev() - sprite.getY());
            boolean coll = true;
            while(coll) 
            {
                if(rest > 1.0D)
                    rest--;
                rest += stepFactor - (double)(int)stepFactor;
                if(dir == 1 || dir == 2)
                    sprite.setY(sprite.getY() + 1);
                else
                if(dir == 0 || dir == 3)
                    sprite.setY(sprite.getY() - 1);
                if(dir == 0 || dir == 1)
                {
                    for(int a = 0; a < (int)stepFactor + (int)rest; a++)
                    {
                        sprite.setX(sprite.getX() - 1);
                        coll = checkCollision(sprite, gameObjects);
                        if(!coll)
                            break;
                    }

                } else
                if(dir == 2 || dir == 3)
                {
                    for(int a = 0; a < (int)stepFactor + (int)rest; a++)
                    {
                        sprite.setX(sprite.getX() + 1);
                        coll = checkCollision(sprite, gameObjects);
                        if(!coll)
                            break;
                    }

                }
                if(sprite.getX() == sprite.getXPrev() && sprite.getY() == sprite.getYPrev())
                    coll = false;
            }
        }
    }

    private static void checkCollisionHorzVert(Sprite s, ArrayList gameObjects)
    {
        if(Math.abs(s.getYPrev() - s.getY()) == 0)
        {
            boolean coll = true;
            while(coll) 
            {
                if(s.getX() > s.getXPrev())
                    s.setX(s.getX() - 1);
                else
                if(s.getX() < s.getXPrev())
                    s.setX(s.getX() + 1);
                coll = checkCollision(s, gameObjects);
                if(s.getX() == s.getXPrev())
                    coll = false;
            }
        } else
        if(Math.abs(s.getXPrev() - s.getX()) == 0)
        {
            boolean coll = true;
            while(coll) 
            {
                if(s.getY() > s.getYPrev())
                    s.setY(s.getY() - 1);
                else
                if(s.getY() < s.getYPrev())
                    s.setY(s.getY() + 1);
                coll = checkCollision(s, gameObjects);
                if(s.getY() == s.getYPrev())
                    coll = false;
            }
        }
    }

    private static boolean checkCollision(Sprite h, ArrayList gameObjects)
    {
        for(Iterator iterator = gameObjects.iterator(); iterator.hasNext();)
        {
            GameObject g = (GameObject)iterator.next();
            if(h.getX() + h.getWidth() > g.getX() && h.getX() < g.getX() + g.getWidth() && h.getY() + h.getHeight() > g.getY() && h.getY() < g.getY() + g.getHeight())
                return true;
        }

        return false;
    }
}
