// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Projectile.java


public class Projectile
    implements Viewer
{

    public void setAngle(double NewAngle)
    {
        angle = NewAngle;
        if(angle < 0.0D)
            angle += 360D;
        if(angle > 360D)
            angle -= 360D;
        gx = Math.sin(((angle + 90D) * 3.1415926535897931D) / 180D) * speed;
        gy = Math.cos(((angle + 90D) * 3.1415926535897931D) / 180D) * speed;
        radians = angle * -0.017453292519943295D;
    }

    public void setX(double newX)
    {
        x = newX;
        prevX = newX;
    }

    public void setY(double newY)
    {
        y = newY;
        prevY = newY;
    }

    public void setGx(double newGx)
    {
        gx = newGx;
        angle = Math.atan2(gy * 100D, gx * 100D) * -57.295779513082323D;
        radians = angle * -0.017453292519943295D;
    }

    public void setGy(double newGy)
    {
        gy = newGy;
        angle = Math.atan2(gy * 100D, gx * 100D) * -57.295779513082323D;
        radians = angle * -0.017453292519943295D;
    }

    public void setGravity(double gravity)
    {
        grav = gravity;
    }

    public void setSpeed(double newSpeed)
    {
        speed = newSpeed;
    }

    public void advance()
    {
        prevX = x;
        prevY = y;
        gy = gy + grav;
        angle = Math.atan2(gy, gx) * -57.295779513082323D;
        radians = angle * -0.017453292519943295D;
        x += gx;
        y += gy;
    }

    public Projectile()
    {
        speed = 1.0D;
        grav = 0.025000000000000001D;
        damRad = 80;
        alt = null;
    }

    public Projectile(double newX, double newY)
    {
        speed = 1.0D;
        grav = 0.025000000000000001D;
        damRad = 80;
        alt = null;
        prevX = newX;
        prevY = newY;
        x = newX;
        y = newY;
    }

    public int getX()
    {
        return (int)x;
    }

    public int getY()
    {
        return (int)y;
    }

    public boolean isCamera()
    {
        return false;
    }

    static final double DEGtoRAD = -0.017453292519943295D;
    static final double RADtoDEG = -57.295779513082323D;
    public double prevX;
    public double prevY;
    public double x;
    public double y;
    public double gx;
    public double gy;
    public double angle;
    public double radians;
    public double speed;
    public double grav;
    public int damRad;
    Chara alt;
}
