// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Pauser.java

package general;


// Referenced classes of package general:
//            Model

public class Pauser
{

    public Pauser()
    {
    }

    public static void calculatePaus()
    {
        if(paused)
        {
            pausCounter += Model.elapsedTime / 1000000D;
            if(pausCounter > pausTime)
            {
                pausCounter = 0.0D;
                paused = false;
            }
        }
    }

    public static void setPausTime(double p)
    {
        paused = true;
        pausTime = p;
    }

    private static final int TIMEDIVIDER = 0xf4240;
    private static double pausTime = 0.0D;
    private static double pausCounter = 0.0D;
    public static boolean paused = false;

}
