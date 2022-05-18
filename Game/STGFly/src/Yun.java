// Decompiled by:       Fernflower v0.6
// Date:                05.11.2010 13:50:14
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

import java.awt.Graphics;

class Yun {

   boolean bWay = false;
   int oldx = 0;
   int oldy = 10;
   int thita = 5;
   int[] beam_x = new int[256];
   int[] beam_y = new int[256];
   int[] beam_oldx = new int[256];
   int[] beam_oldy = new int[256];
   int[] beam_t = new int[256];
   int[] xway = new int[256];
   int[] yway = new int[256];
   String[] speak_str = new String[3];
   int speak_x = 250;
   int speak_y = 110;
   int speak_w = 200;
   int speak_h = 85;
   Character charaMain;


   Yun(Character c) {
      this.charaMain = c;
   }

   public void initData(MaruAdventure main) {
      this.charaMain.nPosBodyX = 48;
      this.charaMain.nPosBodyY = 24;

      for(int i = 0; i < 256; ++i) {
         this.charaMain.nPosBeamX[i] = 0;
         this.charaMain.nPosBeamY[i] = 0;
         this.charaMain.nBeamAct[i] = false;
      }

      this.charaMain.nBeamMax = 256;
      this.charaMain.nLife = 55;
      this.charaMain.nPwr = 1;
      this.charaMain.bBeamRange = false;
      this.charaMain.nScene = 0;
      this.charaMain.nScore = 20000;
      this.bWay = false;
      this.oldx = 15;
      this.oldy = 0;
      this.thita = 95;
      this.speak_str[0] = "\u3082\u30fc\u3063\u3002\u9762\u5012\u3060\u306a\uff5e\u3002\u3061\u3083\u3063\u3061\u3083\u3068\u304b\u304b\u3063\u3066\u304d\u306a\u3088\uff01";
      this.speak_str[1] = "\u3075\u3075\u3093\u3002\u3000\u3000\u3000\u3000\u3000\u3000\u5b50\u732b\u98a8\u60c5\u304c\u732b\u53c8\u69d8\u306b\u6575\u3046\u3068\u306f\u601d\u3046\u306a\u3088\uff1f";
      this.speak_str[2] = "\u307b\u30fc\u3002\u3000\u3000\u3000\u3000\u3000\u3000\u3000\u3053\u308a\u3083\u73cd\u3057\u3044\u3001\u662f\u975e\u7814\u7a76\u6750\u6599\u306b\u306a\u3063\u3066\u9802\u304d\u305f\u3044\u3082\u306e\u3060\u3002";
   }

   public void move(MaruAdventure main) {
      int[] tarx = new int[1];
      int[] tary = new int[1];
      if(main.gnFrameCounter % 50 != 0 && main.gnFrameCounter % 50 != 1 && main.gnFrameCounter % 50 != 2 && main.gnFrameCounter % 50 != 3 && main.gnFrameCounter % 50 != 4 && main.gnFrameCounter % 50 != 5 && main.gnFrameCounter % 50 != 6 && main.gnFrameCounter % 50 != 7 && main.gnBossSpeak <= 0) {
         if(!this.bWay) {
            this.charaMain.vectorShoottarget2(0, 0, this.oldx, this.oldy, 10, this.thita, tarx, tary);
            this.thita += 5;
            this.charaMain.nPosBodyY = tary[0] + 14;
            if(this.thita == 360) {
               this.thita = 0;
            } else if(this.thita == 90) {
               this.bWay = true;
               this.thita = 270;
            }
         } else {
            this.charaMain.vectorShoottarget2(0, 0, this.oldx, this.oldy, 10, this.thita, tarx, tary);
            this.thita -= 5;
            this.charaMain.nPosBodyY = tary[0] + 34;
            if(this.thita == 0) {
               this.thita = 360;
            } else if(this.thita == 270) {
               this.bWay = false;
               this.thita = 90;
            }
         }

         this.charaMain.nPosBodyX = tarx[0] + 48;
         this.oldx = tarx[0];
         this.oldy = tary[0];
      }
   }

   public void beamBirth(MaruAdventure main) {
      if(this.charaMain.nLife != 0 && main.gnBossSpeak <= 0) {
         if(main.gnFrameCounter % 50 == 0 || main.gnFrameCounter % 50 == 1 || main.gnFrameCounter % 50 == 2 || main.gnFrameCounter % 50 == 3 || main.gnFrameCounter % 50 == 4 || main.gnFrameCounter % 50 == 5 || main.gnFrameCounter % 50 == 6 || main.gnFrameCounter % 50 == 7) {
            for(int i = 0; i < this.charaMain.nBeamMax; ++i) {
               if(!this.charaMain.nBeamAct[i]) {
                  this.charaMain.nBeamAct[i] = true;
                  this.charaMain.nPosBeamX[i] = this.charaMain.nPosBodyX;
                  this.charaMain.nPosBeamY[i] = this.charaMain.nPosBodyY;
                  this.beam_x[i] = this.charaMain.nPosBodyX - 6;
                  this.beam_y[i] = this.charaMain.nPosBodyY;
                  this.beam_oldx[i] = 6;
                  this.beam_oldy[i] = 0;
                  this.beam_t[i] = 330;
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
            if(this.beam_t[i] <= 360 && this.beam_t[i] > 180) {
               this.charaMain.vectorShoottarget2(0, 0, this.beam_oldx[i], this.beam_oldy[i], 6, this.beam_t[i], tarx, tary);
            } else {
               this.charaMain.vectorShoottarget2(0, 0, this.beam_oldx[i], this.beam_oldy[i], 3, this.beam_t[i], tarx, tary);
            }

            this.beam_oldx[i] = tarx[0];
            this.beam_oldy[i] = tary[0];
            this.charaMain.nPosBeamX[i] = tarx[0] + this.beam_x[i];
            this.charaMain.nPosBeamY[i] = tary[0] + this.beam_y[i];
            this.charaMain.drawCharaBeam(main, g, 21, i);
            if(this.charaMain.nPosBeamX[i] < 0) {
               this.charaMain.nBeamAct[i] = false;
            }

            this.beam_t[i] -= 30;
            if(this.beam_t[i] == 180) {
               this.beam_oldx[i] = -3;
               this.beam_oldy[i] = 0;
               this.beam_x[i] -= 3;
            } else if(this.beam_t[i] == 0) {
               this.beam_oldx[i] = 6;
               this.beam_oldy[i] = 0;
               this.beam_x[i] -= 3;
               this.beam_t[i] = 360;
            }
         }
      }

   }
}
