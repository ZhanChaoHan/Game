package com.crocro.wrp.dvc.pc;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.app.V;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.mng.MngLayout;
import com.crocro.wrp.mng.MngOpt;
import com.crocro.wrp.utl.UtlCmnPc;
import com.crocro.wrp.wrpPc.WrpDwPc;
import com.crocro.wrp.wrpPc.WrpEnvPc;
import com.crocro.wrp.wrpPc.WrpFilePc;
import com.crocro.wrp.wrpPc.WrpNetPc;
import com.crocro.wrp.wrpPc.WrpSndPc;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

public class Frm extends Frame
  implements Runnable
{
  private static final long serialVersionUID = 1L;
  private static String mPthInitDat = "./res/init.dat";
  private static final int FRM_W = 800;
  private static final int FRM_H = 600;
  private static final Color COL_BG = new Color(255, 255, 255);
  private static final Color COL_FG = new Color(0, 0, 0);

  private Font mFnt = new Font("Dialog", 1, 16);
  private Insets mInsts;
  public AppLoop mAL;
  private MngCnvs mMC;
  public WrpFilePc mWF;
  public WrpDwPc mWD;
  public WrpSndPc mWS;
  public WrpEnvPc mWE;
  public WrpNetPc mWN;
  private boolean mThrdExst = true;
  private boolean mLckStrt = true;

  WindowListener gWinLsnr = new WindowListener() {
    public void windowActivated(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
      System.exit(0);
    }

    public void windowDeactivated(WindowEvent e)
    {
    }

    public void windowDeiconified(WindowEvent e)
    {
    }

    public void windowIconified(WindowEvent e)
    {
    }

    public void windowOpened(WindowEvent e)
    {
    }
  };

  MouseListener gMouseListener = new MouseListener()
  {
    public void mouseClicked(MouseEvent arg0)
    {
      if (SwingUtilities.isRightMouseButton(arg0))
        Frm.this.mMC.setMMn();
    }

    public void mouseEntered(MouseEvent arg0)
    {
    }

    public void mouseExited(MouseEvent arg0)
    {
      if (Frm.this.mMC != null) {
        Frm.this.mMC.setMX(-999);
        Frm.this.mMC.setMY(-999);
        Frm.this.mMC.setMUp();
      }
    }

    public void mousePressed(MouseEvent arg0)
    {
      if (arg0.getButton() != 1) return;
      if (Frm.this.mMC != null) {
        Frm.this.mMC.setMX(arg0.getX() - Frm.this.mInsts.left);
        Frm.this.mMC.setMY(arg0.getY() - Frm.this.mInsts.top);
        Frm.this.mMC.setMDn();
      }
    }

    public void mouseReleased(MouseEvent arg0)
    {
      if (arg0.getButton() != 1) return;
      if (Frm.this.mMC != null) {
        Frm.this.mMC.setMX(arg0.getX() - Frm.this.mInsts.left);
        Frm.this.mMC.setMY(arg0.getY() - Frm.this.mInsts.top);
        Frm.this.mMC.setMUp();
      }
    }
  };

  MouseMotionListener gMouseMotionListener = new MouseMotionListener()
  {
    public void mouseDragged(MouseEvent arg0)
    {
      if (Frm.this.mMC != null) {
        Frm.this.mMC.setMX(arg0.getX() - Frm.this.mInsts.left);
        Frm.this.mMC.setMY(arg0.getY() - Frm.this.mInsts.top);
      }
    }

    public void mouseMoved(MouseEvent arg0)
    {
      if (Frm.this.mMC != null) {
        Frm.this.mMC.setMX(arg0.getX() - Frm.this.mInsts.left);
        Frm.this.mMC.setMY(arg0.getY() - Frm.this.mInsts.top);
      }
    }
  };

  KeyAdapter gKeyAdapter = new KeyAdapter() {
    public void keyReleased(KeyEvent e) {
      int code = e.getKeyCode();
      switch (code) {
      case 8:
      case 27:
        Frm.this.mMC.setMBck();
        break;
      case 17:
      case 525:
        Frm.this.mMC.setMMn();
      }
    }
  };

  public Frm()
  {
  }

  public Frm(String pthInitDat)
  {
    File f = new File(pthInitDat);
    if ((f.exists()) && (f.isFile()))
      mPthInitDat = pthInitDat;
  }

  public void initApp(AppLoop al)
  {
    this.mAL = al;

    setResizable(false);
    setSize(800, 600);
    setLocation(-810, 0);
    setVisible(true);
    this.mInsts = getInsets();

    setTitle(this.mAL.mAppTtl);
    setBackground(COL_BG);
    setForeground(COL_FG);
    setFont(this.mFnt);

    chngScrSz(800, 600);

    addWindowListener(this.gWinLsnr);
    addMouseListener(this.gMouseListener);
    addMouseMotionListener(this.gMouseMotionListener);
    addKeyListener(this.gKeyAdapter);

    this.mMC = new MngCnvs();
    this.mMC.setScrW(800);
    this.mMC.setScrH(600);

    this.mWF = new WrpFilePc();
    this.mWD = new WrpDwPc(this, this.mInsts);
    this.mWS = new WrpSndPc();
    this.mWE = new WrpEnvPc(this);
    this.mWN = new WrpNetPc();

    this.mAL.setMngCnvs(this.mMC);
    this.mAL.setWrpFile(this.mWF);
    this.mAL.setWrpDw(this.mWD);
    this.mAL.setWrpSnd(this.mWS);
    this.mAL.setWrpEnv(this.mWE);
    this.mAL.setWrpNet(this.mWN);

    this.mWD.setAppLoop(this.mAL);
    this.mWS.setAppLoop(this.mAL);
    this.mWF.setAppLoop(this.mAL);
    this.mWN.setAppLoop(this.mAL);

    this.mMC.mAL = this.mAL;

    MngOpt mo = new MngOpt(this.mAL, mPthInitDat, true);

    int resSz = mo.getI("resSz");
    if (resSz > 0) {
      this.mWF.mResPck = new String[resSz];
      this.mWF.mResRt = new String[resSz];
      for (int i = 0; i < resSz; i++) {
        String resFNm = mo.getS("res" + i + ".FNm");
        String resRt = mo.getS("res" + i + ".Rt");
        if (resFNm.length() > 0) this.mWF.mResPck[i] = resFNm;
        if (resRt.length() <= 0) continue; this.mWF.mResRt[i] = resRt;
      }

    }

    int scrW = mo.getI("scrW");
    int scrH = mo.getI("scrH");
    Dimension scrSz = Toolkit.getDefaultToolkit().getScreenSize();

    if ((scrW > 0) && (scrW <= scrSz.width) && 
      (scrH > 0) && (scrH <= scrSz.height) && (
      (scrW != 800) || (scrH != 600)))
    {
      chngScrSz(scrW, scrH);
      this.mMC.setScrW(scrW);
      this.mMC.setScrH(scrH);
    }
    this.mWD.initBufSz(getWidth(), getHeight());

    int sndVol = mo.getI("sndVol");
    if ((sndVol >= 0) && (sndVol <= 100))
    {
      this.mWS.chngVol(sndVol);
      this.mAL.mV.gd.sndVol = sndVol;
    }

    String lngKey = mo.getS("lngKey");
    if (lngKey.length() > 0) {
      this.mWF.setLngKey(lngKey);
    }

    String dirBs = mo.getS("dirBs");
    if (dirBs.length() > 0) {
      this.mWF.setDirBs(dirBs);
    }

    String layoutFNm = mo.getS("layoutFNm");
    MngOpt mol = new MngOpt(this.mAL, layoutFNm, true);
    this.mAL.mML.setOpt(mol);

    mo.finish();
    mo = null;

    InputStream is = this.mWF.getRIS("res/img/cmn/icn32.png");
    try {
      Image icnImg = ImageIO.read(is);
      setIconImage(icnImg);
    } catch (Exception e) {
    	e.printStackTrace();
      UtlCmnPc.prntExcp(e, "icon read error");

      if (is != null) try { is.close(); is = null; } catch (Exception ex) {
    	  ex.printStackTrace();
        }  } finally {
      if (is != null) try { is.close(); is = null;
        } catch (Exception e)
        {
        	e.printStackTrace();
        } 
    }
    this.mLckStrt = false;
  }

  public void chngScrSz(int w, int h)
  {
    setVisible(false);

    int wSum = w + this.mInsts.left + this.mInsts.right;
    int hSum = h + this.mInsts.top + this.mInsts.bottom;

    Dimension scrSz = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation((scrSz.width - wSum) / 2, (scrSz.height - hSum) / 2);
    setSize(wSum, hSum);

    setVisible(true);
  }

  public void stop()
  {
    this.mThrdExst = false;
  }

  public void run()
  {
    this.mAL.strt();

    while (this.mThrdExst)
    {
      if (!this.mAL.mngWt()) {
        repaint();
        this.mAL.mngWtPst();
      }
    }
  }

  public void update(Graphics g)
  {
    paint(g);
  }

  public void paint(Graphics g)
  {
    if (this.mLckStrt) return;

    if (this.mAL.chkUpdt()) return;
    this.mAL.strtUpdt();
    this.mWD.setGrphcs(g);
    this.mAL.paint();
    this.mAL.endUpdt();
  }
}