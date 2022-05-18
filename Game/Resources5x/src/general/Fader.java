// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Fader.java

package general;


// Referenced classes of package general:
//            Model

public class Fader
{

    public Fader()
    {
    }

    public static void calculateFading()
    {
        if(fadingIn)
        {
            fadeCounter -= (float)Model.elapsedTime / (float)steps;
            if(fadeCounter < 0.0F)
                fadingIn = false;
        } else
        if(fadingOut)
        {
            fadeCounter -= (float)Model.elapsedTime / (float)steps;
            if(fadeCounter > 1.0F)
                fadingOut = false;
        }
        if(bossFading)
        {
            bossFadeCounter -= (float)Model.elapsedTime / (float)steps;
            if(bossFadeCounter < 0.0F)
                bossFading = false;
        }
        if(superAmmo)
        {
            superAmmoCounter -= (float)Model.elapsedTime / (float)steps;
            if(superAmmoCounter < 0.0F)
                superAmmo = false;
        }
    }

    public static void initFade(String type)
    {
        if(type.equals("in"))
        {
            fadingIn = true;
            fadingOut = false;
            fadeCounter = 1.0F;
            steps = 0x4c4b40;
        } else
        if(type.equals("out"))
        {
            fadingIn = false;
            fadingOut = true;
            fadeCounter = 0.0F;
            steps = 0x4c4b40;
        } else
        if(type.equals("boss"))
        {
            bossFading = true;
            bossFadeCounter = 0.8F;
            steps = 0x7270e0;
        } else
        if(type.equals("superAmmo"))
        {
            superAmmo = true;
            superAmmoCounter = 0.65F;
            steps = 0x7270e0;
        } else
        if(type.equals("megaAmmo"))
        {
            superAmmo = true;
            superAmmoCounter = 0.15F;
            steps = 0x53ec60;
        }
    }

    private static final int TIMEDIVIDER1 = 0x4c4b40;
    private static final int TIMEDIVIDER2 = 0x7270e0;
    private static final int TIMEDIVIDER3 = 0x53ec60;
    public static float fadeCounter = 0.0F;
    public static float bossFadeCounter = 0.0F;
    public static float superAmmoCounter = 0.0F;
    public static boolean fadingIn = false;
    public static boolean fadingOut = false;
    public static boolean bossFading = false;
    public static boolean superAmmo = false;
    private static int steps;

}
