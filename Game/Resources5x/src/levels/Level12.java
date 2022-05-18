// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Level12.java

package levels;


// Referenced classes of package levels:
//            Level

public class Level12
    implements Level
{

    public Level12()
    {
        gameWidth = 345;
        gameHeight = 90;
    }

    public Object[] readTiles()
    {
        Object t[] = {
            "t7", Integer.valueOf(0), Integer.valueOf(70), Integer.valueOf(20), Integer.valueOf(20), Integer.valueOf(3), "t7", Integer.valueOf(20), Integer.valueOf(70), Integer.valueOf(20), 
            Integer.valueOf(20), Integer.valueOf(3), "t7", Integer.valueOf(40), Integer.valueOf(70), Integer.valueOf(20), Integer.valueOf(20), Integer.valueOf(3), "t7", Integer.valueOf(60), 
            Integer.valueOf(70), Integer.valueOf(20), Integer.valueOf(20), Integer.valueOf(3), "t7", Integer.valueOf(80), Integer.valueOf(70), Integer.valueOf(20), Integer.valueOf(20), Integer.valueOf(3), 
            "t8", Integer.valueOf(0), Integer.valueOf(20), Integer.valueOf(40), Integer.valueOf(20), Integer.valueOf(3), "t8", Integer.valueOf(40), Integer.valueOf(20), Integer.valueOf(40), 
            Integer.valueOf(20), Integer.valueOf(3), "t9", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t9", Integer.valueOf(30), 
            Integer.valueOf(0), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t9", Integer.valueOf(60), Integer.valueOf(0), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), 
            "t9", Integer.valueOf(90), Integer.valueOf(0), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t9", Integer.valueOf(100), Integer.valueOf(70), Integer.valueOf(30), 
            Integer.valueOf(20), Integer.valueOf(3), "t8", Integer.valueOf(130), Integer.valueOf(70), Integer.valueOf(40), Integer.valueOf(20), Integer.valueOf(3), "t8", Integer.valueOf(130), 
            Integer.valueOf(50), Integer.valueOf(40), Integer.valueOf(20), Integer.valueOf(3), "t4", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(120), Integer.valueOf(90), Integer.valueOf(4), 
            "t4", Integer.valueOf(120), Integer.valueOf(0), Integer.valueOf(120), Integer.valueOf(90), Integer.valueOf(4), "t9", Integer.valueOf(120), Integer.valueOf(0), Integer.valueOf(30), 
            Integer.valueOf(20), Integer.valueOf(3), "t9", Integer.valueOf(185), Integer.valueOf(70), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t9", Integer.valueOf(210), 
            Integer.valueOf(70), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t2", Integer.valueOf(285), Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), 
            "t39", Integer.valueOf(285), Integer.valueOf(65), Integer.valueOf(60), Integer.valueOf(15), Integer.valueOf(1)
        };
        return t;
    }

    public Object[] readEnemies()
    {
        Object e[] = new Object[0];
        return e;
    }

    public Object[] readItems()
    {
        Object i[] = new Object[0];
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
