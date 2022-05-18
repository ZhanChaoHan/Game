// Decompiled by:       Fernflower v0.6
// Date:                05.11.2010 13:50:15
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

import java.awt.Graphics;

class Ell {

   int move = 0;
   int[] beam_x = new int[256];
   int[] beam_y = new int[256];
   String[] speak_str = new String[3];
   int speak_x = 330;
   int speak_y = 110;
   int speak_w = 200;
   int speak_h = 85;
   Character charaMain;


   Ell(Character c) {
      this.charaMain = c;
   }

   public void initData(MaruAdventure main) {
      this.charaMain.nPosBodyX = 56;
      this.charaMain.nPosBodyY = 24;

      for(int i = 0; i < 256; ++i) {
         this.charaMain.nPosBeamX[i] = 0;
         this.charaMain.nPosBeamY[i] = 0;
         this.charaMain.nBeamAct[i] = false;
      }

      this.charaMain.nBeamMax = 256;
      this.charaMain.nLife = 80;
      this.charaMain.nPwr = 1;
      this.charaMain.bBeamRange = true;
      this.charaMain.nScene = 0;
      this.charaMain.nScore = '\u9c40';
      this.move = 0;
      this.speak_str[0] = "\u3044\u3089\u3063\u3057\u3083\uff5e\u3044\u3002    \u304a\u30a4\u30bf\u3092\u3059\u308b\u30b3\u306b\u306f\u3001\u304a\u3057\u304a\u304d\u3067\u3059\u308f\u3088\uff5e\u3002";
      this.speak_str[1] = "\u307e\u3041\u307e\u3041\u3001\u53ef\u611b\u3089\u3057\u3044\u5a18\u3067\u3059\u308f\u306d\uff5e\u3002\u3000\u3000\u3000\u2026\u3067\u3082\u98a8\u3067\u6d88\u3057\u98db\u3093\u3067\u9802\u304d\u307e\u3059\u308f\u3088\uff01";
      this.speak_str[2] = "\u4f55\u3092\u8003\u3048\u3066\u3044\u308b\u304b\u308f\u304b\u3089\u306a\u3044\u65b9\u3067\u3059\u308f\u306d\uff5e\u3002\u610f\u5916\u3068\u3080\u3063\u3064\u308a\u3055\u3093\uff1f\uff57\uff57";
   }

   public void move(MaruAdventure main) {
      if(main.gnBossSpeak <= 0) {
         switch(this.move) {
         case 0:
            this.charaMain.nPosBodyY -= 2;
            if(this.charaMain.nPosBodyY == 4) {
               this.move = 1;
            }
            break;
         case 1:
            this.charaMain.nPosBodyX -= 3;
            if(this.charaMain.nPosBodyX == 26) {
               this.move = 2;
            }
            break;
         case 2:
            this.charaMain.nPosBodyX += 3;
            this.charaMain.nPosBodyY += 2;
            if(this.charaMain.nPosBodyX == 56) {
               this.move = 3;
            }
            break;
         case 3:
            this.charaMain.nPosBodyX -= 3;
            this.charaMain.nPosBodyY += 2;
            if(this.charaMain.nPosBodyX == 26) {
               this.move = 4;
            }
            break;
         case 4:
            this.charaMain.nPosBodyX += 3;
            if(this.charaMain.nPosBodyX == 56) {
               this.move = 0;
            }
         }

      }
   }

   public void beamBirth(MaruAdventure main) {
      int[] thita = new int[1];
      int[] tarx = new int[1];
      int[] tary = new int[1];
      if(this.charaMain.nLife != 0 && main.gnBossSpeak <= 0) {
         if(main.gnFrameCounter % 7 == 0) {
            for(int i = 0; i < this.charaMain.nBeamMax; ++i) {
               if(!this.charaMain.nBeamAct[i]) {
                  this.charaMain.nBeamAct[i] = true;
                  this.charaMain.nPosBeamX[i] = this.charaMain.nPosBodyX;
                  this.charaMain.nPosBeamY[i] = this.charaMain.nPosBodyY;
                  this.charaMain.vectorShoottarget(this.charaMain.nPosBodyX, this.charaMain.nPosBodyY, main.charaHiroin.nPosBodyX, main.charaHiroin.nPosBodyY, 2, tarx, tary, thita);
                  this.beam_x[i] = tarx[0];
                  this.beam_y[i] = tary[0];
                  break;
               }
            }
         }

      }
   }

   public void beamAction(MaruAdventure main, Graphics g) {
      for(int i = 0; i < this.charaMain.nBeamMax; ++i) {
         if(this.charaMain.nBeamAct[i]) {
            this.charaMain.nPosBeamX[i] += this.beam_x[i];
            this.charaMain.nPosBeamY[i] += this.beam_y[i];
            if(this.charaMain.nPosBeamX[i] < 0 || this.charaMain.nPosBeamX[i] > 64 || this.charaMain.nPosBeamY[i] < 0 || this.charaMain.nPosBeamY[i] > 48) {
               this.charaMain.nBeamAct[i] = false;
            }

            this.charaMain.drawCharaBeam(main, g, 22, i);
         }
      }

   }
}
