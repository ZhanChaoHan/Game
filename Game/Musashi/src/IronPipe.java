// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java


class IronPipe extends Item
{

    IronPipe(int i)
    {
        name = "\u9244\u30D1\u30A4\u30D7";
        maxHp = 40;
        hp = 40;
        x = i;
        y = 0;
        maxAp = 25;
        ap = 3;
        dp = 0;
        kind = 21;
        comment = "\u9244\u88FD\u306E\u30D1\u30A4\u30D7\u3060\u305E!";
        id = 103;
        for(int j = 0; j < 4; j++)
        {
            for(int k = 0; k < 4; k++)
                motion[j][k] = 432;

        }

    }
}
