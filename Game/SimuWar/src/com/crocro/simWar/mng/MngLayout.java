package com.crocro.simWar.mng;

import com.crocro.wrp.app.AppLoop;

public class MngLayout extends com.crocro.wrp.mng.MngLayout
{
  public Layout l = new Layout();

  public MngLayout(AppLoop al)
  {
    super(al);
    l = new Layout();
    super.l = l;
  }

  public class Layout extends com.crocro.wrp.mng.MngLayout.Layout
  {
    public int gmBtnOutMrgn = 8;
    public int gmBtnOutMrgnL = 8;

    public int mapAllWMax = 80;
    public int mapAllHMax = 120;

    public int vwActLog = 1;

    public int rtClcParFrm = 4;
    public int rtClcFrmMin = 16;

    public Layout()
    {
      super();
    }
  }
}