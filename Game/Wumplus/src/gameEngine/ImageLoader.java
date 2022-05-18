// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImageLoader.java

package gameEngine;

import java.awt.*;

public class ImageLoader
{

    public ImageLoader(Component component)
    {
        parent = component;
        reset();
    }

    public void reset()
    {
        mt = new MediaTracker(parent);
        nextId = 0;
    }

    public void addImage(Image image)
    {
        mt.addImage(image, nextId);
        nextId++;
    }

    public void waitForAll()
    {
        try
        {
            mt.waitForAll();
        }
        catch(InterruptedException interruptedexception)
        {
            Thread.currentThread().interrupt();
        }
        reset();
    }

    private MediaTracker mt;
    private int nextId;
    private Component parent;
}
