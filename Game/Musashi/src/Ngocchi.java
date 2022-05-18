// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enemy2.java


class Ngocchi extends Enemy2
{

    Ngocchi(int i)
    {
        name = "\u30C7\u30E9\u30B4\u30C3\u30C1";
        lv = 1;
        maxHp = 50;
        hp = 50;
        ap = 30;
        dp = 0;
        ep = 28;
        x = i;
        y = 0;
        lineY = 240;
        motionWait = 6;
        moveType = 1;
        motionType = 1;
    }
}
