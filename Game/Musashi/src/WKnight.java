// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enemy2.java


class WKnight extends Enemy2
{

    WKnight(int i)
    {
        name = "W\u30CA\u30A4\u30C8";
        lv = 1;
        maxHp = 64;
        hp = 64;
        ap = 32;
        dp = 6;
        ep = 34;
        x = i;
        y = 0;
        lineY = 336;
        motionWait = 6;
        moveType = 0;
        motionType = 0;
    }
}
