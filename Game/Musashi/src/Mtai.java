// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enemy.java


class Mtai extends Enemy
{

    Mtai(int i)
    {
        name = "\u30A8\u30E0\u30BF\u30A4";
        lv = 1;
        maxHp = 120;
        hp = 120;
        ap = 52;
        dp = 8;
        ep = 128;
        x = i;
        y = 0;
        lineY = 768;
        motionWait = 4;
    }
}
