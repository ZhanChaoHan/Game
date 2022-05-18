// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExplosionHandler.java

package general;

import gameobjects.Enemy;
import gameobjects.Explosion;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package general:
//            Model

public class ExplosionHandler
{

    public ExplosionHandler(ArrayList explosions)
    {
        this.explosions = explosions;
        explTime = 0.0D;
        explCounter = 0;
        finished = false;
    }

    public void checkExplosionTime(Enemy enemy)
    {
        explTime += Model.elapsedTime * 9.9999999999999995E-007D;
        for(Iterator iterator = explosions.iterator(); iterator.hasNext();)
        {
            Explosion e = (Explosion)iterator.next();
            if(explTime >= e.getStartTime() && !e.isStarted())
            {
                e.setX(e.getX() + enemy.getX());
                e.setY(e.getY() + enemy.getY());
                e.setState(4, 0);
                e.setState(0, 1);
                e.setStarted(true);
                explCounter++;
                if(explCounter < explosions.size() - 4 && explCounter % 2 == 0)
                    Model.addSoundToList("/sounds/small_destroy1.wav");
            }
        }

    }

    public ArrayList getExplosions()
    {
        return explosions;
    }

    public int getExplCounter()
    {
        return explCounter;
    }

    public boolean isFinished()
    {
        return finished;
    }

    public void setFinished(boolean finished)
    {
        this.finished = finished;
    }

    private ArrayList explosions;
    private double explTime;
    private int explCounter;
    private boolean finished;
}
