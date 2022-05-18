// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enemy.java


class Bean extends Enemy
{

    Bean(int i)
    {
        name = "\u30D3\u30FC\u30F3";
        lv = 1;
        maxHp = 40;
        hp = 40;
        ap = 14;
        dp = 4;
        ep = 8;
        x = i;
        y = 0;
        lineY = 288;
        motionWait = 10;
        moveType = 1;
        motionType = 1;
        atkRange = 0;
    }
}
