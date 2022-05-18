// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Game.java

import general.Model;
import general.Sound;
import java.applet.AudioClip;
import java.util.*;

class SoundThread extends Thread
{

    SoundThread()
    {
    }

    public void run()
    {
        do
        {
            synchronized(Model.getSoundList())
            {
                Game.tempSoundList = (List)Model.getSoundList().clone();
                Model.clearSoundList();
            }
            checkStopSounds();
            playSounds();
            Game.tempSoundList.clear();
            try
            {
                Thread.sleep(20L);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        } while(true);
    }

    private void playSounds()
    {
        for(Iterator iterator = Game.tempSoundList.iterator(); iterator.hasNext();)
        {
            String sound = (String)iterator.next();
            if(!Model.isMute)
            {
                for(Iterator iterator1 = Model.allSounds.iterator(); iterator1.hasNext();)
                {
                    Sound realSound = (Sound)iterator1.next();
                    if(realSound.getFilename().equals(sound))
                        if(realSound.isLooping())
                            ((AudioClip)Game.allSounds.get(sound)).loop();
                        else
                            ((AudioClip)Game.allSounds.get(sound)).play();
                }

            }
        }

    }

    private void checkStopSounds()
    {
        if(Model.isStopAllSounds())
        {
            AudioClip a;
            for(Iterator iterator = Game.allSounds.values().iterator(); iterator.hasNext(); a.stop())
                a = (AudioClip)iterator.next();

            Model.setStopAllSounds(false);
        }
    }
}
