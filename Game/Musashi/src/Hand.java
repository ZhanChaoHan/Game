// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java


class Hand extends Item
{

    Hand(int i)
    {
        name = "\u7D20\u624B";
        hp = 1;
        x = i;
        y = 0;
        ap = 0;
        dp = 0;
        kind = 21;
        comment = "\u3042\u307E\u308A\u935B\u3048\u3089\u308C\u3066\u306A\u3044\u62F3\u3060\u305E!";
        id = 101;
        for(int j = 0; j < 4; j++)
        {
            for(int k = 0; k < 4; k++)
                motion[j][k] = 0;

        }

    }
}
