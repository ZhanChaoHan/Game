// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java


class NitenArmor extends Item
{

    NitenArmor(int i)
    {
        name = "\u4E8C\u5929\u306E\u93A7";
        maxHp = 200;
        hp = 100;
        x = i;
        y = 0;
        ap = 0;
        maxDp = 100;
        dp = 18;
        kind = 22;
        comment = "\u3082\u3046\u3072\u3068\u3064\u306E\u624D\u80FD\u3060\u305E!";
        id = 208;
        for(int j = 0; j < 4; j++)
        {
            for(int k = 0; k < 4; k++)
                motion[j][k] = 432;

        }

    }
}
