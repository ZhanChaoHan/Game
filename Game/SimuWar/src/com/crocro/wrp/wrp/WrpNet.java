// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WrpNet.java

package com.crocro.wrp.wrp;

import com.crocro.wrp.app.AppLoop;

public interface WrpNet
{

    public abstract void setAppLoop(AppLoop apploop);

    public abstract String getHttp(String s);

    public abstract String getHttp(String s, int i, int j);

    public abstract void openBrwsr(String s);
}
