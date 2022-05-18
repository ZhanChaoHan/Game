// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java


class Vest extends Item
{

    Vest(int i)
    {
        name = "\u9632\u5F3E\u30C1\u30E7\u30C3\u30AD";
        maxHp = 50;
        hp = 50;
        x = i;
        y = 0;
        ap = 0;
        maxDp = 15;
        dp = 4;
        kind = 22;
        comment = "\u9283\u304C\u76F8\u624B\u3067\u3082\u5B89\u5FC3\u3060\u305E!";
        id = 203;
        for(int j = 0; j < 4; j++)
        {
            for(int k = 0; k < 4; k++)
                motion[j][k] = 432;

        }

    }
}
