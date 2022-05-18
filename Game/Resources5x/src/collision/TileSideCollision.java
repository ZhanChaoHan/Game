// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TileSideCollision.java

package collision;


public class TileSideCollision
{

    public TileSideCollision(int x, int y, String type, double dist)
    {
        this.x = x;
        this.y = y;
        this.type = type;
        this.dist = dist;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public double getDist()
    {
        return dist;
    }

    public void setDist(double dist)
    {
        this.dist = dist;
    }

    public static int xCounter;
    public static int yCounter;
    public static String maxAxis;
    private int x;
    private int y;
    private double dist;
    private String type;
}
