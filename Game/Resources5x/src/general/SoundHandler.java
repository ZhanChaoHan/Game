// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SoundHandler.java

package general;

import java.util.*;

// Referenced classes of package general:
//            Sound

public class SoundHandler
{

    public SoundHandler()
    {
        sounds = new String[3];
        clearAllSounds();
        allSounds = new HashMap();
    }

    public void init(ArrayList filenames)
    {
        Sound sound;
        for(Iterator iterator = filenames.iterator(); iterator.hasNext();)
            sound = (Sound)iterator.next();

    }

    public void clearAllSounds()
    {
        for(int i = 0; i < sounds.length; i++)
            sounds[i] = "";

    }

    public void loadSounds(Sound sound1)
    {
    }

    public void playAllSounds()
    {
        for(int i = 0; i < sounds.length; i++)
            sounds[i].equals("");

    }

    public void stop(String s)
    {
    }

    public static void stopAllSounds()
    {
    }

    public static void addSound(String filename)
    {
        for(int i = 0; i < sounds.length; i++)
        {
            if(!sounds[i].equals(""))
                continue;
            sounds[i] = filename;
            break;
        }

    }

    public void cleanUp()
    {
    }

    public static final int MAXSIMSOUNDS = 3;
    public static String sounds[];
    public static HashMap allSounds;
}
