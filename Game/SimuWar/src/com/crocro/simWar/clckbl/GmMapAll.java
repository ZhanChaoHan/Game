// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GmMapAll.java

package com.crocro.simWar.clckbl;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.gm.*;
import com.crocro.simWar.mng.MngLayout;
import com.crocro.simWar.scn.ScnGm;
import com.crocro.wrp.clckbl.Drg;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.utl.UtlDw;
import com.crocro.wrp.wrp.WrpDw;

// Referenced classes of package com.crocro.simWar.clckbl:
//            GmMap

public class GmMapAll extends Drg
    implements GmDtUntF
{

    public GmMapAll(AppLoopSimWar al, ScnGm sg)
    {
        super(al, 0, 0, 0, 0, 0);
        mAL = (AppLoopSimWar)super.mAL;
        mSG = sg;
        mGDUnt = mSG.mGDUnt;
        int mapVwW = mWD.getBufW(ScnGm.IMG_ID_MAP_VW);
        int mapVwH = mWD.getBufH(ScnGm.IMG_ID_MAP_VW);
        mMapAllW = ((MngLayout)mAL.mML).l.mapAllWMax;
        mMapAllH = (mapVwH * ((MngLayout)mAL.mML).l.mapAllWMax) / mapVwW;
        if(mMapAllH > ((MngLayout)mAL.mML).l.mapAllHMax)
        {
            mMapAllW = (mMapAllW * ((MngLayout)mAL.mML).l.mapAllHMax) / mMapAllH;
            mMapAllH = ((MngLayout)mAL.mML).l.mapAllHMax;
        }
        mMvRt = (double)mMapAllW / (double)mWD.getBufW(ScnGm.IMG_ID_MAP_VW);
        mMapAllH = (int)((double)mWD.getBufH(ScnGm.IMG_ID_MAP_VW) * mMvRt);
        mW = mMapAllW + 6;
        mH = mMapAllH + 6;
        mWD.mkBuf(IMG_ID, mW, mH);
        mWD.tgtBuf(IMG_ID);
        mWD.useCol(WrpDw.COL_THM_BS);
        mWD.flRct(0, 0, mWD.getBufW(IMG_ID), mWD.getBufH(IMG_ID));
        mWD.zmImg(ScnGm.IMG_ID_MAP_VW, 3, 3, mMapAllW, mMapAllH);
        setPos(mAL.mMC.getScrW() - mW - ((MngLayout)mAL.mML).l.gmBtnOutMrgn, ((MngLayout)mAL.mML).l.gmBtnOutMrgn);
        mW = mWD.getBufW(IMG_ID);
        mH = mWD.getBufH(IMG_ID);
    }

    public void dwAlwys()
    {
        mWD.dwImg(IMG_ID, mX, mY);
        int x = 3 + (int)((double)mSG.mGmMap.mMapX * mMvRt);
        int y = 3 + (int)((double)mSG.mGmMap.mMapY * mMvRt);
        int w = ((mMapAllW - 1) * mAL.mMC.getScrW()) / mWD.getBufW(ScnGm.IMG_ID_MAP_VW);
        int h = ((mMapAllH - 1) * mAL.mMC.getScrH()) / mWD.getBufH(ScnGm.IMG_ID_MAP_VW);
        if(w >= mMapAllW)
            w = mMapAllW - 1;
        if(h >= mMapAllH)
            h = mMapAllH - 1;
        mWD.setClip(mX + 3, mY + 3, mW - 6, mH - 6);
        int hpSumMy = 0;
        int hpSumEne = 0;
        int noSumMy = 0;
        int noSumEne = 0;
        for(int i = 0; i < 132; i++)
            if(mGDUnt.getEnbl(i) != 0)
            {
                int xNow = mGDUnt.getUS(i, US_MV_X_NOW);
                int yNow = mGDUnt.getUS(i, US_MV_Y_NOW);
                xNow = (mW * xNow) / mSG.mGDMap.mMapW - 1;
                yNow = (mH * yNow) / mSG.mGDMap.mMapH - 1;
                int hp = mGDUnt.getUS(i, US_HP);
                mWD.useCol(WrpDw.COL_WHITE);
                mWD.flRct(mX + xNow, (mY + yNow) - 1, 2, 4);
                mWD.flRct((mX + xNow) - 1, mY + yNow, 4, 2);
                if(i < 32)
                {
                    mWD.useCol(WrpDw.COL_BLUE);
                    hpSumMy += hp;
                    noSumMy++;
                } else
                {
                    mWD.useCol(WrpDw.COL_RED);
                    hpSumEne += hp;
                    noSumEne++;
                }
                mWD.flRct(mX + xNow, mY + yNow, 2, 2);
            }

        mWD.setStrk(1);
        mWD.setTrns(66);
        mWD.useCol(WrpDw.COL_RED);
        mWD.dwRct(mX + x, mY + y, w, h);
        mWD.setTrns(100);
        mWD.clrClip();
        int hMy = (mH * noSumMy) / (noSumMy + noSumEne);
        int hEne = (mH * noSumEne) / (noSumMy + noSumEne);
        int xGrp = mX - 1 - 6;
        int yGrp = mY;
        mWD.setCol(237, 32, 32);
        mWD.flRct(xGrp, yGrp, 6, hEne);
        mWD.setCol(101, 51, 30);
        mWD.dwRct(xGrp, yGrp, 6, hEne);
        xGrp = mX - 1 - 6;
        yGrp = mY + hEne;
        mWD.setCol(99, 176, 238);
        mWD.flRct(xGrp, yGrp, 6, hMy);
        mWD.setCol(68, 115, 176);
        mWD.dwRct(xGrp, yGrp, 6, hMy);
        int xUnt = xGrp - 1;
        int yUnt = mY;
        UtlDw.dwNmbr(mWD, ScnGm.IMG_ID_NMBR, noSumEne, xUnt, yUnt, 0);
        yUnt = (yUnt + mH) - mWD.getBufH(ScnGm.IMG_ID_NMBR);
        UtlDw.dwNmbr(mWD, ScnGm.IMG_ID_NMBR, noSumMy, xUnt, yUnt, 0);
    }

    public void setDrgPos(int x, int y)
    {
        mDrgX = x;
        mDrgY = y;
        mvMap();
    }

    public void actDrp()
    {
    }

    public void mvMap()
    {
        int clckX = (int)((double)(mClckX - mX) / mMvRt);
        int clckY = (int)((double)(mClckY - mY) / mMvRt);
        int drgX = (int)((double)(mDrgX - mX) / mMvRt);
        int drgY = (int)((double)(mDrgY - mY) / mMvRt);
        mSG.mGmMap.mClckX = 0;
        mSG.mGmMap.mClckY = 0;
        mSG.mGmMap.mDrgX = clckX - drgX;
        mSG.mGmMap.mDrgY = clckY - drgY;
        mSG.mGmMap.mvMap();
        mClckX = mDrgX;
        mClckY = mDrgY;
    }

    public void finish()
    {
        super.finish();
        mSG = null;
        mGDUnt = null;
    }

    private AppLoopSimWar mAL;
    public ScnGm mSG;
    public GmDtUnt mGDUnt;
    public static final int IMG_ID;
    public static final int MAP_ALL_FRM_SZ = 3;
    public int mMapAllW;
    public int mMapAllH;
    public double mMvRt;
    public static final int HP_GRP_W = 6;
    public static final int HP_GRP_MRGN = 1;
    public static final int HP_GRP_ENE_R = 237;
    public static final int HP_GRP_ENE_G = 32;
    public static final int HP_GRP_ENE_B = 32;
    public static final int HP_GRP_ENE_FRM_R = 101;
    public static final int HP_GRP_ENE_FRM_G = 51;
    public static final int HP_GRP_ENE_FRM_B = 30;
    public static final int HP_GRP_MY_R = 99;
    public static final int HP_GRP_MY_G = 176;
    public static final int HP_GRP_MY_B = 238;
    public static final int HP_GRP_MY_FRM_R = 68;
    public static final int HP_GRP_MY_FRM_G = 115;
    public static final int HP_GRP_MY_FRM_B = 176;

    static 
    {
        IMG_ID = ScnGm.IMG_ID_MAP_ALL;
    }
}
