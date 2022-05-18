package com.crocro.simWar.scn;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V;
import com.crocro.simWar.app.V.GD;
import com.crocro.simWar.clckbl.GmMapSelUnt;
import com.crocro.simWar.gm.GmDt;
import com.crocro.simWar.gm.GmImgUnt;
import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.bs.F;
import com.crocro.wrp.bs.SF;
import com.crocro.wrp.clckbl.StrBtn;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.mng.MngLayout;
import com.crocro.wrp.mng.MngLayout.Layout;
import com.crocro.wrp.mng.MngLng;
import com.crocro.wrp.mng.MngOpt;
import com.crocro.wrp.mng.MngScn;
import com.crocro.wrp.scn.MngDlg;
import com.crocro.wrp.utl.Actr;
import com.crocro.wrp.utl.Rsrv;
import com.crocro.wrp.utl.UtlDw;
import com.crocro.wrp.utl.UtlTool;
import com.crocro.wrp.wrp.WrpDw;
import com.crocro.wrp.wrp.WrpFile;
import java.util.ArrayList;

public class MnSelUnt
  implements F
{
  private AppLoopSimWar mAL = null;

  private static final int IMG_ID_MAP_VW = AppLoopSimWar.IMG_ID_CMN_MAX + SF.seq();
  private static final int MAP_PDNG_H = 16;
  private int mStgNow;
  private int[][] mLdStgDt;
  private int mMyUntMax;
  private int[] mUntTypArr;
  private GmImgUnt mGmImgUnt;
  private int[] mCanUseRef;
  public GmMapSelUnt mGmMapSelUnt;
  public Actr mActrNxt;
  public Actr mActrBck;

  public MnSelUnt(AppLoopSimWar al)
  {
    this.mAL = al;
  }

  public boolean chkFlwInit(int flw)
  {
    if (flw != V.FLW_MN_SEL_UNT_INIT) return false;

    flwInit();
    this.mAL.mMS.setNxtFlw(V.FLW_MN_SEL_UNT_WAIT);
    return true;
  }

  public boolean chkAlwysWait(int flw)
  {
    if (flw != V.FLW_MN_SEL_UNT_WAIT) return false;

    alwysWait();
    return true;
  }

  public void flwInit()
  {
    this.mAL.mMngDlg.initDlg();

    this.mAL.mMngDlg.setTtl(this.mAL.mMngLng.getS("mnSelUnt_Ttl"));
    this.mAL.mMngDlg.setDrwrPreAlwys(this.mAL.mDfltBGDrwr);

    this.mStgNow = this.mAL.egV.gd.stgNow;
    String ldStr = UtlTool.bToS(this.mAL.mWF.getR(UtlTool.smplFormat("res/dat/stgDat/stg%d.dat", new Object[] { Integer.valueOf(this.mStgNow) })));
    this.mLdStgDt = UtlTool.sToI2dAr(ldStr);
    this.mUntTypArr = new int[this.mLdStgDt.length];

    this.mGmImgUnt = new GmImgUnt(this.mAL);

    for (int i = 0; i < this.mLdStgDt.length; i++)
    {
      int[] dt = this.mLdStgDt[i];
      if ((dt == null) || (dt.length < 4) || 
        (dt[0] >= 32)) {
        continue;
      }
      this.mMyUntMax = (i + 1);

      this.mUntTypArr[i] = dt[1];
      this.mGmImgUnt.ldImg(this.mUntTypArr[i]);
    }

    if (this.mAL.mBsOpt.getS("stgRef" + this.mStgNow).length() != 0)
    {
      int stgRef = this.mAL.mBsOpt.getI("stgRef" + this.mStgNow);

      this.mAL.mWD.mkBuf(IMG_ID_MAP_VW, UtlTool.smplFormat("map/map%d_bs.jpg", new Object[] { Integer.valueOf(stgRef) }));

      String fncStr = this.mAL.mBsOpt.getS("stgPrc" + this.mStgNow);
      UtlDw.prcBufFromS(this.mAL.mWD, IMG_ID_MAP_VW, fncStr);
    }
    else {
      this.mAL.mWD.mkBuf(IMG_ID_MAP_VW, UtlTool.smplFormat("map/map%d_bs.jpg", new Object[] { Integer.valueOf(this.mStgNow) }));
    }

    GmDt gd = this.mAL.egV.mGD;
    boolean[] canUseTyp = new boolean[gd.mUntI.length];

    int stgSz = this.mAL.mBsOpt.getI("stgSz");
    for (int i = 0; i < stgSz; i++)
    {
      int stgNo = i + 1;
      boolean flgClr = this.mAL.egV.gda.stgWinNo[(stgNo - 1)] >= 1;
      if (!flgClr) {
        continue;
      }
      ldStr = UtlTool.bToS(this.mAL.mWF.getR(UtlTool.smplFormat("res/dat/stgDat/stg%d.dat", new Object[] { Integer.valueOf(stgNo) })));
      int[][] tmpLdStgDt = UtlTool.sToI2dAr(ldStr);
      for (int j = 0; j < tmpLdStgDt.length; j++)
      {
        int[] dt = tmpLdStgDt[j];
        if ((dt == null) || (dt.length < 4) || 
          (dt[0] >= 32)) {
          continue;
        }
        int typ = dt[1];
        if ((typ >= 0) && (canUseTyp.length > typ)) {
          canUseTyp[typ] = true;
          this.mGmImgUnt.ldImg(typ);
        }
      }
    }

    int canUseSz = 0;
    for (int i = 0; i < canUseTyp.length; i++) {
      if (canUseTyp[i] ) continue; canUseSz++;
    }

    this.mCanUseRef = new int[canUseSz];
    int pos = 0;
    for (int i = 0; i < canUseTyp.length; i++) {
      if (canUseTyp[i] ) {
        this.mCanUseRef[pos] = i;

        pos++;
      }

    }

    int btnW = (this.mAL.mMC.getScrW() - this.mAL.mML.l.dlg_mrgnOut * 2 - this.mAL.mML.l.dlg_mrgnIn) / 2;

    StrBtn btnNxt = new StrBtn(this.mAL, btnW, this.mAL.mML.l.dlg_btnH, "NEXT")
    {
      public void addActClck() {
        this.mAL.mMngDlg.mClckEnbl = false;
        this.mAL.mRsrvLst.add(new Rsrv(this.mAL, 550L)
        {
          public void addAct() {
            this.mAL.mMngDlg.mClckEnbl = true;
           mStatBtn = 0;

            ((V)this.mAL.mV).mSelUntTypArr = MnSelUnt.this.mUntTypArr;

            MnSelUnt.this.mActrNxt.act();
          }
        });
      }
    };
    this.mAL.mMngDlg.addBtn(btnNxt);

    StrBtn btnBck = new StrBtn(this.mAL, btnW, this.mAL.mML.l.dlg_btnH, "BACK")
    {
      public void addActClck() {
        this.mAL.mMngDlg.mClckEnbl = false;
        this.mAL.mRsrvLst.add(new Rsrv(this.mAL, 550L)
        {
          public void addAct() {
            this.mAL.mMngDlg.mClckEnbl = true;
            mStatBtn = 0;

            MnSelUnt.this.mActrBck.act();
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

    MngDlg dlg = this.mAL.mMngDlg;
    this.mGmMapSelUnt = 
      new GmMapSelUnt(this.mAL, this.mAL.mMngDlg, 
      dlg.mMnMrgnX, 
      dlg.mMnMrgnTop + 16, 
      this.mAL.mMC.getScrW() - dlg.mMnMrgnX * 2, 
      this.mAL.mMC.getScrH() - dlg.mMnMrgnTop - dlg.mMnMrgnBtm - 32, 
      IMG_ID_MAP_VW);

    this.mGmMapSelUnt.mGmImgUnt = this.mGmImgUnt;
    this.mGmMapSelUnt.mLdStgDt = this.mLdStgDt;
    this.mGmMapSelUnt.mMyUntMax = this.mMyUntMax;
    this.mGmMapSelUnt.mUntTypArr = this.mUntTypArr;
    this.mGmMapSelUnt.mCanUseRef = this.mCanUseRef;
    this.mGmMapSelUnt.init();

    this.mAL.mMngDlg.addClckbl(7, this.mGmMapSelUnt);
  }

  public void doSelMn(int unqId)
  {
  }

  public void alwysWait()
  {
    this.mAL.mMngDlg.doAlwysCmn();
  }

  public void finish()
  {
    this.mAL = null;

    if (this.mGmImgUnt != null) { this.mGmImgUnt.finish(); this.mGmImgUnt = null;
    }
    this.mActrNxt = null;
    this.mActrBck = null;

    this.mLdStgDt = null;
    this.mUntTypArr = null;
  }
}