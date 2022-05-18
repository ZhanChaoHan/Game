// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java


class MusashiSword extends Item
{

    MusashiSword(int i)
    {
        name = "\u6B66\u8535\u306E\u5200";
        maxHp = 100;
        hp = 100;
        x = i;
        y = 0;
        maxAp = 100;
        ap = 18;
        dp = 0;
        kind = 21;
        comment = "\u5263\u8C6A \u6B66\u8535\u304C\u4F7F\u3063\u3066\u305F\u3063\u307D\u3044\u305E!";
        id = 110;
        for(int j = 0; j < 4; j++)
        {
            for(int k = 0; k < 4; k++)
                motion[j][k] = 432;

        }

    }
}
