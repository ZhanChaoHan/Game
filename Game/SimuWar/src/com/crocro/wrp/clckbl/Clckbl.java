// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Clckbl.java

package com.crocro.wrp.clckbl;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.bs.F;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.wrp.WrpDw;

public class Clckbl
    implements F
{

    public Clckbl()
    {
        mClckNow = false;
        mThisEnbl = true;
        mNeedRmv = false;
        mTmEf = 0L;
    }

    public Clckbl(AppLoop al, int x, int y, int w, int h, int crdntTyp, int clckTyp)
    {
        mClckNow = false;
        mThisEnbl = true;
        mNeedRmv = false;
        mTmEf = 0L;
        mAL = al;
        mWD = mAL.mWD;
        setPos(x, y);
        mW = w;
        mH = h;
        mCrdntTyp = crdntTyp;
        mClckTyp = clckTyp;
    }

    public int chkClckIn(int x, int y)
    {
        if(!mThisEnbl)
            return 0;
        int res = 0;
        boolean clckEnbl = false;
        if(mClckTyp == 0)
            return res;
        if(mClckNow)
            return res;
        if(mClckExclsn)
            return res;
        if(mX <= x && x < mX + mW && mY <= y && y < mY + mH)
            clckEnbl = true;
        if(!clckEnbl)
            return res;
        if(mClckTyp == 1)
            res = 1;
        else
        if(mClckTyp == 2)
        {
            res = 2;
            if(!mClckNow)
                mClckExclsn = true;
            mClckNow = true;
        }
        mClckX = x;
        mClckY = y;
        return res;
    }

    public void setDrgPos(int x, int y)
    {
        mDrgX = x;
        mDrgY = y;
    }

    public int chkClckOut(int x, int y)
    {
        if(!mThisEnbl)
            return 0;
        int res = 0;
        boolean clckEnbl = false;
        if(mClckTyp == 0)
            return res;
        if(mClckTyp == 1)
            return res;
        if(!mClckNow)
            return res;
        mClckNow = false;
        mClckExclsn = false;
        if(x >= 0 && x < mAL.mMC.getScrW() && y >= 0 && y < mAL.mMC.getScrH())
            clckEnbl = true;
        if(!clckEnbl)
            return res;
        if(mClckTyp == 2)
            res = 3;
        mDrpX = x;
        mDrpY = y;
        return res;
    }

    public void unSelDrg()
    {
        mThisEnbl = false;
        if(mClckTyp == 0)
            return;
        if(mClckTyp == 1)
            return;
        if(!mClckNow)
        {
            return;
        } else
        {
            mClckNow = false;
            mClckExclsn = false;
            return;
        }
    }

    public boolean chkDwRng(int x, int y)
    {
        if(mCrdntTyp == 0)
        {
            if(x + mW <= 0 || mAL.mMC.getScrW() < x || y + mH <= 0 || mAL.mMC.getScrH() < y)
                return false;
        } else
        if(mAL.mMC.mOrgnX + x + mW <= 0 || mAL.mMC.mOrgnX + mAL.mMC.getScrW() < x || mAL.mMC.mOrgnY + y + mH <= 0 || mAL.mMC.mOrgnY + mAL.mMC.getScrH() < y)
            return false;
        return true;
    }

    public void doAlwys()
    {
        if(mCrdntTyp == 1)
        {
            mX = mXIn + mAL.mMC.mOrgnX;
            mY = mYIn + mAL.mMC.mOrgnY;
        }
    }

    public boolean doBck()
    {
        return false;
    }

    public boolean doMn()
    {
        return false;
    }

    public void setPos(int x, int y)
    {
        if(mCrdntTyp == 0)
        {
            mX = mXIn = x;
            mY = mYIn = y;
        } else
        {
            mXIn = x;
            mYIn = y;
            mX = mXIn + mAL.mMC.mOrgnX;
            mY = mYIn + mAL.mMC.mOrgnY;
        }
    }

    public void mvPos(int x, int y)
    {
        if(mCrdntTyp == 0)
        {
            mX = mXIn += x;
            mY = mYIn += y;
        } else
        {
            mXIn += x;
            mYIn += y;
            mX = mXIn + mAL.mMC.mOrgnX;
            mY = mYIn + mAL.mMC.mOrgnY;
        }
    }

    public void dwAlwys()
    {
    }

    public void dwAlwysEf()
    {
    }

    public void actClck()
    {
    }

    public void actDrp()
    {
    }

    public void dwClckEf()
    {
        if(mAL.mTmNow - mTmEf < 500L)
        {
            int x = mClckX;
            int y = mClckY;
            if(mClckTyp == 2)
            {
                x = mDrpX;
                y = mDrpY;
            }
            int elpsTm = (int)(mAL.mTmNow - mTmEf);
            int sz = (((mAL.mMC.getScrMax() * 27) / 10) * elpsTm) / 500;
            mWD.useCol(WrpDw.COL_SKY_BLUE);
            mWD.setStrk(8);
            mWD.useCntr(true);
            mWD.dwOvl(x, y, sz, sz);
            mWD.useCntr(false);
            mWD.setStrk(1);
        }
    }

    public void rsrvRmv()
    {
        mNeedRmv = true;
    }

    public void finish()
    {
        mAL = null;
        mWD = null;
    }

    public AppLoop mAL;
    public WrpDw mWD;
    public int mX;
    public int mY;
    public int mXIn;
    public int mYIn;
    public int mW;
    public int mH;
    public int mCrdntTyp;
    public int mClckTyp;
    public static final int CRDNT_ABSLT = 0;
    public static final int CRDNT_RLTV = 1;
    public static final int CLCK_NOT = 0;
    public static final int CLCK_IN = 1;
    public static final int CLCK_DRG = 2;
    public boolean mClckNow;
    public static boolean mClckExclsn = false;
    public boolean mThisEnbl;
    public boolean mNeedRmv;
    public static final int RES_NOT = 0;
    public static final int RES_IN_OK = 1;
    public static final int RES_DRG_STRT = 2;
    public static final int RES_DRG_OK = 3;
    public static final int RES_FRC_CNCL = 4;
    public int mClckX;
    public int mClckY;
    public int mDrgX;
    public int mDrgY;
    public int mDrpX;
    public int mDrpY;
    public long mTmEf;
    public static final int EF_TM = 500;
    public static final int AFTR_EF_TM = 550;

}
