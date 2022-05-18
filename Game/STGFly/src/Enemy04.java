// Decompiled by:       Fernflower v0.6
// Date:                05.11.2010 13:50:16
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

import java.awt.Graphics;

class Enemy04 {

   int beam_Xway = 0;
   int beam_Yway = 0;
   int move = 0;
   Character charaMain;


   Enemy04(Character c) {
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
      this.charaMain.nBeamMax = 10;
      this.charaMain.nLife = 2;
      this.charaMain.nPwr = 1;
      this.charaMain.bBeamRange = false;
      this.charaMain.nScene = 0;
      this.charaMain.nScore = 800;
   }

   public void move(MaruAdventure main) {
      if(main.gnFrameCounter % 24 != 0 && main.gnFrameCounter % 24 != 1) {
         if(this.move == 0) {
            --this.charaMain.nPosBodyX;
            if(this.charaMain.nPosBodyX <= 48) {
               if(main.charaHiroin.nPosBodyY >= this.charaMain.nPosBodyY) {
                  this.move = 1;
               } else {
                  this.move = 2;
               }
            }
         } else if(this.move != 1 && this.move != 2) {
            if(this.move != 3 && this.move != 4) {
               if(this.move != 5 && this.move != 6) {
                  if(this.move != 7 && this.move != 8) {
                     if(this.move != 9 && this.move != 10) {
                        --this.charaMain.nPosBodyX;
                     } else {
                        ++this.charaMain.nPosBodyX;
                        if(this.move == 9) {
                           ++this.charaMain.nPosBodyY;
                           if(this.charaMain.nPosBodyX == 24) {
                              this.move = 11;
                           }
                        } else {
                           --this.charaMain.nPosBodyY;
                           if(this.charaMain.nPosBodyX == 24) {
                              this.move = 12;
                           }
                        }
                     }
                  } else {
                     --this.charaMain.nPosBodyX;
                     if(this.charaMain.nPosBodyX <= 16) {
                        if(this.move == 7) {
                           this.move = 9;
                        } else {
                           this.move = 10;
                        }
                     }
                  }
               } else {
                  ++this.charaMain.nPosBodyX;
                  if(this.move == 5) {
                     ++this.charaMain.nPosBodyY;
                     if(this.charaMain.nPosBodyX == 40) {
                        this.move = 7;
                     }
                  } else {
                     --this.charaMain.nPosBodyY;
                     if(this.charaMain.nPosBodyX == 40) {
                        this.move = 8;
                     }
                  }
               }
            } else {
               --this.charaMain.nPosBodyX;
               if(this.charaMain.nPosBodyX <= 32) {
                  if(this.move == 3) {
                     this.move = 5;
                  } else {
                     this.move = 6;
                  }
               }
            }
         } else {
            ++this.charaMain.nPosBodyX;
            if(this.move == 1) {
               ++this.charaMain.nPosBodyY;
               if(this.charaMain.nPosBodyX == 56) {
                  this.move = 3;
               }
            } else {
               --this.charaMain.nPosBodyY;
               if(this.charaMain.nPosBodyX == 56) {
                  this.move = 4;
               }
            }
         }

         if(this.charaMain.nPosBodyX < 0 || this.charaMain.nPosBodyY < 0 || this.charaMain.nPosBodyY > 48) {
            this.charaMain.nLife = 0;
         }

      }
   }

   public void beamBirth(MaruAdventure main) {
      int[] thita = new int[1];
      int[] tarx = new int[1];
      int[] tary = new int[1];
      if(this.charaMain.nLife != 0) {
         if(main.gnFrameCounter % 24 == 0 || main.gnFrameCounter % 24 == 1) {
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
            this.charaMain.nPosBeamX[i] -= 2;
            if(this.charaMain.nPosBeamX[i] < 0 || this.charaMain.nPosBeamX[i] > 64 || this.charaMain.nPosBeamY[i] < 0 || this.charaMain.nPosBeamY[i] > 48) {
               this.charaMain.nBeamAct[i] = false;
            }

            this.charaMain.drawCharaBeam(main, g, 11, i);
         }
      }

   }
}
