// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ScoreTable.java

package fab;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class ScoreTable
{

    public ScoreTable()
    {
        gameid = 2;
        path = "http://asphaltgalaxy.com/test2/";
        unable = "unfortunately,0,unable,0,to get,00,high,0,scores,0,";
    }

    public void Insert(String page, String name, int score)
    {
        int k = SKey(name, score);
        String link = (new StringBuilder()).append(path).append("insert2.php?gi=").append(gameid).append("&n=").append(name).append("&s=").append(score).append("&k=").append(k).toString();
        OpenStreamInsert(link);
    }

    public void Insert(String page, String name, int score, String mode)
    {
        int k = SKey(name, score);
        String link = (new StringBuilder()).append(path).append("insert2.php?gi=").append(gameid).append("&n=").append(name).append("&s=").append(score).append("&m=").append(mode).append("&k=").append(k).toString();
        OpenStreamInsert(link);
    }

    private int SKey(String name, int score)
    {
        String s = Integer.toString(score);
        int k = Integer.parseInt(s.substring(s.length() - 1)) * 3;
        if(s.length() > 1)
            k += Integer.parseInt(s.substring(s.length() - 2)) * 2;
        k += name.charAt(0) + name.length() + s.length() * 7;
        return k;
    }

    private void OpenStreamInsert(String link)
    {
        try
        {
            URL url = new URL(link);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            while((inputLine = in.readLine()) != null) ;
            in.close();
        }
        catch(MalformedURLException mue)
        {
            System.out.println("Ouch - a MalformedURLException happened.");
        }
        catch(IOException ioe)
        {
            System.out.println("Ouch - a MalformedURLException happened.");
        }
    }

    public String Get()
    {
        String link = (new StringBuilder()).append(path).append("view3.php?gi=").append(gameid).toString();
        return OpenStreamGet(link);
    }

    public String Get(String mode)
    {
        String link = (new StringBuilder()).append(path).append("view3.php?gi=").append(gameid).append("&m=").append(mode).toString();
        return OpenStreamGet(link);
    }

    private String OpenStreamGet(String link)
    {
        String inputLine = "";
        try
        {
            URL url = new URL(link);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String il;
            while((il = in.readLine()) != null) 
                inputLine = il;
            in.close();
        }
        catch(MalformedURLException mue)
        {
            System.out.println("Ouch - a MalformedURLException happened.");
            return unable;
        }
        catch(IOException ioe)
        {
            System.out.println("Ouch - a MalformedURLException happened.");
            return unable;
        }
        return inputLine;
    }

    private final boolean NO_SCORES = false;
    int gameid;
    String path;
    String unable;
}
