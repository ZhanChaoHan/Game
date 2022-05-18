// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ScnTtl.java

package com.crocro.simWar.scn;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.mng.MngScn;
import com.crocro.wrp.scn.Scn;
import com.crocro.wrp.utl.UtlDw;
import com.crocro.wrp.wrp.WrpDw;

public class ScnTtl extends Scn
{

    public ScnTtl(AppLoopSimWar al)
    {
        super(al);
        mAL = null;
        IMG_ID_BG = AppLoopSimWar.IMG_ID_CMN_MAX + 1;
        IMG_ID_LOGO = AppLoopSimWar.IMG_ID_CMN_MAX + 2;
        mTmStrt = 0L;
    }

    public void init()
    {
        mAL = (AppLoopSimWar)super.mmAL;
        super.init();
    }

    public void doFlw(int flw)
    {
        if(flw == V.FLW_INIT)
            flwInit();
    }

    public void doAlwys(int flw)
    {
        if(flw == V.FLW_WAIT)
            alwysWait();
    }

    public void preAlwys()
    {
        int wScr = mAL.mMC.getScrW();
        int hScr = mAL.mMC.getScrH();
        int wLg = mAL.mWD.getBufW(IMG_ID_LOGO);
        int hLg = mAL.mWD.getBufH(IMG_ID_LOGO);
        UtlDw.dwTile(mAL.mWD, IMG_ID_BG, 0, 0, 0, 0, wScr, hScr);
        mAL.mWD.dwImg(IMG_ID_LOGO, (wScr - wLg) / 2, (hScr - hLg) / 2);
        if(mTmStrt == 0L)
            mTmStrt = mAL.mTmNow;
    }

    public void flwInit()
    {
        mAL.egV.mGmRstrt = false;
        mAL.initCmnImg();
        mAL.mWD.mkBuf(IMG_ID_BG, "ttl/bg_win.jpg");
        mAL.mWD.mkBuf(IMG_ID_LOGO, "ttl/logo_company.png");
        mAL.mMS.setNxtFlw(V.FLW_WAIT);
    }

    public void alwysWait()
    {
        doAlwysCmn();
        if(mAL.mTmNow >= mTmStrt + 1200L)
            mAL.mMS.setNxtScn(V.SCN_TTL_GM);
    }

    public void finish()
    {
        super.finish();
    }

    private AppLoopSimWar mAL;
    private final int IMG_ID_BG;
    private final int IMG_ID_LOGO;
    private final String IMG_FNM_BG = "ttl/bg_win.jpg";
    private final String IMG_FNM_LOGO = "ttl/logo_company.png";
    private final int TM_SPLASH = 1200;
    private long mTmStrt;
}
