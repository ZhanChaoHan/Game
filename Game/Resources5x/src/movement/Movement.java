// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Movement.java

package movement;

import gameobjects.Sprite;

public interface Movement
{

    public abstract void move(Sprite sprite);

    public abstract void init(Sprite sprite);
}
