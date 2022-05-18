// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AnimationSet.java

package mm;

import java.awt.image.BufferedImage;
import java.io.PrintStream;
import java.util.*;

// Referenced classes of package mm:
//            ImageManager, Globals, SoundManager

public class AnimationSet
{
    public static class Animation
    {

        public BufferedImage getCurrentImage()
        {
            return ImageManager.getImage((new StringBuilder(String.valueOf(prefix))).append(currentFrame).append(".png").toString());
        }

        public void advance()
        {
            int now = Globals.getCurrentTurn();
            if(now - lastAdvance >= delay && currentFrame < nFrames - 1)
            {
                currentFrame++;
                lastAdvance = now;
                if(soundPrefix.length() > 0)
                    SoundManager.playSound((new StringBuilder(String.valueOf(soundPrefix))).append(currentFrame).append(".wav").toString());
            }
        }

        public boolean isDone()
        {
            int now = Globals.getCurrentTurn();
            return now - lastAdvance >= Math.max(delay, endDelay) && currentFrame >= nFrames - 1;
        }

        public int getPriority()
        {
            return priority;
        }

        public void restart()
        {
            currentFrame = 0;
            lastAdvance = Globals.getCurrentTurn();
            if(soundPrefix.length() > 0)
                SoundManager.playSound((new StringBuilder(String.valueOf(soundPrefix))).append(currentFrame).append(".wav").toString());
        }

        public void preloadAll()
        {
            for(int i = 0; i < nFrames; i++)
            {
                ImageManager.preloadImage((new StringBuilder(String.valueOf(prefix))).append(i).append(".png").toString());
                if(soundPrefix.length() > 0)
                    SoundManager.preloadSound((new StringBuilder(String.valueOf(soundPrefix))).append(i).append(".wav").toString());
            }

        }

        private String prefix;
        private String soundPrefix;
        private int nFrames;
        private int delay;
        private int endDelay;
        private int priority;
        private int currentFrame;
        private int lastAdvance;

        public Animation(String name, String sound, int num, int pause, int endPause, int prioritee)
        {
            prefix = name;
            soundPrefix = sound;
            nFrames = num;
            delay = pause;
            endDelay = endPause;
            priority = prioritee;
            currentFrame = 0;
        }
    }


    public AnimationSet(String defaultNom, Animation defaultAnimation)
    {
        animations = new HashMap();
        defaultName = defaultNom;
        currentAnimation = defaultName;
        animations.put(defaultName, defaultAnimation);
    }

    public void addAnimation(String name, Animation animation)
    {
        animations.put(name, animation);
    }

    public void setAnimation(String name)
    {
        Animation newAnimation = (Animation)animations.get(name);
        if(newAnimation == null)
        {
            System.err.println((new StringBuilder("Animation \"")).append(name).append("\" not found!").toString());
            return;
        }
        Animation current = (Animation)animations.get(currentAnimation);
        if(current == null || current.isDone())
        {
            startAnimation(name);
            return;
        }
        if(newAnimation.getPriority() > current.getPriority())
        {
            startAnimation(name);
            return;
        } else
        {
            return;
        }
    }

    private void startAnimation(String name)
    {
        currentAnimation = name;
        ((Animation)animations.get(name)).restart();
    }

    public void tick()
    {
        Animation current = (Animation)animations.get(currentAnimation);
        current.advance();
        if(current.isDone())
            startAnimation(defaultName);
    }

    public void preloadAll()
    {
        for(Iterator i = animations.values().iterator(); i.hasNext(); ((Animation)i.next()).preloadAll());
    }

    public BufferedImage getCurrentImage()
    {
        return ((Animation)animations.get(currentAnimation)).getCurrentImage();
    }

    public void finishAnimation()
    {
        currentAnimation = defaultName;
        ((Animation)animations.get(currentAnimation)).restart();
    }

    private HashMap animations;
    private String currentAnimation;
    private String defaultName;
}
