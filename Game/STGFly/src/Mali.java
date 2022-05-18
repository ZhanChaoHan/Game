// Decompiled by:       Fernflower v0.6
// Date:                05.11.2010 13:50:18
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

import java.awt.Graphics;

class Mali {

   int[] beam_x = new int[256];
   int[] beam_y = new int[256];
   int[] beam_r = new int[256];
   int[] beam_oldx = new int[256];
   int[] beam_oldy = new int[256];
   int[] beam_t = new int[256];
   boolean[] way = new boolean[256];
   String[] speak_str = new String[3];
   int speak_x = 350;
   int speak_y = 110;
   int speak_w = 200;
   int speak_h = 85;
   Character charaMain;


   Mali(Character c) {
      this.charaMain = c;
   }

   public void initData(MaruAdventure main) {
      int[] thita = new int[1];
      int[] tarx = new int[1];
      int[] tary = new int[1];
      this.charaMain.nPosBodyX = 58;
      this.charaMain.nPosBodyY = 24;

      for(int i = 0; i < 256; ++i) {
         this.charaMain.nPosBeamX[i] = 0;
         this.charaMain.nPosBeamY[i] = 0;
         this.charaMain.nBeamAct[i] = false;
      }

      this.charaMain.nBeamMax = 256;
      this.charaMain.nLife = 130;
      this.charaMain.nPwr = 1;
      this.charaMain.bBeamRange = true;
      this.charaMain.nScene = 0;
      this.charaMain.nScore = 160000;
      this.charaMain.vectorShoottarget(this.charaMain.nPosBodyX, this.charaMain.nPosBodyY, main.charaHiroin.nPosBodyX, main.charaHiroin.nPosBodyY, 2, tarx, tary, thita);
      this.speak_str[0] = "\u307b\u3001\u672c\u5f53\u306b\u3084\u3063\u3066\u304d\u307e\u3057\u305f\u306e\u306d\uff01\u8cb4\u5973\u306b\u306f\u4e00\u6ef4\u305f\u308a\u3068\u3082\u79c1\u306e\u8840\u306f\u6e21\u3057\u307e\u305b\u3093\uff01\uff08\uff1e\u25b3\uff1c\uff09";
      this.speak_str[1] = "\u307e\u3041\u3001\u53ef\u611b\u3089\u3057\u3044\u304a\u5b22\u3055\u3093\u3067\u3059\u308f\u306d\uff08\uff3e\uff3e\uff09\u2026\u3063\u3066\u305d\u3093\u306a\u5916\u898b\u306b\u9a19\u3055\u308c\u307e\u305b\u3093\u308f\u3088\uff01";
      this.speak_str[2] = "\u305d\u3001\u305d\u3093\u306a\u306b\u3058\u3063\u3068\u898b\u3064\u3081\u306a\u3044\u3067\u4e0b\u3055\u3044\uff01\u3000\u308f\u305f\u304f\u3057\u306f\u98df\u3079\u308c\u307e\u305b\u3093\u308f\u3088\uff01";
   }

   public void move(MaruAdventure main) {
      if(main.gnBossSpeak <= 0) {
         if(main.gnFrameCounter % 40 == 0) {
            this.charaMain.nPosBodyX = main.rnd.nextInt(32) + 24;
            this.charaMain.nPosBodyY = main.rnd.nextInt(32) + 8;
         }

      }
   }

   public void beamBirth(MaruAdventure main) {
      if(this.charaMain.nLife != 0 && main.gnBossSpeak <= 0) {
         int cnt = 0;
         if(main.gnFrameCounter % 10 == 0 || main.gnFrameCounter % 10 == 1 || main.gnFrameCounter % 10 == 2 || main.gnFrameCounter % 10 == 3) {
            for(int i = 0; i < this.charaMain.nBeamMax; ++i) {
               if(!this.charaMain.nBeamAct[i]) {
                  this.charaMain.nBeamAct[i] = true;
                  switch(cnt) {
                  case 0:
                  case 8:
                     this.beam_oldx[i] = 5;
                     this.beam_oldy[i] = 0;
                     this.beam_t[i] = 0;
                     break;
                  case 1:
                  case 9:
                     this.beam_oldx[i] = 5;
                     this.beam_oldy[i] = 5;
                     this.beam_t[i] = 45;
                     break;
                  case 2:
                  case 10:
                     this.beam_oldx[i] = 0;
                     this.beam_oldy[i] = 5;
                     this.beam_t[i] = 90;
                     break;
                  case 3:
                  case 11:
                     this.beam_oldx[i] = -5;
                     this.beam_oldy[i] = 5;
                     this.beam_t[i] = 135;
                     break;
                  case 4:
                  case 12:
                     this.beam_oldx[i] = -5;
                     this.beam_oldy[i] = 0;
                     this.beam_t[i] = 180;
                     break;
                  case 5:
                  case 13:
                     this.beam_oldx[i] = -5;
                     this.beam_oldy[i] = -5;
                     this.beam_t[i] = 225;
                     break;
                  case 6:
                  case 14:
                     this.beam_oldx[i] = 0;
                     this.beam_oldy[i] = -5;
                     this.beam_t[i] = 270;
                     break;
                  case 7:
                  case 15:
                     this.beam_oldx[i] = 5;
                     this.beam_oldy[i] = -5;
                     this.beam_t[i] = 315;
                  }

                  this.charaMain.nPosBeamX[i] = this.charaMain.nPosBodyX;
                  this.charaMain.nPosBeamY[i] = this.charaMain.nPosBodyY;
                  this.beam_x[i] = this.charaMain.nPosBeamX[i];
                  this.beam_y[i] = this.charaMain.nPosBeamY[i];
                  this.beam_r[i] = 5;
                  if(cnt >= 0 && cnt < 8) {
                     this.way[i] = true;
                  } else {
                     this.way[i] = false;
                  }
               }

               ++cnt;
               if(cnt == 16) {
                  break;
               }
            }
         }

      }
   }

   public void beamAction(MaruAdventure main, Graphics g) {
      int[] tarx = new int[1];
      int[] tary = new int[1];

      for(int i = 0; i < this.charaMain.nBeamMax; ++i) {
         if(this.charaMain.nBeamAct[i]) {
            this.charaMain.vectorShoottarget2(0, 0, this.beam_oldx[i], this.beam_oldy[i], this.beam_r[i], this.beam_t[i], tarx, tary);
            if(this.way[i]) {
               this.beam_t[i] += 5;
               if(this.beam_t[i] == 360) {
                  this.beam_t[i] = 0;
               }
            } else {
               this.beam_t[i] -= 5;
               if(this.beam_t[i] == 0) {
                  this.beam_t[i] = 360;
               }
            }

            ++this.beam_r[i];
            this.charaMain.nPosBeamX[i] = this.beam_x[i] + tarx[0];
            this.charaMain.nPosBeamY[i] = this.beam_y[i] + tary[0];
            if(this.charaMain.nPosBeamX[i] < 0 || this.charaMain.nPosBeamX[i] > 64 || this.charaMain.nPosBeamY[i] < 0 || this.charaMain.nPosBeamY[i] > 48) {
               this.charaMain.nBeamAct[i] = false;
            }

            this.charaMain.drawCharaBeam(main, g, 24, i);
         }
      }

   }
}
