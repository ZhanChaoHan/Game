// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FPSleep.java


public class FPSleep
{

    public FPSleep(int i)
    {
        calcInterval = 0L;
        frameCount = 0L;
        actualFPS = 0.0D;
        overSleepTime = 0L;
        noDelays = 0;
        FPS = i;
        PERIOD = (long)((1.0D / (double)FPS) * 1000000000D);
        beforeTime = System.nanoTime();
        prevCalcTime = beforeTime;
    }

    private void calcFPS()
    {
        frameCount++;
        calcInterval += PERIOD;
        if(calcInterval >= MAX_STATS_INTERVAL)
        {
            long l = System.nanoTime();
            long l1 = l - prevCalcTime;
            actualFPS = ((double)frameCount / (double)l1) * 1000000000D;
            frameCount = 0L;
            calcInterval = 0L;
            prevCalcTime = l;
        }
    }

    public double getActualFPS()
    {
        return actualFPS;
    }

    public void sleep()
    {
        afterTime = System.nanoTime();
        timeDiff = afterTime - beforeTime;
        sleepTime = PERIOD - timeDiff - overSleepTime;
        if(sleepTime > 0L)
        {
            try
            {
                Thread.sleep(sleepTime / 0xf4240L);
            }
            catch(InterruptedException interruptedexception)
            {
                interruptedexception.printStackTrace();
            }
            overSleepTime = System.nanoTime() - afterTime - sleepTime;
        } else
        {
            overSleepTime = 0L;
            if(++noDelays >= 16)
            {
                Thread.yield();
                noDelays = 0;
            }
        }
        beforeTime = System.nanoTime();
        calcFPS();
    }

    private static int FPS = 60;
    private static long PERIOD;
    private static long MAX_STATS_INTERVAL = 0x3b9aca00L;
    private long calcInterval;
    private long prevCalcTime;
    private long frameCount;
    private double actualFPS;
    private long beforeTime;
    private long afterTime;
    private long timeDiff;
    private long sleepTime;
    private long overSleepTime;
    private int noDelays;

}
