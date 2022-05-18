// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GmToolSkl.java

package com.crocro.simWar.gm;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V;
import com.crocro.simWar.clckbl.*;
import com.crocro.simWar.scn.ScnGm;
import com.crocro.wrp.bs.F;
import com.crocro.wrp.mng.MngLng;
import com.crocro.wrp.utl.UtlTool;

// Referenced classes of package com.crocro.simWar.gm:
//            GmDtUntF, GmDtUnt, GmDt, GmToolScr, 
//            GmDtMap, GmToolMap

public class GmToolSkl
    implements F, GmDtUntF
{

    public GmToolSkl(AppLoopSimWar al, ScnGm sg, int id)
    {
        mIsFnsh = false;
        mSkl = -1;
        mActRunning = false;
        mTgtArr = new int[132];
        mTgtArrSz = 0;
        mAL = al;
        mSG = sg;
        mGDUnt = mSG.mGDUnt;
        mUId = id;
        mUTyp = mGDUnt.getTyp(mUId);
        mUntI = mAL.egV.mGD.mUntI;
        mSklI = mAL.egV.mGD.mSklI;
        mLogStr = mUId >= 32 ? mAL.mMngLng.getS("scnGm_LogEne") : mAL.mMngLng.getS("scnGm_LogMy");
    }

    public void chkAct()
    {
        if(mSG.mGTScr.mWinLose != 0)
            return;
        if(mActRunning)
        {
            doAct();
            mActRunning = false;
            return;
        }
        mGDUnt.setIdMy(mUId);
        dcdSelSkl();
        mTgtId = -1;
        mTgtArrSz = 0;
        int ndMtr = getSklI(GmDt.SKL_I_ND_MTR);
        int tch = mUntI[mUTyp][GmDt.UNT_I_TCH];
        int mtr = mGDUnt.getMyUS(US_MTR);
        if(mUId < 32)
            mtr += tch;
        else
            mtr += (int)((double)tch * DFCLT[mAL.egV.gd.dfclt][1]);
        if(mtr >= ndMtr)
        {
            mtr = ndMtr;
            if(chkCanAct())
            {
                mtr -= ndMtr;
                if(mtr >= ndMtr)
                    mtr = ndMtr;
                mActRunning = true;
                mGDUnt.setMyUS(US_TM_ACT_LST, (int)mSG.mTmGm);
            }
        }
        mGDUnt.setMyUS(US_MTR, mtr);
    }

    public boolean chkCanAct()
    {
        GmDtUnt lGDUnt = mGDUnt;
        int us[] = lGDUnt.mUS;
        int ud[] = lGDUnt.mUD;
        int uId = mUId;
        int lUntI[][] = mUntI;
        boolean res = false;
        lGDUnt.setIdMy(uId);
        int tgt = getSklI(GmDt.SKL_I_TGT);
        int rng2 = getSklI(GmDt.SKL_I_RNG);
        rng2 *= rng2;
        int scp2 = getSklI(GmDt.SKL_I_SCP);
        scp2 *= scp2;
        int usPos = uId * US_MAX;
        int xMy = us[usPos + US_MV_X_NOW];
        int yMy = us[usPos + US_MV_Y_NOW];
        int tgtHP = 999;
        int tgtDmg = 0;
        int tgtDstnc2 = mSG.mGDMap.mMapW + mSG.mGDMap.mMapH;
        tgtDstnc2 *= tgtDstnc2;
        int atPow = getSklI(GmDt.SKL_I_AT);
        int xHit = -1;
        int yHit = -1;
        int sklI[] = mSklI[mSkl];
        int exPrms[] = {
            sklI[GmDt.SKL_I_EX_DF], sklI[GmDt.SKL_I_EX_MV], sklI[GmDt.SKL_I_EX_AT], sklI[GmDt.SKL_I_ADD_FLY], sklI[GmDt.SKL_I_ADD_PSS]
        };
        int exPrmsLen = exPrms.length;
        int mapMyLnOfFire[] = mSG.mGDMap.mMapMyLnOfFire;
        int mapEneLnOfFire[] = mSG.mGDMap.mMapEneLnOfFire;
        int mapLnOfFire[] = mSG.mGDMap.mMapLnOfFire;
        if(tgt == -1)
            return res;
        boolean enblMyIds = lGDUnt.chkEnblTgt(uId, 0, mSkl);
        int forStrt;
        int forMax;
        if(enblMyIds)
        {
            forStrt = 0;
            forMax = 32;
        } else
        {
            forStrt = 32;
            forMax = 132;
        }
        if(tgt == 0 || tgt == 1)
        {
            int xMyS = xMy / 20;
            int yMyS = yMy / 20;
label0:
            for(int i = forStrt; i < forMax; i++)
            {
                usPos = i * US_MAX;
                int udPos = i * 17;
                if(us[usPos + US_ENBL] == 0)
                    continue;
                int hpTmp = us[usPos + US_HP];
                int hpMaxTmp = lUntI[ud[udPos + 1]][GmDt.UNT_I_HP];
                int xTmp = us[usPos + US_MV_X_NOW];
                int yTmp = us[usPos + US_MV_Y_NOW];
                int dstncTmp2 = (xTmp - xMy) * (xTmp - xMy) + (yTmp - yMy) * (yTmp - yMy);
                if(dstncTmp2 > rng2)
                    continue;
                int xTmpS = xTmp / 20;
                int yTmpS = yTmp / 20;
                int map[];
                if(atPow > 0)
                {
                    if(uId < 32)
                        map = mapMyLnOfFire;
                    else
                        map = mapEneLnOfFire;
                } else
                {
                    map = mapLnOfFire;
                }
                if(!mSG.mGTMap.chkLnOfFireAt(map, xMyS, yMyS, xTmpS, yTmpS))
                    continue;
                boolean enblTmp = false;
                if(atPow > 0)
                {
                    if(hpTmp < tgtHP)
                    {
                        tgtHP = hpTmp;
                        enblTmp = true;
                    }
                } else
                if(atPow < 0)
                {
                    int dmgTmp = hpMaxTmp - hpTmp;
                    if(dmgTmp > tgtDmg)
                    {
                        tgtDmg = dmgTmp;
                        enblTmp = true;
                    }
                } else
                {
                    if(i == uId)
                        continue;
                    for(int p = 0; p < exPrmsLen; p++)
                    {
                        int pSz = p * 2;
                        if(exPrms[p] != 0 && us[usPos + US_EX_DF_TM + pSz] > 0 && exPrms[p] == us[usPos + US_EX_DF_PW + pSz])
                            continue label0;
                    }

                    if(dstncTmp2 < tgtDstnc2)
                    {
                        tgtDstnc2 = dstncTmp2;
                        enblTmp = true;
                    }
                }
                if(enblTmp)
                {
                    mTgtId = i;
                    res = true;
                    xHit = xTmp;
                    yHit = yTmp;
                }
            }

        } else
        {
            res = true;
            xHit = xMy;
            yHit = yMy;
        }
        if(res)
        {
            mTgtArrSz = 0;
            for(int i = forStrt; i < forMax; i++)
            {
                usPos = i * US_MAX;
                int udPos = i * 17;
                if(us[usPos + US_ENBL] == 0)
                    continue;
                if(atPow < 0)
                {
                    int hpTmp = us[usPos + US_HP];
                    int hpMaxTmp = lUntI[ud[udPos + 1]][GmDt.UNT_I_HP];
                    if(hpTmp >= hpMaxTmp)
                        continue;
                }
                int xTmp = us[usPos + US_MV_X_NOW];
                int yTmp = us[usPos + US_MV_Y_NOW];
                if((xTmp - xHit) * (xTmp - xHit) + (yTmp - yHit) * (yTmp - yHit) <= scp2)
                {
                    mTgtArr[mTgtArrSz] = i;
                    mTgtArrSz++;
                }
            }

            if(mTgtArrSz > 0)
            {
                GmEfWpn gmEW = new GmEfWpn(mAL, mSG, this);
                mSG.addClckbl(3, gmEW);
            } else
            {
                res = false;
            }
        }
        return res;
    }

    public void dcdSelSkl()
    {
        mSkl = mGDUnt.getSkl(mUId);
    }

    public int getSklI(int typ)
    {
        if(mSkl == -1)
            dcdSelSkl();
        return mSklI[mSkl][typ];
    }

    public void doAct()
    {
        for(int i = 0; i < mTgtArrSz; i++)
            doAct(mTgtArr[i]);

    }

    public void doAct(int tgtId)
    {
        GmDtUnt lGDUnt = mGDUnt;
        if(lGDUnt.getEnbl(tgtId) == 0)
            return;
        lGDUnt.setIdMy(mUId);
        lGDUnt.setIdTgt(tgtId);
        int tgtUtyp = lGDUnt.getTgtTyp();
        int sklI[] = mSklI[mSkl];
        int untI[] = mUntI[tgtUtyp];
        int atPow = sklI[GmDt.SKL_I_AT];
        if(lGDUnt.getMyUS(US_EX_AT_TM) > 0)
        {
            int exAt = lGDUnt.getMyUS(US_EX_AT_PW);
            if(exAt > 0)
            {
                atPow += (atPow * exAt) / 100;
                if(atPow >= 100)
                    atPow = 99;
            } else
            if(exAt < 0)
            {
                atPow -= (atPow * exAt) / 100;
                if(atPow < 0)
                    atPow = 0;
            }
        }
        int sklAttr = sklI[GmDt.SKL_I_ATTR];
        int tgtUntAttr = untI[GmDt.UNT_I_ATTR];
        if(sklAttr == 2 && tgtUntAttr == 1)
            atPow = (atPow * 15) / 10;
        if(sklAttr == 1 && tgtUntAttr == 1)
            atPow = (atPow * 5) / 10;
        if(atPow >= 100)
            atPow = 99;
        int dfPow = untI[GmDt.UNT_I_DF];
        int wDfPow = mSklI[lGDUnt.getSkl(tgtId)][GmDt.SKL_I_DF];
        dfPow = (dfPow * wDfPow) / 100;
        if(dfPow >= 100)
            dfPow = 99;
        if(lGDUnt.getTgtUS(US_EX_DF_TM) > 0)
        {
            int exDf = lGDUnt.getTgtUS(US_EX_DF_PW);
            if(exDf > 0)
            {
                dfPow += (dfPow * exDf) / 100;
                if(dfPow >= 100)
                    dfPow = 99;
            } else
            if(exDf < 0)
            {
                dfPow -= (dfPow * exDf) / 100;
                if(dfPow < 0)
                    dfPow = 0;
            }
        }
        if(atPow > 0)
        {
            int dmg = atPow;
            if(dfPow < atPow / 2)
            {
                dmg -= dfPow;
            } else
            {
                dmg -= atPow / 2;
                dmg -= (dmg * (dfPow - atPow / 2)) / 100;
            }
            int newHp = lGDUnt.getTgtHp();
            newHp -= dmg;
            if(lGDUnt.getTgtUS(US_TM_DMG_1ST) == 0)
                lGDUnt.setTgtUS(US_TM_DMG_1ST, (int)mSG.mTmGm);
            lGDUnt.setTgtUS(US_DMG_SUM, lGDUnt.getTgtUS(US_DMG_SUM) + dmg);
            lGDUnt.setTgtUS(US_DMG_CNT, lGDUnt.getTgtUS(US_DMG_CNT) + 1);
            mSG.mGmLog.addLog(UtlTool.smplFormat(mLogStr, new Object[] {
                mAL.mMngLng.getS("scnGm_LogAt"), UtlTool.smplFormat("%2d", new Object[] {
                    Integer.valueOf(dmg)
                }), mAL.egV.mGD.mUntS[mUTyp][1], mAL.mMngLng.getS("scnGm_LogDrc"), mAL.egV.mGD.mUntS[tgtUtyp][1]
            }));
            if(newHp <= 0)
            {
                dmg = lGDUnt.getTgtHp();
                lGDUnt.setTgtUS(US_ENBL, 0);
                lGDUnt.setTgtHp(0);
                lGDUnt.setTgtUS(US_TM_DMG_DTH, (int)mSG.mTmGm);
                mSG.mGTScr.chkGmEnd();
                GmEfDth gmEfDth = new GmEfDth(mAL, mSG, tgtId < 32, mGDUnt.getUS(tgtId, US_MV_X_NOW), mGDUnt.getUS(tgtId, US_MV_Y_NOW), mGDUnt.getUS(tgtId, US_TM_DMG_DTH) - mGDUnt.getUS(tgtId, US_TM_DMG_1ST));
                mSG.addClckbl(4, gmEfDth);
                return;
            }
            lGDUnt.setTgtHp(newHp);
            lGDUnt.doRst(mUId, false);
            if(sklI[GmDt.SKL_I_DRN] == 1)
            {
                int myHp = lGDUnt.getMyHp();
                int myHpMax = mUntI[mUTyp][GmDt.UNT_I_HP];
                myHp += dmg / 2;
                if(myHp > myHpMax)
                    myHp = myHpMax;
                lGDUnt.setMyHp(myHp);
            }
        }
        if(atPow < 0)
        {
            int newHp = lGDUnt.getTgtHp();
            newHp += atPow * -1;
            int maxHp = untI[GmDt.UNT_I_HP];
            if(newHp > maxHp)
                newHp = maxHp;
            lGDUnt.setTgtHp(newHp);
            mSG.mGmLog.addLog(UtlTool.smplFormat(mLogStr, new Object[] {
                mAL.mMngLng.getS("scnGm_LogHl"), UtlTool.smplFormat("%2d", new Object[] {
                    Integer.valueOf(atPow * -1)
                }), mAL.egV.mGD.mUntS[mUTyp][1], mAL.mMngLng.getS("scnGm_LogDrc"), mAL.egV.mGD.mUntS[tgtUtyp][1]
            }));
        }
        int exTm = sklI[GmDt.SKL_I_EX_TM];
        if(exTm > 0)
        {
            for(int i = 0; i < US_EX_SZ; i++)
            {
                int exPrm = sklI[GmDt.SKL_I_EX_DF + i];
                if(exPrm != 0)
                {
                    lGDUnt.setTgtUS(US_EX_TM_STRT + i * 2, exTm);
                    lGDUnt.setTgtUS(US_EX_PW_STRT + i * 2, exPrm);
                    if(GmDt.SKL_I_EX_DF + i == GmDt.SKL_I_EX_MV)
                        lGDUnt.setTgtUS(US_ELPS_MV, 0);
                    mSG.mGmLog.addLog(UtlTool.smplFormat(mLogStr, new Object[] {
                        mAL.mMngLng.getS("scnGm_LogEnchnt"), "  ", mAL.egV.mGD.mUntS[mUTyp][1], mAL.mMngLng.getS("scnGm_LogDrc"), mAL.egV.mGD.mUntS[tgtUtyp][1]
                    }));
                }
            }

        }
    }

    public void finish()
    {
        mAL = null;
        mSG = null;
        mGDUnt = null;
        mUntI = null;
        mSklI = null;
        mTgtArr = null;
        mLogStr = null;
        mIsFnsh = true;
    }

    private AppLoopSimWar mAL;
    private ScnGm mSG;
    private GmDtUnt mGDUnt;
    public boolean mIsFnsh;
    public int mUId;
    public int mUTyp;
    public int mUntI[][];
    public int mSklI[][];
    public int mSkl;
    private boolean mActRunning;
    public int mTgtId;
    public int mTgtArr[];
    public int mTgtArrSz;
    String mLogStr;
}
