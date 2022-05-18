package com.crocro.simWar.app;

import com.crocro.simWar.gm.GmDt;
import com.crocro.simWar.mng.MngLayout;
import com.crocro.simWar.mng.MngSnd;
import com.crocro.simWar.scn.*;
import com.crocro.wrp.app.AppLoop;

public class AppLoopSimWar extends AppLoop
{

    public AppLoopSimWar()
    {
        mAppTtl = "SimWar";
        mAppVer = "1.2.0";
        egV = null;
        mV = egV = new V(this);
        egV.init();
        mML = new MngLayout(this);
        mMngSnd = new MngSnd(this);
    }

    public void initGD()
    {
        egV.gd.vwActLog = ((MngLayout)mML).l.vwActLog;
        egV.mGD = new GmDt(this);
        egV.mStgSz = mBsOpt.getI("stgSz");
        int stgSz = egV.mStgSz;
        if(egV.gda.stgEnbl == null)
        {
            stgSz = mBsOpt.getI("stgSz");
            egV.gda.stgEnbl = new int[stgSz];
            for(int i = 0; i < stgSz; i++)
                egV.gda.stgEnbl[i] = mBsOpt.getI((new StringBuilder("stgEnbl")).append(i + 1).toString());

        }
        if(egV.gda.stgWinTm == null)
            egV.gda.stgWinTm = new int[stgSz];
        if(egV.gda.stgWinScr == null)
            egV.gda.stgWinScr = new int[stgSz];
        if(egV.gda.stgWinNo == null)
            egV.gda.stgWinNo = new int[stgSz];
    }

    public void rldUnqScn(int scnNew)
    {
        if(scnNew == V.SCN_TTL_GM)
            mScn = new ScnTtlGm(this);
        else
        if(scnNew == V.SCN_SEL_STG)
            mScn = new ScnSelStg(this);
        else
        if(scnNew == V.SCN_BFR_GM)
            mScn = new ScnBfrGm(this);
        else
        if(scnNew == V.SCN_STG_STRT)
            mScn = new ScnStgStrt(this);
        else
        if(scnNew == V.SCN_GM)
            mScn = new ScnGm(this);
        else
        if(scnNew == V.SCN_STG_CLR)
            mScn = new ScnStgClr(this);
        else
        if(scnNew == V.SCN_WIN_GM)
            mScn = new ScnWinGm(this);
        else
            mScn = new ScnTtl(this);
        mScn.init();
    }

    public String mAppTtl;
    public String mAppVer;
    public V egV;
}
