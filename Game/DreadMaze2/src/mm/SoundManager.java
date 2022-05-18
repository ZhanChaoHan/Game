// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SoundManager.java

package mm;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

public class SoundManager
{

    public SoundManager()
    {
    }

    public static void initialize()
    {
        map = new HashMap();
    }

    public static void preloadImages(ArrayList sounds)
    {
        for(int i = 0; i < sounds.size(); i++)
            preloadSound((String)sounds.get(i));

    }

    public static boolean preloadSound(String name)
    {
      if (!map.containsKey(name))
      {
        try
        {
          map.put(name, Applet.newAudioClip(Thread.currentThread().getContextClassLoader().getResource("Sounds/" + name)));
          return true;
        }
        catch (Exception e)
        {
          System.err.println("Bad sound loading: " + name);
          e.printStackTrace();
        }
      }
      return false;
    }


    public static void clearMap()
    {
        map.clear();
    }

    public static void unloadImages(ArrayList sounds)
    {
        for(int i = 0; i < sounds.size(); i++)
            unloadSound((String)sounds.get(i));

    }

    public static void unloadSound(String name)
    {
        map.remove(name);
    }

    public static void playSound(String name)
    {
        AudioClip s = (AudioClip)map.get(name);
        if(s == null)
        {
            boolean success = preloadSound(name);
            s = (AudioClip)map.get(name);
            if(success)
            {
                System.err.println((new StringBuilder("Late preloading: ")).append(name).toString());
            } else
            {
                System.err.println((new StringBuilder("Unable to load: ")).append(name).toString());
                return;
            }
        }
        s.play();
    }

    public static void loopSound(String name)
    {
        AudioClip s = (AudioClip)map.get(name);
        if(s == null)
        {
            boolean success = preloadSound(name);
            s = (AudioClip)map.get(name);
            if(success)
            {
                System.err.println((new StringBuilder("Late preloading: ")).append(name).toString());
            } else
            {
                System.err.println((new StringBuilder("Unable to load: ")).append(name).toString());
                return;
            }
        }
        s.loop();
    }

    public static void stopSound(String name)
    {
        AudioClip s = (AudioClip)map.get(name);
        if(s == null)
        {
            boolean success = preloadSound(name);
            s = (AudioClip)map.get(name);
            if(success)
            {
                System.err.println((new StringBuilder("Late preloading: ")).append(name).toString());
            } else
            {
                System.err.println((new StringBuilder("Unable to load: ")).append(name).toString());
                return;
            }
        }
        s.stop();
    }

    private static HashMap map;
}
