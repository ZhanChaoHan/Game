// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Rsrv.java

package com.crocro.wrp.utl;

import com.crocro.wrp.app.AppLoop;

public class Rsrv
{

    public Rsrv(AppLoop al)
    {
        mStrtTm = 0L;
        mPrms = null;
        mAL = al;
    }

    public Rsrv(AppLoop al, long wtMSec)
    {
        mStrtTm = 0L;
        mPrms = null;
        mAL = al;
        setTm(wtMSec);
    }

    public void setTm(long wtMSec)
    {
        mStrtTm = mAL.mTmNow + wtMSec;
    }

    public boolean act()
    {
        if(mAL.mTmNow < mStrtTm)
        {
            return false;
        } else
        {
            addAct();
            return true;
        }
    }

    public void addAct()
    {
    }

    public void setPrms(Object prms[])
    {
        mPrms = prms;
    }

    public void finish()
    {
        mAL = null;
        mPrms = null;
    }

    public AppLoop mAL;
    private long mStrtTm;
    public Object mPrms[];
}
