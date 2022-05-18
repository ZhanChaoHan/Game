// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DlgMnChk.java

package com.crocro.wrp.clckbl;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.scn.MngDlg;
import com.crocro.wrp.utl.UtlDw;

// Referenced classes of package com.crocro.wrp.clckbl:
//            DlgMnItm

public class DlgMnChk extends DlgMnItm
{

    public DlgMnChk(AppLoop al, MngDlg mngDlg, int h, String ttlStr, int id, int chkNow)
    {
        super(al, mngDlg, h, ttlStr, id);
        mChk = 0;
        mChk = chkNow;
    }

    public void addActClck()
    {
        unsel();
        mChk = 1 - mChk;
    }

    public void dwAlwys()
    {
        super.dwAlwys();
        int chkSz = mH - CHK_H_MRGN * 2;
        int x = (mX + mW) - CHK_W_MRGN - chkSz;
        int y = mY + CHK_H_MRGN;
        if(mChk == 1)
            UtlDw.dwChkOn(mWD, x, y, chkSz, chkSz);
        else
            UtlDw.dwChkOff(mWD, x, y, chkSz, chkSz);
    }

    public static int CHK_W_MRGN = 24;
    public static int CHK_H_MRGN = 18;
    public static int CHK_FRM = 4;
    public int mChk;

}
