// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MoveParticle.java

package movement;

import gameobjects.Particle;
import gameobjects.Sprite;

// Referenced classes of package movement:
//            Movement

public class MoveParticle
    implements Movement
{

    public MoveParticle()
    {
    }

    public void init(Sprite sprite)
    {
    }

    public void move(Sprite sprite)
    {
        Particle p = (Particle)sprite;
        p.setX(p.getX() - p.getMoveAdd());
        p.setY(p.getY() - p.getMoveAdd());
        checkDistance(p);
    }

    private void checkDistance(Particle p)
    {
        if(p.getY() + p.getHeight() < 0)
            p.setY(90);
    }
}
