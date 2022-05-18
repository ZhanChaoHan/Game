package com.crocro.simWar.scn;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V.GD;
import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.bs.F;
import com.crocro.wrp.clckbl.DlgMnChk;
import com.crocro.wrp.clckbl.StrBtn;
import com.crocro.wrp.mng.MngLayout;
import com.crocro.wrp.mng.MngLayout.Layout;
import com.crocro.wrp.mng.MngLng;
import com.crocro.wrp.mng.MngScn;
import com.crocro.wrp.scn.MngDlg;
import com.crocro.wrp.utl.Rsrv;
import java.util.ArrayList;

public class MnVwMes
  implements F
{
  private AppLoopSimWar mAL = null;

  public MnVwMes(AppLoopSimWar al)
  {
    this.mAL = al;
  }

  public boolean chkFlwInit(int flw)
  {
    if (flw != com.crocro.simWar.app.V.FLW_MN_VW_MES_INIT) return false;

    flwInit();
    this.mAL.mMS.setNxtFlw(com.crocro.simWar.app.V.FLW_MN_VW_MES_WAIT);
    return true;
  }

  public boolean chkAlwysWait(int flw)
  {
    if (flw != com.crocro.simWar.app.V.FLW_MN_VW_MES_WAIT) return false;

    alwysWait();
    return true;
  }

  public void flwInit()
  {
    this.mAL.mMngDlg.initDlg();

    this.mAL.mMngDlg.setTtl(this.mAL.mMngLng.getS("mnVwMes_Ttl"));
    this.mAL.mMngDlg.setDrwrPreAlwys(this.mAL.mDfltBGDrwr);

    DlgMnChk mnItm = new DlgMnChk(
      this.mAL, this.mAL.mMngDlg, this.mAL.mML.l.btnHDflt * 2, this.mAL.mMngLng.getS("mnVwMes_Btn"), 0, 
      this.mAL.egV.gd.vwActLog) {
      public void sel() {
      }

      public void addActClck() {
        super.addActClck();

        ((com.crocro.simWar.app.V)this.mAL.mV).gd.vwActLog = this.mChk;
        this.mAL.mV.svDt(0);
      }
    };
    this.mAL.mMngDlg.addLst(mnItm);
    this.mAL.mMngDlg.setMnSel(0);

    StrBtn btnBck = new StrBtn(this.mAL, 100, this.mAL.mML.l.dlg_btnH, "BACK")
    {
      public void addActClck() {
        this.mAL.mMngDlg.mClckEnbl = false;
        this.mAL.mRsrvLst.add(new Rsrv(this.mAL, 550L) {
          public void addAct() {
            this.mAL.mMngDlg.mClckEnbl = true;
            this.mAL.mMngDlg.finishDlg();
            mStatBtn = 0;

            this.mAL.mMS.setNxtFlw(com.crocro.simWar.app.V.FLW_MN_OPT_INIT);
          }
        });
      }

      public boolean doBck() {
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

  public void alwysWait()
  {
    this.mAL.mMngDlg.doAlwysCmn();
  }

  public void finish()
  {
    this.mAL = null;
  }
}