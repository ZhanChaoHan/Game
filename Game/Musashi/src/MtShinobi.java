// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enemy2.java


class MtShinobi extends Enemy2
{

    MtShinobi(int i)
    {
        name = "\u30A2\u30B5\u30B7\u30F3";
        lv = 1;
        maxHp = 60;
        hp = 60;
        ap = 42;
        dp = 0;
        ep = 32;
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
