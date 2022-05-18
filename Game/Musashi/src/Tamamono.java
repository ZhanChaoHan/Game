// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java


class Tamamono extends Item
{

    Tamamono(int i)
    {
        name = "\u6367\u9B42\u306E\u8CDC\u7269";
        maxHp = 20;
        hp = 20;
        x = i;
        y = 0;
        ap = 0;
        maxDp = 55;
        dp = 20;
        kind = 22;
        comment = "\u4F55\u3060\u304B\u3088\u304F\u308F\u304B\u3089\u306A\u3044\u305E!";
        id = 209;
        for(int j = 0; j < 4; j++)
        {
            for(int k = 0; k < 4; k++)
                motion[j][k] = 432;

        }

    }
}
