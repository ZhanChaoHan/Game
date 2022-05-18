// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Level9.java

package levels;


// Referenced classes of package levels:
//            Level

public class Level9
    implements Level
{

    public Level9()
    {
        gameWidth = 480;
        gameHeight = 90;
    }

    public Object[] readTiles()
    {
        Object t[] = {
            "t13", Integer.valueOf(0), Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t13", Integer.valueOf(60), Integer.valueOf(80), Integer.valueOf(60), 
            Integer.valueOf(10), Integer.valueOf(3), "t13", Integer.valueOf(120), Integer.valueOf(0), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t13", Integer.valueOf(120), 
            Integer.valueOf(70), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t4", Integer.valueOf(120), Integer.valueOf(0), Integer.valueOf(120), Integer.valueOf(90), Integer.valueOf(4), 
            "t6", Integer.valueOf(180), Integer.valueOf(65), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), "t13", Integer.valueOf(60), Integer.valueOf(0), Integer.valueOf(60), 
            Integer.valueOf(10), Integer.valueOf(3), "t13", Integer.valueOf(60), Integer.valueOf(60), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t4", Integer.valueOf(0), 
            Integer.valueOf(0), Integer.valueOf(120), Integer.valueOf(90), Integer.valueOf(4), "t14", Integer.valueOf(60), Integer.valueOf(70), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(4), 
            "t14", Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(4), "t14", Integer.valueOf(120), Integer.valueOf(10), Integer.valueOf(60), 
            Integer.valueOf(10), Integer.valueOf(4), "t13", Integer.valueOf(180), Integer.valueOf(70), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t4", Integer.valueOf(360), 
            Integer.valueOf(0), Integer.valueOf(120), Integer.valueOf(90), Integer.valueOf(4), "t13", Integer.valueOf(360), Integer.valueOf(70), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), 
            "t13", Integer.valueOf(360), Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t13", Integer.valueOf(180), Integer.valueOf(80), Integer.valueOf(60), 
            Integer.valueOf(10), Integer.valueOf(3), "t13", Integer.valueOf(120), Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t13", Integer.valueOf(180), 
            Integer.valueOf(0), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t45", Integer.valueOf(240), Integer.valueOf(70), Integer.valueOf(90), Integer.valueOf(15), Integer.valueOf(3), 
            "t45", Integer.valueOf(330), Integer.valueOf(70), Integer.valueOf(90), Integer.valueOf(15), Integer.valueOf(3), "t5", Integer.valueOf(260), Integer.valueOf(8), Integer.valueOf(35), 
            Integer.valueOf(17), Integer.valueOf(4), "t5", Integer.valueOf(325), Integer.valueOf(23), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t10", Integer.valueOf(240), 
            Integer.valueOf(64), Integer.valueOf(60), Integer.valueOf(6), Integer.valueOf(1), "t10", Integer.valueOf(300), Integer.valueOf(64), Integer.valueOf(60), Integer.valueOf(6), Integer.valueOf(1), 
            "t11", Integer.valueOf(50), Integer.valueOf(40), Integer.valueOf(10), Integer.valueOf(40), Integer.valueOf(6), "t11", Integer.valueOf(50), Integer.valueOf(0), Integer.valueOf(10), 
            Integer.valueOf(40), Integer.valueOf(6), "t6", Integer.valueOf(370), Integer.valueOf(65), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5)
        };
        return t;
    }

    public Object[] readEnemies()
    {
        Object e[] = {
            "enemy2", Integer.valueOf(255), Integer.valueOf(61), Integer.valueOf(14), Integer.valueOf(9), Integer.valueOf(11), "enemy16", Integer.valueOf(335), Integer.valueOf(65), Integer.valueOf(10), 
            Integer.valueOf(5), Integer.valueOf(111), "enemy16", Integer.valueOf(85), Integer.valueOf(55), Integer.valueOf(10), Integer.valueOf(5), Integer.valueOf(111), "enemy16", Integer.valueOf(315), 
            Integer.valueOf(65), Integer.valueOf(10), Integer.valueOf(5), Integer.valueOf(111), "enemy16", Integer.valueOf(145), Integer.valueOf(65), Integer.valueOf(10), Integer.valueOf(5), Integer.valueOf(111)
        };
        return e;
    }

    public Object[] readItems()
    {
        Object i[] = {
            "hero_right", Integer.valueOf(5), Integer.valueOf(68), Integer.valueOf(9), Integer.valueOf(12), Integer.valueOf(1)
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
