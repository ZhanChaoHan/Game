// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enemy.java


class Shinobi extends Enemy
{

    Shinobi(int i)
    {
        name = "\u5FCD";
        lv = 1;
        maxHp = 50;
        hp = 50;
        ap = 24;
        dp = 0;
        ep = 20;
        dir = 0;
        x = i;
        y = 0;
        lineY = 480;
        motionWait = 50;
        moveType = 1;
        motionType = 1;
        for(int j = 0; j < 4; j++)
        {
            motion[j][1] = 192;
            motion[j][2] = 192;
            motion[j][3] = 192;
        }

    }
}
