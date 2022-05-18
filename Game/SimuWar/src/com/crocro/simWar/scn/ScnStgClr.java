// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ScnStgClr.java

package com.crocro.simWar.scn;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V;
import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.mng.MngOpt;
import com.crocro.wrp.mng.MngScn;
import com.crocro.wrp.utl.UtlTool;

// Referenced classes of package com.crocro.simWar.scn:
//            ScnStgStrt

public class ScnStgClr extends ScnStgStrt
{

    public ScnStgClr(AppLoop al)
    {
        super(al);
    }

    public void initTtlStr()
    {
        int stgNow = mAL.egV.gd.stgNow;
        StringBuilder sb = new StringBuilder();
        sb.append(UtlTool.smplFormat(mAL.mBsLngOpt.getS("stgNmBs"), new Object[] {
            Integer.valueOf(stgNow)
        })).append("\n").append(mAL.mBsLngOpt.getS((new StringBuilder("stgNm")).append(stgNow).toString())).append("\n").append(mAL.mBsLngOpt.getS("stgNmClr"));
        mTtlStr = sb.toString();
    }

    public void mvScn()
    {
        int stgNow = mAL.egV.gd.stgNow;
        if(mAL.mBsOpt.getI((new StringBuilder("stgClr")).append(stgNow).toString()) == 1 || stgNow == mAL.egV.mStgSz)
            mAL.mMS.setNxtScn(V.SCN_TTL_GM);
        else
            mAL.mMS.setNxtScn(V.SCN_SEL_STG);
    }
}
