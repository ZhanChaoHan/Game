// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Particle.java

package gameobjects;

import movement.Movement;

// Referenced classes of package gameobjects:
//            Sprite

public class Particle extends Sprite
{

    public Particle(String image, int x, int y, int w, int h, Movement m, double vel, float a, int nrImages, double animInterval)
    {
        super(image, x, y, w, h, 1, vel, 1, nrImages, animInterval);
        angleMove = a;
        movement = m;
        startX = x;
    }

    public int getStartX()
    {
        return startX;
    }

    public float getAngleMove()
    {
        return angleMove;
    }

    private int startX;
    private float angleMove;
}
