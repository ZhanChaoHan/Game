// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WrpFile.java

package com.crocro.wrp.wrp;

import com.crocro.wrp.app.AppLoop;
import java.io.File;
import java.io.InputStream;

public interface WrpFile
{

    public abstract void setAppLoop(AppLoop apploop);

    public abstract File getDatF(String s);

    public abstract byte[] getR(String s);

    public abstract InputStream getRIS(String s);

    public abstract byte[] getFBArr(String s);

    public abstract InputStream getFIS(String s);

    public abstract boolean setFBArr(String s, byte abyte0[]);

    public abstract void setLngKey(String s);

    public abstract String getLngKey();

    public abstract void setDirBs(String s);

    public abstract void setLcncKey(String s);
}
