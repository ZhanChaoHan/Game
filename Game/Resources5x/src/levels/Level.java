// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Level.java

package levels;


interface Level
{

    public abstract Object[] readTiles();

    public abstract Object[] readEnemies();

    public abstract Object[] readItems();

    public abstract int getGameWidth();

    public abstract int getGameHeight();
}
