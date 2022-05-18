// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GmImgUnt.java

package com.crocro.simWar.gm;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V;
import com.crocro.wrp.utl.UtlTool;
import com.crocro.wrp.wrp.WrpDw;

// Referenced classes of package com.crocro.simWar.gm:
//            GmDt

public class GmImgUnt
{

    public GmImgUnt(AppLoopSimWar al)
    {
        mAL = al;
        mWDIn = mAL.mWD.getNew();
        mImgLdEnd = new boolean[mAL.egV.mGD.mUntS.length];
    }

    public void ldImg(int untTyp)
    {
        if(untTyp < 0 || untTyp >= mImgLdEnd.length)
            return;
        if(!mImgLdEnd[untTyp])
        {
            mImgLdEnd[untTyp] = true;
            String pth = UtlTool.smplFormat("us/%s.png", new Object[] {
                mAL.egV.mGD.mUntS[untTyp][0]
            });
            mWDIn.mkBuf(untTyp, pth);
        }
    }

    public void finish()
    {
        mAL = null;
        if(mWDIn != null)
            mWDIn.flshBuf(0);
        mWDIn = null;
        mImgLdEnd = null;
    }

    private AppLoopSimWar mAL;
    public WrpDw mWDIn;
    public boolean mImgLdEnd[];
    public static final String IMG_PTH_US = "us/%s.png";
}
