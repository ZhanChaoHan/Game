// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java


class NoShieldArmor extends Item
{

    NoShieldArmor(int i)
    {
        name = "\u696F\u7121\u93A7";
        maxHp = 150;
        hp = 80;
        x = i;
        y = 0;
        ap = 0;
        maxDp = 35;
        dp = 12;
        kind = 22;
        comment = "\u56FD\u5B9D\u3060\u305E!";
        id = 207;
        for(int j = 0; j < 4; j++)
        {
            for(int k = 0; k < 4; k++)
                motion[j][k] = 432;

        }

    }
}
