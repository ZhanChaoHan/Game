// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Level4.java

package levels;


// Referenced classes of package levels:
//            Level

public class Level4
    implements Level
{

    public Level4()
    {
        gameWidth = 120;
        gameHeight = 90;
    }

    public Object[] readTiles()
    {
        Object t[] = {
            "t16", Integer.valueOf(0), Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t16", Integer.valueOf(60), Integer.valueOf(80), Integer.valueOf(60), 
            Integer.valueOf(10), Integer.valueOf(3)
        };
        return t;
    }

    public Object[] readEnemies()
    {
        Object e[] = {
            "enemy15", Integer.valueOf(85), Integer.valueOf(55), Integer.valueOf(30), Integer.valueOf(25), Integer.valueOf(71)
        };
        return e;
    }

    public Object[] readItems()
    {
        Object i[] = {
            "hero_right", Integer.valueOf(5), Integer.valueOf(68), Integer.valueOf(9), Integer.valueOf(12), Integer.valueOf(1), "item9", Integer.valueOf(15), Integer.valueOf(75), Integer.valueOf(5), 
            Integer.valueOf(5), Integer.valueOf(10), "item9", Integer.valueOf(100), Integer.valueOf(75), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(10), "item9", Integer.valueOf(35), 
            Integer.valueOf(75), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(10), "item9", Integer.valueOf(55), Integer.valueOf(75), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(10), 
            "item9", Integer.valueOf(75), Integer.valueOf(75), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(10)
        };
        return i;
    }

    public int getGameWidth()
    {
        return gameWidth;
    }

    public int getGameHeight()
    {
        return gameHeight;
    }

    private int gameWidth;
    private int gameHeight;
}
