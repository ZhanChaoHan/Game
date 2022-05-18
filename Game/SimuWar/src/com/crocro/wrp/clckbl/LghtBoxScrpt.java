// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LghtBoxScrpt.java

package com.crocro.wrp.clckbl;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.scn.Scn;
import com.crocro.wrp.utl.Rsrv;
import java.util.ArrayList;

// Referenced classes of package com.crocro.wrp.clckbl:
//            LghtBox, DrwScrpt

public class LghtBoxScrpt extends LghtBox
{

    public LghtBoxScrpt(AppLoop al, String ttlStr, Scn scn)
    {
        super(al, ttlStr, scn);
        useArrBtn(new LghtBox.ArrBtn[] {
            new LghtBox.ArrBtn("SKIP") {

                public boolean doClck()
                {
                    closeAct();
                    return true;
                }

  
            }
, new LghtBox.ArrBtn("NEXT") {

                public boolean doClck()
                {
                    return doNxt();
                }

   
            }

        });
        mBgTrns = 33;
        mBgColR = 128;
        mBgColG = 128;
        mBgColB = 128;
    }

    public boolean doNxt()
    {
        if(mDrwScrpt != null)
        {
            boolean resPrg = mDrwScrpt.prgScrpt();
            if(resPrg)
            {
                closeAct();
                return true;
            }
        }
        return false;
    }

    public void closeAct()
    {
    }

    public void dwAlwys()
    {
        super.dwAlwys();
        mDrwScrpt.dwAlwys();
    }

    public int chkClckIn(int x, int y)
    {
        int res = super.chkClckIn(x, y);
        if(res == 4)
        {
            mClckX = x;
            mClckY = y;
            actClck();
            mAL.mRsrvLst.add(new Rsrv(mAL, 550L) {

                public void addAct()
                {
                    mScn.mClckEnbl = true;
                    mStatBtn = 0;
                    if(doNxt())
                        rsrvRmv();
                }

  
            }
);
        }
        return res;
    }

    public boolean doBck()
    {
        mClckX = getBtnX() + getBtnW() / 4;
        mClckY = getBtnY() + getBtnH() / 2;
        actClck();
        mAL.mRsrvLst.add(new Rsrv(mAL, 550L) {

            public void addAct()
            {
                rsrvRmv();
                closeAct();
            }

        }
);
        return true;
    }

    public void finish()
    {
        super.finish();
        if(mDrwScrpt != null)
        {
            mDrwScrpt.finish();
            mDrwScrpt = null;
        }
    }

    public DrwScrpt mDrwScrpt;
}
