// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Level3.java

package levels;


// Referenced classes of package levels:
//            Level

public class Level3
    implements Level
{

    public Level3()
    {
        gameWidth = 1230;
        gameHeight = 100;
    }

    public Object[] readTiles()
    {
        Object t[] = {
            "t49", Integer.valueOf(0), Integer.valueOf(90), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t49", Integer.valueOf(60), Integer.valueOf(90), Integer.valueOf(60), 
            Integer.valueOf(10), Integer.valueOf(3), "t49", Integer.valueOf(120), Integer.valueOf(90), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t49", Integer.valueOf(180), 
            Integer.valueOf(90), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t38", Integer.valueOf(180), Integer.valueOf(10), Integer.valueOf(60), Integer.valueOf(80), Integer.valueOf(4), 
            "t38", Integer.valueOf(240), Integer.valueOf(10), Integer.valueOf(60), Integer.valueOf(80), Integer.valueOf(4), "t24", Integer.valueOf(200), Integer.valueOf(20), Integer.valueOf(20), 
            Integer.valueOf(15), Integer.valueOf(4), "t24", Integer.valueOf(260), Integer.valueOf(20), Integer.valueOf(20), Integer.valueOf(15), Integer.valueOf(4), "t49", Integer.valueOf(240), 
            Integer.valueOf(90), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t49", Integer.valueOf(300), Integer.valueOf(90), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), 
            "t12", Integer.valueOf(305), Integer.valueOf(50), Integer.valueOf(10), Integer.valueOf(40), Integer.valueOf(4), "t6", Integer.valueOf(320), Integer.valueOf(85), Integer.valueOf(5), 
            Integer.valueOf(5), Integer.valueOf(5), "t35", Integer.valueOf(360), Integer.valueOf(80), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(420), 
            Integer.valueOf(60), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(390), Integer.valueOf(70), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), 
            "t51", Integer.valueOf(390), Integer.valueOf(80), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t51", Integer.valueOf(420), Integer.valueOf(80), Integer.valueOf(30), 
            Integer.valueOf(20), Integer.valueOf(3), "t51", Integer.valueOf(450), Integer.valueOf(80), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t51", Integer.valueOf(480), 
            Integer.valueOf(80), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t51", Integer.valueOf(450), Integer.valueOf(60), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), 
            "t35", Integer.valueOf(450), Integer.valueOf(50), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(480), Integer.valueOf(50), Integer.valueOf(30), 
            Integer.valueOf(20), Integer.valueOf(3), "t51", Integer.valueOf(480), Integer.valueOf(70), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t6", Integer.valueOf(445), 
            Integer.valueOf(45), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), "t6", Integer.valueOf(510), Integer.valueOf(45), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), 
            "t35", Integer.valueOf(600), Integer.valueOf(50), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t3", Integer.valueOf(600), Integer.valueOf(70), Integer.valueOf(60), 
            Integer.valueOf(10), Integer.valueOf(3), "t3", Integer.valueOf(600), Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t3", Integer.valueOf(600), 
            Integer.valueOf(90), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t35", Integer.valueOf(630), Integer.valueOf(50), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), 
            "t35", Integer.valueOf(660), Integer.valueOf(50), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(690), Integer.valueOf(50), Integer.valueOf(30), 
            Integer.valueOf(20), Integer.valueOf(3), "t3", Integer.valueOf(660), Integer.valueOf(70), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t3", Integer.valueOf(660), 
            Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t3", Integer.valueOf(660), Integer.valueOf(90), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), 
            "t35", Integer.valueOf(720), Integer.valueOf(80), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(750), Integer.valueOf(80), Integer.valueOf(30), 
            Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(780), Integer.valueOf(80), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(810), 
            Integer.valueOf(80), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t29", Integer.valueOf(740), Integer.valueOf(41), Integer.valueOf(74), Integer.valueOf(39), Integer.valueOf(4), 
            "t28", Integer.valueOf(735), Integer.valueOf(71), Integer.valueOf(7), Integer.valueOf(9), Integer.valueOf(4), "t35", Integer.valueOf(840), Integer.valueOf(80), Integer.valueOf(30), 
            Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(870), Integer.valueOf(80), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(900), 
            Integer.valueOf(80), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t5", Integer.valueOf(565), Integer.valueOf(18), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), 
            "t5", Integer.valueOf(695), Integer.valueOf(13), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t5", Integer.valueOf(830), Integer.valueOf(18), Integer.valueOf(35), 
            Integer.valueOf(17), Integer.valueOf(4), "t31", Integer.valueOf(815), Integer.valueOf(72), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), "t31", Integer.valueOf(835), 
            Integer.valueOf(72), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), "t31", Integer.valueOf(855), Integer.valueOf(72), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), 
            "t31", Integer.valueOf(875), Integer.valueOf(72), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), "t22", Integer.valueOf(60), Integer.valueOf(60), Integer.valueOf(60), 
            Integer.valueOf(30), Integer.valueOf(4), "t22", Integer.valueOf(120), Integer.valueOf(60), Integer.valueOf(60), Integer.valueOf(30), Integer.valueOf(4), "t16", Integer.valueOf(930), 
            Integer.valueOf(90), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t16", Integer.valueOf(990), Integer.valueOf(90), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), 
            "t31", Integer.valueOf(965), Integer.valueOf(82), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), "t12", Integer.valueOf(985), Integer.valueOf(50), Integer.valueOf(10), 
            Integer.valueOf(40), Integer.valueOf(4), "t44", Integer.valueOf(1000), Integer.valueOf(83), Integer.valueOf(16), Integer.valueOf(7), Integer.valueOf(4), "t44", Integer.valueOf(1020), 
            Integer.valueOf(83), Integer.valueOf(16), Integer.valueOf(7), Integer.valueOf(4), "t47", Integer.valueOf(1040), Integer.valueOf(83), Integer.valueOf(5), Integer.valueOf(7), Integer.valueOf(4), 
            "t16", Integer.valueOf(1050), Integer.valueOf(90), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t31", Integer.valueOf(1060), Integer.valueOf(82), Integer.valueOf(15), 
            Integer.valueOf(8), Integer.valueOf(4), "t31", Integer.valueOf(1080), Integer.valueOf(82), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), "t31", Integer.valueOf(1100), 
            Integer.valueOf(82), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), "t31", Integer.valueOf(1120), Integer.valueOf(82), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), 
            "t16", Integer.valueOf(1110), Integer.valueOf(90), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t16", Integer.valueOf(1170), Integer.valueOf(90), Integer.valueOf(60), 
            Integer.valueOf(10), Integer.valueOf(3), "t31", Integer.valueOf(1140), Integer.valueOf(82), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), "t33", Integer.valueOf(920), 
            Integer.valueOf(70), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(620), Integer.valueOf(40), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), 
            "t5", Integer.valueOf(815), Integer.valueOf(23), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t5", Integer.valueOf(840), Integer.valueOf(28), Integer.valueOf(35), 
            Integer.valueOf(17), Integer.valueOf(4), "t5", Integer.valueOf(890), Integer.valueOf(23), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t6", Integer.valueOf(1075), 
            Integer.valueOf(85), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), "t10", Integer.valueOf(510), Integer.valueOf(54), Integer.valueOf(60), Integer.valueOf(6), Integer.valueOf(1), 
            "t10", Integer.valueOf(570), Integer.valueOf(54), Integer.valueOf(60), Integer.valueOf(6), Integer.valueOf(1), "t45", Integer.valueOf(510), Integer.valueOf(60), Integer.valueOf(90), 
            Integer.valueOf(15), Integer.valueOf(3), "t11", Integer.valueOf(235), Integer.valueOf(50), Integer.valueOf(10), Integer.valueOf(40), Integer.valueOf(6), "t11", Integer.valueOf(235), 
            Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(40), Integer.valueOf(6), "t2", Integer.valueOf(245), Integer.valueOf(40), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), 
            "t14", Integer.valueOf(245), Integer.valueOf(50), Integer.valueOf(30), Integer.valueOf(5), Integer.valueOf(4), "t14", Integer.valueOf(270), Integer.valueOf(50), Integer.valueOf(30), 
            Integer.valueOf(5), Integer.valueOf(4), "t33", Integer.valueOf(235), Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), "t6", Integer.valueOf(20), 
            Integer.valueOf(85), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), "t6", Integer.valueOf(255), Integer.valueOf(85), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), 
            "t6", Integer.valueOf(150), Integer.valueOf(85), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), "t38", Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(60), 
            Integer.valueOf(80), Integer.valueOf(4)
        };
        return t;
    }

    public Object[] readEnemies()
    {
        Object e[] = {
            "enemy2", Integer.valueOf(80), Integer.valueOf(81), Integer.valueOf(14), Integer.valueOf(9), Integer.valueOf(11), "enemy2", Integer.valueOf(270), Integer.valueOf(81), Integer.valueOf(14), 
            Integer.valueOf(9), Integer.valueOf(11), "enemy17", Integer.valueOf(340), Integer.valueOf(80), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(76), "enemy17", Integer.valueOf(395), 
            Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(76), "enemy12", Integer.valueOf(430), Integer.valueOf(50), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(61), 
            "enemy2", Integer.valueOf(560), Integer.valueOf(51), Integer.valueOf(14), Integer.valueOf(9), Integer.valueOf(11), "enemy2", Integer.valueOf(875), Integer.valueOf(71), Integer.valueOf(14), 
            Integer.valueOf(9), Integer.valueOf(11), "enemy2", Integer.valueOf(485), Integer.valueOf(41), Integer.valueOf(14), Integer.valueOf(9), Integer.valueOf(11), "enemy17", Integer.valueOf(670), 
            Integer.valueOf(40), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(76), "enemy1left", Integer.valueOf(945), Integer.valueOf(65), Integer.valueOf(20), Integer.valueOf(25), Integer.valueOf(1), 
            "enemy2", Integer.valueOf(825), Integer.valueOf(71), Integer.valueOf(14), Integer.valueOf(9), Integer.valueOf(11), "enemy12", Integer.valueOf(900), Integer.valueOf(70), Integer.valueOf(10), 
            Integer.valueOf(10), Integer.valueOf(61), "enemy12", Integer.valueOf(605), Integer.valueOf(40), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(61), "enemy17", Integer.valueOf(265), 
            Integer.valueOf(30), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(76), "enemy12", Integer.valueOf(280), Integer.valueOf(30), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(61), 
            "enemy12", Integer.valueOf(295), Integer.valueOf(30), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(61), "enemy12", Integer.valueOf(40), Integer.valueOf(80), Integer.valueOf(10), 
            Integer.valueOf(10), Integer.valueOf(61), "enemy17", Integer.valueOf(160), Integer.valueOf(80), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(76)
        };
        return e;
    }

    public Object[] readItems()
    {
        Object i[] = {
            "item8", Integer.valueOf(180), Integer.valueOf(58), Integer.valueOf(60), Integer.valueOf(32), Integer.valueOf(9), "hero_right", Integer.valueOf(10), Integer.valueOf(78), Integer.valueOf(9), 
            Integer.valueOf(12), Integer.valueOf(1), "item14", Integer.valueOf(245), Integer.valueOf(78), Integer.valueOf(9), Integer.valueOf(12), Integer.valueOf(15), "item9", Integer.valueOf(365), 
            Integer.valueOf(75), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(10), "item9", Integer.valueOf(380), Integer.valueOf(75), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(10), 
            "item9", Integer.valueOf(455), Integer.valueOf(45), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(10), "item9", Integer.valueOf(470), Integer.valueOf(45), Integer.valueOf(5), 
            Integer.valueOf(5), Integer.valueOf(10), "item9", Integer.valueOf(695), Integer.valueOf(45), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(10), "item9", Integer.valueOf(710), 
            Integer.valueOf(45), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(10), "item9", Integer.valueOf(665), Integer.valueOf(45), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(10), 
            "item9", Integer.valueOf(680), Integer.valueOf(45), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(10), "item3", Integer.valueOf(1120), Integer.valueOf(74), Integer.valueOf(20), 
            Integer.valueOf(16), Integer.valueOf(4), "item17", Integer.valueOf(510), Integer.valueOf(90), Integer.valueOf(90), Integer.valueOf(10), Integer.valueOf(18), "item15", Integer.valueOf(15), 
            Integer.valueOf(19), Integer.valueOf(15), Integer.valueOf(11), Integer.valueOf(16), "item15", Integer.valueOf(30), Integer.valueOf(19), Integer.valueOf(15), Integer.valueOf(11), Integer.valueOf(16)
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
