// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enemy2.java


class Hellboll extends Enemy2
{

    Hellboll(int i)
    {
        name = "\u30D8\u30EB\u30DC\u30EB";
        lv = 1;
        maxHp = 56;
        hp = 56;
        ap = 38;
        dp = 4;
        ep = 66;
        x = i;
        y = 0;
        lineY = 624;
        motionWait = 10;
        moveType = 3;
        motionType = 1;
    }
}
