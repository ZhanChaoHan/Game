// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StrBtn.java

package com.crocro.wrp.clckbl;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.mng.MngLayout;
import com.crocro.wrp.utl.UtlDw;
import com.crocro.wrp.wrp.WrpDw;

// Referenced classes of package com.crocro.wrp.clckbl:
//            Btn

public class StrBtn extends Btn
{

    public StrBtn(AppLoop al, int w, int h, String ttlStr)
    {
        this(al, 0, 0, w, h, ttlStr);
    }

    public StrBtn(AppLoop al, int x, int y, int w, int h, String ttlStr)
    {
        super(al, x, y, w, h, 0);
        mStrFntOpt = null;
        mBtnWPddng = 30;
        mBtnAutoWMin = 120;
        mBtnHPddng = 6;
        mTtlStr = ttlStr;
        mStrFntOpt = mAL.mML.mStrFntDflt.clone();
        initFntPrm(w, h);
    }

    public void initFntPrm(int w, int h)
    {
        mTtlW = mWD.getStrW(mTtlStr, mStrFntOpt, 999);
        mTtlH = mWD.getStrH(mTtlStr, mStrFntOpt, 999);
        if(w == -1)
        {
            int wStr = mWD.getStrW(mTtlStr, mStrFntOpt, mAL.mMC.getScrW());
            mW = wStr + mBtnWPddng * 2;
            if(mW <= mBtnAutoWMin)
                mW = mBtnAutoWMin;
        }
        if(h == -1)
        {
            int hStr = mWD.getStrH(mTtlStr, mStrFntOpt, mAL.mMC.getScrW());
            mH = hStr + mBtnHPddng * 2;
            if(mH <= mStrFntOpt.fntSz + mBtnHPddng * 2)
                mH = mStrFntOpt.fntSz + mBtnHPddng * 2;
        }
    }

    public void adjstFntSz(int wMax)
    {
        for(int i = 0; i < mAL.mML.mStrFntOptArr.length; i++)
        {
            int w = mWD.getStrW(mTtlStr, mAL.mML.mStrFntOptArr[i], 999);
            if(w > wMax)
                continue;
            if(mStrFntOpt.fntSz != mAL.mML.mStrFntOptArr[i].fntSz)
            {
                mStrFntOpt = null;
                mStrFntOpt = mAL.mML.mStrFntOptArr[i].clone();
            }
            break;
        }

    }

    public void dwBfr()
    {
        dwBfrBtnBs();
        int x = mX + (mW - mTtlW) / 2;
        int y = mY + (mH - mTtlH) / 2;
        mWD.dwStr(mTtlStr, x, y, mAL.mMC.getScrW(), mStrFntOpt, null);
    }

    public void dwAftr()
    {
        dwAftrBtnBs();
        mWD.useCol(WrpDw.COL_BLACK);
        int x = mX + (mW - mTtlW) / 2;
        int y = mY + (mH - mTtlH) / 2;
        mWD.dwStr(mTtlStr, x, y, mAL.mMC.getScrW(), mStrFntOpt, null);
    }

    public void preBtnBs()
    {
    }

    public void pstBtnBs()
    {
    }

    public void dwBfrBtnBs()
    {
        preBtnBs();
        UtlDw.dwBfrBtnBs(mWD, mX, mY, mW, mH);
        pstBtnBs();
    }

    public void dwAftrBtnBs()
    {
        preBtnBs();
        UtlDw.dwAftrBtnBs(mWD, mX, mY, mW, mH);
        pstBtnBs();
    }

    public void dwAftrEf()
    {
        dwClckEf();
    }

    public void finish()
    {
        super.finish();
        mTtlStr = null;
        mStrFntOpt = null;
    }

    public String mTtlStr;
    public com.crocro.wrp.wrp.WrpDw.StrFntOpt mStrFntOpt;
    public int mTtlW;
    public int mTtlH;
    public static final int FRM_SZ = 3;
    public static final int AUTO_W = -1;
    public static final int AUTO_H = -1;
    public int mBtnWPddng;
    public int mBtnAutoWMin;
    public int mBtnHPddng;
}
