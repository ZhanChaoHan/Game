package com.crocro.simWar.clckbl;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.gm.GmDtUntF;
import com.crocro.simWar.scn.ScnGm;
import com.crocro.wrp.clckbl.Clckbl;
import com.crocro.wrp.utl.UtlTool;
import com.crocro.wrp.wrp.WrpDw;

public class GmEfDth extends Clckbl
    implements GmDtUntF
{

    private AppLoopSimWar mAL;
    private ScnGm mSG;
    private WrpDw mWDIn;
    public static final int IMG_ID_THIS = 0;
    public int mImgId;
    private boolean mTypSd;
    private long mTmStrt;
    public static final boolean TYP_SD_MY = true;
    public static final boolean TYP_SD_ENE = false;
    private static final int EF_TM_BFR = 200;
    private static final int EF_TM_MDL = 300;
    private static final int EF_TM_AFTR = 200;
    private static final int EF_TM = 700;
    private static final int FS_BFR = 0;
    private static final int FS_MDLL = 1;
    private static final int FS_AFTR = 2;
    private int mFs;
    private int mElpsFs;
    
    public GmEfDth(AppLoopSimWar al, ScnGm sg, boolean typSd, int uCX, int uCY, int msec)
    {
        super(al, 0, 0, 0, 0, 1, 0);
        mFs = 0;
        mElpsFs = 0;
        mAL = (AppLoopSimWar)super.mAL;
        mSG = sg;
        mTypSd = typSd;
        mTmStrt = mSG.mTmGm;
        mImgId = ScnGm.IMG_ID_EF_DTH_MY;
        if(!mTypSd)
            mImgId = ScnGm.IMG_ID_EF_DTH_ENE;
        mW = mWD.getBufW(mImgId);
        mH = mWD.getBufH(mImgId);
        mWDIn = mAL.mWD.getNew();
        mWDIn.mkBuf(0, mW, mH);
        mWDIn.tgtBuf(0);
        mWDIn.dwImg(mWD, mImgId, 0, 0);
        com.crocro.wrp.wrp.WrpDw.StrFntOpt sf = mAL.mML.mStrFntDflt;
        mWDIn.dwStr((new StringBuilder(String.valueOf(UtlTool.dgt((double)msec * 0.001D, 2)))).append("sec").toString(), 0, (mH - sf.lnH) / 2, mW, sf, null, 1);
        int x = uCX - mW / 2;
        int y = uCY - mH / 2;
        setPos(x, y);
    }

    public void doAlwys()
    {
        doAlwysAdd();
        super.doAlwys();
    }

    public void doAlwysAdd()
    {
        int elps = (int)(mSG.mTmGm - mTmStrt);
        if(elps >= 700)
        {
            rsrvRmv();
            return;
        }
        if(elps >= 500)
        {
            mFs = 2;
            mElpsFs = elps - 500;
        } else
        if(elps >= 200)
        {
            mFs = 1;
            mElpsFs = elps - 200;
        } else
        {
            mElpsFs = elps;
        }
    }

    public void dwAlwys()
    {
        if(mAL.mML.l.specLow == 1 && (mX + mW * 2 < 0 || mX - mW >= mAL.mMC.getScrW() || mY + mH * 2 < 0 || mY - mH >= mAL.mMC.getScrH()))
            return;
        int x2;
        int x1 = x2 = mX;
        int y2;
        int y1 = y2 = mY;
        if(mFs == 0)
        {
            int wScr = mAL.mMC.getScrW();
            int wMax = mX < wScr / 2 ? wScr - mX : mX;
            int wMv = (wMax * mElpsFs) / 200;
            x1 -= wMax - wMv;
            x2 += wMax - wMv;
        } else
        if(mFs == 2)
        {
            int hScr = mAL.mMC.getScrH();
            int hMax = mY < hScr / 2 ? hScr - mY : mY;
            int hMv = (hMax * mElpsFs) / 200;
            y1 += hMv;
            y2 -= hMv;
        }
        mWD.dwImg(mWDIn, 0, x1, y1);
        mWD.dwImg(mWDIn, 0, x2, y2);
    }

    public void finish()
    {
        super.finish();
        mSG = null;
        if(mWDIn != null)
            mWDIn.flshBuf(0);
        mWDIn = null;
    }

}
