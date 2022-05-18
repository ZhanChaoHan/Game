// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GmUnt.java

package com.crocro.simWar.clckbl;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V;
import com.crocro.simWar.gm.*;
import com.crocro.simWar.scn.ScnGm;
import com.crocro.wrp.clckbl.Btn;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.mng.MngLayout;
import com.crocro.wrp.wrp.WrpDw;

public class GmUnt extends Btn
    implements GmDtUntF
{

    public GmUnt(AppLoopSimWar al, ScnGm sg, int id)
    {
        super(al, 0, 0, 0, 0, 1);
        mRtPos = new int[800][2];
        mRtEnbl = false;
        mAL = (AppLoopSimWar)super.mAL;
        mSG = sg;
        mGDUnt = mSG.mGDUnt;
        mGTMap = mSG.mGTMap;
        mGIUnt = mSG.mGIUnt;
        mUId = id;
        mUTyp = mGDUnt.getTyp(mUId);
        mGIUnt.ldImg(mUTyp);
        mGTSkl = new GmToolSkl(mAL, mSG, mUId);
        mRtW = mSG.mGDMap.mRtW;
        mRtH = mSG.mGDMap.mRtH;
        mW = mGIUnt.mWDIn.getBufW(mUTyp);
        mH = mGIUnt.mWDIn.getBufH(mUTyp);
        mGDUnt.setIdMy(mUId);
        int xNew = mGDUnt.getMyUS(US_MV_X_NOW) - mW / 2;
        int yNew = mGDUnt.getMyUS(US_MV_Y_NOW) - mH / 2;
        setPos(xNew, yNew);
        mGDUnt.setMyUS(US_ELPS_ACT, getNewElps(30, 1000));
    }

    public int chkClckIn(int x, int y)
    {
        if(mSG.mUSelTyp != 2)
        {
            if(super.chkClckIn(x, y) == 1)
                mSG.setSelUnt(mUId);
            return 0;
        } else
        {
            return super.chkClckIn(x, y);
        }
    }

    public void doAlwys()
    {
        if(!chkEnbl())
            return;
        if(mSG.mGmPrgrs)
            clcMvUnt();
        mGDUnt.setIdMy(mUId);
        mXIn = mGDUnt.getMyUS(US_MV_X_NOW) - mW / 2;
        mYIn = mGDUnt.getMyUS(US_MV_Y_NOW) - mH / 2;
        super.doAlwys();
    }

    public void dwBfr()
    {
        if(!chkEnbl())
            return;
        int lX = mX;
        int lY = mY;
        int lW = mW;
        int lH = mH;
        if(lX + lW * 2 < 0 || lX - lW >= mAL.mMC.getScrW() || lY + lH * 2 < 0 || lY - lH >= mAL.mMC.getScrH())
            return;
        WrpDw lWD = mWD;
        GmDtUnt lGDUnt = mGDUnt;
        long tmGm = mSG.mTmGm;
        int untI[] = mAL.egV.mGD.mUntI[mUTyp];
        lGDUnt.setIdMy(mUId);
        boolean fly = untI[GmDt.UNT_I_FLY] == 1;
        boolean pss = untI[GmDt.UNT_I_PSS] == 1;
        int mv = untI[GmDt.UNT_I_MV];
        int exFly = lGDUnt.getMyUS(US_EX_FLY_PW);
        int exPss = lGDUnt.getMyUS(US_EX_PSS_PW);
        if(exFly > 0)
            fly = true;
        if(exFly < 0)
            fly = false;
        if(exPss > 0)
            pss = true;
        if(exPss < 0)
            pss = false;
        int sklDf = mAL.egV.mGD.mSklI[lGDUnt.getMySkl()][GmDt.SKL_I_DF];
        if(sklDf != 100)
        {
            int efSz = 0;
            if(sklDf > 100)
            {
                lWD.useCol(WrpDw.COL_GREEN);
                efSz = 1 + (sklDf - 1 - 100) / 50;
            }
            if(sklDf < 100)
            {
                lWD.useCol(WrpDw.COL_DARK_GREEN);
                efSz = 1 + (100 - (sklDf - 1)) / 25;
            }
            int xCntr = lX + lW / 2;
            int yCntr = lY + lH / 2;
            for(int i = 0; i < efSz; i++)
            {
                int w = lW + 8 + i * 8;
                int h = lH - 8 - i * 8;
                lWD.setRtt((int)((tmGm * 360L) / 2L / 1000L), w / 2, h / 2);
                lWD.dwOvl(xCntr - w / 2, yCntr - h / 2, w, h);
                lWD.setRtt((int)((tmGm * 360L) / 2L / 1000L + 90L), w / 2, h / 2);
                lWD.dwOvl(xCntr - w / 2, yCntr - h / 2, w, h);
            }

            lWD.setRtt(0);
        }
        for(int i = 0; i < US_EX_SZ; i++)
        {
            if(i == 0)
            {
                if(lGDUnt.getMyUS(US_EX_DF_TM) <= 0)
                    continue;
                if(lGDUnt.getMyUS(US_EX_DF_PW) > 0)
                    lWD.useCol(WrpDw.COL_BLUE);
                if(lGDUnt.getMyUS(US_EX_DF_PW) < 0)
                    lWD.useCol(WrpDw.COL_DARK_BLUE);
            }
            if(i == 1)
            {
                if(lGDUnt.getMyUS(US_EX_MV_TM) <= 0)
                    continue;
                if(lGDUnt.getMyUS(US_EX_MV_PW) > 0)
                    lWD.useCol(WrpDw.COL_AQUA);
                if(lGDUnt.getMyUS(US_EX_MV_PW) < 0)
                    lWD.useCol(WrpDw.COL_DARK_AQUA);
            }
            if(i == 2)
            {
                if(lGDUnt.getMyUS(US_EX_AT_TM) <= 0)
                    continue;
                if(lGDUnt.getMyUS(US_EX_AT_PW) > 0)
                    lWD.useCol(WrpDw.COL_RED);
                if(lGDUnt.getMyUS(US_EX_AT_PW) < 0)
                    lWD.useCol(WrpDw.COL_DARK_RED);
            }
            if(i == 3)
            {
                if(lGDUnt.getMyUS(US_EX_FLY_TM) <= 0)
                    continue;
                if(lGDUnt.getMyUS(US_EX_FLY_PW) > 0)
                    lWD.useCol(WrpDw.COL_WHITE);
                if(lGDUnt.getMyUS(US_EX_FLY_PW) < 0)
                    lWD.useCol(WrpDw.COL_DARK_GRAY);
            }
            if(i == 4)
            {
                if(lGDUnt.getMyUS(US_EX_PSS_TM) <= 0)
                    continue;
                if(lGDUnt.getMyUS(US_EX_PSS_PW) > 0)
                    lWD.useCol(WrpDw.COL_PURPLE);
                if(lGDUnt.getMyUS(US_EX_PSS_PW) < 0)
                    lWD.useCol(WrpDw.COL_DARK_PURPLE);
            }
            int xCntr = lX + lW / 2;
            int yCntr = lY + lH / 2;
            int w = lW;
            int h = lH;
            lWD.setRtt((int)((tmGm * 360L) / 2L / 1000L + (long)((i * 360) / US_EX_SZ)), w / 2, h / 2);
            lWD.dwRct(xCntr - w / 2, yCntr - h / 2, w, h);
        }

        lWD.setRtt(0);
        int anmUnt = 12;
        int degUnt = 60;
        int xRvsn = 0;
        int yRvsn = 0;
        if(mv <= 0)
        {
            int mvW = 10;
            int mvAnm = (int)((tmGm / (long)anmUnt) % (long)(degUnt * 2) - (long)degUnt);
            xRvsn = (int)((float)mvW * SIN[(int)((((double)mvAnm * 1.0D) / (double)degUnt) * 2D * 180D + 3600D) % 360]);
        } else
        if(!pss && !fly)
        {
            int deg = (int)((tmGm / (long)anmUnt) % (long)degUnt);
            if(deg >= degUnt / 2)
                deg = degUnt - deg;
            deg -= degUnt / 4;
            if(mAL.mML.l.specLow == 0)
            {
                lWD.setRtt(deg, lW / 2, lH);
            } else
            {
                xRvsn = deg / 2;
                yRvsn = -(int)(COS[(deg + 3600) % 360] * 120F - 120F);
            }
        }
        if(pss)
        {
            int trns = (int)(100F * SIN[(int)((((double)((tmGm / (long)(anmUnt * 2)) % (long)degUnt) * 1.0D) / (double)degUnt) * 180D + 3600D) % 360]);
            lWD.setTrns(trns);
        }
        if(fly)
            yRvsn = (int)((float)(lH / 3) * SIN[(int)((((double)((tmGm / (long)anmUnt) % (long)degUnt) * 1.0D) / (double)degUnt) * 180D + 3600D) % 360]) - lH / 3 / 2;
        lWD.dwImg(mGIUnt.mWDIn, mUTyp, lX + xRvsn, lY + yRvsn);
        if(!pss && !fly && mAL.mML.l.specLow == 0)
            lWD.setRtt(0);
        if(pss)
            lWD.setTrns(100);
        int hp = lGDUnt.getMyHp();
        int hpMax = untI[GmDt.UNT_I_HP];
        int yBtm = lY + lH + -5;
        int w = lW / 3;
        int h = 7;
        int x = (lX + lW) - w;
        int y = yBtm - h;
        lWD.useCol(WrpDw.COL_RED);
        lWD.flRct(x, y, w, h);
        w = ((w -= 2) * hp) / hpMax;
        h -= 2;
        x++;
        y++;
        lWD.useCol(WrpDw.COL_GREEN);
        lWD.flRct(x, y, w, h);
        int mtr = lGDUnt.getMyUS(US_MTR);
        int mtrMax = mGTSkl.getSklI(GmDt.SKL_I_ND_MTR);
        if(mtrMax > 0)
        {
            w = lW / 3;
            h = 7;
            x = (lX + lW) - w;
            y = yBtm - h * 2;
            lWD.useCol(WrpDw.COL_GRAY);
            lWD.flRct(x, y, w, h);
            int tmActLst = lGDUnt.getMyUS(US_TM_ACT_LST);
            int elps = (int)(tmGm - (long)tmActLst);
            if(tmActLst != 0 && elps <= 1000)
            {
                double rate = ((double)elps * 1.0D) / 1000D;
                lWD.useCol(WrpDw.COL_LGHT_YELLOW);
                lWD.setTrns((int)(100D - 100D * rate));
                int sz = (int)((double)h - (double)h * rate) * 2;
                lWD.flRct(x - sz, y - sz, w + sz * 2, h + sz * 2);
                lWD.setTrns(100);
            }
            w = ((w -= 2) * mtr) / mtrMax;
            h -= 2;
            x++;
            y++;
            lWD.useCol(WrpDw.COL_BLUE);
            lWD.flRct(x, y, w, h);
        }
    }

    public void dwAftr()
    {
        dwBfr();
    }

    public boolean chkEnbl()
    {
        boolean res = true;
        if(mGDUnt.getUS(mUId, US_ENBL) == 0)
        {
            res = false;
            rsrvRmv();
        }
        return res;
    }

    public void clcMvUnt()
    {
        GmDtUnt lGDUnt = mGDUnt;
        int uId = mUId;
        int elps = (int)mSG.mTmGm;
        lGDUnt.setIdMy(uId);
        if(elps - lGDUnt.getMyUS(US_ELPS_ACT) >= 1320)
        {
            lGDUnt.setMyUS(US_ELPS_ACT, getNewElps(30, 1000));
            mGTSkl.chkAct();
            lGDUnt.updtEx(uId);
        }
        int mvPow = lGDUnt.getMvPow(uId);
        if(mvPow <= 0)
            return;
        if(lGDUnt.mMvClcFrmArr[uId] == lGDUnt.mMvClcFrmPos)
        {
            lGDUnt.setMyUS(US_ELPS_MV, elps);
            mRtEnbl = mGTMap.updtRt(uId, mRtPos);
        }
        if(!mRtEnbl)
            return;
        int elpsRt = (mvPow * (elps - lGDUnt.getMyUS(US_ELPS_MV))) / 1320;
        if(elpsRt >= mRtPos.length)
            elpsRt = mRtPos.length - 1;
        if(elpsRt < 0)
            elpsRt = 0;
        int rtPos[] = mRtPos[elpsRt];
        lGDUnt.setMyUS(US_MV_X_NOW, rtPos[0]);
        lGDUnt.setMyUS(US_MV_Y_NOW, rtPos[1]);
    }

    public int getNewElps(int intrvlId, int intrvlLoop)
    {
        int elps = (int)mSG.mTmGm;
        int res = (elps / intrvlLoop) * intrvlLoop + (mUId * intrvlId) % intrvlLoop;
        return res;
    }

    public void finish()
    {
        super.finish();
        mSG = null;
        mGDUnt = null;
        mGTMap = null;
        mGIUnt = null;
        if(mGTSkl != null)
            mGTSkl.finish();
        mGTSkl = null;
        mRtPos = null;
    }

    private AppLoopSimWar mAL;
    private ScnGm mSG;
    private GmDtUnt mGDUnt;
    private GmToolMap mGTMap;
    private GmImgUnt mGIUnt;
    private GmToolSkl mGTSkl;
    public int mUId;
    public int mUTyp;
    public static final String IMG_PTH_US = "us/%s.png";
    public static final int BUF_UNT = 0;
    public static final int MTR_BTM = -5;
    public static final int MTR_H = 7;
    public static final int TM_MTR_FLSH = 1000;
    private static final int RT_POS_MAX = 800;
    private int mRtPos[][];
    private boolean mRtEnbl;
    public static final int MV_INTRVL_ID = 30;
    public static final int MV_INTRVL_LOOP = 1320;
    public int mRtW;
    public int mRtH;
    public static final int ACT_INTRVL_ID = 30;
    public static final int ACT_INTRVL_LOOP = 1000;
}
