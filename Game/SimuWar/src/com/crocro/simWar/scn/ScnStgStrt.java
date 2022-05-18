package com.crocro.simWar.scn;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V;
import com.crocro.simWar.app.V.GD;
import com.crocro.simWar.clckbl.AnmTtl;
import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.mng.MngOpt;
import com.crocro.wrp.mng.MngScn;
import com.crocro.wrp.scn.Scn;
import com.crocro.wrp.utl.Rsrv;
import com.crocro.wrp.utl.UtlTool;
import java.util.ArrayList;

public class ScnStgStrt extends Scn
{
  public AppLoopSimWar mAL = null;
  private AnmTtl mAnmTtl;
  protected String mTtlStr;

  public ScnStgStrt(AppLoop al)
  {
    super(al);
  }

  public void init()
  {
    this.mAL = ((AppLoopSimWar)this.mmAL);
    super.init();
  }

  public void doFlw(int flw)
  {
    if (flw == V.FLW_INIT)
      flwInit();
  }

  public void doAlwys(int flw)
  {
    if (flw == V.FLW_WAIT)
      alwysWait();
  }

  public void flwInit()
  {
    initTtlStr();

    this.mAnmTtl = new AnmTtl(this.mAL, this.mTtlStr)
    {
      public void doAnmEnd()
      {
        this.mAL.mRsrvLst.add(new Rsrv(this.mAL, 0L) {
          public void addAct() {
            ScnStgStrt.this.mvScn();
          }
        });
      }
    };
    addClckbl(0, this.mAnmTtl);

    this.mAL.mMS.setNxtFlw(V.FLW_WAIT);
  }

  public void initTtlStr()
  {
    int stgNow = this.mAL.egV.gd.stgNow;

    StringBuilder sb = new StringBuilder();
    sb
      .append(UtlTool.smplFormat(this.mAL.mBsLngOpt.getS("stgNmBs"), new Object[] { Integer.valueOf(stgNow) }))
      .append("\n")
      .append(this.mAL.mBsLngOpt.getS("stgNm" + stgNow))
      .append("\n")
      .append(this.mAL.mBsLngOpt.getS("stgNmStrt"));

    this.mTtlStr = sb.toString();
  }

  public void mvScn()
  {
    this.mAL.mMS.setNxtScn(V.SCN_GM);
  }

  public void alwysWait()
  {
    doAlwysCmn();
  }

  public boolean addDoBck()
  {
    return true;
  }

  public void finish()
  {
    super.finish();

    this.mAL = null;
    if (this.mAnmTtl != null) { this.mAnmTtl.finish(); this.mAnmTtl = null;
    }
  }
}