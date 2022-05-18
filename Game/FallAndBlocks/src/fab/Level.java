// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Level.java

package fab;

import java.awt.Color;
import java.awt.Graphics;

public class Level
{

    public Level(int l)
    {
        level = 0;
        levelscore = 0;
        currp = 0;
        level = l;
    }

    public void NextLevel()
    {
        if(level < 7)
            level++;
        ResetLevelScore();
    }

    public void Draw(Graphics g)
    {
        g.setColor(new Color(85, 0, 0));
        g.fillRect(380, 120, 100, 20);
        if(levelscore > 0)
        {
            int p = (int)(((float)levelscore / (float)limits[level - 1]) * 100F);
            if(p > currp)
                currp++;
            g.setColor(new Color(170, 0, 0));
            g.fillRect(380, 120, currp, 20);
        }
    }

    public boolean IsLevelFinished(int sc)
    {
        levelscore += sc;
        return levelscore >= limits[level - 1];
    }

    public void SetLevel(int l)
    {
        level = l;
        ResetLevelScore();
    }

    public void ResetLevelScore()
    {
        if(levelscore > 0 && level > 1)
            levelscore = levelscore - limits[level - 1 - 1];
        else
            levelscore = 0;
        currp = 0;
    }

    private final int METER_X = 380;
    private final int METER_Y = 120;
    private final int METER_H = 20;
    private final int METER_W = 100;
    final int MAX_LEVELS = 7;
    int level;
    int levelscore;
    int currp;
    int limits[] = {
        5, 10, 15, 25, 50, 100, 5000
    };
}
