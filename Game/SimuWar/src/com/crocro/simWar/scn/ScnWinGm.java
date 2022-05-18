// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ScnWinGm.java

package com.crocro.simWar.scn;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V;
import com.crocro.wrp.mng.MngScn;
import com.crocro.wrp.utl.UtlTool;
import com.crocro.wrp.wrp.WrpFile;

// Referenced classes of package com.crocro.simWar.scn:
//            ScnBfrGm

public class ScnWinGm extends ScnBfrGm
{

    public ScnWinGm(AppLoopSimWar al)
    {
        super(al);
    }

    public void doNoScrpt()
    {
        mvNxtScn();
    }

    public boolean chkScn(int scn)
    {
        return scn != V.SCN_WIN_GM;
    }

    public String loadScrpt()
    {
        int stgNow = ((V)mAL.mV).gd.stgNow;
        String scrpt = UtlTool.bToS(mAL.mWF.getR(UtlTool.smplFormat("res/dat/stgScrpt/stg%s_win.txt", new Object[] {
            Integer.valueOf(stgNow)
        })));
        return scrpt;
    }

    public void mvNxtScn()
    {
        mAL.mMS.setNxtScn(V.SCN_STG_CLR);
    }

    public static final String SCRPT_PTH = "res/dat/stgScrpt/stg%s_win.txt";
}
