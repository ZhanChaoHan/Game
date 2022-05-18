package com.crocro.simWar.scn;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V.GD;
import com.crocro.simWar.gm.GmDtUntF;
import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.bs.F;
import com.crocro.wrp.bs.SF;
import com.crocro.wrp.clckbl.DlgMnSldBar;
import com.crocro.wrp.clckbl.Drw;
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
import com.crocro.wrp.wrp.WrpDw.StrFntOpt;
import java.util.ArrayList;

public class MnDfclt
  implements F
{
  private AppLoopSimWar mAL = null;

  private static final int IMG_ID_LV = AppLoopSimWar.IMG_ID_CMN_MAX + SF.seq(0);
  private static final String IMG_PTH_LV = "cmn/icn_star.png";
  private DlgMnSldBar mMnItm;
  private int mRcrdSz;
  private int[] mClrDfcltArr;
  protected int mChkFlwInit = com.crocro.simWar.app.V.FLW_MN_DFCLT_INIT;
  protected int mChkFlwWait = com.crocro.simWar.app.V.FLW_MN_DFCLT_WAIT;

  public Actr mActrNxt = null;
  public Actr mActrBck = new Actr()
  {
    public void act() {
      MnDfclt.this.mAL.mMS.setNxtFlw(com.crocro.simWar.app.V.FLW_MN_OPT_INIT);
    }
  };

  public boolean mVwStgClrStts = false;

  public MnDfclt(AppLoopSimWar al)
  {
    this.mAL = al;
  }

  public boolean chkFlwInit(int flw)
  {
    if (flw != this.mChkFlwInit) return false;

    flwInit();
    this.mAL.mMS.setNxtFlw(this.mChkFlwWait);
    return true;
  }

  public boolean chkAlwysWait(int flw)
  {
    if (flw != this.mChkFlwWait) return false;

    alwysWait();
    return true;
  }

  public void flwInit()
  {
    if (this.mVwStgClrStts)
    {
      int stgNo = this.mAL.egV.gd.stgNow;
      this.mRcrdSz = this.mAL.egV.gda.stgWinNo[(stgNo - 1)];

      String pth = UtlTool.smplFormat("rcrdStg%s.dat", new Object[] { Integer.valueOf(stgNo) });
      MngOpt mo = new MngOpt(this.mAL, pth, true);

      this.mClrDfcltArr = new int[GmDtUntF.DFCLT.length];
      for (int i = 0; i < this.mRcrdSz; i++)
      {
        int dfclt = mo.getI(i + ".WinDfclt") - 1;

        if ((dfclt >= 0) && (dfclt < GmDtUntF.DFCLT.length)) {
          this.mClrDfcltArr[dfclt] += 1;
        }

      }

    }

    this.mAL.mMngDlg.initDlg();

    this.mAL.mMngDlg.setTtl(this.mAL.mMngLng.getS("mnDfclt_Ttl"));
    this.mAL.mMngDlg.setDrwrPreAlwys(this.mAL.mDfltBGDrwr);

    this.mMnItm = 
      new DlgMnSldBar(this.mAL, this.mAL.mMngDlg, this.mAL.mML.l.btnHDflt * 2, this.mAL.mMngLng.getS("mnDfclt_Btn"), 0, 
      1, GmDtUntF.DFCLT.length, this.mAL.egV.gd.dfclt + 1)
    {
      public void sel() {
      }

      public void addActDrp() {
        ((com.crocro.simWar.app.V)this.mAL.mV).gd.dfclt = (this.mSldNow - 1);
        this.mAL.mV.svDt(0);
      }

      public void doAlwys() {
        super.doAlwys();
        this.mSldTtlTail = UtlTool.smplFormat(
          this.mAL.mMngLng.getS("mnDfclt_BtnTail"), new Object[] { 
          Double.valueOf(GmDtUntF.DFCLT[(this.mSldNow - 1)][0]), 
          Double.valueOf(GmDtUntF.DFCLT[(this.mSldNow - 1)][1]) });
      }
    };
    this.mAL.mMngDlg.addLst(this.mMnItm);
    this.mAL.mMngDlg.setMnSel(0);

    int btnW = (this.mAL.mMC.getScrW() - this.mAL.mML.l.dlg_mrgnOut * 2 - this.mAL.mML.l.dlg_mrgnIn) / 2;

    if (this.mActrNxt != null)
    {
      StrBtn btnNxt = new StrBtn(this.mAL, btnW, this.mAL.mML.l.dlg_btnH, "NEXT")
      {
        public void addActClck() {
          this.mAL.mMngDlg.mClckEnbl = false;
          this.mAL.mRsrvLst.add(new Rsrv(this.mAL, 550L)
          {
        	   public void addAct()
               {
                   mAL.mMngDlg.mClckEnbl = true;
                   mStatBtn = 0;
                   mActrNxt.act();
               }
          });
        }
      };
      this.mAL.mMngDlg.addBtn(btnNxt);
    }

    StrBtn btnBck = new StrBtn(this.mAL, btnW, this.mAL.mML.l.dlg_btnH, "BACK")
    {
      public void addActClck() {
        this.mAL.mMngDlg.mClckEnbl = false;
        this.mAL.mRsrvLst.add(new Rsrv(this.mAL, 550L)
        {
          public void addAct() {
            this.mAL.mMngDlg.mClckEnbl = true;
           mStatBtn = 0;

            mActrBck.act();
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

    this.mAL.mWD.mkBuf(IMG_ID_LV, "cmn/icn_star.png");

    int x = this.mMnItm.mX;
    int w = this.mMnItm.mW;
    int y = this.mMnItm.mY + this.mMnItm.mH;
    int h = this.mAL.mMC.getScrH() - (this.mMnItm.mY + this.mMnItm.mH) - (this.mAL.mMC.getScrH() - btnBck.mY);

    Drw drw = new Drw(this.mAL, x, y, w, h, 0)
    {
      public void dwAlwys()
      {
        int iW = this.mWD.getBufW(this.mImgId);
        int iH = this.mWD.getBufH(this.mImgId);
        int cX = this.mX + this.mW / 2;
        int cY = this.mY + (iH / 5 + iH + iH / 2);
        int dfclt = MnDfclt.this.mMnItm.mSldNow - 1;

        if (MnDfclt.this.mVwStgClrStts)
        {
          int rcrdSz = MnDfclt.this.mClrDfcltArr[dfclt];

          int rDX = this.mX;
          int rDW = this.mW;
          int rDY = this.mY + this.mAL.mML.mStrFntDflt.lnH * 4 / 10;
          int rDHOut = this.mAL.mML.mStrFntDflt.lnH * 14 / 10;
          int rDHIn = this.mAL.mML.mStrFntDflt.lnH;

          cY += rDHOut + this.mAL.mML.l.dlg_mrgnIn;

          this.mWD.useCol(WrpDw.COL_WHITE);
          this.mWD.setTrns(75);
          this.mWD.flRRct(rDX, rDY, rDW, rDHOut, 12, 12);
          this.mWD.setTrns(100);

          UtlDw.dwCntrStr(
            this.mWD, 
            rDX, 
            rDY + (rDHOut - rDHIn) / 2, 
            rDW, 
            rDHIn, 
            this.mAL.mMngLng.f("selDfclt_Clr", new Object[] { Integer.valueOf(rcrdSz) }), 
            this.mAL.mML.mStrFntDflt, 
            WrpDw.STR_FRM_DFLT);
        }

        int[][][] pos = { 
          { new int[2] }, 
          { { -iH / 2 }, { iH / 2 } }, 
          { { -iH }, new int[2], { iH } }, 
          { { -iH / 2, -iH / 2 }, { -iH / 2, iH / 2 }, { iH / 2, iH / 2 }, { iH / 2, -iH / 2 } }, 
          { { -iH, -iH }, { -iH, iH }, new int[2], { iH, iH }, { iH, -iH } }, 
          { { -iH, -iH / 2 }, { 0, -iH / 2 }, { iH, -iH / 2 }, 
          { -iH, iH / 2 }, { 0, iH / 2 }, { iH, iH / 2 } }, 
          { { -iH / 2, -iH }, { iH / 2, -iH }, 
          { -iH }, new int[2], { iH }, 
          { -iH / 2, iH }, { iH / 2, iH } }, 
          { { -iH, -iH }, { 0, -iH }, { iH, -iH }, 
          { -iH / 2 }, { iH / 2 }, 
          { -iH, iH }, { 0, iH }, { iH, iH } } };

        for (int i = 0; i < dfclt + 1; i++) {
          int dX = pos[dfclt][i][0];
          int dY = pos[dfclt][i][1];
          dX = cX + dX - iW / 2;
          dY = cY + dY - iH / 2;
          this.mWD.dwImg(this.mImgId, dX, dY);
        }
      }
    };
    drw.mImgId = IMG_ID_LV;
    this.mAL.mMngDlg.addClckbl(0, drw);
  }

  public void alwysWait()
  {
    this.mAL.mMngDlg.doAlwysCmn();
  }

  public void finish()
  {
    this.mAL = null;
    this.mMnItm = null;

    this.mActrNxt = null;
    this.mActrBck = null;
  }
}