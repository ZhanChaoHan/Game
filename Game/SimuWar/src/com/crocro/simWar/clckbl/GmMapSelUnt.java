// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GmMapSelUnt.java

package com.crocro.simWar.clckbl;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.gm.GmImgUnt;
import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.scn.MngDlg;
import com.crocro.wrp.scn.Scn;
import com.crocro.wrp.utl.Rsrv;
import com.crocro.wrp.wrp.WrpDw;
import java.util.ArrayList;

// Referenced classes of package com.crocro.simWar.clckbl:
//            GmMap

public class GmMapSelUnt extends GmMap
{

    public GmMapSelUnt(AppLoopSimWar al, Scn scn, int x, int y, int w, int h, int imgId)
    {
        super(al, x, y, w, h, imgId, 22, 22, 22);
        mStatBtn = 0;
        mSelUntNow = -1;
        mBsSz = 20;
        mUntBsSz = mBsSz * 3;
        mPrntScn = scn;
    }

    public void init()
    {
        mBarW = (mW * mW) / mMapW - 4;
        mBarH = (mH * mH) / mMapH - 4;
        mCanUseUntSz = mCanUseRef.length;
        mUntXSz = (mW - 24 - 16) / mUntBsSz;
        mUntYSz = mCanUseUntSz / mUntXSz + (mCanUseUntSz % mUntXSz != 0 ? 1 : 0);
        mDlgW = mUntXSz * mUntBsSz + 24;
        mDlgH = mUntYSz * mUntBsSz + 24;
        mDlgX = mX + (mW - mDlgW) / 2;
        mDlgY = mY + (mH - mDlgH) / 2;
        mDlgInX = mDlgX + 12;
        mDlgInY = mDlgY + 12;
        int xId0 = mLdStgDt[0][2] * 20 + 10;
        int yId0 = mLdStgDt[0][3] * 20 + 10;
        int setX = xId0 - mW / 2;
        int setY = yId0 - mH / 2;
        setMapPos(setX, setY);
    }

    public void dwAlwys()
    {
        mWD.useCol(WrpDw.COL_DARK_GRAY);
        mWD.flRct(mX, mY, mW, mH);
        super.dwAlwys();
        mWD.setClip(mX, mY, mW, mH);
        for(int i = 0; i < mMyUntMax; i++)
        {
            int dt[] = mLdStgDt[i];
            int typ = mUntTypArr[i];
            int bsX = (dt[2] * mBsSz + mBsSz / 2) - mUntBsSz / 2;
            int bsY = (dt[3] * mBsSz + mBsSz / 2) - mUntBsSz / 2;
            int dwX = ((mX + mDX) - mMapX) + bsX;
            int dwY = ((mY + mDY) - mMapY) + bsY;
            if(mSelUntNow == i)
            {
                mWD.useCol(WrpDw.COL_LGHT_RED);
                mWD.flRRct(dwX, dwY, mUntBsSz, mUntBsSz, 18, 18);
            }
            mWD.useCol(WrpDw.COL_RED);
            mWD.setStrk(4);
            mWD.dwRRct(dwX, dwY, mUntBsSz, mUntBsSz, 18, 18);
            mWD.setStrk(1);
            mWD.dwImg(mGmImgUnt.mWDIn, typ, dwX, dwY);
        }

        mWD.clrClip();
        mWD.useCol(WrpDw.COL_BLACK);
        for(int i = 0; i < 2; i++)
            mWD.dwRct(mX + i, mY + i, mW - i * 2, mH - i * 2);

        if(mMapW > mW)
        {
            int mapX = mMapX;
            if(mapX < 0)
                mapX = 0;
            if(mMapW - mapX < mW)
                mapX = mMapW - mW;
            int barX = (mapX * mW) / mMapW + 2;
            int dwX = mX + barX;
            int dwY = (mY + mH) - 5 - 2;
            int dwW = mBarW;
            int dwH = 5;
            mWD.useCol(WrpDw.COL_DARK_GRAY);
            mWD.flRRct(dwX, dwY, dwW, dwH, 5, 5);
            mWD.useCol(WrpDw.COL_WHITE);
            mWD.dwRRct(dwX, dwY, dwW, dwH, 5, 5);
        }
        if(mMapH > mH)
        {
            int mapY = mMapY;
            if(mapY < 0)
                mapY = 0;
            if(mMapH - mapY < mH)
                mapY = mMapH - mH;
            int barY = (mapY * mH) / mMapH + 2;
            int dwX = (mX + mW) - 5 - 2;
            int dwY = mY + barY;
            int dwW = 5;
            int dwH = mBarH;
            mWD.useCol(WrpDw.COL_DARK_GRAY);
            mWD.flRRct(dwX, dwY, dwW, dwH, 5, 5);
            mWD.useCol(WrpDw.COL_WHITE);
            mWD.dwRRct(dwX, dwY, dwW, dwH, 5, 5);
        }
        if(mSelUntNow != -1)
        {
            mWD.useCol(WrpDw.COL_WHITE);
            mWD.flRRct(mDlgX, mDlgY, mDlgW, mDlgH, 12, 12);
            mWD.useCol(WrpDw.COL_DARK_GRAY);
            mWD.setStrk(2);
            mWD.dwRRct(mDlgX, mDlgY, mDlgW, mDlgH, 12, 12);
            mWD.setStrk(1);
            int selUntTyp = mUntTypArr[mSelUntNow];
            for(int i = 0; i < mCanUseUntSz; i++)
            {
                int typ = mCanUseRef[i];
                int dwX = mDlgInX + (i % mUntXSz) * mUntBsSz;
                int dwY = mDlgInY + (i / mUntXSz) * mUntBsSz;
                if(typ == selUntTyp)
                {
                    mWD.useCol(WrpDw.COL_LGHT_RED);
                    mWD.flRRct(dwX, dwY, mUntBsSz, mUntBsSz, 18, 18);
                    mWD.useCol(WrpDw.COL_RED);
                    mWD.setStrk(4);
                    mWD.dwRRct(dwX, dwY, mUntBsSz, mUntBsSz, 18, 18);
                    mWD.setStrk(1);
                }
                mWD.dwImg(mGmImgUnt.mWDIn, mCanUseRef[i], dwX, dwY);
            }

        }
    }

    public int chkClckIn(int x, int y)
    {
        int res = super.chkClckIn(x, y);
        if(res != 0)
            if(mSelUntNow == -1)
            {
                int clckX = (mClckX - mX) + mMapX;
                int clckY = (mClckY - mY) + mMapY;
                int bsSz = 20;
                for(int i = 0; i < mMyUntMax; i++)
                {
                    int dt[] = mLdStgDt[i];
                    int bsX = (dt[2] - 1) * bsSz;
                    int bsY = (dt[3] - 1) * bsSz;
                    if(bsX > clckX || clckX >= bsX + bsSz * 3 || bsY > clckY || clckY >= bsY + bsSz * 3)
                        continue;
                    mSelUntNow = i;
                    res = 0;
                    mClckNow = false;
                    mClckExclsn = false;
                    mStatBtn = 1;
                    mTmEf = mAL.mTmNow;
                    mDrpX = mClckX;
                    mDrpY = mClckY;
                    mPrntScn.mClckEnbl = false;
                    mAL.mRsrvLst.add(new Rsrv(mAL, 550L) {

                        public void addAct()
                        {
                            mAL.mMngDlg.mClckEnbl = true;
                            mStatBtn = 0;
                        }

                    }
);
                    break;
                }

            } else
            {
                res = 0;
                mClckNow = false;
                mClckExclsn = false;
                if(mDlgX <= mClckX && mClckX < mDlgX + mDlgW && mDlgY <= mClckY && mClckY < mDlgY + mDlgH)
                {
                    if(mDlgInX <= mClckX && mClckX < mDlgInX + mUntXSz * mUntBsSz && mDlgInY <= mClckY && mClckY < mDlgInY + mUntYSz * mUntBsSz)
                    {
                        int selId = ((mClckX - mDlgInX) / mUntBsSz) % mUntXSz + ((mClckY - mDlgInY) / mUntBsSz) * mUntXSz;
                        if(selId >= 0 && selId < mCanUseUntSz)
                        {
                            int newTyp = mCanUseRef[selId];
                            mUntTypArr[mSelUntNow] = newTyp;
                            mStatBtn = 1;
                            mTmEf = mAL.mTmNow;
                            mDrpX = mClckX;
                            mDrpY = mClckY;
                            mPrntScn.mClckEnbl = false;
                            mAL.mRsrvLst.add(new Rsrv(mAL, 550L) {

                                public void addAct()
                                {
                                    mAL.mMngDlg.mClckEnbl = true;
                                    mStatBtn = 0;
                                    mSelUntNow = -1;
                                }

 
                            }
);
                        }
                    }
                } else
                {
                    mSelUntNow = -1;
                }
            }
        return res;
    }

    public void dwAlwysEf()
    {
        if(mStatBtn == 1)
            dwClckEf();
    }

    public void finish()
    {
        super.finish();
        if(mGmImgUnt != null)
        {
            mGmImgUnt.finish();
            mGmImgUnt = null;
        }
        mUntTypArr = null;
        mLdStgDt = null;
    }

    public Scn mPrntScn;
    public static final int CLCK_BFR = 0;
    public static final int CLCK_AFTR = 1;
    public int mStatBtn;
    private static final int FRM_SZ = 2;
    private static final int BAR_SZ = 5;
    private static final int MRGN_DLG = 8;
    public GmImgUnt mGmImgUnt;
    public int mUntTypArr[];
    public int mLdStgDt[][];
    public int mMyUntMax;
    public int mSelUntNow;
    public int mCanUseRef[];
    private int mBsSz;
    private int mUntBsSz;
    private int mBarW;
    private int mBarH;
    private int mCanUseUntSz;
    private int mUntXSz;
    private int mUntYSz;
    private int mDlgW;
    private int mDlgH;
    private int mDlgX;
    private int mDlgY;
    private int mDlgInX;
    private int mDlgInY;
}
