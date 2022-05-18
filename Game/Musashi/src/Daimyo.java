// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enemy2.java


class Daimyo extends Enemy2
{

    Daimyo(int i)
    {
        name = "\u5927\u5C06\u8ECD";
        lv = 1;
        maxHp = 80;
        hp = 80;
        ap = 34;
        dp = 2;
        ep = 80;
        x = i;
        y = 0;
        lineY = 576;
        motionWait = 10;
    }
}
