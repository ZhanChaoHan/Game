// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HighScores.java

package fab;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

// Referenced classes of package fab:
//            Text

public class HighScores
{
    class HighScore
    {

        public String name;
        public int score;


        public HighScore(String name, int score)
        {

            this.name = name;
            this.score = score;
        }
    }


    public HighScores(String scores)
    {
        dy = 200;
        skip = 0;
        hs = new HighScore[10];
        hs[0] = new HighScore(" ", 0);
        maxscores = 0;
        GetHighScores(scores);
        ImageIcon iid = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("message.png"));
        background = iid.getImage();
        background = background.getScaledInstance(260, 200, 0);
    }

    public void GetHighScores(String scores)
    {
        int c = 0;
        int co = 0;
        int i = 0;
        String name = "";
        int s = 0;
        if(scores.length() > 0)
        {
            for(; c + 1 < scores.length() && i < 10; i++)
            {
                c = scores.indexOf(',', co);
                name = scores.substring(co, c);
                co = c + 1;
                c = scores.indexOf(',', co);
                s = Integer.parseInt(scores.substring(co, c));
                co = c + 1;
                hs[i] = new HighScore(name, s);
            }

            maxscores = i - 1;
        }
    }

    public void Draw(Graphics g, JPanel p)
    {
        g.drawImage(background, 126, 222, p);
        java.awt.Shape clip = g.getClip();
        g.setClip(126, 234, 260, 182);
        for(int i = 0; i <= maxscores; i++)
        {
            String msg = hs[i].name;
            int y = 234 + i * 20 + dy;
            if(y >= 234 && y <= 414)
            {
                Text text = new Text();
                text.Draw(msg, 134, 234 + i * 20 + dy, g);
                msg = String.valueOf(hs[i].score);
                text.Draw(msg, 378, 234 + i * 20 + dy, g, Text.Position.RIGHT);
            }
            skip++;
            if(skip != 5)
                continue;
            skip = 0;
            dy--;
            if(dy == -140)
                dy = 200;
        }

        g.setClip(clip);
    }

    final int MAX_HI_SCORES = 10;
    final int X = 134;
    final int Y = 234;
    final int TOP_Y_TO = 414;
    final int SKIP = 5;
    final int DY = 200;
    int maxscores;
    int dy;
    int skip;
    Image background;
    HighScore hs[];
}
