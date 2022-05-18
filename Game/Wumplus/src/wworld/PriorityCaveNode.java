// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PriorityCaveNode.java

package wworld;

import java.util.Vector;

// Referenced classes of package wworld:
//            CaveNode

public class PriorityCaveNode
    implements Comparable
{

    public PriorityCaveNode(CaveNode cavenode, int i, Vector vector, char c)
    {
        node = cavenode;
        cost = i;
        directions = new Vector(vector);
        directions.add(Character.valueOf(c));
    }

    public int compareTo(Object obj)
    {
        PriorityCaveNode prioritycavenode = (PriorityCaveNode)obj;
        if(cost < prioritycavenode.cost)
            return -1;
        return cost != prioritycavenode.cost ? 1 : 0;
    }

    public int cost;
    public CaveNode node;
    public Vector directions;
}
