// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MovementOrder.java

package movement;

import java.util.ArrayList;

public class MovementOrder
{

    public MovementOrder()
    {
    }

    public static ArrayList moveOrder(int type)
    {
        ArrayList m = new ArrayList();
        if(type == 3)
        {
            m.add(new Integer(7));
            m.add(new Integer(-30));
            m.add(new Integer(18));
            m.add(new Integer(0));
            m.add(new Integer(20));
            m.add(new Integer(3));
            m.add(new Integer(0));
            m.add(new Integer(0));
            m.add(new Integer(200));
            m.add(new Integer(-20));
            m.add(new Integer(0));
            m.add(new Integer(0));
        } else
        if(type == 4)
        {
            m.add(new Integer(7));
            m.add(new Integer(-30));
            m.add(new Integer(18));
            m.add(new Integer(0));
            m.add(new Integer(20));
            m.add(new Integer(3));
            m.add(new Integer(0));
            m.add(new Integer(0));
            m.add(new Integer(200));
            m.add(new Integer(-20));
            m.add(new Integer(0));
            m.add(new Integer(0));
        } else
        if(type == 5)
        {
            m.add(new Integer(18));
            m.add(new Integer(-6));
            m.add(new Integer(-15));
            m.add(new Integer(70));
            m.add(new Integer(8));
            m.add(new Integer(7));
            m.add(new Integer(-5));
            m.add(new Integer(60));
            m.add(new Integer(6));
            m.add(new Integer(-17));
            m.add(new Integer(0));
            m.add(new Integer(400));
        } else
        if(type == 6)
        {
            m.add(new Integer(25));
            m.add(new Integer(-5));
            m.add(new Integer(10));
            m.add(new Integer(80));
            m.add(new Integer(3));
            m.add(new Integer(-70));
            m.add(new Integer(0));
            m.add(new Integer(100));
        } else
        if(type == 7)
        {
            m.add(new Integer(25));
            m.add(new Integer(-5));
            m.add(new Integer(-10));
            m.add(new Integer(80));
            m.add(new Integer(3));
            m.add(new Integer(-70));
            m.add(new Integer(0));
            m.add(new Integer(100));
        } else
        if(type == 8)
        {
            m.add(new Integer(25));
            m.add(new Integer(-5));
            m.add(new Integer(10));
            m.add(new Integer(50));
            m.add(new Integer(3));
            m.add(new Integer(-70));
            m.add(new Integer(0));
            m.add(new Integer(100));
        } else
        if(type == 9)
        {
            m.add(new Integer(25));
            m.add(new Integer(-5));
            m.add(new Integer(-10));
            m.add(new Integer(50));
            m.add(new Integer(3));
            m.add(new Integer(-70));
            m.add(new Integer(0));
            m.add(new Integer(100));
        } else
        if(type == 12 || type == 37)
        {
            m.add(new Integer(125));
            m.add(new Double(25D));
        } else
        if(type == 61)
        {
            m.add(new Integer(25));
            m.add(new Integer(-7));
            m.add(new Integer(8));
            m.add(new Integer(100));
            m.add(new Integer(9));
            m.add(new Integer(7));
            m.add(new Integer(-2));
            m.add(new Integer(60));
            m.add(new Integer(10));
            m.add(new Integer(-25));
            m.add(new Integer(0));
            m.add(new Integer(400));
        } else
        if(type == 66 || type == 67)
        {
            m.add(new Integer(125));
            m.add(new Double(20D));
        }
        return m;
    }

    public static ArrayList moveOrderBoss(int type)
    {
        ArrayList moveOrder = new ArrayList();
        ArrayList tempList = new ArrayList();
        if(type == 51 || type == 81)
        {
            tempList = new ArrayList();
            tempList.add(Double.valueOf(0.0D));
            tempList.add(Double.valueOf(0.0D));
            tempList.add(Double.valueOf(0.0D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(145D));
            moveOrder.add(tempList);
        } else
        if(type == 56)
        {
            tempList = new ArrayList();
            tempList.add(Double.valueOf(0.0D));
            tempList.add(Double.valueOf(-2D));
            tempList.add(Double.valueOf(6D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(5D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(-2D));
            tempList.add(Double.valueOf(0.0D));
            tempList.add(Double.valueOf(5D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(20D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(-2D));
            tempList.add(Double.valueOf(0.0D));
            tempList.add(Double.valueOf(5D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(20D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(-2D));
            tempList.add(Double.valueOf(0.0D));
            tempList.add(Double.valueOf(5D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(20D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(-2D));
            tempList.add(Double.valueOf(0.0D));
            tempList.add(Double.valueOf(5D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(20D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(5D));
            tempList.add(Double.valueOf(0.0D));
            tempList.add(Double.valueOf(20D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(0.0D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(0.0D));
            tempList.add(Double.valueOf(2D));
            tempList.add(Double.valueOf(6D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(35D));
            moveOrder.add(tempList);
        } else
        if(type == 71)
        {
            tempList = new ArrayList();
            tempList.add(Double.valueOf(0.0D));
            tempList.add(Double.valueOf(-4D));
            tempList.add(Double.valueOf(49D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(5D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(-5D));
            tempList.add(Double.valueOf(0.0D));
            tempList.add(Double.valueOf(40D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(20D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(-5D));
            tempList.add(Double.valueOf(0.0D));
            tempList.add(Double.valueOf(40D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(5D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(0.0D));
            tempList.add(Double.valueOf(4D));
            tempList.add(Double.valueOf(49D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(45D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(0.0D));
            tempList.add(Double.valueOf(-4D));
            tempList.add(Double.valueOf(49D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(5D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(5D));
            tempList.add(Double.valueOf(0.0D));
            tempList.add(Double.valueOf(80D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(5D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(0.0D));
            tempList.add(Double.valueOf(4D));
            tempList.add(Double.valueOf(49D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(45D));
            moveOrder.add(tempList);
        } else
        if(type == 106)
        {
            tempList = new ArrayList();
            tempList.add(Double.valueOf(0.0D));
            tempList.add(Double.valueOf(-3.7000000000000002D));
            tempList.add(Double.valueOf(13D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(12D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(0.0D));
            tempList.add(Double.valueOf(3.7000000000000002D));
            tempList.add(Double.valueOf(13D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(12D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(0.0D));
            tempList.add(Double.valueOf(-3.7000000000000002D));
            tempList.add(Double.valueOf(7D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(6D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(-3D));
            tempList.add(Double.valueOf(0.0D));
            tempList.add(Double.valueOf(12D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(20D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(3D));
            tempList.add(Double.valueOf(0.0D));
            tempList.add(Double.valueOf(12D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(6D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(0.0D));
            tempList.add(Double.valueOf(-3.7000000000000002D));
            tempList.add(Double.valueOf(6D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(12D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(0.0D));
            tempList.add(Double.valueOf(3.7000000000000002D));
            tempList.add(Double.valueOf(13D));
            moveOrder.add(tempList);
            tempList = new ArrayList();
            tempList.add(Double.valueOf(12D));
            moveOrder.add(tempList);
        }
        return moveOrder;
    }
}
