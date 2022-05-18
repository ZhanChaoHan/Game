package com.crocro.simWar.scn;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V;
import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.bs.F;
import com.crocro.wrp.bs.SF;
import com.crocro.wrp.clckbl.DlgMnItm;
import com.crocro.wrp.clckbl.LghtBox;
import com.crocro.wrp.clckbl.StrBtn;
import com.crocro.wrp.mng.MngLayout;
import com.crocro.wrp.mng.MngLayout.Layout;
import com.crocro.wrp.mng.MngLng;
import com.crocro.wrp.mng.MngScn;
import com.crocro.wrp.scn.MngDlg;
import com.crocro.wrp.utl.Actr;
import com.crocro.wrp.utl.Rsrv;
import com.crocro.wrp.wrp.WrpEnv;
import com.crocro.wrp.wrp.WrpNet;
import java.util.ArrayList;

public class MnOpt
  implements F
{
  private AppLoopSimWar mAL = null;
  private ScnGm mSG = null;
  private String mMnTtlBs;
  public static final int OPT_SV = SF.seq(0);
  public static final int OPT_BCK_TTL = SF.seq();
  public static final int OPT_SND_VOL = SF.seq();
  public static final int OPT_DFCLT = SF.seq();
  public static final int OPT_VW_MES = SF.seq();
  public static final int OPT_BLG = SF.seq();
  public static final int OPT_DEV = SF.seq();
  public static final int OPT_EXIT = SF.seq();
  public static final int OPT_MAX = SF.seq();

  private int mBckFlw = -1;

  private MnSndVol mMnSndVol = null;
  private MnDfclt mMnDfclt = null;
  private MnVwMes mMnVwMes = null;

  public MnOpt(AppLoopSimWar al, ScnGm sg)
  {
    this.mAL = al;
    this.mSG = sg;

    this.mMnSndVol = new MnSndVol(this.mAL);
    this.mMnDfclt = new MnDfclt(this.mAL);
    this.mMnVwMes = new MnVwMes(this.mAL);
  }

  public boolean chkFlwInit(int flw)
  {
    if (this.mMnSndVol.chkFlwInit(flw)) return true;
    if (this.mMnDfclt.chkFlwInit(flw)) return true;
    if (this.mMnVwMes.chkFlwInit(flw)) return true;
    if (flw != V.FLW_MN_OPT_INIT) return false;

    flwInit();
    this.mAL.mMS.setNxtFlw(V.FLW_MN_OPT_WAIT);
    return true;
  }

  public boolean chkAlwysWait(int flw)
  {
    if (this.mMnSndVol.chkAlwysWait(flw)) return true;
    if (this.mMnDfclt.chkAlwysWait(flw)) return true;
    if (this.mMnVwMes.chkAlwysWait(flw)) return true;
    if (flw != V.FLW_MN_OPT_WAIT) return false;

    alwysWait();
    return true;
  }

  public void flwInit()
  {
    if (this.mBckFlw == -1) this.mBckFlw = this.mAL.mMS.getFlw(-1);

    this.mAL.mMngDlg.initDlg();

    this.mMnTtlBs = this.mAL.mMngLng.getS("mnOpt_Ttl");
    this.mAL.mMngDlg.setTtl(this.mMnTtlBs);
    this.mAL.mMngDlg.setDrwrPreAlwys(this.mAL.mDfltBGDrwr);

    for (int i = 0; i < OPT_MAX; i++)
    {
      if ((this.mAL.mMS.getScn(0) != V.SCN_GM) && 
        (this.mAL.mMngLng.getI("mnOpt" + i + "_OnlyScnGm") == 1))
      {
        continue;
      }
      if ((this.mAL.mMS.getScn(0) == V.SCN_GM) && 
        (this.mAL.mMngLng.getI("mnOpt" + i + "_AvoidScnGm") == 1))
      {
        continue;
      }

      DlgMnItm mnItm = new DlgMnItm(
        this.mAL, this.mAL.mMngDlg, this.mAL.mML.l.btnHDflt * 3 / 2, this.mAL.mMngLng.getS("mnOpt" + i + "_Nm"), i) {
        public void sel() {
        }

        public void dwAftrEf() {
          super.dwClckEf();
        }

        public void addActClck() {
          this.mAL.mMngDlg.mClckEnbl = false;
          this.mAL.mRsrvLst.add(new Rsrv(this.mAL, 550L)
          {
            public void addAct() {
              this.mAL.mMngDlg.mClckEnbl = true;
              mStatBtn = 0;
              MnOpt.this.doSelMn(mUnqId);
            }
          });
        }
      };
      this.mAL.mMngDlg.addLst(mnItm);
    }
    this.mAL.mMngDlg.setMnSel(0);

    StrBtn btnBck = new StrBtn(this.mAL, -1, this.mAL.mML.l.dlg_btnH, "BACK")
    {
      public void addActClck() {
        this.mAL.mMngDlg.mClckEnbl = false;
        this.mAL.mRsrvLst.add(new Rsrv(this.mAL, 550L) {
          public void addAct() {
            this.mAL.mMngDlg.mClckEnbl = true;
            this.mAL.mMngDlg.finishDlg();
           mStatBtn = 0;

            this.mAL.mMS.setNxtFlw(MnOpt.this.mBckFlw);

            if (MnOpt.this.mSG != null) MnOpt.this.mSG.unescTmpOpt(); 
          }
        });
      }

      public boolean doBck()
      {
        this.mClckX = (this.mX + this.mW / 2);
        this.mClckY = (this.mY + this.mH / 2);
        actClck();

        return true;
      }

      public boolean doMn() {
        addActClck();
        return true;
      }
    };
    this.mAL.mMngDlg.addBtn(btnBck);

    this.mAL.mMngDlg.layoutAuto();
    this.mAL.mMngDlg.setStrtPgAuto();
  }

  public void doSelMn(int unqId)
  {
    if (unqId == OPT_SV)
    {
      this.mSG.svGmDat();

      LghtBox lghtBox = new LghtBox(this.mAL, 
        this.mAL.mMngLng.getS("mnOpt_SvEnd"), 
        this.mAL.mMngDlg, 
        new Actr() {
        public void act() {
          MnOpt.this.mAL.mMS.setNxtFlw(V.FLW_WAIT);
          MnOpt.this.mAL.mMngDlg.finishDlg();
          MnOpt.this.mSG.unescTmpOpt();
        }
      });
      this.mAL.mMngDlg.addClckbl(9, lghtBox);
    }
    else if (unqId == OPT_BCK_TTL)
    {
      if (this.mAL.mMS.getScn(0) == V.SCN_GM)
      {
        LghtBox lghtBox = new LghtBox(this.mAL, 
          this.mAL.mMngLng.getS("mnOpt_ScnGm_BckTtl"), 
          this.mAL.mMngDlg, 
          new Actr() {
          public void act(Object[] obj) { Boolean bool = (Boolean)obj[0];

            if (bool.booleanValue()) {
              MnOpt.this.mAL.mMS.setNxtScn(V.SCN_TTL_GM);
              MnOpt.this.mAL.mMngDlg.finishDlg();
            } else {
              MnOpt.this.mAL.mMS.setNxtFlw(MnOpt.this.mBckFlw);
              if (MnOpt.this.mSG != null) MnOpt.this.mSG.unescTmpOpt();
            }
          }
        });
        lghtBox.mBtnTyp = 2;
        this.mAL.mMngDlg.addClckbl(9, lghtBox);
      }
      else {
        this.mAL.mMS.setNxtScn(V.SCN_TTL_GM);
        this.mAL.mMngDlg.finishDlg();
      }
    }
    else if (unqId == OPT_SND_VOL)
    {
      this.mAL.mMngDlg.finishDlg();
      this.mAL.mMS.setNxtFlw(V.FLW_MN_SND_VOL_INIT);
    }
    else if (unqId == OPT_DFCLT)
    {
      this.mAL.mMngDlg.finishDlg();
      this.mAL.mMS.setNxtFlw(V.FLW_MN_DFCLT_INIT);
    }
    else if (unqId == OPT_VW_MES)
    {
      this.mAL.mMngDlg.finishDlg();
      this.mAL.mMS.setNxtFlw(V.FLW_MN_VW_MES_INIT);
    }
    else if (unqId == OPT_BLG)
    {
      this.mAL.mWN.openBrwsr(this.mAL.mMngLng.getS("mnOpt" + unqId + "_Lnk"));
    }
    else if (unqId == OPT_DEV)
    {
      this.mAL.mWN.openBrwsr(this.mAL.mMngLng.getS("mnOpt" + unqId + "_Lnk"));
    }
    else if (unqId == OPT_EXIT)
    {
      this.mAL.mWE.usrExit();
    }
  }

  public void alwysWait()
  {
    this.mAL.mMngDlg.doAlwysCmn();
  }

  public void finish()
  {
    this.mAL = null;
    this.mSG = null;
    this.mMnTtlBs = null;

    this.mMnSndVol.finish(); this.mMnSndVol = null;
    this.mMnDfclt.finish(); this.mMnDfclt = null;
    this.mMnVwMes.finish(); this.mMnVwMes = null;
  }
}