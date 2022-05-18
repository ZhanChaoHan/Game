// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enemy.java


class Dragon extends Enemy
{

    Dragon(int i)
    {
        name = "\u30C9\u30E9\u30B4\u30F3";
        lv = 1;
        maxHp = 120;
        hp = 120;
        ap = 50;
        dp = 10;
        ep = 120;
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
