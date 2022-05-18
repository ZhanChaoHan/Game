// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enemy.java


class Furaly extends Enemy
{

    Furaly(int i)
    {
        name = "\u30D5\u30E9\u30EA\u30FC";
        lv = 1;
        maxHp = 7;
        hp = 7;
        ap = 3;
        dp = 0;
        ep = 1;
        x = i;
        y = 0;
        lineY = 96;
        motionWait = 15;
        moveType = 1;
        motionType = 1;
    }
}
