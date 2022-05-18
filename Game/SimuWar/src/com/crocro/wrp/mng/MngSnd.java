// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MngSnd.java

package com.crocro.wrp.mng;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.app.V;
import com.crocro.wrp.wrp.WrpSnd;

// Referenced classes of package com.crocro.wrp.mng:
//            MngOpt

public class MngSnd
{

    public MngSnd(AppLoop al)
    {
        mAL = null;
        mWS = null;
        mFnmBgmNow = "";
        gSeIdMax = 0;
        mAL = al;
    }

    public void initSE()
    {
        mWS = mAL.mWS;
        for(int i = 0; i < gSeIdMax; i++)
        {
            for(int j = 0; j < mSePrms[i][1]; j++)
            {
                int id = mSePrms[i][0] + j;
                mWS.ldSnd(id, mAL.mBsOpt.getS(mSeNms[i]));
                mWS.setPrm(id, 0, 1);
                mWS.setPrm(id, 1, 0);
                mWS.setPrm(id, 2, mAL.mV.gd.sndVol);
            }

        }

    }

    public void setSePrms(int arg[][])
    {
        mSePrms = arg;
    }

    public void setSeNms(String arg[])
    {
        mSeNms = arg;
    }

    public void setSeIdMax(int arg)
    {
        gSeIdMax = arg;
    }

    public void playBgm(String key)
    {
        try
        {
            if(!mFnmBgmNow.equals(mAL.mBsOpt.getS(key)))
            {
                mFnmBgmNow = mAL.mBsOpt.getS(key);
                mWS.chng(0, mFnmBgmNow);
                mWS.setPrm(0, 0, 0);
                mWS.setPrm(0, 1, 1);
                mWS.play(0);
            }
        }
        catch(Exception ex) {
        	ex.printStackTrace();
}
    }

    public void stopBgm()
    {
        if(mFnmBgmNow.length() > 0 && mWS.isRunning(0))
            mWS.stop(0);
    }

    public void restrtBgm()
    {
        if(mFnmBgmNow.length() > 0 && !mWS.isRunning(0))
            mWS.play(0);
    }

    public void playSE(int seId)
    {
        int idStrt = mSePrms[seId][0];
        int idSz = mSePrms[seId][1];
        for(int i = 0; i < idSz; i++)
        {
            int idUse = idStrt + i;
            if(!mWS.isRunning(idUse))
                mWS.play(idUse);
        }

    }

    public void chngVol(int vol)
    {
        mWS.chngVol(vol);
    }

    private AppLoop mAL;
    private WrpSnd mWS;
    private final int S_ID_BGM = 0;
    private String mFnmBgmNow;
    protected static final int SE_ID_STRT = 1;
    protected static final int SE_ID_SZ = 3;
    private int gSeIdMax;
    private static final int SE_PRM_STRT = 0;
    private static final int SE_PRM_SZ = 1;
    private int mSePrms[][] = {
        {
            1, 3
        }
    };
    private String mSeNms[] = {
        "seDmg"
    };
}
