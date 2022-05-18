// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enemy2.java


class DragonKing extends Enemy2
{

    DragonKing(int i)
    {
        name = "\u9ED2\u9F8D";
        lv = 1;
        maxHp = 150;
        hp = 150;
        ap = 70;
        dp = 10;
        ep = 200;
        x = i;
        y = 0;
        lineY = 432;
        motionWait = 20;
        moveType = 1;
        motionType = 1;
        motion[0][3] = 48;
        motion[1][3] = 192;
        motion[2][3] = 336;
        motion[3][3] = 480;
    }
}
