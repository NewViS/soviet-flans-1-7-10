package net.minecraft.scoreboard;

import java.util.Comparator;
import java.util.List;
import net.minecraft.scoreboard.Score$1;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;

public class Score {

   public static final Comparator field_96658_a = new Score$1();
   private final Scoreboard theScoreboard;
   private final ScoreObjective theScoreObjective;
   private final String scorePlayerName;
   private int field_96655_e;


   public Score(Scoreboard var1, ScoreObjective var2, String var3) {
      this.theScoreboard = var1;
      this.theScoreObjective = var2;
      this.scorePlayerName = var3;
   }

   public void increseScore(int var1) {
      if(this.theScoreObjective.getCriteria().isReadOnly()) {
         throw new IllegalStateException("Cannot modify read-only score");
      } else {
         this.setScorePoints(this.getScorePoints() + var1);
      }
   }

   public void decreaseScore(int var1) {
      if(this.theScoreObjective.getCriteria().isReadOnly()) {
         throw new IllegalStateException("Cannot modify read-only score");
      } else {
         this.setScorePoints(this.getScorePoints() - var1);
      }
   }

   public void func_96648_a() {
      if(this.theScoreObjective.getCriteria().isReadOnly()) {
         throw new IllegalStateException("Cannot modify read-only score");
      } else {
         this.increseScore(1);
      }
   }

   public int getScorePoints() {
      return this.field_96655_e;
   }

   public void setScorePoints(int var1) {
      int var2 = this.field_96655_e;
      this.field_96655_e = var1;
      if(var2 != var1) {
         this.getScoreScoreboard().func_96536_a(this);
      }

   }

   public ScoreObjective func_96645_d() {
      return this.theScoreObjective;
   }

   public String getPlayerName() {
      return this.scorePlayerName;
   }

   public Scoreboard getScoreScoreboard() {
      return this.theScoreboard;
   }

   public void func_96651_a(List var1) {
      this.setScorePoints(this.theScoreObjective.getCriteria().func_96635_a(var1));
   }

}
