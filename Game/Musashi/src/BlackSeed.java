// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java


class BlackSeed extends Item
{

    BlackSeed(int i)
    {
        name = "\u9ED2\uFF89\u7A2E";
        hp = 1;
        x = i;
        y = 0;
        kind = 23;
        id = 303;
        for(int j = 0; j < 4; j++)
        {
            for(int k = 0; k < 4; k++)
                motion[j][k] = 480;

        }

    }
}
