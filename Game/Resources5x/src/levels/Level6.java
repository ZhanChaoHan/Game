// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Level6.java

package levels;


// Referenced classes of package levels:
//            Level

public class Level6
    implements Level
{

    public Level6()
    {
        gameWidth = 1000;
        gameHeight = 110;
    }

    public Object[] readTiles()
    {
        Object t[] = {
            "t35", Integer.valueOf(0), Integer.valueOf(90), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(30), Integer.valueOf(90), Integer.valueOf(30), 
            Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(60), Integer.valueOf(90), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(90), 
            Integer.valueOf(90), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t31", Integer.valueOf(5), Integer.valueOf(82), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), 
            "t31", Integer.valueOf(25), Integer.valueOf(82), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), "t31", Integer.valueOf(45), Integer.valueOf(82), Integer.valueOf(15), 
            Integer.valueOf(8), Integer.valueOf(4), "t31", Integer.valueOf(65), Integer.valueOf(82), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), "t48", Integer.valueOf(120), 
            Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(80), Integer.valueOf(4), "t48", Integer.valueOf(135), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(80), Integer.valueOf(4), 
            "t2", Integer.valueOf(120), Integer.valueOf(100), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t2", Integer.valueOf(180), Integer.valueOf(100), Integer.valueOf(60), 
            Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(260), Integer.valueOf(100), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(290), 
            Integer.valueOf(100), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), "t35", Integer.valueOf(320), Integer.valueOf(90), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), 
            "t50", Integer.valueOf(320), Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t48", Integer.valueOf(325), Integer.valueOf(30), Integer.valueOf(10), 
            Integer.valueOf(80), Integer.valueOf(4), "t50", Integer.valueOf(380), Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t48", Integer.valueOf(425), 
            Integer.valueOf(30), Integer.valueOf(10), Integer.valueOf(80), Integer.valueOf(4), "t2", Integer.valueOf(350), Integer.valueOf(100), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), 
            "t35", Integer.valueOf(410), Integer.valueOf(90), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t6", Integer.valueOf(220), Integer.valueOf(95), Integer.valueOf(5), 
            Integer.valueOf(5), Integer.valueOf(5), "t2", Integer.valueOf(440), Integer.valueOf(100), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t11", Integer.valueOf(490), 
            Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(40), Integer.valueOf(6), "t50", Integer.valueOf(460), Integer.valueOf(50), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), 
            "t51", Integer.valueOf(500), Integer.valueOf(90), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t51", Integer.valueOf(530), Integer.valueOf(90), Integer.valueOf(30), 
            Integer.valueOf(20), Integer.valueOf(3), "t11", Integer.valueOf(520), Integer.valueOf(40), Integer.valueOf(10), Integer.valueOf(40), Integer.valueOf(6), "t11", Integer.valueOf(520), 
            Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(40), Integer.valueOf(6), "t5", Integer.valueOf(155), Integer.valueOf(43), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), 
            "t5", Integer.valueOf(175), Integer.valueOf(33), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t5", Integer.valueOf(270), Integer.valueOf(23), Integer.valueOf(35), 
            Integer.valueOf(17), Integer.valueOf(4), "t2", Integer.valueOf(560), Integer.valueOf(100), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t6", Integer.valueOf(525), 
            Integer.valueOf(45), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), "t50", Integer.valueOf(530), Integer.valueOf(50), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), 
            "t50", Integer.valueOf(590), Integer.valueOf(50), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(590), Integer.valueOf(40), Integer.valueOf(10), 
            Integer.valueOf(10), Integer.valueOf(3), "t6", Integer.valueOf(650), Integer.valueOf(45), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), "t35", Integer.valueOf(620), 
            Integer.valueOf(90), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t2", Integer.valueOf(650), Integer.valueOf(100), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), 
            "t35", Integer.valueOf(710), Integer.valueOf(90), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(760), Integer.valueOf(90), Integer.valueOf(30), 
            Integer.valueOf(20), Integer.valueOf(3), "t33", Integer.valueOf(520), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), "t10", Integer.valueOf(460), 
            Integer.valueOf(44), Integer.valueOf(60), Integer.valueOf(6), Integer.valueOf(1), "t10", Integer.valueOf(530), Integer.valueOf(44), Integer.valueOf(60), Integer.valueOf(6), Integer.valueOf(1), 
            "t10", Integer.valueOf(590), Integer.valueOf(44), Integer.valueOf(60), Integer.valueOf(6), Integer.valueOf(1), "t48", Integer.valueOf(355), Integer.valueOf(30), Integer.valueOf(10), 
            Integer.valueOf(80), Integer.valueOf(4), "t48", Integer.valueOf(395), Integer.valueOf(30), Integer.valueOf(10), Integer.valueOf(80), Integer.valueOf(4), "t2", Integer.valueOf(500), 
            Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t48", Integer.valueOf(465), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(80), Integer.valueOf(4), 
            "t48", Integer.valueOf(635), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(80), Integer.valueOf(4), "t5", Integer.valueOf(740), Integer.valueOf(28), Integer.valueOf(35), 
            Integer.valueOf(17), Integer.valueOf(4), "t27", Integer.valueOf(790), Integer.valueOf(65), Integer.valueOf(30), Integer.valueOf(35), Integer.valueOf(3), "t2", Integer.valueOf(790), 
            Integer.valueOf(100), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(780), Integer.valueOf(80), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), 
            "t33", Integer.valueOf(820), Integer.valueOf(90), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(820), Integer.valueOf(65), Integer.valueOf(10), 
            Integer.valueOf(10), Integer.valueOf(3), "t9", Integer.valueOf(820), Integer.valueOf(75), Integer.valueOf(10), Integer.valueOf(15), Integer.valueOf(1), "t43", Integer.valueOf(840), 
            Integer.valueOf(82), Integer.valueOf(25), Integer.valueOf(18), Integer.valueOf(4), "t2", Integer.valueOf(850), Integer.valueOf(100), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), 
            "t33", Integer.valueOf(900), Integer.valueOf(90), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), "t2", Integer.valueOf(910), Integer.valueOf(100), Integer.valueOf(60), 
            Integer.valueOf(10), Integer.valueOf(3), "t48", Integer.valueOf(920), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(80), Integer.valueOf(4), "t2", Integer.valueOf(940), 
            Integer.valueOf(100), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t5", Integer.valueOf(885), Integer.valueOf(23), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), 
            "t48", Integer.valueOf(940), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(80), Integer.valueOf(4), "t6", Integer.valueOf(315), Integer.valueOf(30), Integer.valueOf(5), 
            Integer.valueOf(5), Integer.valueOf(5), "t6", Integer.valueOf(440), Integer.valueOf(30), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), "t50", Integer.valueOf(790), 
            Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t50", Integer.valueOf(850), Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), 
            "t50", Integer.valueOf(910), Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t6", Integer.valueOf(995), Integer.valueOf(30), Integer.valueOf(5), 
            Integer.valueOf(5), Integer.valueOf(5), "t6", Integer.valueOf(785), Integer.valueOf(30), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), "t50", Integer.valueOf(940), 
            Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t5", Integer.valueOf(85), Integer.valueOf(28), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), 
            "t48", Integer.valueOf(105), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(80), Integer.valueOf(4), "t48", Integer.valueOf(90), Integer.valueOf(20), Integer.valueOf(10), 
            Integer.valueOf(80), Integer.valueOf(4), "t5", Integer.valueOf(960), Integer.valueOf(48), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t17", Integer.valueOf(145), 
            Integer.valueOf(92), Integer.valueOf(8), Integer.valueOf(8), Integer.valueOf(4), "t33", Integer.valueOf(260), Integer.valueOf(90), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3)
        };
        return t;
    }

    public Object[] readEnemies()
    {
        Object e[] = {
            "enemy9", Integer.valueOf(220), Integer.valueOf(94), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(46), "enemy11", Integer.valueOf(245), Integer.valueOf(110), Integer.valueOf(10), 
            Integer.valueOf(10), Integer.valueOf(56), "enemy11", Integer.valueOf(275), Integer.valueOf(110), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(56), "enemy11", Integer.valueOf(305), 
            Integer.valueOf(110), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(56), "enemy5", Integer.valueOf(335), Integer.valueOf(60), Integer.valueOf(14), Integer.valueOf(10), Integer.valueOf(26), 
            "enemy12", Integer.valueOf(225), Integer.valueOf(90), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(61), "enemy17", Integer.valueOf(415), Integer.valueOf(80), Integer.valueOf(10), 
            Integer.valueOf(10), Integer.valueOf(76), "enemy2", Integer.valueOf(385), Integer.valueOf(91), Integer.valueOf(14), Integer.valueOf(9), Integer.valueOf(11), "enemy9", Integer.valueOf(490), 
            Integer.valueOf(44), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(46), "enemy9", Integer.valueOf(460), Integer.valueOf(44), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(46), 
            "enemy2", Integer.valueOf(610), Integer.valueOf(41), Integer.valueOf(14), Integer.valueOf(9), Integer.valueOf(11), "enemy12", Integer.valueOf(590), Integer.valueOf(90), Integer.valueOf(10), 
            Integer.valueOf(10), Integer.valueOf(61), "enemy12", Integer.valueOf(605), Integer.valueOf(90), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(61), "enemy9", Integer.valueOf(655), 
            Integer.valueOf(94), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(46), "enemy9", Integer.valueOf(640), Integer.valueOf(44), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(46), 
            "enemy9", Integer.valueOf(690), Integer.valueOf(94), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(46), "enemy5", Integer.valueOf(620), Integer.valueOf(65), Integer.valueOf(14), 
            Integer.valueOf(10), Integer.valueOf(27), "enemy2", Integer.valueOf(570), Integer.valueOf(91), Integer.valueOf(14), Integer.valueOf(9), Integer.valueOf(11), "enemy17", Integer.valueOf(725), 
            Integer.valueOf(80), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(76), "enemy17", Integer.valueOf(815), Integer.valueOf(55), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(76), 
            "enemy21", Integer.valueOf(765), Integer.valueOf(80), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(96), "enemy2", Integer.valueOf(870), Integer.valueOf(91), Integer.valueOf(14), 
            Integer.valueOf(9), Integer.valueOf(11), "enemy7", Integer.valueOf(375), Integer.valueOf(30), Integer.valueOf(14), Integer.valueOf(10), Integer.valueOf(36), "enemy7", Integer.valueOf(825), 
            Integer.valueOf(30), Integer.valueOf(14), Integer.valueOf(10), Integer.valueOf(36), "enemy7", Integer.valueOf(955), Integer.valueOf(30), Integer.valueOf(14), Integer.valueOf(10), Integer.valueOf(36), 
            "enemy16", Integer.valueOf(190), Integer.valueOf(95), Integer.valueOf(10), Integer.valueOf(5), Integer.valueOf(111), "enemy16", Integer.valueOf(555), Integer.valueOf(45), Integer.valueOf(10), 
            Integer.valueOf(5), Integer.valueOf(111), "enemy12", Integer.valueOf(60), Integer.valueOf(80), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(61)
        };
        return e;
    }

    public Object[] readItems()
    {
        Object i[] = {
            "hero_right", Integer.valueOf(10), Integer.valueOf(78), Integer.valueOf(9), Integer.valueOf(12), Integer.valueOf(1), "item16", Integer.valueOf(490), Integer.valueOf(10), Integer.valueOf(10), 
            Integer.valueOf(10), Integer.valueOf(17), "item2", Integer.valueOf(440), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), "item16", Integer.valueOf(500), 
            Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(17), "item13", Integer.valueOf(780), Integer.valueOf(30), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), 
            "item13", Integer.valueOf(770), Integer.valueOf(35), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(700), Integer.valueOf(50), Integer.valueOf(6), 
            Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(710), Integer.valueOf(55), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(720), 
            Integer.valueOf(50), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(730), Integer.valueOf(55), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), 
            "item13", Integer.valueOf(665), Integer.valueOf(35), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(545), Integer.valueOf(65), Integer.valueOf(6), 
            Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(510), Integer.valueOf(30), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(445), 
            Integer.valueOf(45), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(435), Integer.valueOf(40), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), 
            "item16", Integer.valueOf(820), Integer.valueOf(80), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(17), "item3", Integer.valueOf(895), Integer.valueOf(74), Integer.valueOf(20), 
            Integer.valueOf(16), Integer.valueOf(4), "item16", Integer.valueOf(590), Integer.valueOf(30), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(17)
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
