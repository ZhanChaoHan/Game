// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enemy2.java


class BeanKai extends Enemy2
{

    BeanKai(int i)
    {
        name = "\u30D3\u30FC\u30F3\u6539";
        lv = 1;
        maxHp = 55;
        hp = 55;
        ap = 22;
        dp = 8;
        ep = 26;
        x = i;
        y = 0;
        lineY = 288;
        motionWait = 10;
        moveType = 0;
        motionType = 1;
        atkRange = 0;
    }
}
