// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MngDlg.java

package com.crocro.wrp.scn;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.clckbl.*;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.mng.MngLayout;
import com.crocro.wrp.utl.Drwr;
import com.crocro.wrp.utl.Rsrv;
import com.crocro.wrp.wrp.WrpDw;
import java.util.ArrayList;

// Referenced classes of package com.crocro.wrp.scn:
//            Scn

public class MngDlg extends Scn
{

    public MngDlg(AppLoop al)
    {
        super(al);
        mL = null;
        mInitEndFlg = false;
        mMnLst = new ArrayList();
        mBtnLst = new ArrayList();
        mMnMaxH = 0;
        mMnMrgnTop = 0;
        mMnMrgnBtm = 0;
        mMnMrgnX = 0;
        mMnTyp = 0;
        mMnSelNow = 0;
        mMnSelLst = null;
        mPgMax = 1;
        mPgNow = 0;
        mBtnAutoLayout = false;
        mDfltDw = new Drwr() {

            public void dw()
            {
                mmAL.mWD.useCol(WrpDw.COL_WHITE);
                mmAL.mWD.flRct(0, 0, mmAL.mMC.getScrW(), mmAL.mMC.getScrH());
            }

        }
;
        mL = mmAL.mML.l;
    }

    public void initDlg()
    {
        init();
        if(!mInitEndFlg)
            mInitEndFlg = true;
        mBtnLst = new ArrayList();
        mMnLst = new ArrayList();
        mPgMax = 1;
        mPgNow = 0;
        mBtnPgUp = new StrBtn(mmAL, mL.dlg_btnPgW, mL.dlg_btnH, ">") {

            public void addActClck()
            {
                mPgNow = (mPgNow + 1) % mPgMax;
                layoutAuto();
                mmAL.mRsrvLst.add(new Rsrv(mmAL, 275L) {

                    public void addAct()
                    {
                        mStatBtn = 0;
                    }

          
                }
);
            }

            public void dwAftrEf()
            {
            }

        
        }
;
        addClckbl(0, mBtnPgUp);
        mBtnPgDwn = new StrBtn(mmAL, mL.dlg_btnPgW, mL.dlg_btnH, "<") {

            public void addActClck()
            {
                mPgNow = ((mPgNow - 1) + mPgMax) % mPgMax;
                layoutAuto();
                mmAL.mRsrvLst.add(new Rsrv(mmAL, 275L) {

                    public void addAct()
                    {
                        mStatBtn = 0;
                    }

                }
);
            }

            public void dwAftrEf()
            {
            }

   
        }
;
        addClckbl(0, mBtnPgDwn);
        mUsePreDw = mDfltDw;
        mUsePstDw = new Drwr();
        mBtnAutoLayout = false;
    }

    public void finishDlg()
    {
        if(mMnLst != null)
        {
            for(int i = 0; i < mMnLst.size(); i++)
            {
                Clckbl c = (Clckbl)mMnLst.get(i);
                if(c != null)
                    c.finish();
            }

            mMnLst.clear();
            mMnLst = null;
        }
        mMnSelNow = 0;
        if(mBtnLst != null)
        {
            for(int i = 0; i < mBtnLst.size(); i++)
            {
                Clckbl c = (Clckbl)mBtnLst.get(i);
                if(c != null)
                    c.finish();
            }

            mBtnLst.clear();
            mBtnLst = null;
        }
        mTtlStr = null;
        mDlgTtl = null;
        mMnSelLst = null;
        mBtnPgUp = null;
        mBtnPgDwn = null;
        mUsePreDw = null;
        mUsePstDw = null;
        clrClckblLst();
    }

    public void setTtl(String ttlStr)
    {
        mTtlStr = ttlStr;
        if(mDlgTtl != null)
        {
            mDlgTtl.finish();
            mDlgTtl = null;
        }
        mDlgTtl = new DlgTtl(mmAL, mL.dlg_mrgnOut, ttlStr);
        addClckbl(0, mDlgTtl);
    }

    public void chngTtl(String ttlStr)
    {
        ((ArrayList)mClckblLst.get(0)).remove(mDlgTtl);
        if(mDlgTtl != null)
        {
            mDlgTtl.finish();
            mDlgTtl = null;
        }
        mTtlStr = ttlStr;
        mDlgTtl = new DlgTtl(mmAL, mL.dlg_mrgnOut, ttlStr);
        addClckbl(0, mDlgTtl);
        layoutAutoMin();
    }

    public void addBtn(StrBtn dlgBtn)
    {
        mBtnLst.add(dlgBtn);
        addClckbl(0, dlgBtn);
    }

    public void addLst(DlgMnItm dlgMnItm)
    {
        dlgMnItm.mMnId = mMnLst.size();
        mMnLst.add(dlgMnItm);
        addClckbl(0, dlgMnItm);
        mMnSelLst = new boolean[mMnLst.size()];
    }

    public void layoutAuto()
    {
        layoutAutoMin();
        if(mMnLst.size() <= 0)
            return;
        if(mMnTyp == 0)
        {
            ((DlgMnItm)mMnLst.get(mMnSelNow)).sel();
        } else
        {
            for(int i = 0; i < mMnLst.size(); i++)
                if(mMnSelLst[i])
                    ((DlgMnItm)mMnLst.get(i)).sel();

        }
    }

    private void layoutAutoMin()
    {
        int ttlH = 0;
        mPgMax = 1;
        if(mTtlStr.length() > 0 && mDlgTtl != null)
            ttlH = mDlgTtl.mH;
        mMnMrgnX = mL.dlg_mrgnOut;
        mMnMrgnTop = ttlH + mL.dlg_mrgnOut;
        mMnMrgnBtm = mL.dlg_mrgnOut + mL.dlg_btnH;
        int x;
        int y;
        if(mMnLst.size() > 0)
        {
            mMnMaxH = mmAL.mMC.getScrH() - mL.dlg_mrgnOut - ttlH - mL.dlg_mrgnTtl - mL.dlg_mrgnIn - mL.dlg_btnH - mL.dlg_mrgnOut;
            x = mMnMrgnX;
            y = mMnMrgnTop + mL.dlg_mrgnTtl;
            int h = 0;
            for(int i = 0; i < mMnLst.size(); i++)
            {
                DlgMnItm dlgMnItm = (DlgMnItm)mMnLst.get(i);
                if(h + dlgMnItm.mH <= mMnMaxH)
                {
                    h += dlgMnItm.mH;
                } else
                {
                    mPgMax++;
                    h = dlgMnItm.mH;
                }
            }

            h = 0;
            int pgNow = 0;
            for(int i = 0; i < mMnLst.size(); i++)
            {
                DlgMnItm dlgMnItm = (DlgMnItm)mMnLst.get(i);
                if(h + dlgMnItm.mH > mMnMaxH)
                {
                    pgNow++;
                    h = 0;
                }
                if(pgNow == mPgNow)
                {
                    dlgMnItm.mX = x;
                    dlgMnItm.mY = y + h;
                } else
                {
                    dlgMnItm.mY = mmAL.mMC.getScrH() * 2;
                }
                if(h + dlgMnItm.mH <= mMnMaxH)
                    h += dlgMnItm.mH;
            }

        }
        x = mmAL.mMC.getScrW() - mL.dlg_mrgnOut;
        y = mmAL.mMC.getScrH() - mMnMrgnBtm;
        if(!mBtnAutoLayout)
        {
            for(int i = 0; i < mBtnLst.size(); i++)
            {
                StrBtn dlgBtn = (StrBtn)mBtnLst.get(i);
                x -= dlgBtn.mW;
                dlgBtn.mX = x;
                dlgBtn.mY = y;
                x -= mL.dlg_mrgnIn;
            }

        } else
        {
            int w = mmAL.mMC.getScrW() - mL.dlg_mrgnOut * 2;
            if(mPgMax > 1)
                w -= mL.dlg_btnPgW * 2 + mL.dlg_mrgnIn;
            w = w / mBtnLst.size() - mL.dlg_mrgnIn * (mBtnLst.size() - 1);
            for(int i = 0; i < mBtnLst.size(); i++)
            {
                StrBtn dlgBtn = (StrBtn)mBtnLst.get(i);
                dlgBtn.mW = w;
                x -= dlgBtn.mW;
                dlgBtn.mX = x;
                dlgBtn.mY = y;
                x -= mL.dlg_mrgnIn;
            }

        }
        if(mPgMax > 1)
        {
            mBtnPgDwn.mX = mL.dlg_mrgnOut;
            mBtnPgDwn.mY = y;
            mBtnPgUp.mX = mL.dlg_mrgnOut + mBtnPgDwn.mW + mL.dlg_mrgnIn;
            mBtnPgUp.mY = y;
        } else
        {
            mBtnPgDwn.mY = mmAL.mMC.getScrH() * 2;
            mBtnPgUp.mY = mmAL.mMC.getScrH() * 2;
        }
    }

    public void setStrtPgAuto()
    {
        if(mPgMax <= 1)
            return;
        if(mMnTyp != 0)
            return;
        int h = 0;
        int pgNow = 0;
        for(int i = 0; i < mMnLst.size(); i++)
        {
            DlgMnItm dlgMnItm = (DlgMnItm)mMnLst.get(i);
            if(h + dlgMnItm.mH > mMnMaxH)
            {
                pgNow++;
                h = 0;
            }
            if(i == mMnSelNow)
                break;
            if(h + dlgMnItm.mH <= mMnMaxH)
                h += dlgMnItm.mH;
        }

        mPgNow = pgNow;
        layoutAuto();
    }

    public void preAlwys()
    {
        if(mUsePreDw != null)
            mUsePreDw.dw();
    }

    public void setDrwrPreAlwys(Drwr drwr)
    {
        mUsePreDw = drwr;
    }

    public void pstAlwys()
    {
        if(mUsePstDw != null)
            mUsePstDw.dw();
    }

    public void setDrwrPstAlwys(Drwr drwr)
    {
        mUsePstDw = drwr;
    }

    public void setMnTyp(int typ)
    {
        mMnTyp = typ;
    }

    public void setMnSel(int id)
    {
        if(id >= mMnLst.size())
            return;
        if(mMnLst.get(id) == null)
        {
            return;
        } else
        {
            ((DlgMnItm)mMnLst.get(id)).sel();
            rflshMnSel(id);
            return;
        }
    }

    public void unsetMnAll()
    {
        for(int i = 0; i < mMnLst.size(); i++)
            if(mMnLst.get(i) != null)
                ((DlgMnItm)mMnLst.get(i)).unsel();

    }

    public void rflshMnSel(int id)
    {
        if(mMnTyp == 0)
        {
            for(int i = 0; i < mMnLst.size(); i++)
            {
                DlgMnItm dlgMnItm = (DlgMnItm)mMnLst.get(i);
                if(i == id)
                    dlgMnItm.sel();
                else
                    dlgMnItm.unsel();
            }

        }
        for(int i = 0; i < mMnLst.size(); i++)
            mMnSelLst[i] = ((DlgMnItm)mMnLst.get(i)).mMnSel;

        mMnSelNow = id;
    }

    private com.crocro.wrp.mng.MngLayout.Layout mL;
    private boolean mInitEndFlg;
    public ArrayList mMnLst;
    private ArrayList mBtnLst;
    private String mTtlStr;
    private DlgTtl mDlgTtl;
    private int mMnMaxH;
    public int mMnMrgnTop;
    public int mMnMrgnBtm;
    public int mMnMrgnX;
    public static final int MN_TYP_EXCLSN = 0;
    public static final int MN_TYP_FREE = 1;
    public int mMnTyp;
    public int mMnSelNow;
    public boolean mMnSelLst[];
    public int mPgMax;
    public int mPgNow;
    StrBtn mBtnPgUp;
    StrBtn mBtnPgDwn;
    public boolean mBtnAutoLayout;
    public Drwr mDfltDw;
    public Drwr mUsePreDw;
    public Drwr mUsePstDw;
}
