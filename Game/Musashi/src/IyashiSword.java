// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java


class IyashiSword extends Item
{

    IyashiSword(int i)
    {
        name = "\u7652\u3057\u306E\u5263";
        maxHp = 40;
        hp = 40;
        x = i;
        y = 0;
        maxAp = 40;
        ap = 10;
        dp = 0;
        kind = 21;
        comment = "\u4F53\u529B\u304C\u56DE\u5FA9\u3057\u3061\u3083\u3046\u305E!";
        id = 107;
        for(int j = 0; j < 4; j++)
        {
            for(int k = 0; k < 4; k++)
                motion[j][k] = 432;

        }

    }
}
