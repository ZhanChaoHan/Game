package com.crocro.wrp.clckbl;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.scn.MngDlg;
import com.crocro.wrp.wrp.WrpDw;

public class DlgMnSldBar extends DlgMnItm
{
  public static int BAR_W_MRGN = 12;
  public static int BAR_H_MRGN = 6;
  public String mSldTtl;
  public String mSldTtlTail = null;

  public int mSldMin = 0;
  public int mSldMax = 0;
  public int mSldNow = 0;

  public DlgMnSldBar(AppLoop al, MngDlg mngDlg, int h, String ttlStr, int id, int sldMin, int sldMax, int sldNow)
  {
    super(al, mngDlg, h, ttlStr, id);

    this.mClckTyp = 2;
    this.mSldMin = sldMin;
    this.mSldMax = sldMax;
    this.mSldNow = sldNow;
    this.mSldTtl = ttlStr;
  }

  public void dwAlwys()
  {
    dwBfrBtnBs();

    int x = this.mX + BAR_W_MRGN * 2;
    int y = this.mY + BAR_H_MRGN * 2;
    int w = this.mW - BAR_W_MRGN * 4;
    int h = this.mH - BAR_H_MRGN * 4;

    this.mWD.useCol(WrpDw.COL_DARK_GRAY);
    this.mWD.flRct(x, y, w, h);

    x += 3;
    y += 3;
    w -= 6;
    h -= 6;

    this.mWD.useCol(WrpDw.COL_BTN_BFR_DARK);
    this.mWD.flRct(x, y, w, h);

    double barRt = 0.0D;
    if (this.mSldMax > this.mSldMin) barRt = (this.mSldNow - this.mSldMin) * 1.0D / (this.mSldMax - this.mSldMin);
    this.mWD.useCol(WrpDw.COL_BTN_BFR_LGHT);
    this.mWD.flRct(x, y, (int)(w * barRt), h);

    this.mTtlStr = (this.mSldTtl + this.mSldNow);
    if (this.mSldTtlTail != null) this.mTtlStr += this.mSldTtlTail;
    this.mTtlW = this.mWD.getStrW(this.mTtlStr, this.mStrFntOpt, 999);

    this.mWD.useCol(WrpDw.COL_BLACK);
    x = this.mX + (this.mW - this.mTtlW) / 2;
    y = this.mY + (this.mH - this.mTtlH) / 2;
    this.mWD.useCol(WrpDw.COL_BLACK);
    this.mWD.dwStr(this.mTtlStr, x, y, this.mAL.mMC.getScrW(), this.mStrFntOpt, WrpDw.STR_FRM_DFLT);
  }

  public void setDrgPos(int x, int y)
  {
    super.setDrgPos(x, y);

    int xRng = this.mX + BAR_W_MRGN * 2;
    int wRng = this.mW - BAR_W_MRGN * 4;

    if (this.mDrgX < xRng) {
      this.mSldNow = this.mSldMin;
    }
    else if (this.mDrgX >= xRng + wRng) {
      this.mSldNow = this.mSldMax;
    } else {
      int xPos = this.mDrgX - xRng;
      this.mSldNow = (this.mSldMin + (this.mSldMax - this.mSldMin) * xPos / wRng);
    }
  }

  public void actDrp()
  {
    this.mTmEf = this.mAL.mTmNow;
    addActDrp();
  }

  public void addActDrp()
  {
  }
}