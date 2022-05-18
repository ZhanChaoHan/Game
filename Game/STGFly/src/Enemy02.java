// Decompiled by:       Fernflower v0.6
// Date:                05.11.2010 13:50:16
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

import java.awt.Graphics;

class Enemy02 {

   int move = 0;
   Character charaMain;


   Enemy02(Character c) {
      this.charaMain = c;
   }

   public void initData(MaruAdventure main) {
      this.charaMain.nPosBodyX = 63;
      this.charaMain.nPosBodyY = main.rnd.nextInt(48);

      for(int i = 0; i < 256; ++i) {
         this.charaMain.nPosBeamX[i] = 0;
         this.charaMain.nPosBeamY[i] = 0;
         this.charaMain.nBeamAct[i] = false;
      }

      this.move = 0;
      this.charaMain.nBeamMax = 4;
      this.charaMain.nLife = 1;
      this.charaMain.nPwr = 1;
      this.charaMain.bBeamRange = false;
      this.charaMain.nScene = 0;
      this.charaMain.nScore = 200;
   }

   public void move(MaruAdventure main) {
      --this.charaMain.nPosBodyX;
      if(this.charaMain.nPosBodyX <= 32) {
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
      }

      if(this.charaMain.nPosBodyX < 0 || this.charaMain.nPosBodyY < 0 || this.charaMain.nPosBodyY > 48) {
         this.charaMain.nLife = 0;
      }

   }

   public void beamBirth(MaruAdventure main) {
      if(this.charaMain.nLife != 0) {
         if(main.gnFrameCounter % 21 == 0) {
            for(int i = 0; i < this.charaMain.nBeamMax; ++i) {
               if(!this.charaMain.nBeamAct[i]) {
                  this.charaMain.nBeamAct[i] = true;
                  this.charaMain.nPosBeamX[i] = this.charaMain.nPosBodyX;
                  this.charaMain.nPosBeamY[i] = this.charaMain.nPosBodyY;
                  break;
               }
            }
         }

      }
   }

   public void beamAction(MaruAdventure main, Graphics g) {
      for(int i = 0; i < this.charaMain.nBeamMax; ++i) {
         if(this.charaMain.nBeamAct[i]) {
            this.charaMain.nPosBeamX[i] -= 2;
            if(this.charaMain.nPosBeamX[i] < 0 || this.charaMain.nPosBeamX[i] > 64 || this.charaMain.nPosBeamY[i] < 0 || this.charaMain.nPosBeamY[i] > 48) {
               this.charaMain.nBeamAct[i] = false;
            }

            this.charaMain.drawCharaBeam(main, g, 11, i);
         }
      }

   }
}
