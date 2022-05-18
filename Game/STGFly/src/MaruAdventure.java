// Decompiled by:       Fernflower v0.6
// Date:                05.11.2010 13:50:31
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

public class MaruAdventure extends Applet implements MessageBoxValues {

   public static final String VESION = "Version 1.12";
   public static final int DISP_WIDTH = 640;
   public static final int DISP_HEIGHT = 480;
   public static final int DISP_ONE_STEP = 10;
   public static final int POS_WIDTH = 64;
   public static final int POS_HEIGHT = 48;
   public static final int ENEMY_MAX = 64;
   public static final int ENEMY_AFTERDEAD_MAX = 128;
   public static final int ENEMY_AFTERDEAD_COUNT = 30;
   public static final int ITEM_MAX = 3;
   public static final int MODE_OPENING = 0;
   public static final int MODE_GAME = 1;
   public static final int MODE_OVER = 2;
   public static final int MODE_RANKING = 3;
   public static final int HIT_RANGE = 3;
   public static final int ZOMBI_COUNT = 100;
   public static final int POWER_MAX = 4;
   public static final int RANKING_MAX = 10;
   public static final int BOM_ACTIVE = 45;
   public static final int STAGE_DISP_NUM = 24;
   public static final int STAGE_FRAME_MAX = 1536;
   Image imgOpening;
   Image imgBackground;
   Image imGameOver;
   Image imgImage;
   Image imgLife;
   Image imgBom;
   Image imgItem;
   Image imgMaruBody;
   Image imgMaruZombi;
   Image imgMaruOver;
   Image[] imgMaruBeam = new Image[4];
   Image imgCatBody;
   Image imgCatShadow;
   Image imgCatZombi;
   Image imgCatOver;
   Image[] imgCatBeam = new Image[4];
   Image imgChiffonBody;
   Image imgChiffonZombi;
   Image imgChiffonOver;
   Image[] imgChiffonBeam = new Image[4];
   Image imgEnemy01Body;
   Image imgEnemy01Beam;
   Image imgEnemy02Body;
   Image imgEnemy02Beam;
   Image imgEnemy03Body;
   Image imgEnemy03Beam;
   Image imgEnemy04Body;
   Image imgEnemy04Beam;
   Image imgEnemy05Body;
   Image imgEnemy06Body;
   Image imgEnemy07Body;
   Image imgEnemy08Body;
   Image imgKoyukiBody;
   Image imgKoyukiBeam;
   Image imgYunBody;
   Image imgYunBeam;
   Image imgEllBody;
   Image imgEllBeam;
   Image imgCamusBody;
   Image imgCamusBeam;
   Image imgMaliBody;
   Image imgMaliBeam;
   Graphics grpGraphics;
   int gnMode = 0;
   int gnScore = 10000;
   int gnStage = 0;
   int gnBGCounter = 0;
   int gnStageCounter = 0;
   int gnFrameCounter = 0;
   int gnOneupflg = 0;
   int gnPhenixCheck = 0;
   int gnOpeningMousePosX = 0;
   int gnOpeningMousePosY = 0;
   int gnBomActive = 0;
   int gnRegistNumber = 0;
   int gnBossSpeak = 0;
   boolean gbBGflg = false;
   boolean gbBossflg = false;
   boolean gbDrawOk = false;
   boolean gbOvering = false;
   boolean gbPhenix = false;
   boolean gbRankIn = false;
   boolean gbAllClear = false;
   String gsRegistName = "";
   MaruAdventure.MainThread thrdMain = null;
   Character charaHiroin = new Character();
   Character[] charaEnemy = new Character[64];
   Character[] charaItem = new Character[64];
   MaruAdventure.KeyPress keyOperation = new MaruAdventure.KeyPress();
   Ranking[] rnkMaru = new Ranking[10];
   Ranking[] rnkCat = new Ranking[10];
   Ranking[] rnkChiffon = new Ranking[10];
   MaruAdventure.EnemyAfterDead[] eadMain = new MaruAdventure.EnemyAfterDead[128];
   TextField txtfldName = new TextField("");
   Button btnRegist = new Button("REGIST");
   Random rnd = new Random();


   public void init() {
      this.initImage();
      this.imgImage = this.createImage(640, 480);
      this.grpGraphics = this.imgImage.getGraphics();
      this.btnRegist.setActionCommand("regist");
      this.btnRegist.addActionListener(new MaruAdventure.PushButton());
      this.addKeyListener(this.keyOperation);
      this.addMouseListener(new MaruAdventure.MousePress());
      this.addMouseMotionListener(new MaruAdventure.MouseMotion());

      for(int i = 0; i < 128; ++i) {
         this.eadMain[i] = new MaruAdventure.EnemyAfterDead();
      }

      this.ReadDataFile();
     /* Arrays.sort(this.rnkMaru);
      Arrays.sort(this.rnkCat);
      Arrays.sort(this.rnkChiffon);*/
      this.initOpening();
   }

   public void initOpening() {
      this.remove(this.btnRegist);
      this.remove(this.txtfldName);
      this.gbRankIn = false;
      this.initImageLoad(this.grpGraphics);
   }

   public void initGame() {
      this.remove(this.btnRegist);
      this.remove(this.txtfldName);
      this.gbBGflg = false;
      this.gbOvering = false;
      this.gnBGCounter = 0;
      this.gnStageCounter = 0;
      this.gnFrameCounter = 0;
      this.gnScore = 0;
      this.gnOneupflg = 0;
      this.gbBossflg = false;
      this.gbPhenix = false;
      this.gnPhenixCheck = 0;
      this.gbAllClear = false;
      this.charaHiroin.initHiroin(this);
      this.gnStage = 0;
      this.gsRegistName = "";
      this.gnRegistNumber = 0;

      int i;
      for(i = 0; i < 64; ++i) {
         this.charaEnemy[i] = new Character();
         this.charaEnemy[i].initHiroin(this);
         this.charaEnemy[i].nLife = 0;
      }

      for(i = 0; i < 3; ++i) {
         this.charaItem[i] = new Character();
         this.charaEnemy[i].initHiroin(this);
         this.charaEnemy[i].nLife = 0;
      }

   }

   public void initRanking() {
      this.remove(this.btnRegist);
      this.remove(this.txtfldName);
      this.ReadDataFile();
      Arrays.sort(this.rnkMaru);
      Arrays.sort(this.rnkCat);
      Arrays.sort(this.rnkChiffon);
   }

   public void initOver() {
      this.ReadDataFile();
      Arrays.sort(this.rnkMaru);
      Arrays.sort(this.rnkCat);
      Arrays.sort(this.rnkChiffon);
      switch(this.charaHiroin.nHiroinType) {
      case 0:
         if(this.gnScore < this.rnkMaru[9].score) {
            this.gbRankIn = false;
            this.remove(this.btnRegist);
            this.remove(this.txtfldName);
         } else {
            this.gbRankIn = true;
            this.add(this.btnRegist);
            this.add(this.txtfldName);
         }
         break;
      case 1:
         if(this.gnScore < this.rnkCat[9].score) {
            this.gbRankIn = false;
            this.remove(this.btnRegist);
            this.remove(this.txtfldName);
         } else {
            this.gbRankIn = true;
            this.add(this.btnRegist);
            this.add(this.txtfldName);
         }
         break;
      case 2:
         if(this.gnScore < this.rnkChiffon[9].score) {
            this.gbRankIn = false;
            this.remove(this.btnRegist);
            this.remove(this.txtfldName);
         } else {
            this.gbRankIn = true;
            this.add(this.btnRegist);
            this.add(this.txtfldName);
         }
      }

   }

   public void initImage() {
      this.imgOpening = this.getImage(this.getDocumentBase(), "title.gif");
      this.imgBackground = this.getImage(this.getDocumentBase(), "backGround.gif");
      this.imgLife = this.getImage(this.getDocumentBase(), "life.gif");
      this.imgBom = this.getImage(this.getDocumentBase(), "bom.gif");
      this.imgItem = this.getImage(this.getDocumentBase(), "item.gif");
      this.imgMaruBody = this.getImage(this.getDocumentBase(), "charaMaruBody.gif");
      this.imgMaruZombi = this.getImage(this.getDocumentBase(), "charaMaruZombi.gif");
      this.imgMaruOver = this.getImage(this.getDocumentBase(), "charaMaruOver.gif");
      this.imgMaruBeam[0] = this.getImage(this.getDocumentBase(), "charaMaruBeam0.gif");
      this.imgMaruBeam[1] = this.getImage(this.getDocumentBase(), "charaMaruBeam1.gif");
      this.imgMaruBeam[2] = this.getImage(this.getDocumentBase(), "charaMaruBeam2.gif");
      this.imgMaruBeam[3] = this.getImage(this.getDocumentBase(), "charaMaruBeam3.gif");
      this.imgCatBody = this.getImage(this.getDocumentBase(), "charaCatBody.gif");
      this.imgCatShadow = this.getImage(this.getDocumentBase(), "charaCatShadow.gif");
      this.imgCatZombi = this.getImage(this.getDocumentBase(), "charaCatZombi.gif");
      this.imgCatOver = this.getImage(this.getDocumentBase(), "charaCatOver.gif");
      this.imgCatBeam[0] = this.getImage(this.getDocumentBase(), "charaCatBeam.gif");
      this.imgCatBeam[1] = this.getImage(this.getDocumentBase(), "charaCatBeam.gif");
      this.imgCatBeam[2] = this.getImage(this.getDocumentBase(), "charaCatBeam.gif");
      this.imgCatBeam[3] = this.getImage(this.getDocumentBase(), "charaCatBeam.gif");
      this.imgChiffonBody = this.getImage(this.getDocumentBase(), "charaChiffonBody.gif");
      this.imgChiffonZombi = this.getImage(this.getDocumentBase(), "charaChiffonZombi.gif");
      this.imgChiffonOver = this.getImage(this.getDocumentBase(), "charaChiffonOver.gif");
      this.imgChiffonBeam[0] = this.getImage(this.getDocumentBase(), "charaChiffonBeam0.gif");
      this.imgChiffonBeam[1] = this.getImage(this.getDocumentBase(), "charaChiffonBeam0.gif");
      this.imgChiffonBeam[2] = this.getImage(this.getDocumentBase(), "charaChiffonBeam1.gif");
      this.imgChiffonBeam[3] = this.getImage(this.getDocumentBase(), "charaChiffonBeam1.gif");
      this.imgEnemy01Body = this.getImage(this.getDocumentBase(), "charaEnemy01Body.gif");
      this.imgEnemy01Beam = this.getImage(this.getDocumentBase(), "charaEnemy01Beam.gif");
      this.imgEnemy02Body = this.getImage(this.getDocumentBase(), "charaEnemy02Body.gif");
      this.imgEnemy02Beam = this.getImage(this.getDocumentBase(), "charaEnemy02Beam.gif");
      this.imgEnemy03Body = this.getImage(this.getDocumentBase(), "charaEnemy03Body.gif");
      this.imgEnemy03Beam = this.getImage(this.getDocumentBase(), "charaEnemy03Beam.gif");
      this.imgEnemy04Body = this.getImage(this.getDocumentBase(), "charaEnemy04Body.gif");
      this.imgEnemy04Beam = this.getImage(this.getDocumentBase(), "charaEnemy04Beam.gif");
      this.imgEnemy05Body = this.getImage(this.getDocumentBase(), "charaEnemy05Body.gif");
      this.imgEnemy06Body = this.getImage(this.getDocumentBase(), "charaEnemy06Body.gif");
      this.imgEnemy07Body = this.getImage(this.getDocumentBase(), "charaEnemy07Body.gif");
      this.imgEnemy08Body = this.getImage(this.getDocumentBase(), "charaEnemy08Body.gif");
      this.imgKoyukiBody = this.getImage(this.getDocumentBase(), "charaKoyukiBody.gif");
      this.imgKoyukiBeam = this.getImage(this.getDocumentBase(), "charaKoyukiBeam.gif");
      this.imgYunBody = this.getImage(this.getDocumentBase(), "charaYunBody.gif");
      this.imgYunBeam = this.getImage(this.getDocumentBase(), "charaYunBeam.gif");
      this.imgEllBody = this.getImage(this.getDocumentBase(), "charaEllBody.gif");
      this.imgEllBeam = this.getImage(this.getDocumentBase(), "charaEllBeam.gif");
      this.imgCamusBody = this.getImage(this.getDocumentBase(), "charaCamusBody.gif");
      this.imgCamusBeam = this.getImage(this.getDocumentBase(), "charaCamusBeam.gif");
      this.imgMaliBody = this.getImage(this.getDocumentBase(), "charaMaliBody.gif");
      this.imgMaliBeam = this.getImage(this.getDocumentBase(), "charaMaliBeam.gif");
   }

   public void initImageLoad(Graphics g) {
      g.drawImage(this.imgOpening, 0, 0, this);
      g.drawImage(this.imgBackground, 0, 0, this);
      g.drawImage(this.imgLife, 0, 0, this);
      g.drawImage(this.imgBom, 0, 0, this);
      g.drawImage(this.imgItem, 0, 0, this);
      g.drawImage(this.imgMaruBody, 0, 0, this);
      g.drawImage(this.imgMaruZombi, 0, 0, this);
      g.drawImage(this.imgMaruOver, 0, 0, this);
      g.drawImage(this.imgMaruBeam[0], 0, 0, this);
      g.drawImage(this.imgMaruBeam[1], 0, 0, this);
      g.drawImage(this.imgMaruBeam[2], 0, 0, this);
      g.drawImage(this.imgMaruBeam[3], 0, 0, this);
      g.drawImage(this.imgCatBody, 0, 0, this);
      g.drawImage(this.imgCatShadow, 0, 0, this);
      g.drawImage(this.imgCatZombi, 0, 0, this);
      g.drawImage(this.imgCatOver, 0, 0, this);
      g.drawImage(this.imgCatBeam[0], 0, 0, this);
      g.drawImage(this.imgCatBeam[1], 0, 0, this);
      g.drawImage(this.imgCatBeam[2], 0, 0, this);
      g.drawImage(this.imgCatBeam[3], 0, 0, this);
      g.drawImage(this.imgChiffonBody, 0, 0, this);
      g.drawImage(this.imgChiffonZombi, 0, 0, this);
      g.drawImage(this.imgChiffonOver, 0, 0, this);
      g.drawImage(this.imgChiffonBeam[0], 0, 0, this);
      g.drawImage(this.imgChiffonBeam[1], 0, 0, this);
      g.drawImage(this.imgChiffonBeam[2], 0, 0, this);
      g.drawImage(this.imgChiffonBeam[3], 0, 0, this);
      g.drawImage(this.imgEnemy01Body, 0, 0, this);
      g.drawImage(this.imgEnemy01Beam, 0, 0, this);
      g.drawImage(this.imgEnemy02Body, 0, 0, this);
      g.drawImage(this.imgEnemy02Beam, 0, 0, this);
      g.drawImage(this.imgEnemy03Body, 0, 0, this);
      g.drawImage(this.imgEnemy03Beam, 0, 0, this);
      g.drawImage(this.imgEnemy04Body, 0, 0, this);
      g.drawImage(this.imgEnemy04Beam, 0, 0, this);
      g.drawImage(this.imgEnemy05Body, 0, 0, this);
      g.drawImage(this.imgEnemy06Body, 0, 0, this);
      g.drawImage(this.imgEnemy07Body, 0, 0, this);
      g.drawImage(this.imgEnemy08Body, 0, 0, this);
      g.drawImage(this.imgKoyukiBody, 0, 0, this);
      g.drawImage(this.imgKoyukiBeam, 0, 0, this);
      g.drawImage(this.imgYunBody, 0, 0, this);
      g.drawImage(this.imgYunBeam, 0, 0, this);
      g.drawImage(this.imgEllBody, 0, 0, this);
      g.drawImage(this.imgEllBeam, 0, 0, this);
      g.drawImage(this.imgCamusBody, 0, 0, this);
      g.drawImage(this.imgCamusBeam, 0, 0, this);
      g.drawImage(this.imgMaliBody, 0, 0, this);
      g.drawImage(this.imgMaliBeam, 0, 0, this);
   }

   public void initCashCancel(String filename) {
      URL wi_url = null;
      URLConnection wi_connect = null;

      try {
         wi_url = new URL(this.getCodeBase() + filename);

         try {
            wi_connect = wi_url.openConnection();
            wi_connect.setUseCaches(false);
         } catch (IOException var5) {
            ;
         }
      } catch (MalformedURLException var6) {
         ;
      }

   }

   public void start() {
      if(this.thrdMain == null) {
         this.thrdMain = new MaruAdventure.MainThread();
         this.thrdMain.start();
      }

   }

   public void stop() {
      this.imgOpening.flush();
      this.imgBackground.flush();
      this.imgLife.flush();
      this.imgBom.flush();
      this.imgItem.flush();
      this.imgMaruBody.flush();
      this.imgMaruZombi.flush();
      this.imgMaruOver.flush();
      this.imgMaruBeam[0].flush();
      this.imgMaruBeam[1].flush();
      this.imgMaruBeam[2].flush();
      this.imgMaruBeam[3].flush();
      this.imgCatBody.flush();
      this.imgCatShadow.flush();
      this.imgCatZombi.flush();
      this.imgCatOver.flush();
      this.imgCatBeam[0].flush();
      this.imgCatBeam[1].flush();
      this.imgCatBeam[2].flush();
      this.imgCatBeam[3].flush();
      this.imgChiffonBody.flush();
      this.imgChiffonZombi.flush();
      this.imgChiffonOver.flush();
      this.imgChiffonBeam[0].flush();
      this.imgChiffonBeam[1].flush();
      this.imgChiffonBeam[2].flush();
      this.imgChiffonBeam[3].flush();
      this.imgEnemy01Body.flush();
      this.imgEnemy01Beam.flush();
      this.imgEnemy02Body.flush();
      this.imgEnemy02Beam.flush();
      this.imgEnemy03Body.flush();
      this.imgEnemy03Beam.flush();
      this.imgEnemy04Body.flush();
      this.imgEnemy04Beam.flush();
      this.imgEnemy05Body.flush();
      this.imgEnemy06Body.flush();
      this.imgEnemy07Body.flush();
      this.imgEnemy08Body.flush();
      this.imgKoyukiBody.flush();
      this.imgKoyukiBeam.flush();
      this.imgYunBody.flush();
      this.imgYunBeam.flush();
      this.imgEllBody.flush();
      this.imgEllBeam.flush();
      this.imgCamusBody.flush();
      this.imgCamusBeam.flush();
      this.imgMaliBody.flush();
      this.imgMaliBeam.flush();
      if(this.thrdMain != null) {
         this.thrdMain.stop();
         this.thrdMain = null;
      }

   }

   public void destroy() {
   }

   public void paint(Graphics g) {
      if(this.gbDrawOk) {
         this.frame();
         g.drawImage(this.imgImage, 0, 0, this);
         this.gbDrawOk = false;
      }

   }

   public void update(Graphics g) {
      this.paint(g);
   }

   public void frame() {
      DecimalFormat df = new DecimalFormat();
      String buf = "";
      switch(this.gnMode) {
      case 0:
         this.drawBackGround(this.grpGraphics);
         this.grpGraphics.drawImage(this.imgOpening, 20, 40, this);
         this.drawText(this.grpGraphics, "SELECT PLAYING CHARACTER", 320, 268, 20, true);
         this.drawText(this.grpGraphics, "MARU", 242, 283, 12, true);
         this.drawText(this.grpGraphics, "CAT", 322, 283, 12, true);
         this.drawText(this.grpGraphics, "CHIFFON", 402, 283, 12, true);
         this.grpGraphics.drawImage(this.imgMaruBody, 205, 285, this);
         this.grpGraphics.drawImage(this.imgCatBody, 285, 285, this);
         this.grpGraphics.drawImage(this.imgChiffonBody, 365, 285, this);
         this.grpGraphics.setColor(new Color(64, 255, 64));
         if(this.gnOpeningMousePosY >= 285 && this.gnOpeningMousePosY <= 355 && this.gnOpeningMousePosX >= 205 && this.gnOpeningMousePosX <= 275) {
            this.grpGraphics.drawRect(205, 285, 70, 70);
            this.grpGraphics.drawRect(206, 286, 68, 68);
         }

         if(this.gnOpeningMousePosY >= 285 && this.gnOpeningMousePosY <= 355 && this.gnOpeningMousePosX >= 285 && this.gnOpeningMousePosX <= 355) {
            this.grpGraphics.drawRect(285, 285, 70, 70);
            this.grpGraphics.drawRect(286, 286, 68, 68);
         }

         if(this.gnOpeningMousePosY >= 285 && this.gnOpeningMousePosY <= 355 && this.gnOpeningMousePosX >= 365 && this.gnOpeningMousePosX <= 435) {
            this.grpGraphics.drawRect(365, 285, 70, 70);
            this.grpGraphics.drawRect(366, 286, 68, 68);
         }

         if(this.gnOpeningMousePosY >= 350 && this.gnOpeningMousePosY <= 370 && this.gnOpeningMousePosX >= 249 && this.gnOpeningMousePosX <= 391) {
            this.drawTextExt(this.grpGraphics, "SCORE RANKING", 320, 380, 20, true, new Color(64, 255, 64), new Color(128, 0, 128));
         } else {
            this.drawText(this.grpGraphics, "SCORE RANKING", 320, 380, 20, true);
         }

         this.drawText(this.grpGraphics, "Version 1.12", 530, 475, 14, false);
         break;
      case 1:
         this.shadowCheck();
         if(!this.gbOvering && !this.gbAllClear && this.gnBossSpeak == 0) {
            this.keyOperation.keyControl();
         }

         if(this.gnBomActive > 0) {
            if(this.gnFrameCounter % 6 < 3) {
               this.grpGraphics.setColor(new Color(255, 222, 0));
               this.grpGraphics.fillRect(0, 0, 640, 480);
            } else {
               this.drawBackGround(this.grpGraphics);
            }
         } else {
            this.drawBackGround(this.grpGraphics);
         }

         if(!this.gbOvering && !this.gbAllClear && this.gnBossSpeak == 0) {
            this.gbOvering = this.hitCheck();
         }

         this.drawLife(this.grpGraphics);
         this.drawBom(this.grpGraphics);
         this.drawScore(this.grpGraphics);
         this.charaHiroin.actionHiroin(this, this.grpGraphics);
         this.itemBirth();
         this.itemDisp(this.grpGraphics);
         this.enemyBirth();
         this.enemyDisp(this.grpGraphics);
         this.zonbiCheck();
         this.oneupCheck();
         this.overCheck();
         this.bomCheck();
         this.bossSpeak();
         ++this.gnFrameCounter;
         if(!this.gbBossflg && !this.gbAllClear) {
            ++this.gnStageCounter;
            if(this.gbBGflg) {
               ++this.gnBGCounter;
               this.gbBGflg = false;
            } else {
               this.gbBGflg = true;
            }

            if(this.gnStageCounter == 1536) {
               this.bossBirth();
               this.gbBossflg = true;
               this.gnBossSpeak = 2;
            }
         }

         if(this.gbAllClear) {
            this.drawText(this.grpGraphics, "CONGRATULATIONS!", 320, 100, 30, true);
            this.drawText(this.grpGraphics, "YOU CLEARED ALL STAGE!", 320, 140, 20, true);
            this.drawText(this.grpGraphics, "PLEASE CLICK TO RETURN...", 320, 170, 20, true);
         }
         break;
      case 2:
         this.drawBackGround(this.grpGraphics);
         this.drawText(this.grpGraphics, "GAME OVER", 320, 50, 30, true);
         if(this.gbRankIn) {
            this.drawText(this.grpGraphics, "CONGRATULATIONS! YOUR SOCE IS RINKING IN!", 320, 90, 20, true);
            this.drawText(this.grpGraphics, "PLEASE PUSH \'REGIST\' AFTER INPUT YOUR NAME...", 320, 120, 20, true);
            this.drawText(this.grpGraphics, "YOUR NAME", 320, 190, 18, true);
            this.txtfldName.setSize(200, 20);
            this.txtfldName.setLocation(220, 200);
            this.btnRegist.setSize(100, 20);
            this.btnRegist.setLocation(270, 240);
         } else {
            this.drawText(this.grpGraphics, "REGRETTABLY! YOUR SOCRE IS RANKING OUT!", 320, 90, 20, true);
            this.drawText(this.grpGraphics, "PLEASE CLICK TO RETURN...", 320, 120, 20, true);
         }
         break;
      case 3:
         this.drawBackGround(this.grpGraphics);
         this.drawText(this.grpGraphics, "SCORE RANKING", 320, 40, 30, true);
         this.drawText(this.grpGraphics, "MARU", 107, 63, 12, true);
         this.drawText(this.grpGraphics, "CAT", 322, 63, 12, true);
         this.drawText(this.grpGraphics, "CHIFFON", 537, 63, 12, true);
         this.grpGraphics.drawImage(this.imgMaruBody, 70, 65, this);
         this.grpGraphics.drawImage(this.imgCatBody, 285, 65, this);
         this.grpGraphics.drawImage(this.imgChiffonBody, 500, 65, this);

         int i;
         int j;
         for(i = 0; i < 10; ++i) {
            df.applyLocalizedPattern("00");
            buf = df.format((long)(i + 1));
            if(i != 0) {
               for(j = i; j > 0; --j) {
                  if(this.rnkMaru[i].score == this.rnkMaru[j - 1].score) {
                     buf = df.format((long)j);
                  }
               }
            }

            if(this.gbRankIn && this.rnkMaru[i].name.equalsIgnoreCase(this.gsRegistName) && this.gnRegistNumber == i && this.gnScore == this.rnkMaru[i].score && this.charaHiroin.nHiroinType == 0) {
               this.drawTextExt(this.grpGraphics, buf, 20, i * 30 + 160, 20, false, new Color(64, 255, 64), new Color(128, 0, 128));
            } else {
               this.drawText(this.grpGraphics, buf, 20, i * 30 + 160, 20, false);
            }

            df.applyLocalizedPattern("0000000");
            buf = df.format((long)this.rnkMaru[i].score);
            if(this.gbRankIn && this.rnkMaru[i].name.equalsIgnoreCase(this.gsRegistName) && this.gnRegistNumber == i && this.gnScore == this.rnkMaru[i].score && this.charaHiroin.nHiroinType == 0) {
               this.drawTextExt(this.grpGraphics, buf, 50, i * 30 + 160, 20, false, new Color(64, 255, 64), new Color(128, 0, 128));
               if(this.rnkMaru[i].name.length() > 6) {
                  this.drawTextExt(this.grpGraphics, this.rnkMaru[i].name.substring(0, 6), 140, i * 30 + 160, 20, false, new Color(64, 255, 64), new Color(128, 0, 128));
               } else {
                  this.drawTextExt(this.grpGraphics, this.rnkMaru[i].name, 140, i * 30 + 160, 20, false, new Color(64, 255, 64), new Color(128, 0, 128));
               }
            } else {
               this.drawText(this.grpGraphics, buf, 50, i * 30 + 160, 20, false);
               if(this.rnkMaru[i].name.length() > 6) {
                  this.drawText(this.grpGraphics, this.rnkMaru[i].name.substring(0, 6), 140, i * 30 + 160, 20, false);
               } else {
                  this.drawText(this.grpGraphics, this.rnkMaru[i].name, 140, i * 30 + 160, 20, false);
               }
            }
         }

         for(i = 0; i < 10; ++i) {
            df.applyLocalizedPattern("00");
            buf = df.format((long)(i + 1));
            if(i != 0) {
               for(j = i; j > 0; --j) {
                  if(this.rnkCat[i].score == this.rnkCat[j - 1].score) {
                     buf = df.format((long)j);
                  }
               }
            }

            if(this.gbRankIn && this.rnkCat[i].name.equalsIgnoreCase(this.gsRegistName) && this.gnRegistNumber == i && this.gnScore == this.rnkCat[i].score && this.charaHiroin.nHiroinType == 1) {
               this.drawTextExt(this.grpGraphics, buf, 230, i * 30 + 160, 20, false, new Color(64, 255, 64), new Color(128, 0, 128));
            } else {
               this.drawText(this.grpGraphics, buf, 230, i * 30 + 160, 20, false);
            }

            df.applyLocalizedPattern("0000000");
            buf = df.format((long)this.rnkCat[i].score);
            if(this.gbRankIn && this.rnkCat[i].name.equalsIgnoreCase(this.gsRegistName) && this.gnRegistNumber == i && this.gnScore == this.rnkCat[i].score && this.charaHiroin.nHiroinType == 1) {
               this.drawTextExt(this.grpGraphics, buf, 260, i * 30 + 160, 20, false, new Color(64, 255, 64), new Color(128, 0, 128));
               if(this.rnkCat[i].name.length() > 6) {
                  this.drawTextExt(this.grpGraphics, this.rnkCat[i].name.substring(0, 6), 350, i * 30 + 160, 20, false, new Color(64, 255, 64), new Color(128, 0, 128));
               } else {
                  this.drawTextExt(this.grpGraphics, this.rnkCat[i].name, 350, i * 30 + 160, 20, false, new Color(64, 255, 64), new Color(128, 0, 128));
               }
            } else {
               this.drawText(this.grpGraphics, buf, 260, i * 30 + 160, 20, false);
               if(this.rnkCat[i].name.length() > 6) {
                  this.drawText(this.grpGraphics, this.rnkCat[i].name.substring(0, 6), 350, i * 30 + 160, 20, false);
               } else {
                  this.drawText(this.grpGraphics, this.rnkCat[i].name, 350, i * 30 + 160, 20, false);
               }
            }
         }

         for(i = 0; i < 10; ++i) {
            df.applyLocalizedPattern("00");
            buf = df.format((long)(i + 1));
            if(i != 0) {
               for(j = i; j > 0; --j) {
                  if(this.rnkChiffon[i].score == this.rnkChiffon[j - 1].score) {
                     buf = df.format((long)j);
                  }
               }
            }

            if(this.gbRankIn && this.rnkChiffon[i].name.equalsIgnoreCase(this.gsRegistName) && this.gnRegistNumber == i && this.gnScore == this.rnkChiffon[i].score && this.charaHiroin.nHiroinType == 2) {
               this.drawTextExt(this.grpGraphics, buf, 440, i * 30 + 160, 20, false, new Color(64, 255, 64), new Color(128, 0, 128));
            } else {
               this.drawText(this.grpGraphics, buf, 440, i * 30 + 160, 20, false);
            }

            df.applyLocalizedPattern("0000000");
            buf = df.format((long)this.rnkChiffon[i].score);
            if(this.gbRankIn && this.rnkChiffon[i].name.equalsIgnoreCase(this.gsRegistName) && this.gnRegistNumber == i && this.gnScore == this.rnkChiffon[i].score && this.charaHiroin.nHiroinType == 2) {
               this.drawTextExt(this.grpGraphics, buf, 470, i * 30 + 160, 20, false, new Color(64, 255, 64), new Color(128, 0, 128));
               if(this.rnkChiffon[i].name.length() > 6) {
                  this.drawTextExt(this.grpGraphics, this.rnkChiffon[i].name.substring(0, 6), 560, i * 30 + 160, 20, false, new Color(64, 255, 64), new Color(128, 0, 128));
               } else {
                  this.drawTextExt(this.grpGraphics, this.rnkChiffon[i].name, 560, i * 30 + 160, 20, false, new Color(64, 255, 64), new Color(128, 0, 128));
               }
            } else {
               this.drawText(this.grpGraphics, buf, 470, i * 30 + 160, 20, false);
               if(this.rnkChiffon[i].name.length() > 6) {
                  this.drawText(this.grpGraphics, this.rnkChiffon[i].name.substring(0, 6), 560, i * 30 + 160, 20, false);
               } else {
                  this.drawText(this.grpGraphics, this.rnkChiffon[i].name, 560, i * 30 + 160, 20, false);
               }
            }
         }

         this.drawText(this.grpGraphics, "PLEASE CLICK TO RETURN", 425, 470, 16, false);
      }

   }

   public void drawBackGround(Graphics g) {
      int x = this.gnBGCounter % 64 * 10;
      g.drawImage(this.imgBackground, 0 - x, 0, this);
      g.drawImage(this.imgBackground, 640 - x, 0, this);
   }

   public void drawLife(Graphics g) {
      for(int i = 0; i < this.charaHiroin.nLife; ++i) {
         g.drawImage(this.imgLife, 615 - i * 25, 5, this);
      }

   }

   public void drawBom(Graphics g) {
      for(int i = 0; i < this.charaHiroin.nBomNum; ++i) {
         g.drawImage(this.imgBom, 615 - i * 25, 30, this);
      }

   }

   public void drawScore(Graphics g) {
      DecimalFormat df = new DecimalFormat();
      df.applyLocalizedPattern("0000000");
      String str = "SCORE:" + df.format((long)this.gnScore);
      this.drawText(g, str, 3, 22, 22, false);
   }

   public void drawText(Graphics g, String str, int x, int y, int size, boolean center) {
      g.setFont(new Font("\uff2d\uff33 \u30b4\u30b7\u30c3\u30af", 1, size));
      int X;
      if(center) {
         FontMetrics fo = g.getFontMetrics();
         int strWidth = fo.stringWidth(str);
         X = x - strWidth / 2;
      } else {
         X = x;
      }

      g.setColor(new Color(128, 0, 128));
      g.drawString(str, X + 2, y + 2);
      g.setColor(new Color(255, 255, 200));
      g.drawString(str, X, y);
   }

   public void drawTextExt(Graphics g, String str, int x, int y, int size, boolean center, Color fg, Color bg) {
      g.setFont(new Font("\uff2d\uff33 \u30b4\u30b7\u30c3\u30af", 1, size));
      int X;
      if(center) {
         FontMetrics fo = g.getFontMetrics();
         int strWidth = fo.stringWidth(str);
         X = x - strWidth / 2;
      } else {
         X = x;
      }

      g.setColor(bg);
      g.drawString(str, X + 2, y + 2);
      g.setColor(fg);
      g.drawString(str, X, y);
   }

   public void drawSpeakBox(Graphics g, int x, int y, int w, int h, String str, boolean enemy) {
      char[] ch = str.toCharArray();
      int lineLmt = w / 20;
      int[] xpoints = new int[3];

      int i;
      for(i = 0; i < h; ++i) {
         int lvl = (int)(160.0D / (double)h * (double)i + 0.5D) + 0;
         Color c = new Color(lvl, lvl, 32);
         g.setColor(c);
         g.fillRect(x, y + i, w, 1);
      }

      if(enemy) {
         xpoints[0] = x + w - 30;
         xpoints[1] = x + w - 13;
         xpoints[2] = x + w - 7;
      } else {
         xpoints[0] = x + 30;
         xpoints[1] = x + 13;
         xpoints[2] = x + 7;
      }

      int[] ypoints = new int[]{y + h + 0, y + h + 0, y + h + 15};
      g.fillPolygon(xpoints, ypoints, 3);
      g.setFont(new Font("\uff2d\uff33 \u30b4\u30b7\u30c3\u30af", 1, 18));
      g.setColor(new Color(255, 255, 255));
      int xp = 0;
      int yp = 0;

      for(i = 0; i < str.length(); ++i) {
         g.drawString(String.valueOf(ch[i]), x + 20 * xp, y + 18 + 20 * yp);
         ++xp;
         if(lineLmt == xp) {
            ++yp;
            xp = 0;
         }
      }

   }

   public void itemBirth() {
      if(!this.gbBossflg) {
         if(this.gnStageCounter % 320 == 0 && this.gnStageCounter != 0) {
            for(int i = 0; i < 3; ++i) {
               if(this.charaItem[i].nLife == 0) {
                  this.charaItem[i].nEnemyType = 40;
                  this.charaItem[i].enemyInit(this);
                  break;
               }
            }
         }

      }
   }

   public void itemDisp(Graphics g) {
      for(int i = 0; i < 3; ++i) {
         if(this.charaItem[i].nLife != 0) {
            this.charaItem[i].enemyAction(this, g);
         }
      }

   }

   public void enemyBirth() {
      byte enemy_type = 0;
      if(!this.gbBossflg) {
         if(this.gnStageCounter != 0) {
            switch(this.gnStage) {
            case 0:
               if(this.gnStageCounter < 768) {
                  if(this.gnStageCounter % 64 == 0) {
                     enemy_type = 10;
                  }
               } else if(this.gnStageCounter % 64 == 0) {
                  enemy_type = 10;
               } else if(this.gnStageCounter % 64 == 32) {
                  enemy_type = 11;
               }
               break;
            case 1:
               if(this.gnStageCounter < 768) {
                  if(this.gnStageCounter % 64 == 0 || this.gnStageCounter % 64 == 32) {
                     enemy_type = 11;
                  }
               } else if(this.gnStageCounter % 64 == 0) {
                  enemy_type = 11;
               } else if(this.gnStageCounter % 64 == 32) {
                  enemy_type = 12;
               }
               break;
            case 2:
               if(this.gnStageCounter < 512) {
                  if(this.gnStageCounter % 64 == 0 || this.gnStageCounter % 64 == 21 || this.gnStageCounter % 64 == 42) {
                     enemy_type = 12;
                  }
               } else if(this.gnStageCounter < 1024) {
                  if(this.gnStageCounter % 64 == 0) {
                     enemy_type = 12;
                  } else if(this.gnStageCounter % 64 == 21) {
                     enemy_type = 13;
                  } else if(this.gnStageCounter % 64 == 42) {
                     enemy_type = 13;
                  }
               } else if(this.gnStageCounter % 64 == 0 || this.gnStageCounter % 64 == 21 || this.gnStageCounter % 64 == 42) {
                  enemy_type = 13;
               }
               break;
            case 3:
               if(this.gnStageCounter < 512) {
                  if(this.gnStageCounter % 64 == 0 || this.gnStageCounter % 64 == 21 || this.gnStageCounter % 64 == 42) {
                     enemy_type = 14;
                  }
               } else if(this.gnStageCounter < 1024) {
                  if(this.gnStageCounter % 64 == 0) {
                     enemy_type = 14;
                  } else if(this.gnStageCounter % 64 == 21) {
                     enemy_type = 14;
                  } else if(this.gnStageCounter % 64 == 42) {
                     enemy_type = 15;
                  }
               } else if(this.gnStageCounter % 64 == 0 || this.gnStageCounter % 64 == 21 || this.gnStageCounter % 64 == 42) {
                  enemy_type = 15;
               }
               break;
            case 4:
               if(this.gnStageCounter < 512) {
                  if(this.gnStageCounter % 64 == 0 || this.gnStageCounter % 64 == 21 || this.gnStageCounter % 64 == 42) {
                     enemy_type = 16;
                  }
               } else if(this.gnStageCounter < 1024) {
                  if(this.gnStageCounter % 64 == 0) {
                     enemy_type = 16;
                  } else if(this.gnStageCounter % 64 == 21) {
                     enemy_type = 16;
                  } else if(this.gnStageCounter % 64 == 42) {
                     enemy_type = 17;
                  }
               } else if(this.gnStageCounter % 64 == 0 || this.gnStageCounter % 64 == 21 || this.gnStageCounter % 64 == 42) {
                  enemy_type = 17;
               }
            }

            if(enemy_type > 0) {
               for(int i = 0; i < 64; ++i) {
                  if(this.charaEnemy[i].nLife == 0 && !this.enemyIsBeamAction(this.charaEnemy[i])) {
                     this.charaEnemy[i].nEnemyType = enemy_type;
                     this.charaEnemy[i].enemyInit(this);
                     break;
                  }
               }
            }

         }
      }
   }

   public void enemyDisp(Graphics g) {
      for(int i = 0; i < 64; ++i) {
         if(this.charaEnemy[i].nLife != 0 || this.enemyIsBeamAction(this.charaEnemy[i])) {
            this.charaEnemy[i].enemyAction(this, g);
         }
      }

   }

   public boolean enemyIsBeamAction(Character c) {
      boolean ret = false;

      for(int i = 0; i < c.nBeamMax; ++i) {
         if(c.nBeamAct[i]) {
            ret = true;
            break;
         }
      }

      return ret;
   }

   public void bossBirth() {
      if(!this.gbBossflg) {
         for(int i = 0; i < 64; ++i) {
            if(this.charaEnemy[i].nLife > 0) {
               this.charaEnemy[i].nLife = 0;
               this.gnScore += this.charaEnemy[i].nScore;

               for(int j = 0; j < 128; ++j) {
                  if(this.eadMain[j].count == 0) {
                     this.eadMain[j].count = 30;
                     this.eadMain[j].x = this.charaEnemy[i].nPosBodyX - 1;
                     this.eadMain[j].y = this.charaEnemy[i].nPosBodyY;
                     this.eadMain[j].score = this.charaEnemy[i].nScore;
                     break;
                  }
               }
            }
         }

         switch(this.gnStage) {
         case 0:
            this.charaEnemy[0].nEnemyType = 20;
            break;
         case 1:
            this.charaEnemy[0].nEnemyType = 21;
            break;
         case 2:
            this.charaEnemy[0].nEnemyType = 22;
            break;
         case 3:
            this.charaEnemy[0].nEnemyType = 23;
            break;
         case 4:
            this.charaEnemy[0].nEnemyType = 24;
         }

         this.charaEnemy[0].enemyInit(this);
      }
   }

   public void bossSpeak() {
      if(this.gnBossSpeak == 2) {
         switch(this.gnStage) {
         case 0:
            this.drawSpeakBox(this.grpGraphics, this.charaEnemy[0].koyuki.speak_x, this.charaEnemy[0].koyuki.speak_y, this.charaEnemy[0].koyuki.speak_w, this.charaEnemy[0].koyuki.speak_h, this.charaEnemy[0].koyuki.speak_str[this.charaHiroin.nHiroinType], true);
            break;
         case 1:
            this.drawSpeakBox(this.grpGraphics, this.charaEnemy[0].yun.speak_x, this.charaEnemy[0].yun.speak_y, this.charaEnemy[0].yun.speak_w, this.charaEnemy[0].yun.speak_h, this.charaEnemy[0].yun.speak_str[this.charaHiroin.nHiroinType], true);
            break;
         case 2:
            this.drawSpeakBox(this.grpGraphics, this.charaEnemy[0].ell.speak_x, this.charaEnemy[0].ell.speak_y, this.charaEnemy[0].ell.speak_w, this.charaEnemy[0].ell.speak_h, this.charaEnemy[0].ell.speak_str[this.charaHiroin.nHiroinType], true);
            break;
         case 3:
            this.drawSpeakBox(this.grpGraphics, this.charaEnemy[0].camus.speak_x, this.charaEnemy[0].camus.speak_y, this.charaEnemy[0].camus.speak_w, this.charaEnemy[0].camus.speak_h, this.charaEnemy[0].camus.speak_str[this.charaHiroin.nHiroinType], true);
            break;
         case 4:
            this.drawSpeakBox(this.grpGraphics, this.charaEnemy[0].mali.speak_x, this.charaEnemy[0].mali.speak_y, this.charaEnemy[0].mali.speak_w, this.charaEnemy[0].mali.speak_h, this.charaEnemy[0].mali.speak_str[this.charaHiroin.nHiroinType], true);
         }
      } else if(this.gnBossSpeak == 1) {
         this.drawSpeakBox(this.grpGraphics, this.charaHiroin.nPosBodyX * 10 + 15, this.charaHiroin.nPosBodyY * 10 - 125, 200, 85, this.charaHiroin.speakHiroin(this.gnStage), false);
      }

   }

   public void shadowCheck() {
      if(this.charaHiroin.nHiroinType == 1) {
         if(this.charaHiroin.nPwr >= 4) {
            this.charaHiroin.nPosShadowX[2] = this.charaHiroin.nPosShadowX[1];
            this.charaHiroin.nPosShadowY[2] = this.charaHiroin.nPosShadowY[1];
         }

         if(this.charaHiroin.nPwr >= 3) {
            this.charaHiroin.nPosShadowX[1] = this.charaHiroin.nPosShadowX[0];
            this.charaHiroin.nPosShadowY[1] = this.charaHiroin.nPosShadowY[0];
         }

         if(this.charaHiroin.nPwr >= 2) {
            this.charaHiroin.nPosShadowX[0] = this.charaHiroin.nPosBodyX;
            this.charaHiroin.nPosShadowY[0] = this.charaHiroin.nPosBodyY;
         }
      }

   }

   public void zonbiCheck() {
      if(this.charaHiroin.nZombi > 0) {
         --this.charaHiroin.nZombi;
      }

      for(int i = 0; i < 128; ++i) {
         if(this.eadMain[i].count > 0) {
            this.drawText(this.grpGraphics, Integer.toString(this.eadMain[i].score), this.eadMain[i].x * 10, this.eadMain[i].y * 10, 20, true);
            --this.eadMain[i].count;
         }
      }

   }

   public void bomCheck() {
      if(this.gnBomActive > 0) {
         --this.gnBomActive;

         for(int i = 0; i < 64; ++i) {
            int j;
            if(!this.gbBossflg && this.charaEnemy[i].nLife > 0) {
               this.charaEnemy[i].nLife = 0;
               this.gnScore += this.charaEnemy[i].nScore;

               for(j = 0; j < 128; ++j) {
                  if(this.eadMain[j].count == 0) {
                     this.eadMain[j].count = 30;
                     this.eadMain[j].x = this.charaEnemy[i].nPosBodyX - 1;
                     this.eadMain[j].y = this.charaEnemy[i].nPosBodyY;
                     this.eadMain[j].score = this.charaEnemy[i].nScore;
                     break;
                  }
               }
            }

            for(j = 0; j < this.charaEnemy[i].nBeamMax; ++j) {
               if(this.charaEnemy[i].nBeamAct[j]) {
                  this.charaEnemy[i].nBeamAct[j] = false;
               }
            }
         }
      }

   }

   public void overCheck() {
      if(this.gbOvering) {
         this.charaHiroin.drawCharaBody(this, this.grpGraphics, this.charaHiroin.nHiroinType + 30);
         ++this.charaHiroin.nPosBodyY;
         if(this.charaHiroin.nPosBodyY > 48) {
            this.gnMode = 2;
            this.initOver();
         }
      }

   }

   public void oneupCheck() {
      switch(this.gnOneupflg) {
      case 0:
         if(this.gnScore >= 10000) {
            ++this.charaHiroin.nLife;
            ++this.gnOneupflg;
         }
         break;
      case 1:
         if(this.gnScore >= 30000) {
            ++this.charaHiroin.nLife;
            ++this.gnOneupflg;
         }
         break;
      case 2:
         if(this.gnScore >= '\uea60') {
            ++this.charaHiroin.nLife;
            ++this.gnOneupflg;
         }
         break;
      case 3:
         if(this.gnScore >= 100000) {
            ++this.charaHiroin.nLife;
            ++this.gnOneupflg;
         }
         break;
      case 4:
         if(this.gnScore >= 200000) {
            ++this.charaHiroin.nLife;
            ++this.gnOneupflg;
         }
         break;
      default:
         if(this.gnScore >= 150000 * (this.gnOneupflg - 5) + 350000) {
            ++this.charaHiroin.nLife;
            ++this.gnOneupflg;
         }
      }

   }

   public boolean hitCheck() {
      boolean esc = false;
      boolean atk = false;

      int i;
      int j;
      int l;
      int x;
      int y;
      int x2;
      int y2;
      for(i = 0; i < this.charaHiroin.nBeamMax; ++i) {
         if(this.charaHiroin.nBeamAct[i]) {
            for(j = 0; j < 64; ++j) {
               esc = false;
               atk = false;
               if(this.charaEnemy[j].nLife > 0) {
                  for(x = this.charaEnemy[j].nPosBodyX - 2; x <= this.charaEnemy[j].nPosBodyX + 2; ++x) {
                     for(y = this.charaEnemy[j].nPosBodyY - 2; y <= this.charaEnemy[j].nPosBodyY + 2; ++y) {
                        if(this.charaHiroin.bBeamRange) {
                           for(x2 = this.charaHiroin.nPosBeamX[i] - 1; x2 <= this.charaHiroin.nPosBeamX[i] + 1; ++x2) {
                              for(y2 = this.charaHiroin.nPosBeamY[i] - 1; y2 <= this.charaHiroin.nPosBeamY[i] + 1; ++y2) {
                                 if(x == x2 && y == y2) {
                                    atk = true;
                                    break;
                                 }
                              }

                              if(atk) {
                                 break;
                              }
                           }
                        } else if(this.charaHiroin.nPosBeamX[i] == x && this.charaHiroin.nPosBeamY[i] == y) {
                           atk = true;
                        }

                        if(atk) {
                           this.charaEnemy[j].nLife -= this.powerDamage();
                           if(this.charaEnemy[j].nLife < 0) {
                              this.charaEnemy[j].nLife = 0;
                           }

                           if(this.charaEnemy[j].nLife == 0) {
                              this.gnScore += this.charaEnemy[j].nScore;

                              for(l = 0; l < 128; ++l) {
                                 if(this.eadMain[l].count == 0) {
                                    this.eadMain[l].count = 30;
                                    this.eadMain[l].x = this.charaEnemy[j].nPosBodyX - 1;
                                    this.eadMain[l].y = this.charaEnemy[j].nPosBodyY;
                                    this.eadMain[l].score = this.charaEnemy[j].nScore;
                                    break;
                                 }
                              }

                              if(this.gbBossflg) {
                                 this.gnStageCounter = 0;
                                 this.gbBossflg = false;
                                 ++this.gnStage;
                                 if(this.gnStage >= 5) {
                                    this.gbAllClear = true;
                                 }
                              }
                           }

                           this.charaHiroin.nBeamAct[i] = false;
                           esc = true;
                           break;
                        }
                     }

                     if(esc) {
                        break;
                     }
                  }
               }
            }
         }
      }

      if(this.charaHiroin.nZombi == 0 && !this.gbPhenix) {
         for(i = 0; i < 64; ++i) {
            if(this.charaEnemy[i].nLife > 0) {
               for(j = 0; j < this.charaEnemy[i].nBeamMax; ++j) {
                  esc = false;
                  atk = false;
                  if(this.charaEnemy[i].nBeamAct[j]) {
                     for(x = this.charaHiroin.nPosBodyX - 1; x <= this.charaHiroin.nPosBodyX + 1; ++x) {
                        for(y = this.charaHiroin.nPosBodyY - 1; y <= this.charaHiroin.nPosBodyY + 1; ++y) {
                           if(this.charaEnemy[i].bBeamRange) {
                              for(x2 = this.charaEnemy[i].nPosBeamX[j] - 1; x2 <= this.charaEnemy[i].nPosBeamX[j] + 1; ++x2) {
                                 for(y2 = this.charaEnemy[i].nPosBeamY[j] - 1; y2 <= this.charaEnemy[i].nPosBeamY[j] + 1; ++y2) {
                                    if(x2 == x && y2 == y) {
                                       atk = true;
                                       break;
                                    }
                                 }

                                 if(atk) {
                                    break;
                                 }
                              }
                           } else if(this.charaEnemy[i].nPosBeamX[j] == x && this.charaEnemy[i].nPosBeamY[j] == y) {
                              atk = true;
                           }

                           if(atk) {
                              this.powerDown();
                              --this.charaHiroin.nLife;
                              if(this.charaHiroin.nLife <= 0) {
                                 return true;
                              }

                              this.charaHiroin.nZombi = 100;
                              this.charaHiroin.nBomNum = 3;
                              this.charaEnemy[i].nBeamAct[j] = false;
                              esc = true;
                              break;
                           }
                        }

                        if(esc) {
                           break;
                        }
                     }
                  }
               }
            }
         }
      }

      if(this.charaHiroin.nZombi == 0 && !this.gbPhenix) {
         for(i = 0; i < 64; ++i) {
            esc = false;
            if(this.charaEnemy[i].nLife > 0) {
               for(x = this.charaHiroin.nPosBodyX - 1; x <= this.charaHiroin.nPosBodyX + 1; ++x) {
                  for(y = this.charaHiroin.nPosBodyY - 1; y <= this.charaHiroin.nPosBodyY + 1; ++y) {
                     for(x2 = this.charaEnemy[i].nPosBodyX - 1; x2 <= this.charaEnemy[i].nPosBodyX + 1; ++x2) {
                        for(y2 = this.charaEnemy[i].nPosBodyY - 1; y2 <= this.charaEnemy[i].nPosBodyY + 1; ++y2) {
                           if(x == x2 && y == y2) {
                              this.powerDown();
                              --this.charaHiroin.nLife;
                              if(this.charaHiroin.nLife <= 0) {
                                 return true;
                              }

                              this.charaHiroin.nZombi = 100;
                              this.charaHiroin.nBomNum = 3;
                              esc = true;
                              break;
                           }
                        }

                        if(esc) {
                           break;
                        }
                     }

                     if(esc) {
                        break;
                     }
                  }

                  if(esc) {
                     break;
                  }
               }
            }
         }
      }

      for(i = 0; i < 3; ++i) {
         esc = false;
         if(this.charaItem[i].nLife > 0) {
            for(x = this.charaHiroin.nPosBodyX - 2; x <= this.charaHiroin.nPosBodyX + 2; ++x) {
               for(y = this.charaHiroin.nPosBodyY - 2; y <= this.charaHiroin.nPosBodyY + 2; ++y) {
                  for(x2 = this.charaItem[i].nPosBodyX - 2; x2 <= this.charaItem[i].nPosBodyX + 2; ++x2) {
                     for(y2 = this.charaItem[i].nPosBodyY - 2; y2 <= this.charaItem[i].nPosBodyY + 2; ++y2) {
                        if(x == x2 && y == y2) {
                           this.powerUp();
                           this.charaItem[i].nLife = 0;
                           this.gnScore += this.charaItem[i].nScore;

                           for(l = 0; l < 128; ++l) {
                              if(this.eadMain[l].count == 0) {
                                 this.eadMain[l].count = 30;
                                 this.eadMain[l].x = this.charaItem[i].nPosBodyX - 1;
                                 this.eadMain[l].y = this.charaItem[i].nPosBodyY;
                                 this.eadMain[l].score = this.charaItem[i].nScore;
                                 break;
                              }
                           }

                           esc = true;
                           break;
                        }
                     }

                     if(esc) {
                        break;
                     }
                  }

                  if(esc) {
                     break;
                  }
               }

               if(esc) {
                  break;
               }
            }
         }
      }

      return false;
   }

   public int powerDamage() {
      boolean ret = false;
      int ret1;
      switch(this.charaHiroin.nHiroinType) {
      case 0:
      default:
         ret1 = this.charaHiroin.nPwr;
         break;
      case 1:
         ret1 = 1;
         break;
      case 2:
         switch(this.charaHiroin.nPwr) {
         case 3:
         case 4:
            ret = true;
         default:
            ret1 = 1;
         }
      }

      return ret1;
   }

   public void powerUp() {
      if(this.charaHiroin.nPwr != 4) {
         ++this.charaHiroin.nPwr;
         switch(this.charaHiroin.nHiroinType) {
         case 0:
         default:
            if(this.charaHiroin.nPwr == 3) {
               this.charaHiroin.bBeamRange = true;
            }
            break;
         case 1:
            this.charaHiroin.nBeamMax += 6;
            break;
         case 2:
            this.charaHiroin.nBeamMax += 6;
            if(this.charaHiroin.nPwr == 3) {
               this.charaHiroin.bBeamRange = true;
            }
         }
      }

   }

   public void powerDown() {
      if(this.charaHiroin.nPwr != 1) {
         --this.charaHiroin.nPwr;
         switch(this.charaHiroin.nHiroinType) {
         case 0:
         default:
            if(this.charaHiroin.nPwr == 2) {
               this.charaHiroin.bBeamRange = false;
            }
            break;
         case 1:
            this.charaHiroin.nBeamMax -= 6;
            break;
         case 2:
            this.charaHiroin.nBeamMax -= 6;
            if(this.charaHiroin.nPwr == 2) {
               this.charaHiroin.bBeamRange = false;
            }
         }
      }

   }

   public void ReadDataFile() {
      BufferedReader br = null;
      URL wi_url = null;
      URLConnection wi_connect = null;
      InputStream wi_stream = null;
      String buf = null;
      int i = 0;
      byte nReadFlg = 0;

      try {
         wi_url = new URL(this.getCodeBase() + "score.txt");

         try {
            wi_connect = wi_url.openConnection();
            wi_connect.setUseCaches(false);
            wi_stream = wi_connect.getInputStream();
            br = new BufferedReader(new InputStreamReader(wi_stream));

            try {
               while(true) {
                  buf = br.readLine();
                  if(buf == null) {
                     br.close();
                     wi_stream.close();
                     break;
                  }

                  switch(nReadFlg) {
                  case 0:
                     this.rnkMaru[i] = new Ranking();
                     this.rnkMaru[i].score = Integer.parseInt(buf);
                     nReadFlg = 1;
                     break;
                  case 1:
                     this.rnkMaru[i].name = buf;
                     nReadFlg = 0;
                     ++i;
                     if(i == 10) {
                        nReadFlg = 2;
                        i = 0;
                     }
                     break;
                  case 2:
                     this.rnkCat[i] = new Ranking();
                     this.rnkCat[i].score = Integer.parseInt(buf);
                     nReadFlg = 3;
                     break;
                  case 3:
                     this.rnkCat[i].name = buf;
                     nReadFlg = 2;
                     ++i;
                     if(i == 10) {
                        nReadFlg = 4;
                        i = 0;
                     }
                     break;
                  case 4:
                     this.rnkChiffon[i] = new Ranking();
                     this.rnkChiffon[i].score = Integer.parseInt(buf);
                     nReadFlg = 5;
                     break;
                  case 5:
                     this.rnkChiffon[i].name = buf;
                     nReadFlg = 4;
                     ++i;
                  }
               }
            } catch (IOException var9) {
               var9.printStackTrace();
            }
         } catch (IOException var10) {
            ;
         }
      } catch (MalformedURLException var11) {
         ;
      }

   }

   public void WriteDataFile() {
      boolean i = false;

      try {
         String e1 = this.getDocumentBase().toString();
         String cgiUrl = e1.substring(0, e1.lastIndexOf(47) + 1) + "write.cgi";
         URL wi_url = new URL(cgiUrl);

         try {
            URLConnection wi_connect = wi_url.openConnection();
            wi_connect.setDoOutput(true);

            try {
               PrintStream e11 = new PrintStream(wi_connect.getOutputStream());

               int var12;
               for(var12 = 0; var12 < 10; ++var12) {
                  e11.println(this.rnkMaru[var12].score);
                  e11.println(this.rnkMaru[var12].name);
               }

               for(var12 = 0; var12 < 10; ++var12) {
                  e11.println(this.rnkCat[var12].score);
                  e11.println(this.rnkCat[var12].name);
               }

               for(var12 = 0; var12 < 10; ++var12) {
                  e11.println(this.rnkChiffon[var12].score);
                  e11.println(this.rnkChiffon[var12].name);
               }

               e11.close();
               BufferedReader reader = new BufferedReader(new InputStreamReader(wi_connect.getInputStream()));

               String line;
               while((line = reader.readLine()) != null) {
                  System.out.println(line);
               }

               reader.close();
            } catch (IOException var9) {
               var9.printStackTrace();
            }
         } catch (IOException var10) {
            var10.printStackTrace();
         }
      } catch (MalformedURLException var11) {
         var11.printStackTrace();
      }

   }

   public class EnemyAfterDead {

      int count;
      int x;
      int y;
      int score;
      final MaruAdventure this$0 = MaruAdventure.this;


   }

   public class MousePress extends MouseAdapter {

      boolean pressed = false;
      final MaruAdventure this$0 = MaruAdventure.this;


      public void mousePressed(MouseEvent e) {
         this.pressed = true;
         if(this.this$0.gnMode == 0) {
            if(this.this$0.gnOpeningMousePosY >= 285 && this.this$0.gnOpeningMousePosY <= 355 && this.this$0.gnOpeningMousePosX >= 205 && this.this$0.gnOpeningMousePosX <= 275) {
               this.this$0.charaHiroin.nHiroinType = 0;
               this.this$0.gnMode = 1;
               this.this$0.initGame();
            } else if(this.this$0.gnOpeningMousePosY >= 285 && this.this$0.gnOpeningMousePosY <= 355 && this.this$0.gnOpeningMousePosX >= 285 && this.this$0.gnOpeningMousePosX <= 355) {
               this.this$0.charaHiroin.nHiroinType = 1;
               this.this$0.gnMode = 1;
               this.this$0.initGame();
            } else if(this.this$0.gnOpeningMousePosY >= 285 && this.this$0.gnOpeningMousePosY <= 355 && this.this$0.gnOpeningMousePosX >= 365 && this.this$0.gnOpeningMousePosX <= 435) {
               this.this$0.charaHiroin.nHiroinType = 2;
               this.this$0.gnMode = 1;
               this.this$0.initGame();
            } else if(this.this$0.gnOpeningMousePosY >= 360 && this.this$0.gnOpeningMousePosY <= 380 && this.this$0.gnOpeningMousePosX >= 249 && this.this$0.gnOpeningMousePosX <= 391) {
               this.this$0.gnMode = 3;
               this.this$0.initRanking();
            }
         } else if(this.this$0.gnMode == 3) {
            this.this$0.gnMode = 0;
            this.this$0.initOpening();
         } else if(this.this$0.gnMode == 2 && !this.this$0.gbRankIn) {
            this.this$0.gnMode = 0;
            this.this$0.initOpening();
         } else if(this.this$0.gnMode == 1 && this.this$0.gbAllClear) {
            this.this$0.gnMode = 2;
            this.this$0.initOver();
         }

      }

      public void mouseReleased(MouseEvent e) {
         this.pressed = false;
      }
   }

   public class MouseMotion extends MouseMotionAdapter {

      final MaruAdventure this$0 = MaruAdventure.this;


      public void mouseMoved(MouseEvent e) {
         if(this.this$0.gnMode == 0) {
            this.this$0.gnOpeningMousePosX = e.getX();
            this.this$0.gnOpeningMousePosY = e.getY();
         }

      }
   }

   public class PushButton implements ActionListener {

      final MaruAdventure this$0 = MaruAdventure.this;


      public void actionPerformed(ActionEvent ae) {
         if(ae.getActionCommand().equals("regist")) {
            int i;
            int j;
            switch(this.this$0.charaHiroin.nHiroinType) {
            case 0:
               for(i = 0; i < 10; ++i) {
                  if(this.this$0.gnScore >= this.this$0.rnkMaru[i].score) {
                     for(j = 9; j > i; --j) {
                        this.this$0.rnkMaru[j].score = this.this$0.rnkMaru[j - 1].score;
                        this.this$0.rnkMaru[j].name = this.this$0.rnkMaru[j - 1].name;
                     }

                     this.this$0.rnkMaru[i].score = this.this$0.gnScore;
                     this.this$0.rnkMaru[i].name = this.this$0.txtfldName.getText();
                     this.this$0.gsRegistName = this.this$0.txtfldName.getText();
                     this.this$0.gnRegistNumber = i;
                     break;
                  }
               }

               Arrays.sort(this.this$0.rnkMaru);
               break;
            case 1:
               for(i = 0; i < 10; ++i) {
                  if(this.this$0.gnScore >= this.this$0.rnkCat[i].score) {
                     for(j = 9; j > i; --j) {
                        this.this$0.rnkCat[j].score = this.this$0.rnkCat[j - 1].score;
                        this.this$0.rnkCat[j].name = this.this$0.rnkCat[j - 1].name;
                     }

                     this.this$0.rnkCat[i].score = this.this$0.gnScore;
                     this.this$0.rnkCat[i].name = this.this$0.txtfldName.getText();
                     this.this$0.gsRegistName = this.this$0.txtfldName.getText();
                     this.this$0.gnRegistNumber = i;
                     break;
                  }
               }

               Arrays.sort(this.this$0.rnkCat);
               break;
            case 2:
               for(i = 0; i < 10; ++i) {
                  if(this.this$0.gnScore >= this.this$0.rnkChiffon[i].score) {
                     for(j = 9; j > i; --j) {
                        this.this$0.rnkChiffon[j].score = this.this$0.rnkChiffon[j - 1].score;
                        this.this$0.rnkChiffon[j].name = this.this$0.rnkChiffon[j - 1].name;
                     }

                     this.this$0.rnkChiffon[i].score = this.this$0.gnScore;
                     this.this$0.rnkChiffon[i].name = this.this$0.txtfldName.getText();
                     this.this$0.gsRegistName = this.this$0.txtfldName.getText();
                     this.this$0.gnRegistNumber = i;
                     break;
                  }
               }

               Arrays.sort(this.this$0.rnkChiffon);
            }

            this.this$0.WriteDataFile();
            this.this$0.initRanking();
            this.this$0.gnMode = 3;
         }

      }
   }

   public class KeyPress extends KeyAdapter {

      boolean pressedUp = false;
      boolean pressedDown = false;
      boolean pressedLeft = false;
      boolean pressedRight = false;
      boolean pressedZ = false;
      final MaruAdventure this$0 = MaruAdventure.this;


      public void keyControl() {
         if(this.this$0.gnMode == 1) {
            if(this.isPressed(38) && this.this$0.charaHiroin.nPosBodyY > 0) {
               this.this$0.charaHiroin.nPosBodyY -= this.this$0.charaHiroin.nStep;
            }

            if(this.isPressed(40) && this.this$0.charaHiroin.nPosBodyY < 48) {
               this.this$0.charaHiroin.nPosBodyY += this.this$0.charaHiroin.nStep;
            }

            if(this.isPressed(37) && this.this$0.charaHiroin.nPosBodyX > 0) {
               this.this$0.charaHiroin.nPosBodyX -= this.this$0.charaHiroin.nStep;
            }

            if(this.isPressed(39) && this.this$0.charaHiroin.nPosBodyX < 64) {
               this.this$0.charaHiroin.nPosBodyX += this.this$0.charaHiroin.nStep;
            }

            if(this.isPressed(90)) {
               this.this$0.charaHiroin.beamBirthHiroin();
            } else {
               this.this$0.charaHiroin.beamDeathHiroin();
            }

         }
      }

      public boolean isPressed(int key) {
         boolean ret = false;
         switch(key) {
         case 37:
            ret = this.pressedLeft;
            break;
         case 38:
            ret = this.pressedUp;
            break;
         case 39:
            ret = this.pressedRight;
            break;
         case 40:
            ret = this.pressedDown;
            break;
         case 90:
            ret = this.pressedZ;
         }

         return ret;
      }

      public void keyPressed(KeyEvent e) {
         int key = e.getKeyCode();
         if(this.this$0.gnMode == 1) {
            switch(key) {
            case 37:
               this.pressedLeft = true;
               break;
            case 38:
               this.pressedUp = true;
               break;
            case 39:
               this.pressedRight = true;
               break;
            case 40:
               this.pressedDown = true;
               break;
            case 69:
               if((this.this$0.gnPhenixCheck & 7) == 7) {
                  this.this$0.gnPhenixCheck |= 8;
               } else {
                  this.this$0.gnPhenixCheck = 0;
               }
               break;
            case 73:
               if((this.this$0.gnPhenixCheck & 31) == 31) {
                  this.this$0.gbPhenix = true;
                  this.this$0.powerUp();
                  this.this$0.powerUp();
                  this.this$0.powerUp();
               } else {
                  this.this$0.gnPhenixCheck = 0;
               }
               break;
            case 75:
               if((this.this$0.gnPhenixCheck & 15) == 15) {
                  this.this$0.gnPhenixCheck |= 16;
               } else {
                  this.this$0.gnPhenixCheck = 0;
               }
               break;
            case 77:
               if(this.this$0.gnPhenixCheck == 0) {
                  this.this$0.gnPhenixCheck |= 1;
               } else {
                  this.this$0.gnPhenixCheck = 0;
               }
               break;
            case 84:
               if((this.this$0.gnPhenixCheck & 3) == 3) {
                  this.this$0.gnPhenixCheck |= 4;
               } else {
                  this.this$0.gnPhenixCheck = 0;
               }
               break;
            case 85:
               if((this.this$0.gnPhenixCheck & 1) == 1) {
                  this.this$0.gnPhenixCheck |= 2;
               } else {
                  this.this$0.gnPhenixCheck = 0;
               }
               break;
            case 90:
               this.pressedZ = true;
            }

         }
      }

      public void keyReleased(KeyEvent e) {
         int key = e.getKeyCode();
         if(this.this$0.gnMode == 1) {
            switch(key) {
            case 37:
               this.pressedLeft = false;
               break;
            case 38:
               this.pressedUp = false;
               break;
            case 39:
               this.pressedRight = false;
               break;
            case 40:
               this.pressedDown = false;
               break;
            case 88:
               if(this.this$0.gnBossSpeak > 0) {
                  --this.this$0.gnBossSpeak;
               } else if(this.this$0.charaHiroin.nBomNum > 0 && this.this$0.gnBossSpeak == 0 && !this.this$0.gbAllClear) {
                  this.this$0.gnBomActive = 45;
                  --this.this$0.charaHiroin.nBomNum;
               }
               break;
            case 90:
               this.pressedZ = false;
            }

         }
      }
   }

   class MainThread extends Thread {

      final MaruAdventure this$0 = MaruAdventure.this;


      public void run() {
         Thread thisThread = Thread.currentThread();

         while(this.this$0.thrdMain == thisThread) {
            try {
               MaruAdventure.MainThread var10000 = this.this$0.thrdMain;
               sleep(30L);
               this.this$0.gbDrawOk = true;
            } catch (InterruptedException var3) {
               ;
            }
         }

      }
   }
}
