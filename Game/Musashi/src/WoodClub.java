// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java


class WoodClub extends Item
{

    WoodClub(int i)
    {
        name = "\u6728\u306E\u68D2";
        maxHp = 35;
        hp = 35;
        x = i;
        y = 0;
        maxAp = 20;
        ap = 2;
        dp = 0;
        kind = 21;
        comment = "\u305F\u3060\u306E\u6728\u306E\u68D2\u3060\u305E!";
        id = 102;
        for(int j = 0; j < 4; j++)
        {
            for(int k = 0; k < 4; k++)
                motion[j][k] = 432;

        }

    }
}
