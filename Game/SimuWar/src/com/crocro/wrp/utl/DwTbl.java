// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DwTbl.java

package com.crocro.wrp.utl;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.bs.SF;
import com.crocro.wrp.mng.MngLayout;
import com.crocro.wrp.wrp.WrpDw;

// Referenced classes of package com.crocro.wrp.utl:
//            UtlDw

public class DwTbl
{

    public DwTbl(AppLoop al, int x, int y, int w, int h, String strArr[][], int wRateArr[], 
            int hRateArr[])
    {
        mCellWPddng = 10;
        mCellHPddng = 3;
        mAL = al;
        mWD = mAL.mWD;
        mX = x;
        mY = y;
        mW = w;
        mH = h;
        mStrArr = strArr;
        mWRateArr = wRateArr;
        mHRateArr = hRateArr;
        if(mHRateArr == null)
        {
            mHRateArr = new int[mStrArr.length];
            for(int i = 0; i < mHRateArr.length; i++)
                mHRateArr[i] = 1;

        }
        init();
    }

    public void init()
    {
        mWDIn = mWD.getNew();
        mWDIn.mkBuf(0, mW, mH);
        int sumDots = 0;
        int tblX = 2;
        int tblY = 2;
        int tblW = mW - 4;
        int tblH = mH - 4;
        mWArr = new int[mWRateArr.length];
        int sumRate = 0;
        for(int i = 0; i < mWRateArr.length; i++)
            sumRate += mWRateArr[i];

        if(sumRate > 0)
        {
            sumDots = 0;
            for(int i = 0; i < mWRateArr.length; i++)
            {
                mWArr[i] = (tblW * mWRateArr[i]) / sumRate;
                sumDots += mWArr[i];
            }

            mWArr[mWRateArr.length - 1] += tblW - sumDots;
        }
        mHArr = new int[mHRateArr.length];
        sumRate = 0;
        for(int i = 0; i < mHRateArr.length; i++)
            sumRate += mHRateArr[i];

        if(sumRate > 0)
        {
            sumDots = 0;
            for(int i = 0; i < mHRateArr.length; i++)
            {
                mHArr[i] = (tblH * mHRateArr[i]) / sumRate;
                sumDots += mHArr[i];
            }

        }
        mDwPrm = new int[mStrArr.length][][];
        int hSum = tblY;
        for(int i = 0; i < mStrArr.length; i++)
            if(mStrArr[i] != null)
            {
                mDwPrm[i] = new int[mStrArr[i].length][IN_MAX];
                int wSum = tblX;
                for(int j = 0; j < mStrArr[i].length; j++)
                {
                    mDwPrm[i][j][IN_CELL_X] = wSum;
                    mDwPrm[i][j][IN_CELL_Y] = hSum;
                    mDwPrm[i][j][IN_CELL_W] = mWArr[j];
                    mDwPrm[i][j][IN_CELL_H] = mHArr[i];
                    mDwPrm[i][j][IN_STR_X] = mDwPrm[i][j][IN_CELL_X] + mCellWPddng;
                    mDwPrm[i][j][IN_STR_Y] = mDwPrm[i][j][IN_CELL_Y] + mCellHPddng;
                    mDwPrm[i][j][IN_STR_W] = mDwPrm[i][j][IN_CELL_W] - mCellWPddng * 2;
                    mDwPrm[i][j][IN_STR_H] = mDwPrm[i][j][IN_CELL_H] - mCellHPddng * 2;
                    if(mDwPrm[i][j][IN_STR_W] < 0)
                        mDwPrm[i][j][IN_STR_W] = 0;
                    if(mDwPrm[i][j][IN_STR_H] < 0)
                        mDwPrm[i][j][IN_STR_H] = 0;
                    if(mStrArr[i].length == 1)
                    {
                        mDwPrm[i][j][IN_CELL_W] = tblW;
                        mDwPrm[i][j][IN_STR_W] = mDwPrm[i][j][IN_CELL_W] - mCellWPddng * 2;
                    }
                    mDwPrm[i][j][IN_FNT_OPT] = mAL.mML.mStrFntOptArr.length - 1;
                    boolean enbl = false;
                    String s = mStrArr[i][j];
                    if(s.indexOf("bgHighLght$") == 0)
                        s = s.substring("bgHighLght$".length());
                    else
                    if(s.indexOf("bgLght$") == 0)
                        s = s.substring("bgLght$".length());
                    int len = mAL.mML.mStrFntOptArr.length;
                    for(int f = 0; f < len; f++)
                    {
                        int wStr = mWD.getStrW(s, mAL.mML.mStrFntOptArr[f], 999);
                        if(wStr > mDwPrm[i][j][IN_STR_W])
                            continue;
                        mDwPrm[i][j][IN_FNT_OPT] = f;
                        enbl = true;
                        break;
                    }

                    if(!enbl)
                    {
                        for(int f = 0; f < len; f++)
                        {
                            int wStr = mWD.getStrW(s, mAL.mML.mStrFntOptArr[f], mDwPrm[i][j][IN_STR_W]);
                            int hStr = mWD.getStrH(s, mAL.mML.mStrFntOptArr[f], mDwPrm[i][j][IN_STR_W]);
                            if(wStr > mDwPrm[i][j][IN_STR_W] || hStr > mDwPrm[i][j][IN_STR_H])
                                continue;
                            mDwPrm[i][j][IN_FNT_OPT] = f;
                            break;
                        }

                    }
                    int hStr = mWD.getStrH(s, mAL.mML.mStrFntOptArr[mDwPrm[i][j][IN_FNT_OPT]], mDwPrm[i][j][IN_STR_W]);
                    mDwPrm[i][j][IN_STR_Y] = mDwPrm[i][j][IN_CELL_Y] + (mDwPrm[i][j][IN_CELL_H] - hStr) / 2;
                    wSum += mWArr[j];
                }

                hSum += mHArr[i];
            }

        dwIn();
    }

    public void dw()
    {
        mWD.dwImg(mWDIn, 0, mX, mY);
    }

    public void dwBG(WrpDw wd, int x, int y, int w, int h)
    {
        wd.useCol(WrpDw.COL_BTN_BFR_DARK);
        wd.flRct(x, y, w, h);
    }

    public void dwIn()
    {
        mWDIn.tgtBuf(0);
        dwBG(mWDIn, 0, 0, mW, mH);
        for(int i = 0; i < mStrArr.length; i++)
            if(mStrArr[i] != null)
            {
                for(int j = 0; j < mStrArr[i].length; j++)
                {
                    String s = mStrArr[i][j];
                    int bgTyp = 0;
                    if(s.indexOf("bgHighLght$") == 0)
                    {
                        s = s.substring("bgHighLght$".length());
                        bgTyp = 1;
                    } else
                    if(s.indexOf("bgLght$") == 0)
                    {
                        s = s.substring("bgLght$".length());
                        bgTyp = 2;
                    }
                    if(bgTyp == 0)
                        UtlDw.dwTblCellBs(mWDIn, mDwPrm[i][j][IN_CELL_X], mDwPrm[i][j][IN_CELL_Y], mDwPrm[i][j][IN_CELL_W], mDwPrm[i][j][IN_CELL_H]);
                    else
                    if(bgTyp == 1)
                        UtlDw.dwTblCellBs(mWDIn, mDwPrm[i][j][IN_CELL_X], mDwPrm[i][j][IN_CELL_Y], mDwPrm[i][j][IN_CELL_W], mDwPrm[i][j][IN_CELL_H], WrpDw.COL_BTN_AFTR_LGHT);
                    else
                    if(bgTyp == 2)
                        UtlDw.dwTblCellBs(mWDIn, mDwPrm[i][j][IN_CELL_X], mDwPrm[i][j][IN_CELL_Y], mDwPrm[i][j][IN_CELL_W], mDwPrm[i][j][IN_CELL_H], WrpDw.COL_BTN_AFTR);
                    mWDIn.dwStr(s, mDwPrm[i][j][IN_STR_X], mDwPrm[i][j][IN_STR_Y], mDwPrm[i][j][IN_STR_W], mAL.mML.mStrFntOptArr[mDwPrm[i][j][IN_FNT_OPT]], null);
                }

            }

    }

    public void finish()
    {
        mAL = null;
        mWD = null;
        mStrArr = null;
        mWRateArr = null;
        mHRateArr = null;
        mWArr = null;
        mHArr = null;
        mDwPrm = null;
        if(mWDIn != null)
            mWDIn.flshBuf(0);
        mWDIn = null;
    }

    private AppLoop mAL;
    private WrpDw mWD;
    private WrpDw mWDIn;
    private int mX;
    private int mY;
    private int mW;
    private int mH;
    private String mStrArr[][];
    private int mWRateArr[];
    private int mHRateArr[];
    private int mWArr[];
    private int mHArr[];
    private static final int IN_CELL_X = SF.seq(0);
    private static final int IN_CELL_Y = SF.seq();
    private static final int IN_CELL_W = SF.seq();
    private static final int IN_CELL_H = SF.seq();
    private static final int IN_STR_X = SF.seq();
    private static final int IN_STR_Y = SF.seq();
    private static final int IN_STR_W = SF.seq();
    private static final int IN_STR_H = SF.seq();
    private static final int IN_FNT_OPT = SF.seq();
    private static final int IN_MAX = SF.seq();
    private int mDwPrm[][][];
    public static final int TBL_PDDNG = 2;
    private int mCellWPddng;
    private int mCellHPddng;
    public static final String BG_STR_HIGH_LGHT = "bgHighLght$";
    public static final String BG_STR_LGHT = "bgLght$";
    public static final int BG_TYP_NRML = 0;
    public static final int BG_TYP_HIGH_LGHT = 1;
    public static final int BG_TYP_LGHT = 2;

}
