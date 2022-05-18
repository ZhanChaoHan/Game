// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java


class RingMail extends Item
{

    RingMail(int i)
    {
        name = "\u30EA\u30F3\u30B0\u30E1\u30A4\u30EB";
        maxHp = 80;
        hp = 60;
        x = i;
        y = 0;
        ap = 0;
        maxDp = 25;
        dp = 8;
        kind = 22;
        comment = "\u8F2A\u72B6\u306B\u7DE8\u3093\u3067\u3042\u308B\u3089\u3057\u3044\u305E!";
        id = 205;
        for(int j = 0; j < 4; j++)
        {
            for(int k = 0; k < 4; k++)
                motion[j][k] = 432;

        }

    }
}
