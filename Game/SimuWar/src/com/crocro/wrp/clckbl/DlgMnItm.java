// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DlgMnItm.java

package com.crocro.wrp.clckbl;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.mng.MngLayout;
import com.crocro.wrp.scn.MngDlg;
import com.crocro.wrp.wrp.WrpDw;

// Referenced classes of package com.crocro.wrp.clckbl:
//            StrBtn

public class DlgMnItm extends StrBtn
{

    public DlgMnItm(AppLoop al, MngDlg mngDlg, int h, String ttlStr, int id)
    {
        super(al, al.mMC.getScrW() - al.mML.l.dlg_mrgnOut * 2, h, ttlStr);
        mMnSel = false;
        mMngDlg = mngDlg;
        mUnqId = id;
    }

    public void dwBfr()
    {
        dwBfrBtnBs();
        mWD.useCol(WrpDw.COL_BLACK);
        int x = mX + mStrFntOpt.fntSz / 2;
        int y = mY + (mH - mTtlH) / 2;
        mWD.useCol(WrpDw.COL_BLACK);
        mWD.dwStr(mTtlStr, x, y, mAL.mMC.getScrW(), mStrFntOpt, null);
    }

    public void dwAftr()
    {
        dwAftrBtnBs();
        mWD.useCol(WrpDw.COL_BLACK);
        int x = mX + mStrFntOpt.fntSz / 2;
        int y = mY + (mH - mTtlH) / 2;
        mWD.useCol(WrpDw.COL_BLACK);
        mWD.dwStr(mTtlStr, x, y, mAL.mMC.getScrW(), mStrFntOpt, null);
    }

    public void dwAftrEf()
    {
    }

    public void addActClck()
    {
        if(mMnSel)
            sel();
        else
            unsel();
        mMngDlg.rflshMnSel(mMnId);
    }

    public void sel()
    {
        mMnSel = true;
        mStatBtn = 1;
    }

    public void unsel()
    {
        mMnSel = false;
        mStatBtn = 0;
    }

    public void finish()
    {
        super.finish();
        mMngDlg = null;
    }

    public int mMnId;
    public int mUnqId;
    private MngDlg mMngDlg;
    public boolean mMnSel;
}
