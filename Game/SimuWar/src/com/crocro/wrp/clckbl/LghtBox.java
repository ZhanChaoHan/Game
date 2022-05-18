// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LghtBox.java

package com.crocro.wrp.clckbl;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.mng.MngLayout;
import com.crocro.wrp.scn.Scn;
import com.crocro.wrp.utl.*;
import com.crocro.wrp.wrp.WrpDw;
import java.util.ArrayList;

// Referenced classes of package com.crocro.wrp.clckbl:
//            Btn

public class LghtBox extends Btn
{
    public static class ArrBtn
    {

        public boolean doClck()
        {
            return true;
        }

        public boolean chkIn(int x, int y)
        {
            if(x < mX || mX + mW <= x)
                return false;
            return y >= mY && mY + mH > y;
        }

        public int mX;
        public int mY;
        public int mW;
        public int mH;
        public int mIW;
        public int mIH;
        public int mStatBtn;
        public String mTtlStr;
        public int mImgId;
        public int mUnqId;

        public ArrBtn(String ttlStr)
        {
            mStatBtn = 0;
            mTtlStr = null;
            mImgId = -1;
            mTtlStr = ttlStr;
        }

        public ArrBtn(int imgId, WrpDw wd)
        {
            mStatBtn = 0;
            mTtlStr = null;
            mImgId = -1;
            mImgId = imgId;
            mIW = wd.getBufW(mImgId);
            mIH = wd.getBufH(mImgId);
        }
    }


    public LghtBox(AppLoop al, String ttlStr, Scn scn)
    {
        super(al, 0, 0, 0, 0, 0);
        mFrstDo = true;
        mAddActr = null;
        mAddDrwrPreStr = null;
        mAddDrwrPstStr = null;
        mUseDoBck = true;
        mNowDoBck = false;
        mBtnTyp = 0;
        mBtnH = 0;
        mResYesNoBtn = false;
        mArrBtns = null;
        mDwTbl = null;
        mBgTrns = 100;
        mBgColR = -1;
        mBgColG = -1;
        mBgColB = -1;
        mStrColR = -1;
        mStrColG = -1;
        mStrColB = -1;
        mStrSzPer = 100;
        mScn = scn;
        mW = mAL.mMC.getScrW();
        mH = mAL.mMC.getScrH();
        mTtlStr = ttlStr;
        mFrmMrgn = mAL.mML.l.lghtBox_frmMrgn;
        mFrmPddng = mAL.mML.l.lghtBox_frmPddng;
        mBtnH = mAL.mML.l.btnHBold;
    }

    public LghtBox(AppLoop al, String ttlStr, Scn scn, Actr actr)
    {
        this(al, ttlStr, scn);
        mAddActr = actr;
    }

    public void dwAlwys()
    {
        if(mFrstDo)
        {
            mFrstDo = false;
            mAL.mScn.cnslClck();
        }
        mWD.useCol(WrpDw.COL_BLACK);
        mWD.setTrns(75);
        mWD.flRct(mX, mY, mW, mH);
        mWD.setTrns(100);
        if(mBgColR == -1)
        {
            if(mDwTbl != null)
                mDwTbl.dwBG(mWD, 0, 0, mW - mFrmMrgn * 2, mH - mFrmMrgn * 2);
            else
                mWD.dwImg(AppLoop.IMG_ID_CMN_BCK_RNDR, mX + mFrmMrgn, mY + mFrmMrgn, 0, 0, mW - mFrmMrgn * 2, mH - mFrmMrgn * 2);
            mWD.useCol(WrpDw.COL_WHITE);
            int strH = mWD.getStrH(mTtlStr, mStrFnt, mW - (mFrmMrgn + mFrmPddng) * 2);
            mWD.setTrns(75);
            mWD.flRRct(mX + mFrmMrgn + mFrmPddng / 2, mY + mFrmMrgn + mFrmPddng / 2, mW - mFrmMrgn * 2 - mFrmPddng, strH + mFrmPddng, 12, 12);
            mWD.setTrns(100);
        } else
        {
            mWD.setTrns(mBgTrns);
            mWD.setCol(mBgColR, mBgColG, mBgColB);
            mWD.flRct(mX + mFrmMrgn, mY + mFrmMrgn, mW - mFrmMrgn * 2, mH - mFrmMrgn * 2);
            if(mBgTrns != 100)
                mWD.setTrns(100);
        }
        if(mAddDrwrPreStr != null)
            mAddDrwrPreStr.dw();
        mWD.useCol(WrpDw.COL_BLACK);
        if(mDwTbl == null)
        {
            if(mStrColR != -1)
            {
                mStrFnt.r = mStrColR;
                mStrFnt.g = mStrColG;
                mStrFnt.b = mStrColB;
            }
            if(mStrSzPer != 100)
            {
                mStrFnt.fntSz = (mAL.mML.l.lghtBox_fntSz * mStrSzPer) / 100;
                mStrFnt.lnH = (mAL.mML.l.lghtBox_fntSz * mStrSzPer) / 100;
            }
            mWD.dwStr(mTtlStr, mX + mFrmMrgn + mFrmPddng, mY + mFrmMrgn + mFrmPddng, mW - (mFrmMrgn + mFrmPddng) * 2, mStrFnt, null);
            if(mStrColR != -1)
            {
                mStrFnt.r = 0;
                mStrFnt.g = 0;
                mStrFnt.b = 0;
            }
            if(mStrSzPer != 100)
            {
                mStrFnt.fntSz = mAL.mML.l.lghtBox_fntSz;
                mStrFnt.lnH = mAL.mML.l.lghtBox_fntSz;
            }
        } else
        {
            mWD.dwStr(mTtlStr, mX + mFrmMrgn + mFrmPddng, mY + mFrmMrgn + mFrmPddng / 2, mW - (mFrmMrgn + mFrmPddng) * 2, mStrFnt, null);
        }
        if(mDwTbl != null)
            mDwTbl.dw();
        if(mAddDrwrPstStr != null)
            mAddDrwrPstStr.dw();
        if(mBtnTyp != 0)
        {
            int xBs = getBtnX();
            int yBs = getBtnY();
            int wBs = getBtnW();
            int hBs = getBtnH();
            if(mBtnTyp == 2)
            {
                UtlDw.dwBfrBtnBs(mWD, xBs, yBs, wBs / 2, hBs);
                UtlDw.dwBfrBtnBs(mWD, xBs + wBs / 2, yBs, wBs / 2, hBs);
                if(mStatBtn == 1)
                    if(mResYesNoBtn)
                        UtlDw.dwAftrBtnBs(mWD, xBs, yBs, wBs / 2, hBs);
                    else
                        UtlDw.dwAftrBtnBs(mWD, xBs + wBs / 2, yBs, wBs / 2, hBs);
                UtlDw.dwCntrStr(mWD, xBs, yBs, wBs / 2, hBs, "Yes", mAL.mML.mStrFntDflt);
                UtlDw.dwCntrStr(mWD, xBs + wBs / 2, yBs, wBs / 2, hBs, "No", mAL.mML.mStrFntDflt);
            } else
            if(mBtnTyp == 1)
            {
                if(mStatBtn == 0)
                    UtlDw.dwBfrBtnBs(mWD, xBs, yBs, wBs, hBs);
                else
                    UtlDw.dwAftrBtnBs(mWD, xBs, yBs, wBs, hBs);
                UtlDw.dwCntrStr(mWD, xBs, yBs, wBs, hBs, "Close", mAL.mML.mStrFntDflt);
            } else
            if(mBtnTyp == 3 || mBtnTyp == 4)
            {
                int arrSz = mArrBtns.length;
                for(int i = 0; i < arrSz; i++)
                {
                    ArrBtn arrBtn = mArrBtns[i];
                    if(mStatBtn == 1 && arrBtn.mStatBtn == 1)
                        UtlDw.dwAftrBtnBs(mWD, arrBtn.mX, arrBtn.mY, arrBtn.mW, arrBtn.mH);
                    else
                        UtlDw.dwBfrBtnBs(mWD, arrBtn.mX, arrBtn.mY, arrBtn.mW, arrBtn.mH);
                    if(arrBtn.mTtlStr != null)
                        UtlDw.dwCntrStr(mWD, arrBtn.mX, arrBtn.mY, arrBtn.mW, arrBtn.mH, arrBtn.mTtlStr, mAL.mML.mStrFntDflt);
                    if(arrBtn.mImgId != -1)
                        mWD.dwImg(arrBtn.mImgId, arrBtn.mX + (arrBtn.mW - arrBtn.mIW) / 2, arrBtn.mY + (arrBtn.mH - arrBtn.mIH) / 2);
                }

            }
        }
    }

    public int chkClckIn(int x, int y)
    {
        if(mBtnTyp != 0)
        {
            int xBs = getBtnX();
            int yBs = getBtnY();
            int wBs = getBtnW();
            int hBs = getBtnH();
            if(x < xBs || x > xBs + wBs)
                return 4;
            if(y < yBs || y > yBs + hBs)
                return 4;
            if(mBtnTyp == 2)
            {
                if(x < xBs + wBs / 2)
                    mResYesNoBtn = true;
                else
                    mResYesNoBtn = false;
            } else
            if(mBtnTyp == 3 || mBtnTyp == 4)
            {
                int arrSz = mArrBtns.length;
                for(int i = 0; i < arrSz; i++)
                {
                    if(!mArrBtns[i].chkIn(x, y))
                        continue;
                    mArrBtns[i].mStatBtn = 1;
                    break;
                }

            }
        }
        return super.chkClckIn(x, y);
    }

    public boolean doBck()
    {
        if(mBtnTyp == 2)
            mResYesNoBtn = false;
        mClckX = mX + mW / 2;
        mClckY = mY + mH / 2;
        mNowDoBck = true;
        actClck();
        return true;
    }

    public void addActClck()
    {
        mScn.mClckEnbl = false;
        mAL.mRsrvLst.add(new Rsrv(mAL, 550L) {

            public void addAct()
            {
                mScn.mClckEnbl = true;
                mStatBtn = 0;
                if(mNowDoBck)
                {
                    if(mUseDoBck)
                        actNrmlClck();
                } else
                if(mBtnTyp == 3 || mBtnTyp == 4)
                {
                    int arrSz = mArrBtns.length;
                    for(int i = 0; i < arrSz; i++)
                    {
                        if(mArrBtns[i].mStatBtn != 1)
                            continue;
                        if(mArrBtns[i].doClck())
                            rsrvRmv();
                        mArrBtns[i].mStatBtn = 0;
                        break;
                    }

                } else
                {
                    actNrmlClck();
                }
            }

      
        }
);
    }

    public void actNrmlClck()
    {
        rsrvRmv();
        if(mAddActr != null)
            if(mBtnTyp == 2)
                mAddActr.act(new Object[] {
                    new Boolean(mResYesNoBtn)
                });
            else
                mAddActr.act();
    }

    public void useArrBtn(ArrBtn arg[])
    {
        if(arg == null || arg.length <= 0)
            return;
        int arrSz = arg.length;
        mArrBtns = arg;
        int xBs = getBtnX();
        int yBs = getBtnY();
        int wBs = getBtnW();
        int hBs = getBtnH();
        for(int i = 0; i < arrSz; i++)
        {
            ArrBtn arrBtn = mArrBtns[i];
            int wThis = wBs / arrSz;
            arrBtn.mW = wThis + (i != arrSz - 1 ? 0 : wBs % arrSz);
            arrBtn.mX = xBs + wThis * i;
            arrBtn.mY = yBs;
            arrBtn.mH = hBs;
        }

        mBtnTyp = 3;
    }

    public void useLstBtn(ArrBtn arg[])
    {
        if(arg == null || arg.length <= 0)
            return;
        int arrSz = arg.length;
        mArrBtns = arg;
        int xBs = getBtnX();
        int wBs = getBtnW();
        int hBs = mH - (mFrmMrgn + 6) * 2;
        if(mTtlStr != null && mTtlStr.length() > 0)
        {
            if(mStrSzPer != 100)
            {
                mStrFnt.fntSz = (mAL.mML.l.lghtBox_fntSz * mStrSzPer) / 100;
                mStrFnt.lnH = (mAL.mML.l.lghtBox_fntSz * mStrSzPer) / 100;
            }
            int strH = mWD.getStrH(mTtlStr, mStrFnt, mW - (mFrmMrgn + mFrmPddng) * 2);
            hBs -= strH + mFrmPddng * 2;
            if(mStrSzPer != 100)
            {
                mStrFnt.fntSz = mAL.mML.l.lghtBox_fntSz;
                mStrFnt.lnH = mAL.mML.l.lghtBox_fntSz;
            }
        }
        hBs = (hBs / arrSz) * arrSz;
        mBtnH = hBs;
        int yBs = getBtnY();
        for(int i = 0; i < arrSz; i++)
        {
            ArrBtn arrBtn = mArrBtns[i];
            int hThis = hBs / arrSz;
            arrBtn.mH = hThis + (i != arrSz - 1 ? 0 : hBs % arrSz);
            arrBtn.mY = yBs + hThis * i;
            arrBtn.mX = xBs;
            arrBtn.mW = wBs;
        }

        mBtnTyp = 4;
    }

    public void useDwTbl(String strArr[][], int wRateArr[], int hRateArr[])
    {
        int x = mX + mFrmMrgn;
        int y = mY + mFrmMrgn;
        if(mTtlStr.length() > 0)
            y += mWD.getStrH(mTtlStr, mStrFnt, mW - (mFrmMrgn + mFrmPddng) * 2) + mFrmPddng;
        int w = mW - mFrmMrgn * 2;
        int h = mH - y - mFrmMrgn;
        if(mBtnTyp != 0)
            h -= (getBtnH() + 6) - 2;
        mDwTbl = new DwTbl(mAL, x, y, w, h, strArr, wRateArr, hRateArr);
    }

    public int getBtnX()
    {
        return mX + mFrmMrgn + 6;
    }

    public int getBtnY()
    {
        return (mY + mH) - mFrmMrgn - 6 - mBtnH;
    }

    public int getBtnW()
    {
        return mW - mFrmMrgn * 2 - 12;
    }

    public int getBtnH()
    {
        return mBtnH;
    }

    public void finish()
    {
        super.finish();
        mScn = null;
        mTtlStr = null;
        mAddActr = null;
        mAddDrwrPreStr = null;
        mAddDrwrPstStr = null;
        if(mDwTbl != null)
            mDwTbl.finish();
        mDwTbl = null;
    }

    public Scn mScn;
    private boolean mFrstDo;
    public String mTtlStr;
    private com.crocro.wrp.wrp.WrpDw.StrFntOpt mStrFnt = new com.crocro.wrp.wrp.WrpDw.StrFntOpt() {

            
            {
         
                fntSz = mAL.mML.l.lghtBox_fntSz;
                lnH = mAL.mML.l.lghtBox_fntSz;
            }
    }
;
    private int mFrmMrgn;
    private int mFrmPddng;
    public Actr mAddActr;
    public Drwr mAddDrwrPreStr;
    public Drwr mAddDrwrPstStr;
    public static final int BTN_MRGN = 6;
    public boolean mUseDoBck;
    public boolean mNowDoBck;
    public static final int BTN_NOT = 0;
    public static final int BTN_CLS = 1;
    public static final int BTN_YES_NO = 2;
    public static final int BTN_ARR = 3;
    public static final int BTN_LST = 4;
    public int mBtnTyp;
    public int mBtnH;
    private static final String BTN_CLS_S = "Close";
    private static final String BTN_YN_S_YES = "Yes";
    private static final String BTN_YN_S_NO = "No";
    public boolean mResYesNoBtn;
    private ArrBtn mArrBtns[];
    private DwTbl mDwTbl;
    public int mBgTrns;
    public int mBgColR;
    public int mBgColG;
    public int mBgColB;
    public int mStrColR;
    public int mStrColG;
    public int mStrColB;
    public int mStrSzPer;

}
