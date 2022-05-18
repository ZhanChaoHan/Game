// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java


class Nunchaku extends Item
{

    Nunchaku(int i)
    {
        name = "\u30CC\u30F3\u30C1\u30E3\u30AF";
        maxHp = 30;
        hp = 30;
        x = i;
        y = 0;
        maxAp = 40;
        ap = 6;
        dp = 0;
        kind = 21;
        comment = "\u30AB\u30F3\u30D5\u30FC\u3067\u4F7F\u3046\u3042\u308C\u3060\u305E!";
        id = 105;
        for(int j = 0; j < 4; j++)
        {
            for(int k = 0; k < 4; k++)
                motion[j][k] = 432;

        }

    }
}
