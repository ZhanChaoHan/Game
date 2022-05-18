// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Grand.java

package applet;

import java.applet.Applet;
import java.awt.Graphics;

// Referenced classes of package applet:
//            Fq_1

class Grand extends Applet {

	public Grand(Applet applet) {
		XLM = 2;
		this.applet = applet;
		fq_1 = (Fq_1) applet;
	}

	public void Grand_init() {
		GMX_ST = fq_1.MXS * 16;
		GMY_ST = fq_1.MYS * 16;
		GMX_END = GMX_ST + fq_1.MXLM * 16;
		GMY_END = GMY_ST + fq_1.MYLM * 16;
		x_movmin = fq_1.MXS;
		y_movmin = fq_1.MYS;
		x_movmax = (fq_1.MXS + fq_1.MXLM) - XLM;
		y_movmax = (fq_1.MYS + fq_1.MYLM) - XLM;
	}

	boolean map_atari(int mx, int my) {
		if (mx < fq_1.MXS || my < fq_1.MYS || mx >= fq_1.MXLM
				|| my >= fq_1.MYLM)
			return false;
		return (fq_1.mapatrdata[my * fq_1.MXLM + mx] & 0x80) != 0;
	}

	public int goup_keisan(int mx, int my) {
		int x = mx;
		int y = my;
		Klen_L = 0;
		Klen_R = 0;
		if (y <= y_movmin)
			return -1;
		int adr1 = y * fq_1.MXLM + x;
		if ((fq_1.mapatrdata[adr1] & 0x80) == 0
				&& (fq_1.mapatrdata[adr1 + 1] & 0x80) == 0)
			return 0;
		x = mx;
		adr1 = y * fq_1.MXLM + x;
		int adr2 = (y + 1) * fq_1.MXLM + x;
		if (x >= x_movmax)
			Klen_R = -2;
		else
			while ((fq_1.mapatrdata[adr1 + 1] & 0x80) != 0) {
				if (++x >= x_movmax) {
					Klen_R = -2;
					break;
				}
				if ((fq_1.mapatrdata[adr2 + 1] & 0x80) != 0) {
					Klen_R = -1;
					break;
				}
				adr1++;
				adr2++;
				Klen_R++;
			}
		x = mx + 1;
		adr1 = y * fq_1.MXLM + x;
		adr2 = (y + 1) * fq_1.MXLM + x;
		if (x < x_movmin)
			Klen_L = -2;
		else
			while ((fq_1.mapatrdata[adr1 - 1] & 0x80) != 0) {
				if (--x < x_movmin) {
					Klen_L = -2;
					break;
				}
				if ((fq_1.mapatrdata[adr2 - 1] & 0x80) != 0) {
					Klen_L = -1;
					break;
				}
				adr1--;
				adr2--;
				Klen_L++;
			}
		if (Klen_L < 0 && Klen_R < 0)
			return -2;
		if (Klen_L < 0)
			return 3;
		if (Klen_R < 0)
			return 1;
		return Klen_L >= Klen_R ? 3 : 1;
	}

	public int godown_keisan(int mx, int my) {
		int x = mx;
		int y = my;
		Klen_L = 0;
		Klen_R = 0;
		if (y >= y_movmax)
			return -1;
		int adr1 = (y + 2) * fq_1.MXLM + x;
		if ((fq_1.mapatrdata[adr1] & 0x80) == 0
				&& (fq_1.mapatrdata[adr1 + 1] & 0x80) == 0)
			return 2;
		x = mx;
		adr1 = (y + 2) * fq_1.MXLM + x;
		int adr2 = (y + 1) * fq_1.MXLM + x;
		if (x >= x_movmax)
			Klen_R = -2;
		else
			while ((fq_1.mapatrdata[adr1 + 1] & 0x80) != 0) {
				if (++x >= x_movmax) {
					Klen_R = -2;
					break;
				}
				if ((fq_1.mapatrdata[adr2 + 1] & 0x80) != 0) {
					Klen_R = -1;
					break;
				}
				adr1++;
				adr2++;
				Klen_R++;
			}
		x = mx + 1;
		adr1 = (y + 2) * fq_1.MXLM + x;
		adr2 = (y + 1) * fq_1.MXLM + x;
		if (x < x_movmin)
			Klen_L = -2;
		else
			while ((fq_1.mapatrdata[adr1 - 1] & 0x80) != 0) {
				if (--x < x_movmin) {
					Klen_L = -2;
					break;
				}
				if ((fq_1.mapatrdata[adr2 - 1] & 0x80) != 0) {
					Klen_L = -1;
					break;
				}
				adr1--;
				adr2--;
				Klen_L++;
			}
		if (Klen_L < 0 && Klen_R < 0)
			return -2;
		if (Klen_L < 0)
			return 3;
		if (Klen_R < 0)
			return 1;
		return Klen_L >= Klen_R ? 3 : 1;
	}

	public int goleft_keisan(int mx, int my) {
		int x = mx;
		int y = my;
		Klen_U = 0;
		Klen_D = 0;
		if (x <= x_movmin)
			return -1;
		int adr1 = (y + 1) * fq_1.MXLM + (x - 1);
		if ((fq_1.mapatrdata[adr1] & 0x80) == 0)
			return 1;
		y = my + 1;
		adr1 = y * fq_1.MXLM + (x - 1);
		int adr2 = y * fq_1.MXLM + x;
		if (y >= y_movmax)
			Klen_D = -2;
		else
			while ((fq_1.mapatrdata[adr1] & 0x80) != 0) {
				if (++y >= y_movmax) {
					Klen_D = -2;
					break;
				}
				if ((fq_1.mapatrdata[adr2] & 0x80) != 0) {
					Klen_D = -1;
					break;
				}
				adr1 += fq_1.MXLM;
				adr2 += fq_1.MXLM;
				Klen_D++;
			}
		y = my + 1;
		adr1 = y * fq_1.MXLM + (x - 1);
		adr2 = y * fq_1.MXLM + x;
		if (y < y_movmin)
			Klen_U = -2;
		else
			while ((fq_1.mapatrdata[adr1] & 0x80) != 0) {
				if (--y < y_movmin) {
					Klen_U = -2;
					break;
				}
				if ((fq_1.mapatrdata[adr2] & 0x80) != 0) {
					Klen_U = -1;
					break;
				}
				adr1 -= fq_1.MXLM;
				adr2 -= fq_1.MXLM;
				Klen_U++;
			}
		if (Klen_U == 0 || Klen_D == 0)
			return 1;
		if (Klen_U < 0 && Klen_D < 0)
			return -2;
		if (Klen_U < 0)
			return 2;
		if (Klen_D < 0)
			return 0;
		return Klen_U >= Klen_D ? 2 : 0;
	}

	public int goright_keisan(int mx, int my) {
		int x = mx;
		int y = my;
		Klen_U = 0;
		Klen_D = 0;
		if (x >= x_movmax)
			return -1;
		int adr1 = (y + 1) * fq_1.MXLM + (x + 2);
		if ((fq_1.mapatrdata[adr1] & 0x80) == 0)
			return 3;
		y = my + 1;
		adr1 = y * fq_1.MXLM + (x + 2);
		int adr2 = y * fq_1.MXLM + (x + 1);
		if (y >= y_movmax)
			Klen_D = -2;
		else
			while ((fq_1.mapatrdata[adr1] & 0x80) != 0) {
				if (++y >= y_movmax) {
					Klen_D = -2;
					break;
				}
				if ((fq_1.mapatrdata[adr2] & 0x80) != 0) {
					Klen_D = -1;
					break;
				}
				adr1 += fq_1.MXLM;
				adr2 += fq_1.MXLM;
				Klen_D++;
			}
		y = my + 1;
		adr1 = y * fq_1.MXLM + (x + 2);
		adr2 = y * fq_1.MXLM + (x + 1);
		if (y < y_movmin)
			Klen_U = -2;
		else
			while ((fq_1.mapatrdata[adr1] & 0x80) != 0) {
				if (--y < y_movmin) {
					Klen_U = -2;
					break;
				}
				if ((fq_1.mapatrdata[adr2] & 0x80) != 0) {
					Klen_U = -1;
					break;
				}
				adr1 -= fq_1.MXLM;
				adr2 -= fq_1.MXLM;
				Klen_U++;
			}
		if (Klen_U == 0 || Klen_D == 0)
			return 3;
		if (Klen_U < 0 && Klen_D < 0)
			return -2;
		if (Klen_U < 0)
			return 2;
		if (Klen_D < 0)
			return 0;
		return Klen_U >= Klen_D ? 2 : 0;
	}

	public int go_keisan(int dir, int mx, int my) {
		int d = -1;
		switch (dir) {
		case 0: // '\0'
			d = goup_keisan(mx, my);
			break;

		case 1: // '\001'
			d = goleft_keisan(mx, my);
			break;

		case 2: // '\002'
			d = godown_keisan(mx, my);
			break;

		case 3: // '\003'
			d = goright_keisan(mx, my);
			break;
		}
		return d;
	}

	boolean mapcheck_m(int mx, int my) {
		if (map_atari(mx, my + 1))
			return true;
		return map_atari(mx + 1, my + 1);
	}

	void kaihi_hosei(int mx, int my, int tx, int ty) {
		moku_X = tx;
		moku_Y = ty;
		int x2 = mx;
		int y2 = my;
		int x1 = tx;
		int y1 = ty;
		if (!mapcheck_m(x1, y1))
			return;
		int Ddx = x2 - x1;
		int Ddy = y2 - y1;
		int x = x1;
		int y = y1;
		int A = 2 * Math.abs(Ddx);
		int B = 2 * Math.abs(Ddy);
		if (A >= B) {
			int R = -1 * Math.abs(Ddx);
			for (int i = 0; i < Math.abs(Ddx); i++) {
				if (Ddx > 0)
					x++;
				else if (Ddx < 0)
					x--;
				R += B;
				if (R >= 0) {
					if (Ddy > 0)
						y++;
					else if (Ddy < 0)
						y--;
					R -= A;
					if (!mapcheck_m(x, y)) {
						moku_X = x;
						moku_Y = y;
						return;
					}
				} else if (!mapcheck_m(x, y)) {
					moku_X = x;
					moku_Y = y;
					return;
				}
			}

		} else {
			int R = -1 * Math.abs(Ddy);
			for (int i = 0; i < Math.abs(Ddy); i++) {
				if (Ddy > 0)
					y++;
				else if (Ddy < 0)
					y--;
				R += A;
				if (R >= 0) {
					if (Ddx > 0)
						x++;
					else if (Ddx < 0)
						x--;
					R -= B;
					if (!mapcheck_m(x, y)) {
						moku_X = x;
						moku_Y = y;
						return;
					}
				} else if (!mapcheck_m(x, y)) {
					moku_X = x;
					moku_Y = y;
					return;
				}
			}

		}
	}

	public boolean houkou_atari(int dir, int mx, int my) {
		int x = mx;
		int y = my;
		switch (dir) {
		case 0: // '\0'
		{
			int adr1 = y * fq_1.MXLM + x;
			return (fq_1.mapatrdata[adr1] & 0x80) != 0
					|| (fq_1.mapatrdata[adr1 + 1] & 0x80) != 0;
		}

		case 1: // '\001'
		{
			int adr1 = (y + 1) * fq_1.MXLM + (x - 1);
			return (fq_1.mapatrdata[adr1] & 0x80) != 0;
		}

		case 2: // '\002'
		{
			int adr1 = (y + 2) * fq_1.MXLM + x;
			return (fq_1.mapatrdata[adr1] & 0x80) != 0
					|| (fq_1.mapatrdata[adr1 + 1] & 0x80) != 0;
		}

		case 3: // '\003'
		{
			int adr1 = (y + 1) * fq_1.MXLM + (x + 2);
			return (fq_1.mapatrdata[adr1] & 0x80) != 0;
		}
		}
		return false;
	}

	public int Tutai_idosub(int dir, int mx, int my, int tutai_f) {
		dir &= 3;
		if (!houkou_atari(dir, mx, my))
			return dir;
		if (tutai_f == 0) {
			dir = ++dir & 3;
			if (!houkou_atari(dir, mx, my))
				return dir;
			dir = ++dir & 3;
			if (!houkou_atari(dir, mx, my))
				return dir;
			else
				return -1;
		}
		dir = --dir & 3;
		if (!houkou_atari(dir, mx, my))
			return dir;
		dir = --dir & 3;
		if (!houkou_atari(dir, mx, my))
			return dir;
		else
			return -1;
	}

	public int Tutai_ido(int mx, int my, int tx, int ty, int tutai_f) {
		int dx = tx - mx;
		int dy = ty - my;
		if (dx == 0 && dy == 0)
			return -1;
		if (Math.abs(dx) > Math.abs(dy))
			if (dx < 0)
				return Tutai_idosub(1, mx, my, tutai_f);
			else
				return Tutai_idosub(3, mx, my, tutai_f);
		if (dy < 0)
			return Tutai_idosub(0, mx, my, tutai_f);
		else
			return Tutai_idosub(2, mx, my, tutai_f);
	}

	public int Nerai(int mx, int my, int tx, int ty, int sekkin_f) {
		int dx = tx - mx;
		int dy = ty - my;
		if (dx == 0 && dy == 0)
			return -1;
		if (sekkin_f == 0) {
			if (Math.abs(dx) > Math.abs(dy))
				return dx >= 0 ? 3 : 1;
			return dy >= 0 ? 2 : 0;
		}
		if (dx == 0)
			return dy >= 0 ? 2 : 0;
		if (dy == 0)
			return dx >= 0 ? 3 : 1;
		if (Math.abs(dx) > Math.abs(dy))
			return dy >= 0 ? 2 : 0;
		return dx >= 0 ? 3 : 1;
	}

	public int Nerai_xy(int mx, int my, int tx, int ty, int y_yuusen) {
		int mergin = 1;
		int dx = tx - mx;
		int dy = ty - my;
		if (dx == 0 && dy == 0)
			return -1;
		if (y_yuusen == 0) {
			if (dx < -mergin)
				return 1;
			if (dx > mergin)
				return 3;
			if (dy < -mergin)
				return 0;
			if (dy > mergin)
				return 2;
		} else {
			if (dy < -mergin)
				return 0;
			if (dy > mergin)
				return 2;
			if (dx < -mergin)
				return 1;
			if (dx > mergin)
				return 3;
		}
		if (y_yuusen == 0) {
			if (dx < 0)
				return 1;
			if (dx > 0)
				return 3;
			if (dy < 0)
				return 0;
			if (dy > 0)
				return 2;
		} else {
			if (dy < 0)
				return 0;
			if (dy > 0)
				return 2;
			if (dx < 0)
				return 1;
			if (dx > 0)
				return 3;
		}
		return -1;
	}

	public int Nige(int mx, int my, int tx, int ty, int sekkin_f) {
		int dx = tx - mx;
		int dy = ty - my;
		if (sekkin_f == 0) {
			if (Math.abs(dx) > Math.abs(dy))
				return dy >= 0 ? 0 : 2;
			return dx >= 0 ? 1 : 3;
		}
		if (Math.abs(dx) > Math.abs(dy))
			return dx >= 0 ? 1 : 3;
		return dy >= 0 ? 0 : 2;
	}

	public void Hito_screen(int mx, int my) {
		fq_1.Map_SX = mx - 19;
		fq_1.Map_SY = my - 11;
		if (fq_1.Map_SX < fq_1.MXS)
			fq_1.Map_SX = fq_1.MXS;
		if (fq_1.Map_SY < fq_1.MYS)
			fq_1.Map_SY = fq_1.MYS;
		if (fq_1.Map_SX > fq_1.MXS + fq_1.MXLM)
			fq_1.Map_SX = fq_1.MXS + fq_1.MXLM;
		if (fq_1.Map_SY > fq_1.MYS + fq_1.MYLM)
			fq_1.Map_SY = fq_1.MYS + fq_1.MYLM;
		fq_1.DispMapX = fq_1.Map_SX * 16;
		fq_1.DispMapY = fq_1.Map_SY * 16;
		if (fq_1.DispMapX < GMX_ST)
			fq_1.DispMapX = GMX_ST;
		if (fq_1.DispMapY < GMY_ST)
			fq_1.DispMapY = GMY_ST;
		if (fq_1.DispMapX > GMX_END - fq_1.GameWidth)
			fq_1.DispMapX = GMX_END - fq_1.GameWidth;
		if (fq_1.DispMapY > GMY_END - fq_1.GameHeight)
			fq_1.DispMapY = GMY_END - fq_1.GameHeight;
		fq_1.Map_SX = fq_1.DispMapX / 16;
		fq_1.Map_SY = fq_1.DispMapY / 16;
	}

	public void update() {
		MoveScreen();
	}

	public void paint(Graphics g) {
		g.drawImage(fq_1.Map, fq_1.GameSX, fq_1.GameSY, fq_1.GameSX
				+ fq_1.GameWidth, fq_1.GameSY + fq_1.GameHeight, fq_1.DispMapX,
				fq_1.DispMapY, fq_1.DispMapX + fq_1.GameWidth, fq_1.DispMapY
						+ fq_1.GameHeight, this);
	}

	void MoveScreen() {
		int idoustep = 4;
		if (fq_1.DistanceX < 0)
			fq_1.DispMapX += idoustep;
		if (fq_1.DistanceX > 0)
			fq_1.DispMapX -= idoustep;
		if (fq_1.DistanceY < 0)
			fq_1.DispMapY += idoustep;
		if (fq_1.DistanceY > 0)
			fq_1.DispMapY -= idoustep;
		if (fq_1.DispMapX < GMX_ST)
			fq_1.DispMapX = GMX_ST;
		if (fq_1.DispMapY < GMY_ST)
			fq_1.DispMapY = GMY_ST;
		if (fq_1.DispMapX > GMX_END - fq_1.GameWidth)
			fq_1.DispMapX = GMX_END - fq_1.GameWidth;
		if (fq_1.DispMapY > GMY_END - fq_1.GameHeight)
			fq_1.DispMapY = GMY_END - fq_1.GameHeight;
		fq_1.Map_SX = fq_1.DispMapX / 16;
		fq_1.Map_SY = fq_1.DispMapY / 16;
	}

	void MapScroll(int dir) {
		int dx = 0;
		int dy = 0;
		switch (dir) {
		default:
			break;

		case 0: // '\0'
			if (fq_1.Map_SY > fq_1.MYS)
				dy = -1;
			break;

		case 1: // '\001'
			if (fq_1.Map_SX > fq_1.MXS)
				dx = -1;
			break;

		case 2: // '\002'
			if (fq_1.Map_SY < (fq_1.MYS + fq_1.MYLM) - 24)
				dy = 1;
			break;

		case 3: // '\003'
			if (fq_1.Map_SX < (fq_1.MXS + fq_1.MXLM) - 40)
				dx = 1;
			break;
		}
		fq_1.Map_SX += dx;
		fq_1.Map_SY += dy;
		fq_1.DispMapX = fq_1.Map_SX * 16;
		fq_1.DispMapY = fq_1.Map_SY * 16;
	}

	private static final long serialVersionUID = 1L;
	Applet applet;
	int GMX_ST;
	int GMY_ST;
	int GMX_END;
	int GMY_END;
	int Klen_L;
	int Klen_R;
	int Klen_U;
	int Klen_D;
	int x_movmin;
	int y_movmin;
	int x_movmax;
	int y_movmax;
	int XLM;
	Fq_1 fq_1;
	int moku_X;
	int moku_Y;
}
