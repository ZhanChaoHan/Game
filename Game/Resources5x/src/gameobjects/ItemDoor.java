// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemDoor.java

package gameobjects;

import general.Animation;
import movement.MoveDoor;

// Referenced classes of package gameobjects:
//            Item

public class ItemDoor extends Item
{

    public ItemDoor(String tempImage, int x, int y, int w, int h, int nrImages, double animInterval)
    {
        super(tempImage, x, y, w, h, nrImages, animInterval);
        movement = new MoveDoor(h);
        setDir(2, true);
        setVelY(-1D);
    }

    public void isHit()
    {
        if(animHit.isFinished())
        {
            states[2] = 0;
            states[4] = 1;
        }
    }

    public int getType()
    {
        return type;
    }
}
