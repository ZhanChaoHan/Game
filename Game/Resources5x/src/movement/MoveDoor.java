// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MoveDoor.java

package movement;

import gameobjects.ItemDoor;
import gameobjects.Sprite;

// Referenced classes of package movement:
//            Movement

public class MoveDoor
    implements Movement
{

    public MoveDoor(int maxDist)
    {
        this.maxDist = maxDist;
        dist = 0;
        opening = false;
    }

    public void init(Sprite sprite)
    {
    }

    public void move(Sprite sprite)
    {
        if(opening)
        {
            ItemDoor door = (ItemDoor)sprite;
            door.initMoveXY();
            door.setY(door.getY() + door.getMoveAddY());
            checkDistance(door);
        }
    }

    private void checkDistance(ItemDoor door)
    {
        dist += door.getMoveAddY();
        if(Math.abs(dist) >= maxDist)
        {
            door.setY(door.getY() + (Math.abs(dist) - maxDist));
            opening = false;
        }
    }

    private int dist;
    private int maxDist;
    public static boolean opening;
}
