package net.minecraft.scoreboard;

import java.util.List;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;

public class ScoreDummyCriteria implements IScoreObjectiveCriteria {

   private final String field_96644_g;


   public ScoreDummyCriteria(String var1) {
      this.field_96644_g = var1;
      IScoreObjectiveCriteria.field_96643_a.put(var1, this);
   }

   public String func_96636_a() {
      return this.field_96644_g;
   }

   public int func_96635_a(List var1) {
      return 0;
   }

   public boolean isReadOnly() {
      return false;
   }
}
