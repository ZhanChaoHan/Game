// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemEnergy.java

package gameobjects;

import general.Animation;

// Referenced classes of package gameobjects:
//            Item

public class ItemEnergy extends Item
{

    public ItemEnergy(String tempImage, int x, int y, int w, int h, int nrImages, double animInterval)
    {
        super(tempImage, x, y, w, h, nrImages, animInterval);
    }

    public void isHit()
    {
        if(animHit.isFinished())
        {
            states[2] = 0;
            states[4] = 1;
        }
    }
}
