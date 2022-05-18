// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WrpEnv.java

package com.crocro.wrp.wrp;

import com.crocro.wrp.utl.Actr;

public interface WrpEnv
{

    public abstract void dbgOut(String s);

    public abstract void dbgErr(String s);

    public abstract void dbgErr(Exception exception, String s);

    public abstract void setTtl(String s);

    public abstract boolean chkWaitDlg();

    public abstract void inputDlg(String s, String s1, Actr actr);

    public abstract void messageDlg(String s, String s1, Actr actr);

    public abstract void rootBck();

    public abstract void usrExit();
}
