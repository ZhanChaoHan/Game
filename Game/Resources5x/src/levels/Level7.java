// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Level7.java

package levels;


// Referenced classes of package levels:
//            Level

public class Level7
    implements Level
{

    public Level7()
    {
        gameWidth = 245;
        gameHeight = 100;
    }

    public Object[] readTiles()
    {
        Object t[] = {
            "t2", Integer.valueOf(0), Integer.valueOf(90), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t2", Integer.valueOf(60), Integer.valueOf(90), Integer.valueOf(60), 
            Integer.valueOf(10), Integer.valueOf(3), "t2", Integer.valueOf(120), Integer.valueOf(90), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t2", Integer.valueOf(180), 
            Integer.valueOf(90), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t17", Integer.valueOf(70), Integer.valueOf(82), Integer.valueOf(8), Integer.valueOf(8), Integer.valueOf(4), 
            "t4", Integer.valueOf(125), Integer.valueOf(0), Integer.valueOf(120), Integer.valueOf(90), Integer.valueOf(4)
        };
        return t;
    }

    public Object[] readEnemies()
    {
        Object e[] = {
            "enemy16", Integer.valueOf(90), Integer.valueOf(85), Integer.valueOf(10), Integer.valueOf(5), Integer.valueOf(111)
        };
        return e;
    }

    public Object[] readItems()
    {
        Object i[] = {
            "hero_right", Integer.valueOf(10), Integer.valueOf(78), Integer.valueOf(9), Integer.valueOf(12), Integer.valueOf(1)
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
