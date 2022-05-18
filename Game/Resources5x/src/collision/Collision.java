// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Collision.java

package collision;

import gameobjects.Sprite;
import general.Model;

public interface Collision
{

    public abstract void checkCollision(Sprite sprite, Model model);

    public static final int NOBOUNCE = 0;
    public static final int BOUNCING1 = 1;
    public static final int BOUNCING2 = 2;
}
