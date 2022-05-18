// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ObjectsArray.java

import java.awt.Graphics;

public class ObjectsArray
{

    public ObjectsArray(String s, int j)
    {
        emptySearch = 0;
        arrayMax = j;
        gameObject = new GameObject[j];
        for(i = 0; i < j; i++)
        {
            if(s.equals("Enemy"))
                gameObject[i] = new Enemy();
            if(s.equals("Bullet"))
                gameObject[i] = new Bullet();
            if(s.equals("Shoot"))
                gameObject[i] = new Shoot();
            if(s.equals("Effect"))
                gameObject[i] = new Effect();
            if(s.equals("Item"))
                gameObject[i] = new Item();
        }

    }

    public void allMove()
    {
        for(i = 0; i < arrayMax; i++)
            if(gameObject[i].getExist())
                gameObject[i].move();

    }

    public void allDraw(Graphics g)
    {
        for(i = 0; i < arrayMax; i++)
            if(gameObject[i].getExist())
                gameObject[i].draw(g);

    }

    public void allErase()
    {
        for(i = 0; i < arrayMax; i++)
            gameObject[i].erase();

    }

    public GameObject getEmpty()
    {
        for(i = 0; i < arrayMax; i++)
        {
            if(!gameObject[emptySearch].getExist())
                return gameObject[emptySearch];
            emptySearch++;
            if(emptySearch >= arrayMax)
                emptySearch = 0;
        }

        return null;
    }

    public GameObject getObject(int j)
    {
        return gameObject[j];
    }

    public int getArrayMax()
    {
        return arrayMax;
    }

    private GameObject gameObject[];
    private int emptySearch;
    private int arrayMax;
    private int i;
}
