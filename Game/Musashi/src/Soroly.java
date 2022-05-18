// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enemy2.java


class Soroly extends Enemy2
{

    Soroly(int i)
    {
        name = "\u30BD\u30ED\u30EA\u30FC";
        lv = 1;
        maxHp = 21;
        hp = 21;
        ap = 9;
        dp = 0;
        ep = 3;
        x = i;
        y = 0;
        lineY = 96;
        motionWait = 15;
        moveType = 1;
        motionType = 1;
    }
}
