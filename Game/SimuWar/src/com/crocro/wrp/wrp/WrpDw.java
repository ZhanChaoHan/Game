package com.crocro.wrp.wrp;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.bs.SF;

public abstract class WrpDw {
	public static final int BUF_RT = -1;
	public static final int BUF_ONCE = 0;
	public static final int BUF_MAIN = 1;
	public static final int BUF_USER = 2;
	public static final int PRC_BUF_RTT90 = 1;
	public static final int PRC_BUF_RTT180 = 2;
	public static final int PRC_BUF_RTT270 = 3;
	public static final int PRC_BUF_MRR_V = 4;
	public static final int PRC_BUF_MRR_H = 5;
	public static final int COL_BLACK = SF.seq(0);
	public static final int COL_WHITE = SF.seq();
	public static final int COL_GRAY = SF.seq();

	public static final int COL_RED = SF.seq();
	public static final int COL_YELLOW = SF.seq();
	public static final int COL_GREEN = SF.seq();
	public static final int COL_AQUA = SF.seq();
	public static final int COL_BLUE = SF.seq();
	public static final int COL_PURPLE = SF.seq();

	public static final int COL_DARK_GRAY = SF.seq();
	public static final int COL_DARK_RED = SF.seq();
	public static final int COL_DARK_YELLOW = SF.seq();
	public static final int COL_DARK_GREEN = SF.seq();
	public static final int COL_DARK_AQUA = SF.seq();
	public static final int COL_DARK_BLUE = SF.seq();
	public static final int COL_DARK_PURPLE = SF.seq();

	public static final int COL_LGHT_GRAY = SF.seq();
	public static final int COL_LGHT_RED = SF.seq();
	public static final int COL_LGHT_YELLOW = SF.seq();
	public static final int COL_LGHT_GREEN = SF.seq();
	public static final int COL_LGHT_AQUA = SF.seq();
	public static final int COL_LGHT_BLUE = SF.seq();
	public static final int COL_LGHT_PURPLE = SF.seq();

	public static final int COL_SKY_BLUE = SF.seq();
	public static final int COL_THM_BS = SF.seq();
	public static final int COL_BTN_BFR = SF.seq();
	public static final int COL_BTN_BFR_LGHT = SF.seq();
	public static final int COL_BTN_BFR_DARK = SF.seq();
	public static final int COL_BTN_AFTR = SF.seq();
	public static final int COL_BTN_AFTR_LGHT = SF.seq();
	public static final int COL_BTN_AFTR_DARK = SF.seq();
	public static final int COL_USER = SF.seq();

	public static final int COL_ARR[][] = { new int[3], { 255, 255, 255 },
			{ 128, 128, 128 }, { 255, 0, 0 }, { 255, 255, 0 }, { 0, 255, 0 },
			{ 0, 255, 255 }, { 0, 0, 255 }, { 255, 0, 255 }, { 96, 96, 96 },
			{ 96, 0, 0 }, { 96, 96, 0 }, { 0, 96, 0 }, { 0, 96, 96 },
			{ 0, 0, 96 }, { 96, 0, 96 }, { 192, 192, 192 }, { 255, 128, 128 },
			{ 255, 255, 128 }, { 128, 255, 128 }, { 128, 255, 255 },
			{ 128, 128, 255 }, { 255, 128, 255 }, { 128, 192, 255 },
			{ 40, 136, 218 }, { 255, 144, 31 }, { 251, 207, 162 },
			{ 192, 97, 0 }, { 251, 207, 162 }, { 251, 233, 216 },
			{ 231, 167, 104 } };
	public static final int STR_MAX_W_DFLT = 999;
	public static final StrFrmOpt STR_FRM_DFLT = new StrFrmOpt() {
	};
	public static final int STR_ALGN_L = 0;
	public static final int STR_ALGN_C = 1;
	public static final int STR_ALGN_R = 2;
	public static final int PXL_A = 0;
	public static final int PXL_R = 1;
	public static final int PXL_G = 2;
	public static final int PXL_B = 3;
	public static final int PXL_MAX = 4;

	public abstract void setAppLoop(AppLoop paramAppLoop);

	public abstract void initBufSz(int paramInt1, int paramInt2);

	public abstract WrpDw getNew();

	public abstract void mkBuf(int paramInt1, int paramInt2, int paramInt3);

	public abstract void mkBuf(int paramInt, String paramString);

	public abstract void tgtBuf(int paramInt);

	public abstract void delBuf(int paramInt);

	public abstract void flshBuf(int paramInt);

	public abstract int getBufW(int paramInt);

	public abstract int getBufH(int paramInt);

	public abstract void prcBuf(int paramInt1, int paramInt2);

	public abstract void bufOnceToRt();

	public abstract void bufAToB(int paramInt1, int paramInt2);

	public abstract int[][][] getRGBs(int paramInt);

	public abstract int[][][] getRGB(int paramInt1, int paramInt2, int paramInt3);

	public abstract int[][][] getRGBs(int paramInt1, int paramInt2,
			int paramInt3, int paramInt4, int paramInt5);

	public abstract void mkCol(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4);

	public abstract void useCol(int paramInt);

	public abstract void setCol(int paramInt1, int paramInt2, int paramInt3);

	public abstract void setTrns(int paramInt);

	public abstract void setStrk(int paramInt);

	public abstract void setRtt(int paramInt);

	public abstract void setRtt(int paramInt1, int paramInt2, int paramInt3);

	public abstract void useCntr(boolean paramBoolean);

	public abstract void setClip(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4);

	public abstract void clrClip();

	public abstract boolean chkBufId(int paramInt);

	public abstract void dwImg(WrpDw paramWrpDw, int paramInt1, int paramInt2,
			int paramInt3);

	public abstract void dwImg(WrpDw paramWrpDw, int paramInt1, int paramInt2,
			int paramInt3, int paramInt4, int paramInt5, int paramInt6,
			int paramInt7);

	public abstract void zmImg(WrpDw paramWrpDw, int paramInt1, int paramInt2,
			int paramInt3, int paramInt4, int paramInt5);

	public abstract void zmImg(WrpDw paramWrpDw, int paramInt1, int paramInt2,
			int paramInt3, int paramInt4, int paramInt5, int paramInt6,
			int paramInt7, int paramInt8, int paramInt9);

	public abstract void dwImg(int paramInt1, int paramInt2, int paramInt3);

	public abstract void dwImg(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4, int paramInt5, int paramInt6, int paramInt7);

	public abstract void zmImg(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4, int paramInt5);

	public abstract void zmImg(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4, int paramInt5, int paramInt6, int paramInt7,
			int paramInt8, int paramInt9);

	public abstract void dwLn(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4);

	public abstract void dwRct(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4);

	public abstract void flRct(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4);

	public abstract void dwRRct(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4, int paramInt5, int paramInt6);

	public abstract void flRRct(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4, int paramInt5, int paramInt6);

	public abstract void dwOvl(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4);

	public abstract void flOvl(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4);

	public abstract void dwPly(int[] paramArrayOfInt1, int[] paramArrayOfInt2);

	public abstract void flPly(int[] paramArrayOfInt1, int[] paramArrayOfInt2);

	public abstract void dwStr(String paramString, int paramInt1,
			int paramInt2, int paramInt3, StrFntOpt paramStrFntOpt,
			StrFrmOpt paramStrFrmOpt);

	public abstract void dwStrRaw(String paramString, int paramInt1,
			int paramInt2, int paramInt3, StrFntOpt paramStrFntOpt,
			StrFrmOpt paramStrFrmOpt);

	public abstract void dwStr(String paramString, int paramInt1,
			int paramInt2, int paramInt3, StrFntOpt paramStrFntOpt,
			StrFrmOpt paramStrFrmOpt, int paramInt4);

	public abstract void dwStrRaw(String paramString, int paramInt1,
			int paramInt2, int paramInt3, StrFntOpt paramStrFntOpt,
			StrFrmOpt paramStrFrmOpt, int paramInt4);

	public abstract int getStrW(String paramString, StrFntOpt paramStrFntOpt,
			int paramInt);

	public abstract int getStrH(String paramString, StrFntOpt paramStrFntOpt,
			int paramInt);

	public static class StrFntOpt {
		public int fntSz = 24;
		public int r = 0;
		public int g = 0;
		public int b = 0;
		public int w = 0;
		public int lnH = 24;

		public StrFntOpt clone() {
			StrFntOpt res = new StrFntOpt();
			res.fntSz = this.fntSz;
			res.r = this.r;
			res.g = this.g;
			res.b = this.b;
			res.w = this.w;
			res.lnH = this.lnH;
			return res;
		}
	}

	public static class StrFrmOpt {
		public int r = 0;
		public int g = 0;
		public int b = 0;
		public int sz = 0;

		public StrFrmOpt clone() {
			StrFrmOpt res = new StrFrmOpt();
			res.r = this.r;
			res.g = this.g;
			res.b = this.b;
			res.sz = this.sz;
			return res;
		}
	}
}