// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DlgTtl.java

package com.crocro.wrp.clckbl;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.mng.MngLayout;
import com.crocro.wrp.wrp.WrpDw;

// Referenced classes of package com.crocro.wrp.clckbl:
//            Drw

public class DlgTtl extends Drw
{

    public DlgTtl(AppLoop al, int y, String ttlStr)
    {
        super(al, 0, y, 0, 0, 0);
        mStrFntOpt = null;
        mTtlStr = ttlStr;
        mX = mAL.mML.l.dlg_mrgnOut;
        mW = mAL.mMC.getScrW() - mAL.mML.l.dlg_mrgnOut * 2;
        mStrFntOpt = mAL.mML.mStrFntDflt;
        mH = mWD.getStrH(mTtlStr, mStrFntOpt, mW);
    }

    public void dwAlwys()
    {
        mWD.useCol(WrpDw.COL_WHITE);
        mWD.setTrns(75);
        mWD.flRRct(mX - 5, mY - 5, mW + 10, mH + 10, 12, 12);
        mWD.setTrns(100);
        mWD.useCol(WrpDw.COL_BLACK);
        mWD.dwStr(mTtlStr, mX, mY, mW, mStrFntOpt, null);
    }

    public void finish()
    {
        super.finish();
        mTtlStr = null;
        mStrFntOpt = null;
    }

    public String mTtlStr;
    private com.crocro.wrp.wrp.WrpDw.StrFntOpt mStrFntOpt;
    private static final int FRM_MRGN = 5;
}
