// Decompiled by:       Fernflower v0.6
// Date:                05.11.2010 13:50:18
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

import java.awt.Graphics;

class Koyuki {

   boolean bWay = false;
   int oldx = 15;
   int oldy = 0;
   int thita = 5;
   int[] xway = new int[256];
   int[] yway = new int[256];
   String[] speak_str = new String[3];
   int speak_x = 320;
   int speak_y = 110;
   int speak_w = 200;
   int speak_h = 85;
   Character charaMain;


   Koyuki(Character c) {
      this.charaMain = c;
   }

   public void initData(MaruAdventure main) {
      this.charaMain.nPosBodyX = 55;
      this.charaMain.nPosBodyY = 24;

      for(int i = 0; i < 256; ++i) {
         this.charaMain.nPosBeamX[i] = 0;
         this.charaMain.nPosBeamY[i] = 0;
         this.charaMain.nBeamAct[i] = false;
      }

      this.charaMain.nBeamMax = 256;
      this.charaMain.nLife = 30;
      this.charaMain.nPwr = 1;
      this.charaMain.bBeamRange = false;
      this.charaMain.nScene = 0;
      this.charaMain.nScore = 10000;
      this.bWay = false;
      this.oldx = 15;
      this.oldy = 0;
      this.thita = 5;
      this.speak_str[0] = "\u3053\u3053\u304b\u3089\u5148\u306f\u884c\u304b\u305b\u306a\u3044\u308f\u3088\uff01\u3000\u3000\u3000\u3000\u3000\u3000\u5c0f\u96ea\u3001\u53c2\u308b\uff01";
      this.speak_str[1] = "\u30de\u30a1\u30eb\u6bbf\u306e\u72ac\u3063\u3053\u308d\u304c\u3084\u3063\u3066\u304d\u305f\u304b\uff01\u3000\u3000\u3000\u8fd4\u308a\u8a0e\u3061\u306b\u3057\u3066\u3084\u308b\uff01";
      this.speak_str[2] = "\u7570\u5f62\u306e\u8005\u3081\uff01\u3000\u3000\u3000\u3000\u6210\u6557\u3057\u3066\u304f\u308c\u308b\uff01";
   }

   public void move(MaruAdventure main) {
      int[] tarx = new int[1];
      int[] tary = new int[1];
      if(main.gnFrameCounter % 30 != 0 && main.gnFrameCounter % 30 != 1 && main.gnFrameCounter % 30 != 2 && main.gnBossSpeak <= 0) {
         this.charaMain.vectorShoottarget2(0, 0, this.oldx, this.oldy, 15, this.thita, tarx, tary);
         this.charaMain.nPosBodyX = tarx[0] + 40;
         this.charaMain.nPosBodyY = tary[0] + 24;
         this.thita += 5;
         if(this.thita == 360) {
            this.thita = 0;
         }

         this.oldx = tarx[0];
         this.oldy = tary[0];
      }
   }

   public void beamBirth(MaruAdventure main) {
      int cnt = 0;
      int[] thita = new int[1];
      int[] tarx = new int[1];
      int[] tary = new int[1];
      if(this.charaMain.nLife != 0 && main.gnBossSpeak <= 0) {
         if(main.gnFrameCounter % 30 == 0 || main.gnFrameCounter % 30 == 1 || main.gnFrameCounter % 30 == 2) {
            for(int i = 0; i < this.charaMain.nBeamMax; ++i) {
               if(!this.charaMain.nBeamAct[i]) {
                  this.charaMain.nBeamAct[i] = true;
                  this.charaMain.nPosBeamX[i] = this.charaMain.nPosBodyX;
                  this.charaMain.nPosBeamY[i] = this.charaMain.nPosBodyY;
                  switch(cnt) {
                  case 0:
                     this.charaMain.vectorShoottarget(this.charaMain.nPosBodyX, this.charaMain.nPosBodyY, main.charaHiroin.nPosBodyX, main.charaHiroin.nPosBodyY, 2, tarx, tary, thita);
                     this.xway[i] = tarx[0];
                     this.yway[i] = tary[0];
                     break;
                  case 1:
                     this.charaMain.vectorShoottarget2(this.charaMain.nPosBodyX, this.charaMain.nPosBodyY, main.charaHiroin.nPosBodyX, main.charaHiroin.nPosBodyY, 2, thita[0] + 30, tarx, tary);
                     this.xway[i] = tarx[0];
                     this.yway[i] = tary[0];
                     break;
                  case 2:
                     this.charaMain.vectorShoottarget2(this.charaMain.nPosBodyX, this.charaMain.nPosBodyY, main.charaHiroin.nPosBodyX, main.charaHiroin.nPosBodyY, 2, thita[0] - 30, tarx, tary);
                     this.xway[i] = tarx[0];
                     this.yway[i] = tary[0];
                  }

                  ++cnt;
                  if(cnt == 3) {
                     break;
                  }
               }
            }
         }

      }
   }

   public void beamAction(MaruAdventure main, Graphics g) {
      for(int i = 0; i < this.charaMain.nBeamMax; ++i) {
         if(this.charaMain.nBeamAct[i]) {
            this.charaMain.nPosBeamX[i] += this.xway[i];
            this.charaMain.nPosBeamY[i] += this.yway[i];
            if(this.charaMain.nPosBeamX[i] < 0) {
               this.charaMain.nBeamAct[i] = false;
            }

            this.charaMain.drawCharaBeam(main, g, 20, i);
         }
      }

   }
}
