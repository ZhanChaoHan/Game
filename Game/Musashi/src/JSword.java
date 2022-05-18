// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java


class JSword extends Item
{

    JSword(int i)
    {
        name = "\u65E5\u672C\u5200";
        maxHp = 50;
        hp = 50;
        x = i;
        y = 0;
        maxAp = 50;
        ap = 8;
        dp = 0;
        kind = 21;
        comment = "\u65E5\u672C\u4EBA\u306A\u3089\u3084\u3063\u3071\u3053\u308C\u3060\u306D!";
        id = 106;
        for(int j = 0; j < 4; j++)
        {
            for(int k = 0; k < 4; k++)
                motion[j][k] = 432;

        }

    }
}
