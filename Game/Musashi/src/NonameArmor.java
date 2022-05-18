// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java


class NonameArmor extends Item
{

    NonameArmor(int i)
    {
        name = "\u7121\u540D\u306E\u93A7";
        maxHp = 100;
        hp = 70;
        x = i;
        y = 0;
        ap = 0;
        maxDp = 20;
        dp = 5;
        kind = 22;
        comment = "\u540D\u524D\u304C\u306A\u3044\u93A7\u3060\u305E!";
        id = 204;
        for(int j = 0; j < 4; j++)
        {
            for(int k = 0; k < 4; k++)
                motion[j][k] = 432;

        }

    }
}
