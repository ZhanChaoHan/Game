package com.crocro.simWar.scn;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.clckbl.TabOpt;
import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.clckbl.LghtBox;
import com.crocro.wrp.clckbl.StrBtn;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.mng.MngLayout;
import com.crocro.wrp.mng.MngLng;
import com.crocro.wrp.mng.MngOpt;
import com.crocro.wrp.mng.MngScn;
import com.crocro.wrp.mng.MngSnd;
import com.crocro.wrp.scn.Scn;
import com.crocro.wrp.utl.Actr;
import com.crocro.wrp.utl.Drwr;
import com.crocro.wrp.utl.Rsrv;
import com.crocro.wrp.utl.UtlDw;
import com.crocro.wrp.utl.UtlTool;
import com.crocro.wrp.wrp.WrpDw;
import com.crocro.wrp.wrp.WrpDw.StrFntOpt;
import com.crocro.wrp.wrp.WrpEnv;
import com.crocro.wrp.wrp.WrpFile;
import com.crocro.wrp.wrp.WrpNet;
import java.io.File;
import java.util.ArrayList;

public class ScnTtlGm extends Scn
{
  private AppLoopSimWar mAL = null;

  private final int IMG_ID_TTL_BRD = AppLoopSimWar.IMG_ID_CMN_MAX + 1;
  private final int IMG_ID_TTL_CHRS = AppLoopSimWar.IMG_ID_CMN_MAX + 2;

  private final String IMG_FNM_TTL_BRD = "ttl/brd.png";
  private final String IMG_FNM_TTL_CHRS = "ttl/chrs.png";

  private int mTtlX = 0;
  private int mTtlY = 0;
  private int mTtlW = 0;
  private int mTtlH = 0;

  private final int BTN_SCR_MRGN = 60;
  private final int BTN_MID_MRGN = 8;
  private final int BTN_IMG_MRGN = 4;

  private MnOpt mMnOpt = null;

  private final int NEWS_MRGN = 8;
  private final String NEWS_PTH = "lstNews.html";

  public ScnTtlGm(AppLoopSimWar al)
  {
    super(al);
  }

  public void init()
  {
    this.mAL = ((AppLoopSimWar)this.mmAL);
    super.init();

    if (this.mMnOpt == null) this.mMnOpt = new MnOpt(this.mAL, null);
  }

  public void doFlw(int flw)
  {
    if (flw == com.crocro.simWar.app.V.FLW_INIT)
      flwInit();
    else
      this.mMnOpt.chkFlwInit(flw);
  }

  public void doAlwys(int flw)
  {
    if (flw == com.crocro.simWar.app.V.FLW_WAIT)
      alwysWait();
    else
      this.mMnOpt.chkAlwysWait(flw);
  }

  public void preAlwys()
  {
    this.mAL.mDfltBGDrwr.dw();

    this.mAL.mWD.zmImg(this.IMG_ID_TTL_BRD, this.mTtlX, this.mTtlY, this.mTtlW, this.mTtlH);

    UtlDw.dwTile(this.mAL.mWD, this.IMG_ID_TTL_CHRS, 
      0, this.mTtlH, 
      (int)(this.mAL.mTmNow / 16L % this.mAL.mWD.getBufW(this.IMG_ID_TTL_CHRS)), 0, 
      this.mAL.mMC.getScrW(), this.mAL.mWD.getBufH(this.IMG_ID_TTL_CHRS));

    UtlDw.dwTile(this.mAL.mWD, this.IMG_ID_TTL_CHRS, 
      0, this.mAL.mMC.getScrH() - this.mAL.mWD.getBufH(this.IMG_ID_TTL_CHRS), 
      (int)(this.mAL.mTmNow / 16L % this.mAL.mWD.getBufW(this.IMG_ID_TTL_CHRS) + this.mAL.mWD.getBufW(this.IMG_ID_TTL_CHRS) / 2), 0, 
      this.mAL.mMC.getScrW(), this.mAL.mWD.getBufH(this.IMG_ID_TTL_CHRS));
  }

  public void flwInit()
  {
    this.mAL.egV.mGmRstrt = false;

    this.mAL.mWD.mkBuf(this.IMG_ID_TTL_BRD, "ttl/brd.png");
    this.mAL.mWD.mkBuf(this.IMG_ID_TTL_CHRS, "ttl/chrs.png");

    this.mTtlW = this.mAL.mWD.getBufW(this.IMG_ID_TTL_BRD);
    this.mTtlH = this.mAL.mWD.getBufH(this.IMG_ID_TTL_BRD);
    if (this.mAL.mMC.getScrW() > this.mTtlW) {
      this.mTtlX = ((this.mAL.mMC.getScrW() - this.mTtlW) / 2);
    }
    if (this.mAL.mMC.getScrW() < this.mTtlW) {
      this.mTtlH = (this.mTtlH * this.mAL.mMC.getScrW() / this.mTtlW);
      this.mTtlW = this.mAL.mMC.getScrW();
    }

    int btnW = this.mAL.mMC.getScrW() - 120;
    int btnX = 60;
    int btnH = (this.mAL.mMC.getScrH() - 
      this.mTtlH - this.mAL.mWD.getBufH(this.IMG_ID_TTL_CHRS) * 2 - 
      16 - 8) / 3;
    int btnBsY = this.mTtlH + this.mAL.mWD.getBufH(this.IMG_ID_TTL_CHRS);

    StrBtn btnSelStg = new StrBtn(this.mAL, 
      btnX, btnBsY + 4, 
      btnW, btnH, "Select Stage")
    {
      public void addActClck() {
        ScnTtlGm.this.mClckEnbl = false;
        this.mAL.mRsrvLst.add(new Rsrv(this.mAL, 550L) {
          public void addAct() {
            ScnTtlGm.this.mClckEnbl = true;
            mStatBtn = 0;

            ScnTtlGm.this.chkLcnc(new Actr()
            {
              public void act() {
                ScnTtlGm.this.mAL.mMS.setNxtScn(com.crocro.simWar.app.V.SCN_SEL_STG);
              }
            });
          }
        });
      }
    };
    addClckbl(0, btnSelStg);

    StrBtn btnRstrtWar = new StrBtn(this.mAL, 
      btnX, btnBsY + 4 + 8 + btnH, 
      btnW, btnH, "Restart War")
    {
      public void addActClck() {
        ScnTtlGm.this.mClckEnbl = false;
        this.mAL.mRsrvLst.add(new Rsrv(this.mAL, 550L)
        {
          public void addAct() {
            ScnTtlGm.this.mClckEnbl = true;
            mStatBtn = 0;

            if (((com.crocro.simWar.app.V)this.mAL.mV).gd.stgSv > 0)
            {
              if (this.mAL.mV.ldDt(1))
              {
                ((AppLoopSimWar)this.mAL).egV.mGmRstrt = true;
                this.mAL.mMS.setNxtScn(com.crocro.simWar.app.V.SCN_GM);
                return;
              }

            }

            LghtBox lghtBox = new LghtBox(this.mAL, 
              this.mAL.mMngLng.getS("scnTtl_NotSvDt"), 
              ScnTtlGm.this);

            ScnTtlGm.this.addClckbl(9, lghtBox);
          }
        });
      }
    };
    addClckbl(0, btnRstrtWar);

    StrBtn btnHelp = new StrBtn(this.mAL, 
      btnX, btnBsY + 4 + 16 + btnH * 2, 
      btnW, btnH, "Help")
    {
      public void addActClck() {
        ScnTtlGm.this.mClckEnbl = false;
        this.mAL.mRsrvLst.add(new Rsrv(this.mAL, 550L)
        {
          public void addAct() {
            this.mAL.mWN.openBrwsr(this.mAL.mMngLng.getS("hlp_Lnk"));

            ScnTtlGm.this.mClckEnbl = true;
            mStatBtn = 0;
          }
        });
      }
    };
    addClckbl(0, btnHelp);

    chkNews();

    vwBugReport();

    addClckbl(8, new TabOpt(this.mAL));

    this.mAL.mMngSnd.playBgm("bgmTtl");

    this.mAL.mMS.setNxtFlw(com.crocro.simWar.app.V.FLW_WAIT);
  }

  public void chkLcnc(final Actr okActr)
  {

      final String lcncKey = mAL.mBsOpt.getS("lcncKey");
      if(lcncKey.length() == 0)
      {
          okActr.act();
          return;
      }
      final MngOpt mo = new MngOpt(mAL, "lcnc.dat.msk", true);
      String usrNm_ = null;
      String usrHm_ = null;
      try
      {
          usrNm_ = System.getProperty("user.name", "");
      }
      catch(Exception e)
      {
    	  e.printStackTrace();
          usrNm_ = "";
      }
      try
      {
          usrHm_ = System.getProperty("user.home", "");
      }
      catch(Exception e)
      {
    	  e.printStackTrace();
          usrHm_ = "";
      }
      final String usrNm = usrNm_;
      final String usrHm = usrHm_;
      if(!mo.getS("lcncKey").matches(lcncKey) || !mo.getS("usrNm").equals(usrNm) || !mo.getS("usrHm").equals(usrHm))
          mAL.mWE.inputDlg(mAL.mMngLng.getS("lcnc_Mes"), "", new Actr() {

              public  void act(Object obj[])
              {
                  String inputStr = (String)obj[0];
                  if(inputStr == null || !inputStr.matches(lcncKey))
                  {
                      mAL.mLcncLck = true;
                      mAL.mWE.messageDlg(mAL.mMngLng.getS("lcnc_ErrTtl"), UtlTool.smplFormat(mAL.mMngLng.getS("lcnc_ErrMes"), new Object[] {
                          Integer.valueOf(mAL.mBsOpt.getI("stgLcncMax"))
                      }), new Actr() {

                          public void act()
                          {
                              okActr.act();
                          }

                    
                  
                      }
);
                  } else
                  {
                      mAL.mLcncLck = false;
                      mo.setS("lcncKey", inputStr);
                      mo.setS("usrNm", usrNm);
                      mo.setS("usrHm", usrHm);
                      mo.sv();
                      String lcncMsk = mAL.mBsOpt.getS("lcncMsk");
                      if(lcncMsk.length() > 0)
                          mAL.mWF.setLcncKey(mAL.mBsOpt.getS("lcncMsk"));
                      else
                          mAL.mWF.setLcncKey(mo.getS("lcncKey"));
                      okActr.act();
                  }
              }


          }
);
      else{
          okActr.act();
      }
  }


  private void vwBugReport()
  {
    int w = -1;
    int h = this.mAL.mML.mStrFntDflt.lnH * 4 / 2;
    int y = this.mAL.mMC.getScrH() - 8 - h;

    StrBtn btnBug = new InfBtn(this.mAL, 
      0, y, w, h, this.mAL.mMngLng.getS("bugReport_Ttl"))
    {
      public void addActClck() {
        ScnTtlGm.this.mClckEnbl = false;
        this.mAL.mRsrvLst.add(new Rsrv(this.mAL, 550L)
        {
          public void addAct() {
            this.mAL.mWN.openBrwsr(this.mAL.mMngLng.getS("bugReport_Lnk"));

            ScnTtlGm.this.mClckEnbl = true;
            mStatBtn = 0;
          }
        });
      }
    };
    btnBug.mX = (this.mAL.mMC.getScrW() - btnBug.mW - 8);
    addClckbl(0, btnBug);
  }

  private void chkNews()
  {
    if (this.mAL.mMngLng.getI("news_Use") == 0) return;

    String nwsStrOld = UtlTool.bToS(this.mAL.mWF.getFBArr("lstNews.html"));

    dlNews();

    String nwsStr = UtlTool.bToS(this.mAL.mWF.getFBArr("lstNews.html"));
    if ((nwsStr == null) || (nwsStr.length() == 0)) return;

    if (!nwsStr.equals(nwsStrOld))
    {
      this.mAL.mV.gd.chkNws = 0;
      this.mAL.mV.svDt(0);
    }

    String dateStr = UtlTool.getPrmFrmHtml("aplNews.lastUpdate", nwsStr);
    if ((dateStr == null) || (dateStr.length() == 0)) return;

    int w = -1;
    int h = this.mAL.mML.mStrFntDflt.lnH * 4 / 2;
    int x = 8;
    int y = this.mAL.mMC.getScrH() - 8 - h;

    StrBtn btnNews = new InfBtn(this.mAL, 
      x, y, w, h, this.mAL.mMngLng.getS("news_Ttl") + dateStr)
    {
      public void addActClck() {
        ScnTtlGm.this.mClckEnbl = false;
        this.mAL.mRsrvLst.add(new Rsrv(this.mAL, 550L)
        {
          public void addAct() {
            this.mAL.mWN.openBrwsr(this.mAL.mMngLng.getS("news_UrlOpn"));

            this.mAL.mV.gd.chkNws = 1;
            this.mAL.mV.svDt(0);

            ScnTtlGm.this.mClckEnbl = true;
            mStatBtn = 0;
          }
        });
      }

      public boolean isAttnt()
      {
        return this.mAL.mV.gd.chkNws == 0;
      }
    };
    addClckbl(0, btnNews);
  }

  private void dlNews()
  {
    File f = this.mAL.mWF.getDatF("lstNews.html");
    if ((f.exists()) && (f.isFile()) && (f.length() > 0L))
    {
      if (this.mAL.mTmNow - f.lastModified() <= 86400000L)
      {
        return;
      }

    }

    String newsStr = this.mAL.mWN.getHttp(
      this.mAL.mMngLng.getS("news_UrlDl"), 
      1000, 
      1000);

    if (newsStr.length() == 0) return;

    byte[] bArr = new byte[0];
    try {
      bArr = newsStr.getBytes("UTF-8"); } catch (Exception e) {
    	  e.printStackTrace();
    }
    this.mAL.mWF.setFBArr("lstNews.html", bArr);
  }

  public void alwysWait()
  {
    doAlwysCmn();
  }

  public void finish()
  {
    super.finish();

    this.mMnOpt.finish();
    this.mMnOpt = null;
  }

  public class InfBtn extends StrBtn
  {
    public InfBtn(AppLoop al, int x, int y, int w, int h, String ttlStr)
    {
      super(al,x, y, w, h, ttlStr);

      initFntPrm(this.mW, this.mH);
    }

    public boolean isAttnt()
    {
      return false;
    }

    public void dwBfrBtnBs()
    {
      this.mStrFntOpt.r = 224;
      this.mStrFntOpt.g = 224;
      this.mStrFntOpt.b = 224;

      this.mWD.setTrns(60);
      this.mWD.useCol(WrpDw.COL_BLACK);
      this.mWD.flRRct(this.mX, this.mY, this.mW, this.mH, 12, 12);

      this.mWD.setTrns(80);
      this.mWD.setStrk(3);
      if (isAttnt())
        this.mWD.useCol(WrpDw.COL_RED);
      else {
        this.mWD.useCol(WrpDw.COL_BLACK);
      }
      this.mWD.dwRRct(this.mX, this.mY, this.mW, this.mH, 12, 12);
      this.mWD.setStrk(1);
      this.mWD.setTrns(100);
    }

    public void dwAftrBtnBs()
    {
      this.mStrFntOpt.r = 51;
      this.mStrFntOpt.g = 51;
      this.mStrFntOpt.b = 51;

      this.mWD.setTrns(60);
      this.mWD.useCol(WrpDw.COL_WHITE);
      this.mWD.flRRct(this.mX, this.mY, this.mW, this.mH, 12, 12);

      this.mWD.setTrns(80);
      this.mWD.setStrk(3);
      this.mWD.useCol(WrpDw.COL_BLACK);
      this.mWD.dwRRct(this.mX, this.mY, this.mW, this.mH, 12, 12);
      this.mWD.setStrk(1);
      this.mWD.setTrns(100);
    }
  }
}