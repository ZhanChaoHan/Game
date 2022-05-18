// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Score.java

package fab;

import java.awt.Graphics;

// Referenced classes of package fab:
//            ScoreTable, HighScores, Text

public class Score
{

    public Score()
    {
        high = 0;
        score = 0;
        lines = 0;
        ScoreTable scoretable = new ScoreTable();
        allscore = scoretable.Get();
        hs = new HighScores(allscore);
        if(hs.hs.length > 0)
            high = hs.hs[0].score;
    }

    public void Add()
    {
        score++;
    }

    public void AddRow(int rows)
    {
        score += ROWSMODIFIER[rows - 1] * 10;
        lines += rows;
    }

    public void SetHigh(String name)
    {
        if(score > high)
            high = score;
        ScoreTable scoretable = new ScoreTable();
        scoretable.Insert(null, name, score);
        allscore = scoretable.Get();
        hs.GetHighScores(allscore);
    }

    public void SetHigh(String name, String mode)
    {
        if(score > high)
            high = score;
        ScoreTable scoretable = new ScoreTable();
        scoretable.Insert(null, name, score, mode);
        allscore = scoretable.Get();
        hs.GetHighScores(allscore);
    }

    public void Reload(String mode)
    {
        ScoreTable scoretable = new ScoreTable();
        if(mode.equals("*"))
            allscore = scoretable.Get();
        else
            allscore = scoretable.Get(mode);
        hs.GetHighScores(allscore);
    }

    public void Draw(Graphics g)
    {
        Text text = new Text();
        String msg = String.format("HiScore: %05d", new Object[] {
            Integer.valueOf(high)
        });
        text.Draw(msg, 470, 28, g, Text.Position.RIGHT);
        msg = String.format("Score: %05d", new Object[] {
            Integer.valueOf(score)
        });
        text.Draw(msg, 470, 56, g, Text.Position.RIGHT);
        msg = String.format("Lines: %05d", new Object[] {
            Integer.valueOf(lines)
        });
        text.Draw(msg, 470, 84, g, Text.Position.RIGHT);
    }

    public void ResetScore()
    {
        score = 0;
        lines = 0;
    }

    final int LINE = 28;
    final int HISCPOS = 20;
    final int SCPOS = 10;
    final int MAX_HI_SCORES = 10;
    final int ROWSMODIFIER[] = {
        1, 2, 4, 7
    };
    public String allscore;
    int high;
    int score;
    int lines;
    public HighScores hs;
}
