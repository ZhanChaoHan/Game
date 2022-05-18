// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileHandler.java

package general;

import java.io.*;

public class FileHandler
{

    public FileHandler()
    {
    }

    public void saveHighscore(long highscores[], long score)
    {
        try
        {
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("highscores.dat"))));
            int tempPos = 0;
            boolean scoreSaved = false;
            for(int i = 0; i < highscores.length; i++)
                if(score > highscores[i] && !scoreSaved)
                {
                    out.writeLong(score);
                    scoreSaved = true;
                } else
                {
                    out.writeLong(highscores[tempPos]);
                    tempPos++;
                }

            out.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadHighscores(long highscores[])
    {
        try
        {
            DataInputStream in = new DataInputStream(new FileInputStream("highscores.dat"));
            long score = 0L;
            for(int i = 0; i < highscores.length; i++)
            {
                score = in.readLong();
                highscores[i] = score;
            }

            in.close();
        }
        catch(IOException e)
        {
            System.out.println("An error occured when reading the file.");
        }
    }

    public static final int NRPLACES = 9;
}
