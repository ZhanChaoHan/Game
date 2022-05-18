// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GmDtUnt.java

package com.crocro.simWar.gm;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V;
import com.crocro.simWar.mng.MngLayout;
import com.crocro.wrp.bs.F;
import com.crocro.wrp.mng.MngOpt;
import com.crocro.wrp.utl.UtlTool;
import com.crocro.wrp.wrp.WrpEnv;
import com.crocro.wrp.wrp.WrpFile;

// Referenced classes of package com.crocro.simWar.gm:
//            GmDtUntF, GmDt

public class GmDtUnt
    implements F, GmDtUntF
{

    public void setIdMy(int id)
    {
        mIdMy = id;
    }

    public void setIdTgt(int id)
    {
        mIdTgt = id;
    }

    public int getUD(int id, int typ)
    {
        return mUD[id * 17 + typ];
    }

    public int getMyUD(int typ)
    {
        return mUD[mIdMy * 17 + typ];
    }

    public int getTgtUD(int typ)
    {
        return mUD[mIdTgt * 17 + typ];
    }

    public void setUD(int id, int typ, int prm)
    {
        mUD[id * 17 + typ] = prm;
    }

    public void setMyUD(int typ, int prm)
    {
        mUD[mIdMy * 17 + typ] = prm;
    }

    public void setTgtUD(int typ, int prm)
    {
        mUD[mIdTgt * 17 + typ] = prm;
    }

    public int getUS(int id, int typ)
    {
        return mUS[id * US_MAX + typ];
    }

    public int getMyUS(int typ)
    {
        return mUS[mIdMy * US_MAX + typ];
    }

    public int getTgtUS(int typ)
    {
        return mUS[mIdTgt * US_MAX + typ];
    }

    public void setUS(int id, int typ, int prm)
    {
        mUS[id * US_MAX + typ] = prm;
    }

    public void setMyUS(int typ, int prm)
    {
        mUS[mIdMy * US_MAX + typ] = prm;
    }

    public void setTgtUS(int typ, int prm)
    {
        mUS[mIdTgt * US_MAX + typ] = prm;
    }

    public int getTyp(int id)
    {
        return mUD[id * 17 + 1];
    }

    public int getMyTyp()
    {
        return mUD[mIdMy * 17 + 1];
    }

    public int getTgtTyp()
    {
        return mUD[mIdTgt * 17 + 1];
    }

    public int getEnbl(int id)
    {
        return mUS[id * US_MAX + US_ENBL];
    }

    public int getHp(int id)
    {
        return mUS[id * US_MAX + US_HP];
    }

    public int getMyHp()
    {
        return mUS[mIdMy * US_MAX + US_HP];
    }

    public int getTgtHp()
    {
        return mUS[mIdTgt * US_MAX + US_HP];
    }

    public void setHp(int id, int prm)
    {
        mUS[id * US_MAX + US_HP] = prm;
    }

    public void setMyHp(int prm)
    {
        mUS[mIdMy * US_MAX + US_HP] = prm;
    }

    public void setTgtHp(int prm)
    {
        mUS[mIdTgt * US_MAX + US_HP] = prm;
    }

    public int getSkl(int id)
    {
        return mGD.mUntI[getTyp(id)][GmDt.UNT_I_SKL1 + getUS(id, US_SEL_SKL)];
    }

    public int getMySkl()
    {
        return getSkl(mIdMy);
    }

    public int getTgtSkl()
    {
        return getSkl(mIdTgt);
    }

    public GmDtUnt(AppLoopSimWar al)
    {
        mAL = null;
        mGD = null;
        mAL = al;
        mGD = mAL.egV.mGD;
        mUD = mAL.egV.lda.untDat;
        mUS = mAL.egV.lda.untStts;
    }

    public void ldUntDt()
    {
        int stgNow = mAL.egV.gd.stgNow;
        String ldStr = UtlTool.bToS(mAL.mWF.getR(UtlTool.smplFormat("res/dat/stgDat/stg%d.dat", new Object[] {
            Integer.valueOf(stgNow)
        })));
        int ldStgDt[][] = UtlTool.sToI2dAr(ldStr);
        int ln = 0;
        if(((V)mAL.mV).mSelUntTypArr != null)
        {
            int selUnt[] = ((V)mAL.mV).mSelUntTypArr;
            if(selUnt != null && selUnt.length > 0)
            {
                for(int i = 0; i < selUnt.length; i++)
                {
                    int id = ldStgDt[i][0];
                    if(id == i && id < 17)
                        ldStgDt[i][1] = selUnt[i];
                }

            }
            ((V)mAL.mV).mSelUntTypArr = null;
        }
        try
        {
            mAL.egV.lda.untDat = new int[2244];
            mUD = mAL.egV.lda.untDat;
            for(int i = 0; i < 132; i++)
            {
                for(int j = 0; j < 17; j++)
                    setUD(i, j, -1);

            }

            for(int i = ln; i < ldStgDt.length; i++)
            {
                int id = ldStgDt[i][0];
                int setMax = id >= 32 ? 17 : 4;
                for(int j = 0; j < setMax; j++)
                    setUD(id, j, ldStgDt[i][j]);

            }

        }
        catch(Exception e)
        {
        	e.printStackTrace();
            mAL.mWE.dbgErr(e, (new StringBuilder("Stage data read err : ")).append(stgNow).toString());
        }
        mAL.egV.lda.untStts = new int[132 * US_MAX];
        mUS = mAL.egV.lda.untStts;
    }

    public void initStts()
    {
        if(mAL.egV.mGmRstrt)
            return;
        for(int i = 0; i < 132; i++)
            if(getUD(i, 0) != -1 && (mAL.mBsOpt.getI("dbgFrcWin") != 1 || i < 32) && (mAL.mBsOpt.getI("dbgFrcLose") != 1 || i >= 32) && (mAL.mBsOpt.getI("dbgEneOne") != 1 || i <= 32) && (mAL.mBsOpt.getI("dbgMyOne") != 1 || i == 0 || i > 32))
            {
                setUS(i, US_ENBL, 1);
                setUS(i, US_MV_X_NOW, getUD(i, 2) * 20 + 10);
                setUS(i, US_MV_Y_NOW, getUD(i, 3) * 20 + 10);
                setUS(i, US_MV_X_OLD, getUS(i, US_MV_X_NOW));
                setUS(i, US_MV_Y_OLD, getUS(i, US_MV_Y_NOW));
                setUS(i, US_DST_X_NOW, getUS(i, US_MV_X_NOW));
                setUS(i, US_DST_Y_NOW, getUS(i, US_MV_Y_NOW));
                setUS(i, US_DST_X_OLD, getUS(i, US_MV_X_NOW));
                setUS(i, US_DST_Y_OLD, getUS(i, US_MV_Y_NOW));
                setUS(i, US_HP, mGD.mUntI[getTyp(i)][GmDt.UNT_I_HP]);
                setUS(i, US_MTR, 0);
                setUS(i, US_SEL_SKL, 0);
            }

    }

    public void inittMvClcFrm()
    {
        int enblUntCnt = 0;
        for(int i = 0; i < 132; i++)
            if(getEnbl(i) == 1)
                enblUntCnt++;

        mMvClcFrmPos = 0;
        int inFrmCnt = 0;
        int outFrmCnt = 0;
        int f_rtClcParFrm = ((MngLayout)mAL.mML).l.rtClcParFrm;
        int f_rtClcFrmMin = ((MngLayout)mAL.mML).l.rtClcFrmMin;
        int rtClcParFrm = f_rtClcParFrm;
        for(int i = 0; i < f_rtClcParFrm - 1; i++)
        {
            if(rtClcParFrm * enblUntCnt >= f_rtClcFrmMin * f_rtClcParFrm)
                break;
            rtClcParFrm--;
        }

        mMvClcFrmMax = enblUntCnt / rtClcParFrm + (enblUntCnt % rtClcParFrm <= 0 ? 0 : 1);
        if(mMvClcFrmMax < f_rtClcFrmMin)
            mMvClcFrmMax = f_rtClcFrmMin;
        mMvClcFrmArr = new int[132];
        for(int i = 0; i < 17; i++)
            mMvClcFrmArr[i] = -1;

        for(int i = 0; i < 132; i++)
            if(getUS(i, US_ENBL) != 0)
            {
                mMvClcFrmArr[i] = outFrmCnt;
                if(++inFrmCnt >= rtClcParFrm)
                {
                    inFrmCnt = 0;
                    outFrmCnt++;
                }
            }

    }

    public void updtMvClcFrm()
    {
        mMvClcFrmPos = (mMvClcFrmPos + 1) % mMvClcFrmMax;
    }

    public int chkDthAll()
    {
        boolean mySrvvr = false;
        boolean eneSrvvr = false;
        for(int i = 0; i < 32; i++)
        {
            if(getUS(i, US_HP) <= 0)
                continue;
            mySrvvr = true;
            break;
        }

        if(!mySrvvr)
            return 1;
        for(int i = 32; i < 132; i++)
        {
            if(getHp(i) <= 0)
                continue;
            eneSrvvr = true;
            break;
        }

        return eneSrvvr ? 0 : 2;
    }

    public int getMvPow(int uId)
    {
        int mvPow = mGD.mUntI[getTyp(uId)][GmDt.UNT_I_MV] * 3;
        if(mvPow <= 0)
            return 0;
        int exMv = getUS(uId, US_EX_MV_PW) * 3;
        mvPow += exMv;
        if(mvPow <= 0)
            return 0;
        if(mvPow >= 800)
            mvPow = 800;
        if(uId >= 32)
            mvPow = (int)((double)mvPow * DFCLT[mAL.egV.gd.dfclt][0]);
        return mvPow;
    }

    public void doRst(int uId, boolean enbl)
    {
        if(enbl)
        {
            int rstCntr = getUS(uId, US_RST_CNTR);
            if(--rstCntr < 0)
                rstCntr = 0;
            setUS(uId, US_RST_CNTR, rstCntr);
            if(rstCntr == 0)
            {
                int hp = getUS(uId, US_HP);
                int hpMax = mAL.egV.mGD.mUntI[getTyp(uId)][GmDt.UNT_I_HP];
                hp += (hpMax * 5) / 100;
                if(hp >= hpMax)
                    hp = hpMax;
                setHp(uId, hp);
                setUS(uId, US_RST_CNTR, 2);
            }
        } else
        {
            setUS(uId, US_RST_CNTR, 5);
        }
    }

    public void updtEx(int uId)
    {
        int us[] = mUS;
        int usPos = uId * US_MAX;
        for(int i = 0; i < US_EX_SZ; i++)
        {
            int exTm = us[usPos + US_EX_TM_STRT + i * 2];
            if(exTm > 0)
                if(--exTm > 0)
                    us[usPos + US_EX_TM_STRT + i * 2] = exTm;
                else
                if(exTm == 0)
                {
                    us[usPos + US_EX_TM_STRT + i * 2] = 0;
                    us[usPos + US_EX_PW_STRT + i * 2] = 0;
                    if(US_EX_TM_STRT + i * 2 == US_EX_MV_TM)
                        us[usPos + US_ELPS_MV] = 0;
                }
        }

    }

    public boolean chkEnblTgt(int myId, int tgtId, int skl)
    {
        int tgtSide = mGD.mSklI[skl][GmDt.SKL_I_TGT];
        if(tgtSide == 0 || tgtSide == 2)
        {
            if(myId < 32)
            {
                if(tgtId < 32)
                    return false;
            } else
            if(tgtId >= 32)
                return false;
        } else
        if(myId < 32)
        {
            if(tgtId >= 32)
                return false;
        } else
        if(tgtId < 32)
            return false;
        return true;
    }

    public void finish()
    {
        mAL = null;
        mGD = null;
        mUD = null;
        mUS = null;
    }

    private AppLoopSimWar mAL;
    private GmDt mGD;
    public int mIdMy;
    public int mIdTgt;
    public int mUD[];
    public int mUS[];
    public static final String PTH_STG_DAT = "res/dat/stgDat/stg%d.dat";
    public static final int DTH_ALL_NO = 0;
    public static final int DTH_ALL_MY = 1;
    public static final int DTH_ALL_ENE = 2;
    public int mMvClcFrmMax;
    public int mMvClcFrmPos;
    public int mMvClcFrmArr[];
}
