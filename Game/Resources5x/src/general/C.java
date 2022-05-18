// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   C.java

package general;


public class C
{

    public C()
    {
    }

    public static int SIZE;
    public static final int MAXSIZE = 8;
    public static final int WIDTH = 120;
    public static final int HEIGHT = 90;
    public static final int WINDOWWIDTH = 120;
    public static final int WINDOWHEIGHT = 90;
    public static int RESIZEDWINDOWWIDTH;
    public static int RESIZEDWINDOWHEIGHT;
    public static final int Y = 0;
    public static final int GAMESTARTX = 0;
    public static final int GAMESTARTY = 0;
    public static final int TILE = 2;
    public static final int MAXTILES = 51;
    public static final int LASTLEVEL = 4;
    public static final int MAXLEVELS = 13;
    public static final int MINSIZE = 10;
    public static final double SCROLLSPEED = 0.33000000000000002D;

    static 
    {
        SIZE = 5;
        RESIZEDWINDOWWIDTH = 120 * SIZE;
        RESIZEDWINDOWHEIGHT = 90 * SIZE;
    }
}
