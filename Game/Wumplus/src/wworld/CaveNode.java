// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CaveNode.java

package wworld;


public class CaveNode
{

    public CaveNode(int i, int j)
    {
        pitProbability = 0.5D;
        wumpusProbability = 0.10000000000000001D;
        supmuwProbability = 0.10000000000000001D;
        wasVisited = false;
        isSafe = false;
        foundNorthWall = false;
        foundSouthWall = false;
        foundEastWall = false;
        foundWestWall = false;
        hasBreeze = false;
        hasStench = false;
        hasMoo = false;
        hasGold = false;
        hasWumpus = false;
        hasSupmuw = false;
        hasPit = false;
        hasObstacle = false;
        if(i == 1 && j == 1)
            hasEntrance = true;
        else
            hasEntrance = false;
        x = i;
        y = j;
    }

    public double pitProbability;
    public double wumpusProbability;
    public double supmuwProbability;
    public boolean wasVisited;
    public boolean isSafe;
    public boolean foundNorthWall;
    public boolean foundSouthWall;
    public boolean foundEastWall;
    public boolean foundWestWall;
    public int x;
    public int y;
    public boolean hasBreeze;
    public boolean hasStench;
    public boolean hasMoo;
    public boolean hasGold;
    public boolean hasWumpus;
    public boolean hasSupmuw;
    public boolean hasPit;
    public boolean hasObstacle;
    public boolean hasEntrance;
}
