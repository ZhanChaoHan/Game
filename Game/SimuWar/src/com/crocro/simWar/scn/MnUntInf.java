package com.crocro.simWar.scn;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V;
import com.crocro.simWar.gm.GmDt;
import com.crocro.simWar.gm.GmDtUnt;
import com.crocro.simWar.gm.GmDtUntF;
import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.bs.F;
import com.crocro.wrp.clckbl.DlgMnItm;
import com.crocro.wrp.clckbl.StrBtn;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.mng.MngLayout;
import com.crocro.wrp.mng.MngLayout.Layout;
import com.crocro.wrp.mng.MngLng;
import com.crocro.wrp.mng.MngScn;
import com.crocro.wrp.scn.MngDlg;
import com.crocro.wrp.utl.Drwr;
import com.crocro.wrp.utl.Rsrv;
import com.crocro.wrp.utl.UtlTool;
import com.crocro.wrp.wrp.WrpDw;
import java.util.ArrayList;

public class MnUntInf
  implements F, GmDtUntF
{
  private AppLoopSimWar mAL = null;
  private GmDt mGD = null;
  private ScnGm mSG = null;
  private GmDtUnt mGDUnt = null;
  public int mUId;
  public int mUTyp;
  public int mSklCnt;

  public MnUntInf(AppLoopSimWar al, ScnGm sg)
  {
    this.mAL = al;
    this.mGD = this.mAL.egV.mGD;
    this.mSG = sg;
    this.mGDUnt = this.mSG.mGDUnt;
  }

  public boolean chkFlwInit(int flw)
  {
    if (flw != V.FLW_MN_UNT_INF_INIT) return false;

    flwInit();
    this.mAL.mMS.setNxtFlw(V.FLW_MN_UNT_INF_WAIT);
    return true;
  }

  public boolean chkAlwysWait(int flw)
  {
    if (flw != V.FLW_MN_UNT_INF_WAIT) return false;

    alwysWait();
    return true;
  }

  public void flwInit()
  {
    this.mAL.mMngDlg.initDlg();

    this.mUTyp = this.mGDUnt.getTyp(this.mUId);

    this.mAL.mWD.mkBuf(
      ScnGm.IMG_ID_UNT_B_TMP, 
      UtlTool.smplFormat("ub/%s.png", new Object[] { this.mGD.mUntS[this.mUTyp][0] }));

    Drwr drwr = new Drwr()
    {
      public void dw() {
        MnUntInf.this.mAL.mDfltBGDrwr.dw();

        int wChr = MnUntInf.this.mAL.mWD.getBufW(ScnGm.IMG_ID_UNT_B_TMP);
        int wScr = MnUntInf.this.mAL.mMC.getScrW();
        if (wScr >= 640)
        {
          MnUntInf.this.mAL.mWD.dwImg(ScnGm.IMG_ID_UNT_B_TMP, wScr - wChr, 0);
        }
        else
        {
          MnUntInf.this.mAL.mWD.dwImg(ScnGm.IMG_ID_UNT_B_TMP, (wScr - wChr) / 2 + wScr * 3 / 10, 0);
        }
      }
    };
    this.mAL.mMngDlg.setDrwrPreAlwys(drwr);

    for (int i = 0; i < 5; i++)
    {
      int sklId = this.mGD.mUntI[this.mUTyp][(GmDt.UNT_I_SKL1 + i)];
      if (sklId <= 0) break;
      this.mSklCnt = i;

      DlgMnItm mnItm = new DlgMnItm(this.mAL, this.mAL.mMngDlg, 
        this.mAL.mML.l.btnHDflt * 13 / 10, 
        this.mGD.mSklS[sklId][2], i)
      {
        int sklId = MnUntInf.this.mGD.mUntI[MnUntInf.this.mUTyp][(GmDt.UNT_I_SKL1 + MnUntInf.this.mSklCnt)];

        public void addActClck() { if (MnUntInf.this.mUId < 32) {
            super.addActClck();
          } else {
            int id = MnUntInf.this.mGDUnt.getUS(MnUntInf.this.mUId, GmDtUnt.US_SEL_SKL);
            this.mAL.mMngDlg.setMnSel(id);
            this.mAL.mMngDlg.rflshMnSel(id);
          } }

        public void preBtnBs() {
          this.mAL.mWD.setTrns(95);
        }
        public void pstBtnBs() {
          this.mAL.mWD.setTrns(100);
        }
        public void sel() {
          super.sel();
          MnUntInf.this.setTtl(this.sklId);
        }
      };
      this.mAL.mMngDlg.addLst(mnItm);
    }
    if (this.mGDUnt.getSkl(this.mUId) > 0)
    {
      this.mAL.mMngDlg.setMnSel(this.mGDUnt.getUS(this.mUId, US_SEL_SKL));
    }
    else {
      setTtl(this.mGDUnt.getSkl(this.mUId));
    }

    StrBtn btnOk = new StrBtn(this.mAL, 100, this.mAL.mML.l.dlg_btnH, "OK")
    {
      public void addActClck() {
        this.mAL.mMngDlg.mClckEnbl = false;
        this.mAL.mRsrvLst.add(new Rsrv(this.mAL, 550L)
        {
          public void addAct() {
            MnUntInf.this.mGDUnt.setUS(MnUntInf.this.mUId, GmDtUnt.US_SEL_SKL, this.mAL.mMngDlg.mMnSelNow);

            this.mAL.mWD.delBuf(ScnGm.IMG_ID_UNT_B_TMP);

            this.mAL.mMngDlg.mClckEnbl = true;
            this.mAL.mMngDlg.finishDlg();
            mStatBtn = 0;

            this.mAL.mMS.setNxtFlw(V.FLW_WAIT);
            MnUntInf.this.mSG.unescTmpOpt();
          }
        });
      }

      public boolean doBck() {
        this.mClckX = (this.mX + this.mW / 2);
        this.mClckY = (this.mY + this.mH / 2);
        actClck();

        return true;
      }
    };
    this.mAL.mMngDlg.addBtn(btnOk);

    this.mAL.mMngDlg.layoutAuto();
    this.mAL.mMngDlg.setStrtPgAuto();
  }

  public void setTtl(int sklId)
  {
    String nmU = this.mGD.mUntS[this.mUTyp][1];
    String nmS = this.mGD.mSklS[sklId][2];
    int hpNow = this.mGDUnt.getHp(this.mUId);
    int hpMax = this.mGD.mUntI[this.mUTyp][GmDt.UNT_I_HP];
    int dfSum = (int)(this.mGD.mUntI[this.mUTyp][GmDt.UNT_I_DF] * this.mGD.mSklI[sklId][GmDt.SKL_I_DF] * 0.01D);
    int mvSum = this.mGD.mUntI[this.mUTyp][GmDt.UNT_I_MV];
    int dfBs = this.mGD.mUntI[this.mUTyp][GmDt.UNT_I_DF];
    String dfSklRvsn = UtlTool.smplFormat("%.2f", new Object[] { Double.valueOf(this.mGD.mSklI[sklId][GmDt.SKL_I_DF] * 0.01D) });
    int mtrUp = this.mGD.mUntI[this.mUTyp][GmDt.UNT_I_TCH];
    int mtrNd = this.mGD.mSklI[sklId][GmDt.SKL_I_ND_MTR];
    int atSum = this.mGD.mSklI[sklId][GmDt.SKL_I_AT];
    int rng = this.mGD.mSklI[sklId][GmDt.SKL_I_RNG];
    int scp = this.mGD.mSklI[sklId][GmDt.SKL_I_SCP];

    MngLng l = this.mAL.mMngLng;
    StringBuilder sb = new StringBuilder();
    l.f(sb, "untInf_Nm", new Object[] { nmU, nmS });
    l.f(sb, "untInf_HpDfMv", new Object[] { Integer.valueOf(hpNow), Integer.valueOf(hpMax), Integer.valueOf(mvSum) });
    l.f(sb, "untInf_Df", new Object[] { Integer.valueOf(dfSum), Integer.valueOf(dfBs), dfSklRvsn });
    l.f(sb, "untInf_AtRngScp", new Object[] { Integer.valueOf(atSum), Integer.valueOf(rng), Integer.valueOf(scp) });
    l.f(sb, "untInf_Mtr", new Object[] { Integer.valueOf(mtrUp), Integer.valueOf(mtrNd) });

    int exAt = this.mGD.mSklI[sklId][GmDt.SKL_I_EX_AT];
    int exDf = this.mGD.mSklI[sklId][GmDt.SKL_I_EX_DF];
    int exMv = this.mGD.mSklI[sklId][GmDt.SKL_I_EX_MV];
    int exFly = this.mGD.mSklI[sklId][GmDt.SKL_I_ADD_FLY];
    int exPss = this.mGD.mSklI[sklId][GmDt.SKL_I_ADD_PSS];

    sb.append(l.getS("untInf_Ex"));
    if ((exAt != 0) || (exDf != 0) || (exMv != 0) || (exFly != 0) || (exPss != 0))
    {
      if (exAt != 0) l.f(sb, "untInf_ExAt", new Object[] { Integer.valueOf(exAt) });
      if (exDf != 0) l.f(sb, "untInf_ExDf", new Object[] { Integer.valueOf(exDf) });
      if (exMv != 0) l.f(sb, "untInf_ExMv", new Object[] { Integer.valueOf(exMv) });
      if (exFly > 0) sb.append(l.getS("untInf_ExFlyAdd"));
      if (exFly < 0) sb.append(l.getS("untInf_ExFlyLst"));
      if (exPss > 0) sb.append(l.getS("untInf_ExPssAdd"));
      if (exPss < 0) sb.append(l.getS("untInf_ExPssLst")); 
    }
    else
    {
      sb.append(l.getS("untInf_ExNon"));
    }

    exAt = this.mGDUnt.getUS(this.mUId, US_EX_AT_PW);
    exDf = this.mGDUnt.getUS(this.mUId, US_EX_DF_PW);
    exMv = this.mGDUnt.getUS(this.mUId, US_EX_MV_PW);
    exFly = this.mGDUnt.getUS(this.mUId, US_EX_FLY_PW);
    exPss = this.mGDUnt.getUS(this.mUId, US_EX_PSS_PW);

    sb.append(l.getS("untInf_Stts"));
    if ((exAt != 0) || (exDf != 0) || (exMv != 0) || (exFly != 0) || (exPss != 0))
    {
      if (exAt != 0) l.f(sb, "untInf_ExAt", new Object[] { Integer.valueOf(exAt) });
      if (exDf != 0) l.f(sb, "untInf_ExDf", new Object[] { Integer.valueOf(exDf) });
      if (exMv != 0) l.f(sb, "untInf_ExMv", new Object[] { Integer.valueOf(exMv) });
      if (exFly > 0) sb.append(l.getS("untInf_ExFlyAdd"));
      if (exFly < 0) sb.append(l.getS("untInf_ExFlyLst"));
      if (exPss > 0) sb.append(l.getS("untInf_ExPssAdd"));
      if (exPss < 0) sb.append(l.getS("untInf_ExPssLst")); 
    }
    else
    {
      sb.append(l.getS("untInf_SttsNon"));
    }

    this.mAL.mMngDlg.chngTtl(sb.toString());
  }

  public void alwysWait()
  {
    this.mAL.mMngDlg.doAlwysCmn();
  }

  public void finish()
  {
    this.mAL = null;
    this.mGD = null;
    this.mSG = null;
    this.mGDUnt = null;
  }
}