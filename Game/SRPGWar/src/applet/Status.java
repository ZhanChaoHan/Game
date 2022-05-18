// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Status.java

package applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

// Referenced classes of package applet:
//            Fq_1, Suuji, TeamManeger, Gocha

class Status extends Applet {

	public Status(Applet applet, TeamManeger team) {
		ic_y = 3;
		ic_soku = 240;
		ic_book = 308;
		ic_camp = 374;
		ic_form = 420;
		ic_tuujyo = 450;
		ic_kouige = 484;
		ic_nige = 518;
		ic_teisi = 552;
		ic_pouse = 600;
		this.applet = applet;
		fq_1 = (Fq_1) applet;
		this.team = team;
		suuji = new Suuji(applet);
	}

	void itiran_title(Graphics g, int num) {
		if (num > 1) {
			return;
		} else {
			int len = MemberTiltl[num].length();
			int xl = (len + 1) * 16 + 16;
			int yl = 32;
			int sx = fq_1.AppletWidth / 2 - xl / 2;
			int sy = 30;
			g.setColor(new Color(0, 50, 150));
			g.fillRect(sx, sy, xl, yl);
			g.setColor(Color.WHITE);
			g.drawRect(sx, sy, xl, yl);
			g.drawString(MemberTiltl[num], sx + 12, sy + 22);
			return;
		}
	}

	void message_keikoku(Graphics g, String s_mess) {
		int len = s_mess.length();
		int xl = (len + 1) * 16 + 16;
		int yl = 32;
		int sx = fq_1.AppletWidth / 2 - xl / 2;
		int sy = fq_1.AppletHeight / 2 - yl / 2 - 24;
		g.setColor(new Color(0, 50, 150));
		g.fillRect(sx, sy, xl, yl);
		g.setColor(new Color(250, 150, 50));
		g.drawRect(sx, sy, xl, yl);
		g.drawString(s_mess, sx + 12, sy + 22);
	}

	void message_mapname(Graphics g, int num) {
		if (num <= 0 || num > 59) {
			return;
		} else {
			int len = MPNAME[num].length();
			int xl = (len + 1) * 16 + 16;
			int yl = 32;
			int sx = fq_1.AppletWidth / 2 - xl / 2;
			int sy = fq_1.AppletHeight / 2 - yl / 2 - 130;
			g.setColor(new Color(0, 0, 100));
			g.fillRect(sx, sy, xl, yl);
			g.setColor(Color.WHITE);
			g.drawRect(sx, sy, xl, yl);
			g.drawString(MPNAME[num], sx + 12, sy + 22);
			return;
		}
	}

	void message_itemget(Graphics g, int item_num, int mess_num) {
		if (mess_num < 0 || mess_num > 6)
			return;
		if (item_num <= 0 || item_num > fq_1.ITEMMAX)
			return;
		String mess = ITEMNAME[item_num];
		String s_mess = mess.concat(ITEMGETMES[mess_num]);
		int len = s_mess.length();
		int xl = (len + 1) * 16 + 16;
		int yl = 32;
		int sx = fq_1.AppletWidth / 2 - xl / 2;
		int sy = (fq_1.AppletHeight / 2 - yl / 2) + 16;
		if (mess_num == 2) {
			itemget(g, item_num);
			sy = 30;
			if (fq_1.MouseBK_C != 0)
				return;
		}
		g.setColor(new Color(50, 0, 150));
		g.fillRect(sx, sy, xl, yl);
		g.setColor(new Color(250, 250, 100));
		g.drawRect(sx, sy, xl, yl);
		g.drawString(s_mess, sx + 12, sy + 22);
		if (mess_num == 0 || mess_num == 6) {
			int mp_dx = fq_1.Paint_count / 4 & 3;
			g.drawImage(fq_1.FQ_Icon, sx + xl + 4, (sy + yl) - 16,
					sx + xl + 20, sy + yl, mp_dx * 16 + 578, 16,
					mp_dx * 16 + 578 + 16, 32, this);
		}
	}

	void itemget(Graphics g, int item_num) {
		if (fq_1.MouseBK_C != 0) {
			fq_1.MouseBK_C--;
			if (fq_1.MouseBK_C == 0)
				fq_1.MT_ItemGetCont = 0;
			state_draw_allmember(g, 0);
			return;
		}
		if (fq_1.MT_ItemGetCont > 1) {
			state_draw_allmember(g, 0);
			return;
		}
		int num = state_draw_allmember(g, 1);
		if (num >= 0) {
			if (team.hito[num].ITEM != 0)
				fq_1.ITEM_EVENT = team.hito[num].ITEM;
			team.hito[num].ITEM = item_num;
			fq_1.MouseBK_C = 20;
		} else if (fq_1.Bottan_L) {
			fq_1.item_set(item_num);
			fq_1.MT_ItemGetStat = 6;
			fq_1.MT_ItemGetCont = 5;
		}
	}

	void message_serifu(Graphics g, String s_mess1, String s_mess2,
			String s_mess3) {
		int sx1 = 100;
		int sy1 = 300;
		int sy2 = 322;
		int sy3 = 344;
		int xl = 440;
		int yl = 78;
		if (s_mess1.length() <= 0)
			return;
		g.setColor(new Color(0, 0, 100));
		g.fillRect(sx1, sy1, xl, yl);
		g.setColor(new Color(230, 230, 220));
		g.drawRect(sx1, sy1, xl, yl);
		g.drawString(s_mess1, sx1 + 12, sy1 + 22);
		if (s_mess2.length() > 0)
			g.drawString(s_mess2, sx1 + 12, sy2 + 22);
		if (s_mess3.length() > 0)
			g.drawString(s_mess3, sx1 + 12, sy3 + 22);
		int mp_dx = fq_1.Paint_count / 4 & 3;
		g.drawImage(fq_1.FQ_Icon, (sx1 + xl) - 16, (sy1 + yl) - 16, sx1 + xl,
				sy1 + yl, mp_dx * 16 + 578, 16, mp_dx * 16 + 578 + 16, 32, this);
	}

	void message_yn(Graphics g, int mess_num) {
		if (mess_num < 0 || mess_num > 1) {
			return;
		} else {
			int len = YNMES[mess_num].length();
			int xl = (len + 1) * 16 + 16;
			int yl = 32;
			int sx = fq_1.AppletWidth / 2 - xl / 2;
			int sy = fq_1.AppletHeight / 2 - yl / 2 - 64;
			g.setColor(new Color(0, 50, 150));
			g.fillRect(sx, sy, xl, yl);
			g.setColor(new Color(250, 150, 50));
			g.drawRect(sx, sy, xl, yl);
			g.drawString(YNMES[mess_num], sx + 12, sy + 22);
			return;
		}
	}

	void message_mapat(Graphics g, int map_num, int mess_num) {
		if (mess_num < 3 || mess_num > 8)
			return;
		if (map_num < 0 || map_num > 59)
			return;
		switch (mess_num) {
		case 3: // '\003'
			g.setColor(new Color(100, 0, 0));
			break;

		case 5: // '\005'
			g.setColor(new Color(0, 100, 0));
			break;

		case 7: // '\007'
			g.setColor(new Color(0, 0, 100));
			break;

		case 4: // '\004'
		case 6: // '\006'
		case 8: // '\b'
		default:
			g.setColor(new Color(0, 100, 0));
			break;
		}
		String s_mess = MPNAME[map_num].concat(IVMES[mess_num]);
		int len = s_mess.length();
		int xl = (len + 1) * 16 + 16;
		int yl = 32;
		int sx = fq_1.AppletWidth / 2 - xl / 2;
		int sy = fq_1.AppletHeight / 2 - yl / 2 - 80;
		g.fillRect(sx, sy, xl, yl);
		g.setColor(Color.WHITE);
		g.drawRect(sx, sy, xl, yl);
		g.drawString(s_mess, sx + 12, sy + 22);
		int mp_dx = fq_1.Paint_count / 4 & 3;
		g.drawImage(fq_1.FQ_Icon, sx + xl + 4, (sy + yl) - 16, sx + xl + 20, sy
				+ yl, mp_dx * 16 + 578, 16, mp_dx * 16 + 578 + 16, 32, this);
	}

	void message_dead(Graphics g, int m_num, int mess_num) {
		if (mess_num > 2)
			return;
		int xl = 192;
		int yl = 48;
		switch (mess_num) {
		case 0: // '\0'
			g.setColor(new Color(0, 100, 0));
			xl = 256;
			break;

		case 1: // '\001'
		default:
			g.setColor(new Color(100, 0, 0));
			xl = 192;
			break;

		case 2: // '\002'
			g.setColor(new Color(0, 0, 100));
			xl = 208;
			break;
		}
		int sx = fq_1.AppletWidth / 2 - xl / 2;
		int sy = fq_1.AppletHeight / 2 - yl / 2 - 80;
		g.fillRect(sx, sy, xl, yl);
		g.setColor(Color.WHITE);
		g.drawRect(sx, sy, xl, yl);
		g.drawImage(fq_1.CharaImage[team.hito[m_num].CHN_D], sx - 8, sy - 8,
				(sx - 8) + team.hito[m_num].CharaWidth, (sy - 8)
						+ team.hito[m_num].CharaHeight, 0, 0,
				team.hito[m_num].CharaWidth, team.hito[m_num].CharaHeight, this);
		suuji.Name_draw(g, team.hito[m_num].HITO_NUM, 0, sx + 64, sy + 6, 8);
		suuji.paint_suuji_one(g, 21, 1, sx + 144, sy + 6);
		suuji.paint_suuji_one(g, 31, 1, sx + 144 + 8, sy + 6);
		suuji.paint_suuji(g, team.hito[m_num].LV, 1, sx + 144 + 36, sy + 6);
		g.drawString(IVMES[mess_num], sx + 64, sy + 40);
		int mp_dx = fq_1.Paint_count / 4 & 3;
		g.drawImage(fq_1.FQ_Icon, sx + xl + 4, (sy + yl) - 16, sx + xl + 20, sy
				+ yl, mp_dx * 16 + 578, 16, mp_dx * 16 + 578 + 16, 32, this);
	}

	void message_mapout(Graphics g) {
		for (int i = 0; i < 20; i++) {
			int num = fq_1.MEMBER_HAGURE[i];
			if (num >= 0) {
				if (num > 100)
					message_dead(g, num - 100, 2);
				else
					message_dead(g, num, 0);
				if (fq_1.Bottan_L && !fq_1.MouseBK) {
					fq_1.MEMBER_HAGURE[i] = -1;
					fq_1.MouseBK = true;
				}
				return;
			}
		}

		fq_1.MT_MessageCont = 3;
		fq_1.MT_MessageNum = 107;
		fq_1.MT_MapOutNum = 10;
		fq_1.i_Pouse = 0;
	}

	void message_draw(Graphics g) {
		if (fq_1.Paint_count < 64)
			message_mapname(g, 10);
		if (fq_1.MT_MessageCont != 0)
			if (fq_1.MT_MessageNum >= 1000)
				message_dead(g, fq_1.MT_MessageNum - 1000, 1);
			else if (fq_1.MT_MessageNum >= 100)
				message_mapat(g, fq_1.MT_MapOutNum, fq_1.MT_MessageNum - 100);
			else if (fq_1.MT_MessageNum == 1) {
				String s_mess1 = "\u6575\u5C06\uFF1A\u300C\u5F85\u3063\u3066\u3044\u305F\u305E\u3001\u30AB\u30FC\u30C7\u30C3\u30AF\u306E\u30B6\u30B3\u5171\u3002";
				String s_mess2 = "\u6211\u7B49\u3042\u308B\u9650\u308A\u3001\u3053\u306E\u68EE\u3092\u7121\u4E8B\u3067\u901A\u3059\u308F\u3051\u306B\u306F\u884C\u304B\u306C\uFF01\u300D";
				String s_mess3 = "";
				message_serifu(g, s_mess1, s_mess2, s_mess3);
			} else {
				String s_mess1 = "\u4E0D\u660E\u306A\u30E1\u30C3\u30BB\u30FC\u30B8\u767A\u751F\uFF01";
				String s_mess2 = "";
				String s_mess3 = "";
				message_serifu(g, s_mess1, s_mess2, s_mess3);
			}
		if (fq_1.MT_KeikokuCont != 0 && fq_1.MT_KeikokuNum >= 0
				&& fq_1.MT_KeikokuNum <= 15)
			message_keikoku(g, IVMES[fq_1.MT_KeikokuNum]);
		if (fq_1.MT_ItemGetCont != 0)
			message_itemget(g, fq_1.MT_ItemGetNum, fq_1.MT_ItemGetStat);
		int pxl = 74;
		int pyl = 32;
		int mp_dx = fq_1.Paint_count / 16 & 1;
		if (fq_1.i_Pouse == 1 && mp_dx == 1)
			g.drawImage(fq_1.FQ_Icon, 320 - pxl / 2, 360, 320 + pxl / 2,
					360 + pyl, 448, 64, 448 + pxl, 64 + pyl, this);
		if (fq_1.GameStep >= 8) {
			int fail_dxl = fq_1.Fail_count & 0x7f;
			int fail_dyl = (fail_dxl * 32) / 112;
			g.drawImage(fq_1.FQ_Icon, 264 - fail_dxl, 224 - fail_dyl,
					376 + fail_dxl, 256 + fail_dyl, 528, 64, 640, 64 + pyl,
					this);
			if (fq_1.Fail_count < 128)
				fq_1.Fail_count++;
			else
				fq_1.GameStep = 9;
		}
	}

	void mapatr_draw(Graphics g) {
		if (fq_1.b_DrawTestAtr) {
			for (int j = 0; j < 24; j++) {
				for (int i = 0; i < 40; i++) {
					int ch = fq_1.mapatrdata[(fq_1.Map_SY + j) * fq_1.MXLM
							+ fq_1.Map_SX + i];
					byte by = fq_1.mapatrdata[(fq_1.Map_SY + j) * fq_1.MXLM
							+ fq_1.Map_SX + i];
					if (ch < 0)
						ch += 256;
					if (ch != 0) {
						if (fq_1.DrawTestAtrState == 0)
							suuji.paint_16suu(g, (char) ch, i & 1, i * 16, j
									* 16 + fq_1.GameSY);
						if (fq_1.DrawTestAtrState == 1) {
							int isi = ch & 3;
							if (fq_1.Bottan_L && !fq_1.MouseBK
									&& fq_1.Map_SX + i == fq_1.Map_Mouse_X
									&& fq_1.Map_SY + j == fq_1.Map_Mouse_Y) {
								isi = ++isi & 3;
								by &= 0xfc;
								by |= isi;
								fq_1.mapatrdata[(fq_1.Map_SY + j) * fq_1.MXLM
										+ fq_1.Map_SX + i] = by;
								fq_1.MouseBK = true;
							}
							switch (isi) {
							case 1: // '\001'
								g.drawImage(fq_1.FQ_Icon, i * 16, j * 16
										+ fq_1.GameSY, i * 16 + 16, j * 16
										+ fq_1.GameSY + 16, 400, 16, 416, 32,
										this);
								break;

							case 2: // '\002'
								g.drawImage(fq_1.FQ_Icon, i * 16, j * 16
										+ fq_1.GameSY, i * 16 + 16, j * 16
										+ fq_1.GameSY + 16, 96, 16, 112, 32,
										this);
								break;

							case 3: // '\003'
								g.drawImage(fq_1.FQ_Icon, i * 16, j * 16
										+ fq_1.GameSY, i * 16 + 16, j * 16
										+ fq_1.GameSY + 16, 608, 16, 624, 32,
										this);
								break;
							}
						}
						if (fq_1.DrawTestAtrState == 2) {
							if (fq_1.Bottan_L && !fq_1.MouseBK
									&& fq_1.Map_SX + i == fq_1.Map_Mouse_X
									&& fq_1.Map_SY + j == fq_1.Map_Mouse_Y) {
								by &= 0x7f;
								if ((ch & 0x80) == 0)
									by |= 0x80;
								fq_1.mapatrdata[(fq_1.Map_SY + j) * fq_1.MXLM
										+ fq_1.Map_SX + i] = by;
								fq_1.MouseBK = true;
							}
							if ((ch & 0x80) != 0)
								g.drawImage(fq_1.FQ_Icon, i * 16, j * 16
										+ fq_1.GameSY, i * 16 + 16, j * 16
										+ fq_1.GameSY + 16, 464, 0, 480, 16,
										this);
						}
						if (fq_1.DrawTestAtrState == 3) {
							int ya = ch / 16 & 7;
							if (fq_1.Bottan_L && !fq_1.MouseBK
									&& fq_1.Map_SX + i == fq_1.Map_Mouse_X
									&& fq_1.Map_SY + j == fq_1.Map_Mouse_Y) {
								ya = ++ya & 7;
								by &= 0x8f;
								by |= ya * 16;
								fq_1.mapatrdata[(fq_1.Map_SY + j) * fq_1.MXLM
										+ fq_1.Map_SX + i] = by;
								fq_1.MouseBK = true;
							}
							switch (ya) {
							case 1: // '\001'
								g.drawImage(fq_1.FQ_Icon, i * 16, j * 16
										+ fq_1.GameSY, i * 16 + 16, j * 16
										+ fq_1.GameSY + 16, 128, 0, 144, 16,
										this);
								break;

							case 2: // '\002'
								g.drawImage(fq_1.FQ_Icon, i * 16, j * 16
										+ fq_1.GameSY, i * 16 + 16, j * 16
										+ fq_1.GameSY + 16, 144, 0, 160, 16,
										this);
								break;

							case 3: // '\003'
								g.drawImage(fq_1.FQ_Icon, i * 16, j * 16
										+ fq_1.GameSY, i * 16 + 16, j * 16
										+ fq_1.GameSY + 16, 160, 0, 176, 16,
										this);
								break;

							case 4: // '\004'
								g.drawImage(fq_1.FQ_Icon, i * 16, j * 16
										+ fq_1.GameSY, i * 16 + 16, j * 16
										+ fq_1.GameSY + 16, 176, 0, 192, 16,
										this);
								break;

							case 5: // '\005'
								g.drawImage(fq_1.FQ_Icon, i * 16, j * 16
										+ fq_1.GameSY, i * 16 + 16, j * 16
										+ fq_1.GameSY + 16, 66, 0, 82, 16, this);
								break;

							case 6: // '\006'
								g.drawImage(fq_1.FQ_Icon, i * 16, j * 16
										+ fq_1.GameSY, i * 16 + 16, j * 16
										+ fq_1.GameSY + 16, 80, 16, 96, 32,
										this);
								break;

							case 7: // '\007'
								g.drawImage(fq_1.FQ_Icon, i * 16, j * 16
										+ fq_1.GameSY, i * 16 + 16, j * 16
										+ fq_1.GameSY + 16, 528, 0, 544, 16,
										this);
								break;
							}
						}
					}
				}

			}

		}
	}

	int get_item_mark(int i_num, int fl) {
		if (i_num <= 0 || i_num > 30)
			return 0;
		else
			return Item_Mrk[i_num * 2 + (fl & 1)];
	}

	void item_mark_draw(Graphics g, int m_num, int sx, int sy) {
		int i_num = team.hito[m_num].ITEM;
		if (i_num <= 0)
			return;
		int mrk = get_item_mark(i_num, 0);
		if (mrk <= 0) {
			return;
		} else {
			suuji.mark_draw(g, mrk, sx, sy);
			mrk = get_item_mark(i_num, 1);
			suuji.paint_suuji_one(g, mrk, 0, sx + 16, sy);
			return;
		}
	}

	int get_state_mark(int m_num) {
		if ((team.hito[m_num].FLG1 & 0x13) != 0)
			return 3;
		if ((team.hito[m_num].FLG1 & 0x44) != 0)
			return 1;
		return (team.hito[m_num].FLG3 & 0x84) == 0 ? 0 : 2;
	}

	int get_state_kao(int m_num) {
		if ((team.hito[m_num].FL & 7) != 0)
			return 11;
		if ((team.hito[m_num].FL & 0x30) != 0)
			return 8;
		int mlif = team.hito[m_num].MLIF;
		int lif = team.hito[m_num].LIFE;
		if (lif > mlif / 2)
			return 6;
		if (lif > mlif / 4)
			return 5;
		return lif <= mlif / 8 ? 10 : 9;
	}

	void state_mark_draw(Graphics g, int m_num, int sx, int sy) {
		int mrk = get_state_mark(m_num);
		suuji.mark_draw(g, mrk, sx - 4, sy);
		mrk = get_state_kao(m_num);
		suuji.mark_draw(g, mrk, sx + 12, sy);
	}

	void statetus_draw(Graphics g, int ran, int num, int m_num, int sx, int sy) {
		switch (ran) {
		case 0: // '\0'
		default:
			suuji.Name_draw(g, num, 1, sx, sy, 3);
			break;

		case 1: // '\001'
			item_mark_draw(g, m_num, sx, sy);
			break;

		case 2: // '\002'
			suuji.paint_suuji(g, team.hito[m_num].LV, 1, sx + 24, sy);
			break;

		case 3: // '\003'
			state_mark_draw(g, m_num, sx, sy);
			break;

		case 4: // '\004'
			suuji.paint_suuji(g, 100 - team.hito[m_num].FATIG, 2, sx + 24, sy);
			break;

		case 5: // '\005'
			suuji.paint_suuji(g, team.hito[m_num].PAR, 3, sx + 24, sy);
			break;

		case 6: // '\006'
			suuji.paint_suuji(g, team.hito[m_num].PDR, 4, sx + 24, sy);
			break;

		case 7: // '\007'
			suuji.paint_suuji(g, team.hito[m_num].PAT, 3, sx + 24, sy);
			break;

		case 8: // '\b'
			suuji.paint_suuji(g, team.hito[m_num].PDF, 4, sx + 24, sy);
			break;

		case 9: // '\t'
			suuji.paint_suuji(g, team.hito[m_num].PYD, 7, sx + 24, sy);
			break;

		case 10: // '\n'
			suuji.paint_suuji(g, team.hito[m_num].PMD, 7, sx + 24, sy);
			break;

		case 11: // '\013'
			suuji.paint_suuji(g, team.hito[m_num].HEAL, 2, sx + 24, sy);
			break;
		}
	}

	void item_irekae(Graphics g) {
		int m_num = 0;
		int ynum = 0;
		g.setFont(fq_1.font_s);
		g.setColor(Color.WHITE);
		int st_sx2 = 32;
		int st_sy2 = 46;
		int mouse_letu = -1;
		g.drawImage(fq_1.MemberSel, st_sx2, st_sy2, this);
		st_sy2 += 40;
		if (fq_1.MouseX > st_sx2 + 430 && fq_1.MouseX < st_sx2 + 550
				&& fq_1.MouseY > st_sy2 && fq_1.MouseY < st_sy2 + 320)
			mouse_letu = (fq_1.MouseY - st_sy2) / 16;
		for (m_num = 0; m_num < fq_1.Total_Hito; m_num++)
			if ((team.hito[m_num].FL & 8) == 0
					&& fq_1.Control_Team == team.hito[m_num].TM_NUM) {
				team.kougr(m_num);
				team.bougr(m_num);
				int heal_n = team.heal(m_num);
				suuji.Name_draw(g, team.hito[m_num].HITO_NUM, 0, st_sx2 + 8,
						st_sy2 + ynum * 16, 8);
				suuji.Class_draw(g, team.hito[m_num].CharaClass, 1,
						st_sx2 + 100, st_sy2 + ynum * 16, 8);
				suuji.paint_suuji(g, team.hito[m_num].MLIF, 0,
						st_sx2 + 190 + 16, st_sy2 + ynum * 16);
				suuji.paint_suuji(g, team.hito[m_num].LV, 1, st_sx2 + 222 + 16,
						st_sy2 + ynum * 16);
				suuji.paint_suuji(g, team.man_at2, 3, st_sx2 + 254 + 16, st_sy2
						+ ynum * 16);
				suuji.paint_suuji(g, team.man_ar2, 3, st_sx2 + 286 + 16, st_sy2
						+ ynum * 16);
				suuji.paint_suuji(g, team.man_de2, 4, st_sx2 + 318 + 16, st_sy2
						+ ynum * 16);
				suuji.paint_suuji(g, team.man_dr2, 4, st_sx2 + 350 + 16, st_sy2
						+ ynum * 16);
				suuji.paint_suuji(g, heal_n, 2, st_sx2 + 384 + 16, st_sy2
						+ ynum * 16);
				if (fq_1.item_hito == team.hito[m_num].TOOSI_NUM) {
					g.setColor(new Color(230, 100, 0));
					g.drawRect(st_sx2 + 430, st_sy2 + ynum * 16, 120, 16);
				} else {
					g.setColor(new Color(230, 230, 230));
				}
				if (team.hito[m_num].ITEM > 0)
					g.drawString(ITEMNAME[team.hito[m_num].ITEM], st_sx2 + 432,
							st_sy2 + ynum * 16 + 12);
				if (mouse_letu == ynum) {
					g.setColor(new Color(100, 230, 230));
					g.drawRect(st_sx2 + 430, st_sy2 + ynum * 16, 120, 16);
					if (fq_1.Bottan_L && !fq_1.MouseBK) {
						if (fq_1.item_hito < 0) {
							fq_1.item_hito = team.hito[m_num].TOOSI_NUM;
						} else {
							int item = team.hito[fq_1.item_hito].ITEM;
							team.hito[fq_1.item_hito].ITEM = team.hito[m_num].ITEM;
							team.hito[m_num].ITEM = item;
							fq_1.item_hito = -1;
						}
						fq_1.MouseBK = true;
					}
				}
				ynum++;
			}

		g.setFont(fq_1.font);
		itiran_title(g, 0);
	}

	int state_draw_allmember(Graphics g, int flg) {
		int m_num = 0;
		int ynum = 0;
		g.setFont(fq_1.font_s);
		g.setColor(Color.WHITE);
		int st_sx2 = 32;
		int st_sy2 = 46;
		int mouse_letu = -1;
		int select = -1;
		g.drawImage(fq_1.MemberSel, st_sx2, st_sy2, this);
		st_sy2 += 40;
		if (fq_1.MouseX > st_sx2 + 8 && fq_1.MouseX < st_sx2 + 400
				&& fq_1.MouseY > st_sy2 && fq_1.MouseY < st_sy2 + 320)
			mouse_letu = (fq_1.MouseY - st_sy2) / 16;
		for (m_num = 0; m_num < fq_1.Total_Hito; m_num++)
			if ((team.hito[m_num].FL & 8) == 0
					&& fq_1.Control_Team == team.hito[m_num].TM_NUM) {
				team.kougr(m_num);
				team.bougr(m_num);
				int heal_n = team.heal(m_num);
				suuji.Name_draw(g, team.hito[m_num].HITO_NUM, 0, st_sx2 + 8,
						st_sy2 + ynum * 16, 8);
				suuji.Class_draw(g, team.hito[m_num].CharaClass, 1,
						st_sx2 + 100, st_sy2 + ynum * 16, 8);
				suuji.paint_suuji(g, team.hito[m_num].MLIF, 0,
						st_sx2 + 190 + 16, st_sy2 + ynum * 16);
				suuji.paint_suuji(g, team.hito[m_num].LV, 1, st_sx2 + 222 + 16,
						st_sy2 + ynum * 16);
				suuji.paint_suuji(g, team.man_at2, 3, st_sx2 + 254 + 16, st_sy2
						+ ynum * 16);
				suuji.paint_suuji(g, team.man_ar2, 3, st_sx2 + 286 + 16, st_sy2
						+ ynum * 16);
				suuji.paint_suuji(g, team.man_de2, 4, st_sx2 + 318 + 16, st_sy2
						+ ynum * 16);
				suuji.paint_suuji(g, team.man_dr2, 4, st_sx2 + 350 + 16, st_sy2
						+ ynum * 16);
				suuji.paint_suuji(g, heal_n, 2, st_sx2 + 384 + 16, st_sy2
						+ ynum * 16);
				if (team.hito[m_num].ITEM > 0)
					g.drawString(ITEMNAME[team.hito[m_num].ITEM], st_sx2 + 432,
							st_sy2 + ynum * 16 + 12);
				if (flg != 0 && mouse_letu == ynum) {
					g.drawRect(st_sx2 + 4, st_sy2 + ynum * 16, 408, 16);
					if (fq_1.Bottan_L && !fq_1.MouseBK) {
						select = team.hito[m_num].TOOSI_NUM;
						fq_1.MouseBK = true;
					}
				}
				ynum++;
			}

		g.setFont(fq_1.font);
		return select;
	}

	void state_draw_kojin(Graphics g) {
		int cnt_mk_x = team.hito[fq_1.Control_Hito].drawx + 16;
		int cnt_mk_y = team.hito[fq_1.Control_Hito].drawy + fq_1.GameSY + 16;
		int st_sx = 16;
		int st_sy = 240;
		if (cnt_mk_x > -16 && cnt_mk_x < 640 && cnt_mk_y >= 24
				&& cnt_mk_y < 408) {
			if (cnt_mk_x <= 320)
				st_sx = 528;
			g.drawImage(fq_1.MiniSt, st_sx, st_sy, this);
			team.kougr(fq_1.Control_Hito);
			team.bougr(fq_1.Control_Hito);
			suuji.Name_draw(g, team.hito[fq_1.Control_Hito].HITO_NUM, 0,
					st_sx + 12, st_sy + 20, 8);
			suuji.Class_draw(g, team.hito[fq_1.Control_Hito].CharaClass, 1,
					st_sx + 16, st_sy + 40, 8);
			suuji.paint_suuji(g, team.hito[fq_1.Control_Hito].LV, 1,
					st_sx + 76, st_sy + 58);
			suuji.paint_suuji(g, team.man_at2, 3, st_sx + 76, st_sy + 74);
			suuji.paint_suuji(g, team.man_ar2, 3, st_sx + 76, st_sy + 90);
			suuji.paint_suuji(g, team.man_de2, 4, st_sx + 76, st_sy + 106);
			suuji.paint_suuji(g, team.man_dr2, 4, st_sx + 76, st_sy + 122);
			if (team.hito[fq_1.Control_Hito].ITEM > 0)
				item_mark_draw(g, fq_1.Control_Hito, st_sx + 16, st_sy + 138);
		}
	}

	void state_draw(Graphics g) {
		int sx = 0;
		int sxoff = 4;
		int ran_1 = 428;
		int ran_2 = 444;
		int ran_3 = 460;
		int pos = 0;
		int m_num = 0;
		int dispn = 0;
		int color = 0;
		for (m_num = 0; m_num < fq_1.Total_Hito; m_num++)
			if ((team.hito[m_num].FL & 8) == 0
					&& fq_1.Control_Team == team.hito[m_num].TM_NUM) {
				team.hito[m_num].TM_POS1 = pos;
				pos++;
				if (team.hito[m_num].TOOSI_NUM == fq_1.Control_Hito)
					g.drawImage(fq_1.FQ_Icon, dispn * 32, ran_2,
							dispn * 32 + 32, ran_2 + 16, 352, 48, 384, 64, this);
				sx = dispn * 32 + sxoff;
				int num = team.hito[m_num].HITO_NUM;
				statetus_draw(g, fq_1.DispLine_1, num, m_num, sx, ran_1);
				int mlif = team.hito[m_num].MLIF;
				int life = team.hito[m_num].LIFE;
				color = 2;
				if (life > mlif / 8)
					color = 5;
				if (life > mlif / 4)
					color = 4;
				if (life > mlif / 2)
					color = 0;
				if (life == 0)
					color = 6;
				suuji.paint_suuji(g, life, color, sx + 24, ran_2);
				statetus_draw(g, fq_1.DispLine_3, num, m_num, sx, ran_3);
				dispn++;
			}

		team.NINZU[fq_1.Control_Team] = dispn;
		if (fq_1.GameStep == 2 && dispn <= 0)
			fq_1.GameStep = 8;
		switch (fq_1.i_Pouse) {
		default:
			break;

		case 2: // '\002'
			state_draw_kojin(g);
			break;

		case 3: // '\003'
			item_irekae(g);
			break;

		case 4: // '\004'
			int num = state_draw_allmember(g, 1);
			itiran_title(g, 1);
			if (num >= 0) {
				team.hito[num].FL |= 8;
				team.form_change(fq_1.Control_Team,
						team.FORM[fq_1.Control_Team]);
			}
			break;

		case 5: // '\005'
			message_yn(g, 0);
			if (!fq_1.b_YN_MODE)
				break;
			if (fq_1.i_YN > 0) {
				fq_1.i_Pouse = 6;
				fq_1.i_YN = 0;
				fq_1.b_YN_MODE = false;
				break;
			}
			if (fq_1.i_YN < 0) {
				fq_1.i_Pouse = 0;
				fq_1.i_YN = 0;
				fq_1.b_YN_MODE = false;
			}
			break;

		case 6: // '\006'
			message_mapout(g);
			break;
		}
	}

	void title_iconbase(Graphics g, int x, int y, int flg) {
		switch (flg) {
		case 0: // '\0'
		default:
			g.drawImage(fq_1.FQ_Icon, x, y, x + 32, y + 32, 192, 32, 224, 64,
					this);
			break;

		case 1: // '\001'
			g.drawImage(fq_1.FQ_Icon, x, y, x + 32, y + 32, 224, 32, 256, 64,
					this);
			break;

		case 2: // '\002'
			g.drawImage(fq_1.FQ_Icon, x, y, x + 32, y + 32, 256, 32, 288, 64,
					this);
			break;

		case 3: // '\003'
			g.drawImage(fq_1.FQ_Icon, x, y, x + 32, y + 32, 288, 32, 320, 64,
					this);
			break;
		}
	}

	void title_draw(Graphics g) {
		g.drawImage(fq_1.FQ_Title, 0, 0, this);
		if (fq_1.CommandCode == 0)
			title_iconbase(g, ic_tuujyo, ic_y, 2);
		else
			title_iconbase(g, ic_tuujyo, ic_y, 3);
		g.drawImage(fq_1.FQ_Icon, ic_tuujyo, ic_y, ic_tuujyo + 32, ic_y + 32,
				0, 64, 32, 96, this);
		if (fq_1.CommandCode == 1)
			title_iconbase(g, ic_kouige, ic_y, 2);
		else
			title_iconbase(g, ic_kouige, ic_y, 3);
		g.drawImage(fq_1.FQ_Icon, ic_kouige, ic_y, ic_kouige + 32, ic_y + 32,
				32, 64, 64, 96, this);
		if (fq_1.CommandCode == 2)
			title_iconbase(g, ic_nige, ic_y, 2);
		else
			title_iconbase(g, ic_nige, ic_y, 3);
		g.drawImage(fq_1.FQ_Icon, ic_nige, ic_y, ic_nige + 32, ic_y + 32, 64,
				64, 96, 96, this);
		if (fq_1.CommandCode == 3)
			title_iconbase(g, ic_teisi, ic_y, 2);
		else
			title_iconbase(g, ic_teisi, ic_y, 3);
		g.drawImage(fq_1.FQ_Icon, ic_teisi, ic_y, ic_teisi + 32, ic_y + 32, 96,
				64, 128, 96, this);
		if (fq_1.i_Pouse != 0)
			title_iconbase(g, ic_pouse, ic_y, 2);
		else
			title_iconbase(g, ic_pouse, ic_y, 3);
		g.drawImage(fq_1.FQ_Icon, ic_pouse, ic_y, ic_pouse + 32, ic_y + 32,
				192, 64, 224, 96, this);
	}

	void mark_draw(Graphics g) {
		int cnt_mk_xc = 0;
		int cnt_mk_yc = 0;
		int cnt_mk_cc = -1;
		if (fq_1.DispMark != 2) {
			for (int m_num = 0; m_num < fq_1.Total_Hito; m_num++) {
				if ((team.hito[m_num].FL & 8) == 0
						&& fq_1.Control_Team == team.hito[m_num].TM_NUM) {
					int cnt_mk_x = team.hito[m_num].drawx + 12;
					int cnt_mk_y = team.hito[m_num].drawy + fq_1.GameSY + 12;
					if (cnt_mk_x > -16 && cnt_mk_x < 640 && cnt_mk_y >= 32
							&& cnt_mk_y < 416)
						if (team.hito[m_num].TOOSI_NUM == fq_1.Control_Hito) {
							cnt_mk_cc = 448 + 16 * (fq_1.Sys_Count1 & 3);
							cnt_mk_xc = cnt_mk_x;
							cnt_mk_yc = cnt_mk_y;
						} else if (fq_1.DispMark == 0) {
							int cnt_mk_c = 512 + 16 * (fq_1.Sys_Count1 & 3);
							g.drawImage(fq_1.FQ_Icon, cnt_mk_x, cnt_mk_y,
									cnt_mk_x + 16, cnt_mk_y + 16, cnt_mk_c, 0,
									cnt_mk_c + 16, 16, this);
						}
				}
				if ((team.hito[m_num].FLG2 & 0x10) == 0
						&& (team.hito[m_num].FL & 8) == 0 && fq_1.b_DrawLife) {
					int gage_len_x = 24;
					int gage_len_y = 2;
					int gage_len_lif = (gage_len_x * team.hito[m_num].LIFE)
							/ team.hito[m_num].MLIF;
					int gage_len_stam = (gage_len_x * (100 - team.hito[m_num].FATIG)) / 100;
					int gage_mk_x = team.hito[m_num].drawx + 16;
					int gage_mk_y = team.hito[m_num].drawy + fq_1.GameSY + 52;
					if (gage_mk_x > -24 && gage_mk_x < 640 && gage_mk_y >= 40
							&& gage_mk_y < 424) {
						if (fq_1.b_DrawFatig) {
							g.setColor(Color.black);
							g.fillRect(gage_mk_x - 1, gage_mk_y - 1,
									gage_len_x + 2, 2 * gage_len_y + 3);
							g.setColor(Color.green);
							g.fillRect(gage_mk_x, gage_mk_y, gage_len_lif,
									gage_len_y);
							if (gage_len_lif < gage_len_x) {
								g.setColor(Color.red);
								g.fillRect(gage_mk_x + gage_len_lif, gage_mk_y,
										gage_len_x - gage_len_lif, gage_len_y);
							}
							g.setColor(Color.orange);
							g.fillRect(gage_mk_x, gage_mk_y + gage_len_y + 1,
									gage_len_stam, gage_len_y);
						}
						g.setColor(Color.black);
						g.fillRect(gage_mk_x - 1, gage_mk_y - 1,
								gage_len_x + 2, gage_len_y + 2);
						g.setColor(Color.green);
						g.fillRect(gage_mk_x, gage_mk_y, gage_len_lif,
								gage_len_y);
						if (gage_len_lif < gage_len_x) {
							g.setColor(Color.red);
							g.fillRect(gage_mk_x + gage_len_lif, gage_mk_y,
									gage_len_x - gage_len_lif, gage_len_y);
						}
					}
				}
			}

			if (cnt_mk_cc >= 0)
				g.drawImage(fq_1.FQ_Icon, cnt_mk_xc, cnt_mk_yc, cnt_mk_xc + 16,
						cnt_mk_yc + 16, cnt_mk_cc, 0, cnt_mk_cc + 16, 16, this);
		}
		for (int m_num = 0; m_num < fq_1.Total_Hito; m_num++)
			if ((team.hito[m_num].FL & 0xf) == 0
					&& team.hito[m_num].LEVELUPC > 0) {
				int cnt_mk_x = team.hito[m_num].drawx + 20;
				int cnt_mk_y = ((team.hito[m_num].drawy + fq_1.GameSY) - 12)
						+ team.hito[m_num].LEVELUPC;
				if (cnt_mk_x > -16 && cnt_mk_x < 640 && cnt_mk_y >= 32
						&& cnt_mk_y < 416)
					suuji.LvUp_draw(g, cnt_mk_x, cnt_mk_y);
			}

	}

	public void paint(Graphics g) {
		fq_1.Map_Mouse_X = (fq_1.DispMapX + fq_1.MouseX) / 16;
		fq_1.Map_Mouse_Y = ((fq_1.DispMapY + fq_1.MouseY) - 40) / 16;
		title_draw(g);
		g.drawImage(fq_1.Bottom, 0, 424, this);
		mark_draw(g);
		state_draw(g);
		message_draw(g);
		fq_1.Paint_count++;
	}

	private static final long serialVersionUID = 1L;
	Applet applet;
	int Item_Mrk[] = { 0, 0, 12, 14, 22, 28, 12, 22, 20, 27, 21, 19, 33, 27,
			18, 22, 22, 25, 33, 19, 25, 15, 25, 18, 25, 21, 25, 13, 25, 13, 25,
			13, 25, 11, 24, 16, 25, 28, 25, 13, 25, 27, 24, 22, 24, 28, 29, 14,
			25, 28, 33, 12, 25, 29, 28, 25, 28, 12, 28, 28, 28, 16 };
	String MPNAME[] = { "\u30AB\u30FC\u30C7\u30A3\u30C3\u30AF\u57CE",
			"\u30AB\u30FC\u30C7\u30A3\u30C3\u30AF\u306E\u7826",
			"\u30AB\u30FC\u30C7\u30A3\u30C3\u30AF\u306E\u753A",
			"\u30B7\u30A7\u30D5\u30A3\u30FC\u30EB\u30C9\u306E\u5357",
			"\u30D5\u30A9\u30FC\u30B9\u6D77\u5CB8\u306E\u5357",
			"\u30B7\u30A7\u30D5\u30A3\u30FC\u30EB\u30C9\u306E\u897F",
			"\u30B7\u30A7\u30D5\u30A3\u30FC\u30EB\u30C9\u8349\u539F",
			"\u30D5\u30A9\u30FC\u30B9\u6D77\u5CB8",
			"\u30BD\u30EB\u30BA\u30D9\u30EA\u30FC\u306E\u5357",
			"\u30D5\u30A9\u30FC\u30B9\u6D77\u5CB8\u306E\u5317",
			"\u30BD\u30EB\u30BA\u30D9\u30EA\u30FC\u306E\u68EE",
			"\u30BD\u30EB\u30BA\u30D9\u30EA\u30FC\u306E\u6751",
			"\u30A2\u30F3\u30EC\u30FC\u306E\u8349\u539F",
			"\u30BD\u30EB\u30BA\u30D9\u30EA\u30FC\u306E\u897F",
			"\u30BD\u30EB\u30BA\u30D9\u30EA\u30FC\u306E\u7826",
			"\u30B3\u30F3\u30A6\u30A9\u30FC\u30EB\u306E\u5357",
			"\u30A2\u30F3\u30EC\u30FC\u306E\u68EE",
			"\u30BD\u30EB\u30BA\u30D9\u30EA\u30FC\u306E\u5317",
			"\u30B3\u30F3\u30A6\u30A9\u30FC\u30EB\u306E\u68EE",
			"\u30AD\u30E3\u30E1\u30ED\u30C3\u30C8\u306E\u5357",
			"\u30A2\u30F3\u30EC\u30FC\u306E\u753A",
			"\u30A8\u30C9\u30A6\u30A3\u30F3\u57CE",
			"\u30A8\u30C9\u30A6\u30A3\u30F3\u306E\u68EE",
			"\u30B3\u30F3\u30A6\u30A9\u30FC\u30EB\u306E\u7826",
			"\u30B3\u30F3\u30A6\u30A9\u30FC\u30EB\u306E\u6751",
			"\u30B3\u30F3\u30A6\u30A9\u30FC\u30EB\u306E\u5317",
			"\u30AD\u30E3\u30E1\u30ED\u30C3\u30C8",
			"\u30AD\u30E3\u30E1\u30ED\u30C3\u30C8\u57CE",
			"\u30A8\u30C9\u30A6\u30A3\u30F3\u8349\u539F",
			"\u30EC\u30B8\u30F3\u30D7\u306E\u4E18",
			"\u30B5\u30F3\u30EA\u30B9\u306E\u8C37",
			"\u30D9\u30CB\u30C3\u30AF\u306E\u5893\u5730",
			"\u30D9\u30CB\u30C3\u30AF\u306E\u5357", "\u6CBC\u306E\u9928",
			"\u30EB\u30FC\u30C8\u30F3\u306E\u6797",
			"\u30CE\u30FC\u30D5\u30A9\u30FC\u30AF\u306E\u6751",
			"\u30B5\u30F3\u30EA\u30B9\u306E\u5C71\u9053",
			"\u30D9\u30CB\u30C3\u30AF\u57CE",
			"\u30D9\u30CB\u30C3\u30AF\u6E56\u6CBC",
			"\u30D9\u30CB\u30C3\u30AF\u306E\u5317",
			"\u30EB\u30FC\u30C8\u30F3\u8349\u539F",
			"\u30CE\u30FC\u30D5\u30A9\u30FC\u30AF\u306E\u68EE",
			"\u30B5\u30F3\u30EA\u30B9\u306E\u5854",
			"\u30EB\u30FC\u30C8\u30F3\u306E\u5C71\u9053",
			"\u30EA\u30C3\u30C1\u30E2\u30F3\u30C9\u306E\u8C37",
			"\u30EB\u30FC\u30C8\u30F3\u306E\u4E18",
			"\u30EA\u30C3\u30C1\u30E2\u30F3\u30C9\u306E\u5D16",
			"\u30EA\u30C3\u30C1\u30E2\u30F3\u30C9\u306E\u6751",
			"\u30B0\u30FC\u30EB\u306E\u6D1E\u7A74",
			"\u30B0\u30FC\u30EB\u306E\u795E\u6BBF",
			"\u30B5\u30E9\u30B9\u306E\u96EA\u5C71",
			"\u30B5\u30E9\u30B9\u306E\u5C71\u9053",
			"\u30AA\u30EB\u30CB\u30C3\u30AF\u306E\u8C37",
			"\u30AA\u30EB\u30CB\u30C3\u30AF\u57CE", "\u6C7A\u6226\u5834" };
	int ic_y;
	int ic_soku;
	int ic_book;
	int ic_camp;
	int ic_form;
	int ic_tuujyo;
	int ic_kouige;
	int ic_nige;
	int ic_teisi;
	int ic_pouse;
	TeamManeger team;
	Suuji suuji;
	Fq_1 fq_1;
	String MemberTiltl[] = { "\u30A2\u30A4\u30C6\u30E0\u4EA4\u63DB",
			"\u30E1\u30F3\u30D0\u30FC\u89E3\u96C7" };
	String ITEMNAME[] = { " ",
			"\u30A8\u30AF\u30B9\u30AD\u30E3\u30EA\u30D0\u30FC",
			"\u8056\u306A\u308B\u6307\u8F2A",
			"\u30E2\u30F3\u30B9\u30BF\u30FC\u30B9\u30EC\u30A4\u30E4\u30FC",
			"\u8679\u306E\u76FE", "\u30E8\u30CF\u30CD\u306E\u76FE",
			"\u8D64\u5341\u5B57", "\u9B54\u6CD5\u306E\u77E2",
			"\u529B\u306E\u6307\u8F2A",
			"\u30AD\u30EA\u30B9\u30C8\u306E\u5341\u5B57", "\u708E\u306E\u77F3",
			"\u96EA\u306E\u7D50\u6676", "\u5149\u306E\u77F3",
			"\u95C7\u306E\u77F3", "\u95C7\u306E\u77F3", "\u95C7\u306E\u77F3",
			"\u5674\u706B\u306E\u77F3",
			"\u5927\u5730\u306E\u30A2\u30DF\u30E5\u30EC\u30C3\u30C8",
			"\u7A32\u59BB\u306E\u77F3", "\u7834\u58CA\u306E\u77F3",
			"\u5FA9\u6D3B\u306E\u77F3",
			"\u6708\u306E\u30A2\u30DF\u30E5\u30EC\u30C3\u30C8",
			"\u592A\u967D\u306E\u30A2\u30DF\u30E5\u30EC\u30C3\u30C8",
			"\u30A8\u30EB\u30D5\u306E\u7B1B",
			"\u796D\u58C7\u306E\u3069\u304F\u308D", "\u7D0B\u7AE0",
			"\u6D99\u306E\u6EF4\u77F3", "\u7262\u5C4B\u306E\u9375",
			"\u9285\u306E\u9375", "\u9280\u306E\u9375", "\u91D1\u306E\u9375" };
	String ITEMGETMES[] = {
			"\u3092\u3000\u898B\u3064\u3051\u305F\uFF01",
			"\u3092\u3000\u53D6\u308A\u307E\u3059\u304B(\uFF39\uFF0F\uFF2E)",
			"\u3092\u3000\u8AB0\u304C\u6301\u3061\u307E\u3059\u304B\uFF1F",
			"\u3092\u3000\u6368\u3066\u307E\u3059\u3002",
			"\u3092\u3000\u6368\u3066\u3066\u3044\u3044\u3067\u3059\u304B\uFF1F",
			"\u3092\u3000\u3068\u308A\u307E\u3057\u305F\u3002",
			"\u3092\u3000\u4ED5\u821E\u3044\u307E\u3057\u305F\u3002" };
	String YNMES[] = {
			"\u3053\u306E\u5834\u6240\u304B\u3089\u51FA\u307E\u3059\u304B\uFF1F(\uFF39\uFF0F\uFF2E)",
			"\u53D6\u308A\u307E\u3059\u304B\uFF1F(\uFF39\uFF0F\uFF2E)" };
	String IVMES[] = {
			"\u304C\u3000\u306F\u3050\u308C\u3066\u3057\u307E\u3063\u305F\u3002",
			"\u304C\u3000\u5012\u3055\u308C\u305F\uFF01",
			"\u306F\u3000\u3064\u304B\u307E\u3063\u305F\u3002",
			"\u306B\u3000\u6575\u304C\u653B\u3081\u8FBC\u3093\u3060\uFF01",
			"\u6575\u304C\u3044\u308B\u3002\u6226\u95D8\u306B\u306A\u308B",
			"\u306B\u3000\u6575\u304C\u3042\u3089\u308F\u308C\u305F\u3002",
			"\u3069\u3061\u3089\u306B\u3001\u884C\u304D\u307E\u3059\u304B\uFF1F",
			"\u306B\u3000\u9032\u307F\u307E\u3059\u3002",
			"\u306B\u3000\u6575\u304C\u79FB\u52D5\u3057\u305F\u3002",
			"\u6575\u304C\u5473\u65B9\u306B\u3064\u3044\u305F\uFF01",
			"\u6575\u304C\u3044\u3066\u9032\u3081\u307E\u305B\u3093\uFF01",
			"\u3053\u3053\u3067\u306F\u3001\uFF23\uFF21\uFF2D\uFF30\u3067\u304D\u307E\u305B\u3093\u3002",
			"\uFF23\uFF21\uFF2D\uFF30\u3057\u307E\u3059\u3002",
			"\uFF23\uFF21\uFF2D\uFF30\u3059\u308B\u306B\u306F\u5371\u967A\u306A\u69D8\u5B50\u3067\u3059\u3002",
			"\u30DE\u30A6\u30B9\u3067\u306E\u30C0\u30A4\u30EC\u30AF\u30C8\u30C1\u30A7\u30F3\u30B8\uFF2F\uFF2E\uFF01",
			"\u30DE\u30A6\u30B9\u3067\u306E\u30C0\u30A4\u30EC\u30AF\u30C8\u30C1\u30A7\u30F3\u30B8\uFF2F\uFF26\uFF26" };
}
