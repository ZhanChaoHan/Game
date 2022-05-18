// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemBonus.java

package gameobjects;

import general.Animation;

// Referenced classes of package gameobjects:
//            Item

public class ItemBonus extends Item
{

    public ItemBonus(String tempImage, int x, int y, int w, int h, int score, int nrImages, 
            double animInterval)
    {
        super(tempImage, x, y, w, h, nrImages, animInterval);
        this.score = score;
    }

    public void isHit()
    {
        if(animHit.isFinished())
        {
            states[2] = 0;
            states[4] = 1;
        }
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public static final int SCORE1 = 50;
    public static final int SCORE2 = 4000;
    private int score;
}
