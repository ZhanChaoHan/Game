// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GmEfWpn.java

package com.crocro.simWar.clckbl;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V;
import com.crocro.simWar.gm.*;
import com.crocro.simWar.scn.ScnGm;
import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.clckbl.Clckbl;
import com.crocro.wrp.mng.*;
import com.crocro.wrp.utl.UtlTool;
import com.crocro.wrp.wrp.WrpDw;
import java.util.Random;

public class GmEfWpn extends Clckbl
    implements GmDtUntF
{

    public GmEfWpn(AppLoopSimWar al, ScnGm sg, GmToolSkl gmSkl)
    {
        super(al, 0, 0, 0, 0, 1, 0);
        mFs = 0;
        mElpsFs = 0;
        mHitSeEnd = false;
        mAL = (AppLoopSimWar)super.mAL;
        mSG = sg;
        mGDUnt = mSG.mGDUnt;
        mGTSkl = gmSkl;
        mGIWpn = mSG.mGIWpn;
        mXStrt = mGDUnt.getUS(mGTSkl.mUId, US_MV_X_NOW);
        mYStrt = mGDUnt.getUS(mGTSkl.mUId, US_MV_Y_NOW);
        mXGoal = mXStrt;
        mYGoal = mYStrt;
        mTmStrt = mSG.mTmGm;
        if(mGTSkl.mSkl != -1)
        {
            mBufFly = mGIWpn.getId(mGTSkl.mSkl, 0);
            mBufHit = mGIWpn.getId(mGTSkl.mSkl, 1);
            mGIWpn.ldImg(mBufFly);
            mGIWpn.ldImg(mBufHit);
        }
        if(mBufFly >= 0)
        {
            mW = mGIWpn.mWDIn.getBufW(mGIWpn.mImgIds[mBufFly][0]);
            mH = mGIWpn.mWDIn.getBufH(mGIWpn.mImgIds[mBufFly][0]);
        } else
        if(mBufHit >= 0)
        {
            mW = mGIWpn.mWDIn.getBufW(mGIWpn.mImgIds[mBufHit][0]);
            mH = mGIWpn.mWDIn.getBufW(mGIWpn.mImgIds[mBufHit][0]);
        } else
        {
            mW = 1;
            mH = 1;
        }
        int x = mXStrt - mW / 2;
        int y = mYStrt - mH / 2;
        setPos(x, y);
    }

    public void doAlwys()
    {
        if(!mGTSkl.mIsFnsh)
        {
            doAlwysAdd();
            super.doAlwys();
        } else
        {
            rsrvRmv();
        }
    }

    public void doAlwysAdd()
    {
        int elps = (int)(mSG.mTmGm - mTmStrt);
        if(elps >= 1500)
        {
            rsrvRmv();
            return;
        }
        if(elps >= 1000)
        {
            mFs = 1;
            mElpsFs = elps - 1000;
            if(!mHitSeEnd)
            {
                mHitSeEnd = true;
                int skl = mGTSkl.mSkl;
                int at = mAL.egV.mGD.mSklI[skl][GmDt.SKL_I_AT];
                if(at > 0)
                    mAL.mMngSnd.playSE(0);
                else
                if(at < 0)
                    mAL.mMngSnd.playSE(1);
                else
                    mAL.mMngSnd.playSE(2);
            }
        } else
        {
            mElpsFs = elps;
        }
        if(mFs == 0 && mGTSkl.mTgtId != -1)
        {
            mXGoal = mGDUnt.getUS(mGTSkl.mTgtId, US_MV_X_NOW);
            mYGoal = mGDUnt.getUS(mGTSkl.mTgtId, US_MV_Y_NOW);
            double rt = ((double)mElpsFs * 1.0D) / 1000D;
            int xNew = (int)((double)(mXGoal - mXStrt) * rt + (double)mXStrt);
            int yNew = (int)((double)(mYGoal - mYStrt) * rt + (double)mYStrt);
            setPos(xNew - mW / 2, yNew - mH / 2);
        }
        if(mFs == 1 && mGTSkl.mTgtId != -1)
        {
            mXGoal = mGDUnt.getUS(mGTSkl.mTgtId, US_MV_X_NOW);
            mYGoal = mGDUnt.getUS(mGTSkl.mTgtId, US_MV_Y_NOW);
            setPos(mXGoal - mW / 2, mYGoal - mH / 2);
        }
    }

    public void dwAlwys()
    {
        if(!mGTSkl.mIsFnsh)
        {
            if(mAL.mML.l.specLow == 1 && (mX + mW * 2 < 0 || mX - mW >= mAL.mMC.getScrW() || mY + mH * 2 < 0 || mY - mH >= mAL.mMC.getScrH()))
                return;
            if(mFs == 0)
                dwFly();
            else
            if(mFs == 1)
            {
                for(int i = 0; i < mGTSkl.mTgtArrSz; i++)
                    dwHit(mGTSkl.mTgtArr[i]);

            }
        }
    }

    public void dwFly()
    {
        if(mBufFly < 0)
            return;
        int bufIds[] = mGIWpn.mImgIds[mBufFly];
        int bufId = bufIds[0];
        if(bufIds.length > 1)
        {
            int anm = (mElpsFs / 100) % bufIds.length;
            bufId = bufIds[anm];
        }
        int skl = mGTSkl.mSkl;
        int sklI[][] = mAL.egV.mGD.mSklI;
        int scp = sklI[skl][GmDt.SKL_I_SCP];
        if(scp > 0)
        {
            int xCntr = mX + mW / 2;
            int yCntr = mY + mH / 2;
            mWD.useCol(WrpDw.COL_GRAY);
            mWD.setStrk(3);
            int w = scp + (scp * 3) / 30;
            int h = scp - (scp * 3) / 30;
            mWD.setRtt((mElpsFs * 360 * 4) / 1000, w / 2, h / 2);
            mWD.dwOvl(xCntr - w / 2, yCntr - h / 2, w, h);
            mWD.setStrk(1);
            mWD.setRtt(0);
        }
        boolean eDirMv = false;
        if(sklI[skl][GmDt.SKL_I_FLY_DIR_MV] == 1)
            eDirMv = true;
        boolean eRt = false;
        if(sklI[skl][GmDt.SKL_I_FLY_RT] == 1)
            eRt = true;
        boolean eBlnk = false;
        if(sklI[skl][GmDt.SKL_I_FLY_BLNK] == 1)
            eBlnk = true;
        boolean eSprd = false;
        if(sklI[skl][GmDt.SKL_I_FLY_SPRD] == 1)
            eSprd = true;
        boolean eHrzntl = false;
        if(sklI[skl][GmDt.SKL_I_FLY_HRZNTL] == 1)
            eHrzntl = true;
        int degBs = 0;
        int degRt = 0;
        int trns = 100;
        if(eDirMv || eHrzntl)
            degBs = UtlTool.clcDegTop(mXStrt, mYStrt, mXGoal, mYGoal);
        if(eRt)
            degRt = (720 * mElpsFs) / 1000;
        if(eBlnk)
        {
            int angl = (int)(100D * Math.cos(((double)mElpsFs * 2D * 3.1415926535897931D) / 1000D));
            trns = angl <= 0 ? -angl : angl;
        }
        mWD.setRtt(degBs + degRt, mW / 2, mH / 2);
        mWD.setTrns(trns);
        if(eSprd)
        {
            for(int i = 0; i < 3; i++)
                mWD.dwImg(mGIWpn.mWDIn, bufId, (mX + AppLoop.RNDM.nextInt(mW)) - mW / 2, (mY + AppLoop.RNDM.nextInt(mH)) - mH / 2);

        } else
        if(eHrzntl)
        {
            for(int i = 0; i < 3; i++)
            {
                int x = UtlTool.getDegX(degBs, (mW * i) / 3 - mW / 2);
                int y = UtlTool.getDegY(degBs, (mW * i) / 3 - mW / 2);
                mWD.dwImg(mGIWpn.mWDIn, bufId, mX + x, mY + y);
            }

        } else
        {
            mWD.dwImg(mGIWpn.mWDIn, bufId, mX, mY);
        }
        mWD.setRtt(0);
        mWD.setTrns(100);
    }

    public void dwHit(int scpTgtId)
    {
        if(mBufHit < 0)
            return;
        int xScpTgt = mGDUnt.getUS(scpTgtId, US_MV_X_NOW);
        int yScpTgt = mGDUnt.getUS(scpTgtId, US_MV_Y_NOW);
        int xDw = (xScpTgt + mAL.mMC.mOrgnX) - mW / 2;
        int yDw = (yScpTgt + mAL.mMC.mOrgnY) - mH / 2;
        mWD.useCol(WrpDw.COL_RED);
        mWD.setStrk(4);
        mWD.dwOvl(xDw, yDw, mW, mH);
        mWD.setStrk(1);
        int bufIds[] = mGIWpn.mImgIds[mBufHit];
        int bufId = bufIds[0];
        if(bufIds.length > 1)
        {
            int anm = (bufIds.length * mElpsFs) / 500;
            bufId = bufIds[anm];
        }
        int sklI[] = mAL.egV.mGD.mSklI[mGTSkl.mSkl];
        int at = sklI[GmDt.SKL_I_AT];
        if(at > 0)
            mWD.useCol(WrpDw.COL_RED);
        else
        if(at < 0)
            mWD.useCol(WrpDw.COL_YELLOW);
        else
            mWD.useCol(WrpDw.COL_BLUE);
        int anmEfTyp = (120 * mElpsFs) / 500;
        mWD.setTrns(50 - (50 * mElpsFs) / 500);
        mWD.flOvl((xScpTgt + mAL.mMC.mOrgnX) - anmEfTyp, (yScpTgt + mAL.mMC.mOrgnY) - anmEfTyp, anmEfTyp * 2, anmEfTyp * 2);
        mWD.setTrns(100);
        boolean eSprd = sklI[GmDt.SKL_I_HIT_SPRD] == 1;
        boolean e4Way = sklI[GmDt.SKL_I_HIT_4WAY] == 1;
        boolean eRt = sklI[GmDt.SKL_I_HIT_RT] == 1;
        boolean ePllr = sklI[GmDt.SKL_I_HIT_PLLR] == 1;
        boolean eFOut = sklI[GmDt.SKL_I_HIT_FOUT] == 1;
        boolean eFInOut = sklI[GmDt.SKL_I_HIT_FINOUT] == 1;
        boolean eBck = sklI[GmDt.SKL_I_HIT_BCK] == 1;
        boolean eDirMv = sklI[GmDt.SKL_I_FLY_DIR_MV] == 1;
        int degBs = 0;
        int degRt = 0;
        int trns = 100;
        int xRvsn = 0;
        int yRvsn = 0;
        if(eBck || eDirMv)
            degBs = UtlTool.clcDegTop(mXStrt, mYStrt, xScpTgt, yScpTgt);
        if(eRt)
            degRt = (720 * mElpsFs) / 1000;
        if(eFOut)
            trns = (100 * mElpsFs) / 500;
        if(eFInOut)
            trns = (int)(100D * Math.sin(((double)mElpsFs * 3.1415926535897931D) / 500D));
        if(eBck)
        {
            int xNow = mGDUnt.getUS(mGTSkl.mUId, US_MV_X_NOW);
            int yNow = mGDUnt.getUS(mGTSkl.mUId, US_MV_Y_NOW);
            xRvsn = ((xNow - xScpTgt) * mElpsFs) / 500;
            yRvsn = ((yNow - yScpTgt) * mElpsFs) / 500;
        }
        mWD.setRtt(degBs + degRt, mW / 2, mH / 2);
        mWD.setTrns(trns);
        if(eSprd || e4Way)
        {
            int szSprd = eSprd ? 3 : 1;
            int sz4Way = e4Way ? 3 : 1;
            int x4Way = 0;
            int y4Way = 0;
            int mvDot = (int)((double)(mW / 2) * Math.sin((3.1415926535897931D * (double)mElpsFs) / 500D));
            for(int i = 0; i < sz4Way; i++)
            {
                if(!e4Way)
                {
                    x4Way = 0;
                    y4Way = 0;
                } else
                if(i == 0)
                {
                    x4Way = mvDot;
                    y4Way = -mvDot;
                } else
                if(i == 1)
                {
                    x4Way = -mvDot;
                    y4Way = -mvDot;
                } else
                if(i == 2)
                {
                    x4Way = -mvDot;
                    y4Way = mvDot;
                } else
                if(i == 3)
                {
                    x4Way = mvDot;
                    y4Way = mvDot;
                }
                for(int j = 0; j < szSprd; j++)
                {
                    int xRndm = eSprd ? AppLoop.RNDM.nextInt(mW) - mW / 2 : 0;
                    int yRndm = eSprd ? AppLoop.RNDM.nextInt(mH) - mH / 2 : 0;
                    mWD.dwImg(mGIWpn.mWDIn, bufId, xDw + xRvsn + xRndm + x4Way, yDw + yRvsn + yRndm + y4Way);
                }

            }

        } else
        if(ePllr)
            mWD.zmImg(mGIWpn.mWDIn, bufId, xDw + xRvsn, 0 + yRvsn, mW, yDw + mH);
        else
            mWD.dwImg(mGIWpn.mWDIn, bufId, xDw + xRvsn, yDw + yRvsn);
        mWD.setRtt(0);
        mWD.setTrns(100);
    }

    public void finish()
    {
        super.finish();
        mSG = null;
        mGDUnt = null;
        mGTSkl = null;
        mGIWpn = null;
    }

    private AppLoopSimWar mAL;
    private ScnGm mSG;
    private GmDtUnt mGDUnt;
    private GmToolSkl mGTSkl;
    private GmImgWpn mGIWpn;
    private int mXStrt;
    private int mYStrt;
    private int mXGoal;
    private int mYGoal;
    private long mTmStrt;
    private int mBufFly;
    private int mBufHit;
    private static final int FS_FLY = 0;
    private static final int FS_HIT = 1;
    private static final int FS_ELPS_HIT = 1000;
    private static final int FS_ELPS_END = 1500;
    private static final int FS_INTRVL_FLY = 1000;
    private static final int FS_INTRVL_HIT = 500;
    private int mFs;
    private int mElpsFs;
    private static final int EF_DWS_SZ = 3;
    private boolean mHitSeEnd;
}
