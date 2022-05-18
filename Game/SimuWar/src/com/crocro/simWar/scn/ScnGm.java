package com.crocro.simWar.scn;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V.GD;
import com.crocro.simWar.app.V.LD;
import com.crocro.simWar.app.V.LDA;
import com.crocro.simWar.clckbl.GmLog;
import com.crocro.simWar.clckbl.GmMap;
import com.crocro.simWar.clckbl.GmMapAll;
import com.crocro.simWar.clckbl.GmRng;
import com.crocro.simWar.clckbl.GmTmr;
import com.crocro.simWar.clckbl.GmUnt;
import com.crocro.simWar.clckbl.GmUntDst;
import com.crocro.simWar.clckbl.TabOpt;
import com.crocro.simWar.gm.GmDtMap;
import com.crocro.simWar.gm.GmDtUnt;
import com.crocro.simWar.gm.GmDtUntF;
import com.crocro.simWar.gm.GmImgUnt;
import com.crocro.simWar.gm.GmImgWpn;
import com.crocro.simWar.gm.GmToolMap;
import com.crocro.simWar.gm.GmToolScr;
import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.bs.SF;
import com.crocro.wrp.clckbl.Btn;
import com.crocro.wrp.clckbl.DrwScrpt;
import com.crocro.wrp.clckbl.LghtBox;
import com.crocro.wrp.clckbl.LghtBoxScrpt;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.mng.MngLng;
import com.crocro.wrp.mng.MngOpt;
import com.crocro.wrp.mng.MngScn;
import com.crocro.wrp.mng.MngSnd;
import com.crocro.wrp.scn.Scn;
import com.crocro.wrp.utl.Actr;
import com.crocro.wrp.utl.Rsrv;
import com.crocro.wrp.utl.UtlDw;
import com.crocro.wrp.utl.UtlTool;
import com.crocro.wrp.wrp.WrpDw;
import com.crocro.wrp.wrp.WrpFile;
import java.util.ArrayList;
import java.util.Random;

public class ScnGm extends Scn
  implements GmDtUntF
{
  public AppLoopSimWar mAL = null;

  private static final int IMG_ID_BTN_LCT0 = AppLoopSimWar.IMG_ID_CMN_MAX + SF.seq(0);
  private static final int IMG_ID_BTN_LCT1 = AppLoopSimWar.IMG_ID_CMN_MAX + SF.seq();
  private static final int IMG_ID_BTN_LCT2 = AppLoopSimWar.IMG_ID_CMN_MAX + SF.seq();
  private static final int IMG_ID_BTN_RNG0 = AppLoopSimWar.IMG_ID_CMN_MAX + SF.seq();
  private static final int IMG_ID_BTN_RNG1 = AppLoopSimWar.IMG_ID_CMN_MAX + SF.seq();
  private static final int IMG_ID_BTN_RNG2 = AppLoopSimWar.IMG_ID_CMN_MAX + SF.seq();
  private static final int IMG_ID_BTN_CHR0 = AppLoopSimWar.IMG_ID_CMN_MAX + SF.seq();
  private static final int IMG_ID_BTN_CHR1 = AppLoopSimWar.IMG_ID_CMN_MAX + SF.seq();
  private static final int IMG_ID_BTN_CHR2 = AppLoopSimWar.IMG_ID_CMN_MAX + SF.seq();
  public static final int IMG_ID_PNTR_MV_TGT = AppLoopSimWar.IMG_ID_CMN_MAX + SF.seq();
  public static final int IMG_ID_NMBR = AppLoopSimWar.IMG_ID_CMN_MAX + SF.seq();
  public static final int IMG_ID_UNT_B_TMP = AppLoopSimWar.IMG_ID_CMN_MAX + SF.seq();
  public static final int IMG_ID_MAP_VW = AppLoopSimWar.IMG_ID_CMN_MAX + SF.seq();
  public static final int IMG_ID_MAP_ALL = AppLoopSimWar.IMG_ID_CMN_MAX + SF.seq();
  public static final int IMG_ID_MAP_DTCTN = AppLoopSimWar.IMG_ID_CMN_MAX + SF.seq();
  public static final int IMG_ID_EF_DTH_MY = AppLoopSimWar.IMG_ID_CMN_MAX + SF.seq();
  public static final int IMG_ID_EF_DTH_ENE = AppLoopSimWar.IMG_ID_CMN_MAX + SF.seq();
  public static final int IMG_ID_WIN_BG = AppLoopSimWar.IMG_ID_CMN_MAX + SF.seq();
  public static final int IMG_ID_TWITTER = AppLoopSimWar.IMG_ID_CMN_MAX + SF.seq();
  public static final int IMG_ID_FACEBOOK = AppLoopSimWar.IMG_ID_CMN_MAX + SF.seq();
  public static final int IMG_ID_MAX = AppLoopSimWar.IMG_ID_CMN_MAX + SF.seq();
  private static final String IMG_PTH_BTN_LCT0 = "gm/btn_lct0.png";
  private static final String IMG_PTH_BTN_LCT1 = "gm/btn_lct1.png";
  private static final String IMG_PTH_BTN_LCT2 = "gm/btn_lct2.png";
  private static final String IMG_PTH_BTN_RNG0 = "gm/btn_rng0.png";
  private static final String IMG_PTH_BTN_RNG1 = "gm/btn_rng1.png";
  private static final String IMG_PTH_BTN_RNG2 = "gm/btn_rng2.png";
  private static final String IMG_PTH_BTN_CHR0 = "gm/btn_chr0.png";
  private static final String IMG_PTH_BTN_CHR1 = "gm/btn_chr1.png";
  private static final String IMG_PTH_BTN_CHR2 = "gm/btn_chr2.png";
  private static final String IMG_PTH_PNTR_MV_TGT = "gm/pntr_mv_tgt.png";
  private static final String IMG_PTH_NMBR = "gm/nmbr.png";
  public static final String IMG_PTH_UB = "ub/%s.png";
  public static final String IMG_PTH_MAP_VW = "map/map%d_bs.jpg";
  private static final String IMG_PTH_MAP_DTCTN = "map/map%d_dtctn.gif";
  private static final String IMG_PTH_EF_DTH_MY = "gm/ef_dth_my.png";
  private static final String IMG_PTH_EF_DTH_ENE = "gm/ef_dth_ene.png";
  private static final String IMG_PTH_TWITTER = "cmn/icn_twitter.png";
  private static final String IMG_PTH_FACEBOOK = "cmn/icn_facebook.png";
  public static final String IMG_PTH_WIN_BG = "gm/win_bg.png";
  private int mBtnW;
  private int mBtnH;
  public int mBtnWSum;
  public GmMap mGmMap;
  public GmMapAll mGmMapAll;
  public GmRng mGmRng;
  public GmLog mGmLog;
  public static final int U_SEL_TYP_DST = 0;
  public static final int U_SEL_TYP_RNG = 1;
  public static final int U_SEL_TYP_INF = 2;
  public int mUSelTyp = 0;
  private MnUntInf mMnUntInf;
  private MnOpt mMnOpt;
  public GmDtMap mGDMap;
  public GmDtUnt mGDUnt;
  public GmToolMap mGTMap;
  public GmToolScr mGTScr;
  public GmImgUnt mGIUnt;
  public GmImgWpn mGIWpn;
  public long mTmGm;
  public boolean mGmPrgrs;
  public int mSelUntId = -1;
  public long mSelUntTm = 0L;

  public ScnGm(AppLoopSimWar al)
  {
    super(al);
    this.mAL = ((AppLoopSimWar)this.mmAL);
  }

  public void init()
  {
    super.init();

    this.mGmPrgrs = false;
    initGmDat();

    if (this.mMnUntInf == null) this.mMnUntInf = new MnUntInf(this.mAL, this);
    if (this.mMnOpt == null) this.mMnOpt = new MnOpt(this.mAL, this);
  }

  public void doFlw(int flw)
  {
    if (flw == com.crocro.simWar.app.V.FLW_INIT) {
      flwInit();
    }
    else if (!this.mMnUntInf.chkFlwInit(flw))
      this.mMnOpt.chkFlwInit(flw);
  }

  public void doAlwys(int flw)
  {
    if (flw == com.crocro.simWar.app.V.FLW_WAIT) {
      alwysWait();
    }
    else if (!this.mMnUntInf.chkAlwysWait(flw))
      this.mMnOpt.chkAlwysWait(flw);
  }

  public void preAlwys()
  {
    this.mAL.mWD.useCol(WrpDw.COL_BLACK);
    this.mAL.mWD.flRct(0, 0, this.mAL.mMC.getScrW(), this.mAL.mMC.getScrH());
  }

  public void flwInit()
  {
    int stgNow = this.mAL.egV.gd.stgNow;

    if (this.mAL.mBsOpt.getS("stgRef" + stgNow).length() != 0)
    {
      int stgRef = this.mAL.mBsOpt.getI("stgRef" + stgNow);

      this.mAL.mWD.mkBuf(IMG_ID_MAP_VW, UtlTool.smplFormat("map/map%d_bs.jpg", new Object[] { Integer.valueOf(stgRef) }));
      this.mAL.mWD.mkBuf(IMG_ID_MAP_DTCTN, UtlTool.smplFormat("map/map%d_dtctn.gif", new Object[] { Integer.valueOf(stgRef) }));

      String fncStr = this.mAL.mBsOpt.getS("stgPrc" + stgNow);
      UtlDw.prcBufFromS(this.mAL.mWD, IMG_ID_MAP_VW, fncStr);
      UtlDw.prcBufFromS(this.mAL.mWD, IMG_ID_MAP_DTCTN, fncStr);
    }
    else {
      this.mAL.mWD.mkBuf(IMG_ID_MAP_VW, UtlTool.smplFormat("map/map%d_bs.jpg", new Object[] { Integer.valueOf(stgNow) }));
      this.mAL.mWD.mkBuf(IMG_ID_MAP_DTCTN, UtlTool.smplFormat("map/map%d_dtctn.gif", new Object[] { Integer.valueOf(stgNow) }));
    }

    if (this.mAL.mML.l.vwTyp == 1)
    {
      this.mAL.mWD.mkBuf(IMG_ID_BTN_LCT0, com.crocro.simWar.mng.MngLayout.dir2sml("gm/btn_lct0.png"));
      this.mAL.mWD.mkBuf(IMG_ID_BTN_LCT1, com.crocro.simWar.mng.MngLayout.dir2sml("gm/btn_lct1.png"));
      this.mAL.mWD.mkBuf(IMG_ID_BTN_LCT2, com.crocro.simWar.mng.MngLayout.dir2sml("gm/btn_lct2.png"));
      this.mAL.mWD.mkBuf(IMG_ID_BTN_RNG0, com.crocro.simWar.mng.MngLayout.dir2sml("gm/btn_rng0.png"));
      this.mAL.mWD.mkBuf(IMG_ID_BTN_RNG1, com.crocro.simWar.mng.MngLayout.dir2sml("gm/btn_rng1.png"));
      this.mAL.mWD.mkBuf(IMG_ID_BTN_RNG2, com.crocro.simWar.mng.MngLayout.dir2sml("gm/btn_rng2.png"));
      this.mAL.mWD.mkBuf(IMG_ID_BTN_CHR0, com.crocro.simWar.mng.MngLayout.dir2sml("gm/btn_chr0.png"));
      this.mAL.mWD.mkBuf(IMG_ID_BTN_CHR1, com.crocro.simWar.mng.MngLayout.dir2sml("gm/btn_chr1.png"));
      this.mAL.mWD.mkBuf(IMG_ID_BTN_CHR2, com.crocro.simWar.mng.MngLayout.dir2sml("gm/btn_chr2.png"));
    }
    else {
      this.mAL.mWD.mkBuf(IMG_ID_BTN_LCT0, "gm/btn_lct0.png");
      this.mAL.mWD.mkBuf(IMG_ID_BTN_LCT1, "gm/btn_lct1.png");
      this.mAL.mWD.mkBuf(IMG_ID_BTN_LCT2, "gm/btn_lct2.png");
      this.mAL.mWD.mkBuf(IMG_ID_BTN_RNG0, "gm/btn_rng0.png");
      this.mAL.mWD.mkBuf(IMG_ID_BTN_RNG1, "gm/btn_rng1.png");
      this.mAL.mWD.mkBuf(IMG_ID_BTN_RNG2, "gm/btn_rng2.png");
      this.mAL.mWD.mkBuf(IMG_ID_BTN_CHR0, "gm/btn_chr0.png");
      this.mAL.mWD.mkBuf(IMG_ID_BTN_CHR1, "gm/btn_chr1.png");
      this.mAL.mWD.mkBuf(IMG_ID_BTN_CHR2, "gm/btn_chr2.png");
    }

    this.mBtnW = this.mAL.mWD.getBufW(IMG_ID_BTN_LCT0);
    this.mBtnH = this.mAL.mWD.getBufH(IMG_ID_BTN_LCT0);
    this.mBtnWSum = (this.mBtnW * 3);

    this.mAL.mWD.mkBuf(IMG_ID_PNTR_MV_TGT, "gm/pntr_mv_tgt.png");

    if (this.mAL.mML.l.vwTyp == 1)
    {
      this.mAL.mWD.mkBuf(IMG_ID_NMBR, com.crocro.simWar.mng.MngLayout.dir2sml("gm/nmbr.png"));
    }
    else {
      this.mAL.mWD.mkBuf(IMG_ID_NMBR, "gm/nmbr.png");
    }

    this.mAL.mWD.mkBuf(IMG_ID_EF_DTH_MY, "gm/ef_dth_my.png");
    this.mAL.mWD.mkBuf(IMG_ID_EF_DTH_ENE, "gm/ef_dth_ene.png");

    this.mAL.mWD.mkBuf(IMG_ID_TWITTER, "cmn/icn_twitter.png");
    this.mAL.mWD.mkBuf(IMG_ID_FACEBOOK, "cmn/icn_facebook.png");

    this.mGDMap = new GmDtMap(this.mAL, IMG_ID_MAP_VW, IMG_ID_MAP_DTCTN);

    int x = ((com.crocro.simWar.mng.MngLayout)this.mAL.mML).l.gmBtnOutMrgnL;
    int y = ((com.crocro.simWar.mng.MngLayout)this.mAL.mML).l.gmBtnOutMrgn;
    Btn btnLct = new Btn(this.mAL, x, y, this.mBtnW, this.mBtnH, 0) {
      public void addActClck() {
        ScnGm.this.mClckEnbl = false;
        ScnGm.this.strtGmElps();
        this.mAL.mRsrvLst.add(new Rsrv(this.mAL, 550L) {
          public void addAct() {
            ScnGm.this.mUSelTyp = 0;
            ScnGm.this.mClckEnbl = true;
            mStatBtn = 0;
          }
        });
      }

      public void dwBfr() {
        if (ScnGm.this.mUSelTyp == 0)
          this.mAL.mWD.dwImg(ScnGm.IMG_ID_BTN_LCT2, this.mX, this.mY);
        else
          super.dwBfr();
      }
    };
    btnLct.mBfrImgId = IMG_ID_BTN_LCT0;
    btnLct.mAftrImgId = IMG_ID_BTN_LCT1;
    addClckbl(6, btnLct);

    x += this.mBtnW;
    y = ((com.crocro.simWar.mng.MngLayout)this.mAL.mML).l.gmBtnOutMrgn;
    Btn btnRng = new Btn(this.mAL, x, y, this.mBtnW, this.mBtnH, 0) {
      public void addActClck() {
        ScnGm.this.mClckEnbl = false;
        ScnGm.this.mGmRng.unSel();
        ScnGm.this.strtGmElps();
        this.mAL.mRsrvLst.add(new Rsrv(this.mAL, 550L) {
          public void addAct() {
            ScnGm.this.mUSelTyp = 1;
            ScnGm.this.mClckEnbl = true;
           mStatBtn = 0;
          }
        });
      }

      public void dwBfr() {
        if (ScnGm.this.mUSelTyp == 1)
          this.mAL.mWD.dwImg(ScnGm.IMG_ID_BTN_RNG2, this.mX, this.mY);
        else
          super.dwBfr();
      }
    };
    btnRng.mBfrImgId = IMG_ID_BTN_RNG0;
    btnRng.mAftrImgId = IMG_ID_BTN_RNG1;
    addClckbl(6, btnRng);

    x += this.mBtnW;
    y = ((com.crocro.simWar.mng.MngLayout)this.mAL.mML).l.gmBtnOutMrgn;
    Btn btnChr = new Btn(this.mAL, x, y, this.mBtnW, this.mBtnH, 0) {
      public void addActClck() {
        ScnGm.this.mClckEnbl = false;
        ScnGm.this.stopGmElps();
        this.mAL.mRsrvLst.add(new Rsrv(this.mAL, 550L) {
          public void addAct() {
            ScnGm.this.mUSelTyp = 2;
            ScnGm.this.mClckEnbl = true;
           mStatBtn = 0;
          }
        });
      }

      public void dwBfr() {
        if (ScnGm.this.mUSelTyp == 2)
          this.mAL.mWD.dwImg(ScnGm.IMG_ID_BTN_CHR2, this.mX, this.mY);
        else
          super.dwBfr();
      }
    };
    btnChr.mBfrImgId = IMG_ID_BTN_CHR0;
    btnChr.mAftrImgId = IMG_ID_BTN_CHR1;
    addClckbl(6, btnChr);

    this.mGTMap = new GmToolMap(this, this.mAL.egV.mGD, this.mGDUnt, this.mGDMap);
    this.mGTScr = new GmToolScr(this.mAL, this);

    this.mGmMapAll = new GmMapAll(this.mAL, this);
    addClckbl(6, this.mGmMapAll);

    this.mGIUnt = new GmImgUnt(this.mAL);
    this.mGIWpn = new GmImgWpn(this.mAL);

    int btnOutMrgn = ((com.crocro.simWar.mng.MngLayout)this.mAL.mML).l.gmBtnOutMrgn * 2;
    if (btnOutMrgn == 0) btnOutMrgn = 4;
    int mapMrgnHBs = this.mGmMapAll.mMapAllH + btnOutMrgn + 6;

    this.mGmMap = 
      new GmMap(this.mAL, 
      0, 0, this.mAL.mMC.getScrW(), this.mAL.mMC.getScrH(), 
      IMG_ID_MAP_VW, 
      mapMrgnHBs, mapMrgnHBs / 2, 
      0);

    addClckbl(0, this.mGmMap);

    for (int i = 0; i < 132; i++)
    {
      if ((this.mAL.mBsOpt.getI("dbgFrcWin") == 1) && 
        (i >= 32))
        continue;
      if ((this.mAL.mBsOpt.getI("dbgFrcLose") == 1) && 
        (i < 32))
      {
        continue;
      }
      if (this.mGDUnt.getEnbl(i) != 0) {
        GmUnt unt = new GmUnt(this.mAL, this, i) {
          public void addActClck() {
            ScnGm.this.mClckEnbl = false;
            Rsrv rsrv = new Rsrv(ScnGm.this.mAL, 550L) {
              public void addAct() {
                ScnGm.this.mMnUntInf.mUId = mUId;
                ScnGm.this.escTmpOpt();
                this.mAL.mMS.setNxtFlw(com.crocro.simWar.app.V.FLW_MN_UNT_INF_INIT);
                ScnGm.this.mClckEnbl = true;
                mStatBtn = 0;
              }
            };
            ScnGm.this.mAL.mRsrvLst.add(rsrv);
          }
        };
        addClckbl(2, unt);

        GmUntDst untDst = new GmUntDst(this.mAL, this, i, IMG_ID_PNTR_MV_TGT);
        untDst.mNrmlImgId = IMG_ID_PNTR_MV_TGT;
        untDst.mDrgImgId = IMG_ID_PNTR_MV_TGT;
        untDst.mW = this.mAL.mWD.getBufW(IMG_ID_PNTR_MV_TGT);
        untDst.mH = this.mAL.mWD.getBufH(IMG_ID_PNTR_MV_TGT);
        if (i >= 32) {
          untDst.mClckTyp = 0;
        }
        addClckbl(1, untDst);

        int skl = this.mGDUnt.getSkl(i);
        if (skl != -1) {
          int bufFly = this.mGIWpn.getId(skl, 0);
          int bufHit = this.mGIWpn.getId(skl, 1);
          this.mGIWpn.ldImg(bufFly);
          this.mGIWpn.ldImg(bufHit);
        }
      }

    }

    if ((this.mAL.mBsOpt.getI("dbgFrcWin") == 1) || 
      (this.mAL.mBsOpt.getI("dbgFrcLose") == 1))
    {
      this.mGTScr.chkGmEnd();
    }

    x = ((com.crocro.simWar.mng.MngLayout)this.mAL.mML).l.gmBtnOutMrgnL;
    y = ((com.crocro.simWar.mng.MngLayout)this.mAL.mML).l.gmBtnOutMrgn * 2 + this.mBtnH;
    GmTmr gmTmr = new GmTmr(this.mAL, x, y, this);
    addClckbl(6, gmTmr);

    this.mGmRng = new GmRng(this.mAL, this);
    addClckbl(5, this.mGmRng);

    this.mGmLog = new GmLog(this.mAL, this);
    addClckbl(6, this.mGmLog);

    addClckbl(8, new TabOpt(this.mAL)
    {
      public void addActClck() {
        super.addActClck();
        ScnGm.this.escTmpOpt();
      }
    });
    this.mAL.mMngSnd.playBgm("bgmStg" + stgNow);

    this.mAL.mMS.setNxtFlw(com.crocro.simWar.app.V.FLW_WAIT);

    int xId0 = this.mGDUnt.getUS(0, GmUnt.US_MV_X_NOW);
    int yId0 = this.mGDUnt.getUS(0, GmUnt.US_MV_Y_NOW);

    this.mAL.mMC.mOrgnX = (-1 * xId0 + this.mAL.mMC.getScrW() / 2);
    this.mAL.mMC.mOrgnY = (-1 * yId0 + this.mAL.mMC.getScrH() / 2);

    if (this.mAL.mMC.mOrgnX < this.mAL.mMC.getScrW() - this.mGDMap.mMapW) this.mAL.mMC.mOrgnX = (this.mAL.mMC.getScrW() - this.mGDMap.mMapW);
    if (this.mAL.mMC.mOrgnY < this.mAL.mMC.getScrH() - this.mGDMap.mMapH) this.mAL.mMC.mOrgnY = (this.mAL.mMC.getScrH() - this.mGDMap.mMapH);
    if (this.mAL.mMC.mOrgnX > 0) this.mAL.mMC.mOrgnX = 0;
    if (this.mAL.mMC.mOrgnY > 0) this.mAL.mMC.mOrgnY = 0;

    this.mGmMap.setMapPos(-this.mAL.mMC.mOrgnX, -this.mAL.mMC.mOrgnY);

    System.gc();
    strtGmElps();

    String SCRPT_PTH = "res/dat/stgScrpt/stg%s_in.txt";
    String scrptStr = UtlTool.bToS(this.mAL.mWF.getR(UtlTool.smplFormat(SCRPT_PTH, new Object[] { Integer.valueOf(stgNow) })));

    if ((scrptStr.length() > 0) && 
      (this.mTmGm <= 100L))
    {
      stopGmElps();

      LghtBoxScrpt lghtBoxScrpt = new LghtBoxScrpt(this.mAL, "", this)
      {
        public void closeAct() {
          ScnGm.this.strtGmElps();
        }
      };
      lghtBoxScrpt.mDrwScrpt = 
        new DrwScrpt(this.mAL, 
        0, 
        (lghtBoxScrpt.getBtnY() - 240) / 2, 
        this.mAL.mMC.getScrW(), 
        240, 
        scrptStr);

      addClckbl(9, lghtBoxScrpt);
    }
  }

  public void initGmDat()
  {
    if (!this.mAL.egV.mGmRstrt)
    {
      this.mAL.egV.ld.gmStrt = 0;
      this.mAL.egV.ld.gmElps = 0;
    }
    else {
      this.mUSelTyp = 0;
    }
    this.mTmGm = getGmElps();

    if (this.mGDUnt == null) this.mGDUnt = new GmDtUnt(this.mAL);

    int[] untDat = this.mAL.egV.lda.untDat;
    if ((untDat == null) || (untDat.length == 0)) {
      this.mGDUnt.ldUntDt();
    }

    this.mGDUnt.initStts();
    this.mGDUnt.inittMvClcFrm();
  }

  public void svGmDat()
  {
    this.mAL.egV.gd.stgSv = this.mAL.egV.gd.stgNow;

    this.mAL.mV.svDt(1);
    this.mAL.mV.svDt(0);
  }

  public void escTmpOpt()
  {
    this.mAL.egV.ld.gmOrgnX = this.mAL.mMC.mOrgnX;
    this.mAL.egV.ld.gmOrgnY = this.mAL.mMC.mOrgnY;

    stopGmElps();
  }

  public void unescTmpOpt()
  {
    this.mAL.mMC.mOrgnX = this.mAL.egV.ld.gmOrgnX;
    this.mAL.mMC.mOrgnY = this.mAL.egV.ld.gmOrgnY;

    if ((this.mUSelTyp == 0) || 
      (this.mUSelTyp == 1))
    {
      strtGmElps();
    }
  }

  public long getGmElps()
  {
    long elps = this.mAL.egV.ld.gmElps;
    if (this.mAL.egV.ld.gmStrt != 0) {
      elps += this.mAL.mTmNow - this.mAL.mTmStrt - this.mAL.egV.ld.gmStrt;
    }
    return elps;
  }

  public void strtGmElps()
  {
    if (this.mAL.egV.ld.gmStrt == 0) {
      this.mAL.egV.ld.gmStrt = (int)(this.mAL.mTmNow - this.mAL.mTmStrt);
    }
    this.mGmPrgrs = true;
  }

  public void stopGmElps()
  {
    this.mGmPrgrs = false;
    if (this.mAL.egV.ld.gmStrt == 0) return;

    this.mAL.egV.ld.gmElps += (int)(this.mAL.mTmNow - this.mAL.mTmStrt - this.mAL.egV.ld.gmStrt);
    this.mAL.egV.ld.gmStrt = 0;
  }

  public void setSelUnt(int uId)
  {
    this.mSelUntId = uId;
    this.mSelUntTm = this.mTmGm;
  }

  public void alwysWait()
  {
    this.mTmGm = getGmElps();
    AppLoopSimWar.RNDM.setSeed(this.mTmGm);
    this.mGDMap.clcMapRt();
    this.mGDUnt.updtMvClcFrm();
    doAlwysCmn();
  }

  public boolean addDoBck()
  {
    escTmpOpt();

    LghtBox lghtBox = new LghtBox(this.mAL, 
      this.mAL.mMngLng.getS("mnOpt_ScnGm_BckTtl"), 
      this, 
      new Actr() {
      public void act(Object[] obj) { Boolean bool = (Boolean)obj[0];

        if (bool.booleanValue())
          ScnGm.this.mAL.mMS.setNxtScn(com.crocro.simWar.app.V.SCN_TTL_GM);
        else
          ScnGm.this.unescTmpOpt();
      }
    });
    lghtBox.mBtnTyp = 2;
    addClckbl(9, lghtBox);

    return true;
  }

  public void finish()
  {
    super.finish();

    this.mGmMap = null;
    this.mGmMapAll = null;
    this.mGmRng = null;
    this.mGmLog = null;

    this.mMnUntInf.finish(); this.mMnUntInf = null;
    this.mMnOpt.finish(); this.mMnOpt = null;
    this.mGDMap.finish(); this.mGDMap = null;
    this.mGTScr.finish(); this.mGTScr = null;
    this.mGDUnt.finish(); this.mGDUnt = null;
    this.mGTMap.finish(); this.mGTMap = null;
    this.mGIUnt.finish(); this.mGIUnt = null;
    this.mGIWpn.finish(); this.mGIWpn = null;
  }
}