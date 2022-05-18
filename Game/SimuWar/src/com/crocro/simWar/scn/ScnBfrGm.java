package com.crocro.simWar.scn;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V;
import com.crocro.simWar.app.V.GD;
import com.crocro.simWar.clckbl.TabOpt;
import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.clckbl.DrwScrpt;
import com.crocro.wrp.clckbl.StrBtn;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.mng.MngLayout;
import com.crocro.wrp.mng.MngLayout.Layout;
import com.crocro.wrp.mng.MngOpt;
import com.crocro.wrp.mng.MngScn;
import com.crocro.wrp.scn.MngDlg;
import com.crocro.wrp.scn.Scn;
import com.crocro.wrp.utl.Actr;
import com.crocro.wrp.utl.Drwr;
import com.crocro.wrp.utl.Rsrv;
import com.crocro.wrp.utl.UtlTool;
import com.crocro.wrp.wrp.WrpFile;
import java.util.ArrayList;

public class ScnBfrGm extends Scn
{
  public AppLoopSimWar mAL = null;
  public static final int SCRPT_H = 240;
  private DrwScrpt mDrwScrpt;
  protected static final String SCRPT_PTH = "res/dat/stgScrpt/stg%s_bfr.txt";
  private MnOpt mMnOpt = null;
  private MnDfclt mMnDfclt = null;
  private MnSelUnt mMnSelUnt = null;

  private boolean mIsScrpt = true;
  private boolean mUseMnDfclt = false;
  private boolean mUseMnSelUnt = false;

  private Actr mActrNxt = new Actr() {
    public void act() { ScnBfrGm.this.swtchScn(-1, 0);
    }
  };

  private Actr mActrDfcltNxt = new Actr() {
    public void act() { ScnBfrGm.this.swtchScn(V.FLW_MN_DFCLT_WAIT, 0);
    }
  };

  private Actr mActrDfcltBck = new Actr() {
    public void act() { ScnBfrGm.this.swtchScn(V.FLW_MN_DFCLT_WAIT, 1);
    }
  };

  private Actr mActrSelUntNxt = new Actr() {
    public void act() { ScnBfrGm.this.swtchScn(V.FLW_MN_SEL_UNT_WAIT, 0);
    }
  };

  private Actr mActrSelUntBck = new Actr() {
    public void act() { ScnBfrGm.this.swtchScn(V.FLW_MN_SEL_UNT_WAIT, 1);
    }
  };
  private static final int DIR_NXT = 0;
  private static final int DIR_BCK = 1;

  private void swtchScn(int flwMn, int dir)
  {
    if (flwMn == -1)
    {
      if (dir == 0)
      {
        if (this.mUseMnDfclt) {
          this.mAL.mMS.setNxtFlw(V.FLW_MN_DFCLT_BFR_GM_INIT);
        }
        else if (this.mUseMnSelUnt)
          this.mAL.mMS.setNxtFlw(V.FLW_MN_SEL_UNT_INIT);
        else {
          strtGm();
        }
      }
    }
    else if (flwMn == V.FLW_MN_DFCLT_WAIT)
    {
      if (dir == 0)
      {
        if (this.mUseMnSelUnt) {
          this.mAL.mMS.setNxtFlw(V.FLW_MN_SEL_UNT_INIT);
        } else {
          this.mAL.mMngDlg.finishDlg();
          strtGm();
        }
      }
      else if (dir == 1)
      {
        if (this.mIsScrpt)
        {
          bckToScrpt();
        }
        else addDoBck();
      }

    }
    else if (flwMn == V.FLW_MN_SEL_UNT_WAIT)
    {
      if (dir == 0)
      {
        this.mAL.mMngDlg.finishDlg();
        strtGm();
      }
      else if (dir == 1)
      {
        if (this.mUseMnDfclt) {
          this.mAL.mMS.setNxtFlw(V.FLW_MN_DFCLT_BFR_GM_INIT);
        }
        else if (this.mIsScrpt)
        {
          bckToScrpt();
        }
        else addDoBck();
      }
    }
  }

  private void strtGm()
  {
    this.mAL.egV.gd.stgSv = 0;
    this.mAL.egV.lda.untDat = null;
    this.mAL.egV.lda.untStts = null;

    this.mAL.mMS.setNxtScn(V.SCN_STG_STRT);
  }

  private void bckToScrpt()
  {
    this.mDrwScrpt.bckStrtScrpt();
    this.mAL.mMngDlg.finishDlg();
    this.mAL.mMS.setNxtFlw(V.FLW_WAIT);
  }

  public ScnBfrGm(AppLoopSimWar al)
  {
    super(al);
  }

  public void init()
  {
    this.mAL = ((AppLoopSimWar)this.mmAL);
    super.init();

    if (this.mMnOpt == null) this.mMnOpt = new MnOpt(this.mAL, null);
    if (this.mMnDfclt == null)
    {
      this.mMnDfclt = new MnDfclt(this.mAL)
      {
      };
    }

    if (this.mMnSelUnt == null) {
      this.mMnSelUnt = new MnSelUnt(this.mAL)
      {
      };
    }

    int stgStrtVwDfclt = this.mAL.mBsOpt.getI("stgStrtVwDfclt");
    if ((stgStrtVwDfclt > 0) && (stgStrtVwDfclt < this.mAL.egV.mStgSz))
    {
      if (this.mAL.egV.gda.stgEnbl[(stgStrtVwDfclt - 1)] == 1) {
        this.mUseMnDfclt = true;
      }

    }

    int stgNo = this.mAL.egV.gd.stgNow;
    int winNo = this.mAL.egV.gda.stgWinNo[(stgNo - 1)];
    if (winNo >= 1) {
      this.mUseMnSelUnt = true;
    }

    ((V)this.mAL.mV).mSelUntTypArr = null;
  }

  public void doFlw(int flw)
  {
    if (flw == V.FLW_INIT) {
      flwInit();
    }
    else if ((!this.mMnOpt.chkFlwInit(flw)) && 
      (!this.mMnDfclt.chkFlwInit(flw)))
      this.mMnSelUnt.chkFlwInit(flw);
  }

  public void doAlwys(int flw)
  {
    if (flw == V.FLW_WAIT) {
      alwysWait();
    }
    else if ((!this.mMnOpt.chkAlwysWait(flw)) && 
      (!this.mMnDfclt.chkAlwysWait(flw)))
      this.mMnSelUnt.chkAlwysWait(flw);
  }

  public void preAlwys()
  {
    this.mAL.mDfltBGDrwr.dw();
  }

  public void flwInit()
  {
    String scrptStr = loadScrpt();
    if (scrptStr.length() == 0)
    {
      this.mIsScrpt = false;
      doNoScrpt();
      return;
    }

    int btnY = this.mAL.mMC.getScrH() - this.mAL.mML.l.btnHBold - this.mAL.mML.l.dlg_mrgnOut;
    int btnW = (this.mAL.mMC.getScrW() - this.mAL.mML.l.dlg_mrgnOut * 2 - this.mAL.mML.l.dlg_mrgnIn) / 2;
    int btnX = this.mAL.mMC.getScrW() - (btnW * 2 + this.mAL.mML.l.dlg_mrgnIn + this.mAL.mML.l.dlg_mrgnOut);

    this.mDrwScrpt = 
      new DrwScrpt(this.mAL, 
      0, 
      (btnY - 240) / 2, 
      this.mAL.mMC.getScrW(), 
      240, 
      scrptStr)
    {
      public void addActClck() {
        ScnBfrGm.this.mClckEnbl = false;
        this.mAL.mRsrvLst.add(new Rsrv(this.mAL, 550L) {
          public void addAct() {
            boolean resPrg = prgScrpt();
            if (resPrg) ScnBfrGm.this.mvNxtScn();
            ScnBfrGm.this.mClckEnbl = true;
            mStatBtn = 0;
          }
        });
      }
    };
    addClckbl(0, this.mDrwScrpt);

    StrBtn btnSkip = new StrBtn(this.mAL, btnX, btnY, btnW, this.mAL.mML.l.btnHBold, "SKIP") {
      public void addActClck() {
        ScnBfrGm.this.mClckEnbl = false;
        this.mAL.mRsrvLst.add(new Rsrv(this.mAL, 550L) {
          public void addAct() {
            ScnBfrGm.this.mvNxtScn();
            ScnBfrGm.this.mClckEnbl = true;
            mStatBtn = 0;
          }
        });
      }
    };
    addClckbl(0, btnSkip);

    btnX += btnW + this.mAL.mML.l.dlg_mrgnIn;
    StrBtn btnNxt = new StrBtn(this.mAL, btnX, btnY, btnW, this.mAL.mML.l.btnHBold, "NEXT") {
      public void addActClck() {
        ScnBfrGm.this.mClckEnbl = false;
        this.mAL.mRsrvLst.add(new Rsrv(this.mAL, 550L) {
          public void addAct() {
            boolean resPrg = ScnBfrGm.this.mDrwScrpt.prgScrpt();
            if (resPrg) {
              ScnBfrGm.this.mvNxtScn();
            }
            ScnBfrGm.this.mClckEnbl = true;
            mStatBtn = 0;
          }
        });
      }
    };
    addClckbl(0, btnNxt);

    addClckbl(8, new TabOpt(this.mAL));

    this.mAL.mMS.setNxtFlw(V.FLW_WAIT);
  }

  public void alwysWait()
  {
    doAlwysCmn();
  }

  public void doNoScrpt()
  {
    swtchScn(-1, 0);
  }

  public boolean chkScn(int scn)
  {
    return scn != V.SCN_BFR_GM;
  }

  public String loadScrpt()
  {
    int stgNow = this.mAL.egV.gd.stgNow;
    String scrpt = UtlTool.bToS(this.mAL.mWF.getR(UtlTool.smplFormat("res/dat/stgScrpt/stg%s_bfr.txt", new Object[] { Integer.valueOf(stgNow) })));
    return scrpt;
  }

  public void mvNxtScn()
  {
    this.mActrNxt.act();
  }

  public boolean addDoBck()
  {
    this.mAL.mMS.setNxtScn(V.SCN_SEL_STG);

    return true;
  }

  public void finish()
  {
    super.finish();
    this.mDrwScrpt = null;

    this.mMnOpt.finish(); this.mMnOpt = null;
    this.mMnDfclt.finish(); this.mMnDfclt = null;
    this.mMnSelUnt.finish(); this.mMnSelUnt = null;

    this.mActrNxt = null;
  }
}