// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GetKeys.java


public class GetKeys
{

    public GetKeys()
    {
        up = down = left = right = false;
        z = x = shift = false;
    }

    public void keyPressed(int i)
    {
        if(i == 37)
            left = true;
        if(i == 39)
            right = true;
        if(i == 38)
            up = true;
        if(i == 40)
            down = true;
        if(i == 90)
            z = true;
        if(i == 88)
            x = true;
        if(i == 16)
            shift = true;
        if(i == 123)
            f12 = true;
        if(i == 27)
            esc = true;
    }

    public void keyReleased(int i)
    {
        if(i == 37)
            left = false;
        if(i == 39)
            right = false;
        if(i == 38)
            up = false;
        if(i == 40)
            down = false;
        if(i == 90)
            z = false;
        if(i == 88)
            x = false;
        if(i == 16)
            shift = false;
        if(i == 123)
            f12 = false;
        if(i == 27)
            esc = false;
    }

    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    public boolean z;
    public boolean x;
    public boolean shift;
    public boolean f12;
    public boolean esc;
}
