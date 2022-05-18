// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Text.java

package gameobjects;

import movement.Movement;

// Referenced classes of package gameobjects:
//            Sprite

public class Text extends Sprite
{

    public Text(String text, int x, int y, int s, int vel, float r, float g, 
            float b, float a, Movement m)
    {
        super(null, x, y, 400, 400, s, 0.0D, 0, 0, 0.0D);
        this.vel = vel;
        this.text = text;
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        movement = m;
        this.x = (x + 20) - (text.length() - 1) * 6;
    }

    public String getText()
    {
        return text;
    }

    public float getR()
    {
        return r;
    }

    public void setR(float r)
    {
        this.r = r;
    }

    public float getG()
    {
        return g;
    }

    public void setG(float g)
    {
        this.g = g;
    }

    public float getB()
    {
        return b;
    }

    public void setB(float b)
    {
        this.b = b;
    }

    public float getA()
    {
        return a;
    }

    public void setA(float a)
    {
        this.a = a;
    }

    private final int TEXTPOS = 20;
    public static final int TEXTSPEED1 = 7;
    public static final int TEXTSPEED2 = 4;
    public static final int TEXTDIST = 90;
    private String text;
    private float r;
    private float g;
    private float b;
    private float a;
}
