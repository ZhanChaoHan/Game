// Decompiled by:       Fernflower v0.6
// Date:                05.11.2010 13:50:17
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

import java.awt.Graphics;

class Enemy07 {

   boolean bWay = false;
   int[] xway = new int[256];
   int[] yway = new int[256];
   Character charaMain;


   Enemy07(Character c) {
      this.charaMain = c;
   }

   public void initData(MaruAdventure main) {
      this.charaMain.nPosBodyX = 63;
      this.charaMain.nPosBodyY = main.rnd.nextInt(48);
      this.bWay = false;

      for(int i = 0; i < 256; ++i) {
         this.charaMain.nPosBeamX[i] = 0;
         this.charaMain.nPosBeamY[i] = 0;
         this.charaMain.nBeamAct[i] = false;
      }

      this.charaMain.nBeamMax = 100;
      this.charaMain.nLife = 4;
      this.charaMain.nPwr = 1;
      this.charaMain.bBeamRange = true;
      this.charaMain.nScene = 0;
      this.charaMain.nScore = 6400;
   }

   public void move(MaruAdventure main) {
      if(main.gnFrameCounter % 10 != 0 && main.gnFrameCounter % 10 != 1 && main.gnFrameCounter % 10 != 2) {
         if(!this.bWay) {
            --this.charaMain.nPosBodyX;
            if(this.charaMain.nPosBodyX <= 46) {
               this.bWay = true;
            }
         } else {
            ++this.charaMain.nPosBodyX;
         }

         if(this.charaMain.nPosBodyX < 0 || this.charaMain.nPosBodyX > 64 || this.charaMain.nPosBodyY < 0 || this.charaMain.nPosBodyY > 48) {
            this.charaMain.nLife = 0;
         }

      }
   }

   public void beamBirth(MaruAdventure main) {
      int cnt = 0;
      int[] thita = new int[1];
      int[] tarx = new int[1];
      int[] tary = new int[1];
      if(this.charaMain.nLife != 0) {
         if(main.gnFrameCounter % 10 == 0 || main.gnFrameCounter % 10 == 1 || main.gnFrameCounter % 10 == 2) {
            for(int i = 0; i < this.charaMain.nBeamMax; ++i) {
               if(!this.charaMain.nBeamAct[i]) {
                  this.charaMain.nBeamAct[i] = true;
                  this.charaMain.nPosBeamX[i] = this.charaMain.nPosBodyX;
                  this.charaMain.nPosBeamY[i] = this.charaMain.nPosBodyY;
                  switch(cnt) {
                  case 0:
                     this.charaMain.vectorShoottarget(this.charaMain.nPosBodyX, this.charaMain.nPosBodyY, main.charaHiroin.nPosBodyX, main.charaHiroin.nPosBodyY, 3, tarx, tary, thita);
                     this.xway[i] = tarx[0];
                     this.yway[i] = tary[0];
                     break;
                  case 1:
                     this.charaMain.vectorShoottarget2(this.charaMain.nPosBodyX, this.charaMain.nPosBodyY, main.charaHiroin.nPosBodyX, main.charaHiroin.nPosBodyY, 3, thita[0] + 30, tarx, tary);
                     this.xway[i] = tarx[0];
                     this.yway[i] = tary[0];
                     break;
                  case 2:
                     this.charaMain.vectorShoottarget2(this.charaMain.nPosBodyX, this.charaMain.nPosBodyY, main.charaHiroin.nPosBodyX, main.charaHiroin.nPosBodyY, 3, thita[0] - 30, tarx, tary);
                     this.xway[i] = tarx[0];
                     this.yway[i] = tary[0];
                     break;
                  case 3:
                     this.charaMain.vectorShoottarget2(this.charaMain.nPosBodyX, this.charaMain.nPosBodyY, main.charaHiroin.nPosBodyX, main.charaHiroin.nPosBodyY, 3, thita[0] + 60, tarx, tary);
                     this.xway[i] = tarx[0];
                     this.yway[i] = tary[0];
                     break;
                  case 4:
                     this.charaMain.vectorShoottarget2(this.charaMain.nPosBodyX, this.charaMain.nPosBodyY, main.charaHiroin.nPosBodyX, main.charaHiroin.nPosBodyY, 3, thita[0] - 60, tarx, tary);
                     this.xway[i] = tarx[0];
                     this.yway[i] = tary[0];
                  }

                  ++cnt;
                  if(cnt == 5) {
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

            this.charaMain.drawCharaBeam(main, g, 12, i);
         }
      }

   }
}
