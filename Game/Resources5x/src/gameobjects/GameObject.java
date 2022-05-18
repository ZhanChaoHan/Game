// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GameObject.java

package gameobjects;


public class GameObject
{

    public GameObject(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        angle = 0.0F;
        order = 2;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getWidth()
    {
        return width;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getHeight()
    {
        return height;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public float getAngle()
    {
        return angle;
    }

    public void setAngle(float angle)
    {
        this.angle = angle;
    }

    public void setOrder(int order)
    {
        this.order = order;
    }

    public int getOrder()
    {
        return order;
    }

    public String getImage()
    {
        return img;
    }

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int order;
    protected String img;
    protected float angle;
}
