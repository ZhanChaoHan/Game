// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java


class PunchGlove extends Item
{

    PunchGlove(int i)
    {
        name = "\u30D1\u30F3\u30C1\u30B0\u30E9\u30D6";
        maxHp = 40;
        hp = 40;
        x = i;
        y = 0;
        maxAp = 30;
        ap = 5;
        dp = 0;
        kind = 21;
        comment = "\u5927\u304D\u306A\u30B0\u30ED\u30FC\u30D6\u3060\u305E!";
        id = 104;
        for(int j = 0; j < 4; j++)
        {
            for(int k = 0; k < 4; k++)
                motion[j][k] = 432;

        }

    }
}
