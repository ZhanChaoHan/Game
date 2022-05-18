// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Sound.java

package general;


public class Sound
{

    public Sound(String filename, boolean looping)
    {
        this.filename = filename;
        this.looping = looping;
    }

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public boolean isLooping()
    {
        return looping;
    }

    public void setLooping(boolean looping)
    {
        this.looping = looping;
    }

    private String filename;
    private boolean looping;
}
