// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemCharacter.java

package gameobjects;

import movement.Movement;

// Referenced classes of package gameobjects:
//            Item

public class ItemCharacter extends Item
{

    public ItemCharacter(String tempImage, int x, int y, int w, int h, Movement m, double velX, double velY, int nrImages, double animInterval)
    {
        super(tempImage, x, y, w, h, nrImages, animInterval);
        movement = m;
        this.velX = velX;
        this.velY = velY;
        states[1] = 1;
        setDir(0, true);
        runningDir = 0;
    }
}
