// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Level8.java

package levels;


// Referenced classes of package levels:
//            Level

public class Level8
    implements Level
{

    public Level8()
    {
        gameWidth = 120;
        gameHeight = 90;
    }

    public Object[] readTiles()
    {
        Object t[] = {
            "t2", Integer.valueOf(0), Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t2", Integer.valueOf(60), Integer.valueOf(80), Integer.valueOf(60), 
            Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(0), Integer.valueOf(70), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(10), 
            Integer.valueOf(70), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(0), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), 
            "t26", Integer.valueOf(40), Integer.valueOf(40), Integer.valueOf(56), Integer.valueOf(40), Integer.valueOf(4)
        };
        return t;
    }

    public Object[] readEnemies()
    {
        Object e[] = {
            "enemy23", Integer.valueOf(90), Integer.valueOf(72), Integer.valueOf(10), Integer.valueOf(8), Integer.valueOf(106)
        };
        return e;
    }

    public Object[] readItems()
    {
        Object i[] = {
            "hero_right", Integer.valueOf(25), Integer.valueOf(68), Integer.valueOf(9), Integer.valueOf(12), Integer.valueOf(1)
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
