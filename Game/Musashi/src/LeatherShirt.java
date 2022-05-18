// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java


class LeatherShirt extends Item
{

    LeatherShirt(int i)
    {
        name = "\u76AE\u306E\u30B7\u30E3\u30C4";
        maxHp = 40;
        hp = 40;
        x = i;
        y = 0;
        ap = 0;
        maxDp = 10;
        dp = 2;
        kind = 22;
        comment = "\u30EC\u30B6\u30FC\u3060\u305E!";
        id = 202;
        for(int j = 0; j < 4; j++)
        {
            for(int k = 0; k < 4; k++)
                motion[j][k] = 432;

        }

    }
}
