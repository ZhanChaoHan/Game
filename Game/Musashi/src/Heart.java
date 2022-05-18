// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enemy.java


class Heart extends Enemy
{

    Heart(int i)
    {
        name = "\u3042\u3093\u306E\u3046\u3093";
        lv = 1;
        maxHp = 100;
        hp = 100;
        ap = 32;
        dp = 8;
        ep = 99;
        x = i;
        y = 0;
        lineY = 720;
        motionWait = 10;
    }
}
