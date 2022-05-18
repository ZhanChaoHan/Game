// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java


class Kiriichimonji extends Item
{

    Kiriichimonji(int i)
    {
        name = "\u6850\u4E00\u6587\u5B57";
        maxHp = 30;
        hp = 30;
        x = i;
        y = 0;
        maxAp = 60;
        ap = 12;
        dp = 0;
        kind = 21;
        comment = "\u540D\u5200\u3089\u3057\u3044\u305E!";
        id = 108;
        for(int j = 0; j < 4; j++)
        {
            for(int k = 0; k < 4; k++)
                motion[j][k] = 432;

        }

    }
}
