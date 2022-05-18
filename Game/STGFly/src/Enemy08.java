// Decompiled by:       Fernflower v0.6
// Date:                05.11.2010 13:50:17
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

import java.awt.Graphics;

class Enemy08 {

   boolean bWay = false;
   int[] xway = new int[256];
   int[] yway = new int[256];
   int move = 0;
   Character charaMain;


   Enemy08(Character c) {
      this.charaMain = c;
   }

   public void initData(MaruAdventure main) {
      this.charaMain.nPosBodyX = 63;
      this.charaMain.nPosBodyY = main.rnd.nextInt(48);
      this.bWay = false;
      this.move = 0;

      for(int i = 0; i < 256; ++i) {
         this.charaMain.nPosBeamX[i] = 0;
         this.charaMain.nPosBeamY[i] = 0;
         this.charaMain.nBeamAct[i] = false;
      }

      this.charaMain.nBeamMax = 256;
      this.charaMain.nLife = 4;
      this.charaMain.nPwr = 1;
      this.charaMain.bBeamRange = true;
      this.charaMain.nScene = 0;
      this.charaMain.nScore = 12800;
   }

   public void move(MaruAdventure main) {
      if(this.move == 0) {
         if(main.charaHiroin.nPosBodyY >= this.charaMain.nPosBodyY) {
            this.move = 1;
         } else {
            this.move = 2;
         }
      }

      if(this.move == 1) {
         ++this.charaMain.nPosBodyY;
      } else {
         --this.charaMain.nPosBodyY;
      }

      this.charaMain.nPosBodyX -= 2;
      if(this.charaMain.nPosBodyX < 0 || this.charaMain.nPosBodyY < 0 || this.charaMain.nPosBodyY > 48) {
         this.charaMain.nLife = 0;
      }

   }

   public void beamBirth(MaruAdventure main) {
      int cnt = 0;
      short offset = 0;
      int[] thita = new int[1];
      int[] tarx = new int[1];
      int[] tary = new int[1];
      if(this.charaMain.nLife != 0) {
         if(main.gnFrameCounter % 10 == 0) {
            for(int i = 0; i < this.charaMain.nBeamMax; ++i) {
               if(!this.charaMain.nBeamAct[i]) {
                  this.charaMain.nBeamAct[i] = true;
                  switch(cnt) {
                  case 0:
                  case 12:
                  case 24:
                     offset = 0;
                     break;
                  case 1:
                  case 13:
                  case 25:
                     offset = 30;
                     break;
                  case 2:
                  case 14:
                  case 26:
                     offset = -30;
                     break;
                  case 3:
                  case 15:
                  case 27:
                     offset = 60;
                     break;
                  case 4:
                  case 16:
                  case 28:
                     offset = -60;
                     break;
                  case 5:
                  case 17:
                  case 29:
                     offset = 90;
                     break;
                  case 6:
                  case 18:
                  case 30:
                     offset = -90;
                     break;
                  case 7:
                  case 19:
                  case 31:
                     offset = 120;
                     break;
                  case 8:
                  case 20:
                  case 32:
                     offset = -120;
                     break;
                  case 9:
                  case 21:
                  case 33:
                     offset = 150;
                     break;
                  case 10:
                  case 22:
                  case 34:
                     offset = -150;
                     break;
                  case 11:
                  case 23:
                  case 35:
                     offset = 180;
                  }

                  this.charaMain.vectorShoottarget2(this.charaMain.nPosBodyX, this.charaMain.nPosBodyY, main.charaHiroin.nPosBodyX, main.charaHiroin.nPosBodyY, 3, thita[0] + offset, tarx, tary);
                  this.xway[i] = tarx[0];
                  this.yway[i] = tary[0];
                  if(cnt >= 0 && cnt < 12) {
                     this.charaMain.nPosBeamX[i] = this.charaMain.nPosBodyX + tarx[0] * 2;
                     this.charaMain.nPosBeamY[i] = this.charaMain.nPosBodyY + tary[0] * 2;
                  } else if(cnt >= 12 && cnt < 24) {
                     this.charaMain.nPosBeamX[i] = this.charaMain.nPosBodyX + tarx[0] * 1;
                     this.charaMain.nPosBeamY[i] = this.charaMain.nPosBodyY + tary[0] * 1;
                  } else {
                     this.charaMain.nPosBeamX[i] = this.charaMain.nPosBodyX + tarx[0] * 0;
                     this.charaMain.nPosBeamY[i] = this.charaMain.nPosBodyY + tary[0] * 0;
                  }

                  ++cnt;
                  if(cnt == 36) {
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

            this.charaMain.drawCharaBeam(main, g, 13, i);
         }
      }

   }
}
