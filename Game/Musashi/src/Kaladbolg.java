// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java


class Kaladbolg extends Item
{

    Kaladbolg(int i)
    {
        name = "\u30AB\u30E9\u30C9\u30DC\u30EB\u30B0";
        maxHp = 100;
        hp = 100;
        x = i;
        y = 0;
        maxAp = 30;
        ap = 14;
        dp = 0;
        kind = 21;
        comment = "\u9B54\u5263\u3089\u3057\u3044\u305E!";
        id = 109;
        for(int j = 0; j < 4; j++)
        {
            for(int k = 0; k < 4; k++)
                motion[j][k] = 432;

        }

    }
}
