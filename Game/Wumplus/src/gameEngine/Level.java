// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Level.java

package gameEngine;

import java.awt.Component;
import java.awt.Graphics2D;

// Referenced classes of package gameEngine:
//            ImageLoader

public abstract class Level
{

    public Level(Component component)
    {
        parent = component;
        imgLoader = new ImageLoader(component);
    }

    public abstract void loadData();

    public void clean()
    {
        imgLoader = null;
        parent = null;
    }

    public abstract void timerLoop();

    public abstract void render(Graphics2D graphics2d);

    public Component parent;
    protected ImageLoader imgLoader;
}
