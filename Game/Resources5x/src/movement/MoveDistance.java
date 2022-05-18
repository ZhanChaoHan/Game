// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MoveDistance.java

package movement;

import gameobjects.*;

// Referenced classes of package movement:
//            Movement

public class MoveDistance
    implements Movement
{

    public MoveDistance()
    {
    }

    public void init(Sprite sprite)
    {
    }

    public void move(Sprite sprite)
    {
        Distance d = (Distance)sprite;
        if(Hero.scrolling)
            d.setX((d.getX() - d.getMoveAdd()) + Hero.scrollAdd);
        if(d.getX() < -d.getWidth() + Hero.scrollX)
            d.setX(d.getWidth() + (d.getX() + d.getWidth()));
    }
}
