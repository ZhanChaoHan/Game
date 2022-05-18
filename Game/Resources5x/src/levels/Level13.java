// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Level13.java

package levels;


// Referenced classes of package levels:
//            Level

public class Level13
    implements Level
{

    public Level13()
    {
        gameWidth = 40;
        gameHeight = 30;
    }

    public Object[] readTiles()
    {
        Object t[] = {
            "t32", Integer.valueOf(0), Integer.valueOf(25), Integer.valueOf(40), Integer.valueOf(5), Integer.valueOf(3)
        };
        return t;
    }

    public Object[] readEnemies()
    {
        Object e[] = {
            "enemy18", Integer.valueOf(25), Integer.valueOf(7), Integer.valueOf(13), Integer.valueOf(8), Integer.valueOf(81), "enemy10", Integer.valueOf(25), Integer.valueOf(7), Integer.valueOf(13), 
            Integer.valueOf(18), Integer.valueOf(51)
        };
        return e;
    }

    public Object[] readItems()
    {
        Object i[] = {
            "hero_right", Integer.valueOf(5), Integer.valueOf(20), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(1)
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
