package com.crocro.simWar.clckbl;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.clckbl.Clckbl;
import com.crocro.wrp.wrp.WrpDw;

public class AnmTtl extends Clckbl {

	private int mTtlSz;
	private String mTtlSAr[];
	private com.crocro.wrp.wrp.WrpDw.StrFntOpt mSFntO;
	private com.crocro.wrp.wrp.WrpDw.StrFrmOpt mSFrmO1;
	private com.crocro.wrp.wrp.WrpDw.StrFrmOpt mSFrmO2;
	private int mBgCol;
	private long mTmStrt;
	private long mTmElps;
	private boolean isAnmEnd;

	public AnmTtl(AppLoop al, String ttlStr) {
		super(al, 0, 0, al.mMC.getScrW(), al.mMC.getScrH(), 0, 0);
		mTmStrt = 0L;
		mTmElps = 0L;
		isAnmEnd = false;
		mSFntO = mAL.mML.mStrFntDflt.clone();
		mSFntO.fntSz = (mSFntO.fntSz * 3) / 2;
		mSFntO.lnH = (mSFntO.lnH * 3) / 2;
		mSFntO.r = 255;
		mSFntO.g = 255;
		mSFntO.b = 168;
		mSFrmO1 = new com.crocro.wrp.wrp.WrpDw.StrFrmOpt();
		mSFrmO1.sz = 2;
		mSFrmO1.r = 168;
		mSFrmO1.g = 168;
		mSFrmO1.b = 48;
		mSFrmO2 = new com.crocro.wrp.wrp.WrpDw.StrFrmOpt();
		mSFrmO2.sz = 4;
		mSFrmO2.r = 48;
		mSFrmO2.g = 48;
		mSFrmO2.b = 16;
		mBgCol = WrpDw.COL_BLACK;
		mTtlSAr = ttlStr.split("\n");
		mTtlSz = mTtlSAr.length;
		mTmStrt = System.currentTimeMillis();
	}

	public void doAlwys() {
		mTmElps = System.currentTimeMillis() - mTmStrt;
		if (isAnmEnd)
			return;
		if (mTmElps >= 4500L) {
			isAnmEnd = true;
			doAnmEnd();
		}
	}

	public void doAnmEnd() {
	}

	public void dwAlwys() {
		mWD.useCol(mBgCol);
		mWD.flRct(mX, mY, mW, mH);
		if (mTmElps >= 4500L)
			return;
		int bsH = (mSFntO.lnH * 3) / 2;
		int bsY = ((mH - bsH * mTtlSz) / 2 + (bsH - mSFntO.lnH) / 2)
				- mSFntO.lnH;
		int bsW = mW;
		int bsX = mX;
		int trns = 0;
		double tmRngStrt = 450D;
		double tmRngEnd = 4500D;
		if ((double) mTmElps >= tmRngStrt && (double) mTmElps < tmRngEnd) {
			double rate = ((double) mTmElps - tmRngStrt)
					/ (tmRngEnd - tmRngStrt);
			trns = (int) (100F * SIN[(int) (255D * rate)]);
		}
		mWD.setTrns(trns);
		for (int i = 0; i < mTtlSz; i++) {
			mWD.dwStr(mTtlSAr[i], bsX, bsY + i * bsH, bsW, mSFntO, mSFrmO2, 1);
			mWD.dwStr(mTtlSAr[i], bsX, bsY + i * bsH, bsW, mSFntO, mSFrmO1, 1);
		}

		mWD.setTrns(100);
	}

	public void finish() {
		super.finish();
		mTtlSAr = null;
		mSFntO = null;
		mSFrmO1 = null;
	}

}
