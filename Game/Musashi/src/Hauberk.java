// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java


class Hauberk extends Item
{

    Hauberk(int i)
    {
        name = "\u30CF\u30A5\u30FC\u30D0\u30FC\u30AF";
        maxHp = 100;
        hp = 40;
        x = i;
        y = 0;
        ap = 0;
        maxDp = 30;
        dp = 10;
        kind = 22;
        comment = "\u30EF\u30F3\u30D4\u30FC\u30B9\u3063\u307D\u3044\u3089\u3057\u3044\u305E!";
        id = 206;
        for(int j = 0; j < 4; j++)
        {
            for(int k = 0; k < 4; k++)
                motion[j][k] = 432;

        }

    }
}
