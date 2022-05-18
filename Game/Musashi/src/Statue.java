// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bg.java


class Statue extends Bg
{

    Statue(int i)
    {
        name = "\u50CF";
        hp = 3;
        x = i;
        y = 0;
        lineY = 0;
        id = 506;
        for(int j = 0; j < 4; j++)
        {
            for(int k = 0; k < 4; k++)
                motion[j][k] = 336;

        }

    }
}
