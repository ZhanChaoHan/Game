// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TabOpt.java

package com.crocro.simWar.clckbl;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V;
import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.clckbl.Tab;
import com.crocro.wrp.mng.MngLayout;
import com.crocro.wrp.mng.MngScn;
import com.crocro.wrp.scn.Scn;
import com.crocro.wrp.utl.Rsrv;
import java.util.ArrayList;

public class TabOpt extends Tab
{

    public TabOpt(AppLoop al)
    {
        super(al, -1, AppLoopSimWar.IMG_ID_CMN_TAB_OPT);
        if(mAL.mML.l.vwTyp == 1)
        {
            mW = 0;
            mH = 0;
            mSldDir = 1;
        }
    }

    public void addActClck()
    {
        mAL.mScn.mClckEnbl = false;
        mAL.mRsrvLst.add(new Rsrv(mAL, 500L) {

            public void addAct()
            {
                mAL.mScn.mClckEnbl = true;
                mStatBtn = 0;
                mAL.mMS.setNxtFlw(V.FLW_MN_OPT_INIT);
            }

        }
);
    }
}
