// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Tama.java

package applet;

import java.applet.Applet;
import java.awt.Graphics;

// Referenced classes of package applet:
//            Fq_1, YaManeger

class Tama extends Applet {

	public Tama(Applet applet, YaManeger ya) {
		this.applet = applet;
		fq_1 = (Fq_1) applet;
		this.ya = ya;
		FC = 0;
		FL = 0;
		CPN = 0;
		CHN = 0;
		X = 0;
		Y = 0;
		Z = 0;
		XNE = 0;
		YNE = 0;
		ZNE = 0;
		DIR = 0;
		GX = 0;
		GY = 0;
		XL = 0;
		YL = 0;
		ZOKUJ = 0;
		TPOW = 0;
		TOKU = 0;
		HBUF = -1;
		AITE = -1;
		ITEM = 0;
	}

	public void Tama_Init(int fc, int chn, int hito, int aite, int x, int y,
			int dir, int pow, int zokuj, int item) {
		FC = fc;
		FL = 0;
		CPN = 0;
		CHN = chn;
		X = x;
		Y = y;
		XNE = 0;
		YNE = 0;
		ZNE = 0;
		DIR = dir;
		ZOKUJ = zokuj;
		TPOW = pow;
		HBUF = hito;
		AITE = aite;
		ITEM = item;
		switch (CHN) {
		default:
			break;

		case 0: // '\0'
			GX = 0;
			GY = 0;
			XL = 16;
			YL = 16;
			break;

		case 1: // '\001'
			switch (DIR & 3) {
			case 0: // '\0'
				GX = 512;
				GY = 0;
				break;

			case 1: // '\001'
				GX = 528;
				GY = 0;
				break;

			case 2: // '\002'
				GX = 544;
				GY = 0;
				break;

			case 3: // '\003'
				GX = 560;
				GY = 0;
				break;
			}
			XL = 16;
			YL = 16;
			break;

		case 2: // '\002'
			XL = 48;
			YL = 48;
			DIR = fq_1.Sys_Count1;
			break;

		case 3: // '\003'
			XL = 32;
			YL = 32;
			break;

		case 4: // '\004'
			switch (DIR & 7) {
			case 0: // '\0'
				GX = 64;
				GY = 160;
				break;

			case 1: // '\001'
				GX = 0;
				GY = 160;
				break;

			case 2: // '\002'
				GX = 192;
				GY = 160;
				break;

			case 3: // '\003'
				GX = 128;
				GY = 160;
				break;

			case 4: // '\004'
				GX = 32;
				GY = 160;
				break;

			case 5: // '\005'
				GX = 224;
				GY = 160;
				break;

			case 6: // '\006'
				GX = 160;
				GY = 160;
				break;

			case 7: // '\007'
				GX = 96;
				GY = 160;
				break;
			}
			XL = 32;
			YL = 32;
			break;

		case 5: // '\005'
			XL = 48;
			YL = 48;
			DIR = fq_1.Sys_Count2;
			break;

		case 6: // '\006'
			XL = 48;
			YL = 48;
			DIR = (int) (Math.random() * 8D);
			break;

		case 7: // '\007'
			XL = 80;
			YL = 96;
			DIR = fq_1.Sys_Count2;
			break;
		}
	}

	public void ya_move() {
		switch (CHN) {
		case 1: // '\001'
			int ya = fq_1.mapatrdata[Y * fq_1.MXLM + X];
			if (ya < 0)
				ya += 256;
			ya = ya / 16 & 7;
			if (ITEM == 7)
				ya = 0;
			switch (ya) {
			case 1: // '\001'
			{
				if ((DIR & 3) == 0)
					FC = 0;
				break;
			}

			case 2: // '\002'
			{
				if ((DIR & 3) == 1)
					FC = 0;
				break;
			}

			case 3: // '\003'
			{
				if ((DIR & 3) == 2)
					FC = 0;
				break;
			}

			case 4: // '\004'
			{
				if ((DIR & 3) == 3)
					FC = 0;
				break;
			}

			case 5: // '\005'
			{
				int rd = (int) (Math.random() * 30D);
				if (rd < 6)
					FC = 0;
				break;
			}

			case 6: // '\006'
			{
				int rd = (int) (Math.random() * 30D);
				if (rd < 2)
					FC = 0;
				break;
			}

			case 7: // '\007'
			{
				FC = 0;
				break;
			}
			}
			if (FC == 0)
				return;
			switch (DIR & 3) {
			case 0: // '\0'
				Y--;
				break;

			case 1: // '\001'
				X--;
				break;

			case 2: // '\002'
				Y++;
				break;

			case 3: // '\003'
				X++;
				break;
			}
			break;

		case 3: // '\003'
			switch (DIR & 7) {
			case 0: // '\0'
				Y--;
				break;

			case 1: // '\001'
				X--;
				break;

			case 2: // '\002'
				Y++;
				break;

			case 3: // '\003'
				X++;
				break;

			case 4: // '\004'
				X--;
				Y--;
				break;

			case 5: // '\005'
				X--;
				Y++;
				break;

			case 6: // '\006'
				X++;
				Y++;
				break;

			case 7: // '\007'
				X++;
				Y--;
				break;
			}
			break;

		case 4: // '\004'
			switch (DIR & 7) {
			case 0: // '\0'
				Y--;
				break;

			case 1: // '\001'
				X--;
				break;

			case 2: // '\002'
				Y++;
				break;

			case 3: // '\003'
				X++;
				break;

			case 4: // '\004'
				X--;
				Y--;
				break;

			case 5: // '\005'
				X--;
				Y++;
				break;

			case 6: // '\006'
				X++;
				Y++;
				break;

			case 7: // '\007'
				X++;
				Y--;
				break;
			}
			break;
		}
		if (X < fq_1.MXS || Y < fq_1.MYS || X >= fq_1.MXLM || Y >= fq_1.MYLM)
			FC = 0;
	}

	void calc_gxgy() {
		switch (CHN) {
		case 0: // '\0'
		case 1: // '\001'
		default:
			break;

		case 2: // '\002'
		{
			GX = 288;
			GY = 64;
			if (DIR != fq_1.Sys_Count1) {
				DIR = fq_1.Sys_Count1;
				if (CPN < 5)
					CPN++;
				else
					FC = 0;
			}
			GX += CPN * XL;
			break;
		}

		case 3: // '\003'
		{
			int count = fq_1.Sys_Count2 & 3;
			switch (DIR & 7) {
			case 0: // '\0'
				GX = 0;
				GY = 192;
				break;

			case 1: // '\001'
				GX = 128;
				GY = 192;
				break;

			case 2: // '\002'
				GX = 256;
				GY = 192;
				break;

			case 3: // '\003'
				GX = 384;
				GY = 192;
				break;

			case 4: // '\004'
				GX = 0;
				GY = 224;
				break;

			case 5: // '\005'
				GX = 128;
				GY = 224;
				break;

			case 6: // '\006'
				GX = 256;
				GY = 224;
				break;

			case 7: // '\007'
				GX = 384;
				GY = 224;
				break;
			}
			GX += count * XL;
			break;
		}

		case 4: // '\004'
		{
			int count = fq_1.Sys_TurnCount & 7;
			GX = count * XL;
			GY = 160;
			break;
		}

		case 5: // '\005'
		{
			GY = 112;
			if (DIR != fq_1.Sys_Count2) {
				DIR = fq_1.Sys_Count2;
				if (CPN < 9)
					CPN++;
				else
					FC = 0;
			}
			GX = CPN * XL;
			break;
		}

		case 6: // '\006'
		{
			int count = fq_1.Sys_Count2 & 7;
			GY = 16;
			if (count > 4) {
				GY += YL;
				count -= 5;
			}
			GX = count * 2 * XL;
			break;
		}

		case 7: // '\007'
		{
			GX = CPN * XL;
			GY = 256;
			if (CPN >= 8) {
				GX = 0;
				GY += YL;
			}
			if (DIR == fq_1.Sys_Count2)
				break;
			DIR = fq_1.Sys_Count2;
			if (CPN < 9)
				CPN++;
			else
				FC = 0;
			break;
		}
		}
	}

	public int damege(int tx, int ty, int size, int zoku, int PYD, int PMD,
			int PYR) {
		if ((zoku & 1) == (ZOKUJ & 1))
			return 0;
		int rd = (int) (Math.random() * 100D);
		int i;
		switch (CHN) {
		default:
			break;

		case 0: // '\0'
		{
			return 0;
		}

		case 1: // '\001'
		{
			if (fq_1.Headon_m(X, Y, tx, ty, 1, size, size)) {
				if (rd < PYR)
					return 0;
				FC = 0;
				int pow = TPOW - PYD;
				if (pow <= 0)
					pow = 1;
				return pow;
			}
			break;
		}

		case 2: // '\002'
		{
			if (!fq_1.Headon_m(X, Y, tx, ty, 2, size, size))
				break;
			int pow = TPOW - PMD;
			if (pow <= 0)
				pow = 0;
			return pow;
		}

		case 3: // '\003'
		{
			if (!fq_1.Headon_m(X, Y, tx, ty, 2, size, size))
				break;
			if (rd < PYR)
				return 0;
			ya.Ya_SetOne(4, 2, HBUF, -1, X, Y, 0, 10, ZOKUJ, 0);
			FC = 0;
			int pow = TPOW - PMD;
			if (pow <= 0)
				pow = 1;
			return pow;
		}

		case 4: // '\004'
		{
			if (!fq_1.Headon_m(X, Y, tx, ty, 2, size, size))
				break;
			if (rd < PYR)
				return 0;
			int pow = TPOW - PMD;
			if (pow <= 0)
				pow = 1;
			return pow;
		}

		case 5: // '\005'
		{
			if (!fq_1.Headon_m(X, Y, tx, ty, 2, size, size))
				break;
			int pow = TPOW - PMD;
			if (pow <= 0)
				pow = 0;
			return pow;
		}

		case 6: // '\006'
		{
			return 0;
		}

		case 7: // '\007'
		{
			i = (int) (Math.random() * 100D) + 50;
			break;
		}
		}
		return 0;
	}

	public void update() {
		ya_move();
		calc_gxgy();
		if (FC > 0)
			FC--;
	}

	public void paint(Graphics g) {
		int drawx = X * 16 - fq_1.DispMapX;
		int drawy = Y * 16 - fq_1.DispMapY;
		if (CHN == 2 || CHN == 5 || CHN == 6 || CHN == 7) {
			drawx -= 8;
			drawy -= 8;
		}
		FL &= 0xffffff7f;
		if (drawx > -XL && drawx < 640 && drawy > -YL && drawy < 440) {
			FL |= 0x80;
			g.drawImage(fq_1.Magic, drawx, drawy + fq_1.GameSY, drawx + XL,
					drawy + fq_1.GameSY + YL, GX, GY, GX + XL, GY + YL, this);
			if (CHN == 6) {
				int count = fq_1.Sys_Count2 + DIR & 7;
				GY = 16;
				if (count > 4) {
					GY += YL;
					count -= 5;
				}
				GX = count * 2 * XL + XL;
				g.drawImage(fq_1.Magic, drawx, drawy + fq_1.GameSY, drawx + XL,
						drawy + fq_1.GameSY + YL, GX, GY, GX + XL, GY + YL,
						this);
			}
		}
	}

	private static final long serialVersionUID = 1L;
	Applet applet;
	int AppletWidth;
	int AppletHeight;
	int GameSX;
	int GameSY;
	int MapWidth;
	int MapHeight;
	int FC;
	int FL;
	int CPN;
	int CHN;
	int X;
	int Y;
	int Z;
	int XNE;
	int YNE;
	int ZNE;
	int GX;
	int GY;
	int XL;
	int YL;
	int DIR;
	int ZOKUJ;
	int TPOW;
	int TOKU;
	int HBUF;
	int AITE;
	int ITEM;
	Fq_1 fq_1;
	YaManeger ya;
}
