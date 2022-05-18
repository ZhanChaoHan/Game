// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Tama.java

package applet;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;

// Referenced classes of package applet:
//            Tama, Fq_1

class YaManeger extends Applet {

	public YaManeger(Applet applet) {
		TamaKazu = 0;
		tama = new Tama[64];
		this.applet = applet;
		fq_1 = (Fq_1) applet;
		for (int i = 0; i < 64; i++)
			tama[i] = new Tama(applet, this);

	}

	public boolean Ya_SetOne(int fc, int chn, int hito, int aite, int x, int y,
			int dir, int pow, int zokuj, int item) {
		if (x < fq_1.MXS || y < fq_1.MYS || x >= fq_1.MXLM || y >= fq_1.MYLM)
			return false;
		for (int i = 0; i < 64; i++)
			if (tama[i].FC == 0) {
				tama[i].Tama_Init(fc, chn, hito, aite, x, y, dir, pow, zokuj,
						item);
				return true;
			}

		return false;
	}

	public void Ya_Init(int chn, int hito, int aite, int x, int y, int dir,
			int zokuj, int pow, int item, int FL) {
		int fc = 20;
		boolean bb = false;
		switch (chn) {
		case 0: // '\0'
		default:
			break;

		case 1: // '\001'
			fc = 24;
			pow += 10;
			switch (dir & 3) {
			case 0: // '\0'
				bb = Ya_SetOne(fc, chn, hito, aite, x + 1, y - 1, dir, pow,
						zokuj, item);
				break;

			case 1: // '\001'
				bb = Ya_SetOne(fc, chn, hito, aite, x - 1, y + 1, dir, pow,
						zokuj, item);
				break;

			case 2: // '\002'
				bb = Ya_SetOne(fc, chn, hito, aite, x, y + 2, dir, pow, zokuj,
						item);
				break;

			case 3: // '\003'
				bb = Ya_SetOne(fc, chn, hito, aite, x + 2, y + 1, dir, pow,
						zokuj, item);
				break;
			}
			if (bb && (FL & 0x80) != 0)
				fq_1.EffectSound[9].play();
			break;

		case 2: // '\002'
			fc = 100;
			pow = 0;
			bb = Ya_SetOne(fc, chn, hito, aite, x, y, dir, pow, zokuj, item);
			if (bb && (FL & 0x80) != 0)
				fq_1.EffectSound[6].play();
			break;

		case 3: // '\003'
			fc = 15;
			pow += 10;
			switch (dir & 3) {
			case 0: // '\0'
				bb = Ya_SetOne(fc, chn, hito, aite, x, y - 1, dir, pow, zokuj,
						item);
				break;

			case 1: // '\001'
				bb = Ya_SetOne(fc, chn, hito, aite, x - 1, y, dir, pow, zokuj,
						item);
				break;

			case 2: // '\002'
				bb = Ya_SetOne(fc, chn, hito, aite, x, y + 2, dir, pow, zokuj,
						item);
				break;

			case 3: // '\003'
				bb = Ya_SetOne(fc, chn, hito, aite, x + 2, y, dir, pow, zokuj,
						item);
				break;
			}
			if (bb && (FL & 0x80) != 0)
				fq_1.EffectSound[9].play();
			break;

		case 4: // '\004'
			fc = 30;
			switch (dir & 3) {
			case 0: // '\0'
				bb = Ya_SetOne(fc, chn, hito, aite, x, y - 1, dir, pow, zokuj,
						item);
				break;

			case 1: // '\001'
				bb = Ya_SetOne(fc, chn, hito, aite, x - 1, y, dir, pow, zokuj,
						item);
				break;

			case 2: // '\002'
				bb = Ya_SetOne(fc, chn, hito, aite, x, y + 2, dir, pow, zokuj,
						item);
				break;

			case 3: // '\003'
				bb = Ya_SetOne(fc, chn, hito, aite, x + 2, y, dir, pow, zokuj,
						item);
				break;
			}
			if (bb && (FL & 0x80) != 0)
				fq_1.EffectSound[9].play();
			break;

		case 5: // '\005'
			fc = 1000;
			pow = 100;
			bb = Ya_SetOne(fc, chn, hito, aite, x, y, dir, pow, zokuj, item);
			if (bb && (FL & 0x80) != 0)
				fq_1.EffectSound[6].play();
			break;

		case 6: // '\006'
			fc = 100;
			pow = 0;
			bb = Ya_SetOne(fc, chn, hito, aite, x, y, dir, pow, zokuj, item);
			break;

		case 7: // '\007'
			fc = 1000;
			pow = 200;
			bb = Ya_SetOne(fc, chn, hito, aite, x - 1, y - 3, dir, pow, zokuj,
					item);
			if (bb && (FL & 0x80) != 0)
				fq_1.EffectSound[6].play();
			break;

		case 8: // '\b'
			fc = 10;
			pow = 20;
			bb = Ya_SetOne(fc, 3, hito, aite, x, y, 0, pow, zokuj, item);
			Ya_SetOne(fc, 3, hito, aite, x, y, 1, pow, zokuj, item);
			Ya_SetOne(fc, 3, hito, aite, x, y, 2, pow, zokuj, item);
			Ya_SetOne(fc, 3, hito, aite, x, y, 3, pow, zokuj, item);
			Ya_SetOne(fc, 3, hito, aite, x, y, 4, pow, zokuj, item);
			Ya_SetOne(fc, 3, hito, aite, x, y, 5, pow, zokuj, item);
			Ya_SetOne(fc, 3, hito, aite, x, y, 6, pow, zokuj, item);
			Ya_SetOne(fc, 3, hito, aite, x, y, 7, pow, zokuj, item);
			if (bb && (FL & 0x80) != 0)
				fq_1.EffectSound[9].play();
			break;
		}
	}

	public int Ya_damege(int x, int y, int size, int zokuj, int PYD, int PMD,
			int PYR, int item) {
		for (int i = 0; i < 64; i++)
			if (tama[i].FC != 0) {
				int da = tama[i].damege(x, y, size, zokuj, PYD, PMD, PYR);
				if (tama[i].CHN == 1 && item == 5)
					da = 0;
				fq_1.ya_hito = tama[i].HBUF;
				if (da != 0)
					return da;
			}

		return 0;
	}

	public void update() {
		for (int i = 0; i < 64; i++)
			if (tama[i].FC != 0) {
				tama[i].update();
				if (tama[i].CHN == 3 && tama[i].FC == 1)
					Ya_SetOne(4, 2, tama[i].HBUF, -1, tama[i].X, tama[i].Y, 0,
							10, tama[i].ZOKUJ, 0);
			}

	}

	public void paint(Graphics g) {
		for (int i = 0; i < 64; i++)
			if (tama[i].FC != 0)
				tama[i].paint(g);

	}

	private static final long serialVersionUID = 1L;
	Applet applet;
	int TamaKazu;
	Tama tama[];
	Fq_1 fq_1;
}
