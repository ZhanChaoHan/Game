// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Supmuw.java

package wworld;


// Referenced classes of package wworld:
//            Agent, CaveNode

public class Supmuw
{

    public Supmuw(int i, int j, CaveNode cavenode)
    {
        x = i;
        y = j;
        isDead = false;
        hasFood = true;
        supmuwNode = cavenode;
    }

    public String encounter(Agent agent)
    {
        if(isFriendly && hasFood)
        {
            hasFood = false;
            agent.hasFood = true;
            getClass();
            agent.score += 100;
            return "FOODGET";
        }
        if(!isFriendly)
        {
            agent.die();
            return "DIE";
        } else
        {
            return "SUPMUWWAVES";
        }
    }

    public void updateMode()
    {
        if(supmuwNode.hasStench)
            isFriendly = false;
        else
            isFriendly = true;
        if(supmuwNode.hasPit)
            isInPit = true;
        else
            isInPit = false;
    }

    public int x;
    public int y;
    public boolean isDead;
    public boolean isInPit;
    public boolean isFriendly;
    public boolean hasFood;
    private CaveNode supmuwNode;
    private final int scoreForFood = 100;
}
