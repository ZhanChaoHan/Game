// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GmTmr.java

package com.crocro.simWar.clckbl;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V;
import com.crocro.simWar.gm.*;
import com.crocro.simWar.scn.ScnGm;
import com.crocro.wrp.clckbl.Drw;
import com.crocro.wrp.mng.MngLayout;
import com.crocro.wrp.mng.MngLng;
import com.crocro.wrp.utl.UtlDw;
import com.crocro.wrp.wrp.WrpDw;

public class GmTmr extends Drw
    implements GmDtUntF
{

    public GmTmr(AppLoopSimWar al, int x, int y, ScnGm sg)
    {
        super(al, x, y, 0, 0, 0);
        mImgNmbrH = 30;
        mEneSz = 0;
        mTmMax = 0;
        mSB = new StringBuilder();
        mLstTm = 0;
        mAL = (AppLoopSimWar)super.mAL;
        mSG = sg;
        mGD = mAL.egV.mGD;
        mGDUnt = mSG.mGDUnt;
        mImgId = ScnGm.IMG_ID_NMBR;
        mUInfFnt = mAL.mML.mStrFntSml;
        mUInfFnt.lnH = mUInfFnt.fntSz + 2;
        mH = mImgNmbrH = mWD.getBufH(mImgId);
        for(int i = 32; i < 132; i++)
            if(mGDUnt.getEnbl(i) == 1)
                mEneSz++;

        mTmMax = 10 * mEneSz;
        if(mTmMax <= 0)
            mTmMax = 10;
    }

    public void dwAlwys()
    {
        int tm = (int)mSG.mTmGm;
        if(mSG.mGTScr.mWinLose != 0)
            tm = mLstTm;
        else
            mLstTm = tm;
        if(mSG.mUSelTyp == 2)
            mWD.setTrns((int)(100D - 100D * Math.sin(((double)(mAL.mTmNow % 1200L) / 1200D) * 2D * 3.1415926535897931D)));
        UtlDw.dwNmbr(mWD, mImgId, (double)tm * 0.001D, mX, mY, 1);
        if(mSG.mUSelTyp == 2)
            mWD.setTrns(100);
        int yBs = mY + mImgNmbrH + 4;
        int wBs = mSG.mBtnWSum;
        mWD.setCol(204, 204, 204);
        mWD.flRct(mX, yBs, wBs, 8);
        int elpsW = (int)(((long)wBs * (mSG.mTmGm / 1000L)) / (long)mTmMax);
        if(elpsW >= wBs)
            elpsW = wBs;
        mWD.setCol(99, 176, 238);
        mWD.flRct(mX, yBs, elpsW, 8);
        mWD.setCol(237, 32, 32);
        for(int i = 1; i < mEneSz; i++)
            if(mEneSz < 16 || i % 2 != 0)
                mWD.dwLn(mX + (i * wBs) / mEneSz, yBs, mX + (i * wBs) / mEneSz, yBs + 8);

        mWD.setCol(68, 115, 176);
        mWD.dwRct(mX, yBs, wBs, 8);
        if(mAL.egV.gd.vwActLog == 1 && mSG.mSelUntId != -1 && mSG.mTmGm <= mSG.mSelUntTm + 2000L)
        {
            yBs += 14;
            mWD.dwStr(getUInf(), mX, yBs, 999, mUInfFnt, WrpDw.STR_FRM_DFLT);
        }
    }

    public String getUInf()
    {
        int uId = mSG.mSelUntId;
        int uTyp = mGDUnt.getTyp(uId);
        int sklId = mGDUnt.getSkl(uId);
        String nmU = mGD.mUntS[uTyp][1];
        String nmS = mGD.mSklS[sklId][2];
        int hpNow = mGDUnt.getHp(uId);
        int hpMax = mGD.mUntI[uTyp][GmDt.UNT_I_HP];
        int dfSum = (int)((double)(mGD.mUntI[uTyp][GmDt.UNT_I_DF] * mGD.mSklI[sklId][GmDt.SKL_I_DF]) * 0.01D);
        int mvSum = mGD.mUntI[uTyp][GmDt.UNT_I_MV];
        int atSum = mGD.mSklI[sklId][GmDt.SKL_I_AT];
        int rng = mGD.mSklI[sklId][GmDt.SKL_I_RNG];
        int scp = mGD.mSklI[sklId][GmDt.SKL_I_SCP];
        MngLng l = mAL.mMngLng;
        mSB.setLength(0);
        l.f(mSB, "untInf_Nm", new Object[] {
            nmU, nmS
        });
        l.f(mSB, "untInf_HpDfMv", new Object[] {
            Integer.valueOf(hpNow), Integer.valueOf(hpMax), Integer.valueOf(dfSum), Integer.valueOf(mvSum)
        });
        l.f(mSB, "untInf_AtRngScp", new Object[] {
            Integer.valueOf(atSum), Integer.valueOf(rng), Integer.valueOf(scp)
        });
        int exAt = mGDUnt.getUS(uId, US_EX_AT_PW);
        int exDf = mGDUnt.getUS(uId, US_EX_DF_PW);
        int exMv = mGDUnt.getUS(uId, US_EX_MV_PW);
        int exFly = mGD.mSklI[sklId][GmDt.SKL_I_ADD_FLY];
        int exPss = mGD.mSklI[sklId][GmDt.SKL_I_ADD_PSS];
        if(exAt != 0 || exDf != 0 || exMv != 0 || exFly != 0 || exPss != 0)
        {
            mSB.append(l.getS("untInf_Stts"));
            if(exAt != 0)
                l.f(mSB, "untInf_ExAt", new Object[] {
                    Integer.valueOf(exAt)
                });
            if(exDf != 0)
                l.f(mSB, "untInf_ExDf", new Object[] {
                    Integer.valueOf(exDf)
                });
            if(exMv != 0)
                l.f(mSB, "untInf_ExMv", new Object[] {
                    Integer.valueOf(exMv)
                });
            if(exFly > 0)
                mSB.append(l.getS("untInf_ExFlyAdd"));
            if(exFly < 0)
                mSB.append(l.getS("untInf_ExFlyLst"));
            if(exPss > 0)
                mSB.append(l.getS("untInf_ExPssAdd"));
            if(exPss < 0)
                mSB.append(l.getS("untInf_ExPssLst"));
        }
        return mSB.toString();
    }

    public void finish()
    {
        super.finish();
        mSG = null;
        mGD = null;
        mGDUnt = null;
        if(mSB != null)
            mSB.setLength(0);
        mSB = null;
    }

    private AppLoopSimWar mAL;
    private ScnGm mSG;
    private GmDt mGD;
    private GmDtUnt mGDUnt;
    private int mImgNmbrH;
    private static final int GRP_MRGN = 4;
    private static final int GRP_H = 8;
    private int mEneSz;
    private int mTmMax;
    private com.crocro.wrp.wrp.WrpDw.StrFntOpt mUInfFnt;
    private static final int UINF_MRGN = 6;
    private static final int UINF_VW_TM = 2000;
    StringBuilder mSB;
    private int mLstTm;
}
