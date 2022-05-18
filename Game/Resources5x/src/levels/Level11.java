// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Level11.java

package levels;


// Referenced classes of package levels:
//            Level

public class Level11
    implements Level
{

    public Level11()
    {
        gameWidth = 40;
        gameHeight = 30;
    }

    public Object[] readTiles()
    {
        Object t[] = {
            "t24", Integer.valueOf(0), Integer.valueOf(25), Integer.valueOf(40), Integer.valueOf(5), Integer.valueOf(3), "t18", Integer.valueOf(20), Integer.valueOf(20), Integer.valueOf(20), 
            Integer.valueOf(5), Integer.valueOf(3)
        };
        return t;
    }

    public Object[] readEnemies()
    {
        Object e[] = {
            "enemy11", Integer.valueOf(23), Integer.valueOf(6), Integer.valueOf(16), Integer.valueOf(14), Integer.valueOf(56)
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
