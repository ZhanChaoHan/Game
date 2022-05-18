// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ScnSelStg.java

package com.crocro.simWar.scn;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V;
import com.crocro.simWar.clckbl.TabOpt;
import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.clckbl.DlgMnItmImg;
import com.crocro.wrp.clckbl.StrBtn;
import com.crocro.wrp.mng.*;
import com.crocro.wrp.scn.MngDlg;
import com.crocro.wrp.utl.*;
import com.crocro.wrp.wrp.WrpDw;
import java.util.ArrayList;

// Referenced classes of package com.crocro.simWar.scn:
//            MnOpt, MnRcrd

public class ScnSelStg extends MngDlg
{

    public ScnSelStg(AppLoopSimWar al)
    {
        super(al);
        mAL = null;
        IMG_ID_STG_BUF = AppLoopSimWar.IMG_ID_CMN_MAX + 16;
        IMG_ID_STG_THMB = AppLoopSimWar.IMG_ID_CMN_MAX + 17;
        mMnOpt = null;
        mMnRcrd = null;
    }

    public void init()
    {
        mAL = (AppLoopSimWar)super.mmAL;
        super.init();
        if(mMnOpt == null)
            mMnOpt = new MnOpt(mAL, null);
        if(mMnRcrd == null)
            mMnRcrd = new MnRcrd(mAL);
    }

    public void doFlw(int flw)
    {
        if(flw == V.FLW_INIT)
            flwInit();
        else
        if(!mMnOpt.chkFlwInit(flw))
            mMnRcrd.chkFlwInit(flw);
    }

    public void doAlwys(int flw)
    {
        if(flw == V.FLW_WAIT)
            alwysWait();
        else
        if(!mMnOpt.chkAlwysWait(flw))
            mMnRcrd.chkAlwysWait(flw);
    }

    public void flwInit()
    {
        init();
        initDlg();
        setTtl(mAL.mMngLng.getS("scnSelStg_Ttl"));
        setDrwrPreAlwys(mAL.mDfltBGDrwr);
        int stgSz = mAL.egV.mStgSz;
        int oldStgSz = mAL.egV.gda.stgWinTm.length;
        if(oldStgSz != stgSz)
        {
            int stgEnbl[] = new int[stgSz];
            int stgWinTm[] = new int[stgSz];
            int stgWinScr[] = new int[stgSz];
            int stgWinNo[] = new int[stgSz];
            for(int i = 0; i < oldStgSz; i++)
            {
                stgEnbl[i] = mAL.egV.gda.stgEnbl[i];
                stgWinTm[i] = mAL.egV.gda.stgWinTm[i];
                stgWinScr[i] = mAL.egV.gda.stgWinScr[i];
                stgWinNo[i] = mAL.egV.gda.stgWinNo[i];
            }

            if(stgSz > oldStgSz && stgWinNo[oldStgSz - 1] >= 1)
                stgEnbl[oldStgSz] = 1;
            mAL.egV.gda.stgEnbl = stgEnbl;
            mAL.egV.gda.stgWinTm = stgWinTm;
            mAL.egV.gda.stgWinScr = stgWinScr;
            mAL.egV.gda.stgWinNo = stgWinNo;
        }
        initImgStgThmb();
        int crntStg = 0;
        for(int i = 0; i < mAL.egV.mStgSz; i++)
            if((mAL.mBsOpt.getI("dbgStgAllEnbl") == 1 || mAL.egV.gda.stgEnbl[i] == 1) && (!mAL.mLcncLck || i + 1 <= mAL.mBsOpt.getI("stgLcncMax")))
            {
                StringBuilder sb = new StringBuilder();
                sb.append(UtlTool.smplFormat(mAL.mMngLng.getS("scnSelStg_Stg"), new Object[] {
                    Integer.valueOf(i + 1)
                }));
                int tm = mAL.egV.gda.stgWinTm[i];
                int scr = mAL.egV.gda.stgWinScr[i];
                int no = mAL.egV.gda.stgWinNo[i];
                if(tm > 0 && scr > 0)
                    sb.append(" ").append(UtlTool.smplFormat(mAL.mMngLng.getS("scnSelStg_No"), new Object[] {
                        Integer.valueOf(no)
                    })).append("\n").append(UtlTool.smplFormat(mAL.mMngLng.getS("scnSelStg_Scr"), new Object[] {
                        UtlTool.nf(scr)
                    })).append("\n").append(UtlTool.smplFormat(mAL.mMngLng.getS("scnSelStg_Tm"), new Object[] {
                        UtlTool.dgt((double)tm * 0.001D, 2)
                    }));
                DlgMnItmImg mnItm = new DlgMnItmImg(mAL, this, mAL.mWD.getBufH(IMG_ID_STG_THMB + i) + 6, sb.toString(), i, IMG_ID_STG_THMB + i, 0);
                addLst(mnItm);
                crntStg = i;
            }

        setMnSel(crntStg);
        StrBtn btnStrt = new StrBtn(mAL, 0, mAL.mML.l.dlg_btnH, "START") {

            public void addActClck()
            {
                mClckEnbl = false;
                mAL.mRsrvLst.add(new Rsrv(mAL, 550L) {

                    public void addAct()
                    {
                        mStatBtn = 0;
                        mClckEnbl = true;
                        if(mMnLst.size() == 0)
                        {
                            mStatBtn = 0;
                            mClckEnbl = true;
                            mAL.mMS.setNxtScn(V.SCN_TTL_GM);
                            return;
                        } else
                        {
                            ((V)mAL.mV).gd.stgNow = mMnSelNow + 1;
                            mClckEnbl = true;
                            finishDlg();
                            mAL.mMS.setNxtScn(V.SCN_BFR_GM);
                            return;
                        }
                    }

        
                }
);
            }

        }
;
        addBtn(btnStrt);
        StrBtn btnRcrd = new StrBtn(mAL, 0, mAL.mML.l.dlg_btnH, "RECORD") {

            public void addActClck()
            {
                mClckEnbl = false;
                mAL.mRsrvLst.add(new Rsrv(mAL, 550L) {

                    public void addAct()
                    {
                        if(mMnLst.size() == 0)
                        {
                            mStatBtn = 0;
                            mClckEnbl = true;
                            mAL.mMS.setNxtScn(V.SCN_TTL_GM);
                            return;
                        } else
                        {
                            ((V)mAL.mV).gd.stgNow = mMnSelNow + 1;
                            mStatBtn = 0;
                            mClckEnbl = true;
                            mAL.mMS.setNxtFlw(V.FLW_MN_RCRD_INIT);
                            return;
                        }
                    }

                }
);
            }


        }
;
        addBtn(btnRcrd);
        mBtnAutoLayout = true;
        layoutAuto();
        setStrtPgAuto();
        addClckbl(8, new TabOpt(mAL));
        mAL.mMngSnd.playBgm("bgmTtl");
        mAL.mMS.setNxtFlw(V.FLW_WAIT);
    }

    public void initImgStgThmb()
    {
        for(int i = 0; i < mAL.egV.mStgSz; i++)
        {
            int stgNow = i + 1;
            int imgId = IMG_ID_STG_THMB + i;
            if(mAL.mBsOpt.getS((new StringBuilder("stgRef")).append(stgNow).toString()).length() != 0)
            {
                int stgRef = mAL.mBsOpt.getI((new StringBuilder("stgRef")).append(stgNow).toString());
                mAL.mWD.mkBuf(IMG_ID_STG_BUF, (new StringBuilder("sel_stg/map")).append(stgRef).append(".jpg").toString());
                String fncStr = mAL.mBsOpt.getS((new StringBuilder("stgPrc")).append(stgNow).toString());
                UtlDw.prcBufFromS(mAL.mWD, IMG_ID_STG_BUF, fncStr);
            } else
            {
                mAL.mWD.mkBuf(IMG_ID_STG_BUF, (new StringBuilder("sel_stg/map")).append(stgNow).append(".jpg").toString());
            }
            mAL.mWD.mkBuf(imgId, "sel_stg/stg_bg.png");
            int bsW = mAL.mWD.getBufW(imgId);
            int bsH = mAL.mWD.getBufH(imgId);
            int imgW = mAL.mWD.getBufW(IMG_ID_STG_BUF);
            int imgH = mAL.mWD.getBufH(IMG_ID_STG_BUF);
            mAL.mWD.tgtBuf(imgId);
            mAL.mWD.dwImg(IMG_ID_STG_BUF, (bsW - imgW) / 2, 3 + (bsH - 15 - imgH) / 2);
        }

    }

    public void alwysWait()
    {
        doAlwysCmn();
    }

    public boolean addDoBck()
    {
        mAL.mMS.setNxtScn(V.SCN_TTL_GM);
        return true;
    }

    public void finish()
    {
        super.finishDlg();
        super.finish();
        mMnOpt.finish();
        mMnOpt = null;
        mMnRcrd.finish();
        mMnRcrd = null;
    }

    private AppLoopSimWar mAL;
    private final int IMG_ID_STG_BUF;
    private final int IMG_ID_STG_THMB;
    private MnOpt mMnOpt;
    private MnRcrd mMnRcrd;
}
