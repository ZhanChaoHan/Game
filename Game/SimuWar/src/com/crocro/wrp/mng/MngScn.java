// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MngScn.java

package com.crocro.wrp.mng;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.app.V;
import com.crocro.wrp.bs.F;
import com.crocro.wrp.clckbl.Clckbl;

// Referenced classes of package com.crocro.wrp.mng:
//            MngCnvs

public class MngScn
    implements F
{

    public MngScn(V sv, AppLoop al)
    {
        mAL = null;
        mV = null;
        mScnOld = -1;
        mAL = al;
        mV = sv;
        mV.sda.scn = new int[32];
        mV.sda.flw = new int[32];
    }

    public int getScn(int pos)
    {
        int res = -1;
        int enblPos = 0;
        enblPos = (mV.sd.arrPntrScn + pos + 32) % 32;
        res = mV.sda.scn[enblPos];
        return res;
    }

    public int getFlw(int pos)
    {
        int enblPos = (mV.sd.arrPntrFlw + pos + 32) % 32;
        int res = mV.sda.flw[enblPos];
        return res;
    }

    public void setNxtScn(int scnId)
    {
        mV.sd.arrPntrScn = (mV.sd.arrPntrScn + 1) % 32;
        mV.sd.arrPntrFlw = (mV.sd.arrPntrFlw + 1) % 32;
        int posScn = mV.sd.arrPntrScn;
        mV.sda.scn[posScn] = scnId;
        int posFlw = mV.sd.arrPntrFlw;
        mV.sda.flw[posFlw] = 0;
        chngFlwCmn();
    }

    public void setNxtFlw(int flwId)
    {
        mV.sd.arrPntrFlw = (mV.sd.arrPntrFlw + 1) % 32;
        int pos = mV.sd.arrPntrFlw;
        mV.sda.flw[pos] = flwId;
        chngFlwCmn();
    }

    public void chngFlwCmn()
    {
        mAL.clrClck();
        mAL.mMC.rstOrgn();
        Clckbl.mClckExclsn = false;
        int scnNow = getScn(0);
        if(scnNow != mScnOld)
            mAL.rldScn(mScnOld, scnNow);
        mScnOld = scnNow;
        mAL.mngFlw();
    }

    private AppLoop mAL;
    private V mV;
    private static final int SCN_ARR_MAX = 32;
    private int mScnOld;
}
