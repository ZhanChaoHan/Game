// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java

package gameobjects;

import general.Animation;

// Referenced classes of package gameobjects:
//            Sprite

public class Item extends Sprite
{

    public Item(String tempImage, int x, int y, int w, int h, int nrImages, double animInterval)
    {
        super(tempImage, x, y, w, h, 0, 0.0D, 0, nrImages, animInterval);
        for(int i = 0; i < 4; i++)
            animStopped[i] = new Animation(tempImage, nrImages, animInterval, -1, "png");

        animHit = new Animation("item_hit", 5, 0.80000000000000004D, 0, "png");
    }
}
