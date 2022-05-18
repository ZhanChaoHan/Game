// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Fq_1.java

package applet;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.Date;

// Referenced classes of package applet:
//            YaManeger, Grand, TeamManeger, Status, 
//            Gocha, Suuji

public class Fq_1 extends Applet implements Runnable, ActionListener,
		KeyListener, MouseListener, MouseMotionListener {

	public Fq_1() {
		AppletWidth = 640;
		AppletHeight = 480;
		thread = null;
		ITEMMAX = 30;
		GameSX = 0;
		GameSY = 40;
		GameWidth = 640;
		GameHeight = 384;
		CharaImage = new Image[64];
		DCharaImage = new Image[4];
		CIL_SUU = 0;
		CIL_Table = new int[40];
		EffectSound = new AudioClip[10];
		SoundState = 0;
		MouseX = 0;
		MouseY = 0;
		DistanceX = 0;
		DistanceY = 0;
		ya_hito = 0;
		item_hito = -1;
		CommandCode = 1;
		DispLine_1 = 0;
		DispLine_3 = 3;
		DispMark = 0;
		b_DrawLife = false;
		b_DrawFatig = false;
		b_MCCFlg = false;
		GameStep = 0;
		MouseBK_C = 0;
		DragFlag = false;
		MouseBK = false;
		Bottan_L = false;
		Bottan_R = false;
		b_KeyPressd = false;
		b_KyouseiScoll = false;
		b_AutoMove = false;
		b_Escape = false;
		i_Pouse = 0;
		b_YN_MODE = false;
		i_YN = 0;
		MT_MessageCont = 0;
		MT_ItemGetCont = 0;
		MT_KeikokuCont = 0;
		MT_KaijyoCont = 0;
		MT_MessageNum = 0;
		MT_ItemGetNum = 0;
		MT_KeikokuNum = 0;
		MT_MapOutNum = -1;
		MT_KaijyoTeam = 0;
		MT_ItemGetStat = 0;
		Control_Team = 0;
		Control_Hito = 0;
		Control_Key = 0;
		Control_Hito_GX = 0;
		Control_Hito_GY = 0;
		Total_Hito = 0;
		ITEM_PACK = new int[32];
		ITEM_KAISYUU = new int[32];
		MAP_ITEM = new int[32];
		MEMBER_HAGURE = new int[20];
		Paint_count = 0;
		Fail_count = 0;
		b_DrawTestAtr = false;
		DrawTestAtrState = 0;
		ErrorCode = 0;
		now = new Date();
		nowtime = now.getTime();
		Sys_TurnCount = 0;
		Sys_Count1 = 0;
		Sys_Count1w = 0;
		Sys_Count2 = 0;
		Sys_Count2w = 0;
		mapatrdata = new byte[0x10000];
		hparadata = new byte[16384];
		grbufdata = new byte[4864];
	}

	public int random(int max) {
		return (int) (Math.random() * (double) max);
	}

	public void set_RandSE(int sound, int rand) {
		if (sound < 0 || sound >= 10)
			return;
		if (random(rand) == 0)
			EffectSound[sound].play();
	}

	public boolean Headon_m(int mx, int my, int tx, int ty, int mlen,
			int alen_x, int alen_y) {
		if (tx <= mx && mx < tx + alen_x && ty <= my && my < ty + alen_y)
			return true;
		if (mlen > 1) {
			if (tx <= mx + 1 && mx + 1 < tx + alen_x && ty <= my
					&& my < ty + alen_y)
				return true;
			if (tx <= mx && mx < tx + alen_x && ty <= my + 1
					&& my + 1 < ty + alen_y)
				return true;
			if (tx <= mx + 1 && mx + 1 < tx + alen_x && ty <= my + 1
					&& my + 1 < ty + alen_y)
				return true;
		}
		return false;
	}

	public int Attack(int mx, int my, int tx, int ty, int mlen, int alen) {
		if (tx + alen >= mx && mx + mlen >= tx && (ty + alen) - 1 >= my
				&& (my + mlen) - 1 >= ty)
			return mx <= tx ? 3 : 1;
		if ((tx + alen) - 1 >= mx && (mx + mlen) - 1 >= tx && ty + alen >= my
				&& my + mlen >= ty)
			return my <= ty ? 2 : 0;
		else
			return -1;
	}

	public boolean Headon(int mx, int my, int tx, int ty, int mlen, int alen_x,
			int alen_y) {
		if (tx <= mx && mx < tx + alen_x && ty <= my && my < ty + alen_y)
			return true;
		if (mlen > 1) {
			if (tx <= mx + 1 && mx + 1 < tx + alen_x && ty <= my
					&& my < ty + alen_y)
				return true;
			if (tx <= mx && mx < tx + alen_x && ty <= my + 1
					&& my + 1 < ty + alen_y)
				return true;
			if (tx <= mx + 1 && mx + 1 < tx + alen_x && ty <= my + 1
					&& my + 1 < ty + alen_y)
				return true;
		}
		return false;
	}

	public boolean Headon_dir(int mx, int my, int tx, int ty, int dir,
			int alen_x, int alen_y) {
		switch (dir) {
		default:
			break;

		case 0: // '\0'
			if (tx <= mx && mx < tx + alen_x && ty <= my && my < ty + alen_y)
				return true;
			if (tx <= mx + 1 && mx + 1 < tx + alen_x && ty <= my
					&& my < ty + alen_y)
				return true;
			break;

		case 1: // '\001'
			if (tx <= mx && mx < tx + alen_x && ty <= my && my < ty + alen_y)
				return true;
			if (tx <= mx && mx < tx + alen_x && ty <= my + 1
					&& my + 1 < ty + alen_y)
				return true;
			break;

		case 2: // '\002'
			if (tx <= mx && mx < tx + alen_x && ty <= my + 1
					&& my + 1 < ty + alen_y)
				return true;
			if (tx <= mx + 1 && mx + 1 < tx + alen_x && ty <= my + 1
					&& my + 1 < ty + alen_y)
				return true;
			break;

		case 3: // '\003'
			if (tx <= mx + 1 && mx + 1 < tx + alen_x && ty <= my
					&& my < ty + alen_y)
				return true;
			if (tx <= mx + 1 && mx + 1 < tx + alen_x && ty <= my + 1
					&& my + 1 < ty + alen_y)
				return true;
			break;
		}
		return false;
	}

	public int kyori(int mx, int my, int tx, int ty) {
		double dx = (double) tx - (double) mx;
		double dy = (double) ty - (double) my;
		int sq = (int) Math.sqrt(dx * dx + dy * dy);
		return sq;
	}

	public int Get_hpara(int hito_num, int Hp_off) {
		if (hito_num < 0 || hito_num >= 512 || Hp_off < 0 || Hp_off >= 32)
			return -1;
		int byt = hparadata[32 * hito_num + Hp_off];
		if (byt < 0)
			byt += 256;
		if (byt < 0 || byt >= 256)
			return -1;
		else
			return byt;
	}

	public int Get_hpara_mon(int hito_num, int Hp_off) {
		if (hito_num < 0 || hito_num >= 64 || Hp_off < 0 || Hp_off >= 32)
			return -1;
		int byt = hparadata[32 * HparaNum[hito_num] + Hp_off];
		if (byt < 0)
			byt += 256;
		if (byt < 0 || byt >= 256)
			return -1;
		else
			return byt;
	}

	public int Get_grbuf(int t_num, int gr_off) {
		if (gr_off < 0 || gr_off >= 16)
			return -1;
		int byt = grbufdata[56 * t_num + 768 + gr_off];
		if (byt < 0)
			byt += 256;
		if (byt < 0 || byt >= 256)
			return -1;
		else
			return byt;
	}

	public int Get_grbuf_mannum(int t_num, int hito_num) {
		if (hito_num < 0 || hito_num >= 20)
			return -1;
		int byt1 = grbufdata[56 * t_num + 784 + hito_num * 2];
		int byt2 = grbufdata[56 * t_num + 785 + hito_num * 2];
		if (byt1 < 0)
			byt1 += 256;
		if (byt2 < 0)
			byt2 += 256;
		if (byt1 < 0 || byt1 >= 256)
			return -1;
		if (byt2 < 192 || byt2 >= 256) {
			return -1;
		} else {
			byt2 -= 192;
			byt1 += byt2 * 256;
			return byt1 / 32;
		}
	}

	public int Set_grbuf(int t_num, int gr_off, int set_data) {
		if (gr_off < 0 || gr_off >= 16)
			return -1;
		if (set_data >= 128)
			set_data -= 256;
		grbufdata[56 * t_num + 768 + gr_off] = (byte) set_data;
		return 0;
	}

	public int Set_grbuf_mannum(int t_num, int hito_num, int set_data) {
		if (hito_num < 0 || hito_num >= 20)
			return -1;
		if (set_data < 49152 || set_data >= 65535)
			set_data = 0;
		int byt2 = set_data / 256;
		int byt1 = set_data - byt2 * 256;
		if (byt1 >= 128)
			byt1 -= 256;
		if (byt2 >= 128)
			byt2 -= 256;
		grbufdata[56 * t_num + 784 + hito_num * 2] = (byte) byt1;
		grbufdata[56 * t_num + 785 + hito_num * 2] = (byte) byt2;
		return 0;
	}

	void ParaInit() {
		GameStep = 0;
		MouseBK_C = 0;
		DragFlag = false;
		MouseBK = false;
		Bottan_L = false;
		Bottan_R = false;
		b_KeyPressd = false;
		b_KyouseiScoll = false;
		b_AutoMove = false;
		b_Escape = false;
		b_MCCFlg = false;
		i_Pouse = 0;
		b_YN_MODE = false;
		i_YN = 0;
		MT_MessageCont = 0;
		MT_ItemGetCont = 0;
		MT_KeikokuCont = 0;
		MT_KaijyoCont = 0;
		MT_MessageNum = 0;
		MT_ItemGetNum = 0;
		MT_KeikokuNum = 0;
		MT_MapOutNum = -1;
		MT_ItemGetStat = 0;
		Control_Team = 0;
		Control_Hito = 0;
		Control_Key = 0;
		Control_Hito_GX = 0;
		Control_Hito_GY = 0;
		Total_Hito = 0;
		ITEM_EVENT = 0;
		for (int i = 0; i < 32; i++) {
			ITEM_PACK[i] = 0;
			ITEM_KAISYUU[i] = 0;
			MAP_ITEM[i] = 0;
		}

		for (int i = 0; i < 20; i++)
			MEMBER_HAGURE[i] = -1;

		ya_hito = 0;
		Paint_count = 0;
		Fail_count = 0;
	}

	public void init() {
		urlCode = getCodeBase();
		System.out.println("Code : " + urlCode.getPath());
		WorkImage = createImage(AppletWidth, AppletHeight);
		WorkGraphics = WorkImage.getGraphics();
		font_s = new Font("\uFF2D\uFF33 \u30B4\u30B7\u30C3\u30AF", 0, 12);
		font = new Font("\uFF2D\uFF33 \u30B4\u30B7\u30C3\u30AF", 1, 16);
		WorkGraphics.setFont(font);
		fontmet = getFontMetrics(font);
		try {
			URL urladd = new URL(urlCode, "res/HPARA.img");
			DataInputStream dis = new DataInputStream(urladd.openStream());
			int ch;
			for (int i = 0; (ch = dis.read()) != -1; i++)
				hparadata[i] = (byte) ch;

			dis.close();
		} catch (IOException e) {
			ErrorCode++;
			e.printStackTrace();
		}
		try {
			URL urladd = new URL(urlCode, "res/Usave0.img");
			DataInputStream dis = new DataInputStream(urladd.openStream());
			int ch;
			for (int i = 0; (ch = dis.read()) != -1; i++)
				grbufdata[i] = (byte) ch;

			dis.close();
		} catch (IOException e) {
			ErrorCode += 10;
			e.printStackTrace();
		}
		int Im = 1;
		for (int i = 0; i < 10; i++) {
			if (Im < 10)
				EffectSound[i] = getAudioClip(urlCode, "res/se0" + Im + ".au");
			else
				EffectSound[i] = getAudioClip(urlCode, "res/se" + Im + ".au");
			Im++;
		}

		BgmSound = getAudioClip(urlCode, "res/fq_bgm15.au");
		SoundState = 1;
		MediaTracker mediatracker = new MediaTracker(this);
		Bottom = getImage(urlCode, "res/botm.gif");
		mediatracker.addImage(Bottom, 0);
		FQ_Title = getImage(urlCode, "res/FQ_title.gif");
		mediatracker.addImage(FQ_Title, 0);
		Kazu = getImage(urlCode, "res/moji8.gif");
		mediatracker.addImage(Kazu, 0);
		FQ_Icon = getImage(urlCode, "res/FQ_icon.gif");
		mediatracker.addImage(FQ_Icon, 0);
		Magic = getImage(urlCode, "res/magic.gif");
		mediatracker.addImage(Magic, 0);
		MiniSt = getImage(urlCode, "res/mini_st.gif");
		mediatracker.addImage(MiniSt, 0);
		MemberSel = getImage(urlCode, "res/smember.gif");
		mediatracker.addImage(MemberSel, 0);
		try {
			mediatracker.waitForAll();
		} catch (InterruptedException e) {
			ErrorCode += 5;
			e.printStackTrace();
			showStatus(" " + e);
		}
		ya = new YaManeger(this);
		CIL_SUU = 0;
		for (int i = 0; i < 40; i++)
			CIL_Table[i] = -1;

		ParaInit();
		DataLoad();
		MapWidth = Map.getWidth(this);
		MapHeight = Map.getHeight(this);
		MXS = 0;
		MYS = 0;
		MXLM = MapWidth / 16;
		MYLM = MapHeight / 16;
		grand = new Grand(this);
		team = new TeamManeger(this, grand, ya);
		team.Team_Init();
		grand.Grand_init();
		status = new Status(this, team);
		CharaLoad();
		team.hito_command();
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		GameStep = 1;
		repaint();
	}

	void DataLoad() {
		MediaTracker mediatracker = new MediaTracker(this);
		Map = getImage(urlCode, "res/map010.gif");
		mediatracker.addImage(Map, 0);
		Map2 = getImage(urlCode, "res/map010s.gif");
		mediatracker.addImage(Map2, 0);
		try {
			mediatracker.waitForAll();
		} catch (InterruptedException e) {
			ErrorCode += 10000;
			e.printStackTrace();
		}
		try {
			URL urladd = new URL(urlCode, "res/map010_.atr");
			DataInputStream dis = new DataInputStream(urladd.openStream());
			int ch;
			int i;
			for (i = 0; (ch = dis.read()) != -1; i++)
				mapatrdata[i] = (byte) ch;

			Mapdata_size = i;
			dis.close();
		} catch (IOException e) {
			ErrorCode += 100;
			e.printStackTrace();
		}
	}

	void CharaLoad() {
		if (CIL_SUU == 0)
			return;
		MediaTracker mediatracker = new MediaTracker(this);
		for (int i = 0; i < CIL_SUU; i++) {
			int Im = CIL_Table[i];
			if (Im < 10) {
				System.out.println("res/C0" + Im + ".gif");
				CharaImage[i] = getImage(urlCode, "res/C0" + Im + ".gif");
			} else {
				System.out.println("res/C" + Im + ".gif");
				CharaImage[i] = getImage(urlCode, "res/C" + Im + ".gif");
			}
			mediatracker.addImage(CharaImage[i], 0);
		}

		try {
			mediatracker.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void GameInitialize() {
		ParaInit();
		team.Team_Init();
		grand.Grand_init();
		team.hito_command();
		GameStep = 2;
	}

	public void start() {
		thread = new Thread(this);
		thread.start();
	}

	public void paint(Graphics g) {
		switch (GameStep) {
		case 1: // '\001'
			GameStep = 2;
			break;

		case 2: // '\002'
			sikou();
			grand.paint(WorkGraphics);
			team.paint(WorkGraphics);
			ya.paint(WorkGraphics);
			status.paint(WorkGraphics);
			g.drawImage(WorkImage, 0, 0, this);
			break;

		case 3: // '\003'
			grand.paint(WorkGraphics);
			team.paint(WorkGraphics);
			ya.paint(WorkGraphics);
			status.paint(WorkGraphics);
			g.drawImage(WorkImage, 0, 0, this);
			break;

		case 8: // '\b'
			grand.paint(WorkGraphics);
			team.paint(WorkGraphics);
			ya.paint(WorkGraphics);
			status.paint(WorkGraphics);
			g.drawImage(WorkImage, 0, 0, this);
			break;

		case 9: // '\t'
			GameInitialize();
			break;
		}
	}

	public boolean item_cls(int item) {
		if (item <= 0 || item > ITEMMAX)
			return false;
		for (int i = 0; i < 32; i++)
			if (ITEM_PACK[i] == item) {
				ITEM_PACK[i] = 0;
				return true;
			}

		return false;
	}

	public boolean item_set(int item) {
		if (item <= 0 || item > ITEMMAX)
			return false;
		for (int i = 0; i < 32; i++)
			if (ITEM_PACK[i] == 0) {
				ITEM_PACK[i] = item;
				return true;
			}

		return false;
	}

	public boolean item_kaisyuu(int item) {
		if (item <= 0 || item > ITEMMAX)
			return false;
		for (int i = 0; i < 32; i++)
			if (ITEM_KAISYUU[i] == 0) {
				ITEM_KAISYUU[i] = item;
				return true;
			}

		return false;
	}

	void eventchk() {
		if (MT_KaijyoCont != 0) {
			MT_KaijyoCont--;
			if (MT_KaijyoCont == 0)
				team.Team_MapPointKaijyo(MT_KaijyoTeam);
		}
		for (int i = 0; i < 8; i++) {
			int item = MAP_ITEM[i * 4 + 2];
			if (team.hito[Control_Hito].X == MAP_ITEM[i * 4 + 0]
					&& team.hito[Control_Hito].Y == MAP_ITEM[i * 4 + 1]
					&& item != 0 && item_kaisyuu(item))
				MAP_ITEM[i * 4 + 2] = 0;
		}

		if (ITEM_EVENT > 0) {
			if (ITEM_EVENT < ITEMMAX) {
				MT_ItemGetStat = 0;
				MT_ItemGetCont = 3;
				MT_ItemGetNum = ITEM_EVENT;
			}
			ITEM_EVENT = 0;
			return;
		}
		if ((team.TEKI[Control_Team] & 0x3c) == 0) {
			for (int i = 0; i < 32; i++)
				if (ITEM_KAISYUU[i] > 0) {
					if (ITEM_KAISYUU[i] < ITEMMAX) {
						MT_ItemGetStat = 0;
						MT_ItemGetCont = 3;
						MT_ItemGetNum = ITEM_KAISYUU[i];
					}
					ITEM_KAISYUU[i] = 0;
					return;
				}

		}
	}

	void sikou() {
		if (i_Pouse != 0)
			return;
		Sys_TurnCount++;
		Sys_Count1w++;
		Sys_Count2w++;
		if (Sys_Count1w > 8) {
			Sys_Count1w = 0;
			Sys_Count1++;
			if (MT_MessageCont > 1)
				MT_MessageCont--;
			if (MT_ItemGetCont > 1)
				MT_ItemGetCont--;
			if (MT_KeikokuCont > 0)
				MT_KeikokuCont--;
		}
		if (Sys_Count2w > 4) {
			Sys_Count2w = 0;
			Sys_Count2++;
		}
		if (MT_ItemGetCont != 0 || MT_MessageCont != 0) {
			return;
		} else {
			eventchk();
			ya.update();
			team.update();
			grand.update();
			return;
		}
	}

	public void run() {
		requestFocus();
		while (thread != null) {
			repaint();
			try {
				Thread.sleep(30L);
			} catch (InterruptedException e) {
				showStatus(" " + e);
			}
		}
	}

	public void stop() {
		thread = null;
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void gameover() {
		GameStep = 1;
	}

	public void actionPerformed(ActionEvent evt) {
		GameInitialize();
		requestFocus();
	}

	boolean counter_ret() {
		if (MT_ItemGetCont != 0) {
			if (MT_ItemGetStat == 0) {
				MT_ItemGetStat = 2;
				MT_ItemGetCont = 3;
			}
			if (MT_ItemGetStat > 2)
				MT_ItemGetCont = 0;
			return true;
		}
		if (MT_MessageCont != 0) {
			MT_MessageCont = 0;
			if (MT_MapOutNum >= 0)
				GameStep = 9;
			return true;
		}
		if (MT_KeikokuCont > 0 && MT_KeikokuCont < 3) {
			MT_KeikokuCont = 0;
			return true;
		} else {
			return false;
		}
	}

	public void mousePressed(MouseEvent evt) {
		Bottan_L = true;
		Bottan_R = true;
		MouseX = evt.getX();
		MouseY = evt.getY();
		int pos = 0;
		if (b_YN_MODE)
			i_YN = -1;
		if (counter_ret())
			return;
		if (MouseY >= status.ic_y && MouseY < status.ic_y + 32
				&& MouseY >= status.ic_y && MouseY < status.ic_y + 32) {
			if (MouseX >= status.ic_tuujyo && MouseX < status.ic_tuujyo + 32) {
				CommandCode = 0;
				team.hito_command();
			}
			if (MouseX >= status.ic_kouige && MouseX < status.ic_kouige + 32) {
				CommandCode = 1;
				team.hito_command();
			}
			if (MouseX >= status.ic_nige && MouseX < status.ic_nige + 32) {
				CommandCode = 2;
				team.hito_command();
			}
			if (MouseX >= status.ic_teisi && MouseX < status.ic_teisi + 32) {
				CommandCode = 3;
				team.hito_command();
			}
			if (MouseX >= status.ic_pouse && MouseX < status.ic_pouse + 32)
				if (i_Pouse != 0)
					i_Pouse = 0;
				else
					i_Pouse = 1;
		}
		if (MouseY > 424) {
			pos = MouseX / 32;
			if (pos < 0 || pos >= 20)
				pos = 0;
			team.hito_change(pos);
			grand.Hito_screen(team.hito[Control_Hito].X,
					team.hito[Control_Hito].Y);
			i_Pouse = 2;
		}
		if (MouseY >= 40 && MouseY < 424) {
			if (b_MCCFlg && !DragFlag
					&& team.hito_gadd_test(MouseX, MouseY) >= 0)
				return;
			DragFlag = true;
			if (i_Pouse < 3)
				i_Pouse = 0;
		}
	}

	public void mouseReleased(MouseEvent evt) {
		MouseBK = false;
		Bottan_L = false;
		Bottan_R = false;
		DragFlag = false;
		MouseX = evt.getX();
		MouseY = evt.getY();
	}

	public void mouseClicked(MouseEvent evt) {
		b_KyouseiScoll = false;
		MouseX = evt.getX();
		MouseY = evt.getY();
	}

	public void mouseEntered(MouseEvent mouseevent) {
	}

	public void mouseExited(MouseEvent mouseevent) {
	}

	public void mouseDragged(MouseEvent evt) {
		if (DragFlag) {
			MouseX = evt.getX();
			MouseY = evt.getY();
		}
	}

	public void mouseMoved(MouseEvent evt) {
		MouseX = evt.getX();
		MouseY = evt.getY();
	}

	public void keyPressed(KeyEvent evt) {
		switch (evt.getKeyCode()) {
		default:
			break;

		case 10: // '\n'
			Bottan_L = true;
			Bottan_R = true;
			if (b_YN_MODE)
				i_YN = 1;
			counter_ret();
			break;

		case 83: // 'S'
			b_KyouseiScoll = true;
			grand.MapScroll(2);
			break;

		case 87: // 'W'
			b_KyouseiScoll = true;
			grand.MapScroll(0);
			break;

		case 68: // 'D'
			b_KyouseiScoll = true;
			grand.MapScroll(3);
			break;

		case 65: // 'A'
			b_KyouseiScoll = true;
			grand.MapScroll(1);
			break;

		case 90: // 'Z'
			Control_Key = 6;
			b_AutoMove = true;
			break;

		case 88: // 'X'
			b_Escape = true;
			break;

		case 76: // 'L'
			if (SoundState == 1) {
				BgmSound.loop();
				SoundState = 2;
			}
			break;

		case 75: // 'K'
			if (SoundState == 2) {
				BgmSound.stop();
				SoundState = 1;
			}
			break;

		case 40: // '('
			team.hito_change(0);
			grand.Hito_screen(team.hito[Control_Hito].X,
					team.hito[Control_Hito].Y);
			i_Pouse = 2;
			break;

		case 38: // '&'
			team.hito_change(0);
			grand.Hito_screen(team.hito[Control_Hito].X,
					team.hito[Control_Hito].Y);
			i_Pouse = 2;
			break;

		case 39: // '\''
			team.hito_change_pn(1);
			grand.Hito_screen(team.hito[Control_Hito].X,
					team.hito[Control_Hito].Y);
			i_Pouse = 2;
			break;

		case 37: // '%'
			team.hito_change_pn(-1);
			grand.Hito_screen(team.hito[Control_Hito].X,
					team.hito[Control_Hito].Y);
			i_Pouse = 2;
			break;

		case 98: // 'b'
			b_KyouseiScoll = false;
			Control_Key = 2;
			b_KeyPressd = true;
			if (i_Pouse < 3)
				i_Pouse = 0;
			break;

		case 104: // 'h'
			b_KyouseiScoll = false;
			Control_Key = 0;
			b_KeyPressd = true;
			if (i_Pouse < 3)
				i_Pouse = 0;
			break;

		case 102: // 'f'
			b_KyouseiScoll = false;
			Control_Key = 3;
			b_KeyPressd = true;
			if (i_Pouse < 3)
				i_Pouse = 0;
			break;

		case 100: // 'd'
			b_KyouseiScoll = false;
			Control_Key = 1;
			b_KeyPressd = true;
			if (i_Pouse < 3)
				i_Pouse = 0;
			break;

		case 112: // 'p'
			DispMark++;
			if (DispMark > 2)
				DispMark = 0;
			break;

		case 113: // 'q'
			DispLine_1++;
			if (DispLine_1 > 3)
				DispLine_1 = 0;
			break;

		case 114: // 'r'
			DispLine_3++;
			if (DispLine_3 > 3)
				DispLine_3 = 0;
			break;

		case 115: // 's'
			if (team.FORM[Control_Team] > 20) {
				team.FORM[Control_Team] -= 100;
				break;
			}
			if (team.FORM[Control_Team] < 20)
				team.FORM[Control_Team] += 100;
			break;

		case 116: // 't'
			int fnum = team.FORM[Control_Team];
			if (++fnum > 8)
				fnum = 1;
			team.form_change(Control_Team, fnum);
			if (i_Pouse < 3)
				i_Pouse = 0;
			break;

		case 117: // 'u'
			b_DrawLife = !b_DrawLife;
			break;

		case 118: // 'v'
			b_DrawFatig = !b_DrawFatig;
			break;

		case 119: // 'w'
			if (i_Pouse != 0)
				break;
			b_MCCFlg = !b_MCCFlg;
			MT_KeikokuCont = 8;
			if (b_MCCFlg)
				MT_KeikokuNum = 14;
			else
				MT_KeikokuNum = 15;
			break;

		case 120: // 'x'
			if (i_Pouse == 0) {
				item_hito = -1;
				i_Pouse = 3;
				break;
			}
			if (i_Pouse == 3)
				i_Pouse = 0;
			break;

		case 123: // '{'
			if (i_Pouse == 0) {
				i_Pouse = 4;
				break;
			}
			if (i_Pouse == 4)
				i_Pouse = 0;
			break;

		case 80: // 'P'
			team.team_change(1);
			grand.Hito_screen(team.hito[Control_Hito].X,
					team.hito[Control_Hito].Y);
			break;

		case 89: // 'Y'
			if (b_YN_MODE)
				i_YN = 1;
			break;

		case 78: // 'N'
			if (b_YN_MODE)
				i_YN = -1;
			break;

		case 32: // ' '
			Control_Key = 5;
			b_KeyPressd = true;
			break;
		}
	}

	public void keyReleased(KeyEvent evt) {
		Control_Key = -1;
		b_KeyPressd = false;
		if (b_AutoMove)
			team.hito[Control_Hito].FLG2 &= 0xffffff7f;
		b_AutoMove = false;
		b_Escape = false;
		MouseBK = false;
		Bottan_L = false;
		Bottan_R = false;
	}

	public void keyTyped(KeyEvent keyevent) {
	}

	private static final long serialVersionUID = 1L;
	int AppletWidth;
	int AppletHeight;
	Thread thread;
	int ITEMMAX;
	Image WorkImage;
	Graphics WorkGraphics;
	int GameSX;
	int GameSY;
	int GameWidth;
	int GameHeight;
	Image FQ_Title;
	Image FQ_Icon;
	Image Bottom;
	Image Kazu;
	Image Magic;
	Image MiniSt;
	Image MemberSel;
	Image Map;
	Image Map2;
	int MapWidth;
	int MapHeight;
	Image CharaImage[];
	Image DCharaImage[];
	int CIL_SUU;
	int CIL_Table[];
	AudioClip EffectSound[];
	AudioClip BgmSound;
	int SoundState;
	int MouseX;
	int MouseY;
	int DispMapX;
	int DispMapY;
	int Map_SX;
	int Map_SY;
	int Map_Mouse_X;
	int Map_Mouse_Y;
	int MXS;
	int MYS;
	int MXLM;
	int MYLM;
	int DistanceX;
	int DistanceY;
	int ya_hito;
	int item_hito;
	int CommandCode;
	int DispLine_1;
	int DispLine_3;
	int DispMark;
	boolean b_DrawLife;
	boolean b_DrawFatig;
	boolean b_MCCFlg;
	int GameStep;
	int MouseBK_C;
	boolean DragFlag;
	boolean MouseBK;
	boolean Bottan_L;
	boolean Bottan_R;
	boolean b_KeyPressd;
	boolean b_KyouseiScoll;
	boolean b_AutoMove;
	boolean b_Escape;
	int i_Pouse;
	boolean b_YN_MODE;
	int i_YN;
	int MT_MessageCont;
	int MT_ItemGetCont;
	int MT_KeikokuCont;
	int MT_KaijyoCont;
	int MT_MessageNum;
	int MT_ItemGetNum;
	int MT_KeikokuNum;
	int MT_MapOutNum;
	int MT_KaijyoTeam;
	int MT_ItemGetStat;
	int Control_Team;
	int Control_Hito;
	int Control_Key;
	int Control_Hito_GX;
	int Control_Hito_GY;
	int Total_Hito;
	int ITEM_EVENT;
	int ITEM_PACK[];
	int ITEM_KAISYUU[];
	int MAP_ITEM[];
	int MEMBER_HAGURE[];
	int Paint_count;
	int Fail_count;
	Grand grand;
	Suuji suuji;
	Status status;
	TeamManeger team;
	YaManeger ya;
	boolean b_DrawTestAtr;
	int DrawTestAtrState;
	int ErrorCode;
	Font font;
	Font font_s;
	FontMetrics fontmet;
	URL urlCode;
	URL urlDoc;
	Date now;
	long nowtime;
	int Sys_TurnCount;
	int Sys_Count1;
	int Sys_Count1w;
	int Sys_Count2;
	int Sys_Count2w;
	char HMBUF[][] = {
			{ '\0', '"', '\202', '\002', '\003', '\003', '\0', '\n' },
			{ '\001', '"', '\b', '\200', '\003', '\003', '\0', '\n' },
			{ '\002', '"', '\n', '\0', '\003', '\003', '\0', '\005' },
			{ '\003', '"', '\210', '\0', '\003', '\003', '\0', '\0' },
			{ '\004', '"', '\b', '\0', '\003', '\003', '\0', '\0' },
			{ '\005', '"', '\0', '\004', '\003', '\003', '\0', '\0' },
			{ '\006', '"', '@', '"', '\003', '\003', '\0', '\n' },
			{ '\007', '"', '@', '\002', '\003', '\003', '\0', '\n' },
			{ '\b', '"', '\0', '\002', '\003', '\003', '\0', '\n' },
			{ '\t', '"', '\0', '\0', '\003', '\003', '\0', '\n' },
			{ '\n', '"', '\200', '\n', '\003', '\003', '\0', '\n' },
			{ '\013', '"', '\n', '\0', '\003', '\003', '\0', '\005' },
			{ '\f', '"', '(', '*', '\003', '\003', '\0', '\005' },
			{ '\r', '"', '\b', '\0', '\003', '\003', '\0', '\0' },
			{ '\016', '"', '\b', '"', '\003', '\003', '\0', '\n' },
			{ '\017', '"', '@', '\002', '\003', '\003', '\0', '\n' },
			{ '\020', '"', '\002', '\0', '\003', '\003', '\0', '\n' },
			{ '\021', '"', '@', '\0', '\003', '\003', '\0', '\017' },
			{ '\022', '"', '\001', '\0', '\003', '\003', '\0', '\n' },
			{ '\023', '"', '\b', '\200', '\003', '\003', '\0', '\005' },
			{ '\024', '"', '\0', 'H', '\003', '\003', '\0', '\0' },
			{ '\025', '"', '\0', 'H', '\003', '\003', '\0', '\005' },
			{ '\026', '"', '\0', '\0', '\003', '\003', '\0', '\017' },
			{ '\027', '"', '\0', '\0', '\003', '\003', '\0', '\017' },
			{ '\030', '"', '\0', '\0', '\003', '\003', '\0', '\017' },
			{ '\031', '"', '\0', '\0', '\003', '\003', '\0', '\017' },
			{ '\032', '"', '\200', '\0', '\003', '\003', '\0', '\n' },
			{ '\033', '"', '\0', '\0', '\003', '\003', '\0', '\n' },
			{ '\034', '"', '\021', '\0', '\003', '\003', '\0', '\0' },
			{ '\035', '"', '\0', '@', '\003', '\023', '\0', '\005' },
			{ '\036', '"', '\0', '@', '\003', '\023', '\0', '\005' },
			{ '\037', '"', '\001', '@', '\003', '\023', '\0', '\0' },
			{ ' ', '"', '\0', '@', '\003', '\023', '\0', '\n' },
			{ '!', '"', '\001', '@', '\003', '\023', '\0', '\005' },
			{ '"', '"', '\001', 'H', '\003', '\023', '\0', '\0' },
			{ '#', '"', '\0', '@', '\003', '\023', '\0', '\n' },
			{ '$', '"', '\0', '@', '\003', '\023', '\0', '\005' },
			{ '%', '"', '\0', '\004', '\003', '\003', '\0', '\n' },
			{ '&', '"', '\0', '@', '\003', '\023', '\0', '\005' },
			{ '\'', '"', '\021', '@', '\003', '\023', '\0', '\017' },
			{ '(', '"', '\0', '@', '\003', '3', '\0', '\n' },
			{ ')', '"', '\021', '\0', '\003', '\003', '\0', '\n' },
			{ '*', '"', '\0', '@', '\003', '3', '\0', '\005' },
			{ '+', '"', '@', '\0', '\003', '\003', '\0', '\017' },
			{ ',', '"', ' ', '\0', '\003', '\003', '\0', '\n' },
			{ '-', '"', '\b', '\0', '\003', '\003', '\0', '\005' },
			{ '.', '"', '\0', '\0', '\003', '\003', '\0', '\n' },
			{ '/', '"', '\0', '\004', '\003', '\003', '\0', '\005' },
			{ '0', '"', '\0', '\004', '\003', '\003', '\0', '\n' },
			{ '1', '"', '\001', '\0', '\003', '\003', '\0', '\005' },
			{ '2', '"', 'A', '\0', '\003', '\003', '\0', '\n' },
			{ '3', '"', '\021', '\0', '\003', '\023', '\0', '\n' },
			{ '4', '"', '\001', '@', '\003', '3', '\0', '\005' },
			{ '5', '"', '\200', '\0', '\003', '\003', '\0', '\n' },
			{ '6', '"', '\200', '\0', '\003', '\003', '\0', '\005' },
			{ '7', '"', '\200', '\0', '\003', '\003', '\0', '\005' },
			{ '8', '"', '\200', '\0', '\003', '\003', '\0', '\005' },
			{ '9', '"', '\0', '@', '\003', '\023', '\0', '\0' },
			{ ':', '"', '\0', '\0', '\003', '\003', '\0', '\n' },
			{ ';', '"', '\0', '\200', '\003', '\003', '\0', '\005' },
			{ '<', '"', '\0', '\0', '\003', '\003', '\0', '\n' },
			{ '=', '"', '\0', '\0', '\003', '\003', '\0', '\0' },
			{ '>', '"', '\0', '\0', '\003', '\003', '\0', '\005' },
			{ '?', '"', '\0', '\0', '\003', '\003', '\0', '\n' },
			{ '\0', 'f', '\0', '\200', '\003', '\003', '\0', '\0' },
			{ '\001', 'f', '\0', '\200', '\003', '\003', '\0', '\0' },
			{ '\002', 'f', '\0', '\200', '\003', '\003', '\0', '\0' },
			{ '\003', 'f', '\0', '\200', '\003', '\003', '\0', '\0' },
			{ '\004', 'f', '\0', '\0', '\003', '\003', '\0', '\0' },
			{ '\021', '"', '@', '\0', '\003', '\003', '\0', '\017' } };
	int HparaNum[] = { 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458,
			459, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471,
			472, 473, 474, 475, 476, 477, 478, 479, 480, 481, 482, 483, 484,
			485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 497,
			498, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510,
			511, 449, 450, 451, 450, 464, 465 };
	int Mapdata_size;
	byte mapatrdata[];
	byte hparadata[];
	byte grbufdata[];
}
