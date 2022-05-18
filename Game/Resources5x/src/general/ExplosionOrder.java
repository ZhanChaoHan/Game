// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExplosionOrder.java

package general;

import gameobjects.Explosion;
import java.util.ArrayList;

public class ExplosionOrder
{

    public ExplosionOrder()
    {
    }

    public static ArrayList getOrder(int type)
    {
        ArrayList e = new ArrayList();
        if(type == 1)
        {
            for(int i = 0; i < 4; i++)
            {
                e.add(new Explosion("enemy_destroy2", -3, -3, 15, 15, 2 + i * 10, 5, 1.0D));
                e.add(new Explosion("enemy_destroy2", 8, -3, 15, 15, 4 + i * 10, 5, 1.0D));
                e.add(new Explosion("enemy_destroy2", -5, 5, 15, 15, 6 + i * 10, 5, 1.0D));
                e.add(new Explosion("enemy_destroy2", 10, 5, 15, 15, 8 + i * 10, 5, 1.0D));
                e.add(new Explosion("enemy_destroy2", -3, 14, 15, 15, 10 + i * 10, 5, 1.0D));
                e.add(new Explosion("enemy_destroy2", 8, 14, 15, 15, 12 + i * 10, 5, 1.0D));
            }

        } else
        if(type == 51)
        {
            for(int i = 0; i < 10; i++)
            {
                e.add(new Explosion("enemy_destroy1", -4, -2, 15, 15, 2 + i * 10, 5, 1.0D));
                e.add(new Explosion("enemy_destroy1", 3, -2, 15, 15, 4 + i * 10, 5, 1.0D));
                e.add(new Explosion("enemy_destroy1", -6, 3, 15, 15, 6 + i * 10, 5, 1.0D));
                e.add(new Explosion("enemy_destroy1", 5, 3, 15, 15, 8 + i * 10, 5, 1.0D));
                e.add(new Explosion("enemy_destroy1", -4, 8, 15, 15, 10 + i * 10, 5, 1.0D));
                e.add(new Explosion("enemy_destroy1", 3, 8, 15, 15, 12 + i * 10, 5, 1.0D));
            }

        } else
        if(type == 56)
        {
            for(int i = 0; i < 8; i++)
            {
                e.add(new Explosion("enemy_destroy1", -4, -5, 15, 15, 2 + i * 8, 5, 1.0D));
                e.add(new Explosion("enemy_destroy1", 7, -5, 15, 15, 4 + i * 8, 5, 1.0D));
                e.add(new Explosion("enemy_destroy1", -4, 6, 15, 15, 6 + i * 8, 5, 1.0D));
                e.add(new Explosion("enemy_destroy1", 7, 6, 15, 15, 8 + i * 8, 5, 1.0D));
            }

        } else
        if(type == 71)
        {
            for(int i = 0; i < 8; i++)
            {
                e.add(new Explosion("enemy_destroy2", -3, -3, 15, 15, 2 + i * 10, 5, 1.0D));
                e.add(new Explosion("enemy_destroy2", 18, -3, 15, 15, 4 + i * 10, 5, 1.0D));
                e.add(new Explosion("enemy_destroy2", -5, 4, 15, 15, 6 + i * 10, 5, 1.0D));
                e.add(new Explosion("enemy_destroy2", 20, 4, 15, 15, 8 + i * 10, 5, 1.0D));
                e.add(new Explosion("enemy_destroy2", -3, 14, 15, 15, 10 + i * 10, 5, 1.0D));
                e.add(new Explosion("enemy_destroy2", 18, 14, 15, 15, 12 + i * 10, 5, 1.0D));
                e.add(new Explosion("enemy_destroy2", 8, 4, 15, 15, 14 + i * 10, 5, 1.0D));
            }

        } else
        if(type == 106)
        {
            for(int i = 0; i < 8; i++)
            {
                e.add(new Explosion("enemy_destroy1", -4, -6, 15, 15, 2 + i * 8, 5, 1.0D));
                e.add(new Explosion("enemy_destroy1", 3, -6, 15, 15, 4 + i * 8, 5, 1.0D));
                e.add(new Explosion("enemy_destroy1", -4, 3, 15, 15, 6 + i * 8, 5, 1.0D));
                e.add(new Explosion("enemy_destroy1", 3, 3, 15, 15, 8 + i * 8, 5, 1.0D));
            }

        }
        return e;
    }

    private static final int STEP = 8;
    private static final int STEP2 = 10;
    private static final int SIZE1 = 15;
}
