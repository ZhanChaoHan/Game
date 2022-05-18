// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java


class Wear extends Item
{

    Wear(int i)
    {
        name = "\u666E\u6BB5\u7740";
        hp = 1;
        x = i;
        y = 0;
        ap = 0;
        dp = 0;
        kind = 22;
        comment = "\u30D7\u30EA\u30C6\u30A3\u306A\u666E\u6BB5\u7740\u3060\u305E!";
        id = 201;
        for(int j = 0; j < 4; j++)
        {
            for(int k = 0; k < 4; k++)
                motion[j][k] = 0;

        }

    }
}
