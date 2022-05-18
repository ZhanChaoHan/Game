// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Level5.java

package levels;


// Referenced classes of package levels:
//            Level

public class Level5
    implements Level
{

    public Level5()
    {
        gameWidth = 1400;
        gameHeight = 100;
    }

    public Object[] readTiles()
    {
        Object t[] = {
            "t2", Integer.valueOf(0), Integer.valueOf(90), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t2", Integer.valueOf(60), Integer.valueOf(90), Integer.valueOf(60), 
            Integer.valueOf(10), Integer.valueOf(3), "t2", Integer.valueOf(120), Integer.valueOf(90), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t2", Integer.valueOf(180), 
            Integer.valueOf(90), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t50", Integer.valueOf(240), Integer.valueOf(10), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), 
            "t50", Integer.valueOf(300), Integer.valueOf(10), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t35", Integer.valueOf(240), Integer.valueOf(80), Integer.valueOf(30), 
            Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(270), Integer.valueOf(80), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(300), 
            Integer.valueOf(80), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(330), Integer.valueOf(80), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), 
            "t35", Integer.valueOf(360), Integer.valueOf(80), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(390), Integer.valueOf(80), Integer.valueOf(30), 
            Integer.valueOf(20), Integer.valueOf(3), "t50", Integer.valueOf(360), Integer.valueOf(10), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t48", Integer.valueOf(345), 
            Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(80), Integer.valueOf(4), "t5", Integer.valueOf(50), Integer.valueOf(18), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), 
            "t2", Integer.valueOf(420), Integer.valueOf(90), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t2", Integer.valueOf(480), Integer.valueOf(90), Integer.valueOf(60), 
            Integer.valueOf(10), Integer.valueOf(3), "t35", Integer.valueOf(540), Integer.valueOf(80), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(570), 
            Integer.valueOf(80), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(600), Integer.valueOf(80), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), 
            "t35", Integer.valueOf(630), Integer.valueOf(80), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(660), Integer.valueOf(80), Integer.valueOf(30), 
            Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(690), Integer.valueOf(80), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t50", Integer.valueOf(540), 
            Integer.valueOf(10), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t50", Integer.valueOf(600), Integer.valueOf(10), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), 
            "t50", Integer.valueOf(660), Integer.valueOf(10), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t48", Integer.valueOf(545), Integer.valueOf(20), Integer.valueOf(10), 
            Integer.valueOf(80), Integer.valueOf(4), "t48", Integer.valueOf(705), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(80), Integer.valueOf(4), "t48", Integer.valueOf(645), 
            Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(80), Integer.valueOf(4), "t48", Integer.valueOf(305), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(80), Integer.valueOf(4), 
            "t2", Integer.valueOf(720), Integer.valueOf(90), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t2", Integer.valueOf(780), Integer.valueOf(90), Integer.valueOf(60), 
            Integer.valueOf(10), Integer.valueOf(3), "t32", Integer.valueOf(750), Integer.valueOf(60), Integer.valueOf(50), Integer.valueOf(30), Integer.valueOf(4), "t31", Integer.valueOf(800), 
            Integer.valueOf(82), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), "t31", Integer.valueOf(735), Integer.valueOf(82), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), 
            "t31", Integer.valueOf(815), Integer.valueOf(82), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), "t35", Integer.valueOf(840), Integer.valueOf(80), Integer.valueOf(30), 
            Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(890), Integer.valueOf(80), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(940), 
            Integer.valueOf(80), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(990), Integer.valueOf(80), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), 
            "t35", Integer.valueOf(1040), Integer.valueOf(80), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t5", Integer.valueOf(375), Integer.valueOf(28), Integer.valueOf(35), 
            Integer.valueOf(17), Integer.valueOf(4), "t48", Integer.valueOf(405), Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(80), Integer.valueOf(4), "t5", Integer.valueOf(450), 
            Integer.valueOf(18), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t5", Integer.valueOf(500), Integer.valueOf(33), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), 
            "t5", Integer.valueOf(600), Integer.valueOf(33), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t48", Integer.valueOf(605), Integer.valueOf(20), Integer.valueOf(10), 
            Integer.valueOf(80), Integer.valueOf(4), "t5", Integer.valueOf(735), Integer.valueOf(33), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t5", Integer.valueOf(900), 
            Integer.valueOf(18), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t5", Integer.valueOf(940), Integer.valueOf(38), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), 
            "t5", Integer.valueOf(985), Integer.valueOf(18), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t6", Integer.valueOf(720), Integer.valueOf(75), Integer.valueOf(5), 
            Integer.valueOf(5), Integer.valueOf(5), "t6", Integer.valueOf(535), Integer.valueOf(75), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), "t2", Integer.valueOf(1100), 
            Integer.valueOf(90), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t2", Integer.valueOf(1160), Integer.valueOf(90), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), 
            "t11", Integer.valueOf(1125), Integer.valueOf(50), Integer.valueOf(10), Integer.valueOf(40), Integer.valueOf(6), "t27", Integer.valueOf(1175), Integer.valueOf(55), Integer.valueOf(30), 
            Integer.valueOf(35), Integer.valueOf(3), "t50", Integer.valueOf(1135), Integer.valueOf(45), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t50", Integer.valueOf(1195), 
            Integer.valueOf(45), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t2", Integer.valueOf(1220), Integer.valueOf(90), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), 
            "t48", Integer.valueOf(1165), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(80), Integer.valueOf(4), "t48", Integer.valueOf(1205), Integer.valueOf(10), Integer.valueOf(10), 
            Integer.valueOf(80), Integer.valueOf(4), "t48", Integer.valueOf(1240), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(80), Integer.valueOf(4), "t33", Integer.valueOf(1125), 
            Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(1185), Integer.valueOf(35), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), 
            "t11", Integer.valueOf(1125), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(40), Integer.valueOf(6), "t6", Integer.valueOf(1130), Integer.valueOf(40), Integer.valueOf(5), 
            Integer.valueOf(5), Integer.valueOf(5), "t2", Integer.valueOf(1280), Integer.valueOf(90), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t2", Integer.valueOf(1340), 
            Integer.valueOf(90), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t12", Integer.valueOf(1375), Integer.valueOf(50), Integer.valueOf(10), Integer.valueOf(40), Integer.valueOf(4), 
            "t5", Integer.valueOf(1300), Integer.valueOf(18), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t5", Integer.valueOf(1355), Integer.valueOf(28), Integer.valueOf(35), 
            Integer.valueOf(17), Integer.valueOf(4), "t5", Integer.valueOf(1120), Integer.valueOf(18), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t48", Integer.valueOf(1140), 
            Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(80), Integer.valueOf(4), "t6", Integer.valueOf(600), Integer.valueOf(20), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), 
            "t6", Integer.valueOf(655), Integer.valueOf(20), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), "t48", Integer.valueOf(245), Integer.valueOf(20), Integer.valueOf(10), 
            Integer.valueOf(80), Integer.valueOf(4), "t26", Integer.valueOf(0), Integer.valueOf(50), Integer.valueOf(56), Integer.valueOf(40), Integer.valueOf(4), "t26", Integer.valueOf(60), 
            Integer.valueOf(60), Integer.valueOf(56), Integer.valueOf(40), Integer.valueOf(4), "t43", Integer.valueOf(195), Integer.valueOf(72), Integer.valueOf(25), Integer.valueOf(18), Integer.valueOf(4), 
            "t26", Integer.valueOf(120), Integer.valueOf(50), Integer.valueOf(56), Integer.valueOf(40), Integer.valueOf(4), "t5", Integer.valueOf(105), Integer.valueOf(38), Integer.valueOf(35), 
            Integer.valueOf(17), Integer.valueOf(4), "t43", Integer.valueOf(160), Integer.valueOf(72), Integer.valueOf(25), Integer.valueOf(18), Integer.valueOf(4), "t26", Integer.valueOf(1315), 
            Integer.valueOf(50), Integer.valueOf(56), Integer.valueOf(40), Integer.valueOf(4), "t5", Integer.valueOf(200), Integer.valueOf(23), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), 
            "t5", Integer.valueOf(790), Integer.valueOf(23), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4)
        };
        return t;
    }

    public Object[] readEnemies()
    {
        Object e[] = {
            "enemy21", Integer.valueOf(70), Integer.valueOf(80), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(96), "enemy21", Integer.valueOf(90), Integer.valueOf(80), Integer.valueOf(10), 
            Integer.valueOf(10), Integer.valueOf(96), "enemy21", Integer.valueOf(110), Integer.valueOf(80), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(96), "enemy2", Integer.valueOf(490), 
            Integer.valueOf(81), Integer.valueOf(14), Integer.valueOf(9), Integer.valueOf(11), "enemy2", Integer.valueOf(450), Integer.valueOf(81), Integer.valueOf(14), Integer.valueOf(9), Integer.valueOf(11), 
            "enemy12", Integer.valueOf(325), Integer.valueOf(70), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(61), "enemy12", Integer.valueOf(625), Integer.valueOf(70), Integer.valueOf(10), 
            Integer.valueOf(10), Integer.valueOf(61), "enemy11", Integer.valueOf(875), Integer.valueOf(100), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(56), "enemy11", Integer.valueOf(925), 
            Integer.valueOf(100), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(56), "enemy11", Integer.valueOf(975), Integer.valueOf(100), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(56), 
            "enemy11", Integer.valueOf(1025), Integer.valueOf(100), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(56), "enemy2", Integer.valueOf(775), Integer.valueOf(81), Integer.valueOf(14), 
            Integer.valueOf(9), Integer.valueOf(11), "enemy2", Integer.valueOf(700), Integer.valueOf(71), Integer.valueOf(14), Integer.valueOf(9), Integer.valueOf(11), "enemy5", Integer.valueOf(1145), 
            Integer.valueOf(80), Integer.valueOf(14), Integer.valueOf(10), Integer.valueOf(26), "enemy5", Integer.valueOf(1225), Integer.valueOf(65), Integer.valueOf(14), Integer.valueOf(10), Integer.valueOf(26), 
            "enemy2", Integer.valueOf(1150), Integer.valueOf(36), Integer.valueOf(14), Integer.valueOf(9), Integer.valueOf(11), "enemy17", Integer.valueOf(1235), Integer.valueOf(35), Integer.valueOf(10), 
            Integer.valueOf(10), Integer.valueOf(76), "enemy9", Integer.valueOf(1255), Integer.valueOf(84), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(46), "enemy12", Integer.valueOf(1050), 
            Integer.valueOf(70), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(61), "enemy7", Integer.valueOf(610), Integer.valueOf(20), Integer.valueOf(14), Integer.valueOf(10), Integer.valueOf(36), 
            "enemy5", Integer.valueOf(255), Integer.valueOf(20), Integer.valueOf(14), Integer.valueOf(10), Integer.valueOf(26), "enemy5", Integer.valueOf(390), Integer.valueOf(20), Integer.valueOf(14), 
            Integer.valueOf(10), Integer.valueOf(26), "enemy21", Integer.valueOf(855), Integer.valueOf(70), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(96), "enemy5", Integer.valueOf(290), 
            Integer.valueOf(20), Integer.valueOf(14), Integer.valueOf(10), Integer.valueOf(28), "enemy5", Integer.valueOf(355), Integer.valueOf(20), Integer.valueOf(14), Integer.valueOf(10), Integer.valueOf(28), 
            "enemy5", Integer.valueOf(565), Integer.valueOf(20), Integer.valueOf(14), Integer.valueOf(10), Integer.valueOf(28), "enemy5", Integer.valueOf(680), Integer.valueOf(70), Integer.valueOf(14), 
            Integer.valueOf(10), Integer.valueOf(28), "enemy12", Integer.valueOf(900), Integer.valueOf(70), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(61), "enemy12", Integer.valueOf(1165), 
            Integer.valueOf(80), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(61)
        };
        return e;
    }

    public Object[] readItems()
    {
        Object i[] = {
            "item13", Integer.valueOf(990), Integer.valueOf(40), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(940), Integer.valueOf(25), Integer.valueOf(6), 
            Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(950), Integer.valueOf(30), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(960), 
            Integer.valueOf(35), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(805), Integer.valueOf(45), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), 
            "item13", Integer.valueOf(830), Integer.valueOf(35), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(725), Integer.valueOf(30), Integer.valueOf(6), 
            Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(680), Integer.valueOf(40), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(1090), 
            Integer.valueOf(30), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), "item2", Integer.valueOf(1205), Integer.valueOf(80), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), 
            "item3", Integer.valueOf(1300), Integer.valueOf(74), Integer.valueOf(20), Integer.valueOf(16), Integer.valueOf(4), "item16", Integer.valueOf(1165), Integer.valueOf(0), Integer.valueOf(10), 
            Integer.valueOf(10), Integer.valueOf(17), "item16", Integer.valueOf(1215), Integer.valueOf(80), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(17), "hero_right", Integer.valueOf(10), 
            Integer.valueOf(78), Integer.valueOf(9), Integer.valueOf(12), Integer.valueOf(1)
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
