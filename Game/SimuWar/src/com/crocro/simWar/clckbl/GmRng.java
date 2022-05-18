// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GmRng.java

package com.crocro.simWar.clckbl;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.gm.*;
import com.crocro.simWar.scn.ScnGm;
import com.crocro.wrp.clckbl.Drg;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.wrp.WrpDw;

public class GmRng extends Drg
    implements GmDtUntF
{

    public GmRng(AppLoopSimWar al, ScnGm sg)
    {
        super(al, 0, 0, al.mMC.getScrW(), al.mMC.getScrH(), 0);
        mSelNow = 0;
        mSelXStrt = -1;
        mSelYStrt = -1;
        mSelXEnd = -1;
        mSelYEnd = -1;
        mMvXStrt = -1;
        mMvYStrt = -1;
        mSelXOrgn = -1;
        mSelYOrgn = -1;
        mAL = (AppLoopSimWar)super.mAL;
        mSG = sg;
        mGDUnt = mSG.mGDUnt;
    }

    public int chkClckIn(int x, int y)
    {
        if(mSG.mUSelTyp != 1)
            return 0;
        int res = super.chkClckIn(x, y);
        if(res == 2)
            if(mSelNow == 1)
            {
                mSelNow = 2;
                mMvXStrt = x;
                mMvYStrt = y;
            } else
            {
                mSelNow = 1;
                mSelXStrt = x;
                mSelYStrt = y;
            }
        return res;
    }

    public void dwAlwys()
    {
        if(mSG.mUSelTyp != 1)
        {
            return;
        } else
        {
            super.dwAlwys();
            return;
        }
    }

    public void dwNrml()
    {
        mWD.useCol(WrpDw.COL_WHITE);
        if(mSelNow == 1)
        {
            mSelXStrt += mAL.mMC.mOrgnX - mSelXOrgn;
            mSelXEnd += mAL.mMC.mOrgnX - mSelXOrgn;
            mSelYStrt += mAL.mMC.mOrgnY - mSelYOrgn;
            mSelYEnd += mAL.mMC.mOrgnY - mSelYOrgn;
            mSelXOrgn = mAL.mMC.mOrgnX;
            mSelYOrgn = mAL.mMC.mOrgnY;
            mWD.setTrns(50);
            mWD.flRct(mSelXStrt >= mSelXEnd ? mSelXEnd : mSelXStrt, mSelYStrt >= mSelYEnd ? mSelYEnd : mSelYStrt, mSelXEnd <= mSelXStrt ? mSelXStrt - mSelXEnd : mSelXEnd - mSelXStrt, mSelYEnd <= mSelYStrt ? mSelYStrt - mSelYEnd : mSelYEnd - mSelYStrt);
            mWD.setTrns(100);
            mWD.useCol(WrpDw.COL_GRAY);
            mWD.setStrk(2);
            mWD.dwRct(mSelXStrt >= mSelXEnd ? mSelXEnd : mSelXStrt, mSelYStrt >= mSelYEnd ? mSelYEnd : mSelYStrt, mSelXEnd <= mSelXStrt ? mSelXStrt - mSelXEnd : mSelXEnd - mSelXStrt, mSelYEnd <= mSelYStrt ? mSelYStrt - mSelYEnd : mSelYEnd - mSelYStrt);
            mWD.setStrk(1);
        }
    }

    public void dwDrg()
    {
        mWD.useCol(WrpDw.COL_WHITE);
        if(mSelNow == 1)
        {
            mWD.setTrns(50);
            mWD.flRct(mSelXStrt >= mDrgX ? mDrgX : mSelXStrt, mSelYStrt >= mDrgY ? mDrgY : mSelYStrt, mDrgX <= mSelXStrt ? mSelXStrt - mDrgX : mDrgX - mSelXStrt, mDrgY <= mSelYStrt ? mSelYStrt - mDrgY : mDrgY - mSelYStrt);
            mWD.setTrns(100);
            mWD.useCol(WrpDw.COL_GRAY);
            mWD.setStrk(2);
            mWD.dwRct(mSelXStrt >= mDrgX ? mDrgX : mSelXStrt, mSelYStrt >= mDrgY ? mDrgY : mSelYStrt, mDrgX <= mSelXStrt ? mSelXStrt - mDrgX : mDrgX - mSelXStrt, mDrgY <= mSelYStrt ? mSelYStrt - mDrgY : mDrgY - mSelYStrt);
            mWD.setStrk(1);
        } else
        if(mSelNow == 2)
        {
            int x = (mSelXStrt >= mSelXEnd ? mSelXEnd : mSelXStrt) + (mDrgX - mMvXStrt);
            int y = (mSelYStrt >= mSelYEnd ? mSelYEnd : mSelYStrt) + (mDrgY - mMvYStrt);
            mWD.setTrns(50);
            mWD.flRct(x, y, mSelXEnd <= mSelXStrt ? mSelXStrt - mSelXEnd : mSelXEnd - mSelXStrt, mSelYEnd <= mSelYStrt ? mSelYStrt - mSelYEnd : mSelYEnd - mSelYStrt);
            mWD.setTrns(100);
            mWD.useCol(WrpDw.COL_RED);
            mWD.setStrk(2);
            mWD.dwRct(x, y, mSelXEnd <= mSelXStrt ? mSelXStrt - mSelXEnd : mSelXEnd - mSelXStrt, mSelYEnd <= mSelYStrt ? mSelYStrt - mSelYEnd : mSelYEnd - mSelYStrt);
            mWD.setStrk(1);
        }
    }

    public void actDrp()
    {
        if(mSelNow == 1)
        {
            mSelXEnd = mDrpX;
            mSelYEnd = mDrpY;
            mSelXOrgn = mAL.mMC.mOrgnX;
            mSelYOrgn = mAL.mMC.mOrgnY;
        } else
        if(mSelNow == 2)
        {
            mSelNow = 0;
            int xRngStrt = (mSelXStrt >= mSelXEnd ? mSelXEnd : mSelXStrt) - mAL.mMC.mOrgnX;
            int yRngStrt = (mSelYStrt >= mSelYEnd ? mSelYEnd : mSelYStrt) - mAL.mMC.mOrgnY;
            int xRngEnd = (mSelXStrt <= mSelXEnd ? mSelXEnd : mSelXStrt) - mAL.mMC.mOrgnX;
            int yRngEnd = (mSelYStrt <= mSelYEnd ? mSelYEnd : mSelYStrt) - mAL.mMC.mOrgnY;
            int xMv = mDrpX - mMvXStrt;
            int yMv = mDrpY - mMvYStrt;
            for(int i = 0; i < 32; i++)
                if(mGDUnt.getEnbl(i) != 0)
                {
                    int xUDst = mGDUnt.getUS(i, US_DST_X_NOW);
                    int yUDst = mGDUnt.getUS(i, US_DST_Y_NOW);
                    if(xUDst >= xRngStrt && xRngEnd >= xUDst && yUDst >= yRngStrt && yRngEnd >= yUDst)
                    {
                        int xUMv = xUDst + xMv;
                        int yUMv = yUDst + yMv;
                        if(xUMv < 20)
                            xUMv = 20;
                        if(yUMv < 20)
                            yUMv = 20;
                        if(xUMv + 20 >= mSG.mGDMap.mMapW)
                            xUMv = mSG.mGDMap.mMapW - 20;
                        if(yUMv + 20 >= mSG.mGDMap.mMapH)
                            yUMv = mSG.mGDMap.mMapH - 20;
                        mGDUnt.setUS(i, US_DST_X_OLD, xUDst);
                        mGDUnt.setUS(i, US_DST_Y_OLD, yUDst);
                        mGDUnt.setUS(i, US_DST_X_NOW, xUMv);
                        mGDUnt.setUS(i, US_DST_Y_NOW, yUMv);
                    }
                }

        }
        mTmEf = mAL.mTmNow;
    }

    public void unSel()
    {
        mSelNow = 0;
    }

    public void finish()
    {
        super.finish();
        mSG = null;
        mGDUnt = null;
    }

    private AppLoopSimWar mAL;
    ScnGm mSG;
    GmDtUnt mGDUnt;
    public static final int SEL_NO = 0;
    public static final int SEL_SEL = 1;
    public static final int SEL_MV = 2;
    public int mSelNow;
    public int mSelXStrt;
    public int mSelYStrt;
    public int mSelXEnd;
    public int mSelYEnd;
    public int mMvXStrt;
    public int mMvYStrt;
    public int mSelXOrgn;
    public int mSelYOrgn;
}
