// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SoundLoader.java

package general;

import java.util.ArrayList;

// Referenced classes of package general:
//            Sound

public class SoundLoader
{

    public SoundLoader()
    {
    }

    public void loadSounds(ArrayList sounds)
    {
        sounds.add(new Sound("/sounds/ammo1.wav", false));
        sounds.add(new Sound("/sounds/ammo2.wav", false));
        sounds.add(new Sound("/sounds/ammo3.wav", false));
        sounds.add(new Sound("/sounds/ammo4.wav", false));
        sounds.add(new Sound("/sounds/ammo5.wav", false));
        sounds.add(new Sound("/sounds/hit1.wav", false));
        sounds.add(new Sound("/sounds/hit2.wav", false));
        sounds.add(new Sound("/sounds/hero_hit1.wav", false));
        sounds.add(new Sound("/sounds/destroy1.wav", false));
        sounds.add(new Sound("/sounds/destroy2.wav", false));
        sounds.add(new Sound("/sounds/destroy3.wav", false));
        sounds.add(new Sound("/sounds/small_destroy1.wav", false));
        sounds.add(new Sound("/sounds/big_destroy1.wav", false));
        sounds.add(new Sound("/sounds/bonus1.wav", false));
        sounds.add(new Sound("/sounds/energy1.wav", false));
        sounds.add(new Sound("/sounds/energy2.wav", false));
        sounds.add(new Sound("/sounds/jump1.wav", false));
        sounds.add(new Sound("/sounds/exit1.wav", false));
        sounds.add(new Sound("/sounds/intro2.wav", false));
        sounds.add(new Sound("/sounds/title1.wav", true));
        sounds.add(new Sound("/sounds/title3.wav", true));
        sounds.add(new Sound("/sounds/boss_music1.wav", true));
    }

    public static final String PATH = "/sounds/";
}
