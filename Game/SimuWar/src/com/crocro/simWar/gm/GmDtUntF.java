// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GmDtUntF.java

package com.crocro.simWar.gm;

import com.crocro.wrp.bs.SF;

public interface GmDtUntF
{

    public static final int ID_MY_MAX = 32;
    public static final int ID_ENE_MAX = 100;
    public static final int ID_MAX = 132;
    public static final int UD_ID = 0;
    public static final int UD_TYP = 1;
    public static final int UD_X = 2;
    public static final int UD_Y = 3;
    public static final int UD_MY_MAX = 4;
    public static final int UD_RNG_SRCH = 4;
    public static final int UD_RNG_MV = 5;
    public static final int UD_PTRL0_X = 6;
    public static final int UD_PTRL0_Y = 7;
    public static final int UD_PTRL1_X = 8;
    public static final int UD_PTRL1_Y = 9;
    public static final int UD_PTRL2_X = 10;
    public static final int UD_PTRL2_Y = 11;
    public static final int UD_PTRL3_X = 12;
    public static final int UD_PTRL3_Y = 13;
    public static final int UD_PTRL4_X = 14;
    public static final int UD_PTRL4_Y = 15;
    public static final int UD_FLLW_ID = 16;
    public static final int UD_ENE_MAX = 17;
    public static final int UD_MAX = 17;
    public static final int US_ENBL = SF.seq(0);
    public static final int US_MV_X_NOW = SF.seq();
    public static final int US_MV_Y_NOW = SF.seq();
    public static final int US_MV_X_OLD = SF.seq();
    public static final int US_MV_Y_OLD = SF.seq();
    public static final int US_MV_X_TGT = SF.seq();
    public static final int US_MV_Y_TGT = SF.seq();
    public static final int US_DST_X_NOW = SF.seq();
    public static final int US_DST_Y_NOW = SF.seq();
    public static final int US_DST_X_OLD = SF.seq();
    public static final int US_DST_Y_OLD = SF.seq();
    public static final int US_PTRL_TGT = SF.seq();
    public static final int US_MV_RNG_OVR = SF.seq();
    public static final int US_ELPS_MV = SF.seq();
    public static final int US_ELPS_ACT = SF.seq();
    public static final int US_HP = SF.seq();
    public static final int US_MTR = SF.seq();
    public static final int US_SEL_SKL = SF.seq();
    public static final int US_EX_DF_TM = SF.seq();
    public static final int US_EX_DF_PW = SF.seq();
    public static final int US_EX_MV_TM = SF.seq();
    public static final int US_EX_MV_PW = SF.seq();
    public static final int US_EX_AT_TM = SF.seq();
    public static final int US_EX_AT_PW = SF.seq();
    public static final int US_EX_FLY_TM = SF.seq();
    public static final int US_EX_FLY_PW = SF.seq();
    public static final int US_EX_PSS_TM = SF.seq();
    public static final int US_EX_PSS_PW = SF.seq();
    public static final int US_RST_CNTR = SF.seq();
    public static final int US_TM_DMG_1ST = SF.seq();
    public static final int US_TM_DMG_DTH = SF.seq();
    public static final int US_TM_ACT_LST = SF.seq();
    public static final int US_DMG_SUM = SF.seq();
    public static final int US_DMG_CNT = SF.seq();
    public static final int US_MAX = SF.seq();
    public static final int US_EX_TM_STRT = US_EX_DF_TM;
    public static final int US_EX_PW_STRT = US_EX_DF_PW;
    public static final int US_EX_TYP_SZ = 2;
    public static final int US_EX_SZ = (US_EX_PSS_TM - US_EX_TM_STRT) / 2 + 1;
    public static final int PTRL_TGT_MAX = 4;
    public static final int HP_MAX = 999;
    public static final int MV_MAX = 800;
    public static final int RST_CNTR_ADD = 5;
    public static final int MV_RT = 3;
    public static final double DFCLT[][] = {
        {
            0.33000000000000002D, 1.0D
        }, {
            0.66000000000000003D, 1.0D
        }, {
            1.0D, 1.0D
        }, {
            1.1000000000000001D, 1.5D
        }, {
            1.2D, 2D
        }, {
            1.3D, 2.5D
        }, {
            1.3999999999999999D, 3D
        }, {
            1.5D, 3.5D
        }
    };
    public static final int DFCLT_MV_RT_ENE = 0;
    public static final int DFCLT_MTR_RT_ENE = 1;
    public static final int TGT_NO = -1;
    public static final int TGT_TEHY = 0;
    public static final int TGT_WE = 1;
    public static final int TGT_TEHY_AUTO = 2;
    public static final int TGT_WE_AUTO = 3;

}
