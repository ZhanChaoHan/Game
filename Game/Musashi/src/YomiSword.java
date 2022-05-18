// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java


class YomiSword extends Item
{

    YomiSword(int i)
    {
        name = "\u9EC4\u6CC9\u30CE\u30C4\u30EB\u30AE";
        maxHp = 20;
        hp = 20;
        x = i;
        y = 0;
        maxAp = 70;
        ap = 20;
        dp = 0;
        kind = 21;
        comment = "\u6050\u308D\u3057\u3044\u5263\u3060\u305E!";
        id = 111;
        for(int j = 0; j < 4; j++)
        {
            for(int k = 0; k < 4; k++)
                motion[j][k] = 432;

        }

    }
}
