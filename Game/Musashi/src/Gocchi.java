// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enemy.java


class Gocchi extends Enemy
{

    Gocchi(int i)
    {
        name = "\u30B4\u30C3\u30C1";
        lv = 1;
        maxHp = 25;
        hp = 25;
        ap = 7;
        dp = 0;
        ep = 4;
        x = i;
        y = 0;
        lineY = 240;
        motionWait = 6;
        moveType = 1;
        motionType = 1;
    }
}
