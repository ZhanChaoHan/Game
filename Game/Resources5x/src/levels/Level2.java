// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Level2.java

package levels;


// Referenced classes of package levels:
//            Level

public class Level2
    implements Level
{

    public Level2()
    {
        gameWidth = 950;
        gameHeight = 90;
    }

    public Object[] readTiles()
    {
        Object t[] = {
            "t35", Integer.valueOf(0), Integer.valueOf(70), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t2", Integer.valueOf(30), Integer.valueOf(80), Integer.valueOf(60), 
            Integer.valueOf(10), Integer.valueOf(3), "t2", Integer.valueOf(90), Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t2", Integer.valueOf(150), 
            Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t38", Integer.valueOf(180), Integer.valueOf(0), Integer.valueOf(60), Integer.valueOf(80), Integer.valueOf(4), 
            "t12", Integer.valueOf(165), Integer.valueOf(40), Integer.valueOf(10), Integer.valueOf(40), Integer.valueOf(4), "t2", Integer.valueOf(210), Integer.valueOf(80), Integer.valueOf(60), 
            Integer.valueOf(10), Integer.valueOf(3), "t34", Integer.valueOf(200), Integer.valueOf(10), Integer.valueOf(20), Integer.valueOf(15), Integer.valueOf(4), "t2", Integer.valueOf(270), 
            Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t2", Integer.valueOf(305), Integer.valueOf(35), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), 
            "t2", Integer.valueOf(330), Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t2", Integer.valueOf(390), Integer.valueOf(80), Integer.valueOf(60), 
            Integer.valueOf(10), Integer.valueOf(3), "t2", Integer.valueOf(450), Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t2", Integer.valueOf(485), 
            Integer.valueOf(35), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t2", Integer.valueOf(510), Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), 
            "t11", Integer.valueOf(475), Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(40), Integer.valueOf(6), "t2", Integer.valueOf(570), Integer.valueOf(80), Integer.valueOf(60), 
            Integer.valueOf(10), Integer.valueOf(3), "t2", Integer.valueOf(650), Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), "t35", Integer.valueOf(710), 
            Integer.valueOf(70), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), "t35", Integer.valueOf(740), Integer.valueOf(70), Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(3), 
            "t6", Integer.valueOf(480), Integer.valueOf(30), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), "t6", Integer.valueOf(545), Integer.valueOf(30), Integer.valueOf(5), 
            Integer.valueOf(5), Integer.valueOf(5), "t31", Integer.valueOf(120), Integer.valueOf(72), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), "t31", Integer.valueOf(135), 
            Integer.valueOf(72), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), "t31", Integer.valueOf(150), Integer.valueOf(72), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), 
            "t5", Integer.valueOf(110), Integer.valueOf(23), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t5", Integer.valueOf(145), Integer.valueOf(3), Integer.valueOf(35), 
            Integer.valueOf(17), Integer.valueOf(4), "t38", Integer.valueOf(360), Integer.valueOf(0), Integer.valueOf(60), Integer.valueOf(80), Integer.valueOf(4), "t34", Integer.valueOf(380), 
            Integer.valueOf(10), Integer.valueOf(20), Integer.valueOf(15), Integer.valueOf(4), "t25", Integer.valueOf(380), Integer.valueOf(60), Integer.valueOf(15), Integer.valueOf(20), Integer.valueOf(4), 
            "t28", Integer.valueOf(400), Integer.valueOf(71), Integer.valueOf(7), Integer.valueOf(9), Integer.valueOf(4), "t31", Integer.valueOf(710), Integer.valueOf(62), Integer.valueOf(15), 
            Integer.valueOf(8), Integer.valueOf(4), "t31", Integer.valueOf(725), Integer.valueOf(62), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), "t37", Integer.valueOf(385), 
            Integer.valueOf(55), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(4), "t5", Integer.valueOf(685), Integer.valueOf(13), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), 
            "t27", Integer.valueOf(515), Integer.valueOf(45), Integer.valueOf(30), Integer.valueOf(35), Integer.valueOf(3), "t31", Integer.valueOf(35), Integer.valueOf(72), Integer.valueOf(15), 
            Integer.valueOf(8), Integer.valueOf(4), "t44", Integer.valueOf(55), Integer.valueOf(73), Integer.valueOf(16), Integer.valueOf(7), Integer.valueOf(4), "t47", Integer.valueOf(75), 
            Integer.valueOf(73), Integer.valueOf(5), Integer.valueOf(7), Integer.valueOf(4), "t12", Integer.valueOf(85), Integer.valueOf(40), Integer.valueOf(10), Integer.valueOf(40), Integer.valueOf(4), 
            "t33", Integer.valueOf(345), Integer.valueOf(70), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(355), Integer.valueOf(70), Integer.valueOf(10), 
            Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(345), Integer.valueOf(45), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(355), 
            Integer.valueOf(45), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), "t27", Integer.valueOf(505), Integer.valueOf(45), Integer.valueOf(30), Integer.valueOf(35), Integer.valueOf(3), 
            "t33", Integer.valueOf(485), Integer.valueOf(70), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(495), Integer.valueOf(70), Integer.valueOf(10), 
            Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(485), Integer.valueOf(45), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(495), 
            Integer.valueOf(45), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), "t43", Integer.valueOf(555), Integer.valueOf(62), Integer.valueOf(25), Integer.valueOf(18), Integer.valueOf(4), 
            "t6", Integer.valueOf(630), Integer.valueOf(75), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), "t27", Integer.valueOf(315), Integer.valueOf(45), Integer.valueOf(30), 
            Integer.valueOf(35), Integer.valueOf(3), "t9", Integer.valueOf(345), Integer.valueOf(55), Integer.valueOf(10), Integer.valueOf(15), Integer.valueOf(1), "t9", Integer.valueOf(355), 
            Integer.valueOf(55), Integer.valueOf(10), Integer.valueOf(15), Integer.valueOf(1), "t9", Integer.valueOf(495), Integer.valueOf(55), Integer.valueOf(10), Integer.valueOf(15), Integer.valueOf(1), 
            "t9", Integer.valueOf(485), Integer.valueOf(55), Integer.valueOf(10), Integer.valueOf(15), Integer.valueOf(1), "t5", Integer.valueOf(40), Integer.valueOf(8), Integer.valueOf(35), 
            Integer.valueOf(17), Integer.valueOf(4), "t5", Integer.valueOf(20), Integer.valueOf(13), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t5", Integer.valueOf(590), 
            Integer.valueOf(18), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t5", Integer.valueOf(605), Integer.valueOf(23), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), 
            "t5", Integer.valueOf(580), Integer.valueOf(23), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t2", Integer.valueOf(770), Integer.valueOf(80), Integer.valueOf(60), 
            Integer.valueOf(10), Integer.valueOf(3), "t27", Integer.valueOf(770), Integer.valueOf(45), Integer.valueOf(30), Integer.valueOf(35), Integer.valueOf(3), "t33", Integer.valueOf(800), 
            Integer.valueOf(45), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(800), Integer.valueOf(70), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), 
            "t9", Integer.valueOf(800), Integer.valueOf(55), Integer.valueOf(10), Integer.valueOf(15), Integer.valueOf(1), "t9", Integer.valueOf(810), Integer.valueOf(55), Integer.valueOf(10), 
            Integer.valueOf(15), Integer.valueOf(1), "t33", Integer.valueOf(810), Integer.valueOf(45), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), "t33", Integer.valueOf(810), 
            Integer.valueOf(70), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), "t2", Integer.valueOf(830), Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), 
            "t31", Integer.valueOf(825), Integer.valueOf(72), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), "t31", Integer.valueOf(845), Integer.valueOf(72), Integer.valueOf(15), 
            Integer.valueOf(8), Integer.valueOf(4), "t31", Integer.valueOf(865), Integer.valueOf(72), Integer.valueOf(15), Integer.valueOf(8), Integer.valueOf(4), "t6", Integer.valueOf(880), 
            Integer.valueOf(75), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), "t2", Integer.valueOf(890), Integer.valueOf(80), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(3), 
            "t5", Integer.valueOf(820), Integer.valueOf(3), Integer.valueOf(35), Integer.valueOf(17), Integer.valueOf(4), "t5", Integer.valueOf(865), Integer.valueOf(18), Integer.valueOf(35), 
            Integer.valueOf(17), Integer.valueOf(4), "t33", Integer.valueOf(760), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(3), "t27", Integer.valueOf(305), 
            Integer.valueOf(45), Integer.valueOf(30), Integer.valueOf(35), Integer.valueOf(3), "t38", Integer.valueOf(240), Integer.valueOf(0), Integer.valueOf(60), Integer.valueOf(80), Integer.valueOf(4), 
            "t38", Integer.valueOf(300), Integer.valueOf(0), Integer.valueOf(60), Integer.valueOf(80), Integer.valueOf(4), "t40", Integer.valueOf(265), Integer.valueOf(76), Integer.valueOf(8), 
            Integer.valueOf(4), Integer.valueOf(4), "t34", Integer.valueOf(260), Integer.valueOf(10), Integer.valueOf(20), Integer.valueOf(15), Integer.valueOf(4), "t11", Integer.valueOf(295), 
            Integer.valueOf(40), Integer.valueOf(10), Integer.valueOf(40), Integer.valueOf(6), "t11", Integer.valueOf(295), Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(40), Integer.valueOf(6), 
            "t38", Integer.valueOf(890), Integer.valueOf(0), Integer.valueOf(60), Integer.valueOf(80), Integer.valueOf(4), "t38", Integer.valueOf(420), Integer.valueOf(0), Integer.valueOf(60), 
            Integer.valueOf(80), Integer.valueOf(4), "t38", Integer.valueOf(480), Integer.valueOf(0), Integer.valueOf(60), Integer.valueOf(80), Integer.valueOf(4), "t11", Integer.valueOf(475), 
            Integer.valueOf(40), Integer.valueOf(10), Integer.valueOf(40), Integer.valueOf(6), "t40", Integer.valueOf(445), Integer.valueOf(76), Integer.valueOf(8), Integer.valueOf(4), Integer.valueOf(4)
        };
        return t;
    }

    public Object[] readEnemies()
    {
        Object e[] = {
            "enemy21", Integer.valueOf(125), Integer.valueOf(70), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(96), "enemy21", Integer.valueOf(140), Integer.valueOf(70), Integer.valueOf(10), 
            Integer.valueOf(10), Integer.valueOf(96), "enemy19", Integer.valueOf(200), Integer.valueOf(10), Integer.valueOf(20), Integer.valueOf(15), Integer.valueOf(86), "enemy2", Integer.valueOf(520), 
            Integer.valueOf(26), Integer.valueOf(14), Integer.valueOf(9), Integer.valueOf(11), "enemy21", Integer.valueOf(280), Integer.valueOf(70), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(96), 
            "enemy21", Integer.valueOf(345), Integer.valueOf(25), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(96), "enemy19", Integer.valueOf(380), Integer.valueOf(10), Integer.valueOf(20), 
            Integer.valueOf(15), Integer.valueOf(86), "enemy2", Integer.valueOf(370), Integer.valueOf(71), Integer.valueOf(14), Integer.valueOf(9), Integer.valueOf(11), "enemy21", Integer.valueOf(160), 
            Integer.valueOf(70), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(96), "enemy21", Integer.valueOf(465), Integer.valueOf(70), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(96), 
            "enemy2", Integer.valueOf(600), Integer.valueOf(71), Integer.valueOf(14), Integer.valueOf(9), Integer.valueOf(11), "enemy12", Integer.valueOf(695), Integer.valueOf(70), Integer.valueOf(10), 
            Integer.valueOf(10), Integer.valueOf(61), "enemy17", Integer.valueOf(800), Integer.valueOf(35), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(76), "enemy2", Integer.valueOf(835), 
            Integer.valueOf(71), Integer.valueOf(14), Integer.valueOf(9), Integer.valueOf(11), "enemy12", Integer.valueOf(745), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(61), 
            "enemy19", Integer.valueOf(260), Integer.valueOf(10), Integer.valueOf(20), Integer.valueOf(15), Integer.valueOf(86)
        };
        return e;
    }

    public Object[] readItems()
    {
        Object i[] = {
            "hero_right", Integer.valueOf(10), Integer.valueOf(58), Integer.valueOf(9), Integer.valueOf(12), Integer.valueOf(1), "item13", Integer.valueOf(185), Integer.valueOf(15), Integer.valueOf(6), 
            Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(205), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(110), 
            Integer.valueOf(10), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), "item13", Integer.valueOf(80), Integer.valueOf(20), Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(14), 
            "item14", Integer.valueOf(365), Integer.valueOf(68), Integer.valueOf(9), Integer.valueOf(12), Integer.valueOf(15), "item15", Integer.valueOf(190), Integer.valueOf(54), Integer.valueOf(15), 
            Integer.valueOf(11), Integer.valueOf(16), "item15", Integer.valueOf(205), Integer.valueOf(54), Integer.valueOf(15), Integer.valueOf(11), Integer.valueOf(16), "item3", Integer.valueOf(840), 
            Integer.valueOf(64), Integer.valueOf(20), Integer.valueOf(16), Integer.valueOf(4), "item16", Integer.valueOf(340), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(17), 
            "item16", Integer.valueOf(350), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(17), "item16", Integer.valueOf(500), Integer.valueOf(60), Integer.valueOf(10), 
            Integer.valueOf(10), Integer.valueOf(17), "item16", Integer.valueOf(800), Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(17), "item16", Integer.valueOf(810), 
            Integer.valueOf(60), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(17)
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
