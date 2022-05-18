// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enemy2.java


class Mejibo extends Enemy2
{

    Mejibo(int i)
    {
        name = "\u30E1\u30B8\u30DC\u30FC";
        lv = 1;
        maxHp = 60;
        hp = 60;
        ap = 18;
        dp = 8;
        ep = 24;
        x = i;
        y = 0;
        lineY = 384;
        motionWait = 15;
        moveType = 2;
        motionType = 1;
        atkRange = 0;
    }

    void attack()
    {
        byte byte0 = 8;
        stat = 11;
        motionCount = 0;
        app.musashi.hp -= byte0;
        battleMsg(app.musashi, byte0);
    }
}
