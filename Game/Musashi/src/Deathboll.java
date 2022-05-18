// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enemy.java


class Deathboll extends Enemy
{

    Deathboll(int i)
    {
        name = "\u30C7\u30B9\u30DC\u30EB";
        lv = 1;
        maxHp = 56;
        hp = 56;
        ap = 28;
        dp = 2;
        ep = 45;
        x = i;
        y = 0;
        lineY = 624;
        motionWait = 10;
        moveType = 3;
        motionType = 1;
    }
}
