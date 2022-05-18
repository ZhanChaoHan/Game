// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MoveTag.java

package movement;

import gameobjects.Enemy;
import gameobjects.Sprite;

// Referenced classes of package movement:
//            Movement

public class MoveTag
    implements Movement
{

    public MoveTag()
    {
    }

    public void init(Sprite sprite)
    {
    }

    public void move(Sprite sprite)
    {
        Enemy s = (Enemy)sprite;
        if(s.getAnimMovingNr() == 0 || s.getAnimMovingNr() == 1)
            s.setOrder(2);
        else
            s.setOrder(3);
    }
}
