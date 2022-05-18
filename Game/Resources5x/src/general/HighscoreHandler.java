// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HighscoreHandler.java

package general;

import java.io.*;
import java.net.*;

// Referenced classes of package general:
//            GameControl

public class HighscoreHandler
{

    public static HighscoreHandler getInstance()
    {
        if(singleInstance == null)
            singleInstance = new HighscoreHandler();
        return singleInstance;
    }

    private HighscoreHandler()
    {
        names = new String[10];
        scores = new int[10];
        clear();
    }

    public String[] getNamesArray()
    {
        return names;
    }

    public int[] getScoresArray()
    {
        return scores;
    }

    private void clear()
    {
        for(int i = 0; i < 10; i++)
        {
            names[i] = "-";
            scores[i] = (int)Math.random() * 0xf4240;
        }

    }

    public static void save(String name, int score)
    {
        if(GameControl.online)
            try
            {
                URL url = new URL("http://www.jalex.se/Cephei/bin/save_cephei.php");
                URLConnection urlConn = url.openConnection();
                urlConn.setDoInput(true);
                urlConn.setDoOutput(true);
                urlConn.setUseCaches(false);
                urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                DataOutputStream printout = new DataOutputStream(urlConn.getOutputStream());
                String content = (new StringBuilder("Score1=")).append(URLEncoder.encode(Integer.toString(score), "UTF-8")).append("&Name1=").append(URLEncoder.encode(name, "UTF-8")).toString();
                printout.writeBytes(content);
                printout.flush();
                printout.close();
                DataInputStream input = new DataInputStream(urlConn.getInputStream());
                String str;
                while((str = input.readLine()) != null) ;
                input.close();
            }
            catch(MalformedURLException me)
            {
                System.err.println((new StringBuilder("MalformedURLException: ")).append(me).toString());
            }
            catch(IOException ioe)
            {
                System.err.println((new StringBuilder("IOException: ")).append(ioe.getMessage()).toString());
            }
    }

    public void load()
    {
        clear();
        if(GameControl.online)
        {
            try
            {
                URL url = new URL("http://www.jalex.se/Cephei/bin/get_cephei.php");
                URLConnection urlConn = url.openConnection();
                urlConn.setDoInput(true);
                urlConn.setDoOutput(true);
                urlConn.setUseCaches(false);
                urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                DataOutputStream printout = new DataOutputStream(urlConn.getOutputStream());
                String content = (new StringBuilder("Get=")).append(URLEncoder.encode("", "UTF-8")).toString();
                printout.writeBytes(content);
                printout.flush();
                printout.close();
                DataInputStream input = new DataInputStream(urlConn.getInputStream());
                String str;
                for(int i = 0; (str = input.readLine()) != null; i++)
                    if(i == 0)
                        names[0] = str;
                    else
                    if(i == 1)
                        scores[0] = Integer.parseInt(str);
                    else
                    if(i == 2)
                        names[1] = str;
                    else
                    if(i == 3)
                        scores[1] = Integer.parseInt(str);
                    else
                    if(i == 4)
                        names[2] = str;
                    else
                    if(i == 5)
                        scores[2] = Integer.parseInt(str);
                    else
                    if(i == 6)
                        names[3] = str;
                    else
                    if(i == 7)
                        scores[3] = Integer.parseInt(str);
                    else
                    if(i == 8)
                        names[4] = str;
                    else
                    if(i == 9)
                        scores[4] = Integer.parseInt(str);
                    else
                    if(i == 10)
                        names[5] = str;
                    else
                    if(i == 11)
                        scores[5] = Integer.parseInt(str);
                    else
                    if(i == 12)
                        names[6] = str;
                    else
                    if(i == 13)
                        scores[6] = Integer.parseInt(str);
                    else
                    if(i == 14)
                        names[7] = str;
                    else
                    if(i == 15)
                        scores[7] = Integer.parseInt(str);
                    else
                    if(i == 16)
                        names[8] = str;
                    else
                    if(i == 17)
                        scores[8] = Integer.parseInt(str);
                    else
                    if(i == 18)
                        names[9] = str;
                    else
                    if(i == 19)
                        scores[9] = Integer.parseInt(str);

                input.close();
            }
            catch(MalformedURLException me)
            {
                System.err.println((new StringBuilder("MalformedURLException: ")).append(me).toString());
            }
            catch(IOException ioe)
            {
                System.err.println((new StringBuilder("IOException: ")).append(ioe.getMessage()).toString());
            }
        } else
        {
            for(int i = 0; i < 10; i++)
            {
                names[i] = "Johan";
                scores[i] = 125;
            }

        }
    }

    public static final int MAXSCORES = 10;
    public static final int MAXNAMELENGTH = 10;
    public static final int NRPLACES = 9;
    private static String names[];
    private static int scores[];
    private static HighscoreHandler singleInstance = new HighscoreHandler();

}
