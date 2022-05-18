// Decompiled by:       Fernflower v0.6
// Date:                05.11.2010 13:50:15
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

import java.awt.Graphics;

class Enemy01 {

   int beam_Xway = 0;
   int beam_Yway = 0;
   Character charaMain;


   Enemy01(Character c) {
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

      this.charaMain.nBeamMax = 1;
      this.charaMain.nLife = 1;
      this.charaMain.nPwr = 1;
      this.charaMain.bBeamRange = false;
      this.charaMain.nScene = 0;
      this.charaMain.nScore = 100;
      this.beam_Xway = 0;
      this.beam_Yway = 0;
   }

   public void move(MaruAdventure main) {
      --this.charaMain.nPosBodyX;
      if(this.charaMain.nPosBodyX < 0) {
         this.charaMain.nLife = 0;
      }

   }

   public void beamBirth(MaruAdventure main) {
      int[] thita = new int[1];
      int[] tarx = new int[1];
      int[] tary = new int[1];
      if(this.charaMain.nLife != 0) {
         if(main.gnFrameCounter % 32 == 0) {
            for(int i = 0; i < this.charaMain.nBeamMax; ++i) {
               if(!this.charaMain.nBeamAct[i]) {
                  this.charaMain.nBeamAct[i] = true;
                  this.charaMain.nPosBeamX[i] = this.charaMain.nPosBodyX;
                  this.charaMain.nPosBeamY[i] = this.charaMain.nPosBodyY;
                  this.charaMain.vectorShoottarget(this.charaMain.nPosBodyX, this.charaMain.nPosBodyY, main.charaHiroin.nPosBodyX, main.charaHiroin.nPosBodyY, 2, tarx, tary, thita);
                  this.beam_Xway = tarx[0];
                  this.beam_Yway = tary[0];
                  break;
               }
            }
         }

      }
   }

   public void beamAction(MaruAdventure main, Graphics g) {
      for(int i = 0; i < this.charaMain.nBeamMax; ++i) {
         if(this.charaMain.nBeamAct[i]) {
            this.charaMain.nPosBeamX[i] += this.beam_Xway;
            this.charaMain.nPosBeamY[i] += this.beam_Yway;
            if(this.charaMain.nPosBeamX[i] < 0 || this.charaMain.nPosBeamX[i] > 64 || this.charaMain.nPosBeamY[i] < 0 || this.charaMain.nPosBeamY[i] > 48) {
               this.charaMain.nBeamAct[i] = false;
            }

            this.charaMain.drawCharaBeam(main, g, 10, i);
         }
      }

   }
}
