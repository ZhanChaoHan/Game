// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Gocha.java

package applet;

import java.applet.Applet;
import java.awt.Graphics;
import java.io.PrintStream;

// Referenced classes of package applet:
//            Fq_1, Grand, TeamManeger, YaManeger

class Gocha extends Applet {

	public Gocha(Applet applet, TeamManeger team, Grand grand, YaManeger ya) {
		MovStep = 2;
		MovWait = 16 / MovStep;
		Image_Off_Y = 0;
		Anime_C = 0;
		BitMapAtari = 0;
		K_YUMI_NIGE = 10;
		K_YUMI_FIRE = 24;
		K_OIKAKE_S = 20;
		K_OIKAKE_L = 40;
		K_SARTCH = 60;
		this.applet = applet;
		fq_1 = (Fq_1) applet;
		this.team = team;
		this.grand = grand;
		this.ya = ya;
	}

	public void initialize(int num, int chn, int team_num, int hm_num, int pos,
			int boss, int set_fl, int init_XS, int init_YS, int init_XE,
			int init_YE, int zoku, int nige_dir) {
		FL = 0;
		FLG1 = 0;
		FLG2 = 128;
		FLG3 = 0;
		CharaClass = chn;
		if (zoku != 0 && chn == 17)
			chn = 69;
		TOOSI_NUM = num;
		HITO_NUM = hm_num;
		CHN = chn;
		TM_NUM = team_num;
		TM_POS1 = pos + 1;
		TM_BOSS = boss;
		boolean no_chara = true;
		if (fq_1.CIL_SUU != 0) {
			for (int i = 0; i < fq_1.CIL_SUU; i++) {
				if (chn != fq_1.CIL_Table[i])
					continue;
				CHN_D = i;
				no_chara = false;
				break;
			}

		}
		if (no_chara)
			if (fq_1.CIL_SUU <= 40) {
				CHN_D = fq_1.CIL_SUU;
				fq_1.CIL_Table[fq_1.CIL_SUU] = chn;
				fq_1.CIL_SUU++;
			} else {
				CHN_D = fq_1.CIL_SUU - 1;
			}
		DIR = 4;
		DIR_H = 0;
		DIR_NIGE = nige_dir;
		if (set_fl < 0) {
			if (init_XS < 0 || init_YS < 0) {
				FLG3 |= 0x10;
				X = (fq_1.MXS + fq_1.random(fq_1.MXLM)) - XLM;
				for (Y = (fq_1.MYS + fq_1.random(fq_1.MYLM)) - XLM; map_atari(
						X, Y)
						|| map_atari(X, Y + 1)
						|| map_atari(X + 1, Y)
						|| map_atari(X + 1, Y + 1); Y = (fq_1.MYS + fq_1
						.random(fq_1.MYLM)) - XLM)
					X = (fq_1.MXS + fq_1.random(fq_1.MXLM)) - XLM;

			} else {
				FLG3 |= 0x10;
				X = init_XS + fq_1.random(init_XE - init_XS);
				for (Y = init_YS + fq_1.random(init_YE - init_YS); map_atari(X,
						Y)
						|| map_atari(X, Y + 1)
						|| map_atari(X + 1, Y)
						|| map_atari(X + 1, Y + 1); Y = init_YS
						+ fq_1.random(init_YE - init_YS))
					X = init_XS + fq_1.random(init_XE - init_XS);

			}
		} else {
			X = init_XS;
			Y = init_YS;
		}
		XH = X;
		YH = Y;
		CPN = 0;
		XLM = 2;
		PLM = 32;
		CTOK = fq_1.HMBUF[CHN][2];
		MTOK = fq_1.HMBUF[CHN][3];
		ATYP = fq_1.HMBUF[CHN][4];
		STYP = fq_1.HMBUF[CHN][5];
		RTYP = fq_1.HMBUF[CHN][6];
		RRAT = fq_1.HMBUF[CHN][7];
		XFOR = 0;
		YFOR = 0;
		PAT = 4 * fq_1.Get_hpara(HITO_NUM, 14);
		PDF = 4 * fq_1.Get_hpara(HITO_NUM, 15);
		PAR = 4 * fq_1.Get_hpara(HITO_NUM, 12);
		PDR = 4 * fq_1.Get_hpara(HITO_NUM, 13);
		PYD = 4 * (fq_1.Get_hpara(HITO_NUM, 16) / 16);
		PMD = 4 * (fq_1.Get_hpara(HITO_NUM, 17) / 16);
		PYR = 4 * (fq_1.Get_hpara(HITO_NUM, 16) & 0xf);
		CUNTY = 0;
		CUNT1 = 0;
		CUNT2 = 0;
		CUNTK = 0;
		MO_CNT = 0;
		AT_CNT = 0;
		SIKO_C = fq_1.random(512);
		MOVEC = 0;
		MOVES = 0;
		XO = X;
		YO = Y;
		XO2 = X;
		YO2 = Y;
		XNE = X;
		YNE = Y;
		XMOKU = X;
		YMOKU = Y;
		CharaWidth = 64;
		CharaHeight = 64;
		HEAL = 4 * (fq_1.Get_hpara(HITO_NUM, 11) & 0xf);
		HEAL_C = 0;
		LIFE = fq_1.Get_hpara(HITO_NUM, 10);
		FATIG = fq_1.Get_hpara(HITO_NUM, 20);
		LSHIP = fq_1.Get_hpara(HITO_NUM, 1) & 0xf;
		SPEED = fq_1.Get_hpara(HITO_NUM, 2);
		MLIF = LIFE;
		NAMENO = HITO_NUM;
		FATG = 0;
		FATC = 0;
		LV = fq_1.Get_hpara(HITO_NUM, 9) / 16;
		LEVELC = 0;
		LEVELUPC = 0;
		ZOKUJ = 0;
		ZOKUA = 7;
		if (zoku != 0) {
			ZOKUJ = 7;
			ZOKUA = 0;
		}
		AITE_A = -1;
		AITE_D = -1;
		TEKI_LEN = -1;
		T_NERAWARE = 0;
		T_KAKOMARE = 0;
		SERIFEV = 0;
		ITEM = 0;
		x_movmin = fq_1.MXS;
		y_movmin = fq_1.MYS;
		x_movmax = (fq_1.MXS + fq_1.MXLM) - XLM;
		y_movmax = (fq_1.MYS + fq_1.MYLM) - XLM;
		MovStep = 4;
		MovWait = 16 / MovStep;
		mov_off = 0;
		Anime_C = 0;
		BitMapAtari = 0;
		if (TOOSI_NUM == fq_1.Control_Hito)
			grand.Hito_screen(X, Y);
	}

	public void update() {
		int gamennai = 0;
		if (TM_NUM == fq_1.Control_Team)
			gamennai = 1;
		int kakomisuu = 1;
		boolean b_move = false;
		boolean b_oikake = false;
		boolean b_s_nige = false;
		boolean b_nige = false;
		boolean b_rest = false;
		if ((FLG1 & 0x12) != 0 || (fq_1.CommandCode == 2 || fq_1.b_Escape)
				&& fq_1.Control_Team == TM_NUM)
			b_nige = true;
		if (CUNTY != 0)
			CUNTY--;
		FLG1 &= -9;
		int heal_n = team.heal(TOOSI_NUM);
		if (X == XH && Y == YH && (FLG1 & 4) == 0)
			if (LIFE > 0 && LIFE < MLIF) {
				FLG1 |= 8;
				HEAL_C += heal_n / 4;
				if (HEAL_C >= 64) {
					HEAL_C -= 64;
					LIFE++;
				}
			} else if (FATIG > 0) {
				HEAL_C += heal_n / 4;
				if (HEAL_C >= 256) {
					HEAL_C -= 256;
					FATIG--;
				}
			}
		if ((FLG1 & 0x80) == 0) {
			switch (RRAT & 3) {
			case 1: // '\001'
				if (MLIF / 4 > LIFE) {
					b_s_nige = true;
					b_rest = true;
				}
				break;

			case 2: // '\002'
				if (MLIF / 2 > LIFE) {
					b_s_nige = true;
					b_rest = true;
				}
				break;

			case 3: // '\003'
				if ((MLIF * 3) / 4 > LIFE) {
					b_s_nige = true;
					b_rest = true;
				}
				break;
			}
			switch (RRAT / 4 & 3) {
			default:
				break;

			case 1: // '\001'
				if (MLIF / 2 < LIFE) {
					b_s_nige = false;
					b_rest = false;
				}
				break;

			case 2: // '\002'
				if ((MLIF * 3) / 4 < LIFE) {
					b_s_nige = false;
					b_rest = false;
				}
				break;

			case 3: // '\003'
				if (MLIF == LIFE) {
					b_s_nige = false;
					b_rest = false;
				}
				break;
			}
		}
		FLG1 &= -2;
		if (b_s_nige)
			FLG1 |= 1;
		if (LEVELUPC != 0) {
			LEVELUPC -= 2;
			if (LEVELUPC < 0)
				LEVELUPC = 0;
		}
		boolean c_henka = false;
		if (CUNTK != fq_1.Sys_Count2) {
			c_henka = true;
			CUNTK = fq_1.Sys_Count2;
		}
		if (c_henka) {
			if ((FL & 1) != 0) {
				FL |= 8;
				if (ITEM > 0 && fq_1.item_kaisyuu(ITEM))
					ITEM = 0;
				if (fq_1.Control_Team == TM_NUM) {
					fq_1.MT_MessageCont = 3;
					fq_1.MT_MessageNum = 1000 + TOOSI_NUM;
				}
			}
			if (AT_CNT != 0)
				AT_CNT -= 2;
			if ((FL & 6) != 0) {
				FL -= 2;
				if ((FL & 6) == 0)
					FL |= 1;
			}
			if ((FL & 0x30) != 0)
				FL -= 16;
			if (MO_CNT != 0)
				MO_CNT--;
			if ((CUNTK & 7) == 0)
				SIKO_C++;
			if ((FLG2 & 0x80) != 0) {
				XH = X;
				YH = Y;
			}
		}
		if ((FL & 7) != 0)
			team.TEKI[TM_NUM] |= 8;
		if (LIFE > 0) {
			DIR_O = DIR;
			map_atari(X, Y);
			MapToku = MapAtari;
			TEKI_LEN = -1;
			FLG3 &= -2;
			if (team.hito_hit_check_move(TOOSI_NUM, X, Y, XLM, 0) >= 0)
				FLG3 |= 1;
			ya_hit_check();
			if ((FL & 0xf) == 0)
				team.teki_hit_check(TOOSI_NUM);
			if ((STYP & 0x40) != 0)
				kakomisuu = 1;
			else if ((CTOK & 0xc0) != 0)
				kakomisuu = 7;
			else
				kakomisuu = 3;
			if ((FL & 0xf) == 0 && TEKI_LEN < 0)
				team.teki_search(TOOSI_NUM, X, Y, K_SARTCH, gamennai, kakomisuu);
			if ((CTOK & 0xc0) != 0 && TEKI_LEN >= 0 && TEKI_LEN < K_YUMI_NIGE)
				b_s_nige = true;
			if (TEKI_LEN > 0) {
				team.TEKI[TM_NUM] |= 8;
				if (TEKI_LEN >= 0 && TEKI_LEN < K_OIKAKE_L) {
					team.TEKI[TM_NUM] |= 0x10;
					if ((FLG1 & 0x40) != 0)
						b_oikake = true;
				}
				if (TEKI_LEN >= 0 && TEKI_LEN < K_OIKAKE_S) {
					team.TEKI[TM_NUM] |= 0x20;
					if ((FL & 0x80) != 0 && SERIFEV != 0) {
						fq_1.MT_MessageCont = 3;
						fq_1.MT_MessageNum = SERIFEV;
						SERIFEV = 0;
						if ((FLG2 & 0x10) != 0 || (FLG3 & 4) != 0) {
							fq_1.MT_KaijyoCont = 30;
							fq_1.MT_KaijyoTeam = TM_NUM;
						}
					}
				}
				if ((STYP & 1) != 0 && TEKI_LEN < K_OIKAKE_S)
					b_oikake = true;
				if ((STYP & 2) != 0 && TEKI_LEN < K_SARTCH)
					if ((STYP & 0x20) != 0) {
						if (team.teki_search_op1(TOOSI_NUM, X, Y, K_SARTCH,
								gamennai, kakomisuu) >= 0)
							b_oikake = true;
					} else {
						b_oikake = true;
					}
				if (b_rest)
					b_oikake = false;
			}
			if (TM_NUM == fq_1.Control_Team) {
				if (fq_1.CommandCode != 1)
					b_oikake = false;
				if (fq_1.CommandCode == 3)
					b_s_nige = false;
			}
			if (b_nige || (FLG3 & 4) != 0) {
				b_oikake = false;
				b_s_nige = false;
			}
			if (!fq_1.b_AutoMove && TOOSI_NUM == fq_1.Control_Hito) {
				b_move = controll_move();
			} else {
				if ((TM_NUM != fq_1.Control_Team || fq_1.CommandCode == 1)
						&& !b_nige && mov_off == 0 && hassya_chk_auto() >= 0
						&& (FLG3 & 4) == 0 && TEKI_LEN > 2
						&& TEKI_LEN < K_YUMI_FIRE) {
					int d = team.onfire_line(TOOSI_NUM, X, Y, 30);
					if (d >= 0) {
						FLG2 |= 0x80;
						XMOKU = X;
						YMOKU = Y;
						DIR = d;
						hassya(1);
						b_move = true;
					}
				}
				if (!b_move && b_nige) {
					if (nige_move())
						b_move = true;
					if ((BitMapAtari & 8) != 0 && TM_NUM != fq_1.Control_Team
							&& (BitMapAtari & 3) == DIR_NIGE)
						FL |= 8;
				}
				if (!b_move && b_s_nige && sekkin_nige_move())
					b_move = true;
				if (!b_rest && !b_move && b_oikake && tuiseki_move())
					b_move = true;
				if (!b_rest && !b_move && (FLG3 & 4) == 0)
					if ((FLG3 & 0x10) != 0)
						random_move();
					else
						form_move();
			}
			if (TM_NUM == fq_1.Control_Team && TM_POS1 == 0)
				team.form_tuibi_in(X, Y);
			if (TOOSI_NUM == fq_1.Control_Hito) {
				fq_1.DistanceX = 0;
				fq_1.DistanceY = 0;
				drawx = (X * 16 + draw_dx) - fq_1.DispMapX;
				drawy = (Y * 16 + draw_dy) - fq_1.DispMapY;
				fq_1.Control_Hito_GX = drawx;
				fq_1.Control_Hito_GY = drawy + fq_1.GameSY;
				if (!fq_1.b_KyouseiScoll)
					if ((FL & 0x80) == 0) {
						grand.Hito_screen(X, Y);
					} else {
						if (drawx > 352)
							fq_1.DistanceX = -1;
						if (drawx < 288)
							fq_1.DistanceX = 1;
						if (drawy > 208)
							fq_1.DistanceY = -1;
						if (drawy < 176)
							fq_1.DistanceY = 1;
					}
			}
			if (XO != X && XO2 != XO && XO2 == X || YO != Y && YO2 != YO
					&& YO2 == Y) {
				SIKO_C++;
				MOVES++;
			}
		}
		calc_cpn();
		if ((FLG2 & 0x80) == 0) {
			mov_off++;
			if (mov_off >= MovWait)
				mov_off = 0;
		}
	}

	int hassya_chk_use() {
		if (ITEM == 10)
			return 3;
		if (ITEM == 11)
			return 4;
		if (ITEM == 12)
			return 6;
		if (ITEM == 13)
			return 11;
		if (ITEM == 16)
			return 8;
		if (ITEM == 17)
			return 9;
		if (ITEM == 18)
			return 0;
		if (ITEM == 19)
			return 5;
		if (ITEM == 20)
			return 10;
		if (ITEM == 23)
			return 12;
		if ((CTOK & 0x40) != 0)
			return 1;
		return (CTOK & 0x80) == 0 ? -1 : 3;
	}

	int hassya_chk_auto() {
		if (ITEM == 10)
			return 3;
		if (ITEM == 11)
			return 4;
		if (ITEM == 12)
			return 6;
		if (ITEM == 16)
			return 8;
		if (ITEM == 18)
			return 0;
		if ((CTOK & 0x40) != 0)
			return 1;
		return (CTOK & 0x80) == 0 ? -1 : 3;
	}

	void hassya(int autof) {
		int nani;
		if (autof == 0)
			nani = hassya_chk_use();
		else
			nani = hassya_chk_auto();
		if (nani < 0)
			return;
		FLG1 |= 4;
		if (CUNTY == 0) {
			switch (nani) {
			case 0: // '\0'
				CUNTY = 30;
				break;

			case 1: // '\001'
				ya.Ya_Init(1, TOOSI_NUM, -1, X, Y, DIR & 3, ZOKUJ, PAT, ITEM,
						FL);
				CUNTY = 20;
				break;

			case 2: // '\002'
				ya.Ya_Init(2, TOOSI_NUM, -1, X, Y, 0, ZOKUJ, PAT, ITEM, FL);
				CUNTY = 20;
				break;

			case 3: // '\003'
				ya.Ya_Init(3, TOOSI_NUM, -1, X, Y, DIR & 3, ZOKUJ, PAT, ITEM,
						FL);
				CUNTY = 30;
				break;

			case 4: // '\004'
				ya.Ya_Init(4, TOOSI_NUM, -1, X, Y, DIR & 3, ZOKUJ, PAT, ITEM,
						FL);
				CUNTY = 20;
				break;

			case 5: // '\005'
				ya.Ya_Init(5, TOOSI_NUM, -1, X, Y, 0, ZOKUJ, PAT, ITEM, FL);
				CUNTY = 20;
				break;

			case 6: // '\006'
				ya.Ya_Init(6, TOOSI_NUM, -1, X, Y, 0, ZOKUJ, PAT, ITEM, FL);
				CUNTY = 40;
				break;

			case 7: // '\007'
				ya.Ya_Init(7, TOOSI_NUM, -1, X, Y, 0, ZOKUJ, PAT, ITEM, FL);
				CUNTY = 50;
				break;

			case 8: // '\b'
				ya.Ya_Init(8, TOOSI_NUM, -1, X, Y, 0, ZOKUJ, PAT, ITEM, FL);
				CUNTY = 20;
				break;

			case 9: // '\t'
				CUNTY = 40;
				break;

			case 10: // '\n'
				CUNTY = 50;
				break;

			case 11: // '\013'
				CUNTY = 50;
				break;

			case 12: // '\f'
				CUNTY = 50;
				break;
			}
			if (FATIG < 100)
				FATIG++;
			if (AT_CNT == 0)
				AT_CNT = 8;
		}
		DIR &= 3;
	}

	boolean ya_hit_check() {
		if ((FL & 0x37) != 0)
			return false;
		int da = ya.Ya_damege(X, Y, 2, ZOKUJ, PYD, PMD, PYR, ITEM);
		if (da != 0) {
			team.givedom(fq_1.ya_hito, TOOSI_NUM, da);
			if (LIFE <= 0)
				team.dead(TOOSI_NUM);
			return true;
		} else {
			return false;
		}
	}

	boolean map_atari(int mx, int my) {
		if (mx < fq_1.MXS || my < fq_1.MYS || mx >= fq_1.MXLM
				|| my >= fq_1.MYLM)
			return false;
		MapAtari = fq_1.mapatrdata[my * fq_1.MXLM + mx];
		return (MapAtari & 0x80) != 0;
	}

	boolean map_houkou_atari(int dir, int mx, int my) {
		BitMapAtari = 0;
		dx = 0;
		dy = 0;
		switch (dir) {
		default:
			break;

		case 0: // '\0'
			if (my <= y_movmin) {
				BitMapAtari = 8;
				return true;
			}
			if ((CTOK & 0x10) == 0
					&& (map_atari(mx, my) || map_atari(mx + 1, my))) {
				BitMapAtari = 4;
				return true;
			}
			dy = -1;
			break;

		case 1: // '\001'
			if (mx <= x_movmin) {
				BitMapAtari = 9;
				return true;
			}
			if ((CTOK & 0x10) == 0 && map_atari(mx - 1, my + 1)) {
				BitMapAtari = 5;
				return true;
			}
			dx = -1;
			break;

		case 2: // '\002'
			if (my >= y_movmax) {
				BitMapAtari = 10;
				return true;
			}
			if ((CTOK & 0x10) == 0
					&& (map_atari(mx, my + 2) || map_atari(mx + 1, my + 2))) {
				BitMapAtari = 6;
				return true;
			}
			dy = 1;
			break;

		case 3: // '\003'
			if (mx >= x_movmax) {
				BitMapAtari = 11;
				return true;
			}
			if ((CTOK & 0x10) == 0 && map_atari(mx + 2, my + 1)) {
				BitMapAtari = 7;
				return true;
			}
			dx = 1;
			break;
		}
		return false;
	}

	void map_houkou(int dir) {
		dx = 0;
		dy = 0;
		switch (dir) {
		case 0: // '\0'
			dy = -1;
			break;

		case 1: // '\001'
			dx = -1;
			break;

		case 2: // '\002'
			dy = 1;
			break;

		case 3: // '\003'
			dx = 1;
			break;
		}
	}

	boolean map_next(int dir) {
		XMOKU = X;
		YMOKU = Y;
		switch (dir) {
		default:
			break;

		case 0: // '\0'
			if (Y > y_movmin)
				YMOKU = Y - 1;
			else
				return false;
			break;

		case 1: // '\001'
			if (X > x_movmin)
				XMOKU = X - 1;
			else
				return false;
			break;

		case 2: // '\002'
			if (Y < y_movmax)
				YMOKU = Y + 1;
			else
				return false;
			break;

		case 3: // '\003'
			if (X < x_movmax)
				XMOKU = X + 1;
			else
				return false;
			break;
		}
		return true;
	}

	void stop_dir(int d) {
		XMOKU = X;
		YMOKU = Y;
		FLG2 |= 0x80;
		DIR = d;
		SIKO_C++;
	}

	void set_dir(int ddd) {
		FLG2 &= 0xffffff7f;
		int flg = 1;
		int d = DIR;
		DIR = ddd;
		if (!map_next(DIR)) {
			FLG2 |= 0x80;
			BitMapAtari = 8 + (DIR & 3);
			return;
		}
		if (TEKI_LEN >= 0 && TEKI_LEN < K_OIKAKE_L + 2)
			flg = 0;
		if (team.hito_check_move(TOOSI_NUM, XMOKU, YMOKU, DIR, flg) >= 0) {
			if ((SIKO_C & 8) != 0)
				DIR++;
			else
				DIR--;
			DIR &= 3;
			map_houkou_atari(DIR, X, Y);
			if ((BitMapAtari & 4) != 0) {
				stop_dir(d);
				return;
			}
			if (!map_next(DIR)) {
				FLG2 |= 0x80;
				BitMapAtari = 8 + (DIR & 3);
				return;
			}
			if (team.hito_check_move(TOOSI_NUM, XMOKU, YMOKU, DIR, flg) >= 0) {
				stop_dir(d);
				return;
			}
			CUNT2 = (int) (Math.random() * 2D) + 1;
		}
	}

	void set_dir_c(int ddd) {
		FLG2 &= 0xffffff7f;
		int d = DIR;
		DIR = ddd;
		if (!map_next(DIR)) {
			FLG2 |= 0x80;
			BitMapAtari = 8 + (DIR & 3);
			return;
		}
		if (team.hito_check_move2(TOOSI_NUM, XMOKU, YMOKU, DIR) >= 0)
			stop_dir(d);
	}

	public void move(int move_x, int move_y) {
		XO2 = XO;
		YO2 = YO;
		XO = X;
		YO = Y;
		X += move_x;
		if (X < x_movmin) {
			X = x_movmin;
			CUNT1 = 0;
		}
		if (X > x_movmax) {
			X = x_movmax;
			CUNT1 = 0;
		}
		Y += move_y;
		if (Y < y_movmin) {
			Y = y_movmin;
			CUNT1 = 0;
		}
		if (Y > y_movmax) {
			Y = y_movmax;
			CUNT1 = 0;
		}
		XMOKU = X;
		YMOKU = Y;
		MOVEC++;
		CTOK = fq_1.HMBUF[CHN][2];
		if (DIR == 4 || XO != X || YO != Y) {
			Anime_C++;
			Anime_C &= 3;
		}
	}

	void dir_move() {
		if (mov_off > mov_off / 2 || mov_off == 0)
			map_houkou_atari(DIR, X, Y);
		else
			map_houkou(DIR);
		draw_dx = dx * MovStep * mov_off;
		draw_dy = dy * MovStep * mov_off;
		if (mov_off == 0) {
			FLG3 &= -9;
			move(dx, dy);
		} else {
			FLG3 |= 8;
		}
	}

	void goto_dir(int d) {
		if (d >= 0 && d < 4)
			if ((SIKO_C & 0x10) != 0) {
				map_houkou_atari(d, X, Y);
				if ((BitMapAtari & 4) != 0) {
					DIR_H = d;
					CUNT1 = 6;
					int ddd = grand.Tutai_idosub(DIR_H, X, Y, SIKO_C & 8);
					if (ddd >= 0 && ddd < 4)
						set_dir(ddd);
					return;
				}
				int dd = grand.go_keisan(d, X, Y);
				if (dd >= 0 && dd < 4)
					set_dir(dd);
				if (dd == -1)
					BitMapAtari = 8 + (d & 3);
			} else {
				int dd = grand.go_keisan(d, X, Y);
				if (dd >= 0 && dd < 4)
					set_dir(dd);
				if (dd == -1)
					BitMapAtari = 8 + (d & 3);
			}
	}

	int goto_mokuhyou(int mx, int my, int yumi) {
		int dx = mx - X;
		int dy = my - Y;
		if (dx == 0 && dy == 0) {
			CUNT1 = 0;
			MOVEC = 0;
			MOVES = 0;
			return -1;
		}
		if (CUNT1 > 0) {
			int ddd = grand.Tutai_idosub(DIR_H, X, Y, SIKO_C & 8);
			if (ddd >= 0 && ddd < 4)
				set_dir(ddd);
			CUNT1--;
			return ddd;
		}
		int d = grand.Nerai_xy(X, Y, mx, my, SIKO_C & 4);
		if (d >= 0 && d < 4) {
			if ((SIKO_C & 0x10) != 0) {
				map_houkou_atari(d, X, Y);
				if ((BitMapAtari & 4) != 0) {
					DIR_H = d;
					CUNT1 = 6;
					int ddd = grand.Tutai_idosub(DIR_H, X, Y, SIKO_C & 8);
					if (ddd >= 0 && ddd < 4) {
						set_dir(ddd);
						return ddd;
					} else {
						return d;
					}
				}
				int dd = grand.go_keisan(d, X, Y);
				if (dd >= 0 && dd < 4) {
					set_dir(dd);
					return dd;
				}
			} else {
				int dd = grand.go_keisan(d, X, Y);
				if (dd >= 0 && dd < 4) {
					set_dir(dd);
					return dd;
				}
			}
			return d;
		} else {
			return -1;
		}
	}

	void goto_mokuhyou_hanten(int mx, int my) {
		if (CUNT1 > 0) {
			int ddd = grand.Tutai_idosub(DIR_H, X, Y, SIKO_C & 8);
			if (ddd >= 0 && ddd < 4)
				set_dir(ddd);
			CUNT1--;
			return;
		}
		int d = grand.Nige(X, Y, mx, my, 1);
		if (d >= 0 && d < 4)
			if ((SIKO_C & 0x10) != 0) {
				map_houkou_atari(d, X, Y);
				if ((BitMapAtari & 4) != 0) {
					DIR_H = d;
					CUNT1 = 6;
					int ddd = grand.Tutai_idosub(DIR_H, X, Y, SIKO_C & 8);
					if (ddd >= 0 && ddd < 4)
						set_dir(ddd);
					return;
				}
				int dd = grand.go_keisan(d, X, Y);
				if (dd >= 0 && dd < 4)
					set_dir(dd);
			} else {
				int dd = grand.go_keisan(d, X, Y);
				if (dd >= 0 && dd < 4)
					set_dir(dd);
			}
	}

	void map_limmit() {
		if (TM_POS1 == 0) {
			if (DIR == DIR_NIGE)
				team.Team_MapOut(0);
			if (DIR == (DIR_NIGE + 2 & 3))
				team.Team_MapOut(1);
		}
	}

	boolean controll_move() {
		boolean b_moved = false;
		if (mov_off != 0) {
			dir_move();
			b_moved = true;
		} else {
			if ((FLG3 & 8) != 0) {
				dir_move();
				b_moved = true;
			}
			FLG2 |= 0x80;
			if (fq_1.b_KeyPressd) {
				int ddd = fq_1.Control_Key;
				if (ddd >= 0 && ddd < 4) {
					ddd = grand.go_keisan(ddd, X, Y);
					if (ddd == -1)
						map_limmit();
					if (ddd >= 0 && ddd < 4)
						set_dir_c(ddd);
				} else if (ddd == 5) {
					hassya(0);
					return b_moved;
				}
			} else if (fq_1.DragFlag) {
				if ((X == fq_1.Map_Mouse_X || X + 1 == fq_1.Map_Mouse_X)
						&& (Y == fq_1.Map_Mouse_Y || Y + 1 == fq_1.Map_Mouse_Y)) {
					hassya(0);
					return b_moved;
				}
				int d = -1;
				d = grand.Nerai(X, Y, fq_1.Map_Mouse_X, fq_1.Map_Mouse_Y, 0);
				if (d >= 0 && d < 4) {
					int dd = grand.go_keisan(d, X, Y);
					if (dd == -1)
						map_limmit();
					if (dd >= 0 && dd < 4)
						set_dir_c(dd);
				}
			}
		}
		return b_moved;
	}

	boolean nige_move() {
		boolean b_moved = false;
		if (DIR_NIGE >= 4)
			if (sekkin_nige_move())
				return true;
			else
				return b_moved;
		if (mov_off != 0) {
			dir_move();
			b_moved = true;
		} else {
			if ((FLG3 & 8) != 0) {
				dir_move();
				b_moved = true;
			}
			FLG2 |= 0x80;
			if (team.teki_check(TOOSI_NUM) >= 0) {
				stop_dir(DIR);
				return b_moved;
			}
			if (CUNT1 > 0) {
				int ddd = grand.Tutai_idosub(DIR_H, X, Y, SIKO_C & 8);
				if (ddd >= 0 && ddd < 4)
					set_dir(ddd);
				CUNT1--;
				return b_moved;
			}
			goto_dir(DIR_NIGE);
			b_moved = true;
		}
		return b_moved;
	}

	boolean sekkin_nige_move() {
		boolean b_moved = false;
		int teki_len_o = TEKI_LEN;
		int d = DIR;
		if (mov_off != 0) {
			dir_move();
			b_moved = true;
		} else {
			if ((FLG3 & 8) != 0) {
				dir_move();
				b_moved = true;
			}
			FLG2 |= 0x80;
			if (team.teki_check(TOOSI_NUM) >= 0) {
				stop_dir(DIR);
				return b_moved;
			}
			int dx = XNE - X;
			int dy = YNE - Y;
			if (dx == 0 && dy == 0)
				CUNT1 = 0;
			else if (TEKI_LEN < K_YUMI_NIGE) {
				goto_mokuhyou_hanten(XNE, YNE);
				if (team.teki_search(TOOSI_NUM, XMOKU, YMOKU, K_YUMI_NIGE + 2,
						0, 256) >= 0 && teki_len_o >= TEKI_LEN)
					stop_dir(d);
				b_moved = true;
			}
		}
		return b_moved;
	}

	boolean tuiseki_move() {
		boolean b_moved = false;
		int d = DIR;
		if (mov_off != 0) {
			dir_move();
			b_moved = true;
		} else {
			if ((FLG3 & 8) != 0) {
				dir_move();
				b_moved = true;
			}
			FLG2 |= 0x80;
			if (team.teki_check(TOOSI_NUM) >= 0) {
				stop_dir(DIR);
				return b_moved;
			}
			if (CUNT2 == 0) {
				if (MOVES > 128) {
					CUNT2 = (int) (Math.random() * 4D) + 2;
					goto_dir(DIR);
					MOVES = 0;
				} else if ((CTOK & 0xc0) != 0) {
					goto_mokuhyou(XNE, YNE, 1);
					if (team.teki_search(TOOSI_NUM, XMOKU, YMOKU,
							K_YUMI_NIGE + 2, 0, 256) >= 0
							&& TEKI_LEN < K_YUMI_NIGE)
						stop_dir(d);
				} else {
					goto_mokuhyou(XNE, YNE, 0);
				}
			} else {
				CUNT2--;
				goto_dir(DIR);
			}
			b_moved = true;
		}
		return b_moved;
	}

	boolean form_move() {
		boolean b_moved = false;
		if (TM_POS1 == 0)
			return b_moved;
		int teki_len_o = TEKI_LEN;
		int d = DIR;
		int flg = 1;
		int moku = 0;
		if (TEKI_LEN >= 0 && TEKI_LEN < K_OIKAKE_L + 2)
			flg = 0;
		if (mov_off != 0) {
			dir_move();
			b_moved = true;
		} else {
			if ((FLG3 & 8) != 0) {
				dir_move();
				b_moved = true;
			}
			FLG2 |= 0x80;
			if (team.teki_check(TOOSI_NUM) >= 0) {
				stop_dir(DIR);
				return b_moved;
			}
			if (CUNT2 == 0) {
				if (MOVES > 128) {
					DIR = (int) (Math.random() * 4D);
					CUNT2 = (int) (Math.random() * 4D) + 2;
					MOVES = 0;
				} else {
					int bx = team.Hito_XMOKU(TM_BOSS);
					int by = team.Hito_YMOKU(TM_BOSS);
					if (TM_NUM == fq_1.Control_Team && team.FORM[TM_NUM] > 20)
						grand.kaihi_hosei(X, Y,
								team.Formation_abs[TM_POS1 * 6],
								team.Formation_abs[TM_POS1 * 6 + 1]);
					else
						grand.kaihi_hosei(X, Y, bx + XFOR, by + YFOR);
					int c = team.hito_check_move(TOOSI_NUM, grand.moku_X,
							grand.moku_Y, DIR, flg);
					if (MOVEC > 10 && (FLG3 & 1) == 0 && c >= 0) {
						stop_dir(d);
						return b_moved;
					}
					moku = goto_mokuhyou(grand.moku_X, grand.moku_Y, 0);
					if ((CTOK & 0xc0) != 0 && TEKI_LEN > 0
							&& TEKI_LEN < K_YUMI_NIGE + 2) {
						team.teki_search(TOOSI_NUM, XMOKU, YMOKU,
								K_YUMI_NIGE + 2, 0, 256);
						if (teki_len_o >= TEKI_LEN)
							stop_dir(d);
					}
					if (moku == -1 && team.FORM[TM_NUM] < 20
							&& (TEKI_LEN < 0 || TEKI_LEN > K_OIKAKE_L + 5))
						stop_dir(team.Hito_DIR(TM_BOSS));
				}
			} else {
				CUNT2--;
				goto_dir(DIR);
				b_moved = true;
			}
		}
		return b_moved;
	}

	boolean random_move() {
		boolean b_moved = false;
		if (mov_off != 0) {
			dir_move();
			b_moved = true;
		} else {
			if ((FLG3 & 8) != 0) {
				dir_move();
				b_moved = true;
			}
			FLG2 |= 0x80;
			if (team.teki_check(TOOSI_NUM) >= 0) {
				stop_dir(DIR);
				return b_moved;
			}
			if (CUNT1 == 0) {
				mov_off = 0;
				DIR = (int) (Math.random() * 5D);
				if (DIR == 4) {
					CUNT1 = ((int) (Math.random() * 3D) + 2) * MovWait;
				} else {
					CUNT1 = ((int) (Math.random() * 5D) + 3) * MovWait;
					set_dir(DIR);
				}
			} else {
				if ((BitMapAtari & 8) != 0)
					DIR = BitMapAtari + 2 & 3;
				CUNT1--;
				if ((BitMapAtari & 4) != 0)
					CUNT1 = 0;
				set_dir(DIR);
				b_moved = true;
			}
		}
		return b_moved;
	}

	void calc_cpn() {
		int dd = DIR;
		int count;
		if ((FLG1 & 4) != 0) {
			if (AT_CNT != 0)
				count = (8 - AT_CNT) / 2 & 3;
			else
				count = fq_1.Sys_Count2 & 3;
		} else if ((CTOK & 1) != 0)
			count = fq_1.Sys_Count1 & 3;
		else
			count = Anime_C & 3;
		switch (dd) {
		case 0: // '\0'
			CPN = 8 + count;
			break;

		case 1: // '\001'
			CPN = 16 + count;
			break;

		case 2: // '\002'
			CPN = 0 + count;
			break;

		case 3: // '\003'
			CPN = 24 + count;
			break;

		case 4: // '\004'
			switch (count) {
			case 0: // '\0'
				CPN = 8;
				break;

			case 1: // '\001'
				CPN = 16;
				break;

			case 2: // '\002'
				CPN = 0;
				break;

			case 3: // '\003'
				CPN = 24;
				break;
			}
			break;

		default:
			CPN = 0;
			break;
		}
		if ((FLG1 & 4) != 0 && AT_CNT != 0)
			CPN += 4;
		if ((FLG1 & 8) != 0) {
			count = fq_1.Sys_Count1 & 1;
			CPN = 32 + count;
		}
		if ((FL & 0x30) != 0) {
			if ((FL & 0x10) != 0)
				count = 1;
			else
				count = 0;
			if ((CTOK & 2) != 0)
				CPN = 32 + count;
			else
				CPN = 34 + count;
		}
		if ((FL & 1) != 0) {
			count = fq_1.Sys_Count1 & 1;
			CPN = 34 + count;
		}
	}

	public void paint(Graphics g) {
		Image_Off_Y = CharaWidth * CPN;
		if (TOOSI_NUM == fq_1.Control_Hito && fq_1.i_Pouse == 2
				&& (fq_1.Paint_count & 8) == 0)
			return;
		if ((FL & 0x80) != 0) {
			byte ch1 = fq_1.mapatrdata[Y * fq_1.MXLM + X];
			ch1 &= 3;
			byte ch2 = fq_1.mapatrdata[(Y + 1) * fq_1.MXLM + X];
			ch2 &= 3;
			byte ch3 = fq_1.mapatrdata[Y * fq_1.MXLM + (X + 1)];
			ch3 &= 3;
			byte ch4 = fq_1.mapatrdata[(Y + 1) * fq_1.MXLM + (X + 1)];
			ch4 &= 3;
			g.drawImage(fq_1.CharaImage[CHN_D], drawx, drawy + fq_1.GameSY,
					drawx + CharaWidth, drawy + fq_1.GameSY + CharaHeight, 0,
					Image_Off_Y, CharaWidth, Image_Off_Y + CharaHeight, this);
			if ((CTOK & 0x10) == 0) {
				if (ch1 != 0 || ch2 != 0) {
					int sc_sx = fq_1.DispMapX + drawx;
					int sc_sy = fq_1.DispMapY + drawy;
					if (ch1 == 1 && ch2 == 1)
						g.drawImage(fq_1.Map2, drawx, drawy + fq_1.GameSY,
								drawx + 32, drawy + fq_1.GameSY + 32, sc_sx,
								sc_sy, sc_sx + 32, sc_sy + 32, this);
					if (ch1 == 3)
						g.drawImage(fq_1.Map2, drawx, drawy + fq_1.GameSY,
								drawx + 32, drawy + fq_1.GameSY + 32, sc_sx,
								sc_sy, sc_sx + 32, sc_sy + 32, this);
				}
				if (ch3 != 0 || ch4 != 0) {
					int sc_sx = fq_1.DispMapX + drawx + 32;
					int sc_sy = fq_1.DispMapY + drawy;
					if (ch3 == 1 && ch4 == 1)
						g.drawImage(fq_1.Map2, drawx + 32, drawy + fq_1.GameSY,
								drawx + 64, drawy + fq_1.GameSY + 32, sc_sx,
								sc_sy, sc_sx + 32, sc_sy + 32, this);
					if (ch3 == 3)
						g.drawImage(fq_1.Map2, drawx + 32, drawy + fq_1.GameSY,
								drawx + 64, drawy + fq_1.GameSY + 32, sc_sx,
								sc_sy, sc_sx + 32, sc_sy + 32, this);
				}
				if (ch2 != 0) {
					int sc_sx = fq_1.DispMapX + drawx;
					int sc_sy = fq_1.DispMapY + drawy + 32;
					if (ch2 == 2)
						g.drawImage(fq_1.Map2, drawx, drawy + fq_1.GameSY + 32,
								drawx + 32, drawy + fq_1.GameSY + CharaHeight,
								sc_sx, sc_sy, sc_sx + 32, sc_sy + 32, this);
					if (ch2 == 1 || ch2 == 3)
						g.drawImage(fq_1.Map2, drawx, drawy + fq_1.GameSY + 32,
								drawx + 32, drawy + fq_1.GameSY + CharaHeight,
								sc_sx, sc_sy, sc_sx + 32, sc_sy + 32, this);
				}
				if (ch4 != 0) {
					int sc_sx = fq_1.DispMapX + drawx + 32;
					int sc_sy = fq_1.DispMapY + drawy + 32;
					if (ch4 == 2)
						g.drawImage(fq_1.Map2, drawx + 32, drawy + fq_1.GameSY
								+ 32, drawx + 64, drawy + fq_1.GameSY
								+ CharaHeight, sc_sx, sc_sy, sc_sx + 32,
								sc_sy + 32, this);
					if (ch4 == 1 || ch4 == 3)
						g.drawImage(fq_1.Map2, drawx + 32, drawy + fq_1.GameSY
								+ 32, drawx + 64, drawy + fq_1.GameSY
								+ CharaHeight, sc_sx, sc_sy, sc_sx + 32,
								sc_sy + 32, this);
				}
			}
		} else {
			System.out.println("screen out error");
		}
	}

	private static final long serialVersionUID = 1L;
	Applet applet;
	int CharaWidth;
	int CharaHeight;
	int MovStep;
	int MovWait;
	int drawx;
	int drawy;
	int draw_dx;
	int draw_dy;
	int dx;
	int dy;
	int mov_off;
	int Image_Off_Y;
	int Anime_C;
	int BitMapAtari;
	int x_movmin;
	int y_movmin;
	int x_movmax;
	int y_movmax;
	int TOOSI_NUM;
	int HITO_NUM;
	int TM_NUM;
	int TM_POS1;
	int TM_BOSS;
	int CharaClass;
	int FL;
	int FLG1;
	int FLG2;
	int FLG3;
	int CPN;
	int CHN;
	int CHN_D;
	int XLM;
	int PLM;
	int LSHIP;
	int CTOK;
	int MTOK;
	int ATYP;
	int STYP;
	int RTYP;
	int RRAT;
	int XFOR;
	int YFOR;
	int PAT;
	int PDF;
	int PAR;
	int PDR;
	int PYD;
	int PMD;
	int PYR;
	int DIR;
	int DIR_O;
	int DIR_H;
	int DIR_NIGE;
	int SPEED;
	int CUNTY;
	int CUNT1;
	int CUNT2;
	int CUNTK;
	int MO_CNT;
	int AT_CNT;
	int SIKO_C;
	int MOVEC;
	int MOVES;
	int X;
	int Y;
	int XO;
	int YO;
	int XO2;
	int YO2;
	int XH;
	int YH;
	int XNE;
	int YNE;
	int XMOKU;
	int YMOKU;
	int HEAL;
	int HEAL_C;
	int LIFE;
	int MLIF;
	int FATIG;
	int NAMENO;
	int FATG;
	int FATC;
	int LV;
	int LEVELC;
	int LEVELUPC;
	int ZOKUJ;
	int ZOKUA;
	int AITE_A;
	int AITE_D;
	int TEKI_LEN;
	int T_NERAWARE;
	int T_KAKOMARE;
	int SERIFEV;
	int ITEM;
	int K_YUMI_NIGE;
	int K_YUMI_FIRE;
	int K_OIKAKE_S;
	int K_OIKAKE_L;
	int K_SARTCH;
	byte MapToku;
	byte MapAtari;
	Fq_1 fq_1;
	TeamManeger team;
	Grand grand;
	YaManeger ya;
}
