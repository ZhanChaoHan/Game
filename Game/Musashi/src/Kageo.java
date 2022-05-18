// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enemy.java


class Kageo extends Enemy
{

    Kageo(int i)
    {
        name = "\u5F71\u7537";
        lv = 1;
        maxHp = 35;
        hp = 35;
        ap = 12;
        dp = 2;
        ep = 5;
        x = i;
        y = 0;
        lineY = 192;
        motionWait = 6;
        moveType = 3;
        motionType = 1;
    }
}
