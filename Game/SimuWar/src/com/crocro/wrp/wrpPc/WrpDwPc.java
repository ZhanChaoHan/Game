package com.crocro.wrp.wrpPc;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.mng.MngLayout;
import com.crocro.wrp.utl.UtlCmnPc;
import com.crocro.wrp.utl.UtlDw;
import com.crocro.wrp.utl.UtlDw.StringPrm;
import com.crocro.wrp.utl.UtlTool;
import com.crocro.wrp.wrp.WrpDw;
import com.crocro.wrp.wrp.WrpDw.StrFntOpt;
import com.crocro.wrp.wrp.WrpDw.StrFrmOpt;
import com.crocro.wrp.wrp.WrpFile;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class WrpDwPc extends WrpDw {
	private AppLoop mAL = null;
	private static final String PTH_IMG = "res/img/";
	public ArrayList<Image> mBufArrLst = new ArrayList();
	public ArrayList<Graphics2D> mGrpArrLst = new ArrayList();
	private static ArrayList<Color> mColArrLst = new ArrayList();

	private Graphics2D mG = null;
	private Graphics2D mGNow = null;
	private Component mPrnt = null;
	private Insets mInsts = null;
	private WrpFile mWF = null;

	private double mRttRad = 0.0D;
	private double mRttX = 0.0D;
	private double mRttY = 0.0D;
	private boolean mUseCntr = false;

	private static final AffineTransform DEF_AT = new AffineTransform();

	private static final BasicStroke[] STRKS = { new BasicStroke(0.0F),
			new BasicStroke(1.0F), new BasicStroke(2.0F),
			new BasicStroke(3.0F), new BasicStroke(4.0F),
			new BasicStroke(5.0F), new BasicStroke(6.0F),
			new BasicStroke(7.0F), new BasicStroke(8.0F) };

	private Font mFntDflt = null;
	private Font mFntSml = null;
	private Font mFntXSml = null;

	private static double mAscntRate = 1.0D;
	private static double mDscntRate = 1.0D;

	public WrpDwPc(Component cmpnnt, Insets insts) {
		this.mPrnt = cmpnnt;
		this.mInsts = insts;

		if (mColArrLst.size() == 0) {
			for (int i = 0; i < COL_USER; i++) {
				int[] c = COL_ARR[i];
				mColArrLst.add(i, new Color(c[0], c[1], c[2]));
			}

		}

		int tstW = 60;
		int tstH = 120;
		int tstSz = 30;
		BufferedImage img = new BufferedImage(tstW, tstH, 1);
		Graphics2D g2d = (Graphics2D) img.getGraphics();
		Font fnt = new Font("Monospaced", 1, tstSz);
		g2d.setFont(fnt);

		FontMetrics fm = g2d.getFontMetrics(fnt);
		int ascnt = fm.getAscent();
		int dscnt = fm.getDescent();

		g2d.setColor(new Color(0, 0, 0));
		g2d.fillRect(0, 0, tstW, tstH);
		g2d.setColor(new Color(255, 255, 255));
		g2d.drawString("Bg", 0, ascnt);

		int frstFntY = -1;
		int lstFntY = -1;

		for (int y = 0; y < tstH; y++) {
			for (int x = 0; x < tstW; x++) {
				int c = img.getRGB(x, y) & 0xFFFFFF;
				if (c != 0) {
					if (frstFntY == -1)
						frstFntY = y;
					lstFntY = y;
				}

			}

		}

		mAscntRate = (ascnt - frstFntY) * 1.0D / ascnt;
		mDscntRate = (lstFntY - ascnt) * 1.0D / dscnt;

		g2d.dispose();
		g2d = null;
		img = null;
	}

	public void setAppLoop(AppLoop al) {
		this.mAL = al;
		this.mWF = this.mAL.mWF;
	}

	public void initBufSz(int w, int h) {
		mkBuf(0, w, h);
	}

	public WrpDw getNew() {
		WrpDw wd = new WrpDwPc(this.mPrnt, this.mInsts);
		wd.setAppLoop(this.mAL);
		return wd;
	}

	public void setGrphcs(Graphics g) {
		this.mG = ((Graphics2D) g);
		tgtBuf(0);
	}

	public void mkBuf(int id, int w, int h) {
		if (id < 0)
			return;

		if (id >= this.mBufArrLst.size()) {
			for (int i = this.mBufArrLst.size(); i <= id; i++)
				this.mBufArrLst.add(i, null);

		}

		BufferedImage img = new BufferedImage(w, h, 2);
		this.mBufArrLst.set(id, img);

		Graphics2D g2d = (Graphics2D) ((Image) this.mBufArrLst.get(id))
				.getGraphics();

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		if (id >= this.mGrpArrLst.size()) {
			for (int i = this.mGrpArrLst.size(); i <= id; i++)
				this.mGrpArrLst.add(i, null);
		}
		this.mGrpArrLst.set(id, g2d);
	}

	public void mkBuf(int id, String fNm) {
		if ((fNm == null) || (fNm.length() == 0))
			return;
		if (id < 0)
			return;

		Image img = ldImg(fNm);

		if (img == null) {
			img = ldMskImg(fNm);
		}

		if (img == null) {
			img = new BufferedImage(1, 1, 2);
		}

		if (id >= this.mBufArrLst.size()) {
			for (int i = this.mBufArrLst.size(); i <= id; i++)
				this.mBufArrLst.add(i, null);
		}
		this.mBufArrLst.set(id, img);

		if (id >= this.mGrpArrLst.size()) {
			for (int i = this.mGrpArrLst.size(); i <= id; i++)
				this.mGrpArrLst.add(i, null);
		}
		this.mGrpArrLst.set(id,
				(Graphics2D) ((Image) this.mBufArrLst.get(id)).getGraphics());
	}

	private Image ldImg(String fNm) {
		Image img = null;
		try {
			String pth = "res/img/" + fNm;
			InputStream is = null;
			try {
				is = this.mWF.getRIS(pth);
				img = ImageIO.read(is);
			} catch (Exception ex) {

				if (is != null)
					try {
						is.close();
						is = null;
					} catch (Exception localException2) {
					}
			} finally {
				if (is != null)
					try {
						is.close();
						is = null;
					} catch (Exception localException3) {
					}

			}

			MediaTracker tracker = new MediaTracker(this.mPrnt);
			tracker.addImage(img, 0);
			tracker.waitForAll();
		} catch (Exception e) {
			UtlCmnPc.prntExcp(e, "ldImg img : " + fNm);
		}

		return img;
	}

	private Image ldMskImg(String fNm) {
		Image img = null;

		if (fNm.endsWith(".png")) {
			String fNmBs = UtlTool.replOne(fNm, ".png", "");

			Image imgBs = ldImg(fNmBs + ".gif");
			if (imgBs == null)
				return img;

			int w = imgBs.getWidth(null);
			int h = imgBs.getHeight(null);
			int[] pxlsBs = new int[w * h];
			PixelGrabber pgBs = new PixelGrabber(imgBs, 0, 0, w, h, pxlsBs, 0,
					w);
			try {
				pgBs.grabPixels();
			} catch (Exception ex) {
			}
			if ((pgBs.getStatus() & 0x80) != 0)
				return img;
			imgBs.flush();
			imgBs = null;

			Image imgMsk = ldImg(fNmBs + "_msk.gif");
			if (imgMsk == null)
				return img;

			if ((w == imgMsk.getWidth(null)) && (h == imgMsk.getHeight(null))) {
				int[] pxlsMsk = new int[w * h];
				PixelGrabber pgMsk = new PixelGrabber(imgMsk, 0, 0, w, h,
						pxlsMsk, 0, w);
				try {
					pgMsk.grabPixels();
				} catch (Exception ex) {
				}
				if ((pgMsk.getStatus() & 0x80) != 0)
					return img;
				imgMsk.flush();
				imgMsk = null;

				int sz = w * h;
				for (int i = 0; i < sz; i++) {
					pxlsBs[i] = (pxlsBs[i] & 0xFFFFFF | (pxlsMsk[i] & 0xFF) << 24);
				}

				Image imgMem = this.mPrnt.createImage(new MemoryImageSource(w,
						h, pxlsBs, 0, w));

				img = new BufferedImage(w, h, 2);
				Graphics g = img.getGraphics();
				g.drawImage(imgMem, 0, 0, null);
				g.dispose();
				imgMem.flush();
				imgMem = null;
			}

		}

		return img;
	}

	public void tgtBuf(int id) {
		if (id == -1)
			this.mGNow = this.mG;
		else
			try {
				this.mGNow = ((Graphics2D) this.mGrpArrLst.get(id));
				if (this.mGNow == null)
					this.mGNow = this.mG;
			} catch (Exception e) {
				this.mGNow = this.mG;
			}
	}

	public void delBuf(int id) {
		if (id >= this.mBufArrLst.size())
			return;
		this.mBufArrLst.set(id, null);
		if (this.mGrpArrLst.get(id) != null)
			((Graphics2D) this.mGrpArrLst.get(id)).dispose();
		this.mGrpArrLst.set(id, null);
	}

	public void flshBuf(int id) {
		if (id < 0)
			return;
		for (int i = id; i < this.mBufArrLst.size(); i++)
			delBuf(i);
	}

	public void prcBuf(int id, int prcTyp) {
		if ((id < 0) || (id >= this.mBufArrLst.size()))
			return;

		Image img = (Image) this.mBufArrLst.get(id);

		int w = img.getWidth(this.mPrnt);
		int h = img.getHeight(this.mPrnt);

		Image imgNew = null;
		Graphics2D g2d = null;
		AffineTransform at = new AffineTransform();

		if (prcTyp == 1) {
			imgNew = new BufferedImage(h, w, 2);
			g2d = (Graphics2D) imgNew.getGraphics();
			at.translate(h, 0.0D);
			at.rotate(Math.toRadians(90.0D));
			g2d.drawImage(img, at, this.mPrnt);
		} else if (prcTyp == 2) {
			imgNew = new BufferedImage(w, h, 2);
			g2d = (Graphics2D) imgNew.getGraphics();
			at.rotate(Math.toRadians(180.0D), w / 2, h / 2);
			g2d.drawImage(img, at, this.mPrnt);
		} else if (prcTyp == 3) {
			imgNew = new BufferedImage(h, w, 2);
			g2d = (Graphics2D) imgNew.getGraphics();
			at.translate(0.0D, w);
			at.rotate(Math.toRadians(270.0D));
			g2d.drawImage(img, at, this.mPrnt);
		} else if (prcTyp == 5) {
			imgNew = new BufferedImage(w, h, 2);
			g2d = (Graphics2D) imgNew.getGraphics();
			at.scale(-1.0D, 1.0D);
			at.translate(-w, 0.0D);
			g2d.drawImage(img, at, this.mPrnt);
		} else if (prcTyp == 4) {
			imgNew = new BufferedImage(w, h, 2);
			g2d = (Graphics2D) imgNew.getGraphics();
			at.scale(1.0D, -1.0D);
			at.translate(0.0D, -h);
			g2d.drawImage(img, at, this.mPrnt);
		}

		if (imgNew != null) {
			this.mBufArrLst.set(id, imgNew);
			this.mGrpArrLst.set(id, g2d);
		}
	}

	public int getBufW(int id) {
		return ((Image) this.mBufArrLst.get(id)).getWidth(this.mPrnt);
	}

	public int getBufH(int id) {
		return ((Image) this.mBufArrLst.get(id)).getHeight(this.mPrnt);
	}

	public void bufOnceToRt() {
		this.mG.drawImage((Image) this.mBufArrLst.get(0), this.mInsts.left,
				this.mInsts.top, this.mPrnt);
	}

	public void bufAToB(int aId, int bId) {
		if ((aId < 0) && (this.mBufArrLst.size() <= aId))
			return;
		if ((bId < 0) && (this.mBufArrLst.size() <= bId))
			return;

		((Graphics2D) this.mGrpArrLst.get(bId)).drawImage(
				(Image) this.mBufArrLst.get(aId), 0, 0, this.mPrnt);
	}

	public int[][][] getRGBs(int id) {
		return getRGBs(id, 0, 0, getBufW(id), getBufH(id));
	}

	public int[][][] getRGB(int id, int x, int y) {
		return getRGBs(id, x, y, 1, 1);
	}

	public int[][][] getRGBs(int id, int x, int y, int w, int h) {
		int[][][] resPxlArr = new int[w][h][4];

		int[] pixels = new int[w * h];
		PixelGrabber pg = new PixelGrabber((Image) this.mBufArrLst.get(id), x,
				y, w, h, pixels, 0, w);
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
			UtlCmnPc.prntExcp(e, "getRGBs img : " + id + " x " + x + " y " + y
					+ " w " + w + " h " + h);
			return resPxlArr;
		}

		for (int pY = 0; pY < h; pY++) {
			for (int pX = 0; pX < w; pX++) {
				int pxl = pixels[(pY * w + pX)];
				resPxlArr[pX][pY][0] = (pxl >> 24 & 0xFF);
				resPxlArr[pX][pY][1] = (pxl >> 16 & 0xFF);
				resPxlArr[pX][pY][2] = (pxl >> 8 & 0xFF);
				resPxlArr[pX][pY][3] = (pxl & 0xFF);
			}

		}

		return resPxlArr;
	}

	public void mkCol(int id, int r, int g, int b) {
		if (id < COL_USER)
			return;
		if (id >= mColArrLst.size())
			mColArrLst.add(id, null);
		mColArrLst.set(id, new Color(r, g, b));
	}

	public void useCol(int id) {
		if ((id < 0) || (id >= mColArrLst.size()))
			return;
		try {
			this.mGNow.setColor((Color) mColArrLst.get(id));
		} catch (Exception ex) {
		}
	}

	public void setCol(int r, int g, int b) {
		try {
			this.mGNow.setColor(new Color(r, g, b));
		} catch (Exception ex) {
		}
	}

	public void setTrns(int per) {
		if (per < 0)
			per = 0;
		if (per > 100)
			per = 100;
		try {
			this.mGNow.setComposite(AlphaComposite.getInstance(3, 0.01F * per));
		} catch (Exception ex) {
		}
	}

	public void setStrk(int sz) {
		if (sz <= 0)
			return;
		try {
			if (sz < STRKS.length) {
				this.mGNow.setStroke(STRKS[sz]);
			} else
				this.mGNow.setStroke(new BasicStroke(sz));
		} catch (Exception ex) {
		}
	}

	public void setRtt(int deg) {
		if (deg == 0)
			this.mRttRad = 0.0D;
		else {
			this.mRttRad = (deg * 3.141592653589793D / 180.0D);
		}
		this.mRttX = 0.0D;
		this.mRttY = 0.0D;
	}

	public void setRtt(int deg, int x, int y) {
		setRtt(deg);
		this.mRttX = x;
		this.mRttY = y;
	}

	public void useCntr(boolean use) {
		this.mUseCntr = use;
	}

	private void doRtt(int x, int y, int w, int h) {
		if (this.mRttRad == 0.0D)
			return;
		if (this.mUseCntr)
			this.mGNow.rotate(this.mRttRad, this.mRttX + x + w / 2, this.mRttY
					+ y + h / 2);
		else
			this.mGNow.rotate(this.mRttRad, this.mRttX + x, this.mRttY + y);
	}

	private void bckRtt(int x, int y, int w, int h) {
		if (this.mRttRad == 0.0D)
			return;
		this.mGNow.setTransform(DEF_AT);
	}

	private int chkX(int x, int w) {
		if (this.mUseCntr)
			return x - w / 2;
		return x;
	}

	private int chkY(int y, int h) {
		if (this.mUseCntr)
			return y - h / 2;
		return y;
	}

	public void setClip(int x, int y, int w, int h) {
		this.mGNow.setClip(x, y, w, h);
	}

	public void clrClip() {
		this.mGNow.setClip(null);
	}

	public boolean chkBufId(int id) {
		return (id < 0) || (id >= this.mBufArrLst.size());
	}

	public void dwImg(int id, int dx, int dy) {
		dwImg(this, id, dx, dy);
	}

	public void dwImg(WrpDw wd, int id, int dx, int dy) {
		if (wd.chkBufId(id))
			return;
		try {
			int w = ((Image) ((WrpDwPc) wd).mBufArrLst.get(id))
					.getWidth(this.mPrnt);
			int h = ((Image) ((WrpDwPc) wd).mBufArrLst.get(id))
					.getHeight(this.mPrnt);
			dx = chkX(dx, w);
			dy = chkY(dy, h);
			doRtt(dx, dy, w, h);
			this.mGNow.drawImage((Image) ((WrpDwPc) wd).mBufArrLst.get(id), dx,
					dy, this.mPrnt);
			bckRtt(dx, dy, w, h);
		} catch (Exception ex) {
		}
	}

	public void dwImg(int id, int dx, int dy, int rx, int ry, int w, int h) {
		dwImg(this, id, dx, dy, rx, ry, w, h);
	}

	public void dwImg(WrpDw wd, int id, int dx, int dy, int rx, int ry, int w,
			int h) {
		if (wd.chkBufId(id))
			return;
		try {
			dx = chkX(dx, w);
			dy = chkY(dy, h);
			doRtt(dx, dy, w, h);
			this.mGNow.drawImage((Image) ((WrpDwPc) wd).mBufArrLst.get(id), dx,
					dy, dx + w, dy + h, rx, ry, rx + w, ry + h, this.mPrnt);

			bckRtt(dx, dy, w, h);
		} catch (Exception ex) {
		}
	}

	public void zmImg(int id, int dx, int dy, int dw, int dh) {
		zmImg(this, id, dx, dy, dw, dh);
	}

	public void zmImg(WrpDw wd, int id, int dx, int dy, int dw, int dh) {
		if (wd.chkBufId(id))
			return;
		try {
			Image img = (Image) ((WrpDwPc) wd).mBufArrLst.get(id);
			int w = img.getWidth(this.mPrnt);
			int h = img.getHeight(this.mPrnt);
			dx = chkX(dx, w);
			dy = chkY(dy, h);
			doRtt(dx, dy, w, h);
			this.mGNow.drawImage((Image) ((WrpDwPc) wd).mBufArrLst.get(id), dx,
					dy, dx + dw, dy + dh, 0, 0, img.getWidth(this.mPrnt),
					img.getHeight(this.mPrnt), this.mPrnt);

			bckRtt(dx, dy, w, h);
		} catch (Exception ex) {
		}
	}

	public void zmImg(int id, int dx, int dy, int dw, int dh, int rx, int ry,
			int rw, int rh) {
		zmImg(this, id, dx, dy, dw, dh, rx, ry, rw, rh);
	}

	public void zmImg(WrpDw wd, int id, int dx, int dy, int dw, int dh, int rx,
			int ry, int rw, int rh) {
		if (wd.chkBufId(id))
			return;
		try {
			dx = chkX(dx, dw);
			dy = chkY(dy, dh);
			doRtt(dx, dy, dw, dh);
			this.mGNow.drawImage((Image) ((WrpDwPc) wd).mBufArrLst.get(id), dx,
					dy, dx + dw, dy + dh, rx, ry, rx + rw, ry + rh, this.mPrnt);

			bckRtt(dx, dy, dw, dh);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void dwLn(int x0, int y0, int x1, int y1) {
		this.mGNow.drawLine(x0, y0, x1, y1);
	}

	public void dwRct(int x, int y, int w, int h) {
		x = chkX(x, w);
		y = chkY(y, h);
		doRtt(x, y, w, h);
		this.mGNow.drawRect(x, y, w, h);
		bckRtt(x, y, w, h);
	}

	public void flRct(int x, int y, int w, int h) {
		x = chkX(x, w);
		y = chkY(y, h);
		doRtt(x, y, w, h);
		this.mGNow.fillRect(x, y, w, h);
		bckRtt(x, y, w, h);
	}

	public void dwRRct(int x, int y, int w, int h, int rx, int ry) {
		x = chkX(x, w);
		y = chkY(y, h);
		doRtt(x, y, w, h);
		this.mGNow.drawRoundRect(x, y, w, h, rx * 2, ry * 2);
		bckRtt(x, y, w, h);
	}

	public void flRRct(int x, int y, int w, int h, int rx, int ry) {
		x = chkX(x, w);
		y = chkY(y, h);
		doRtt(x, y, w, h);
		this.mGNow.fillRoundRect(x, y, w, h, rx * 2, ry * 2);
		bckRtt(x, y, w, h);
	}

	public void dwOvl(int x, int y, int w, int h) {
		x = chkX(x, w);
		y = chkY(y, h);
		doRtt(x, y, w, h);
		this.mGNow.drawOval(x, y, w, h);
		bckRtt(x, y, w, h);
	}

	public void flOvl(int x, int y, int w, int h) {
		x = chkX(x, w);
		y = chkY(y, h);
		doRtt(x, y, w, h);
		this.mGNow.fillOval(x, y, w, h);
		bckRtt(x, y, w, h);
	}

	public void dwPly(int[] xPoints, int[] yPoints) {
		this.mGNow.drawPolyline(xPoints, yPoints, xPoints.length);
	}

	public void flPly(int[] xPoints, int[] yPoints) {
		this.mGNow.fillPolygon(xPoints, yPoints, xPoints.length);
	}

	private Font getFnt(int sz) {
		Font fnt = null;
		if (sz == this.mAL.mML.mStrFntDflt.fntSz) {
			if (this.mFntDflt == null)
				this.mFntDflt = new Font("Monospaced", 1, sz);
			fnt = this.mFntDflt;
		} else if (sz == this.mAL.mML.mStrFntSml.fntSz) {
			if (this.mFntSml == null)
				this.mFntSml = new Font("Monospaced", 1, sz);
			fnt = this.mFntSml;
		} else if (sz == this.mAL.mML.mStrFntXSml.fntSz) {
			if (this.mFntXSml == null)
				this.mFntXSml = new Font("Monospaced", 1, sz);
			fnt = this.mFntXSml;
		} else {
			fnt = new Font("Monospaced", 1, sz);
		}
		return fnt;
	}

	public void dwStr(String s, int x, int y, int w, WrpDw.StrFntOpt fntOpt,
			WrpDw.StrFrmOpt frmOpt) {
		dwStr(s, x, y, w, fntOpt, frmOpt, 0);
	}

	public void dwStr(String s, int x, int y, int w, WrpDw.StrFntOpt fntOpt,
			WrpDw.StrFrmOpt frmOpt, int strAlgn) {
		this.mGNow.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		Color tmpCol = this.mGNow.getColor();

		Font fnt = getFnt(fntOpt.fntSz);
		this.mGNow.setFont(fnt);
		FontMetrics fm = this.mGNow.getFontMetrics(fnt);

		int adjstH = (int) (fm.getAscent() * mAscntRate)
				+ (fntOpt.fntSz - (int) (fm.getAscent() * mAscntRate + fm
						.getDescent() * mDscntRate)) / 2
				+ (fntOpt.lnH - fntOpt.fntSz) / 2;

		int chrW = fm.stringWidth("a");
		int wChrW = fm.stringWidth("あ");
		int h = fntOpt.lnH;

		UtlDw.StringPrm[] dwStrArr = UtlDw.getStrWrp(s, chrW, wChrW, w);

		x = chkX(x, w);
		y = chkY(y, h);
		doRtt(x, y, w, h * dwStrArr.length);

		if (frmOpt != null) {
			Color frmCol = new Color(frmOpt.r, frmOpt.g, frmOpt.b);
			this.mGNow.setColor(frmCol);

			for (int i = 0; i < dwStrArr.length; i++) {
				for (int yPos = frmOpt.sz * -1; yPos <= frmOpt.sz; yPos++) {
					for (int xPos = frmOpt.sz * -1; xPos <= frmOpt.sz; xPos++) {
						UtlDw.StringPrm sp = dwStrArr[i];
						if (strAlgn == 1) {
							this.mGNow.drawString(sp.s, x + xPos
									+ (w - sp.thisW) / 2, y + yPos + adjstH + h
									* i);
						} else if (strAlgn == 2)
							this.mGNow
									.drawString(sp.s,
											x + xPos + (w - sp.thisW), y + yPos
													+ adjstH + h * i);
						else {
							this.mGNow.drawString(sp.s, x + xPos, y + yPos
									+ adjstH + h * i);
						}
					}
				}
			}

		}

		Color dwCol = new Color(fntOpt.r, fntOpt.g, fntOpt.b);
		this.mGNow.setColor(dwCol);

		for (int i = 0; i < dwStrArr.length; i++) {
			UtlDw.StringPrm sp = dwStrArr[i];
			if (strAlgn == 1) {
				this.mGNow.drawString(sp.s, x + (w - sp.thisW) / 2, y + adjstH
						+ h * i);
			} else if (strAlgn == 2)
				this.mGNow.drawString(sp.s, x + (w - sp.thisW), y + adjstH + h
						* i);
			else {
				this.mGNow.drawString(sp.s, x, y + adjstH + h * i);
			}

		}

		bckRtt(x, y, w, h);

		this.mGNow.setColor(tmpCol);

		this.mGNow.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
	}

	public void dwStrRaw(String s, int x, int y, int w, WrpDw.StrFntOpt fntOpt,
			WrpDw.StrFrmOpt frmOpt) {
		dwStr(s, x, y, w, fntOpt, frmOpt);
	}

	public void dwStrRaw(String s, int x, int y, int w, WrpDw.StrFntOpt fntOpt,
			WrpDw.StrFrmOpt frmOpt, int strAlgn) {
		dwStr(s, x, y, w, fntOpt, frmOpt, strAlgn);
	}

	public int getStrW(String s, WrpDw.StrFntOpt fntOpt, int maxW) {
		if (maxW < 0)
			return 0;

		int resW = 0;

		Font fnt = getFnt(fntOpt.fntSz);
		this.mGNow.setFont(fnt);
		FontMetrics fm = this.mGNow.getFontMetrics(fnt);
		int chrW = fm.stringWidth("a");
		int wChrW = fm.stringWidth("あ");

		UtlDw.StringPrm[] sArr = UtlDw.getStrWrp(s, chrW, wChrW, maxW);
		if (sArr.length >= 1) {
			resW = sArr[0].maxW;
		}

		return resW;
	}

	public int getStrH(String s, WrpDw.StrFntOpt fntOpt, int maxW) {
		int resH = 0;

		Font fnt = getFnt(fntOpt.fntSz);
		this.mGNow.setFont(fnt);
		FontMetrics fm = this.mGNow.getFontMetrics(fnt);
		int chrW = fm.stringWidth("a");
		int wChrW = fm.stringWidth("あ");

		UtlDw.StringPrm[] sArr = UtlDw.getStrWrp(s, chrW, wChrW, maxW);
		resH = sArr.length * fntOpt.lnH;

		return resH;
	}

	protected void finalize() throws Throwable {
		flshBuf(0);

		this.mBufArrLst.clear();
		this.mBufArrLst = null;

		this.mGrpArrLst.clear();
		this.mGrpArrLst = null;

		this.mG = null;
		this.mGNow = null;
		this.mPrnt = null;
		this.mInsts = null;
		this.mWF = null;
	}
}