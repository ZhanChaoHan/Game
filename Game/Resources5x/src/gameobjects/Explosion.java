// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Explosion.java

package gameobjects;

import general.Animation;

// Referenced classes of package gameobjects:
//            Sprite

public class Explosion extends Sprite
{

    public Explosion(String tempImage, int x, int y, int w, int h, double startTime, 
            int nrImages, double animInterval)
    {
        super(tempImage, x, y, w, h, 0, 0.0D, 0, nrImages, animInterval);
        this.startTime = startTime;
        for(int i = 0; i < 4; i++)
            animStopped[i] = new Animation(tempImage, nrImages, animInterval, 0, "png");

        clearStates();
        finished = started = false;
    }

    public void isStopped()
    {
        if(animStopped[1].isFinished())
        {
            states[0] = 0;
            states[4] = 1;
            finished = true;
        }
    }

    public double getStartTime()
    {
        return startTime;
    }

    public boolean isStarted()
    {
        return started;
    }

    public void setStarted(boolean started)
    {
        this.started = started;
    }

    public boolean isFinished()
    {
        return finished;
    }

    private double startTime;
    private boolean started;
    private boolean finished;
}
