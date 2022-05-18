// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Distance.java

package gameobjects;

import general.Animation;
import movement.MoveDistance;

// Referenced classes of package gameobjects:
//            Sprite

public class Distance extends Sprite
{

    public Distance(String tempImage, int x, int y, int w, int h, int vel)
    {
        super(tempImage, x, y, w, h, 1, vel, 0, 1, 0.0D);
        runningDir = 0;
        movement = new MoveDistance();
        for(int i = 0; i < 4; i++)
            animMoving[i] = new Animation(tempImage, nrImages, 0.0D, -1, "png");

    }

    public static int VEL = 2;

}
