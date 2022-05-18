package com.crocro.simWar.clckbl;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V;
import com.crocro.simWar.scn.ScnGm;
import com.crocro.wrp.clckbl.StrBtn;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.mng.MngLayout;
import com.crocro.wrp.wrp.WrpDw;
import java.util.ArrayList;

public class GmLog extends StrBtn
{

    private AppLoopSimWar mAL;
    private ScnGm mSG;
    public static final int LOG_MRGN_H = 4;
    private static final String STR_LOG_VOID = "Log is not.";
    public static final int BTN_W_PDDNG = 10;
    StringBuilder mSB;
    private boolean mVwRflsh;
    ArrayList mAlLogStr;
    ArrayList mAlLogTm;
    public static final int LOG_TM_MAX = 5000;
    
    public GmLog(AppLoopSimWar al, ScnGm sg)
    {
        super(al, 0, 0, 0, 0, "Log is not.");
        mSB = new StringBuilder();
        mVwRflsh = false;
        mAlLogStr = new ArrayList();
        mAlLogTm = new ArrayList();
        mAL = (AppLoopSimWar)super.mAL;
        mSG = sg;
        mBtnWPddng = 10;
        mBtnAutoWMin = 80;
        mBtnHPddng = 4;
        mStrFntOpt = mAL.mML.mStrFntXSml;
        mH = mStrFntOpt.lnH * 2 + 8;
        initFntPrm(-1, mH);
        mY = mAL.mMC.getScrH() - mH;
    }

    public int chkClckIn(int x, int y)
    {
        return 0;
    }

    public void doAlwys()
    {
        if(mAL.egV.gd.vwActLog == 0)
            return;
        if(mVwRflsh)
        {
            mSB.setLength(0);
            if(mAlLogStr.size() == 0)
            {
                mSB.append("Log is not.");
            } else
            {
                for(int i = 0; i < mAlLogStr.size(); i++)
                {
                    if(i != 0)
                        mSB.append("\n");
                    mSB.append((String)mAlLogStr.get(i));
                }

            }
            mTtlStr = mSB.toString();
            initFntPrm(-1, -1);
            mY = mAL.mMC.getScrH() - mH;
            for(int i = 0; i < mAlLogStr.size(); i++)
                if(mSG.mTmGm >= ((Long)mAlLogTm.get(i)).longValue() + 5000L)
                {
                    mAlLogTm.remove(i);
                    mAlLogStr.remove(i);
                    i--;
                    mVwRflsh = true;
                }

        }
    }

    public void dwBfr()
    {
        if(mAL.egV.gd.vwActLog == 0)
        {
            return;
        } else
        {
            super.dwBfr();
            return;
        }
    }

    public void dwAftr()
    {
        if(mAL.egV.gd.vwActLog == 0)
        {
            return;
        } else
        {
            super.dwAftr();
            return;
        }
    }

    public void dwBfrBtnBs()
    {
        mWD.setTrns(50);
        mWD.useCol(WrpDw.COL_WHITE);
        mWD.flRct(mX, mY, mW, mH);
        mWD.setTrns(100);
    }

    public void dwAftrBtnBs()
    {
        dwBfrBtnBs();
    }

    public void addActClck()
    {
    }

    public void addLog(String strNew)
    {
        mAlLogStr.add(strNew);
        mAlLogTm.add(Long.valueOf(mSG.mTmGm));
        mVwRflsh = true;
    }

    public void finish()
    {
        super.finish();
        if(mSB != null)
            mSB.setLength(0);
        mSB = null;
        if(mAlLogStr != null)
            mAlLogStr.clear();
        mAlLogStr = null;
        if(mAlLogTm != null)
            mAlLogTm.clear();
        mAlLogTm = null;
    }

}
