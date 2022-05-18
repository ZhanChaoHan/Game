// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImageManager.java

package mm;

import java.awt.image.BufferedImage;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class ImageManager
{

    public ImageManager()
    {
    }

    public static void initialize()
    {
        map = new HashMap();
    }

    public static void preloadImages(ArrayList images)
    {
        for(int i = 0; i < images.size(); i++)
            preloadImage((String)images.get(i));

    }

    public static boolean preloadImage(String name)
    {
      if (!map.containsKey(name))
      {
        try
        {
          map.put(name, ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("Images/" + name)));
          return true;
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
      return false;
    }

    public static BufferedImage getImage(String name)
    {
        BufferedImage s = (BufferedImage)map.get(name);
        if(s == null)
        {
            preloadImage(name);
            s = (BufferedImage)map.get(name);
            System.err.println((new StringBuilder("Late preloading: ")).append(name).toString());
        }
        return s;
    }

    public static void clearMap()
    {
        map.clear();
    }

    public static void unloadImages(ArrayList images)
    {
        for(int i = 0; i < images.size(); i++)
            unloadImage((String)images.get(i));

    }

    public static void unloadImage(String name)
    {
        map.remove(name);
    }

    private static HashMap map;
}
