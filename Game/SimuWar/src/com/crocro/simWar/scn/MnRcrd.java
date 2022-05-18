package com.crocro.simWar.scn;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V;
import com.crocro.simWar.app.V.GD;
import com.crocro.simWar.gm.GmDtUntF;
import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.bs.F;
import com.crocro.wrp.clckbl.DlgMnItm;
import com.crocro.wrp.clckbl.LghtBox;
import com.crocro.wrp.clckbl.LghtBox.ArrBtn;
import com.crocro.wrp.clckbl.StrBtn;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.mng.MngLayout;
import com.crocro.wrp.mng.MngLayout.Layout;
import com.crocro.wrp.mng.MngLng;
import com.crocro.wrp.mng.MngOpt;
import com.crocro.wrp.mng.MngScn;
import com.crocro.wrp.scn.MngDlg;
import com.crocro.wrp.utl.Rsrv;
import com.crocro.wrp.utl.UtlSort;
import com.crocro.wrp.utl.UtlTool;
import com.crocro.wrp.wrp.WrpDw.StrFntOpt;
import java.util.ArrayList;
import java.util.Comparator;

public class MnRcrd
  implements F
{
  private AppLoopSimWar mAL = null;
  private MngOpt mMO;
  public static final String RCRD_STG_PTH = "rcrdStg%s.dat";
  private int mRcrdSz;
  Rcrd4Sort[] mR4SArr;
  public static final RcrdComparator mRcrdCmprtr = new RcrdComparator();

  private int mFltrDfclt = -1;
  private int[] mClrDfcltArr;

  public MnRcrd(AppLoopSimWar al)
  {
    this.mAL = al;
  }

  public boolean chkFlwInit(int flw)
  {
    if (flw != V.FLW_MN_RCRD_INIT) return false;

    flwInit();
    this.mAL.mMS.setNxtFlw(V.FLW_MN_RCRD_WAIT);
    return true;
  }

  public boolean chkAlwysWait(int flw)
  {
    if (flw != V.FLW_MN_RCRD_WAIT) return false;

    alwysWait();
    return true;
  }

  public void flwInit()
  {
    this.mAL.mMngDlg.initDlg();

    int stgNo = this.mAL.egV.gd.stgNow;
    this.mRcrdSz = this.mAL.egV.gda.stgWinNo[(stgNo - 1)];
    int rcrdFltrSz = 0;

    MngOpt l = this.mAL.mMngLng;
    String pth = UtlTool.smplFormat("rcrdStg%s.dat", new Object[] { Integer.valueOf(stgNo) });
    this.mMO = new MngOpt(this.mAL, pth, true);

    this.mClrDfcltArr = new int[GmDtUntF.DFCLT.length];
    for (int i = 0; i < this.mRcrdSz; i++)
    {
      int dfclt = this.mMO.getI(i + ".WinDfclt") - 1;

      if ((dfclt >= 0) && (dfclt < GmDtUntF.DFCLT.length)) {
        this.mClrDfcltArr[dfclt] += 1;
      }
    }
    if (this.mFltrDfclt == -1)
      rcrdFltrSz = this.mRcrdSz;
    else {
      rcrdFltrSz = this.mClrDfcltArr[this.mFltrDfclt];
    }

    this.mR4SArr = new Rcrd4Sort[rcrdFltrSz];

    int pos = 0;
    for (int i = 0; i < this.mRcrdSz; i++)
    {
      int dfclt = this.mMO.getI(i + ".WinDfclt") - 1;
      if ((this.mFltrDfclt != -1) && 
        (dfclt != this.mFltrDfclt))
      {
        continue;
      }

      StringBuilder sb = new StringBuilder();

      l.f(sb, "scrLst0", new Object[] { UtlTool.nf(this.mMO.getI(i + ".WinScr")), this.mMO.getS(i + ".WinTm") });
      l.f(sb, "scrLst1", new Object[] { 
        UtlTool.nf(this.mMO.getI(i + ".WinEPerPnt")), 
        UtlTool.nf(this.mMO.getI(i + ".WinHpPerPnt")), 
        UtlTool.nf(
        this.mMO.getI(i + ".WinSK2Pnt") + this.mMO.getI(i + ".WinSK3Pnt") + 
        this.mMO.getI(i + ".WinSK4Pnt") + this.mMO.getI(i + ".WinSK5Pnt") + 
        this.mMO.getI(i + ".WinSK6Pnt")) });

      Rcrd4Sort r4s = new Rcrd4Sort(i, sb.toString(), 
        this.mMO.getI(i + ".WinScr"), this.mMO.getI(i + ".elpsTm"));
      this.mR4SArr[pos] = r4s;

      pos++;
    }

    UtlSort.sort(this.mR4SArr, mRcrdCmprtr);

    for (int i = 0; i < rcrdFltrSz; i++) {
      DlgMnItm mnItm = new DlgMnItm(this.mAL, this.mAL.mMngDlg, 
        -1, 
        i + 1 + this.mR4SArr[i].mStr, i)
      {
        public void initFntPrm(int w, int h)
        {
          this.mBtnHPddng = 12;
          if (this.mAL.mMC.getScrW() < 640)
          {
            this.mStrFntOpt = this.mAL.mML.mStrFntSml.clone();
          }
          super.initFntPrm(w, h);
        }

        public void dwAftrEf() {
          super.dwClckEf();
        }

        public void addActClck() {
          super.addActClck();

          this.mAL.mMngDlg.mClckEnbl = false;
          this.mAL.mRsrvLst.add(new Rsrv(this.mAL, 550L)
          {
            public void addAct() {
              LghtBox lghtBox = new LghtBox(this.mAL, 
                "", 
                this.mAL.mMngDlg);

              this.mAL.mMngDlg.addClckbl(9, lghtBox);

              MnRcrd.this.setRcrdTbl(lghtBox, this.mAL.mMngDlg.mMnSelNow);

              this.mAL.mMngDlg.mClckEnbl = true;
            }
          });
        }
      };
      this.mAL.mMngDlg.addLst(mnItm);
    }

    if (this.mRcrdSz == 0)
    {
      this.mAL.mMngDlg.setTtl(this.mAL.mMngLng.getS("scrVw_TtlNon"));
    }
    else {
      String ttlStr = UtlTool.smplFormat(this.mAL.mMngLng.getS("scrVw_Ttl"), new Object[] { Integer.valueOf(stgNo) }) + "\n";
      if (this.mFltrDfclt == -1)
        ttlStr = ttlStr + this.mAL.mMngLng.f("scrFltrDfclt_BtnAll", new Object[] { Integer.valueOf(this.mRcrdSz) });
      else {
        ttlStr = ttlStr + this.mAL.mMngLng.f("scrFltrDfclt_BtnDflt", new Object[] { Integer.valueOf(this.mFltrDfclt + 1), Integer.valueOf(this.mClrDfcltArr[this.mFltrDfclt]) });
      }
      this.mAL.mMngDlg.setTtl(ttlStr);
    }
    this.mAL.mMngDlg.setDrwrPreAlwys(this.mAL.mDfltBGDrwr);

    StrBtn btnCls = new StrBtn(this.mAL, -1, this.mAL.mML.l.dlg_btnH, "BACK")
    {
      public void addActClck() {
        this.mAL.mMngDlg.mClckEnbl = false;
        this.mAL.mRsrvLst.add(new Rsrv(this.mAL, 550L)
        {
          public void addAct() {
            mStatBtn = 0;
            this.mAL.mMngDlg.mClckEnbl = true;

            this.mAL.mMngDlg.finishDlg();
            this.mAL.mMS.setNxtFlw(V.FLW_WAIT);
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
    this.mAL.mMngDlg.addBtn(btnCls);

    StrBtn btnFltr = new StrBtn(this.mAL, -1, this.mAL.mML.l.dlg_btnH, "FILTER")
    {
      public void addActClck() {
        this.mAL.mMngDlg.mClckEnbl = false;
        this.mAL.mRsrvLst.add(new Rsrv(this.mAL, 550L)
        {
          public void addAct() {
            mStatBtn = 0;
            this.mAL.mMngDlg.mClckEnbl = true;

            LghtBox lghtBox = new LghtBox(
              this.mAL, 
              this.mAL.mMngLng.getS("scrFltrDfclt_Ttl"), 
              this.mAL.mMngDlg);

            LghtBox.ArrBtn[] arrBtns = new LghtBox.ArrBtn[GmDtUntF.DFCLT.length + 1];

            arrBtns[0] = 
              new LghtBox.ArrBtn(this.mAL.mMngLng.f("scrFltrDfclt_BtnAll", new Object[] { Integer.valueOf(mRcrdSz) }))
            {
              public boolean doClck() {
                MnRcrd.this.mFltrDfclt = -1;

                AppLoop al = MnRcrd.this.mAL;
                al.mMngDlg.finishDlg();
                al.mMS.setNxtFlw(V.FLW_MN_RCRD_INIT);

                return true;
              }
            };
            for (int i = 0; i < GmDtUntF.DFCLT.length; i++) {
              arrBtns[(i + 1)] = 
                new LghtBox.ArrBtn(this.mAL.mMngLng.f("scrFltrDfclt_BtnDflt", new Object[] { Integer.valueOf(i + 1), Integer.valueOf(mClrDfcltArr[i]) }))
              {
                public boolean doClck() {
                  MnRcrd.this.mFltrDfclt = this.mUnqId;

                  AppLoop al = MnRcrd.this.mAL;
                  al.mMngDlg.finishDlg();
                  al.mMS.setNxtFlw(V.FLW_MN_RCRD_INIT);

                  return true;
                }
              };
              arrBtns[(i + 1)].mUnqId = i;
            }

            lghtBox.useLstBtn(arrBtns);
            this.mAL.mMngDlg.addClckbl(9, lghtBox);
          }
        });
      }
    };
    this.mAL.mMngDlg.addBtn(btnFltr);

    this.mAL.mMngDlg.layoutAuto();
    this.mAL.mMngDlg.setStrtPgAuto();
    this.mAL.mMngDlg.unsetMnAll();
  }

  public void setRcrdTbl(LghtBox lghtBox, int selId)
  {
    int lstId = this.mR4SArr[selId].mSrcId;
    MngOpt l = this.mAL.mMngLng;

    String[][] strArr = new String[14][];
    int[] wRateArr = { 3, 7 };
    int[] hRateArr = (int[])null;
    int ln = 0;

    strArr[ln] = l.f("scr_WinTm", new Object[] { this.mMO.getS(lstId + ".WinTm") }).split(";"); ln++;

    strArr[ln] = l.f("scr_WinEne", new Object[] { this.mMO.getS(lstId + ".WinEne") }).split(";"); ln++;
    strArr[ln] = l.f("scr_WinEPer", new Object[] { 
      this.mMO.getS(lstId + ".WinEPerSec"), 
      this.mMO.getS(lstId + ".WinEPerRnk"), 
      UtlTool.nf(this.mMO.getI(lstId + ".WinEPerPnt")) }).split(";"); ln++;

    strArr[ln] = l.f("scr_WinSK1", new Object[] { this.mMO.getS(lstId + ".WinSK1Cnt"), UtlTool.nf(this.mMO.getI(lstId + ".WinSK1Pnt")) }).split(";");
    for (int i = 0; i < strArr[ln].length; i++) strArr[ln][i] = ("bgLght$" + strArr[ln][i]); ln++;
    strArr[ln] = l.f("scr_WinSK2", new Object[] { this.mMO.getS(lstId + ".WinSK2Cnt"), UtlTool.nf(this.mMO.getI(lstId + ".WinSK2Pnt")) }).split(";");
    for (int i = 0; i < strArr[ln].length; i++) strArr[ln][i] = ("bgLght$" + strArr[ln][i]); ln++;
    strArr[ln] = l.f("scr_WinSK3", new Object[] { this.mMO.getS(lstId + ".WinSK3Cnt"), UtlTool.nf(this.mMO.getI(lstId + ".WinSK3Pnt")) }).split(";");
    for (int i = 0; i < strArr[ln].length; i++) strArr[ln][i] = ("bgLght$" + strArr[ln][i]); ln++;
    strArr[ln] = l.f("scr_WinSK4", new Object[] { this.mMO.getS(lstId + ".WinSK4Cnt"), UtlTool.nf(this.mMO.getI(lstId + ".WinSK4Pnt")) }).split(";");
    for (int i = 0; i < strArr[ln].length; i++) strArr[ln][i] = ("bgLght$" + strArr[ln][i]); ln++;
    strArr[ln] = l.f("scr_WinSK5", new Object[] { this.mMO.getS(lstId + ".WinSK5Cnt"), UtlTool.nf(this.mMO.getI(lstId + ".WinSK5Pnt")) }).split(";");
    for (int i = 0; i < strArr[ln].length; i++) strArr[ln][i] = ("bgLght$" + strArr[ln][i]); ln++;
    strArr[ln] = l.f("scr_WinSKAvr", new Object[] { this.mMO.getS(lstId + ".WinSKAvr") }).split(";");
    for (int i = 0; i < strArr[ln].length; i++) strArr[ln][i] = ("bgLght$" + strArr[ln][i]); ln++;

    strArr[ln] = l.f("scr_WinHp", new Object[] { 
      this.mMO.getS(lstId + ".WinHpRst"), 
      this.mMO.getS(lstId + ".WinHpStrt") }).split(";"); ln++;
    strArr[ln] = l.f("scr_WinHpPer", new Object[] { 
      this.mMO.getS(lstId + ".WinHpPer"), 
      this.mMO.getS(lstId + ".WinHpPerRnk"), 
      UtlTool.nf(this.mMO.getI(lstId + ".WinHpPerPnt")) }).split(";"); ln++;

    strArr[ln] = l.f("scr_WinScr", new Object[] { UtlTool.nf(this.mMO.getI(lstId + ".WinScr")) }).split(";");
    for (int i = 0; i < strArr[ln].length; i++) strArr[ln][i] = ("bgHighLght$" + strArr[ln][i]);
    ln++;

    strArr[ln] = l.f("scr_WinDfclt", new Object[] { this.mMO.getS(lstId + ".WinDfclt") }).split(";"); ln++;

    strArr[ln] = l.f("scr_WinDate", new Object[] { 
      this.mMO.getS(lstId + ".WinYear"), 
      this.mMO.getS(lstId + ".WinMonth"), 
      this.mMO.getS(lstId + ".WinDay"), 
      this.mMO.getS(lstId + ".WinHour"), 
      this.mMO.getS(lstId + ".WinMinute"), 
      this.mMO.getS(lstId + ".WinSecond") }).split(";"); ln++;

    lghtBox.useDwTbl(strArr, wRateArr, hRateArr);
  }

  public void alwysWait()
  {
    this.mAL.mMngDlg.doAlwysCmn();
  }

  public void finish()
  {
    this.mAL = null;
    this.mMO = null;
    this.mR4SArr = null;
  }

  public static final class Rcrd4Sort
  {
    int mSrcId;
    String mStr;
    int mScr;
    int mTm;

    public Rcrd4Sort(int id, String str, int scr, int tm)
    {
      this.mSrcId = id;
      this.mScr = scr;
      this.mStr = str;
      this.mTm = tm;
    }
  }

  public static final class RcrdComparator implements Comparator<Object>
  {
    public int compare(Object o1_, Object o2_) {
      MnRcrd.Rcrd4Sort o1 = (MnRcrd.Rcrd4Sort)o1_;
      MnRcrd.Rcrd4Sort o2 = (MnRcrd.Rcrd4Sort)o2_;
      if (o2.mScr == o1.mScr) {
        return o1.mTm - o2.mTm;
      }
      return o2.mScr - o1.mScr;
    }
  }
}