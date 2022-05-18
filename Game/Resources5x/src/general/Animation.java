// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Animation.java

package general;


// Referenced classes of package general:
//            Model

public class Animation
{

    public Animation(String tempImage, int nrImages, double animInterval, int nrRepeat, String type)
    {
        this.nrImages = nrImages;
        this.animInterval = animInterval;
        this.nrRepeat = nrRepeat;
        animCounter = 0;
        image = new String[nrImages];
        for(int i = 0; i < nrImages; i++)
            image[i] = (new StringBuilder("/images/")).append(tempImage).append("_").append(i).append(".").append(type).toString();

        finished = false;
    }

    public void init(String tempImage, double animInterval, int nrRepeat, String type)
    {
        for(int i = 0; i < nrImages; i++)
            image[i] = (new StringBuilder("/images/")).append(tempImage).append("_").append(i).append(".").append(type).toString();

        this.animInterval = animInterval;
        this.nrRepeat = nrRepeat;
        animCounter = 0;
        finished = false;
    }

    public void init()
    {
        animCounter = 0;
        animTime = 0.0D;
        finished = false;
    }

    public void clear()
    {
        finished = false;
        repeat = 0;
        animTime = 0.0D;
        animCounter = 0;
    }

    public void clearCounters()
    {
        animTime = 0.0D;
        animCounter = 0;
    }

    public boolean isFinished()
    {
        if(finished)
        {
            finished = false;
            repeat = 0;
            return true;
        } else
        {
            return false;
        }
    }

    public boolean isFinishedReal()
    {
        return finished;
    }

    public String getImage()
    {
        if(!finished)
        {
            animTime += Model.elapsedTime * 9.9999999999999995E-007D;
            if(animTime > animInterval)
            {
                animTime = 0.0D;
                animCounter++;
                if(animCounter == nrImages)
                    if(repeat != -1)
                    {
                        if(repeat == nrRepeat)
                        {
                            finished = true;
                            animCounter--;
                        } else
                        {
                            repeat++;
                            animCounter = 0;
                        }
                    } else
                    {
                        animCounter = 0;
                    }
            }
        }
        return image[animCounter];
    }

    public String getImage(int nr)
    {
        return image[nr];
    }

    public int getNr()
    {
        return animCounter;
    }

    public boolean isMaxNr()
    {
        return animCounter == nrImages;
    }

    public double getTime()
    {
        return animTime;
    }

    public static final String PATH = "/images/";
    public static final int ALWAYSREPEAT = -1;
    private String image[];
    private double animTime;
    private double animInterval;
    private int nrImages;
    private int animCounter;
    private int repeat;
    private int nrRepeat;
    private boolean finished;
}
