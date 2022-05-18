// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Hitobuf.java

package applet;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.io.PrintStream;

// Referenced classes of package applet:
//            Gocha, Fq_1, Grand, YaManeger

class TeamManeger extends Applet {

	public int Hito_X(int hito_toosinum) {
		return hito[hito_toosinum].X;
	}

	public int Hito_Y(int hito_toosinum) {
		return hito[hito_toosinum].Y;
	}

	public int Hito_XMOKU(int hito_toosinum) {
		return hito[hito_toosinum].XMOKU;
	}

	public int Hito_YMOKU(int hito_toosinum) {
		return hito[hito_toosinum].YMOKU;
	}

	public int Hito_DIR(int hito_toosinum) {
		return hito[hito_toosinum].DIR;
	}

	public void MapItemtSet(int map_num) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 4; j++) {
				if (MapItemPoint_10[i][j] < 0)
					return;
				fq_1.MAP_ITEM[i * 4 + j] = MapItemPoint_10[i][j];
			}

		}

	}

	public void flg_cls() {
		if (fq_1.Total_Hito == 0)
			return;
		for (int i = 0; i < fq_1.Total_Hito; i++)
			if ((hito[i].FL & 0xf) == 0) {
				hito[i].T_NERAWARE = 0;
				hito[i].T_KAKOMARE = 0;
			}

		for (int i = 0; i < TotalTeam; i++)
			TEKI[i] &= 0xffffffc3;

	}

	public boolean hagure_set(int mem) {
		if (mem < 0)
			return false;
		for (int i = 0; i < 20; i++)
			if (fq_1.MEMBER_HAGURE[i] < 0) {
				fq_1.MEMBER_HAGURE[i] = mem;
				return true;
			}

		return false;
	}

	public void Team_MapOut(int flug) {
		int mx = hito[fq_1.Control_Hito].X;
		int my = hito[fq_1.Control_Hito].Y;
		if (flug == 1) {
			int teamnum = 1;
			if (fq_1.Control_Team == 1)
				teamnum = 0;
			int tnin = GetNINZU(teamnum);
			if (tnin != 0) {
				fq_1.MT_KeikokuCont = 3;
				fq_1.MT_KeikokuNum = 10;
				return;
			}
		}
		for (int i = 0; i < fq_1.Total_Hito; i++)
			if ((hito[i].FL & 0xf) == 0 && hito[i].TM_NUM == fq_1.Control_Team
					&& i != fq_1.Control_Hito)
				if ((hito[i].FLG1 & 4) != 0 || (hito[i].FL & 0x30) != 0) {
					int aite = hito[i].AITE_D;
					if (aite >= 0 && (hito[aite].MTOK & 0x40) == 0)
						hagure_set(i + 100);
				} else {
					int len = fq_1.kyori(mx, my, hito[i].X, hito[i].Y);
					if (len >= 128)
						hagure_set(i);
					else if (len >= 64 && fq_1.random((128 - len) / 8 + 1) == 0)
						hagure_set(i);
				}

		fq_1.i_Pouse = 5;
		fq_1.b_YN_MODE = true;
	}

	public void Team_MapPointKaijyo(int team_num) {
		for (int i = 0; i < fq_1.Total_Hito; i++)
			if ((hito[i].FL & 0xf) == 0 && hito[i].TM_NUM == team_num) {
				hito[i].FLG2 &= 0xffffffef;
				hito[i].FLG3 &= -5;
			}

	}

	public void Team_MapPointSet(int team_num, int data_num) {
		if (data_num > 2)
			return;
		int suu = 0;
		for (int i = 0; i < fq_1.Total_Hito; i++)
			if ((hito[i].FL & 0xf) == 0 && hito[i].TM_NUM == team_num) {
				hito[i].X = MapSetPoint[data_num][suu * 2 + 0];
				hito[i].Y = MapSetPoint[data_num][suu * 2 + 1];
				hito[i].XO = hito[i].X;
				hito[i].YO = hito[i].Y;
				hito[i].XO2 = hito[i].X;
				hito[i].YO2 = hito[i].Y;
				hito[i].XNE = hito[i].X;
				hito[i].YNE = hito[i].Y;
				hito[i].XMOKU = hito[i].X;
				hito[i].YMOKU = hito[i].Y;
				hito[i].FLG2 |= 0x10;
				hito[i].FLG3 |= 4;
				if (++suu >= 20)
					return;
			}

	}

	public void hito_command() {
		for (int m_num = 0; m_num < fq_1.Total_Hito; m_num++)
			if ((hito[m_num].FL & 8) == 0
					&& fq_1.Control_Team == hito[m_num].TM_NUM)
				switch (fq_1.CommandCode) {
				case 0: // '\0'
					hito[m_num].FLG3 &= -5;
					hito[m_num].FLG1 &= 0xffffffbd;
					hito[m_num].FLG2 &= 0xffffffef;
					break;

				case 1: // '\001'
					hito[m_num].FLG3 &= -5;
					hito[m_num].FLG1 |= 0x40;
					hito[m_num].FLG1 &= -3;
					hito[m_num].FLG2 &= 0xffffffef;
					break;

				case 2: // '\002'
					hito[m_num].FLG3 &= -5;
					hito[m_num].FLG1 &= 0xffffffbf;
					hito[m_num].FLG2 &= 0xffffffef;
					break;

				case 3: // '\003'
					hito[m_num].FLG3 |= 4;
					hito[m_num].FLG1 &= 0xffffffbd;
					break;
				}

	}

	public void form_tuibi_in(int x, int y) {
		if (Formation_abs[0] == x && Formation_abs[1] == y)
			return;
		for (int i = 59; i > 0; i--) {
			Formation_abs[i * 2] = Formation_abs[(i - 1) * 2];
			Formation_abs[i * 2 + 1] = Formation_abs[(i - 1) * 2 + 1];
		}

		Formation_abs[0] = x;
		Formation_abs[1] = y;
	}

	public void form_tuibi_cls(int x, int y) {
		for (int i = 0; i < 80; i++) {
			Formation_abs[i * 2] = x;
			Formation_abs[i * 2 + 1] = y;
		}

	}

	public void form_change(int team_num, int form_num) {
		if (fq_1.Total_Hito == 0)
			return;
		if (form_num > 16)
			return;
		FORM[team_num] = form_num;
		int pos = -1;
		for (int i = 0; i < fq_1.Total_Hito; i++)
			if ((hito[i].FL & 0xf) == 0 && hito[i].TM_NUM == team_num) {
				if (pos < 0) {
					hito[i].XFOR = 0;
					hito[i].YFOR = 0;
				} else {
					hito[i].XFOR = Formation[FORM[team_num]][pos * 2];
					hito[i].YFOR = Formation[FORM[team_num]][pos * 2 + 1];
				}
				if (pos < 20)
					pos++;
			}

	}

	public int onfire_line(int hito_toosinum, int mx, int my, int maxlen) {
		int zoku = hito[hito_toosinum].ZOKUJ;
		int minlen = maxlen;
		int nowdir = -1;
		for (int i = 0; i < fq_1.Total_Hito; i++)
			if ((hito[i].FL & 0xf) == 0 && (hito[i].FLG2 & 0x10) == 0
					&& i != hito_toosinum) {
				int zoku_t = hito[i].ZOKUJ;
				int dx = hito[i].X - mx;
				int dy = hito[i].Y - my;
				if (zoku_t != zoku) {
					if (dy < 0 && -dy < maxlen && (dx == 0 || dx == 1)) {
						int len = -dy;
						if (len < minlen) {
							minlen = len;
							nowdir = 0;
						}
					}
					if (dx < 0 && -dx < maxlen && (dy == 0 || dy == 1)) {
						int len = -dx;
						if (len < minlen) {
							minlen = len;
							nowdir = 1;
						}
					}
					if (dy > 0 && dy < maxlen && (dx == 0 || dx == 1)) {
						int len = dy;
						if (len < minlen) {
							minlen = len;
							nowdir = 2;
						}
					}
					if (dx > 0 && dx < maxlen && (dy == 0 || dy == 1)) {
						int len = dx;
						if (len < minlen) {
							minlen = len;
							nowdir = 3;
						}
					}
				}
			}

		return nowdir;
	}

	public int teki_iruka(int hito_toosinum, int mx, int my, int maxlen) {
		int zoku = hito[hito_toosinum].ZOKUJ;
		for (int i = 0; i < fq_1.Total_Hito; i++)
			if ((hito[i].FL & 8) == 0 && (hito[i].FLG2 & 0x10) == 0
					&& i != hito_toosinum) {
				int zoku_t = hito[i].ZOKUJ;
				if (zoku_t != zoku
						&& fq_1.kyori(mx, my, hito[i].X, hito[i].Y) < maxlen)
					return i;
			}

		return -1;
	}

	public int teki_search(int hito_toosinum, int mx, int my, int maxlen,
			int gamennai, int kakomisuu) {
		int zoku = hito[hito_toosinum].ZOKUJ;
		int mini = maxlen;
		int teki = -1;
		for (int i = 0; i < fq_1.Total_Hito; i++)
			if ((hito[i].FL & 0xf) == 0 && (hito[i].FLG2 & 0x10) == 0
					&& i != hito_toosinum) {
				int zoku_t = hito[i].ZOKUJ;
				if (zoku_t != zoku && hito[i].T_KAKOMARE < kakomisuu
						&& (gamennai == 0 || (hito[i].FL & 0x80) != 0)) {
					int tx = hito[i].X;
					int ty = hito[i].Y;
					int len = fq_1.kyori(mx, my, tx, ty);
					if (len < mini) {
						mini = len;
						teki = i;
					}
				}
			}

		if (teki >= 0) {
			hito[hito_toosinum].AITE_A = teki;
			hito[hito_toosinum].AITE_D = teki;
			hito[hito_toosinum].XNE = hito[teki].X;
			hito[hito_toosinum].YNE = hito[teki].Y;
			hito[hito_toosinum].TEKI_LEN = mini;
			hito[teki].T_NERAWARE++;
			return teki;
		} else {
			return -1;
		}
	}

	public int teki_search_op1(int hito_toosinum, int mx, int my, int maxlen,
			int gamennai, int kakomisuu) {
		int zoku = hito[hito_toosinum].ZOKUJ;
		int mini = maxlen;
		int teki = -1;
		for (int i = 0; i < fq_1.Total_Hito; i++)
			if ((hito[i].FL & 0xf) == 0 && i != hito_toosinum) {
				int zoku_t = hito[i].ZOKUJ;
				if (zoku_t != zoku
						&& hito[i].T_KAKOMARE < kakomisuu
						&& ((hito[i].FL & 0x30) != 0 || (hito[i].FLG1 & 5) != 0)
						&& (gamennai == 0 || (hito[i].FL & 0x80) != 0)) {
					int tx = hito[i].X;
					int ty = hito[i].Y;
					int len = fq_1.kyori(mx, my, tx, ty);
					if (len < mini) {
						mini = len;
						teki = i;
					}
				}
			}

		if (teki >= 0) {
			hito[hito_toosinum].AITE_A = teki;
			hito[hito_toosinum].AITE_D = teki;
			hito[hito_toosinum].XNE = hito[teki].X;
			hito[hito_toosinum].YNE = hito[teki].Y;
			hito[hito_toosinum].TEKI_LEN = mini;
			hito[teki].T_NERAWARE++;
			return teki;
		} else {
			return -1;
		}
	}

	public void kougr(int atack) {
		int ch = hito[atack].PAT;
		int dl = hito[atack].PAR;
		int item = hito[atack].ITEM;
		switch (item) {
		case 1: // '\001'
			ch += 40;
			break;

		case 2: // '\002'
			dl += 16;
			break;

		case 8: // '\b'
			ch += 12;
			break;
		}
		man_at2 = ch;
		man_ar2 = dl;
		int isi = hito[atack].MapToku & 3;
		if (isi == 2 && (hito[atack].MTOK & 2) != 0)
			ch /= 2;
		man_at = ch;
		man_ar = dl / 4;
	}

	public void bougr(int yarare) {
		int ch = hito[yarare].PDF;
		int dl = hito[yarare].PDR;
		int item = hito[yarare].ITEM;
		switch (item) {
		case 5: // '\005'
			ch += 12;
			break;

		case 8: // '\b'
			ch += 12;
			break;
		}
		man_de2 = ch;
		man_dr2 = dl;
		int isi = hito[yarare].MapToku & 3;
		if (isi == 2 && (hito[yarare].MTOK & 2) != 0)
			ch /= 2;
		man_de = ch;
		man_dr = dl / 4;
	}

	public int heal(int hito_num) {
		int ch = hito[hito_num].HEAL;
		if (hito[hito_num].ITEM == 6)
			ch += 16;
		return ch;
	}

	void knock(int yarare, int d) {
		int xn = hito[yarare].X;
		int yn = hito[yarare].Y;
		if ((hito[yarare].MTOK & 0x20) != 0)
			return;
		if (d >= 0 && d < 4) {
			if (hito[yarare].map_houkou_atari(d, xn, yn))
				return;
			if ((hito[yarare].BitMapAtari & 4) != 0)
				return;
			xn += hito[yarare].dx;
			yn += hito[yarare].dy;
			int dd = hito_check_move(yarare, xn, yn, hito[yarare].DIR, 0);
			if (dd < 0) {
				hito[yarare].XO2 = hito[yarare].XO;
				hito[yarare].YO2 = hito[yarare].YO;
				hito[yarare].XO = hito[yarare].X;
				hito[yarare].YO = hito[yarare].Y;
				hito[yarare].X = xn;
				hito[yarare].Y = yn;
				hito[yarare].XH = hito[yarare].X;
				hito[yarare].YH = hito[yarare].Y;
				hito[yarare].mov_off = 0;
			}
		}
	}

	public void team_change(int pn_flug) {
		for (int i = 0; i < TotalTeam; i++) {
			if (pn_flug > 0) {
				fq_1.Control_Team++;
				if (fq_1.Control_Team >= TotalTeam)
					fq_1.Control_Team = 0;
				if (NINZU[fq_1.Control_Team] != 0)
					break;
				continue;
			}
			fq_1.Control_Team--;
			if (fq_1.Control_Team < 0)
				fq_1.Control_Team = TotalTeam - 1;
			if (NINZU[fq_1.Control_Team] != 0)
				break;
		}

		for (int m_num = 0; m_num < fq_1.Total_Hito; m_num++)
			if ((hito[m_num].FL & 0xf) == 0
					&& fq_1.Control_Team == hito[m_num].TM_NUM) {
				fq_1.Control_Hito = hito[m_num].TOOSI_NUM;
				hito[fq_1.Control_Hito].FLG2 &= 0xffffff7f;
				return;
			}

	}

	public int hito_gadd_test(int gx, int gy) {
		int cxs = 0;
		int cys = 0;
		int cxe = 0;
		int cye = 0;
		for (int m_num = 0; m_num < fq_1.Total_Hito; m_num++)
			if ((hito[m_num].FL & 0xf) == 0
					&& fq_1.Control_Hito != hito[m_num].TOOSI_NUM
					&& fq_1.Control_Team == hito[m_num].TM_NUM) {
				cxs = hito[m_num].drawx + 16;
				cys = hito[m_num].drawy + fq_1.GameSY + 16;
				cxe = cxs + hito[m_num].PLM;
				cye = cys + hito[m_num].PLM;
				if (cxs < gx && gx < cxe && cys < gy && gy < cye) {
					fq_1.Control_Hito = hito[m_num].TOOSI_NUM;
					hito[fq_1.Control_Hito].FLG2 &= 0xffffff7f;
					return m_num;
				}
			}

		return -1;
	}

	public void hito_change(int pos) {
		for (int m_num = 0; m_num < fq_1.Total_Hito; m_num++)
			if ((hito[m_num].FL & 0xf) == 0
					&& fq_1.Control_Team == hito[m_num].TM_NUM
					&& pos == hito[m_num].TM_POS1) {
				fq_1.Control_Hito = hito[m_num].TOOSI_NUM;
				hito[fq_1.Control_Hito].FLG2 &= 0xffffff7f;
				return;
			}

	}

	public void hito_change_pn(int pn_flug) {
		if (NINZU[fq_1.Control_Team] <= 1)
			return;
		int m_num = fq_1.Control_Hito;
		if (pn_flug > 0) {
			if (++m_num >= fq_1.Total_Hito)
				m_num = 0;
			for (int i = 0; i < fq_1.Total_Hito; i++) {
				if (fq_1.Control_Team == hito[m_num].TM_NUM
						&& (hito[m_num].FL & 0xf) == 0) {
					fq_1.Control_Hito = hito[m_num].TOOSI_NUM;
					hito[fq_1.Control_Hito].FLG2 &= 0xffffff7f;
					return;
				}
				if (++m_num >= fq_1.Total_Hito)
					m_num = 0;
			}

		} else {
			if (--m_num < 0)
				m_num = fq_1.Total_Hito - 1;
			for (int i = 0; i < fq_1.Total_Hito; i++) {
				if (fq_1.Control_Team == hito[m_num].TM_NUM
						&& (hito[m_num].FL & 0xf) == 0) {
					fq_1.Control_Hito = hito[m_num].TOOSI_NUM;
					hito[fq_1.Control_Hito].FLG2 &= 0xffffff7f;
					return;
				}
				if (--m_num < 0)
					m_num = fq_1.Total_Hito - 1;
			}

		}
	}

	int GetNINZU(int team_num) {
		if (team_num < 0 && team_num >= TotalTeam)
			return -1;
		int pos = 0;
		for (int i = 0; i < fq_1.Total_Hito; i++)
			if ((hito[i].FL & 8) == 0 && team_num == hito[i].TM_NUM)
				pos++;

		return pos;
	}

	void Taikyaku(int team_num) {
		if (team_num < 0 && team_num >= TotalTeam)
			return;
		for (int m_num = 0; m_num < fq_1.Total_Hito; m_num++)
			if ((hito[m_num].FL & 0xf) == 0 && team_num == hito[m_num].TM_NUM)
				hito[m_num].FLG1 |= 2;

	}

	void Totugeki(int team_num) {
		if (team_num < 0 && team_num >= TotalTeam)
			return;
		for (int m_num = 0; m_num < fq_1.Total_Hito; m_num++)
			if ((hito[m_num].FL & 0xf) == 0 && team_num == hito[m_num].TM_NUM)
				hito[m_num].FLG1 |= 0x80;

	}

	int GetBoss(int team_num) {
		if (team_num < 0 && team_num >= TotalTeam)
			return -1;
		for (int i = 0; i < fq_1.Total_Hito; i++)
			if (team_num == hito[i].TM_NUM && (hito[i].FL & 0xf) == 0)
				return i;

		return -1;
	}

	void SetAllDir(int team_num, int hito_dir) {
		if (team_num < 0 && team_num >= TotalTeam)
			return;
		if (hito_dir >= 0 && hito_dir < 4) {
			for (int i = 0; i < fq_1.Total_Hito; i++)
				if (team_num == hito[i].TM_NUM && (hito[i].FL & 0xf) == 0)
					hito[i].DIR = hito_dir;

		}
	}

	void SetBoss(int team_num, int hito_num) {
		if (team_num < 0 && team_num >= TotalTeam)
			return;
		LEADER[team_num] = hito_num;
		if (hito_num >= 0) {
			for (int i = 0; i < fq_1.Total_Hito; i++)
				if (team_num == hito[i].TM_NUM && (hito[i].FL & 0xf) == 0)
					hito[i].TM_BOSS = hito_num;

		}
	}

	public void dead(int yarare) {
		if (hito[yarare].ITEM == 9) {
			hito[yarare].LIFE = hito[yarare].MLIF;
			hito[yarare].ITEM = 0;
			return;
		}
		if (NINZU[hito[yarare].TM_NUM] > 0)
			NINZU[hito[yarare].TM_NUM]--;
		if (yarare == fq_1.Control_Hito)
			hito_change(0);
		hito[yarare].LIFE = 0;
		hito[yarare].FL |= 6;
		if (hito[yarare].TM_POS1 == 0) {
			TEKI[hito[yarare].TM_NUM] |= 0x80;
			if (fq_1.Control_Team != hito[yarare].TM_NUM
					&& (hito[yarare].MTOK & 0x40) == 0
					&& hito[yarare].LSHIP < 7)
				Taikyaku(hito[yarare].TM_NUM);
			int new_b = GetBoss(hito[yarare].TM_NUM);
			SetBoss(hito[yarare].TM_NUM, new_b);
			if (fq_1.Control_Team == hito[yarare].TM_NUM)
				form_tuibi_cls(hito[new_b].X, hito[new_b].Y);
		}
		if ((hito[yarare].MTOK & 0x40) == 0) {
			int team_num = hito[yarare].TM_NUM;
			form_change(team_num, FORM[team_num]);
		}
		if ((hito[yarare].FL & 0x80) != 0)
			fq_1.EffectSound[2].play();
	}

	public void negaeri(int atack, int yarare) {
		if (NINZU[hito[atack].TM_NUM] >= 20)
			return;
		if (NINZU[hito[yarare].TM_NUM] == 0) {
			return;
		} else {
			fq_1.MT_KeikokuCont = 5;
			fq_1.MT_KeikokuNum = 9;
			NINZU[hito[atack].TM_NUM]++;
			NINZU[hito[yarare].TM_NUM]--;
			int team_num = hito[yarare].TM_NUM;
			hito[yarare].TM_NUM = hito[atack].TM_NUM;
			hito[yarare].TM_BOSS = hito[atack].TM_BOSS;
			hito[yarare].ZOKUJ = hito[atack].ZOKUJ;
			hito[yarare].ZOKUA = hito[atack].ZOKUA;
			hito[yarare].FLG1 &= -3;
			hito[yarare].CUNT1 = 0;
			hito[yarare].DIR_NIGE = hito[atack].DIR_NIGE;
			form_change(team_num, FORM[team_num]);
			team_num = hito[atack].TM_NUM;
			form_change(team_num, FORM[team_num]);
			hito_command();
			return;
		}
	}

	public void givedom(int atack, int yarare, int damege) {
		int dead_flg = 0;
		hito[yarare].AITE_D = atack;
		hito[yarare].FL |= 0x30;
		damege = damege / 4 + 1;
		hito[yarare].LIFE -= damege;
		if (hito[yarare].LIFE <= 0) {
			hito[yarare].LIFE = 0;
			dead_flg = 1;
		}
		TEKI[hito[yarare].TM_NUM] |= 0x40;
		levelup(atack, dead_flg);
		if ((TEKI[hito[yarare].TM_NUM] & 0x80) == 0)
			return;
		if ((hito[yarare].FLG1 & 1) == 0)
			return;
		if ((hito[yarare].FLG1 & 0x80) != 0)
			return;
		if (hito[yarare].TM_NUM == fq_1.Control_Team)
			return;
		if (hito[yarare].HITO_NUM >= 448)
			return;
		if (fq_1.random(hito[yarare].LSHIP) != 0) {
			return;
		} else {
			negaeri(atack, yarare);
			return;
		}
	}

	public void levelup(int atack, int dead_flg) {
		if (hito[atack].TM_NUM != fq_1.Control_Team)
			return;
		if (dead_flg != 0 && fq_1.random(hito[atack].LV / 10 + 2) != 0)
			return;
		hito[atack].LEVELC++;
		if (hito[atack].LEVELC < hito[atack].LV * 2)
			return;
		hito[atack].LEVELC = 0;
		if (hito[atack].LV < 99) {
			hito[atack].LV++;
			int uphp = 5 + fq_1.random(3);
			if (hito[atack].MLIF + uphp > 999)
				uphp = 999 - hito[atack].MLIF;
			hito[atack].MLIF += uphp;
			hito[atack].LIFE += uphp;
			int uppara = 4 + fq_1.random(3);
			switch (hito[atack].LV & 3) {
			default:
				break;

			case 0: // '\0'
				hito[atack].PAT += uppara;
				if (hito[atack].PAT > 99)
					hito[atack].PAT = 99;
				break;

			case 1: // '\001'
				hito[atack].PDF += uppara;
				if (hito[atack].PDF > 99)
					hito[atack].PDF = 99;
				break;

			case 2: // '\002'
				hito[atack].PAR += uppara;
				if (hito[atack].PAR > 99)
					hito[atack].PAR = 99;
				break;

			case 3: // '\003'
				hito[atack].PDR += uppara;
				if (hito[atack].PDR > 99)
					hito[atack].PDR = 99;
				break;
			}
			int upheal = 2 + fq_1.random(2);
			if ((hito[atack].LV & 3) == 0) {
				hito[atack].HEAL += upheal;
				if (hito[atack].HEAL > 99)
					hito[atack].HEAL = 99;
			}
			if (hito[atack].LEVELUPC == 0)
				hito[atack].LEVELUPC = 36;
		}
	}

	public void attatta(int atack, int yarare) {
		int item_a = hito[atack].ITEM;
		int item_y = hito[yarare].ITEM;
		int sound = fq_1.random(2);
		if ((hito[yarare].FLG2 & 0x10) != 0
				|| hito[yarare].TM_NUM != fq_1.Control_Team
				&& (hito[yarare].FLG3 & 4) != 0)
			Team_MapPointKaijyo(hito[yarare].TM_NUM);
		TEKI[hito[atack].TM_NUM] |= 4;
		hito[atack].FLG1 |= 4;
		hito[yarare].FLG1 |= 4;
		hito[atack].AITE_A = yarare;
		hito[yarare].AITE_D = atack;
		hito[yarare].T_KAKOMARE++;
		hito[atack].XNE = hito[yarare].X;
		hito[atack].YNE = hito[yarare].Y;
		hito[yarare].XNE = hito[atack].X;
		hito[yarare].YNE = hito[atack].Y;
		if ((hito[atack].FLG2 & 0x80) == 0) {
			if (hito[atack].mov_off != 0)
				return;
		} else if ((fq_1.Sys_Count1 & 3) != 0)
			return;
		if ((hito[atack].FL & 0x30) != 0 || (hito[yarare].FL & 0x30) != 0)
			return;
		kougr(atack);
		int rand = fq_1.random(20);
		if (man_ar > rand) {
			if (hito[atack].FATIG < 100)
				hito[atack].FATIG++;
			if (hito[atack].AT_CNT == 0)
				hito[atack].AT_CNT = 8;
			bougr(yarare);
			int damege = man_at - man_de;
			if (damege <= 0)
				damege = 1;
			if (item_a == 3 && (hito[yarare].MTOK & 0x40) != 0)
				damege *= 2;
			if (item_y == 4 && (hito[atack].MTOK & 0x40) != 0)
				return;
			rand = fq_1.random(20);
			if (damege <= 0 || man_dr > rand) {
				if (fq_1.random(4) > 1)
					sound = 4;
			} else {
				if (fq_1.random(4) > 2)
					sound = 3;
				if (hito[yarare].mov_off == 0)
					knock(yarare, hito[atack].DIR);
				givedom(atack, yarare, damege);
				if (hito[yarare].FATIG < 100)
					hito[yarare].FATIG++;
				if (hito[yarare].LIFE <= 0) {
					dead(yarare);
					sound = -1;
				}
			}
			if ((hito[atack].FL & 0x80) != 0)
				fq_1.set_RandSE(sound, 5);
		}
	}

	public int teki_check(int hito_toosinum) {
		int zoku = hito[hito_toosinum].ZOKUJ;
		int mx = hito[hito_toosinum].X;
		int my = hito[hito_toosinum].Y;
		int mlen = hito[hito_toosinum].XLM;
		for (int i = 0; i < fq_1.Total_Hito; i++)
			if ((hito[i].FL & 0xf) == 0 && i != hito_toosinum) {
				int zoku_t = hito[i].ZOKUJ;
				if (zoku_t != zoku) {
					int tx = hito[i].X;
					int ty = hito[i].Y;
					int alen = hito[i].XLM;
					int hit_d = fq_1.Attack(mx, my, tx, ty, mlen, alen);
					if (hit_d >= 0)
						return i;
				}
			}

		return -1;
	}

	public int teki_hit_check(int hito_toosinum) {
		if ((hito[hito_toosinum].FL & 0xf) != 0)
			return -1;
		int zoku = hito[hito_toosinum].ZOKUJ;
		int mx = hito[hito_toosinum].X;
		int my = hito[hito_toosinum].Y;
		int mlen = hito[hito_toosinum].XLM;
		hito[hito_toosinum].FLG1 &= -5;
		for (int i = 0; i < fq_1.Total_Hito; i++)
			if ((hito[i].FL & 0xf) == 0 && i != hito_toosinum) {
				int zoku_t = hito[i].ZOKUJ;
				if (zoku_t != zoku) {
					int tx = hito[i].X;
					int ty = hito[i].Y;
					int alen = hito[i].XLM;
					int hit_d = fq_1.Attack(mx, my, tx, ty, mlen, alen);
					if (hit_d >= 0) {
						attatta(hito_toosinum, i);
						hito[hito_toosinum].TEKI_LEN = 0;
						if ((hito[hito_toosinum].FLG2 & 0x80) != 0)
							hito[hito_toosinum].DIR = hit_d;
						if ((hito[i].FLG2 & 0x80) != 0)
							hito[i].DIR = hit_d + 2 & 3;
						if ((hito[i].FL & 0x30) != 0)
							return i;
					}
				}
			}

		return -1;
	}

	public int hito_hit_check_move(int hito_toosinum, int mx, int my, int mlen,
			int teki_only) {
		int zoku = hito[hito_toosinum].ZOKUJ;
		for (int i = 0; i < fq_1.Total_Hito; i++) {
			int zoku_t = hito[i].ZOKUJ;
			if ((teki_only == 0 || zoku_t != zoku) && (hito[i].FL & 8) == 0
					&& i != hito_toosinum) {
				int tx = hito[i].X;
				int ty = hito[i].Y;
				int mokux = hito[i].XMOKU;
				int mokuy = hito[i].YMOKU;
				int alen_x = hito[i].XLM;
				int alen_y = hito[i].XLM;
				if (mokux == tx + 1)
					alen_x = hito[i].XLM + 1;
				else if (mokux == tx - 1) {
					tx = mokux;
					alen_x = hito[i].XLM + 1;
				}
				if (mokuy == ty + 1)
					alen_y = hito[i].XLM + 1;
				else if (mokuy == ty - 1) {
					ty = mokuy;
					alen_y = hito[i].XLM + 1;
				}
				if (fq_1.Headon(mx, my, tx, ty, mlen, alen_x, alen_y))
					return i;
			}
		}

		return -1;
	}

	public int hito_check_move(int hito_toosinum, int mx, int my, int dir,
			int teki_only) {
		int zoku = hito[hito_toosinum].ZOKUJ;
		for (int i = 0; i < fq_1.Total_Hito; i++) {
			int zoku_t = hito[i].ZOKUJ;
			if ((teki_only == 0 || zoku_t != zoku) && (hito[i].FL & 8) == 0
					&& i != hito_toosinum) {
				int tx = hito[i].X;
				int ty = hito[i].Y;
				int alen_x = hito[i].XLM;
				int alen_y = hito[i].XLM;
				int mokux = hito[i].XMOKU;
				int mokuy = hito[i].YMOKU;
				if (mokux == tx + 1)
					alen_x = hito[i].XLM + 1;
				else if (mokux == tx - 1) {
					tx = mokux;
					alen_x = hito[i].XLM + 1;
				}
				if (mokuy == ty + 1)
					alen_y = hito[i].XLM + 1;
				else if (mokuy == ty - 1) {
					ty = mokuy;
					alen_y = hito[i].XLM + 1;
				}
				if (fq_1.Headon_dir(mx, my, tx, ty, dir, alen_x, alen_y))
					return i;
			}
		}

		return -1;
	}

	public int hito_check_move2(int hito_toosinum, int mx, int my, int dir) {
		int zoku = hito[hito_toosinum].ZOKUJ;
		for (int i = 0; i < fq_1.Total_Hito; i++) {
			int zoku_t = hito[i].ZOKUJ;
			if (zoku_t != zoku && (hito[i].FL & 8) == 0 && i != hito_toosinum) {
				int tx = hito[i].X;
				int ty = hito[i].Y;
				int alen_x = hito[i].XLM;
				int alen_y = hito[i].XLM;
				if (fq_1.Headon_dir(mx, my, tx, ty, dir, alen_x, alen_y))
					return i;
			}
		}

		return -1;
	}

	int Gr_Tensou(int dist_num, int sorc_num, int flug) {
		if (dist_num < 0 || dist_num >= 20)
			return -1;
		for (int i = 0; i < 34; i++) {
			int byt = mgrsu_m[sorc_num][i];
			if (i < 14)
				fq_1.Set_grbuf(dist_num, i, byt);
			else
				fq_1.Set_grbuf_mannum(dist_num, i - 14, byt);
		}

		return 0;
	}

	void MakeTeamFromGrBuf(int t_num, int zoku, int init_X, int init_Y,
			int form, int nige_dir) {
		NINZU[TotalTeam] = fq_1.Get_grbuf(t_num, TM_NINZU);
		LEADX = init_X;
		LEADY = init_Y;
		EVENT[TotalTeam] = fq_1.Get_grbuf(t_num, TM_EVENT);
		SERIFU[TotalTeam] = fq_1.Get_grbuf(t_num, TM_SERIFU);
		KOD[TotalTeam] = fq_1.Get_grbuf(t_num, TM_KOD);
		KOH[TotalTeam] = fq_1.Get_grbuf(t_num, TM_KOH);
		SCJYULE[TotalTeam] = fq_1.Get_grbuf(t_num, TM_SCJYULE);
		FORM[TotalTeam] = form;
		TEKI[TotalTeam] = 0;
		ZOKUJ[TotalTeam] = fq_1.Get_grbuf(t_num, TM_ZOKUJ);
		if (NINZU[TotalTeam] <= 0) {
			System.out.println("grope set error.");
			return;
		}
		int pos = -1;
		int boss = fq_1.Total_Hito;
		for (int i = 0; i < NINZU[TotalTeam]; i++) {
			int num = fq_1.Get_grbuf_mannum(t_num, i);
			if (num >= 0 && num < 512) {
				int chn = fq_1.Get_hpara(num, Hp_CHR);
				if (chn >= 0 && chn < 68) {
					hito[fq_1.Total_Hito].initialize(fq_1.Total_Hito, chn,
							TotalTeam, num, pos, boss, 0, LEADX, LEADY, 0, 0,
							zoku, nige_dir);
					if (pos < 0) {
						hito[fq_1.Total_Hito].XFOR = 0;
						hito[fq_1.Total_Hito].YFOR = 0;
					} else {
						hito[fq_1.Total_Hito].XFOR = Formation[FORM[TotalTeam]][pos * 2];
						hito[fq_1.Total_Hito].YFOR = Formation[FORM[TotalTeam]][pos * 2 + 1];
					}
					fq_1.Total_Hito++;
					if (pos < 20)
						pos++;
				}
			}
		}

		TotalTeam++;
	}

	void Map_TekiTeam(int Map_num) {
		for (int i = 16; i < 72; i++) {
			MAP = fq_1.Get_grbuf(i, TM_MAP);
			if (fq_1.Get_grbuf(i, TM_NINZU) > 0 && MAP == Map_num)
				MakeTeamFromGrBuf(i, 1, 40, 41, 1, 0);
		}

	}

	void MakeMonster(int chn, int suu, int set_fl, int init_XS, int init_YS,
			int init_XE, int init_YE) {
		int pos = 0;
		int boss = fq_1.Total_Hito;
		if (chn < 0 || chn >= 68)
			return;
		int num = fq_1.HparaNum[chn];
		NINZU[TotalTeam] = suu;
		for (int i = 0; i < suu; i++) {
			hito[fq_1.Total_Hito].initialize(fq_1.Total_Hito, chn, TotalTeam,
					num, pos, boss, set_fl, init_XS, init_YS, init_XE, init_YE,
					1, 4);
			hito[fq_1.Total_Hito].PAT *= 3;
			if (hito[fq_1.Total_Hito].PAT > 99)
				hito[fq_1.Total_Hito].PAT = 99;
			fq_1.Total_Hito++;
			pos++;
		}

		TotalTeam++;
	}

	void Map_Monster(int Map_num) {
		int index = 0;
		for (int i = 0; i < 33; i++) {
			index = i;
			if (mons_m[i] == Map_num)
				break;
			if (mons_m[i] >= 255)
				return;
		}

		int flug = mons_tab[index][0];
		int kazu = mons_tab[index][1];
		int jyouken = mons_tab[index][2];
		int chara = mons_tab[index][3];
		int suu = mons_tab[index][4];
		int xs = mons_tab[index][5];
		int xe = mons_tab[index][6];
		int ys = mons_tab[index][7];
		int ye = mons_tab[index][8];
		if (flug > 0 && jyouken >= 0) {
			if (suu <= 0)
				suu = 20;
			MakeMonster(chara, suu, -1, xs, ys, xe, ye);
			if (kazu > 1) {
				chara = mons_tab[index][9];
				suu = mons_tab[index][10];
				xs = mons_tab[index][11];
				xe = mons_tab[index][12];
				ys = mons_tab[index][13];
				ye = mons_tab[index][14];
				MakeMonster(chara, suu, -1, xs, ys, xe, ye);
			}
		}
	}

	public TeamManeger(Applet applet, Grand grand, YaManeger ya) {
		hito = new Gocha[80];
		drawbuf_p = new int[80];
		drawbuf = new int[80];
		TotalTeam = 0;
		TM_MAP = 0;
		TM_NINZU = 1;
		NINZU = new int[8];
		TM_LEADX = 2;
		TM_LEADY = 3;
		LEADER = new int[8];
		TM_EVENT = 6;
		EVENT = new int[8];
		TM_SERIFU = 7;
		SERIFU = new int[8];
		TM_KOD = 8;
		KOD = new int[8];
		TM_SCJYULE = 9;
		SCJYULE = new int[8];
		TM_FORM = 10;
		FORM = new int[8];
		TM_TEKI = 13;
		TEKI = new int[8];
		TM_KOH = 14;
		KOH = new int[8];
		TM_ZOKUJ = 15;
		ZOKUJ = new int[8];
		Hp_CHR = 8;
		man_at = 0;
		man_at2 = 0;
		man_ar = 0;
		man_ar2 = 0;
		man_de = 0;
		man_de2 = 0;
		man_dr = 0;
		man_dr2 = 0;
		this.applet = applet;
		fq_1 = (Fq_1) applet;
		this.grand = grand;
		this.ya = ya;
		for (int i = 0; i < 80; i++)
			hito[i] = new Gocha(applet, this, grand, ya);

	}

	public void Team_Init() {
		MapItemtSet(10);
		fq_1.Total_Hito = 0;
		TotalTeam = 0;
		LEADER[TotalTeam] = fq_1.Total_Hito;
		MakeTeamFromGrBuf(1, 0, 32, 242, 2, 2);
		SetAllDir(0, 0);
		form_tuibi_cls(hito[LEADER[0]].X, hito[LEADER[0]].Y);
		hito[1].ITEM = 10;
		LEADER[TotalTeam] = fq_1.Total_Hito;
		Map_TekiTeam(10);
		hito[LEADER[1]].LSHIP = 0;
		hito[LEADER[1]].ITEM = 5;
		hito[LEADER[1]].SERIFEV = 1;
		SetAllDir(1, 2);
		Team_MapPointSet(1, 0);
		LEADER[TotalTeam] = fq_1.Total_Hito;
		Map_Monster(10);
	}

	public void update() {
		if (fq_1.Total_Hito == 0)
			return;
		flg_cls();
		for (int i = 0; i < fq_1.Total_Hito; i++)
			if ((hito[i].FL & 8) == 0)
				hito[i].update();

	}

	public void paint(Graphics g) {
		if (fq_1.Total_Hito == 0)
			return;
		drawbuf_c = 0;
		for (int i = 0; i < fq_1.Total_Hito; i++) {
			drawbuf_p[i] = -1;
			if ((hito[i].FL & 8) == 0) {
				hito[i].drawx = ((hito[i].X * 16 - 16) + hito[i].draw_dx)
						- fq_1.DispMapX;
				hito[i].drawy = ((hito[i].Y * 16 - 16) + hito[i].draw_dy)
						- fq_1.DispMapY;
				hito[i].FL &= 0xffffff7f;
				if (hito[i].drawx > -(hito[i].PLM + 16) && hito[i].drawx < 640
						&& hito[i].drawy > -(hito[i].PLM + 16)
						&& hito[i].drawy < 440) {
					hito[i].FL |= 0x80;
					drawbuf_p[drawbuf_c++] = i;
				}
			}
		}

		for (int i = 0; i < drawbuf_c; i++) {
			int MIN_Y = 9999;
			int MIN_NUM = -1;
			for (int j = 0; j < drawbuf_c; j++)
				if (drawbuf_p[j] >= 0) {
					int y = hito[drawbuf_p[j]].drawy;
					if (y < MIN_Y) {
						MIN_Y = y;
						MIN_NUM = j;
					}
				}

			if (MIN_NUM < 0) {
				System.out.println("MIN_NUM error");
			} else {
				drawbuf[i] = drawbuf_p[MIN_NUM];
				drawbuf_p[MIN_NUM] = -1;
			}
		}

		for (int i = 0; i < drawbuf_c; i++)
			if (drawbuf[i] >= 0)
				hito[drawbuf[i]].paint(g);

	}

	private static final long serialVersionUID = 1L;
	int mgrsu_m[][] = {
			{ 60, 1, 99, 144, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 49152, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 14, 17, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 49536, 51328, 51360,
					51392, 51424, 51456, 51488, 51520, 51552, 51584, 51616,
					52800, 52832, 52864, 52896, 51648, 51680, 0, 0, 0 } };
	int grsu_m[][] = {
			{ 61, 12, 230, 189, 0, 16, 0, 0, 0, 0, 0, 1, 0, 0, 50944, 50976,
					51008, 51040, 51072, 51104, 52672, 52704, 52736, 52768,
					52928, 52960, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 62, 1, 2, 0, 0, 30, 0, 0, 0, 0, 0, 1, 0, 0, 49216, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 65, 7, 2, 0, 19, 35, 0, 0, 0, 0, 0, 1, 0, 0, 49376, 50752, 50784,
					50816, 50848, 50880, 50912, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0 },
			{ 89, 14, 2, 0, 0, 87, 0, 0, 0, 0, 0, 1, 0, 0, 49184, 52352, 52384,
					52416, 52448, 52480, 52512, 51136, 51168, 51200, 51232,
					51264, 51296, 52000, 0, 0, 0, 0, 0, 0 },
			{ 92, 1, 2, 0, 0, 92, 0, 0, 0, 0, 0, 1, 0, 0, 49344, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 103, 10, 2, 0, 0, 123, 0, 0, 0, 0, 0, 1, 0, 0, 50112, 50144,
					50176, 50208, 50240, 50272, 50304, 50336, 50368, 50400, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 105, 3, 2, 0, 0, 124, 0, 0, 0, 0, 0, 1, 0, 0, 49440, 49472,
					49504, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 112, 1, 2, 0, 0, 134, 0, 0, 0, 0, 0, 1, 1, 0, 49312, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 49, 1, 97, 28, 0, 151, 0, 0, 0, 0, 0, 1, 123, 0, 65216, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	int tgrsu_m[][] = {
			{ 132, 17, 2, 0, 0, 156, 1, 3, 0, 0, 0, 3, 0, 0, 65248, 63904,
					63904, 63904, 63904, 63904, 63904, 63968, 63968, 63968,
					63968, 63968, 63968, 63968, 63968, 63968, 63968, 0, 0, 0 },
			{ 57, 20, 2, 0, 0, 0, 1, 0, 0, 0, 0, 3, 0, 0, 63904, 63904, 63904,
					63904, 63904, 63904, 63904, 63904, 63904, 63904, 63936,
					63936, 63936, 63936, 63936, 63936, 63936, 63936, 63936,
					63936 },
			{ 133, 20, 2, 0, 0, 0, 1, 0, 0, 0, 0, 3, 0, 0, 63904, 63904, 63904,
					63904, 63904, 63904, 63904, 63904, 63904, 63904, 63936,
					63936, 63936, 63936, 63936, 63936, 63936, 63936, 63936,
					63936 },
			{ 45, 20, 158, 30, 0, 0, 1, 2, 4, 0, 0, 3, 0, 0, 63904, 63904,
					63904, 63904, 63904, 63904, 63904, 63904, 63904, 63904,
					63968, 63968, 63968, 63968, 63968, 63968, 63968, 63968,
					63968, 63968 },
			{ 53, 20, 64, 140, 0, 0, 1, 0, 4, 0, 0, 3, 0, 0, 63904, 63904,
					63904, 63904, 65440, 65440, 65440, 65440, 65440, 65440,
					63968, 63968, 63968, 63968, 63968, 63968, 63968, 63968,
					63968, 63968 },
			{ 138, 20, 2, 0, 0, 0, 1, 0, 0, 0, 0, 3, 0, 0, 65440, 65440, 65440,
					65440, 65440, 65440, 63968, 63968, 63968, 63968, 63936,
					63936, 63936, 63936, 63936, 63936, 63936, 63936, 63936,
					63936 },
			{ 46, 16, 224, 45, 0, 0, 1, 2, 4, 0, 0, 3, 0, 0, 63968, 63968,
					63968, 63968, 63968, 63968, 63968, 63968, 63968, 63968,
					63968, 63968, 63968, 63968, 63968, 63968, 0, 0, 0, 0 },
			{ 51, 14, 172, 282, 0, 0, 1, 2, 4, 0, 0, 3, 0, 0, 65440, 65440,
					65440, 65440, 63968, 63968, 63968, 63968, 63968, 63968,
					63968, 63968, 63968, 63968, 63968, 63968, 0, 0, 0, 0 },
			{ 39, 20, 2, 0, 0, 119, 4, 3, 0, 0, 0, 3, 0, 3, 65024, 65024,
					65024, 65024, 65024, 65024, 65024, 65024, 65024, 65024,
					64992, 64992, 64992, 64992, 64992, 64992, 64992, 64992,
					64992, 64992 },
			{ 38, 8, 2, 0, 0, 131, 0, 3, 0, 1, 0, 3, 0, 3, 64992, 64992, 64992,
					64992, 64992, 65024, 65024, 65024, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0 },
			{ 32, 20, 2, 0, 0, 132, 1, 1, 0, 0, 0, 3, 0, 0, 65024, 65024,
					65024, 65024, 65024, 64992, 64992, 64992, 64992, 64992,
					64992, 64992, 64992, 64992, 64992, 64992, 64992, 64992,
					64992, 64992 },
			{ 42, 20, 2, 0, 0, 0, 4, 0, 0, 0, 0, 3, 0, 0, 65120, 65120, 65120,
					65120, 65120, 65120, 65120, 65120, 65120, 65120, 65120,
					65120, 65120, 65120, 65120, 65120, 65120, 65120, 65120,
					65120 },
			{ 43, 20, 2, 0, 0, 0, 0, 0, 0, 1, 0, 3, 0, 0, 65120, 65120, 65120,
					65120, 65120, 65120, 65120, 65120, 65120, 65120, 65120,
					65120, 65120, 65120, 65120, 65120, 65120, 65120, 65120,
					65120 },
			{ 17, 20, 2, 0, 0, 0, 0, 0, 0, 1, 0, 3, 0, 0, 64064, 64064, 64064,
					64064, 64064, 64064, 64064, 64064, 64064, 64064, 64064,
					64064, 64064, 64064, 64064, 64064, 64064, 64064, 64064,
					64064 },
			{ 19, 20, 2, 0, 0, 0, 0, 0, 0, 1, 0, 3, 0, 0, 64064, 64064, 64064,
					64064, 64064, 64064, 64064, 64064, 64064, 64064, 64064,
					64064, 64064, 64064, 64064, 64064, 64064, 64064, 64064,
					64064 },
			new int[36],
			new int[36],
			{ 52, 17, 2, 0, 0, 168, 1, 0, 0, 0, 0, 3, 0, 0, 57152, 54912,
					54944, 54976, 55008, 55040, 55072, 55104, 55136, 55168,
					55200, 63968, 63968, 63968, 63968, 63968, 63968, 0, 0, 0 },
			{ 45, 15, 2, 0, 0, 165, 3, 3, 0, 0, 0, 3, 0, 0, 57184, 63936,
					63936, 63936, 63936, 55232, 55264, 55296, 55328, 55360,
					55392, 55424, 55456, 55488, 55520, 0, 0, 0, 0, 0 },
			{ 44, 20, 2, 0, 0, 164, 4, 0, 0, 0, 0, 3, 0, 0, 57216, 65440,
					65440, 65440, 65440, 63968, 63968, 63968, 63968, 63968,
					63968, 63968, 63968, 63968, 56320, 56352, 56384, 56416,
					56448, 56480 },
			{ 36, 15, 2, 0, 0, 162, 2, 1, 0, 0, 0, 3, 0, 0, 57248, 55552,
					55584, 55616, 55648, 52992, 53024, 53056, 53088, 53120,
					53152, 53216, 53248, 53280, 53312, 0, 0, 0, 0, 0 },
			{ 28, 17, 2, 0, 0, 163, 0, 1, 0, 1, 0, 3, 0, 0, 57280, 56512,
					56544, 56576, 56608, 56640, 56672, 56704, 56736, 56768,
					56800, 53344, 53376, 53408, 53440, 53472, 53504, 0, 0, 0 },
			{ 10, 15, 2, 0, 0, 166, 0, 1, 0, 0, 0, 3, 0, 0, 57312, 56832,
					56864, 56896, 56928, 56960, 56992, 57024, 57056, 57088,
					57120, 53536, 53568, 53600, 53632, 0, 0, 0, 0, 0 },
			{ 29, 20, 158, 30, 0, 0, 1, 2, 4, 0, 0, 3, 0, 0, 55680, 53664,
					53696, 53728, 53760, 53792, 53824, 53856, 53888, 53920,
					63936, 63936, 63936, 63936, 65120, 65120, 65120, 65120,
					65120, 65120 },
			{ 26, 10, 2, 0, 0, 0, 2, 0, 0, 0, 0, 3, 0, 0, 63936, 63936, 63936,
					63936, 63936, 55712, 55744, 55776, 55808, 55840, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0 },
			{ 43, 16, 2, 0, 0, 0, 4, 0, 0, 0, 0, 3, 0, 0, 63904, 62464, 62496,
					62528, 62560, 62592, 62624, 62656, 62688, 62720, 62752,
					62784, 62816, 62848, 62880, 62912, 0, 0, 0, 0 },
			{ 41, 16, 2, 0, 0, 0, 2, 0, 0, 0, 0, 3, 0, 0, 63904, 62944, 62976,
					63008, 63040, 63072, 55872, 55904, 55936, 55968, 56000,
					56032, 65120, 65120, 65120, 65120, 0, 0, 0, 0 },
			{ 35, 17, 2, 0, 0, 0, 2, 0, 0, 0, 0, 3, 0, 0, 63904, 54464, 54496,
					54528, 54560, 54592, 54624, 54656, 54688, 54720, 54752,
					54784, 54816, 54848, 54880, 62400, 62432, 0, 0, 0 },
			{ 40, 20, 2, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 63904, 54144, 54176,
					54208, 54240, 54272, 54304, 54336, 54368, 54400, 54432,
					61504, 61536, 61568, 61600, 61632, 61664, 61696, 61728,
					61760 },
			{ 34, 20, 2, 0, 0, 0, 2, 0, 0, 0, 0, 3, 0, 0, 63904, 61792, 61824,
					61856, 61888, 61920, 61952, 61984, 62016, 62048, 62080,
					62112, 62144, 62176, 62208, 62240, 62272, 62304, 62336,
					62368 }, new int[36], new int[36], new int[36],
			new int[36], new int[36], new int[36] };
	int hgrsu_m[][] = {
			{ 77, 20, 2, 0, 7, 56, 0, 0, 0, 0, 0, 2, 0, 2, 60544, 60576, 60608,
					60640, 60672, 60704, 60736, 60768, 60800, 60832, 61184,
					61216, 61248, 61280, 61312, 60864, 60896, 60928, 60960,
					60992 },
			{ 102, 20, 2, 0, 0, 0, 0, 0, 0, 1, 0, 2, 11, 10, 49920, 49952,
					49984, 50016, 53952, 53984, 54016, 54048, 54080, 54112,
					52032, 52064, 52096, 52128, 52160, 52192, 52224, 52256,
					52288, 52320 },
			{ 97, 20, 154, 301, 12, 98, 0, 0, 0, 1, 0, 2, 0, 9, 57664, 57696,
					57728, 57760, 57792, 57824, 57856, 57888, 57920, 57952,
					58144, 58176, 58208, 58240, 58272, 58304, 58336, 58368,
					58400, 58432 },
			{ 25, 15, 2, 0, 9, 106, 0, 0, 0, 0, 0, 2, 0, 4, 59264, 58624,
					58656, 58688, 58720, 58752, 58784, 58816, 58848, 58880,
					58912, 58944, 58976, 59008, 59040, 0, 0, 0, 0, 0 },
			{ 100, 15, 2, 0, 9, 104, 0, 0, 0, 1, 0, 2, 0, 4, 59296, 59328,
					59360, 59392, 59424, 59456, 59488, 59520, 59552, 59072,
					59104, 59136, 59168, 59200, 59232, 0, 0, 0, 0, 0 },
			{ 83, 15, 2, 0, 14, 68, 0, 0, 0, 1, 0, 2, 0, 3, 59584, 59616,
					59648, 59680, 59712, 59744, 59776, 59808, 59840, 59872,
					60224, 60256, 60128, 60160, 60192, 0, 0, 0, 0, 0 },
			{ 84, 15, 2, 0, 14, 69, 0, 0, 0, 0, 0, 2, 0, 3, 59904, 59936,
					59968, 60000, 60288, 60320, 60352, 60384, 60416, 60448,
					60480, 60512, 60032, 60064, 60096, 0, 0, 0, 0, 0 },
			{ 4, 10, 180, 16, 3, 44, 0, 0, 0, 0, 0, 2, 7, 7, 50432, 50464,
					50496, 50528, 50560, 50592, 50624, 50656, 50688, 50720, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 27, 20, 94, 54, 0, 112, 0, 0, 0, 0, 0, 2, 10, 10, 49248, 49568,
					49600, 49632, 49664, 49696, 49728, 49760, 50048, 50080,
					51712, 51744, 51776, 51808, 51840, 51872, 51904, 51936,
					51968, 49408 },
			{ 21, 16, 32, 38, 0, 95, 0, 0, 0, 0, 0, 2, 9, 9, 49280, 57344,
					57376, 57408, 57440, 57472, 57504, 57536, 57568, 57600,
					57632, 57984, 58016, 58048, 58080, 58112, 0, 0, 0, 0 },
			{ 21, 20, 2, 0, 12, 182, 0, 0, 0, 1, 0, 2, 9, 0, 64800, 64800,
					64800, 64800, 64800, 64800, 64800, 64800, 64800, 64800,
					64800, 64800, 64800, 64800, 64800, 64800, 64800, 64800,
					64800, 64800 },
			{ 27, 16, 2, 0, 16, 181, 0, 0, 0, 1, 0, 2, 10, 10, 63104, 63136,
					63168, 63200, 63232, 63264, 63296, 63328, 63360, 63392,
					63424, 63456, 49792, 49824, 49856, 49888, 0, 0, 0, 0 } };
	int mons_m[] = { 3, 4, 6, 7, 75, 9, 10, 15, 16, 17, 18, 27, 28, 30, 31, 36,
			109, 38, 40, 41, 43, 44, 45, 21, 48, 49, 129, 50, 51, 55, 56, 21,
			255 };
	int mons_tab[][] = {
			{ 1, 1, 0, 32, 0, 136, 34, 180, 98, 0, 0, 0, 0, 0, 0 },
			{ 1, 2, 0, 35, 0, 11, 31, 60, 226, 36, 0, 176, 122, 189, 159 },
			{ 1, 1, 0, 32, 0, 20, 34, 100, 182, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 0, 36, 0, 196, 138, 252, 182, 0, 0, 0, 0, 0, 0 },
			{ 2, 1, 2, 64, 0, 146, 459, 147, 460, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 0, 35, 0, 2, 302, 124, 348, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 0, 52, 0, 11, 56, 56, 204, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 0, 40, 0, 154, 440, 184, 484, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 0, 39, 0, 80, 93, 116, 160, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 0, 52, 0, 147, 39, 187, 200, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 0, 39, 0, 77, 260, 118, 328, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 0, 52, 0, 46, 250, 108, 374, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 3, 15, 0, 91, 149, 126, 157, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 57, 0, 18, 414, 46, 484, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 0, 52, 0, 210, 318, 227, 370, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 20, 0, 130, 102, 162, 124, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 0, 38, 0, 196, 295, 248, 394, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 21, 0, 131, 85, 161, 94, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 0, 40, 0, 55, 176, 71, 200, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 0, 42, 0, 136, 160, 182, 232, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 57, 0, 147, 39, 187, 200, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 30, 0, 196, 295, 248, 394, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 31, 0, 219, 22, 239, 40, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 0, 42, 0, 136, 28, 182, 78, 0, 0, 0, 0, 0, 0 },
			{ 1, 2, 0, 20, 0, 0, 190, 0, 210, 29, 0, 75, 302, 172, 382 },
			{ 1, 2, 0, 33, 0, 14, 48, 140, 122, 34, 0, 3, 142, 64, 495 },
			{ 1, 1, 0, 34, 0, 20, 72, 80, 114, 0, 0, 0, 0, 0, 0 },
			{ 2, 1, 4, 65, 0, 234, 6, 235, 7, 0, 0, 0, 0, 0, 0 },
			{ 1, 2, 1, 30, 0, 6, 59, 52, 102, 38, 0, 6, 124, 54, 196 },
			{ 1, 1, 1, 31, 0, 140, 376, 180, 408, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 0, 57, 0, 226, 148, 244, 175, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 0, 33, 0, 226, 402, 246, 426, 0, 0, 0, 0, 0, 0 } };
	int zako_m[] = { 0, 1, 2, 9, 20, 27, 60, 63, 64, 65, 66, 67, 68, 69, 70,
			71, 72, 73, 74, 76, 78, 80, 81, 82, 85, 86, 87, 88, 89, 90, 91, 94,
			98, 99, 100, 101, 103, 104, 106, 111, 117, 119, 121, 123, 124, 126,
			128, 139, 47, 5, 255 };
	int zmap_0[] = { 0, 4, 0, 9, 1, 0, 17, 79, 304, 9, 1, 0, 17, 105, 304, 9,
			1, 0, 0, 84, 290, 9, 1, 0, 0, 100, 290 };
	int zmap_1[] = { 0, 5, 0, 9, 1, 0, 0, 154, 47, 9, 1, 0, 20, 166, 44, 9, 1,
			0, 0, 178, 47, 9, 1, 0, 0, 145, 24, 9, 1, 0, 0, 188, 24 };
	int zmap_2[] = { 0, 20, 0, 23, 0, 0, 18, 193, 212, 22, 17, 0, 19, 217, 226,
			22, 17, 0, 21, 151, 126, 23, 0, 0, 22, 133, 36, 63, 0, 0, 23, 225,
			78, 25, 0, 0, 24, 183, 22, 23, 17, 0, 25, 183, 76, 26, 17, 0, 26,
			205, 134, 22, 0, 0, 27, 242, 180, 27, 17, 0, 28, 192, 122, 22, 17,
			0, 29, 251, 36, 22, 0, 0, 0, 211, 212, 23, 0, 0, 0, 245, 197, 23,
			0, 0, 0, 147, 109, 63, 0, 0, 0, 177, 29, 22, 0, 0, 0, 215, 73, 22,
			0, 0, 0, 213, 115, 63, 0, 0, 0, 245, 135, 22, 0, 0, 0, 195, 153,
			23, 0, 0, 0, 219, 143 };
	int zmap_5[] = { 0, 1, 0, 28, 1, 6, 49, 202, 159 };
	int zmap_9[] = { 0, 2, 5, 37, 1, 7, 53, 27, 354, 37, 1, 0, 0, 65, 354 };
	int zmap_20[] = { 0, 20, 0, 22, 0, 0, 70, 24, 218, 23, 17, 0, 71, 71, 217,
			22, 17, 0, 72, 91, 187, 60, 0, 0, 73, 49, 147, 22, 17, 0, 74, 63,
			129, 22, 17, 0, 75, 117, 231, 25, 0, 0, 76, 123, 83, 23, 1, 0, 77,
			3, 41, 22, 0, 0, 78, 57, 60, 22, 1, 0, 79, 113, 16, 23, 0, 0, 0,
			54, 206, 22, 0, 0, 0, 104, 226, 23, 0, 0, 0, 120, 184, 22, 0, 0, 0,
			18, 152, 63, 0, 0, 0, 58, 132, 22, 0, 0, 0, 116, 166, 23, 0, 0, 0,
			53, 93, 22, 0, 0, 0, 25, 31, 22, 0, 0, 0, 71, 13, 23, 0, 0, 0, 103,
			49 };
	int zmap_27[] = { 1, 2, 6, 58, 1, 16, 109, 82, 218, 58, 1, 0, 0, 108, 218 };
	int zmap_47[] = { 0, 8, 0, 21, 0, 20, 135, 2, 0, 21, 0, 20, 136, 2, 0, 21,
			0, 20, 137, 2, 0, 21, 0, 20, 137, 2, 0, 21, 0, 20, 138, 2, 0, 21,
			0, 20, 138, 2, 0, 21, 0, 20, 137, 2, 0, 21, 0, 0, 0, 2, 0 };
	int zmap_60[] = { 0, 2, 0, 59, 1, 1, 9, 93, 138, 24, 1, 2, 13, 106, 136 };
	int zmap_63[] = { 0, 1, 0, 27, 0, 0, 31, 2, 0 };
	int zmap_64[] = { 0, 2, 0, 22, 0, 0, 32, 2, 0, 23, 0, 0, 33, 2, 0 };
	int zmap_65[] = { 0, 1, 0, 23, 0, 0, 34, 3, 0 };
	int zmap_66[] = { 0, 1, 0, 60, 0, 10, 36, 2, 0 };
	int zmap_67[] = { 0, 2, 0, 23, 0, 0, 37, 2, 0, 22, 0, 0, 0, 2, 0 };
	int zmap_68[] = { 0, 1, 0, 63, 0, 0, 39, 2, 0 };
	int zmap_69[] = { 0, 1, 0, 25, 0, 0, 40, 2, 0 };
	int zmap_70[] = { 0, 1, 7, 5, 0, 3, 43, 2, 0 };
	int zmap_71[] = { 0, 1, 7, 5, 0, 3, 42, 2, 0 };
	int zmap_72[] = { 0, 1, 8, 22, 1, 4, 46, 2, 0 };
	int zmap_73[] = { 0, 1, 0, 22, 1, 5, 47, 3, 0 };
	int zmap_74[] = { 0, 1, 0, 23, 1, 0, 51, 114, 64 };
	int zmap_76[] = { 0, 1, 0, 26, 0, 0, 52, 3, 0 };
	int zmap_78[] = { 0, 2, 0, 37, 0, 0, 58, 2, 0, 5, 0, 0, 0, 2, 0 };
	int zmap_80[] = { 0, 1, 0, 63, 1, 0, 61, 114, 64 };
	int zmap_81[] = { 0, 1, 0, 26, 0, 8, 62, 2, 0 };
	int zmap_82[] = { 0, 1, 0, 26, 0, 13, 64, 2, 0 };
	int zmap_85[] = { 0, 2, 0, 23, 0, 0, 80, 2, 0, 22, 0, 0, 81, 2, 0 };
	int zmap_86[] = { 0, 2, 0, 37, 0, 0, 82, 2, 0, 37, 0, 0, 83, 2, 0 };
	int zmap_87[] = { 0, 1, 0, 27, 0, 0, 84, 2, 0 };
	int zmap_88[] = { 0, 1, 0, 25, 0, 0, 85, 2, 0 };
	int zmap_89[] = { 0, 1, 0, 22, 1, 0, 86, 4, 34 };
	int zmap_90[] = { 0, 1, 0, 22, 0, 10, 89, 2, 0 };
	int zmap_91[] = { 0, 1, 0, 23, 0, 0, 91, 2, 0 };
	int zmap_94[] = { 0, 9, 9, 19, 1, 11, 94, 59, 141, 44, 1, 0, 0, 52, 139,
			44, 1, 0, 0, 66, 139, 44, 17, 0, 18, 48, 146, 44, 1, 0, 0, 70, 146,
			44, 1, 0, 0, 50, 151, 44, 1, 0, 0, 68, 151, 44, 1, 0, 0, 53, 145,
			44, 1, 0, 0, 65, 145 };
	int zmap_98[] = { 0, 1, 0, 45, 0, 13, 101, 2, 0 };
	int zmap_99[] = { 0, 1, 0, 46, 0, 10, 103, 2, 0 };
	int zmap_100[] = { 0, 1, 0, 60, 1, 0, 34, 114, 64 };
	int zmap_101[] = { 0, 3, 10, 22, 1, 0, 164, 137, 115, 59, 1, 15, 113, 148,
			10, 63, 17, 0, 73, 156, 10 };
	int zmap_103[] = { 0, 1, 0, 22, 1, 0, 122, 114, 64 };
	int zmap_104[] = { 0, 1, 11, 56, 1, 17, 120, 55, 203 };
	int zmap_106[] = { 0, 1, 0, 46, 0, 0, 186, 3, 0 };
	int zmap_111[] = { 0, 1, 0, 22, 0, 18, 133, 2, 0 };
	int zmap_117[] = { 0, 2, 0, 29, 0, 0, 143, 2, 0, 29, 0, 0, 143, 2, 0 };
	int zmap_119[] = { 0, 1, 0, 29, 0, 0, 144, 2, 0 };
	int zmap_121[] = { 0, 1, 0, 29, 0, 0, 145, 2, 0 };
	int zmap_123[] = { 0, 1, 0, 29, 0, 0, 146, 2, 0 };
	int zmap_124[] = { 0, 1, 0, 29, 0, 0, 147, 2, 0 };
	int zmap_126[] = { 0, 1, 0, 29, 0, 0, 148, 2, 0 };
	int zmap_128[] = { 0, 1, 0, 53, 0, 0, 150, 60, 199 };
	int zmap_139[] = { 0, 1, 0, 59, 0, 0, 154, 2, 0 };
	int zmap_140[] = { 0, 1, 0, 24, 1, 0, 155, 63, 71 };
	int Formation_abs[] = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
	int Formation[][] = {
			{ -2, 0, 2, 0, -2, 0, 2, 0, -2, 0, 2, 0, -2, 0, 2, 0, -2, 0, 2, 0,
					-2, 0, 2, 0, -2, 0, 2, 0, -2, 0, 2, 0, -2, 0, 2, 0, -2, 0,
					2, 0 },
			{ 0, 3, 4, 3, -4, 3, 8, 3, -8, 3, 4, 0, -4, 0, 0, 6, 6, 6, -6, 6,
					12, 3, -12, 3, 10, 6, -10, 6, 14, 6, -14, 6, 0, 9, 4, 9,
					-4, 9, 0, 0 },
			{ 4, 0, -4, 0, -2, -3, 2, -3, 0, 3, 8, 0, -8, 0, 10, -3, -10, -3,
					0, -6, 6, -6, -6, -6, 12, 0, -12, 0, 6, 3, -6, 3, 9, 3, -9,
					3, 0, 6, 0, 0 },
			{ 4, 0, -4, 0, 8, 0, -8, 0, 12, 0, -12, 0, 16, 0, -16, 0, 2, -3,
					-2, -3, 20, 0, -20, 0, 7, -4, -7, -4, 26, 0, -26, 0, -11,
					-4, 11, -4, 0, 3, 0, 0 },
			{ 0, -4, -4, 2, 4, 2, -12, -4, 12, -4, -14, 2, 14, 2, -10, 2, 10,
					2, -12, 0, 12, 0, -8, 0, 8, 0, -16, -4, 16, -4, 0, 4, 14,
					-6, -14, -6, 4, -6, -4, -6 },
			{ 4, 0, -4, 0, 8, 0, -8, 0, 12, 0, -12, 0, 16, 0, -16, 0, 2, -3,
					-2, -3, 20, 0, -20, 0, 7, -4, -7, -4, 26, 0, -26, 0, 10,
					-3, -10, -3, 16, -3, -16, -3 },
			{ 0, -3, -4, -3, 4, -3, 10, 0, -10, 0, -14, 0, 14, 0, -18, 0, 18,
					0, -16, 3, 16, 3, -12, 3, 12, 3, -20, 3, 20, 3, 0, 3, -16,
					-3, 16, -3, -20, -3, 20, -3 },
			{ 3, -3, 6, -3, -3, -3, -6, -3, 10, 1, 13, 1, -10, 1, -13, 1, -5,
					4, -2, 4, 5, 4, 2, 4, -15, -3, -12, -3, 15, -3, 12, -3, -5,
					-6, -2, -6, 5, -6, 2, -6 },
			{ 4, -3, -4, -3, -4, -6, 4, -6, 7, -6, -7, -6, -7, -3, 7, -3, -12,
					-2, 12, -2, -12, 1, 12, 1, -15, -2, 15, -2, -15, 1, 15, 1,
					-12, 2, 12, 2, -15, 4, 15, 4 },
			{ 0, -4, 0, 4, -8, 0, 8, 0, -4, 4, 4, 4, -16, 0, 16, 0, -20, 4, 20,
					4, -4, -4, 4, -4, -12, -4, 12, -4, -20, -8, 20, -8, -20,
					-4, 20, -4, -14, -6, 14, -6 },
			{ -3, 2, 3, 2, -6, 4, 6, 4, -9, 6, 9, 6, -12, 8, 12, 8, -15, 10,
					15, 10, -18, 12, 18, 12, -21, 14, 21, 14, -24, 16, 24, 16,
					-27, 18, 27, 18, 0, 20, 0, 0 },
			{ -5, 0, 5, 0, 0, -3, 0, 3, -10, 0, 10, 0, -4, -3, 4, -3, -4, 3, 4,
					3, 0, -6, 0, 6, -8, -3, 8, -3, -8, 3, 8, 3, -3, 6, 3, 6, 0,
					9, 0, 0 },
			{ 0, 3, 4, 0, -4, 0, 4, 3, -4, 3, 0, 6, 4, 6, -4, 6, 8, 0, -8, 0,
					0, -3, 4, -3, -4, -3, 0, 9, 4, 9, -4, 9, 0, 12, -3, 12, 3,
					12, 0, 0 },
			{ 2, 4, -2, 4, 2, 8, -2, 8, 2, 12, -2, 12, 4, 0, -4, 0, 2, 16, -2,
					16, 5, 4, -5, 4, 5, 8, -5, 8, 2, 20, -2, 20, 0, -3, 0, -6,
					0, -9, 0, -12 },
			{ 0, 4, 8, 4, -8, 4, 12, 4, -12, 4, 4, 0, -4, 0, 16, 4, -16, 4, 0,
					8, 10, 8, -10, 8, 14, 8, -14, 8, 18, 8, -18, 8, -8, 0, 8,
					0, 0, 12, 0, 0 },
			{ 6, 0, -6, 0, 0, -4, 4, -4, -4, -4, 10, 0, -10, 0, 12, -4, -12,
					-4, 0, -8, 8, -8, -8, -8, 14, 0, -14, 0, 2, 4, -2, 4, -5,
					5, 5, 5, 0, -12, 0, 0 },
			{ 4, 0, -4, 0, -2, -4, 2, -4, 0, 4, 8, 0, -8, 0, 10, -4, -10, -4,
					0, -8, 6, -6, -6, -6, 12, 0, -12, 0, 6, 4, -6, 4, -14, -4,
					14, -4, 10, -10, -10, -10 } };
	int MapSetPoint[][] = {
			{ 30, 157, 27, 160, 23, 157, 38, 158, 10, 160, 39, 161, 6, 159, 33,
					154, 27, 154, 45, 150, 10, 155, 54, 152, 24, 144, 27, 146,
					35, 138, 38, 131, 5, 138, 59, 137, 42, 128, 3, 162 },
			{ 30, 157, 27, 160, 23, 157, 38, 158, 10, 160, 39, 161, 6, 159, 33,
					154, 27, 154, 45, 150, 10, 155, 54, 152, 24, 144, 26, 143,
					35, 138, 38, 131, 5, 138, 59, 137, 42, 128, 3, 162 },
			{ 30, 157, 27, 160, 23, 157, 38, 158, 10, 160, 39, 161, 6, 159, 33,
					154, 27, 154, 45, 150, 10, 155, 54, 152, 24, 144, 26, 143,
					35, 138, 38, 131, 5, 138, 59, 137, 42, 128, 3, 162 } };
	int MapItemPoint_10[][] = { { 32, 240, 3, 1 }, { 5, 3, 2, 1 },
			{ 61, 69, 8, 1 }, { 7, 52, 6, 1 }, { 3, 164, 9, 1 },
			{ -1, -1, -1, -1 } };
	Applet applet;
	Gocha hito[];
	int drawbuf_c;
	int drawbuf_p[];
	int drawbuf[];
	int CHN;
	int Pattern;
	int TotalTeam;
	int TM_MAP;
	int MAP;
	int TM_NINZU;
	int NINZU[];
	int TM_LEADX;
	int LEADX;
	int TM_LEADY;
	int LEADY;
	int LEADER[];
	int TM_EVENT;
	int EVENT[];
	int TM_SERIFU;
	int SERIFU[];
	int TM_KOD;
	int KOD[];
	int TM_SCJYULE;
	int SCJYULE[];
	int TM_FORM;
	int FORM[];
	int TM_TEKI;
	int TEKI[];
	int TM_KOH;
	int KOH[];
	int TM_ZOKUJ;
	int ZOKUJ[];
	int Hp_CHR;
	int man_at;
	int man_at2;
	int man_ar;
	int man_ar2;
	int man_de;
	int man_de2;
	int man_dr;
	int man_dr2;
	Fq_1 fq_1;
	Grand grand;
	YaManeger ya;
}
