// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enemy.java


class Shogun extends Enemy
{

    Shogun(int i)
    {
        name = "\u5C06\u8ECD";
        lv = 1;
        maxHp = 60;
        hp = 60;
        ap = 20;
        dp = 6;
        ep = 42;
        x = i;
        y = 0;
        lineY = 576;
        motionWait = 10;
    }
}
