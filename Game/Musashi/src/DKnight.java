// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enemy.java


class DKnight extends Enemy
{

    DKnight(int i)
    {
        name = "D\u30CA\u30A4\u30C8";
        lv = 1;
        maxHp = 55;
        hp = 55;
        ap = 16;
        dp = 6;
        ep = 20;
        x = i;
        y = 0;
        lineY = 336;
        motionWait = 6;
        moveType = 0;
        motionType = 0;
    }
}
