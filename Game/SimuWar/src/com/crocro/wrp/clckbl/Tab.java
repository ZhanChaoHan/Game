// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Tab.java

package com.crocro.wrp.clckbl;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.wrp.WrpDw;

// Referenced classes of package com.crocro.wrp.clckbl:
//            Btn

public class Tab extends Btn
{

    public Tab(AppLoop al, int y, int imgId)
    {
        super(al, 0, y, 0, 0, 0);
        mSldDir = 0;
        mBfrImgId = imgId;
        mAftrImgId = imgId;
        mW = mWD.getBufW(mBfrImgId);
        mH = mWD.getBufH(mBfrImgId);
        if(y == -1)
        {
            y = (mAL.mMC.getScrH() - mH) / 2;
            setPos(mX, y);
        }
    }

    public void dwBfr()
    {
        if(mW > 0 && mH > 0)
            super.dwBfr();
    }

    public void dwAftr()
    {
        int elpsTm = (int)(mAL.mTmNow - mTmEf);
        int scrW = mAL.mMC.getScrW();
        int scrH = mAL.mMC.getScrH();
        int x = (scrW * elpsTm) / 250;
        if(x > scrW)
            x = scrW;
        int y = scrH - (scrH * elpsTm) / 250;
        if(y <= 0)
            y = 0;
        if(mW > 0 && mH > 0)
            mWD.dwImg(mAftrImgId, x, mY);
        mWD.useCol(WrpDw.COL_BLACK);
        mWD.setStrk(3);
        if(mSldDir == 0)
            mWD.dwLn(x, 0, x, scrH);
        else
        if(mSldDir == 1)
            mWD.dwLn(0, y, scrH, y);
        mWD.setStrk(1);
        if(mSldDir == 0)
            mWD.dwImg(AppLoop.IMG_ID_CMN_BCK_RNDR, 0, 0, 0, 0, x, scrH);
        else
        if(mSldDir == 1)
            mWD.dwImg(AppLoop.IMG_ID_CMN_BCK_RNDR, 0, y, 0, 0, scrW, scrH - y);
    }

    public void dwClckEf()
    {
    }

    public boolean doMn()
    {
        mClckX = mX + mW / 2;
        mClckY = mY + mH / 2;
        actClck();
        return true;
    }

    public static final int AUTO_CNTR = -1;
    public static final int SLD_L2R = 0;
    public static final int SLD_B2U = 1;
    public int mSldDir;
}
