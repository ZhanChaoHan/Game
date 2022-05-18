package com.crocro.simWar.app;

import com.crocro.simWar.gm.GmDt;
import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.bs.SF;

public class V extends com.crocro.wrp.app.V
{
    public class GD extends com.crocro.wrp.app.V.GD
    {

        public int stgNow;
        public int stgSv;
        public int dfclt;
        public int vwActLog;
   
        public GD()
        {
     
            vwActLog = 1;
        }
    }

    public class GDA extends com.crocro.wrp.app.V.GDA
    {

        public int stgEnbl[];
        public int stgWinTm[];
        public int stgWinScr[];
        public int stgWinNo[];


        public GDA()
        {
 
        }
    }

    public class LD extends com.crocro.wrp.app.V.LD
    {

        public int gmOrgnX;
        public int gmOrgnY;
        public int gmStrt;
        public int gmElps;
   

        public LD()
        {
     
         
        }
    }

    public class LDA extends com.crocro.wrp.app.V.LDA
    {

        public int untDat[];
        public int untStts[];
   
        public LDA()
        {

        }
    }


    public V(AppLoop al)
    {
        super(al);
        mStgSz = 0;
        mGmRstrt = false;
        mGD = null;
        mSelUntTypArr = null;
    }

    public void init()
    {
        super.gd = gd = new GD();
        super.gda = gda = new GDA();
        super.ld = ld = new LD();
        super.lda = lda = new LDA();
        sd = new com.crocro.wrp.app.V.SD();
        sda = new com.crocro.wrp.app.V.SDA();
    }

    public GD gd;
    public GDA gda;
    public LD ld;
    public LDA lda;
    public static final int SCN_TTL_GM;
    public static final int SCN_SEL_STG = SF.seq();
    public static final int SCN_BFR_GM = SF.seq();
    public static final int SCN_STG_STRT = SF.seq();
    public static final int SCN_GM = SF.seq();
    public static final int SCN_STG_CLR = SF.seq();
    public static final int SCN_WIN_GM = SF.seq();
    public static final int FLW_MN_SEL_STG_INIT;
    public static final int FLW_MN_SEL_STG_WAIT = SF.seq();
    public static final int FLW_MN_RCRD_INIT = SF.seq();
    public static final int FLW_MN_RCRD_WAIT = SF.seq();
    public static final int FLW_MN_UNT_INF_INIT = SF.seq();
    public static final int FLW_MN_UNT_INF_WAIT = SF.seq();
    public static final int FLW_MN_OPT_INIT = SF.seq();
    public static final int FLW_MN_OPT_WAIT = SF.seq();
    public static final int FLW_MN_SND_VOL_INIT = SF.seq();
    public static final int FLW_MN_SND_VOL_WAIT = SF.seq();
    public static final int FLW_MN_DFCLT_INIT = SF.seq();
    public static final int FLW_MN_DFCLT_WAIT = SF.seq();
    public static final int FLW_MN_VW_MES_INIT = SF.seq();
    public static final int FLW_MN_VW_MES_WAIT = SF.seq();
    public static final int FLW_MN_DFCLT_BFR_GM_INIT = SF.seq();
    public static final int FLW_MN_DFCLT_BFR_GM_WAIT = SF.seq();
    public static final int FLW_MN_SEL_UNT_INIT = SF.seq();
    public static final int FLW_MN_SEL_UNT_WAIT = SF.seq();
    public static final int LYR_UNT_DST = 1;
    public static final int LYR_UNT = 2;
    public static final int LYR_EF_W = 3;
    public static final int LYR_EF_DTH = 4;
    public static final int LYR_RNG = 5;
    public static final int LYR_GM_TOP = 6;
    public int mStgSz;
    public boolean mGmRstrt;
    public GmDt mGD;
    public int mSelUntTypArr[];
    public static final String SEL_UNT_FNM = "selUnt.dat";

    static 
    {
        SCN_TTL_GM = SF.seq(SCN_USR_STRT);
        FLW_MN_SEL_STG_INIT = SF.seq(FLW_USR_STRT);
    }
}
