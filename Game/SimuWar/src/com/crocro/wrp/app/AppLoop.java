// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AppLoop.java

package com.crocro.wrp.app;

import com.crocro.wrp.bs.F;
import com.crocro.wrp.bs.SF;
import com.crocro.wrp.mng.*;
import com.crocro.wrp.scn.MngDlg;
import com.crocro.wrp.scn.Scn;
import com.crocro.wrp.utl.*;
import com.crocro.wrp.wrp.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;

// Referenced classes of package com.crocro.wrp.app:
//            V

public class AppLoop
    implements F
{

    public AppLoop()
    {
        mAppTtl = "AppLoop";
        mAppVer = "1.0.0";
        mMC = null;
        mML = null;
        mWE = null;
        mMS = null;
        mWD = null;
        mWS = null;
        mWF = null;
        mWN = null;
        mV = null;
        mMngLng = null;
        mBsOpt = null;
        mBsLngOpt = null;
        mMngSnd = null;
        mTmStrt = System.currentTimeMillis();
        mTmOld = mTmStrt;
        mTmNow = mTmOld - 40L;
        mFrstWt = true;
        mLckStrt = true;
        mLckUpdt = false;
        mScn = null;
        mScnEnd = null;
        mRsrvLst = new ArrayList();
        mDfltBGDrwr = new Drwr() {

            public void dw()
            {
                mWD.dwImg(AppLoop.IMG_ID_CMN_BCK_RNDR, 0, 0);
            }

       
        }
;
        mLcncLck = false;
        mDbgFlgVw = false;
        mSB = new StringBuilder();
        mNFDgt2 = null;
        mV = new V(this);
        mV.init();
        mML = new MngLayout(this);
        mMngSnd = new MngSnd(this);
    }

    public void setMngCnvs(MngCnvs mngCnvs)
    {
        mMC = mngCnvs;
    }

    public void setMngLayout(MngLayout mngLayout)
    {
        mML = mngLayout;
    }

    public void setMngSnd(MngSnd mngSnd)
    {
        mMngSnd = mngSnd;
    }

    public void setWrpEnv(WrpEnv wrpEnv)
    {
        mWE = wrpEnv;
    }

    public void setWrpDw(WrpDw wrpDw)
    {
        mWD = wrpDw;
    }

    public void setWrpSnd(WrpSnd wrpSnd)
    {
        mWS = wrpSnd;
    }

    public void setWrpFile(WrpFile wrpFile)
    {
        mWF = wrpFile;
    }

    public void setWrpNet(WrpNet wrpNet)
    {
        mWN = wrpNet;
    }

    public boolean mngWt()
    {
        if(mLckUpdt)
        {
            try
            {
                Thread.sleep(5L);
            }
            catch(Exception ex) {
            	ex.printStackTrace();
            }
            return true;
        }
        if(!mFrstWt)
            try
            {
                Thread.sleep(5L);
            }
            catch(Exception ex) {
            	ex.printStackTrace();
            }
        mFrstWt = false;
        mTmNow = System.currentTimeMillis();
        if(mTmNow < mTmOld + 40L)
        {
            return true;
        } else
        {
            mTmOldTmp = mTmNow;
            return false;
        }
    }

    public void strtUpdt()
    {
        mLckUpdt = true;
    }

    public void endUpdt()
    {
        mLckUpdt = false;
    }

    public boolean chkUpdt()
    {
        return mLckUpdt;
    }

    public void mngWtPst()
    {
        mTmOld = mTmOldTmp;
        mFrstWt = true;
    }

    public void strt()
    {
        mMS = new MngScn(mV, this);
        mML.rfrctOpt(mML.l);
        mMngDlg = new MngDlg(this);
        mMngLng = new MngLng(this, null);
        mBsOpt = new MngOpt(this, "res/dat/bs.dat");
        mBsLngOpt = new MngOpt(this, "res/dat/bsLng.dat");
        mWE.setTtl(mBsOpt.getS("appNm"));
        mDbgFlgVw = mBsOpt.getI("dbgVw") == 1;
        if(mDbgFlgVw)
        {
            mNFDgt2 = NumberFormat.getInstance();
            mNFDgt2.setMaximumFractionDigits(2);
        }
        initGD();
        if(mV.ldDt(0))
        {
            int _tmp = mV.gd.qckSv;
        }
        mMngSnd.initSE();
        if(mV.gd.autoHrdVol == 1)
            mWS.doAutoHrdVol();
        if(mMS.getScn(0) == V.SCN_NULL)
            mMS.setNxtScn(V.SCN_TTL);
        if(mV.gd.sndVol == -1)
        {
            mV.gd.sndVol = mBsOpt.getI("sndVol");
            if(mV.gd.sndVol < 0)
                mV.gd.sndVol = 50;
        }
        mMngSnd.chngVol(mV.gd.sndVol);
        mLckStrt = false;
    }

    public void initCmnImg()
    {
        mWD.mkBuf(IMG_ID_CMN_BCK_PRTS, "cmn/bck.png");
        mWD.mkBuf(IMG_ID_CMN_BCK_RNDR, mMC.getScrW(), mMC.getScrH());
        mWD.tgtBuf(IMG_ID_CMN_BCK_RNDR);
        UtlDw.dwTile(mWD, IMG_ID_CMN_BCK_PRTS, 0, 0, mMC.getScrW(), mMC.getScrH());
        if(mML.l.vwTyp == 1)
            mWD.mkBuf(IMG_ID_CMN_TAB_OPT, MngLayout.dir2sml("cmn/tab_opt.png"));
        else
            mWD.mkBuf(IMG_ID_CMN_TAB_OPT, "cmn/tab_opt.png");
        mWD.tgtBuf(0);
    }

    public void initGD()
    {
    }

    public void paint()
    {
        if(mLckStrt)
            return;
        if(mWE.chkWaitDlg())
            return;
        mngAlwys();
        if(mDbgFlgVw)
            dwDbgVw();
        mWD.bufOnceToRt();
    }

    public void dwDbgVw()
    {
        int h = 3 * mML.mStrFntSml.lnH;
        int y = mMC.getScrH() - h;
        long memTtl = Runtime.getRuntime().totalMemory();
        long memFree = Runtime.getRuntime().freeMemory();
        long memUse = memTtl - memFree;
        mWD.tgtBuf(0);
        mWD.setTrns(50);
        mWD.useCol(WrpDw.COL_WHITE);
        mWD.flRct(0, y, mMC.getScrW(), h);
        mWD.setTrns(100);
        mSB.setLength(0);
        mSB.append("TTL : ").append(mNFDgt2.format((double)memTtl / 1024D / 1024D)).append("\n").append("USE : ").append(mNFDgt2.format((double)memUse / 1024D / 1024D)).append("\n").append("PER : ").append(mNFDgt2.format((100D * (double)memUse) / (double)memTtl));
        mWD.dwStrRaw(mSB.toString(), 0, y, 999, mML.mStrFntSml, null);
    }

    public void mngFlw()
    {
        mScn.doFlw(mMS.getFlw(0));
        System.gc();
    }

    public void mngAlwys()
    {
        if(mScnEnd != null)
        {
            mScnEnd.finish();
            mScnEnd = null;
            System.gc();
        }
        mScn.doAlwys(mMS.getFlw(0));
    }

    public void rldScn(int scnOld, int scnNew)
    {
        if(scnOld != -1 && mScn != null)
        {
            mScnEnd = mScn;
            mWD.flshBuf(IMG_ID_CMN_MAX);
        }
        rldUnqScn(scnNew);
    }

    public void rldUnqScn(int i)
    {
    }

    public void doRsrv()
    {
        for(int i = 0; i < mRsrvLst.size(); i++)
        {
            Rsrv rsrv = (Rsrv)mRsrvLst.get(i);
            if(!rsrv.act())
                continue;
            rsrv.finish();
            mRsrvLst.remove(i);
            break;
        }

    }

    public void clrClck()
    {
        mMC.rstClckLst();
    }

    public String mAppTtl;
    public String mAppVer;
    public MngCnvs mMC;
    public MngLayout mML;
    public WrpEnv mWE;
    public MngScn mMS;
    public WrpDw mWD;
    public WrpSnd mWS;
    public WrpFile mWF;
    public WrpNet mWN;
    public V mV;
    public MngLng mMngLng;
    public MngOpt mBsOpt;
    public static final String BS_OPT_PTH = "res/dat/bs.dat";
    public MngOpt mBsLngOpt;
    public static final String BS_LNG_OPT_PTH = "res/dat/bsLng.dat";
    public MngSnd mMngSnd;
    public final int SKIP_TIME = 5;
    public final int WAIT_TIME = 40;
    public long mTmStrt;
    public long mTmOld;
    public long mTmNow;
    public long mTmOldTmp;
    private boolean mFrstWt;
    private boolean mLckStrt;
    private boolean mLckUpdt;
    public MngDlg mMngDlg;
    public Scn mScn;
    public Scn mScnEnd;
    public ArrayList mRsrvLst;
    public Drwr mDfltBGDrwr;
    public boolean mLcncLck;
    private boolean mDbgFlgVw;
    private StringBuilder mSB;
    private NumberFormat mNFDgt2;
    public static final int IMG_ID_CMN_BCK_PRTS = 2 + SF.seq(0);
    public static final int IMG_ID_CMN_BCK_RNDR = 2 + SF.seq();
    public static final int IMG_ID_CMN_TAB_OPT = 2 + SF.seq();
    public static final int IMG_ID_CMN_MAX = 2 + SF.seq();
    private final String IMG_FNM_CMN_BCK = "cmn/bck.png";
    private final String IMG_FNM_CMN_TAB_OPT = "cmn/tab_opt.png";
    public static Random RNDM = new Random();

}
