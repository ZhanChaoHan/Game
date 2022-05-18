// Decompiled by:       Fernflower v0.6
// Date:                05.11.2010 13:50:17
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com


class Item {

   Character charaMain;


   Item(Character c) {
      this.charaMain = c;
   }

   public void initData(MaruAdventure main) {
      this.charaMain.nPosBodyX = 63;
      this.charaMain.nPosBodyY = main.rnd.nextInt(48);
      this.charaMain.nLife = 1;
      this.charaMain.nScore = 1000;
   }

   public void move(MaruAdventure main) {
      --this.charaMain.nPosBodyX;
      if(this.charaMain.nPosBodyX < 0) {
         this.charaMain.nLife = 0;
      }

   }
}
