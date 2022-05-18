// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Level1.java

package levels;


// Referenced classes of package levels:
//            Level

public class Level1
    implements Level
{

    public Level1()
    {
        gameWidth = 1200;
        gameHeight = 90;
    }

    public Object[] readTiles()
    {
        Object t[] = {
            "t49", Integer.valueOf(0), Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t49", Integer.valueOf(60), Integer.valueOf(80), Integer.valueOf(60), 
            Integer.valueOf(10), Integer.valueOf(3), "t15", Integer.valueOf(0), Integer.valueOf(60), Integer.valueOf(60), Integer.valueOf(20), Integer.valueOf(4), "t12", Integer.valueOf(65), 
            Integer.valueOf(40), Integer.valueOf(10), Integer.valueOf(40), Integer.valueOf(4), "t31", Integer.valueOf(80), Integer.valueOf(72), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), 
            "t31", Integer.valueOf(100), Integer.valueOf(72), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), "t31", Integer.valueOf(120), Integer.valueOf(72), Integer.valueOf(15), 
            Integer.valueOf(8), Integer.valueOf(4), "t49", Integer.valueOf(120), Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t49", Integer.valueOf(180), 
            Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t49", Integer.valueOf(240), Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), 
            "t46", Integer.valueOf(175), Integer.valueOf(35), Integer.valueOf(70), Integer.valueOf(5), Integer.valueOf(3), "t5", Integer.valueOf(10), Integer.valueOf(23), Integer.valueOf(35), 
            Integer.valueOf(17), Integer.valueOf(4), "t5", Integer.valueOf(35), Integer.valueOf(13), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t5", Integer.valueOf(110), 
            Integer.valueOf(28), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t28", Integer.valueOf(255), Integer.valueOf(71), Integer.valueOf(7), Integer.valueOf(9), Integer.valueOf(4), 
            "t19", Integer.valueOf(265), Integer.valueOf(40), Integer.valueOf(60), Integer.valueOf(40), Integer.valueOf(4), "t46", Integer.valueOf(260), Integer.valueOf(35), Integer.valueOf(70), 
            Integer.valueOf(5), Integer.valueOf(3), "t20", Integer.valueOf(295), Integer.valueOf(57), Integer.valueOf(20), Integer.valueOf(23), Integer.valueOf(4), "t39", Integer.valueOf(275), 
            Integer.valueOf(56), Integer.valueOf(15), Integer.valueOf(14), Integer.valueOf(4), "t49", Integer.valueOf(300), Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), 
            "t49", Integer.valueOf(360), Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t31", Integer.valueOf(330), Integer.valueOf(72), Integer.valueOf(15), 
            Integer.valueOf(8), Integer.valueOf(4), "t31", Integer.valueOf(350), Integer.valueOf(72), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), "t31", Integer.valueOf(370), 
            Integer.valueOf(72), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), "t31", Integer.valueOf(390), Integer.valueOf(72), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), 
            "t12", Integer.valueOf(405), Integer.valueOf(40), Integer.valueOf(10), Integer.valueOf(40), Integer.valueOf(4), "t49", Integer.valueOf(420), Integer.valueOf(80), Integer.valueOf(60), 
            Integer.valueOf(10), Integer.valueOf(3), "t49", Integer.valueOf(480), Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t46", Integer.valueOf(475), 
            Integer.valueOf(35), Integer.valueOf(70), Integer.valueOf(5), Integer.valueOf(3), "t28", Integer.valueOf(540), Integer.valueOf(71), Integer.valueOf(7), Integer.valueOf(9), Integer.valueOf(4), 
            "t49", Integer.valueOf(540), Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(570), Integer.valueOf(70), Integer.valueOf(10), 
            Integer.valueOf(10), Integer.valueOf(3), "t49", Integer.valueOf(600), Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(660), 
            Integer.valueOf(70), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), "t19", Integer.valueOf(700), Integer.valueOf(40), Integer.valueOf(60), Integer.valueOf(40), Integer.valueOf(4), 
            "t46", Integer.valueOf(695), Integer.valueOf(35), Integer.valueOf(70), Integer.valueOf(5), Integer.valueOf(3), "t46", Integer.valueOf(760), Integer.valueOf(35), Integer.valueOf(70), 
            Integer.valueOf(5), Integer.valueOf(3), "t20", Integer.valueOf(715), Integer.valueOf(57), Integer.valueOf(20), Integer.valueOf(23), Integer.valueOf(4), "t16", Integer.valueOf(660), 
            Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t16", Integer.valueOf(720), Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), 
            "t16", Integer.valueOf(780), Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t16", Integer.valueOf(840), Integer.valueOf(80), Integer.valueOf(60), 
            Integer.valueOf(10), Integer.valueOf(3), "t35", Integer.valueOf(900), Integer.valueOf(70), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(930), 
            Integer.valueOf(70), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t7", Integer.valueOf(930), Integer.valueOf(58), Integer.valueOf(10), Integer.valueOf(12), Integer.valueOf(4), 
            "t7", Integer.valueOf(945), Integer.valueOf(58), Integer.valueOf(10), Integer.valueOf(12), Integer.valueOf(4), "t7", Integer.valueOf(960), Integer.valueOf(58), Integer.valueOf(10), 
            Integer.valueOf(12), Integer.valueOf(4), "t7", Integer.valueOf(975), Integer.valueOf(58), Integer.valueOf(10), Integer.valueOf(12), Integer.valueOf(4), "t35", Integer.valueOf(960), 
            Integer.valueOf(70), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(990), Integer.valueOf(70), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), 
            "t32", Integer.valueOf(990), Integer.valueOf(40), Integer.valueOf(50), Integer.valueOf(30), Integer.valueOf(4), "t35", Integer.valueOf(1020), Integer.valueOf(70), Integer.valueOf(30), 
            Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(1050), Integer.valueOf(70), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(1080), 
            Integer.valueOf(70), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(1110), Integer.valueOf(70), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), 
            "t35", Integer.valueOf(1140), Integer.valueOf(70), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(1170), Integer.valueOf(70), Integer.valueOf(30), 
            Integer.valueOf(20), Integer.valueOf(3), "t5", Integer.valueOf(1090), Integer.valueOf(8), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t5", Integer.valueOf(1135), 
            Integer.valueOf(18), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t5", Integer.valueOf(935), Integer.valueOf(3), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), 
            "t6", Integer.valueOf(385), Integer.valueOf(75), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), "t6", Integer.valueOf(320), Integer.valueOf(75), Integer.valueOf(5), 
            Integer.valueOf(5), Integer.valueOf(5), "t5", Integer.valueOf(425), Integer.valueOf(8), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t5", Integer.valueOf(560), 
            Integer.valueOf(23), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t6", Integer.valueOf(1050), Integer.valueOf(65), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), 
            "t6", Integer.valueOf(925), Integer.valueOf(65), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), "t19", Integer.valueOf(180), Integer.valueOf(40), Integer.valueOf(60), 
            Integer.valueOf(40), Integer.valueOf(4), "t20", Integer.valueOf(190), Integer.valueOf(57), Integer.valueOf(20), Integer.valueOf(23), Integer.valueOf(4), "t23", Integer.valueOf(215), 
            Integer.valueOf(56), Integer.valueOf(15), Integer.valueOf(14), Integer.valueOf(4), "t19", Integer.valueOf(760), Integer.valueOf(40), Integer.valueOf(60), Integer.valueOf(40), Integer.valueOf(4), 
            "t39", Integer.valueOf(770), Integer.valueOf(56), Integer.valueOf(15), Integer.valueOf(14), Integer.valueOf(4), "t23", Integer.valueOf(795), Integer.valueOf(56), Integer.valueOf(15), 
            Integer.valueOf(14), Integer.valueOf(4), "t5", Integer.valueOf(615), Integer.valueOf(13), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t5", Integer.valueOf(870), 
            Integer.valueOf(23), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t19", Integer.valueOf(480), Integer.valueOf(40), Integer.valueOf(60), Integer.valueOf(40), Integer.valueOf(4), 
            "t20", Integer.valueOf(490), Integer.valueOf(57), Integer.valueOf(20), Integer.valueOf(23), Integer.valueOf(4), "t21", Integer.valueOf(515), Integer.valueOf(56), Integer.valueOf(15), 
            Integer.valueOf(14), Integer.valueOf(4), "t28", Integer.valueOf(820), Integer.valueOf(71), Integer.valueOf(7), Integer.valueOf(9), Integer.valueOf(4), "t33", Integer.valueOf(650), 
            Integer.valueOf(70), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3)
        };
        return t;
    }

    public Object[] readEnemies()
    {
        Object e[] = {
            "enemy4", Integer.valueOf(185), Integer.valueOf(25), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(21), "enemy21", Integer.valueOf(160), Integer.valueOf(70), Integer.valueOf(10), 
            Integer.valueOf(10), Integer.valueOf(96), "enemy4", Integer.valueOf(270), Integer.valueOf(25), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(21), "enemy21", Integer.valueOf(440), 
            Integer.valueOf(70), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(96), "enemy4", Integer.valueOf(520), Integer.valueOf(25), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(21), 
            "enemy2", Integer.valueOf(600), Integer.valueOf(71), Integer.valueOf(14), Integer.valueOf(9), Integer.valueOf(11), "enemy12", Integer.valueOf(685), Integer.valueOf(70), Integer.valueOf(10), 
            Integer.valueOf(10), Integer.valueOf(61), "enemy4", Integer.valueOf(705), Integer.valueOf(25), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(21), "enemy4", Integer.valueOf(720), 
            Integer.valueOf(25), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(21), "enemy21", Integer.valueOf(910), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(96), 
            "enemy21", Integer.valueOf(245), Integer.valueOf(70), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(96), "enemy2", Integer.valueOf(350), Integer.valueOf(71), Integer.valueOf(14), 
            Integer.valueOf(9), Integer.valueOf(11), "enemy2", Integer.valueOf(755), Integer.valueOf(71), Integer.valueOf(14), Integer.valueOf(9), Integer.valueOf(11), "enemy2", Integer.valueOf(1010), 
            Integer.valueOf(61), Integer.valueOf(14), Integer.valueOf(9), Integer.valueOf(11), "enemy12", Integer.valueOf(470), Integer.valueOf(70), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(61), 
            "enemy2", Integer.valueOf(735), Integer.valueOf(71), Integer.valueOf(14), Integer.valueOf(9), Integer.valueOf(11), "enemy4", Integer.valueOf(795), Integer.valueOf(25), Integer.valueOf(10), 
            Integer.valueOf(10), Integer.valueOf(21), "enemy12", Integer.valueOf(870), Integer.valueOf(70), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(61), "enemy12", Integer.valueOf(560), 
            Integer.valueOf(70), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(61), "enemy17", Integer.valueOf(1070), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(76), 
            "enemy12", Integer.valueOf(1085), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(61), "enemy17", Integer.valueOf(660), Integer.valueOf(60), Integer.valueOf(10), 
            Integer.valueOf(10), Integer.valueOf(76)
        };
        return e;
    }

    public Object[] readItems()
    {
        Object i[] = {
            "item6", Integer.valueOf(115), Integer.valueOf(68), Integer.valueOf(10), Integer.valueOf(12), Integer.valueOf(7), "item13", Integer.valueOf(170), Integer.valueOf(10), Integer.valueOf(6), 
            Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(155), Integer.valueOf(15), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(105), 
            Integer.valueOf(15), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), "item12", Integer.valueOf(410), Integer.valueOf(70), Integer.valueOf(21), Integer.valueOf(10), Integer.valueOf(13), 
            "item3", Integer.valueOf(1100), Integer.valueOf(54), Integer.valueOf(20), Integer.valueOf(16), Integer.valueOf(4), "item13", Integer.valueOf(940), Integer.valueOf(20), Integer.valueOf(6), 
            Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(945), Integer.valueOf(35), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(865), 
            Integer.valueOf(35), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(840), Integer.valueOf(25), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), 
            "item13", Integer.valueOf(1190), Integer.valueOf(25), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(1185), Integer.valueOf(20), Integer.valueOf(6), 
            Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(1155), Integer.valueOf(25), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), "hero_right", Integer.valueOf(10), 
            Integer.valueOf(68), Integer.valueOf(9), Integer.valueOf(12), Integer.valueOf(1), "item4", Integer.valueOf(150), Integer.valueOf(69), Integer.valueOf(10), Integer.valueOf(11), Integer.valueOf(5)
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
