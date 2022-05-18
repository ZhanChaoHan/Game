// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Level10.java

package levels;


// Referenced classes of package levels:
//            Level

public class Level10
    implements Level
{

    public Level10()
    {
        gameWidth = 430;
        gameHeight = 95;
    }

    public Object[] readTiles()
    {
        Object t[] = {
            "t27", Integer.valueOf(0), Integer.valueOf(60), Integer.valueOf(40), Integer.valueOf(5), Integer.valueOf(3), "t27", Integer.valueOf(40), Integer.valueOf(60), Integer.valueOf(40), 
            Integer.valueOf(5), Integer.valueOf(3), "t6", Integer.valueOf(75), Integer.valueOf(85), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), "t13", Integer.valueOf(5), 
            Integer.valueOf(65), Integer.valueOf(40), Integer.valueOf(5), Integer.valueOf(3), "t16", Integer.valueOf(0), Integer.valueOf(65), Integer.valueOf(5), Integer.valueOf(20), Integer.valueOf(3), 
            "t16", Integer.valueOf(0), Integer.valueOf(75), Integer.valueOf(5), Integer.valueOf(20), Integer.valueOf(3), "t24", Integer.valueOf(5), Integer.valueOf(90), Integer.valueOf(40), 
            Integer.valueOf(5), Integer.valueOf(3), "t32", Integer.valueOf(45), Integer.valueOf(90), Integer.valueOf(40), Integer.valueOf(5), Integer.valueOf(3), "t23", Integer.valueOf(45), 
            Integer.valueOf(85), Integer.valueOf(40), Integer.valueOf(5), Integer.valueOf(3), "t22", Integer.valueOf(45), Integer.valueOf(65), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(3), 
            "t22", Integer.valueOf(65), Integer.valueOf(65), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(3), "t6", Integer.valueOf(40), Integer.valueOf(80), Integer.valueOf(5), 
            Integer.valueOf(5), Integer.valueOf(5), "t6", Integer.valueOf(85), Integer.valueOf(80), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), "t18", Integer.valueOf(85), 
            Integer.valueOf(90), Integer.valueOf(20), Integer.valueOf(5), Integer.valueOf(3), "t18", Integer.valueOf(95), Integer.valueOf(90), Integer.valueOf(20), Integer.valueOf(5), Integer.valueOf(3), 
            "t33", Integer.valueOf(115), Integer.valueOf(75), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(3), "t26", Integer.valueOf(115), Integer.valueOf(85), Integer.valueOf(20), 
            Integer.valueOf(10), Integer.valueOf(3), "t26", Integer.valueOf(115), Integer.valueOf(50), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(3), "t20", Integer.valueOf(45), 
            Integer.valueOf(65), Integer.valueOf(40), Integer.valueOf(30), Integer.valueOf(4), "t33", Integer.valueOf(135), Integer.valueOf(75), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(3), 
            "t26", Integer.valueOf(135), Integer.valueOf(85), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(155), Integer.valueOf(85), Integer.valueOf(20), 
            Integer.valueOf(10), Integer.valueOf(3), "t26", Integer.valueOf(135), Integer.valueOf(50), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(3), "t26", Integer.valueOf(155), 
            Integer.valueOf(50), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(3), "t26", Integer.valueOf(175), Integer.valueOf(60), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(3), 
            "t33", Integer.valueOf(175), Integer.valueOf(85), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(195), Integer.valueOf(85), Integer.valueOf(20), 
            Integer.valueOf(10), Integer.valueOf(3), "t26", Integer.valueOf(175), Integer.valueOf(50), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(3), "t26", Integer.valueOf(215), 
            Integer.valueOf(85), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(215), Integer.valueOf(65), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(3), 
            "t26", Integer.valueOf(215), Integer.valueOf(75), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(3), "t26", Integer.valueOf(195), Integer.valueOf(40), Integer.valueOf(20), 
            Integer.valueOf(10), Integer.valueOf(3), "t11", Integer.valueOf(210), Integer.valueOf(60), Integer.valueOf(5), Integer.valueOf(25), Integer.valueOf(6), "t11", Integer.valueOf(210), 
            Integer.valueOf(50), Integer.valueOf(5), Integer.valueOf(25), Integer.valueOf(6), "t26", Integer.valueOf(215), Integer.valueOf(40), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(3), 
            "t26", Integer.valueOf(175), Integer.valueOf(40), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(235), Integer.valueOf(65), Integer.valueOf(20), 
            Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(255), Integer.valueOf(65), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(3), "t26", Integer.valueOf(235), 
            Integer.valueOf(40), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(3), "t6", Integer.valueOf(210), Integer.valueOf(50), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), 
            "t6", Integer.valueOf(255), Integer.valueOf(50), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), "t6", Integer.valueOf(210), Integer.valueOf(60), Integer.valueOf(5), 
            Integer.valueOf(5), Integer.valueOf(5), "t8", Integer.valueOf(275), Integer.valueOf(60), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(3), "t33", Integer.valueOf(275), 
            Integer.valueOf(65), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(3), "t27", Integer.valueOf(255), Integer.valueOf(40), Integer.valueOf(40), Integer.valueOf(5), Integer.valueOf(3), 
            "t16", Integer.valueOf(295), Integer.valueOf(40), Integer.valueOf(5), Integer.valueOf(20), Integer.valueOf(3), "t16", Integer.valueOf(295), Integer.valueOf(50), Integer.valueOf(5), 
            Integer.valueOf(20), Integer.valueOf(3), "t6", Integer.valueOf(155), Integer.valueOf(70), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), "t6", Integer.valueOf(110), 
            Integer.valueOf(70), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), "t6", Integer.valueOf(155), Integer.valueOf(60), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), 
            "t11", Integer.valueOf(110), Integer.valueOf(65), Integer.valueOf(5), Integer.valueOf(25), Integer.valueOf(6), "t11", Integer.valueOf(110), Integer.valueOf(40), Integer.valueOf(5), 
            Integer.valueOf(25), Integer.valueOf(6), "t11", Integer.valueOf(110), Integer.valueOf(15), Integer.valueOf(5), Integer.valueOf(25), Integer.valueOf(6), "t3", Integer.valueOf(80), 
            Integer.valueOf(35), Integer.valueOf(5), Integer.valueOf(30), Integer.valueOf(3), "t3", Integer.valueOf(80), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(30), Integer.valueOf(3), 
            "t24", Integer.valueOf(255), Integer.valueOf(35), Integer.valueOf(40), Integer.valueOf(5), Integer.valueOf(3), "t24", Integer.valueOf(215), Integer.valueOf(25), Integer.valueOf(40), 
            Integer.valueOf(5), Integer.valueOf(3), "t24", Integer.valueOf(175), Integer.valueOf(25), Integer.valueOf(40), Integer.valueOf(5), Integer.valueOf(3), "t24", Integer.valueOf(135), 
            Integer.valueOf(25), Integer.valueOf(40), Integer.valueOf(5), Integer.valueOf(3), "t26", Integer.valueOf(115), Integer.valueOf(40), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(3), 
            "t26", Integer.valueOf(115), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(115), Integer.valueOf(20), Integer.valueOf(20), 
            Integer.valueOf(10), Integer.valueOf(3), "t27", Integer.valueOf(80), Integer.valueOf(0), Integer.valueOf(40), Integer.valueOf(5), Integer.valueOf(3), "t11", Integer.valueOf(110), 
            Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(25), Integer.valueOf(6), "t35", Integer.valueOf(115), Integer.valueOf(0), Integer.valueOf(30), Integer.valueOf(10), Integer.valueOf(3), 
            "t35", Integer.valueOf(175), Integer.valueOf(0), Integer.valueOf(30), Integer.valueOf(10), Integer.valueOf(3), "t35", Integer.valueOf(145), Integer.valueOf(0), Integer.valueOf(30), 
            Integer.valueOf(10), Integer.valueOf(3), "t35", Integer.valueOf(205), Integer.valueOf(0), Integer.valueOf(30), Integer.valueOf(10), Integer.valueOf(3), "t26", Integer.valueOf(235), 
            Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(3), "t35", Integer.valueOf(225), Integer.valueOf(0), Integer.valueOf(30), Integer.valueOf(10), Integer.valueOf(3), 
            "t27", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(40), Integer.valueOf(5), Integer.valueOf(3), "t27", Integer.valueOf(295), Integer.valueOf(0), Integer.valueOf(40), 
            Integer.valueOf(5), Integer.valueOf(3), "t24", Integer.valueOf(295), Integer.valueOf(35), Integer.valueOf(40), Integer.valueOf(5), Integer.valueOf(3), "t34", Integer.valueOf(295), 
            Integer.valueOf(25), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), "t27", Integer.valueOf(335), Integer.valueOf(0), Integer.valueOf(40), Integer.valueOf(5), Integer.valueOf(3), 
            "t24", Integer.valueOf(325), Integer.valueOf(35), Integer.valueOf(40), Integer.valueOf(5), Integer.valueOf(3), "t24", Integer.valueOf(365), Integer.valueOf(55), Integer.valueOf(40), 
            Integer.valueOf(5), Integer.valueOf(3), "t24", Integer.valueOf(385), Integer.valueOf(55), Integer.valueOf(40), Integer.valueOf(5), Integer.valueOf(3), "t3", Integer.valueOf(425), 
            Integer.valueOf(30), Integer.valueOf(5), Integer.valueOf(30), Integer.valueOf(3), "t27", Integer.valueOf(375), Integer.valueOf(0), Integer.valueOf(40), Integer.valueOf(5), Integer.valueOf(3), 
            "t3", Integer.valueOf(425), Integer.valueOf(0), Integer.valueOf(5), Integer.valueOf(30), Integer.valueOf(3), "t6", Integer.valueOf(365), Integer.valueOf(30), Integer.valueOf(5), 
            Integer.valueOf(5), Integer.valueOf(5), "t8", Integer.valueOf(220), Integer.valueOf(10), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(3), "t6", Integer.valueOf(220), 
            Integer.valueOf(20), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), "t6", Integer.valueOf(255), Integer.valueOf(10), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), 
            "t20", Integer.valueOf(215), Integer.valueOf(0), Integer.valueOf(40), Integer.valueOf(30), Integer.valueOf(4), "t20", Integer.valueOf(175), Integer.valueOf(0), Integer.valueOf(40), 
            Integer.valueOf(30), Integer.valueOf(4), "t20", Integer.valueOf(135), Integer.valueOf(0), Integer.valueOf(40), Integer.valueOf(30), Integer.valueOf(4), "t36", Integer.valueOf(345), 
            Integer.valueOf(50), Integer.valueOf(20), Integer.valueOf(5), Integer.valueOf(1), "t24", Integer.valueOf(325), Integer.valueOf(55), Integer.valueOf(40), Integer.valueOf(5), Integer.valueOf(3), 
            "t26", Integer.valueOf(345), Integer.valueOf(40), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(3), "t26", Integer.valueOf(325), Integer.valueOf(45), Integer.valueOf(20), 
            Integer.valueOf(10), Integer.valueOf(3)
        };
        return t;
    }

    public Object[] readEnemies()
    {
        Object e[] = {
            "enemy7", Integer.valueOf(229), Integer.valueOf(50), Integer.valueOf(8), Integer.valueOf(5), Integer.valueOf(36), "enemy3left", Integer.valueOf(195), Integer.valueOf(80), Integer.valueOf(5), 
            Integer.valueOf(5), Integer.valueOf(16), "enemy3left", Integer.valueOf(120), Integer.valueOf(70), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(16), "enemy3left", Integer.valueOf(135), 
            Integer.valueOf(70), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(16), "enemy3left", Integer.valueOf(150), Integer.valueOf(70), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(16), 
            "enemy1left", Integer.valueOf(270), Integer.valueOf(19), Integer.valueOf(15), Integer.valueOf(16), Integer.valueOf(1), "enemy19", Integer.valueOf(296), Integer.valueOf(5), Integer.valueOf(8), 
            Integer.valueOf(4), Integer.valueOf(86), "enemy8left", Integer.valueOf(315), Integer.valueOf(29), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(41), "enemy8left", Integer.valueOf(330), 
            Integer.valueOf(29), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(41), "enemy3left", Integer.valueOf(155), Integer.valueOf(20), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(16), 
            "enemy3left", Integer.valueOf(175), Integer.valueOf(20), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(16), "enemy3left", Integer.valueOf(195), Integer.valueOf(20), Integer.valueOf(5), 
            Integer.valueOf(5), Integer.valueOf(16), "enemy3left", Integer.valueOf(215), Integer.valueOf(20), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(16), "enemy3left", Integer.valueOf(235), 
            Integer.valueOf(20), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(16), "enemy3left", Integer.valueOf(250), Integer.valueOf(20), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(16), 
            "enemy2", Integer.valueOf(243), Integer.valueOf(60), Integer.valueOf(8), Integer.valueOf(5), Integer.valueOf(13), "enemy19", Integer.valueOf(261), Integer.valueOf(45), Integer.valueOf(8), 
            Integer.valueOf(4), Integer.valueOf(86), "enemy3left", Integer.valueOf(175), Integer.valueOf(80), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(16), "enemy13", Integer.valueOf(80), 
            Integer.valueOf(82), Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(6), "enemy13", Integer.valueOf(72), Integer.valueOf(82), Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(6), 
            "enemy13", Integer.valueOf(64), Integer.valueOf(82), Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(6)
        };
        return e;
    }

    public Object[] readItems()
    {
        Object i[] = {
            "item8", Integer.valueOf(30), Integer.valueOf(82), Integer.valueOf(10), Integer.valueOf(8), Integer.valueOf(9), "hero_right", Integer.valueOf(10), Integer.valueOf(85), Integer.valueOf(4), 
            Integer.valueOf(5), Integer.valueOf(1), "item4", Integer.valueOf(285), Integer.valueOf(61), Integer.valueOf(4), Integer.valueOf(4), Integer.valueOf(5), "item3", Integer.valueOf(410), 
            Integer.valueOf(45), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(4), "item5", Integer.valueOf(130), Integer.valueOf(10), Integer.valueOf(5), Integer.valueOf(10), Integer.valueOf(6), 
            "item2", Integer.valueOf(205), Integer.valueOf(81), Integer.valueOf(4), Integer.valueOf(4), Integer.valueOf(3), "item1", Integer.valueOf(420), Integer.valueOf(51), Integer.valueOf(4), 
            Integer.valueOf(4), Integer.valueOf(2), "item2", Integer.valueOf(346), Integer.valueOf(51), Integer.valueOf(4), Integer.valueOf(4), Integer.valueOf(3), "item1", Integer.valueOf(95), 
            Integer.valueOf(86), Integer.valueOf(4), Integer.valueOf(4), Integer.valueOf(2)
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
