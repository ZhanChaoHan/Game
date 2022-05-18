// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DlgMnItmImg.java

package com.crocro.wrp.clckbl;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.mng.MngLayout;
import com.crocro.wrp.scn.MngDlg;
import com.crocro.wrp.wrp.WrpDw;

// Referenced classes of package com.crocro.wrp.clckbl:
//            DlgMnItm

public class DlgMnItmImg extends DlgMnItm
{

    public DlgMnItmImg(AppLoop al, MngDlg mngDlg, int h, String ttlStr, int id, int bufId, int imgPos)
    {
        super(al, mngDlg, h, ttlStr, id);
        mImgPos = 0;
        setBfrClckImg(bufId);
        setAftrClckImg(bufId);
        mImgPos = imgPos;
        mStrFntOpt.lnH = (int)((double)mStrFntOpt.lnH * 1.2D);
        initFntPrm(mW, mH);
    }

    public void dwBfr()
    {
        dwBfrBtnBs();
        int imgW = mWD.getBufW(mBfrImgId);
        if(mImgPos == 0)
            mWD.dwImg(mBfrImgId, (mX + mW) - imgW - 12, mY + 3, 0, 0, imgW, mH - 6);
        int x = mX + mAL.mML.mStrFntDflt.fntSz / 2;
        int y = mY + (mH - mTtlH) / 2;
        mWD.useCol(WrpDw.COL_BLACK);
        mWD.dwStr(mTtlStr, x, y, mAL.mMC.getScrW(), mStrFntOpt, null);
    }

    public void dwAftr()
    {
        dwAftrBtnBs();
        int imgW = mWD.getBufW(mAftrImgId);
        if(mImgPos == 0)
            mWD.dwImg(mAftrImgId, (mX + mW) - imgW - 12, mY + 3, 0, 0, imgW, mH - 6);
        int x = mX + mStrFntOpt.fntSz / 2;
        int y = mY + (mH - mTtlH) / 2;
        mWD.useCol(WrpDw.COL_BLACK);
        mWD.dwStr(mTtlStr, x, y, mAL.mMC.getScrW(), mStrFntOpt, null);
    }

    public static final int IMG_POS_R_T = 0;
    public int mImgPos;
}
