package net.minecraft.scoreboard;

import java.util.Comparator;
import net.minecraft.scoreboard.Score;

final class Score$1 implements Comparator {

   public int compare(Score var1, Score var2) {
      return var1.getScorePoints() > var2.getScorePoints()?1:(var1.getScorePoints() < var2.getScorePoints()?-1:0);
   }

   // $FF: synthetic method
   public int compare(Object var1, Object var2) {
      return this.compare((Score)var1, (Score)var2);
   }
}
