// Decompiled by:       Fernflower v0.6
// Date:                05.11.2010 13:50:15
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

import java.awt.Graphics;
import java.awt.Image;

public class Character {

   public static final int BEAM_MAX = 256;
   public static final int SCENE_MAX = 2;
   public static final int CHARA_WIDTH = 70;
   public static final int BEAM_WIDTH_BIG = 30;
   public static final int BEAM_WIDTH_SMALL = 10;
   public static final int POS_WIDTH = 64;
   public static final int POS_HEIGHT = 48;
   public static final int CHARA_MARU = 0;
   public static final int CHARA_CAT = 1;
   public static final int CHARA_CHIFFON = 2;
   public static final int CHARA_ENEMY01 = 10;
   public static final int CHARA_ENEMY02 = 11;
   public static final int CHARA_ENEMY03 = 12;
   public static final int CHARA_ENEMY04 = 13;
   public static final int CHARA_ENEMY05 = 14;
   public static final int CHARA_ENEMY06 = 15;
   public static final int CHARA_ENEMY07 = 16;
   public static final int CHARA_ENEMY08 = 17;
   public static final int CHARA_KOYUKI = 20;
   public static final int CHARA_YUN = 21;
   public static final int CHARA_ELL = 22;
   public static final int CHARA_CAMUS = 23;
   public static final int CHARA_MALI = 24;
   public static final int CHARA_MARU_OVER = 30;
   public static final int CHARA_CAT_OVER = 31;
   public static final int CHARA_CHIFFON_OVER = 32;
   public static final int CHARA_ITEM = 40;
   public static final int LIFE_ENEMY01 = 1;
   public static final int LIFE_ENEMY02 = 1;
   public static final int LIFE_ENEMY03 = 2;
   public static final int LIFE_ENEMY04 = 2;
   public static final int LIFE_ENEMY05 = 3;
   public static final int LIFE_ENEMY06 = 3;
   public static final int LIFE_ENEMY07 = 4;
   public static final int LIFE_ENEMY08 = 4;
   public static final int LIFE_KOYUKI = 30;
   public static final int LIFE_YUN = 55;
   public static final int LIFE_ELL = 80;
   public static final int LIFE_CAMUS = 105;
   public static final int LIFE_MALI = 130;
   public static final int BEAM_STEP_MARU = 2;
   public static final double OND_DO = 0.017453292519943295D;
   int nPosBodyX;
   int nPosBodyY;
   int[] nPosShadowX = new int[255];
   int[] nPosShadowY = new int[255];
   int[] nPosBeamX = new int[256];
   int[] nPosBeamY = new int[256];
   boolean[] nBeamAct = new boolean[256];
   int nBeamMax;
   int nLife;
   int nPwr;
   boolean bBeamRange;
   int nZombi;
   int nScene;
   int nScore;
   int nBomNum;
   int nHiroinType = 0;
   int nEnemyType = 0;
   int nStep;
   Enemy01 enemy01 = new Enemy01(this);
   Enemy02 enemy02 = new Enemy02(this);
   Enemy03 enemy03 = new Enemy03(this);
   Enemy04 enemy04 = new Enemy04(this);
   Enemy05 enemy05 = new Enemy05(this);
   Enemy06 enemy06 = new Enemy06(this);
   Enemy07 enemy07 = new Enemy07(this);
   Enemy08 enemy08 = new Enemy08(this);
   Koyuki koyuki = new Koyuki(this);
   Yun yun = new Yun(this);
   Ell ell = new Ell(this);
   Camus camus = new Camus(this);
   Mali mali = new Mali(this);
   Item item = new Item(this);


   public void drawCharaBody(MaruAdventure main, Graphics g, int type) {
      Image img = null;
      switch(type) {
      case 0:
         if(this.nZombi > 0) {
            img = main.imgMaruZombi;
         } else {
            img = main.imgMaruBody;
         }
         break;
      case 1:
         if(this.nZombi > 0) {
            img = main.imgCatZombi;
         } else {
            img = main.imgCatBody;
         }
         break;
      case 2:
         if(this.nZombi > 0) {
            img = main.imgChiffonZombi;
         } else {
            img = main.imgChiffonBody;
         }
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 18:
      case 19:
      case 25:
      case 26:
      case 27:
      case 28:
      case 29:
      case 33:
      case 34:
      case 35:
      case 36:
      case 37:
      case 38:
      case 39:
      default:
         break;
      case 10:
         img = main.imgEnemy01Body;
         break;
      case 11:
         img = main.imgEnemy02Body;
         break;
      case 12:
         img = main.imgEnemy03Body;
         break;
      case 13:
         img = main.imgEnemy04Body;
         break;
      case 14:
         img = main.imgEnemy05Body;
         break;
      case 15:
         img = main.imgEnemy06Body;
         break;
      case 16:
         img = main.imgEnemy07Body;
         break;
      case 17:
         img = main.imgEnemy08Body;
         break;
      case 20:
         img = main.imgKoyukiBody;
         break;
      case 21:
         img = main.imgYunBody;
         break;
      case 22:
         img = main.imgEllBody;
         break;
      case 23:
         img = main.imgCamusBody;
         break;
      case 24:
         img = main.imgMaliBody;
         break;
      case 30:
         img = main.imgMaruOver;
         break;
      case 31:
         img = main.imgCatOver;
         break;
      case 32:
         img = main.imgChiffonOver;
         break;
      case 40:
         img = main.imgItem;
      }

      if(type == 1) {
         if(this.nPwr >= 4) {
            g.drawImage(main.imgCatShadow, this.nPosShadowX[2] * 10 - 35, this.nPosShadowY[2] * 10 - 35, main);
         }

         if(this.nPwr >= 3) {
            g.drawImage(main.imgCatShadow, this.nPosShadowX[1] * 10 - 35, this.nPosShadowY[1] * 10 - 35, main);
         }

         if(this.nPwr >= 2) {
            g.drawImage(main.imgCatShadow, this.nPosShadowX[0] * 10 - 35, this.nPosShadowY[0] * 10 - 35, main);
         }
      }

      g.drawImage(img, this.nPosBodyX * 10 - 35, this.nPosBodyY * 10 - 35, main);
   }

   public void drawCharaBeam(MaruAdventure main, Graphics g, int type, int beam_num) {
      Image img = null;
      switch(type) {
      case 0:
         img = main.imgMaruBeam[this.nPwr - 1];
         break;
      case 1:
         img = main.imgCatBeam[this.nPwr - 1];
         break;
      case 2:
         img = main.imgChiffonBeam[this.nPwr - 1];
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      default:
         break;
      case 10:
         img = main.imgEnemy01Beam;
         break;
      case 11:
         img = main.imgEnemy02Beam;
         break;
      case 12:
         img = main.imgEnemy03Beam;
         break;
      case 13:
         img = main.imgEnemy04Beam;
         break;
      case 20:
         img = main.imgKoyukiBeam;
         break;
      case 21:
         img = main.imgYunBeam;
         break;
      case 22:
         img = main.imgEllBeam;
         break;
      case 23:
         img = main.imgCamusBeam;
         break;
      case 24:
         img = main.imgMaliBeam;
      }

      byte beamsize;
      if(this.bBeamRange) {
         beamsize = 30;
      } else {
         beamsize = 10;
      }

      g.drawImage(img, this.nPosBeamX[beam_num] * 10 - beamsize / 2, this.nPosBeamY[beam_num] * 10 - beamsize / 2, main);
   }

   public void vectorShoottarget(int nNowX, int nNowY, int nTargetX, int nTargetY, int nVect, int[] pnVectX, int[] pnVectY, int[] pnThita) {
      double nDisX = (double)(nTargetX - nNowX);
      double nDisY = (double)(nTargetY - nNowY);
      double nDis = Math.sqrt(Math.pow(nDisX, 2.0D) + Math.pow(nDisY, 2.0D));
      if(nDisX < 0.0D) {
         pnVectX[0] = (int)(nDisX * (double)nVect / nDis - 0.5D);
      } else {
         pnVectX[0] = (int)(nDisX * (double)nVect / nDis + 0.5D);
      }

      if(nDisY < 0.0D) {
         pnVectY[0] = (int)(nDisY * (double)nVect / nDis - 0.5D);
      } else {
         pnVectY[0] = (int)(nDisY * (double)nVect / nDis + 0.5D);
      }

      double rad = Math.acos(nDisX / nDis);
      pnThita[0] = (int)(rad / 0.017453292519943295D + 0.5D);
      if(nDisY < 0.0D) {
         pnThita[0] = 360 - pnThita[0];
      }

   }

   public void vectorShoottarget2(int nNowX, int nNowY, int nTargetX, int nTargetY, int nVect, int nThita, int[] pnVectX, int[] pnVectY) {
      double nDisX = (double)(nTargetX - nNowX);
      double nDisY = (double)(nTargetY - nNowY);
      double nDis = Math.sqrt(Math.pow(nDisX, 2.0D) + Math.pow(nDisY, 2.0D));
      double nVctX = nDis * Math.cos(0.017453292519943295D * (double)nThita);
      double nVctY = nDis * Math.sin(0.017453292519943295D * (double)nThita);
      double hoseiX;
      double hoseiY;
      if(nThita >= 0 && nThita < 90) {
         hoseiX = 0.5D;
         hoseiY = 0.5D;
      } else if(nThita >= 90 && nThita < 180) {
         hoseiX = -0.5D;
         hoseiY = 0.5D;
      } else if(nThita >= 180 && nThita < 270) {
         hoseiX = -0.5D;
         hoseiY = -0.5D;
      } else {
         hoseiX = 0.5D;
         hoseiY = -0.5D;
      }

      pnVectX[0] = (int)(nVctX * (double)nVect / nDis + hoseiX);
      pnVectY[0] = (int)(nVctY * (double)nVect / nDis + hoseiY);
   }

   public void vectorNewTarget(int nNowX, int nNowY, int nTargetX, int nTargetY, int[] nNewTargetX, int[] nNewTargetY) {
      double nDisX = (double)(nTargetX - nNowX);
      double nDisY = (double)(nTargetY - nNowY);
      double tarX;
      double tarY;
      if(nDisX >= 0.0D) {
         tarY = (double)(64 - nNowX) * nDisY / nDisX;
         tarY += (double)nNowY;
         if(tarY >= 0.0D && tarY <= 48.0D) {
            nNewTargetY[0] = (int)tarY;
            nNewTargetX[0] = 64;
         } else if(nDisY >= 0.0D) {
            tarX = (double)(48 - nNowY) * nDisX / nDisY;
            tarX += (double)nNowX;
            nNewTargetX[0] = (int)tarX;
            nNewTargetY[0] = 48;
         } else {
            tarX = (double)(0 - nNowY) * nDisX / nDisY;
            tarX += (double)nNowX;
            nNewTargetX[0] = (int)tarX;
            nNewTargetY[0] = 0;
         }
      } else {
         tarY = (double)(0 - nNowX) * nDisY / nDisX;
         tarY += (double)nNowY;
         if(tarY >= 0.0D && tarY <= 48.0D) {
            nNewTargetY[0] = (int)tarY;
            nNewTargetX[0] = 0;
         } else if(nDisY >= 0.0D) {
            tarX = (double)(48 - nNowY) * nDisX / nDisY;
            tarX += (double)nNowX;
            nNewTargetX[0] = (int)tarX;
            nNewTargetY[0] = 48;
         } else {
            tarX = (double)(0 - nNowY) * nDisX / nDisY;
            tarX += (double)nNowX;
            nNewTargetX[0] = (int)tarX;
            nNewTargetY[0] = 0;
         }
      }

   }

   public void initHiroin(MaruAdventure main) {
      this.nPosBodyX = 4;
      this.nPosBodyY = 24;

      for(int i = 0; i < 256; ++i) {
         this.nPosBeamX[i] = 0;
         this.nPosBeamY[i] = 0;
         this.nBeamAct[i] = false;
      }

      this.nBeamMax = 6;
      this.nLife = 6;
      this.nZombi = 0;
      this.nScene = 0;
      this.nPwr = 1;
      this.nBomNum = 3;
      this.bBeamRange = false;
      switch(this.nHiroinType) {
      case 0:
      default:
         this.nStep = 1;
         break;
      case 1:
         this.nStep = 2;
         break;
      case 2:
         this.nStep = 1;
      }

   }

   public void actionHiroin(MaruAdventure main, Graphics g) {
      this.beamActionHiroin(main, g);
      this.drawCharaBody(main, g, this.nHiroinType);
   }

   public void beamBirthHiroin() {
      int i = 0;
      if(this.nHiroinType == 1) {
         do {
            if(!this.nBeamAct[i]) {
               if(this.nPwr >= 4) {
                  this.nBeamAct[i + 3] = true;
                  this.nPosBeamX[i + 3] = this.nPosShadowX[2];
                  this.nPosBeamY[i + 3] = this.nPosShadowY[2];
               }

               if(this.nPwr >= 3) {
                  this.nBeamAct[i + 2] = true;
                  this.nPosBeamX[i + 2] = this.nPosShadowX[1];
                  this.nPosBeamY[i + 2] = this.nPosShadowY[1];
               }

               if(this.nPwr >= 2) {
                  this.nBeamAct[i + 1] = true;
                  this.nPosBeamX[i + 1] = this.nPosShadowX[0];
                  this.nPosBeamY[i + 1] = this.nPosShadowY[0];
               }

               this.nBeamAct[i] = true;
               this.nPosBeamX[i] = this.nPosBodyX;
               this.nPosBeamY[i] = this.nPosBodyY;
               break;
            }

            i += this.nPwr;
         } while(i < this.nBeamMax);
      } else if(this.nHiroinType == 2) {
         if(this.nPwr >= 1) {
            for(i = 0; i < 3; ++i) {
               this.nBeamAct[i] = true;
               this.nPosBeamX[i] = this.nPosBodyX + 8 * (i + 1);
               this.nPosBeamY[i] = this.nPosBodyY;
               this.nBeamAct[i + 3] = true;
               this.nPosBeamX[i + 3] = this.nPosBodyX - 8 * (i + 1);
               this.nPosBeamY[i + 3] = this.nPosBodyY;
            }
         }

         if(this.nPwr >= 2) {
            for(i = 0; i < 3; ++i) {
               this.nBeamAct[i + 6] = true;
               this.nPosBeamX[i + 6] = this.nPosBodyX;
               this.nPosBeamY[i + 6] = this.nPosBodyY + 6 * (i + 1);
               this.nBeamAct[i + 9] = true;
               this.nPosBeamX[i + 9] = this.nPosBodyX;
               this.nPosBeamY[i + 9] = this.nPosBodyY - 6 * (i + 1);
            }
         }

         if(this.nPwr >= 3) {
            for(i = 0; i < 3; ++i) {
               this.nBeamAct[i + 12] = true;
               this.nPosBeamX[i + 12] = this.nPosBodyX + 8 * (i + 1);
               this.nPosBeamY[i + 12] = this.nPosBodyY + 6 * (i + 1);
               this.nBeamAct[i + 15] = true;
               this.nPosBeamX[i + 15] = this.nPosBodyX + 8 * (i + 1);
               this.nPosBeamY[i + 15] = this.nPosBodyY - 6 * (i + 1);
            }
         }

         if(this.nPwr >= 4) {
            for(i = 0; i < 3; ++i) {
               this.nBeamAct[i + 18] = true;
               this.nPosBeamX[i + 18] = this.nPosBodyX - 8 * (i + 1);
               this.nPosBeamY[i + 18] = this.nPosBodyY + 6 * (i + 1);
               this.nBeamAct[i + 21] = true;
               this.nPosBeamX[i + 21] = this.nPosBodyX - 8 * (i + 1);
               this.nPosBeamY[i + 21] = this.nPosBodyY - 6 * (i + 1);
            }
         }
      } else {
         do {
            if(!this.nBeamAct[i]) {
               this.nBeamAct[i] = true;
               this.nPosBeamX[i] = this.nPosBodyX;
               this.nPosBeamY[i] = this.nPosBodyY;
               break;
            }

            ++i;
         } while(i < this.nBeamMax);
      }

   }

   public void beamDeathHiroin() {
      if(this.nHiroinType == 2) {
         for(int i = 0; i < this.nBeamMax; ++i) {
            this.nBeamAct[i] = false;
         }
      }

   }

   public void beamActionHiroin(MaruAdventure main, Graphics g) {
      int i;
      if(this.nHiroinType == 2) {
         for(i = 0; i < this.nBeamMax; ++i) {
            if(this.nBeamAct[i]) {
               this.drawCharaBeam(main, g, this.nHiroinType, i);
            }
         }
      } else {
         for(i = 0; i < this.nBeamMax; ++i) {
            if(this.nBeamAct[i]) {
               this.nPosBeamX[i] += 2;
               if(this.nPosBeamX[i] > 64) {
                  this.nBeamAct[i] = false;
               }

               this.drawCharaBeam(main, g, this.nHiroinType, i);
            }
         }
      }

   }

   public String speakHiroin(int stage) {
      String ret = "";
      switch(stage) {
      case 0:
         switch(this.nHiroinType) {
         case 0:
            ret = "\u96ea\u5973\u306e\u304f\u305b\u306b\u6691\u3063\u82e6\u3057\u3044\u5974\u3058\u3083\u306e\u3046\uff5e\u3002";
            return ret;
         case 1:
            ret = "\u72ac\u3067\u306f\u306a\u3044\u308f\u2026\u3002\u3000\u3000\u3069\u3061\u3089\u304b\u3068\u3044\u3046\u3068\u5b50\u732b\u304b\u3057\u3089\u2026\u3002";
            return ret;
         case 2:
            ret = "\u3046\u2026\u3002\u3000\u3000\u3000\u3000\u3000\u3000\u3000\u304a\u524d\u3001\u3058\u3083\u307e\u3002";
            return ret;
         default:
            return ret;
         }
      case 1:
         switch(this.nHiroinType) {
         case 0:
            ret = "\u9762\u5012\u306a\u3089\u5225\u306b\u6226\u308f\u305a\u3068\u3082\u9053\u3092\u8b72\u3063\u3066\u304f\u308c\u3066\u3082\u3088\u304b\u308d\u3046\u305e\uff1f";
            return ret;
         case 1:
            ret = "\u5b50\u732b\u306a\u306e\u306f\u5916\u898b\u3060\u3051\u3088";
            return ret;
         case 2:
            ret = "\u3046\uff5e\u3002\u3000\u3000\u3000\u3000\u3000\u3000\u3000\u89e6\u308b\u306a\u3063\u3063\u3002";
            return ret;
         default:
            return ret;
         }
      case 2:
         switch(this.nHiroinType) {
         case 0:
            ret = "\u3059\u3063\u3068\u307c\u3051\u305f\u5974\u3058\u3083\u3002\u3053\u3046\u3044\u3046\u8f29\u306f\u82e6\u624b\u3058\u3083\u306e\u3046\u2026\u3002";
            return ret;
         case 1:
            ret = "\u304a\u3068\u306a\u3057\u305d\u3046\u306a\u9854\u3057\u3066\u3059\u3063\u3054\u304f\u6016\u3044\u2026\u2026\u3002";
            return ret;
         case 2:
            ret = "\u3046\u30fc\uff08\u30fb\u30fb\uff03";
            return ret;
         default:
            return ret;
         }
      case 3:
         switch(this.nHiroinType) {
         case 0:
            ret = "\u305d\u3046\u3044\u3046\u4e8b\u3092\u8a00\u3046\u5974\u306b\u9650\u3063\u3066\u3001\u5927\u3057\u305f\u4e8b\u306a\u3044\u3082\u306e\u3058\u3083\uff01";
            return ret;
         case 1:
            ret = "\u2026\u904b\u3060\u3051\u3067\u3053\u3053\u307e\u3067\u6765\u305f\u3068\u601d\u3063\u3066\u3044\u308b\u306e\u306a\u3089\u3070\u3001\u8cb4\u5973\u3082\u5927\u3057\u305f\u4e8b\u306a\u3044\u308f\u306d\u3002";
            return ret;
         case 2:
            ret = "\u30de\u30b9\u30bf\u30fc\u306e\u6575\u3002\u3000\u3000\u3000\u79c1\u306e\u6575\u3002\u3000\u3000\u3000\u3000\u3000\u3000\u304a\u524d\u3001\u5012\u3059\u3002";
            return ret;
         default:
            return ret;
         }
      case 4:
         switch(this.nHiroinType) {
         case 0:
            ret = "\u304a\u304a\u3002\u3000\u3000\u3000\u3000\u3000\u3000\u3000\u3088\u3046\u3084\u304f\u9022\u3048\u305f\u306e\uff5e\u3002\u751f\u553e\u304c\u6b62\u307e\u3089\u3093\u308f\u3044\u3002";
            break;
         case 1:
            ret = "\u306f\u3058\u3081\u307e\u3057\u3066\u3001\u7121\u4f5c\u6cd5\u3068\u306f\u7406\u89e3\u3057\u3066\u304a\u308a\u307e\u3059\u304c\u3001\u4e3b\u4eba\u306e\u8a00\u3044\u4ed8\u3051\u306b\u3088\u308a\u5931\u793c\u3057\u307e\u3059\u3002";
            break;
         case 2:
            ret = "\u3046\u30fc\u30fc\u3002\u3000\u3000\u3000\u3000\u3000\u3000\uff08\u30b3\u30a4\u30c4\u306e\u8840\u2026\u3046\u307e\u3044\u306e\u304b\uff1f\uff09";
         }
      }

      return ret;
   }

   public void enemyInit(MaruAdventure main) {
      switch(this.nEnemyType) {
      case 10:
         this.enemy01.initData(main);
         break;
      case 11:
         this.enemy02.initData(main);
         break;
      case 12:
         this.enemy03.initData(main);
         break;
      case 13:
         this.enemy04.initData(main);
         break;
      case 14:
         this.enemy05.initData(main);
         break;
      case 15:
         this.enemy06.initData(main);
         break;
      case 16:
         this.enemy07.initData(main);
         break;
      case 17:
         this.enemy08.initData(main);
      case 18:
      case 19:
      case 25:
      case 26:
      case 27:
      case 28:
      case 29:
      case 30:
      case 31:
      case 32:
      case 33:
      case 34:
      case 35:
      case 36:
      case 37:
      case 38:
      case 39:
      default:
         break;
      case 20:
         this.koyuki.initData(main);
         break;
      case 21:
         this.yun.initData(main);
         break;
      case 22:
         this.ell.initData(main);
         break;
      case 23:
         this.camus.initData(main);
         break;
      case 24:
         this.mali.initData(main);
         break;
      case 40:
         this.item.initData(main);
      }

   }

   public void enemyAction(MaruAdventure main, Graphics g) {
      switch(this.nEnemyType) {
      case 10:
         this.enemy01.move(main);
         this.enemy01.beamAction(main, g);
         this.enemy01.beamBirth(main);
         break;
      case 11:
         this.enemy02.move(main);
         this.enemy02.beamAction(main, g);
         this.enemy02.beamBirth(main);
         break;
      case 12:
         this.enemy03.move(main);
         this.enemy03.beamAction(main, g);
         this.enemy03.beamBirth(main);
         break;
      case 13:
         this.enemy04.move(main);
         this.enemy04.beamAction(main, g);
         this.enemy04.beamBirth(main);
         break;
      case 14:
         this.enemy05.move(main);
         this.enemy05.beamAction(main, g);
         this.enemy05.beamBirth(main);
         break;
      case 15:
         this.enemy06.move(main);
         this.enemy06.beamAction(main, g);
         this.enemy06.beamBirth(main);
         break;
      case 16:
         this.enemy07.move(main);
         this.enemy07.beamAction(main, g);
         this.enemy07.beamBirth(main);
         break;
      case 17:
         this.enemy08.move(main);
         this.enemy08.beamAction(main, g);
         this.enemy08.beamBirth(main);
      case 18:
      case 19:
      case 25:
      case 26:
      case 27:
      case 28:
      case 29:
      case 30:
      case 31:
      case 32:
      case 33:
      case 34:
      case 35:
      case 36:
      case 37:
      case 38:
      case 39:
      default:
         break;
      case 20:
         this.koyuki.move(main);
         this.koyuki.beamAction(main, g);
         this.koyuki.beamBirth(main);
         break;
      case 21:
         this.yun.move(main);
         this.yun.beamAction(main, g);
         this.yun.beamBirth(main);
         break;
      case 22:
         this.ell.move(main);
         this.ell.beamAction(main, g);
         this.ell.beamBirth(main);
         break;
      case 23:
         this.camus.move(main);
         this.camus.beamAction(main, g);
         this.camus.beamBirth(main);
         break;
      case 24:
         this.mali.move(main);
         this.mali.beamAction(main, g);
         this.mali.beamBirth(main);
         break;
      case 40:
         this.item.move(main);
      }

      if(this.nLife != 0) {
         this.drawCharaBody(main, g, this.nEnemyType);
      }

   }
}
