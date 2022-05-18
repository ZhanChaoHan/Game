// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Status.java

package applet;

import java.applet.Applet;
import java.awt.Graphics;
import java.io.UnsupportedEncodingException;

// Referenced classes of package applet:
//            Fq_1

class Suuji extends Applet {

	public Suuji(Applet applet) {
		this.applet = applet;
		fq_1 = (Fq_1) applet;
	}

	public void LvUp_draw(Graphics g, int sx, int sy) {
		int gx = 544;
		int gy = 16;
		g.drawImage(fq_1.FQ_Icon, sx, sy, sx + 32, sy + 16, gx, gy, gx + 32,
				gy + 16, this);
	}

	public void mark_draw(Graphics g, int num, int sx, int sy) {
		int gx = num * 16;
		int gy = 16;
		g.drawImage(fq_1.FQ_Icon, sx, sy, sx + 16, sy + 16, gx, gy, gx + 16,
				gy + 16, this);
	}

	public void paint_moji_one(Graphics g, int num, int color, int sx, int sy) {
		int offset_y = 16;
		if (num > 79) {
			offset_y = 0;
			num -= 256;
		}
		if (num < 0 || num > 79)
			return;
		if (color > 7)
			color = 0;
		int gx = num * 8;
		int gy = color * 32 + offset_y;
		g.drawImage(fq_1.Kazu, sx, sy, sx + 8, sy + 16, gx, gy, gx + 8,
				gy + 16, this);
	}

	public void paint_suuji_one(Graphics g, int num, int color, int sx, int sy) {
		if (num > 79)
			return;
		if (color > 7)
			color = 0;
		int gx = num * 8;
		int gy = color * 32 + 16;
		g.drawImage(fq_1.Kazu, sx, sy, sx + 8, sy + 16, gx, gy, gx + 8,
				gy + 16, this);
	}

	public void paint_suuji(Graphics g, int num, int color, int sx, int sy) {
		if (num > 999)
			num = 999;
		int nn2 = num / 100;
		int nn1 = (num - nn2 * 100) / 10;
		int nn0 = num - nn2 * 100 - nn1 * 10;
		sx -= 8;
		paint_suuji_one(g, nn0, color, sx, sy);
		sx -= 8;
		if (nn1 != 0 || nn2 != 0)
			paint_suuji_one(g, nn1, color, sx, sy);
		sx -= 8;
		if (nn2 != 0)
			paint_suuji_one(g, nn2, color, sx, sy);
	}

	public void paint_16suu(Graphics g, char num, int color, int sx, int sy) {
		if (num > '\377')
			num = '\377';
		int nn1 = num / 16;
		int nn0 = num - nn1 * 16;
		paint_suuji_one(g, nn1, color, sx, sy);
		sx += 8;
		paint_suuji_one(g, nn0, color, sx, sy);
	}

	public int Excang_code(char cc) {
		if (cc >= ' ' && cc <= ')') {
			int code = cc - 32;
			return code;
		}
		if (cc >= '@' && cc <= '_') {
			int code = (cc - 64) + 9;
			return code;
		}
		if (cc >= '`' && cc <= '\177') {
			int code = (cc - 96) + 36;
			return code;
		}
		if (cc == '\336')
			return 78;
		if (cc == '\337')
			return 79;
		if (cc == '\260')
			return 74;
		if (cc == '-')
			return 74;
		if (cc == '\335')
			return (cc - 176) + 256;
		if (cc >= '\261' && cc <= '\336') {
			int code = (cc - 177) + 256;
			return code;
		}
		if (cc >= '\247' && cc <= '\257') {
			int code = (cc - 167) + 46 + 256;
			return code;
		} else {
			return -1;
		}
	}

	public void Name_draw(Graphics g, int num, int color, int sx, int sy,
			int draw_max) {
		int adr = num * 32 + 22;
		if (draw_max > 10)
			draw_max = 10;
		for (int i = 0; i < draw_max; i++) {
			byte by = fq_1.hparadata[adr];
			char cc;
			if (by < 0)
				cc = (char) (by + 256);
			else
				cc = (char) by;
			if (cc != 0) {
				int cnum = Excang_code(cc);
				paint_moji_one(g, cnum, color, sx, sy);
				sx += 8;
			}
			adr++;
		}

	}

	public void Class_draw(Graphics g, int num, int color, int sx, int sy,
			int draw_max) {
		if (num < 0 || num > 69)
			return;
		int adr = 0;
		if (draw_max > 8)
			draw_max = 8;
		byte asciiArray[];
		try {
			asciiArray = ClassName[num].getBytes("Shift_JIS");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		for (int i = 0; i < draw_max; i++) {
			byte by = asciiArray[adr];
			char cc;
			if (by < 0)
				cc = (char) (by + 256);
			else
				cc = (char) by;
			if (cc != 0) {
				int cnum = Excang_code(cc);
				if (cnum >= 0 && cc > ')') {
					paint_moji_one(g, cnum, color, sx, sy);
					sx += 8;
				}
			}
			adr++;
		}

	}

	public void Para_draw(Graphics g, int num, int color, int sx, int sy,
			int para, int CF) {
		int adr = num * 32 + para;
		byte by = fq_1.hparadata[adr];
		char cc;
		if (by < 0)
			cc = (char) (by + 256);
		else
			cc = (char) by;
		if (CF == 1)
			cc /= '\020';
		if (CF == 2)
			cc &= '\017';
		paint_suuji(g, cc, color, sx + 24, sy);
	}

	private static final long serialVersionUID = 1L;
	Applet applet;
	Fq_1 fq_1;
	String ClassName[] = { "\uFF8F\uFF7C\uFF9E\uFF7C\uFF6C\uFF9D  ",
			"\uFF8C\uFF9F\uFF98\uFF9D\uFF7D   ",
			"\uFF95\uFF73\uFF7C\uFF6C    ",
			"\uFF7C\uFF6D\uFF73\uFF84\uFF9E\uFF73\uFF7C ",
			"\uFF85\uFF72\uFF84     ",
			"\uFF71\uFF8F\uFF7F\uFF9E\uFF88\uFF7D  ",
			"\uFF8E\uFF9E\uFF73\uFF8C\uFF67\uFF72\uFF80\uFF70",
			"\uFF71\uFF70\uFF8F\uFF70\uFF7C\uFF6D\uFF70\uFF80",
			"\uFF8C\uFF67\uFF72\uFF80\uFF70   ",
			"\uFF7F\uFF99\uFF7C\uFF9E\uFF6C\uFF70  ",
			"\uFF79\uFF9E\uFF9D\uFF7C\uFF9E\uFF6D\uFF82\uFF7C",
			"\uFF78\uFF9B\uFF77\uFF7C    ", "\uFF97\uFF9D\uFF7D     ",
			"\uFF8F\uFF79\uFF9D\uFF7C    ",
			"\uFF9A\uFF6F\uFF84\uFF9E\uFF71\uFF70\uFF8F\uFF70",
			"\uFF78\uFF9E\uFF9A\uFF91\uFF7C\uFF6D\uFF70\uFF80",
			"\uFF7F\uFF99\uFF7C\uFF9E\uFF6C\uFF70  ",
			"\uFF7C\uFF6D\uFF70\uFF80\uFF70   ", "\uFF77\uFF8D\uFF72     ",
			"\uFF74\uFF99\uFF8C \uFF77\uFF9D\uFF78\uFF9E",
			"\uFF7D\uFF79\uFF99\uFF84\uFF9D   ",
			"\uFF7F\uFF9E\uFF9D\uFF8B\uFF9E   ", "\uFF75\uFF84\uFF7A     ",
			"\uFF75\uFF9D\uFF85     ",
			"\uFF8C\uFF9F\uFF98\uFF9D\uFF7E\uFF7D  ",
			"\uFF75\uFF84\uFF7A\uFF89\uFF7A   ",
			"\uFF84\uFF9E\uFF73\uFF7C    ", "\uFF8E\uFF9E\uFF78\uFF7C    ",
			"\uFF83\uFF9D\uFF7C     ", "\uFF78\uFF9E\uFF70\uFF99    ",
			"\uFF71\uFF78\uFF8F     ", "\uFF7C\uFF86\uFF76\uFF9E\uFF90   ",
			"\uFF71\uFF99\uFF8F\uFF7C\uFF9E\uFF9B  ",
			"\uFF75\uFF75\uFF92\uFF80\uFF9E\uFF8F  ",
			"\uFF7A\uFF9E\uFF70\uFF7D\uFF84   ", "\uFF9C\uFF86      ",
			"\uFF7B\uFF92      ",
			"\uFF8A\uFF9D\uFF77\uFF9E\uFF6E\uFF7C\uFF9E\uFF9D",
			"\uFF94\uFF8F \uFF7A\uFF9E\uFF98\uFF97 ",
			"\uFF7A\uFF73\uFF93\uFF98    ",
			"\uFF8A\uFF9F\uFF6F\uFF78\uFF9D\uFF8C\uFF97\uFF9C",
			"\uFF8A\uFF9E\uFF70\uFF84\uFF9E\uFF8F\uFF9D ",
			"\uFF8B\uFF9E\uFF6F\uFF78\uFF9E\uFF8F\uFF73\uFF7D",
			"\uFF74\uFF99\uFF8C \uFF95\uFF90  ",
			"\uFF74\uFF99\uFF8C \uFF94\uFF98  ",
			"\uFF84\uFF9E\uFF9C\uFF70\uFF8C   ", "\uFF89\uFF70\uFF91     ",
			"\uFF98\uFF7B\uFF9E\uFF70\uFF84\uFF9E  ",
			"\uFF98\uFF7B\uFF9E\uFF70\uFF84\uFF9E\uFF8F\uFF9D",
			"\uFF79\uFF9D\uFF80\uFF73\uFF9B\uFF7D  ",
			"\uFF79\uFF9D\uFF80\uFF73\uFF9B\uFF7D  ",
			"\uFF9A\uFF72\uFF8A\uFF9E\uFF9D   ", "\uFF73\uFF99\uFF8C     ",
			"\uFF77\uFF84\uFF73\uFF7C    ",
			"\uFF84\uFF9E\uFF97\uFF7A\uFF9E\uFF9D  ",
			"\uFF78\uFF72\uFF70\uFF9D    ", "\uFF73\uFF72\uFF6F\uFF81    ",
			"\uFF7A\uFF9E\uFF70\uFF9A\uFF91   ",
			"\uFF7F\uFF99\uFF7C\uFF9E\uFF6C\uFF70  ",
			"\uFF77\uFF9D\uFF78\uFF9E    ",
			"\uFF71\uFF77\uFF9D\uFF84\uFF9E   ",
			"\uFF8A\uFF9E\uFF70\uFF7B\uFF70\uFF76\uFF70 ",
			"\uFF7E\uFF9D\uFF7C     ", "\uFF7C\uFF9E\uFF72\uFF7B\uFF9D   ",
			"\uFF75\uFF75\uFF80\uFF9E\uFF7A   ",
			"\uFF84\uFF9E\uFF97\uFF7A\uFF9E\uFF9D  ",
			"\uFF8F\uFF75\uFF73     ",
			"\uFF8F\uFF9D\uFF83\uFF68\uFF7A\uFF71  ",
			"\uFF7F\uFF99\uFF7C\uFF9E\uFF6C\uFF70  ",
			"\uFF7C\uFF6D\uFF70\uFF80\uFF70   " };
	String MagicName[] = { "8 Point Burst", "Ice Blade", "Bind of Light",
			"Alter Shape", "Split Shape", "Angel Call", "Charm Monster",
			"Earth Quake", "Thunder", "Nova" };
}
