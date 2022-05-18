// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bg.java


class Wood extends Bg
{

    Wood(int i, int j)
    {
        name = "\u6728";
        hp = 1;
        x = i;
        y = j;
        lineY = 0;
        id = 501;
        for(int k = 0; k < 4; k++)
        {
            for(int l = 0; l < 4; l++)
                motion[k][l] = 48;

        }

    }
}
