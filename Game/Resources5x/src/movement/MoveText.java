// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MoveText.java

package movement;

import gameobjects.Sprite;
import gameobjects.Text;

// Referenced classes of package movement:
//            Movement

public class MoveText
    implements Movement
{

    public MoveText(int maxDist)
    {
        this.maxDist = maxDist;
    }

    public void init(Sprite sprite)
    {
    }

    public void move(Sprite sprite)
    {
        Text t = (Text)sprite;
        t.initMove();
        t.setY(t.getY() - t.getMoveAdd());
        checkDistance(t);
    }

    private void checkDistance(Text t)
    {
        dist += t.getMoveAdd();
        if(dist >= maxDist)
            t.setState(4, 1);
    }

    private int dist;
    private int maxDist;
}
