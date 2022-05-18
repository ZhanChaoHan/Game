package com.crocro.wrp.mng;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.app.V;
import com.crocro.wrp.bs.F;
import com.crocro.wrp.wrp.WrpDw;
import com.crocro.wrp.wrp.WrpDw.StrFntOpt;

public class MngLayout
  implements F
{
  private AppLoop mAL = null;
  MngOpt mMngOpt;
  public static final int VW_TYP_NRML = 0;
  public static final int VW_TYP_SML = 1;
  public Layout l = new Layout();

  public WrpDw.StrFntOpt mStrFntDflt = new WrpDw.StrFntOpt();
  public WrpDw.StrFntOpt mStrFntSml = new WrpDw.StrFntOpt() { } ;
  public WrpDw.StrFntOpt mStrFntXSml = new WrpDw.StrFntOpt() { } ;
  public WrpDw.StrFntOpt mStrFntXSml2 = new WrpDw.StrFntOpt() { } ;
  public WrpDw.StrFntOpt mStrFntXSml3 = new WrpDw.StrFntOpt() { } ;
  public WrpDw.StrFntOpt mStrFntXSml4 = new WrpDw.StrFntOpt() { } ;
  public WrpDw.StrFntOpt mStrFntXSml5 = new WrpDw.StrFntOpt() { } ;
  public WrpDw.StrFntOpt[] mStrFntOptArr = { 
    this.mStrFntDflt, 
    this.mStrFntSml, 
    this.mStrFntXSml, 
    this.mStrFntXSml2, 
    this.mStrFntXSml3, 
    this.mStrFntXSml4, 
    this.mStrFntXSml5 };

  public MngLayout(AppLoop al)
  {
    this.mAL = al;
  }

  public void setOpt(MngOpt mngOpt)
  {
    this.mMngOpt = mngOpt;
  }

  public void rfrctOpt(Object obj)
  {
    this.mAL.mV.f2Dat(obj, this.mMngOpt);
    this.mMngOpt = null;
    Layout layout = (Layout)obj;

    this.mStrFntDflt = new WrpDw.StrFntOpt()
    {
    };
    this.mStrFntSml = new WrpDw.StrFntOpt()
    {
    };
    this.mStrFntXSml = new WrpDw.StrFntOpt()
    {
    };
    this.mStrFntXSml2 = new WrpDw.StrFntOpt()
    {
    };
    this.mStrFntXSml3 = new WrpDw.StrFntOpt()
    {
    };
    this.mStrFntXSml4 = new WrpDw.StrFntOpt()
    {
    };
    this.mStrFntXSml5 = new WrpDw.StrFntOpt()
    {
    };
    this.mStrFntOptArr = new WrpDw.StrFntOpt[] { 
      this.mStrFntDflt, 
      this.mStrFntSml, 
      this.mStrFntXSml, 
      this.mStrFntXSml2, 
      this.mStrFntXSml3, 
      this.mStrFntXSml4, 
      this.mStrFntXSml5 };

    this.l.lghtBox_fntSz = this.l.fntDfltSz;
  }

  public static String dir2sml(String srcPth)
  {
    String resStr = null;

    resStr = srcPth.replaceFirst("(.+?)/", "$1_sml/");

    return resStr;
  }

  public class Layout
  {
    public int specLow = 0;

    public int vwTyp = 0;

    public int fntDfltSz = 24;
    public int fntSmlSz = 18;
    public int fntXSmlSz = 12;

    public int btnHDflt = 42;
    public int btnHBold = 60;

    public int lghtBox_frmMrgn = 18;
    public int lghtBox_frmPddng = 10;
    public int lghtBox_fntSz = this.fntDfltSz;

    public int dlg_mrgnOut = 32;
    public int dlg_mrgnIn = 8;
    public int dlg_mrgnTtl = 16;
    public int dlg_btnH = this.btnHDflt;
    public int dlg_btnPgW = 60;

    public Layout()
    {
    }
  }
}