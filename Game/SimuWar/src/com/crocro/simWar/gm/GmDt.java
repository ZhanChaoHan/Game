// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GmDt.java

package com.crocro.simWar.gm;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.wrp.bs.F;
import com.crocro.wrp.bs.SF;
import com.crocro.wrp.utl.UtlTool;
import com.crocro.wrp.wrp.WrpEnv;
import com.crocro.wrp.wrp.WrpFile;

public class GmDt
    implements F
{

    public GmDt(AppLoopSimWar al)
    {
        mAL = null;
        mAL = al;
        String tmpStr = UtlTool.bToS(mAL.mWF.getR("res/dat/untI.dat"));
        mUntI = UtlTool.sToI2dAr(tmpStr);
        tmpStr = UtlTool.bToS(mAL.mWF.getR("res/dat/untS.dat"));
        mUntS = UtlTool.sToS2dAr(tmpStr);
        mUntMax = mUntI.length >= mUntS.length ? mUntS.length : mUntI.length;
        for(int i = 0; i < mUntMax; i++)
        {
            if(mUntI[i].length < UNT_I_MAX)
                mAL.mWE.dbgErr((new StringBuilder("gGD_UntI is illegal. line : ")).append(i).toString());
            if(mUntS[i].length < 2)
                mAL.mWE.dbgErr((new StringBuilder("gGD_UntS is illegal. line : ")).append(i).toString());
        }

        tmpStr = UtlTool.bToS(mAL.mWF.getR("res/dat/sklI.dat"));
        mSklI = UtlTool.sToI2dAr(tmpStr);
        tmpStr = UtlTool.bToS(mAL.mWF.getR("res/dat/sklS.dat"));
        mSklS = UtlTool.sToS2dAr(tmpStr);
        mSklMax = mSklI.length >= mSklS.length ? mSklS.length : mSklI.length;
        for(int i = 0; i < mSklMax; i++)
        {
            if(mSklI[i].length < SKL_I_MAX)
                mAL.mWE.dbgErr((new StringBuilder("gGD_SklI is illegal. line : ")).append(i).toString());
            if(mSklS[i].length < 3)
                mAL.mWE.dbgErr((new StringBuilder("gGD_SklS is illegal. line : ")).append(i).toString());
        }

    }

    private AppLoopSimWar mAL;
    public static final String UNT_I_PTH = "res/dat/untI.dat";
    public static final String UNT_S_PTH = "res/dat/untS.dat";
    public int mUntI[][];
    public String mUntS[][];
    public int mUntMax;
    public static final int UNT_I_HP = SF.seq(0);
    public static final int UNT_I_DF = SF.seq();
    public static final int UNT_I_MV = SF.seq();
    public static final int UNT_I_TCH = SF.seq();
    public static final int UNT_I_FLY = SF.seq();
    public static final int UNT_I_PSS = SF.seq();
    public static final int UNT_I_ATTR = SF.seq();
    public static final int UNT_I_SKL1 = SF.seq();
    public static final int UNT_I_SKL2 = SF.seq();
    public static final int UNT_I_SKL3 = SF.seq();
    public static final int UNT_I_SKL4 = SF.seq();
    public static final int UNT_I_SKL5 = SF.seq();
    public static final int UNT_I_MAX = SF.seq();
    public static final int UNT_I_SKL_MAX = 5;
    public static final int ATTR_NRML = 0;
    public static final int ATTR_MGC = 1;
    public static final int ATTR_HLY = 2;
    public static final int UNT_S_FNM = 0;
    public static final int UNT_S_NM_J = 1;
    public static final int UNT_S_MAX = 2;
    public static final String SKL_I_PTH = "res/dat/sklI.dat";
    public static final String SKL_S_PTH = "res/dat/sklS.dat";
    public int mSklI[][];
    public String mSklS[][];
    public int mSklMax;
    public static final int SKL_I_TGT = SF.seq(0);
    public static final int SKL_I_AT = SF.seq();
    public static final int SKL_I_RNG = SF.seq();
    public static final int SKL_I_SCP = SF.seq();
    public static final int SKL_I_ND_MTR = SF.seq();
    public static final int SKL_I_DF = SF.seq();
    public static final int SKL_I_ATTR = SF.seq();
    public static final int SKL_I_DRN = SF.seq();
    public static final int SKL_I_EX_TM = SF.seq();
    public static final int SKL_I_EX_DF = SF.seq();
    public static final int SKL_I_EX_MV = SF.seq();
    public static final int SKL_I_EX_AT = SF.seq();
    public static final int SKL_I_ADD_FLY = SF.seq();
    public static final int SKL_I_ADD_PSS = SF.seq();
    public static final int SKL_I_FLY_DIR_MV = SF.seq();
    public static final int SKL_I_FLY_RT = SF.seq();
    public static final int SKL_I_FLY_BLNK = SF.seq();
    public static final int SKL_I_FLY_SPRD = SF.seq();
    public static final int SKL_I_FLY_HRZNTL = SF.seq();
    public static final int SKL_I_HIT_SPRD = SF.seq();
    public static final int SKL_I_HIT_4WAY = SF.seq();
    public static final int SKL_I_HIT_RT = SF.seq();
    public static final int SKL_I_HIT_PLLR = SF.seq();
    public static final int SKL_I_HIT_FOUT = SF.seq();
    public static final int SKL_I_HIT_FINOUT = SF.seq();
    public static final int SKL_I_HIT_BCK = SF.seq();
    public static final int SKL_I_MAX = SF.seq();
    public static final int SKL_S_FNM_FLY = 0;
    public static final int SKL_S_FNM_HIT = 1;
    public static final int SKL_S_NM_J = 2;
    public static final int SKL_S_MAX = 3;
    public static final int SKL_TGT_ENE = 0;
    public static final int SKL_TGT_MY = 1;

}
