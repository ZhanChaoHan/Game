// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AmmoOrder.java

package general;

import collision.CollisionAmmo;
import gameobjects.Ammo;
import java.util.ArrayList;
import movement.MoveAmmo;

public class AmmoOrder
{

    public AmmoOrder()
    {
    }

    public static ArrayList getAmmoOrderEnemy(int type)
    {
        ArrayList a = new ArrayList();
        if(type == 1)
        {
            a.add(new Integer(40));
            a.add(new Ammo("ammo8", -1, 1, 8, 5, new MoveAmmo(1), new CollisionAmmo(), 4, 3.5D, 0.0D, 240, 0.0F, 1, null, 2, 1.3999999999999999D));
        } else
        if(type == 2)
        {
            a.add(new Integer(30));
            a.add(new Ammo("ammo5", 0, 0, 8, 5, new MoveAmmo(), new CollisionAmmo(), 4, -20D, 0.0D, 240, 0.0F, 1, null, 2, 2D));
            a.add(new Ammo("ammo5", -20, 0, 8, 5, new MoveAmmo(), new CollisionAmmo(), 4, -15D, 10D, 240, 0.0F, 1, null, 2, 2D));
            a.add(new Ammo("ammo5", -20, 0, 8, 5, new MoveAmmo(), new CollisionAmmo(), 4, -15D, -10D, 240, 0.0F, 1, null, 2, 2D));
            a.add(new Integer(8));
            a.add(new Ammo("ammo5", 0, 0, 8, 5, new MoveAmmo(), new CollisionAmmo(), 4, -20D, 0.0D, 240, 0.0F, 1, null, 2, 2D));
        } else
        if(type == 3)
        {
            a.add(new Integer(15));
            a.add(new Ammo("ammo5", 0, 0, 8, 5, new MoveAmmo(), new CollisionAmmo(), 4, -20D, 0.0D, 240, 0.0F, 1, null, 2, 1.3999999999999999D));
        } else
        if(type == 4)
        {
            a.add(new Integer(9));
            a.add(new Ammo("ammo5", 0, 0, 8, 5, new MoveAmmo(), new CollisionAmmo(), 4, -27D, 0.0D, 240, 0.0F, 1, null, 2, 2D));
            a.add(new Integer(3));
            a.add(new Ammo("ammo5", -20, 0, 8, 5, new MoveAmmo(), new CollisionAmmo(), 4, -15D, 10D, 240, 0.0F, 1, null, 2, 2D));
            a.add(new Ammo("ammo5", -20, 0, 8, 5, new MoveAmmo(), new CollisionAmmo(), 4, -15D, -10D, 240, 0.0F, 1, null, 2, 2D));
        } else
        if(type == 5)
        {
            a.add(new Integer(23));
            a.add(new Ammo("ammo5", 0, 0, 8, 5, new MoveAmmo(), new CollisionAmmo(), 4, -25D, 0.0D, 240, 0.0F, 1, null, 2, 1.3999999999999999D));
            a.add(new Integer(10));
            a.add(new Ammo("ammo5", 0, 0, 8, 5, new MoveAmmo(), new CollisionAmmo(), 4, -20D, 0.0D, 240, 0.0F, 1, null, 2, 2D));
            a.add(new Ammo("ammo5", -20, 0, 8, 5, new MoveAmmo(), new CollisionAmmo(), 4, -15D, 10D, 240, 0.0F, 1, null, 2, 2D));
            a.add(new Ammo("ammo5", -20, 0, 8, 5, new MoveAmmo(), new CollisionAmmo(), 4, -15D, -10D, 240, 0.0F, 1, null, 2, 2D));
        } else
        if(type == 36)
        {
            a.add(new Integer(30));
            a.add(new Ammo("ammo7", 0, 1, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, 0.0D, -2.5D, 240, 0.0F, 1, null, 2, 1.3999999999999999D));
        } else
        if(type == 12)
        {
            a.add(new Integer(20));
            a.add(new Ammo("ammo3", 0, 1, 8, 5, new MoveAmmo(), new CollisionAmmo(), 4, 0.0D, 1.3999999999999999D, 240, 0.0F, 1, null, 2, 2D));
        } else
        if(type == 13)
        {
            a.add(new Integer(22));
            a.add(new Ammo("ammo3", 0, 1, 8, 5, new MoveAmmo(), new CollisionAmmo(), 4, -1.3999999999999999D, 0.0D, 240, 0.0F, 1, null, 2, 2D));
            a.add(new Ammo("ammo3", 0, 1, 8, 5, new MoveAmmo(), new CollisionAmmo(), 4, 1.3999999999999999D, 0.0D, 240, 0.0F, 1, null, 2, 2D));
        } else
        if(type == 41)
        {
            a.add(new Integer(25));
            a.add(new Ammo("ammo9", 0, 0, 5, 3, new MoveAmmo(1), new CollisionAmmo(), 4, 2D, 0.0D, 240, 0.0F, 1, null, 2, 2D));
        } else
        if(type == 26)
        {
            a.add(new Integer(24));
            a.add(new Ammo("ammo5", 0, 0, 5, 5, new MoveAmmo(true), new CollisionAmmo(), 4, -3D, 0.0D, 240, 1.0F, 2, "enemyammo1", 2, 1.3999999999999999D));
        } else
        if(type == 97)
        {
            a.add(new Integer(14));
            a.add(new Ammo("ammo3", 0, 0, 8, 5, new MoveAmmo(), new CollisionAmmo(), 4, -2D, 0.0D, 240, 0.0F, 1, null, 2, 1.3999999999999999D));
            a.add(new Integer(21));
            a.add(null);
        } else
        if(type == 46)
        {
            a.add(new Integer(14));
            a.add(new Ammo("ammo3", 0, 0, 8, 5, new MoveAmmo(), new CollisionAmmo(), 4, 2D, 0.0D, 240, 0.0F, 1, null, 2, 1.3999999999999999D));
            a.add(new Integer(21));
            a.add(null);
        } else
        if(type == 76)
        {
            for(int i = 0; i < 3; i++)
            {
                a.add(new Integer(15));
                a.add(new Ammo("ammo7", -1, 0, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, -3D, 0.0D, 240, 0.0F, 1, null, 2, 1.3999999999999999D));
            }

            a.add(new Integer(50));
            a.add(null);
        } else
        if(type == 21)
        {
            a.add(new Integer(25));
            a.add(new Ammo("ammo6", 0, -2, 8, 5, new MoveAmmo(), new CollisionAmmo(), 4, 0.0D, 1.0D, 240, 0.0F, 1, null, 2, 1.3999999999999999D));
        } else
        if(type == 82)
        {
            a.add(new Integer(20));
            a.add(new Ammo("ammo3", 0, 0, 8, 5, new MoveAmmo(), new CollisionAmmo(), 4, -35D, 0.0D, 240, 0.0F, 1, null, 2, 1.3999999999999999D));
        } else
        if(type == 86)
        {
            a.add(new Integer(40));
            a.add(new Ammo("ammo7", 0, 1, 5, 5, new MoveAmmo(2), new CollisionAmmo(), 4, 0.0D, 3.5D, 240, 0.0F, 1, "ammo3", 2, 1.0D));
        } else
        if(type == 93)
        {
            a.add(new Integer(20));
            a.add(new Ammo("ammo7", 0, 1, 5, 5, new MoveAmmo(2), new CollisionAmmo(), 4, 0.0D, 1.5D, 240, 0.0F, 1, "ammo3", 2, 1.0D));
        } else
        if(type == 111)
        {
            a.add(new Integer(14));
            a.add(new Ammo("ammo10", 0, 1, 5, 3, new MoveAmmo(), new CollisionAmmo(), 4, -3.5D, 0.0D, 240, 0.0F, 1, null, 2, 1.3999999999999999D));
            a.add(new Ammo("ammo10", 0, 1, 5, 3, new MoveAmmo(), new CollisionAmmo(), 4, 3.5D, 0.0D, 240, 0.0F, 1, null, 2, 1.3999999999999999D));
            a.add(new Integer(21));
            a.add(null);
        } else
        if(type == 112)
        {
            a.add(new Integer(14));
            a.add(new Ammo("ammo10", 0, 1, 5, 3, new MoveAmmo(), new CollisionAmmo(), 4, -3.5D, 0.0D, 240, 0.0F, 1, null, 2, 1.3999999999999999D));
            a.add(new Ammo("ammo10", 0, 1, 5, 3, new MoveAmmo(), new CollisionAmmo(), 4, 3.5D, 0.0D, 240, 0.0F, 1, null, 2, 1.3999999999999999D));
            a.add(new Integer(21));
            a.add(null);
        }
        return a;
    }

    public static ArrayList getAmmoOrderBoss(int type)
    {
        ArrayList a = new ArrayList();
        ArrayList ammoList = new ArrayList();
        if(type == 51)
        {
            ammoList = new ArrayList();
            a.add(ammoList);
            ammoList = new ArrayList();
            for(int i = 0; i < 3; i++)
            {
                ammoList.add(new Integer(9));
                ammoList.add(new Ammo("ammo7", 0, -4, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, -1.2D, 0.0D, 240, 0.0F, 1, "ammo3", 2, 2D));
                ammoList.add(new Integer(9));
                ammoList.add(new Ammo("ammo7", 0, 2, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, -1.2D, 0.0D, 240, 0.0F, 1, "ammo3", 2, 2D));
                ammoList.add(new Integer(9));
                ammoList.add(new Ammo("ammo7", 0, 6, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, -1.2D, 0.0D, 240, 0.0F, 1, "ammo3", 2, 2D));
            }

            ammoList.add(new Integer(20));
            ammoList.add(new Ammo("ammo7", 0, -4, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, -1.2D, -0.90000000000000002D, 240, 0.0F, 1, "ammo3", 2, 2D));
            ammoList.add(new Ammo("ammo7", 0, -4, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, -1.2D, 0.0D, 240, 0.0F, 1, "ammo3", 2, 2D));
            ammoList.add(new Ammo("ammo7", 0, -4, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, -1.2D, 0.90000000000000002D, 240, 0.0F, 1, "ammo3", 2, 2D));
            ammoList.add(new Integer(20));
            ammoList.add(new Ammo("ammo7", 0, -4, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, -1.2D, -0.69999999999999996D, 240, 0.0F, 1, "ammo3", 2, 2D));
            ammoList.add(new Ammo("ammo7", 0, -4, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, -1.2D, 0.0D, 240, 0.0F, 1, "ammo3", 2, 2D));
            ammoList.add(new Ammo("ammo7", 0, -4, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, -1.2D, 0.69999999999999996D, 240, 0.0F, 1, "ammo3", 2, 2D));
            ammoList.add(new Integer(20));
            ammoList.add(new Ammo("ammo7", 0, -4, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, -1.2D, -0.5D, 240, 0.0F, 1, "ammo3", 2, 2D));
            ammoList.add(new Ammo("ammo7", 0, -4, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, -1.2D, 0.0D, 240, 0.0F, 1, "ammo3", 2, 2D));
            ammoList.add(new Ammo("ammo7", 0, -4, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, -1.2D, 0.5D, 240, 0.0F, 1, "ammo3", 2, 2D));
            a.add(ammoList);
        } else
        if(type == 56)
        {
            for(int i = 0; i < 3; i++)
            {
                ammoList = new ArrayList();
                a.add(ammoList);
            }

            ammoList = new ArrayList();
            ammoList.add(new Integer(4));
            ammoList.add(new Ammo("ammo7", 0, 0, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, 1.2D, -1.2D, 240, 0.0F, 1, "ammo3", 2, 2D));
            ammoList.add(new Ammo("ammo7", 0, 0, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, 0.0D, -1.2D, 240, 0.0F, 1, "ammo3", 2, 2D));
            ammoList.add(new Ammo("ammo7", 0, 0, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, -1.2D, -1.2D, 240, 0.0F, 1, "ammo3", 2, 2D));
            a.add(ammoList);
            ammoList = new ArrayList();
            a.add(ammoList);
            ammoList = new ArrayList();
            ammoList.add(new Integer(4));
            ammoList.add(new Ammo("ammo7", 0, 0, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, 1.2D, -1.2D, 240, 0.0F, 1, "ammo3", 2, 2D));
            ammoList.add(new Ammo("ammo7", 0, 0, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, 0.0D, -1.2D, 240, 0.0F, 1, "ammo3", 2, 2D));
            ammoList.add(new Ammo("ammo7", 0, 0, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, -1.2D, -1.2D, 240, 0.0F, 1, "ammo3", 2, 2D));
            a.add(ammoList);
            ammoList = new ArrayList();
            a.add(ammoList);
            ammoList = new ArrayList();
            ammoList.add(new Integer(4));
            ammoList.add(new Ammo("ammo7", 0, 0, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, 1.2D, -1.2D, 240, 0.0F, 1, "ammo3", 2, 2D));
            ammoList.add(new Ammo("ammo7", 0, 0, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, 0.0D, -1.2D, 240, 0.0F, 1, "ammo3", 2, 2D));
            ammoList.add(new Ammo("ammo7", 0, 0, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, -1.2D, -1.2D, 240, 0.0F, 1, "ammo3", 2, 2D));
            a.add(ammoList);
            ammoList = new ArrayList();
            a.add(ammoList);
            ammoList = new ArrayList();
            ammoList.add(new Integer(4));
            ammoList.add(new Ammo("ammo7", 0, 0, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, 1.2D, -1.2D, 240, 0.0F, 1, "ammo3", 2, 2D));
            ammoList.add(new Ammo("ammo7", 0, 0, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, 0.0D, -1.2D, 240, 0.0F, 1, "ammo3", 2, 2D));
            ammoList.add(new Ammo("ammo7", 0, 0, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, -1.2D, -1.2D, 240, 0.0F, 1, "ammo3", 2, 2D));
            a.add(ammoList);
            for(int i = 0; i < 3; i++)
            {
                ammoList = new ArrayList();
                a.add(ammoList);
            }

            ammoList = new ArrayList();
            for(int i = 0; i < 2; i++)
            {
                ammoList.add(new Integer(10));
                ammoList.add(new Ammo("ammo7", 0, -5, 5, 5, new MoveAmmo(2), new CollisionAmmo(), 4, 0.0D, 1.5D, 240, 0.0F, 1, "ammo3", 2, 1.3999999999999999D));
            }

            a.add(ammoList);
        } else
        if(type == 71)
        {
            for(int i = 0; i < 3; i++)
            {
                ammoList = new ArrayList();
                a.add(ammoList);
            }

            ammoList = new ArrayList();
            ammoList.add(new Integer(8));
            ammoList.add(new Ammo("ammo7", 0, 0, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, 1.2D, -2.2000000000000002D, 240, 0.0F, 1, null, 2, 2D));
            ammoList.add(new Ammo("ammo9", 0, 0, 14, 10, new MoveAmmo(), new CollisionAmmo(), 4, 0.0D, -7D, 240, 0.0F, 1, "ammo4", 2, 0.5D));
            ammoList.add(new Ammo("ammo7", 0, 0, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, -1.2D, -2.2000000000000002D, 240, 0.0F, 1, null, 2, 2D));
            a.add(ammoList);
            for(int i = 0; i < 3; i++)
            {
                ammoList = new ArrayList();
                a.add(ammoList);
            }

            ammoList = new ArrayList();
            for(int i = 0; i < 3; i++)
            {
                ammoList.add(new Integer(12));
                ammoList.add(new Ammo("ammo7", 2, 0, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, 5D, 0.0D, 240, 0.0F, 1, "ammo3", 2, 2D));
            }

            a.add(ammoList);
            for(int i = 0; i < 5; i++)
            {
                ammoList = new ArrayList();
                a.add(ammoList);
            }

            ammoList = new ArrayList();
            for(int i = 0; i < 3; i++)
            {
                ammoList.add(new Integer(12));
                ammoList.add(new Ammo("ammo7", 2, 0, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, -5D, 0.0D, 240, 0.0F, 1, "ammo3", 2, 2D));
            }

            a.add(ammoList);
        }
        if(type == 106)
        {
            ammoList = new ArrayList();
            a.add(ammoList);
            ammoList = new ArrayList();
            for(int i = 0; i < 1; i++)
            {
                ammoList.add(new Integer(8));
                ammoList.add(new Ammo("ammo11", -2, -1, 3, 12, new MoveAmmo(), new CollisionAmmo(), 4, -2.2000000000000002D, 0.0D, 240, 0.0F, 1, "ammo4", 2, 2D));
            }

            a.add(ammoList);
            ammoList = new ArrayList();
            a.add(ammoList);
            ammoList = new ArrayList();
            for(int i = 0; i < 1; i++)
            {
                ammoList.add(new Integer(2));
                ammoList.add(new Ammo("ammo11", -2, -2, 3, 12, new MoveAmmo(), new CollisionAmmo(), 4, -2.2000000000000002D, 0.0D, 240, 0.0F, 1, "ammo4", 2, 2D));
            }

            a.add(ammoList);
            for(int i = 0; i < 2; i++)
            {
                ammoList = new ArrayList();
                a.add(ammoList);
            }

            ammoList = new ArrayList();
            a.add(ammoList);
            ammoList = new ArrayList();
            for(int i = 0; i < 1; i++)
            {
                ammoList.add(new Integer(8));
                ammoList.add(new Ammo("ammo7", -2, -1, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, -1.3999999999999999D, -1D, 240, 0.0F, 1, "ammo3", 2, 2D));
                ammoList.add(new Ammo("ammo7", -2, -1, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, -2.2000000000000002D, 0.0D, 240, 0.0F, 1, "ammo3", 2, 2D));
                ammoList.add(new Ammo("ammo7", -2, -1, 5, 5, new MoveAmmo(), new CollisionAmmo(), 4, -1.3999999999999999D, 1.0D, 240, 0.0F, 1, "ammo3", 2, 2D));
            }

            a.add(ammoList);
            for(int i = 0; i < 2; i++)
            {
                ammoList = new ArrayList();
                a.add(ammoList);
            }

            ammoList = new ArrayList();
            a.add(ammoList);
            ammoList = new ArrayList();
            for(int i = 0; i < 1; i++)
            {
                ammoList.add(new Integer(8));
                ammoList.add(new Ammo("ammo11", -2, -1, 3, 12, new MoveAmmo(), new CollisionAmmo(), 4, -2.2000000000000002D, 0.0D, 240, 0.0F, 1, "ammo4", 2, 2D));
            }

            a.add(ammoList);
            ammoList = new ArrayList();
            a.add(ammoList);
            ammoList = new ArrayList();
            for(int i = 0; i < 1; i++)
            {
                ammoList.add(new Integer(8));
                ammoList.add(new Ammo("ammo11", -2, -2, 3, 12, new MoveAmmo(), new CollisionAmmo(), 4, -2.2000000000000002D, 0.0D, 240, 0.0F, 1, "ammo4", 2, 2D));
            }

            a.add(ammoList);
        }
        return a;
    }

    public static final int WIDTH1 = 8;
    public static final int HEIGHT1 = 5;
    public static final int WIDTH2 = 8;
    public static final int HEIGHT2 = 5;
    public static final int WIDTH3 = 5;
    public static final int HEIGHT3 = 5;
    public static final int WIDTH4 = 5;
    public static final int HEIGHT4 = 3;
    public static final int WIDTH5 = 14;
    public static final int HEIGHT5 = 10;
    public static final int WIDTH6 = 45;
    public static final int HEIGHT6 = 45;
    public static final int WIDTH7 = 40;
    public static final int HEIGHT7 = 40;
    private static final double ANIM = 1.3999999999999999D;
}
