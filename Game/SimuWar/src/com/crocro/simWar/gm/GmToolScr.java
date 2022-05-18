package com.crocro.simWar.gm;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V.GD;
import com.crocro.simWar.app.V.GDA;
import com.crocro.simWar.clckbl.GmUnt;
import com.crocro.simWar.scn.ScnGm;
import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.bs.F;
import com.crocro.wrp.clckbl.LghtBox;
import com.crocro.wrp.clckbl.LghtBox.ArrBtn;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.mng.MngLng;
import com.crocro.wrp.mng.MngOpt;
import com.crocro.wrp.mng.MngScn;
import com.crocro.wrp.mng.MngSnd;
import com.crocro.wrp.utl.Actr;
import com.crocro.wrp.utl.Drwr;
import com.crocro.wrp.utl.Rsrv;
import com.crocro.wrp.utl.UtlDwEf;
import com.crocro.wrp.utl.UtlTool;
import com.crocro.wrp.wrp.WrpDw;
import com.crocro.wrp.wrp.WrpNet;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;

public class GmToolScr
  implements F, GmDtUntF
{
  private AppLoopSimWar mAL;
  private ScnGm mSG;
  private GmDtUnt mGDUnt;
  public static final int WL_NOT = 0;
  public static final int WL_WIN = 1;
  public static final int WL_LOSE = 2;
  public int mWinLose = 0;
  public static final int RNK_D = 0;
  public static final int RNK_C = 1;
  public static final int RNK_B = 2;
  public static final int RNK_A = 3;
  public static final int RNK_S = 4;
  public static final int RNK_SS = 5;
  public static final int RNK_SSS = 6;
  public static final String[] RNK_STR = { "D", "C", "B", "A", "S", "SS", "SSS" };
  public static final int[] RNK_PNT = { 1, 10, 100, 1000, 2000, 3000, 5000 };
  public static final int[] WTHIN_PNT = { 1000, 500, 200, 100, 50 };
  public static final int TM_A_BS = 10;
  public static final int WL_POP_TM = 2000;
  public LghtBox gLghtBox;
  private static final int STR_COL_R = 255;
  private static final int STR_COL_G = 255;
  private static final int STR_COL_B = 255;
  private static final int STR_SZ_PER = 200;
  private static final int BG_COL_WIN_R = 24;
  private static final int BG_COL_WIN_G = 24;
  private static final int BG_COL_WIN_B = 24;
  private static final int BG_COL_LS_R = 64;
  private static final int BG_COL_LS_G = 64;
  private static final int BG_COL_LS_B = 64;
  private final Drwr gAnmDrwr = new Drwr()
  {
    public void dw()
    {
      AppLoopSimWar al = GmToolScr.this.mAL;

      int scrW = GmToolScr.this.mAL.mMC.getScrW();
      int scrH = GmToolScr.this.mAL.mMC.getScrH();

      if (GmToolScr.this.mWinLose == 1)
      {
        int winBgW = al.mWD.getBufW(ScnGm.IMG_ID_WIN_BG);
        int winBgH = al.mWD.getBufH(ScnGm.IMG_ID_WIN_BG);
        al.mWD.dwImg(ScnGm.IMG_ID_WIN_BG, (scrW - winBgW) / 2, scrH - winBgH);

        UtlDwEf.dwCnftt(GmToolScr.this.mAL, scrH * scrW / 2904);

        UtlDwEf.dwFireWrks(GmToolScr.this.mAL, GmToolScr.this.mAL.mTmNow);
        UtlDwEf.dwFireWrks(GmToolScr.this.mAL, GmToolScr.this.mAL.mTmNow + 2100L);
        UtlDwEf.dwFireWrks(GmToolScr.this.mAL, GmToolScr.this.mAL.mTmNow + 4200L);
        int h;
        int w = h = 0;
        for (int i = 1; i < 32; i++) {
          int uTyp = GmToolScr.this.mGDUnt.getTyp(i);

          if (uTyp < 0) {
            continue;
          }
          if (w == 0) {
            w = GmToolScr.this.mSG.mGIUnt.mWDIn.getBufW(uTyp);
            h = GmToolScr.this.mSG.mGIUnt.mWDIn.getBufH(uTyp);
          }
          if ((i - 1) * w >= scrW) {
            break;
          }
          GmToolScr.this.mAL.mWD.dwImg(
            GmToolScr.this.mSG.mGIUnt.mWDIn, 
            uTyp, 
            w * (i - 1), 
            scrH - h - (int)(h * GmToolScr.SIN[(int)((al.mTmNow + i * 60) % 600L * 1.0D / 600.0D * 180.0D)]));
        }
      }
      else
      {
        for (int i = 0; i < 17; i++) {
          UtlDwEf.dwDrpOfWtr(GmToolScr.this.mAL, GmToolScr.this.mAL.mTmNow + 1750 * i + 1750 * i / 17);
        }

      }

      int w = al.mWD.getBufW(ScnGm.IMG_ID_UNT_B_TMP);
      int h = al.mWD.getBufH(ScnGm.IMG_ID_UNT_B_TMP);

      int x = al.mMC.getScrW();
      x -= w * 8 / 10;

      int bsH = scrH;
      int y = bsH - h + h * 1 / 10;
      y -= (int)((bsH - h) * 3 / 5 * GmToolScr.SIN[(int)(al.mTmNow % 600L * 1.0D / 600.0D * 180.0D)]);

      al.mWD.dwImg(ScnGm.IMG_ID_UNT_B_TMP, x, y);
    }
  };

  private final int BTN_ARR_H = 40;

  private String mPstRcrdStr = "";

  public GmToolScr(AppLoopSimWar al, ScnGm sg)
  {
    this.mAL = al;
    this.mSG = sg;
    this.mGDUnt = this.mSG.mGDUnt;
  }

  public void chkGmEnd()
  {
    if (this.mWinLose != 0) return;

    int dthAll = this.mSG.mGDUnt.chkDthAll();
    if (dthAll == 1)
    {
      this.mWinLose = 2;
      doLose();
    }
    else if (dthAll == 2)
    {
      this.mWinLose = 1;
      doWin();
    }
  }

  public void doLose()
  {
    Rsrv rsrv = new Rsrv(this.mAL)
    {
      public void addAct() {
        GmToolScr.this.doLoseIn();
        GmToolScr.this.delClckbl();

        GmToolScr.this.mSG.addClckbl(9, GmToolScr.this.gLghtBox);
      }
    };
    rsrv.setTm(2000L);
    this.mAL.mRsrvLst.add(rsrv);
  }

  public void doLoseIn()
  {
    int uTyp = this.mSG.mGDUnt.getTyp(32);
    this.mAL.mWD.mkBuf(
      ScnGm.IMG_ID_UNT_B_TMP, 
      UtlTool.smplFormat("ub/%s.png", new Object[] { this.mAL.egV.mGD.mUntS[uTyp][0] }));

    this.gLghtBox = 
      new LghtBox(this.mAL, this.mAL.mMngLng.getS("scnGm_MesLose"), this.mSG, 
      new Actr() {
      public void act() { GmToolScr.this.mAL.mMS.setNxtScn(com.crocro.simWar.app.V.SCN_SEL_STG);
      }
    });
    this.gLghtBox.mAddDrwrPreStr = this.gAnmDrwr;
    this.gLghtBox.mBgColR = 64;
    this.gLghtBox.mBgColG = 64;
    this.gLghtBox.mBgColB = 64;
    this.gLghtBox.mStrColR = 255;
    this.gLghtBox.mStrColG = 255;
    this.gLghtBox.mStrColB = 255;
    this.gLghtBox.mStrSzPer = 200;
  }

  public void doWin()
  {
    Rsrv rsrv = new Rsrv(this.mAL)
    {
      public void addAct() {
        GmToolScr.this.doWinIn();
        GmToolScr.this.delClckbl();
        this.mAL.mMngSnd.playBgm("bgmWin");

        GmToolScr.this.mSG.addClckbl(9, GmToolScr.this.gLghtBox);

        LghtBox lghtBox = new LghtBox(
          this.mAL, this.mAL.mMngLng.getS("scnGm_MesWin"), GmToolScr.this.mSG);

        lghtBox.mAddDrwrPstStr = GmToolScr.this.gAnmDrwr;
        lghtBox.mBgColR = 24;
        lghtBox.mBgColG = 24;
        lghtBox.mBgColB = 24;
        lghtBox.mStrColR = 255;
        lghtBox.mStrColG = 255;
        lghtBox.mStrColB = 255;
        lghtBox.mStrSzPer = 200;
        GmToolScr.this.mSG.addClckbl(9, lghtBox);
      }
    };
    rsrv.setTm(2000L);
    this.mAL.mRsrvLst.add(rsrv);
  }

  public void doWinIn()
  {
    int stgNow = this.mAL.egV.gd.stgNow;

    if (stgNow != this.mAL.egV.mStgSz)
    {
      this.mAL.egV.gda.stgEnbl[stgNow] = 1;
    }

    int elpsTm = (int)this.mSG.mTmGm;
    int sumHpMax = 0;
    int sumHpNow = 0;
    int sumHpPer = 0;
    int sumEneNo = 0;
    double enePerSec = 0.0D;

    int sumEneDmgSum = 0;
    int sumEneDmgCnt = 0;
    int sumMyDmgSum = 0;
    int sumMyDmgCnt = 0;

    int sumDthTm1 = 0;
    int sumDthTm2 = 0;
    int sumDthTm3 = 0;
    int sumDthTm4 = 0;
    int sumDthTm5 = 0;
    int sumDthTmSum = 0;

    for (int i = 0; i < 132; i++) {
      int uTyp = this.mGDUnt.getTyp(i);
      if (uTyp < 0)
        continue;
      int dmgSum = this.mGDUnt.getUS(i, GmUnt.US_DMG_SUM);
      int dmgCnt = this.mGDUnt.getUS(i, GmUnt.US_DMG_CNT);
      if (i < 32) {
        int hpMax = this.mAL.egV.mGD.mUntI[uTyp][GmDt.UNT_I_HP];
        int hpNow = this.mGDUnt.getHp(i);
        sumHpMax += hpMax;
        sumHpNow += hpNow;

        sumMyDmgSum += dmgSum;
        sumMyDmgCnt += dmgCnt;
      } else {
        sumEneNo++;
        sumEneDmgSum += dmgSum;
        sumEneDmgCnt += dmgCnt;

        int tm1stToDth = 
          this.mGDUnt.getUS(i, GmUnt.US_TM_DMG_DTH) - 
          this.mGDUnt.getUS(i, GmUnt.US_TM_DMG_1ST);

        if (tm1stToDth / 1000 <= 1) sumDthTm1++;
        else if (tm1stToDth / 1000 <= 2) sumDthTm2++;
        else if (tm1stToDth / 1000 <= 3) sumDthTm3++;
        else if (tm1stToDth / 1000 <= 4) sumDthTm4++;
        else if (tm1stToDth / 1000 <= 5) sumDthTm5++;
        sumDthTmSum += tm1stToDth;
      }
    }
    if (sumHpMax > 0) sumHpPer = 100 * sumHpNow / sumHpMax;

    int rnkTm = 0;
    enePerSec = elpsTm * 0.001D / sumEneNo;
    if (enePerSec <= 5.5D) rnkTm = 6;
    else if (enePerSec <= 7.0D) rnkTm = 5;
    else if (enePerSec <= 8.5D) rnkTm = 4;
    else if (enePerSec <= 10.0D) rnkTm = 3;
    else if (enePerSec <= 15.0D) rnkTm = 2;
    else if (enePerSec <= 20.0D) rnkTm = 1; else {
      rnkTm = 0;
    }

    int rnkHp = 0;

    if (sumHpPer == 100.0D) rnkHp = 6;
    else if (sumHpPer >= 97.5D) rnkHp = 5;
    else if (sumHpPer >= 95.0D) rnkHp = 4;
    else if (sumHpPer >= 90.0D) rnkHp = 3;
    else if (sumHpPer >= 80.0D) rnkHp = 2;
    else if (sumHpPer >= 70.0D) rnkHp = 1; else {
      rnkHp = 0;
    }

    double perEneDmg = sumEneDmgCnt > 0 ? sumEneDmgSum / sumEneDmgCnt : 0;
    double perMyDmg = sumMyDmgCnt > 0 ? sumMyDmgSum / sumMyDmgCnt : 0;

    int bnsDthTm1 = sumDthTm1 * WTHIN_PNT[0];
    int bnsDthTm2 = sumDthTm2 * WTHIN_PNT[1];
    int bnsDthTm3 = sumDthTm3 * WTHIN_PNT[2];
    int bnsDthTm4 = sumDthTm4 * WTHIN_PNT[3];
    int bnsDthTm5 = sumDthTm5 * WTHIN_PNT[4];

    int score = RNK_PNT[rnkTm] + RNK_PNT[rnkHp] + 
      bnsDthTm1 + bnsDthTm2 + bnsDthTm3 + bnsDthTm4 + bnsDthTm5;

    int stgSvPos = stgNow - 1;

    int oldBestTm = 0;
    int oldBestScr = 0;
    boolean isBstTm = false;
    boolean isBstScr = false;

    int[] tgtArr = this.mAL.egV.gda.stgWinTm;
    oldBestTm = tgtArr[stgSvPos];
    if ((oldBestTm == 0) || 
      (oldBestTm > elpsTm))
    {
      tgtArr[stgSvPos] = elpsTm;
      if (oldBestTm > 0) isBstTm = true;

    }

    tgtArr = this.mAL.egV.gda.stgWinScr;
    oldBestScr = tgtArr[stgSvPos];
    if ((oldBestScr == 0) || 
      (oldBestScr < score))
    {
      tgtArr[stgSvPos] = score;
      if (oldBestScr > 0) isBstScr = true;

    }

    tgtArr = this.mAL.egV.gda.stgWinNo;
    tgtArr[stgSvPos] += 1;

    this.mAL.mV.svDt(0);

    MngLng l = this.mAL.mMngLng;

    String[][] strArr = new String[15][];
    int[] wRateArr = { 3, 7 };
    int[] hRateArr = (int[])null;
    int ln = 0;

    strArr[ln] = l.f("scr_WinTm", new Object[] { UtlTool.dgt(elpsTm * 0.001D, 2) }).split(";"); ln++;

    strArr[ln] = l.f("scr_WinEne", new Object[] { Integer.valueOf(sumEneNo) }).split(";"); ln++;
    strArr[ln] = l.f("scr_WinEPer", new Object[] { 
      UtlTool.dgt(enePerSec, 2), 
      RNK_STR[rnkTm], 
      UtlTool.nf(RNK_PNT[rnkTm]) }).split(";"); ln++;

    strArr[ln] = l.f("scr_WinSK1", new Object[] { Integer.valueOf(sumDthTm1), UtlTool.nf(bnsDthTm1) }).split(";");
    for (int i = 0; i < strArr[ln].length; i++) strArr[ln][i] = ("bgLght$" + strArr[ln][i]); ln++;
    strArr[ln] = l.f("scr_WinSK2", new Object[] { Integer.valueOf(sumDthTm2), UtlTool.nf(bnsDthTm2) }).split(";");
    for (int i = 0; i < strArr[ln].length; i++) strArr[ln][i] = ("bgLght$" + strArr[ln][i]); ln++;
    strArr[ln] = l.f("scr_WinSK3", new Object[] { Integer.valueOf(sumDthTm3), UtlTool.nf(bnsDthTm3) }).split(";");
    for (int i = 0; i < strArr[ln].length; i++) strArr[ln][i] = ("bgLght$" + strArr[ln][i]); ln++;
    strArr[ln] = l.f("scr_WinSK4", new Object[] { Integer.valueOf(sumDthTm4), UtlTool.nf(bnsDthTm4) }).split(";");
    for (int i = 0; i < strArr[ln].length; i++) strArr[ln][i] = ("bgLght$" + strArr[ln][i]); ln++;
    strArr[ln] = l.f("scr_WinSK5", new Object[] { Integer.valueOf(sumDthTm5), UtlTool.nf(bnsDthTm5) }).split(";");
    for (int i = 0; i < strArr[ln].length; i++) strArr[ln][i] = ("bgLght$" + strArr[ln][i]); ln++;
    strArr[ln] = l.f("scr_WinSKAvr", new Object[] { UtlTool.dgt(sumDthTmSum / 1000.0D / sumEneNo, 2) }).split(";");
    for (int i = 0; i < strArr[ln].length; i++) strArr[ln][i] = ("bgLght$" + strArr[ln][i]); ln++;

    strArr[ln] = l.f("scr_WinHp", new Object[] { Integer.valueOf(sumHpNow), Integer.valueOf(sumHpMax) }).split(";"); ln++;
    strArr[ln] = l.f("scr_WinHpPer", new Object[] { Integer.valueOf(sumHpPer), RNK_STR[rnkHp], UtlTool.nf(RNK_PNT[rnkHp]) }).split(";"); ln++;

    strArr[ln] = l.f("scr_WinScr", new Object[] { UtlTool.nf(score) }).split(";");
    for (int i = 0; i < strArr[ln].length; i++) strArr[ln][i] = ("bgHighLght$" + strArr[ln][i]);
    ln++;

    strArr[ln] = l.f("scr_WinDfclt", new Object[] { Integer.valueOf(this.mAL.egV.gd.dfclt + 1) }).split(";"); ln++;

    if (oldBestTm == 0)
    {
      strArr[ln] = new String[]{ "" }; ln++;
    }
    else if (elpsTm == oldBestTm)
    {
      strArr[ln] = l.f("scr_SameTm", new Object[] { 
        UtlTool.dgt(oldBestTm * 0.001D, 2) })
        .split(";"); ln++;
    }
    else if (isBstTm)
    {
      strArr[ln] = l.f("scr_BstTm", new Object[] { 
        UtlTool.dgt(oldBestTm * 0.001D, 2), 
        UtlTool.dgt(elpsTm * 0.001D, 2) })
        .split(";"); ln++;
    }
    else {
      strArr[ln] = l.f("scr_NotBstTm", new Object[] { 
        UtlTool.dgt(oldBestTm * 0.001D, 2), 
        UtlTool.dgt((elpsTm - oldBestTm) * 0.001D, 2) })
        .split(";"); ln++;
    }

    if (oldBestScr == 0)
    {
      strArr[ln] = new String[]{ "" }; ln++;
    }
    else if (oldBestScr == score)
    {
      strArr[ln] = l.f("scr_SameScr", new Object[] { 
        UtlTool.nf(oldBestScr) })
        .split(";"); ln++;
    }
    else if (isBstScr)
    {
      strArr[ln] = l.f("scr_BstScr", new Object[] { 
        UtlTool.nf(oldBestScr), 
        UtlTool.nf(score) })
        .split(";"); ln++;
    }
    else {
      strArr[ln] = l.f("scr_NotBstScr", new Object[] { 
        UtlTool.nf(oldBestScr), 
        UtlTool.nf(oldBestScr - score) })
        .split(";"); ln++;
    }

    String pth = UtlTool.smplFormat("rcrdStg%s.dat", new Object[] { Integer.valueOf(stgNow) });
    MngOpt mngOpt = new MngOpt(this.mAL, pth, true);

    int rcrdSz = mngOpt.getI("rcrdSz");

    mngOpt.setI(rcrdSz + ".elpsTm", elpsTm);

    mngOpt.setS(rcrdSz + ".WinTm", UtlTool.dgt(elpsTm * 0.001D, 2));
    mngOpt.setI(rcrdSz + ".WinEne", sumEneNo);
    mngOpt.setS(rcrdSz + ".WinEPerSec", UtlTool.dgt(enePerSec, 2));
    mngOpt.setS(rcrdSz + ".WinEPerRnk", RNK_STR[rnkTm]);
    mngOpt.setI(rcrdSz + ".WinEPerPnt", RNK_PNT[rnkTm]);

    mngOpt.setI(rcrdSz + ".WinHpRst", sumHpNow);
    mngOpt.setI(rcrdSz + ".WinHpStrt", sumHpMax);
    mngOpt.setI(rcrdSz + ".WinHpPer", sumHpPer);
    mngOpt.setS(rcrdSz + ".WinHpPerRnk", RNK_STR[rnkHp]);
    mngOpt.setI(rcrdSz + ".WinHpPerPnt", RNK_PNT[rnkHp]);

    mngOpt.setI(rcrdSz + ".WinSK1Cnt", sumDthTm1);
    mngOpt.setI(rcrdSz + ".WinSK1Pnt", bnsDthTm1);
    mngOpt.setI(rcrdSz + ".WinSK2Cnt", sumDthTm2);
    mngOpt.setI(rcrdSz + ".WinSK2Pnt", bnsDthTm2);
    mngOpt.setI(rcrdSz + ".WinSK3Cnt", sumDthTm3);
    mngOpt.setI(rcrdSz + ".WinSK3Pnt", bnsDthTm3);
    mngOpt.setI(rcrdSz + ".WinSK4Cnt", sumDthTm4);
    mngOpt.setI(rcrdSz + ".WinSK4Pnt", bnsDthTm4);
    mngOpt.setI(rcrdSz + ".WinSK5Cnt", sumDthTm5);
    mngOpt.setI(rcrdSz + ".WinSK5Pnt", bnsDthTm5);
    mngOpt.setS(rcrdSz + ".WinSKAvr", UtlTool.dgt(sumDthTmSum / 1000.0D / sumEneNo, 2));
    mngOpt.setI(rcrdSz + ".WinScr", score);

    mngOpt.setI(rcrdSz + ".WinDmg2ESum", sumEneDmgSum);
    mngOpt.setI(rcrdSz + ".WinDmg2ECnt", sumEneDmgCnt);
    mngOpt.setS(rcrdSz + ".WinDmg2EPer", UtlTool.dgt(perEneDmg, 1));
    mngOpt.setI(rcrdSz + ".WinDmg2MSum", sumMyDmgSum);
    mngOpt.setI(rcrdSz + ".WinDmg2MCnt", sumMyDmgCnt);
    mngOpt.setS(rcrdSz + ".WinDmg2MPer", UtlTool.dgt(perMyDmg, 1));
    mngOpt.setI(rcrdSz + ".WinDfclt", this.mAL.egV.gd.dfclt + 1);

    Calendar cal = Calendar.getInstance();
    mngOpt.setI(rcrdSz + ".WinYear", cal.get(1));
    mngOpt.setI(rcrdSz + ".WinMonth", cal.get(2) + 1);
    mngOpt.setI(rcrdSz + ".WinDay", cal.get(5));
    mngOpt.setI(rcrdSz + ".WinHour", cal.get(11));
    mngOpt.setI(rcrdSz + ".WinMinute", cal.get(12));
    mngOpt.setI(rcrdSz + ".WinSecond", cal.get(13));

    rcrdSz++;
    mngOpt.setI("rcrdSz", rcrdSz);

    mngOpt.sv();
    mngOpt.finish();

    this.mPstRcrdStr = this.mAL.mMngLng.getS("scrPstPrm");
    this.mPstRcrdStr = UtlTool.replOne(this.mPstRcrdStr, "$ttl$", this.mAL.mBsOpt.getS("appNm"));
    this.mPstRcrdStr = UtlTool.replOne(this.mPstRcrdStr, "$stg$", String.valueOf(stgNow));
    this.mPstRcrdStr = UtlTool.replOne(this.mPstRcrdStr, "$dfclt$", String.valueOf(this.mAL.egV.gd.dfclt + 1));
    this.mPstRcrdStr = UtlTool.replOne(this.mPstRcrdStr, "$scr$", UtlTool.nf(score));
    this.mPstRcrdStr = UtlTool.replOne(this.mPstRcrdStr, "$ctm$", UtlTool.dgt(elpsTm * 0.001D, 2));
    try {
      this.mPstRcrdStr = URLEncoder.encode(this.mPstRcrdStr, "UTF-8");
      this.mPstRcrdStr = UtlTool.repAll(this.mPstRcrdStr, "+", "%20");
    }
    catch (Exception e)
    {
    	e.printStackTrace();
    }

    int uTyp = this.mGDUnt.getTyp(0);
    this.mAL.mWD.mkBuf(
      ScnGm.IMG_ID_UNT_B_TMP, 
      UtlTool.smplFormat("ub/%s.png", new Object[] { this.mAL.egV.mGD.mUntS[uTyp][0] }));

    this.mAL.mWD.mkBuf(
      ScnGm.IMG_ID_WIN_BG, 
      "gm/win_bg.png");

    this.gLghtBox = 
      new LghtBox(this.mAL, "", this.mSG, 
      new Actr() {
      public void act() { GmToolScr.this.mAL.mMngSnd.playBgm("bgmTtl");
        GmToolScr.this.mAL.mMS.setNxtScn(com.crocro.simWar.app.V.SCN_WIN_GM);
      }
    });
    this.gLghtBox.mBtnH = 40;
    this.gLghtBox.useArrBtn(new LghtBox.ArrBtn[] { 
      new LghtBox.ArrBtn(ScnGm.IMG_ID_TWITTER, this.mAL.mWD)
    {
      public boolean doClck() {
        String urlStr = GmToolScr.this.mAL.mMngLng.getS("scrPstTwUrl");
        urlStr = UtlTool.replOne(urlStr, "$rep$", GmToolScr.this.mPstRcrdStr);
        GmToolScr.this.mAL.mWN.openBrwsr(urlStr);

        return false;
      }
    }
    , new LghtBox.ArrBtn(ScnGm.IMG_ID_FACEBOOK, this.mAL.mWD)
    {
      public boolean doClck() {
        String urlStr = GmToolScr.this.mAL.mMngLng.getS("scrPstFbUrl");
        urlStr = UtlTool.replOne(urlStr, "$rep$", GmToolScr.this.mPstRcrdStr);
        GmToolScr.this.mAL.mWN.openBrwsr(urlStr);

        return false;
      }
    }
    , new LghtBox.ArrBtn("Close")
    {
      public boolean doClck() {
        GmToolScr.this.mAL.mMngSnd.playBgm("bgmTtl");
        GmToolScr.this.mAL.mMS.setNxtScn(com.crocro.simWar.app.V.SCN_WIN_GM);

        return true;
      }
    }
     });
    this.gLghtBox.useDwTbl(strArr, wRateArr, hRateArr);
  }

  public void delClckbl()
  {
    this.mSG.clrClckblLyr(1);
    this.mSG.clrClckblLyr(2);
    this.mSG.clrClckblLyr(3);
    this.mSG.clrClckblLyr(5);
    this.mSG.clrClckblLyr(6);
  }

  public void finish()
  {
    this.mAL = null;
    this.mSG = null;
    this.mGDUnt = null;

    if (this.gLghtBox != null) {
      this.gLghtBox.finish();
      this.gLghtBox = null;
    }
  }
}