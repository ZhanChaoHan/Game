// Decompiled by:       Fernflower v0.6
// Date:                05.11.2010 13:50:14
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

import java.awt.Graphics;

class Camus {

   int move_x = 0;
   int move_y = 0;
   int[] beam_way = new int[256];
   String[] speak_str = new String[3];
   int speak_x = 350;
   int speak_y = 110;
   int speak_w = 200;
   int speak_h = 85;
   Character charaMain;


   Camus(Character c) {
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
      this.charaMain.nLife = 105;
      this.charaMain.nPwr = 1;
      this.charaMain.bBeamRange = true;
      this.charaMain.nScene = 0;
      this.charaMain.nScore = 80000;
      this.charaMain.vectorShoottarget(this.charaMain.nPosBodyX, this.charaMain.nPosBodyY, main.charaHiroin.nPosBodyX, main.charaHiroin.nPosBodyY, 3, tarx, tary, thita);
      this.move_x = tarx[0];
      this.move_y = tary[0];
      this.speak_str[0] = "\u3088\u304f\u3053\u3093\u306a\u3068\u3053\u308d\u307e\u3067\u3084\u3063\u3066\u3053\u308c\u305f\u308f\u306d\u3002\u3000\u3067\u3082\u79c1\u306f\u4eca\u307e\u3067\u306e\u3088\u3046\u306b\u306f\u3044\u304b\u306a\u3044\u308f\u3088\uff01";
      this.speak_str[1] = "\u5f1f\u5b50\u98a8\u60c5\u3067\u3088\u304f\u3053\u3093\u306a\u3068\u3053\u308d\u307e\u3067\u3084\u3063\u3066\u3053\u308c\u305f\u308f\u306d\u3002\u3067\u3082\u3001\u5f37\u904b\u3082\u3053\u308c\u307e\u3067\u3088\u3002";
      this.speak_str[2] = "\u30de\u30a1\u30eb\u6bbf\u304c\u7814\u7a76\u3057\u3066\u3044\u305f\u3068\u3044\u3046\u751f\u547d\u5175\u5668\u306d\u3002\u76f8\u624b\u306b\u3068\u3063\u3066\u4e0d\u8db3\u306f\u306a\u3044\uff01";
   }

   public void move(MaruAdventure main) {
      int[] thita = new int[1];
      int[] tarx = new int[1];
      int[] tary = new int[1];
      if(main.gnBossSpeak <= 0) {
         this.charaMain.nPosBodyX += this.move_x;
         this.charaMain.nPosBodyY += this.move_y;
         if(this.charaMain.nPosBodyX < 0) {
            this.charaMain.nPosBodyX = 64;
            this.charaMain.vectorShoottarget(this.charaMain.nPosBodyX, this.charaMain.nPosBodyY, main.charaHiroin.nPosBodyX, main.charaHiroin.nPosBodyY, 3, tarx, tary, thita);
            this.move_x = tarx[0];
            this.move_y = tary[0];
         } else if(this.charaMain.nPosBodyY < 0 || this.charaMain.nPosBodyY > 48) {
            this.charaMain.nPosBodyX = 64;
            this.charaMain.nPosBodyY = main.rnd.nextInt(48);
            this.charaMain.vectorShoottarget(this.charaMain.nPosBodyX, this.charaMain.nPosBodyY, main.charaHiroin.nPosBodyX, main.charaHiroin.nPosBodyY, 3, tarx, tary, thita);
            this.move_x = tarx[0];
            this.move_y = tary[0];
         }

      }
   }

   public void beamBirth(MaruAdventure main) {
      int cnt = 0;
      byte offset = 0;
      if(this.charaMain.nLife != 0 && main.gnBossSpeak <= 0) {
         if(main.gnFrameCounter % 7 == 0) {
            for(int i = 0; i < this.charaMain.nBeamMax; ++i) {
               if(!this.charaMain.nBeamAct[i]) {
                  this.charaMain.nBeamAct[i] = true;
                  switch(cnt) {
                  case 0:
                     offset = 0;
                     break;
                  case 1:
                     offset = 0;
                     break;
                  case 2:
                     offset = 3;
                     break;
                  case 3:
                     offset = -3;
                     break;
                  case 4:
                     offset = 6;
                     break;
                  case 5:
                     offset = -6;
                     break;
                  case 6:
                     offset = 9;
                     break;
                  case 7:
                     offset = -9;
                     break;
                  case 8:
                     offset = 12;
                     break;
                  case 9:
                     offset = -12;
                     break;
                  case 10:
                     offset = 15;
                     break;
                  case 11:
                     offset = -15;
                  }

                  if(cnt % 2 == 0) {
                     this.beam_way[i] = 2;
                  } else {
                     this.beam_way[i] = -2;
                  }

                  this.charaMain.nPosBeamX[i] = this.charaMain.nPosBodyX;
                  this.charaMain.nPosBeamY[i] = this.charaMain.nPosBodyY + offset;
                  ++cnt;
                  if(cnt == 12) {
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
            this.charaMain.nPosBeamY[i] += this.beam_way[i];
            if(this.charaMain.nPosBeamX[i] < 0 || this.charaMain.nPosBeamY[i] < 0 || this.charaMain.nPosBeamY[i] > 48) {
               this.charaMain.nBeamAct[i] = false;
            }

            this.charaMain.drawCharaBeam(main, g, 23, i);
         }
      }

   }
}
