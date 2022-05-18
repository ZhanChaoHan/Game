package com.crocro.wrp.mng;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.bs.F;
import java.util.ArrayList;

public class MngCnvs
  implements F
{
  public static final int MX_OUT = -999;
  public static final int MY_OUT = -999;
  private int mScrW = 0;
  private int mScrH = 0;

  public int mOrgnX = 0;
  public int mOrgnY = 0;

  private boolean mLckSet = false;

  private int mMXTmp = -999;
  private int mMYTmp = -999;
  private int mMXNow = -999;
  private int mMYNow = -999;
  private int mMXOld = -999;
  private int mMYOld = -999;

  private boolean mMDnTmp = false;
  private boolean mMUpTmp = false;
  private boolean mMDn = false;
  private boolean mMUp = false;
  private boolean mMDrg = false;

  private ArrayList<Clck> mClcLst = new ArrayList();
  public static final int CLCK_NOT = -999;
  private boolean mMBck = false;
  public boolean mBck = false;

  private boolean mMMn = false;
  public boolean mMn = false;
  public AppLoop mAL;

  public int getScrW()
  {
    return this.mScrW; } 
  public int getScrH() { return this.mScrH; } 
  public int getScrMax() {
    return this.mScrW > this.mScrH ? this.mScrW : this.mScrH; } 
  public int getScrMin() { return this.mScrW < this.mScrH ? this.mScrW : this.mScrH; }

  public void setScrW(int w) {
    this.mScrW = w; } 
  public void setScrH(int h) { this.mScrH = h;
  }

  public boolean update()
  {
    if (this.mLckSet) return false;

    updateMouse();

    this.mMDn = this.mMDnTmp;
    this.mMUp = this.mMUpTmp;
    this.mMDnTmp = false;
    this.mMUpTmp = false;

    if (this.mMDn) this.mMDrg = true;
    if ((!this.mMDrg) && (this.mMUp)) this.mMUp = false;
    if (this.mMUp) this.mMDrg = false;

    if ((this.mMBck) && (!this.mBck))
    {
      this.mBck = true;
      this.mMBck = false;
    }

    if ((this.mMMn) && (!this.mMn)) {
      this.mMn = true;
      this.mMMn = false;
    }

    return true;
  }

  public void updateMouse()
  {
    this.mMXOld = this.mMXNow;
    this.mMYOld = this.mMYNow;
    this.mMXNow = this.mMXTmp;
    this.mMYNow = this.mMYTmp;
  }

  public int getMX() {
    return this.mMXNow; } 
  public int getMY() { return this.mMYNow; } 
  public int getMXOld() { return this.mMXOld; } 
  public int getMYOld() { return this.mMYOld; }

  public void setMX(int x) {
    this.mLckSet = true; this.mMXTmp = x; this.mLckSet = false; } 
  public void setMY(int y) { this.mLckSet = true; this.mMYTmp = y; this.mLckSet = false; }

  public boolean getMDn() {
    return this.mMDn; } 
  public boolean getMUp() { return this.mMUp; } 
  public boolean getMDrg() { return this.mMDrg; }

  public void setMDn()
  {
    this.mLckSet = true;
    if (!this.mMUpTmp) {
      this.mMDnTmp = true;
      updateMouse();

      synchronized (this.mClcLst) {
        Clck clck = new Clck(this.mMXNow, this.mMYNow);
        this.mClcLst.add(clck);
      }
    }
    this.mLckSet = false;
  }
  public void setMUp() {
    this.mLckSet = true;
    this.mMUpTmp = true;
    updateMouse();
    this.mLckSet = false;

    synchronized (this.mClcLst) {
      if (this.mClcLst.size() >= 1) {
        Clck clck = (Clck)this.mClcLst.get(this.mClcLst.size() - 1);
        clck.setOut(this.mMXNow, this.mMYNow);
      }
    }
  }

  public void setMBck() {
    this.mLckSet = true;
    if (!this.mBck) this.mMBck = true;
    this.mLckSet = false;
  }
  public void setMMn() {
    this.mLckSet = true;
    if (!this.mMn) this.mMMn = true;
    this.mLckSet = false;
  }

  public void rstOrgn()
  {
    this.mOrgnX = 0;
    this.mOrgnY = 0;
  }

  public void rstClckLst()
  {
    synchronized (this.mClcLst) {
      this.mClcLst.clear();
    }
  }

  public Clck getTopClck()
  {
    synchronized (this.mClcLst) {
      if (this.mClcLst.size() == 0) return null;
      return (Clck)this.mClcLst.get(0);
    }
  }

  public void rmvTopClck()
  {
    synchronized (this.mClcLst) {
      if (this.mClcLst.size() == 0) return;
      this.mClcLst.remove(0);
    }
  }

  public class Clck
  {
    public int inX = -999;
    public int inY = -999;
    public int outX = -999;
    public int outY = -999;

    public Clck(int inX_, int inY_) { this.inX = inX_;
      this.inY = inY_; }

    public void setOut(int outX_, int outY_) {
      this.outX = outX_;
      this.outY = outY_;
    }
    public boolean chkFnsh() {
      if (this.outX == -999) return false;

      if (this.outX < 0) this.outX = 0;
      if (this.outY < 0) this.outY = 0;
      if (this.outX >= MngCnvs.this.mScrW) this.outX = (MngCnvs.this.mScrW - 1);
      if (this.outY >= MngCnvs.this.mScrH) this.outY = (MngCnvs.this.mScrH - 1);

      return true;
    }
  }
}