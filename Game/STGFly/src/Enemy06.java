// Decompiled by:       Fernflower v0.6
// Date:                05.11.2010 13:50:16
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

import java.awt.Graphics;

class Enemy06 {

   int movex = 0;
   int movey = 0;
   Character charaMain;


   Enemy06(Character c) {
      this.charaMain = c;
   }

   public void initData(MaruAdventure main) {
      int[] thita = new int[1];
      int[] tarx = new int[1];
      int[] tary = new int[1];
      this.charaMain.nPosBodyX = 63;
      this.charaMain.nPosBodyY = main.rnd.nextInt(48);
      this.charaMain.vectorShoottarget(this.charaMain.nPosBodyX, this.charaMain.nPosBodyY, main.charaHiroin.nPosBodyX, main.charaHiroin.nPosBodyY, 5, tarx, tary, thita);
      this.movex = tarx[0];
      this.movey = tary[0];

      for(int i = 0; i < 256; ++i) {
         this.charaMain.nPosBeamX[i] = 0;
         this.charaMain.nPosBeamY[i] = 0;
         this.charaMain.nBeamAct[i] = false;
      }

      this.charaMain.nBeamMax = 15;
      this.charaMain.nLife = 3;
      this.charaMain.nPwr = 1;
      this.charaMain.bBeamRange = true;
      this.charaMain.nScene = 0;
      this.charaMain.nScore = 3200;
   }

   public void move(MaruAdventure main) {
      this.charaMain.nPosBodyX += this.movex;
      this.charaMain.nPosBodyY += this.movey;
      if(this.charaMain.nPosBodyX < 0 || this.charaMain.nPosBodyY < 0 || this.charaMain.nPosBodyY > 48) {
         this.charaMain.nLife = 0;
      }

   }

   public void beamBirth(MaruAdventure main) {
   }

   public void beamAction(MaruAdventure main, Graphics g) {
   }
}
