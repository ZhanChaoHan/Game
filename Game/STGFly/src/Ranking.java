// Decompiled by:       Fernflower v0.6
// Date:                05.11.2010 13:50:31
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com


public class Ranking implements Comparable {

   int score;
   String name;


   public int compareTo(Object object) {
      Ranking operand = (Ranking)object;
      return this.score > operand.score?-1:(this.score < operand.score?1:0);
   }
}
